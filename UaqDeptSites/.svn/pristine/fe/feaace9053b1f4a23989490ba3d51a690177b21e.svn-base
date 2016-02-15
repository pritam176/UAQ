package com.uaq.service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import uaq.db.si.model.common.AccountDetailsViewSDO;
import uaq.db.si.model.common.AppModuleService;
import uaq.db.si.model.common.AppModuleService_Service;
import uaq.db.si.model.common.ApplicantRequestViewSDO;
import uaq.db.si.model.common.AreaLookupsViewSDO;
import uaq.db.si.model.common.EmirateLookupsViewSDO;
import uaq.db.si.model.common.LpLandCategoryViewSDO;
import uaq.db.si.model.common.LpLandStatusLookupViewSDO;
import uaq.db.si.model.common.LpLandTypeLookupViewSDO;
import uaq.db.si.model.common.LpProCardReqDetailsViewSDO;
import uaq.db.si.model.common.LpValuationViewSDO;
import uaq.db.si.model.common.NationalityLookupsViewSDO;
import uaq.db.si.model.common.SectorLookupsViewSDO;
import uaq.db.si.model.common.TradelicensetypeLookupsViewSDO;
import uaq.db.si.model.common.TwimcAddressedtoLookupViewSDO;

import com.oracle.xmlns.adf.svc.errors.ServiceException;
import com.oracle.xmlns.adf.svc.types.Conjunction;
import com.oracle.xmlns.adf.svc.types.FindControl;
import com.oracle.xmlns.adf.svc.types.FindCriteria;
import com.oracle.xmlns.adf.svc.types.SortAttribute;
import com.oracle.xmlns.adf.svc.types.SortOrder;
import com.oracle.xmlns.adf.svc.types.ViewCriteria;
import com.oracle.xmlns.adf.svc.types.ViewCriteriaItem;
import com.oracle.xmlns.adf.svc.types.ViewCriteriaRow;
import com.uaq.exception.UAQException;
import com.uaq.logger.UAQLogger;

/*
 * author gSekhar
 * WSDL-http://94.57.252.234:7001/UAQEServiceServiceInterface/AppModuleService?WSDL
 * returns list of National values
 */
@Service(value = "LPServiceLookUp")
public class LPServiceLookUp {

	protected static UAQLogger logger = new UAQLogger(LPServiceLookUp.class);

	AppModuleService_Service service;// new AppModuleService_Service();
	AppModuleService stub;// service.getAppModuleServiceSoapHttpPort();
	FindCriteria criteria = new FindCriteria();

	private class FilterCondition {

		private String filterField;
		private String filterOp;
		private String filterValue;

		public FilterCondition(String filterField, String filterOp, String filterValue) {
			this.filterField = filterField;
			this.filterOp = filterOp;
			this.filterValue = filterValue;
		}

	}

	public List<uaq.db.si.model.common.AccountattachmentsViewSDO> getUserAttachments(String accountId) {

		ViewCriteriaItem item = new ViewCriteriaItem();
		item.setAttribute("Accountid");
		item.setOperator("=");
		item.getValue().add(accountId);
		item.setUpperCaseCompare(false);
		item.setConjunction(Conjunction.AND);

		ViewCriteriaRow group = new ViewCriteriaRow();
		group.setConjunction(Conjunction.AND);
		group.setUpperCaseCompare(false);

		group.getItem().add(item);

		ViewCriteria filter = new ViewCriteria();
		filter.setConjunction(Conjunction.AND);
		filter.getGroup().add(group);

		FindCriteria criteria = new FindCriteria();
		criteria.setFetchSize(-1);
		criteria.setFetchStart(0);

		criteria.setFilter(filter);

		FindControl findControl = getFindControl();

		service = new AppModuleService_Service();
		stub = service.getAppModuleServiceSoapHttpPort();

		List<uaq.db.si.model.common.AccountattachmentsViewSDO> userAttachmentList = new ArrayList<uaq.db.si.model.common.AccountattachmentsViewSDO>();

		try {
			userAttachmentList = stub.findAccountattachmentsView1(criteria, findControl);
			logger.info("userAttachmentList Size  is ::" + userAttachmentList.size());

		} catch (uaq.db.si.model.common.ServiceException e) {
			logger.error("Service exception from findAccountattachmentsView1 ::" + e);
		}

		return userAttachmentList;
	}

	public Map<String, Map<String, String>> getNationalityListAR_EN() throws ServiceException, UAQException {
		List<FilterCondition> conditions = new ArrayList<FilterCondition>();
		conditions.add(new FilterCondition("IsActive", "=", "1"));
		conditions.add(new FilterCondition("NationalityId", ">=", "2"));
		FindCriteria criteria = createFilterCriteria(conditions, "NationalitynameEn");
		FindControl findControl = getFindControl();

		List<NationalityLookupsViewSDO> nationalList = null;
		try {
			service = new AppModuleService_Service();
			stub = service.getAppModuleServiceSoapHttpPort();
			nationalList = stub.findNationalityLookupsView1(criteria, findControl);
		} catch (Exception e) {
			throw new UAQException("findNationalityLookupsView Service Execution Failed");
		}

		Map<String, Map<String, String>> conutryEN_AR = new HashMap<String, Map<String, String>>();
		Map<String, String> country_EN = new HashMap<String, String>();
		Map<String, String> country_AR = new HashMap<String, String>();
		if (nationalList != null) {
			for (int i = 0; i < nationalList.size(); i++) {
				NationalityLookupsViewSDO conutry = nationalList.get(i);
				country_EN.put(conutry.getNationalityId().toString(), conutry.getNationalitynameEn().getValue());
				country_AR.put(conutry.getNationalityId().toString(), conutry.getNationalitynameAr().getValue());
			}
		}
		conutryEN_AR.put("ar", country_AR);
		conutryEN_AR.put("en", country_EN);
		return conutryEN_AR;
	}

	private FindControl getFindControl() {
		FindControl findControl = new FindControl();
		findControl.setRetrieveAllTranslations(true);
		return findControl;
	}

	private FindCriteria createFilterCriteria(List<FilterCondition> conditions, String sortField) {
		FindCriteria criteria = new FindCriteria();
		List<ViewCriteriaItem> items = new ArrayList<ViewCriteriaItem>();
		if (conditions != null) {
			for (FilterCondition condition : conditions) {
				ViewCriteriaItem item = new ViewCriteriaItem();
				item.setAttribute(condition.filterField);
				item.setOperator(condition.filterOp);
				item.getValue().add(condition.filterValue);
				item.setUpperCaseCompare(false);
				item.setConjunction(Conjunction.AND);
				items.add(item);
			}
		}

		if (!items.isEmpty()) {
			ViewCriteriaRow group = new ViewCriteriaRow();
			group.setConjunction(Conjunction.AND);
			group.setUpperCaseCompare(false);
			for (ViewCriteriaItem item : items) {
				group.getItem().add(item);
			}
			ViewCriteria filter = new ViewCriteria();
			filter.setConjunction(Conjunction.AND);
			filter.getGroup().add(group);

			criteria.setFilter(filter);
		}

		if (sortField != null) {
			SortAttribute sortAttribute = new SortAttribute();
			sortAttribute.setName(sortField);
			sortAttribute.setDescending(false);
			SortOrder sortOrder = new SortOrder();
			sortOrder.getSortAttribute().add(sortAttribute);
			criteria.setSortOrder(sortOrder);
		}

		criteria.setFetchSize(-1);
		criteria.setFetchStart(0);
		criteria.setExcludeAttribute(false);
		return criteria;
	}

	public Map<String, Map<String, String>> getGCCListAR_EN() throws ServiceException, UAQException {

		ViewCriteriaItem irem = new ViewCriteriaItem();
		irem.setAttribute("Isgcccountries");
		irem.setOperator("=");
		irem.getValue().add("1");
		irem.setUpperCaseCompare(false);
		irem.setConjunction(Conjunction.AND);

		ViewCriteriaItem item = new ViewCriteriaItem();
		item.setAttribute("NationalityId");
		item.setOperator(">=");
		item.getValue().add("2");
		item.setUpperCaseCompare(false);
		item.setConjunction(Conjunction.AND);

		ViewCriteriaRow group = new ViewCriteriaRow();
		group.setConjunction(Conjunction.AND);
		group.setUpperCaseCompare(false);
		group.getItem().add(irem);
		group.getItem().add(item);

		SortAttribute sortAttribute = new SortAttribute();
		sortAttribute.setName("NationalitynameEn");
		sortAttribute.setDescending(false);
		SortOrder sortOrder = new SortOrder();
		sortOrder.getSortAttribute().add(sortAttribute);

		ViewCriteria filter = new ViewCriteria();
		filter.setConjunction(Conjunction.AND);
		filter.getGroup().add(group);

		FindCriteria criteria = new FindCriteria();
		criteria.setFetchSize(-1);
		criteria.setFetchStart(0);

		criteria.setFilter(filter);
		criteria.getFindAttribute().add("NationalityId");
		criteria.getFindAttribute().add("NationalitynameEn");
		criteria.getFindAttribute().add("NationalitynameAr");
		criteria.setExcludeAttribute(false);
		criteria.setSortOrder(sortOrder);

		FindControl findControl = getFindControl();

		List<NationalityLookupsViewSDO> nationalList = null;
		try {
			service = new AppModuleService_Service();
			stub = service.getAppModuleServiceSoapHttpPort();
			nationalList = stub.findNationalityLookupsView1(criteria, findControl);
		} catch (Exception e) {
			throw new UAQException("findNationalityLookupsView Service Execution Failed");
		}

		Map<String, Map<String, String>> conutryEN_AR = new HashMap<String, Map<String, String>>();

		Map<String, String> country_EN = new HashMap<String, String>();
		Map<String, String> country_AR = new HashMap<String, String>();
		if (nationalList != null) {
			for (int i = 0; i < nationalList.size(); i++) {

				NationalityLookupsViewSDO conutry = nationalList.get(i);

				country_EN.put(conutry.getNationalityId().toString(), conutry.getNationalitynameEn().getValue());
				country_AR.put(conutry.getNationalityId().toString(), conutry.getNationalitynameAr().getValue());

			}
		}

		conutryEN_AR.put("ar", country_AR);
		conutryEN_AR.put("en", country_EN);
		return conutryEN_AR;
	}

	public Map<String, Map<String, String>> getEmiratesListAR_EN() throws ServiceException, UAQException {

		FindCriteria criteria = new FindCriteria();
		criteria.setFetchSize(-1);
		criteria.setFetchStart(0);
		FindControl findControl = new FindControl();

		ViewCriteria vc = new ViewCriteria();
		vc.setConjunction(Conjunction.AND);

		ViewCriteriaRow vcr = new ViewCriteriaRow();
		vcr.setConjunction(Conjunction.AND);
		vcr.setUpperCaseCompare(false);

		ViewCriteriaItem vci = new ViewCriteriaItem();
		vci.setConjunction(Conjunction.AND);
		vci.setUpperCaseCompare(false);
		vci.setAttribute("IsActive");
		vci.setOperator("=");
		vci.getValue().add("1");

		vcr.getItem().add(vci);

		vc.getGroup().add(vcr);
		criteria.setFilter(vc);
		criteria.getFindAttribute().add("EmirateId");
		criteria.getFindAttribute().add("EmiratenameEn");
		criteria.getFindAttribute().add("EmiratenameAr");
		criteria.setExcludeAttribute(false);

		List<EmirateLookupsViewSDO> emirateList = null;
		try {
			service = new AppModuleService_Service();
			stub = service.getAppModuleServiceSoapHttpPort();
			emirateList = stub.findEmirateLookupsView1(criteria, findControl);
		} catch (Exception e) {
			throw new UAQException("findEmirateLookupsView Service Execution Failed");
		}

		Map<String, Map<String, String>> emirateEN_AR = new HashMap<String, Map<String, String>>();

		Map<String, String> emirate_EN = new HashMap<String, String>();
		Map<String, String> emirate_AR = new HashMap<String, String>();
		if (emirateList != null) {
			for (int i = 0; i < emirateList.size(); i++) {

				EmirateLookupsViewSDO emirate = emirateList.get(i);

				emirate_EN.put(emirate.getEmirateId().toString(), emirate.getEmiratenameEn().getValue());
				emirate_AR.put(emirate.getEmirateId().toString(), emirate.getEmiratenameAr().getValue());

			}
		}

		emirateEN_AR.put("ar", emirate_AR);
		emirateEN_AR.put("en", emirate_EN);
		return emirateEN_AR;
	}

	public Map<String, Map<String, String>> getTradeLicenceAR_EN() throws ServiceException, UAQException {

		FindCriteria criteria = new FindCriteria();
		criteria.setFetchSize(-1);
		criteria.setFetchStart(0);
		FindControl findControl = new FindControl();

		ViewCriteria vc = new ViewCriteria();
		vc.setConjunction(Conjunction.AND);

		ViewCriteriaRow vcr = new ViewCriteriaRow();
		vcr.setConjunction(Conjunction.AND);
		vcr.setUpperCaseCompare(false);

		ViewCriteriaItem vci = new ViewCriteriaItem();
		vci.setConjunction(Conjunction.AND);
		vci.setUpperCaseCompare(false);
		vci.setAttribute("Isactive");
		vci.setOperator("=");
		vci.getValue().add("1");

		vcr.getItem().add(vci);

		vc.getGroup().add(vcr);
		criteria.setFilter(vc);
		criteria.getFindAttribute().add("Tradelicensetypeid");
		criteria.getFindAttribute().add("TradelicensetypeEn");
		criteria.getFindAttribute().add("TradelicensetypeAr");
		criteria.setExcludeAttribute(false);

		List<TradelicensetypeLookupsViewSDO> tradelicensetypeLookups = null;
		try {
			service = new AppModuleService_Service();
			stub = service.getAppModuleServiceSoapHttpPort();
			tradelicensetypeLookups = stub.findTradelicensetypeLookupsView1(criteria, findControl);
		} catch (Exception e) {
			throw new UAQException("findTradelicensetypeLookupsView Service Execution Failed");
		}

		Map<String, Map<String, String>> tradeliceneEN_AR = new HashMap<String, Map<String, String>>();

		Map<String, String> tradelicene_EN = new HashMap<String, String>();
		Map<String, String> tradelicene_AR = new HashMap<String, String>();

		if (tradelicensetypeLookups != null) {
			for (int i = 0; i < tradelicensetypeLookups.size(); i++) {

				TradelicensetypeLookupsViewSDO tradelicene = tradelicensetypeLookups.get(i);

				tradelicene_EN.put(tradelicene.getTradelicensetypeid().toString(), tradelicene.getTradelicensetypeEn().getValue());
				tradelicene_AR.put(tradelicene.getTradelicensetypeid().toString(), tradelicene.getTradelicensetypeAr().getValue());
			}
		}

		tradeliceneEN_AR.put("ar", tradelicene_AR);
		tradeliceneEN_AR.put("en", tradelicene_EN);

		return tradeliceneEN_AR;
	}

	public Map<String, String> getAddressedTOValues(String locale) throws ServiceException {

		Map<String, String> addressedTOMap = new LinkedHashMap<String, String>();
		FindCriteria findCriteria = new FindCriteria();
		FindControl findControl = new FindControl();

		service = new AppModuleService_Service();
		stub = service.getAppModuleServiceSoapHttpPort();

		findCriteria.setFetchStart(0);
		findCriteria.setFetchSize(-1);
		findCriteria.getFindAttribute().add("AddressedtoId");
		findCriteria.getFindAttribute().add("AddressedtoEn");
		findCriteria.getFindAttribute().add("AddressedtoAr");
		findCriteria.setExcludeAttribute(false);

		findControl.setRetrieveAllTranslations(false);

		List<TwimcAddressedtoLookupViewSDO> tWIMCaddressedToList = null;
		try {
			tWIMCaddressedToList = stub.findTwimcAddressedtoLookupView1(findCriteria, findControl);
		} catch (uaq.db.si.model.common.ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (TwimcAddressedtoLookupViewSDO listValues : tWIMCaddressedToList) {
			if (locale.equals("en")) {
				addressedTOMap.put(listValues.getAddressedtoId().toString(), listValues.getAddressedtoEn().getValue());
			} else {
				addressedTOMap.put(listValues.getAddressedtoId().toString(), listValues.getAddressedtoAr().getValue());
			}
		}

		return addressedTOMap;
	}

	public AccountDetailsViewSDO getAccountDetails(String accountId) {
		service = new AppModuleService_Service();
		stub = service.getAppModuleServiceSoapHttpPort();
		try {
			return stub.getAccountDetailsView1(accountId);
		} catch (uaq.db.si.model.common.ServiceException e) {
			e.printStackTrace();
		}
		return null;
	}

	public ApplicantRequestViewSDO getApplicantRequestByRequestNumber(String requestNumber) {
		List<FilterCondition> conditions = new ArrayList<FilterCondition>();
		conditions.add(new FilterCondition("RequestNo", "=", requestNumber));
		FindCriteria criteria = createFilterCriteria(conditions, null);
		FindControl findControl = getFindControl();

		try {
			service = new AppModuleService_Service();
			stub = service.getAppModuleServiceSoapHttpPort();
			return stub.findApplicantRequestView1(criteria, findControl).get(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public Map<String, Map<String, String>> getRenewableCardRequestListAR_EN(String accountId) throws UAQException {
		List<FilterCondition> conditions = new ArrayList<FilterCondition>();
		conditions.add(new FilterCondition("AccountId", "=", accountId));
		conditions.add(new FilterCondition("ServId", "=", "403"));
		FindCriteria criteria = createFilterCriteria(conditions, "ProName");
		FindControl findControl = getFindControl();

		List<LpProCardReqDetailsViewSDO> proCardList = null;
		try {
			service = new AppModuleService_Service();
			stub = service.getAppModuleServiceSoapHttpPort();
			proCardList = stub.findLpProCardReqDetailsView1(criteria, findControl);
		} catch (Exception e) {
			throw new UAQException("findApplicantRequestView1 Service Execution Failed");
		}

		Map<String, Map<String, String>> proCardReturnMap = new HashMap<String, Map<String, String>>();

		Map<String, String> proCardMap = new HashMap<String, String>();
		if (proCardList != null) {
			for (int i = 0; i < proCardList.size(); i++) {
				LpProCardReqDetailsViewSDO proCard = proCardList.get(i);
				proCardMap.put(proCard.getProCardReqId().toString(), proCard.getProName().getValue());
			}
		}

		proCardReturnMap.put("All", proCardMap);
		return proCardReturnMap;
	}

	public LpProCardReqDetailsViewSDO getProCard(String proCardId) {
		service = new AppModuleService_Service();
		stub = service.getAppModuleServiceSoapHttpPort();
		try {
			return stub.getLpProCardReqDetailsView1(new BigInteger(proCardId));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public LpProCardReqDetailsViewSDO getProCardByProNumber(String proIdNo) {
		List<FilterCondition> conditions = new ArrayList<FilterCondition>();
		conditions.add(new FilterCondition("ProIdNum", "=", proIdNo));
		FindCriteria findCriteria = createFilterCriteria(conditions, null);
		FindControl findControl = getFindControl();

		service = new AppModuleService_Service();
		stub = service.getAppModuleServiceSoapHttpPort();
		try {
			return stub.findLpProCardReqDetailsView1(findCriteria, findControl).get(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public LpProCardReqDetailsViewSDO getProCardByRequestNumber(String requestNumber) {
		List<FilterCondition> conditions = new ArrayList<FilterCondition>();
		conditions.add(new FilterCondition("RequestNo", "=", requestNumber));
		FindCriteria findCriteria = createFilterCriteria(conditions, null);
		FindControl findControl = getFindControl();

		service = new AppModuleService_Service();
		stub = service.getAppModuleServiceSoapHttpPort();
		try {
			return stub.findLpProCardReqDetailsView1(findCriteria, findControl).get(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public Map<String, Map<String, String>> getLandStatusListAR_EN() throws UAQException {
		FindCriteria findCriteria = createFilterCriteria(null, null);
		FindControl findControl = getFindControl();

		service = new AppModuleService_Service();
		stub = service.getAppModuleServiceSoapHttpPort();

		List<LpLandStatusLookupViewSDO> lookupStatusList;
		try {
			lookupStatusList = stub.findLpLandStatusLookupView1(findCriteria, findControl);
		} catch (Exception e) {
			throw new UAQException("findLpLandStatusLookupView1 Service Execution Failed");
		}

		Map<String, Map<String, String>> lookupStatusReturnMap = new HashMap<String, Map<String, String>>();

		Map<String, String> lookupStatus_EN = new HashMap<String, String>();
		Map<String, String> lookupStatus_AR = new HashMap<String, String>();
		if (lookupStatusList != null) {
			for (int i = 0; i < lookupStatusList.size(); i++) {
				LpLandStatusLookupViewSDO landStatus = lookupStatusList.get(i);
				lookupStatus_EN.put(landStatus.getLandStatusId().toString(), landStatus.getStatusNameEn().getValue());
				lookupStatus_AR.put(landStatus.getLandStatusId().toString(), landStatus.getStatusNameAr().getValue());
			}
		}

		lookupStatusReturnMap.put("ar", lookupStatus_AR);
		lookupStatusReturnMap.put("en", lookupStatus_EN);
		return lookupStatusReturnMap;
	}

	public Map<String, Map<String, String>> getLandTypeListAR_EN() throws UAQException {
		FindCriteria findCriteria = createFilterCriteria(null, null);
		FindControl findControl = getFindControl();

		service = new AppModuleService_Service();
		stub = service.getAppModuleServiceSoapHttpPort();

		List<LpLandTypeLookupViewSDO> lookupTypeList;
		try {
			lookupTypeList = stub.findLpLandTypeLookupView1(findCriteria, findControl);
		} catch (Exception e) {
			throw new UAQException("findLpLandTypeLookupView1 Service Execution Failed");
		}

		Map<String, Map<String, String>> lookupTypeReturnMap = new HashMap<String, Map<String, String>>();

		Map<String, String> lookupType_EN = new HashMap<String, String>();
		Map<String, String> lookupType_AR = new HashMap<String, String>();
		if (lookupTypeList != null) {
			for (int i = 0; i < lookupTypeList.size(); i++) {
				LpLandTypeLookupViewSDO landType = lookupTypeList.get(i);
				lookupType_EN.put(landType.getLandTypeId().toString(), landType.getLandTypeNameEn().getValue());
				lookupType_AR.put(landType.getLandTypeId().toString(), landType.getLandTypeNameAr().getValue());
			}
		}

		lookupTypeReturnMap.put("ar", lookupType_AR);
		lookupTypeReturnMap.put("en", lookupType_EN);
		return lookupTypeReturnMap;
	}

	public Map<String, Map<String, String>> getSectorListAR_EN() throws UAQException {
		List<FilterCondition> conditions = new ArrayList<FilterCondition>();
		conditions.add(new FilterCondition("IsActive", "=", "1"));
		conditions.add(new FilterCondition("IsDeleted", "<>", "1"));
		FindCriteria findCriteria = createFilterCriteria(conditions, "SectorEn");
		FindControl findControl = getFindControl();

		service = new AppModuleService_Service();
		stub = service.getAppModuleServiceSoapHttpPort();

		List<SectorLookupsViewSDO> sectorList;
		try {
			sectorList = stub.findSectorLookupsView1(findCriteria, findControl);
		} catch (Exception e) {
			throw new UAQException("findSectorLookupsView1 Service Execution Failed");
		}

		Map<String, Map<String, String>> sectorReturnMap = new HashMap<String, Map<String, String>>();

		Map<String, String> sector_EN = new HashMap<String, String>();
		Map<String, String> sector_AR = new HashMap<String, String>();
		if (sectorList != null) {
			for (int i = 0; i < sectorList.size(); i++) {
				SectorLookupsViewSDO sector = sectorList.get(i);
				sector_EN.put(sector.getSectorId().toString(), sector.getSectorEn().getValue());
				sector_AR.put(sector.getSectorId().toString(), sector.getSectorAr().getValue());
			}
		}

		sectorReturnMap.put("ar", sector_AR);
		sectorReturnMap.put("en", sector_EN);
		return sectorReturnMap;
	}

	public Map<String, Map<String, String>> getAreaListAR_EN() throws UAQException {
		List<FilterCondition> conditions = new ArrayList<FilterCondition>();
		conditions.add(new FilterCondition("IsActive", "=", "1"));
		conditions.add(new FilterCondition("IsDeleted", "<>", "1"));
		FindCriteria findCriteria = createFilterCriteria(conditions, "AreaEn");
		FindControl findControl = getFindControl();

		service = new AppModuleService_Service();
		stub = service.getAppModuleServiceSoapHttpPort();

		List<AreaLookupsViewSDO> areaList;
		try {
			areaList = stub.findAreaLookupsView1(findCriteria, findControl);
		} catch (uaq.db.si.model.common.ServiceException e) {
			throw new UAQException("findAreaLookupsView1 Service Execution Failed");
		}

		Map<String, Map<String, String>> areaReturnMap = new HashMap<String, Map<String, String>>();
		Map<String, String> area_EN = new HashMap<String, String>();
		Map<String, String> area_AR = new HashMap<String, String>();
		if (areaList != null) {
			for (int i = 0; i < areaList.size(); i++) {
				AreaLookupsViewSDO Area = areaList.get(i);
				area_EN.put(Area.getAreaId().toString(), Area.getAreaEn().getValue());
				area_AR.put(Area.getAreaId().toString(), Area.getAreaAr().getValue());
			}
		}

		areaReturnMap.put("ar", area_AR);
		areaReturnMap.put("en", area_EN);
		return areaReturnMap;
	}

	public String getNationalityValueAR_EN(String languageCode, String lookupValueCode) throws UAQException {
		NationalityLookupsViewSDO nationality = null;
		try {
			service = new AppModuleService_Service();
			stub = service.getAppModuleServiceSoapHttpPort();
			nationality = stub.getNationalityLookupsView1(new BigDecimal(lookupValueCode));
			if (nationality == null)
				return "";
			if (languageCode.equals("en"))
				return nationality.getNationalitynameEn().getValue();
			else
				return nationality.getNationalitynameAr().getValue();
		} catch (Exception e) {
			throw new UAQException("getNationalityLookupsView1 Service Execution Failed");
		}
	}

	public String getLandStatusValueAR_EN(String languageCode, String lookupValueCode) throws UAQException {
		LpLandStatusLookupViewSDO landStatus = null;
		try {
			service = new AppModuleService_Service();
			stub = service.getAppModuleServiceSoapHttpPort();
			landStatus = stub.getLpLandStatusLookupView1(new BigInteger(lookupValueCode));
			if (landStatus == null)
				return "";
			if (languageCode.equals("en"))
				return landStatus.getStatusNameEn().getValue();
			else
				return landStatus.getStatusNameAr().getValue();
		} catch (Exception e) {
			throw new UAQException("getLpLandStatusLookupView1 Service Execution Failed");
		}
	}

	public String getLandTypeValueAR_EN(String languageCode, String lookupValueCode) throws UAQException {
		LpLandTypeLookupViewSDO landType = null;
		try {
			service = new AppModuleService_Service();
			stub = service.getAppModuleServiceSoapHttpPort();
			landType = stub.getLpLandTypeLookupView1(new BigInteger(lookupValueCode));
			if (landType == null)
				return "";
			if (languageCode.equals("en"))
				return landType.getLandTypeNameEn().getValue();
			else
				return landType.getLandTypeNameEn().getValue();
		} catch (Exception e) {
			throw new UAQException("getLpLandTypeLookupView1 Service Execution Failed");
		}
	}

	public String getSectorValueAR_EN(String languageCode, String lookupValueCode) throws UAQException {
		SectorLookupsViewSDO sector = null;
		try {
			service = new AppModuleService_Service();
			stub = service.getAppModuleServiceSoapHttpPort();
			sector = stub.getSectorLookupsView1(new BigDecimal(lookupValueCode));
			if (sector == null)
				return "";
			if (languageCode.equals("en"))
				return sector.getSectorEn().getValue();
			else
				return sector.getSectorAr().getValue();
		} catch (Exception e) {
			throw new UAQException("getSectorLookupsView1 Service Execution Failed");
		}
	}

	public String getAreaValueAR_EN(String languageCode, String lookupValueCode) throws UAQException {
		AreaLookupsViewSDO area = null;
		try {
			service = new AppModuleService_Service();
			stub = service.getAppModuleServiceSoapHttpPort();
			area = stub.getAreaLookupsView1(new BigDecimal(lookupValueCode));
			if (area == null)
				return "";
			if (languageCode.equals("en"))
				return area.getAreaEn().getValue();
			else
				return area.getAreaAr().getValue();
		} catch (Exception e) {
			throw new UAQException("getAreaLookupsView1 Service Execution Failed");
		}
	}

	public LpValuationViewSDO getLPValudationRequestByRequestNumber(String requestNumber) {
		List<FilterCondition> conditions = new ArrayList<FilterCondition>();
		conditions.add(new FilterCondition("RequestNo", "=", requestNumber));
		FindCriteria criteria = createFilterCriteria(conditions, null);
		FindControl findControl = getFindControl();
		try {
			service = new AppModuleService_Service();
			stub = service.getAppModuleServiceSoapHttpPort();
			return stub.findLpValuationView1(criteria, findControl).get(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public Map<String, Map<String, String>> getOwnerLandCatergoryListAR_EN() throws UAQException {
		List<FilterCondition> conditions = new ArrayList<FilterCondition>();
		conditions.add(new FilterCondition("UserPositionId", "=", "1"));
		FindCriteria findCriteria = createFilterCriteria(conditions, null);
		FindControl findControl = getFindControl();

		service = new AppModuleService_Service();
		stub = service.getAppModuleServiceSoapHttpPort();

		List<LpLandCategoryViewSDO> lookupStatusList;
		try {
			lookupStatusList = stub.findLpLandCategoryView1(findCriteria, findControl);
		} catch (Exception e) {
			throw new UAQException("findLpLandCategoryView1 Service Execution Failed");
		}

		Map<String, Map<String, String>> lookupStatusReturnMap = new HashMap<String, Map<String, String>>();

		Map<String, String> lookupStatus_EN = new HashMap<String, String>();
		Map<String, String> lookupStatus_AR = new HashMap<String, String>();
		if (lookupStatusList != null) {
			for (int i = 0; i < lookupStatusList.size(); i++) {
				LpLandCategoryViewSDO landStatus = lookupStatusList.get(i);
				lookupStatus_EN.put(landStatus.getLandCategoryId().toString(), landStatus.getLandCategoryNameEn().getValue());
				lookupStatus_AR.put(landStatus.getLandCategoryId().toString(), landStatus.getLandCategoryNameAr().getValue());
			}
		}

		lookupStatusReturnMap.put("ar", lookupStatus_AR);
		lookupStatusReturnMap.put("en", lookupStatus_EN);
		return lookupStatusReturnMap;
	}

}