/**
 * 
 */
package com.uaq.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import uaq.pymt.si.model.common.AppModuleService;
import uaq.pymt.si.model.common.AppModuleService_Service;
import uaq.pymt.si.model.common.EserviceFeeMatrixViewSDO;

import com.oracle.xmlns.adf.svc.errors.ServiceException;
import com.oracle.xmlns.adf.svc.types.Conjunction;
import com.oracle.xmlns.adf.svc.types.FindControl;
import com.oracle.xmlns.adf.svc.types.FindCriteria;
import com.oracle.xmlns.adf.svc.types.ViewCriteria;
import com.oracle.xmlns.adf.svc.types.ViewCriteriaItem;
import com.oracle.xmlns.adf.svc.types.ViewCriteriaRow;
import com.uaq.logger.UAQLogger;

/**
 * @author aajay
 *
 */
@Service(value= "paymentService")
public class PaymentService {
	
	protected static UAQLogger logger = new UAQLogger(PaymentService.class);
	
	AppModuleService_Service service;
	AppModuleService stub;
	
	 public String generateSearchCrietaria(String serviceId,String searchCriteria){
		 
		 return "";
	 }

	
	public List<EserviceFeeMatrixViewSDO> getPaymentFeeIdList(Map<String, String> paymentSearchCriteria){
		
		ViewCriteriaItem viewCriteriaItems=null;
		ViewCriteriaRow group =new ViewCriteriaRow();
		group.setConjunction(Conjunction.AND);
		group.setUpperCaseCompare(false);
		
		for (Map.Entry<String, String> entry : paymentSearchCriteria.entrySet()) {
			viewCriteriaItems=new ViewCriteriaItem();
			viewCriteriaItems.setAttribute(entry.getKey());
			viewCriteriaItems.setOperator("=");
			viewCriteriaItems.getValue().add(entry.getValue());
			viewCriteriaItems.setUpperCaseCompare(false);
			viewCriteriaItems.setConjunction(Conjunction.AND);
			group.getItem().add(viewCriteriaItems);
			
			logger.info("search attribute ::" + entry.getKey() +" value :: " +entry.getValue());
		}
		
		//logger.info("getting payment Fee Id for serviceID ::" + serviceId +" $feeTypeCode " +feeTypeCode);
		List<EserviceFeeMatrixViewSDO> paymentFeeId = null;
		
		/*ViewCriteriaItem serviceIdItem=new ViewCriteriaItem();
		serviceIdItem.setAttribute("ServiceId");
		serviceIdItem.setOperator("=");
		serviceIdItem.getValue().add(serviceId);
		serviceIdItem.setUpperCaseCompare(false);
		serviceIdItem.setConjunction(Conjunction.AND);
		
		ViewCriteriaItem feeTypeCodeItem=new ViewCriteriaItem();
		feeTypeCodeItem.setAttribute("FeeTypeCode");
		feeTypeCodeItem.setOperator("=");
		feeTypeCodeItem.getValue().add(feeTypeCode);
		feeTypeCodeItem.setUpperCaseCompare(false);
		feeTypeCodeItem.setConjunction(Conjunction.AND);
		
		ViewCriteriaRow group =new ViewCriteriaRow();
		group.setConjunction(Conjunction.AND);
		group.setUpperCaseCompare(false);
		
		group.getItem().add(serviceIdItem);
		group.getItem().add(feeTypeCodeItem);*/
		
		ViewCriteria filter=new ViewCriteria();
		filter.setConjunction(Conjunction.AND);
		filter.getGroup().add(group);
		

		
		FindCriteria criteria = new FindCriteria();
		criteria.setFetchSize(-1);
		criteria.setFetchStart(0);
		criteria.getFindAttribute().add("FeeId");
		criteria.setExcludeAttribute(false);
		
		criteria.setFilter(filter);
		
		FindControl findControl = new FindControl();
		findControl.setRetrieveAllTranslations(true);
		
		service = new AppModuleService_Service();
		stub = service.getAppModuleServiceSoapHttpPort();
		
		try {
			paymentFeeId =  stub.findEserviceFeeMatrixView1(criteria, findControl);
			logger.info("paymentFeeId list size is ::" + paymentFeeId.size());
		} catch (ServiceException e) {
			logger.info("ServiceException from findEserviceFeeMatrixView1 ", e.getMessage());
			
		}
		
		return paymentFeeId;
		
	}

}
