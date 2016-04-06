package com.uaq.soap.warpper;

import java.util.ArrayList;
import java.util.List;

import javax.xml.ws.handler.Handler;
import javax.xml.ws.handler.HandlerResolver;
import javax.xml.ws.handler.PortInfo;


public class HeaderHandlerResolver implements HandlerResolver  {
	private String username;
	private String password;

	public HeaderHandlerResolver(String username, String password) {
		this.username = username;
		this.password = password;
	}

	@Override
	public List<Handler> getHandlerChain(PortInfo arg0) {
		 List<Handler> handlerChain = new ArrayList<Handler>();
		 
	      HeaderHandler hh = new HeaderHandler(username,password);
	 
	      handlerChain.add(hh);
	 
	      return handlerChain;
	}

}
