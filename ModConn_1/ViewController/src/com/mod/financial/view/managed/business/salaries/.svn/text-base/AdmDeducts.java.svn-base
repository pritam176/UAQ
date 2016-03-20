package com.mod.financial.view.managed.business.salaries;

import com.mod.financial.view.utils.ADFUtils;

import com.mod.financial.view.utils.JSFUtils;

import java.math.BigDecimal;

import java.util.Map;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import oracle.adf.view.rich.context.AdfFacesContext;

import oracle.binding.OperationBinding;

import oracle.jbo.domain.Number;

public class AdmDeducts {
    public AdmDeducts() {
    }

    public void saveDeduct(ActionEvent actionEvent) {
        try {
            OperationBinding oper =
                ADFUtils.findOperation("callCalculateDeductFlagProcedure");
            Map paramsMap = oper.getParamsMap();
            paramsMap.put("flags", ADFUtils.getBoundAttributeValue("Flags"));
            paramsMap.put("incInInsRep",
                          ADFUtils.getBoundAttributeValue("IncInInsRep"));
            paramsMap.put("qrterLimited",
                          ADFUtils.getBoundAttributeValue("QrterLimited"));
            paramsMap.put("govDebt",
                          ADFUtils.getBoundAttributeValue("GovDebt"));
            paramsMap.put("installmentType",
                          ADFUtils.getBoundAttributeValue("InstallmentType"));
            BigDecimal resultFlags = (BigDecimal)oper.execute();
            ADFUtils.setBoundAttributeValue("Flags", resultFlags);
            ADFUtils.findOperation("Commit").execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
