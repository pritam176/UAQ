package com.uaq.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.fatwire.rest.beans.AssetBean;
import com.fatwire.rest.beans.Attribute;
import com.fatwire.wem.sso.SSOException;
import com.uaq.command.SearchCommand;
import com.uaq.common.AssetUtil;
import com.uaq.common.PropertiesUtil;
import com.uaq.controller.mapper.BaseVOMapper;
import com.uaq.dao.BaseDAO;
import com.uaq.dao.GenericDao;
import com.uaq.exception.UAQException;
import com.uaq.logger.UAQLogger;
import com.uaq.util.PaginationUtil;
import com.uaq.vo.CareersPageVO;
import com.uaq.vo.JobSearchResponseVO;
import com.uaq.vo.JobVO;
import com.uaq.vo.NavigationVO;
import com.uaq.vo.SearchResponseVO;

/**
 * 
 * @author ajain
 * 
 *         Service Class for the Careers page.
 */

@Service(value = "careerService")
public class CareerService implements BaseService<JobVO, JobVO> {

	protected static UAQLogger logger = new UAQLogger(EventsService.class);

	@Autowired
	@Qualifier("JobsDAO")
	BaseDAO<SearchCommand, SearchResponseVO> jobsDAO;

	@Autowired
	@Qualifier("contentReferneceDAO")
	GenericDao<NavigationVO, NavigationVO> contentReferneceDAO;
	
	@Autowired
	@Qualifier("careersPageVOMapper")
	private BaseVOMapper careersPageVOMapper;

	/**
	 * 
	 * @param jobSearchCommand
	 * @return JobList to CareerController.
	 */
	public SearchResponseVO getJobsList(SearchCommand jobSearchCommand) throws UAQException, SSOException {

		SearchResponseVO searchResponseVO = null;
		int pageSize = jobSearchCommand.getPageSize() > 0 ? jobSearchCommand.getPageSize() : Integer.valueOf(PropertiesUtil.getProperty("pagination.pagesize"));
		int paginationSetSize = Integer.valueOf(PropertiesUtil.getProperty("pagination.setsize"));
		jobSearchCommand.setStartRow((jobSearchCommand.getCurrentPage() - 1) * pageSize);
		jobSearchCommand.setPageSize(pageSize);

		searchResponseVO = jobsDAO.execute(jobSearchCommand);

		if (searchResponseVO != null && ((JobSearchResponseVO) searchResponseVO).getSearchResults() != null && ((JobSearchResponseVO) searchResponseVO).getSearchResults().size() > 0) {
			searchResponseVO.setCurrentPage(jobSearchCommand.getCurrentPage());
			PaginationUtil.updateSearcvhResponseWithPaginationLimits(searchResponseVO, pageSize, paginationSetSize);
		}

		return searchResponseVO;
	}

	/**
	 * 
	 * @param JobVO
	 * @return JobVO to the CareersController.
	 * @throws SSOException
	 */

	public JobVO getJobDetails(NavigationVO navigationVO) throws SSOException {

		JobVO jobVO = null;
		try {
			NavigationVO navigation = contentReferneceDAO.findByPrimaryKey(navigationVO);
			AssetBean assetBean = AssetUtil.getAssetDetail("uaq", "Content_C", navigation.getAssetId(), navigationVO.getTicketId());
			jobVO = getAssetDetail(assetBean);

		} catch (UAQException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return jobVO;
	}

	/**
	 * 
	 * @param assetBean
	 * @return JobVO to getJobDetails method.
	 */
	private JobVO getAssetDetail(AssetBean assetBean) {
		JobVO jobVO = new JobVO();
		jobVO.setAssetId(assetBean.getId().split(":")[1]);
		jobVO.setName(assetBean.getName());

		for (Attribute attribute : assetBean.getAttributes()) {
			if (attribute.getName().equalsIgnoreCase("JobTitle") && attribute.getData() != null) {
				jobVO.setJobTitle(attribute.getData().getStringValue());
			} else if (attribute.getName().equalsIgnoreCase("JobDescription") && attribute.getData() != null) {
				jobVO.setJobDescription(attribute.getData().getStringValue());
			} else if (attribute.getName().equalsIgnoreCase("JobResponsibility") && attribute.getData() != null) {
				jobVO.setJobResponsibility(attribute.getData().getStringValue());
			} else if (attribute.getName().equalsIgnoreCase("CreatedDate") && attribute.getData() != null && attribute.getData().getDateValue() != null) {
				jobVO.setCreatedDate(attribute.getData().getDateValue().toString().split(" ")[1]);
			} else if (attribute.getName().equalsIgnoreCase("EndDate") && attribute.getData() != null && attribute.getData().getDateValue() != null) {
				jobVO.setEndDate(attribute.getData().getDateValue().toString());
			} else if (attribute.getName().equalsIgnoreCase("teaserText") && attribute.getData() != null) {
				jobVO.setTeaserText(attribute.getData().getStringValue());
			} else if (attribute.getName().equalsIgnoreCase("DepartmentNameEN") && attribute.getData() != null) {
				jobVO.setDepartmentNameEN(attribute.getData().getStringValue());
			} else if (attribute.getName().equalsIgnoreCase("DepartmentNameAR") && attribute.getData() != null) {
				jobVO.setDepartmentNameAR(attribute.getData().getStringValue());
			} else if (attribute.getName().equalsIgnoreCase("JobReferenceNumber") && attribute.getData() != null) {
				jobVO.setJobReferenceNumber(attribute.getData().getStringValue());
			} else if (attribute.getName().equalsIgnoreCase("MailTo") && attribute.getData() != null) {
				jobVO.setMailTo(attribute.getData().getStringValue());
			} else if (attribute.getName().equalsIgnoreCase("mobileImage") && attribute.getData() != null && attribute.getData().getBlobValue() != null) {
				jobVO.setMobileImage(AssetUtil.getBolbURL(attribute.getData().getBlobValue().getHref(), "image%2Fjpeg"));
			}
		}
		return jobVO;
	}

	@Override
	public JobVO execute(JobVO inputObject) throws UAQException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JobVO executeSites(JobVO inputObject) throws UAQException {
		// TODO Auto-generated method stub
		return null;
	}

	public CareersPageVO getPageVO(NavigationVO navigationVO) {
		
		logger.enter("CareerService");
		CareersPageVO careersPageVO = null;
		
		try {
			AssetBean  assetBean = AssetUtil.getAssetDetail(navigationVO.getSite(), "Page", navigationVO.getAssetId(), navigationVO.getTicketId());
			careersPageVO = (CareersPageVO) careersPageVOMapper.mapAssetToVO(assetBean);
		} catch (SSOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// TODO Auto-generated method stub
		return careersPageVO;
	}
}
