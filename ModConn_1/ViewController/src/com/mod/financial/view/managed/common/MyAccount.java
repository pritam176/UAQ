package com.mod.financial.view.managed.common;

import com.mod.financial.view.utils.ADFUtils;
import com.mod.financial.view.utils.JSFUtils;
import com.mod.financial.view.utils.UserInfo;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;

import oracle.adf.view.rich.component.rich.nav.RichCommandButton;
import oracle.adf.view.rich.context.AdfFacesContext;

import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

import oracle.ui.pattern.dynamicShell.Tab;
import oracle.ui.pattern.dynamicShell.TabContext;

public class MyAccount {
    private RichCommandButton saveBtn;

    public MyAccount() {
    }

    public String save_action() {
        UserInfo userInfo =
            (UserInfo)JSFUtils.resolveExpression("#{userInfo}") ;
        JSFUtils.setExpressionValue("#{bindings.Language.inputValue}",
                                    userInfo.getLanguage());
        JSFUtils.setExpressionValue("#{bindings.Theme.inputValue}",
                                    userInfo.getThemeName());
        ADFUtils.findOperation("Commit").execute();
        userInfo.changeLocal();
        TabContext context =
            (TabContext)AdfFacesContext.getCurrentInstance().getPageFlowScope().get("tabContext");
        Iterator<Tab> tabs = context.getTabs().iterator();
        while (tabs.hasNext()) {
            Tab tab = tabs.next();
            Map<String, Object> map = tab.getParameters();
            if (map != null) {
                for (Map.Entry <String,Object> entry  :   map.entrySet()){
                    String titleKey =(String) entry.getValue();
                    tab.setTitle(JSFUtils.getStringFromBundle(titleKey));
                }
            }
        }
        ADFUtils.refreshPage();
        return "home";
    }
}
