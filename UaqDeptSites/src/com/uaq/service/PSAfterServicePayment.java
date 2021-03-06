/**
 * 
 */
package com.uaq.service;

import static com.uaq.common.ApplicationConstants.SERVICE_FAILED;

import java.text.ParseException;

import javax.xml.datatype.DatatypeConfigurationException;

import org.springframework.stereotype.Service;

import com.uaq.common.PropertiesUtil;
import com.uaq.logger.UAQLogger;
import com.uaq.soap.warpper.WebServiceWarpper;
import com.uaq.vo.LandInputVO;
import com.uaq.vo.LandOutputVO;
import com.uaq.vo.UserDeatilVO;

/**
 * This Service Class is Used for After Service Payment Call
 * After this request will move to next step
 * 
 * Note -WSDL URL May be changed depend on environment.
 * URL will load from Application.property file.
 *
 *@author Pritam
 */
@Service("pSAfterServicePayment")
public class PSAfterServicePayment {
	
	protected static UAQLogger logger = new UAQLogger(PSAfterServicePayment.class);
	
	private WebServiceWarpper webServiceWarpper = new WebServiceWarpper();
	
	
	
		
	
	
/*	private PSServiceMiddleLayerServicePaymentService service =null;
	private PSServiceMiddleLayerServicePayment port= null;
	private PSServiceMiddleLayerServicePaymentPortBindingStub stub=null;
	
	UserContext userContext =null;
	
	private void createStub(){
		userContext = new UserContext(); 
		userContext.setUsername(ApplicationConstants.WS_USERNAME);
		userContext.setPassword(ApplicationConstants.WS_PASSWORD);

		try {
			service = (PSServiceMiddleLayerServicePaymentService)new PSServiceMiddleLayerServicePaymentServiceLocator();
			port = service.getPSServiceMiddleLayerServicePaymentPort();
			stub = (PSServiceMiddleLayerServicePaymentPortBindingStub)port;

		} catch (ServiceException e) {
			logger.error("WebService Error  |" + e.getMessage());
			

		}
	}*/
	
	public LandOutputVO servicePaymentAddlandRequest(UserDeatilVO user, LandInputVO landDemacreationVO) throws DatatypeConfigurationException, ParseException{
		LandOutputVO outputVO = new LandOutputVO();
		/*LandOutputVO outputVO = new LandOutputVO();
		com.AddLandRequest.Inputpayload inputpayload =new com.AddLandRequest.Inputpayload();
		com.AddLandRequest.UserDetailsPayload  userDetail=new UserDetailsPayload();
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
		
		inputpayload.setServiceType(landDemacreationVO.getServiceType());
		inputpayload.setUserComment(landDemacreationVO.getUserComment());
		inputpayload.setSubmittedByUserId(landDemacreationVO.getSubmittedByUserId());
		inputpayload.setSourceType(landDemacreationVO.getSourceType());
		inputpayload.setStatus(landDemacreationVO.getStatus()"1");
		inputpayload.setServiceName(landDemacreationVO.getServiceName());
		inputpayload.setServiceid(landDemacreationVO.getServiceid());
		inputpayload.setRequestID(landDemacreationVO.getRequestID());
		inputpayload.setRequestNo(landDemacreationVO.getRequestNo());
		inputpayload.setWorkflowId(landDemacreationVO.getWorkflowId());
		//inputpayload.setLandDemarcationId(landDemacreationVO.getLandDemarcationId());
		inputpayload.setLanguageId(landDemacreationVO.getLanguageId());
		inputpayload.setSite_Plan_No(landDemacreationVO.getSite_Plan_No());
		inputpayload.setSite_Plan_Date(PortalDataMapper.toCalendar(landDemacreationVO.getSitePlanDate()));
		inputpayload.setSec_Plot_No(landDemacreationVO.getSecPlotNo());
		inputpayload.setSector_block(landDemacreationVO.getSectorBlock());
		inputpayload.setSec_Sector_Name(landDemacreationVO.getSecSectorName());
		inputpayload.setSub_Sector_No(landDemacreationVO.getSubSectorNo());
		inputpayload.setArea_Name(landDemacreationVO.getAreaName());
		inputpayload.setArea_Block(landDemacreationVO.getAreaBlock());
		inputpayload.setArea_Plot_No(landDemacreationVO.getAreaPlotNo());
		inputpayload.setArea_Sub_Area(landDemacreationVO.getAreaSubArea());
		inputpayload.setArea_Sub_Area(landDemacreationVO.getAreaSubArea());
		inputpayload.setLand_Usage(landDemacreationVO.getLandUsage());
		inputpayload.setGrant_Issuance_Date(PortalDataMapper.toCalendar(landDemacreationVO.getGrantIssuanceDate()));
		inputpayload.setAssignedToUserName(landDemacreationVO.getAssignedToUserName());
		inputpayload.setSurveyor_Report(landDemacreationVO.getSurveyo_Report());
		inputpayload.setOwnerName(landDemacreationVO.getOwnerName());
		inputpayload.setSurve_Report_DocId(landDemacreationVO.getSurveReportDocId());
		inputpayload.setOwnerID(landDemacreationVO.getOwnerID());
		inputpayload.setOwner_Nationality_ID(landDemacreationVO.getOwnerNationalityID());
		inputpayload.setSupportiveAttachmentid(landDemacreationVO.getSupportiveAttachmentid());
		inputpayload.setExtendedDate(PortalDataMapper.toCalendar(landDemacreationVO.getExtendedDate()));
		inputpayload.setCommiteeRemarks(landDemacreationVO.getCommiteeRemarks());
		inputpayload.setLandValue(new BigInteger(landDemacreationVO.getLandValue()));
		
		com.AddLandRequest.AttachmentRecPayload[] attachmentList = new com.AddLandRequest.AttachmentRecPayload[1];
		attachmentList[0] = new com.AddLandRequest.AttachmentRecPayload();
		attachmentList[0].setContentid("1");
		attachmentList[0].setFilename("");
		attachmentList[0].setUrl(ApplicationConstants.FILE_SERVER_URL+ "");
		attachmentList[0].setIsMandatory("1");

		inputpayload.setAttachmentList(attachmentList);
		inputpayload.setUserDetails(userDetail);
		
		PaymentDetailsPayload paymentDetails=new PaymentDetailsPayload();
		paymentDetails.setApplicationPaymentDiscount("");
		paymentDetails.setApplicationPaymentFees("");
		paymentDetails.setApplicationPaymentId("");
		paymentDetails.setApplicationPaymentStatus("");
		paymentDetails.setServicePaymentDiscount("");
		paymentDetails.setServicePaymentFees(landDemacreationVO.getTransationAmount());
		paymentDetails.setServicePaymentId(landDemacreationVO.getTransactionId());
		paymentDetails.setServicePaymentStatus(landDemacreationVO.getTransationStatus());
		inputpayload.setPaymentDetails(paymentDetails);
		
		try {
			com.AddLandRequest.OutputPayload output=null;//stub.servicePaymentAddlandRequest(inputpayload, userContext);
			if (output != null) {
				outputVO.setStatus(output.getStausFlag());
				outputVO.setStatus_EN(output.getStatus_EN());
				outputVO.setStatus_AR(output.getStatus_AR());
				outputVO.setSatausId(output.getInputFields().getStatus());
				outputVO.setServiceId(output.getInputFields().getServiceid());
				outputVO.setRequestNo(output.getRequestNo());
				logger.info(output.getStausFlag() + "  | " + output.getStatus_EN());
			} else {
				logger.info(" Failed | stub.servicePaymentAddlandRequest(inputpayload, uc)  Return Null");
				outputVO.setStatus(SERVICE_FAILED);
			}
		} catch (Exception e) {
			logger.error("Failed  |  "+e.getMessage());
			outputVO.setStatus(SERVICE_FAILED);
		}*/
		return outputVO;
	}
	
	/**
	 * This Method is responsible for moving the ExtensionGrandLand request after Service Payment
	 * 
	 * WSDL -http://soa:8001/soa-infra/services/PS_Department/UAQ_PS_Extension_Of_Grant_Land/ExtensionOfGrantLandAfterServicePaymentForOut.service?WSDL
	 *
	 * JAR-UAQProxy
	 * @author Pritam
	 * 
	 */
	
	public LandOutputVO servicePaymentExtensionGrandLandRequest(UserDeatilVO user, LandInputVO landDemacreationVO) throws DatatypeConfigurationException, ParseException{
	
		logger.enter("servicePaymentExtensionGrandLandRequest");
		LandOutputVO outputVO = new LandOutputVO();
		com.uaq.proxies.ExtensionOfGrantLandAfterServicePaymentForOut.types.Inputpayload inputpayload =new com.uaq.proxies.ExtensionOfGrantLandAfterServicePaymentForOut.types.Inputpayload();
		com.uaq.proxies.ExtensionOfGrantLandAfterServicePaymentForOut.types.UserDetailsPayload  userDetail=new com.uaq.proxies.ExtensionOfGrantLandAfterServicePaymentForOut.types.UserDetailsPayload();
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
		userDetail.setDOB(null);
		
		if(user.getTypeOfUser().equals("1")){
			userDetail.setApplicantTypeid(user.getApplicantTypeId());	
		}else{
			userDetail.setApplicantTypeid("0");
		}
		
		inputpayload.setServiceType(landDemacreationVO.getServiceType());
		inputpayload.setUserComment(landDemacreationVO.getUserComment());
		inputpayload.setSubmittedByUserId(landDemacreationVO.getSubmittedByUserId());
		inputpayload.setSourceType(landDemacreationVO.getSourceType());
		inputpayload.setStatus(landDemacreationVO.getStatus());
		inputpayload.setServiceName(landDemacreationVO.getServiceName());
		inputpayload.setServiceid(landDemacreationVO.getServiceid());
		inputpayload.setRequestID(landDemacreationVO.getRequestID());
		inputpayload.setRequestNo(landDemacreationVO.getRequestNo());
		inputpayload.setWorkflowId(landDemacreationVO.getWorkflowId());
		//inputpayload.setLandDemarcationId(landDemacreationVO.getLandDemarcationId());
		inputpayload.setLanguageId(landDemacreationVO.getLanguageId());
		inputpayload.setSitePlanNo(landDemacreationVO.getSite_Plan_No());
		//inputpayload.setSitePlanDate(PortalDataMapper.toCalendar(landDemacreationVO.getSitePlanDate()));
		inputpayload.setSecPlotNo(landDemacreationVO.getSecPlotNo());
		inputpayload.setSectorBlock(landDemacreationVO.getSectorBlock());
		inputpayload.setSecSectorName(landDemacreationVO.getSecSectorName());
		inputpayload.setSubSectorNo(landDemacreationVO.getSubSectorNo());
		inputpayload.setAreaName(landDemacreationVO.getAreaName());
		inputpayload.setAreaBlock(landDemacreationVO.getAreaBlock());
		inputpayload.setAreaPlotNo(landDemacreationVO.getAreaPlotNo());
		inputpayload.setAreaSubArea(landDemacreationVO.getAreaSubArea());
		//inputpayload.setArea_Sub_Area(landDemacreationVO.getAreaSubArea());
		inputpayload.setLandUsage(landDemacreationVO.getLandUsage());
		//inputpayload.setGrantIssuance_Date(PortalDataMapper.toCalendar(landDemacreationVO.getGrantIssuanceDate()));
		//inputpayload.setAssignedToUserName(landDemacreationVO.getAssignedToUserName());
		//inputpayload.setSurveyor_Report(landDemacreationVO.getSurveyo_Report());
		inputpayload.setOwnerName(landDemacreationVO.getOwnerName());
		//inputpayload.setSurve_Report_DocId(landDemacreationVO.getSurveReportDocId());
		inputpayload.setOwnerID(landDemacreationVO.getOwnerID());
		inputpayload.setOwnerNationalityID(landDemacreationVO.getOwnerNationalityID());
		inputpayload.setSupportiveAttachmentid(landDemacreationVO.getSupportiveAttachmentid());
		//inputpayload.setExtendedDate(PortalDataMapper.toCalendar(landDemacreationVO.getExtendedDate()));
		//inputpayload.setCommiteeRemarks(landDemacreationVO.getCommiteeRemarks());
		//inputpayload.setLandDemarcationId(landDemacreationVO.getLandDemarcationId());
		inputpayload.setSubmittedByUserId(user.getUsername());
		//inputpayload.setExtensionofgrantlandid(landDemacreationVO.getExtensionOfGrantLandId());

		/*inputpayload.setSurveyor_Report("");
		inputpayload.setSurve_Report_DocId("");
		inputpayload.setMarkasDelivered("");
		inputpayload.setOperatorComment("");
		inputpayload.setRecieverIDDocument("");
		inputpayload.setSubmittedDate(Calendar.getInstance());*/
		

		
		com.uaq.proxies.ExtensionOfGrantLandAfterServicePaymentForOut.types.PaymentDetailsPayload paymentDetails=new com.uaq.proxies.ExtensionOfGrantLandAfterServicePaymentForOut.types.PaymentDetailsPayload();
		paymentDetails.setApplicationPaymentDiscount("");
		paymentDetails.setApplicationPaymentFees("");
		paymentDetails.setApplicationPaymentId("");
		paymentDetails.setApplicationPaymentStatus("");
		paymentDetails.setServicePaymentDiscount("");
		paymentDetails.setServicePaymentFees(landDemacreationVO.getTransationAmount());
		paymentDetails.setServicePaymentId(landDemacreationVO.getTransactionId());
		paymentDetails.setServicePaymentStatus(landDemacreationVO.getTransationStatus());
		inputpayload.setPaymentDetails(paymentDetails);
		
		/*com.ExtensionOfGrantLand.AttachmentRecPayload[] attachmentList = new com.ExtensionOfGrantLand.AttachmentRecPayload[1];
		attachmentList[0] = new com.ExtensionOfGrantLand.AttachmentRecPayload();
		attachmentList[0].setContentid("");
		attachmentList[0].setFilename("");
		attachmentList[0].setUrl(ApplicationConstants.FILE_SERVER_URL+ "");
		attachmentList[0].setIsMandatory("1");
		//attachmentList[0].setDownloadUrl("");

		inputpayload.setAttachmentList(attachmentList);*/
		inputpayload.setUserDetails(userDetail);
		
		//ActionLogRecPayload  actionLogList[] = new ActionLogRecPayload[1];
		
		//inputpayload.setActionLogList(actionLogList);
		try {
			com.uaq.proxies.ExtensionOfGrantLandAfterServicePaymentForOut.types.OutputPayload output=webServiceWarpper.servicePaymentDoneExtGrandLand(inputpayload, PropertiesUtil.getProperty("SOA_URL_PS_EXTENTIONGRANDLAND_SERVICEPAYMENT_DONE"), PropertiesUtil.getProperty("SOA_USERNAME"),
					PropertiesUtil.getProperty("SOA_PASSWORD"));
			if (output != null) {
				outputVO.setStatus(output.getStausFlag());
				outputVO.setStatus_EN(output.getStatusEN());
				outputVO.setStatus_AR(output.getStatusAR());
				outputVO.setSatausId(output.getInputFields().getStatus());
				outputVO.setServiceId(output.getInputFields().getServiceid());
				outputVO.setRequestNo(output.getRequestNo());
				logger.info(output.getStausFlag() + "  | " + output.getStatusEN());
			} else {
				logger.info(" Failure | stub.servicePaymentExtensionGrandLandRequest(inputpayload, uc)  Return Null");
				outputVO.setStatus(SERVICE_FAILED);
			}
		} catch (Exception e) {
			logger.error("Failure  |  "+e.getMessage());
			outputVO.setStatus(SERVICE_FAILED);
		}
		return outputVO;
	}

}
