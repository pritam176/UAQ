package com.uaq.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.uaq.command.SearchCommand;
import com.uaq.exception.UAQException;
import com.uaq.logger.UAQLogger;
import com.uaq.vo.EventsSearchResponseVO;
import com.uaq.vo.EventsVO;
import com.uaq.vo.SearchResponseVO;

/**
 * This class inserts data into the USER_FEEDBACK table.
 * 
 * @author mraheem
 * 
 */
@Repository("eventsDAO")
public class EventsDAO implements BaseDAO<SearchCommand, SearchResponseVO> {

	private static transient UAQLogger logger = new UAQLogger(EventsDAO.class);

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

		List<EventsVO> eventsObjects = null;
		SearchResponseVO searchResponseVO = null;

		String queryString = prepareQueryString(searchCommand);

		try {

			eventsObjects = jdbcTemplate.query(queryString, new Object[] {}, new EventsMapper());

			if (eventsObjects != null && eventsObjects.size() > 0) {
				searchResponseVO = getVO(eventsObjects, eventsObjects.get(0).getCount());

				logger.debug("Size of results =" + eventsObjects.size());
				logger.debug("Total results =" + eventsObjects.get(0).getCount());

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

		StringBuilder queryString = new StringBuilder("");

		queryString.append("select * from (select ns.*, row_number() over(order by openingdate asc) rn, count(*) over() cnt from EVENTS_VIEW ns " + "where ns.site='" + searchCommand.getSite()
				+ "' and ns.lang='" + searchCommand.getLanguage() + "'");
		if (searchCommand != null && searchCommand.getKeyword() != null && !searchCommand.getKeyword().isEmpty()) {
			queryString.append(" and (lower(ns.EVENTNAME) like '%' || lower('" + searchCommand.getKeyword() + "') || '%' OR " + "lower(ns.teasertext) like '%' || lower('" + searchCommand.getKeyword()
					+ "') || '%' OR " + "lower(ns.body) like '%' || lower('" + searchCommand.getKeyword() + "') || '%')");
		}
		if (searchCommand != null && searchCommand.getStartDate() != null && !searchCommand.getStartDate().isEmpty()) {
			queryString.append(" and ns.openingdate >= TO_DATE('" + searchCommand.getStartDate() + " 00:00:00', 'DD/MM/YYYY HH24:MI:SS')");
		}
		if (searchCommand != null && searchCommand.getEndDate() != null && !searchCommand.getEndDate().isEmpty()) {
			queryString.append(" and ns.endingdate <= TO_DATE('" + searchCommand.getEndDate() + " 23:59:59', 'DD/MM/YYYY HH24:MI:SS')");
		}
		if (searchCommand != null && searchCommand.getCategory() != null && !searchCommand.getCategory().isEmpty()) {
			if (searchCommand.getLanguage().equals("en")) {
				queryString.append(" and (lower(ns.CATEGORYINENGLISH) like '%' || lower('" + searchCommand.getCategory() + "') || '%')");
			} else {
				queryString.append(" and (ns.CATEGORYINARABIC) like '%' || '" + searchCommand.getCategory() + "' || '%'");
			}

		}
		queryString.append(") where rn between " + (searchCommand.getStartRow() + 1) + " and " + (searchCommand.getPageSize() * searchCommand.getCurrentPage()));
		
		return queryString.toString();
	}

	private SearchResponseVO getVO(List<EventsVO> eventsList, long totalRows) {

		SearchResponseVO searchResponseVO = new EventsSearchResponseVO();

		((EventsSearchResponseVO) searchResponseVO).setSearchResults(eventsList);
		searchResponseVO.setTotalNumberOfrows(totalRows);

		return searchResponseVO;
	}
}
