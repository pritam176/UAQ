package com.mod.financial.view.managed.common;

import com.mod.financial.view.utils.ADFUtils;
import com.mod.financial.view.utils.JSFUtils;

import java.sql.SQLException;

import java.util.HashMap;
import java.util.Iterator;

import java.util.List;

import java.util.Map;

import javax.el.ELContext;
import javax.el.ExpressionFactory;

import javax.el.MethodExpression;

import javax.faces.application.Application;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import javax.faces.event.MethodExpressionActionListener;
import javax.faces.event.PhaseEvent;

import javax.faces.event.PhaseId;

import oracle.adf.controller.TaskFlowId;

import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.layout.RichPanelBorderLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelStretchLayout;
import oracle.adf.view.rich.component.rich.nav.RichCommandImageLink;
import oracle.adf.view.rich.component.rich.nav.RichCommandNavigationItem;

import oracle.adf.view.rich.component.rich.nav.RichNavigationPane;

import oracle.adf.view.rich.component.rich.output.RichOutputText;
import oracle.adf.view.rich.context.AdfFacesContext;
import oracle.adf.view.rich.event.ItemEvent;

import oracle.adf.view.rich.event.PopupCanceledEvent;

import oracle.adf.view.rich.render.ClientEvent;

import oracle.ui.pattern.dynamicShell.Tab;
import oracle.ui.pattern.dynamicShell.TabContext;

public class Launcher {


    public Launcher() {

    }

    public void closeCurrentActivity(ActionEvent actionEvent) {
        TabContext tabContext = TabContext.getCurrentInstance();
        int tabIndex = tabContext.getSelectedTabIndex();
        if (tabIndex != -1) {
            tabContext.removeTab(tabIndex);
        }
    }

    public void currentTabDirty(ActionEvent e) {
        /**
          * When called, marks the current tab "dirty". Only at the View level
          * is it possible to mark a tab dirty since the model level does not
          * track to which tab data belongs.
          */
        TabContext tabContext = TabContext.getCurrentInstance();
        tabContext.markCurrentTabDirty(true);
    }

    public void currentTabClean(ActionEvent e) {
        TabContext tabContext = TabContext.getCurrentInstance();
        tabContext.markCurrentTabDirty(false);
    }

    private void _launchActivity(String title, String taskflowId,
                                 boolean newTab) {
        try {
            if (newTab) {
                TabContext.getCurrentInstance().addTab(title, taskflowId);
            } else {
                TabContext.getCurrentInstance().addOrSelectTab(title,
                                                               taskflowId);
            }
        } catch (TabContext.TabOverflowException toe) {
            // causes a dialog to be displayed to the user saying that there are
            // too many tabs open - the new tab will not be opened...
            toe.handleDefault();
        }
    }
    
    private void _launchActivity(String title, String taskflowId,
                                 boolean newTab ,Map<String,Object> param) {
        try {
            if (newTab) {
                TabContext.getCurrentInstance().addTab(title, taskflowId,param);
            } else {
                TabContext.getCurrentInstance().addOrSelectTab(title,
                                                               taskflowId,param);
            }
        } catch (TabContext.TabOverflowException toe) {
            // causes a dialog to be displayed to the user saying that there are
            // too many tabs open - the new tab will not be opened...
            toe.handleDefault();
        }
    }

    public void launchFirstReplaceNPlace(ActionEvent actionEvent) {
        TabContext tabContext = TabContext.getCurrentInstance();
        try {
            tabContext.setMainContent("/WEB-INF/flows/first.xml#first");
        } catch (TabContext.TabContentAreaDirtyException toe) {
            // TODO: warn user TabContext api needed for this use case.
        }
    }

    // Created by Amr Ahmed 
    public String onMyAccount() {
        Map<String, Object> params = new HashMap();
        params.put("titleKey", "common_link_myAccount");
        _launchActivity(JSFUtils.getStringFromBundle("common_link_myAccount"),
                        "/WEB-INF/taskflows/common/myAccount-Task.xml#myAccount-Task",
                        false,params);
        return null;
    }
    
    
    // Created by Amr Ahmed 
    public String open_Favorites () {
        Map<String, Object> params = new HashMap();
        params.put("titleKey", "common_label_fav");
        _launchActivity(JSFUtils.getStringFromBundle("common_label_fav"),
                        "/WEB-INF/taskflows/common/myFavorites-Task.xml#myFavorites-Task",
                        false,params);

        return null;
    }
    // Created by Amr Ahmed 
    public String onInbox() {
        Map<String, Object> params = new HashMap();
        params.put("titleKey", "common_label_myTasks");
        _launchActivity(JSFUtils.getStringFromBundle("common_label_myTasks"),
                        "/WEB-INF/taskflows/correspondance/inbox-Task.xml#inbox-Task",
                        false,params);

        return null;
    }

    // Created by Amr Ahmed 
    public void cancelTabDirty() {
        TabContext context = TabContext.getCurrentInstance();
        RichPanelStretchLayout area = context.getContentArea();
        RichPanelBorderLayout children = (RichPanelBorderLayout)area.getTop();
        RichCommandImageLink component =
            (RichCommandImageLink)children.getEnd();
        FacesContext facesContext = FacesContext.getCurrentInstance();
        Application app = facesContext.getApplication();
        ExpressionFactory elFactory = app.getExpressionFactory();
        ELContext elContext = facesContext.getELContext();
        MethodExpression methodExpression = 
        elFactory.createMethodExpression(elContext, "#{viewScope.launcherBean.tabRemoveListener}",  Object.class,new Class[] {ActionEvent.class});
        //component.setActionListener(methodExpression);
       // component.setActionExpression(methodExpression);
       MethodExpressionActionListener actionListener = null;
       actionListener = new MethodExpressionActionListener(methodExpression);
       //add listener to the commandButton instance
       component.addActionListener(actionListener); 
        
    }
    // Created by Amr Ahmed 
    public void tabRemoveListener(ActionEvent itemEvent) {
        TabContext.getCurrentInstance().removeCurrentTab();
        ADFUtils.findOperation("Rollback").execute();
    }

    // Created by Amr Ahmed 
    public void displayCloseIconTab() {
        TabContext context = TabContext.getCurrentInstance();
        RichPanelStretchLayout area = context.getContentArea();
        RichPanelBorderLayout children = (RichPanelBorderLayout)area.getTop();
        RichNavigationPane  navPane  = (RichNavigationPane)children.getChildren().get(0);
        navPane.setItemRemoval("all");
        RichCommandNavigationItem component = (RichCommandNavigationItem) navPane.getNodeStamp();
        FacesContext facesContext = FacesContext.getCurrentInstance();
        Application app = facesContext.getApplication();
        ExpressionFactory elFactory = app.getExpressionFactory();
        ELContext elContext = facesContext.getELContext();
        MethodExpression methodExpression = 
        elFactory.createMethodExpression(elContext, "#{viewScope.launcherBean.tabRemoveListener}",  Object.class,new Class[] {ItemEvent.class});
        component.setItemListener(methodExpression);

    }

  
    // Created by Amr Ahmed 
    public void openInbox(PhaseEvent phaseEvent) {
        Map<String, Object> flowScope = AdfFacesContext.getCurrentInstance().getPageFlowScope();
        String userLogin =(String)flowScope.get("userLogin");
        if ( phaseEvent.getPhaseId() != null){ 
        if (phaseEvent.getPhaseId().equals(PhaseId.RENDER_RESPONSE) &&
            TabContext.getCurrentInstance().getSelectedTabIndex() == -1 && userLogin.equals("Sucess") ) {
            Map<String, Object> params = new HashMap();
            params.put("titleKey", "common_label_myTasks");
//            params.put("menuId", "70");
            _launchActivity(JSFUtils.getStringFromBundle("common_label_myTasks"),
                            "WEB-INF/taskflows/correspondance/inbox-Task.xml#inbox-Task",
                            false,params);
          //  cancelTabDirty();
            flowScope.put("userLogin", "fail");
        }
        }
    }
    
    // Created by Amr Ahmed 
    //refresh inbox when login
    public void refresh(ClientEvent clientEvent) {
        AdfFacesContext.getCurrentInstance().addPartialTarget(JSFUtils.findComponentInRoot("pt_region0"));
    }

    public String displayDetailMenu_action() throws SQLException {
        TabContext context = TabContext.getCurrentInstance();
        List<Tab> list = context.getTabs();
        Tab tabSelected = list.get(context.getSelectedTabIndex());
        Map<String, Object> params = tabSelected.getParameters();
        String menu = (String)  params.get("menuId");
        if (menu != null ){
        oracle.jbo.domain.Number menuId = new oracle.jbo.domain.Number(menu);
        ADFUtils.findOperation("ExecuteWithParams").getParamsMap().put("bindMenuId", menuId);
        ADFUtils.findOperation("ExecuteWithParams").execute();
        ((RichPopup)JSFUtils.findComponentInRoot("detailMenuPoup")).show(new RichPopup.PopupHints());
        }
        
        return null;
    }
}
