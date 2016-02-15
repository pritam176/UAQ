package com.uaq.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.uaq.command.SearchCommand;
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
@Repository("newsDAO")
public class NewsDAO implements BaseDAO<SearchCommand, SearchResponseVO> {

	private static transient UAQLogger logger = new UAQLogger(NewsDAO.class);

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

		List<NewsVO> newsObjects = null;
		SearchResponseVO searchResponseVO = null;

		String queryString = prepareQueryString(searchCommand);

		try {

			newsObjects = jdbcTemplate.query(queryString, new Object[] {}, new NewsMapper());

			if (newsObjects != null && newsObjects.size() > 0) {
				searchResponseVO = getVO(newsObjects, newsObjects.get(0).getCount());

				logger.debug("Size of results =" + newsObjects.size());
				logger.debug("Total results =" + newsObjects.get(0).getCount());

			} else {
				logger.debug("Size of results =" + 0);
				logger.debug("Total results =" + 0);
			}

		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		logger.exit("");

		return searchResponseVO;
	}

	private String prepareQueryString(SearchCommand searchCommand) {

		StringBuilder queryString = new StringBuilder("");

		queryString.append("select * from (select ns.*, row_number() over(order by ns.POSTEDDATE desc) rn, count(*) over() cnt from NEWS_SUMMARY ns " + "where ns.site='" + searchCommand.getSite()
				+ "' and ns.lang='" + searchCommand.getLanguage() + "'");
		if (searchCommand != null && searchCommand.getKeyword() != null && !searchCommand.getKeyword().isEmpty()) {
			queryString.append(" and (lower(ns.teasertitle) like '%' || lower('" + searchCommand.getKeyword() + "') || '%' OR " + "lower(ns.teasertext) like '%' || lower('"
					+ searchCommand.getKeyword() + "') || '%' OR " + "lower(ns.body) like '%' || lower('" + searchCommand.getKeyword() + "') || '%')");
		}
		if (searchCommand != null && searchCommand.getStartDate() != null && !searchCommand.getStartDate().isEmpty()) {
			queryString.append(" and ns.POSTEDDATE >= TO_DATE('" + searchCommand.getStartDate() + " 00:00:00', 'DD/MM/YYYY HH24:MI:SS')");
		}
		if (searchCommand != null && searchCommand.getEndDate() != null && !searchCommand.getEndDate().isEmpty()) {
			queryString.append(" and ns.POSTEDDATE <= TO_DATE('" + searchCommand.getEndDate() + " 23:59:59', 'DD/MM/YYYY HH24:MI:SS')");
		}
		if (searchCommand != null && searchCommand.getCategory() != null && !searchCommand.getCategory().isEmpty()) {
			if (searchCommand.getLanguage().equals("en")) {
				queryString.append(" and (lower(ns.CategoryEnglish) like '%' || lower('" + searchCommand.getCategory() + "') || '%')");
			} else {
				queryString.append(" and (ns.CategoryArabic) like '%' || '" + searchCommand.getCategory() + "' || '%'");
			}
		}
		queryString.append(") where rn between " + (searchCommand.getStartRow() + 1) + " and " + (searchCommand.getPageSize() * searchCommand.getCurrentPage()));
				
		return queryString.toString();
	}

	private SearchResponseVO getVO(List<NewsVO> newsList, long totalRows) {

		SearchResponseVO searchResponseVO = new SearchResponseVO();

		searchResponseVO.setSearchResult(newsList);
		searchResponseVO.setTotalNumberOfrows(totalRows);

		return searchResponseVO;
	}
}
