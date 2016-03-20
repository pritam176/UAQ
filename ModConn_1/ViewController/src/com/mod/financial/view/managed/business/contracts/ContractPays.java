package com.mod.financial.view.managed.business.contracts;

import com.mod.financial.view.utils.ADFUtils;

public class ContractPays {
    private String iterName = "AccContractPaysViewIterator";
    public ContractPays() {
    }

    public String paysReturnAction() {
        ADFUtils.cancelChangesCurrentRow(iterName);
        return "back";
    }
    
    public String paysCancelAction() {
        int operator = ADFUtils.getEntityStatus(iterName);
        ADFUtils.cancelChangesCurrentRow(iterName);
        if (operator == 0) {
            return "back";
        }
        return null;
    }
    
    public String doPayAction() {
        
        return null;
    }
    
    public void initContractPays() {
        ADFUtils.setEL("#{pageFlowScope.pageTypeFlag}", "pays");
    }
}
