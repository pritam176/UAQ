package com.uaq.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import uaq.db.si.model.common.AppModuleService;
import uaq.db.si.model.common.AppModuleService_Service;
import uaq.db.si.model.common.AreaLookupsViewSDO;
import uaq.db.si.model.common.EgdSuppCategoryLookupViewSDO;
import uaq.db.si.model.common.EgdSuppRegTypesLookupViewSDO;
import uaq.db.si.model.common.LandusageLookupsViewSDO;
import uaq.db.si.model.common.LpLandStatusLookupViewSDO;
import uaq.db.si.model.common.LpLandTypeLookupViewSDO;
import uaq.db.si.model.common.SectorLookupsViewSDO;

import com.oracle.xmlns.adf.svc.errors.ServiceException;
import com.oracle.xmlns.adf.svc.types.Conjunction;
import com.oracle.xmlns.adf.svc.types.FindControl;
import com.oracle.xmlns.adf.svc.types.FindCriteria;
import com.oracle.xmlns.adf.svc.types.ViewCriteria;
import com.oracle.xmlns.adf.svc.types.ViewCriteriaItem;
import com.oracle.xmlns.adf.svc.types.ViewCriteriaRow;
import static com.uaq.common.ApplicationConstants.*;
import static com.uaq.common.WebServiceConstant.*;
import com.uaq.logger.UAQLogger;

@Service("lplookup")
public class LpLookupService {
	AppModuleService_Service service;
	AppModuleService stub;
	
	
	public static transient UAQLogger logger = new UAQLogger(LpLookupService.class);

	/** Service Method for PS Land Status Look up **/

	public Map<String, Map<String, String>> landStatusLookupAR_EN() throws ServiceException {
		service = new AppModuleService_Service();
		stub = service.getAppModuleServiceSoapHttpPort();
		FindCriteria criteria = new FindCriteria();
		FindControl ctrl = new FindControl();
		criteria.setFetchSize(-1);
		criteria.setFetchStart(0);

		List<LpLandStatusLookupViewSDO> landstatus = null;
		try {
			landstatus = stub.findLpLandStatusLookupView1(criteria, ctrl);
		} catch (uaq.db.si.model.common.ServiceException e) {
			logger.error(e.getMessage());
		}

		Map<String, Map<String, String>> landstatusEN_AR = new HashMap<String, Map<String, String>>();

		Map<String, String> landStatus_EN = new HashMap<String, String>();
		Map<String, String> landStatus_AR = new HashMap<String, String>();

		for (int i = 0; i < landstatus.size(); i++) {

			LpLandStatusLookupViewSDO test = landstatus.get(i);
			landStatus_EN.put(test.getLandStatusId().toString(), test.getStatusNameEn().getValue());
			landStatus_AR.put(test.getLandStatusId().toString(), test.getStatusNameAr().getValue());
		}
		landstatusEN_AR.put(LANG_ARABIC, landStatus_AR);
		landstatusEN_AR.put(LANG_ENGLISH, landStatus_EN);
		return landstatusEN_AR;
	}

	/** Service Method for PS Land Type Look up **/

	public Map<String, Map<String, String>> landTypeLookupAR_EN() throws ServiceException {
		service = new AppModuleService_Service();
		stub = service.getAppModuleServiceSoapHttpPort();
		FindCriteria criteria = new FindCriteria();
		FindControl ctrl = new FindControl();
		
		// FindControl findControl = new FindControl();
		ViewCriteriaItem item = new ViewCriteriaItem();
		item.setAttribute(SOAP_PARAM_ISACTIVE1);
		item.setOperator(EQUAL_SIGN);
		item.getValue().add("1");
		item.setUpperCaseCompare(false);
		item.setConjunction(Conjunction.AND);
		ViewCriteriaRow group = new ViewCriteriaRow();
		group.setConjunction(Conjunction.AND);
		group.setUpperCaseCompare(false);
		group.getItem().add(item);

		ViewCriteria filter = new ViewCriteria();
		filter.setConjunction(Conjunction.AND);
		filter.getGroup().add(group);
		
		criteria.setFetchSize(-1);
		criteria.setFetchStart(0);
		criteria.setFilter(filter);

		List<LpLandTypeLookupViewSDO> landtype = null;
		try {
			landtype = stub.findLpLandTypeLookupView1(criteria, ctrl);
		} catch (uaq.db.si.model.common.ServiceException e) {
			logger.error(e.getMessage());
		}

		Map<String, Map<String, String>> landstatusEN_AR = new HashMap<String, Map<String, String>>();

		Map<String, String> landType_EN = new HashMap<String, String>();
		Map<String, String> landType_AR = new HashMap<String, String>();

		for (int i = 0; i < landtype.size(); i++) {

			LpLandTypeLookupViewSDO test = landtype.get(i);
			landType_EN.put(test.getLandTypeId().toString(), test.getLandTypeNameEn().getValue());
			landType_AR.put(test.getLandTypeId().toString(), test.getLandTypeNameAr().getValue());
		}
		landstatusEN_AR.put(LANG_ARABIC, landType_AR);
		landstatusEN_AR.put(LANG_ENGLISH, landType_EN);
		return landstatusEN_AR;
	}

	/** Service Method for PS Land USage Look up **/
	public Map<String, Map<String, String>> landUsageLookupAR_EN() throws ServiceException {
		service = new AppModuleService_Service();
		stub = service.getAppModuleServiceSoapHttpPort();
		FindCriteria criteria = new FindCriteria();
		FindControl ctrl = new FindControl();
		
		// FindControl findControl = new FindControl();
		ViewCriteriaItem item = new ViewCriteriaItem();
		item.setAttribute(SOAP_PARAM_ISACTIVE1);
		item.setOperator(EQUAL_SIGN);
		item.getValue().add("1");
		item.setUpperCaseCompare(false);
		item.setConjunction(Conjunction.AND);
		ViewCriteriaRow group = new ViewCriteriaRow();
		group.setConjunction(Conjunction.AND);
		group.setUpperCaseCompare(false);
		group.getItem().add(item);

		ViewCriteria filter = new ViewCriteria();
		filter.setConjunction(Conjunction.AND);
		filter.getGroup().add(group);
		
		criteria.setFetchSize(-1);
		criteria.setFetchStart(0);
		criteria.setFilter(filter);
	
		criteria.getFindAttribute().add(LAND_USAGE_EN);
		criteria.getFindAttribute().add(LAND_USAGE_AR);
		criteria.getFindAttribute().add(LAND_USAGE_ID);
		criteria.setExcludeAttribute(false);

		List<LandusageLookupsViewSDO> landtype = null;
		try {
			landtype = stub.findLandusageLookupsView1(criteria, ctrl);
		} catch (uaq.db.si.model.common.ServiceException e) {
			logger.error(e.getMessage());
		}

		Map<String, Map<String, String>> landstatusEN_AR = new HashMap<String, Map<String, String>>();

		Map<String, String> landType_EN = new HashMap<String, String>();
		Map<String, String> landType_AR = new HashMap<String, String>();

		for (int i = 0; i < landtype.size(); i++) {

			LandusageLookupsViewSDO test = landtype.get(i);
			landType_EN.put(test.getLandusageId().toString(), test.getLandusageEn().getValue());
			landType_AR.put(test.getLandusageId().toString(), test.getLandusageAr().getValue());
		}
		landstatusEN_AR.put(LANG_ARABIC, landType_AR);
		landstatusEN_AR.put(LANG_ENGLISH, landType_EN);
		return landstatusEN_AR;
	}
	/** Service Method for PS Sector Look up **/

	public Map<String, Map<String, String>> sectorLookupAR_EN() throws ServiceException {
		service = new AppModuleService_Service();
		stub = service.getAppModuleServiceSoapHttpPort();
		FindCriteria criteria = new FindCriteria();
		
		ViewCriteriaItem item = new ViewCriteriaItem();
		item.setAttribute(SOAP_PARAM_ISACTIVE1);
		item.setOperator(EQUAL_SIGN);
		item.getValue().add("1");
		item.setUpperCaseCompare(false);
		item.setConjunction(Conjunction.AND);
		ViewCriteriaRow group = new ViewCriteriaRow();
		group.setConjunction(Conjunction.AND);
		group.setUpperCaseCompare(false);
		group.getItem().add(item);

		ViewCriteria filter = new ViewCriteria();
		filter.setConjunction(Conjunction.AND);
		filter.getGroup().add(group);

		criteria.setFilter(filter);
		criteria.setFetchSize(-1);
		criteria.setFetchStart(0);

		FindControl control = new FindControl();
		control.setRetrieveAllTranslations(true);
		
		criteria.getFindAttribute().add(SECTOR_ID);
		criteria.getFindAttribute().add(SECTOR_EN);
		criteria.getFindAttribute().add(SECTOR_AR);
		criteria.setExcludeAttribute(false);

		List<SectorLookupsViewSDO> sector = null;
		try {
			sector = stub.findSectorLookupsView1(criteria, control);
		} catch (uaq.db.si.model.common.ServiceException e) {
			logger.error(e.getMessage());
		}

		Map<String, Map<String, String>> sectorEN_AR = new HashMap<String, Map<String, String>>();

		Map<String, String> sector_EN = new HashMap<String, String>();
		Map<String, String> sector_AR = new HashMap<String, String>();

		for (int i = 0; i < sector.size(); i++) {
			SectorLookupsViewSDO test = sector.get(i);
			//TODO: will change this
			sector_EN.put(String.valueOf(test.getSectorId()), test.getSectorEn().getValue());
			sector_AR.put(String.valueOf(test.getSectorId()), test.getSectorAr().getValue());
		}
		sectorEN_AR.put(LANG_ARABIC, sector_AR);
		sectorEN_AR.put(LANG_ENGLISH, sector_EN);
		return sectorEN_AR;
	}

	/** Service Method for PS Area Look up **/

	public Map<String, Map<String, String>> areaLookupAR_EN() throws ServiceException {
		service = new AppModuleService_Service();
		stub = service.getAppModuleServiceSoapHttpPort();
		FindCriteria criteria = new FindCriteria();
		
		ViewCriteriaItem item = new ViewCriteriaItem();
		item.setAttribute(SOAP_PARAM_ISACTIVE1);
		item.setOperator(EQUAL_SIGN);
		item.getValue().add("1");
		item.setUpperCaseCompare(false);
		item.setConjunction(Conjunction.AND);
		ViewCriteriaRow group = new ViewCriteriaRow();
		group.setConjunction(Conjunction.AND);
		group.setUpperCaseCompare(false);
		group.getItem().add(item);

		ViewCriteria filter = new ViewCriteria();
		filter.setConjunction(Conjunction.AND);
		filter.getGroup().add(group);

		criteria.setFilter(filter);
		criteria.setFetchSize(-1);
		criteria.setFetchStart(0);

		FindControl control = new FindControl();
		control.setRetrieveAllTranslations(true);
		
		criteria.getFindAttribute().add(AREA_EN);
		criteria.getFindAttribute().add(AREA_AR);
		criteria.getFindAttribute().add(AREA_ID);
		criteria.setExcludeAttribute(false);

		List<AreaLookupsViewSDO> area = null;
		try {
			area = stub.findAreaLookupsView1(criteria, control) ;
		} catch (uaq.db.si.model.common.ServiceException e) {
			logger.error(e.getMessage());
		}
		Map<String, Map<String, String>> areaEN_AR = new HashMap<String, Map<String, String>>();

		Map<String, String> area_EN = new HashMap<String, String>();
		Map<String, String> area_AR = new HashMap<String, String>();

		for (int i = 0; i < area.size(); i++) {

			AreaLookupsViewSDO test = area.get(i);
			area_EN.put(String.valueOf(test.getAreaId()), test.getAreaEn().getValue());
			area_AR.put(String.valueOf(test.getAreaId()), test.getAreaAr().getValue());
		}
		areaEN_AR.put(LANG_ARABIC, area_AR);
		areaEN_AR.put(LANG_ENGLISH, area_EN);
		return areaEN_AR;
	}

	/** Service Method for EGD Supplier Category List **/

	public Map<String, Map<String, String>> findEgdSuppCategoryEN_AR() throws ServiceException {
		service = new AppModuleService_Service();
		stub = service.getAppModuleServiceSoapHttpPort();
		
		FindControl ctrl = new FindControl();
		FindCriteria criteria = new FindCriteria();
		criteria.setFetchSize(-1);
		criteria.setFetchStart(0);
		
		
		criteria.getFindAttribute().add(SUPPLIER_CATAGORY_ID);
		criteria.getFindAttribute().add(SUPPLIER_CATAGORY_EN);
		criteria.getFindAttribute().add(SUPPLIER_CATAGORY_AR);
		criteria.setExcludeAttribute(false);
		
		List<EgdSuppCategoryLookupViewSDO> area = null;
		try {
			area = stub.findEgdSuppCategoryLookupView1(criteria, ctrl);
		} catch (uaq.db.si.model.common.ServiceException e) {
			logger.error(e.getMessage());
		}
		logger.info("findEgdSuppCategoryLookupView1 size is  ::" +area.size());
		logger.info("findEgdSuppCategoryLookupView1  is  ::" +area);
		Map<String, Map<String, String>> areaEN_AR = new HashMap<String, Map<String, String>>();

		Map<String, String> area_EN = new HashMap<String, String>();
		Map<String, String> area_AR = new HashMap<String, String>();

		
		for (int i = 0; i < area.size(); i++) {

			EgdSuppCategoryLookupViewSDO test = area.get(i);
			area_EN.put(test.getSuppCatgId().toString(), test.getSuppCategoryNameEn().getValue());
			area_AR.put(test.getSuppCatgId().toString(), test.getSuppCategoryNameAr().getValue());
		}
		areaEN_AR.put(LANG_ARABIC, area_AR);
		areaEN_AR.put(LANG_ENGLISH, area_EN);
		logger.info("areaEN_AR  is  ::" +areaEN_AR);
		return areaEN_AR;
	}

	/** Service Method for EGD Supplier Registration Type **/

	public Map<String, Map<String, String>> findEgdSuppRegTypesEN_AR() throws ServiceException {
		service = new AppModuleService_Service();
		stub = service.getAppModuleServiceSoapHttpPort();
		FindCriteria criteria = new FindCriteria();
		FindControl ctrl = new FindControl();
		criteria.setFetchSize(-1);
		criteria.setFetchStart(0);
		
		criteria.getFindAttribute().add(SUPPLIER_REGISTRATION_EN);
		criteria.getFindAttribute().add(SUPPLIER_REGISTRATION_AR);
		criteria.getFindAttribute().add(SUPPLIER_REGISTRATION_ID);
		criteria.setExcludeAttribute(false);

		List<EgdSuppRegTypesLookupViewSDO> area = null;
		try {
			area = stub.findEgdSuppRegTypesLookupView1(criteria, ctrl);
		} catch (uaq.db.si.model.common.ServiceException e) {
			logger.error(e.getMessage());
		}

		Map<String, Map<String, String>> areaEN_AR = new HashMap<String, Map<String, String>>();
		logger.info("findEgdSuppRegTypesLookupView1 size is ::"+area.size());
		logger.info("findEgdSuppRegTypesLookupView1 is ::"+area);
		Map<String, String> area_EN = new HashMap<String, String>();
		Map<String, String> area_AR = new HashMap<String, String>();

		for (int i = 0; i < area.size(); i++) {

			EgdSuppRegTypesLookupViewSDO test = area.get(i);
			area_EN.put(test.getSuppRegId().toString(), test.getSuppRegCategory().getValue());
			area_AR.put(test.getSuppRegId().toString(), test.getSuppRegCategoryAr().getValue());
			
		}
		areaEN_AR.put(LANG_ARABIC, area_AR);
		areaEN_AR.put(LANG_ENGLISH, area_EN);
		logger.info("areaEN_AR is ::"+areaEN_AR);
		return areaEN_AR;
	}

}
