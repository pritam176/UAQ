package com.uaq.payment;

/**
 * This class represents the ServiceCode and Merchant Account info for particular e-service.
 * It is used in Payment Request objects to pass through.
 * 
 * @author raheem
 * 
 */

public class PaymentServiceCode {
	
	//e-service info
		
	private String serviceId; // eServiceId which identifies application e-service such as Land and Properties
	
	private String departmentId;
			
	private Double serviceFee;
	
	//Service Code info
	
	private String serviceCode;
	
	private Boolean isVar;
	
	private Boolean isFixed;	
	
	//Merchant info	
	
	MerchantAccount merchantAccount;

	/**
	 * @return the serviceId
	 */
	public String getServiceId() {
		return serviceId;
	}

	/**
	 * @param serviceId the serviceId to set
	 */
	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

	/**
	 * @return the departmentId
	 */
	public String getDepartmentId() {
		return departmentId;
	}

	/**
	 * @param departmentId the departmentId to set
	 */
	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}

	/**
	 * @return the serviceFee
	 */
	public Double getServiceFee() {
		return serviceFee;
	}

	/**
	 * @param serviceFee the serviceFee to set
	 */
	public void setServiceFee(Double serviceFee) {
		this.serviceFee = serviceFee;
	}

	/**
	 * @return the serviceCode
	 */
	public String getServiceCode() {
		return serviceCode;
	}

	/**
	 * @param serviceCode the serviceCode to set
	 */
	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}

	/**
	 * @return the isVar
	 */
	public Boolean getIsVar() {
		return isVar;
	}

	/**
	 * @param isVar the isVar to set
	 */
	public void setIsVar(Boolean isVar) {
		this.isVar = isVar;
	}

	/**
	 * @return the isFixed
	 */
	public Boolean getIsFixed() {
		return isFixed;
	}

	/**
	 * @param isFixed the isFixed to set
	 */
	public void setIsFixed(Boolean isFixed) {
		this.isFixed = isFixed;
	}

	/**
	 * @return the merchantAccount
	 */
	public MerchantAccount getMerchantAccount() {
		return merchantAccount;
	}

	/**
	 * @param merchantAccount the merchantAccount to set
	 */
	public void setMerchantAccount(MerchantAccount merchantAccount) {
		this.merchantAccount = merchantAccount;
	}

	
	@Override
	public String toString() {
		return "PaymentServiceCode [serviceId=" + serviceId + ", departmentId=" + departmentId + ", serviceFee=" + serviceFee + ", serviceCode="
				+ serviceCode + ", isVar=" + isVar + ", isFixed=" + isFixed + "]";
	}
			
}
