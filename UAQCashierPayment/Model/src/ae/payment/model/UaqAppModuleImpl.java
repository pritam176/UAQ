package ae.payment.model;

import ae.payment.model.common.UaqAppModule;

import ae.payment.model.pojo.PaymentRequestECR;
import ae.payment.model.views.BrokenTransactionsViewImpl;

import ae.payment.model.views.BrokenTransactionsViewRowImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import oracle.jbo.Row;
import oracle.jbo.server.ApplicationModuleImpl;
import oracle.jbo.server.DBTransaction;
import oracle.jbo.server.SequenceImpl;
import oracle.jbo.server.ViewObjectImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Tue Feb 09 09:43:12 EET 2016
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class UaqAppModuleImpl extends ApplicationModuleImpl implements UaqAppModule {
    /**
     * This is the default constructor (do not remove).
     */
    public UaqAppModuleImpl() {
    }

    /**
     * Container's getter for CashierPaymentDetailsView1.
     * @return CashierPaymentDetailsView1
     */
    public ViewObjectImpl getCashierPaymentDetailsView1() {
        return (ViewObjectImpl)findViewObject("CashierPaymentDetailsView1");
    }

    public Number getSequenceNumber(String sequenceName) {
        try {
            SequenceImpl seq =
                new SequenceImpl(sequenceName, getDBTransaction());
            return Integer.parseInt(seq.getSequenceNumber().toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * Container's getter for BrokenTransactionsView1.
     * @return BrokenTransactionsView1
     */
    public ViewObjectImpl getBrokenTransactionsInParticularPOS() {
        return (ViewObjectImpl)findViewObject("BrokenTransactionsInParticularPOS");
    }

    /**
     * Container's getter for TransactionInProgressAnotherPOS.
     * @return TransactionInProgressAnotherPOS
     */
    public ViewObjectImpl getTransactionInProgressAnotherPOS() {
        return (ViewObjectImpl)findViewObject("TransactionInProgressAnotherPOS");
    }

    /**
     * this metod is to get previous broken transactions for current POS machine
     * @param merchantId
     * @param terminalNo
     * @return
     */
    public PaymentRequestECR getBrokenTransactionOnParticularPOS(String merchantId, String terminalNo){
        PaymentRequestECR paymentRequestECR = null;
        BrokenTransactionsViewImpl brokenTransactionsInParticularPOS = (BrokenTransactionsViewImpl)getBrokenTransactionsInParticularPOS();
        brokenTransactionsInParticularPOS.setp_merchantId(merchantId);
        brokenTransactionsInParticularPOS.setp_terminalNo(terminalNo);
        brokenTransactionsInParticularPOS.executeQuery();
        Row[] allRowsInRange = brokenTransactionsInParticularPOS.getAllRowsInRange();
        if(allRowsInRange.length > 0){
            //There is a broken transaction on this POS
            //The allRowsInRange array length is 1 as it shouldn't be more than one broken transaction on a particular POS
            // TODO : write your logic here
            BrokenTransactionsViewRowImpl row = (BrokenTransactionsViewRowImpl)allRowsInRange[0];
            paymentRequestECR = new PaymentRequestECR();
            paymentRequestECR.setEcrIdNo(row.getTransactionId());
            paymentRequestECR.setPaymentId(row.getPaymentId());
            paymentRequestECR.setTransactionType("TS");
        }
        
        return paymentRequestECR;
    }
    
    public PaymentRequestECR getPaymentInProgressOnAnotherPOS(String paymentId){
        BrokenTransactionsViewImpl transactionInProgressAnotherPOS = (BrokenTransactionsViewImpl)getTransactionInProgressAnotherPOS();
        transactionInProgressAnotherPOS.setp_paymentId(paymentId);
        transactionInProgressAnotherPOS.executeQuery();
        Row[] allRowsInRange = transactionInProgressAnotherPOS.getAllRowsInRange();
        PaymentRequestECR paymentRequestECR = null;
        if(allRowsInRange.length > 0){
            //This transaction is inprogresson another POS
            //The allRowsInRange array length is 1 as transaction shouldn't be in progress on more than one POS device
            // TODO : write your logic here
            BrokenTransactionsViewRowImpl row = (BrokenTransactionsViewRowImpl)allRowsInRange[0];
            paymentRequestECR = new PaymentRequestECR();
            paymentRequestECR.setPaymentId(row.getPaymentId());       
            paymentRequestECR.setServiceCode(row.getServiceCode());       
            paymentRequestECR.setAmount(row.getAmount().toString());  
            paymentRequestECR.setTerminalNo(row.getTerminalNo().toString());
            paymentRequestECR.setPaymentInProgress(row.getPaymentInProgress().toString());
//            paymentRequestECR.setPaymentStatus(row.getPaymentStatus());           
//            paymentRequestECR.setPaymentInProgress(row.getPaymentInProgress());
//            paymentRequestECR.setServiceId(row.getServiceId());
//            paymentRequestECR.setDepartmentId(row.getDepartmentId());
//            paymentRequestECR.setCustomerId(row.getCustomerId());
//            paymentRequestECR.setCustomerName(row.getCustomerName());   
//            paymentRequestECR.setFeeId(row.getFeeId());
            
            
            
            paymentRequestECR.setStatusCode("X0");
            paymentRequestECR.setStatusMessage("Transaction already in progress on another POS machine");
        }
        return paymentRequestECR;
    }
    
//    public PaymentRequestECR getLastTransactionStatus(String paymentId){
//        BrokenTransactionsViewImpl transactionInProgressAnotherPOS = (BrokenTransactionsViewImpl)getTransactionInProgressAnotherPOS();
//        transactionInProgressAnotherPOS.setp_paymentId(paymentId);
//        transactionInProgressAnotherPOS.executeQuery();
//        Row[] allRowsInRange = transactionInProgressAnotherPOS.getAllRowsInRange();
//        PaymentRequestECR paymentRequestECR;
//        if(allRowsInRange.length > 0){
//            //This transaction is inprogresson another POS
//            //The allRowsInRange array length is 1 as transaction shouldn't be in progress on more than one POS device
//            // TODO : write your logic here
//            BrokenTransactionsViewRowImpl row = (BrokenTransactionsViewRowImpl)allRowsInRange[0];
//            paymentRequestECR.setPaymentId(row.getPaymentId());       
//            paymentRequestECR.setServiceCode(row.getServiceCode());       
//            paymentRequestECR.setAmount(row.getAmount().toString());
//    //            paymentRequestECR.setPaymentStatus(row.getPaymentStatus());
//    //            paymentRequestECR.setPaymentInProgress(row.getPaymentInProgress());
//    //            paymentRequestECR.setServiceId(row.getServiceId());
//    //            paymentRequestECR.setDepartmentId(row.getDepartmentId());
//    //            paymentRequestECR.setCustomerId(row.getCustomerId());
//    //            paymentRequestECR.setCustomerName(row.getCustomerName());
//    //            paymentRequestECR.setFeeId(row.getFeeId());
//            
//            
//            
//            paymentRequestECR.setStatusCode("X0");
//            paymentRequestECR.setStatusMessage("Transaction already in progress on another POS machine");
//        }
//        return paymentRequestECR;
//    }
    
    public String getSequenceByDepartment(String sequenceName) {

        String query = "SELECT " + sequenceName + ".NEXTVAL FROM DUAL";
    
        String nextValue = "-1";
        PreparedStatement ps = null;

        try {

            DBTransaction dbTransaction = getDBTransaction();
            ps = dbTransaction.createPreparedStatement(query, 1);
            ResultSet rs  = ps.executeQuery();
            if(rs.next())
                nextValue = rs.getInt(1)+"";

        } catch (SQLException e) {
            //TODO: write your exception logic here
        }
        
        return nextValue;
    }

    /**
     * Container's getter for PosPaymentView1.
     * @return PosPaymentView1
     */
    public ViewObjectImpl getPosPaymentView1() {
        return (ViewObjectImpl)findViewObject("PosPaymentView1");
    }

    /**
     * Container's getter for EServicesMatrixView1.
     * @return EServicesMatrixView1
     */
    public ViewObjectImpl getEServicesMatrixView1() {
        return (ViewObjectImpl)findViewObject("EServicesMatrixView1");
    }

    /**
     * Container's getter for PaymentServiceCodeView1.
     * @return PaymentServiceCodeView1
     */
    public ViewObjectImpl getPaymentServiceCodeView1() {
        return (ViewObjectImpl)findViewObject("PaymentServiceCodeView1");
    }

    /**
     * Container's getter for PosPaymentTransactionView1.
     * @return PosPaymentTransactionView1
     */
    public ViewObjectImpl getPosPaymentTransactionView1() {
        return (ViewObjectImpl)findViewObject("PosPaymentTransactionView1");
    }
}
