package com.uaq.ws.webservice.interfac;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import org.springframework.http.HttpHeaders;

import com.uaq.ws.pojo.EIDAVO;


@Path("/eidaservice/")
public interface EIDAService {
	
	@GET
	@Produces("application/json;charset=utf-8")
	@Path("/readcard")
	Response  readCard();

}
