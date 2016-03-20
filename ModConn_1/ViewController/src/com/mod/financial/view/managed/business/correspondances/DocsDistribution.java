package com.mod.financial.view.managed.business.correspondances;

import com.mod.financial.view.utils.ADFUtils;

import com.mod.financial.view.utils.JSFUtils;
import com.mod.financial.view.utils.UserInfo;

import java.util.HashMap;
import java.util.Map;

import oracle.adf.view.rich.component.rich.input.RichInputDate;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.input.RichSelectOneChoice;

import oracle.adf.view.rich.context.AdfFacesContext;

import oracle.binding.OperationBinding;

import oracle.jbo.domain.Date;

public class DocsDistribution {
    private RichInputDate docDateId;
    private RichInputDate rcvDateId;
    private RichInputText docRefIt;

    public DocsDistribution() {
    }

    public String distributeDocs() {
        try {
            java.util.Date docUtilDate =  (java.util.Date)docDateId.getValue();
            java.util.Date rcvUtilDate =  (java.util.Date)rcvDateId.getValue();
            UserInfo userInfo =
                (UserInfo)JSFUtils.resolveExpression("#{userInfo}");
            OperationBinding operation = ADFUtils.findOperation("callDistributeDocsFunction");
            Map paramsMap = operation.getParamsMap();
            paramsMap.put("userId", userInfo.getUserId());
            paramsMap.put("docRef", docRefIt.getValue());
            paramsMap.put("recvDate", rcvUtilDate);
            paramsMap.put("docDate", docUtilDate);
            paramsMap.put("sectionId", ADFUtils.getBoundAttributeValue("SectionIdLov"));
            paramsMap.put("docDest", ADFUtils.getBoundAttributeValue("DocDest"));
            paramsMap.put("lang", userInfo.getLanguage());
            String distMessage = (String)operation.execute();
            JSFUtils.addFacesInformationMessage(distMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String cancelDocsDistribution() {
        try {
            java.util.Date docUtilDate =  (java.util.Date)docDateId.getValue();
            java.util.Date rcvUtilDate =  (java.util.Date)rcvDateId.getValue();
            UserInfo userInfo =
                (UserInfo)JSFUtils.resolveExpression("#{userInfo}");
            OperationBinding operation = ADFUtils.findOperation("callCancelDocsDistributionFunction");
            Map paramsMap = operation.getParamsMap();
            paramsMap.put("userId", userInfo.getUserId());
            paramsMap.put("docRef", docRefIt.getValue());
            paramsMap.put("recvDate", rcvUtilDate);
            paramsMap.put("docDate", docUtilDate);
            paramsMap.put("sectionId", ADFUtils.getBoundAttributeValue("SectionIdLov"));
            paramsMap.put("docDest", ADFUtils.getBoundAttributeValue("DocDest"));
            paramsMap.put("lang", userInfo.getLanguage());
            String distMessage = (String)operation.execute();
            JSFUtils.addFacesInformationMessage(distMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void setDocDateId(RichInputDate docDateId) {
        this.docDateId = docDateId;
    }

    public RichInputDate getDocDateId() {
        return docDateId;
    }

    public void setRcvDateId(RichInputDate rcvDateId) {
        this.rcvDateId = rcvDateId;
    }

    public RichInputDate getRcvDateId() {
        return rcvDateId;
    }

    public void setDocRefIt(RichInputText docRefIt) {
        this.docRefIt = docRefIt;
    }

    public RichInputText getDocRefIt() {
        return docRefIt;
    }
}
