<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!--mainmenu -->
<div class="mainmenu">
	<div class="col-md-12 hidden-xs hidden-sm">
		<div class="mainmenu-wrap">
			<ul class="no-list cf">
				<li><a href="/${param.languageCode}/home.html"><img
						src="/img/home1.png" alt="Home UAQ"> </a></li>
				<c:if
					test="${not empty parentLandingPage.url and not empty parentLandingPage.title and not empty parentLandingPage.navigations}">
					<li class="active"><a href="/${param.languageCode}/${parentLandingPage.url}">${parentLandingPage.title}</a></li>

					<c:forEach items="${parentLandingPage.navigations}" var="item">
						<c:choose>
							<c:when test="${source==item.url}">
								<li class="active-sub"><a
									href="/${param.languageCode}/${item.url}">${item.title}</a></li>
							</c:when>
							<c:otherwise>
								<li><a href="/${param.languageCode}/${item.url}">${item.title}</a></li>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</c:if>

				<c:if test="${empty parentLandingPage}">
					<li class="in-active"><spring:message code="footer.feedback" /></li>
				</c:if>
			</ul>
		</div>
	</div>
</div>
<!--/mainmenu -->
<!-- content area -->
<div class="content">
	<div class="row">
		<!-- right col -->
		<div class="col-xs-12 col-sm-3 col-md-3 main-right-col">
			<!-- social media share callout -->
			<div class="callout-wrap">
				<div class="callout-content">
					<div class="social-share-wrap">
						<ul>
							<li><a href="/${param.languageCode}/rssFeeds.html"><img src="/img/icons/icon-rss.png"
									alt="uaq"></a></li>
							<li class="share-fb"><a target="_blank" alt="share it"
								href="http://www.facebook.com/sharer/sharer.php?s=100&amp;p[url]=http://dev.uaq.ae/en&amp;p[images][0]=http://logicum.co/wp-content/uploads/2013/01/sharetweetbuttons.jpg&amp;p[title]=Creating Custom share buttons: Facebook, Twitter, Google+&amp;p[summary]=Build your custom share buttons from normal images with examples on each button">
									<img src="/img/icons/icon-fb.png" alt="uaq">
							</a></li>
							<li class="share-twitter"><a target="_blank"
								href="http://www.twitter.com/share?url=http://www.google.com/"><img
									src="/img/icons/icon-twitter.png" alt="tweet"></a></li>
							<li class="share-print"><a href="#"><img
									src="/img/icons/icon-print.png" alt="uaq"></a></li>
							<c:set var="subjectValue"><spring:message code='feedback.title'/></c:set>
							<li class="share-email"><a
								href="mailto:?&amp;Subject=${subjectValue}&amp;Body=${sourceURL}">
									<img src="/img/icons/icon-email.png" alt="uaq">
							</a></li>
							<li class="share-fontsize">
								<div class="dropdown">
									<button class="btn btn-default dropdown-toggle" type="button"
										data-toggle="dropdown">
										<span class="sele"> A </span> <span class="caret"></span>
									</button>
									<ul class="dropdown-menu">
										<li><a href="javascript:void(0);">A</a></li>
										<li><a href="javascript:void(0);">A+</a></li>
										<li><a href="javascript:void(0);">A++</a></li>
									</ul>
								</div>
							</li>
						</ul>
					</div>
				</div>
			</div>
			<!-- /social media share callout -->
		</div>
		<!-- right col -->
		<!-- left col -->
		<div class="col-xs-12 col-sm-9 col-md-9 main-left-col">
			<!-- page title -->
			<div class="page-title-wrap">
				<div class="row">
					<div class="col-xs-12 col-sm-9 col-md-10 title-txt">
						<!-- sub page title 
										<h4 class="page-title-sub">
										MUNICIPALITY
										</h4>-->
						<!-- /sub page title -->
						<!-- page title -->
						<h2 class="page-title">
							<spring:message code="footer.feedback" />
						</h2>
						<!-- /page title -->
					</div>
					<div class="hidden-xs show-sm col-md-2 title-icon">
						<img src="/img/banner/Feedback.png" alt="uaq">
					</div>
				</div>
			</div>
			<!-- /page title -->
			<div class="main-content-wrap">

				<div class="page-content-wrap">
					<div class="form-content cf">
						<form:form method="POST" commandName="feedbackCommand"
							action="/${param.languageCode}/feedback.html" id="feedbak">
							<div class="row">
								<div class="row">
									<form:errors path="message" />
									<c:set var="feedbackMandatory"><spring:message code="feedback.form.mandatory" /></c:set>
									<div class="col-md-12 remove-pad">
										<div class="form-group cf">
											<label class="mandatory_lbl pull-right">${feedbackMandatory}</label>
										</div>
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
											<div class="col-md-7">
											<c:set var="firstNameMandatory"><spring:message code='feedback.form.field.required.firstName'/></c:set>
												<form:input path="firstName"
													cssClass="form-control required" id="first_name" 
													data-msg-required="${firstNameMandatory}"
													type="text" />
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
											<div class="col-md-7">
											<c:set var="lastNameMandatory"><spring:message code='feedback.form.field.required.lastName'/></c:set>
												<form:input path="lastName" id="last_name" 
												data-msg-required="${lastNameMandatory}"
													cssClass="form-control required" type="text" />
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
											</div>
											<div class="col-md-7">
											<c:set var="emailMandatory"><spring:message code='feedback.form.field.required.email'/></c:set>
											<c:set var="emailInvalid"><spring:message code='feedback.form.field.email.invalid'/></c:set>
												<form:input path="email" type="email" id="email" 
												data-msg-required="${emailMandatory}"
													cssClass="form-control required" data-msg-email="${emailInvalid}" />
											</div>
										</div>
										<!-- /text box -->
									</div>
									<div class="col-md-6 remove-pad">
										<!-- text box -->
										<div class="form-group cf">
											<div class="col-md-5">
												<label for="telephone" class="form-lbl mandatory_lbl"><spring:message code="feedback.form.field.phone.number" /></label>
											</div>
											<div class="col-md-7">
											<c:set var="telephoneRequired"><spring:message code='feedback.form.field.required.telephone'/></c:set>
											<c:set var="telephoneInvalid"><spring:message code='feedback.form.field.telephone.invalid'/></c:set>
											<c:set var="lengthMessage"><spring:message code='feedback.enter.ten.digit'/></c:set>
												<form:input path="telephone" type="text" placeholder="##########"
												data-msg-required="${telephoneRequired}"
													id="mobile_number" name="telephone"  cssClass="form-control required" class="form-control required"  
													  data-msg-maxlength="${lengthMessage}" 
													  data-msg-minlength="${lengthMessage}" 
													  data-rule-maxlength="10" 
													  data-rule-minlength="10"
													  data-rule-number="true"
													  data-msg-number="${lengthMessage}"/>
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
												<form:textarea path="message" 
												data-msg-required="${messageRequired}"
													class="form-control required feedbackmsg " id="message" />
											</div>
										</div>
										<!-- /text box -->
									</div>
									<div class="col-md-6 remove-pad">
										<!-- text box -->
										<div class="form-group cf">
											<div class="col-md-5">
												<label for="country" class="form-lbl mandatory_lbl"><spring:message
														code="feedback.form.field.country"/> </label>
											</div>
											<div class="col-md-7">
												<div class="custom-select-box">
												<c:set var="countryRequired"><spring:message code='feedback.form.field.required.country'/></c:set>
													<form:select path="country" id="country" name="country" 
													data-msg-required="${countryRequired}"
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
												<div class="col-md-4 col-sm-4 col-xs-4 remove-pad">
													<img src="captcha.html" alt="Captcha captchaimg"
														class="captcha captchaimg" />
												</div>
												<div class="col-md-8 col-sm-8 col-xs-8"
													style="padding-right: 0px;">
													<c:set var="captchaRequired"><spring:message code='feedback.form.field.required.captcha'/></c:set>
													<form:input path="captchaText" name="verification"
														id="verification" cssclass="form-control" 
														data-msg-required="${captchaRequired}"
														class="required form-control" type="text" 	/>
													<p>
														<label class="error"><form:errors
																path="captchaText" /></label>
													</p>
												</div>
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
										<div class="row">
											<div class="form-group submission">
												<div class="col-md-offset-5 col-md-7">
													<input type="submit" class="btn"
														value="<spring:message code='form.button.submit'/>" />
												</div>
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
			<!-- /left col -->
		</div>
	</div>
</div>
<!-- /content area -->
	<!-- script -->
		<script src="/js/libs/jquery.validate.js"></script>
	    <script>
	  
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
		<!-- /script -->