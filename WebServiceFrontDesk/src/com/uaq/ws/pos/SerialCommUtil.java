package com.uaq.ws.pos;


import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;

public class SerialCommUtil {
    
    //just a boolean flag that is used for enabling
    //and disabling buttons depending on whether the program
    //is connected to a serial port or not
    private boolean bConnected = false;

    //the timeout value for connecting with the port
    static final long TIMEOUT_1_MIN = 60000; // in mil sec. 3 min. 1000 mil sec = 1 sec
    long timeout = TIMEOUT_1_MIN;
    
    PaymentResponseECR paymentResponseECR = new PaymentResponseECR();
    
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		SerialCommUtil communicator = new SerialCommUtil();

    	PaymentRequestECR paymentRequestECR = new PaymentRequestECR();
    	// set values here in paymentRequestECR
    	
    	// for sales transaction testing
    	paymentRequestECR.setTransactionType("01"); 
    	paymentRequestECR.setTerminalNo("00051427");
    	paymentRequestECR.setMerchantId("936696000");
    	paymentRequestECR.setServiceCode("000000-0008");
    	paymentRequestECR.setAmount("20");
    	paymentRequestECR.setEcrIdNo("1447752004921");
    	    	
    	// for last transaction status testing
    	/*paymentRequestECR.setTransactionType("TS");
    	paymentRequestECR.setEcrIdNo("1447752004921");
    	paymentRequestECR.setTerminalNo("00051427");*/
    	
    	PaymentResponseECR paymentResponseECR = communicator.doTransaction(paymentRequestECR);
		
		System.out.println("paymentResponseECR : " + paymentResponseECR);		
	
	}
	
	@SuppressWarnings("static-access")
	public PaymentResponseECR doTransaction(PaymentRequestECR paymentRequestECR){			
		    
		System.out.println("paymentRequestECR : " + paymentRequestECR);		
		
		paymentResponseECR.setEcrIdNo(paymentRequestECR.getEcrIdNo()); // in case of error, we need this on the receiving side
	    
	    long startTime = 0, currentTime = 0;	
	    SerialPortReader serialPortReader = null;
	    
	    String selectedPort = "COM4";
	    
	    SerialPort serialPort = connect(selectedPort);
	    if (serialPort.isOpened())
        {
	    	serialPortReader = new SerialPortReader(serialPort);
	    	try {
				serialPort.addEventListener(serialPortReader);
			} catch (SerialPortException e) {
				e.printStackTrace();
				bConnected = false;
			}
        } else {
        	
        	paymentResponseECR.setResponseCode("XX");
        	paymentResponseECR.setResponseMessage("Port Problem");
        	
        	return paymentResponseECR;
        }
		
        if (bConnected)
        {       	        	
        	startTime = System.currentTimeMillis();
        	System.out.println("startTime = " + startTime);
        	
        	String message = "";
        	// factory
        	if(paymentRequestECR.getTransactionType().equalsIgnoreCase("01")){
        		message = Message.getSalesRequest(paymentRequestECR);
        	} else if(paymentRequestECR.getTransactionType().equalsIgnoreCase("TS")){
        		message = Message.getLastTransactionStatusRequest(paymentRequestECR);
        	}
        	
        	writeData(message, serialPort); // initiate sale transaction on POS
            
        	System.out.println("initiate sale transaction on POS");  
        	
        	try {
            
	        	do{ 
	        		currentTime = System.currentTimeMillis();
	        		        		        		
	            	if(serialPortReader.proceed){ // waiting for the reader thread
			            if(serialPortReader.isCompleted){ // transaction completed with either success or wrong LRC or error
							//wrong LRC or some error received from POS						
							if(serialPortReader.status){ // transaction completed successfully							
								System.out.println("transaction completed successfully");	
								break;
							} else { // NACK received or error or wrong LRC							
								System.out.println("NACK or error or wrong LRC");
								System.out.println("retryCounter = " + serialPortReader.retryCounter);
								if(serialPortReader.retryCounter >= 1){
									serialPortReader.isCompleted = false;
									serialPortReader.status = false;
									serialPortReader.proceed = false;
								    Thread.currentThread().sleep(8000);
									writeData(message, serialPort); // initiate sale transaction on POS
								} else {
									System.out.println("retry limit finished");
									break;
								}
								//System.out.println("transaction has to be completed manually on the PC");
								//break;
							}						
						} else {
							System.out.println("not completed");
							
							if(serialPortReader.retryCounter >= 1){
								serialPortReader.isCompleted = false;
								serialPortReader.status = false;
								serialPortReader.proceed = false;
								Thread.currentThread().sleep(8000);
								writeData(Message.getSalesRequest(paymentRequestECR), serialPort); // initiate sale transaction on POS
							} else {
								System.out.println("retry limit finished");
								break;
							}
						}
	            	} 
	            	
					Thread.currentThread().sleep(2000);					
					
	            } while( (currentTime - startTime) <= timeout );
            
        	} catch (InterruptedException e) {					
				e.printStackTrace();
				System.out.println("main thread interrupted");
			}
        	
        	System.out.println("time taken = " + (currentTime - startTime));
            disconnect(serialPort); // shut down the com port  
            
            if((currentTime - startTime) > timeout){
            	paymentResponseECR.setResponseCode("XX");
            	paymentResponseECR.setResponseMessage("POS connection problem");
            }
        }
		return paymentResponseECR;
	}
	    
    // the connected comm port is stored in commPort, otherwise,
    //an exception is generated
    public SerialPort connect(String selectedPort)
    {              
        String logText = "";
        SerialPort serialPort = null;

        try
        {            
            //the CommPort object can be casted to a SerialPort object
        	serialPort = new SerialPort("COM4"); // get from properties file
        	serialPort.openPort();
            serialPort.setParams(9600,SerialPort.DATABITS_8,SerialPort.STOPBITS_1,SerialPort.PARITY_NONE);            
            bConnected = true;
            
            //logging
            logText = selectedPort + " opened successfully";
          
        }        
        catch (Exception e)
        {
            logText = "Failed to open " + selectedPort + "(" + e.toString() + ")";            
        }
        System.out.println(logText);
        
        return serialPort;
    }
        
  //disconnect the serial port
    //pre : an open serial port
    //post: closed serial port
    public void disconnect(SerialPort serialPort)
    {
    	String logText = "";
    	
        //close the serial port
        try
        {        
        	if(serialPort != null){
        		serialPort.removeEventListener();
        		serialPort.closePort();
        	}
                        
            logText = "Disconnected";            
        }
        catch (Exception e)
        {
            logText = "Failed to close " + serialPort.getPortName()
                              + "(" + e.toString() + ")";      
        }
        System.out.println(logText);
    }

	
	//method that can be called to send data
    //pre : open serial port
    //post: data sent to the other device
    public void writeData(String message, SerialPort serialPort)
    {
    	String logText = "";
    	
        try
        {
        	System.out.println("[WRITING MESSAGE]");        	
        	serialPort.writeBytes(HexUtil.hexStringToByteArray(message));          	
        	//serialPort.setEventsMask(SerialPort.MASK_RXCHAR);         
        }
        catch (Exception e)
        {
            logText = "Failed to write data. (" + e.toString() + ")";            
        }
        
        System.out.println(logText);
    }    
    
    private class SerialPortReader implements SerialPortEventListener {
    	
    	SerialPort serialPort;    	
    	StringBuilder responseHexResult = new StringBuilder();

        boolean isCompleted = false, status = false, proceed = false, endOfMessage = false;
        int retryCounter = 3;
    	
    	public SerialPortReader(SerialPort serialPort){    		
    		this.serialPort = serialPort;    		
    	}

    	@Override
    	//what happens when data is received. creates a separate thread
        //pre : serial event is triggered
        //post: processing on the data it reads
    	public void serialEvent(SerialPortEvent event) {		
    				
    		if(event.isRXCHAR()){
    			try {
    				String responseHex = serialPort.readHexString();
    				System.out.println("response = " + responseHex);
    				responseHexResult.append(responseHex);
    				if(responseHex.equals("01")){ // start of message
    					paymentResponseECR = null;
    					responseHexResult = new StringBuilder();		// reset result
    				} else if(responseHex.equals("03")){ // end of response message	but LRC follows		
    					endOfMessage = true;
    					System.out.println("end of message");								
    				} else if(endOfMessage){
    					//String responseLRC = responseHex; // last received byte is LRC   					
    					//System.out.println("responseLRC = " + responseLRC);
    					
    					System.out.println("complete response message = " + responseHexResult.toString());							
    					// check if success or error message
    					boolean isErrorMessage = isErrorMessage(responseHexResult.toString()); // check if success or error
    					// if success and incorrect LRC then send nack
    					if(!isErrorMessage){ // correct message						
    						paymentResponseECR = Message.getResponse(responseHexResult.toString());    						
    						
    						System.out.println("response LRC = " + paymentResponseECR.getLrc() 
									    + " : Calculated LRC = " + paymentResponseECR.getCustomLRC());
    						
    						if(!paymentResponseECR.getCustomLRC().equals(paymentResponseECR.getLrc()) ){ // check LRC
    							// LRC is incorrect, send nack and dec retry counter
    							boolean nack = serialPort.writeBytes(HexUtil.hexStringToByteArray("15")); // send nack
    							if (nack) {
    								System.out.println("[SENDING NEGATIVE ACKNOWLEDGEMENT]");
    								retryCounter -- ; // dec retry counter
        							System.out.println("retry counter after dec = " + retryCounter);
    							}
    							status = false; // signal error, LRC is wrong   							
    						} else {    // success							
    							boolean ack = serialPort.writeBytes(HexUtil.hexStringToByteArray("06")); // send acknowledgment
        						if (ack) {
        							System.out.println("[SENDING ACKNOWLEDGEMENT]");       							
        							status = true; // mark the transaction as success
        							isCompleted = true;
        							proceed = true;
        						}
    						}    						
    					} else { // error
    						boolean ack = serialPort.writeBytes(HexUtil.hexStringToByteArray("06")); // send acknowledgment
    						if (ack) {
    							// we should also check lrc and send ack or nack based on the result. but we skip here
    							System.out.println("[SENDING ACKNOWLEDGEMENT]");
    							timeout = TIMEOUT_1_MIN;
    							retryCounter = 0 ; // expire retry counter    							
    							status = false; // mark the transaction as failed   
    							isCompleted = true;
    							proceed = true;
    						}
    					}    					
    					// reset
						endOfMessage = false;
    				} else if(responseHex.equals("06")){ // ack received
    					System.out.println("[ACKNOWLEDGMENT RECEIVED]"); // ack, now keep waiting for response
    					responseHexResult = new StringBuilder(); // reset result
    					//retryCounter = 3; // reset the retry counter
    					timeout = TIMEOUT_1_MIN; 
    				} else if(responseHex.equals("15")){ // nack received
    					System.out.println("[NEGATIVE ACKNOWLEDGMENT RECEIVED]");
    					responseHexResult = new StringBuilder(); // reset result					
    					retryCounter -- ; // dec retry counter
    					System.out.println("retry counter after dec = " + retryCounter);
    					timeout = TIMEOUT_1_MIN;
    					proceed = true;
    				} else if(responseHex.equals("1A")){ // Substitute/end of file or transaction. transaction terminated
    					// happens when successful completion or proper error message received from payment service provider
    					isCompleted = true; // mark the transaction as completed					
    					System.out.println("[TRANSACTION COMPLETED]");					
    					proceed = true;
    					System.out.println("paymentResponseECR : " + paymentResponseECR.toString());
    				} else {
    					// message other hex parts
    				}
    			} catch (SerialPortException e) {
    				e.printStackTrace();
    			} 
    		}
        }
    	
    	private boolean isErrorMessage(String message){
        	
        	System.out.println("enter isErrorMessage");
        	
        	boolean result = false;
        	
        	if(message != null && !message.isEmpty()){
        		//check Message Length decimal (0027 for error message , 0979 for successful) 
        		System.out.println("message length in hex = " + message.substring(2, 10));
        		if(message.substring(2, 10).equals("30303237")){ //0027
        			
        			result = true;
        			// take error message and error code        			
        			String errorCode = HexUtil.convertHexToString(message.substring(22, 28));        			
        			paymentResponseECR.setResponseCode(errorCode);
        			paymentResponseECR.setResponseMessage(Message.getErrorCodeDescription(errorCode));
        			
        			System.out.println("Error Code = " + errorCode);
        			System.out.println("Error Description = " + Message.getErrorCodeDescription(errorCode));
        			
        		} else {
        			//0979
        			result = false; // success
        		}
        	} else {
        		result = true;
        	}
        	
        	System.out.println("exit isErrorMessage result = " + result);
        	
        	return result;
        }
    	
		    	    	
    } 
}
