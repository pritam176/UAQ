package com.uaq.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.fatwire.rest.beans.AssetBean;
import com.fatwire.wem.sso.SSOException;
import com.uaq.command.SurveyCommand;
import com.uaq.common.AssetUtil;
import com.uaq.controller.mapper.BaseVOMapper;
import com.uaq.dao.GenericDao;
import com.uaq.dao.SurveyDAO;
import com.uaq.exception.UAQException;
import com.uaq.logger.UAQLogger;
import com.uaq.vo.FormFieldVO;
import com.uaq.vo.FormVO;
import com.uaq.vo.LandingPageVO;
import com.uaq.vo.NavigationVO;
import com.uaq.vo.OptionResultVO;

/**
 * 
 * @author mraheem
 * 
 */
@Service(value = "surveyService")
public class SurveyService implements BaseService<SurveyCommand, Boolean> {

	public static transient UAQLogger logger = new UAQLogger(SurveyService.class);

	@Autowired
	@Qualifier("surveyDAO")
	SurveyDAO dao;

	@Autowired
	@Qualifier("landingPageVOMapper")
	private BaseVOMapper landingPageVOMapper;

	@Autowired
	@Qualifier("formVOMapper")
	private BaseVOMapper formVOMapper;

	@Autowired
	@Qualifier("formFieldVOMapper")
	private BaseVOMapper formFieldVOMapper;

	@Autowired
	@Qualifier("webReferneceDAO")
	GenericDao<NavigationVO, NavigationVO> webReferneceDAO;

	/**
	 * save the survey
	 */
	@Override
	public Boolean execute(SurveyCommand surveyCommand) throws UAQException {
		return dao.execute(surveyCommand);
	}

	@Override
	public Boolean executeSites(SurveyCommand inputObject) throws UAQException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * This is a facade method for getting all polls
	 * 
	 * @param site
	 * @param landingPageId
	 * @param ticket
	 * @return
	 */
	public SurveyCommand getPolls(NavigationVO navigationVO) {

		logger.enter("getPolls");

		SurveyCommand surveyCommand = getPollsAndSurveys(navigationVO, true);

		logger.exit("getPolls");

		return surveyCommand;
	}

	/**
	 * This is a facade method for getting the survey
	 * 
	 * @param site
	 * @param landingPageId
	 * @param ticket
	 * @return
	 */

	public SurveyCommand getSurvey(NavigationVO navigationVO) {

		logger.enter("getSurvey");

		SurveyCommand surveyCommand = getPollsAndSurveys(navigationVO, false);

		logger.exit("getSurvey");

		return surveyCommand;
	}

	/**
	 * This method is used to fetch polls or survey depending upon facade
	 * request
	 * 
	 * @param site
	 * @param landingPageId
	 * @param isPoll
	 * @param ticket
	 * @return
	 */

	public SurveyCommand getPollsAndSurveys(NavigationVO navigationVO, boolean isPoll) {

		logger.enter("getPollsAndSurveys");

		SurveyCommand surveyCommand = new SurveyCommand();
		List<FormVO> surveyForms = new ArrayList<FormVO>();

		try {

			AssetBean landingPageAssetBean = AssetUtil.getAssetDetail(navigationVO.getSite(), "Page", navigationVO.getAssetId(), navigationVO.getTicketId());
			LandingPageVO landingPageVO = (LandingPageVO) landingPageVOMapper.mapAssetToVO(landingPageAssetBean);

			for (String bodyID : landingPageVO.getBodyAssociatedIds()) {

				AssetBean bodyMainAssociatedAsset = AssetUtil.getAssetDetail(navigationVO.getSite(), "Content_C", bodyID.split(":")[1], navigationVO.getTicketId());
				String associatedAssetSubType = bodyMainAssociatedAsset.getSubtype();

				if (associatedAssetSubType.equals("Form")) {

					List<FormFieldVO> formFields = new ArrayList<FormFieldVO>();

					FormVO formVO = (FormVO) formVOMapper.mapAssetToVO(bodyMainAssociatedAsset);

					if ((isPoll && !formVO.getFormSubmissionType().equalsIgnoreCase("Poll")) || (!isPoll && !formVO.getFormSubmissionType().equalsIgnoreCase("Survey"))) {
						continue;
					}

					for (FormFieldVO formFieldVO : formVO.getFormFields()) {

						AssetBean formFieldAsset = AssetUtil.getAssetDetail(navigationVO.getSite(), "Content_C", formFieldVO.getAssetId(), navigationVO.getTicketId());
						formFieldVO = (FormFieldVO) formFieldVOMapper.mapAssetToVO(formFieldAsset);
						formFields.add(formFieldVO);
					}

					formVO.setFormFields(formFields);
					surveyForms.add(formVO);

					if (!isPoll && formVO.getFormSubmissionType().equals("survey")) {
						break; // max one survey per page
					}
				}
			}

			surveyCommand.setSurveys(surveyForms);

		} catch (SSOException e) {
			logger.error(e.getMessage());
		}

		logger.exit("getPollsAndSurveys");

		return surveyCommand;
	}

	public List<FormVO> getPollsResults(NavigationVO navigationVO) {

		List<FormVO> finalList = new ArrayList<FormVO>();

		List<OptionResultVO> pollsResults = dao.getPollsResults();

		// mix options and results. this is to include the option which doesn't
		// have result
		if (pollsResults != null && pollsResults.size() > 0) {

			SurveyCommand surveyCommand = getPolls(navigationVO);

			for (FormVO poll : surveyCommand.getSurveys()) { // polls/forms

				List<FormFieldVO> resultQuestionsList = new ArrayList<FormFieldVO>();

				for (FormFieldVO question : poll.getFormFields()) { // questions/formFields

					List<OptionResultVO> resultOptionList = new ArrayList<OptionResultVO>();

					for (String option : question.getValues()) { // options

						OptionResultVO result = getOptionResultFromResults(option, poll.getAssetId(), question.getAssetId(), pollsResults);
						// prepare result obj and put in final resultList
						if (result == null) {
							result = new OptionResultVO();
							result.setFormId(poll.getAssetId());
							result.setFormFieldId(question.getAssetId());
							result.setOptionId(option);
							result.setPercentage("0%");
						}
						resultOptionList.add(result);
					}
					question.setOptionsResult(resultOptionList);
					resultQuestionsList.add(question);
				}
				poll.setFormFields(resultQuestionsList);
				finalList.add(poll);
			}
		}

		return finalList;
	}

	private OptionResultVO getOptionResultFromResults(String option, String pollId, String questionId, List<OptionResultVO> pollsResults) {

		OptionResultVO result = null;

		for (OptionResultVO optionResult : pollsResults) {
			if (optionResult.getOptionId().equals(option) && optionResult.getFormId().equals(pollId) && optionResult.getFormFieldId().equals(questionId)) {
				result = optionResult;
				break;
			}
		}

		return result;
	}

}
