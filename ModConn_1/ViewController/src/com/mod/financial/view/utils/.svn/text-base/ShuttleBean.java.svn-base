package com.mod.financial.view.utils;

import java.io.Serializable;
import java.util.List;
import javax.faces.event.ValueChangeEvent;

import oracle.adf.model.binding.DCIteratorBinding;


// Creted by Amr Ahmed to use many Shuttle Component as General 

public class ShuttleBean implements Serializable {
    private String allItemsIteratorName;
    private String allItemsValueAttrName;
    private String allItemsDisplayAttrName;
    private String allItemsDescriptionAttrName;
    private String selectedValuesIteratorName;
    private String selectedValuesValueAttrName;
    private List selectedValues;
    private List allItems;
    private boolean refreshSelectedList=false;
    private boolean refreshAllList=false;

    public ShuttleBean() {
        
    }
    
    public void getIteratorName(String iterName){
        allItemsIteratorName = iterName;
        getAllItems();
    }

    public void setAllItemsIteratorName(String allItemsIteratorName) {
        this.allItemsIteratorName = allItemsIteratorName;
    }

    public String getAllItemsIteratorName() {
        return allItemsIteratorName;
    }
    // other getter and setter methods are omitted

    public void setSelectedValues(List selectedValues) {
        this.selectedValues = selectedValues;
    }

    public void refreshSelectedList(ValueChangeEvent event) {
        refreshSelectedList = false;
    }
    public void refreshAllList(ValueChangeEvent event) {
        refreshAllList = false;
    }

    public List getSelectedValues() {
        if (refreshSelectedList ==false) {
            selectedValues =
                    ADFUtils.attributeListForIterator(selectedValuesIteratorName,
                                                      selectedValuesValueAttrName);
            refreshSelectedList = true;
        }
        return selectedValues;
    }

    public void setAllItems(List allItems) {
        this.allItems = allItems;
    }

    public List getAllItems() {
        if (refreshAllList == false) {
            allItems =
                    ADFUtils.selectItemsForIterator(allItemsIteratorName, allItemsValueAttrName,
                                                    allItemsDisplayAttrName,
                                                    allItemsDescriptionAttrName);
            refreshAllList = true;
        }
        return allItems;
    }

    public void setAllItemsValueAttrName(String allItemsValueAttrName) {
        this.allItemsValueAttrName = allItemsValueAttrName;
    }

    public String getAllItemsValueAttrName() {
        return allItemsValueAttrName;
    }

    public void setAllItemsDisplayAttrName(String allItemsDisplayAttrName) {
        this.allItemsDisplayAttrName = allItemsDisplayAttrName;
    }

    public String getAllItemsDisplayAttrName() {
        return allItemsDisplayAttrName;
    }

    public void setAllItemsDescriptionAttrName(String allItemsDescriptionAttrName) {
        this.allItemsDescriptionAttrName = allItemsDescriptionAttrName;
    }

    public String getAllItemsDescriptionAttrName() {
        return allItemsDescriptionAttrName;
    }

    public void setSelectedValuesIteratorName(String selectedValuesIteratorName) {
        this.selectedValuesIteratorName = selectedValuesIteratorName;
    }

    public String getSelectedValuesIteratorName() {
        return selectedValuesIteratorName;
    }

    public void setSelectedValuesValueAttrName(String selectedValuesValueAttrName) {
        this.selectedValuesValueAttrName = selectedValuesValueAttrName;
    }

    public String getSelectedValuesValueAttrName() {
        return selectedValuesValueAttrName;
    }

    public void setRefreshSelectedList(boolean refreshSelectedList) {
        this.refreshSelectedList = refreshSelectedList;
    }

    public boolean isRefreshSelectedList() {
        return refreshSelectedList;
    }
}
