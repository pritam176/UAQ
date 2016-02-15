package com.uaq.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import uaq.db.si.model.common.AppModuleService;
import uaq.db.si.model.common.AppModuleService_Service;
import uaq.db.si.model.common.EmirateLookupsViewSDO;
import uaq.db.si.model.common.NationalityLookupsViewSDO;
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

import static com.uaq.common.ApplicationConstants.*;
import static com.uaq.common.WebServiceConstant.*;

/*
 * author gSekhar
 * WSDL-http://94.57.252.234:7001/UAQEServiceServiceInterface/AppModuleService?WSDL
 * returns list of National values
 */
@Service(value = "lookupServiceEN_AR")
public class LookupServiceEN_AR {

	protected static UAQLogger logger = new UAQLogger(LookupServiceEN_AR.class);

	AppModuleService_Service service;// new AppModuleService_Service();
	AppModuleService stub;// service.getAppModuleServiceSoapHttpPort();

	FindCriteria criteria = new FindCriteria();
	

	
	public List<uaq.db.si.model.common.AccountattachmentsViewSDO> getUserAttachments(String accountId){
		
		ViewCriteriaItem item=new ViewCriteriaItem();
		item.setAttribute(SOAP_ACCOUNT_ID);
		item.setOperator(EQUAL_SIGN);
		item.getValue().add(accountId);
		item.setUpperCaseCompare(false);
		item.setConjunction(Conjunction.AND);
		
		ViewCriteriaRow group =new ViewCriteriaRow();
		group.setConjunction(Conjunction.AND);
		group.setUpperCaseCompare(false);
		
		group.getItem().add(item);
		
		ViewCriteria filter=new ViewCriteria();
		filter.setConjunction(Conjunction.AND);
		filter.getGroup().add(group);
		

		FindCriteria criteria = new FindCriteria();
		criteria.setFetchSize(-1);
		criteria.setFetchStart(0);
		
		criteria.setFilter(filter);
		
		FindControl findControl = new FindControl();
		findControl.setRetrieveAllTranslations(true);
		
		service = new AppModuleService_Service();
		stub = service.getAppModuleServiceSoapHttpPort();
		
		List<uaq.db.si.model.common.AccountattachmentsViewSDO> userAttachmentList = new ArrayList<uaq.db.si.model.common.AccountattachmentsViewSDO>();
		
		try {
			userAttachmentList = stub.findAccountattachmentsView1(criteria, findControl);
			logger.info("userAttachmentList Size  is ::"+userAttachmentList.size());
			
			
		} catch (uaq.db.si.model.common.ServiceException e) {
			logger.error("Service exception from findAccountattachmentsView1 ::"+e);
		}
		
		return userAttachmentList;
	}

	public Map<String, Map<String, String>> getNationalityListAR_EN() throws ServiceException, UAQException {

		ViewCriteriaItem irem=new ViewCriteriaItem();
		irem.setAttribute(SOAP_PARAM_ISACTIVE1);
		irem.setOperator(EQUAL_SIGN);
		irem.getValue().add("1");
		irem.setUpperCaseCompare(false);
		irem.setConjunction(Conjunction.AND);
		
		ViewCriteriaItem item=new ViewCriteriaItem();
		item.setAttribute(SOAP_NATIONALITY_ID);
		item.setOperator(">=");
		item.getValue().add("2");
		item.setUpperCaseCompare(false);
		item.setConjunction(Conjunction.AND);
		
		ViewCriteriaRow group =new ViewCriteriaRow();
		group.setConjunction(Conjunction.AND);
		group.setUpperCaseCompare(false);
		group.getItem().add(irem);
		group.getItem().add(item);
		
		SortAttribute sortAttribute=new SortAttribute();
		sortAttribute.setName(SOAP_NATIONALITY_EN);
		sortAttribute.setDescending(false);
		SortOrder sortOrder=new SortOrder();
		sortOrder.getSortAttribute().add(sortAttribute);
		
		ViewCriteria filter=new ViewCriteria();
		filter.setConjunction(Conjunction.AND);
		filter.getGroup().add(group);
		

		FindCriteria criteria = new FindCriteria();
		criteria.setFetchSize(-1);
		criteria.setFetchStart(0);
		
		criteria.setFilter(filter);
		criteria.getFindAttribute().add(SOAP_NATIONALITY_ID);
		criteria.getFindAttribute().add(SOAP_NATIONALITY_EN);
		criteria.getFindAttribute().add(SOAP_NATIONALITY_AR);
		criteria.setExcludeAttribute(false);
		criteria.setSortOrder(sortOrder);
		
		
		FindControl findControl = new FindControl();
		findControl.setRetrieveAllTranslations(true);

		
		List<NationalityLookupsViewSDO> nationalList=null;
		try{
			service = new AppModuleService_Service();
			stub = service.getAppModuleServiceSoapHttpPort();
			nationalList = stub.findNationalityLookupsView1(criteria, findControl);
		}catch (Exception e) {
			throw new UAQException("findNationalityLookupsView Service Execution Failed");
		}
		

		Map<String, Map<String, String>> conutryEN_AR = new HashMap<String, Map<String, String>>();

		Map<String, String> country_EN = new HashMap<String, String>();
		Map<String, String> country_AR = new HashMap<String, String>();
		if(nationalList !=null){
			for (int i = 0; i < nationalList.size(); i++) {

				NationalityLookupsViewSDO conutry = nationalList.get(i);

				country_EN.put(conutry.getNationalityId().toString(), conutry.getNationalitynameEn().getValue());
				country_AR.put(conutry.getNationalityId().toString(), conutry.getNationalitynameAr().getValue());

			}	
		}
		
		conutryEN_AR.put(LANG_ARABIC, country_AR);
		conutryEN_AR.put(LANG_ENGLISH, country_EN);
		return conutryEN_AR;
	}
	
	public Map<String, Map<String, String>> getGCCListAR_EN() throws ServiceException, UAQException {

		ViewCriteriaItem irem=new ViewCriteriaItem();
		irem.setAttribute(SOAP_IS_GCC);
		irem.setOperator(EQUAL_SIGN);
		irem.getValue().add("1");
		irem.setUpperCaseCompare(false);
		irem.setConjunction(Conjunction.AND);
		
		ViewCriteriaItem item=new ViewCriteriaItem();
		item.setAttribute(SOAP_NATIONALITY_ID);
		item.setOperator(">=");
		item.getValue().add("2");
		item.setUpperCaseCompare(false);
		item.setConjunction(Conjunction.AND);
		
		ViewCriteriaRow group =new ViewCriteriaRow();
		group.setConjunction(Conjunction.AND);
		group.setUpperCaseCompare(false);
		group.getItem().add(irem);
		group.getItem().add(item);
		
		SortAttribute sortAttribute=new SortAttribute();
		sortAttribute.setName(SOAP_NATIONALITY_EN);
		sortAttribute.setDescending(false);
		SortOrder sortOrder=new SortOrder();
		sortOrder.getSortAttribute().add(sortAttribute);
		
		ViewCriteria filter=new ViewCriteria();
		filter.setConjunction(Conjunction.AND);
		filter.getGroup().add(group);
		

		FindCriteria criteria = new FindCriteria();
		criteria.setFetchSize(-1);
		criteria.setFetchStart(0);
		
		criteria.setFilter(filter);
		criteria.getFindAttribute().add(SOAP_NATIONALITY_ID);
		criteria.getFindAttribute().add(SOAP_NATIONALITY_EN);
		criteria.getFindAttribute().add(SOAP_NATIONALITY_AR);
		criteria.setExcludeAttribute(false);
		criteria.setSortOrder(sortOrder);
		
		
		FindControl findControl = new FindControl();
		findControl.setRetrieveAllTranslations(true);

		
		List<NationalityLookupsViewSDO> nationalList=null;
		try{
			service = new AppModuleService_Service();
			stub = service.getAppModuleServiceSoapHttpPort();
			nationalList = stub.findNationalityLookupsView1(criteria, findControl);
		}catch (Exception e) {
			throw new UAQException("findNationalityLookupsView Service Execution Failed");
		}
		

		Map<String, Map<String, String>> conutryEN_AR = new HashMap<String, Map<String, String>>();

		Map<String, String> country_EN = new HashMap<String, String>();
		Map<String, String> country_AR = new HashMap<String, String>();
		if(nationalList !=null){
			for (int i = 0; i < nationalList.size(); i++) {

				NationalityLookupsViewSDO conutry = nationalList.get(i);

				country_EN.put(conutry.getNationalityId().toString(), conutry.getNationalitynameEn().getValue());
				country_AR.put(conutry.getNationalityId().toString(), conutry.getNationalitynameAr().getValue());

			}	
		}
		
		conutryEN_AR.put(LANG_ARABIC, country_AR);
		conutryEN_AR.put(LANG_ENGLISH, country_EN);
		return conutryEN_AR;
	}

	public Map<String, Map<String, String>> getEmiratesListAR_EN() throws ServiceException, UAQException {

		FindCriteria criteria = new FindCriteria();
		criteria.setFetchSize(-1);
		criteria.setFetchStart(0);
		FindControl findControl = new FindControl();
		
        ViewCriteria vc=new ViewCriteria();
        vc.setConjunction(Conjunction.AND);
        
        ViewCriteriaRow vcr=new ViewCriteriaRow();
        vcr.setConjunction(Conjunction.AND);
        vcr.setUpperCaseCompare(false);
        
        ViewCriteriaItem vci=new ViewCriteriaItem();
        vci.setConjunction(Conjunction.AND);
        vci.setUpperCaseCompare(false);
        vci.setAttribute(SOAP_PARAM_ISACTIVE1);
        vci.setOperator(EQUAL_SIGN);
        vci.getValue().add("1");
              
        vcr.getItem().add(vci);
        
        vc.getGroup().add(vcr);
        criteria.setFilter(vc);
        criteria.getFindAttribute().add(SOAP_EMIRATE_ID);
        criteria.getFindAttribute().add(SOAP_EMIRATE_EN);
        criteria.getFindAttribute().add(SOAP_EMIRATE_AR);
        criteria.setExcludeAttribute(false);



		
		List<EmirateLookupsViewSDO> emirateList = null;
		try{
			   service = new AppModuleService_Service();
				stub = service.getAppModuleServiceSoapHttpPort();
			 emirateList = stub.findEmirateLookupsView1(criteria, findControl);
		}catch (Exception e) {
			throw new UAQException("findEmirateLookupsView Service Execution Failed");
		}
		

		Map<String, Map<String, String>> emirateEN_AR = new HashMap<String, Map<String, String>>();

		Map<String, String> emirate_EN = new HashMap<String, String>();
		Map<String, String> emirate_AR = new HashMap<String, String>();
		if(emirateList!=null){
			for (int i = 0; i < emirateList.size(); i++) {

				EmirateLookupsViewSDO emirate = emirateList.get(i);

				emirate_EN.put(emirate.getEmirateId().toString(), emirate.getEmiratenameEn().getValue());
				emirate_AR.put(emirate.getEmirateId().toString(), emirate.getEmiratenameAr().getValue());

			}
		}
		

		emirateEN_AR.put(LANG_ARABIC, emirate_AR);
		emirateEN_AR.put(LANG_ENGLISH, emirate_EN);
		return emirateEN_AR;
	}


	public Map<String, Map<String, String>> getTradeLicenceAR_EN() throws ServiceException, UAQException {

		FindCriteria criteria = new FindCriteria();
		criteria.setFetchSize(-1);
		criteria.setFetchStart(0);
		FindControl findControl = new FindControl();
		
		 ViewCriteria vc=new ViewCriteria();
	        vc.setConjunction(Conjunction.AND);
	        
	        ViewCriteriaRow vcr=new ViewCriteriaRow();
	        vcr.setConjunction(Conjunction.AND);
	        vcr.setUpperCaseCompare(false);
	        
	        ViewCriteriaItem vci=new ViewCriteriaItem();
	        vci.setConjunction(Conjunction.AND);
	        vci.setUpperCaseCompare(false);
	        vci.setAttribute(SOAP_PARAM_ISACTIVE);
	        vci.setOperator(EQUAL_SIGN);
	        vci.getValue().add("1");
	              
	        vcr.getItem().add(vci);
	        
	        vc.getGroup().add(vcr);
	        criteria.setFilter(vc);
	        criteria.getFindAttribute().add(SOAP_TRAIDLICENE_ID);
	        criteria.getFindAttribute().add(SOAP_TRAIDLICENE_EN);
	        criteria.getFindAttribute().add(SOAP_TRAIDLICENE_AR);
	        criteria.setExcludeAttribute(false);

		
		List<TradelicensetypeLookupsViewSDO> tradelicensetypeLookups =null;
		try{
			service = new AppModuleService_Service();
			stub = service.getAppModuleServiceSoapHttpPort();
			tradelicensetypeLookups = stub.findTradelicensetypeLookupsView1(criteria, findControl);
		}catch (Exception e) {
			throw new UAQException("findTradelicensetypeLookupsView Service Execution Failed");
		}
		

		Map<String, Map<String, String>> tradeliceneEN_AR = new HashMap<String, Map<String, String>>();

		Map<String, String> tradelicene_EN = new HashMap<String, String>();
		Map<String, String> tradelicene_AR = new HashMap<String, String>();

		if(tradelicensetypeLookups !=null){
			for (int i = 0; i < tradelicensetypeLookups.size(); i++) {

				TradelicensetypeLookupsViewSDO tradelicene = tradelicensetypeLookups.get(i);

				tradelicene_EN.put(tradelicene.getTradelicensetypeid().toString(), tradelicene.getTradelicensetypeEn().getValue());
				tradelicene_AR.put(tradelicene.getTradelicensetypeid().toString(), tradelicene.getTradelicensetypeAr().getValue());
			}
		}
		

		tradeliceneEN_AR.put(LANG_ARABIC, tradelicene_AR);
		tradeliceneEN_AR.put(LANG_ENGLISH, tradelicene_EN);

		return tradeliceneEN_AR;
	}

	public Map<String, String> getAddressedTOValues(String locale) throws ServiceException{
		
		Map<String, String> addressedTOMap = new LinkedHashMap<String, String>();
		FindCriteria findCriteria = new FindCriteria();
		FindControl findControl = new FindControl();


		service = new AppModuleService_Service();
		stub = service.getAppModuleServiceSoapHttpPort();

		findCriteria.setFetchStart(0);
		findCriteria.setFetchSize(-1);
		findCriteria.getFindAttribute().add(SOAP_ADDRESTO_ID);
		findCriteria.getFindAttribute().add(SOAP_ADDRESTO_EN);
		findCriteria.getFindAttribute().add(SOAP_ADDRESTO_AR);
		findCriteria.setExcludeAttribute(false);
		
		findControl.setRetrieveAllTranslations(false);

		
		List<TwimcAddressedtoLookupViewSDO> tWIMCaddressedToList=null;
		try {
			tWIMCaddressedToList = stub.findTwimcAddressedtoLookupView1(findCriteria, findControl);
		} catch (uaq.db.si.model.common.ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(TwimcAddressedtoLookupViewSDO listValues : tWIMCaddressedToList ){
			if(locale.equals(LANG_ENGLISH)){
				addressedTOMap.put(listValues.getAddressedtoId().toString(), listValues.getAddressedtoEn().getValue());
			}else{
				addressedTOMap.put(listValues.getAddressedtoId().toString(), listValues.getAddressedtoAr().getValue());
			}
		}
		
		return addressedTOMap;
	}
}