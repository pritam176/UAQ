package com.uaq.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.uaq.command.SearchCommand;
import com.uaq.common.PropertiesUtil;
import com.uaq.exception.UAQException;
import com.uaq.logger.UAQLogger;
import com.uaq.vo.PublicationsVO;

/**
 * This class inserts data into the USER_FEEDBACK table.
 * 
 * @author anair
 * 
 */
@Repository("publicationsDAO")
public class PublicationsDAO implements BaseDAO<SearchCommand, List<PublicationsVO>> {

	private static transient UAQLogger logger = new UAQLogger(PublicationsDAO.class);

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	/**
	 * 
	 */
	@Override
	public List<PublicationsVO> execute(SearchCommand searchCommand) throws UAQException {

		logger.enter("");

		List<PublicationsVO> publicationsObjects = null;

		String queryString = prepareQueryString(searchCommand);

		try {

			publicationsObjects = jdbcTemplate.query(queryString, new Object[] {}, new PublicationsMapper());

		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		logger.exit("");

		return publicationsObjects;
	}

	private String prepareQueryString(SearchCommand searchCommand) {

		StringBuilder queryString = new StringBuilder("");

		queryString.append("select * from PUBLICATION_SUMMARY where site='" + PropertiesUtil.getProperty(searchCommand.getSite() + "_csSiteName") + "' and lang='" + searchCommand.getLanguage() + "'");
		if (searchCommand != null && searchCommand.getKeyword() != null && !searchCommand.getKeyword().isEmpty()) {
			queryString.append(" and (lower(teasertitle) like '%' || lower('" + searchCommand.getKeyword() + "') || '%' OR " + "lower(teasertext) like '%' || lower('" + searchCommand.getKeyword()
					+ "') || '%' OR " + "lower(body) like '%' || lower('" + searchCommand.getKeyword() + "') || '%')");
		}
		if (searchCommand != null && searchCommand.getStartDate() != null && !searchCommand.getStartDate().isEmpty()) {
			queryString.append(" and createddate >= TO_DATE('" + searchCommand.getStartDate() + " 00:00:00', 'DD/MM/YYYY HH24:MI:SS')");
		}
		if (searchCommand != null && searchCommand.getEndDate() != null && !searchCommand.getEndDate().isEmpty()) {
			queryString.append(" and createddate <= TO_DATE('" + searchCommand.getEndDate() + " 23:59:59', 'DD/MM/YYYY HH24:MI:SS')");
		}

		return queryString.toString();
	}
}
