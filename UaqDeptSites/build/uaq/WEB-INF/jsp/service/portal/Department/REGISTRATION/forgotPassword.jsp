<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="emaritesid"><spring:message code="enterEmirates" /></c:set>
<c:set var="valLength"><spring:message code="validate.length" /></c:set>
<c:set var ="plzPassportmsg"><spring:message code="plzPassportNum"/></c:set>
<c:set var ="emailMsg"><spring:message code="plzEmailAddress"/></c:set>
<c:set var="valMobileLength"><spring:message code="maxnum" /></c:set>
<c:set var="valMobile"><spring:message code="plzMobileNumber" /></c:set>
<c:set var="tradeLicNoReq"><spring:message code="plzTradeLicenseNumber" /></c:set>
<c:set var="userName"><spring:message code='plzUserName'/></c:set>
<c:set var="emipass"><spring:message code='emirateorpassport'/></c:set>


<div class="container-fluid">
			<div class="wrapper">
<div class="content">
					<div class="row">
		<!-- right col -->
		<div class="col-xs-12 col-sm-9 col-md-9 main-left-col">
				<div class="mainmenu">
					<div class="col-md-12 hidden-xs hidden-sm">
						<div class="mainmenu-wrap">
							<ul class="no-list cf">
								<li>
									<a href="/${param.languageCode}/home.html"><img src="/img/home1.png" alt="Home UAQ">
									</a>
								</li>
								
								<li class="active"><a href="#"><spring:message code="header.forget.forgotpassword" /></a>
								</li>
								
							</ul>
						</div>
					</div>
				</div>
				</div>
			<!-- left col -->	
	<div class="col-xs-12 col-sm-9 col-md-9 main-left-col">
		<!-- page title -->
		<div class="page-title-wrap">
			<div class="row">
				<div class="col-xs-12 col-sm-9 col-md-10 title-txt">
					
					<!-- page title -->
					<h2 class="page-title"><spring:message code="header.forget.forgotpassword"/></h2>
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
				<div class="form-content cf">
					<form:form action="forgetpassword.html" method="POST"
						id="forgetPassword" class="eserviceform" commandName="forgetPasswordCommand">

						<div class="row">
							<div class="row">
								<div class="col-md-12 remove-pad">
									<div class="form-group cf">
										<label class=" pull-right"></label>
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
											<div class="radio inline-custom">

												<form:radiobutton path="userType" id="regtype1"
													name="regtype" value="1" class="radiobtn required" />

												<label for="regtype1" class="custom"><spring:message code="individualUserTxt"/></label>
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
									<div class="form-group cf">
										<div class="col-md-5">
											<label for="individual-username"
												class="form-lbl mandatory_lbl"><spring:message code="header.username"/> </label>
										</div>
										<div class="col-md-7">
											<form:input type="text" path="username" id="iusername"
												name="username" class="form-control indiform indiform_req" placeholder=""
												readonly="readonly" data-msg-required="${userName}" />

										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="row">  <!-- / Mobile Num -->
							<div class="col-md-12 remove-pad">
								<div class="col-md-6 remove-pad">
									<div class="form-group cf">
										<div class="col-md-5">
											<label for="mobile_number" class="form-lbl mandatory_lbl"><spring:message code="mobileNum1"/></label>
										</div>
										<div class="col-md-7">
											<div class="col-md-4 col-xs-3 remove-pad arbic-code">
												<div class="custom-select-box">
													<form:select path="mobileCodeIndividual"
														class="required1 indiform" name="mobile-code-individual"
														id="mobileCodeIndividual" disable="disable">
														<option value="050">050</option>
														<option value="052">052</option>
														<option value="055">055</option>
														<option value="056">056</option>
													</form:select>
												</div>
											</div>
											<div class="col-md-8 col-xs-9 remove-pad">
													<form:input id="mobileNumberIndividual"
														path="mobileNumberIndividual" name="mobileNumberIndividual"
														type="text" class="form-control indiform indiform_req" placeholder=""
														readonly="readonly"
														data-rule-maxlength="7" data-rule-minlength="7" 
														data-msg-required="${valMobile}" data-rule-number="true" 
														data-msg-number="${valMobile}"
														data-msg-maxlength="${valMobileLength}" 
														data-msg-minlength="${valMobileLength}" maxlength="7"/>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div> <!-- / Mobile Num -->
						<div class="row">
							<div class="col-md-12 remove-pad">
								<div class="col-md-6 remove-pad">
									<!-- text box -->
									<div class="form-group cf">
										<div class="col-md-5">
											<label for="tradelicence" class="form-lbl mandatory_lbl"><spring:message code="emiratedId"/>
												</label>
												<span class="either-label"><spring:message code="lable.or"/></span>
										</div>
										<div class="col-md-7">
											<form:input id="emiritesID" path="emiritesID"
												name="emiritesID" class="form-control indiform indiform-either"
												placeholder="" readonly="readonly"
												data-rule-maxlength="18" data-rule-minlength="18"
												 data-msg-required="${emaritesid}"
												data-msg-maxlength="${valLength}" 
												data-msg-minlength="${valLength}" data-mask="000-0000-0000000-0" />
										</div>
									</div>
									<!-- /text box -->
								</div>
							</div>
						</div>
						
						<div class="row"> <!-- passportNum -->
							<div class="col-md-12 remove-pad">
								<div class="col-md-6 remove-pad">
									<!-- text box -->
									<div class="form-group cf">
										<div class="col-md-5">
											<label for="tradelicence" class="form-lbl mandatory_lbl"><spring:message code="passportNum"/></label>
										</div>
										<div class="col-md-7">
											<form:input id="passportNo" path="passportNo"
												name="passportNo" class="form-control indiform  indiform-either"
												placeholder="" readonly="readonly" data-msg-required="${plzPassportmsg}"  data-msg-either_group="${emipass}" maxlength="20"/>
										</div>
									</div>
								</div>
							</div>
						</div> <!-- /passportNum -->
						<!--   -----------------------------------  Establishment  --------------------------- -->
						<div class="row">
							<div class="col-md-12 remove-pad">
								<div class="col-md-6 remove-pad">
									<!-- text box -->
									<div class="form-group cf">
										<div class="col-md-5">
											<div class="radio inline-custom">
												<form:radiobutton id="regtype2" path="userType"
													name="regtype" value="2" class="radiobtn required" />
												<label for="regtype2" class="custom"><spring:message code="establishment"/></label>
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
									<!-- text box -->
									<div class="form-group cf">
										<div class="col-md-5">
											<label for="establish-username"
												class="form-lbl mandatory_lbl"><spring:message code="header.username"/> </label>
										</div>
										<div class="col-md-7">
											<form:input id="establishUsername" path="establishUsername"
												name="establishUsername" class="form-control  estform"
												placeholder="" readonly="readonly" data-msg-required="${userName}" />
										</div>
									</div>
									<!-- /text box -->
								</div>
							</div>
						</div>

						<div class="row">
							<div class="col-md-12 remove-pad">
								<div class="col-md-6 remove-pad">
									<div class="form-group cf">
										<div class="col-md-5">
											<label for="emailpassport" class="form-lbl mandatory_lbl"><spring:message code="trade.licence.number"/>
												 </label>
										</div>
										<div class="col-md-7">
											<form:input id="tradeLicenseNo" path="tradeLicenseNo"
												name="tradeLicenseNo" class="form-control  estform"
												placeholder="" readonly="readonly" data-msg-required="${tradeLicNoReq}" maxlength="20"/>
										</div>
									</div>
								</div>
							</div>
						</div>

						<div class="row">
							<div class="col-md-12 remove-pad">
								<div class="col-md-6 remove-pad">
									<!-- text box -->
									<div class="col-md-5">
										<label for="mobile-number-establish"
											class="form-lbl mandatory_lbl"><spring:message code="mobileNum1"/> </label>
									</div>
									<div class="col-md-7">
										<div class="col-md-4 col-xs-3 remove-pad arbic-code">
											<div class="custom-select-box">
												<form:select path="mobileCodeEstablish"
													class="required1 estform" name="mobile-code-establish"
													id="mobileCodeEstablish" disabled="disabled">
													<option value="050">050</option>
													<option value="052">052</option>
													<option value="055">055</option>
													<option value="056">056</option>
												</form:select>
											</div>
										</div>
										<div class="col-md-8 col-xs-9 remove-pad">
											<form:input id="mobileNumberEstablish"
												path="mobileNumberEstablish" name="mobileNumberEstablish"
												type="text" class="form-control estform" placeholder=""
												readonly="readonly" data-rule-maxlength="7" data-rule-minlength="7" 
												data-msg-required="${valMobile}" data-rule-number="true" 
												data-msg-number="${valMobile}"
												data-msg-maxlength="${valMobileLength}" 
												data-msg-minlength="${valMobileLength}" maxlength="7"/>
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
												<input type="submit" class="btn" value='<spring:message code="form.button.submit"/>'
													id="forgot-btn" />
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
<!-- /content area -->

<!-- script -->
<!-- <script src="/js/dest/app.js"></script> -->
<script src="/js/libs/jquery.validate.js"></script>
<script src="/js/libs/additional-methods.min.js"></script>
<script type="text/javascript"src="/js/libs/jquery.mask.js"></script>

<script>
	jQuery(function($) {

		$("#forgetPassword").validate();
		var datamsgeither = $("#passportNo").attr("data-msg-either_group");
				jQuery.extend(jQuery.validator.messages, {		 
				require_from_group: jQuery.format(datamsgeither)
			});
			
			var radioButton1 = $('input[name=userType]:checked', '#forgetPassword').val();
			
				if (radioButton1 === "1") {
						$("label.error").hide();
						$(".estform").removeClass("required");
						$(".indiform").attr("readonly", false);
						$(".estform").attr("readonly", true);
						$(".estform").attr("disable", true);
						//$('.estform input[type="text"]').val("");
						//$('.indiform input[type="text"]').val("");
						$(".indiform_req").addClass("required");
						$(".indiform-either").addClass("either_group");
						$("#mobileCodeEstablish").attr("disabled", true);
						$("#mobileCodeIndividual").attr("disabled", false);
						$("#mobileCodeIndividual").val($("#mobileCodeIndividual option:first").val());
						document.getElementById("forgot-btn").disabled = false;
						$("#forgot-btn").attr("readonly", false);
						$('input[name="emiritesID"]').rules('add', {
							require_from_group: [1, ".either_group"]
						});
						 $('input[name="passportNo"]').rules('add', {
							require_from_group: [1, ".either_group"]
						});

					} else if(radioButton1 === "2"){
						$("label.error").hide();
						$(".indiform_req").removeClass("required");
						$(".indiform-either").removeClass("either_group");
						$(".estform").attr("readonly", false);
						$(".indiform").attr("readonly", true);
						//$('.indiform').val("");
						//$('.estform').val("");
						$(".estform").addClass("required");
						$("#mobileCodeEstablish").attr("disabled", false);
						$("#mobileCodeEstablish").val($("#mobileCodeEstablish option:first").val());
						$("#mobileCodeIndividual").attr("disabled", true);
						document.getElementById("forgot-btn").disabled = false;
						$("#forgot-btn").attr("readonly", false);
						$('input[name="emiritesID"]').rules('remove');
						$('input[name="passportNo"]').rules('remove');

					}else{
						$('#forgetPassword :input').attr('readonly', '');
						document.getElementById("forgot-btn").disabled = true;
						
					}

		$('#forgetPassword input[type="radio"]').on('change', function() {

					var radioButton = $('input[name=userType]:checked',
							'#forgetPassword').val();
					//console.log(radioButton);

					if (radioButton === "1") {
						$("label.error").hide();
						$(".estform").removeClass("required");
						$(".indiform").attr("readonly", false);
						$(".estform").attr("readonly", true);
						$(".estform").attr("disable", true);
						$('.estform').val("");
						$('.indiform').val("");
						$(".indiform_req").addClass("required");
						$(".indiform-either").addClass("either_group");
						$("#mobileCodeEstablish").attr("disabled", true);
						$("#mobileCodeIndividual").attr("disabled", false);
						$("#mobileCodeIndividual").val($("#mobileCodeIndividual option:first").val());
						document.getElementById("forgot-btn").disabled = false;
						$("#forgot-btn").attr("readonly", false);
						$('input[name="emiritesID"]').rules('add', {
							require_from_group: [1, ".either_group"]
						});
						 $('input[name="passportNo"]').rules('add', {
							require_from_group: [1, ".either_group"]
						});

					} else {
						$("label.error").hide();
						$(".indiform_req").removeClass("required");
						$(".indiform-either").removeClass("either_group");
						$(".estform").attr("readonly", false);
						$(".indiform").attr("readonly", true);
						$('.indiform').val("");
						$('.estform').val("");
						$(".estform").addClass("required");
						$("#mobileCodeEstablish").attr("disabled", false);
						$("#mobileCodeEstablish").val($("#mobileCodeEstablish option:first").val());
						$("#mobileCodeIndividual").attr("disabled", true);
						document.getElementById("forgot-btn").disabled = false;
						$("#forgot-btn").attr("readonly", false);
						$('input[name="emiritesID"]').rules('remove');
						$('input[name="passportNo"]').rules('remove');

					}
				});
		
	});
</script>