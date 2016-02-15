/**
 * 
 */
package com.uaq.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.uaq.exception.UAQException;
import com.uaq.logger.UAQLogger;
import com.uaq.util.UrlTransliterationUtil;
import com.uaq.vo.ServicesVO;

/**
 * @author TACME
 * 
 */

@Repository("servicesDAO")
public class ServicesDAO {

	private static transient UAQLogger logger = new UAQLogger(ServicesDAO.class);

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public List<ServicesVO> execute(String language) throws UAQException {
		logger.debug("Inside Services DAO");
		StringBuffer query = new StringBuffer();
		query.append("select cc.id AS ID,cc.NAME as NAME,pp.name AS SITE ,t1.DepartmentnameEN AS DepartmentnameEN,t2.DepartmentnameAR as DepartmentnameAR,t3.ServiceTitle as ServiceTitle,t4.ServiceEndUser as SERVICEENDUSER, t5.ServiceEnabled as SERVICEENABLED, t6.ExternalLink as EXTERNALLINK from content_c cc ");
		query.append("JOIN content_cd ccd  ON ccd.id=cc.flextemplateid JOIN Content_c_dim cdim   ON cdim.cs_ownerid=cc.id JOIN dimension dim   ON cdim.cs_dimensionid=dim.id ");
		query.append("JOIN assetpublication ap   ON cc.id=ap.assetid 	JOIN Publication pp   ON pp.id=pubid ");
		query.append("LEFT OUTER JOIN( SELECT cc.id,ccm.textvalue AS DepartmentnameEN, cc.flextemplateid AS flextemplateid  FROM content_c cc  JOIN content_c_mungo ccm ");
		query.append("ON ccm.cs_ownerid  =cc.id  AND ccm.cs_attrid IN  (SELECT ID FROM content_A WHERE name='DepartmentNameEN'   ) )t1  ON t1.id=cc.id  ");
		query.append("LEFT OUTER JOIN(   SELECT cc.id,ccm.textvalue     AS DepartmentnameAR   FROM content_c cc   JOIN content_c_mungo ccm   ON ccm.cs_ownerid  =cc.id ");
		query.append("AND ccm.cs_attrid IN  (SELECT ID FROM content_A WHERE name='DepartmentNameAR'  ))t2 ON t1.id=t2.id ");
		query.append("LEFT OUTER JOIN(   SELECT cc.id, ccm.textvalue     AS ServiceTitle   FROM content_c cc  JOIN content_c_mungo ccm   ON ccm.cs_ownerid  =cc.id ");
		query.append("AND ccm.cs_attrid IN (SELECT ID FROM content_A WHERE name='Title'    )  )t3 ON t1.id=t3.id  ");
		query.append("LEFT OUTER JOIN(   SELECT cc.id, ccm.stringvalue     AS ServiceEndUser   FROM content_c cc  JOIN content_c_mungo ccm   ON ccm.cs_ownerid  =cc.id ");
		query.append("AND ccm.cs_attrid IN (SELECT ID FROM content_A WHERE name='ServiceEndUser'    )  )t4 ON t1.id=t4.id  ");
		query.append("LEFT OUTER JOIN(   SELECT cc.id, ccm.stringvalue     AS ServiceEnabled   FROM content_c cc  JOIN content_c_mungo ccm   ON ccm.cs_ownerid  =cc.id ");
		query.append("AND ccm.cs_attrid IN (SELECT ID FROM content_A WHERE name='ServiceEnabled'    )  )t5 ON t1.id=t5.id ");
		query.append("LEFT OUTER JOIN(   SELECT cc.id, ccm.textvalue     AS ExternalLink   FROM content_c cc  JOIN content_c_mungo ccm   ON ccm.cs_ownerid  =cc.id ");
		query.append("AND ccm.cs_attrid IN (SELECT ID FROM content_A WHERE name='ExternalLink'    )  )t6 ON t1.id=t6.id  ");
		query.append(" WHERE ccd.name='Service'  AND cc.status !='VO' ");
		query.append("AND dim.name=?");

		List<ServicesVO> serviceList = jdbcTemplate.query(query.toString(), new Object[] { language }, new RowMapper<ServicesVO>() {
			public ServicesVO mapRow(ResultSet resultset, int rowNum) throws SQLException {
				ServicesVO service = new ServicesVO();
				service.setId(resultset.getString("ID"));
				service.setName(resultset.getString("NAME"));
				service.setSite(resultset.getString("SITE"));
				service.setDepartmentNameEN(resultset.getString("DepartmentnameEN"));
				service.setDepartmentNameAR(resultset.getString("DepartmentnameAR"));
				service.setServiceEndUser(resultset.getString("SERVICEENDUSER"));
				service.setUrl(getUrl(service.getSite(), service.getName()));
				service.setTitle(resultset.getString("ServiceTitle"));
				service.setExternalLink(resultset.getString("EXTERNALLINK"));
				service.setServiceEnabled(resultset.getString("SERVICEENABLED"));
				return service;
			}
		});
		return serviceList;

	}

	private String getUrl(String site, String name) {
		String url = UrlTransliterationUtil.getTransliteratedString(name);
		String siteAbr = null;
		if (site.equals("EGovernment")) {
			siteAbr = "egd";

		} else if (site.equals("Municipality")) {
			siteAbr = "mun";

		} else if (site.equals("EconomicDevelopment")) {
			siteAbr = "ded";

		} else if (site.equals("LandsProperties")) {
			siteAbr = "lap";

		} else if (site.equals("PlanningSurvey")) {
			siteAbr = "pas";

		} else if (site.equals("PublicWorksService")) {
			siteAbr = "pws";

		} else if (site.equals("ArchaeologyHeritage")) {
			siteAbr = "aah";

		} else if (site.equals("FalajMunicipality")) {
			siteAbr = "dfm";

		} else if (site.equals("IndustrialCityAuthority")) {
			siteAbr = "ica";

		} else if (site.equals("PortsCustomsFreeZone")) {
			siteAbr = "pcf";

		} else if (site.equals("ExecutiveCouncil")) {
			siteAbr = "dec";

		} else if (site.equals("FinanceAdministration")) {
			siteAbr = "faa";

		} else if (site.equals("FinancialAuditing")) {
			siteAbr = "dfa";

		}
		if (null != siteAbr) {

			url = siteAbr + "/" + url + ".html";
		}
		return url;
	}

}
