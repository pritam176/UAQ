package com.uaq.service;

import java.math.BigInteger;
import java.rmi.RemoteException;
import java.text.ParseException;

import javax.xml.rpc.ServiceException;


import org.springframework.stereotype.Service;

import uaq.service.lp.model.LPMiddlewareServicePortBindingStub;
import uaq.service.lp.model.LPMiddlewareService_PortType;
import uaq.service.lp.model.LPMiddlewareService_Service;
import uaq.service.lp.model.LPMiddlewareService_ServiceLocator;

import com.oracle.xmlns.UAQ_LP_Twimc_App.UAQ_LP_Twimc_Prj.UAQ_LP_Twimc_BPEL.AddressToDetailsType;
import com.uaq.common.ApplicationConstants;
import com.uaq.logger.UAQLogger;
import com.uaq.util.DateUtil;
import com.uaq.util.StringUtil;
import com.uaq.vo.LandOutputVO;
import com.uaq.vo.UserDeatilVO;
import com.uaq.vo.WhomItmayConcernVO;

@Service("lPRequestService")
public class LPRequestService {
	protected static UAQLogger logger = new UAQLogger(LPRequestService.class);

	

	uaq.service.lp.model.UserContext uc = null;

	LPMiddlewareService_Service service = null;
	LPMiddlewareService_PortType port = null;
	LPMiddlewareServicePortBindingStub stub = null;

	public void createStub() {
		uc = new uaq.service.lp.model.UserContext();
		uc.setUsername(ApplicationConstants.WS_USERNAME);
		uc.setPassword(ApplicationConstants.WS_PASSWORD);

		try {
			service = new LPMiddlewareService_ServiceLocator();
			port = service.getLPMiddlewareServicePort();
			stub = (LPMiddlewareServicePortBindingStub) port;
		} catch (ServiceException e) {
			logger.error("WebService Error  |" + e.getMessage());

		}

	}

	public LandOutputVO submitWhomItMayConcern(UserDeatilVO userDeatilVO, WhomItmayConcernVO whomItmayConcern) throws 
			ParseException {
		createStub();

		LandOutputVO outputVO = new LandOutputVO();

		com.oracle.xmlns.UAQ_LP_Twimc_App.UAQ_LP_Twimc_Prj.UAQ_LP_Twimc_BPEL.ProcessType processtype = new com.oracle.xmlns.UAQ_LP_Twimc_App.UAQ_LP_Twimc_Prj.UAQ_LP_Twimc_BPEL.ProcessType();

		com.oracle.xmlns.UAQ_LP_Twimc_App.UAQ_LP_Twimc_Prj.UAQ_LP_Twimc_BPEL.AddressToDetailsType address = new AddressToDetailsType();
		address.setEmiratesId(userDeatilVO.getEmiratesId());
		address.setAddressTo(whomItmayConcern.getAddressTo());
		address.setFamilyBookNo(whomItmayConcern.getFamilyBookNo());
		address.setSpauseId(whomItmayConcern.getSpouseIdNo());
		address.setSource(whomItmayConcern.getSource()== null ? new BigInteger("00") : new BigInteger(whomItmayConcern.getSource()));
		address.setServiceId(whomItmayConcern.getServiceId()== null?  new BigInteger("00"): new BigInteger(whomItmayConcern.getServiceId()));
		address.setUserCommnets(whomItmayConcern.getUserCommnets());
		
		address.setAttachment1(whomItmayConcern.getScanFamilyBook().replace("-", "|"));
		address.setAttachment2(whomItmayConcern.getSposeEmiratesId().replace("-", "|"));
		address.setAttachment3("");
		//address.setAttachment4(whomItmayConcern.getOther());
		
		com.oracle.xmlns.UAQ_LP_Twimc_App.UAQ_LP_Twimc_Prj.UAQ_LP_Twimc_BPEL.CommonRequestBPMType commonBpm = new com.oracle.xmlns.UAQ_LP_Twimc_App.UAQ_LP_Twimc_Prj.UAQ_LP_Twimc_BPEL.CommonRequestBPMType();

		commonBpm.setRequestId(whomItmayConcern.getRequestId() == null ? new BigInteger("00"): new BigInteger(whomItmayConcern.getRequestId().toString()));
		commonBpm.setRequestNo(whomItmayConcern.getRequestNo());
		commonBpm.setWorkListId(whomItmayConcern.getWorkListId() == null ? new BigInteger("00"):new BigInteger(whomItmayConcern.getWorkListId().toString()));
		commonBpm.setStateId(1);
		commonBpm.setToWhomItmayId(whomItmayConcern.getToWhomItmayId()== null ? new BigInteger("00"):new BigInteger(whomItmayConcern.getToWhomItmayId().toString()));
		commonBpm.setTemp_ToWhomItmayId(whomItmayConcern.getTempToWhomItmayId() == null? new BigInteger("00"):new BigInteger(whomItmayConcern.getTempToWhomItmayId().toString()));
		commonBpm.setServiceName_En(whomItmayConcern.getServiceName_En());
		commonBpm.setServiceName_Ar(whomItmayConcern.getServiceName_Ar());

		com.oracle.xmlns.UAQ_LP_Twimc_App.UAQ_LP_Twimc_Prj.UAQ_LP_Twimc_BPEL.PaymentRequestType paymentDetails = new com.oracle.xmlns.UAQ_LP_Twimc_App.UAQ_LP_Twimc_Prj.UAQ_LP_Twimc_BPEL.PaymentRequestType();

		paymentDetails.setPaymentId(whomItmayConcern.getPaymentId());
		paymentDetails.setPaymentStatus(whomItmayConcern.getPaymentStatus());
		paymentDetails.setServiceFee(whomItmayConcern.getServiceFee());
		paymentDetails.setApplicationFee(whomItmayConcern.getApplicationFee());
		paymentDetails.setDeptID(whomItmayConcern.getDeptID());
		paymentDetails.setServiceFeeDisc(whomItmayConcern.getServiceFeeDisc());
		paymentDetails.setAppFeeDisc(whomItmayConcern.getAppFeeDisc());
		paymentDetails.setEdirhamServCode(whomItmayConcern.getEdirhamServCode());
		paymentDetails.setIsPaymentRequired(whomItmayConcern.getIsPaymentRequired());

		com.oracle.xmlns.UAQ_LP_Twimc_App.UAQ_LP_Twimc_Prj.UAQ_LP_Twimc_BPEL.UserDetailsPayload userDetails = new com.oracle.xmlns.UAQ_LP_Twimc_App.UAQ_LP_Twimc_Prj.UAQ_LP_Twimc_BPEL.UserDetailsPayload();

		userDetails.setUsername(userDeatilVO.getUsername());
		userDetails.setTypeOfUser(userDeatilVO.getTypeOfUser());
		userDetails.setAccountid(userDeatilVO.getAccountid());
		userDetails.setMobileNo(userDeatilVO.getMobileNo());
		userDetails.setEmailID(userDeatilVO.getEmailID());
		userDetails.setEmiratesId(userDeatilVO.getEmiratesId());
		userDetails.setTradeLienceNo(userDeatilVO.getTradeLienceNo());
		userDetails.setFamilyBookNo(whomItmayConcern.getFamilyBookNo());
		userDetails.setNationality_ID(StringUtil.isEmpty(userDeatilVO.getNationality()) ?new BigInteger("0"):new BigInteger(userDeatilVO.getNationality()));
		userDetails.setEmirate(userDeatilVO.getEmirate());
		userDetails.setFirstName(userDeatilVO.getFirstName());
		userDetails.setLastName(userDeatilVO.getLastName());
		userDetails.setAddress1(userDeatilVO.getAddress1());
		userDetails.setAddress2(whomItmayConcern.getOther());
		BigInteger postbox=new BigInteger(StringUtil.isEmpty(userDeatilVO.getPOBOX())?"0":userDeatilVO.getPOBOX());
		userDetails.setPOBOX(postbox);
		userDetails.setDOB(userDeatilVO.getDOB());
		userDetails.setHome_Phone(userDeatilVO.getHomePhone());
		userDetails.setLanguage_ID(new BigInteger(whomItmayConcern.getLanguageId()));
		//userDetails.setEirates_Code((userDeatilVO.getEmiratesCode().toBigInteger()));
		userDetails.setEirates_Code(StringUtil.isEmpty(userDeatilVO.getEmiratesCode())? new BigInteger("1"): new BigInteger(userDeatilVO.getEmiratesCode()));
		userDetails.setAddress2(whomItmayConcern.getOther());
		if(userDeatilVO.getEdiaExpirtyDate()!=null){
			userDetails.setEDIA_Expirty_Date(DateUtil.getDateFromString(userDeatilVO.getEdiaExpirtyDate()));
		}

		processtype.setAddressToDetails(address);
		processtype.setCommonRequestBPM(commonBpm);
		processtype.setPaymentDetails(paymentDetails);
		processtype.setUserDetails(userDetails);
		
		com.oracle.xmlns.UAQ_LP_Twimc_App.UAQ_LP_Twimc_Prj.UAQ_LP_Twimc_BPEL.ProcessResponseType output;
		try {
			output = stub.toWhomeEverSubmitRequest(	processtype, uc);
			if (output != null) {

				outputVO.setSatausId(output.getStatus());
				outputVO.setRequestNo(output.getCommonRequestBPM()!=null?output.getCommonRequestBPM().getRequestNo():"");
				//Service ID is not in the response. for now setting service ID from Inputpayload
				outputVO.setServiceId(address.getServiceId().toString());
				outputVO.setStatus_AR(output.getStatus_AR());
				outputVO.setStatus_EN(output.getStatus_EN());
				outputVO.setStatus(output.getStatus());
			} 
			else {
				outputVO.setStatus("Failed");
				logger.info("stub.toWhomeEverSubmitRequest(	processtype, uc)");
			}
		} catch (RemoteException e) {
			outputVO.setStatus("Failed");
			logger.error("Service Exception for service toWhomeEverSubmitRequest ::"+e.getMessage());
		}

		return outputVO;

	}

}
