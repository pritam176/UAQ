package com.uaq.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.uaq.command.SearchCommand;
import com.uaq.common.PropertiesUtil;
import com.uaq.exception.UAQException;
import com.uaq.logger.UAQLogger;
import com.uaq.vo.NewsVO;
import com.uaq.vo.SearchResponseVO;

/**
 * This class inserts data into the USER_FEEDBACK table.
 * 
 * @author mraheem
 * 
 */
@Repository("searchDAO")
public class SearchDAO implements BaseDAO<SearchCommand, SearchResponseVO> {

	private static transient UAQLogger logger = new UAQLogger(SearchDAO.class);

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	/**
	 * 
	 */
	@Override
	public SearchResponseVO execute(SearchCommand searchCommand) throws UAQException {

		logger.enter("");

		List<NewsVO> searchObjects = null;
		SearchResponseVO searchResponseVO = null;

		String queryString = prepareQueryString(searchCommand);

		try {

			searchObjects = jdbcTemplate.query(queryString, new Object[] {}, new SearchMapper());

			if (searchObjects != null && searchObjects.size() > 0) {
				searchResponseVO = getVO(searchObjects, searchObjects.get(0).getCount());

				logger.debug("Size of results =" + searchObjects.size());
				logger.debug("Total results =" + searchObjects.get(0).getCount());

			} else {
				logger.debug("Size of results =" + 0);
				logger.debug("Total results =" + 0);
			}

		} catch (DataAccessException e) {
			logger.error(e.getMessage());
		}

		logger.exit("");

		return searchResponseVO;
	}

	private String prepareQueryString(SearchCommand searchCommand) {
		String siteName = PropertiesUtil.getProperty(searchCommand.getSite() + "_" + "csSiteName");
		StringBuilder queryString = new StringBuilder("");

		queryString.append("select * from (select gs.*, row_number() over(order by gs.id) rn, count(*) over() cnt from GLOBAL_SEARCH gs "
				+ "where gs.site='" + siteName + "' and gs.lang='" + searchCommand.getLanguage() + "'");
		if (searchCommand != null && searchCommand.getKeyword() != null && !searchCommand.getKeyword().isEmpty()) {
			queryString.append(" and (lower(gs.title) like '%' || lower('" + searchCommand.getKeyword() + "') || '%' OR "
					+ "lower(gs.teasertext) like '%' || lower('" + searchCommand.getKeyword() + "') || '%' OR "
					+ "lower(gs.name) like '%' || lower('" + searchCommand.getKeyword() + "') || '%' OR " + "lower(gs.body) like '%' || lower('"
					+ searchCommand.getKeyword() + "') || '%')");
			queryString.append(") where rn between " + (searchCommand.getStartRow() + 1) + " and "
					+ (searchCommand.getPageSize() * searchCommand.getCurrentPage()));
		}

		return queryString.toString();
	}

	private SearchResponseVO getVO(List<NewsVO> newsList, long totalRows) {

		SearchResponseVO searchResponseVO = new SearchResponseVO();

		searchResponseVO.setSearchResult(newsList);
		searchResponseVO.setTotalNumberOfrows(totalRows);

		return searchResponseVO;
	}
}
