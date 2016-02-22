package com.pkm.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class URLTEST {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try {
			URLConnection connection = new URL("http://94.57.252.237/en/myrequest.html").openConnection();
			connection.setRequestProperty("Accept-Charset", java.nio.charset.StandardCharsets.UTF_8.name());
			/*InputStream in = connection.getInputStream();
			
			System.out.print(in.read());*/
			 InputStreamReader in = new InputStreamReader((InputStream) connection.getContent());
			 BufferedReader buff = new BufferedReader(in);
			 String line;
			    do {
			      line = buff.readLine();
			      System.out.println(line + "\n");
			    } while (line != null);
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}

	}

}
