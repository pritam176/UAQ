package com.uaq.vo;

/***
 * 
 * @author Gsekhar
 * 
 *         output VO for Land and Property Request
 * 
 */
public class LandandPropertyOutputVO {

	private String statusFlag;
	private String status_EN;
	private String status_AR;
	private String executionStatus;

	public String getStatusFlag() {
		return statusFlag;
	}

	public void setStatusFlag(String statusFlag) {
		this.statusFlag = statusFlag;
	}

	public String getStatus_EN() {
		return status_EN;
	}

	public void setStatus_EN(String status_EN) {
		this.status_EN = status_EN;
	}

	public String getStatus_AR() {
		return status_AR;
	}

	public void setStatus_AR(String status_AR) {
		this.status_AR = status_AR;
	}

	public String getExecutionStatus() {
		return executionStatus;
	}

	public void setExecutionStatus(String executionStatus) {
		this.executionStatus = executionStatus;
	}

}
