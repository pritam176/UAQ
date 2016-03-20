package com.uaq.schedulers;

import static com.uaq.common.ApplicationConstants.LANG_ENGLISH;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.uaq.dao.DAOManager;
import com.uaq.dao.PurchaseDAO;
import com.uaq.logger.UAQLogger;
import com.uaq.service.PaymentWorkFlowService;
import com.uaq.service.PurchaseService;
import com.uaq.service.ReviewerServiceBrokenTracaction;
import com.uaq.service.UserDetailService;
import com.uaq.util.StringUtil;
import com.uaq.vo.AccountDetailTokenOutputVO;
import com.uaq.vo.LandOutputVO;
import com.uaq.vo.PaymentTransactionDetailVO;
import com.uaq.vo.PaymentWorkFlowVO;

public class BrokenTransactionsTasks {

	private PaymentWorkFlowService paymentWorkFlowService;
	private UserDetailService userDetailService;
	private PurchaseDAO purchaseDAO = null;
	private PurchaseService purchaseService = null;

	private static transient UAQLogger logger = new UAQLogger(BrokenTransactionsTasks.class);

	public BrokenTransactionsTasks() {
		paymentWorkFlowService = new PaymentWorkFlowService();
		userDetailService = new UserDetailService();
		purchaseDAO = new PurchaseDAO();
		purchaseService = new PurchaseService();
	}

	public void initWorkflow() {
		DAOManager daoManager = new DAOManager();
		try {
			ReviewerServiceBrokenTracaction reviewerServiceBrokenTracaction = new ReviewerServiceBrokenTracaction();
			List<PaymentWorkFlowVO> paymentWorkFlowVOList = paymentWorkFlowService.getPaymentWorkFlowVOList(daoManager.getConnection());
			for (PaymentWorkFlowVO vo : paymentWorkFlowVOList) {
				PaymentTransactionDetailVO paymentTransactionDetailVO = new PaymentTransactionDetailVO();
				paymentTransactionDetailVO.setRequestId(vo.getRequestId());
				paymentTransactionDetailVO.setRequestNo(vo.getRequestNo());
				paymentTransactionDetailVO.setServiceId(vo.getServiceId());
				paymentTransactionDetailVO.setStatusId(vo.getStatusId());
				paymentTransactionDetailVO.setTransactionAmount(vo.getEdiramFees());
				paymentTransactionDetailVO.setTransactionId(vo.getTransactionId());
				vo.setModifiedBy("CRON_JOB");
				AccountDetailTokenOutputVO accountDetailfromToken = userDetailService.getUserdetailByUsername(vo.getUserName());
				LandOutputVO landOutputVO = reviewerServiceBrokenTracaction.invokeRevierIneceator(accountDetailfromToken, paymentTransactionDetailVO);
				logger.debug("Reviewer Status-" + landOutputVO.getStatus());
				//model.addAttribute("servicemessage", (languageCode.equals(LANG_ENGLISH)) ? landOutputVO.getStatus_EN() : landOutputVO.getStatus_AR());
				if ("Success".equals(landOutputVO.getStatus())) {
					logger.debug("delete workflowTabel for requestId=" + vo.getRequestId());
					boolean deletePaymentWorkflow = paymentWorkFlowService.deletePaymentWorkFLow(vo,daoManager.getConnection());
					logger.debug("status of workflowTabel Deletion=" + deletePaymentWorkflow);
				} else {
					logger.debug("update workflowTabel for requestId=" + vo.getRequestId());
					boolean updataPaymentWorkflow = paymentWorkFlowService.updatePaymentWorkFLow(vo,daoManager.getConnection());
					logger.debug("status of workflowTabel Updation=" + updataPaymentWorkflow);
					//model.addAttribute("servicemessage", messageSource.getMessage("reviewer.failed.again", null, locale));
				}
				daoManager.commit();
			}
		} catch (Exception e) {
			daoManager.rollback();
			logger.error("Failed", e);
		} finally {
			daoManager.closeConnection();
		}
	}

	public void doBrokenTranasaction() {
		DAOManager daoManager = new DAOManager();
		try {
			Map<String, String> allFailedData = purchaseDAO.getAllFailedPurchase(daoManager.getConnection());
			Iterator it = allFailedData.entrySet().iterator();
			while (it.hasNext()) {
				boolean isComplete = false;
				Map.Entry pair = (Map.Entry) it.next();

				String purchaseId = (String) pair.getKey();
				String transactionId = (String) pair.getValue();
				if (!StringUtil.isEmpty(transactionId)) {
					logger.debug("AutoUpdate For Purchase Id -" + purchaseId + " | transactionId-" + transactionId);
					try {
						isComplete = purchaseService.autoUpdatePaymentTransaction(transactionId, daoManager.getConnection(), daoManager.getERPConnection());
						daoManager.commit();
						daoManager.erpCommit();
					} catch (Exception e) {
						daoManager.rollback();
						daoManager.erpRollback();
						logger.error("AutoUpadate Failed For Purchase Id -" + purchaseId);
						logger.error("Failed", e);

					}
				}
			}
		} catch (SQLException e) {
			logger.error("Failed", e);
		} finally {
			daoManager.closeConnection();
		}

	}
}
