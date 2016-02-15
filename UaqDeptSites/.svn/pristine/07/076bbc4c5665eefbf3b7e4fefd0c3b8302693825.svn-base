package com.uaq.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.uaq.command.FeedbackCommand;
import com.uaq.dao.FeedbackDAO;
import com.uaq.exception.UAQException;

/**
 * 
 * @author mraheem
 * 
 */
@Service(value = "feedbackService")
public class FeedbackService implements BaseService<FeedbackCommand, Boolean> {

	@Autowired
	@Qualifier("feedbackDAO")
	FeedbackDAO dao;

	@Override
	public Boolean execute(FeedbackCommand feedbackCommand) throws UAQException {

		Boolean isFeedbackSuccessfull;

		isFeedbackSuccessfull = dao.execute(feedbackCommand);

		return isFeedbackSuccessfull;
	}

	@Override
	public Boolean executeSites(FeedbackCommand inputObject) throws UAQException {
		// TODO Auto-generated method stub
		return null;
	}
}
