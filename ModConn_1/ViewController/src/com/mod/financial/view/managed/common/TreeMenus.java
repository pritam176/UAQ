package com.mod.financial.view.managed.common;


import com.mod.financial.view.utils.ADFUtils;
import com.mod.financial.view.utils.JSFUtils;
import com.mod.financial.view.utils.QueueSend;
import com.mod.financial.view.utils.UserInfo;

import java.io.File;


import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.el.MethodExpression;

import javax.faces.application.Application;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import javax.jms.JMSException;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import javax.servlet.http.HttpServletRequest;

import oracle.adf.controller.TaskFlowId;

import oracle.jbo.domain.Number;
import oracle.adf.model.BindingContext;
import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.data.RichTree;
import oracle.adf.view.rich.context.AdfFacesContext;

import oracle.binding.BindingContainer;

import oracle.jbo.Key;
import oracle.jbo.Row;
import oracle.jbo.ViewObject;
import oracle.jbo.uicli.binding.JUCtrlHierBinding;
import oracle.jbo.uicli.binding.JUCtrlHierNodeBinding;

import oracle.ui.pattern.dynamicShell.Tab;
import oracle.ui.pattern.dynamicShell.TabContext;

import org.apache.myfaces.trinidad.event.SelectionEvent;
import org.apache.myfaces.trinidad.model.CollectionModel;
import org.apache.myfaces.trinidad.model.RowKeySet;
import org.apache.myfaces.trinidad.model.TreeModel;


public class TreeMenus {
    @SuppressWarnings("compatibility:3454546610421983633")
    private static final long serialVersionUID = 1L ;
    Row menuRow ;
    private RichTree tree;
    Row favRow;

    public TreeMenus() {
    }
    TabContext context;

    public void setContext(TabContext context) {
        this.context = context;
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

    public TabContext getContext() {
        return (TabContext)AdfFacesContext.getCurrentInstance().getPageFlowScope().get("tabContext");
    }

    private void _launchActivity(String title, String taskflowId,
                                 boolean newTab) {
        try {
            if (newTab) {
                getContext().addTab(title, taskflowId);
            } else {
                getContext().getCurrentInstance().addOrSelectTab(title,
                                                                 taskflowId);
            }
        } catch (TabContext.TabOverflowException toe) {
            // causes a dialog to be displayed to the user saying that there are
            // too many tabs open - the new tab will not be opened...
            toe.handleDefault();
        }
    }

    public void OnTreeSelection(SelectionEvent selectionEvent) {
        //original selection listener set by ADF
     
        String adfSelectionListener =
            "#{bindings.TreeMenusROView.treeModel.makeCurrent}";
        //make sure the default selection listener functionality is
        //preserved. you don't need to do this for multi select trees
        //as the ADF binding only supports single current row selection

        /* START PRESERVER DEFAULT ADF SELECT BEHAVIOR */
        FacesContext fctx = FacesContext.getCurrentInstance();
        Application application = fctx.getApplication();
        ELContext elCtx = fctx.getELContext();
        ExpressionFactory exprFactory = application.getExpressionFactory();

        MethodExpression me = null;
        me =
 exprFactory.createMethodExpression(elCtx, adfSelectionListener, Object.class,
                                    new Class[] { SelectionEvent.class });


        me.invoke(elCtx, new Object[] { selectionEvent });
    //    Map<String, String> map = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();

        /* END PRESERVER DEFAULT ADF SELECT BEHAVIOR */
        RichTree tree = (RichTree)selectionEvent.getSource();
        TreeModel model = (TreeModel)tree.getValue();

        //get selected nodes
        RowKeySet rowKeySet = selectionEvent.getAddedSet();
        Iterator rksIterator = rowKeySet.iterator();
        //for single select configurations,this only is called once
        while (rksIterator.hasNext()) {
            List key = (List)rksIterator.next();
            JUCtrlHierBinding treeBinding = null;
            CollectionModel collectionModel = (CollectionModel)tree.getValue();
            treeBinding = (JUCtrlHierBinding)collectionModel.getWrappedData();
            JUCtrlHierNodeBinding nodeBinding = null;
            nodeBinding = treeBinding.findNodeByKeyPath(key);
            menuRow = nodeBinding.getRow();
        }

    }

    private void sendJMS(String formUrl) {
        QueueSend qs = new QueueSend();
        InitialContext ic;

        try {
            HttpServletRequest req = ADFUtils.getReuqestObject();
            ic =
 QueueSend.getInitialContext("t3://" + req.getServerName() + ":" +
                             req.getServerPort());
            qs.init(ic, qs.QUEUE);
            qs.send(formUrl);
            qs.close();
        } catch (NamingException e) {
            e.printStackTrace();
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    private void processSelection(Row selectedRow) {
        try {
            String taskFlowPath = null;
            String titleAr = null;
            String titleEn = null;
            String titleKey = null;
            String formFlag = null;
            String formPath = null;
            String menuId = null;
            String reportId;
            formFlag =
                    selectedRow.getAttribute("FormFlag") != null ? selectedRow.getAttribute("FormFlag").toString() :
                    "N";
            taskFlowPath = (String)selectedRow.getAttribute("ActionString");
            titleAr = (String)selectedRow.getAttribute("ArMenuTitle");
            titleEn = (String)selectedRow.getAttribute("EnMenuTitle");
            titleKey =
                    selectedRow.getAttribute("TitleKey") != null ? selectedRow.getAttribute("TitleKey").toString() :
                    "";
            formPath =
                    selectedRow.getAttribute("FormCommand") != null ? selectedRow.getAttribute("FormCommand").toString() :
                    "";
            menuId =
                    selectedRow.getAttribute("MenuId") != null ? selectedRow.getAttribute("MenuId").toString() :
                    "";
            reportId = selectedRow.getAttribute("ReportId") != null ? selectedRow.getAttribute("ReportId").toString() :"";
        UserInfo userInfo =
            (UserInfo)JSFUtils.resolveExpression("#{userInfo}");
        List<Tab> tabsList = getContext().getTabs();
        boolean newTabFlag = true;
        int selectedIndex = getContext().getSelectedTabIndex();
        for (int index = 0; index < tabsList.size(); index++) {
            Tab currentTab = tabsList.get(index);
            Map<String, Object> tabParams = currentTab.getParameters();
            if (tabParams != null)
                if (!"".equals(menuId) && tabParams != null &&
                    menuId.equals(tabParams.get("menuId"))) {
                    selectedIndex = index;
                    newTabFlag = false;
                    break;
                }
        }
        Map<String, Object> params = new HashMap();
        if (formFlag.equalsIgnoreCase("N")) {
            if (!newTabFlag) {
                getContext().setSelectedTabIndex(selectedIndex);
            } else {
                if (!"".equals(titleKey)) {
                    _launchActivity(JSFUtils.getStringFromBundle(titleKey),
                                    taskFlowPath, true);
                } else if (userInfo.getLanguage().equals("ar")) {
                    _launchActivity(titleAr, taskFlowPath, true);
                } else {
                    _launchActivity(titleEn, taskFlowPath, true);
                }
                int index = getContext().getSelectedTabIndex();
                tabsList = getContext().getTabs();
                Tab newTab = tabsList.get(index);
                params.put("titleKey", titleKey);
                params.put("menuId", menuId);
                params.put("reportId", reportId);
                newTab.setParameters(params);
            }
        } else {
            if (formPath.equals("")) {
                RichPopup popup =
                    (RichPopup)JSFUtils.findComponentInRoot("formPathPopup");
                popup.show(new RichPopup.PopupHints());
            } else {
                formPath = formPath.replace(File.separator, "/");
                sendJMS(formPath);
            }
        }
        }
        catch (Exception e) {
            // TODO: Add catch code
            e.printStackTrace();
        }
    }

    public void OnUsersFav(SelectionEvent selectionEvent) {
        String adfSelectionListener =
            "#{bindings.AdmUsersFavoritesView.treeModel.makeCurrent}";
        //make sure the default selection listener functionality is
        //preserved. you don't need to do this for multi select trees
        //as the ADF binding only supports single current row selection

        /* START PRESERVER DEFAULT ADF SELECT BEHAVIOR */
        FacesContext fctx = FacesContext.getCurrentInstance();
        Application application = fctx.getApplication();
        ELContext elCtx = fctx.getELContext();
        ExpressionFactory exprFactory = application.getExpressionFactory();

        MethodExpression me = null;
        me =
 exprFactory.createMethodExpression(elCtx, adfSelectionListener, Object.class,
                                    new Class[] { SelectionEvent.class });


        me.invoke(elCtx, new Object[] { selectionEvent });

        /* END PRESERVER DEFAULT ADF SELECT BEHAVIOR */

        RichTree tree = (RichTree)selectionEvent.getSource();
        TreeModel model = (TreeModel)tree.getValue();

        //get selected nodes
        RowKeySet rowKeySet = selectionEvent.getAddedSet();
        Iterator rksIterator = rowKeySet.iterator();
        //for single select configurations,this only is called once
        while (rksIterator.hasNext()) {
            List key = (List)rksIterator.next();
            JUCtrlHierBinding treeBinding = null;
            CollectionModel collectionModel = (CollectionModel)tree.getValue();
            treeBinding = (JUCtrlHierBinding)collectionModel.getWrappedData();
            JUCtrlHierNodeBinding nodeBinding = null;
            nodeBinding = treeBinding.findNodeByKeyPath(key);
            favRow = nodeBinding.getRow();
           
    }
    }

    public void refreshShuttle(){
        TabContext context =
            (TabContext)AdfFacesContext.getCurrentInstance().getPageFlowScope().get("tabContext");
        Iterator<Tab> tabs = context.getTabs().iterator();
        while (tabs.hasNext()) {
            Tab tab = tabs.next();
            TaskFlowId flowId = tab.getTaskflowId();            
            if (tab.getTaskflowId().getLocalTaskFlowId().equals("myFavorites-Task")){
             Map<String, Object> map1 = tab.getParameters();
         if (map1 != null ){
            int size = map1.size();
            for (int i=0 ;i<size ;i++){
              String title=  (String)map1.get("titleKey");
              context.removeTab(tab.getIndex());
                Map<String, Object> titles = new HashMap();
                titles.put("titleKey", "common_label_fav");
                titles.put("menuId", ADFUtils.getBoundAttributeValue("MenuId1"));
                titles.put("reportId", ADFUtils.getBoundAttributeValue("ReportId"));
                _launchActivity(JSFUtils.getStringFromBundle(title), flowId.getFullyQualifiedName(),
                                             false,titles) ;
                        break;
            }
         }
                break;
            }
        }
    }

    public String createAndSaveFav_action() {
        Map map = ADFUtils.findOperation("Createwithparameters").getParamsMap();
        map.put("MenuId", menuRow.getAttribute("MenuId"));
        ADFUtils.findOperation("Createwithparameters").execute();
        ADFUtils.findOperation("Commit").execute();
        Row currentRow = ADFUtils.findIterator("AdmUsersFavoritesViewIterator").getCurrentRow();
        menuRow.setAttribute("FavId",currentRow.getAttribute("Id"));
        refreshShuttle();
        ADFUtils.refreshIterator("TreeMenusROViewIterator");
        ADFUtils.refreshIterator("AdmUsersFavoritesViewIterator");
        return null;
    }

    public String removeNodeFav() {
        
        ADFUtils.findOperation("setCurrentRowWithKeyValue").getParamsMap().put("rowKey", menuRow.getAttribute("FavId").toString());
        ADFUtils.findOperation("setCurrentRowWithKeyValue").execute();
        ADFUtils.findIterator("AdmUsersFavoritesViewIterator").getCurrentRow().remove();
        ADFUtils.findOperation("Commit").execute();

        refreshShuttle();
        ADFUtils.refreshIterator("TreeMenusROViewIterator");
        ADFUtils.refreshIterator("AdmUsersFavoritesViewIterator");
        return null;
    }

    public BindingContainer getBindings() {
        return BindingContext.getCurrent().getCurrentBindingsEntry();
    }

    public void setTree(RichTree tree) {
        this.tree = tree;
    }

    public RichTree getTree() {
        return tree;
    }

    public String processOpenMenu() {
        processSelection(menuRow);
        return null;
    }

    public String OnFavAction() {
        processSelection(favRow);
        return null;
    }
}
