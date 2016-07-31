package com.uaq.ws.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class TestFeedback {

	public static void main(String[] args) {

		try {

			URL url = new URL("http://94.57.252.237:8080/uaqws/asset/sendFeedback/");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();

			String userpass = "uaqwebserviceuser" + ":" + "uaqwebserviceuser";
			String basicAuth = "Basic " + javax.xml.bind.DatatypeConverter.printBase64Binary(userpass.getBytes());
			conn.setRequestProperty("Authorization", basicAuth);
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json");

			String input = "{\"firstName\":\"Naveen\",\"lastName\":\"Sharma\",\"emailAddress\":\"nsharma@tacme.com\",\"telephoneNumber\":\"0112222222\",\"country\":\"India\",\"details\":\"Test Feedback\"}";

			OutputStream os = conn.getOutputStream();
			os.write(input.getBytes());
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