/**
 * 
 */
package com.uaq.pn.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.uaq.pn.util.ConfUtils;
import com.uaq.pn.pojo.Notification;
import com.uaq.pn.pojo.PushNotificationResult;

/**
 * This class is used to send notification to Android devices through GCM
 * @author raheem
 * 
 */
public class AndroidPushNotificationService {
	
	/**
	 * This method is used to send single notification
	 */

	public PushNotificationResult pushNotification(Notification notification) {
		
		PushNotificationResult result = new PushNotificationResult();
		
		List<Notification> notList = new ArrayList<Notification>();
		notList.add(notification);
		result = pushNotifications(notList).get(0);
		
		return result;
	}
	
	/**
	 * This method is used to send push notifications in bulk through GCM
	 * partial implementation. it uses the single notification.
	 */

	public List<PushNotificationResult> pushNotifications(List<Notification> notifications) {
		
		List<PushNotificationResult> results = new ArrayList<PushNotificationResult>();
		
		try {
			
			for (Notification notification : notifications) {
				PushNotificationResult result = doSendNotification(notification);
				results.add(result);
			}
			
		} catch (Throwable t) {
			throw new RuntimeException(t);
		}
		
		return results;
	}

	/**
	 * This method is used to push the notification to the given device through GCM
	 * @param notification
	 * @return
	 * @throws HttpException
	 * @throws IOException
	 * @throws JSONException
	 */
	
	public PushNotificationResult doSendNotification(Notification notification) throws HttpException, IOException, JSONException {
		
		System.out.println("doSendNotification");
		
		System.out.println("ANDROID sending notification to : ");			
		System.out.println("device tocken = " + notification.getTocken());
		
		PushNotificationResult result = new PushNotificationResult();
		HttpClient client = new HttpClient();
		PostMethod method = new PostMethod("https://gcm-http.googleapis.com/gcm/send");		
		
		JSONObject json = new JSONObject();
		JSONObject dataJson = new JSONObject();
		JSONObject payloadJson = new JSONObject();		
		JSONObject androidJson = new JSONObject();
		
		androidJson.put("title", "UAQ");		
		androidJson.put("icon", "icon.png");
		androidJson.put("badge", notification.getBadge());
		androidJson.put("vibrate", true);		
		androidJson.put("alert", notification.getText());

		androidJson.put("custom_message_json", notification.getPayload());
		
		payloadJson.put("android", androidJson);
		dataJson.put("payload", payloadJson);		
		json.put("data", dataJson);
		json.put("to", notification.getTocken());
		
		// removing unwanted double quotes and slashes
		String jsonStr = json.toString();
		if(!jsonStr.isEmpty()){			
			jsonStr = jsonStr.replaceAll("\\\\", "");
			jsonStr = jsonStr.replaceAll("\"\\{", "{");            
			jsonStr = jsonStr.replaceAll("\\}\"", "}");
		}
		
		System.out.println("payload = " + jsonStr);
				
		StringRequestEntity requestEntity = new StringRequestEntity(jsonStr, "application/json", "UTF-8");		
		
		Header headerAuthorization = new Header("Authorization", "key=" + ConfUtils.getValue("GoogleAppID"));
		//Header headerSenderId = new Header("Sender", "id=" + ConfUtils.getValue("SenderId"));
		Header contentType = new Header("Content-Type", "application/json");
		
		method.addRequestHeader(headerAuthorization);
		//method.addRequestHeader(headerSenderId);
		method.addRequestHeader(contentType);
		method.setRequestEntity(requestEntity);
				
		client.executeMethod(method);
		
		byte[] responseBody = method.getResponseBody();
		String response = new String(responseBody);
		
		if(response != null && !response.isEmpty()){
			result.setDeliveryResult(true);
		}

		System.out.println("response : " + response);
		System.out.println("result  : " + result.getDeliveryResult());
		
		//System.out.println(response);
		
		return result;
	}
	

}
