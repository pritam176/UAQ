/**
 * 
 */
package com.uaq.interceptor;

import static com.uaq.common.ApplicationConstants.CAS_TIME_OUT;
import static com.uaq.common.ApplicationConstants.SESSION_TICKET;
import static com.uaq.common.ApplicationConstants.TICKET_CREATION_TIME;

import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.omg.CORBA.SystemException;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.fatwire.wem.sso.SSO;
import com.fatwire.wem.sso.SSOException;
import com.fatwire.wem.sso.SSOSession;
import com.uaq.common.PropertiesUtil;
import com.uaq.logger.UAQLogger;

/**
 * This controller is used for About Us Page.
 * 
 * @author nsharma
 * 
 */
public class CASTicketInterceptor extends HandlerInterceptorAdapter {

	private static transient UAQLogger logger = new UAQLogger(CASTicketInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		logger.debug("Inside CASTicketInterceptor:pre handle");
		try {
			logger.info("Intercepting: " + request.getRequestURI());
			getTicket(request);
			return true;
		} catch (SystemException e) {
			logger.info("request update failed");
			return false;
		}
	}

	/**
	 * @param request
	 * @return
	 */
	protected void getTicket(HttpServletRequest request) {

		Long creationTime = (Long) request.getSession().getAttribute(TICKET_CREATION_TIME);
		if (null == creationTime) {
			creationTime = request.getSession(true).getCreationTime();
		}
		long currentTime = Calendar.getInstance().getTimeInMillis();
		String multiticket;
		if (currentTime - creationTime < CAS_TIME_OUT) {
			multiticket = (String) request.getSession().getAttribute(SESSION_TICKET);
			if (null == multiticket) {
				try {
					createTicket(request, creationTime);
				} catch (SSOException e) {
					logger.error("Error getting the ticket", e);
				}
			}
		} else {
			try {
				request.getSession().removeAttribute(SESSION_TICKET);
				request.getSession().removeAttribute(TICKET_CREATION_TIME);
				createTicket(request, Calendar.getInstance().getTimeInMillis());
			} catch (SSOException e) {
				logger.error("Error getting the ticket", e);
			}
		}

	}

	/**
	 * Create Ticekt an dstore sit in the session.
	 * 
	 * @param request
	 * @throws SSOException
	 */
	private void createTicket(HttpServletRequest request, long creationTime) throws SSOException {
		logger.debug("No ticket for session Id" + request.getSession().getId() + ". Hence generating a new one.");
		SSOSession ssoSession = null;
		String multiticket;
		ssoSession = SSO.getSSOSession("CASConfig.xml");
		logger.debug("User name" + PropertiesUtil.getProperty("csUsername") + "Password " + PropertiesUtil.getProperty("csPassword"));
		multiticket = ssoSession.getMultiTicket(PropertiesUtil.getProperty("csUsername"), PropertiesUtil.getProperty("csPassword"));
		request.getSession().setAttribute(SESSION_TICKET, multiticket);
		request.getSession().setAttribute(TICKET_CREATION_TIME, creationTime);
		logger.debug("Ticket Id for session Id" + request.getSession().getId() + " is " + multiticket);
	}
}
