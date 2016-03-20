package com.mod.financial.view.backing.fragments.common;

import com.mod.financial.view.utils.ADFUtils;

import com.mod.financial.view.utils.JSFUtils;

import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.context.AdfFacesContext;
import oracle.adf.view.rich.event.DialogEvent;
import oracle.adf.view.rich.event.PopupCanceledEvent;

public class LookupsDT {
    private RichPopup formPopup;
    private RichPopup deletePopup;
    private RichTable detailTable;

    public void setFormPopup(RichPopup formPopup) {
        this.formPopup = formPopup;
    }

    public RichPopup getFormPopup() {
        return formPopup;
    }

    public void setDeletePopup(RichPopup deletePopup) {
        this.deletePopup = deletePopup;
    }

    public RichPopup getDeletePopup() {
        return deletePopup;
    }

    public String createNew() {
        ADFUtils.findOperation("CreateInsert").execute();
        formPopup.show(new RichPopup.PopupHints());
        return null;
    }

    public void saveListener(DialogEvent dialogEvent) {
        ADFUtils.findOperation("Commit").execute();
        AdfFacesContext.getCurrentInstance().addPartialTarget(JSFUtils.findComponentInRoot("t2"));
    }

    public void deleteListener(DialogEvent dialogEvent) {
        ADFUtils.findOperation("Delete").execute();
        ADFUtils.findOperation("Commit").execute();
        AdfFacesContext.getCurrentInstance().addPartialTarget(detailTable);
    }

    public void cancelListener(PopupCanceledEvent popupCanceledEvent) {
        ADFUtils.cancelChangesCurrentRow("AdmLookupDtViewIterator");
    }

    public void setDetailTable(RichTable detailTable) {
        this.detailTable = detailTable;
    }

    public RichTable getDetailTable() {
        return detailTable;
    }
}
