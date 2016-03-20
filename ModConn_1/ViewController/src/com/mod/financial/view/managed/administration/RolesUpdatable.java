package com.mod.financial.view.managed.administration;

import com.mod.financial.view.utils.ADFUtils;
import com.mod.financial.view.utils.JSFUtils;
import com.mod.financial.view.utils.ShuttleBean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ValueChangeEvent;

import oracle.adf.model.BindingContext;

import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.view.rich.component.rich.input.RichSelectOrderShuttle;
import oracle.adf.view.rich.context.AdfFacesContext;

import oracle.binding.BindingContainer;

import oracle.binding.OperationBinding;

import oracle.jbo.Row;
import oracle.jbo.RowIterator;
import oracle.jbo.domain.DBSequence;
import oracle.jbo.domain.Number;
import oracle.jbo.uicli.binding.JUCtrlListBinding;

public class RolesUpdatable extends ShuttleBean{

    public RolesUpdatable() {
        super();
    }

//Created By Amr Ahmed 
    public void onNodeTypeChanged(ValueChangeEvent valueChangeEvent) {
        BindingContainer bindings =BindingContext.getCurrent().getCurrentBindingsEntry();
        JUCtrlListBinding listBinding =(JUCtrlListBinding)bindings.get("NodeType");
        listBinding.setSelectedIndex(Integer.parseInt(valueChangeEvent.getNewValue().toString()));
        Row currentRow = listBinding.getCurrentRow();
        System.out.println(ADFUtils.getBoundAttributeValue("NodeTypeAttr"));
        ADFUtils.findOperation("ExecuteRolesWithNodeType").getParamsMap().put("bindNodeType", currentRow.getAttribute("NodeType"));
        ADFUtils.findOperation("ExecuteRolesWithNodeType").execute();
        refreshSelectedList(valueChangeEvent);
        refreshAllList(valueChangeEvent);
    }

    public BindingContainer getBindings() {
        return BindingContext.getCurrent().getCurrentBindingsEntry();
    }

    public String save_action() {
        List<Number> newAddedMenus = new ArrayList<Number>();
        List<Number> removedMenus = new ArrayList<Number>();
       
        List<Number> selectedValues = getSelectedValues();
        if (selectedValues==null) {
            selectedValues= new ArrayList<Number>();
        }
    
      Number RoleId = ((DBSequence)ADFUtils.getBoundAttributeValue("Id")).getSequenceNumber();
    
        DCIteratorBinding rolesPogsIterator = ADFUtils.findIterator("AdmRolesProgsViewIterator");
    
        for (Number selectedProgId : selectedValues) {                                          
            RowIterator resultRow = rolesPogsIterator.findRowsByAttributeValues(new String[]{"RoleId","ProgId"},new Object[]{RoleId,selectedProgId});
            if (!resultRow.hasNext()) {
                Row row = rolesPogsIterator.getViewObject().createRow();
                row.setAttribute("ProgId", selectedProgId);
                row.setAttribute("RoleId", RoleId);
                newAddedMenus.add(selectedProgId);
            }
        } 
    
        Row[] allRows = rolesPogsIterator.getAllRowsInRange();
        for (int i = 0; i<allRows.length;i++) {
            Row row = allRows[i];
            if (!selectedValues.contains(row.getAttribute("ProgId"))) {
                removedMenus.add((Number) row.getAttribute("ProgId"));
                row.remove();
            }
        }
        

        // Commit the data
        ADFUtils.findOperation("Commit").execute();
 //       OperationBinding operationBinding = ADFUtils.findOperation("Commit");
//        Object result = operationBinding.execute();
//        ADFUtils.findOperation("refreshMenuItemViews").execute();
//        ADFUtils.findOperation("ExecuteWithParams").execute();
//        ADFUtils.refreshPage();
//
//        if (!operationBinding.getErrors().isEmpty()) {
//            return null;
//        }
        return null ;
    }
}
