package com.uaq.payment;

/**
 * This class is used to hold serviceId and payment statusId (not eDirham but eService) for particular request
 * @author raheem
 *
 */
public class PaymentStatus{
	
	private String requestId;
	
	private String serviceId;
	
	private String statusId;
	
	private String requestNo;

	/**
	 * @return the requestId
	 */
	public String getRequestId() {
		return requestId;
	}

	/**
	 * @param requestId the requestId to set
	 */
	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

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
	 * @return the statusId
	 */
	public String getStatusId() {
		return statusId;
	}

	/**
	 * @param statusId the statusId to set
	 */
	public void setStatusId(String statusId) {
		this.statusId = statusId;
	}

	/**
	 * @return the requestNo
	 */
	public String getRequestNo() {
		return requestNo;
	}

	/**
	 * @param requestNo the requestNo to set
	 */
	public void setRequestNo(String requestNo) {
		this.requestNo = requestNo;
	}


	@Override
	public String toString() {
		return "PaymentStatus [requestId=" + requestId + ", serviceId=" + serviceId + ", statusId=" + statusId + ", requestNo=" + requestNo + "]";
	}
	
}
