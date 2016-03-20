package com.mod.financial.view.managed.business.correspondances;

import com.mod.financial.view.utils.ADFUtils;

import com.mod.financial.view.utils.JSFUtils;
import com.mod.financial.view.utils.UserInfo;

import java.util.Map;

import javax.faces.component.ContextCallback;
import javax.faces.component.UIComponent;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

import oracle.adf.model.binding.DCIteratorBinding;

import oracle.adf.view.rich.component.rich.RichQuery;
import oracle.adf.view.rich.model.QueryDescriptor;
import oracle.adf.view.rich.model.QueryModel;

import oracle.binding.OperationBinding;

import oracle.jbo.Row;
import oracle.jbo.domain.Number;

public class AccCheckDelivery {
    public AccCheckDelivery() {
    }

    public void onDlevCheckChange(ValueChangeEvent valueChangeEvent) {
        try {
            if (valueChangeEvent != null) {
                DCIteratorBinding accCheckDelVIter =
                    ADFUtils.findIterator("AccCheckDeliveryViewRO1Iterator");
                if (accCheckDelVIter != null) {
                    Row currentCheckRow = accCheckDelVIter.getCurrentRow();
                    if (valueChangeEvent.getNewValue().toString().equals("true"))
                        currentCheckRow.setAttribute("CancelChk", "1");
                    else if (valueChangeEvent.getNewValue().toString().equals("false"))
                        currentCheckRow.setAttribute("CancelChk", "2");
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onCancelChkChange(ValueChangeEvent valueChangeEvent) {
        try {
            if (valueChangeEvent != null) {
                DCIteratorBinding accCheckDelVIter =
                    ADFUtils.findIterator("AccCheckDeliveryViewRO1Iterator");
                if (accCheckDelVIter != null) {
                    Row currentCheckRow = accCheckDelVIter.getCurrentRow();
                    if (valueChangeEvent.getNewValue().toString().equals("true"))
                        currentCheckRow.setAttribute("DlevCheck", "1");
                    else if (valueChangeEvent.getNewValue().toString().equals("false"))
                        currentCheckRow.setAttribute("DlevCheck", "2");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String executeAccCheckDelivery() {
        try {
            UserInfo userInfo =
                (UserInfo)JSFUtils.resolveExpression("#{userInfo}");
            DCIteratorBinding accCheckDelVIter =
                ADFUtils.findIterator("AccCheckDeliveryViewRO1Iterator");
            if (accCheckDelVIter != null) {
                Row currentCheckRow = accCheckDelVIter.getCurrentRow();
                OperationBinding operation =
                    ADFUtils.findOperation("callExecuteAccCheckDeliveryFunction");
                Map paramsMap = operation.getParamsMap();
                paramsMap.put("checkStatus",
                              currentCheckRow.getAttribute("CheckStatus"));
                paramsMap.put("delvCheck",
                              currentCheckRow.getAttribute("DlevCheck"));
                paramsMap.put("cancelChk",
                              currentCheckRow.getAttribute("CancelChk"));
                paramsMap.put("fiscalYear1",
                              currentCheckRow.getAttribute("FiscalYear1"));
                paramsMap.put("fiscalYear2",
                              currentCheckRow.getAttribute("FiscalYear2"));
                paramsMap.put("sheetId",
                              currentCheckRow.getAttribute("SheetId"));
                paramsMap.put("checkAmount",
                              currentCheckRow.getAttribute("CheckAmount"));
                paramsMap.put("accCheckId",
                              currentCheckRow.getAttribute("Id"));
                paramsMap.put("orgId", currentCheckRow.getAttribute("OrgId"));
                paramsMap.put("userId", userInfo.getUserId());
                paramsMap.put("lang", userInfo.getLanguage());
                String execMessage = (String)operation.execute();
                ADFUtils.findOperation("ExecuteWithParams").execute();
                JSFUtils.addFacesInformationMessage(execMessage);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
