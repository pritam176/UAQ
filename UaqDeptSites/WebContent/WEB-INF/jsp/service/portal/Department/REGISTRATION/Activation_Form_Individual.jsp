<%-- <%@page import="net.tanesha.recaptcha.ReCaptchaFactory"%>
<%@page import="net.tanesha.recaptcha.ReCaptcha"%> --%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<c:set var="userName">
	<spring:message code='plzUserName' />
</c:set>
<c:set var="password">
	<spring:message code='plzPassword' />
</c:set>
<c:set var="passwordMax">
	<spring:message code='passMax' />
</c:set>
<c:set var="passwordMin">
	<spring:message code='passMin' />
</c:set>
<c:set var="passwordMisMatchmsg">
	<spring:message code='passwordMisMatch' />
</c:set>
<c:set var ="postboxmsg"><spring:message code="label.egd.postBox.required"/></c:set>
<c:set var ="enterDigit"><spring:message code="feedback.enter.ten.digit"/></c:set>
<c:set var ="validemailMsg"><spring:message code="validemail"/></c:set>
<c:set var ="emailMsg"><spring:message code="plzEmailAddress"/></c:set>
<c:set var ="acceptMessgaelabel"><spring:message code="acceptMessgae"/></c:set>
<c:set var ="usernamemax"><spring:message code="username.max"/></c:set>
					<c:set var ="usernamemin"><spring:message code="username.min"/></c:set>
					
					<c:set var ="alphmsg"><spring:message code="alph.msg"/></c:set>
					<c:set var ="passwordformatmsg"><spring:message code="password.format"/></c:set>

<div class="container-fluid">
	<div class="wrapper">
		<!-- header -->

		<!-- /header -->
		<!--mainmenu -->
		<div class="mainmenu">
			<div class="col-md-12 hidden-xs hidden-sm">
				<div class="mainmenu-wrap">
					<ul class="no-list cf">
						<li><a href="/${param.languageCode}/home.html"><img
								src="/img/home1.png" alt="Home UAQ"> </a></li>
						<li class=""><a
							href="/${param.languageCode}/registrationlanding.html"><spring:message
									code="registration" /></a></li>
						<li class="active"><a href="#"><spring:message
									code="activateAcounttxt" /></a></li>

					</ul>
				</div>
			</div>
		</div>
		<!--/mainmenu -->
		<!-- content area -->
		<div class="content">
			<div class="row">

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
									<spring:message code="activateAcounttxt" />
								</h2>
								<!-- /page title -->
							</div>
							<div class="hidden-xs show-sm col-md-2 title-icon">
								<img src="/img/icons/icon-uaq.png" alt="uaq">
							</div>
						</div>
					</div>
					<!-- /page title -->
					<div class="main-content-wrap">

						<div class="page-content-wrap">
							<div class="form-content cf" style="margin-bottom: 280px;">
								<form:form action="/${param.languageCode}/activateform.html"
									class="eservice-form" commandName="activateFormCommand"
									method="post" id="activateAccount">
									<div class="row">
										<div class="row">
											<div class="col-md-12 remove-pad">
												<div class="form-group cf">
													<label class="mandatory_lbl pull-right"><spring:message
															code="allfieldmandatorylbl" /></label>
												</div>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-md-12">
											<!-- form-sect-head-->
											<h5 class="form-title">
												<spring:message code="accountDetailsSplit" />
											</h5>
										</div>
										<!-- /form-sect-head-->
									</div>
									<div class="row">
										<div class="col-md-12 remove-pad">
											<div class="col-md-6 remove-pad">
												<!-- text box -->
												<div class="form-group cf">
													<div class="col-md-5">
														<label for="fullname" class="form-lbl mandatory_lbl"><spring:message
																code="fullName" /> </label>
													</div>
													<div class="col-md-7 ">
														<form:input path="fullName" type="text" name="fullName"
															id="fullName" class="form-control" readonly="readonly" />
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
										
										
				<!-- Individual -->
												<c:if test="${typeOfuser ==1}">
													<div class="form-group cf">
														<div class="col-md-5">
															<label for="countryofcitizen"
																class="form-lbl mandatory_lbl"><spring:message
																	code="countryCitizen" /></label>
														</div>
														<div class="col-md-7">
															<div class="custom-select-box disabled">
																<form:select path="citizen" id="citizen">
																	<form:option value="1">
																		<spring:message code="UAE" />
																	</form:option>
																	<form:options items="${nationalList}" />
																</form:select>
															</div>
														</div>
													</div>
												</c:if>
				<!-- Establishment -->
												<c:if test="${typeOfuser ==2}">
													<div class="form-group cf">
														<div class="col-md-5">
															<label for="countryofcitizen"
																class="form-lbl mandatory_lbl"><spring:message
																	code="trade.licence.number" /></label>
														</div>
														<div class="col-md-7">
															<div class="disabled">
																<form:input path="treadLicence" id="treadLicence"
																	name="treadLicence" type="text" class="form-control"
																	readonly="readonly" required="required" />
															</div>
														</div>
													</div>
												</c:if>

												<!-- /text box -->
											</div>








											<div class="col-md-6 remove-pad">
												<!-- text box -->
												<c:if test="${typeOfuser ==2}">
													<div class="form-group cf">
														<div class="col-md-5">
															<label for="first_name" class="form-lbl "><spring:message
																	code="tradeLicenExpiryDate" /></label>
														</div>
														<div class="col-md-7">
															<div class="disabled">
																<form:input path="treadLicenceExprDate"
																	id="treadLicenceExprDate" name="treadLicenceExprDate"
																	type="text" class="form-control" readonly="readonly" />
															</div>
														</div>
													</div>
												</c:if>

												<c:if test="${typeOfuser ==1}">
													<div class="form-group cf">
														<div class="col-md-5">
															<label for="first_name" class="form-lbl mandatory_lbl"><spring:message
																	code="countryResidence" /></label>
														</div>
														<div class="col-md-7">
															<div class="custom-select-box disabled">
																<form:select path="residency" id="residency">
																	<form:option value="1">
																		<spring:message code="UAE" />
																	</form:option>
																	<form:options items="${nationalList}" />

																</form:select>
															</div>
														</div>
													</div>
												</c:if>
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
														<label for="mobile_number1" class="form-lbl mandatory_lbl"><spring:message
																code="mobileNum" /> </label>
													</div>
													<div class="col-md-7">
														<div class="col-md-4 col-xs-3 remove-pad">
															<div class="custom-select-box disabled">
																<form:select path="mobilecode1">
																	<option
																		<c:if test="${activateFormCommand.mobilecode1 == '050'}">selected="selected"</c:if>
																		value="050">050</option>
																	<option
																		<c:if test="${activateFormCommand.mobilecode1 == '052'}">selected="selected"</c:if>
																		value="052">052</option>
																	<option
																		<c:if test="${activateFormCommand.mobilecode1 == '055'}">selected="selected"</c:if>
																		value="055">055</option>
																	<option
																		<c:if test="${activateFormCommand.mobilecode1 == '056'}">selected="selected"</c:if>
																		value="056">056</option>
																</form:select>
															</div>
														</div>
														<div class="col-md-8 col-xs-9 remove-pad">

															<form:input path="mobile1" id="mobile_number1"
																name="mobile_number1" type="text" class="form-control"
																readonly="readonly" required="required" maxlength="7" />

														</div>
													</div>
												</div>

												<!-- /text box -->
											</div>
											
											<!-- Mobile NO 2 -->
											<c:if test="${typeOfuser ==1}">
												<div class="col-md-6 remove-pad">
													<!-- text box -->
													<div class="form-group cf">
														<div class="col-md-5">
															<label for="mobile_number2" class="form-lbl "><spring:message
																	code="mobileNum2" /> </label>
														</div>
														<div class="col-md-7">
															<div class="col-md-4 col-xs-3 remove-pad">
																<div class="custom-select-box disabled">
																	<form:select path="mobilecode2">
																		<option <c:if test="${activateFormCommand.mobilecode2 == '050'}">selected="selected"</c:if>  value="050">050</option>
																			<option  <c:if test="${activateFormCommand.mobilecode2 == '052'}">selected="selected"</c:if> value="052">052</option>
																			<option  <c:if test="${activateFormCommand.mobilecode2 == '055'}">selected="selected"</c:if> value="055">055</option>
																			<option  <c:if test="${activateFormCommand.mobilecode2 == '056'}">selected="selected"</c:if> value="056">056</option>
																	</form:select>
																</div>
															</div>
															<div class="col-md-8 col-xs-9 remove-pad">
																<form:input path="mobile2" id="mobile_number2"
																	name="mobile_number2" type="text" class="form-control"
																	readonly="readonly" maxlength="7" />


															</div>
														</div>
													</div>
													<!-- /text box -->
												</div>
											</c:if>
											
											<!-- Office Phone for Establishment-->
											<c:if test="${typeOfuser ==2}">
												<div class="col-md-6 remove-pad ">
													<!-- text box -->
													<div class="form-group cf">
														<div class="col-md-5">
															<label for="mobile_number2"
																class="form-lbl mandatory_lbl "><spring:message
																	code="officePhoneLabel" /> </label>
														</div>
														<div class="col-md-7">
															<div class="col-md-4 col-xs-3 remove-pad">
																<div class="custom-select-box cf">
																	<form:select path="LandLinecode" class="required1"
																		name="countryofcitizen">
																		<option <c:if test="${activateFormCommand.landLinecode == '02'}">	selected="selected"</c:if>  value="02">02</option>
																			<option  <c:if test="${activateFormCommand.landLinecode == '04'}">selected="selected"</c:if> value="04">04</option>
																			<option  <c:if test="${activateFormCommand.landLinecode == '06'}">selected="selected"</c:if> value="06">06</option>
																			<option  <c:if test="${activateFormCommand.landLinecode == '07'}">selected="selected"</c:if> value="07">07</option>
																			<option  <c:if test="${activateFormCommand.landLinecode == '09'}">selected="selected"</c:if> value="09">09</option>
																	</form:select>
																</div>
															</div>
															<div class="col-md-8 col-xs-9 remove-pad">
																<form:input path="landLine" id="mobile_number2"
																	name="mobile_number2" type="text"
																	class="form-control required" data-rule-maxlength="7"
																	data-rule-minlength="7"
																	data-msg-required="${enterOfficeNum}"
																	data-rule-number="true" data-msg-number="${valMobile}"
																	data-msg-maxlength="${valMobileLength}"
																	data-msg-minlength="${valMobileLength}" maxlength="7"/>
															</div>
														</div>
													</div>
													<!-- /text box -->
												</div>
											</c:if>
										</div>
									</div>

									<div class="row">
										<div class="col-md-12 remove-pad">
										<!-- Land Line -->
												<c:if test="${typeOfuser ==1}">
													<div class="col-md-6 remove-pad">
														<!-- text box -->
								
													<div class="form-group cf">
														<div class="col-md-5">
															<label for="landline" class="form-lbl"><spring:message
																	code="landlineNum" /> </label>
														</div>
														<div class="col-md-7">
															<div class="col-md-4 col-xs-3 remove-pad">
																<div class="custom-select-box disabled">
																	<form:select path="landLinecode" required="required">
																		<option <c:if test="${activateFormCommand.landLinecode == '02'}">	selected="selected"</c:if>  value="02">02</option>
																			<option  <c:if test="${activateFormCommand.landLinecode == '04'}">selected="selected"</c:if> value="04">04</option>
																			<option  <c:if test="${activateFormCommand.landLinecode == '06'}">selected="selected"</c:if> value="06">06</option>
																			<option  <c:if test="${activateFormCommand.landLinecode == '07'}">selected="selected"</c:if> value="07">07</option>
																			<option  <c:if test="${activateFormCommand.landLinecode == '09'}">selected="selected"</c:if> value="09">09</option>
																	</form:select>
																</div>
															</div>
															<div class="col-md-8 col-xs-9 remove-pad">
																<form:input path="landLine" id="landline"
																	name="landline" type="text" class="form-control"
																	readonly="readonly" />
															</div>
														</div>
													</div>
												
												<!-- /text box -->
											</div>
											</c:if>
											<div class="col-md-6 remove-pad">
												<!-- text box -->
												<div class="form-group cf">
													<div class="col-md-5">
														<label for="email" class="form-lbl mandatory_lbl"><spring:message
																code="email" /> </label>
													</div>
													<div class="col-md-7">
														<form:input path="email" type="email" id="email"
															name="email" class="form-control" readonly="readonly"
															required="required" data-msg-email="${validemailMsg}" data-msg-required="${emailMsg}"/>
													</div>
												</div>

												<!-- /text box -->
											</div>
										</div>
									</div>


									<!-- Establish ment Addres emirate post box website-->
									<c:if test="${typeOfuser ==2}">
										<div class="row">
											<div class="col-md-12 remove-pad">
												<div class="col-md-6 remove-pad">
													<!-- text box -->
													<div class="form-group cf">
														<div class="col-md-5">
															<label for="address" class="form-lbl mandatory_lbl">
																<spring:message code="label.address" />
															</label>
														</div>
														<div class="col-md-7">
															<form:input path="address" id="address" name="address"
																type="text" class="form-control " required="required"
																data-msg-required="${addressReq}" maxlength="100"
																 />
														</div>
													</div>
													<!-- /text box -->
												</div>
												<div class="col-md-6 remove-pad ">
													<!-- text box -->
													<div class="form-group cf">
														<div class="col-md-5">
															<label for="website" class="form-lbl "><spring:message
																	code="label.website" /> </label>
														</div>
														<div class="col-md-7">
															<form:input path="website" type="url" id="website"
																name="website" url="true" class="form-control"
																data-msg-url="${enterWebsite}" />
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
															<label for="emirates" class="form-lbl mandatory_lbl"><spring:message
																	code="chooseEmirates" /> </label>
														</div>
														<div class="col-md-7">
															<div class="custom-select-box cf">
																<form:select path="emirates" class="required1"
																	name="countryofcitizen" required="required"
																	data-msg-required="${emiratesReq}">
																	<form:option value=""><spring:message code="selectEmirates"/></form:option>
																	<form:options items="${emirateList}" />
																</form:select>
															</div>
														</div>
													</div>
													<!-- /text box -->
												</div>
												<!-- postbox number added -->
												<div class="col-md-6 remove-pad ">
													<!-- text box -->
													<div class="form-group cf">
														<div class="col-md-5">
															<label for="post_box" class="form-lbl mandatory_lbl"><spring:message code="label.egd.postBox" /></label>
														</div>
														<div class="col-md-7">
															<form:input path="postbox" id="post_box" name="postbox"
																class="form-control required" maxlength="10"
																data-rule-number="true"
																data-msg-number="${enterDigit}"
																data-msg-required="${postboxmsg}" />
														</div>
													</div>
												</div>
												<!-- /postbox number added -->
											</div>
										</div>
									</c:if>
									<!-- Establishment -->
									<div class="row">
										<div class="col-md-12">
											<!-- form-sect-head-->
											<h5 class="form-title">
												<spring:message code="credentialsSplit" />
											</h5>
										</div>
										<!-- /form-sect-head-->
									</div>
									<div class="row">
										<div class="col-md-12 remove-pad">
											<div class="col-md-6 remove-pad">
												<!-- text box -->
												<div class="form-group cf">
													<div class="col-md-5">
														<label for="username" class="form-lbl mandatory_lbl"><spring:message
																code="username" /> </label>
													</div>
													<div class="col-md-7">
														<form:input path="userName" name="username" type="text"
															class="form-control " required="required"
															data-msg-required="${userName}"
															 data-rule-alphacheck="true" data-msg-alphacheck="${alphmsg}"
			                                                    data-rule-maxlength="15"
																data-rule-minlength="4" 
																data-msg-maxlength="${usernamemax}" 
																data-msg-minlength="${usernamemin}" />
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
														<label for="password" class="form-lbl mandatory_lbl"><spring:message
																code="password" /> </label>
													</div>
													<div class="col-md-7">
														<form:password path="password" id="password"
															name="password" class="form-control" required="required"
															data-msg-required="${password}" data-rule-maxlength="15"
															data-rule-minlength="8"
															data-msg-maxlength="${passwordMax}"
															data-msg-minlength="${passwordMin}"
															data-rule-passwdweak="true" data-msg-passwdweak="${passwordformatmsg}" />
													</div>
												</div>
												<!-- /text box -->
											</div>
											<div class="col-md-6 remove-pad">
												<!-- text box -->
												<div class="form-group cf">
													<div class="col-md-5">
														<label for="confirm-password"
															class="form-lbl mandatory_lbl"><spring:message
																code="cpassword" /> </label>
													</div>
													<div class="col-md-7">
														<form:password path="confirmPassword"
															id="confirm-password" name="confirm-password"
															class="form-control" required="required"
															data-msg-required="${passwordMisMatchmsg}" data-msg-equalto="${passwordMisMatchmsg}"/>
													</div>
												</div>

												<!-- /text box -->
											</div>
										</div>
									</div>
									
									 <!-- captcha part Starts here -->
											
											<div class="row">
												<div class="col-md-12 remove-pad">
													<div class="col-md-6 remove-pad">
														<!-- text box -->
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
																		<label class="error error-captcha"><form:errors
																				path="captchaText" /></label>
																	</p>
																</div>
															</div>
		                                            	</div>
		                                        		<!-- /text box -->
		                                        	</div>
												</div>
	                                        </div>
									<!-- Terms and conditions -->
											<div class="row">
												<div class="col-md-12 remove-pad">
															<div class="col-md-6 remove-pad">
																<!-- text box -->
																<div class="form-group cf">
																	<div class="col-md-5">
																		<label for="termsandcondition" class="form-lbl mandatory_lbl"><spring:message code="acceptTerans"/></label>
																	</div>
																	<div class="col-md-7">
																		
																		<div class="checkbox inline-custom">
																				<div class="terms-link"> <a href="javascript:void(0)" data-toggle="modal" data-target="#termsModal" ><spring:message code="termsAndCondition"/></a></div>
																			<input type="checkbox" name="termsandcondition" id="termsandcondition1"  name="terms" value="" class="required"  data-msg-required="${acceptMessgaelabel}" />
																			<label for="termsandcondition1" class="custom"></label>
																			
																		</div>
																	</div>
																</div>
																<!-- /text box -->
															</div>
												</div>
											</div>
											<!-- /Terms and conditions-->

									<div class="row">
										<div class="col-md-12 remove-pad">
											<div class="col-md-6 remove-pad">
												<!-- submit button -->
												<div class="row">
													<div class="form-group submission">
														<div class="col-md-offset-5 col-md-7">
															<input type="submit" class="btn" value="Submit" />
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
				</div>
			</div>
		</div>
	</div>
</div>
<!-- Modal -->
	<div class="modal fade terms-model" id="termsModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
		<button type="button" class="close pull-right close-btn" data-dismiss="modal">
						<span class="glyphicon glyphicon-remove"></span>
		</button>
      <div class="modal-header">
        <h4 class="modal-title" id="myModalLabel"><spring:message code="termsAndCondition"/></h4>
      </div>
      <div class="modal-body">
		<c:if test="${param.languageCode == 'en' }">
			<%@include file="tearmscondition_EN.jsp"%>
		</c:if>
		<c:if test="${param.languageCode == 'ar' }">
			<%@include file="tearmscondition_AR.jsp"%>
		</c:if>
      </div>
      <div class="modal-footer">
        <button type="button" class="link-btn close pull-right close-btn" data-dismiss="modal"><spring:message code='btn.close'/></button>
      </div>
    </div>
  </div>
</div>
	
<!-- Modal -->
<!-- <script src="/js/dest/app.js"></script> -->
<script src="/js/libs/jquery.validate.js"></script>
<script type="text/javascript"src="/js/libs/jquery.mask.js"></script>

<script type="text/javascript">
	jQuery(function($) {
		
		$.validator.addMethod("alphacheck", function(value, element) {
			return this.optional(element) || /^([a-zA-Z0-9._])+$/i.test(value);
		});
		 $.validator.addMethod("passwdweak", function(value, element) {
				if(/^[a-z0-9\-\s]+$/i.test(value)){
					return false;	
				}else{
					
					return true;
				}
			});


		$("#activateAccount").validate({ignore: []});

		//for disaable input
		$("#fullNameHide").val($("#fullName").val());
		$("#residencyHide").val($("#residency").val());
		$("#citizenHide").val($("#citizen").val());
		$("#fullName").prop("readonly", true);
		$("#treadLicence").prop("readonly", true);
		$("#treadLicenceExprDate").prop("readonly", true);
		$("#residency").prop('disabled', true);
		$("#citizen").prop('disabled', true);
		$("#emirates").prop('disabled', true);
		
		$("#verification").focus(function(){
			
				$(".error-captcha").html("");
			});
	});
</script>
