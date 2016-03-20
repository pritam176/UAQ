package com.adf.pos.utils;


import ae.payment.model.UaqAppModuleImpl;

import java.io.OutputStream;

import java.util.HashMap;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;


import oracle.adf.model.binding.DCIteratorBinding;

public class Reports {
    private UaqAppModuleImpl appModule;
    private String projectYear;
    private String projName;
    private String projCode;
    private String typeId;
    private String sponsorId;
    private String stageId;
    private String projYear;
    private String emirateId;
    private String deptsectorId;
    private String deptId;
    private String projStatus;

    public Reports() {
        try {
            appModule =
                    (UaqAppModuleImpl)ADFUtils.evaluateEL("#{data.UaqAppModuleDataControl.dataProvider}");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private String ext;


    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }

    public void generateGLRRPT(FacesContext facesContext,
                               OutputStream outputStream) {
        HashMap params = new HashMap();
        String userName = null;
        String reportName = null;

        // Add event code here...
        if (ADFUtils.evaluateEL("#{bindings.ProjName.inputValue}") != null) {
            if (!ADFUtils.evaluateEL("#{bindings.ProjName.inputValue}").equals(""))
                projName =
                        ADFUtils.evaluateEL("#{bindings.ProjName.inputValue}").toString();
        }
        if (ADFUtils.evaluateEL("#{bindings.ProjCode.inputValue}") != null) {
            if (!ADFUtils.evaluateEL("#{bindings.ProjCode.inputValue}").equals(""))
                projCode =
                        ADFUtils.evaluateEL("#{bindings.ProjCode.inputValue}").toString();
        }


        params.put("projname", projName);
        params.put("projcode", projCode);
        params.put("typeid", typeId);
        params.put("sponsorid", sponsorId);
        params.put("stageid", stageId);
        params.put("projyear", projectYear);
        params.put("emirateid", emirateId);
        params.put("deptsectorid", deptsectorId);
        params.put("deptid", deptId);
        //        params.put("projstatus", projStatus);
        params.put("username", userName);
        params.put("reportname", reportName);

        if (ext.equalsIgnoreCase("PDF")) {


            ReportsUtil.GenerateReportPDF(params, "AllProjectsReport", false,
                                          true,
                                          ReportsUtil.getAppModuleConnection(appModule),
                                          outputStream,"");


        }

        else
            ReportsUtil.GenerateReportExcel(params, "AllProjectsReport", false,
                                            true,
                                            ReportsUtil.getAppModuleConnection(appModule),
                                            outputStream,"");
        projectYear = null;
        projName = null;
        projCode = null;
        typeId = null;
        sponsorId = null;
        stageId = null;
        projYear = null;
        emirateId = null;
        deptsectorId = null;
        deptId = null;
        projStatus = null;
    }

    public void setProjectYear(String projectYear) {
        this.projectYear = projectYear;
    }

    public String getProjectYear() {
        return projectYear;
    }

    public void onProjectTypeChangeListener(ValueChangeEvent valueChangeEvent) {
        if (valueChangeEvent.getNewValue() != null) {
            int selectedTypeIndex =
                Integer.parseInt(valueChangeEvent.getNewValue().toString());
            DCIteratorBinding projectTypeVO1Iterator =
                ADFUtils.findIterator("ProjectTypeVO1Iterator");
            if (projectTypeVO1Iterator != null) {
                projectTypeVO1Iterator.setCurrentRowIndexInRange(selectedTypeIndex -
                                                                 1);
                typeId =
                        projectTypeVO1Iterator.getCurrentRow().getAttribute("TypeId").toString();
            }
        }
    }

    public void onSponsorChangeListener(ValueChangeEvent valueChangeEvent) {
        if (valueChangeEvent.getNewValue() != null) {
            int selectedSponsorIndex =
                Integer.parseInt(valueChangeEvent.getNewValue().toString());
            DCIteratorBinding sponsorsVO1Iterator =
                ADFUtils.findIterator("SponsorsVO1Iterator");
            if (sponsorsVO1Iterator != null) {
                sponsorsVO1Iterator.setCurrentRowIndexInRange(selectedSponsorIndex -
                                                              1);
                sponsorId =
                        sponsorsVO1Iterator.getCurrentRow().getAttribute("FunderId").toString();
            }
        }
    }

    public void onStageChangeListener(ValueChangeEvent valueChangeEvent) {
        if (valueChangeEvent.getNewValue() != null) {
            int selectedStageIndex =
                Integer.parseInt(valueChangeEvent.getNewValue().toString());
            DCIteratorBinding projectStageVO1Iterator =
                ADFUtils.findIterator("ProjectStageVO1Iterator");
            if (projectStageVO1Iterator != null) {
                projectStageVO1Iterator.setCurrentRowIndexInRange(selectedStageIndex -
                                                                  1);
                stageId =
                        projectStageVO1Iterator.getCurrentRow().getAttribute("StageId").toString();
            }
        }
    }

    public void onEmirateChangeListener(ValueChangeEvent valueChangeEvent) {
        if (valueChangeEvent.getNewValue() != null) {
            int selectedEmirateIndex =
                Integer.parseInt(valueChangeEvent.getNewValue().toString());
            DCIteratorBinding hrGnlEmiratesView1Iterator =
                ADFUtils.findIterator("HrGnlEmiratesView1Iterator");
            if (hrGnlEmiratesView1Iterator != null) {
                hrGnlEmiratesView1Iterator.setCurrentRowIndexInRange(selectedEmirateIndex -
                                                                     1);
                emirateId =
                        hrGnlEmiratesView1Iterator.getCurrentRow().getAttribute("EmirateId").toString();
            }
        }
    }

    public void onDeptsectorChangeListener(ValueChangeEvent valueChangeEvent) {
        if (valueChangeEvent.getNewValue() != null) {
            int selectedDeptsectorIndex =
                Integer.parseInt(valueChangeEvent.getNewValue().toString());
            DCIteratorBinding hrGnlDeptsectorView1Iterator =
                ADFUtils.findIterator("HrGnlDeptsectorView1Iterator");
            if (hrGnlDeptsectorView1Iterator != null) {
                hrGnlDeptsectorView1Iterator.setCurrentRowIndexInRange(selectedDeptsectorIndex -
                                                                       1);
                deptsectorId =
                        hrGnlDeptsectorView1Iterator.getCurrentRow().getAttribute("DeptsectorId").toString();
            }
        }
    }

    public void onDeptChangeListener(ValueChangeEvent valueChangeEvent) {
        if (valueChangeEvent.getNewValue() != null) {
            int selectedDeptIndex =
                Integer.parseInt(valueChangeEvent.getNewValue().toString());
            DCIteratorBinding hrGnlDeprtmntView1Iterator =
                ADFUtils.findIterator("HrGnlDeprtmntView1Iterator");
            if (hrGnlDeprtmntView1Iterator != null) {
                hrGnlDeprtmntView1Iterator.setCurrentRowIndexInRange(selectedDeptIndex -
                                                                     1);
                deptId =
                        hrGnlDeprtmntView1Iterator.getCurrentRow().getAttribute("DeptId").toString();
            }
        }
    }

    public void onStatusChangeListener(ValueChangeEvent valueChangeEvent) {
        if (valueChangeEvent.getNewValue() != null) {
            int selectedDeptIndex =
                Integer.parseInt(valueChangeEvent.getNewValue().toString());
            DCIteratorBinding hrGnlDeprtmntView1Iterator =
                ADFUtils.findIterator("HrGnlDeprtmntView1Iterator");
            if (hrGnlDeprtmntView1Iterator != null) {
                hrGnlDeprtmntView1Iterator.setCurrentRowIndexInRange(selectedDeptIndex -
                                                                     1);
                deptId =
                        hrGnlDeprtmntView1Iterator.getCurrentRow().getAttribute("DeptId").toString();
            }
        }
    }

    public void emptyAllFields(ActionEvent actionEvent) {
        projectYear = null;
        projName = null;
        projCode = null;
        typeId = null;
        sponsorId = null;
        stageId = null;
        projYear = null;
        emirateId = null;
        deptsectorId = null;
        deptId = null;
        projStatus = null;
    }
}
