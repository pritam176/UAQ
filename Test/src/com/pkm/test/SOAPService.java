package com.pkm.test;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService(serviceName = "SOAPService")
public class SOAPService {
	
	@WebMethod(operationName = "no")
    public String hello(@WebParam(name = "no") String txt) {
        return "Hello " + txt + " !";
    }

}
