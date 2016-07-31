package com.uaq.ws.webservice;

import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;

import com.uaq.ws.pojo.EIDAVO;
import com.uaq.ws.service.EIDACardService;
import com.uaq.ws.util.UAQLogger;
import com.uaq.ws.webservice.interfac.EIDAService;

public class EIDARequestImpl implements EIDAService {

	private static transient UAQLogger logger = new UAQLogger(EIDARequestImpl.class);

	@Autowired
	private EIDACardService eidaService;

	@Override
	public Response  readCard() {
		logger.enter("readCard");
		EIDAVO eidaVO = eidaService.readEIDA();
		/*response.addHeader("Access-Control-Allow-Origin", "*");
		response.addHeader("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT");*/
		 return Response.ok(eidaVO).header("Access-Control-Allow-Origin", "*").build();
	}

}
