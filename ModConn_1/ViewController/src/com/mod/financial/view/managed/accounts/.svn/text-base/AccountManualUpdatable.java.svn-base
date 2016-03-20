package com.mod.financial.view.managed.accounts;

import com.mod.financial.view.utils.ADFUtils;

import com.mod.financial.view.utils.JSFUtils;

import java.util.Map;

import oracle.adf.model.BindingContext;

import oracle.adf.view.rich.context.AdfFacesContext;

import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

public class AccountManualUpdatable {
    public AccountManualUpdatable() {
    }

    public BindingContainer getBindings() {
        return BindingContext.getCurrent().getCurrentBindingsEntry();
    }

    public String cancel_action() {
       int operator = ADFUtils.getEntityStatus("AccAccountsViewIterator");
      ADFUtils.cancelChangesCurrentRow("AccAccountsViewIterator");
        if (operator == 0 ){
            return "back";
        }
        return null;
    }
    
    public String back_action() {
        ADFUtils.cancelChangesCurrentRow("AccAccountsViewIterator");
            return "back";
    
    }

    public String copyAccount_action() {
       Object result=  ADFUtils.findOperation("copyAccount").execute();
        return null;
    }

}
