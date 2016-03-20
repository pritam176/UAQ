package com.mod.financial.view.managed.business.correspondances;


import com.mod.financial.view.utils.ADFUtils;
import com.mod.financial.view.utils.JSFUtils;
import com.mod.financial.view.utils.UserInfo;

import java.util.Map;

import oracle.binding.OperationBinding;

public class DocsRedistribution {    
    public DocsRedistribution() {
    }

    public String redistributeDocs() {
        try {
            UserInfo userInfo =
                (UserInfo)JSFUtils.resolveExpression("#{userInfo}");
            OperationBinding operation = ADFUtils.findOperation("callReDistributeDocsFunction");
            Map paramsMap = operation.getParamsMap();
            paramsMap.put("userId", ADFUtils.getBoundAttributeValue("UserIdRedistributeLov"));
            paramsMap.put("docRef", ADFUtils.getBoundAttributeValue("DocRef"));
            paramsMap.put("sectionId", ADFUtils.getBoundAttributeValue("SectionIdLov"));
            paramsMap.put("subjectId", ADFUtils.getBoundAttributeValue("SubjIdLov"));
            paramsMap.put("lang", userInfo.getLanguage());
            String distMessage = (String)operation.execute();
            JSFUtils.addFacesInformationMessage(distMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
