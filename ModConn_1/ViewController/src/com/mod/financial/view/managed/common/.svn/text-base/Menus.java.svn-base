package com.mod.financial.view.managed.common;

import com.mod.financial.view.utils.ADFUtils;
import com.mod.financial.view.utils.JSFUtils;


import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.input.RichInputListOfValues;
import oracle.adf.view.rich.component.rich.layout.RichPanelFormLayout;

import oracle.adf.view.rich.event.DialogEvent;

import oracle.jbo.domain.Number;
import java.util.Iterator;

import java.util.List;

import java.util.Map;


import javax.el.ELContext;

import javax.el.ExpressionFactory;

import javax.el.ValueExpression;

import javax.faces.context.FacesContext;

import oracle.adf.model.BindingContext;
import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.view.rich.component.rich.data.RichTree;
import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.context.AdfFacesContext;


import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

import oracle.jbo.Key;
import oracle.jbo.Row;
import oracle.jbo.ViewObject;
import oracle.jbo.uicli.binding.JUCtrlHierBinding;

import oracle.jbo.uicli.binding.JUCtrlHierNodeBinding;

import oracle.jbo.uicli.binding.JUCtrlHierTypeBinding;

import org.apache.myfaces.trinidad.context.RequestContext;
import org.apache.myfaces.trinidad.event.SelectionEvent;
import org.apache.myfaces.trinidad.model.CollectionModel;
import org.apache.myfaces.trinidad.model.RowKeySet;

public class Menus {
    private RichPanelFormLayout form;
    private RichTree tree;
    private RichInputListOfValues parentLov;
    private RichPopup deletePopup;

    public Menus() {
    }

    private DCIteratorBinding resolveTargetIterWithSpel(String spelExpr) {
        FacesContext fctx = FacesContext.getCurrentInstance();
        ELContext elctx = fctx.getELContext();
        ExpressionFactory elFactory =
            fctx.getApplication().getExpressionFactory();
        ValueExpression valueExpr =
            elFactory.createValueExpression(elctx, spelExpr, Object.class);
        DCIteratorBinding dciter =
            (DCIteratorBinding)valueExpr.getValue(elctx);
        return dciter;
    }


    public void OnSelection(SelectionEvent selectionEvent) {
        Object value =
            JSFUtils.resolveMethodExpression("#{bindings.AdmMenusROView.treeModel.makeCurrent}",
                                             Object.class,
                                             new Class[] { SelectionEvent.class },
                                             new Object[] { selectionEvent });


        //get the tree information from the event object
        RichTree tree = (RichTree)selectionEvent.getSource();

        RowKeySet rks2 = tree.getSelectedRowKeys();
        Iterator rksIterator = rks2.iterator();

        //support single row selection case
        if (rksIterator.hasNext()) {
            //get the tree node key, which is a List of path entries describing
            //the location of the node in the tree including its parents nodes

            List key = (List)rksIterator.next();
            for (int i = 0; i < key.size(); i++) {
                System.out.println(key.get(i));
            }
            //get the ADF tree binding to work with
            JUCtrlHierBinding treeBinding = null;
            //The Trinidad CollectionModel is used to provide data to trees and
            //tables. In theADF binding case, it contains the tree binding as
            // wrapped data
            treeBinding =
                    (JUCtrlHierBinding)((CollectionModel)tree.getValue()).getWrappedData();
            //find the node identified by the node path from the ADF binding
            //layer. Note that we don't need to know about the name of the
            // tree binding in the PageDef file because all information is
            //provided
            JUCtrlHierNodeBinding nodeBinding =
                treeBinding.findNodeByKeyPath(key);
            Row currentRow = nodeBinding.getCurrentRow();

            //the current row is set on the iterator binding. Because all
            //bindings have an internal reference to their iterator usage, the
            //iterator can be queried from the ADF binding object
            DCIteratorBinding _treeIteratorBinding = null;
            _treeIteratorBinding = treeBinding.getDCIteratorBinding();
            Key rowKey = nodeBinding.getRowKey();
            Number Id = (Number)rowKey.getKeyValues()[0];

            ADFUtils.findOperation("setCurrentRowWithKeyValue").getParamsMap().put("rowKey",
                                                                                   Id.toString());
            ADFUtils.findOperation("setCurrentRowWithKeyValue").execute();

            Map<String, Object> flowScope =
                RequestContext.getCurrentInstance().getPageFlowScope();
            flowScope.put("sameParentMenu",
                          currentRow.getAttribute("ParentTitleName"));
            if (JSFUtils.resolveExpression("#{userInfo.language}").equals("ar"))
                flowScope.put("childMenu",
                              currentRow.getAttribute("ArMenuTitle"));
            else
                flowScope.put("childMenu",
                              currentRow.getAttribute("EnMenuTitle"));
            System.out.println(flowScope.get("sameParentMenu") + "   " +
                               flowScope.get("childMenu"));

          //  AdfFacesContext.getCurrentInstance().addPartialTarget(form);
        }
    }


    public void setForm(RichPanelFormLayout form) {
        this.form = form;
    }

    public RichPanelFormLayout getForm() {
        return form;
    }

    public String create_sameLevel_nodeMenu() {
        Map<String, Object> flowScope =
            RequestContext.getCurrentInstance().getPageFlowScope();
        ADFUtils.findOperation("CreateInsertMenu").execute();
        Row currentRow =
            ADFUtils.findIterator("AdmMenusViewIterator").getCurrentRow();
        Object ParentTitle = flowScope.get("sameParentMenu");
        if (ParentTitle != null )
        currentRow.setAttribute("ParentTitleName",ParentTitle);
        return null;
    }

    public String create_childLevel_nodeMenu() {
                Map<String, Object> flowScope =
                    RequestContext.getCurrentInstance().getPageFlowScope();
        ADFUtils.findOperation("CreateInsertMenu").execute();
                Row currentRow =
                    ADFUtils.findIterator("AdmMenusViewIterator").getCurrentRow();
                currentRow.setAttribute("ParentTitleName",
                                        flowScope.get("childMenu"));
                
        
       
       
        //EnMenuTitle1 
  //      if (JSFUtils.resolveExpression("#{userInfo.language}").equals("ar"))
  //      ADFUtils.setBoundAttributeValue("PMenuId2", ADFUtils.getBoundAttributeValue("MenuId"));
  //      AdfFacesContext.getCurrentInstance().addPartialTarget(form);
  //      else
   //         ADFUtils.setBoundAttributeValue("ParentTitleName", ADFUtils.getBoundAttributeValue("EnMenuTitle1"));
        return null;
    }

    public BindingContainer getBindings() {
        return BindingContext.getCurrent().getCurrentBindingsEntry();
    }

    public String save_action() {
        ADFUtils.findOperation("Commit").execute();
        ADFUtils.findOperation("ExecuteTreeMenu").execute();
        ADFUtils.findOperation("setCurrentRowWithKeyValue1").getParamsMap().put("rowKey", ADFUtils.getBoundAttributeValue("MenuId1"));

        AdfFacesContext.getCurrentInstance().addPartialTarget(tree);
        return null;
    }

    public void setTree(RichTree tree) {
        this.tree = tree;
    }

    public RichTree getTree() {
        return tree;
    }

    public void setParentLov(RichInputListOfValues parentLov) {
        this.parentLov = parentLov;
    }

    public RichInputListOfValues getParentLov() {
        return parentLov;
    }

    public String cancel_action() {
        ADFUtils.cancelChangesCurrentRow("AdmMenusViewIterator");

        return null;
    }

    public void setDeletePopup(RichPopup deletePopup) {
        this.deletePopup = deletePopup;
    }

    public RichPopup getDeletePopup() {
        return deletePopup;
    }

    public void deleteDialogListener(DialogEvent dialogEvent) {
        ADFUtils.findOperation("Delete").execute();
        ADFUtils.findOperation("Commit").execute();
        ADFUtils.findOperation("ExecuteTreeMenu").execute();
        ADFUtils.findOperation("setCurrentRowWithKeyValue1").getParamsMap().put("rowKey", ADFUtils.getBoundAttributeValue("MenuId1"));
        AdfFacesContext.getCurrentInstance().addPartialTarget(form);
        AdfFacesContext.getCurrentInstance().addPartialTarget(tree);
        
    }

    public String showDeletePopup() {
        deletePopup.show(new RichPopup.PopupHints());
        return null;
    }
}
