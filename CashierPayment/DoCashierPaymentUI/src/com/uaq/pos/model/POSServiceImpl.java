package com.uaq.pos.model;

import com.google.gson.Gson;
import com.uaq.pos.pojo.PaymentRequestECR;
import com.uaq.pos.pojo.PaymentResponseECR;
import com.uaq.pos.pojo.PaymentStatus;
import com.uaq.pos.pojo.PaymentVO;
import com.uaq.pos.service.PaymentService;
import com.uaq.pos.util.ConfUtils;
import com.uaq.pos.util.PosUtil;
import com.uaq.pos.util.UAQLogger;

/**
 * This class is used to handle pre transaction and post transaction activities for pos payment
 * @author raheem
 *
 */
public class POSServiceImpl implements IPOSService{
		
	private static transient UAQLogger logger = new UAQLogger(POSServiceImpl.class);
	
	private static final String PAYMENT_STATUS_SUCCESS= "success";
//Oraby: What is the difference between the two below status??!
	private static final String PAYMENT_STATUS_FAILURE = "failure";
        private static final String PAYMENT_STATUS_FAILED = "failed";	
	private static final String PAYMENT_STATUS_COMPLETED = "Completed";
//Oraby: Note that the below constant equals approved with all small letters while the same constant in the PamentDAO equals Approved with capital A
	private static final String PURCHASE_STATUS_APPROVED = "approved";
	private static final String RESPONSE_STATUS_SUCCESS = "00";
	private static final String GENERAL_FEE_ID = "0025031";
	
	/**
	 * This method is called by integration system to perform pre transaction activity such as db interaction.
	 */	
	@Override
	public PaymentRequestECR preTransaction(String paymentID, String feeID, String customerId, String customerName, String terminalNo, String merchantID) {
		
		logger.enter("preTransaction");		
			
		PaymentService paymentService = new PaymentService();
		
		PaymentRequestECR paymentRequestECR = new PaymentRequestECR(); 
		paymentRequestECR.setStatusCode("Z0");
    	paymentRequestECR.setStatusMessage("Unknown Error");
    	
		String lastPaymentTransactionId = null;
//Oraby: What is auto update?
		boolean isAutoUpdate = false;
		PaymentVO paymentVO = null;
	
		String status = PAYMENT_STATUS_FAILURE;
	    
	    logger.debug("paymentID = " + paymentID + " : feeId = " + feeID + 
	    		" : customerId = " + customerId + " : customerName = " + customerName + " : terminalNo = " + terminalNo + " : merchantID = " + merchantID);
	    
	    if(paymentID == null || paymentID.isEmpty()){ //auto-update or broken transaction inquiry for particular pos
	    	
	    	paymentVO = paymentService.getLastPaymentTransaction(null, terminalNo, merchantID);					
			logger.debug("lastPaymentTransaction = " + paymentVO);
//Oraby: What if paymentVO.getTransactionID() = null? that will happen if there is no proken transactions for this POS device
			if(paymentVO != null && paymentVO.getTransactionID() != null && !paymentVO.getTransactionID().isEmpty()){			
				paymentRequestECR = PosUtil.fillRequest(paymentVO, "LastTransactionStatus", null);
				paymentRequestECR.setPaymentId(paymentVO.getPaymentId());
		        logger.debug("paymentRequestECR : " + paymentRequestECR);				
			}
	    } else {
	    
		    boolean isValid = validateRequest(paymentID, feeID, customerId, customerName, terminalNo, merchantID);
			
		    if(isValid){
		    	try{
			    	boolean result = false;
//Oraby: from where this DB record with the same paymentID might be inserted?!
			    	paymentVO = paymentService.execute(paymentID);
			        logger.debug("paymentVO = " + paymentVO);
                            System.err.println("paymentVO ===>>> " + paymentVO);
//Oraby: If there is no records returned from the below query feeDetail will be a new object so feeDetail.getServiceId() used below will be null
			        PaymentVO feeDetail = paymentService.getFeeDetail(feeID);
			        logger.debug("feeDetail = " + feeDetail);
                            System.err.println("feeDetail ==>> " + feeDetail);
//Oraby: If there is no records returned from the below query paymentServiceCode will be a new object             
			        PaymentVO paymentServiceCode = paymentService.getPaymentServiceCode(feeDetail.getServiceId());
			        //only for testing. comment out on production. it leaks secret information to log file
			        logger.debug("paymentServiceCode = " + paymentServiceCode);		         
			        
			        if (paymentVO == null) // new request
			        {
			        	paymentVO = fillObj(paymentID, feeID, customerId, customerName, paymentServiceCode.getServiceCode(), 
				        		  paymentServiceCode.getServiceId(), feeDetail.getDepartmentId(), feeDetail.getAmount(), terminalNo, merchantID);
			        	logger.debug("paymentVO = " + paymentVO);
			        	
			        	result = paymentService.savePayment(paymentVO);	  		  
			    		logger.exit("savePayment result = " + result);	        	        
			        		         
				        if (result) {
				        	status = PAYMENT_STATUS_SUCCESS;
				        }
			        }
//Oraby: What is the cenario which we can find  getPaymentStatus = Completed so that the compiler will go throwgh the below block??
//Oraby: Why we set the status = failure in the below block?
			        if (paymentVO.getPaymentStatus().equals(PAYMENT_STATUS_COMPLETED)) {
			        	logger.debug("paymentVO.getPaymentInProgress() = " + paymentVO.getPaymentInProgress() + " : paymentVO.getPaymentStatus()" + 
				        		  paymentVO.getPaymentStatus());			                   
				          status = PAYMENT_STATUS_FAILURE;
//Oraby: What is the business cenario in which getPaymentInProgress might be true? seems that it's when there is a transaction record with this paymentId.
//Oraby: We should keep in mind that if the business scenario is to create a new record the getPaymentInProgress will be null so it will not go through the below code.
                                } else if ((paymentVO.getPaymentInProgress() != null) && (paymentVO.getPaymentInProgress().booleanValue())) { // in progress on other POS
				    	logger.debug("paymentVO.getPaymentInProgress() = " + paymentVO.getPaymentInProgress() + " : paymentVO.getPaymentStatus()" + 
			        		  paymentVO.getPaymentStatus());		          
				    	// we don't allow a transaction on this POS which is already in progress on other POS	
//Oraby: How we found that transaction is already in progress on other POS??
				    	paymentRequestECR.setStatusCode("X0");
				    	paymentRequestECR.setStatusMessage("Transaction already in progress on another POS machine");
				    	logger.debug("paymentRequestECR : " + paymentRequestECR);
                                        status = PAYMENT_STATUS_FAILED;
			        } else if (paymentVO.getPaymentStatus().equals(PAYMENT_STATUS_FAILED)) {
			        	logger.debug("paymentVO.getPaymentInProgress() = " + paymentVO.getPaymentInProgress() + " : paymentVO.getPaymentStatus()" + 
			        		  paymentVO.getPaymentStatus());
			        	
			        	// if failed on another pos, then we allow to pay it on this POS
			          
			          status = PAYMENT_STATUS_SUCCESS;		          
			          //repay = true;
			        } else if(paymentVO.getPaymentStatus().equals(PURCHASE_STATUS_APPROVED)) {
			          logger.debug("paymentVO.getPaymentInProgress() = " + paymentVO.getPaymentInProgress() + " : paymentVO.getPaymentStatus()" + 
			        		  paymentVO.getPaymentStatus());
			         
			          status = PAYMENT_STATUS_SUCCESS;
			        } else {  // any other case is a failure
			          logger.debug("paymentVO.getPaymentInProgress() = " + paymentVO.getPaymentInProgress() + " : paymentVO.getPaymentStatus()" + 
			        		  paymentVO.getPaymentStatus());		
			          
			          status = PAYMENT_STATUS_FAILURE;
			        }
			        
                        if (status.equals(PAYMENT_STATUS_SUCCESS)) {
			        	// to use the payment information already saved in the database and not the incoming params
			        	paymentVO = fillObj(paymentVO.getPaymentId(), paymentVO.getFeeId(), paymentVO.getCustomerId(), 
			        			  paymentVO.getCustomerName(), paymentServiceCode.getServiceCode(), 
				        		  paymentServiceCode.getServiceId(), feeDetail.getDepartmentId(), feeDetail.getAmount(), terminalNo, merchantID);
			        	if(isAutoUpdate){
//Oraby: the below variable has not been intialized yet, it is null!!
			        		paymentVO.setTransactionID(lastPaymentTransactionId);
			        	}
//Oraby: Why we update here the paymentInprogress to true??                    
			        	result = paymentService.updatePaymentInProgress(paymentID, true);
				        logger.debug("updatePaymentInProgress result : " + result);
				        
				        /**
				         * unique transaction id based on a sequence for each service belong to a department
				         * format Transaction Id = E + ‘ENV_ID’ + department Id + 0000 + db Sequence
				         * start
				         */
				        
				        //Get Sequence Name for Service
						String sequenceName = ConfUtils.getValue("SEQ_" + feeDetail.getServiceId());
						String ENV_ID = ConfUtils.getValue("environment.id"); // dev, staging, prod
						if(sequenceName == null || sequenceName.isEmpty()){
							status = PAYMENT_STATUS_FAILURE;
							logger.error("Null Sequence Name");		
							paymentRequestECR.setStatusCode("X2");
					    	paymentRequestECR.setStatusMessage("Empty Sequence Name");
							return paymentRequestECR;
						}
						
						if(ENV_ID == null || ENV_ID.isEmpty()){
							status = PAYMENT_STATUS_FAILURE;
							logger.error("Null tran id");
							paymentRequestECR.setStatusCode("X3");
					    	paymentRequestECR.setStatusMessage("Empty transaction id");
							return paymentRequestECR;
						}
				        
				      //Get Department Name For Service
						String department = "E" + ENV_ID + feeDetail.getDepartmentId();						
						String transactionIDseq = department + "0000";								
						String sequenceID	=	paymentService.getSequnceNextValue(sequenceName);
						
						if(sequenceID == null || sequenceID.isEmpty()){
							logger.error("sequenceID is Null");
							status = PAYMENT_STATUS_FAILURE;
							paymentRequestECR.setStatusCode("X4");
					    	paymentRequestECR.setStatusMessage("Empty sequence no");
					    	return paymentRequestECR;
						}
						
						transactionIDseq += sequenceID;
				        
				        /**
				         * unique tranaction id based on sequence
				         * end
				         */
				        
				        paymentRequestECR = PosUtil.fillRequest(paymentVO, isAutoUpdate ? "LastTransactionStatus" : "Sales", transactionIDseq);
				        logger.debug("paymentRequestECR : " + paymentRequestECR);
				        
				        if(!isAutoUpdate){ // save only for sales transaction, not for autoupdate as it has the same transaction id so cant be inserted
					        result = paymentService.savePaymentRequest(paymentRequestECR);
				    		logger.debug("savePaymentRequest :" + result);
				        }
			        }		        
		    	} catch (Exception e) {
					logger.error("preTransaction : " + e.getMessage());
				}
		    } else {
		    	// invalid parameters
		    	paymentRequestECR.setStatusCode("X1");
		    	paymentRequestECR.setStatusMessage("Invalid Parameters");
		    	logger.debug("paymentRequestECR : " + paymentRequestECR);
		    }
	    }
	    logger.exit("preTransaction");
		
		return paymentRequestECR;
	}
	
	/**
	 * This methos is used to validate all parameters of the incoming integration parameters
	 * @param paymentID
	 * @param feeID
	 * @param customerId
	 * @param customerName
	 * @param languageCode
	 * @return
	 */
	private boolean validateRequest(String paymentID, String feeID, String customerId, String customerName, String terminalNo, String merchantID) {
		
		boolean result = false;
		
		if(paymentID != null && !paymentID.isEmpty() && feeID != null && !feeID.isEmpty() && customerId != null && 
				!customerId.isEmpty() && customerName != null && !customerName.isEmpty() && terminalNo != null && !terminalNo.isEmpty() 
				&& merchantID != null && !merchantID.isEmpty()){
			
			result = true;
		}
		
		return result;
	}
		
	/**
	 * This method is used to fill object using given values
	 * @param paymentID
	 * @param feeID
	 * @param customerId
	 * @param customerName
	 * @param serviceCode
	 * @param serviceID
	 * @param departmentID
	 * @param amount
	 * @param merchantID
	 * @param terminalID
	 * @return
	 */
	private PaymentVO fillObj(String paymentID, String feeID, String customerId, String customerName, 
			String serviceCode, String serviceID, String departmentID, double amount, String terminalNo, String merchantID){
		
		PaymentVO paymentVO = new PaymentVO();		  
		  
		paymentVO.setPaymentId(paymentID);
//Oraby: What is meant by setting the PaymentStatus = approved?
		paymentVO.setPaymentStatus(PURCHASE_STATUS_APPROVED);
		paymentVO.setFeeId(feeID);
		paymentVO.setServiceId(serviceID);
		paymentVO.setCustomerId(customerId);
		paymentVO.setCustomerName(customerName);
		paymentVO.setDepartmentId(departmentID);	
		paymentVO.setServiceCode(serviceCode);		
		paymentVO.setAmount(amount);
		paymentVO.setTerminalId(terminalNo);
		paymentVO.setMerchantId(merchantID);
				
		return paymentVO;
	}

	/**
	 * This method is used to handle post transaction activities of pos payment
	 */
	@Override
	public PaymentResponseECR postTransaction(String jsonResponse) {
		
            logger.enter("postTransaction input = " + jsonResponse);
				        	
            PaymentService paymentService = new PaymentService();
            boolean result = false;
            PaymentResponseECR paymentResponseECR = new PaymentResponseECR();
    	
            if(jsonResponse != null && !jsonResponse.isEmpty()){
	    	// map response to object
                paymentResponseECR = mapper(jsonResponse);
	    	
	    	if (paymentResponseECR != null && paymentResponseECR.getResponseCode() != null){
					        	
                    String transactionID = paymentResponseECR.getEcrIdNo();
                    logger.debug("transactionID = " + transactionID + " : Status = " + paymentResponseECR.getResponseCode() + " : StatusMessage = "
						+ paymentResponseECR.getResponseMessage());
			
                    if ((transactionID != null) && (!transactionID.isEmpty())) {
                        try{
                            String paymentID = paymentService.getPaymentIdForTransactionId(transactionID);			
                            logger.debug("paymentID = " + paymentID);	
                            
                            result = paymentService.savePaymentResponse(paymentResponseECR);
                            logger.debug("savePaymentResponse :" + result);
                    
                            //mark payment txn status in progress as completed
                            result = paymentService.updatePaymentInProgress(paymentID, false);
                            logger.debug("updatePaymentInProgress :" + result);
                            
                            if (paymentResponseECR.getResponseCode().equals(RESPONSE_STATUS_SUCCESS)) {
                                    
                                // mark  purchase transaction as completed
                                result = paymentService.updatePaymentStatus(paymentID, PAYMENT_STATUS_COMPLETED);
                                logger.debug("updatePaymentStatus :" + result);
                                
                                // update SOA table
                                PaymentStatus paymentStatus = paymentService.updateApplicantRequestTableStatus(paymentID);
                                logger.debug("updateApplicantRequestTableStatus :" + paymentStatus.toString());	
                                
                                // get merchant account info to be used in filling the web payment response
                                PaymentVO paymentVO = paymentService.getPayment(paymentID);
                                
                                // update ERP table for eGD super entity general fee payment					
                                result = paymentService.updateERPtable(	paymentResponseECR.getDynamicFee3Amount(), 
                                                                        paymentVO.getDepartmentId(), paymentResponseECR.getEcrIdNo(), GENERAL_FEE_ID, paymentVO.getCustomerId(), 
                                                                        paymentVO.getCustomerName(), paymentResponseECR.getTransactionResponseDate(), 
                                                                        paymentResponseECR.getRetrievalRefNo());
                                logger.debug("updateERPtable for eGD super entity fee :" + paymentResponseECR.getDynamicFee3Amount() + " : result = " + result);
                                
                                // update ERP table for department fee payment					
                                result = paymentService.updateERPtable( paymentResponseECR.getService1Amount() - paymentResponseECR.getDynamicFee1Amount() - paymentResponseECR.getDynamicFee2Amount(), 
                                                                        paymentVO.getDepartmentId(), paymentResponseECR.getEcrIdNo(), paymentVO.getFeeId(), paymentVO.getCustomerId(), 
                                                                        paymentVO.getCustomerName(), paymentResponseECR.getTransactionResponseDate(), 
                                                                        paymentResponseECR.getRetrievalRefNo());
                                logger.debug("updateERPtable for department fee :" + paymentResponseECR.getService1Amount() + " : result = " + result);
                                    
                            }  else {
                                    result = paymentService.updatePaymentStatus(paymentID, PAYMENT_STATUS_FAILED);
                                    logger.debug("updatePaymentStatus :" + result);			
                            }	
                            
                    } catch(Exception e){
                            logger.error(e.getMessage());
                    }
				}		
	    	}
    	}
    	
    	logger.exit("postTransaction");
    	
    	return paymentResponseECR;
	}
	
	/**
	 * This method is used to map the json string to PaymentResponseECR object
	 * @param response json String
	 * @return PaymentResponseECR
	 */
	public PaymentResponseECR mapper(String response){
		
		PaymentResponseECR paymentResponseECR = new PaymentResponseECR();
		Gson gson = new Gson();
		
		if(response != null){
			paymentResponseECR = gson.fromJson(response, PaymentResponseECR.class);
		}
		
		return paymentResponseECR;		
	}


    public PaymentRequestECR preTransaction1(String paymentID, String feeID,
                                             String customerId,
                                             String customerName,
                                             String terminalNo,
                                             String merchantID) {
        logger.enter("preTransaction");         
                
        PaymentService paymentService = new PaymentService();
        
        PaymentRequestECR paymentRequestECR = new PaymentRequestECR(); 
        paymentRequestECR.setStatusCode("Z0");
        paymentRequestECR.setStatusMessage("Unknown Error");
        
        String lastPaymentTransactionId = null;
        //Oraby: What is auto update?
        boolean isAutoUpdate = false;
        PaymentVO paymentVO = null;
        
        String status = PAYMENT_STATUS_FAILURE;
        
        logger.debug("paymentID = " + paymentID + " : feeId = " + feeID +
                " : customerId = " + customerId + " : customerName = " + customerName + " : terminalNo = " + terminalNo + " : merchantID = " + merchantID);
        
        if(paymentID == null || paymentID.isEmpty()){ //auto-update or broken transaction inquiry for particular pos
        
        paymentVO = paymentService.getLastPaymentTransaction1(null, terminalNo, merchantID);                                     
                logger.debug("lastPaymentTransaction = " + paymentVO);
        //Oraby: What if paymentVO.getTransactionID() = null? that will happen if there is no proken transactions for this POS device
                if(paymentVO != null && paymentVO.getTransactionID() != null && !paymentVO.getTransactionID().isEmpty()){                       
                    paymentRequestECR = PosUtil.fillRequest(paymentVO, "LastTransactionStatus", null);
                    paymentRequestECR.setPaymentId(paymentVO.getPaymentId());
                    logger.debug("paymentRequestECR : " + paymentRequestECR);                               
                }
        } else {
        
            boolean isValid = validateRequest(paymentID, feeID, customerId, customerName, terminalNo, merchantID);
                
            if(isValid){
                try{
                        boolean result = false;
        //Oraby: from where this DB record with the same paymentID might be inserted?!
                        paymentVO = paymentService.execute(paymentID);
                        logger.debug("paymentVO = " + paymentVO);
                    System.err.println("paymentVO ===>>> " + paymentVO);
        //Oraby: If there is no records returned from the below query feeDetail will be a new object so feeDetail.getServiceId() used below will be null
                        PaymentVO feeDetail = paymentService.getFeeDetail(feeID);
                        logger.debug("feeDetail = " + feeDetail);
                    System.err.println("feeDetail ==>> " + feeDetail);
        //Oraby: If there is no records returned from the below query paymentServiceCode will be a new object
                        PaymentVO paymentServiceCode = paymentService.getPaymentServiceCode(feeDetail.getServiceId());
                        //only for testing. comment out on production. it leaks secret information to log file
                        logger.debug("paymentServiceCode = " + paymentServiceCode);                      
                        
                        if (paymentVO == null) // new request
                        {
                                paymentVO = fillObj(paymentID, feeID, customerId, customerName, paymentServiceCode.getServiceCode(), 
                                                  paymentServiceCode.getServiceId(), feeDetail.getDepartmentId(), feeDetail.getAmount(), terminalNo, merchantID);
                                logger.debug("paymentVO = " + paymentVO);
                                
                                result = paymentService.save
                                    Payment(paymentVO);                   
                                logger.exit("savePayment result = " + result);                          
                                                 
                                if (result) {
                                        status = PAYMENT_STATUS_SUCCESS;
                                }
                        }
        //Oraby: What is the cenario which we can find  getPaymentStatus = Completed so that the compiler will go throwgh the below block??
        //Oraby: Why we set the status = failure in the below block?
                        if (paymentVO.getPaymentStatus().equals(PAYMENT_STATUS_COMPLETED)) {
                                logger.debug("paymentVO.getPaymentInProgress() = " + paymentVO.getPaymentInProgress() + " : paymentVO.getPaymentStatus()" + 
                                                  paymentVO.getPaymentStatus());                                           
                                  status = PAYMENT_STATUS_FAILURE;
        //Oraby: What is the business cenario in which getPaymentInProgress might be true? seems that it's when there is a transaction record with this paymentId.
        //Oraby: We should keep in mind that if the business scenario is to create a new record the getPaymentInProgress will be null so it will not go through the below code.
                        } else if ((paymentVO.getPaymentInProgress() != null) && (paymentVO.getPaymentInProgress().booleanValue())) { // in progress on other POS
                                logger.debug("paymentVO.getPaymentInProgress() = " + paymentVO.getPaymentInProgress() + " : paymentVO.getPaymentStatus()" + 
                                          paymentVO.getPaymentStatus());                          
                                // we don't allow a transaction on this POS which is already in progress on other POS   
        //Oraby: How we found that transaction is already in progress on other POS??
                                paymentRequestECR.setStatusCode("X0");
                                paymentRequestECR.setStatusMessage("Transaction already in progress on another POS machine");
                                logger.debug("paymentRequestECR : " + paymentRequestECR);
                                status = PAYMENT_STATUS_FAILED;
                        } else if (paymentVO.getPaymentStatus().equals(PAYMENT_STATUS_FAILED)) {
                                logger.debug("paymentVO.getPaymentInProgress() = " + paymentVO.getPaymentInProgress() + " : paymentVO.getPaymentStatus()" + 
                                          paymentVO.getPaymentStatus());
                                
                                // if failed on another pos, then we allow to pay it on this POS
                          
                          status = PAYMENT_STATUS_SUCCESS;                        
                          //repay = true;
                        } else if(paymentVO.getPaymentStatus().equals(PURCHASE_STATUS_APPROVED)) {
                          logger.debug("paymentVO.getPaymentInProgress() = " + paymentVO.getPaymentInProgress() + " : paymentVO.getPaymentStatus()" + 
                                          paymentVO.getPaymentStatus());
                         
                          status = PAYMENT_STATUS_SUCCESS;
                        } else {  // any other case is a failure
                          logger.debug("paymentVO.getPaymentInProgress() = " + paymentVO.getPaymentInProgress() + " : paymentVO.getPaymentStatus()" + 
                                          paymentVO.getPaymentStatus());                
                          
                          status = PAYMENT_STATUS_FAILURE;
                        }
                        
                if (status.equals(PAYMENT_STATUS_SUCCESS)) {
                                // to use the payment information already saved in the database and not the incoming params
                                paymentVO = fillObj(paymentVO.getPaymentId(), paymentVO.getFeeId(), paymentVO.getCustomerId(), 
                                                  paymentVO.getCustomerName(), paymentServiceCode.getServiceCode(), 
                                                  paymentServiceCode.getServiceId(), feeDetail.getDepartmentId(), feeDetail.getAmount(), terminalNo, merchantID);
                                if(isAutoUpdate){
        //Oraby: the below variable has not been intialized yet, it is null!!
                                        paymentVO.setTransactionID(lastPaymentTransactionId);
                                }
        //Oraby: Why we update here the paymentInprogress to true??
                                result = paymentService.updatePaymentInProgress(paymentID, true);
                                logger.debug("updatePaymentInProgress result : " + result);
                                
                                /**
                                 * unique transaction id based on a sequence for each service belong to a department
                                 * format Transaction Id = E + ‘ENV_ID’ + department Id + 0000 + db Sequence
                                 * start
                                 */
                                
                                //Get Sequence Name for Service
                                        String sequenceName = ConfUtils.getValue("SEQ_" + feeDetail.getServiceId());
                                        String ENV_ID = ConfUtils.getValue("environment.id"); // dev, staging, prod
                                        if(sequenceName == null || sequenceName.isEmpty()){
                                                status = PAYMENT_STATUS_FAILURE;
                                                logger.error("Null Sequence Name");             
                                                paymentRequestECR.setStatusCode("X2");
                                        paymentRequestECR.setStatusMessage("Empty Sequence Name");
                                                return paymentRequestECR;
                                        }
                                        
                                        if(ENV_ID == null || ENV_ID.isEmpty()){
                                                status = PAYMENT_STATUS_FAILURE;
                                                logger.error("Null tran id");
                                                paymentRequestECR.setStatusCode("X3");
                                        paymentRequestECR.setStatusMessage("Empty transaction id");
                                                return paymentRequestECR;
                                        }
                                
                              //Get Department  Name For Service
                                        String department = "E" + ENV_ID + feeDetail.getDepartmentId();                                         
                                        String transactionIDseq = department + "0000";                                                          
                                        String sequenceID       =       paymentService.getSequnceNextValue(sequenceName);
                                        
                                        if(sequenceID == null || sequenceID.isEmpty()){
                                                logger.error("sequenceID is Null");
                                                status = PAYMENT_STATUS_FAILURE;
                                                paymentRequestECR.setStatusCode("X4");
                                        paymentRequestECR.setStatusMessage("Empty sequence no");
                                        return paymentRequestECR;
                                        }
                                        
                                        transactionIDseq += sequenceID;
                                
                                /**
                                 * unique tranaction id based on sequence
                                 * end
                                 */
                                
                                paymentRequestECR = PosUtil.fillRequest(paymentVO, isAutoUpdate ? "LastTransactionStatus" : "Sales", transactionIDseq);
                                logger.debug("paymentRequestECR : " + paymentRequestECR);
                                
                                if(!isAutoUpdate){ // save only for sales transaction, not for autoupdate as it has the same transaction id so cant be inserted
                                        result = paymentService.savePaymentRequest(paymentRequestECR);
                                        logger.debug("savePaymentRequest :" + result);
                                }
                        }                       
                } catch (Exception e) {
                                logger.error("preTransaction : " + e.getMessage());
                        }
            } else {
                // invalid parameters
                paymentRequestECR.setStatusCode("X1");
                paymentRequestECR.setStatusMessage("Invalid Parameters");
                logger.debug("paymentRequestECR : " + paymentRequestECR);
            }
        }
        logger.exit("preTransaction");
        
        return paymentRequestECR;
    }


    /**
     * This method is to get last transaction status in case of broken transactions exist
     * @param paymentID
     * @param terminalNo
     * @param merchantID
     * @return
     */
    public PaymentRequestECR getLastTransactionStatus(String terminalNo, String merchantID){
        PaymentRequestECR paymentRequestEcr = null;
        try {
            PaymentService paymentService = new PaymentService();
            PaymentVO paymentVO = paymentService.getLastPaymentTransaction(null, terminalNo, merchantID);                                     
                    logger.debug("lastPaymentTransaction = " + paymentVO);
            //Oraby: What if paymentVO.getTransactionID() = null? that will happen if there is no proken transactions for this POS device
                    if(paymentVO != null && paymentVO.getTransactionID() != null && !paymentVO.getTransactionID().isEmpty()){                       
                        PaymentRequestECR paymentRequestECR = PosUtil.fillRequest(paymentVO, "LastTransactionStatus", null);
                        paymentRequestECR.setPaymentId(paymentVO.getPaymentId());
                        logger.debug("paymentRequestECR : " + paymentRequestECR);                               
                    }
        } catch (Exception e) {
            // TODO: Add catch code
            e.printStackTrace();
        }
        return paymentRequestEcr;
    }
}
