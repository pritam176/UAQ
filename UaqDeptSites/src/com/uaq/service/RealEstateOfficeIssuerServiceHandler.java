package com.uaq.service;

import java.util.Map;

import com.uaq.common.PropertiesUtil;

public class RealEstateOfficeIssuerServiceHandler extends RealEstateOfficeServiceHandler {

	@Override
	public void issueServiceRequest(Map<String, Object> inputParams) throws Exception {
		WebServiceInvoker.issueNewRealEstateProcess(inputParams);
	}

	@Override
	public String getPhaseActivityName(String phase) {
		if (phase != null && phase.equals("Resubmit"))
			return "Resubmit New RealEstate Request Task";
		else if (phase != null && phase.equals("Step1"))
			return "Submit Trading License";
		return "";
	}

	@Override
	public void proceedWithServiceAfterPayment(Map<String, String> params) throws Exception {
		WebServiceInvoker.proceedWithLpServiceAfterPayment(PropertiesUtil.getProperty("SOA_URL_ISSUE_REALESTATE"), "payServiceFeesMessage", "RequestNo", params.get("requestNumber"));
	}
}