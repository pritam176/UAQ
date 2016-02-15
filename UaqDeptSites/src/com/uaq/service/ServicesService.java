package com.uaq.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.fatwire.rest.beans.AssetBean;
import com.fatwire.rest.beans.AssetInfo;
import com.fatwire.rest.beans.AssetsBean;
import com.fatwire.wem.sso.SSOException;
import com.uaq.common.AssetUtil;
import com.uaq.controller.mapper.BaseVOMapper;
import com.uaq.dao.ServicesDAO;
import com.uaq.exception.UAQException;
import com.uaq.logger.UAQLogger;
import com.uaq.util.UrlTransliterationUtil;
import com.uaq.vo.ServiceVO;
import com.uaq.vo.ServicesVO;

@Service(value = "servicesService")
public class ServicesService {

	@Autowired
	@Qualifier("ServicesDetailMapper")
	private BaseVOMapper mapper;
	@Autowired
	@Qualifier("servicesDAO")
	private ServicesDAO servicesDAO;

	protected static UAQLogger logger = new UAQLogger(ServicesService.class);

	public Map<String, List<ServicesVO>> executeSites(ServicesVO servicesVO) throws UAQException {
		Map<String, List<ServicesVO>> servicesMap = new HashMap<String, List<ServicesVO>>();
		AssetsBean assetsBean;
		try {
			assetsBean = AssetUtil.searchAssetbyDefinition("Service", servicesVO.getSite(), "DateAndTime", "desc", servicesVO.getTicketId());
			servicesMap = getCategoryServiceMap(assetsBean, servicesVO);
		} catch (SSOException e) {
			logger.error("Error getting Services List");
		}
		return servicesMap;
	}

	/**
	 * 
	 * @param assetsBean
	 * @param servicesVO
	 * @return
	 * @throws SSOException
	 */
	private Map<String, List<ServicesVO>> getCategoryServiceMap(AssetsBean assetsBean, ServicesVO servicesVO) throws SSOException {
		List<ServicesVO> services = new ArrayList<ServicesVO>();
		List<String> categories = null;
		
		ServicesVO serviceVO;
		String language = servicesVO.getLanguage();
		Map<String, List<ServicesVO>> serviceMap = new HashMap<String, List<ServicesVO>>();
		for (AssetInfo assetBean : assetsBean.getAssetinfos()) {
			ServicesVO service = new ServicesVO();
			service.setAssetId(assetBean.getId().split(":")[1]);
			service.setTicketId(servicesVO.getTicketId());
			service.setSite(servicesVO.getSite());
			service.setLanguage(servicesVO.getLanguage());
			service = getServicesDetails(service);
			
			if (service != null) {
				service.setUrl(UrlTransliterationUtil.getTransliteratedString(service.getName()));
				services.add(service);
			}
			
		}
			categories = getCategoryList(services);
			for(String category : categories){
				{	
					serviceVO = new ServicesVO();
					serviceVO = getSitesService(category, services, servicesVO.getLanguage());
					if(language.equals("en") && null!=serviceVO.getServices()){
						serviceMap.put(category.split("\\|")[0], serviceVO.getServices());	
					}
					else if(language.equals("ar") && null!=serviceVO.getServices()){
						serviceMap.put(category.split("\\|")[1], serviceVO.getServices());	
					}
				}
		}
			// TODO Auto-generated method stub
			return serviceMap;
	}


	private ServicesVO getSitesService(String category, List<ServicesVO> services, String language) {
		ServicesVO service = new ServicesVO();
		String serviceCategoryName="";
		List<ServicesVO> servicesList = new ArrayList<ServicesVO>();
		
		
		for(ServicesVO serviceVO : services){
			if(language.equals("en") && null!=serviceVO.getServiceCategory() && category.equals(serviceVO.getServiceCategory())){
				serviceCategoryName = serviceVO.getServiceCategory().split("\\|")[0];
				serviceVO.setServiceCategory(serviceCategoryName);
				servicesList.add(serviceVO);
				service.setServices(servicesList);
			}
			else if(language.equals("ar") && null!=serviceVO.getServiceCategory() && category.equals(serviceVO.getServiceCategory())){
				serviceCategoryName = serviceVO.getServiceCategory().split("\\|")[1];
				serviceVO.setServiceCategory(serviceCategoryName);
				servicesList.add(serviceVO);
				service.setServices(servicesList);
			}
		}
		
		return service;
	}

	private List<String> getCategoryList(List<ServicesVO> services) {
		List<String> categories = new ArrayList<String>();
		for(ServicesVO serviceVO : services ){
			if(null!=serviceVO.getServiceCategory() && !(categories.contains(serviceVO.getServiceCategory()))){
				categories.add(serviceVO.getServiceCategory());
			}
		}
		return categories;
	}

/*	private ServicesVO getServiceCategoryMapFromServiceDetails(ServicesVO service, String language) {
		
		HashMap<String, ServicesVO> serviceCategoryMap = new HashMap<String, ServicesVO>();
		String serviceCategory = "";
		
		if(language.equals("en") && null!=service.getServiceCategory()){
			serviceCategory = service.getServiceCategory().split("\\|")[0];
		}
		
		else if(language.equals("ar") && null!=service.getServiceCategory()){
			serviceCategory = service.getServiceCategory().split("\\|")[1].toString();
		}
		
		serviceCategoryMap.put(serviceCategory, service);
		service.setServiceCategoryMap(serviceCategoryMap);
		// TODO Auto-generated method stub
		return service;
	}*/

	
	
	/**
	 * 
	 * @param servicesVO
	 * @return
	 */
	private ServicesVO getServicesDetails(ServicesVO servicesVO) {
		ServicesVO servicesVOReturn = null;
		AssetBean assetBean = null;
		String assetLocale = null;
		try {
			assetBean = AssetUtil.getAssetDetail(servicesVO.getSite(), "Content_C", servicesVO.getAssetId(), servicesVO.getTicketId());
			if (assetBean.getDimensions().size() > 0) {
				assetLocale = assetBean.getDimensions().get(0).getName();
			}
		} catch (SSOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (null != assetLocale && assetLocale.equalsIgnoreCase(servicesVO.getLanguage())) {
			servicesVOReturn = (ServicesVO) mapper.mapAssetToVO(assetBean);
		}
		// TODO Auto-generated method stub
		return servicesVOReturn;
	}

	public Map<String, List<ServicesVO>> getServices(String locale) throws UAQException {

		Map<String, List<ServicesVO>> serviceMap = new HashMap<String, List<ServicesVO>>();

		ServicesVO servicesVO = null;

		List<ServicesVO> serviceList = servicesDAO.execute(locale);

		servicesVO = getService("PlanningSurvey", locale, serviceList);
		if (null != servicesVO) {
			serviceMap.put(servicesVO.getDepartmentName(), servicesVO.getServices());
		}
		servicesVO = getService("LandsProperties", locale, serviceList);
		if (null != servicesVO) {
			serviceMap.put(servicesVO.getDepartmentName(), servicesVO.getServices());
		}
		servicesVO = getService("IndustrialCityAuthority", locale, serviceList);
		if (null != servicesVO) {
			serviceMap.put(servicesVO.getDepartmentName(), servicesVO.getServices());
		}
		servicesVO = getService("EGovernment", locale, serviceList);
		if (null != servicesVO) {
			serviceMap.put(servicesVO.getDepartmentName(), servicesVO.getServices());
		}
		servicesVO = getService("Municipality", locale, serviceList);
		if (null != servicesVO) {
			serviceMap.put(servicesVO.getDepartmentName(), servicesVO.getServices());
		}
		servicesVO = getService("EconomicDevelopment", locale, serviceList);
		if (null != servicesVO) {
			serviceMap.put(servicesVO.getDepartmentName(), servicesVO.getServices());
		}
		servicesVO = getService("FalajMunicipality", locale, serviceList);
		if (null != servicesVO) {
			serviceMap.put(servicesVO.getDepartmentName(), servicesVO.getServices());
		}
		servicesVO = getService("PublicWorksService", locale, serviceList);
		if (null != servicesVO) {
			serviceMap.put(servicesVO.getDepartmentName(), servicesVO.getServices());
		}
		servicesVO = getService("ArchaeologyHeritage", locale, serviceList);
		if (null != servicesVO) {
			serviceMap.put(servicesVO.getDepartmentName(), servicesVO.getServices());
		}
		servicesVO = getService("PortsCustomsFreeZone", locale, serviceList);
		if (null != servicesVO) {
			serviceMap.put(servicesVO.getDepartmentName(), servicesVO.getServices());
		}
		servicesVO = getService("ExecutiveCouncil", locale, serviceList);
		if (null != servicesVO) {
			serviceMap.put(servicesVO.getDepartmentName(), servicesVO.getServices());
		}
		servicesVO = getService("FinanceAdministration", locale, serviceList);
		if (null != servicesVO) {
			serviceMap.put(servicesVO.getDepartmentName(), servicesVO.getServices());
		}
		servicesVO = getService("FinancialAuditing", locale, serviceList);
		if (null != servicesVO) {
			serviceMap.put(servicesVO.getDepartmentName(), servicesVO.getServices());
		}
		return serviceMap;
	}

	public ServicesVO getService(String site, String locale, List<ServicesVO> serviceList) {
		ServicesVO service = null;
		List<ServicesVO> servicesList = new ArrayList<ServicesVO>();
		for (ServicesVO servicesVO : serviceList) {

			if (servicesVO.getSite().equals(site)) {
				service = new ServicesVO();
				if (locale.equals("en")) {
					service.setDepartmentName(servicesVO.getDepartmentNameEN());
				}
				if (locale.equals("ar")) {
					service.setDepartmentName(servicesVO.getDepartmentNameAR());
				}
				// servicesVO.setSite(getSector(servicesVO.getSite()));
				servicesList.add(servicesVO);
				service.setServices(servicesList);
			}
		}
		return service;
	}

}
