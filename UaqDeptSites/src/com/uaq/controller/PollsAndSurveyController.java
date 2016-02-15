package com.uaq.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.uaq.command.SurveyCommand;
import com.uaq.common.ViewPath;
import com.uaq.dao.GenericDao;
import com.uaq.exception.DAOException;
import com.uaq.exception.UAQException;
import com.uaq.logger.UAQLogger;
import com.uaq.service.BaseService;
import com.uaq.service.GenericPageService;
import com.uaq.service.SurveyService;
import com.uaq.util.UrlTransliterationUtil;
import com.uaq.vo.FormVO;
import com.uaq.vo.GenericPageVO;
import com.uaq.vo.NavigationVO;

/**
 * @author mraheem
 * 
 *         Controller for Portal Detail Page
 */

@Controller
public class PollsAndSurveyController extends BaseController {

	@Autowired
	@Qualifier("surveyService")
	SurveyService surveyService;

	@Autowired
	@Qualifier("webReferneceDAO")
	GenericDao<NavigationVO, NavigationVO> pageReferneceDAO;
	
	@Autowired
	@Qualifier("genericPageService")
	BaseService<NavigationVO, GenericPageVO> genericPageService;

	public static transient UAQLogger logger = new UAQLogger(DetailPageController.class);

	@RequestMapping(value = { ViewPath.SITES_POLLS, ViewPath.SITES_SURVEY }, method = RequestMethod.GET)
	public String handleRequestSites(@PathVariable("site") String site, @PathVariable("landing") String landing, HttpServletRequest request, ModelMap model) {
		logger.enter("Portal Detail Page | handle Request");

		super.handleDepartmentRequest(request, model, site);

		String view = "polls.surveys";
		String pageName = "";
		String source = (String) model.get("source");
		if (source.contains("/")) {
			pageName = source.split("/")[1];
			pageName = pageName.split("\\.")[0];
		}

		SurveyCommand surveyCommand = null;
		NavigationVO navigationVO = new NavigationVO();
		navigationVO.setSite(site);
		navigationVO.setLanguage(request.getParameter("languageCode"));
		navigationVO.setName(UrlTransliterationUtil.getString(pageName));
		navigationVO.setTicketId(multiticket);
		GenericPageVO genericPageVO = null;
		
		try {

			NavigationVO navigation = pageReferneceDAO.findByPrimaryKey(navigationVO);
			navigationVO.setAssetId(navigation.getAssetId());

			if (pageName.contains("polls")) {
				surveyCommand = surveyService.getPolls(navigationVO);
			} else if (pageName.contains("surveys")) {
				surveyCommand = surveyService.getSurvey(navigationVO);
			}

			genericPageVO = ((GenericPageService)genericPageService).getPageMetaData(navigationVO);
			
		} catch (UAQException e) {
			logger.error(e.getMessage());
		}

		model.addAttribute("surveyCommand", surveyCommand);
		model.addAttribute("pageName", pageName);
		model.addAttribute("landing", landing);
		model.addAttribute("pageMetadata", genericPageVO.getPageMetadataVO());

		return view;
	}

	/**
	 * Process Survey form and save the data in database, using service.
	 * 
	 * @param surveyCommand
	 *            object holding form data.
	 * @param result
	 *            implicit errors object.
	 * @param request
	 *            implicit http request object.
	 * @return view name.
	 */
	@RequestMapping(value = { ViewPath.SITES_POLLS, ViewPath.SITES_SURVEY }, method = RequestMethod.POST)
	public String processSurvey(@PathVariable("site") String site, @ModelAttribute("surveyCommand") SurveyCommand surveyCommand, @PathVariable("landing") String landing, BindingResult result,
			HttpServletRequest request, ModelMap model) {

		logger.enter("processSurvey");
		super.handleDepartmentRequest(request, model, site);
		String languageCode = request.getParameter("languageCode");
		surveyCommand.setLanguageCode(languageCode);
		String view = "polls.surveys";
		String pageName = "";
		String source = (String) model.get("source");
		if (source.contains("/")) {
			pageName = source.split("/")[1];
			pageName = pageName.split("\\.")[0];
		}
		String sessionId = request.getSession().getId();
		surveyCommand.setSessionId(sessionId);
		surveyCommand.setSite(site);
		String errorMessage = "";
		boolean operationResult = false;
		List<FormVO> pollsResults = new ArrayList<FormVO>();

		NavigationVO navigationVO = new NavigationVO();
		navigationVO.setSite(site);
		navigationVO.setLanguage(request.getParameter("languageCode"));
		navigationVO.setName(UrlTransliterationUtil.getString(pageName));
		navigationVO.setTicketId(multiticket);

		try {

			surveyCommand.getQuestionAnswers().remove(0);

			surveyService.execute(surveyCommand);

			if (pageName.contains("polls")) {
				NavigationVO navigation = pageReferneceDAO.findByPrimaryKey(navigationVO);
				navigationVO.setAssetId(navigation.getAssetId());
				pollsResults = surveyService.getPollsResults(navigationVO);
			}

			operationResult = true;

		} catch (DAOException daoExecption) {
			logger.error(daoExecption.getSystemFaultCode().toString());
			if (daoExecption.getSystemFaultCode().toString().equals("duplicate.session")) {
				// result.rejectValue("subject",
				// daoExecption.getSystemFaultCode().toString(),
				// "You have already participated!");
				errorMessage = messageSource.getMessage("duplicate.session", null, new Locale(languageCode));
				model.addAttribute("formAssetId", surveyCommand.getFormAssetId()); // to
																					// find
																					// already
																					// participated
																					// poll
																					// in
																					// jsp
				model.addAttribute("errorMessage", errorMessage);
				NavigationVO navigation;
				try {
					navigation = pageReferneceDAO.findByPrimaryKey(navigationVO);

					navigationVO.setAssetId(navigation.getAssetId());

					if (pageName.contains("polls")) {
						surveyCommand = surveyService.getPolls(navigationVO);
					} else if (pageName.contains("surveys")) {
						surveyCommand = surveyService.getSurvey(navigationVO);
					}
				} catch (UAQException e) {
					logger.error("Error + " + e.getMessage());
				}
				model.addAttribute("surveyCommand", surveyCommand);
				
				model.addAttribute("landing", landing);
			}
		} catch (UAQException e) {
			logger.error(e.getMessage());
		}

		if (operationResult && pageName.contains("polls")) {
			view = "polls.results";
			model.addAttribute("pollsResults", pollsResults);
		} else if (operationResult && pageName.contains("surveys")) {
			view = "survey.confirmation";
		}
		model.addAttribute("pageName", pageName);
		logger.exit("processSurvey");

		return view;
	}

}
