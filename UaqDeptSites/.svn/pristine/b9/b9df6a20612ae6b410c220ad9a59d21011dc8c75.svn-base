<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<div class="page-content-wrapper feedback-form">
	<div class="row">
		<div class="col-lg-12 col-sm-12 col-md-12 col-xs-12">
			<!-- BreadCrumbs Start -->
			<div class="breadcrumbs hide-on-mobile">
				<spring:message code="label.pageLocation" />
				<a href="/${param.languageCode}/home.html"><spring:message
						code="header.home" /></a> <span><i
					class="glyphicon glyphicon-menu-right"></i></span>
				<spring:message code="feedback.page.keywords" />
			</div>
			<!-- BreadCrumbs End -->

			<!-- Page Content Start -->
			<div class="content-wrapper">
			
				<div class="page-content-wrap">
					<div class="form-content cf">
						<form:form method="POST" commandName="feedbackCommand"
							action="/${param.languageCode}/feedback.html" id="feedbak">

							<div class="row">
								<div class="col-md-12">
									<div class="form-group cf">
										<label class="mandatory_lbl pull-right"><spring:message
												code="feedback.form.mandatory" /></label>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-md-12 remove-pad">
									<div class="col-md-6 remove-pad">
										<!-- text box -->
										<div class="form-group cf">
											<div class="col-md-5">											
												<label for="first_name" class="form-lbl mandatory_lbl"><spring:message
														code="feedback.form.field.firstName" /></label>
											</div>

											<c:set var="firstNameVal">
												<spring:message
													code="feedback.form.field.firstName.required" />
											</c:set>

											<div class="col-md-7">
												<form:input path="firstName"
													cssClass="form-control required" id="first_name"
													type="text" data-msg-required="${firstNameVal}" />
											</div>
										</div>
										<!-- /text box -->
									</div>
									<div class="col-md-6 remove-pad">
										<!-- text box -->
										<div class="form-group cf">
											<div class="col-md-5">
												<label for="last_name" class="form-lbl mandatory_lbl"><spring:message
														code="feedback.form.field.lastName" /></label>
											</div>

											<c:set var="lastNameVal">
												<spring:message code="feedback.form.field.lastName.required" />
											</c:set>

											<div class="col-md-7">
												<form:input path="lastName" id="last_name"
													cssClass="form-control required" type="text"
													data-msg-required="${lastNameVal}" />
											</div>
										</div>
										<!-- /text box -->
									</div>
								</div>
							</div>

							<div class="row">
								<div class="col-md-12 remove-pad">
									<div class="col-md-6 remove-pad">
										<!-- text box -->
										<div class="form-group cf">
											<div class="col-md-5">
												<label for="email" class="form-lbl mandatory_lbl"><spring:message
														code="feedback.form.field.email" /> </label>

												<c:set var="emailVal"><spring:message code="feedback.form.field.email.required" /></c:set>
												<c:set var="emailInvalid"><spring:message code='feedback.form.field.email.invalid'/></c:set>
											</div>
											<div class="col-md-7">
												<form:input path="email" type="email" id="email"
													cssClass="form-control required"
													data-msg-required="${emailVal}" data-msg-email="${emailInvalid}"/>
											</div>
										</div>
										<!-- /text box -->
									</div>
									<div class="col-md-6 remove-pad">
										<!-- text box -->
										<div class="form-group cf">
											<div class="col-md-5">
												<label for="mobile_number" class="form-lbl mandatory_lbl"><spring:message
														code="feedback.form.field.phone.number" /> </label>
											</div>

											<c:set var="mobileVal">
												<spring:message
													code="feedback.form.field.telephone.required" />
											</c:set>

											<div class="col-md-7">
												<c:set var="telephoneRequired"><spring:message code='feedback.form.field.required.telephone'/></c:set>
												<c:set var="lengthMessage"><spring:message code='feedback.enter.ten.digit'/></c:set>
												<form:input path="telephone" type="tel" id="mobile_number" 
												cssClass="form-control required" data-msg-required="${telephoneRequired}"
													  data-msg-maxlength="${lengthMessage}" 
													  data-msg-minlength="${lengthMessage}" 
													  data-rule-maxlength="10" 
													  data-rule-minlength="10"
													  data-rule-number="true"
													  data-msg-number="${lengthMessage}" maxlength="10"/>
											</div>
										</div>
										<!-- /text box -->
									</div>
								</div>
							</div>

							<div class="row">
								<div class="col-md-12 remove-pad">
									<div class="col-md-6 remove-pad">
										<!-- text box -->
										<div class="form-group cf">
											<div class="col-md-5">
												<label for="message" class="form-lbl mandatory_lbl"><spring:message
														code="feeedback.form.message" /> </label>
											</div>
											<div class="col-md-7">
												<c:set var="messageRequired"><spring:message code='feedback.form.field.required.message'/></c:set>
												<form:textarea path="message" class="form-control required"
													id="message" data-msg-required="${messageRequired}" />
											</div>
										</div>
										<!-- /text box -->
									</div>
									<div class="col-md-6 remove-pad">
										<!-- text box -->
										<div class="form-group cf">
											<div class="col-md-5">
												<label for="country" class="form-lbl mandatory_lbl"><spring:message
														code="feedback.form.field.country" /> </label>
											</div>
											<div class="col-md-7">
												<div class="custom-select-box">
												<c:set var="countryRequired"><spring:message code='feedback.form.field.required.country'/></c:set>
													<form:select path="country" id="country" data-msg-required="${countryRequired}" 
													class="required">
														<option value="">
															<spring:message code="option.select" />
														</option>
														<form:options items="${countriesList}" />
													</form:select>
												</div>
											</div>
										</div>
										<div class="form-group cf">
											<div class="col-md-5">
												<label for="verification" class="form-lbl mandatory_lbl"><spring:message
														code="captcha.verification.code" /> </label>

											</div>

											<div class="col-md-7">
												<img src="captcha.html" alt="Captcha" class="captcha" />
												<c:set var="captchaRequired"><spring:message code='feedback.form.field.required.captcha'/></c:set>
												<form:input path="captchaText" name="verification"
													id="verification" cssclass="form-control" class="required"
													type="text" data-msg-required="${captchaRequired}" />
												<p>
													<label class="error"><form:errors
															path="captchaText" /></label>
												</p>
											</div>


										</div>
										<!-- /text box -->
									</div>
								</div>
							</div>

							<div class="row">
								<div class="col-md-12 remove-pad">
									<div class="col-md-6 remove-pad">
										<!-- submit button -->
										<div class="form-group submission">
											<div class="col-md-offset-5 col-md-7">
												<input type="submit" class="btn"
													value="<spring:message code='form.button.submit'/>" />
											</div>
										</div>
										<!-- /submit button -->
									</div>
								</div>
							</div>
						</form:form>
					</div>


				</div>
			</div>
			<!-- Page Content End -->
		</div>

	</div>
</div>
<script src="/js/lib/jquery.validate.js" type="text/javascript"></script>
<script type="text/javascript">

jQuery(function($) {
	$("#feedbak").validate();

	$.fn.allchange = function (callback) {
	var me = this;
	var last = "";
	var infunc = function () {
	var text = $(me).val();
	if (text != last) {
	last = text;
	callback();
	}
	setTimeout(infunc, 100);
	}
	setTimeout(infunc, 100);
	};
	$("#email").allchange(function(){
	$('#email').valid();
	});

	});
</script>