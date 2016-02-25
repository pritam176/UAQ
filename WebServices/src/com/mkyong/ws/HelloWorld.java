package com.mkyong.ws;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import com.pkm.pojo.SimplePojo;

//Service Endpoint Interface
@WebService
@SOAPBinding(style = Style.RPC)
public interface HelloWorld{
	
	@WebMethod SimplePojo getHelloWorldAsString(SimplePojo pojo) throws Exception;
	
}