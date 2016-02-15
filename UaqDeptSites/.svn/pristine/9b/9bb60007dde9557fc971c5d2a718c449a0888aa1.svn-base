package com.uaq.controller.mapper;

import com.uaq.command.SubmitNocCommand;
import com.uaq.vo.LandInputVO;
import com.uaq.vo.PSResubmissonOutputVO;

public class SubmitNOCDataMapper {
	
	 public static LandInputVO setdataToSUbmitNOCService(SubmitNocCommand submitNocCommand){
		 LandInputVO landInputVO=new LandInputVO();
		 landInputVO.setRequestNo(submitNocCommand.getRequestNo());
		 landInputVO.setServiceid(submitNocCommand.getServiceId());
		 landInputVO.setSourceType(submitNocCommand.getSorceType());
		 //landInputVO.setSubmitedNocID(did);
		 landInputVO.setSubmitNoc(submitNocCommand.getSubnoc_name());
		return landInputVO;
	 }
	 
	 public static SubmitNocCommand setdataToSUbmitNOCCommand(PSResubmissonOutputVO resubmitdata){
		 SubmitNocCommand submitNocCommand=new SubmitNocCommand();
		 submitNocCommand.setServiceId(String.valueOf(resubmitdata.getServiceId()));
		 submitNocCommand.setSorceType(resubmitdata.getSourcetype());
		 submitNocCommand.setStausId(String.valueOf(resubmitdata.getStatusid()));
		 submitNocCommand.setRequestNo(resubmitdata.getRequestNo());
		 submitNocCommand.setOwnerName(resubmitdata.getOwnername());
		 submitNocCommand.setOwnerId(resubmitdata.getOwnerId());
		 submitNocCommand.setSubmittednocid(String.valueOf(resubmitdata.getSubmittednocid()));
		 submitNocCommand.setSitePalnDOcId(String.valueOf(resubmitdata.getSiteplanDocId()));
		 submitNocCommand.setLandValue(resubmitdata.getLandValue());
		 submitNocCommand.setCommeteRemark(resubmitdata.getCommiteeRemarks());
		 submitNocCommand.setTrueSitePlanDocId(String.valueOf(resubmitdata.getTrueSitePlanDocumentid()));
		 submitNocCommand.setCreatedBy(resubmitdata.getCreatedby());
		 
		 submitNocCommand.setArea("-1".equals(resubmitdata.getAreaNo())?"":resubmitdata.getAreaNo());
		 submitNocCommand.setAreablock("-1".equals(resubmitdata.getAreaBlock())?"":resubmitdata.getAreaBlock());
		 submitNocCommand.setAreaPlotNumber("-1".equals(resubmitdata.getAreaPlotNo())?"":resubmitdata.getAreaPlotNo());
		 submitNocCommand.setBlock("-1".equals(resubmitdata.getSectorBlock())?"":resubmitdata.getSectorBlock());
		 submitNocCommand.setLandUsage(resubmitdata.getLandUsage());
			// command.setLocations(resubmissonVO.get);
		 submitNocCommand.setPlotNumber("-1".equals(resubmitdata.getSecPlotNo())?"":resubmitdata.getSecPlotNo());
		 submitNocCommand.setSector("-1".equals(resubmitdata.getSecSectorNo())?"":resubmitdata.getSecSectorNo());
		 //submitNocCommand.setSiteNumber(resubmitdata.getSitePlanNo());
		 submitNocCommand.setSubarea("-1".equals(resubmitdata.getAreaSubArea())?"":resubmitdata.getAreaSubArea());
		 submitNocCommand.setSubsector("-1".equals(resubmitdata.getSubSectorNo())?"":resubmitdata.getSubSectorNo());
		
			if(resubmitdata.getSubSectorNo()!=null && resubmitdata.getSecPlotNo()!=null && resubmitdata.getSecSectorNo()!=null && !resubmitdata.getSubSectorNo().equals("-1") && !resubmitdata.getSecPlotNo().equals("-1") && !resubmitdata.getSecSectorNo().equals("-1")){
				submitNocCommand.setLocations("sector");
			}else if(resubmitdata.getAreaNo()!=null && resubmitdata.getAreaBlock()!=null && resubmitdata.getAreaSubArea()!=null && !resubmitdata.getAreaNo().equals("-1") && !resubmitdata.getAreaBlock().equals("-1") && !resubmitdata.getAreaSubArea().equals("-1")){
				submitNocCommand.setLocations("area");
			}
		 
		return submitNocCommand;
	 
	 }

}
