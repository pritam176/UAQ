/**
 * 
 */
package com.uaq.controller.mapper;

import com.uaq.command.WhomItmayConcernCommand;
import com.uaq.common.ServiceNameConstant;
import com.uaq.util.StringUtil;
import com.uaq.vo.AccountDetailTokenOutputVO;
import com.uaq.vo.LPtoWhomeConcernVO;
import com.uaq.vo.WhomItmayConcernVO;

/**
 * @author TACME
 *
 */
public class LPDataMapper {
	
	
	public static WhomItmayConcernVO getWhomItmayConcernVOForProceedToOpertor (LPtoWhomeConcernVO lptoWhomeConcernVO){
		
		WhomItmayConcernVO whomItmayConcernVO=new WhomItmayConcernVO();
	    whomItmayConcernVO.setAddressTo(String.valueOf(lptoWhomeConcernVO.getAddressedtoId()));
        whomItmayConcernVO.setFamilyBookNo(lptoWhomeConcernVO.getFamilyBookNum());
        whomItmayConcernVO.setSpouseIdNo(lptoWhomeConcernVO.getSpouseId());
        whomItmayConcernVO.setSource("1");
        whomItmayConcernVO.setServiceId(ServiceNameConstant.ISSUE_TO_WHOME_IT_MAY_CERTIFICATE);
        whomItmayConcernVO.setUserCommnets("");
        whomItmayConcernVO.setOther(lptoWhomeConcernVO.getOther());
        whomItmayConcernVO.setRequestId(lptoWhomeConcernVO.getReqId().intValue());
        whomItmayConcernVO.setRequestNo(String.valueOf(lptoWhomeConcernVO.getRequestNo()));
        whomItmayConcernVO.setWorkListId(1);
        //For Proceed to reviwer the status should be 41 for Operartor
        whomItmayConcernVO.setStateId(48);
        whomItmayConcernVO.setToWhomItmayId(lptoWhomeConcernVO.getTowhomeReqId()==null?new Integer(0): lptoWhomeConcernVO.getTowhomeReqId().intValue());
        whomItmayConcernVO.setTempToWhomItmayId(1);
        whomItmayConcernVO.setServiceName_En(ServiceNameConstant.ISSUE_TO_WHOME_IT_MAY_CERTIFICATE_NAME);
        whomItmayConcernVO.setServiceName_Ar("to whom it may concern");
		
		return whomItmayConcernVO;
	}
	
	public static WhomItmayConcernCommand setWhomItmayConcernCommand(LPtoWhomeConcernVO lptoWhomeConcernVO){
		
		WhomItmayConcernCommand whomItmayConcernCommand = new WhomItmayConcernCommand();
		whomItmayConcernCommand.setAddressTo(String.valueOf(lptoWhomeConcernVO.getAddressedtoId()));
		whomItmayConcernCommand.setFamilyBookNo(lptoWhomeConcernVO.getFamilyBookNum());
		whomItmayConcernCommand.setSpouseIdNo(lptoWhomeConcernVO.getSpouseId());
		whomItmayConcernCommand.setOther(lptoWhomeConcernVO.getOther());
		whomItmayConcernCommand.setScanFamilyBook_name("");
		whomItmayConcernCommand.setSposeEmiratesId_name("");
		whomItmayConcernCommand.setRequestNo(lptoWhomeConcernVO.getRequestNo());
		whomItmayConcernCommand.setStatusId(lptoWhomeConcernVO.getStatus());
		whomItmayConcernCommand.setTowhomeReqId(String.valueOf(lptoWhomeConcernVO.getTowhomeReqId()));
		
		
		return whomItmayConcernCommand;
		
	}
	
	public static WhomItmayConcernVO getWhomItmayConcernVOForResubmit (WhomItmayConcernCommand whomItmayConcernCommand){
		

		WhomItmayConcernVO whomItmayConcernVO = new WhomItmayConcernVO();
		
		whomItmayConcernVO.setAddressTo(whomItmayConcernCommand.getAddressTo());
		whomItmayConcernVO.setFamilyBookNo(whomItmayConcernCommand.getFamilyBookNo());
		whomItmayConcernVO.setSpouseIdNo(whomItmayConcernCommand.getSpouseIdNo());
		whomItmayConcernVO.setScanFamilyBook(whomItmayConcernCommand.getScanFamilyBook_name());
        whomItmayConcernVO.setSposeEmiratesId(whomItmayConcernCommand.getSposeEmiratesId_name());
        whomItmayConcernVO.setOther(whomItmayConcernCommand.getOther());
        
        whomItmayConcernVO.setSource("1");
        whomItmayConcernVO.setServiceId("405");
        whomItmayConcernVO.setUserCommnets("");
        

        whomItmayConcernVO.setRequestId(Integer.parseInt(whomItmayConcernCommand.getRequestNo().split("-")[3]));
        whomItmayConcernVO.setRequestNo(whomItmayConcernCommand.getRequestNo());
       
      //For Proceed to reviwer the status should be 2
        whomItmayConcernVO.setStateId(2);
        whomItmayConcernVO.setToWhomItmayId(StringUtil.isEmpty(whomItmayConcernCommand.getTowhomeReqId())? new Integer(0):new Integer(whomItmayConcernCommand.getTowhomeReqId()));
        whomItmayConcernVO.setTempToWhomItmayId(1);
        whomItmayConcernVO.setServiceName_En("to whom it may concern");
        whomItmayConcernVO.setServiceName_Ar("to whom it may concern");

        whomItmayConcernVO.setPaymentId(7);
        whomItmayConcernVO.setPaymentStatus("");
        whomItmayConcernVO.setServiceFee("");
        whomItmayConcernVO.setApplicationFee("");
        whomItmayConcernVO.setDeptID("");
        whomItmayConcernVO.setAppFeeDisc("");
        whomItmayConcernVO.setServiceFeeDisc("");
        whomItmayConcernVO.setEdirhamServCode("");
        whomItmayConcernVO.setIsPaymentRequired("");
        
		return whomItmayConcernVO;
		
	}
	public static WhomItmayConcernVO getAddressToDetails(AccountDetailTokenOutputVO accountDetailfromToken, WhomItmayConcernCommand whomItmayConcernCommand) {

		WhomItmayConcernVO whomItmayConcernVO = new WhomItmayConcernVO();

		whomItmayConcernVO.setAddressTo(whomItmayConcernCommand.getAddressTo());
		whomItmayConcernVO.setFamilyBookNo(whomItmayConcernCommand.getFamilyBookNo());
		whomItmayConcernVO.setSpouseIdNo(whomItmayConcernCommand.getSpouseIdNo());
		whomItmayConcernVO.setSource("1");
		whomItmayConcernVO.setServiceId("405");
		whomItmayConcernVO.setUserCommnets("");
		whomItmayConcernVO.setOther(whomItmayConcernCommand.getOther());

		whomItmayConcernVO.setRequestId(1);
		whomItmayConcernVO.setRequestNo("");
		whomItmayConcernVO.setWorkListId(1);
		whomItmayConcernVO.setStateId(1);
		whomItmayConcernVO.setToWhomItmayId(1);
		whomItmayConcernVO.setTempToWhomItmayId(1);
		whomItmayConcernVO.setServiceName_En("to whom it may concern");
		whomItmayConcernVO.setServiceName_Ar("to whom it may concern");

		whomItmayConcernVO.setPaymentId(1);
		whomItmayConcernVO.setPaymentStatus("");
		whomItmayConcernVO.setServiceFee("");
		whomItmayConcernVO.setApplicationFee("");
		whomItmayConcernVO.setDeptID("");
		whomItmayConcernVO.setAppFeeDisc("");
		whomItmayConcernVO.setServiceFeeDisc("");
		whomItmayConcernVO.setEdirhamServCode("");
		whomItmayConcernVO.setIsPaymentRequired("");

		whomItmayConcernVO.setScanFamilyBook(whomItmayConcernCommand.getScanFamilyBook_name());
		whomItmayConcernVO.setSposeEmiratesId(whomItmayConcernCommand.getSposeEmiratesId_name());

		return whomItmayConcernVO;
	}

}
