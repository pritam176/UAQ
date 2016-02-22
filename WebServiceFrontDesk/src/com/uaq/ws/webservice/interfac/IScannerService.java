package com.uaq.ws.webservice.interfac;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.uaq.ws.pojo.Response;

@Path("/scanner/")
public interface IScannerService {
		
	@GET	
	@Produces("application/json;charset=utf-8")
	@Path("/scan")
	Response scan();	
	
}
