/**
 * 
 */
package com.uaq.pn.pojo;

import java.util.Date;

/**
 * @author raheem
 *
 */
public class Message {

	private Integer messageId;
	
	private String deviceId;
	
	private String deviceTocken;
	
	private String userId;
	
	private String message = "";
	
	private Date delivery;
	
	private String deviceTypeId;
	
	private Integer notificationTypeId;	
	
	private String notificationTypeIdValue;	
	
	private String other;
	
	private String lang;
	
	private String date;	
	
	private Date created;
	
	private Date modified;

	/**
	 * @return the messageId
	 */
	public Integer getMessageId() {
		return messageId;
	}

	/**
	 * @param messageId the messageId to set
	 */
	public void setMessageId(Integer messageId) {
		this.messageId = messageId;
	}

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
	 * @return the deviceTocken
	 */
	public String getDeviceTocken() {
		return deviceTocken;
	}

	/**
	 * @param deviceTocken the deviceTocken to set
	 */
	public void setDeviceTocken(String deviceTocken) {
		this.deviceTocken = deviceTocken;
	}

	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the delivery
	 */
	public Date getDelivery() {
		return delivery;
	}

	/**
	 * @param delivery the delivery to set
	 */
	public void setDelivery(Date delivery) {
		this.delivery = delivery;
	}

	/**
	 * @return the deviceTypeId
	 */
	public String getDeviceTypeId() {
		return deviceTypeId;
	}

	/**
	 * @param deviceTypeId the deviceTypeId to set
	 */
	public void setDeviceTypeId(String deviceTypeId) {
		this.deviceTypeId = deviceTypeId;
	}

	/**
	 * @return the notificationTypeId
	 */
	public Integer getNotificationTypeId() {
		return notificationTypeId;
	}

	/**
	 * @param notificationTypeId the notificationTypeId to set
	 */
	public void setNotificationTypeId(Integer notificationTypeId) {
		this.notificationTypeId = notificationTypeId;
	}

	/**
	 * @return the notificationTypeIdValue
	 */
	public String getNotificationTypeIdValue() {
		return notificationTypeIdValue;
	}

	/**
	 * @param notificationTypeIdValue the notificationTypeIdValue to set
	 */
	public void setNotificationTypeIdValue(String notificationTypeIdValue) {
		this.notificationTypeIdValue = notificationTypeIdValue;
	}

	/**
	 * @return the other
	 */
	public String getOther() {
		return other;
	}

	/**
	 * @param other the other to set
	 */
	public void setOther(String other) {
		this.other = other;
	}

	/**
	 * @return the lang
	 */
	public String getLang() {
		return lang;
	}

	/**
	 * @param lang the lang to set
	 */
	public void setLang(String lang) {
		this.lang = lang;
	}

	/**
	 * @return the date
	 */
	public String getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(String date) {
		this.date = date;
	}

	/**
	 * @return the created
	 */
	public Date getCreated() {
		return created;
	}

	/**
	 * @param created the created to set
	 */
	public void setCreated(Date created) {
		this.created = created;
	}

	/**
	 * @return the modified
	 */
	public Date getModified() {
		return modified;
	}

	/**
	 * @param modified the modified to set
	 */
	public void setModified(Date modified) {
		this.modified = modified;
	}

	@Override
	public String toString() {
		return "Message [messageId=" + messageId + ", deviceId=" + deviceId + ", deviceTocken=" + deviceTocken + ", userId=" + userId + ", message="
				+ message + ", delivery=" + delivery + ", deviceTypeId=" + deviceTypeId + ", notificationTypeId=" + notificationTypeId
				+ ", notificationTypeIdValue=" + notificationTypeIdValue + ", other=" + other + ", lang=" + lang + ", date=" + date + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((message == null) ? 0 : message.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Message other = (Message) obj;
		/*if (!message.equals(other.message))
			return false;		
		if (userId == null || userId.equals("0") || other.userId == null ||other.userId.equals("0")) {
			return false;
		} else if (!userId.equals(other.userId))
			return false;*/
		if(notificationTypeId == 5 && other.notificationTypeId == 5 &&  
		    userId != null && !userId.equals("0") && other.userId != null && !other.userId.equals("0") && userId.equals(other.userId) &&
		    message.equals(other.message)){
		    return true;
		} else {
			return false;
		}		
	}	
	
}
