package com.mod.financial.view.managed.administration;

import com.mod.financial.view.utils.ADFUtils;

import com.mod.financial.view.utils.JSFUtils;

import java.util.Map;

import oracle.adf.model.BindingContext;

import oracle.adf.view.rich.context.AdfFacesContext;

import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

public class UsersUpdatable {
    public UsersUpdatable() {
    }

    public BindingContainer getBindings() {
        return BindingContext.getCurrent().getCurrentBindingsEntry();
    }

    public String cancel_action() {
    int operator = ADFUtils.getEntityStatus("AdmUsersViewIterator");
    ADFUtils.cancelChangesCurrentRow("AdmUsersViewIterator");
        if (operator == 0 ){
            return "back";
        }
        return null;
    }
    
    public String back_action() {
        ADFUtils.cancelChangesCurrentRow("AdmUsersViewIterator");
            return "back";
   
    }

}
