package com.uaq.controller.mapper;

import java.util.ArrayList;
import java.util.Calendar;

import org.apache.commons.lang.StringUtils;

import com.uaq.command.WasteContainerCommand;
import com.uaq.util.StringUtil;
import com.uaq.vo.PWSReSubmissonOuputVO;
import com.uaq.vo.WasteContainerRequestInputVO;

public class PWSDataMapper {

	public static WasteContainerRequestInputVO setcommandToVO(WasteContainerCommand command) {
		WasteContainerRequestInputVO wasteContainerRequestInputVO = new WasteContainerRequestInputVO();
		wasteContainerRequestInputVO.setAddress(command.getAddress());
		java.util.List<String> fileListName = new ArrayList<String>();

		if (!StringUtil.isEmpty(command.getWase_container_file_0_name())) {
			fileListName.add(command.getWase_container_file_0_name());
		}

		if (!StringUtil.isEmpty(command.getWase_container_file_1_name())) {
			fileListName.add(command.getWase_container_file_1_name());
		}

		if (!StringUtil.isEmpty(command.getWase_container_file_2_name())) {
			fileListName.add(command.getWase_container_file_2_name());
		}

		if (!StringUtil.isEmpty(command.getWase_container_file_3_name())) {
			fileListName.add(command.getWase_container_file_3_name());
		}

		wasteContainerRequestInputVO.setFiles(fileListName);
		wasteContainerRequestInputVO.setFindLocation(command.getFindLocation());
		wasteContainerRequestInputVO.setLatitude(command.getLatitude());
		wasteContainerRequestInputVO.setLongitude(command.getLongitude());
		wasteContainerRequestInputVO.setReplacement(StringUtils.isBlank(command.getReplacement()) ? " " : command.getReplacement());
		wasteContainerRequestInputVO.setServiceType(command.getServiceType());
		wasteContainerRequestInputVO.setSubmissionTime(Calendar.getInstance());
		return wasteContainerRequestInputVO;

	}

	public static WasteContainerCommand settingWasteContinorCommand(PWSReSubmissonOuputVO resubmissionVo) {
		WasteContainerCommand command = new WasteContainerCommand();
		command.setAddress(resubmissionVo.getAddress1());
		command.setLatitude(resubmissionVo.getLatitude());
		command.setLongitude(resubmissionVo.getLongitude());

		if (StringUtil.isEmpty(resubmissionVo.getReplacementReason())) {
			command.setServiceType("New Container");
		}else{
			command.setServiceType("replacement");
		}

		command.setReplacement(resubmissionVo.getReplacementReason());
		command.setWasteContainerattachmentRecPayload(resubmissionVo.getWasteContainerattachmentRecPayload());
		command.setUserComments(resubmissionVo.getUserComments());

		command.setWasteCOntainoerId(String.valueOf(resubmissionVo.getId()));
		command.setRequestNo(resubmissionVo.getRequestNo());
		command.setServiceId(String.valueOf(resubmissionVo.getServiceId()));
		command.setSorceType(resubmissionVo.getSourceType());
		command.setStatus(resubmissionVo.getStatus());
		return command;

	}

}
