package com.mod.financial.view.managed.business.correspondances;

import com.mod.financial.view.utils.ADFUtils;

import com.mod.financial.view.utils.JSFUtils;

import oracle.adf.model.binding.DCIteratorBinding;

import oracle.binding.OperationBinding;

import oracle.jbo.Row;

public class CorDocDelivered {
    public CorDocDelivered() {
    }

    public String saveDocDelivered() {
        try {
            DCIteratorBinding docsIter =
                ADFUtils.findIterator("CorDocDeliveredView1Iterator");
            int docsSize = 0;
            if (docsIter != null) {
                docsSize = docsIter.getAllRowsInRange().length;
                for (int index = 0; index < docsSize; index++) {
                    Row currentDocRow = docsIter.getRowAtRangeIndex(index);
                    if (currentDocRow != null)
                        currentDocRow.setAttribute("ReceivedBy",
                                                   ADFUtils.getBoundAttributeValue("UserIdDocDelivered"));
                }
            }
            ADFUtils.findOperation("Commit").execute();
            JSFUtils.addFacesInfoMessage(docsSize + "  " + JSFUtils.getStringFromBundle("DOCS_DELIVERED"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
