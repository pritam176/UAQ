package com.mod.financial.view.managed.generalReports;

import com.mod.financial.view.utils.ADFUtils;

import com.mod.financial.view.utils.JSFUtils;

import java.util.List;
import java.util.Map;

import javax.el.ValueExpression;

import javax.faces.component.UIComponent;

import javax.faces.convert.Converter;

import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.view.rich.component.rich.input.RichInputDate;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.layout.RichPanelBox;
import oracle.adf.view.rich.component.rich.layout.RichPanelFormLayout;
import oracle.adf.view.rich.context.AdfFacesContext;

import oracle.jbo.Row;

import oracle.ui.pattern.dynamicShell.Tab;
import oracle.ui.pattern.dynamicShell.TabContext;

import utils.system;

public class GeneralReports {
    private RichPanelFormLayout panelFormReport;

    public GeneralReports() {

    }
    public TabContext getTabContext() {
        return (TabContext)AdfFacesContext.getCurrentInstance().getPageFlowScope().get("tabContext");
    }

    public void initComponetMethod() {
        panelFormReport = new RichPanelFormLayout();
        DCIteratorBinding iter = ADFUtils.findIterator("AdmReportsItemsView2Iterator");
        for (Row r :iter.getAllRowsInRange()){
            if (r.getAttribute("ItemType").equals("T")){
                RichInputText inputText = new RichInputText();
                inputText.setLabel((String)r.getAttribute("ItemArTitle"));
                inputText.setId((String)r.getAttribute("ItemName"));
                inputText.setRequired(r.getAttribute("RequiredFlag").equals("Y") ? true : false);
                inputText.setValueExpression("value", JSFUtils.getValueExpression("#{pageFlowScope."+(String)r.getAttribute("ItemName")+"}"));
                addComponent(panelFormReport, inputText);
            }
            else if (r.getAttribute("ItemType").equals("D")){
                RichInputDate inputDate = new RichInputDate();
                inputDate.setLabel((String)r.getAttribute("ItemArTitle"));
                inputDate.setId((String)r.getAttribute("ItemName"));
                inputDate.setRequired(r.getAttribute("RequiredFlag").equals("Y") ? true : false);
                inputDate.setValueExpression("value", JSFUtils.getValueExpression("#{pageFlowScope."+(String)r.getAttribute("ItemName")+"}"));
                addComponent(panelFormReport, inputDate);
            }
        }
       
    }

    public void addComponent(UIComponent parentUIComponent,
                             UIComponent childUIComponent) {
        parentUIComponent.getChildren().add(childUIComponent);
        AdfFacesContext.getCurrentInstance().addPartialTarget(parentUIComponent);
    }



    public void setPanelFormReport(RichPanelFormLayout panelFormReport) {
        this.panelFormReport = panelFormReport;
    }

    public RichPanelFormLayout getPanelFormReport() {
        return panelFormReport;
    }
    public void getReportIdValue(){
        TabContext context = getTabContext();
        List<Tab> tabsList = context.getTabs();
        int selectedIndex =context.getSelectedTabIndex();
            Tab currentTab = tabsList.get(selectedIndex);
            Map<String, Object> tabParams = currentTab.getParameters();
            if  (tabParams != null  &&  tabParams.get("reportId") != null ){
                AdfFacesContext.getCurrentInstance().getPageFlowScope().put("reportId", tabParams.get("reportId"));
            }
        }


    public String test_action() {
        RichPanelFormLayout formLayout = getPanelFormReport();
        List<UIComponent> children = formLayout.getChildren();
        String value ="?";
        for (int i = 0 ; i<children.size() ; i++){
            System.out.println(JSFUtils.resolveExpression("#{pageFlowScope."+children.get(i).getId()+"}"));
         value=value+children.get(i).getId()+"="+
               JSFUtils.resolveExpression("#{pageFlowScope."+children.get(i).getId()+"}" ) +"&";
        }
        ADFUtils.openUrlInNewWindow("reportServletAction"+value,false);
        return null;
    }
}
