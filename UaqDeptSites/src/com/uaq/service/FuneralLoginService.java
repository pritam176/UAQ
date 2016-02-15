package com.uaq.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.uaq.command.Logincommand;
import com.uaq.dao.BaseDAO;
import com.uaq.exception.UAQException;

/**
 * Service class for login which validates the password provided by the user and
 * the one returned form the database.
 * 
 * @author mraheem
 * 
 */
@Service(value = "funeralLoginService")
public class FuneralLoginService implements BaseService<Logincommand, Logincommand> {

	@Autowired
	@Qualifier("funeralLoginDAO")
	private BaseDAO<Logincommand, Logincommand> dao;

	protected static Logger logger = Logger.getLogger(FuneralLoginService.class);
	
	@Override
	public Logincommand execute(Logincommand loginCommand) throws UAQException {
		Logincommand loginCommandResult = dao.execute(loginCommand);
		if (null != loginCommandResult) {			
			if (loginCommand.getLoginPassword().equals(loginCommandResult.getLoginPassword())) {
				loginCommandResult.setIsLoggedInSucessfull(Boolean.TRUE);
			} else {
				loginCommandResult.setIsLoggedInSucessfull(Boolean.FALSE);
			}
		}
		return loginCommandResult;
	}

	@Override
	public Logincommand executeSites(Logincommand inputObject) throws UAQException {
		// TODO Auto-generated method stub
		return null;
	}	
	
}
