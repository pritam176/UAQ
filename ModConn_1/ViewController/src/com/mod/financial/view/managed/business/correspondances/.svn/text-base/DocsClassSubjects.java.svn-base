package com.mod.financial.view.managed.business.correspondances;

import com.mod.financial.view.utils.ADFUtils;
import com.mod.financial.view.utils.JSFUtils;

import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.context.AdfFacesContext;
import oracle.adf.view.rich.event.DialogEvent;
import oracle.adf.view.rich.event.PopupCanceledEvent;

import oracle.ui.pattern.dynamicShell.Tab;
import oracle.ui.pattern.dynamicShell.TabContext;

public class DocsClassSubjects {
    private RichPopup attachPopup;
    private RichPopup detailPopup;
    
    public DocsClassSubjects() {
    }

    public String createAttachAction() {
       ADFUtils.findOperation("InsertAttach").execute();
       getAttachPopup().show(new RichPopup.PopupHints());
        return null;
    }

    public void deleteAttachDialogListener(DialogEvent dialogEvent) {
        ADFUtils.findOperation("DeleteAttach").execute();
        ADFUtils.findOperation("Commit").execute();
        AdfFacesContext.getCurrentInstance().addPartialTarget(JSFUtils.findComponentInRoot("attachT1"));
    }

    public void saveListener(DialogEvent dialogEvent) {
        ADFUtils.findOperation("Commit").execute();
        AdfFacesContext.getCurrentInstance().addPartialTarget(JSFUtils.findComponentInRoot("attachT1"));
        AdfFacesContext.getCurrentInstance().addPartialTarget(JSFUtils.findComponentInRoot("detailT1"));
    }

    public void cancelAttachListener(PopupCanceledEvent popupCanceledEvent) {
        ADFUtils.cancelChangesCurrentRow("CorDocAttachView1Iterator");
        AdfFacesContext.getCurrentInstance().addPartialTarget(JSFUtils.findComponentInRoot("attachT1"));
    }

    public void cancelDetailListener(PopupCanceledEvent popupCanceledEvent) {
        ADFUtils.cancelChangesCurrentRow("CorDocDetailView1Iterator");
        AdfFacesContext.getCurrentInstance().addPartialTarget(JSFUtils.findComponentInRoot("detailT1"));
    }
    
    public String createDetailAction() {
       ADFUtils.findOperation("InsertDetail").execute();
       getDetailPopup().show(new RichPopup.PopupHints());
        return null;
    }

    public void deleteDetailDialogListener(DialogEvent dialogEvent) {
        ADFUtils.findOperation("DeleteDetail").execute();
        ADFUtils.findOperation("Commit").execute();
        AdfFacesContext.getCurrentInstance().addPartialTarget(JSFUtils.findComponentInRoot("detailT1"));
    }
    
    public String createMasterDocAction() {
       ADFUtils.findOperation("InsertDocument").execute();
       getDetailPopup().show(new RichPopup.PopupHints());
        return null;
    }

    public void deleteMasterDialogListener(DialogEvent dialogEvent) {
        ADFUtils.findOperation("DeleteMaster").execute();
        ADFUtils.findOperation("Commit").execute();
        AdfFacesContext.getCurrentInstance().addPartialTarget(JSFUtils.findComponentInRoot("detailT1"));
    }

    public void setAttachPopup(RichPopup attachPopup) {
        this.attachPopup = attachPopup;
    }

    public RichPopup getAttachPopup() {
        return attachPopup;
    }

    public void setDetailPopup(RichPopup detailPopup) {
        this.detailPopup = detailPopup;
    }

    public RichPopup getDetailPopup() {
        return detailPopup;
    }

    public TabContext getContext() {
        return (TabContext)AdfFacesContext.getCurrentInstance().getPageFlowScope().get("tabContext");
    }

    public void setPageTypeFlag() {
        try {
                ADFUtils.setEL("#{pageFlowScope.pageTypeFlag}", "edit");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String afterSaveProcessing() {
        try {
            ADFUtils.setBoundAttributeValue("HdId", ADFUtils.getBoundAttributeValue("Id1").toString());
            ADFUtils.findOperation("Commit").execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String cancelDocChanges() {
        try {
            ADFUtils.cancelChangesCurrentRow("CorDocMasterView1Iterator");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String distributeDocs() {
        // Add event code here...
        return null;
    }

    public String cancelDocsDistribution() {
        // Add event code here...
        return null;
    }
}
