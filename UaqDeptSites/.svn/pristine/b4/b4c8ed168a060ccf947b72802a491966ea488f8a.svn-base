package com.uaq.service;

import java.util.Map;

import com.uaq.common.PropertiesUtil;

public class RealEstateOfficeRenewerServiceHandler extends RealEstateOfficeServiceHandler {

	@Override
	public void issueServiceRequest(Map<String, Object> inputParams) throws Exception {
		WebServiceInvoker.issueRenewRealEstateProcess(inputParams);
	}

	@Override
	public String getPhaseActivityName(String phase) {
		if (phase != null && phase.equals("Resubmit"))
			return "ReSubmit Request  Renew Real Estate Office";
		else if (phase != null && phase.equals("Step1"))
			return "Submit Renewed Trading License";
		return "";

	}

	@Override
	public void proceedWithServiceAfterPayment(Map<String, String> params) throws Exception {
		WebServiceInvoker.proceedWithLpServiceAfterPayment(PropertiesUtil.getProperty("SOA_URL_RENEW_REALESTATE"), "payServiceFeesMessage", "RequestNo", params.get("requestNumber"));
	}
}
