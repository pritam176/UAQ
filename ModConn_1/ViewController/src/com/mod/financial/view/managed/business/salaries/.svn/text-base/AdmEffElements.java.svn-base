package com.mod.financial.view.managed.business.salaries;

import com.mod.financial.view.utils.ADFUtils;
import com.mod.financial.view.utils.JSFUtils;

import javax.faces.event.ValueChangeEvent;

import oracle.adf.view.rich.component.rich.layout.RichPanelFormLayout;
import oracle.adf.view.rich.context.AdfFacesContext;

public class AdmEffElements {
    private RichPanelFormLayout mainPF;

    public AdmEffElements() {
    }

    public void onElementRuleChange(ValueChangeEvent valueChangeEvent) {
        System.err.println("value ==>> " + valueChangeEvent.getNewValue());
        if (valueChangeEvent.getNewValue() != null) {
            String elementRuleIndex = valueChangeEvent.getNewValue().toString();
            if(elementRuleIndex.equals("2") || elementRuleIndex.equals("3")){
                ADFUtils.setBoundAttributeValue("TableName", null);
                ADFUtils.setBoundAttributeValue("LookupTable", null);
            }
            
        }
        AdfFacesContext.getCurrentInstance().addPartialTarget(mainPF);
    }

    public void setMainPF(RichPanelFormLayout mainPF) {
        this.mainPF = mainPF;
    }

    public RichPanelFormLayout getMainPF() {
        return mainPF;
    }
}
