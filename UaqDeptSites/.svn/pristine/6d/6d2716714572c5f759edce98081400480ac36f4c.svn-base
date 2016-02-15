package com.uaq.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.octo.captcha.service.CaptchaServiceException;
import com.octo.captcha.service.multitype.GenericManageableCaptchaService;
import com.uaq.command.FeedbackCommand;
import com.uaq.common.PropertiesUtil;
import com.uaq.common.ViewPath;
import com.uaq.exception.DAOException;
import com.uaq.exception.UAQException;
import com.uaq.logger.UAQLogger;
import com.uaq.service.FeedbackService;
import com.uaq.util.EmailUtil;
import com.uaq.util.MetaDataUtil;

/**
 * This controller is used for feedback page.
 * 
 * @author mraheem
 * 
 */
@Controller
public class FeedbackController extends BaseController {

	private static transient UAQLogger logger = new UAQLogger(FeedbackController.class);

	@Autowired
	@Qualifier("feedbackValidator")
	private Validator validator;

	@Autowired
	@Qualifier("feedbackService")
	private FeedbackService service;

	@Autowired
	private GenericManageableCaptchaService captchaService;

	@Autowired
	private MessageSource messageSource;

	/**
	 * Initializes the feedback form
	 * 
	 * @param model
	 *            implicit object.
	 * @return view name.
	 */
	@RequestMapping(value = ViewPath.FEEDBACK, method = RequestMethod.GET)
	public String handleRequest(@PathVariable("site") String site, HttpServletRequest request, ModelMap model) {

		logger.enter("handle Request");

		String view = "";
		if (site.equals("uaq")) {
			view = "feedback";
			super.handleRequest(request, model);
		} else {
			view = "sites.feedback";
			super.handleDepartmentRequest(request, model, site);
		}
		String languageCode = request.getParameter("languageCode");		

		FeedbackCommand feedbackCommand = new FeedbackCommand();
		
		model.addAttribute("pageMetadata", MetaDataUtil.getPageMetaData("feedback", languageCode, messageSource));
		model.addAttribute("feedbackCommand", feedbackCommand);
		model.addAttribute("countriesList", getCountryList(languageCode));

		return view;
	}

	private List<String> getCountryList(String languageCode) {
		List<String> countriesList = new ArrayList<String>();

		String countries = messageSource.getMessage("profile.countries", null, new Locale(languageCode));
		String countriesArray[] = countries.split(",");

		for (String country : countriesArray) {
			countriesList.add(country);
		}

		return countriesList;
	}

	/**
	 * Processes the feedback form submitted by the user.
	 * 
	 * @param feedbackCommand
	 *            object for holding the feedback form data.
	 * @param result
	 *            object holding errors.
	 * @param request
	 *            implicit http request object.
	 * @return view name.
	 */
	@RequestMapping(value = ViewPath.FEEDBACK, method = RequestMethod.POST)
	public String processForm(@PathVariable("site") String site, @ModelAttribute("feedbackCommand") FeedbackCommand feedbackCommand, BindingResult result, HttpServletRequest request, ModelMap model) {

		logger.debug("Inside Feedback Controller | Entry");

		String view = "";
		if (site.equals("uaq")) {
			view = "feedback";
			super.handleRequest(request, model);
		} else {
			view = "sites.feedback";
			super.handleDepartmentRequest(request, model, site);
		}
		String languageCode = request.getParameter("languageCode");
		String sessionId = request.getSession().getId();

		validator.validate(feedbackCommand, result);
		Boolean isValidResult = validateCaptcha(request.getSession().getId(), feedbackCommand.getCaptchaText(), result);

		if (!result.hasErrors() && isValidResult) {

			try {
				feedbackCommand.setSessionId(sessionId);
				feedbackCommand.setSite(site);

				service.execute(feedbackCommand);

				EmailUtil.sendEmail(messageSource.getMessage("feedback.title", null, new Locale(languageCode)), getMessageText(feedbackCommand), site);

				view = "feedbackConfirmation";
				if (site.equals("uaq")) {
					view = "feedbackConfirmation";
				} else {
					view = "sites.feedback.confirmation";
				}
			} catch (DAOException daoExecption) {
				logger.error(daoExecption.getSystemFaultCode().toString());
				if (daoExecption.getSystemFaultCode().toString().equals("duplicate.session")) {
					result.rejectValue("message", daoExecption.getSystemFaultCode().toString(), messageSource.getMessage("duplicate.session", null, new Locale(languageCode)));
				}
			} catch (UAQException e) {
				logger.error(e.getMessage());
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
		}

		String pageName = "feedback";
		model.addAttribute("pageMetadata", MetaDataUtil.getPageMetaData(pageName, languageCode, messageSource));
		model.addAttribute("feedbackCommand", feedbackCommand);
		model.addAttribute("countriesList", getCountryList(languageCode));

		return view;
	}

	/**
	 * Validates the captcha text entered by the user.
	 * 
	 * @param sessionId
	 *            of the user.
	 * @param captchaText
	 *            entered by the user.
	 * @param errors
	 *            error object for the form.
	 * @return true if valid or false if incorrect.
	 */
	private boolean validateCaptcha(String sessionId, String captchaText, Errors errors) {

		boolean isResponseCorrect = false;

		try {
			isResponseCorrect = captchaService.validateResponseForID(sessionId, captchaText);
			if (!isResponseCorrect) {
				errors.rejectValue("captchaText", "captchaText.incorrect", "Invalid Captcha Text");
			}
		} catch (CaptchaServiceException e) {
			logger.error("Invalid Session Id");
		}

		return isResponseCorrect;
	}

	/**
	 * Gets the message text to be sent as an email.
	 * 
	 * @param feedbackCommand
	 *            the object holding the data.
	 * @return the constructed message.
	 */
	private String getMessageText(FeedbackCommand feedbackCommand) {

		StringBuffer messageText = new StringBuffer();
		//messageText.append("\n" + "\n");
		messageText.append("\n Dear Admin, \n\n");
		messageText.append("\n Please find the below feedback we have recieved from \n" + feedbackCommand.getEmail() + " of the website "+PropertiesUtil.getProperty(feedbackCommand.getSite() + "_csSiteName") + "\n");
		//messageText.append("Below is the feedback received from a user of the website "+PropertiesUtil.getProperty(feedbackCommand.getSite() + "_csSiteName") + "\n");
		messageText.append("\n" + "First Name : " + feedbackCommand.getFirstName() + "\n");
		messageText.append("\n" + "Last Name : " + feedbackCommand.getLastName() + "\n");
		messageText.append("\n" + "Email : " + feedbackCommand.getEmail() + "\n");
		messageText.append("\n" + "Telephone Number : " + feedbackCommand.getTelephone() + "\n");
		messageText.append("\n" + "Country : " + feedbackCommand.getCountry() + "\n");
		messageText.append("\n" + "Message : " + feedbackCommand.getMessage() + "\n");
		messageText.append("\n\n" + "Sent By, \n UAQ Admin \n");
		return messageText.toString();
	}
}