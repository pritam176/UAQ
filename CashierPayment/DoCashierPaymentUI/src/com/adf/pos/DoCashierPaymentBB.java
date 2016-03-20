package com.adf.pos;

import ae.payment.model.UaqAppModuleImpl;

//import ae.payment.model.pojo.PaymentRequestECR;
//import ae.payment.model.pojo.PaymentResponseECR;
import ae.payment.model.views.EServicesMatrixViewRowImpl;

import ae.payment.model.views.PaymentServiceCodeViewRowImpl;

import com.adf.pos.utils.ADFUtils;

import com.adf.pos.utils.JSFUtils;

import com.adf.pos.utils.ReportsUtil;

import com.uaq.pos.model.IPOSService;

import com.uaq.pos.model.POSServiceImpl;

import com.uaq.pos.pojo.PaymentRequestECR;
//
//import com.uaq.pos.pojo.PaymentResponseECR;

import com.uaq.pos.util.ConfUtils;
import com.uaq.pos.util.UAQLogger;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;

import java.math.BigDecimal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.text.DateFormat;

import java.text.SimpleDateFormat;

import java.util.Date;

import java.util.HashMap;

import java.util.Locale;
import java.util.ResourceBundle;

import javax.faces.context.FacesContext;

import net.sf.jasperreports.engine.JRException;

import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.input.RichInputText;

import oracle.adf.view.rich.component.rich.layout.RichPanelFormLayout;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;


import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.context.AdfFacesContext;
import oracle.adf.view.rich.render.ClientEvent;

import oracle.binding.OperationBinding;

import oracle.bpel.services.workflow.WorkflowException;

import oracle.bpel.services.workflow.client.IWorkflowServiceClient;

import oracle.bpel.services.workflow.query.ITaskQueryService;

import oracle.bpel.services.workflow.util.WorkflowServiceUtil;
import oracle.bpel.services.workflow.verification.IWorkflowContext;

import oracle.bpel.services.workflow.worklist.adf.ADFWorklistBeanUtil;

import oracle.bpel.services.workflow.worklist.adf.InvokeActionBean;

import oracle.jbo.Row;

import org.apache.commons.lang.StringUtils;

public class DoCashierPaymentBB {
    private static transient UAQLogger logger =
        new UAQLogger(DoCashierPaymentBB.class);
    private RichPanelGroupLayout a;
    private RichInputText amountIT;
    private RichInputText ecrIdNoIT;
    private RichInputText serviceCodeIT;
    private RichInputText transactionTypeIT;
    private RichPanelFormLayout panelfrom;
    private RichInputText paymentIdNoT;
    private BigDecimal finalTotalAmount;
    private BigDecimal generalAmount;
    private InvokeActionBean invokeActionBean;
    private RichPopup receiptDetPopup;
    private String ext;
    private UaqAppModuleImpl appModule;
    private String paymentMethod;
    private String paymentReceipt;
    private String receiptNo;
    private String serviceTransactionId;
    private ResourceBundle bundle;
    private EServicesMatrixViewRowImpl feeDetails;

    private static final String EMPTY_SEQUENCE_NAME = "EMPTY_SEQUENCE_NAME";
    private static final String EMPTY_TRANSACTION_ID = "EMPTY_TRANSACTION_ID";
    private static final String EMPTY_SEQUENCE_NO = "EMPTY_SEQUENCE_NO";

    public DoCashierPaymentBB() {
        appModule =
                (UaqAppModuleImpl)ADFUtils.evaluateEL("#{data.UaqAppModuleDataControl.dataProvider}");
        Locale locale =
            FacesContext.getCurrentInstance().getViewRoot().getLocale();
        bundle =
                ResourceBundle.getBundle("docashierpaymentui.DoCashierPaymentUIBundle",
                                         locale);
    }

    public void setA(RichPanelGroupLayout a) {
        this.a = a;
    }

    public RichPanelGroupLayout getA() {
        return a;
    }

    //    public void firstAjaxCallback(ClientEvent clientEvent) {
    //        logger.enter("BPM Server Pre Transaction - First Ajax Call");
    //        System.out.println("#################################################################");
    //        String value = (String)clientEvent.getParameters().get("fvalue");
    //        JSONObject json;
    //        try {
    //            //            invokeActionBean =
    //            //                    (InvokeActionBean)JSFUtils.resolveExpression("#{requestScope.invokeActionBean}");
    //            //            invokeActionBean.setOperation(arg0)
    //            json = new JSONObject(value);
    //            String paymentID =
    //                (String)ADFUtils.getBoundAttributeValue("PaymentId");
    //            String feeID = (String)ADFUtils.getBoundAttributeValue("FeeId");
    //            String customerId =
    //                (String)ADFUtils.getBoundAttributeValue("CustomerId");
    //            String customerName =
    //                (String)ADFUtils.getBoundAttributeValue("CustomerName");
    //
    //            String merchantId = "" + (Integer)json.get("merchantID");
    //            String terminalNumber = (String)json.get("terminalID");
    //
    //
    //            System.out.println("PaymentID =" + paymentID);
    //            System.out.println("FeeID =" + feeID);
    //            System.out.println("CustomerId =" + customerId);
    //            System.out.println("CustomerName =" + customerName);
    //            System.out.println("MerchantId =" + merchantId);
    //            System.out.println("terminalNo =" + terminalNumber);
    //
                IPOSService pos = new POSServiceImpl();
    //            //Oraby: Should we make a call with null valued paymentID to check if there a proken transaction on this POS before the below call? If not when this paymentId might be null?
    //            //            PaymentRequestECR paymentRequestECR =
//                pos.preTransaction(paymentID, feeID, customerId, customerName,
//                                   terminalNumber, merchantId);
    //            //Oraby: there is no check here if this paymentRequestECR is a new object if paymentId was sent with null value and no proken transactions were found for this POS
    //            if (paymentRequestECR != null) {
    //                //                System.out.println("PaymentRequestECR = " + paymentRequestECR);
    //
    //                //                String ecrIdNo1 = paymentRequestECR.getEcrIdNo();
    //                //                String amount1 = paymentRequestECR.getAmount();
    //                //                String serviceCode1 = paymentRequestECR.getServiceCode();
    //                //                String transactionType1 =
    //                //                    paymentRequestECR.getTransactionType();
    //                //                System.out.println(ecrIdNo1);
    //                //                System.out.println(amount1);
    //                //                System.out.println(serviceCode1);
    //                //                System.out.println(transactionType1);
    //                //Changes for testing
    //
    //                this.ecrIdNoIT.setValue(ecrIdNo1);
    //                this.paymentIdNoT.setValue(paymentID);
    //
    //                AdfFacesContext.getCurrentInstance().addPartialTarget(this.ecrIdNoIT);
    //                AdfFacesContext.getCurrentInstance().addPartialTarget(this.paymentIdNoT);
    //                ADFUtils.setBoundAttributeValue("EcrIdNo", ecrIdNo1);
    //                ADFUtils.setBoundAttributeValue("Amount", amount1);
    //                ADFUtils.setBoundAttributeValue("ServiceCode", serviceCode1);
    //                ADFUtils.setBoundAttributeValue("TransactionType",
    //                                                transactionType1);
    //
    //                ADFUtils.setBoundAttributeValue("MerchantId", merchantId);
    //                ADFUtils.setBoundAttributeValue("TerminalNo", terminalNumber);
    //                logger.exit("BPM Server Pre Transaction - First Ajax Calls");
    //                ADFUtils.addScriptOnPartialRequest("submitActions(" + amount1 +
    //                                                   ",'" + ecrIdNo1 + "','" +
    //                                                   "000000-0008" + "','" +
    //                                                   transactionType1 + "');");
    //            } else {
    //
    //            }
    //
    //
    //        } catch (Exception e) {
    //            e.printStackTrace();
    //        }
    //    }

    public void newFirstAjaxCallback(ClientEvent clientEvent) {
        logger.enter("BPM Server Pre Transaction - First Ajax Call1");
        String value = (String)clientEvent.getParameters().get("fvalue");
        JSONObject json;
        try {
            json = new JSONObject(value);

            //Getting values from the ajax params and the payload
            String paymentID =
                (String)ADFUtils.getBoundAttributeValue("PaymentId");
            String feeID = (String)ADFUtils.getBoundAttributeValue("FeeId");
            String customerId =
                (String)ADFUtils.getBoundAttributeValue("CustomerId");
            String customerName =
                (String)ADFUtils.getBoundAttributeValue("CustomerName");
            String merchantId = "" + (Integer)json.get("merchantID");
            String terminalNumber = (String)json.get("terminalID");
            PaymentRequestECR paymentRequestECR =
                (PaymentRequestECR)ADFUtils.findOperation("getBrokenTransactionOnParticularPOS").execute();
            if (paymentRequestECR != null) {
                IPOSService pos = new POSServiceImpl();
                // A broken transaction where found on this POS
                // get last transaction status for current pos machine
                // TODO: getLastTransactionStatus
                paymentRequestECR =
                        pos.getLastTransactionStatus1(terminalNumber,
                                                     merchantId);
                System.err.println("broken success status ===>>> " +
                                   paymentRequestECR.getStatusCode());
                if (paymentRequestECR != null) {
                    if (paymentRequestECR.getStatusCode() != null &&
                        paymentRequestECR.getStatusCode().equalsIgnoreCase("success")) {
                        // TODO: write your logic here for bt success
                        String requestNo =
                            (String)ADFUtils.getBoundAttributeValue("RequestNumber");
                        paymentRequestECR =
                                preparePreTransactionData(feeID, paymentID,
                                                          customerName,
                                                          terminalNumber,
                                                          customerId, merchantId);
                        doPreTransactionBusiness(paymentRequestECR, feeDetails,
                                                 customerName, customerId, feeID);
                        ADFUtils.addScriptOnPartialRequest("submitActions(" +
                                                           paymentRequestECR.getAmount() +
                                                           ",'" +
                                                           paymentRequestECR.getEcrIdNo() +
                                                           "','" +
                                                           paymentRequestECR.getServiceCode() +
                                                           "','" +
                                                           paymentRequestECR.getTransactionType() +
                                                           "');");
                    } else if (paymentRequestECR.getStatusCode() != null &&
                               paymentRequestECR.getStatusCode().equalsIgnoreCase("failed")) {
                        // TODO: write your logic here for bt failure
                        JSFUtils.addFacesErrorMessage(bundle.getString("PAYMENT_FAILED"));
                    }
                }
                logger.debug("paymentRequestECR : " + paymentRequestECR);
            } else {
                PaymentRequestECR trnsInProgOnAnotherPOS =
                    (PaymentRequestECR)ADFUtils.findOperation("isPaymentInProgressOnAnotherPOS").execute();

                if (trnsInProgOnAnotherPOS != null) {
                    // There is a transaction already in progress on another POS device
                    // we don't allow a transaction on this POS which is already in progress on other POS
                    // TODO: write your logic here
                    if ("1".equals(trnsInProgOnAnotherPOS.getPaymentInProgress())) {
                        JSFUtils.addFacesErrorMessage(bundle.getString("PAY_IN_ANOTHER_POS") +
                                                      trnsInProgOnAnotherPOS.getTerminalNo() +
                                                      " and merchantId : " +
                                                      trnsInProgOnAnotherPOS.getMerchantId());
                    } else {
                    System.err.println("trnsInProgOnAnotherPOS.getPaymentStatus() ===>>>> " + trnsInProgOnAnotherPOS.getPaymentStatus());
                        if ("Completed".equalsIgnoreCase(trnsInProgOnAnotherPOS.getPaymentStatus())) {
                            JSFUtils.addFacesErrorMessage(bundle.getString("PAYMENT_DONE_BEFORE"));
                            //TODO Eid: print receipt
//                            updatePosPayment(trnsInProgOnAnotherPOS,"0","Success");
//                            updateApplicantRequestStatus("Success");
                                        ADFUtils.addScriptOnPartialRequest("submitActions(" +
                                                       trnsInProgOnAnotherPOS.getAmount() +
                                                       ",'" +
                                                       trnsInProgOnAnotherPOS.getEcrIdNo() +
                                                       "','" +
                                                       trnsInProgOnAnotherPOS.getServiceCode() +
                                                       "','" +
                                                       trnsInProgOnAnotherPOS.getTransactionType() +
                                                       "');");
                            logger.exit("BPM Server Pre Transaction - First Ajax Calls");
                        } else {
//                                        paymentRequestECR =
//                            preparePreTransactionData(feeID, paymentID,
//                                                      customerName,
//                                                      terminalNumber,
//                                                      customerId, merchantId);
//                    doPreTransactionBusiness(paymentRequestECR, feeDetails,
//                                             customerName, customerId, feeID);
                    update
                    ADFUtils.addScriptOnPartialRequest("submitActions(" +
                                                       paymentRequestECR.getAmount() +
                                                       ",'" +
                                                       paymentRequestECR.getEcrIdNo() +
                                                       "','" +
                                                       paymentRequestECR.getServiceCode() +
                                                       "','" +
                                                       paymentRequestECR.getTransactionType() +
                                                       "');");
                        }
                    }
                    logger.debug("paymentRequestECR : " + paymentRequestECR);
                } else {
                    // There is no broken transaction on this POS and this transaction is not in progress on another POS device
                    // TODO: write your logic here
                    paymentRequestECR =
                            preparePreTransactionData(feeID, paymentID,
                                                      customerName,
                                                      terminalNumber,
                                                      customerId, merchantId);
                    doPreTransactionBusiness(paymentRequestECR, feeDetails,
                                             customerName, customerId, feeID);
                    ADFUtils.addScriptOnPartialRequest("submitActions(" +
                                                       paymentRequestECR.getAmount() +
                                                       ",'" +
                                                       paymentRequestECR.getEcrIdNo() +
                                                       "','" +
                                                       paymentRequestECR.getServiceCode() +
                                                       "','" +
                                                       paymentRequestECR.getTransactionType() +
                                                       "');");
                    logger.exit("BPM Server Pre Transaction - First Ajax Calls");
                    //                    ADFUtils.addScriptOnPartialRequest("submitActions(" +
                    //                                                       paymentRequestECR.getAmount() +
                    //                                                       ",'" +
                    //                                                       paymentRequestECR.getEcrIdNo() +
                    //                                                       "','" +
                    //                                                       paymentRequestECR.getServiceCode() +
                    //                                                       "','" +
                    //                                                       paymentRequestECR.getTransactionType() +
                    //                                                       "');");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void newSecondAjaxCallback(ClientEvent clientEvent) {
        logger.enter("BPM Server Post Transaction - Second Ajax call Method");
        //JSFUtils.addFacesErrorMessage("succeed");
        IPOSService pos = new POSServiceImpl();
        String value = (String)clientEvent.getParameters().get("fvalue");
        System.out.println("value is " + value);
        JSONObject json;
        try {
            json = new JSONObject(value);
            String reponseCode = "" + (String)json.get("responseCode");
            System.out.println(reponseCode);
            if (reponseCode.equals("00")) {
                //                PaymentResponseECR paymentResponseECR =
                //                    pos.postTransaction(value);
                PaymentResponseECR paymentResponseECR =
                    pos.mapper(reponseCode);
                if (paymentResponseECR != null &&
                    paymentResponseECR.getResponseCode() != null) {
                    receiptNo = paymentResponseECR.getEcrIdNo();
                    paymentReceipt = paymentResponseECR.getTransactionId();
                    finalTotalAmount =
                            ADFUtils.getBoundAttributeValue("TotalAmount") !=
                            null ?
                            new BigDecimal(ADFUtils.getBoundAttributeValue("TotalAmount").toString()) :
                            new BigDecimal(0);
                    generalAmount =
                            ADFUtils.getBoundAttributeValue("GeneralAmount") !=
                            null ?
                            new BigDecimal(ADFUtils.getBoundAttributeValue("GeneralAmount").toString()) :
                            new BigDecimal(0);
                    saveErpData("CARD");
                    saveCashierPaymentDetails();
                    updatePosPayment(paymentResponseECR);
                    updateApplicantRequestStatus("success");
                    OperationBinding oper = ADFUtils.findOperation("Commit");
                    oper.execute();
                    for (int index = 0; index < oper.getErrors().size();
                         index++) {
                        JSFUtils.addFacesErrorMessage(bundle.getString("PAYMENT_FAILED"));
                    }
                    paymentMethod = "POS";
                    showReceiptPopup();
                    ADFUtils.addScriptOnPartialRequest("$('.POSOriginalbtn')[0].click();");
                } else {
                    JSFUtils.addFacesErrorMessage(bundle.getString("PAYMENT_FAILED"));
                }
            } else {
                //TODO Eid: handle other edirham response codes
                logger.error("BPM Server Post Transaction - Payment Error");
                System.out.println("Payment Failed");
                JSFUtils.addFacesErrorMessage(bundle.getString("PAYMENT_FAILED"));
            }
        } catch (Exception e) {
            logger.enter("Error in second Ajax call " + e.getMessage());
            JSFUtils.addFacesErrorMessage(bundle.getString("PAYMENT_FAILED"));
            e.printStackTrace();
        }
        // ADFUtils.addScriptOnPartialRequest("alert('sever side Post Call');");

        logger.exit("BPM Server Post Transaction");
    }

    private void updateApplicantRequestStatus(String status) {
        try {
            if (status != null) {
                OperationBinding oper =
                    ADFUtils.findOperation("getApplicantReqDetails");
                oper.getParamsMap().put("p_reqNo",
                                        ADFUtils.getBoundAttributeValue("RequestNumber"));
                oper.execute();
                DCIteratorBinding applicantReqIter =
                    ADFUtils.findIterator("ApplicantRequestView1Iterator");
                if (applicantReqIter != null) {
                    Row currtApplicantReqRow =
                        applicantReqIter.getCurrentRow();
                    if (currtApplicantReqRow != null) {
                        String feeTypeCode =
                            ADFUtils.getBoundAttributeValue("FeeTypeCode") !=
                            null ?
                            ADFUtils.getBoundAttributeValue("FeeTypeCode").toString() :
                            "";
                        if (status.equalsIgnoreCase("success")) {
                            if (feeTypeCode.equalsIgnoreCase("A001"))
                                currtApplicantReqRow.setAttribute("StatusId",
                                                                  "29");
                            else
                                currtApplicantReqRow.setAttribute("StatusId",
                                                                  "30");
                        } else {
                            if (feeTypeCode.equalsIgnoreCase("S001"))
                                currtApplicantReqRow.setAttribute("StatusId",
                                                                  "FAILED_ID");
                            else
                                currtApplicantReqRow.setAttribute("StatusId",
                                                                  "FAILED_ID");
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //TODO Eid: change name to proceedPosPayment

    private PaymentRequestECR doPreTransactionBusiness(PaymentRequestECR paymentRequestECR,
                                                       EServicesMatrixViewRowImpl feeDetails,
                                                       String customerName,
                                                       String customerId,
                                                       String feeId) {
        try {
            //            OperationBinding getFeeDetails =
            //                ADFUtils.findOperation("getFeeDetails");
            //            getFeeDetails.getParamsMap().put("p_feeId", feeId);
            //            getFeeDetails.execute();
            //            DCIteratorBinding eServicesMatrixView1Iterator =
            //                ADFUtils.findIterator("EServicesMatrixView1Iterator");
            //            Row[] feeDetailsRows =
            //                eServicesMatrixView1Iterator.getAllRowsInRange();
            //            if (feeDetailsRows != null && feeDetailsRows.length > 0) {
            //                EServicesMatrixViewRowImpl feeDetails =
            //                    (EServicesMatrixViewRowImpl)feeDetailsRows[0];
            //                String sequenceResponse = getServiceSeqName(feeDetails);
            //                // TODO Eid: what are these values X2, X3,....etc
            //                if (sequenceResponse.equals(EMPTY_SEQUENCE_NAME)) {
            //                    paymentRequestECR.setStatusCode("X2");
            //                    paymentRequestECR.setStatusMessage("Empty Sequence Name");
            //                } else if (sequenceResponse.equals(EMPTY_TRANSACTION_ID)) {
            //                    paymentRequestECR.setStatusCode("X3");
            //                    paymentRequestECR.setStatusMessage("Empty transaction id");
            //                } else if (sequenceResponse.equals(EMPTY_SEQUENCE_NO)) {
            //                    paymentRequestECR.setStatusCode("X4");
            //                    paymentRequestECR.setStatusMessage("Empty sequence no");
            //                } else {
            //                    OperationBinding getPaymentServiceCode =
            //                        ADFUtils.findOperation("getPaymentServiceCode");
            //                    getPaymentServiceCode.getParamsMap().put("p_serviceId",
            //                                                             feeDetails.getServiceId());
            //                    getPaymentServiceCode.execute();
            //                    Row[] paymentServiceCodeRows =
            //                        ADFUtils.findIterator("PaymentServiceCodeView1Iterator").getAllRowsInRange();
            //                    if (paymentServiceCodeRows != null &&
            //                        paymentServiceCodeRows.length > 0) {
            //                        PaymentServiceCodeViewRowImpl paymentServiceCode =
            //                            (PaymentServiceCodeViewRowImpl)paymentServiceCodeRows[0];
            //                        paymentRequestECR.setTransactionType("01");
            //                        paymentRequestECR.setPaymentId(paymentId);
            //                        paymentRequestECR.setServiceCode(paymentServiceCode.getServiceCode());
            //                        paymentRequestECR.setAmount(feeDetails.getAmount().toString());
            //                        paymentRequestECR.setEcrIdNo(StringUtils.leftPad(sequenceResponse,
            //                                                                         16,
            //                                                                         "0")); // max length 16, small is left padded with 0s
            //                        paymentRequestECR.setTerminalNo(terminalNumber);
            //                        paymentRequestECR.setMerchantId(merchantId);

            insertPosPayment(paymentRequestECR, feeDetails, customerName,
                             customerId, feeId);
            insertPosPaymentTransaction(paymentRequestECR);
            OperationBinding oper = ADFUtils.findOperation("Commit");
            oper.execute();
            for (int index = 0; index < oper.getErrors().size(); index++) {
                JSFUtils.addFacesErrorMessage(bundle.getString("PAYMENT_FAILED"));
                System.err.println("error while saving pos data ===>>> " +
                                   oper.getErrors().get(index));
            }
            //                        ADFUtils.addScriptOnPartialRequest("submitActions(" +
            //                                                           paymentRequestECR.getAmount() +
            //                                                           ",'" +
            //                                                           paymentRequestECR.getEcrIdNo() +
            //                                                           "','" +
            //                                                           paymentRequestECR.getServiceCode() +
            //                                                           "','" +
            //                                                           paymentRequestECR.getTransactionType() +
            //                                                           "');");
            //                    } else {
            //                        //TODO: write the exception logic if paymentServiceCodeView1Iterator.getAllRowsInRange().length = 0
            //                        JSFUtils.addFacesInformationMessage(bundle.getString("NO_SERVICE_PAYMENT_CODE"));
            //                    }
            //                }
            //            } else {
            //                //TODO: write the exception logic if eServicesMatrixView1Iterator.getAllRowsInRange().length = 0
            //                JSFUtils.addFacesInformationMessage(bundle.getString("NO_FEE_DETAILS_IN_MATRIX"));
            //            }
        } catch (Exception e) {
            // TODO: Add catch code
            e.printStackTrace();
        }
        return paymentRequestECR;
    }

    private void insertPosPayment(PaymentRequestECR paymentRequestECR,
                                  EServicesMatrixViewRowImpl feeDetails,
                                  String customerName, String customerId,
                                  String feeId) {
        try {
            // Inserting into the POS_PAYMENT
            ADFUtils.findOperation("InsertPosPayment").execute();
            DCIteratorBinding posPaymentVIter =
                ADFUtils.findIterator("PosPaymentView1Iterator");
            if (posPaymentVIter != null) {
                Row newPosPaymentRow = posPaymentVIter.getCurrentRow();
                newPosPaymentRow.setAttribute("PaymentId",
                                              paymentRequestECR.getPaymentId());
                newPosPaymentRow.setAttribute("PaymentStatus", "approved");
                newPosPaymentRow.setAttribute("ServiceCode",
                                              paymentRequestECR.getServiceCode());
                newPosPaymentRow.setAttribute("Amount",
                                              paymentRequestECR.getAmount());
                newPosPaymentRow.setAttribute("PaymentInProgress", "0");
                newPosPaymentRow.setAttribute("ServiceId",
                                              feeDetails.getServiceId());
                newPosPaymentRow.setAttribute("DepartmentId",
                                              feeDetails.getDepartmentCode());
                newPosPaymentRow.setAttribute("FeeId", feeId);
                newPosPaymentRow.setAttribute("CustomerId", customerId);
                newPosPaymentRow.setAttribute("CustomerName", customerName);
                //                newPosPaymentRow.setAttribute("ReceiptId",
                //                                              paymentRequestECR.getEcrIdNo());
                newPosPaymentRow.setAttribute("RequestNumber",
                                              ADFUtils.getBoundAttributeValue("RequestNumber"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updatePosPayment(PaymentResponseECR paymentResponseECR, String paymentInProgress, String paymentStatus) {
        try {
            //TODO get pos payment by paymentID
            DCIteratorBinding posPaymentVIter =
                ADFUtils.findIterator("PosPaymentView1Iterator");
            if (posPaymentVIter != null) {
                Row currPosPaymentRow = posPaymentVIter.getCurrentRow();
                if (currPosPaymentRow != null) {
                    // update pos payment
                    currPosPaymentRow.setAttribute("PaymentInProgress", paymentInProgress);
                    currPosPaymentRow.setAttribute("ReceiptId",
                                                   serviceTransactionId);
                    currPosPaymentRow.setAttribute("PaymentStatus",
                                                   paymentStatus);
                }
            }

            // update pos payment transactions
            OperationBinding oper =
                ADFUtils.findOperation("getPaymentTransByTransId");
            oper.getParamsMap().put("p_transId",
                                    paymentResponseECR.getEcrIdNo());
            oper.execute();
            DCIteratorBinding posPaymentTransVIter =
                ADFUtils.findIterator("PosPaymentTransactionView1Iterator");
            if (posPaymentTransVIter != null) {
                Row currPosPaymentTransRow =
                    posPaymentTransVIter.getCurrentRow();
                if (currPosPaymentTransRow != null) {
                    // Inserting into the POS_PAYMENT_TRANSACTION
                    currPosPaymentTransRow.setAttribute("ResponseCode",
                                                        paymentResponseECR.getResponseCode());
                    currPosPaymentTransRow.setAttribute("ResponseMessage",
                                                        paymentResponseECR.getResponseMessage());
                    currPosPaymentTransRow.setAttribute("PosTransactionId",
                                                        paymentResponseECR.getTransactionId());
                    currPosPaymentTransRow.setAttribute("RetrievalRefNo",
                                                        paymentResponseECR.getRetrievalRefNo());
                    currPosPaymentTransRow.setAttribute("ApprovalCode",
                                                        paymentResponseECR.getApprovalCode());
                    currPosPaymentTransRow.setAttribute("ServicesCount",
                                                        paymentResponseECR.getServicesCount());
                    currPosPaymentTransRow.setAttribute("Service1Amount",
                                                        paymentResponseECR.getService1Amount());
                    currPosPaymentTransRow.setAttribute("Service1Fee1Amount",
                                                        paymentResponseECR.getService1Fee1Amount());
                    currPosPaymentTransRow.setAttribute("Service1Fee1Name",
                                                        paymentResponseECR.getService1Fee1Name());
                    currPosPaymentTransRow.setAttribute("DynamicFeeCount",
                                                        paymentResponseECR.getDynamicFeeCount());
                    currPosPaymentTransRow.setAttribute("DynamicFee1Amount",
                                                        paymentResponseECR.getDynamicFee1Amount());
                    currPosPaymentTransRow.setAttribute("DynamicFee1Text",
                                                        paymentResponseECR.getDynamicFee1Text());
                    currPosPaymentTransRow.setAttribute("DynamicFee2Amount",
                                                        paymentResponseECR.getDynamicFee2Amount());
                    currPosPaymentTransRow.setAttribute("DynamicFee2Text",
                                                        paymentResponseECR.getDynamicFee2Text());
                    currPosPaymentTransRow.setAttribute("DynamicFee3Amount",
                                                        paymentResponseECR.getDynamicFee3Amount());
                    currPosPaymentTransRow.setAttribute("DynamicFee3Text",
                                                        paymentResponseECR.getDynamicFee3Text());
                    currPosPaymentTransRow.setAttribute("InvoiceNo",
                                                        paymentResponseECR.getInvoiceNo());
                    currPosPaymentTransRow.setAttribute("TransactionDate",
                                                        paymentResponseECR.getTransactionResponseDate());
                    currPosPaymentTransRow.setAttribute("AutoupdateStatus",
                                                        paymentResponseECR.getAutoUpdateStatus());
                    currPosPaymentTransRow.setAttribute("AutoupdateStatusMsg",
                                                        paymentResponseECR.getAutoUpdateStatusMessage());
                    currPosPaymentTransRow.setAttribute("Other",
                                                        paymentResponseECR.getOther());
                    currPosPaymentTransRow.setAttribute("LastModifiedDate",
                                                        paymentResponseECR.getPosDate());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void insertPosPaymentTransaction(PaymentRequestECR paymentRequestECR) {
        try {
            // Inserting into the POS_PAYMENT_TRANSACTION
            ADFUtils.findOperation("InsertPosPaymentTrans").execute();
            DCIteratorBinding posPaymentTransVIter =
                ADFUtils.findIterator("PosPaymentTransactionView1Iterator");
            if (posPaymentTransVIter != null) {
                Row newPosPaymentTransRow =
                    posPaymentTransVIter.getCurrentRow();
                // Inserting into the POS_PAYMENT_TRANSACTION
                newPosPaymentTransRow.setAttribute("TransactionId",
                                                   paymentRequestECR.getEcrIdNo());
                newPosPaymentTransRow.setAttribute("TransactionType", "01");
                newPosPaymentTransRow.setAttribute("PaymentId",
                                                   paymentRequestECR.getPaymentId());
                newPosPaymentTransRow.setAttribute("TerminalNo",
                                                   paymentRequestECR.getTerminalNo());
                newPosPaymentTransRow.setAttribute("MerchantId",
                                                   paymentRequestECR.getMerchantId());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getServiceSeqName(EServicesMatrixViewRowImpl feeDetail) {
        String sequenceName =
            ConfUtils.getValue("SEQ_" + feeDetail.getServiceId());
        String ENV_ID =
            ConfUtils.getValue("environment.id"); // dev, staging, prod
        if (sequenceName == null || sequenceName.isEmpty()) {
            logger.error("Null Sequence Name");
            return EMPTY_SEQUENCE_NAME;
        }

        if (ENV_ID == null || ENV_ID.isEmpty()) {
            logger.error("Null tran id");
            return EMPTY_TRANSACTION_ID;
        }

        //Get Department  Name For Service
        String department = "E" + ENV_ID + feeDetail.getDepartmentCode();

        String transactionIDseq = department + "0000";
        OperationBinding operation =
            ADFUtils.findOperation("getSequenceNumber1");
        operation.getParamsMap().put("sequenceName", sequenceName);
        String sequenceID = (String)operation.execute();

        if (sequenceID == null || sequenceID.equals("-1")) {
            logger.error("sequenceID is Null");
            return EMPTY_SEQUENCE_NO;
        }

        return transactionIDseq += sequenceID;
    }


    public void setAmountIT(RichInputText amountIT) {
        this.amountIT = amountIT;
    }

    public RichInputText getAmountIT() {
        return amountIT;
    }

    public void setEcrIdNoIT(RichInputText ecrIdNoIT) {
        this.ecrIdNoIT = ecrIdNoIT;
    }

    public RichInputText getEcrIdNoIT() {
        return ecrIdNoIT;
    }

    public void setServiceCodeIT(RichInputText serviceCodeIT) {
        this.serviceCodeIT = serviceCodeIT;
    }

    public RichInputText getServiceCodeIT() {
        return serviceCodeIT;
    }

    public void setTransactionTypeIT(RichInputText transactionTypeIT) {
        this.transactionTypeIT = transactionTypeIT;
    }

    public RichInputText getTransactionTypeIT() {
        return transactionTypeIT;
    }

    public void setPanelfrom(RichPanelFormLayout panelfrom) {
        this.panelfrom = panelfrom;
    }

    public RichPanelFormLayout getPanelfrom() {
        return panelfrom;
    }

    //    public void secondAjaxCallback(ClientEvent clientEvent) {
    //        logger.enter("BPM Server Post Transaction - Second Ajax call Method");
    //        //JSFUtils.addFacesErrorMessage("succeed");
    //        IPOSService pos = new POSServiceImpl();
    //        String value = (String)clientEvent.getParameters().get("fvalue");
    //        System.out.println("value is " + value);
    //        JSONObject json;
    //        try {
    //            json = new JSONObject(value);
    //            String reponseCode = "" + (String)json.get("responseCode");
    //            System.out.println(reponseCode);
    //            if (reponseCode.equals("00")) {
    //                PaymentResponseECR paymentResponseECR =
    //                    pos.postTransaction(value);
    //                System.out.println(paymentResponseECR);
    //                if (paymentResponseECR != null) {
    //                    System.out.println("I am in Payment response Page");
    //                    System.out.println("Reciept Number --> " +
    //                                       paymentResponseECR.getEcrIdNo());
    //                    System.out.println("Payment Reciept --> " +
    //                                       paymentResponseECR.getTransactionId());
    //                    receiptNo = paymentResponseECR.getEcrIdNo();
    //                    paymentReceipt = paymentResponseECR.getTransactionId();
    //                    //                    ADFUtils.setBoundAttributeValue("EcrIdNo",
    //                    //                                                    paymentResponseECR.getEcrIdNo());
    //                    //                    ADFUtils.setBoundAttributeValue("PaymentId",
    //                    //                                                    paymentResponseECR.getTransactionId());
    //                    //                    this.ecrIdNoIT.setValue(paymentResponseECR.getEcrIdNo());
    //                    //                    this.paymentIdNoT.setValue(paymentResponseECR.getTransactionId());
    //                    //                    AdfFacesContext.getCurrentInstance().addPartialTarget(this.ecrIdNoIT);
    //                    //                    AdfFacesContext.getCurrentInstance().addPartialTarget(this.paymentIdNoT);
    //                }
    //                finalTotalAmount =
    //                        ADFUtils.getBoundAttributeValue("TotalAmount") !=
    //                        null ?
    //                        new BigDecimal(ADFUtils.getBoundAttributeValue("TotalAmount").toString()) :
    //                        new BigDecimal(0);
    //                generalAmount =
    //                        ADFUtils.getBoundAttributeValue("GeneralAmount") !=
    //                        null ?
    //                        new BigDecimal(ADFUtils.getBoundAttributeValue("GeneralAmount").toString()) :
    //                        new BigDecimal(0);
    //                insertCashierPaymentDetails();
    //                paymentMethod = "POS";
    //                //                invokeActionBean.invokeOperation();
    //                showReceiptPopup();
    //                ADFUtils.addScriptOnPartialRequest("$('.POSOriginalbtn')[0].click();");
    //            } else {
    //                logger.error("BPM Server Post Transaction - Payment Error");
    //                System.out.println("Payment Failed");
    //                JSFUtils.addFacesErrorMessage(bundle.getString("PAYMENT_FAILED"));
    //            }
    //        } catch (Exception e) {
    //            logger.enter("Error in second Ajax call " + e.getMessage());
    //            JSFUtils.addFacesErrorMessage(bundle.getString("PAYMENT_FAILED"));
    //            e.printStackTrace();
    //        }
    //        // ADFUtils.addScriptOnPartialRequest("alert('sever side Post Call');");
    //
    //        logger.exit("BPM Server Post Transaction");
    //    }

    public String doCashPayment() {
        try {
            finalTotalAmount =
                    ADFUtils.getBoundAttributeValue("TotalAmount") != null ?
                    new BigDecimal(ADFUtils.getBoundAttributeValue("TotalAmount").toString()) :
                    new BigDecimal(0);
            generalAmount =
                    ADFUtils.getBoundAttributeValue("GeneralAmount") != null ?
                    new BigDecimal(ADFUtils.getBoundAttributeValue("GeneralAmount").toString()) :
                    new BigDecimal(0);
            finalTotalAmount =
                    finalTotalAmount.subtract(new BigDecimal(3)).subtract(generalAmount);
            saveErpData("CASH");
            saveCashierPaymentDetails();
            updateApplicantRequestStatus("success");
            OperationBinding oper = ADFUtils.findOperation("Commit");
            oper.execute();
            for (int index = 0; index < oper.getErrors().size(); index++) {
                JSFUtils.addFacesErrorMessage(bundle.getString("PAYMENT_FAILED"));
            }
            paymentMethod = "CASH";
            invokeActionBean =
                    (InvokeActionBean)JSFUtils.resolveExpression("#{requestScope.invokeActionBean}");
            invokeActionBean.invokeOperation();
            showReceiptPopup();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private void saveErpData(String receiptMethodType) {
        try {
            DCIteratorBinding uaqESrvcIntVIter =
                ADFUtils.findIterator("XxUaqEserviceIntegTblView1Iterator");
            if (uaqESrvcIntVIter != null) {
                ADFUtils.findOperation("InsertErpData").execute();
                Row newErpRow = uaqESrvcIntVIter.getCurrentRow();
                if (newErpRow != null) {
                    String specificDeptCode =
                        ADFUtils.getBoundAttributeValue("specificDeptCode") !=
                        null ?
                        ADFUtils.getBoundAttributeValue("specificDeptCode").toString() :
                        "";
                    newErpRow.setAttribute("CurrencyCode", "AED");
                    newErpRow.setAttribute("DepartmentCode",
                                           ADFUtils.getBoundAttributeValue("specificDeptCode"));
                    newErpRow.setAttribute("ReceiptMethodType",
                                           receiptMethodType);
                    newErpRow.setAttribute("FeesId",
                                           ADFUtils.getBoundAttributeValue("SpecificFeeId"));
                    newErpRow.setAttribute("CustomerId",
                                           ADFUtils.getBoundAttributeValue("Accountid"));
                    newErpRow.setAttribute("CustomerName",
                                           ADFUtils.getBoundAttributeValue("FirstName"));
                    String feeTypeCode =
                        ADFUtils.getBoundAttributeValue("FeeTypeCode").toString();
                    if (feeTypeCode.equalsIgnoreCase("A001"))
                        newErpRow.setAttribute("Amount",
                                               ADFUtils.getBoundAttributeValue("ApplicationAmount").toString());
                    else if (feeTypeCode.equalsIgnoreCase("S001"))
                        newErpRow.setAttribute("Amount",
                                               ADFUtils.getBoundAttributeValue("ServiceAmount").toString());
                    serviceTransactionId =
                            getServiceTransactionId(specificDeptCode);
                    newErpRow.setAttribute("ServiceTransactionId",
                                           serviceTransactionId);
                    OperationBinding oper = ADFUtils.findOperation("Commit1");
                    oper.execute();
                    for (int index = 0; index < oper.getErrors().size();
                         index++) {
                        JSFUtils.addFacesErrorMessage(bundle.getString("ERP_COMMIT_ERROR"));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getServiceTransactionId(String specificDeptCode) {
        try {
            if (!"".equals(specificDeptCode)) {
                String srvcTransactionId = "E3" + specificDeptCode + "0000";
                Number seqForSelectedDept = 0;
                OperationBinding getDeptSeqOper =
                    ADFUtils.findOperation("getSequenceNumber");
                if (specificDeptCode.equals("010"))
                    getDeptSeqOper.getParamsMap().put("sequenceName",
                                                      "PS_SEQ_TRAN");
                else if (specificDeptCode.equals("003"))
                    getDeptSeqOper.getParamsMap().put("sequenceName",
                                                      "LP_SEQ_TRAN");
                seqForSelectedDept = (Number)getDeptSeqOper.execute();
                srvcTransactionId += seqForSelectedDept;
                return srvcTransactionId;
            } else
                return "";
        } catch (Exception e) {
            JSFUtils.addFacesErrorMessage(bundle.getString("PAYMENT_FAILED"));
            e.printStackTrace();
        }
        return null;
    }

    private void saveCashierPaymentDetails() {
        try {
            DCIteratorBinding uaqESrvcIntVIter =
                ADFUtils.findIterator("CashierPaymentDetailsView1Iterator");
            if (uaqESrvcIntVIter != null) {
                ADFUtils.findOperation("InsertCashierPymnt").execute();
                Row newCashPayRow = uaqESrvcIntVIter.getCurrentRow();
                if (newCashPayRow != null) {
                    String requestNo =
                        ADFUtils.getBoundAttributeValue("RequestNumber").toString();
                    newCashPayRow.setAttribute("UserId",
                                               ADFUtils.getBoundAttributeValue("Username"));
                    newCashPayRow.setAttribute("RequestNo", requestNo);
                    newCashPayRow.setAttribute("ReqId",
                                               getReqIDFromReqNo(requestNo));
                    String feeTypeCode =
                        ADFUtils.getBoundAttributeValue("FeeTypeCode").toString();
                    if (feeTypeCode.equalsIgnoreCase("S001")) {
                        newCashPayRow.setAttribute("TypeOfPaymentId", "1");
                        newCashPayRow.setAttribute("Amount",
                                                   ADFUtils.getBoundAttributeValue("ServiceAmount").toString());
                    } else {
                        newCashPayRow.setAttribute("TypeOfPaymentId", "2");
                        newCashPayRow.setAttribute("Amount",
                                                   ADFUtils.getBoundAttributeValue("ApplicationAmount").toString());
                    }
                    newCashPayRow.setAttribute("Status", "21");
                    newCashPayRow.setAttribute("ServiceId",
                                               ADFUtils.getBoundAttributeValue("FirstName"));
                    newCashPayRow.setAttribute("Createdby", getLoggedInUser());
                    newCashPayRow.setAttribute("Modifiedby",
                                               getLoggedInUser());
                    newCashPayRow.setAttribute("ServiceId",
                                               ADFUtils.getBoundAttributeValue("ServiceId"));

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getLoggedInUser() throws WorkflowException {
        try {
            String userId = "";
            IWorkflowServiceClient wfSvcClient;
            ITaskQueryService queryService;
            IWorkflowContext wfContext;
            // Get username of User Login
            String contextStr = ADFWorklistBeanUtil.getWorklistContextId();
            wfSvcClient = WorkflowServiceUtil.getWorkflowServiceClient();
            queryService = wfSvcClient.getTaskQueryService();
            wfContext = queryService.getWorkflowContext(contextStr);
            userId = wfContext.getUser();
            return userId;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private int getReqIDFromReqNo(String requestNo) {
        try {
            String reqId = requestNo.substring(requestNo.lastIndexOf("-") + 1);
            return Integer.parseInt(reqId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void setPaymentIdNoT(RichInputText paymentIdNoT) {
        this.paymentIdNoT = paymentIdNoT;
    }

    public RichInputText getPaymentIdNoT() {
        return paymentIdNoT;
    }

    private String showReceiptPopup() {
        try {
            ADFUtils.showPopup(getReceiptDetPopup().getId());
            return null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void generateReceiptRPT(FacesContext facesContext,
                                   OutputStream outputStream) throws FileNotFoundException,
                                                                     JRException,
                                                                     IOException,
                                                                     Exception {
        try {
            String reportName = "PaymentReceipt";
            HashMap params = new HashMap();
            String title = null;
            String paymentType = null;
            BigDecimal eDirhamServices = new BigDecimal(0);
            BigDecimal eServiceFees = new BigDecimal(0);
            BigDecimal transactionFees =
                ADFUtils.getBoundAttributeValue("ServiceAmount") != null ?
                new BigDecimal(ADFUtils.getBoundAttributeValue("ServiceAmount").toString()) :
                new BigDecimal(0);
            String feeTypeCode =
                ADFUtils.getBoundAttributeValue("FeeTypeCode") != null ?
                ADFUtils.getBoundAttributeValue("FeeTypeCode").toString() : "";
            if (!"".equals(paymentMethod))
                if (paymentMethod.equalsIgnoreCase("POS")) {
                    paymentType = "EDirham";
                    eServiceFees = generalAmount;
                    eDirhamServices = new BigDecimal(3);
                } else {
                    paymentType = "Cash";
                    eDirhamServices = new BigDecimal(0);
                    eServiceFees = new BigDecimal(0);
                    paymentReceipt = "N/A";
                    receiptNo = serviceTransactionId;
                }
            if (!feeTypeCode.equals(""))
                if (feeTypeCode.equalsIgnoreCase("S001"))
                    title = "Service Fees";
                else
                    title = "Application Fees";
            params.put("P_APPLICANT_NAME",
                       ADFUtils.getBoundAttributeValue("FirstName"));
            params.put("P_TRANSACTION_FEES", transactionFees);
            params.put("P_EDIRHAM_SERVICE_FEES", eDirhamServices);
            params.put("P_E_SERVICE_FEES", eServiceFees);
            params.put("P_TOTAL_FEES", finalTotalAmount);
            params.put("P_TITLE", title);
            params.put("P_AMOUNT", finalTotalAmount);
            params.put("P_PAYMENT_TYPE", paymentType);
            params.put("P_PAYMENT_RECEIPT", paymentReceipt);
            params.put("P_EMP_NAME", getLoggedInUser());
            params.put("P_RECEIPT_NO", receiptNo);
            String serviceId =
                ADFUtils.getBoundAttributeValue("ServiceId") != null ?
                ADFUtils.getBoundAttributeValue("ServiceId").toString() :
                "502";
            if (ext.equalsIgnoreCase("PDF"))
                ReportsUtil.GenerateReportPDF(params, reportName, false, true,
                                              ReportsUtil.getAppModuleConnection(appModule),
                                              outputStream, serviceId);
            else
                ReportsUtil.GenerateReportExcel(params, reportName, false,
                                                true,
                                                ReportsUtil.getAppModuleConnection(appModule),
                                                outputStream, serviceId);
        } catch (Exception e) {
            JSFUtils.addFacesErrorMessage(bundle.getString("RECEIPT_PRINT_ERROR"));
            e.printStackTrace();
        }
    }


    public void setReceiptDetPopup(RichPopup receiptDetPopup) {
        this.receiptDetPopup = receiptDetPopup;
    }

    public RichPopup getReceiptDetPopup() {
        return receiptDetPopup;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }

    public String getExt() {
        return ext;
    }

    public void setFinalTotalAmount(BigDecimal finalTotalAmount) {
        this.finalTotalAmount = finalTotalAmount;
    }

    public BigDecimal getFinalTotalAmount() {
        return finalTotalAmount;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public String moveTask() {
        invokeActionBean =
                (InvokeActionBean)JSFUtils.resolveExpression("#{requestScope.invokeActionBean}");
        invokeActionBean.invokeOperation();
        showReceiptPopup();
        return null;
    }

    private PaymentRequestECR preparePreTransactionData(String feeId,
                                                        String paymentId,
                                                        String customerName,
                                                        String terminalNumber,
                                                        String customerId,
                                                        String merchantId) {
        PaymentRequestECR paymentRequestECR = new PaymentRequestECR();
        try {
            OperationBinding getFeeDetails =
                ADFUtils.findOperation("getFeeDetails");
            getFeeDetails.getParamsMap().put("p_feeId", feeId);
            getFeeDetails.execute();
            DCIteratorBinding eServicesMatrixView1Iterator =
                ADFUtils.findIterator("EServicesMatrixView1Iterator");
            Row[] feeDetailsRows =
                eServicesMatrixView1Iterator.getAllRowsInRange();
            if (feeDetailsRows != null && feeDetailsRows.length > 0) {
                feeDetails = (EServicesMatrixViewRowImpl)feeDetailsRows[0];
                String sequenceResponse = getServiceSeqName(feeDetails);
                // TODO Eid: what are these values X2, X3,....etc
                if (sequenceResponse.equals(EMPTY_SEQUENCE_NAME)) {
                    paymentRequestECR.setStatusCode("X2");
                    paymentRequestECR.setStatusMessage("Empty Sequence Name");
                } else if (sequenceResponse.equals(EMPTY_TRANSACTION_ID)) {
                    paymentRequestECR.setStatusCode("X3");
                    paymentRequestECR.setStatusMessage("Empty transaction id");
                } else if (sequenceResponse.equals(EMPTY_SEQUENCE_NO)) {
                    paymentRequestECR.setStatusCode("X4");
                    paymentRequestECR.setStatusMessage("Empty sequence no");
                } else {
                    OperationBinding getPaymentServiceCode =
                        ADFUtils.findOperation("getPaymentServiceCode");
                    getPaymentServiceCode.getParamsMap().put("p_serviceId",
                                                             feeDetails.getServiceId());
                    getPaymentServiceCode.execute();
                    Row[] paymentServiceCodeRows =
                        ADFUtils.findIterator("PaymentServiceCodeView1Iterator").getAllRowsInRange();
                    if (paymentServiceCodeRows != null &&
                        paymentServiceCodeRows.length > 0) {
                        PaymentServiceCodeViewRowImpl paymentServiceCode =
                            (PaymentServiceCodeViewRowImpl)paymentServiceCodeRows[0];
                        paymentRequestECR.setTransactionType("01");
                        paymentRequestECR.setPaymentId(paymentId);
                        paymentRequestECR.setServiceCode(paymentServiceCode.getServiceCode());
                        paymentRequestECR.setAmount(feeDetails.getAmount().toString());
                        paymentRequestECR.setEcrIdNo(StringUtils.leftPad(sequenceResponse,
                                                                         16,
                                                                         "0")); // max length 16, small is left padded with 0s
                        paymentRequestECR.setTerminalNo(terminalNumber);
                        paymentRequestECR.setMerchantId(merchantId);
                        doPreTransactionBusiness(paymentRequestECR, feeDetails,
                                                 customerName, customerId,
                                                 feeId);

                        //                                        insertPosPayment(paymentRequestECR, feeDetails,
                        //                                                         customerName, customerId, feeId);
                        //                                        insertPosPaymentTransaction(paymentRequestECR);
                        //                                        OperationBinding oper =
                        //                                            ADFUtils.findOperation("Commit");
                        //                                        oper.execute();
                        //                                        for (int index = 0; index < oper.getErrors().size();
                        //                                             index++) {
                        //                                            JSFUtils.addFacesErrorMessage(bundle.getString("PAYMENT_FAILED"));
                        //                                            System.err.println("error while saving pos data ===>>> " +
                        //                                                               oper.getErrors().get(index));
                        //                                        }
                        ADFUtils.addScriptOnPartialRequest("submitActions(" +
                                                           paymentRequestECR.getAmount() +
                                                           ",'" +
                                                           paymentRequestECR.getEcrIdNo() +
                                                           "','" +
                                                           paymentRequestECR.getServiceCode() +
                                                           "','" +
                                                           paymentRequestECR.getTransactionType() +
                                                           "');");
                    } else {
                        //TODO: write the exception logic if paymentServiceCodeView1Iterator.getAllRowsInRange().length = 0
                        JSFUtils.addFacesInformationMessage(bundle.getString("CHECK_WITH_SYSTEM_ADMIN"));
                    }
                }
            } else {
                //TODO: write the exception logic if eServicesMatrixView1Iterator.getAllRowsInRange().length = 0
                JSFUtils.addFacesInformationMessage(bundle.getString("CHECK_WITH_SYSTEM_ADMIN"));
            }
        } catch (Exception e) {
            // TODO: Add catch code
            e.printStackTrace();
        }
    }
}
