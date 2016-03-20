package com.mod.financial.view.managed.business.correspondances;

import com.mod.financial.view.utils.ADFUtils;

import com.mod.financial.view.utils.JSFUtils;

import com.mod.financial.view.utils.UserInfo;

import java.util.Map;

import oracle.adf.model.binding.DCIteratorBinding;

import oracle.binding.OperationBinding;

import oracle.jbo.Row;

public class executeRedirection {
    public executeRedirection() {
    }

    public String executeDocRedirection() {
        try {
            ADFUtils.findOperation("Commit").execute();
            UserInfo userInfo =
                (UserInfo)JSFUtils.resolveExpression("#{userInfo}");
            DCIteratorBinding sentDocsIter =
                ADFUtils.findIterator("SentDocsMasterView1Iterator");
            if (sentDocsIter != null) {
                Row selectedRow = sentDocsIter.getCurrentRow();
                if (selectedRow != null) {
                    OperationBinding operation =
                        ADFUtils.findOperation("callExecuteSentDocsRedirectionFunction");
                    Map paramsMap = operation.getParamsMap();
                    paramsMap.put("userId", userInfo.getUserId());
                    paramsMap.put("docId",
                                  selectedRow.getAttribute("Id") != null ?
                                  selectedRow.getAttribute("Id").toString() :
                                  null);
                    paramsMap.put("docDest",
                                  selectedRow.getAttribute("DocDest1") != null ?
                                  selectedRow.getAttribute("DocDest1").toString() :
                                  null);
                    paramsMap.put("lang", userInfo.getLanguage());
                    operation.execute();
                    ADFUtils.findOperation("ExecuteWithParams").execute();
                    JSFUtils.addFacesInformationMessage(JSFUtils.getStringFromBundle("document_created_message"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
