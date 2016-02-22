package com.uaq.ws.pos;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import  com.uaq.ws.util.POSConstants;
import com.uaq.ws.util.PosUtil;

import org.apache.commons.lang.StringUtils;


public class Message {

	public static void main(String[] args){
		/*String lrc = HexUtil.calculateLRC("3033363830311C303030303135303030303237313232381C311C3030303030303030303330301C3030303030303030303030301C452D53455256494345204F574E455220434F4D4D1C30331C3030303030303030303030301C434F4C4C454354494F4E20434E545220434F4D4D20202020202020202020202020202020202020201C3030303030303030303330301C452D44495248414D20434F4D4D2020202020202020202020202020202020202020202020202020201C3030303030303030303730301C5541512065476F7620666565732020202020202020202020202020202020202020202020202020201C30301C3534353937311C202020202020202020202020202020201C3135303030303237313232381C3030303837341C75736572202020201C30303035313432371C2020202020203933363639363030301C3030303030321C3131313131361C313234361C202020202020202020202020202020201C202020202020202020202020202020201C03");
		System.out.println("lrc = " + lrc);*/
		
		PaymentResponseECR paymentResponseECR = new PaymentResponseECR(); 
		
		// sales or last transaction status
		paymentResponseECR = getResponse("023033363830311C303030303135303030303237313232381C311C3030303030303030303330301C3030303030303030303030301C452D53455256494345204F574E455220434F4D4D1C30331C3030303030303030303030301C434F4C4C454354494F4E20434E545220434F4D4D20202020202020202020202020202020202020201C3030303030303030303330301C452D44495248414D20434F4D4D2020202020202020202020202020202020202020202020202020201C3030303030303030303730301C5541512065476F7620666565732020202020202020202020202020202020202020202020202020201C30301C3534353937311C202020202020202020202020202020201C3135303030303237313232381C3030303837341C75736572202020201C30303035313432371C2020202020203933363639363030301C3030303030321C3131313131361C313234361C202020202020202020202020202020201C202020202020202020202020202020201C0342");
	
		System.out.println(paymentResponseECR.toString());
	}
	
	public static String getSalesRequest(PaymentRequestECR paymentRequestECR) {
		
		SimpleDateFormat sdfDate = new SimpleDateFormat(POSConstants.DD_MM_YY);
		SimpleDateFormat sdfTime = new SimpleDateFormat(POSConstants.HH_MM);
		
		StringBuilder result = new StringBuilder();
		
		result.append(HexUtil.getHexFromString(POSConstants.MESSAGE_LENGTH_SALES)); // 4 byte
		// ECR Version Number
		result.append(HexUtil.getHexFromString(POSConstants.ECR_VERSION_NUMBER)); // 3 byte
		result.append(POSConstants.FS);
		// Transaction type, Length: 2, Fieldtype: N
		// 01 = Sales
		result.append(HexUtil.getHexFromString(POSConstants.ECR_TRANSACTION_TYPE_SALES));
		result.append(POSConstants.FS);
		// ECR Application ID
		result.append(HexUtil.getHexFromString(StringUtils.leftPad(POSConstants.ECR_Application_ID, 8, POSConstants.STRING_ZERO)));
		result.append(POSConstants.FS);
		
		// Terminal number, Length: 8, Fieldtype: N
		result.append(HexUtil.getHexFromString(StringUtils.leftPad(System.getProperty("tid"), 8, POSConstants.STRING_ZERO)));
		result.append(POSConstants.FS);

		// Service code
		result.append(HexUtil.getHexFromString(StringUtils.rightPad(paymentRequestECR.getServiceCode(), 20, POSConstants.STRING_SPACE)));
		result.append(POSConstants.FS);		
		result.append(HexUtil.getHexFromString(StringUtils.leftPad(PosUtil.convertAmountDecimalToISOFormat(paymentRequestECR.getAmount()), 12, POSConstants.STRING_ZERO)));
		result.append(POSConstants.FS);	
		result.append(HexUtil.getHexFromString(StringUtils.leftPad(POSConstants.STRING_ONE, 6, POSConstants.STRING_ZERO)));
		result.append(POSConstants.FS);

		// Skip Serv2 Code
		result.append(HexUtil.getHexFromString(StringUtils.repeat(POSConstants.STRING_SPACE, 20)));
		result.append(POSConstants.FS);		
		result.append(HexUtil.getHexFromString(StringUtils.repeat(POSConstants.STRING_ZERO, 12)));
		result.append(POSConstants.FS);
		result.append(HexUtil.getHexFromString(StringUtils.repeat(POSConstants.STRING_ZERO, 6)));
		result.append(POSConstants.FS);
		
		// Skip Serv3 Code
		result.append(HexUtil.getHexFromString(StringUtils.repeat(POSConstants.STRING_SPACE, 20)));
		result.append(POSConstants.FS);
		result.append(HexUtil.getHexFromString(StringUtils.repeat(POSConstants.STRING_ZERO, 12)));
		result.append(POSConstants.FS);
		result.append(HexUtil.getHexFromString(StringUtils.repeat(POSConstants.STRING_ZERO, 6)));
		result.append(POSConstants.FS);
		
		// Skip Serv4 Code
		result.append(HexUtil.getHexFromString(StringUtils.repeat(POSConstants.STRING_SPACE, 20)));
		result.append(POSConstants.FS);
		result.append(HexUtil.getHexFromString(StringUtils.repeat(POSConstants.STRING_ZERO, 12)));
		result.append(POSConstants.FS);
		result.append(HexUtil.getHexFromString(StringUtils.repeat(POSConstants.STRING_ZERO, 6)));
		result.append(POSConstants.FS);
		
		// Skip Serv5 Code
		result.append(HexUtil.getHexFromString(StringUtils.repeat(POSConstants.STRING_SPACE, 20)));
		result.append(POSConstants.FS);
		result.append(HexUtil.getHexFromString(StringUtils.repeat(POSConstants.STRING_ZERO, 12)));
		result.append(POSConstants.FS);
		result.append(HexUtil.getHexFromString(StringUtils.repeat(POSConstants.STRING_ZERO, 6)));
		result.append(POSConstants.FS);
		
		// Skip Serv6 Code
		result.append(HexUtil.getHexFromString(StringUtils.repeat(POSConstants.STRING_SPACE, 20)));
		result.append(POSConstants.FS);
		result.append(HexUtil.getHexFromString(StringUtils.repeat(POSConstants.STRING_ZERO, 12)));
		result.append(POSConstants.FS);
		result.append(HexUtil.getHexFromString(StringUtils.repeat(POSConstants.STRING_ZERO, 6)));
		result.append(POSConstants.FS);

		// Skip No of App
		result.append(HexUtil.getHexFromString(POSConstants.No_of_App));
		result.append(POSConstants.FS);
		// Addition Amount
		result.append(HexUtil.getHexFromString(StringUtils.repeat(POSConstants.STRING_ZERO, 12)));
		result.append(POSConstants.FS);

		// Skip App No
		result.append(HexUtil.getHexFromString(StringUtils.repeat(POSConstants.STRING_SPACE, 20)));
		result.append(POSConstants.FS);

		// Skip POS Assign App.
		result.append(HexUtil.getHexFromString(StringUtils.repeat(POSConstants.STRING_SPACE, 1)));
		result.append(POSConstants.FS);

		// Original Invoice No.
		result.append(HexUtil.getHexFromString(POSConstants.ECR_Invoice_No));
		result.append(POSConstants.FS);

		// ECR Invoice No.
		result.append(HexUtil.getHexFromString(POSConstants.ECR_Invoice_No));
		result.append(POSConstants.FS);

		// ECR ID No.
		result.append(HexUtil.getHexFromString(StringUtils.leftPad(paymentRequestECR.getEcrIdNo(), 16, POSConstants.STRING_ZERO)));
		result.append(POSConstants.FS);
		// skip user
		result.append(HexUtil.getHexFromString(StringUtils.repeat(POSConstants.STRING_SPACE, 8)));
		result.append(POSConstants.FS);

		// Transaction date
		result.append(HexUtil.getHexFromString(sdfDate.format(Calendar.getInstance().getTime())));
		result.append(POSConstants.FS);

		// Transaction time
		result.append(HexUtil.getHexFromString(sdfTime.format(Calendar.getInstance().getTime())));
		result.append(POSConstants.FS);
		// skip Country code
		result.append(HexUtil.getHexFromString(StringUtils.repeat(POSConstants.STRING_SPACE, 4)));
		result.append(POSConstants.FS);
		// skip Main area code
		result.append(HexUtil.getHexFromString(StringUtils.repeat(POSConstants.STRING_SPACE, 16)));
		result.append(POSConstants.FS);
		// skip Sub-area code
		result.append(HexUtil.getHexFromString(StringUtils.repeat(POSConstants.STRING_SPACE, 8)));
		result.append(POSConstants.FS);
		// skip Card No.
		result.append(HexUtil.getHexFromString(StringUtils.repeat(POSConstants.STRING_F, 19)));
		result.append(POSConstants.FS);
		// skip Expiry date
		result.append(HexUtil.getHexFromString(StringUtils.repeat(POSConstants.STRING_SPACE, 4)));
		result.append(POSConstants.FS);
		// merchant id.
		result.append(HexUtil.getHexFromString(StringUtils.leftPad(System.getProperty("mid"), 15, POSConstants.STRING_SPACE)));		
		result.append(POSConstants.FS);

		result.append(POSConstants.ETX);
		// LRC Checksum
		//result.append(calculateLRC(result.toString().getBytes()));
		result.append(HexUtil.calculateLRC(result.toString()));
		
		String request = POSConstants.STX + result.toString();
		
		System.out.println("request = " + request);
		
		// Start of text
		//result.Insert(0, (Char) TwoStepProtocol.StartOfText);

		return request;
	}
	
	public static PaymentResponseECR getResponse(String posResponseMessageHex) {
		
		PaymentResponseECR paymentResponseECR = new PaymentResponseECR();
				
		int index = 0;
		
		String stx = HexUtil.getDecimalStringFromHexString(posResponseMessageHex.substring(index, index += 2)), // ignore, 1 byte
		messageLength = HexUtil.getDecimalStringFromHexString(posResponseMessageHex.substring(index, index += 8)), // ignore, 4 byte,	value=0979	
		transactionType = HexUtil.getDecimalStringFromHexString(posResponseMessageHex.substring(index, index += 4)), // ignore, should be sales(01 dec), 2 byte
		// FS ignore, 1 byte
		transactionId = HexUtil.getDecimalStringFromHexString(posResponseMessageHex.substring(index += 2, index += 32)), // 16 byte
		// FS ignore, 1 byte
		
		servicesNumber = HexUtil.getDecimalStringFromHexString(posResponseMessageHex.substring(index += 2, index += 2)), // no of services, 1 in our case, 1 byte
		// FS ignore, 1 byte
		service1Amount = HexUtil.getDecimalStringFromHexString(posResponseMessageHex.substring(index += 2, index += 24)), // 12 byte
		// FS ignore, 1 byte
		service1Fee1Amount = HexUtil.getDecimalStringFromHexString(posResponseMessageHex.substring(index += 2, index += 24)), // 12 byte
		// FS ignore, 1 byte
		service1Fee1Name = HexUtil.getDecimalStringFromHexString(posResponseMessageHex.substring(index += 2, index += 40)), // 20 byte
		// FS ignore, 1 byte		
		
		dynamicFeeCount = HexUtil.getDecimalStringFromHexString(posResponseMessageHex.substring(index += 2, index += 4)), // 2 byte
		// FS ignore, 1 byte
		dynamicFee1Amount = HexUtil.getDecimalStringFromHexString(posResponseMessageHex.substring(index += 2, index += 24)), // 12 byte		
		// FS ignore, 1 byte
		dynamicFee1Text = HexUtil.getDecimalStringFromHexString(posResponseMessageHex.substring(index += 2, index += 80)), // 40 byte
		// FS ignore, 1 byte
		dynamicFee2Amount = HexUtil.getDecimalStringFromHexString(posResponseMessageHex.substring(index += 2, index += 24)), // 12 byte		
		// FS ignore, 1 byte
		dynamicFee2Text = HexUtil.getDecimalStringFromHexString(posResponseMessageHex.substring(index += 2, index += 80)), // 40 byte
		// FS ignore, 1 byte
		dynamicFee3Amount = HexUtil.getDecimalStringFromHexString(posResponseMessageHex.substring(index += 2, index += 24)), // 12 byte		
		// FS ignore, 1 byte
		dynamicFee3Text = HexUtil.getDecimalStringFromHexString(posResponseMessageHex.substring(index += 2, index += 80)), // 40 byte
		// FS ignore, 1 byte
		
		// OR to make it dynamic
		/*for(int i = 0; i < Integer.parseInt(dynamicFeeCount); i++){
			String dynamicFee1Amount = HexUtil.getDecimalStringFromHexString(posResponseMessageHex.substring(index += 2, index += 24)), // 12 byte		
			// FS ignore, 1 byte
			dynamicFee1Text = HexUtil.getDecimalStringFromHexString(posResponseMessageHex.substring(index += 2, index += 80)); // 40 byte
			// FS ignore, 1 byte
		}*/
		
		responseCode = HexUtil.getDecimalStringFromHexString(posResponseMessageHex.substring(index += 2, index += 4)), // use as message status, 2 byte
		// FS ignore, 1 byte
		approvalCode = HexUtil.getDecimalStringFromHexString(posResponseMessageHex.substring(index += 2, index += 12)), // 6 byte
		// FS ignore, 1 byte
		ecrIdNo = HexUtil.getDecimalStringFromHexString(posResponseMessageHex.substring(index += 2, index += 32)), // ignore, 16 byte
		// FS ignore, 1 byte
		retrievalRefNo = HexUtil.getDecimalStringFromHexString(posResponseMessageHex.substring(index += 2, index += 24)), // 12 byte
		// FS ignore, 1 byte
		invoiceNo = HexUtil.getDecimalStringFromHexString(posResponseMessageHex.substring(index += 2, index += 12)), // 6 byte
		// FS ignore, 1 byte
		posUserId = HexUtil.getDecimalStringFromHexString(posResponseMessageHex.substring(index += 2, index += 16)), // ignore, 8 byte
		// FS ignore, 1 byte
		terminalId = HexUtil.getDecimalStringFromHexString(posResponseMessageHex.substring(index += 2, index += 16)), // 8 byte
		// FS ignore, 1 byte
		merchantId = HexUtil.getDecimalStringFromHexString(posResponseMessageHex.substring(index += 2, index += 30)), // 15 byte
		// FS ignore, 1 byte
		batchNo = HexUtil.getDecimalStringFromHexString(posResponseMessageHex.substring(index += 2, index += 12)), // ignore, 6 byte
		// FS ignore, 1 byte
		posDate = HexUtil.getDecimalStringFromHexString(posResponseMessageHex.substring(index += 2, index += 12)), // 6 byte
		// FS ignore, 1 byte
		posTime = HexUtil.getDecimalStringFromHexString(posResponseMessageHex.substring(index += 2, index += 8)), // 4 byte
		// FS ignore, 1 byte
		chipTransactionTC = HexUtil.getDecimalStringFromHexString(posResponseMessageHex.substring(index += 2, index += 32)), // ignore, 16 byte
		// FS ignore, 1 byte
		chipTransactionTVR = HexUtil.getDecimalStringFromHexString(posResponseMessageHex.substring(index += 2, index += 32)), // ignore, 16 byte
		// FS ignore, 1 byte
		etx = HexUtil.getDecimalStringFromHexString(posResponseMessageHex.substring(index += 2, index += 2)), // ignore, 1 byte
		lrc = HexUtil.getDecimalStringFromHexString(posResponseMessageHex.substring(index, index += 2)); // use only for message validation, 1 byte
		String customLRC = HexUtil.getDecimalStringFromHexString(HexUtil.calculateLRC(posResponseMessageHex.substring(2, index - 2))); // excluding STX, for validation.
		
		System.out.println("All fields from Response except field separator : ");
		
		//print all fields for logging purpose
		System.out.println("stx = " + stx + " : messageLength = " + messageLength + " : transactionType = " + transactionType + " : transactionId = " + transactionId + 
		" : servicesNumber = " + servicesNumber + " : service1Amount = " + service1Amount + " : service1Fee1Name = " + service1Fee1Name + 
		" : dynamicFeeCount = " + dynamicFeeCount + " : dynamicFee1Amount = " + dynamicFee1Amount + " : dynamicFee1Text = " + dynamicFee1Text 	+ 
		" : dynamicFee2Amount = " + dynamicFee2Amount + " : dynamicFee2Text = " + dynamicFee2Text + " : dynamicFee3Amount = " + dynamicFee3Amount + 
		" : dynamicFee3Text = " + dynamicFee3Text + " : responseCode = " + responseCode + " : approvalCode = " + approvalCode + 
		" : ecrIdNo = " + ecrIdNo + " : retrievalRefNo = " + retrievalRefNo + " : invoiceNo = " + invoiceNo + " : posUserId = " + posUserId + " : terminalId = " + terminalId + 
		" : merchantId = " + merchantId + " : batchNo = " + batchNo + " : posDate = " + posDate + " : posTime = " + posTime + 
		" : chipTransactionTC = " + chipTransactionTC + " : chipTransactionTVR = " + chipTransactionTVR + " : etx = " + etx + 
		" : lrc = " + lrc + " : customLRC = " + customLRC);
				
		// taking away only important fields		
		paymentResponseECR.setApprovalCode(approvalCode); 
		paymentResponseECR.setTransactionType(transactionType);
		paymentResponseECR.setTransactionId(transactionId);
		
		paymentResponseECR.setServicesCount(Integer.parseInt(servicesNumber));
		paymentResponseECR.setService1Amount(PosUtil.convertAmountISOToDecimalFormat(service1Amount));
		paymentResponseECR.setService1Fee1Amount(PosUtil.convertAmountISOToDecimalFormat(service1Fee1Amount));
		paymentResponseECR.setService1Fee1Name(service1Fee1Name.trim());
		
		paymentResponseECR.setDynamicFeeCount(Integer.parseInt(dynamicFeeCount));
		paymentResponseECR.setDynamicFee1Amount(PosUtil.convertAmountISOToDecimalFormat(dynamicFee1Amount));
		paymentResponseECR.setDynamicFee1Text(dynamicFee1Text.trim());
		paymentResponseECR.setDynamicFee2Amount(PosUtil.convertAmountISOToDecimalFormat(dynamicFee2Amount));
		paymentResponseECR.setDynamicFee2Text(dynamicFee2Text.trim());
		paymentResponseECR.setDynamicFee3Amount(PosUtil.convertAmountISOToDecimalFormat(dynamicFee3Amount));
		paymentResponseECR.setDynamicFee3Text(dynamicFee3Text.trim());
		
		paymentResponseECR.setResponseCode(responseCode);
		paymentResponseECR.setApprovalCode(approvalCode);
		paymentResponseECR.setEcrIdNo(ecrIdNo);
		paymentResponseECR.setRetrievalRefNo(retrievalRefNo);
		paymentResponseECR.setPosDate(posDate);
		paymentResponseECR.setPosTime(posTime);
		paymentResponseECR.setLrc(lrc);
		paymentResponseECR.setCustomLRC(customLRC);
						
		return paymentResponseECR;
	}
	
	public static String getLastTransactionStatusRequest(PaymentRequestECR paymentRequestECR) {
						
		StringBuilder result = new StringBuilder();
		
		result.append(HexUtil.getHexFromString(POSConstants.MESSAGE_LENGTH_LAST_TRANSACTION_STATUS)); // 4 byte
		// ECR Version Number
		result.append(HexUtil.getHexFromString(POSConstants.ECR_VERSION_NUMBER)); // 3 byte
		result.append(POSConstants.FS);
		// Transaction type, Length: 2, Fieldtype: N
		// 01 = Sales
		result.append(HexUtil.getHexFromString(POSConstants.ECR_TRANSACTION_TYPE_LAST_TRANSACTION_STATUS));
		result.append(POSConstants.FS);
		// ECR Application ID
		result.append(HexUtil.getHexFromString(StringUtils.leftPad(POSConstants.ECR_Application_ID, 8, POSConstants.STRING_ZERO)));
		result.append(POSConstants.FS);

		// Terminal number, Length: 8, Fieldtype: N
		result.append(HexUtil.getHexFromString(StringUtils.leftPad(System.getProperty("tid"), 8, POSConstants.STRING_ZERO)));
		result.append(POSConstants.FS);

		// ECR ID No.
		result.append(HexUtil.getHexFromString(StringUtils.leftPad(paymentRequestECR.getEcrIdNo(), 16, POSConstants.STRING_ZERO)));
		result.append(POSConstants.FS);
		
		result.append(POSConstants.ETX);
		// LRC Checksum		
		result.append(HexUtil.calculateLRC(result.toString()));
		
		String message = POSConstants.STX + result.toString();
		
		System.out.println("request = " + message);

		return message;
	}
	
	private static Map<String, String> getErrorCodesMap(){
    	
    	Map<String, String> errorCodesMap = new HashMap<String, String>();
    	
    	errorCodesMap.put(POSConstants.ECR_Application_ID, "No user is logged on POS"); //{en desc, ar desc}
    	errorCodesMap.put("002", "POS is not connected to host (Check Phone Line) or IP");
    	errorCodesMap.put("003", "POS Batch is full, please settle batch");
    	errorCodesMap.put("004", "Settlement was attempted, batch should be settled first");
    	errorCodesMap.put("005", "Used card is expired");
    	errorCodesMap.put("006", "Card is not readable");
    	errorCodesMap.put("007", "Card Swipe or Insertion Timed Out");
    	errorCodesMap.put("008", "Card range is not supported on POS");
    	errorCodesMap.put("009", "Card Track I is not readable");
    	errorCodesMap.put("010", "User Canceled Transaction on POS");
    	errorCodesMap.put("011", "POS was not able to reach host");
    	errorCodesMap.put("012", "Transaction is not allowed on POS");
    	errorCodesMap.put("013", "Original Transaction not found in POS batch");
    	errorCodesMap.put("014", "User pressed Cancel for PIN, PIN Bypass is not allowed");
    	errorCodesMap.put("015", "User Failed to Verify Transaction Password");
    	errorCodesMap.put("016", "Chip Card Was Removed From POS");
    	errorCodesMap.put("017", "Transaction Declined by Chip Card");
    	errorCodesMap.put("018", "Fall back from ICC to Magnetic stripe has failed");
    	errorCodesMap.put("019", "Message LRC is not correct");
    	errorCodesMap.put("020", "Message length in header does not match physical length");
    	errorCodesMap.put("021", "Requested transaction type is not recognized");
    	errorCodesMap.put("022", "Parsing error, message is not formatted correctly");
    	errorCodesMap.put("023", "PC response was not received in time");
    	errorCodesMap.put("024", "Card Mismatch");
    	errorCodesMap.put("025", "Change Password Required");
    	errorCodesMap.put("026", "Paper Out");
    	errorCodesMap.put("027", "Invalid PAN, MOD-10 check");
    	errorCodesMap.put("028", "Manual PAN entry not allowed");
    	errorCodesMap.put("029", "Settlement was attempted, batch is empty");
    	errorCodesMap.put("030", "Service Code Check, Chip card swiped");
    	errorCodesMap.put("031", "Service Code Check, no PIN verification available");
    	errorCodesMap.put("032", "Service Code Check, Reject");
    	errorCodesMap.put("033", "Service Code Check, wrong value");
    	errorCodesMap.put("034", "Manual not Allowed By Issuer");
    	errorCodesMap.put("035", "Swipe not Allowed By Issuer");
    	errorCodesMap.put("036", "Chip not Allowed By Issuer");
    	errorCodesMap.put("037", "Requested eServices exceed the maximum");
    	errorCodesMap.put("038", "ECR ID Number Mismatch");
    	errorCodesMap.put("039", "SERVICE PROCESS FAILED");
    	errorCodesMap.put("040", "General ECR Error INCOMPLETE ECR TRANSACTION");
    	errorCodesMap.put("041", "INVALID MERCHANT ID");
    	errorCodesMap.put("042", "INVALID ECR VERSION");
    	errorCodesMap.put("043", "INVALID TERMINAL ID");
    	errorCodesMap.put("044", "INVALID ECR APPLCIATION ID");
    	
    	errorCodesMap.put("H01", "Referral - Call card issuer");
    	errorCodesMap.put("H02", "Call card issuer, special condition");
    	errorCodesMap.put("H03", "Call Help");
    	errorCodesMap.put("H04", "Pickup Card");
    	errorCodesMap.put("H05", "Transaction declined, do not honor");
    	errorCodesMap.put("H06", "Pickup Card");
    	errorCodesMap.put("H07", "Invalid transaction amount"); 
    	
    	errorCodesMap.put("H12", "Invalid Transaction");
    	errorCodesMap.put("H15", "Card unknown");
    	errorCodesMap.put("H19", "Re-Enter Transaction");
    	
    	errorCodesMap.put("H25", "Unable to locate record on file");
    	
    	errorCodesMap.put("H30", "Format Error");
    	errorCodesMap.put("H31", "Bank not supported by switch");
    	errorCodesMap.put("H32", "Card not Supported");
    	
    	errorCodesMap.put("H41", "Lost Card");
    	errorCodesMap.put("H43", "Stolen Card - Pickup Card");
    	
    	errorCodesMap.put("H51", "No sufficient Funds");
    	errorCodesMap.put("H54", "Expired Card");    	
    	errorCodesMap.put("H55", "Incorrect PIN");
    	errorCodesMap.put("H56", "Incorrect PassCode");
    	errorCodesMap.put("H58", "Payment Request is Invalid");
    	
    	errorCodesMap.put("H60", "Transaction not permitted to terminal");
    	errorCodesMap.put("H61", "Maximum Sale value reached");
    	errorCodesMap.put("H63", "Error in Cryptogram");
    	errorCodesMap.put("H64", "Requested Action Not allowed");
    	errorCodesMap.put("H65", "Batch Not Found");
    	errorCodesMap.put("H66", "Branch Not Found");
    	errorCodesMap.put("H67", "Branch Deleted");
    	errorCodesMap.put("H68", "Section Not Found");
    	errorCodesMap.put("H69", "Section Not Active");
    	
    	errorCodesMap.put("H70", "Section Deleted");
    	errorCodesMap.put("H71", "Shortcut Not Assigned");
    	errorCodesMap.put("H72", "Old Password is Empty");
    	errorCodesMap.put("H73", "New Password is Empty");
    	errorCodesMap.put("H74", "Password Less than Minimum Length");
    	errorCodesMap.put("H75", "Password More than Maximum Length");
    	errorCodesMap.put("H76", "Invalid Product Code");
    	errorCodesMap.put("H77", "Reconcile Error");
    	errorCodesMap.put("H78", "Trace Number not Found");
    	errorCodesMap.put("H79", "Transaction Declined, Wrong CVV");
    	
    	errorCodesMap.put("H80", "Batch Number not Found");
    	errorCodesMap.put("H82", "Password Changed Successfully");
    	errorCodesMap.put("H85", "Batch not Found on Host");
    	errorCodesMap.put("H86", "Terminal Not Found");
    	errorCodesMap.put("H87", "Terminal Not Active");
    	errorCodesMap.put("H88", "Terminal Merchant Error");
    	errorCodesMap.put("H89", "Terminal ID is not valid");
    	
    	errorCodesMap.put("H90", "Invalid Terminal IP");
    	errorCodesMap.put("H91", "Issuer or switch inoperative");
    	errorCodesMap.put("H94", "Duplicate Transmission");
    	errorCodesMap.put("H95", "Reconcile Error, Batch upload started");
    	errorCodesMap.put("H96", "System Malfunction");
    	
    	errorCodesMap.put("HA0", "Backend Error");
    	errorCodesMap.put("HA4", "Card Is Not Numeric");
    	errorCodesMap.put("HA5", "Length Is Invalid");
    	errorCodesMap.put("HA6", "Card Number Is Missing");
    	errorCodesMap.put("HA7", "Holder Name Missing");
    	errorCodesMap.put("HA9", "Card Is Banned");
    	    	
    	errorCodesMap.put("HB0", "Invalid Card Type");
    	errorCodesMap.put("HB2", "No Details Found");
    	errorCodesMap.put("HB3", "Bank Id Missing");
    	errorCodesMap.put("HB4", "Bank Isn't Available");
    	errorCodesMap.put("HB5", "Merchant Does Not Exist");
    	errorCodesMap.put("HB6", "No Active Merchant");
    	errorCodesMap.put("HB7", "Merchant Not Received");
    	errorCodesMap.put("HB8", "Inactive Merchant");
    	errorCodesMap.put("HB9", "Currency Does Not Exist");
    	
    	errorCodesMap.put("HC0", "Currency Not Supported");
    	errorCodesMap.put("HC2", "Transaction Not Available");
    	errorCodesMap.put("HC3", "Transaction Not Validated");
    	errorCodesMap.put("HC4", "Error Not Available");
    	errorCodesMap.put("HC5", "Parameter Not Available");
    	errorCodesMap.put("HC6", "Sign Is not Verified");
    	errorCodesMap.put("HC7", "Amount Not Received");
    	errorCodesMap.put("HC8", "Currency Not Received");
    	errorCodesMap.put("HC9", "PUN Was Not Received");
    	
    	errorCodesMap.put("HD0", "Lang Not Received");
    	errorCodesMap.put("HD1", "Action not Received");
    	errorCodesMap.put("HD2", "Date Not Received");
    	errorCodesMap.put("HD3", "Conf Id Not Received");
    	errorCodesMap.put("HD4", "Status Not Received");
    	errorCodesMap.put("HD5", "Stat Msg Not Received");
    	errorCodesMap.put("HD6", "Action Type Not Avail");
    	errorCodesMap.put("HD7", "Payment Not Supported");
    	errorCodesMap.put("HD8", "Parameters Not Received");
    	errorCodesMap.put("HD9", "Payment Not Safe Pg");
    	
    	errorCodesMap.put("HE0", "Payment Not Safe");
    	errorCodesMap.put("HE1", "Missing Signature");
    	errorCodesMap.put("HE2", "Missing Parameter");
    	errorCodesMap.put("HE3", "Wrong Account Format");
    	errorCodesMap.put("HE4", "Amount Is Zero Error");
    	errorCodesMap.put("HE5", "Amount Exceed Limit");
    	errorCodesMap.put("HE6", "PUN Already Exists");
    	errorCodesMap.put("HE7", "Transaction Request Received");
    	errorCodesMap.put("HE8", "Signature Verification Failed");
    	errorCodesMap.put("HE9", "Currency Not Supported");
    	errorCodesMap.put("HEF", "Inquiry Not Found");
    	errorCodesMap.put("HES", "Inquiry Not Successful");
    	errorCodesMap.put("HEV", "Inquiry Not Valid");
    	
    	errorCodesMap.put("HF0", "Missing Parameters - Pc");
    	errorCodesMap.put("HF1", "Missing Parameters");
    	errorCodesMap.put("HF2", "Invalid Signature");    	
    	errorCodesMap.put("HF4", "Invalid Pay Method");    	
    	errorCodesMap.put("HF6", "Invalid Cancel Method");
    	errorCodesMap.put("HF7", "Cancel Error");    	
    	errorCodesMap.put("HF9", "Payment Failed");
    	
    	errorCodesMap.put("HH0", "Invalid Action");    	
    	errorCodesMap.put("HH2", "Invalid Action");
    	errorCodesMap.put("HH3", "Merchant Validation Error");
    	errorCodesMap.put("HH4", "Currency Validation Error");
    	errorCodesMap.put("HH5", "Trans Validation Error");
    	errorCodesMap.put("HH6", "Invalid Msg Format");
    	errorCodesMap.put("HH7", "Trans Sign Error");
    	errorCodesMap.put("HH8", "Trans Corrupted Data");
    	errorCodesMap.put("HH9", "Trans Not Enough Credit");
    	
    	errorCodesMap.put("HI0", "Request Not Supported");    	errorCodesMap.put("I0", "Request Not Supported");
    	errorCodesMap.put("HI1", "User Does Not Exist");
    	errorCodesMap.put("HI3", "Min Amount");
    	errorCodesMap.put("HI4", "Max Amount");
    	errorCodesMap.put("HI5", "Ineligible Card Info");
    	errorCodesMap.put("HI6", "Payment Attempted");
    	errorCodesMap.put("HI7", "Payment Auth Error");
    	errorCodesMap.put("HI8", "Payment Auth Failed");
    	errorCodesMap.put("HI9", "Invalid Err Code");
    	
    	errorCodesMap.put("HJ0", "Card Enrolled");    	
    	errorCodesMap.put("HJ2", "Pay Invalid Response");
    	errorCodesMap.put("HJ3", "Invalid Response Cert");
    	errorCodesMap.put("HJ4", "Backend Error");
    	errorCodesMap.put("HJ5", "Authnet Not Available");    	
    	errorCodesMap.put("HJ7", "Action Not Supported");
    	errorCodesMap.put("HJ8", "IP Not Supported");
    	errorCodesMap.put("HJ9", "Card Validation Failed");
    	
    	errorCodesMap.put("HK0", "Invalid Merchant Type Id"); 
    	errorCodesMap.put("HK1", "Inquiry Already Used"); 
    	errorCodesMap.put("HK2", "Merchant Not Authorized");
    	errorCodesMap.put("HK3", "Query Missing");
    	errorCodesMap.put("HK4", "Query Does Not Exist");
    	errorCodesMap.put("HK5", "Query Not Unique");
    	errorCodesMap.put("HK6", "Terminal Id Mismatch");
    	errorCodesMap.put("HK7", "Amounts Do Not Match");
    	errorCodesMap.put("HK8", "Method Not Supported");
    	
    	errorCodesMap.put("HL0", "Service Mismatch"); 
    	errorCodesMap.put("HL1", "Invalid URLs"); 
    	errorCodesMap.put("HL2", "Merchant Id Mismatch");
    	errorCodesMap.put("HL3", "Received Parameter Missing");
    	errorCodesMap.put("HL4", "URLs Incorrect");
    	errorCodesMap.put("HL5", "Authorization Failed");
    	errorCodesMap.put("HL6", "Verification Failed");
    	errorCodesMap.put("HL7", "No Records Found");
    	errorCodesMap.put("HL8", "Request Reversal");
    	errorCodesMap.put("HL9", "Trans Not Found");
    	
    	errorCodesMap.put("HN0", "Trans Previously Voided"); 
    	errorCodesMap.put("HN1", "As Not Authorized"); 
    	errorCodesMap.put("HN2", "Pay Voucher Generated");
    	errorCodesMap.put("HN3", "Void Grace Period Finished");
    	errorCodesMap.put("HN4", "Backend Error");
    	errorCodesMap.put("HN5", "No Available Trans");
    	errorCodesMap.put("HN6", "Backend Error");
    	errorCodesMap.put("HN7", "Unexpected Msg Strc");
    	errorCodesMap.put("HN8", "Trans Not Marked");
    	errorCodesMap.put("HN9", "Void Trans Failed");
    	
    	errorCodesMap.put("HM0", "Pending Trans Error"); 
    	errorCodesMap.put("HM1", "Failed Transaction"); 
    	errorCodesMap.put("HM2", "Backend Error");
    	errorCodesMap.put("HM3", "Trans Previously Updated");
    	errorCodesMap.put("HM4", "Original Trans Missing");
    	errorCodesMap.put("HM6", "Encryption Error");
    	errorCodesMap.put("HM7", "Decryption Error");
    	errorCodesMap.put("HM8", "Failed Conversion");
    	errorCodesMap.put("HM9", "Card Type Not Supported");
    	    	
    	errorCodesMap.put("HO1", "Void Trans Not Found"); 
    	errorCodesMap.put("HO3", "Refund Not Pre-Approved");     	
    	errorCodesMap.put("HO4", "Trans Previously Refunded");
    	errorCodesMap.put("HO5", "Acq Server Not Authorized");
    	errorCodesMap.put("HO6", "Voucher Previously Generated");
    	errorCodesMap.put("HO7", "Exceeded Refund Grace Period");    	    	
    	errorCodesMap.put("HO9", "Refund Trans Error");
    	
    	errorCodesMap.put("HP1", "Fraud Authentication Failed"); 
    	errorCodesMap.put("HP2", "Account Num Missing");
    	errorCodesMap.put("HP3", "Id Black List Match");
    	errorCodesMap.put("HP4", "Bank Black List Match");
    	errorCodesMap.put("HP5", "Account Black List Match");
    	errorCodesMap.put("HP6", "Bin Black List Match");
    	errorCodesMap.put("HP7", "Card Rejection Match");
    	errorCodesMap.put("HP8", "Country Black List Match");
    	errorCodesMap.put("HP9", "IP Black List Match");
    	
    	errorCodesMap.put("HQ0", "Risk Rule Violation"); 
    	errorCodesMap.put("HQ1", "Risk Rule Violation"); 
    	errorCodesMap.put("HQ2", "Risk Rule Violation");
    	errorCodesMap.put("HQ3", "Risk Rule Violation");
    	errorCodesMap.put("HQ4", "Pay Invoice Missing");
    	errorCodesMap.put("HQ5", "Invoice Item Missing");
    	errorCodesMap.put("HQ6", "Invalid Xmlpay Tendr");
    	errorCodesMap.put("HQ7", "Pay Duplicate Payment");
    	errorCodesMap.put("HQ8", "Pay Merchant Id Missing");
    	errorCodesMap.put("HQ9", "3ds Check Failed");
    	
    	errorCodesMap.put("HR0", "Pay Trxn Not Found"); 
    	errorCodesMap.put("HR1", "Pay Amount < 1"); 
    	errorCodesMap.put("HR2", "Currency Not Found");    	
    	errorCodesMap.put("HR4", "Inactive Service Owner");
    	errorCodesMap.put("HR5", "Inactive Eservice");
    	errorCodesMap.put("HR6", "Inactive E-Dirham Service");
    	errorCodesMap.put("HR7", "Exceeding Max #");
    	errorCodesMap.put("HR8", "Exceeding Max #");
    	errorCodesMap.put("HR9", "Service Not Assigned");
    	
    	errorCodesMap.put("HS0", "Wrong Transaction"); 
    	errorCodesMap.put("HS1", "Trans Inquiry Failed"); 
    	errorCodesMap.put("HS2", "Amounts Differ"); 
    	errorCodesMap.put("HS3", "Service With 0 Price"); 
    	errorCodesMap.put("HS4", "Service Not Assigned");
    	errorCodesMap.put("HS5", "Service Not Assigned");
    	errorCodesMap.put("HS6", "Backend Error");
    	errorCodesMap.put("HS7", "Trans Not Pre-Auth");
    	errorCodesMap.put("HS8", "Update Trans Failed");
    	
    	errorCodesMap.put("HTO", "Session Time Out"); // O not zero    	
    	errorCodesMap.put("HT1", "Transaction Started"); 
    	errorCodesMap.put("HT2", "Transaction Complete"); 
    	errorCodesMap.put("HT3", "Transaction Pending"); 
    	errorCodesMap.put("HT4", "Transaction Rejected");
    	errorCodesMap.put("HT5", "Original Trans Not Found");
    	errorCodesMap.put("HTP", "Payment Trans Pending");
    	
    	errorCodesMap.put("HV1", "EOD Settlement Mismatch"); 
    	errorCodesMap.put("HV2", "Original Trans Not Auth");
    	errorCodesMap.put("HV3", "Sale Request Code");
    	errorCodesMap.put("HV4", "Complete Request Code");
    	errorCodesMap.put("HV5", "Topup Request Code");
    	errorCodesMap.put("HV6", "Refund Request Code");
    	errorCodesMap.put("HV7", "Void Request Code");
    	errorCodesMap.put("HV8", "Comp IP Not Supported");
    	errorCodesMap.put("HV9", "Amounts Differ");
    	
    	errorCodesMap.put("HW1", "Card None Existent"); 
    	errorCodesMap.put("HW2", "Query None Existent");
    	errorCodesMap.put("HW3", "Request None Existent");
    	errorCodesMap.put("HW4", "Account None Existent");
    	
    	//errorCodesMap.put("H**", "All Other Response Codes");
      	
    	return errorCodesMap;
    }
    
    public static String getErrorCodeDescription(String errorCode){
    	    	
    	String errorCodeDesc = "Unknown Error";
    	
    	Map<String, String> errorCodesMap = getErrorCodesMap();
    	
    	if(errorCodesMap.containsKey(errorCode)){
    		errorCodeDesc = errorCodesMap.get(errorCode);
    	}
    	
    	return errorCodeDesc;
    }
}
