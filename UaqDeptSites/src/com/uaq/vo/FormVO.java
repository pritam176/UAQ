package com.uaq.vo;

import java.util.List;

public class FormVO extends BaseVO {

	String text;

	String formSubmissionType; // poll, survey

	List<FormFieldVO> formFields;

	private PageMetadataVO pageMetadataVO;

	public PageMetadataVO getPageMetadataVO() {
		return pageMetadataVO;
	}

	public void setPageMetadataVO(PageMetadataVO pageMetadataVO) {
		this.pageMetadataVO = pageMetadataVO;
	}

	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}

	/**
	 * @param text
	 *            the text to set
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * @return the formFields
	 */
	public List<FormFieldVO> getFormFields() {
		return formFields;
	}

	/**
	 * @param formFields
	 *            the formFields to set
	 */
	public void setFormFields(List<FormFieldVO> formFields) {
		this.formFields = formFields;
	}

	/**
	 * @return the formSubmissionType
	 */
	public String getFormSubmissionType() {
		return formSubmissionType;
	}

	/**
	 * @param formSubmissionType
	 *            the formSubmissionType to set
	 */
	public void setFormSubmissionType(String formSubmissionType) {
		this.formSubmissionType = formSubmissionType;
	}

}
