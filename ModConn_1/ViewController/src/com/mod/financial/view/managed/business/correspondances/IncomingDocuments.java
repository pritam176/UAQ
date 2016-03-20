package com.mod.financial.view.managed.business.correspondances;

import com.mod.financial.view.utils.ADFUtils;

public class IncomingDocuments {
    public IncomingDocuments() {
    }

    public void setPageTypeFlag() {
        try {
            ADFUtils.setEL("#{pageFlowScope.pageTypeFlag}", "add");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
