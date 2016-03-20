package ae.payment.model.pojo;

public class Payment {
    private static final long serialVersionUID = 1L;

    private String paymentId;       
    
    private String paymentStatus;
    
    private Boolean paymentInProgress;
    
    private PaymentRequestECR paymentRequestECR;    

    private PaymentResponseECR paymentResponseECR;
    
    private String departmentId;
    
    private String feeId;   
            
    private String serviceId;
    
    private String customerId;
    
    private String customerName;
    
    private String serviceCode;
    
    private Double amount;
    
    private String merchantId;
    
    private String terminalId;
    
    private String transactionID;

    /**
     * @return the paymentId
     */
    public String getPaymentId() {
            return paymentId;
    }

    /**
     * @param paymentId the paymentId to set
     */
    public void setPaymentId(String paymentId) {
            this.paymentId = paymentId;
    }

    /**
     * @return the paymentStatus
     */
    public String getPaymentStatus() {
            return paymentStatus;
    }

    /**
     * @param paymentStatus the paymentStatus to set
     */
    public void setPaymentStatus(String paymentStatus) {
            this.paymentStatus = paymentStatus;
    }

    /**
     * @return the paymentInProgress
     */
    public Boolean getPaymentInProgress() {
            return paymentInProgress;
    }

    /**
     * @param paymentInProgress the paymentInProgress to set
     */
    public void setPaymentInProgress(Boolean paymentInProgress) {
            this.paymentInProgress = paymentInProgress;
    }

    /**
     * @return the paymentRequestECR
     */
    public PaymentRequestECR getPaymentRequestECR() {
            return paymentRequestECR;
    }

    /**
     * @param paymentRequestECR the paymentRequestECR to set
     */
    public void setPaymentRequestECR(PaymentRequestECR paymentRequestECR) {
            this.paymentRequestECR = paymentRequestECR;
    }

    /**
     * @return the paymentResponseECR
     */
    public PaymentResponseECR getPaymentResponseECR() {
            return paymentResponseECR;
    }

    /**
     * @param paymentResponseECR the paymentResponseECR to set
     */
    public void setPaymentResponseECR(PaymentResponseECR paymentResponseECR) {
            this.paymentResponseECR = paymentResponseECR;
    }

    /**
     * @return the departmentId
     */
    public String getDepartmentId() {
            return departmentId;
    }

    /**
     * @param departmentId the departmentId to set
     */
    public void setDepartmentId(String departmentId) {
            this.departmentId = departmentId;
    }

    /**
     * @return the feeId
     */
    public String getFeeId() {
            return feeId;
    }

    /**
     * @param feeId the feeId to set
     */
    public void setFeeId(String feeId) {
            this.feeId = feeId;
    }

    /**
     * @return the serviceId
     */
    public String getServiceId() {
            return serviceId;
    }

    /**
     * @param serviceId the serviceId to set
     */
    public void setServiceId(String serviceId) {
            this.serviceId = serviceId;
    }

    /**
     * @return the customerId
     */
    public String getCustomerId() {
            return customerId;
    }

    /**
     * @param customerId the customerId to set
     */
    public void setCustomerId(String customerId) {
            this.customerId = customerId;
    }

    /**
     * @return the customerName
     */
    public String getCustomerName() {
            return customerName;
    }

    /**
     * @param customerName the customerName to set
     */
    public void setCustomerName(String customerName) {
            this.customerName = customerName;
    }

    /**
     * @return the serviceCode
     */
    public String getServiceCode() {
            return serviceCode;
    }

    /**
     * @param serviceCode the serviceCode to set
     */
    public void setServiceCode(String serviceCode) {
            this.serviceCode = serviceCode;
    }

    /**
     * @return the amount
     */
    public Double getAmount() {
            return amount;
    }

    /**
     * @param amount the amount to set
     */
    public void setAmount(Double amount) {
            this.amount = amount;
    }
    
    /**
     * @return the merchantId
     */
    public String getMerchantId() {
            return merchantId;
    }

    /**
     * @param merchantId the merchantId to set
     */
    public void setMerchantId(String merchantId) {
            this.merchantId = merchantId;
    }

    /**
     * @return the terminalId
     */
    public String getTerminalId() {
            return terminalId;
    }

    /**
     * @param terminalId the terminalId to set
     */
    public void setTerminalId(String terminalId) {
            this.terminalId = terminalId;
    }       
    
    /**
     * @return the transactionID
     */
    public String getTransactionID() {
            return transactionID;
    }

    /**
     * @param transactionID the transactionID to set
     */
    public void setTransactionID(String transactionID) {
            this.transactionID = transactionID;
    }

    @Override
    public String toString() {
            return "PaymentVO [paymentId=" + paymentId + ", paymentStatus=" + paymentStatus + ", paymentInProgress=" + paymentInProgress
                            + ", paymentRequestECR=" + paymentRequestECR + ", paymentResponseECR=" + paymentResponseECR + ", departmentId=" + departmentId
                            + ", feeId=" + feeId + ", serviceId=" + serviceId + ", customerId=" + customerId + ", customerName=" + customerName
                            + ", serviceCode=" + serviceCode + ", amount=" + amount + ", merchantId=" + merchantId + ", terminalId=" + terminalId
                            + ", transactionID=" + transactionID + "]";
    }
}
