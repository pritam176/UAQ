package com.uaq.vo;

public class QuizVO {

	private String question; // formField assetId

	private String[] answer; // options Ids

	private String formFieldType; // radio button, checkbox, dropdown (single
									// select, multi select)

	/**
	 * @return the question
	 */
	public String getQuestion() {
		return question;
	}

	/**
	 * @param question
	 *            the question to set
	 */
	public void setQuestion(String question) {
		this.question = question;
	}

	/**
	 * @return the answer
	 */
	public String[] getAnswer() {
		return answer;
	}

	/**
	 * @param answer
	 *            the answer to set
	 */
	public void setAnswer(String[] answer) {
		this.answer = answer;
	}

	/**
	 * @return the formFieldType
	 */
	public String getFormFieldType() {
		return formFieldType;
	}

	/**
	 * @param formFieldType
	 *            the formFieldType to set
	 */
	public void setFormFieldType(String formFieldType) {
		this.formFieldType = formFieldType;
	}

}