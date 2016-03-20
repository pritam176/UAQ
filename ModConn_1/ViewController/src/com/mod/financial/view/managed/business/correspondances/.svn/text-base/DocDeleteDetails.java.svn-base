package com.mod.financial.view.managed.business.correspondances;

import com.mod.financial.view.utils.ADFUtils;
import com.mod.financial.view.utils.JSFUtils;
import com.mod.financial.view.utils.UserInfo;

import java.util.Map;

import javax.faces.event.ValueChangeEvent;

import oracle.adf.view.rich.component.rich.input.RichInputDate;
import oracle.adf.view.rich.component.rich.input.RichInputText;

import oracle.binding.OperationBinding;

public class DocDeleteDetails {
    private RichInputText lineNoIt;
    private String joinId;
    private String joinName;
    private String empNo;
    private String empName;

    public DocDeleteDetails() {
    }

    public String deleteDetails() {
        try {
            UserInfo userInfo =
                (UserInfo)JSFUtils.resolveExpression("#{userInfo}");
            OperationBinding operation =
                ADFUtils.findOperation("callDeleteDocsDetailsFunction");
            Map paramsMap = operation.getParamsMap();
            paramsMap.put("docRef", ADFUtils.getBoundAttributeValue("DocRef"));
            paramsMap.put("sectionId",
                          ADFUtils.getBoundAttributeValue("SectionIdLov"));
            paramsMap.put("subjectId",
                          ADFUtils.getBoundAttributeValue("SubjIdLov"));
            paramsMap.put("lineNo",
                          lineNoIt.getValue());
            paramsMap.put("lang", userInfo.getLanguage());
            String distMessage = (String)operation.execute();
            JSFUtils.addFacesInformationMessage(distMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void setLineNoIt(RichInputText lineNoIt) {
        this.lineNoIt = lineNoIt;
    }

    public RichInputText getLineNoIt() {
        return lineNoIt;
    }

    public void onSectionChange(ValueChangeEvent valueChangeEvent) {
        try {
            if(valueChangeEvent.getNewValue() != null)
                getJoinAndEmployeeData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onSubjectChange(ValueChangeEvent valueChangeEvent) {
        try {
            if(valueChangeEvent.getNewValue() != null)
                getJoinAndEmployeeData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onDocRefChange(ValueChangeEvent valueChangeEvent) {
        try {
            if(valueChangeEvent.getNewValue() != null)
                getJoinAndEmployeeData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onLineNoChange(ValueChangeEvent valueChangeEvent) {
        try {
            if(valueChangeEvent.getNewValue() != null)
                getJoinAndEmployeeData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void getJoinAndEmployeeData(){
        try {
            OperationBinding oper = ADFUtils.findOperation("GetJoinAndEmployee");
            Map paramsMap = oper.getParamsMap();
            
            paramsMap.put("pDocRef", ADFUtils.getBoundAttributeValue("DocRef"));
            paramsMap.put("pSectionId",
                          ADFUtils.getBoundAttributeValue("SectionIdLov"));
            paramsMap.put("pSubjectId",
                          ADFUtils.getBoundAttributeValue("SubjIdLov"));
            paramsMap.put("pLineNo",
                          lineNoIt.getValue());
            oper.execute();
            setJoinId(ADFUtils.getBoundAttributeValue("JoinId") != null ? ADFUtils.getBoundAttributeValue("JoinId").toString() : "");
            setJoinName(ADFUtils.getBoundAttributeValue("Name") != null ? ADFUtils.getBoundAttributeValue("Name").toString() : "");
            setEmpNo(ADFUtils.getBoundAttributeValue("EmpNo") != null ? ADFUtils.getBoundAttributeValue("EmpNo").toString() : "");
            setEmpName(ADFUtils.getBoundAttributeValue("EmpName") != null ? ADFUtils.getBoundAttributeValue("EmpName").toString() : "");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setJoinId(String joinId) {
        this.joinId = joinId;
    }

    public String getJoinId() {
        return joinId;
    }

    public void setJoinName(String joinName) {
        this.joinName = joinName;
    }

    public String getJoinName() {
        return joinName;
    }

    public void setEmpNo(String empNo) {
        this.empNo = empNo;
    }

    public String getEmpNo() {
        return empNo;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getEmpName() {
        return empName;
    }
}
