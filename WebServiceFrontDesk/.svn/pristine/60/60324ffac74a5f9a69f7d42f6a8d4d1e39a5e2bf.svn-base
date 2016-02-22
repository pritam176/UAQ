package com.uaq.ws.webservice.interfac;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.uaq.ws.pojo.PosInfo;
import com.uaq.ws.pos.PaymentRequestECR;
import com.uaq.ws.pos.PaymentResponseECR;

@Path("/pos/")
public interface IPOSService {
	
	/**
	 * @param paymentRequestECR
	 * @return
	 */
	@POST
	@Consumes("application/json;charset=utf-8")
	@Produces("application/json;charset=utf-8")
	@Path("/doTransaction/")
	PaymentResponseECR doTransaction(PaymentRequestECR paymentRequestECR);
	
	
	/**
	 * @return
	 */
	@GET
	@Produces("application/json;charset=utf-8")
	@Path("/getPosInfo/")
	PosInfo getPosInfo();
}
