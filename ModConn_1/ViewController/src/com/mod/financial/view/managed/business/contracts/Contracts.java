package com.mod.financial.view.managed.business.contracts;

import com.mod.financial.view.utils.ADFUtils;
import com.mod.financial.view.utils.JSFUtils;

import java.sql.Date;

import java.util.Map;

import javax.faces.application.FacesMessage;

import javax.faces.context.FacesContext;

import oracle.adf.model.BindingContext;
import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.context.AdfFacesContext;
import oracle.adf.view.rich.event.DialogEvent;

import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

import oracle.jbo.Row;
import oracle.jbo.domain.Number;

import oracle.ui.pattern.dynamicShell.Tab;
import oracle.ui.pattern.dynamicShell.TabContext;

public class Contracts {
    private RichPopup performedPopup;
    private RichPopup bdgPopup;
    private RichPopup wpnPopup;
    private RichPopup payAccountPopup;
    private String notifText;
    private String iterName= "AccContractsViewIterator";

    public Contracts() {
    }


    public void deleteServicesDialogListener(DialogEvent dialogEvent) {
        ADFUtils.findOperation("Delete").execute();
        ADFUtils.findOperation("Commit").execute();
        AdfFacesContext.getCurrentInstance().addPartialTarget(JSFUtils.findComponentInRoot("contT1"));
    }

    public void setPageType() {
        //        try {
        //            int index = getContext().getSelectedTabIndex();
        //            Tab tab = getContext().getTabs().get(index);
        //            System.out.printf("### menuId = %s",tab.getParameters().get("menuId"));
        //            if(tab.getParameters() != null && tab.getParameters().get("menuId") != null && tab.getParameters().get("menuId").toString().equals("108")) {
        //                ADFUtils.setEL("#{pageFlowScope.pageTypeFlag}", "all");
        //            }
        //        } catch (Exception e) {
        //            e.printStackTrace();
        //        }
        ADFUtils.setEL("#{pageFlowScope.pageTypeFlag}", "all");
    }

    public void initQuery() {
        ADFUtils.setEL("#{pageFlowScope.pageTypeFlag}", "query");
    }

    public void initQuerySection() {
        ADFUtils.setEL("#{pageFlowScope.pageTypeFlag}", "section");
    }

    public TabContext getContext() {
        return (TabContext)AdfFacesContext.getCurrentInstance().getPageFlowScope().get("tabContext");
    }

    public BindingContainer getBindings() {
        return BindingContext.getCurrent().getCurrentBindingsEntry();
    }

    /**
     * By Gamal Abdelhamid
     * Executes the function LG_ACTIVE.
     * @return the Function Result
     */
    public String lgActiveAction() {
        BindingContainer bindings = getBindings();
        OperationBinding operationBinding =
            bindings.getOperationBinding("callLGActiveProcedure");
        Object result = operationBinding.execute();

        Map map = (Map)result;
        /**Check if res_desc = 1037 then
        * open_new_form('acc_sht_qry',:sht1_id);
        * else show error desc */

        if (!operationBinding.getErrors().isEmpty()) {
            return null;
        }
        return null;
    }

    public String lgExtendAction() {
        BindingContainer bindings = getBindings();
        OperationBinding operationBinding =
            bindings.getOperationBinding("callLGExtendProcedure");
        Object result = operationBinding.execute();

        Map map = (Map)result;
        /**Check if res_desc = 1040 then
        * open_new_form('acc_sht_qry',:sht1_id);
        * else if 1040 sjow info else  show error desc */
        String code = (String)map.get("res_code");
        String desc = (String)map.get("res_desc");

        FacesMessage msg =
            new FacesMessage(code.equals("1040") ? FacesMessage.SEVERITY_INFO :
                             FacesMessage.SEVERITY_ERROR, "", desc);
        FacesContext.getCurrentInstance().addMessage(null, msg);

        if (!operationBinding.getErrors().isEmpty()) {
            return null;
        }
        return null;
    }

    public String lgCashAction() {
        BindingContainer bindings = getBindings();
        OperationBinding operationBinding =
            bindings.getOperationBinding("callLGCashProcedure");
        Object result = operationBinding.execute();

        Map map = (Map)result;
        /**Check if res_desc = 1048 then show info and
        * open_new_form('acc_sht_qry',in_csh_sht_id);
        * else show error desc */
        String code = (String)map.get("res_code");
        String desc = (String)map.get("res_desc");
        String lgStatus = (String)map.get("io_lg_status");
        Date cashDate = (Date)map.get("io_cash_date");
        Number sht1Id = (Number)map.get("o_csh_sht_id");
        Number shtSerialId = (Number)map.get("o_csh_sht_serial");

        FacesMessage msg =
            new FacesMessage(code.equals("1048") ? FacesMessage.SEVERITY_INFO :
                             FacesMessage.SEVERITY_ERROR, "", desc);
        FacesContext.getCurrentInstance().addMessage(null, msg);

        if (!operationBinding.getErrors().isEmpty()) {
            return null;
        }
        return null;
    }

    public String lgDeliverAction() {
        BindingContainer bindings = getBindings();
        OperationBinding operationBinding =
            bindings.getOperationBinding("callLGDeliverProcedure");
        Object result = operationBinding.execute();

        Map map = (Map)result;
        /**Check if res_desc = 1044 then show info and
        * open_new_form('acc_sht_qry',O_RET_SHT_ID);
        * else show error desc */
        String code = (String)map.get("res_code");
        String desc = (String)map.get("res_desc");
        String lgStatus = (String)map.get("io_lg_status");
        Date cashDate = (Date)map.get("io_cash_date");
        Number sht1Id = (Number)map.get("o_csh_sht_id");
        Number shtSerialId = (Number)map.get("o_csh_sht_serial");

        FacesMessage msg =
            new FacesMessage(code.equals("1044") ? FacesMessage.SEVERITY_INFO :
                             FacesMessage.SEVERITY_ERROR, "", desc);
        FacesContext.getCurrentInstance().addMessage(null, msg);

        if (!operationBinding.getErrors().isEmpty()) {
            return null;
        }
        return null;
    }

    public String showDeductionDetailAction() {
        BindingContainer bindings = getBindings();
        OperationBinding operationBinding =
            bindings.getOperationBinding("ExecuteWithParams");
        Object result = operationBinding.execute();

        DCIteratorBinding iter =
            ADFUtils.findIterator("DeductionDetailViewIterator");
        Row currentRow = iter.getCurrentRow();
        Number bdg = (Number)currentRow.getAttribute("VBdg");
        Number wpn = (Number)currentRow.getAttribute("VWpn");

        if (bdg.intValue() > 0) {
            //show bdg popup
            bdgPopup.show(new RichPopup.PopupHints());
        } else if (wpn.intValue() > 0) {
            //show wpn popup
            wpnPopup.show(new RichPopup.PopupHints());
        }

        if (!operationBinding.getErrors().isEmpty()) {
            return null;
        }
        return null;
    }

    public void setPerformedPopup(RichPopup performedPopup) {
        this.performedPopup = performedPopup;
    }

    public RichPopup getPerformedPopup() {
        return performedPopup;
    }

    public void setBdgPopup(RichPopup bdgPopup) {
        this.bdgPopup = bdgPopup;
    }

    public RichPopup getBdgPopup() {
        return bdgPopup;
    }

    public void setWpnPopup(RichPopup wpnPopup) {
        this.wpnPopup = wpnPopup;
    }

    public RichPopup getWpnPopup() {
        return wpnPopup;
    }

    public void setPayAccountPopup(RichPopup payAccountPopup) {
        this.payAccountPopup = payAccountPopup;
    }

    public RichPopup getPayAccountPopup() {
        return payAccountPopup;
    }

    public String payAccountAction() {
        // check if contract type != 5 then open payAccountsPopup
        Number contractType =
            (Number)ADFUtils.getBoundAttributeValue("ContractType");
        if (contractType != null && contractType.intValue() != 5) {
            payAccountPopup.show(new RichPopup.PopupHints());
        }
        return null;
    }

    public String cancelPayAction() {
        OperationBinding operation =
            ADFUtils.findOperation("callCancelPayProc");
        Map result = (Map)operation.execute();
        String code = (String)result.get("res_code");
        String desc = (String)result.get("res_desc");

        /**if  code 793 then info else error*/
        FacesMessage msg =
            new FacesMessage(code.equals("793") ? FacesMessage.SEVERITY_INFO :
                             FacesMessage.SEVERITY_ERROR, "", desc);
        FacesContext.getCurrentInstance().addMessage(null, msg);
        return null;
    }

    public String delPayAction() {
        OperationBinding operation = ADFUtils.findOperation("callDelPayProc");
        Map result = (Map)operation.execute();
        String code = (String)result.get("res_code");
        String desc = (String)result.get("res_desc");

        /**if  code 793 then info else error*/
        FacesMessage msg =
            new FacesMessage(code.equals("793") ? FacesMessage.SEVERITY_INFO :
                             FacesMessage.SEVERITY_ERROR, "", desc);
        FacesContext.getCurrentInstance().addMessage(null, msg);

        return null;
    }

    public void setNotifText(String notifText) {
        this.notifText = notifText;
    }

    public String getNotifText() {
        OperationBinding op =
            ADFUtils.findOperation("callCheckContractsHaveLGsProc");
        Map res = (Map)op.execute();

        return res.get("RES_DESC") != null ? (String)res.get("RES_DESC") : "";
    }
    
    public String globReturnAction() {
        ADFUtils.cancelChangesCurrentRow(iterName);
        return "back";
    }
    
    public String globCancelAction() {
        int operator = ADFUtils.getEntityStatus(iterName);
        ADFUtils.cancelChangesCurrentRow(iterName);
        if (operator == 0) {
            return "back";
        }
        return null;
    }
}
