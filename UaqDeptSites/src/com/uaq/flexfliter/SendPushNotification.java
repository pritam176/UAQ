package com.uaq.flexfliter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class SendPushNotification {

	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String subTypeId = "1";
		String assetId = "1437027862514";
		String pushNotificationMessage = "This is Test Notification";
		String lang = "en";
		String date = "25/10/2015";

		postPushNotification(subTypeId, assetId, pushNotificationMessage, lang, date);

	}

	/**
	 * @param subTypeId
	 * @param assetId
	 * @param pushNotificationMessage
	 * @param lang
	 * @param date
	 */
	public static void postPushNotification(String subTypeId, String assetId, String pushNotificationMessage, String lang, String date) {
		try {

			URL url = new URL(FlexConstants.PUSHNOTIFICATION_ENDPOINT);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();

			String userpass = "uaqwebserviceuser" + ":" + "uaqwebserviceuser";
			String basicAuth = "Basic " + javax.xml.bind.DatatypeConverter.printBase64Binary(userpass.getBytes());
			conn.setRequestProperty("Authorization", basicAuth);
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json");
			StringBuffer message = new StringBuffer();
			message.append("{\"nTypeId\":\"");
			message.append(subTypeId);
			message.append("\",\"nTypeIdVal\":\"");
			message.append(assetId);
			message.append("\",\"mText\":\"");
			message.append(pushNotificationMessage);
			message.append("\",\"date\":\"");
			message.append(date);
			message.append("\",\"userid\":\"");
			message.append("0");
			message.append("\",\"lang\":\"");
			message.append(lang);
			message.append("\"}");
			System.out.println(message.toString());

			OutputStream os = conn.getOutputStream();
			os.write(message.toString().getBytes());
			os.flush();

			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

			String output;
			System.out.println("Output from Server .... \n");
			while ((output = br.readLine()) != null) {
				System.out.println(output);
			}

			conn.disconnect();

		} catch (MalformedURLException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		}
	}

}
