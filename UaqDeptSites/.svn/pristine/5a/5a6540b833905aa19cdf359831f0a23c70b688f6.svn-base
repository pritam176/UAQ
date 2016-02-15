package com.uaq.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.uaq.command.Logincommand;
import com.uaq.exception.UAQException;
import com.uaq.logger.UAQLogger;


/**
 * This class retrieves user record from the table for the given user name.
 * 
 * @author mraheem
 * 
 */
@Repository("funeralLoginDAO")
public class FuneralLoginDAO implements BaseDAO<Logincommand, Logincommand> {

	private static transient UAQLogger logger = new UAQLogger(FuneralLoginDAO.class);

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	/**
	 * 
	 */
	@Override
	public Logincommand execute(Logincommand loginCommand) throws UAQException {

		logger.enter("FuneralLoginDAO");

		Logincommand loginCommandResult = null;

		List<Logincommand> loginObjects = jdbcTemplate.query("select * from LOGIN where user_name=? and status=1", new Object[] {
				loginCommand.getLoginUsername()}, new FuneralLoginMapper());
		if (!loginObjects.isEmpty()) {
			Logincommand userObject = loginObjects.get(0);
			loginCommandResult = new Logincommand();
			loginCommandResult.setLoginUsername(userObject.getLoginUsername());
			loginCommandResult.setLoginPassword(userObject.getLoginPassword());			
		}
		
		logger.exit("FuneralLoginDAO");

		return loginCommandResult;
	}
}
