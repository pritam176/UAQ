package com.mod.financial.view.managed.common;

import com.mod.financial.view.utils.ADFUtils;
import com.mod.financial.view.utils.JSFUtils;
import com.mod.financial.view.utils.UserInfo;
import javax.faces.event.ValueChangeEvent;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.context.AdfFacesContext;

public class Inbox {
    private RichTable docsTable;

    public Inbox() {
    }

    public void onCreateDateChange(ValueChangeEvent valueChangeEvent) {
        try {
            ADFUtils.setBoundAttributeValue("pCreateDate",
                                            valueChangeEvent.getNewValue());
            searchInbox();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onRcvDateChange(ValueChangeEvent valueChangeEvent) {
        try {
            ADFUtils.setBoundAttributeValue("pReceiveDate",
                                            valueChangeEvent.getNewValue());
            searchInbox();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void searchInbox() {
        try {
            UserInfo userInfo =
                (UserInfo)JSFUtils.resolveExpression("#{userInfo}");
            ADFUtils.setBoundAttributeValue("P_USER_ID", userInfo.getUserId());
            ADFUtils.findOperation("ExecuteWithParams").execute();
            AdfFacesContext.getCurrentInstance().addPartialTarget(docsTable);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setDocsTable(RichTable docsTable) {
        this.docsTable = docsTable;
    }

    public RichTable getDocsTable() {
        return docsTable;
    }
}
