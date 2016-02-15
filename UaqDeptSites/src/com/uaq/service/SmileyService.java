package com.uaq.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.uaq.command.SearchCommand;
import com.uaq.dao.BaseDAO;
import com.uaq.dao.SmileyDAO;
import com.uaq.exception.UAQException;
import com.uaq.logger.UAQLogger;

/**
 * Service class for login which validates the password provided by the user and
 * the one returned form the database.
 * 
 * @author mraheem
 * 
 */
@Service(value = "smileyService")
public class SmileyService implements BaseService<SearchCommand, String> {

	protected static UAQLogger logger = new UAQLogger(SmileyService.class);

	@Autowired
	@Qualifier("smileyDAO")
	BaseDAO<SearchCommand, String> smileyDAO;

	@Override
	public String execute(SearchCommand searchCommand) throws UAQException {

		return smileyDAO.execute(searchCommand);

	}
	
	public Boolean isDuplicate(SearchCommand searchCommand){
		return ((SmileyDAO)smileyDAO).isDuplicate(searchCommand);
	}

	@Override
	public String executeSites(SearchCommand inputObject) throws UAQException {
		// TODO Auto-generated method stub
		return null;
	}

}
