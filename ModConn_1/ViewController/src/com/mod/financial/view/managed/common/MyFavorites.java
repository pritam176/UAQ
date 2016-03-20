package com.mod.financial.view.managed.common;

import com.mod.financial.view.utils.ADFUtils;
import com.mod.financial.view.utils.JSFUtils;
import com.mod.financial.view.utils.ShuttleBean;

import java.util.ArrayList;
import java.util.List;


import oracle.adf.model.binding.DCIteratorBinding;

import oracle.binding.OperationBinding;

import oracle.jbo.Row;
import oracle.jbo.RowIterator;
import oracle.jbo.domain.Number;

public class MyFavorites extends ShuttleBean{
    public String save_action() {
        List<Number> newAddedMenus = new ArrayList<Number>();
        List<Number> removedMenus = new ArrayList<Number>();
        // Get the user id
        Number userId = (Number) JSFUtils.resolveExpression("#{userInfo.userId}");

        List<Number> selectedValues = getSelectedValues();
        if (selectedValues==null) {
            selectedValues= new ArrayList<Number>();
        }
    
        DCIteratorBinding usersMenusIterator = ADFUtils.findIterator("AdmUsersFavoritesViewIterator");
  
        for (Number selectedMenuId : selectedValues) {                                          
            RowIterator resultRow = usersMenusIterator.findRowsByAttributeValues(new String[]{"MenuId","UserId"},new Object[]{selectedMenuId,userId});
            if (!resultRow.hasNext()) {
                Row row = usersMenusIterator.getViewObject().createRow();
                row.setAttribute("MenuId", selectedMenuId);
                row.setAttribute("UserId", userId);
                newAddedMenus.add(selectedMenuId);
            }
        } 
 
        Row[] allRows = usersMenusIterator.getAllRowsInRange();
        for (int i = 0; i<allRows.length;i++) {
            Row row = allRows[i];
            if (!selectedValues.contains(row.getAttribute("MenuId"))) {
                removedMenus.add((Number) row.getAttribute("MenuId"));
                row.remove();
            }
        }
        

        // Commit the data
        OperationBinding operationBinding = ADFUtils.findOperation("Commit");
        Object result = operationBinding.execute();
        ADFUtils.findOperation("refreshMenuItemViews").execute();
        ADFUtils.findOperation("ExecuteWithParams").execute();
        ADFUtils.refreshPage();

        if (!operationBinding.getErrors().isEmpty()) {
            return null;
        }
        return null ;
    }
}
