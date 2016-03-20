package com.mod.financial.view.managed.business.correspondances;

import com.mod.financial.view.utils.ADFUtils;
import com.mod.financial.view.utils.JSFUtils;

import oracle.adf.view.rich.context.AdfFacesContext;
import oracle.adf.view.rich.event.DialogEvent;

public class OutboundDocs {
    public OutboundDocs() {
    }

    public void deleteMasterDialogListener(DialogEvent dialogEvent) {
        try {
            ADFUtils.findOperation("DeleteMaster").execute();
            ADFUtils.findOperation("Commit").execute();
            AdfFacesContext.getCurrentInstance().addPartialTarget(JSFUtils.findComponentInRoot("masterT1"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
