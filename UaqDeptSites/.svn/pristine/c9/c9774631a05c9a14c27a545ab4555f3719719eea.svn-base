package com.uaq.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.uaq.command.SearchCommand;
import com.uaq.exception.UAQException;
import com.uaq.logger.UAQLogger;
import com.uaq.vo.JobSearchResponseVO;
import com.uaq.vo.JobVO;
import com.uaq.vo.SearchResponseVO;

@Repository("JobsDAO")
/**
 * 
 * @author ajain
 * Jobs DAO class for searching Jobs in the DB.
 */
public class JobsDAO implements BaseDAO<SearchCommand, SearchResponseVO> {
	private static transient UAQLogger logger = new UAQLogger(JobsDAO.class);

	private JdbcTemplate jdbcTemplate;

	/**
	 * 
	 * @param dataSource
	 */
	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	/**
	 * !
	 * 
	 * @param searchCommand
	 * @return list of job Objects.
	 */
	@Override
	public SearchResponseVO execute(SearchCommand searchCommand) throws UAQException {

		logger.enter("Entered execute of jobsDAO");
		List<JobVO> jobObjects = null;
		SearchResponseVO searchResponseVO = null;

		String queryString = prepareQueryString(searchCommand);

		try {
			jobObjects = jdbcTemplate.query(queryString, new Object[] {}, new JobMapper());

			if (jobObjects != null && jobObjects.size() > 0) {
				searchResponseVO = getVO(jobObjects, jobObjects.get(0).getCount());

				logger.debug("Size of results =" + jobObjects.size());
				logger.debug("Total results =" + jobObjects.get(0).getCount());

			} else {
				logger.debug("Size of results =" + 0);
				logger.debug("Total results =" + 0);
			}

		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		logger.debug("Exiting JobsDAO");
		// TODO Auto-generated method stub
		return searchResponseVO;
	}

	/**
	 * 
	 * @param searchCommand
	 * @return query String to search the appropriate jobs between Posted Date
	 *         and End Date.
	 */
	private String prepareQueryString(SearchCommand searchCommand) {

		StringBuilder queryString = new StringBuilder("");

		queryString.append("select * from (select ns.*, row_number() over(order by posted_date desc) rn, count(*) over() cnt from job_summary ns " + "where ns.site='" + searchCommand.getSite() + "' and lang='"
				+ searchCommand.getLanguage() + "'");

		queryString.append("  and sysdate between posted_date-1 and end_date+1");
		queryString.append(") where rn between " + (searchCommand.getStartRow() + 1) + " and " + (searchCommand.getPageSize() * searchCommand.getCurrentPage()));
		return queryString.toString();
	}

	private SearchResponseVO getVO(List<JobVO> jobsList, long totalRows) {

		SearchResponseVO searchResponseVO = new JobSearchResponseVO();

		((JobSearchResponseVO) searchResponseVO).setSearchResults(jobsList);
		searchResponseVO.setTotalNumberOfrows(totalRows);

		return searchResponseVO;
	}

}
