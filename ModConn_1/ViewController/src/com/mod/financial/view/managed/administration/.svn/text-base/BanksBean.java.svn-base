package com.mod.financial.view.managed.administration;

import com.mod.financial.view.utils.ADFUtils;
import com.mod.financial.view.utils.JSFUtils;

import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.context.AdfFacesContext;
import oracle.adf.view.rich.event.DialogEvent;
import oracle.adf.view.rich.event.PopupCanceledEvent;

public class BanksBean {
    private RichPopup banksPopup;

    public BanksBean() {
    }

    public String create_action() {
       ADFUtils.findOperation("CreateInsert").execute();
       getBanksPopup().show(new RichPopup.PopupHints());
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
        //ADFUtils.findOperation("Rollback").execute();
        ADFUtils.cancelChangesCurrentRow("AdmBanksViewIterator");
    //    AdfFacesContext.getCurrentInstance().addPartialTarget(JSFUtils.findComponentInRoot("resId1"));
    }

    public void setBanksPopup(RichPopup banksPopup) {
        this.banksPopup = banksPopup;
    }

    public RichPopup getBanksPopup() {
        return banksPopup;
    }
}
