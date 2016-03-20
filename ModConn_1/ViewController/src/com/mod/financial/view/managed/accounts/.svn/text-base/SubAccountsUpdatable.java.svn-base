package com.mod.financial.view.managed.accounts;

import com.mod.financial.view.utils.ADFUtils;

import com.mod.financial.view.utils.JSFUtils;

import com.mod.financial.view.utils.UserInfo;

import java.util.Map;

import javax.faces.event.ValueChangeEvent;

import oracle.adf.model.BindingContext;
import oracle.adf.view.rich.context.AdfFacesContext;

import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

public class SubAccountsUpdatable {
    public SubAccountsUpdatable() {
    }

    public void OnCheckChanged(ValueChangeEvent valueChangeEvent) {
//        if (valueChangeEvent.getNewValue() != null) 
//            if (valueChangeEvent.getNewValue().equals("Y")){
//                UserInfo userInfo =(UserInfo) JSFUtils.resolveExpression("#{userInfo}");
//                ADFUtils.setBoundAttributeValue("ConvertDebitUser", userInfo.getUserName());
//            }
//            else{
//                ADFUtils.setBoundAttributeValue("ConvertDebitUser", null);
//            }
//        
   }

    public BindingContainer getBindings() {
        return BindingContext.getCurrent().getCurrentBindingsEntry();
    }

    public String back_action() {
        ADFUtils.cancelChangesCurrentRow("AccSubAccountsViewIterator");
            return "back";
    
    }
    public String cancel_action() {
        int  operator =ADFUtils.getEntityStatus("AccSubAccountsViewIterator");
        ADFUtils.cancelChangesCurrentRow("AccSubAccountsViewIterator");
        if (operator == 0 ){
            return "back";
        }
        return null;
    }


}
