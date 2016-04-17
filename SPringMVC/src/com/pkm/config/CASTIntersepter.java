package com.pkm.config;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.omg.CORBA.SystemException;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;



/**
 * This controller is used for About Us Page.
 * 
 * @author nsharma
 * 
 */
public class CASTIntersepter extends HandlerInterceptorAdapter {

	private static transient PKMLogger logger = new PKMLogger(CASTIntersepter.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		logger.debug("Inside CASTicketInterceptor:pre handle");
		try {
			logger.info("Intercepting: " + request.getRequestURI());
			
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
	

	/**
	 * Create Ticekt an dstore sit in the session.
	 * 
	 * @param request
	 * @throws SSOException
	 */
	/*private void createTicket(HttpServletRequest request, long creationTime) throws SSOException {
		logger.debug("No ticket for session Id" + request.getSession().getId() + ". Hence generating a new one.");
		SSOSession ssoSession = null;
		String multiticket;
		ssoSession = SSO.getSSOSession("CASConfig.xml");
		logger.debug("User name" + PropertiesUtil.getProperty("csUsername") + "Password " + PropertiesUtil.getProperty("csPassword"));
		multiticket = ssoSession.getMultiTicket(PropertiesUtil.getProperty("csUsername"), PropertiesUtil.getProperty("csPassword"));
		request.getSession().setAttribute(SESSION_TICKET, multiticket);
		request.getSession().setAttribute(TICKET_CREATION_TIME, creationTime);
		logger.debug("Ticket Id for session Id" + request.getSession().getId() + " is " + multiticket);
	}*/
}
