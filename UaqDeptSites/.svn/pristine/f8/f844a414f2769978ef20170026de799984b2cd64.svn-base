package com.uaq.command;

import java.io.Serializable;
import java.util.List;

import com.uaq.vo.FormVO;
import com.uaq.vo.OptionResultVO;
import com.uaq.vo.QuizVO;

/**
 * Command Class for Survey.
 * 
 * @author mraheem
 * 
 */
public class SurveyCommand implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String formAssetId; // used at form submission time

	private String languageCode;

	private String sessionId;

	private List<FormVO> surveys; // used to hold all polls or one survey, used
									// before submission

	private List<QuizVO> questionAnswers; // used at form submission time

	private boolean isPoll; // used at form submission time

	private String site;

	private List<OptionResultVO> pollsResults;

	private String subject;

	/**
	 * @return the languageCode
	 */
	public String getLanguageCode() {
		return languageCode;
	}

	/**
	 * @param languageCode
	 *            the languageCode to set
	 */
	public void setLanguageCode(String languageCode) {
		this.languageCode = languageCode;
	}

	/**
	 * @return the sessionId
	 */
	public String getSessionId() {
		return sessionId;
	}

	/**
	 * @param sessionId
	 *            the sessionId to set
	 */
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	/**
	 * @return the surveys
	 */
	public List<FormVO> getSurveys() {
		return surveys;
	}

	/**
	 * @param surveys
	 *            the surveys to set
	 */
	public void setSurveys(List<FormVO> surveys) {
		this.surveys = surveys;
	}

	/**
	 * @return the formAssetId
	 */
	public String getFormAssetId() {
		return formAssetId;
	}

	/**
	 * @param formAssetId
	 *            the formAssetId to set
	 */
	public void setFormAssetId(String formAssetId) {
		this.formAssetId = formAssetId;
	}

	/**
	 * @return the questionAnswers
	 */
	public List<QuizVO> getQuestionAnswers() {
		return questionAnswers;
	}

	/**
	 * @param questionAnswers
	 *            the questionAnswers to set
	 */
	public void setQuestionAnswers(List<QuizVO> questionAnswers) {
		this.questionAnswers = questionAnswers;
	}

	/**
	 * @return the isPoll
	 */
	public boolean isPoll() {
		return isPoll;
	}

	/**
	 * @param isPoll
	 *            the isPoll to set
	 */
	public void setIsPoll(boolean isPoll) {
		this.isPoll = isPoll;
	}

	/**
	 * @return the site
	 */
	public String getSite() {
		return site;
	}

	/**
	 * @param site
	 *            the site to set
	 */
	public void setSite(String site) {
		this.site = site;
	}

	/**
	 * @return the pollsResults
	 */
	public List<OptionResultVO> getPollsResults() {
		return pollsResults;
	}

	/**
	 * @param pollsResults
	 *            the pollsResults to set
	 */
	public void setPollsResults(List<OptionResultVO> pollsResults) {
		this.pollsResults = pollsResults;
	}

	/**
	 * @return the subject
	 */
	public String getSubject() {
		return subject;
	}

	/**
	 * @param subject
	 *            the subject to set
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}

}
