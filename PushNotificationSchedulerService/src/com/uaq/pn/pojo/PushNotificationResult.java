package com.uaq.pn.pojo;

public class PushNotificationResult {

	String deviceId;
	
	String messageId;
	
	Boolean deliveryResult;

	/**
	 * @return the deviceId
	 */
	public String getDeviceId() {
		return deviceId;
	}

	/**
	 * @param deviceId the deviceId to set
	 */
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	/**
	 * @return the messageId
	 */
	public String getMessageId() {
		return messageId;
	}

	/**
	 * @param messageId the messageId to set
	 */
	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}

	/**
	 * @return the deliveryResult
	 */
	public Boolean getDeliveryResult() {
		return deliveryResult;
	}

	/**
	 * @param deliveryResult the deliveryResult to set
	 */
	public void setDeliveryResult(Boolean deliveryResult) {
		this.deliveryResult = deliveryResult;
	}

	@Override
	public String toString() {
		return "PushNotificationResult [deviceId=" + deviceId + ", messageId=" + messageId + ", deliveryResult=" + deliveryResult + "]";
	}
	
	
}
