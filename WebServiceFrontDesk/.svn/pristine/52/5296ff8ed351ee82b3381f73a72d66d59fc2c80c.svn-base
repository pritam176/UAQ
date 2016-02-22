package com.uaq.ws.pos;

import org.apache.commons.lang.StringUtils;

public class HexUtil {
	
	public static void main(String[] args){
		StringBuffer result= new StringBuffer();
		/*result.append(getHexFromString(StringUtils.leftPad("001", 8, "0")));
		System.out.println(result);*/
		
		
		
		result.append(HexUtil.getDecimalStringFromHexString("023033363830311C303030303135303030303237313139341C311C3030303030303030303330301C3030303030303030303030301C452D53455256494345204F574E455220434F4D4D1C30331C3030303030303030303030301C434F4C4C454354494F4E20434E545220434F4D4D20202020202020202020202020202020202020201C3030303030303030303330301C452D44495248414D20434F4D4D2020202020202020202020202020202020202020202020202020201C3030303030303030303730301C5541512065476F7620666565732020202020202020202020202020202020202020202020202020201C30301C3435353733361C202020202020202020202020202020201C3135303030303237313139341C3030303837301C75736572202020201C30303035313432371C2020202020203933363639363030301C3030303030311C3130313131361C313532391C202020202020202020202020202020201C202020202020202020202020202020201C03")); // 000000-0001
		System.out.println(result);
		//System.out.println(Message.getMessageSalesResponse("3030303030302d30303031202020202020202020"));
	}

	/**
	 * @param args
	 */
	public static String getHexFromString(String args) {

		StringBuffer hexStringBuffer = new StringBuffer();
		for (char charStr : args.toCharArray()) {
			hexStringBuffer.append(Integer.toHexString((int) charStr));
		}
		System.out.println("In :" + args + " : " + hexStringBuffer.toString());
		return hexStringBuffer.toString();
	}
	
	public static String getDecimalStringFromHexString(String hexStr) {

		StringBuffer stringBuffer = new StringBuffer();
		int count = 1;
		String tempStr = "";
		for (char charStr : hexStr.toCharArray()) {	
			tempStr = tempStr + charStr;
			if(count % 2 == 0){ // take two char at a time and convert which is one byte
				stringBuffer.append((char)Long.parseLong(tempStr, 16)) ;
				tempStr = "";
			}			
			count ++;
		}
		
		System.out.println("In = " + hexStr + " : out = " + stringBuffer.toString());
		
		return stringBuffer.toString();
	
	}
	
	public static String calculateLRC(String inputString){
        /*
         * String is Hex String, need to convert in ASCII.
         */
        inputString = convertHexToString(inputString);
        int checksum = 0;
        char[] chars = inputString.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            checksum ^= chars[i];
        }    
        String val = Integer.toHexString(checksum);        
        if(val.length()< 2){
            val = "0"+ val;
        }
        
        return val;       
    }	
		
	public static String convertHexToString(String hex){
        
        StringBuilder sb = new StringBuilder();
        StringBuilder temp = new StringBuilder();             
        //49204c6f7665204a617661 split into two characters 49, 20, 4c...
        for( int i=0; i<hex.length()-1; i+=2 ){
             //grab the hex in pairs
            String output = hex.substring(i, (i + 2));
            //convert hex to decimal
            int decimal = Integer.parseInt(output, 16);                  
                sb.append((char)decimal);
//            }
            temp.append(decimal);
        }         
   
        return sb.toString();
    }
	
	public static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i+1), 16));
        }
        return data;
    }

}
