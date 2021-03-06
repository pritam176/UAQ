package com.uaq.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uaq.logger.UAQLogger;
import com.uaq.vo.LandInputVO;
import com.uaq.vo.LandOutputVO;
import com.uaq.vo.UserDeatilVO;

@Service("submitNOCRequestService")
public class SubmitNOCRequestService {
	
	@Autowired
	private UCMCenterURLService uCMCenterURLService;
	
	
	
	protected static UAQLogger logger =new UAQLogger(SubmitNOCRequestService.class);
	
	
	
	public LandOutputVO submitNOCRequest(UserDeatilVO user,LandInputVO submitNoc){
		LandOutputVO outputVo =new LandOutputVO();
		
		/*com.AddLandRequest.Inputpayload  inputpayload=new Inputpayload();
		com.AddLandRequest.UserDetailsPayload userDetail =new UserDetailsPayload();
		userDetail.setUsername(user.getLoginUserName());
		userDetail.setTypeOfUser(user.getTypeOfUser());
		userDetail.setNationality(user.getNationality());
		userDetail.setAccountid(user.getAccountid());
		userDetail.setMobileNo(user.getMobileNo());
		userDetail.setEmailID(user.getEmailID());
		userDetail.setEmiratesId(user.getEmiratesId());
		userDetail.setTradeLienceNo(user.getTradeLienceNo());
		userDetail.setFamilyBookNo(user.getFamilyBookNo());
		userDetail.setEmirate(user.getEmirate());
		userDetail.setFirstName(user.getFirstName());
		userDetail.setMiddleName(user.getMiddleName());
		userDetail.setLastName(user.getLastName());
		userDetail.setAddress1(user.getAddress1());
		userDetail.setAddress2(user.getAddress2());
		userDetail.setAddress3(user.getAddress3());
		userDetail.setPOBOX(user.getPOBOX());
		userDetail.setDOB(user.getDOB());
		
		if(user.getTypeOfUser().equals("1")){
			userDetail.setApplicanttypeid(user.getApplicantTypeId());	
		}else{
			userDetail.setApplicanttypeid("0");
		}
		
		inputpayload.setRequestNo(submitNoc.getRequestNo());
		inputpayload.setSourceType(submitNoc.getSourceType());
		inputpayload.setRequestID(submitNoc.getRequestNo().split("-")[3]);
		inputpayload.setSubmittedNOCId(submitNoc.getSubmitedNocID());
		inputpayload.setServiceid(submitNoc.getServiceid());
		inputpayload.setServiceType("New");
		inputpayload.setSubmittedByUserId(user.getUsername());
		inputpayload.setStatus("1");
		inputpayload.setAddLandReqID("1");
		inputpayload.setLand_Usage("NoLandUsage");
		inputpayload.setServiceName("Add Land Request");
		inputpayload.setLanguageId(submitNoc.getLanguageId());
		PaymentDetailsPayload paymentDetails=new PaymentDetailsPayload();
		paymentDetails.setApplicationPaymentDiscount("");
		paymentDetails.setApplicationPaymentFees("");
		paymentDetails.setApplicationPaymentId("");
		paymentDetails.setApplicationPaymentStatus("");
		paymentDetails.setServicePaymentDiscount("");
		paymentDetails.setServicePaymentFees("");
		paymentDetails.setServicePaymentId("");
		paymentDetails.setServicePaymentStatus("");
		inputpayload.setPaymentDetails(paymentDetails);
		
		
		
		
		NewSubmitNOCRecPayload[] newSubmitNOCList = new NewSubmitNOCRecPayload[submitNoc.getSubmitNoc().size()];
	
		Iterator it=submitNoc.getSubmitNoc().entrySet().iterator();
		int index=0;
		 while (it.hasNext()) {
		        Map.Entry pair = (Map.Entry)it.next();
		        String filename = (String) pair.getValue();
		        logger.debug("File Name from Controller   |  " + filename);
				String did = filename.split("-")[0];
				String name = filename.split("-")[1];
				String ucmUrl = uCMCenterURLService.getWebCenterURLofFile(did);
				logger.debug("From WebCenter URL=" + ucmUrl);
				NewSubmitNOCRecPayload attachmentRecPayload = new NewSubmitNOCRecPayload();
				attachmentRecPayload.setRequestNOCdid((String) pair.getKey());
				attachmentRecPayload.setRequestNOCUrl("");
				attachmentRecPayload.setSubmitNOCFileName(name);
				attachmentRecPayload.setSubmitNOCDid(did);
				attachmentRecPayload.setSubmitNOCurl(ucmUrl);
				newSubmitNOCList[index] = attachmentRecPayload;
				index++;
		    }
		
	
		
		
		inputpayload.setNewSubmitNOCList(newSubmitNOCList);

	
		LandOutputVO outputVo =new LandOutputVO();
		
		inputpayload.setUserDetails(userDetail);
		com.AddLandRequest.OutputPayload ouput =null;
		try {
			ouput=stub.submitNOCAddlandRequest(inputpayload, uc);
			if(ouput !=null){
				outputVo.setStatus(ouput.getStausFlag());
				outputVo.setStatus_EN(ouput.getStatus_EN());
				outputVo.setStatus_AR(ouput.getStatus_AR());
				logger.error(ouput.getStatus_AR());
			}else{
				outputVo.setStatus("Failed");
				logger.error("Failure | stub.submitNOCAddlandRequest(inputpayload, uc); return null");
				
			}
		} catch (Exception e) {
			outputVo.setStatus("Failed");
			logger.error("Failure | "+e.getMessage());
		}*/
		return  outputVo;
	}

}
