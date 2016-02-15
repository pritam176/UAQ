package com.uaq.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.uaq.command.SearchCommand;
import com.uaq.logger.UAQLogger;
import com.uaq.service.SmileyService;

@Repository(value = "smileyDAO")
public class SmileyDAO implements BaseDAO<SearchCommand, String> {

	protected static UAQLogger logger = new UAQLogger(SmileyService.class);

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public String execute(SearchCommand searchCommand) {

		logger.enter("execute");

		String query = "";
		int intResult = 0;

		try {

			query = "INSERT INTO Smiley (SESSION_ID, SITE, QUESTION, ANSWER) values(?, ?, ?, ?)";

			Object[] params = new Object[] { searchCommand.getTicketId(), searchCommand.getSite(), searchCommand.getKeyword(), searchCommand.getOther() };

			int[] types = new int[] { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR };

			intResult = jdbcTemplate.update(query, params, types);

		} catch (Exception e) {
			logger.error(e.getMessage());
			if (e.getMessage().contains("unique constraint")) {
				return "unique constraint voilation";
			}
		}

		logger.exit("execute");

		return intResult > 0 ? "success" : "fail";
	}
	
	public Boolean isDuplicate(SearchCommand searchCommand){
		
		logger.enter("isDuplicate : session for happiness indicater " + searchCommand.getTicketId());
		
		Boolean isDuplicate = Boolean.FALSE;
		
		Integer rowCount = jdbcTemplate.query("select count(1) from Smiley where SESSION_ID=? and SITE=?",
			new Object[] { searchCommand.getTicketId(), searchCommand.getSite() }, new RowMapper<Integer>() {
				@Override
				public Integer mapRow(ResultSet resultset, int arg1) throws SQLException {
					return resultset.getInt(1);
				}
			}).get(0);
		
		if (rowCount.intValue() == 1) {
			isDuplicate = Boolean.TRUE;
		}
		
		logger.exit("isDuplicate : " + isDuplicate);

		return isDuplicate;
	}

}
