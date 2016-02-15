package com.uaq.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import uaq.db.si.model.common.AppModuleService;
import uaq.db.si.model.common.AppModuleService_Service;
import uaq.db.si.model.common.EgdSuppDetailsViewSDO;

import com.oracle.xmlns.adf.svc.errors.ServiceException;
import com.oracle.xmlns.adf.svc.types.Conjunction;
import com.oracle.xmlns.adf.svc.types.FindControl;
import com.oracle.xmlns.adf.svc.types.FindCriteria;
import com.oracle.xmlns.adf.svc.types.ViewCriteria;
import com.oracle.xmlns.adf.svc.types.ViewCriteriaItem;
import com.oracle.xmlns.adf.svc.types.ViewCriteriaRow;
import com.uaq.logger.UAQLogger;
import com.uaq.vo.EGDSupplierViewVO;
import com.uaq.vo.ReSubmiisionInputVO;

@Service
@Qualifier("eGDReSubmissionService")
public class EGDReSubmissionService {

	protected static UAQLogger logger = new UAQLogger(EGDReSubmissionService.class);

	AppModuleService_Service service;
	AppModuleService stub;
	FindCriteria findCriteria = new FindCriteria();
	FindControl findControl = new FindControl();

	public EGDSupplierViewVO findEgdSuppDetailsView(ReSubmiisionInputVO inputVO) {

		ViewCriteriaItem viewCriteriaItem = new ViewCriteriaItem();
		viewCriteriaItem.setConjunction(Conjunction.AND);
		viewCriteriaItem.setAttribute(inputVO.getAttributeName());
		viewCriteriaItem.setUpperCaseCompare(false);
		viewCriteriaItem.setOperator("=");
		viewCriteriaItem.getValue().add(inputVO.getAttributeValue());

		ViewCriteriaRow viewCriteriaRow = new ViewCriteriaRow();
		viewCriteriaRow.setConjunction(Conjunction.AND);
		viewCriteriaRow.getItem().add(viewCriteriaItem);

		com.oracle.xmlns.adf.svc.types.ViewCriteria nested = new ViewCriteria();
		nested.setConjunction(Conjunction.AND);

		ViewCriteria filter = new ViewCriteria();
		filter.setConjunction(Conjunction.AND);
		filter.getGroup().add(viewCriteriaRow);
		filter.getNested().add(nested);

		findControl.setRetrieveAllTranslations(true);
		findCriteria.setFilter(filter);
		findCriteria.setFetchSize(-1);
		findCriteria.setFetchStart(0);

		EGDSupplierViewVO ouputVO = new EGDSupplierViewVO();

		List<EgdSuppDetailsViewSDO> output = null;
		try {
			output = stub.findEgdSuppDetailsView1(findCriteria, findControl);

			if (output != null) {
				if (output.size() > 0) {
					ouputVO.setExecutionStatus("Success");
					EgdSuppDetailsViewSDO temp = output.get(0);

					ouputVO.setAccountId(temp.getAccountId().getValue());
					ouputVO.setActiveAc(temp.getActiveAc().getValue());
					ouputVO.setCreatedBy(temp.getCreatedBy().getValue());
					ouputVO.setEstablishmentName(temp.getEstablishmentName().getValue());
					ouputVO.setInactiveAc(temp.getInactiveAc().getValue());
					ouputVO.setModifiedBy(temp.getModifiedBy().getValue());
					ouputVO.setReqId(temp.getReqId());
					ouputVO.setRequestNo(temp.getRequestNo().getValue());
					ouputVO.setServiceId(temp.getServiceId().getValue());
					ouputVO.setStatus(temp.getStatus().getValue());
					ouputVO.setSuppCatgId(temp.getSuppCatgId().getValue());
					ouputVO.setSuppRegId(temp.getSuppRegId().getValue());
					ouputVO.setTradeLicenseNum(temp.getTradeLicenseNum().getValue());
					ouputVO.setTrdExpDate(temp.getTrdExpDate().getValue());

					logger.info("Success  |" + output.size());

				} else {
					ouputVO.setExecutionStatus("Failure");
					logger.info("Failure  | stub.findEgdSuppDetailsView1(findCriteria, findControl); return 0 elemnt");
				}

			} else {
				ouputVO.setExecutionStatus("Failure");
				logger.info("Failure  | stub.findEgdSuppDetailsView1(findCriteria, findControl); return null");
			}
		} catch (uaq.db.si.model.common.ServiceException e) {
			ouputVO.setExecutionStatus("Failure");
			logger.error("Failure  |" + e.getMessage());
		}
		return ouputVO;

	}

}
