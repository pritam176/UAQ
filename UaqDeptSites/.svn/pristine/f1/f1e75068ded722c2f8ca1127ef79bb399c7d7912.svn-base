package com.uaq.service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;

import javax.xml.rpc.ServiceException;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import uaq.db.si.model.common.LpToWhomConcernViewSDO;
import uaq.service.lp.model.LPMiddlewareResubmitServicePortBindingStub;
import uaq.service.lp.model.LPMiddlewareResubmitService_PortType;
import uaq.service.lp.model.LPMiddlewareResubmitService_Service;
import uaq.service.lp.model.LPMiddlewareResubmitService_ServiceLocator;

import com.oracle.xmlns.UAQ_LP_Twimc_App.UAQ_LP_Twimc_Prj.UAQ_LP_Twimc_BPEL.AddressToDetailsType;
import com.uaq.common.ApplicationConstants;
import com.uaq.controller.mapper.PortalDataMapper;
import com.uaq.logger.UAQLogger;
import com.uaq.vo.LPtoWhomeConcernVO;
import com.uaq.vo.ReSubmiisionInputVO;
import static com.uaq.common.ApplicationConstants.*;

@Service
@Qualifier("lpFindRequestService")
public class LPFindRequestService {

	protected static UAQLogger logger = new UAQLogger(LPFindRequestService.class);

	LPMiddlewareResubmitService_PortType port;
	LPMiddlewareResubmitServicePortBindingStub stub;
	LPMiddlewareResubmitService_Service service;
	uaq.service.lp.model.UserContext uc = null;

	private void creteStub() {

		uc = new uaq.service.lp.model.UserContext();
		uc.setUsername(ApplicationConstants.WS_USERNAME);
		uc.setPassword(ApplicationConstants.WS_PASSWORD);

		service = new LPMiddlewareResubmitService_ServiceLocator();
		try {
			port = service.getLPMiddlewareResubmitServicePort();
			stub = (LPMiddlewareResubmitServicePortBindingStub) port;
		} catch (ServiceException e) {
			logger.error("WebService Error  |" + e.getMessage());
		}
	}

	@SuppressWarnings("unused")
	public LPtoWhomeConcernVO findLpToWhomConcernView1(ReSubmiisionInputVO input, String languageCode) {

		creteStub();
		com.oracle.xmlns.UAQ_LP_Twimc_App.UAQ_LP_Twimc_Prj.UAQ_LP_Twimc_BPEL.ProcessType processType = new com.oracle.xmlns.UAQ_LP_Twimc_App.UAQ_LP_Twimc_Prj.UAQ_LP_Twimc_BPEL.ProcessType();

		com.oracle.xmlns.UAQ_LP_Twimc_App.UAQ_LP_Twimc_Prj.UAQ_LP_Twimc_BPEL.AddressToDetailsType address = new AddressToDetailsType();

		address.setEmiratesId("");
		address.setAddressTo("");
		address.setFamilyBookNo("");
		address.setSpauseId("");
		address.setSource(new BigInteger("0"));
		address.setServiceId(new BigInteger("0"));
		address.setUserCommnets("");

		address.setAttachment1("");
		address.setAttachment2("");
		address.setAttachment3("");
		address.setAttachment4("");

		com.oracle.xmlns.UAQ_LP_Twimc_App.UAQ_LP_Twimc_Prj.UAQ_LP_Twimc_BPEL.CommonRequestBPMType commonBpm = new com.oracle.xmlns.UAQ_LP_Twimc_App.UAQ_LP_Twimc_Prj.UAQ_LP_Twimc_BPEL.CommonRequestBPMType();

		commonBpm.setRequestId(new BigInteger(input.getAttributeValue().split("-")[3]));
		commonBpm.setRequestNo(input.getAttributeValue());
		commonBpm.setWorkListId(new BigInteger("0"));
		commonBpm.setStateId(3);// for Get Detail
		commonBpm.setToWhomItmayId(new BigInteger("0"));
		commonBpm.setTemp_ToWhomItmayId(new BigInteger("0"));
		commonBpm.setServiceName_En("");
		commonBpm.setServiceName_Ar("");

		com.oracle.xmlns.UAQ_LP_Twimc_App.UAQ_LP_Twimc_Prj.UAQ_LP_Twimc_BPEL.PaymentRequestType paymentDetails = new com.oracle.xmlns.UAQ_LP_Twimc_App.UAQ_LP_Twimc_Prj.UAQ_LP_Twimc_BPEL.PaymentRequestType();

		paymentDetails.setPaymentId(new Integer(0));
		paymentDetails.setPaymentStatus("");
		paymentDetails.setServiceFee("");
		paymentDetails.setApplicationFee("");
		paymentDetails.setDeptID("");
		paymentDetails.setServiceFeeDisc("");
		paymentDetails.setAppFeeDisc("");
		paymentDetails.setEdirhamServCode("");
		paymentDetails.setIsPaymentRequired("");

		com.oracle.xmlns.UAQ_LP_Twimc_App.UAQ_LP_Twimc_Prj.UAQ_LP_Twimc_BPEL.ProcessResponseType processResponseType = new com.oracle.xmlns.UAQ_LP_Twimc_App.UAQ_LP_Twimc_Prj.UAQ_LP_Twimc_BPEL.ProcessResponseType();

		com.oracle.xmlns.UAQ_LP_Twimc_App.UAQ_LP_Twimc_Prj.UAQ_LP_Twimc_BPEL.UserDetailsPayload userDetails = new com.oracle.xmlns.UAQ_LP_Twimc_App.UAQ_LP_Twimc_Prj.UAQ_LP_Twimc_BPEL.UserDetailsPayload();

		userDetails.setUsername("");
		userDetails.setTypeOfUser("");
		userDetails.setAccountid("");
		userDetails.setMobileNo("");
		userDetails.setEmailID("");
		userDetails.setEmiratesId("");
		userDetails.setTradeLienceNo("");
		userDetails.setFamilyBookNo("");
		userDetails.setNationality_ID(new BigInteger("0"));
		userDetails.setEmirate("");
		userDetails.setFirstName("");
		userDetails.setLastName("");
		userDetails.setAddress1("");
		userDetails.setAddress2("");
		userDetails.setPOBOX(new BigInteger("0"));
		userDetails.setDOB(new Date());
		userDetails.setHome_Phone("");
		userDetails.setLanguage_ID(new BigInteger(PortalDataMapper.getLanguageId(languageCode)));
		userDetails.setEirates_Code(new BigInteger("0"));

		userDetails.setEDIA_Expirty_Date(new Date());

		processType.setAddressToDetails(address);
		processType.setCommonRequestBPM(commonBpm);
		processType.setPaymentDetails(paymentDetails);
		processType.setUserDetails(userDetails);

		LPtoWhomeConcernVO outputVO = new LPtoWhomeConcernVO();
		List<LpToWhomConcernViewSDO> output = null;

		try {
			processResponseType = stub.toWhomeEverResubmitRequest(processType, uc);
		} catch (RemoteException e) {
			logger.error("Failed" + e.getMessage());
			outputVO.setStatus(SERVICE_FAILED);
		}

		if (processResponseType != null) {
			if (processResponseType.getAddressToDetails() != null) {
				outputVO.setAddressedtoId(new BigDecimal(processResponseType.getAddressToDetails().getAddressTo()));
				outputVO.setFamilyBookNum(processResponseType.getAddressToDetails().getFamilyBookNo());
				outputVO.setSpouseId(processResponseType.getAddressToDetails().getSpauseId());
				outputVO.setAttachmentRecPayload(processResponseType.getAttachmentList());
				outputVO.setComments(processResponseType.getAddressToDetails().getUserCommnets());
				outputVO.setReqId(processResponseType.getCommonRequestBPM().getRequestId());
				outputVO.setRequestNo(processResponseType.getCommonRequestBPM().getRequestNo());
				outputVO.setTowhomeReqId(processResponseType.getCommonRequestBPM().getTemp_ToWhomItmayId());
				outputVO.setOther(processResponseType.getUserDetails().getAddress2());
				outputVO.setStatus(processResponseType.getStatus());
			} else {
				outputVO.setStatus(SERVICE_FAILED);
				logger.info("Failed  | processResponseType.getAddressToDetails(); return null");
			}
		} else {
			outputVO.setStatus(SERVICE_FAILED);
			logger.info("Failed  | stub.toWhomeEverResubmitRequest(processType, uc); return null");
		}
		return outputVO;

	}

	/*
	 * public LPRealEsatteOfficeVO
	 * findLpRealestateOfficeReqView(ReSubmiisionInputVO input) {
	 * 
	 * creteStub();
	 * 
	 * FindCriteria findCriteria = new FindCriteria(); FindControl findControl =
	 * new FindControl();
	 * 
	 * ViewCriteriaItem viewCriteriaItem = new ViewCriteriaItem();
	 * viewCriteriaItem.setConjunction(Conjunction.AND);
	 * viewCriteriaItem.setAttribute(input.getAttributeName());
	 * viewCriteriaItem.setUpperCaseCompare(false);
	 * viewCriteriaItem.setOperator("=");
	 * viewCriteriaItem.getValue().add(input.getAttributeValue());
	 * 
	 * ViewCriteriaRow viewCriteriaRow = new ViewCriteriaRow();
	 * viewCriteriaRow.setConjunction(Conjunction.AND);
	 * viewCriteriaRow.getItem().add(viewCriteriaItem);
	 * 
	 * com.oracle.xmlns.adf.svc.types.ViewCriteria nested = new ViewCriteria();
	 * nested.setConjunction(Conjunction.AND);
	 * 
	 * ViewCriteria filter = new ViewCriteria();
	 * filter.setConjunction(Conjunction.AND);
	 * filter.getGroup().add(viewCriteriaRow); filter.getNested().add(nested);
	 * 
	 * findControl.setRetrieveAllTranslations(true);
	 * findCriteria.setFilter(filter); findCriteria.setFetchSize(-1);
	 * findCriteria.setFetchStart(0);
	 * 
	 * LPRealEsatteOfficeVO outputVO = new LPRealEsatteOfficeVO();
	 * List<LpRealestateOfficeReqViewSDO> output = null; try { output =
	 * stub.findLpRealestateOfficeReqView1(findCriteria, findControl);
	 * 
	 * if (output != null) { if (output.size() > 0) {
	 * outputVO.setExcutionstatus("Success"); LpRealestateOfficeReqViewSDO temp
	 * = output.get(0);
	 * 
	 * outputVO.setAccountId(temp.getAccountId().getValue());
	 * outputVO.setAttchmentId(temp.getAttchmentId().getValue());
	 * outputVO.setCity(temp.getCity().getValue());
	 * outputVO.setCountry(temp.getCountry().getValue());
	 * outputVO.setCreatedDate(temp.getCreatedDate().getValue());
	 * outputVO.setEstablishmentId(temp.getEstablishmentId().getValue());
	 * outputVO.setModifiedBy(temp.getModifiedBy().getValue());
	 * outputVO.setModifiedDate(temp.getModifiedDate().getValue());
	 * outputVO.setReqId(temp.getReqId());
	 * outputVO.setRequestNo(temp.getRequestNo().getValue());
	 * outputVO.setMdAddress1(temp.getMdAddress1().getValue());
	 * outputVO.setMdAdress2(temp.getMdAdress2().getValue());
	 * outputVO.setStatus(temp.getStatus().getValue());
	 * outputVO.setMdEmiratesId(temp.getMdEmiratesId().getValue());
	 * outputVO.setMdEmrIdExpDate(temp.getMdEmrIdExpDate().getValue());
	 * outputVO.setMdName(temp.getMdName().getValue());
	 * outputVO.setMdPhoneNum(temp.getMdPhoneNum().getValue());
	 * outputVO.setPoboxNo(temp.getPoboxNo().getValue());
	 * outputVO.setRealOffReqId(temp.getRealOffReqId());
	 * outputVO.setServiceId(temp.getServiceId().getValue());
	 * outputVO.setState(temp.getState().getValue());
	 * outputVO.setStatus(temp.getStatus().getValue());
	 * 
	 * logger.info("Success  |" + output.size());
	 * 
	 * } else { outputVO.setExcutionstatus("Failure"); logger.info(
	 * "Failure  | stub.findLpRealestateOfficeReqView1(findCriteria, findControl); return 0 elemnt"
	 * ); }
	 * 
	 * } else { outputVO.setExcutionstatus("Failure"); logger.info(
	 * "Failure  | stub.findLpRealestateOfficeReqView1(findCriteria, findControl); return null"
	 * ); } } catch (uaq.db.si.model.common.ServiceException e) {
	 * outputVO.setExcutionstatus("Failure"); logger.error("Failure  |" +
	 * e.getMessage()); } return outputVO;
	 * 
	 * }
	 * 
	 * public ProCardDeatilViewVO
	 * findLpProCardReqDetailsView(ReSubmiisionInputVO input) {
	 * 
	 * creteStub();
	 * 
	 * FindCriteria findCriteria = new FindCriteria(); FindControl findControl =
	 * new FindControl();
	 * 
	 * ViewCriteriaItem viewCriteriaItem = new ViewCriteriaItem();
	 * viewCriteriaItem.setConjunction(Conjunction.AND);
	 * viewCriteriaItem.setAttribute(input.getAttributeName());
	 * viewCriteriaItem.setUpperCaseCompare(false);
	 * viewCriteriaItem.setOperator("=");
	 * viewCriteriaItem.getValue().add(input.getAttributeValue());
	 * 
	 * ViewCriteriaRow viewCriteriaRow = new ViewCriteriaRow();
	 * viewCriteriaRow.setConjunction(Conjunction.AND);
	 * viewCriteriaRow.getItem().add(viewCriteriaItem);
	 * 
	 * com.oracle.xmlns.adf.svc.types.ViewCriteria nested = new ViewCriteria();
	 * nested.setConjunction(Conjunction.AND);
	 * 
	 * ViewCriteria filter = new ViewCriteria();
	 * filter.setConjunction(Conjunction.AND);
	 * filter.getGroup().add(viewCriteriaRow); filter.getNested().add(nested);
	 * 
	 * findControl.setRetrieveAllTranslations(true);
	 * findCriteria.setFilter(filter); findCriteria.setFetchSize(-1);
	 * findCriteria.setFetchStart(0);
	 * 
	 * ProCardDeatilViewVO outputVO = new ProCardDeatilViewVO();
	 * List<LpProCardReqDetailsViewSDO> output = null; try { output =
	 * stub.findLpProCardReqDetailsView1(findCriteria, findControl);
	 * 
	 * if (output != null) { if (output.size() > 0) {
	 * outputVO.setExecutionStatus("Success"); LpProCardReqDetailsViewSDO temp =
	 * output.get(0);
	 * 
	 * outputVO.setAccountId(temp.getAccountId().getValue());
	 * outputVO.setAttchmentId(temp.getAttchmentId().getValue());
	 * outputVO.setCreatedBy(temp.getCreatedBy().getValue());
	 * outputVO.setCreatedDate(temp.getCreatedDate().getValue());
	 * outputVO.setEstablishmentId(temp.getEstablishmentId().getValue());
	 * outputVO.setModifiedBy(temp.getModifiedBy().getValue());
	 * outputVO.setModifiedDate(temp.getModifiedDate().getValue());
	 * outputVO.setReqId(temp.getReqId());
	 * outputVO.setRequestNo(temp.getRequestNo().getValue());
	 * outputVO.setEstabllishmentName(temp.getEstabllishmentName().getValue());
	 * outputVO.setProCardReqId(temp.getProCardReqId());
	 * outputVO.setStatus(temp.getStatus().getValue());
	 * outputVO.setProEmiIdExpDate(temp.getProEmiIdExpDate().getValue());
	 * outputVO.setProEmiratesId(temp.getProEmiratesId().getValue());
	 * outputVO.setProIdExpDate(temp.getProIdExpDate().getValue());
	 * outputVO.setProIdNum(temp.getProIdNum().getValue());
	 * outputVO.setProName(temp.getProName().getValue());
	 * 
	 * outputVO.setServId(temp.getServId().getValue());
	 * outputVO.setStatus(temp.getStatus().getValue());
	 * outputVO.setTradeLicenceIssueDate
	 * (temp.getTradeLicenceIssueDate().getValue());
	 * 
	 * logger.info("Success  |" + output.size());
	 * 
	 * } else { outputVO.setExecutionStatus("Failure"); logger.info(
	 * "Failure  | stub.findLpProCardReqDetailsView(findCriteria, findControl); return 0 elemnt"
	 * ); }
	 * 
	 * } else { outputVO.setExecutionStatus("Failure"); logger.info(
	 * "Failure  | stub.findLpProCardReqDetailsView(findCriteria, findControl); return null"
	 * ); } } catch (uaq.db.si.model.common.ServiceException e) {
	 * outputVO.setExecutionStatus("Failure"); logger.error("Failure  |" +
	 * e.getMessage()); } return outputVO;
	 * 
	 * }
	 */

}
