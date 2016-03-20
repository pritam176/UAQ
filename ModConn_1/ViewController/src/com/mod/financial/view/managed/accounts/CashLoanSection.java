package com.mod.financial.view.managed.accounts;

import com.mod.financial.view.utils.JSFUtils;

import oracle.adf.view.rich.component.rich.layout.RichPanelFormLayout;

import oracle.adf.view.rich.context.AdfFacesContext;

import org.apache.myfaces.trinidad.event.SelectionEvent;

public class CashLoanSection {
    private RichPanelFormLayout form;

    public CashLoanSection() {
    }

    public void OnTreeSelection(SelectionEvent selectionEvent) {
        Object value =
            JSFUtils.resolveMethodExpression("#{bindings.MasterAdmSectionsView.treeModel.makeCurrent}",
                                             Object.class,
                                             new Class[] { SelectionEvent.class },
                                             new Object[] { selectionEvent });  
      AdfFacesContext.getCurrentInstance().addPartialTarget(form);
      AdfFacesContext.getCurrentInstance().addPartialTarget(JSFUtils.findComponentInRoot("t3"));
    }

    public void setForm(RichPanelFormLayout form) {
        this.form = form;
    }

    public RichPanelFormLayout getForm() {
        return form;
    }
}
