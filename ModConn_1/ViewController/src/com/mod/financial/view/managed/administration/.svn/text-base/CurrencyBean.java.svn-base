package com.mod.financial.view.managed.administration;

import com.mod.financial.view.utils.ADFUtils;
import com.mod.financial.view.utils.JSFUtils;

import javax.faces.component.UIComponent;

import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.context.AdfFacesContext;
import oracle.adf.view.rich.event.DialogEvent;
import oracle.adf.view.rich.event.PopupCanceledEvent;

public class CurrencyBean {
    private RichPopup currencyPopup;

    public CurrencyBean() {
    }

    public String create_action() {
       ADFUtils.findOperation("CreateInsert").execute();
        getCurrencyPopup().show(new RichPopup.PopupHints());
        return null;
    }

    public void deleteServicesDialogListener(DialogEvent dialogEvent) {
        ADFUtils.findOperation("Delete").execute();
        ADFUtils.findOperation("Commit").execute();
        AdfFacesContext.getCurrentInstance().addPartialTarget(JSFUtils.findComponentInRoot("resId1"));
    }

    public void saveListener(DialogEvent dialogEvent) {
        ADFUtils.findOperation("Commit").execute();
        AdfFacesContext.getCurrentInstance().addPartialTarget(JSFUtils.findComponentInRoot("resId1"));
    }

    public void cancelListener(PopupCanceledEvent popupCanceledEvent) {
        ADFUtils.findOperation("Rollback").execute();
        AdfFacesContext.getCurrentInstance().addPartialTarget(JSFUtils.findComponentInRoot("resId1"));
    }

    public void setCurrencyPopup(RichPopup currencyPopup) {
        this.currencyPopup = currencyPopup;
    }

    public RichPopup getCurrencyPopup() {
        return currencyPopup;
    }
}
