<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%-- <%@ page import="net.tanesha.recaptcha.ReCaptcha"%>
 <%@ page import="net.tanesha.recaptcha.ReCaptchaFactory"%> --%>
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
<c:set var="emipass"><spring:message code='emirateorpassport'/></c:set>
<c:set var ="validemailMsg"><spring:message code="validemail"/></c:set>
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
								
								<li class="active"><a href="#"><spring:message code="header.forget.username" /></a>
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
					<h2 class="page-title"><spring:message code="header.forget.username"/></h2>
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
					<form:form name="fogetUsername" id="fogetUsername"
						enctype="multipart/form-data" method="post"
						action="forgetusername.html"  class="eserviceform" commandName="forgetUsernameCommand">
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
												<form:radiobutton path="userType" value="1" id="regtype1"
													class="radiobtn required" />

												<label for="regtype1" class="custom"><spring:message code="individualUserTxt"/></label>
											</div>
										</div>
									</div>
									<!-- /text box -->
								</div>
							</div>
						</div>
						<div class="row">   <!-- Mobile number -->
							<div class="col-md-12 remove-pad">
								<div class="col-md-6 remove-pad">
									<div class="form-group cf">
										<div class="col-md-5">
											<label for="mobile_number" class="form-lbl mandatory_lbl"><spring:message code="mobileNum1"/>
												 </label>
										</div>
								
										<div class="col-md-7">
										<div class="col-md-4 col-xs-3 remove-pad arbic-code">
											<div class="custom-select-box">
												<form:select path="mobileNumberIndividualCode"
													class="required1 indiform"  name="mobile-code-individual"
													id="mobileNumberIndividualCode" disable="disable">
													<option value="050">050</option>
													<option value="052">052</option>
													<option value="055">055</option>
													<option value="056">056</option>
												</form:select>
											</div>
											</div>
											<div class="col-md-8 col-xs-9 remove-pad">
											<form:input path="mobileNumberIndividual" id="mobile_number"
												name="mobile_number" type="text"
												class="form-control indiform indiform_req" 
												readonly="readonly"
												data-rule-maxlength="7" data-rule-minlength="7" 
												data-msg-required="${valMobile}" data-rule-number="true" 
												data-msg-number="${valMobile}"
												data-msg-maxlength="${valMobileLength}" 
												data-msg-minlength="${valMobileLength}"	maxlength="7" data-mask="0000000"/>

											</div>
										</div>
									</div>
								</div>
							</div>
						</div>  <!-- /Mobile number -->
						<div class="row">  <!-- Email  -->
							<div class="col-md-12 remove-pad">
								<div class="col-md-6 remove-pad">
									<div class="form-group cf">
										<div class="col-md-5">
											<label for="email" class="form-lbl mandatory_lbl"><spring:message code="email"/>
											</label>
										</div>
										<div class="col-md-7">
											<form:input  path="emailAddress" id="email"
												name="email" type="email" class="form-control indiform indiform_req "
												placeholder="" readonly="readonly" data-msg-required="${emailMsg}" data-msg-email="${validemailMsg}" />

										</div>
									</div>
								</div>
							</div>
						</div>  <!-- /Email  -->
						<div class="row"> <!-- Emirates Id  -->
							<div class="col-md-12 remove-pad">
								<div class="col-md-6 remove-pad">
									<div class="form-group cf">
										<div class="col-md-5">
											<label for="individual-idornum"
												class="form-lbl mandatory_lbl"><spring:message code="emiratedId"/> </label>
												<span class="either-label"><spring:message code="lable.or"/></span>
										</div>
										<div class="col-md-7">
											<form:input type="text" path="emiratesId" id="emiratesId"
												class="form-control indiform indiform-either " readonly="readonly" data-rule-maxlength="18" data-rule-minlength="18"
															 data-msg-required="${emaritesid}"
																	data-msg-maxlength="${valLength}" 
																	data-msg-minlength="${valLength}" data-mask="000-0000-0000000-0" />

										</div>
									</div>
								</div>
							</div>
						</div> <!-- /Emirates Id  -->

						<div class="row"> <!-- passport  -->
							<div class="col-md-12 remove-pad">
								<div class="col-md-6 remove-pad">
									<div class="form-group cf">
										<div class="col-md-5">
											<label for="individual-idornum"
												class="form-lbl mandatory_lbl"><spring:message code="passportNum"/></label>
										</div>
										<div class="col-md-7">
											<form:input type="text" path="passportNo" id="passportNo"
												class="form-control indiform indiform-either " readonly="readonly"  data-msg-either_group="${emipass}" data-msg-required="${plzPassportmsg}" maxlength="20"/>

										</div>
									</div>
								</div>
							</div>
						</div> <!-- /passport  -->
					
				
						<!--   ---------------------------Establishment-------------------------------------------   -->
						<div class="row">
							<div class="col-md-12 remove-pad">
								<div class="col-md-6 remove-pad">
									<!-- text box -->
									<div class="form-group cf">
										<div class="col-md-5">
											<div class="radio inline-custom">
												<form:radiobutton path="userType" id="regtype2" value="2"
													class="radiobtn required" />

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
											<label for="tradelicencenumber"
												class="form-lbl mandatory_lbl"><spring:message code="trade.licence.number"/>
											</label>
										</div>
										<div class="col-md-7">
											<form:input path="tradeLicenceNumber" id="tradelicencenumber"
												 class="form-control  estform"
												placeholder="" readonly="readonly" data-msg-required="${tradeLicNoReq}" maxlength="20"/>

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
											<label for="emailpassport" class="form-lbl mandatory_lbl"><spring:message code="email"/>
											</label>
										</div>
										<div class="col-md-7">
											<form:input path="emailAddressEstablish" id="emailpassport" type="email"
												 class="form-control  estform"
												placeholder="" readonly="readonly" data-msg-required="${emailMsg}" />

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
									
									<div class="col-md-5">
										<label for="mobile-number-establish"
											class="form-lbl mandatory_lbl"><spring:message code="mobileNum1"/></label>
									</div>
								
										
										<div class="col-md-7">
										<div class="col-md-4 col-xs-3 remove-pad arbic-code">
											<div class="custom-select-box">
												<form:select path="mobileNumberEstablismentCode"
													class="required1 indiform" name="mobile-code-individual"
													id="mobileNumberEstablismentCode" disable="disable">
													<option value="050">050</option>
													<option value="052">052</option>
													<option value="055">055</option>
													<option value="056">056</option>
												</form:select>
											</div>
											</div>
											<div class="col-md-8 col-xs-9 remove-pad">
											<form:input type="text" path="mobileNumberEstablish"
												id="mobile-number-establish" 
												 class="form-control estform" placeholder=""
												readonly="readonly" data-rule-maxlength="7" data-rule-minlength="7" 
												data-msg-required="${valMobile}" data-rule-number="true" 
												data-msg-number="${valMobile}"
												data-msg-maxlength="${valMobileLength}" 
												data-msg-minlength="${valMobileLength}" maxlength="7" data-mask="0000000"/>

										</div>
										
									<!-- /text box -->
									</div>
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
	</div>
	</div>
	<div>

<!-- <script src="/js/dest/app.js"></script> -->
<script src="/js/libs/jquery.validate.js"></script>
<script src="/js/libs/additional-methods.min.js"></script>
<script type="text/javascript"src="/js/libs/jquery.mask.js"></script>

<script type="text/javascript">
	jQuery(function($) {

		$("#fogetUsername").validate();
			var datamsgeither = $("#passportNo").attr("data-msg-either_group");
				jQuery.extend(jQuery.validator.messages, {		 
				require_from_group: jQuery.format(datamsgeither)
			});
		var radioButton1 = $('input[name=userType]:checked', '#fogetUsername').val();
			if (radioButton1 === "1") {
				
						$("label.error").hide();
						$(".estform").removeClass("required");
						$(".indiform").attr("readonly", false);
						$(".estform").attr("readonly", true);
						$(".estform").val("");
						$(".indiform_req").addClass("required");
						$(".indiform-either").addClass("either_group");
						$("#mobileNumberEstablismentCode").attr("disabled", true);
						$("#mobileNumberIndividualCode").attr("disabled", false);
						$("#mobileNumberIndividualCode").val($("#mobileNumberIndividualCode option:first").val());
						document.getElementById("forgot-btn").disabled = false;
						$("#forgot-btn").attr("readonly", false);
						$('input[name="emiratesId"]').rules('add', {
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
						$(".indiform").val("");
						$(".estform").addClass("required");
						$("#mobileNumberEstablismentCode").attr("disabled", false);
						$("#mobileNumberEstablismentCode").val($("#mobileNumberEstablismentCode option:first").val());
						$("#mobileNumberIndividualCode").attr("disabled", true);
						document.getElementById("forgot-btn").disabled = false;
						$("#forgot-btn").attr("readonly", false);
						$('input[name="emiratesId"]').rules('remove');
						$('input[name="passportNo"]').rules('remove');
					}else{
						
						$('#fogetUsername :input').attr('readonly', '');
						document.getElementById("forgot-btn").disabled = true;
					}
		
		
		
		$('#fogetUsername input[type="radio"]').on('change',
				function() {
					var radioButton = $('input[name=userType]:checked', '#fogetUsername').val();
					
					
					if (radioButton === "1") {
						$("label.error").hide();
						$(".estform").removeClass("required");
						$(".indiform").attr("readonly", false);
						$(".estform").attr("readonly", true);
						$(".estform").val("");
						$(".indiform_req").addClass("required");
						$(".indiform-either").addClass("either_group");
						$("#mobileNumberEstablismentCode").attr("disabled", true);
						$("#mobileNumberIndividualCode").attr("disabled", false);
						$("#mobileNumberIndividualCode").val($("#mobileNumberIndividualCode option:first").val());
						document.getElementById("forgot-btn").disabled = false;
						$("#forgot-btn").attr("readonly", false);
						$('input[name="emiratesId"]').rules('add', {
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
						$(".indiform").val("");
						$(".estform").addClass("required");
						$("#mobileNumberEstablismentCode").attr("disabled", false);
						$("#mobileNumberEstablismentCode").val($("#mobileNumberEstablismentCode option:first").val());
						$("#mobileNumberIndividualCode").attr("disabled", true);
						document.getElementById("forgot-btn").disabled = false;
						$("#forgot-btn").attr("readonly", false);
						$('input[name="emiratesId"]').rules('remove');
						$('input[name="passportNo"]').rules('remove');
					}
				});

		

	});
</script>