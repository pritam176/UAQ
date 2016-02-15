package com.uaq.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.uaq.command.FeedbackCommand;
import com.uaq.util.ValidationUtil;

/**
 * Validator class for Feedback Form.
 * 
 * @author mraheem
 * 
 */
@Component("feedbackValidator")
public class FeedbackValidator implements Validator {

	@Autowired
	private EmailValidator emailValidator;

	@Override
	public boolean supports(@SuppressWarnings("rawtypes") Class clazz) {
		return FeedbackCommand.class.isAssignableFrom(clazz);

	}

	@Override
	public void validate(Object target, Errors errors) {

		FeedbackCommand feedbackCommand = (FeedbackCommand) target;

		String firstName = feedbackCommand.getFirstName();
		String lastName = feedbackCommand.getLastName();
		String email = feedbackCommand.getEmail();
		String telephone = feedbackCommand.getTelephone();
		String country = feedbackCommand.getCountry();

		if (ValidationUtil.isEmpty(firstName)) {
			errors.rejectValue("firstName", "feedback.form.field.firstName.required", "Field name is required.");
		} else if (!ValidationUtil.validateName(firstName)) {
			errors.rejectValue("firstName", "feedback.form.field.firstName.invalid", "Invalid name");
		}

		if (ValidationUtil.isEmpty(lastName)) {
			errors.rejectValue("lastName", "feedback.form.field.lastName.required", "Field name is required.");
		} else if (!ValidationUtil.validateName(lastName)) {
			errors.rejectValue("lastName", "feedback.form.field.lastName.invalid", "Invalid name");
		}

		if (ValidationUtil.isEmpty(email)) {
			errors.rejectValue("email", "feedback.form.field.email.required", "Field name is required.");
		} else if (!getEmailValidator().validate(feedbackCommand.getEmail())) {
			errors.rejectValue("email", "feedback.form.field.email.invalid", "Invalid Email Id.");
		}

		if (!ValidationUtil.isEmpty(telephone) && !ValidationUtil.isValidMobileNumer(telephone)) {
			errors.rejectValue("telephone", "feedback.form.field.telephone.invalid", "Invalid telephone number");
		}

		if (ValidationUtil.isEmpty(country)) {
			errors.rejectValue("country", "feedback.form.field.country.required", "Field name is required.");
		} else if (!ValidationUtil.validateName(country)) {
			errors.rejectValue("country", "feedback.form.field.country.invalid", "Invalid name");
		}

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "message", "feedback.form.field.message.required", "Field name is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "captchaText", "required.captchaText", "Field name is required.");
	}

	/**
	 * @return the emailValidator
	 */
	public EmailValidator getEmailValidator() {
		return emailValidator;
	}

	/**
	 * @param emailValidator
	 *            the emailValidator to set
	 */
	public void setEmailValidator(EmailValidator emailValidator) {
		this.emailValidator = emailValidator;
	}

}