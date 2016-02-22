package com.uaq.ws.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;

import com.uaq.ws.exception.UAQException;

public class PostUtil {

	private static transient UAQLogger logger = new UAQLogger(
			PostUtil.class);

	public static void postData(Map<String, String> data)
			throws UAQException {
		try {
			// Send data
			System.setProperty("java.protocol.handler.pkgs",
					"com.sun.net.ssl.internal.www.protocol");
			java.security.Security
					.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
			String url = PropertiesUtil.getProperty("crm.endpoint");
			java.net.URL siteUrl = new URL(null, url,
					new sun.net.www.protocol.https.Handler());
			trustAllHttpsCertificates();
			HttpsURLConnection conn = (HttpsURLConnection) siteUrl
					.openConnection();
			conn.setHostnameVerifier(new HostnameVerifier() {

				@Override
				public boolean verify(String hostname, SSLSession session) {
					System.out.println("Warning: URL Host: " + hostname
							+ " vs. " + session.getPeerHost());
					return true;
				}
			});

			conn.setRequestMethod("POST");
			conn.setDoOutput(true);
			OutputStreamWriter writer = new OutputStreamWriter(
					conn.getOutputStream());
			String content = getContenToBePosted(data);
			logger.debug("Content posted ::" + content);
			writer.write(content);
			writer.flush();

			// Get the response
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					conn.getInputStream()));
			String line = "";
			while ((line = reader.readLine()) != null) {
				logger.debug("Response from the CRM :" + line);
			}
			writer.close();
			reader.close();

		} catch (Exception e) {
			logger.error("Error while posting the data" + e.getMessage());
			throw new UAQException(
					"Error inserting data for Add Listing to CRM");
		}

	}

	/**
	 * @param data
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	private static String getContenToBePosted(Map<String, String> data)
			throws UnsupportedEncodingException {
		StringBuffer content = new StringBuffer();
		for (String key : data.keySet()) {
			content.append("&");
			content.append(key);
			content.append("=");
			content.append(URLEncoder.encode(data.get(key), "UTF-8"));
		}
		return content.toString();
	}

	// Just add these two functions in your program
	public static class miTM implements javax.net.ssl.TrustManager,
			javax.net.ssl.X509TrustManager {
		public java.security.cert.X509Certificate[] getAcceptedIssuers() {
			return null;
		}

		public boolean isServerTrusted(
				java.security.cert.X509Certificate[] certs) {
			return true;
		}

		public boolean isClientTrusted(
				java.security.cert.X509Certificate[] certs) {
			return true;
		}

		public void checkServerTrusted(
				java.security.cert.X509Certificate[] certs, String authType)
				throws java.security.cert.CertificateException {
			return;
		}

		public void checkClientTrusted(
				java.security.cert.X509Certificate[] certs, String authType)
				throws java.security.cert.CertificateException {
			return;
		}
	}

	private static void trustAllHttpsCertificates() throws Exception {

		// Create a trust manager that does not validate certificate chains:

		javax.net.ssl.TrustManager[] trustAllCerts =

		new javax.net.ssl.TrustManager[1];

		javax.net.ssl.TrustManager tm = new miTM();

		trustAllCerts[0] = tm;

		javax.net.ssl.SSLContext sc =

		javax.net.ssl.SSLContext.getInstance("SSL");

		sc.init(null, trustAllCerts, null);

		javax.net.ssl.HttpsURLConnection.setDefaultSSLSocketFactory(

		sc.getSocketFactory());

	}

}
