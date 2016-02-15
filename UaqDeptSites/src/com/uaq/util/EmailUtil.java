package com.uaq.util;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.URLDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;

import com.uaq.common.PropertiesUtil;
import com.uaq.logger.UAQLogger;

/**
 * Email Utility to send messages using the configured values in
 * Application.properties file.
 * 
 * @author nsharma
 * 
 */
public class EmailUtil {

	private static transient UAQLogger logger = new UAQLogger(EmailUtil.class);

	/**
	 * @param site TODO
	 * @param args
	 */
	public static void sendEmail(String Subject, String messageText, String site) {

		String[] toAdressList = null;
		Properties props = System.getProperties();
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", PropertiesUtil.getProperty("email.host"));
		props.put("mail.smtp.user", PropertiesUtil.getProperty("email.from"));
		props.put("mail.smtp.password", PropertiesUtil.getProperty("email.password"));
		props.put("mail.smtp.port", PropertiesUtil.getProperty("email.port"));
		props.put("mail.smtp.auth", "true");
		String toAddresses = "email.to";
		
		if(null!=site){
			toAddresses = toAddresses + "."+site;
		}
		toAddresses = PropertiesUtil.getProperty(toAddresses);
		if (toAddresses.contains(",")) {
			toAdressList = toAddresses.split(",");
		} else {
			toAdressList = new String[1];
			toAdressList[0] = toAddresses;
		}
		Session session = Session.getDefaultInstance(props, null);
		MimeMessage message = new MimeMessage(session);
		InternetAddress[] toAddress = new InternetAddress[toAdressList.length];

		try {
			// To get the array of addresses
			for (int i = 0; i < toAdressList.length; i++) {
				toAddress[i] = new InternetAddress(toAdressList[i]);
			}

			for (int i = 0; i < toAddress.length; i++) {
				message.addRecipient(Message.RecipientType.TO, toAddress[i]);
			}
			message.setFrom(new InternetAddress(PropertiesUtil.getProperty("email.from")));
			message.setSubject(Subject, "UTF-8");
			message.setText(messageText, "UTF-8");
		} catch (AddressException addressException) {
			logger.error("Error adding address to Message", addressException);
		} catch (MessagingException messagingException) {
			logger.error("Error adding Recepients to Message", messagingException);
		}
		Transport transport = null;

		try {
			transport = session.getTransport("smtp");
			transport.connect(PropertiesUtil.getProperty("email.host"), PropertiesUtil.getProperty("email.from"), PropertiesUtil.getProperty("email.password"));
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
		} catch (NoSuchProviderException noSuchProviderException) {
			logger.error("No Such Provider Exception ", noSuchProviderException);
		} catch (MessagingException messagingException) {
			logger.error("Error adding Recepients to Message", messagingException);
		}

	}

	/**
	 * @param multipart
	 * @throws MessagingException
	 */
	public static BodyPart embedImage(String url, String oid) throws MessagingException {
		BodyPart messageBodyPart;
		// Create part for the image
		messageBodyPart = new MimeBodyPart();
		// Fetch the image and associate to part
		DataSource fds = null;
		try {
			fds = new URLDataSource(new URL(url));
		} catch (MalformedURLException e) {
			logger.error("Error Fetching Image" + e);
		}
		messageBodyPart.setDataHandler(new DataHandler(fds));
		messageBodyPart.addHeader("Content-ID", oid);

		return messageBodyPart;
	}

	/**
	 * @param transport
	 * @param session
	 * @param message
	 */
	public static void sendEmail(Session session, MimeMessage message) {
		Transport transport = null;
		try {
			transport = session.getTransport("smtp");
			transport.connect(PropertiesUtil.getProperty("email.host"), PropertiesUtil.getProperty("email.from"), PropertiesUtil.getProperty("email.password"));
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
			logger.debug("email sent successfully!");
		} catch (NoSuchProviderException noSuchProviderException) {
			logger.error("No Such Provider Exception ", noSuchProviderException);
		} catch (MessagingException messagingException) {
			logger.error("Error adding Recepients to Message", messagingException);
		}
	}

}
