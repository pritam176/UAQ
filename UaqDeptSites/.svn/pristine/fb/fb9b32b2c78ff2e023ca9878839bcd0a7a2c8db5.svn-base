package com.uaq.dao;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.uaq.command.FeedbackCommand;
import com.uaq.exception.DAOException;
import com.uaq.exception.UAQException;
import com.uaq.exception.UAQFaultCode;
import com.uaq.logger.UAQLogger;

/**
 * This class inserts data into the USER_FEEDBACK table.
 * 
 * @author mraheem
 * 
 */
@Repository("feedbackDAO")
public class FeedbackDAO implements BaseDAO<FeedbackCommand, Boolean> {

	private static transient UAQLogger logger = new UAQLogger(FeedbackDAO.class);

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	/**
	 * 
	 */
	@Override
	public Boolean execute(FeedbackCommand feedbackCommand) throws UAQException {

		logger.debug("Feedback for name" + feedbackCommand.getFirstName() + " " + feedbackCommand.getLastName());

		try {

			jdbcTemplate.update("INSERT INTO FEEDBACK (SESSION_ID, FIRST_NAME, LAST_NAME, EMAIL, PHONE, COUNTRY, MESSAGE, SITE) values (?,?,?,?,?,?,?,?)",
					new Object[] {feedbackCommand.getSessionId(), feedbackCommand.getFirstName(), feedbackCommand.getLastName(), feedbackCommand.getEmail(), 
							feedbackCommand.getTelephone(), feedbackCommand.getCountry(), feedbackCommand.getMessage(), feedbackCommand.getSite() });
		} catch (DataIntegrityViolationException dataIntegrityViolationException) {
			throw new DAOException(UAQFaultCode.SESSION_PK, dataIntegrityViolationException);
		}

		logger.debug("Feedback successfull for username" + feedbackCommand.getFirstName() + " " + feedbackCommand.getLastName());

		return true;
	}
}
