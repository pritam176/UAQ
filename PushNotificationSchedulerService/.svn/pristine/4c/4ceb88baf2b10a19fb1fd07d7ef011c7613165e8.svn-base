package com.uaq.pn.service;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.httpclient.HttpException;

import com.google.gson.JsonObject;
import com.notnoop.apns.APNS;
import com.notnoop.apns.ApnsService;
import com.uaq.pn.dao.PushNotificationSchedulerDAO;
import com.uaq.pn.exception.UAQException;
import com.uaq.pn.pojo.Message;
import com.uaq.pn.pojo.Notification;
import com.uaq.pn.pojo.PushNotificationResult;
import com.uaq.pn.service.AndroidPushNotificationService;
import com.uaq.pn.util.ConfUtils;


/**
 * This class is used to send pending notifications, to be called by scheduler
 * 
 * @author raheem
 * 
 */
public class PushNotificationDeliveryService {

	static final int THRESH_HOLD_TIME_OUT_MILLI_SECONDS_24_HR = 3600000 * 24; // 1 Hour = 3,600,000 Milliseconds. 24 Hours or one day = 86,400,000 Milliseconds 
	private static final String MESSAGE_STATUS_UNREAD = "2";

	public static void main(String[] args) {

		PushNotificationDeliveryService pushNotificationDeliveryService = new PushNotificationDeliveryService();
		pushNotificationDeliveryService.sendPushNotifications();
	}

	/**
	 * This method is used to send pending notifications to android and ios
	 * devices
	 */
	public void sendPushNotifications() {

		long currentTime = 0, startTime = 0;
		
		List<PushNotificationResult> results = new ArrayList<PushNotificationResult>();		
		PushNotificationSchedulerDAO dao = new PushNotificationSchedulerDAO();
		
		String certPassword = ConfUtils.getValue("certPassword");
		InputStream certResource = this.getClass().getClassLoader().getResourceAsStream(ConfUtils.getValue("certFileName"));
		// boolean useProductionServer = !isTest;
		String isProduction = ConfUtils.getValue("apns.is.production");
		boolean useProductionServer = (isProduction != null && !isProduction.isEmpty() && isProduction.equals("true")) ? true : false;
		
		ApnsService svc = null;
		if (useProductionServer) {
			svc = APNS.newService().withProductionDestination().withCert(certResource, certPassword).build();
		} else {
			svc = APNS.newService().withSandboxDestination().withCert(certResource, certPassword).build();
		}
		
		startTime = Calendar.getInstance().getTimeInMillis();
		System.out.println("start time = " + startTime);
		
		svc.start();

		do {
			
			List<Notification> notificationsAndroid = new ArrayList<Notification>();

			try {

				List<Message> messages = dao.getDeliveryNotifications(); // pending notifications list

				for (Message message : messages) {
					System.out.println("Sending message");
					Notification notification = fillNotification(message);
					notification.setBadge(dao.getNotifications(message.getDeviceId()) + 1);

					if (message.getDeviceTypeId().equals("1")) { // android
						notificationsAndroid.add(notification);
						System.out.println("Android notification sent to deviceuid = " + message.getDeviceId() + " : devicetocken = " + message.getDeviceTocken());						
					} else if (message.getDeviceTypeId().equals("2")) { // ios
						String payload = APNS.newPayload().customField("custom_message", notification.getPayload())
								.alertBody(notification.getText())
								.badge(notification.getBadge())
								.build();
						svc.push(message.getDeviceTocken(), payload);
						System.out.println("Apple apns ios notification sent to deviceuid = " + message.getDeviceId() + " : devicetocken = " + message.getDeviceTocken());						
					}
					// mark as delivered
					boolean result = dao.updateMessage(message.getDeviceId(), message.getMessageId().toString(), null, null, MESSAGE_STATUS_UNREAD);
					System.out.println("updated delivery status result = " + result);
				}
												
				if (notificationsAndroid.size() > 0) {
					results = sendNotificationsAndroid(notificationsAndroid);
					System.out.println("android results : " + results);
				}

				Thread.sleep(60000); // pause of 1 min

			} catch (UAQException e) {
				System.out.println(e.getMessage());
			} catch (InterruptedException e) {
				System.out.println(e.getMessage());
			} catch(Exception e){
				System.out.println(e.getMessage());
			}
			
			currentTime = Calendar.getInstance().getTimeInMillis();
			System.out.println("current time = " + currentTime);
			
		} while( (currentTime - startTime) <= THRESH_HOLD_TIME_OUT_MILLI_SECONDS_24_HR );
		svc.stop();

	}

	/**
	 * This method is used to create notification object with custom json to be
	 * sent to devices
	 * 
	 * @param message
	 * @param device
	 * @return
	 */
	private Notification fillNotification(Message message) {

		// create json payload
		JsonObject jsonObj = new JsonObject();
		jsonObj.addProperty("mid", message.getMessageId());
		jsonObj.addProperty("nTypeId", message.getNotificationTypeId());
		jsonObj.addProperty("nTypeIdVal", message.getNotificationTypeIdValue());
		jsonObj.addProperty("mText", message.getMessage());		
		jsonObj.addProperty("date", message.getDate());

		Notification notification = new Notification();
		notification.setPayload(jsonObj.toString());
		notification.setText(message.getMessage());
		notification.setTocken(message.getDeviceTocken());		

		return notification;
	}

	/**
	 * This method is used to send multiple notifications to multiple devices
	 * 
	 * @param notifications
	 * @return
	 * @throws UAQException
	 * @throws SQLException
	 * @throws HttpException
	 * @throws IOException
	 */
	private List<PushNotificationResult> sendNotificationsAndroid(List<Notification> notifications) {

		System.out.println("sendNotificationsAndroid notifications");

		AndroidPushNotificationService pushNotificationService = new AndroidPushNotificationService();

		List<PushNotificationResult> results = pushNotificationService.pushNotifications(notifications);

		System.out.println("sendNotificationsAndroid");

		return results;
	}

}
