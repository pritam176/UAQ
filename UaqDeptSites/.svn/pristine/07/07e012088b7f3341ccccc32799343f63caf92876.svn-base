package com.uaq.service;

import static com.uaq.payment.PaymentConstants.PAYMENT_RESPONSE_STATUS_CODE_ALREADY_UPDATED;
import static com.uaq.payment.PaymentConstants.PAYMENT_RESPONSE_STATUS_CODE_PAYMENT_METHOD_SELECTED;
import static com.uaq.payment.PaymentConstants.PAYMENT_RESPONSE_STATUS_CODE_TXN_NOT_FOUND;
import static com.uaq.payment.PaymentConstants.PAYMENT_RESPONSE_STATUS_CODE_UPDATED_FAILED;
import static com.uaq.payment.PaymentConstants.PAYMENT_RESPONSE_STATUS_CODE_UPDATED_SUCCESSFUL;
import static com.uaq.payment.PaymentConstants.RESPONSE_STATUS_SUCCESS;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.uaq.common.PropertiesUtil;
import com.uaq.controller.mapper.PaymentDataMapper;
import com.uaq.dao.PurchaseDAO;
import com.uaq.dao.RequestDAO;
import com.uaq.exception.DAOException;
import com.uaq.exception.UAQException;
import com.uaq.logger.UAQLogger;
import com.uaq.payment.AutoUpdatePaymentResponse;
import com.uaq.payment.InquiryPaymentResponse;
import com.uaq.payment.MerchantAccount;
import com.uaq.payment.PayWebPaymentRequest;
import com.uaq.payment.PayWebPaymentResponse;
import com.uaq.payment.PaymentConstants;
import com.uaq.payment.PaymentServiceCode;
import com.uaq.payment.PaymentStatus;
import com.uaq.payment.PaymentUtil;
import com.uaq.util.StringUtil;
import com.uaq.vo.AccountDetailTokenOutputVO;
import com.uaq.vo.LandOutputVO;
import com.uaq.vo.PaymentReportFilterVO;
import com.uaq.vo.PaymentReportVO;
import com.uaq.vo.PaymentTransactionDetailVO;
import com.uaq.vo.PaymentWorkFlowVO;
import com.uaq.vo.PurchaseVO;

/**
 * Service class for Photo Purchase process. It calls the DAO layer for database
 * access
 * 
 * @author mraheem
 * 
 */

public class RequestService {

	RequestDAO requestDAO = null;

	

	public RequestService() {
		try {
			requestDAO = new RequestDAO();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static transient UAQLogger logger = new UAQLogger(RequestService.class);

	/**
	 * This method is used to get full purchase records and perform autoupdate
	 * if its broken transaction. abstract class method.
	 * @throws Exception 
	 */

	
	public String getSubmitWorkFlowHistoryId(String requestId,Connection con) throws SQLException {

		return requestDAO.getSubmitWorkFlowHistoryId(requestId, con);
	}

}
