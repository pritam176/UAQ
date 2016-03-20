package com.mod.financial.view.managed.business.correspondances;

import com.mod.financial.view.utils.ADFUtils;
import com.mod.financial.view.utils.JSFUtils;
import com.mod.financial.view.utils.UserInfo;

import java.util.Map;

import oracle.adf.view.rich.component.rich.input.RichInputDate;
import oracle.adf.view.rich.component.rich.input.RichInputText;

import oracle.binding.OperationBinding;

public class DocsDetDistribution {
    private RichInputDate docDateId;
    private RichInputDate rcvDateId;
//    private RichInputText docRefIt;

    public DocsDetDistribution() {
    }

    public void setDocDestByUserDeptNo() {
        try {
            UserInfo userInfo =
                (UserInfo)JSFUtils.resolveExpression("#{userInfo}");
            ADFUtils.setBoundAttributeValue("DocDest", userInfo.getDeptNo());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public String distributeDetDocs() {
        try {
            java.util.Date docUtilDate =  (java.util.Date)docDateId.getValue();
            java.util.Date rcvUtilDate =  (java.util.Date)rcvDateId.getValue();
            UserInfo userInfo =
                (UserInfo)JSFUtils.resolveExpression("#{userInfo}");
            OperationBinding operation = ADFUtils.findOperation("callDistributeDetDocsFunction");
            Map paramsMap = operation.getParamsMap();
            paramsMap.put("userId", userInfo.getUserId());
            paramsMap.put("recvDate", rcvUtilDate);
            paramsMap.put("docDate", docUtilDate);
            paramsMap.put("docRef", ADFUtils.getBoundAttributeValue("DocRef"));
            paramsMap.put("sectionId", ADFUtils.getBoundAttributeValue("SectionIdLov"));
            paramsMap.put("subjectId", ADFUtils.getBoundAttributeValue("SubjIdLov"));
            paramsMap.put("docDest", ADFUtils.getBoundAttributeValue("DocDest"));
            paramsMap.put("lang", userInfo.getLanguage());
            String distMessage = (String)operation.execute();
            JSFUtils.addFacesInformationMessage(distMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String cancelDetDocsDistribution() {
        try {
            java.util.Date docUtilDate =  (java.util.Date)docDateId.getValue();
            java.util.Date rcvUtilDate =  (java.util.Date)rcvDateId.getValue();
            UserInfo userInfo =
                (UserInfo)JSFUtils.resolveExpression("#{userInfo}");
            OperationBinding operation = ADFUtils.findOperation("callCancelDistributeDetDocsFunction");
            Map paramsMap = operation.getParamsMap();
            paramsMap.put("userId", userInfo.getUserId());
            paramsMap.put("recvDate", rcvUtilDate);
            paramsMap.put("docDate", docUtilDate);
            paramsMap.put("docRef", ADFUtils.getBoundAttributeValue("DocRef"));
            paramsMap.put("sectionId", ADFUtils.getBoundAttributeValue("SectionIdLov"));
            paramsMap.put("subjectId", ADFUtils.getBoundAttributeValue("SubjIdLov"));
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
}
