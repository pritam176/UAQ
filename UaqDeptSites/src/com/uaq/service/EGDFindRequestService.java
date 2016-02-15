/**
 * 
 */
package com.uaq.service;

import java.math.BigInteger;
import java.rmi.RemoteException;
import java.util.Date;

import javax.xml.rpc.ServiceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uaq.service.egd.model.EGDMiddlewareGetSupplier;
import uaq.service.egd.model.EGDMiddlewareGetSupplierPortBindingStub;
import uaq.service.egd.model.EGDMiddlewareGetSupplierService;
import uaq.service.egd.model.EGDMiddlewareGetSupplierServiceLocator;
import uaq.service.egd.model.UserContext;

import com.oracle.xmlns.UAQ_eGD_BusinessProcess.Supp_Registration_Renew.Supp_Registration_BPEL.CommonRequestBPMType;
import com.oracle.xmlns.UAQ_eGD_BusinessProcess.Supp_Registration_Renew.Supp_Registration_BPEL.OutputPayload;
import com.oracle.xmlns.UAQ_eGD_BusinessProcess.Supp_Registration_Renew.Supp_Registration_BPEL.ProcessType;
import com.oracle.xmlns.UAQ_eGD_BusinessProcess.Supp_Registration_Renew.Supp_Registration_BPEL.RegistrationTypeRecType;
import com.oracle.xmlns.UAQ_eGD_BusinessProcess.Supp_Registration_Renew.Supp_Registration_BPEL.Supp_RegisterRenewReqType;
import com.oracle.xmlns.UAQ_eGD_BusinessProcess.Supp_Registration_Renew.Supp_Registration_BPEL.UserDetailsPayload;
import com.uaq.common.ApplicationConstants;
import com.uaq.controller.mapper.PortalDataMapper;
import com.uaq.logger.UAQLogger;
import com.uaq.vo.EGDResubmissionVO;
import com.uaq.vo.ReSubmiisionInputVO;

/**
 * @author WINDOS
 *
 */
@Service("eGDFindRequestService")
public class EGDFindRequestService {
	
	protected static UAQLogger logger = new UAQLogger(EGDFindRequestService.class);
	
	@Autowired
	private PortalUtil portalUtil;
	
	private uaq.service.egd.model.UserContext uc = null;
	private EGDMiddlewareGetSupplierService service = null;
	private EGDMiddlewareGetSupplier port = null;
	private EGDMiddlewareGetSupplierPortBindingStub stub = null;


	
	private void creteStub() {
		uc=new UserContext();
		uc.setUsername(ApplicationConstants.WS_USERNAME);
		uc.setPassword(ApplicationConstants.WS_PASSWORD);

		try {
			service = (EGDMiddlewareGetSupplierService) new EGDMiddlewareGetSupplierServiceLocator();
			port = service.getEGDMiddlewareGetSupplierPort();
			stub = (EGDMiddlewareGetSupplierPortBindingStub) port;

		} catch (ServiceException e) {
			logger.error("WebService Error  |" + e.getMessage());
			e.printStackTrace();

		}

	}
	
	public EGDResubmissionVO findNewSupplierRequest(ReSubmiisionInputVO inputVO,String languageCode) {
		creteStub();
		OutputPayload outputPayload = null;
		ProcessType processType = new ProcessType();
		
		CommonRequestBPMType commonRequestBPMType = new CommonRequestBPMType();
		Supp_RegisterRenewReqType suppDetails = new Supp_RegisterRenewReqType();
		com.oracle.xmlns.UAQ_eGD_BusinessProcess.Supp_Registration_Renew.Supp_Registration_BPEL.UserDetailsPayload userDetailsPayload = new UserDetailsPayload(); 
		suppDetails.setEstablishmentName("");
		suppDetails.setTradeLicenceNo("");
		suppDetails.setTradExpDateo(new Date());
		suppDetails.setRequestType("");
		suppDetails.setSourceType("");
		suppDetails.setServiceId("501");
		suppDetails.setSupp_Reg_Id("1");
		suppDetails.setSupp_Catg_Id("");
		suppDetails.setUserCommnets("");
		suppDetails.setOff_tel_num("");
		suppDetails.setSupplierCategory("");
		suppDetails.setRegistrationType("");
		suppDetails.setCertificateAttachemntid("");
		suppDetails.setCOCMemAttachmentid("");
		suppDetails.setSignetoryAttestationAttachid("");
		suppDetails.setTradeLicenseAttachmentid("");
		suppDetails.setSupportiveAttachmentid("");
		RegistrationTypeRecType regRecType[] = new RegistrationTypeRecType[1];
		regRecType[0] = new RegistrationTypeRecType();
		regRecType[0].setRegistrationTypeId("1");
		regRecType[0].setRegistrationType("1");

		suppDetails.setRegistrationTypeList(regRecType);
		commonRequestBPMType.setRequestId(new Integer(inputVO.getAttributeValue().split("-")[3]));
		commonRequestBPMType.setRequestNo(inputVO.getAttributeValue());
		commonRequestBPMType.setStateId(2);
		
		userDetailsPayload.setLanguage_ID(new BigInteger(PortalDataMapper.getLanguageId(languageCode)));
		userDetailsPayload.setAccountid("");
		userDetailsPayload.setAddress1("");
		userDetailsPayload.setAddress2("");
		userDetailsPayload.setDOB(new Date());
		userDetailsPayload.setEDIA_Expirty_Date(new Date());
		userDetailsPayload.setEirates_Code(new BigInteger("0"));
		userDetailsPayload.setEmailID("");
		userDetailsPayload.setEmirate("");
		userDetailsPayload.setEmiratesId("");
		userDetailsPayload.setFamilyBookNo("");
		userDetailsPayload.setFirstName("");
		userDetailsPayload.setHome_Phone("");
		userDetailsPayload.setLanguage("");
		userDetailsPayload.setLastName("");
		userDetailsPayload.setMiddleName("");
		userDetailsPayload.setMobileNo("");
		userDetailsPayload.setNationality("");
		userDetailsPayload.setNationality_ID(new BigInteger("0"));
		userDetailsPayload.setPOBOX(new BigInteger("0"));
		userDetailsPayload.setTradeLienceNo("");
		userDetailsPayload.setTypeOfUser("");
		userDetailsPayload.setUsername("");
		
		processType.setCommonRequestBPM(commonRequestBPMType);
		processType.setSuppDetails(suppDetails);
		processType.setUserDetails(userDetailsPayload);
		
		
		EGDResubmissionVO eGDResubmissionVO = new EGDResubmissionVO();
		try {
			outputPayload = stub.getSupplierDetails(processType,uc);
			if (outputPayload != null) {
					eGDResubmissionVO.setStatus("Success");
					eGDResubmissionVO.setEstablishmentName(outputPayload.getEstablishmentName());
					eGDResubmissionVO.setAttachmentRecPayload(outputPayload.getAttachmentList());
					eGDResubmissionVO.setTradeLicenseNum(outputPayload.getTradeLicenceNo());
					eGDResubmissionVO.setTradeLicenseExpiryDate(outputPayload.getExpiryDate());
					eGDResubmissionVO.setSupplierCategory(outputPayload.getSuppCategory_en());
					eGDResubmissionVO.setRegistrationTypeArray(outputPayload.getSuppRegistrationType_en().split(","));
					eGDResubmissionVO.setRegistrationType(outputPayload.getSuppRegistrationType_en());
					eGDResubmissionVO.setReviewerComments(outputPayload.getReviewer_Comments());	
					eGDResubmissionVO.setReqId(outputPayload.getRequestId());
					eGDResubmissionVO.setRequestNo(outputPayload.getRequestNo());
					
					
					if(outputPayload.getUserDetails()!=null){
						eGDResubmissionVO.setEmirates(String.valueOf(outputPayload.getUserDetails().getEirates_Code()));
						//Post Box
						eGDResubmissionVO.setPostBox(String.valueOf(outputPayload.getUserDetails().getPOBOX()));
						//Address Field.
						eGDResubmissionVO.setAddress(outputPayload.getUserDetails().getAddress1());
						//Office Telephone Number.
						eGDResubmissionVO.setTelephoneNumber(outputPayload.getUserDetails().getHome_Phone());
					}if(outputPayload.getUserDetails()==null){
						logger.info("outputPayload.getUserDetails() is null");
						eGDResubmissionVO.setStatus("Failed");
					}
				
			} else {
				eGDResubmissionVO.setStatus("Failed");
				logger.info("Failure  | stub.findEgdSuppDetailsView1(findCriteria, findControl); return null");
			}
		} catch (RemoteException e) {
			eGDResubmissionVO.setStatus("Failed");
			logger.error("Failure  | " + e.getMessage());
		}
		return eGDResubmissionVO;
	}

}
