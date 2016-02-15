/**
 * 
 */
package com.uaq.vo;

/**
 * @author Akhil
 * 
 */
public class FaqVO extends BaseVO {

	private String questionText;
	private String answerText;

	/**
	 * @return the questionText
	 */
	public String getQuestionText() {
		return questionText;
	}

	/**
	 * @param questionText
	 *            the questionText to set
	 */
	public void setQuestionText(String questionText) {
		this.questionText = questionText;
	}

	/**
	 * @return the answerText
	 */
	public String getAnswerText() {
		return answerText;
	}

	/**
	 * @param answerText
	 *            the answerText to set
	 */
	public void setAnswerText(String answerText) {
		this.answerText = answerText;
	}

}
