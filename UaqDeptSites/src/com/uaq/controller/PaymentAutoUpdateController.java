package com.uaq.controller;

import java.sql.Connection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.uaq.common.ViewPath;
import com.uaq.dao.DAOManager;
import com.uaq.exception.UAQException;
import com.uaq.logger.UAQLogger;
import com.uaq.service.PurchaseService;
import com.uaq.vo.PaymentReportFilterVO;
import com.uaq.vo.PaymentReportVO;

/**
 * REST webService class for Autoupdate, to be called from scheduler for in progress/incomplete transactions
 * performed in a day. Its not used as controller for application
 * instead will be called by scheduler.
 * 
 * @author raheem
 * 
 */

@Controller
public class PaymentAutoUpdateController{

	public static transient UAQLogger logger = new UAQLogger(PaymentAutoUpdateController.class);
	
	PurchaseService purchaseService = new PurchaseService();

	/**
	 * autoupdate transactions performed in a single day
	 * 
	 * @param request
	 *            implicit http request object.
	 * @param response
	 *            implicit http response object.
	 * @return nothing.
	 */
	
	@RequestMapping(value = ViewPath.PAYMENT_AUTOUPDATE, method = RequestMethod.GET)
	@ResponseBody
	public String handleRequest(HttpServletRequest request, ModelMap model) {

		logger.enter("handleRequest Get");

		PaymentReportFilterVO paymentReportFilterVO = new PaymentReportFilterVO();
		
		String transactionId = null, responseMessage = "No Broken Transactions Found";		
		DAOManager daoManager = new DAOManager();
		try {
			
			Connection con = daoManager.getConnection();
			Connection erpCon = daoManager.getERPConnection();
			DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
			String currentDate = df.format(Calendar.getInstance().getTime());
			
			if (null == request.getParameter("fromDate") || request.getParameter("fromDate").isEmpty()) {
				paymentReportFilterVO.setStartDate(currentDate);				
			} else {
				paymentReportFilterVO.setStartDate(request.getParameter("fromDate"));
			}
			if (null == request.getParameter("toDate") || request.getParameter("toDate").isEmpty()) {				
				paymentReportFilterVO.setEndDate(currentDate);
			} else {				
				paymentReportFilterVO.setEndDate(request.getParameter("toDate"));
			}
			
			logger.debug("From Date = " + paymentReportFilterVO.getStartDate() + " : To Date = " + paymentReportFilterVO.getEndDate());
						
			List<PaymentReportVO> payments = purchaseService.execute(paymentReportFilterVO,con);

			if(payments != null && !payments.isEmpty()){
				
				boolean result = false; 
				
				for (PaymentReportVO transaction : payments) {
					
					transactionId = transaction.getTransactinId();
					result = purchaseService.autoUpdatePaymentTransaction(transactionId,con,erpCon);
					
					logger.debug("autoupdate completed for transactionID = " + transactionId + " with status = " + result);
					responseMessage = "AutoUpdate on all broken payment transactions performed successfully";
				}
			}
			daoManager.commit();
			daoManager.erpCommit();
		} catch (Exception e) {
			daoManager.rollback();
			daoManager.erpRollback();
			logger.error(e.getMessage());
			logger.debug("autoupdate failed transactionID = " + transactionId );
			responseMessage = "autoupdate failed transactionID = " + transactionId;
		}finally{
			daoManager.closeConnection();
			daoManager.closeERPConnection();
		}
		
		logger.exit("PaymentAutoUpdateController doGet");
		
		return responseMessage;
	}

}
