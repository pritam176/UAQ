/**
 * 
 */
package com.uaq.service.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.oracle.xmlns.Mobile_PaymentHistory.Mobile_PaymentHistory_pjr.Mobile_PaymentHistory_BPEL.ProcessResponseListType;
import com.uaq.logger.UAQLogger;
import com.uaq.payment.PaymentUtil;

import com.uaq.util.DateUtil;
import com.uaq.vo.PaymentHistoryVO;

/**
 * @author akhil
 * 
 */

@Component("paymentHistoryMapper")
public class PaymentHistoryMapper {
	
	protected static UAQLogger logger = new UAQLogger(PaymentHistoryMapper.class);

	public List<PaymentHistoryVO> mapPaymentReponseToVO(ProcessResponseListType[] processResponseListType) {

		List<PaymentHistoryVO> paymentHistoryVOList = new ArrayList<PaymentHistoryVO>();
		logger.debug("No Of Record - processResponseListType.length");
		if (processResponseListType != null && processResponseListType.length != 0) {
			logger.debug("No Of Record - processResponseListType.length");
			for (ProcessResponseListType response : processResponseListType) {
				PaymentHistoryVO paymentHistoryVO = new PaymentHistoryVO();
				paymentHistoryVO.setAction(response.getACTION());
				paymentHistoryVO.setAmount(response.getAMOUNT());
				paymentHistoryVO.setAmountWithFees(response.getAMOUNT_WITH_FEES());
				paymentHistoryVO.setAmountWithoutFees(response.getAMOUNT_WITHOUT_FEES());
				paymentHistoryVO.setCollectionCenterFees(response.getCOLLECTION_CENTRE_FEES());
				paymentHistoryVO.setConfirmationId(response.getCONFIRMATION_ID());
				paymentHistoryVO.setCustomerId(response.getCUSTOMER_ID());
				paymentHistoryVO.setCustomerName(response.getCUSTOMER_NAME());
				paymentHistoryVO.setDepartmentId(response.getDEPARTMENT_ID());
				paymentHistoryVO.seteDirhamFees(response.getEDIRHAM_FEES());
				paymentHistoryVO.setFeeId(response.getFEE_ID());
				paymentHistoryVO.setOrginalTransactionID(response.getOrig_TransactionID());
				paymentHistoryVO.setOther(response.getOTHER());
				paymentHistoryVO.setOwnerFees(response.getOWNER_FEES());
				paymentHistoryVO.setPaymentInProgress(response.getPAYMENT_IN_PROGRESS());
				paymentHistoryVO.setPurchaseId(response.getPURCHASE_ID());
				paymentHistoryVO.setPurchaseStatus(response.getPURCHASE_STATUS());
				paymentHistoryVO.setRetrievalRefNumber(response.getRETRIEVAL_REF_NUMBER());
				paymentHistoryVO.setServiceId(response.getSERVICE_ID());
				paymentHistoryVO.setStatus(response.getSTATUS());
				paymentHistoryVO.setStatusMessage(response.getSTATUS_MESSAGE().replace("+", " "));
				paymentHistoryVO.setTransactionID(response.getTRANSACTION_ID());
				paymentHistoryVO.setTranscationAmount(String.valueOf(PaymentUtil.convertAmountISOToDecimalFormat(response.getTRANSACTION_AMOUNT())));
				paymentHistoryVO.setTranscationDate(DateUtil.getUAQFormattedDate(response.getTRANSACTION_DATE().getTime(), "MMMM dd, yyyy", "en"));
				paymentHistoryVOList.add(paymentHistoryVO);
			}
		}

		return paymentHistoryVOList;
	}

}
