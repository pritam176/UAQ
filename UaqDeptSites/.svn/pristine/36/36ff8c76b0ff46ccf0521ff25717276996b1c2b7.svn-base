package com.uaq.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.uaq.common.PropertiesUtil;
import com.uaq.exception.UAQException;
import com.uaq.logger.UAQLogger;
import com.uaq.vo.NewsVO;

/**
 * This class inserts data into the USER_FEEDBACK table.
 * 
 * @author nsharma
 * 
 */
@Repository("latestNewsDAO")
public class LatestNewsDAO implements BaseDAO<NewsVO, NewsVO> {

	private static transient UAQLogger logger = new UAQLogger(LatestNewsDAO.class);

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	/**
	 * 
	 */
	@Override
	public NewsVO execute(NewsVO newsVO) throws UAQException {
		List<NewsVO> newsObjects = null;
		NewsVO newsVOO = null;

		newsObjects = jdbcTemplate.query("select * from (select *  from news_summary  where lang=? and site=?  order by posteddate desc ) where rownum=1",
				new Object[] { newsVO.getLanguage(), PropertiesUtil.getProperty(newsVO.getSite() + "_csSiteName") }, new NewsDetailMapper());

		if (newsObjects != null && newsObjects.size() > 0) {
			logger.debug("Size of results =" + newsObjects.size());
			newsVOO = newsObjects.get(0);

		}

		return newsVOO;
	}
}
