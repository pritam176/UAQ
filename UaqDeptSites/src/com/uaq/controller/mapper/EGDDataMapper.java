package com.uaq.controller.mapper;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import com.uaq.command.NewSupplierRegistrationCommand;
import com.uaq.util.DateUtil;
import com.uaq.util.StringUtil;
import com.uaq.vo.AccountDetailTokenOutputVO;
import com.uaq.vo.EGDResubmissionVO;
import com.uaq.vo.NewSupplierRegistrationVO;

public class EGDDataMapper {

	/***
	 * setting Account Detail & NewSupplier Command Data to Service Input
	 ***/

	public static NewSupplierRegistrationVO getSupplierData(AccountDetailTokenOutputVO accountDetailfromToken, NewSupplierRegistrationCommand newSupplierRegistrationCommand) {

		NewSupplierRegistrationVO supplierVo = new NewSupplierRegistrationVO();
		supplierVo.setEstablishmentName(newSupplierRegistrationCommand.getEstablishmentName());
		supplierVo.setTradeLicenceNumber(newSupplierRegistrationCommand.getTradeLicenceNumber());
		supplierVo.setExpiryDate(newSupplierRegistrationCommand.getExpiryDate());
		supplierVo.setMobileNumber(newSupplierRegistrationCommand.getMobileNumber());
		supplierVo.setAddress(newSupplierRegistrationCommand.getAddress());
		supplierVo.setRequestType("1");
		supplierVo.setSourceType("1");
		supplierVo.setUserName(accountDetailfromToken.getUserName());
		supplierVo.setServiceId("501");
		supplierVo.setSuppRegId("");
		supplierVo.setEmirates(newSupplierRegistrationCommand.getEmirates());
		supplierVo.setSuppCatgId(newSupplierRegistrationCommand.getSupplierCategory());
		supplierVo.setUserCommnets("");
		supplierVo.setSupplierCategory(newSupplierRegistrationCommand.getSupplierCategory());
		supplierVo.setRequestId(1);
		supplierVo.setRequestNo("");
		supplierVo.setWorkListId(1);
		supplierVo.setSupprReqId(1);
		supplierVo.setStateId(1);
		supplierVo.setTempSupprReqId(1);
		supplierVo.setServiceName_En("Supplier Registration");
		supplierVo.setServiceName_Ar("Supplier Registration");
		supplierVo.setRegistrationType(newSupplierRegistrationCommand.getRegistrationType());
		supplierVo.setRegistrationsType(newSupplierRegistrationCommand.getRegistrationsType());
		supplierVo.setPaymentId(7);
		supplierVo.setPaymentStatus("");
		supplierVo.setServiceFee("");
		supplierVo.setApplicationFee("");
		supplierVo.setDeptID("");
		supplierVo.setServiceFeeDisc("");
		supplierVo.setEdirhamServCode("");
		supplierVo.setIsPaymentRequired("");
		supplierVo.setEmail(newSupplierRegistrationCommand.getEmail());
		supplierVo.setOfficeNumber(newSupplierRegistrationCommand.getOfficeNumber());
		supplierVo.setPostBox(newSupplierRegistrationCommand.getPostBox());
		supplierVo.setUserAttacmentListVO(accountDetailfromToken.getUserAttachmentsList());

		Map<String, String> fileListName = new HashMap<String, String>();
		//As will come from UserAtatchment
		/*if (!StringUtil.isEmpty(newSupplierRegistrationCommand.getTradingLicence_name())) {
			fileListName.put("1", newSupplierRegistrationCommand.getTradingLicence_name());

			String did = newSupplierRegistrationCommand.getTradingLicence_name().split("-")[0];
			String name = newSupplierRegistrationCommand.getTradingLicence_name().split("-")[1];
			supplierVo.setTradeLicenceName(did + "|" + name);
			supplierVo.setCertificates(did + "|" + name);
		}

		if (!StringUtil.isEmpty(newSupplierRegistrationCommand.getSignatureAttestation_name())) {
			fileListName.put("2", newSupplierRegistrationCommand.getSignatureAttestation_name());

			String did = newSupplierRegistrationCommand.getSignatureAttestation_name().split("-")[0];
			String name = newSupplierRegistrationCommand.getSignatureAttestation_name().split("-")[1];
			supplierVo.setSignatureAttestation(did + "|" + name);
		}*/

		if (!StringUtil.isEmpty(newSupplierRegistrationCommand.getChamberOfCommerce_name())) {
			fileListName.put("3", newSupplierRegistrationCommand.getChamberOfCommerce_name());
			String did = newSupplierRegistrationCommand.getChamberOfCommerce_name().split("-")[0];
			String name = newSupplierRegistrationCommand.getChamberOfCommerce_name().split("-")[1];

			supplierVo.setChamberOfCommerce(did + "|" + name);
		}
		int counter=0;
		for (String file : newSupplierRegistrationCommand.getSupplier_file_name()) {
			
			if(!StringUtil.isEmpty(file)){
				if (file.trim().length() > 0) {
					fileListName.put("others"+counter, file);
					counter++;
				}
			}
			
		}
		supplierVo.setFileList(fileListName);
		return supplierVo;
	}

	public static NewSupplierRegistrationVO getSupplierDataForResubmit(AccountDetailTokenOutputVO accountDetailfromToken, NewSupplierRegistrationCommand newSupplierRegistrationCommand){
			

		NewSupplierRegistrationVO supplierVo = new NewSupplierRegistrationVO();
		supplierVo.setEstablishmentName(newSupplierRegistrationCommand.getEstablishmentName());
		supplierVo.setTradeLicenceNumber(newSupplierRegistrationCommand.getTradeLicenceNumber());
		supplierVo.setExpiryDate(newSupplierRegistrationCommand.getExpiryDate());

		supplierVo.setRequestType("1");
		supplierVo.setSourceType("1");
		supplierVo.setUserName(accountDetailfromToken.getUserName());
		supplierVo.setServiceId("501");
		supplierVo.setSuppRegId("1");
		supplierVo.setEmirates(newSupplierRegistrationCommand.getEmirates());
		supplierVo.setSuppCatgId(newSupplierRegistrationCommand.getSupplierCategory());
		supplierVo.setUserCommnets("");
		supplierVo.setSupplierCategory(newSupplierRegistrationCommand.getSupplierCategory());
		supplierVo.setRequestId(Integer.parseInt(newSupplierRegistrationCommand.getRequestNo().split("-")[3]));
		supplierVo.setRequestNo(newSupplierRegistrationCommand.getRequestNo());
		supplierVo.setWorkListId(1);
		supplierVo.setSupprReqId(1);
		supplierVo.setAddress(newSupplierRegistrationCommand.getAddress());
		supplierVo.setPostBox(newSupplierRegistrationCommand.getPostBox());
		supplierVo.setOfficeNumber(newSupplierRegistrationCommand.getOfficeNumber());
		// For resubmit the state ID should be 2
		supplierVo.setStateId(2);
		supplierVo.setTempSupprReqId(1);
		supplierVo.setServiceName_En("Supplier Registration");
		supplierVo.setServiceName_Ar("Supplier Registration");
		supplierVo.setRegistrationsType(newSupplierRegistrationCommand.getRegistrationsType());
		supplierVo.setRegistrationType(newSupplierRegistrationCommand.getRegistrationType());
		supplierVo.setPaymentId(7);
		supplierVo.setPaymentStatus("7");
		supplierVo.setServiceFee("400");
		supplierVo.setApplicationFee("244");
		supplierVo.setDeptID("123");
		supplierVo.setServiceFeeDisc("1");
		supplierVo.setEdirhamServCode("1");
		supplierVo.setIsPaymentRequired("1");
		supplierVo.setOfficeNumber(newSupplierRegistrationCommand.getOfficeNumber());
		/*
		 * supplierVo.setTradingLicence(newSupplierRegistrationCommand.
		 * getTradingLicence());
		 * supplierVo.setSignatureAttestation(newSupplierRegistrationCommand
		 * .getSignatureAttestation());
		 * supplierVo.setChamberOfCommerce(newSupplierRegistrationCommand
		 * .getChamberOfCommerce());
		 * supplierVo.setCertificates(newSupplierRegistrationCommand
		 * .getCertificates());
		 */
		supplierVo.setUserAttacmentListVO(accountDetailfromToken.getUserAttachmentsList());
		Map<String, String> fileListName = new HashMap<String, String>();

		if (!StringUtil.isEmpty(newSupplierRegistrationCommand.getChamberOfCommerce_name())) {
			fileListName.put("3", newSupplierRegistrationCommand.getChamberOfCommerce_name());
			String did = newSupplierRegistrationCommand.getChamberOfCommerce_name().split("-")[0];
			String name = newSupplierRegistrationCommand.getChamberOfCommerce_name().split("-")[1];

			supplierVo.setChamberOfCommerce(did + "|" + name);
		}
		//As will come from UserAtatchment
		/*if (!StringUtil.isEmpty(newSupplierRegistrationCommand.getSignatureAttestation_name())) {
			fileListName.put("2", newSupplierRegistrationCommand.getSignatureAttestation_name());

			String did = newSupplierRegistrationCommand.getSignatureAttestation_name().split("-")[0];
			String name = newSupplierRegistrationCommand.getSignatureAttestation_name().split("-")[1];
			supplierVo.setSignatureAttestation(did + "|" + name);
		}

		if (!StringUtil.isEmpty(newSupplierRegistrationCommand.getTradingLicence_name())) {
			fileListName.put("1", newSupplierRegistrationCommand.getTradingLicence_name());
			String did = newSupplierRegistrationCommand.getTradingLicence_name().split("-")[0];
			String name = newSupplierRegistrationCommand.getTradingLicence_name().split("-")[1];
			supplierVo.setTradeLicenceName(did + "|" + name);
			supplierVo.setCertificates(did + "|" + name);
		}*/
		int counter=0;
		for (String file : newSupplierRegistrationCommand.getSupplier_file_name()) {
			if (file.trim().length() > 0) {
				fileListName.put("others"+counter, file);
				counter++;
			}
		}
		supplierVo.setFileList(fileListName);

		return supplierVo;
	}

	/***
	 * setting Resubmission Data to Modal
	 ***/

	public static NewSupplierRegistrationCommand addResubmissionDataToModal(EGDResubmissionVO eGDResubmissionVO, AccountDetailTokenOutputVO accountDetailfromToken) {

		// will change this later
		
		String tradeLicenseExpiryDate = DateUtil.converXMLCalenderTOString(eGDResubmissionVO.getTradeLicenseExpiryDate());

		NewSupplierRegistrationCommand newSupplierRegistrationCommand = new NewSupplierRegistrationCommand();
		newSupplierRegistrationCommand.setEstablishmentName(eGDResubmissionVO.getEstablishmentName());
		newSupplierRegistrationCommand.setTradeLicenceNumber(eGDResubmissionVO.getTradeLicenseNum());
		newSupplierRegistrationCommand.setExpiryDate(tradeLicenseExpiryDate);
		newSupplierRegistrationCommand.setSupplierCategory(eGDResubmissionVO.getSupplierCategory());
		newSupplierRegistrationCommand.setEmirates(eGDResubmissionVO.getEmirates());
		newSupplierRegistrationCommand.setMobileNumber(accountDetailfromToken.getMobileNo());
		newSupplierRegistrationCommand.setEmail(accountDetailfromToken.getEmailAddress());
		newSupplierRegistrationCommand.setRegistrationsType(eGDResubmissionVO.getRegistrationTypeArray());
		newSupplierRegistrationCommand.setRegistrationType(eGDResubmissionVO.getRegistrationType());
		newSupplierRegistrationCommand.setSupplierFiles(eGDResubmissionVO.getAttachmentRecPayload());
		newSupplierRegistrationCommand.setAddress(eGDResubmissionVO.getAddress());
		newSupplierRegistrationCommand.setOfficeNumber(eGDResubmissionVO.getTelephoneNumber());
		newSupplierRegistrationCommand.setPostBox(String.valueOf(eGDResubmissionVO.getPostBox()));
		newSupplierRegistrationCommand.setEmirates(eGDResubmissionVO.getEmirates());
		newSupplierRegistrationCommand.setEmirateId(accountDetailfromToken.getEmiratesId());
		newSupplierRegistrationCommand.setRequestNo(eGDResubmissionVO.getRequestNo());
		newSupplierRegistrationCommand.setStatusId(eGDResubmissionVO.getStatus());
		return newSupplierRegistrationCommand;

	}

	/***
	 * setting Resubmission Data to NewSupplierRegistrationVO for After payment
	 * Service Call
	 ***/
	public static NewSupplierRegistrationVO setSupplierDataForAfterPayment(AccountDetailTokenOutputVO accountDetailfromToken, EGDResubmissionVO eGDResubmissionVO) {

		NewSupplierRegistrationVO supplierVo = new NewSupplierRegistrationVO();
		supplierVo.setEstablishmentName(eGDResubmissionVO.getEstablishmentName());
		supplierVo.setTradeLicenceNumber(eGDResubmissionVO.getTradeLicenseNum());
		// supplierVo.setExpiryDate(eGDResubmissionVO.getTrdExpDate());
		supplierVo.setRequestType("1");
		supplierVo.setSourceType("1");
		supplierVo.setUserName(accountDetailfromToken.getUserName());
		supplierVo.setServiceId("501");
		supplierVo.setSuppRegId("1");
		supplierVo.setSuppCatgId(String.valueOf(eGDResubmissionVO.getSuppCatgId()));
		supplierVo.setUserCommnets("");
		supplierVo.setSupplierCategory("");
		supplierVo.setRequestId(Integer.parseInt(eGDResubmissionVO.getReqId()));
		supplierVo.setRequestNo(eGDResubmissionVO.getRequestNo());
		supplierVo.setWorkListId(1);
		supplierVo.setSupprReqId(1);
		supplierVo.setStateId(1);
		supplierVo.setTempSupprReqId(1);
		supplierVo.setServiceName_En("Supplier Registration");
		supplierVo.setServiceName_Ar("Supplier Registration");
		// supplierVo.setRegistrationsType(eGDResubmissionVO.getRegistrationsType());
		supplierVo.setRegistrationType(eGDResubmissionVO.getRegistrationType());
		supplierVo.setPaymentId(7);
		supplierVo.setPaymentStatus("7");
		supplierVo.setServiceFee("400");
		supplierVo.setApplicationFee("244");
		supplierVo.setDeptID("123");
		supplierVo.setServiceFeeDisc("1");
		supplierVo.setEdirhamServCode("1");
		supplierVo.setIsPaymentRequired("1");
		supplierVo.setOfficeNumber("1234");
		// supplierVo.setTradingLicence();
		// supplierVo.setSignatureAttestation(eGDResubmissionVO.getSignatureAttestation());
		// supplierVo.setChamberOfCommerce(eGDResubmissionVO.getChamberOfCommerce());
		// supplierVo.setCertificates(eGDResubmissionVO.getCertificates());

		return supplierVo;
	}

	public static NewSupplierRegistrationCommand setuserDataToNewSupplierCommand(AccountDetailTokenOutputVO accountDetailfromToken) {
		NewSupplierRegistrationCommand newSupplierRegistrationCommand = new com.uaq.command.NewSupplierRegistrationCommand();
		newSupplierRegistrationCommand.setEstablishmentName(accountDetailfromToken.getFirstName());
		newSupplierRegistrationCommand.setEmail(accountDetailfromToken.getEmailAddress());
		newSupplierRegistrationCommand.setMobileNumber(accountDetailfromToken.getMobileNo());
		newSupplierRegistrationCommand.setExpiryDate(accountDetailfromToken.getTradeLienceExpiryDate());
		newSupplierRegistrationCommand.setTradeLicenceNumber(accountDetailfromToken.getTradeLienceNo());
		newSupplierRegistrationCommand.setEmirateId(accountDetailfromToken.getEmiratesId());

		return newSupplierRegistrationCommand;
	}

	public static NewSupplierRegistrationCommand setuserDataToReNewSupplierCommand(AccountDetailTokenOutputVO accountDetailfromToken) {
		NewSupplierRegistrationCommand newSupplierRegistrationCommand = new com.uaq.command.NewSupplierRegistrationCommand();
		newSupplierRegistrationCommand.setEstablishmentName(accountDetailfromToken.getFirstName());
		newSupplierRegistrationCommand.setEmail(accountDetailfromToken.getEmailAddress());
		newSupplierRegistrationCommand.setMobileNumber(accountDetailfromToken.getMobileNo());
		newSupplierRegistrationCommand.setExpiryDate(accountDetailfromToken.getTradeLienceExpiryDate());
		newSupplierRegistrationCommand.setTradeLicenceNumber(accountDetailfromToken.getTradeLienceNo());
		newSupplierRegistrationCommand.setEmirateId(accountDetailfromToken.getEmiratesId());
		return newSupplierRegistrationCommand;
	}

	public static NewSupplierRegistrationVO setSupplierDetailToReviewService(EGDResubmissionVO eGDResubmissionVO, AccountDetailTokenOutputVO accountDetailfromToken) {
		NewSupplierRegistrationVO supplierVo = new NewSupplierRegistrationVO();
		supplierVo.setEstablishmentName(eGDResubmissionVO.getEstablishmentName());
		supplierVo.setTradeLicenceNumber(eGDResubmissionVO.getTradeLicenseNum());
		supplierVo.setExpiryDate("2015-01-01");
		supplierVo.setRequestType("1");
		supplierVo.setSourceType("1");
		supplierVo.setUserName(accountDetailfromToken.getUserName());
		supplierVo.setServiceId("501");
		supplierVo.setSuppRegId("1");
		supplierVo.setSuppCatgId(String.valueOf(eGDResubmissionVO.getSuppCatgId()));
		supplierVo.setUserCommnets("1");
		supplierVo.setSupplierCategory("");
		supplierVo.setRequestId(Integer.parseInt(eGDResubmissionVO.getReqId()));
		supplierVo.setRequestNo(eGDResubmissionVO.getRequestNo());
		supplierVo.setWorkListId(1);
		supplierVo.setSupprReqId(1);
		supplierVo.setStateId(1);
		supplierVo.setTempSupprReqId(1);
		supplierVo.setServiceName_En("Supplier Registration");
		supplierVo.setServiceName_Ar("Supplier Registration");
		// supplierVo.setRegistrationsType(eGDResubmissionVO.getRegistrationsType());
		supplierVo.setRegistrationType(eGDResubmissionVO.getRegistrationType());
		supplierVo.setPaymentId(7);
		supplierVo.setPaymentStatus("7");
		supplierVo.setServiceFee("400");
		supplierVo.setApplicationFee("244");
		supplierVo.setDeptID("123");
		supplierVo.setServiceFeeDisc("1");
		supplierVo.setEdirhamServCode("1");
		supplierVo.setIsPaymentRequired("1");
		supplierVo.setOfficeNumber("1234");
		return supplierVo;

	}
}
