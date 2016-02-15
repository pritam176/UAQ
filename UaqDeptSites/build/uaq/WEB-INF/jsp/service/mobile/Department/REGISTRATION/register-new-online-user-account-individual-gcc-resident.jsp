<!DOCTYPE html>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="en">
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>UAQ</title>
		<link href="css/app.css" rel="stylesheet">
		<!--<link rel="shortcut icon" href="" type="image/x-icon" />-->
		<script src="js/libs/jquery.min.js"></script>
		<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
		<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
		<!--[if lt IE 9]>
		<script src="js/ie.min.js"></script>
		<![endif]-->
	</head>
	<!-- body -->
	<body>
		<div class="container-fluid">
			<div class="wrapper">
			<div class="mainmenu">
					<div class="col-md-12 hidden-xs hidden-sm">
						<div class="mainmenu-wrap">
							<ul class="no-list cf">
								<li>
									<a href="/${param.languageCode}/home.html"><img src="/img/home1.png" alt="Home UAQ">
									</a>
								</li>
								<li class=""><a href="/${param.languageCode}/registrationlanding.html"><spring:message code="registration" /></a>
								</li>
								
								</li>
								<li class="active"><a href="#"><spring:message code="individualGccResident"/></a>
								</li>
								
							</ul>
						</div>
					</div>
				</div>
				<!--/mainmenu -->
				<!-- content area -->
				<div class="content">
				
				<c:set var="enterCountry"><spring:message code="feedback.form.field.required.country" /></c:set>
				<c:set var="enterLandLine"><spring:message code = 'plzLandLine'/></c:set>
				<c:set var="valMobile"><spring:message code="plzMobileNumber" /></c:set>
				<c:set var="valMobileLength"><spring:message code="maxnum" /></c:set>
				<c:set var ="emailMsg"><spring:message code="plzEmailAddress"/></c:set>
				<c:set var="userName"><spring:message code='plzUserName'/></c:set>
					<c:set var="password"><spring:message code='plzPassword'/></c:set>
					<c:set var="passwordMax"><spring:message code='passMax'/></c:set>
					<c:set var="passwordMin"><spring:message code = 'passMin'/></c:set>
					<c:set var="passwordMisMatch"><spring:message code = 'passwordMisMatch'/></c:set>
						<c:set var="enterPassportNo"><spring:message code = 'plzPassportNumber'/></c:set>
					<c:set var="uploadPassport"><spring:message code = 'plzUploadPassport'/></c:set>
					<c:set var="uploadPassportResidency"><spring:message code = 'plzUploadPaassportResidency'/></c:set>
					<c:set var ="enterValidlandlinemsg"><spring:message code="enterValidlandline"/></c:set>	
					<c:set var="fullNamereq"><spring:message code = 'plzFullNameIndiv'/></c:set>
					<c:set var ="hintTxtPassPostNamemsg"><spring:message code="hintTxtPassPostName"/></c:set>
					
					
				
					<div class="row">
						<!-- left col -->
						<div class="col-xs-12 col-sm-9 col-md-9 main-left-col">
							<!-- page title -->
							<div class="page-title-wrap">
								<div class="row">
									<div class="col-xs-12 col-sm-9 col-md-10 title-txt">
										<!-- page title -->
										<h2 class="page-title">
											<spring:message code="label.gccResident" />
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
										<div class="form-content cf">
										   <form:form commandName="registrationGccResidentCommand" name="feedbak" id="feedbak" enctype="multipart/form-data" method="post" action="gccresidentregister.html" >
										    <div class="row">
												<div class="row"> 
													<div class="col-md-12 remove-pad"><div class="form-group cf"><label class="mandatory_lbl pull-right"> <spring:message code="feedback.form.mandatory" /></label> </div></div>
												</div>
											</div>	
											<div class="row">
												<div class="col-md-12">  <!-- form-sect-head-->
													<h5 class="form-title"><spring:message code="accountDetailsSplit" /></h5>
												</div> <!-- /form-sect-head-->
											</div>
											<div class="row">
												<div class="col-md-12 remove-pad">
													<div class="col-md-6 remove-pad">
														<!-- text box -->
		                                            	<div class="form-group cf">
			                                                <div class="col-md-5">
			                                                   
			                                                   <label for="countryofcitizen" class="form-lbl mandatory_lbl"><spring:message code="countryCitizen" /></label>
				                                            </div>
				                                            <div class="col-md-7 form-grp-help">
			                                                    <div class="custom-select-box cf">
	                                                               
	                                                                 <form:select path="countryCitizenship"  class="required1" name="countryofcitizen" required="required" data-msg-required="${enterCountry}" >
	                                                                   
	                                                                    <form:option value="1"><spring:message code="UAE"/> </form:option>
	                                                                
																			<form:options items="${nationalList}" />
	                                                                </form:select>
	                                                            </div><span class="form-help" id="popoverCountryofResidence">?</span>
			                                                </div>
		                                            	</div>
		                                        		<!-- /text box -->
		                                        	</div>
		                                        	<div class="col-md-6 remove-pad">
														<!-- text box -->
		                                            	<div class="form-group cf">
			                                                <div class="col-md-5">
			                                                    <label for="first_name"  class="form-lbl mandatory_lbl"><spring:message code="countryResidence" /></label>
				                                            </div>
				                                            <div class="col-md-7">
			                                                   <div class="custom-select-box cf">
	                                                                <form:select path="countryResidence"  class="required1" name="country" required="required" data-msg-required="${enterCountry}" >
	                                                                    <form:option value="1"><spring:message code="UAE"/> </form:option>
																			<form:options items="${nationalList}" />
	                                                                </form:select>
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
			                                                    <label for="mobile_number1" class="form-lbl mandatory_lbl"><spring:message code="mobileNum" />
			                                                    </label>
				                                            </div>
				                                            <div class="col-md-7">
															    <div class="col-md-4 col-xs-3 remove-pad">
																	<div class="custom-select-box cf">
																		<form:select path="mobileCode1"  class="required1" name="mobile_number1" >
																			<option value="050">050</option>
																			<option value="052">052</option>
																			<option value="055">055</option>
																			<option value="056">056</option>
																		</form:select>
																	</div> 
																</div>
																<div class="col-md-8 col-xs-9 remove-pad">
																	<form:input path="mobileNumber1" id="mobile_number1" name="mobile_number1" type="text"  class="form-control required" 
																	data-rule-maxlength="7" data-rule-minlength="7" data-msg-required="${valMobile}" data-rule-number="true" data-msg-number="${valMobile}"
																	data-msg-maxlength="${valMobileLength}" 
																	data-msg-minlength="${valMobileLength}" />
																</div>
			                                                </div>
		                                            	</div>
		                                            	
		                                        		<!-- /text box -->
		                                        	</div>
		                                        	<div class="col-md-6 remove-pad">
														<!-- text box -->
		                                            	<div class="form-group cf">
			                                                <div class="col-md-5">
			                                                    <label for="mobile_number2" class="form-lbl "><spring:message code="mobileNum2" />
			                                                    </label>
				                                            </div>
				                                            <div class="col-md-7">
																<div class="col-md-4 col-xs-3 remove-pad">
																	<div class="custom-select-box cf">
																		<form:select path="mobileCode2"  class="required1" name="mobile_number2"  >
																			<option value="050">050</option>
																			<option value="052">052</option>
																			<option value="055">055</option>
																			<option value="056">056</option>
																		</form:select>
																	</div> 
																</div>
																<div class="col-md-8 col-xs-9 remove-pad">
																	<form:input path="mobileNumber2" id="mobile_number2" name="mobile_number2" type="text"  class="form-control" 
																data-rule-maxlength="7" data-rule-minlength="7" data-msg-required="${valMobile}" data-rule-number="true" data-msg-number="${valMobile}"
																	data-msg-maxlength="${valMobileLength}" 
																	data-msg-minlength="${valMobileLength}" />
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
			                                                    <label for="landline" class="form-lbl mandatory_lbl"><spring:message code="landlineNum" />
			                                                    </label>
				                                            </div>
				                                            <div class="col-md-7">
																 <div class="col-md-4 col-xs-3 remove-pad">
																	<div class="custom-select-box cf">
																		<form:select path="landLineCode"  class="required1 " name="landline"  >
																			<option value="02">02</option>
																			<option value="04">04</option>
																			<option value="06">06</option>
																			<option value="07">07</option>
																			<option value="09">09</option>
																		</form:select>
																	</div> 
																</div>
																<div class="col-md-8 col-xs-9 remove-pad">
																	<form:input path="landLineNumber" id="landline" name="landline" type="text" class="form-control " 
																	data-rule-number="true" data-msg-number="${enterValidlandlinemsg}"  />
																</div>	
			                                                </div>
		                                            	</div>
		                                        		<!-- /text box -->
		                                        	</div>
		                                        	<div class="col-md-6 remove-pad">
														<!-- text box -->
		                                            	<div class="form-group cf">
			                                                <div class="col-md-5">
			                                                    <label  for="email" class="form-lbl mandatory_lbl"><spring:message code="email" />
			                                                    </label>
				                                            </div>
				                                            <div class="col-md-7">
																<form:input path="email"  type="email" id="email" name="email" class="form-control required" data-msg-required="${emailMsg}" />
			                                                </div>
		                                            	</div>
													
		                                        		<!-- /text box -->
		                                        	</div>
												</div>
	                                        </div>
											<div class="row">
												<div class="col-md-12">  <!-- form-sect-head-->
													<h5 class="form-title"><spring:message code="credentialsSplit" /></h5>
												</div> <!-- /form-sect-head-->
											</div>	
											<div class="row">
												<div class="col-md-12 remove-pad">
													<div class="col-md-6 remove-pad">
														<!-- text box -->
		                                            	<div class="form-group cf">
			                                                <div class="col-md-5">
			                                                    <label for="username" class="form-lbl mandatory_lbl"><spring:message code="username" />
			                                                    </label>
				                                            </div>
				                                            <div class="col-md-7"> 
			                                                    <form:input path="userName" id="username" name="username" type="text" class="form-control required"   data-msg-required="${userName}"/>
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
			                                                    <label for="password" class="form-lbl mandatory_lbl"><spring:message code="password" />
			                                                    </label>
				                                            </div>
				                                            <div class="col-md-7">
			                                                    <form:input path="password" id="password_register" name="password_register" type="password" class="form-control required"  
			                                                	 data-msg-required="${password}" 
																data-rule-maxlength="15" 
																data-rule-minlength="8" 
																data-msg-maxlength="${passwordMax}" 
																data-msg-minlength="${passwordMin}"/>
			                                                </div>
		                                            	</div>
		                                        		<!-- /text box -->
		                                        	</div>
		                                        	<div class="col-md-6 remove-pad">
														<!-- text box -->
		                                            	<div class="form-group cf">
			                                                <div class="col-md-5">
			                                                    <label  for="confirm-password" class="form-lbl mandatory_lbl"><spring:message code="cpassword" />
			                                                    </label>
				                                            </div>
				                                            <div class="col-md-7">
																<form:input path="confirmPassword"  type="password" id="confirm-password" equalto="#password_register" name="confirm-password" class="form-control"    data-msg-required="${passwordMisMatch}"/>
			                                                </div>
		                                            	</div>
													
		                                        		<!-- /text box -->
		                                        	</div>
												</div>
	                                        </div>
											
											<div class="row">
												<div class="col-md-12"> <!-- form-sect-head-->
													<h5 class="form-title"><spring:message code="passportInformatioinSplit" /></h5>
												</div>
											</div><!-- /form-sect-head-->
											<div class="row">
												<div class="col-md-12 remove-pad">
													<div class="col-md-6 remove-pad">
														<!-- text box -->
		                                            	<div class="form-group cf">
			                                                <div class="col-md-5">
			                                                    <label for="fullname" class="form-lbl mandatory_lbl"><spring:message code="fullNameBook" />
			                                                    </label><span class="form-lbl-subtxt"> <br/> <spring:message code="hintTxtPassPostName"/> </span>
				                                            </div>
				                                            <div class="col-md-7">
			                                                    <form:input path="fullname" id="fullname" name="fullname" type="text" class="form-control required"   data-msg-required="${fullNamereq}"/>
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
			                                                    <label for="passportnum" class="form-lbl mandatory_lbl"><spring:message code="passportNum" />
			                                                    </label>
				                                            </div>
				                                            <div class="col-md-7">
			                                                    <form:input path="passportNumber" id="passportnum" name="passportnum" type="text" class="form-control required"  data-msg-required="${enterPassportNo}"/>
			                                                </div>
		                                            	</div>
		                                        		<!-- /text box -->
		                                        	</div>
													<div class="col-md-6 remove-pad">
														<!-- text box -->
		                                            	<div class="form-group cf">
			                                                <div class="col-md-5">
			                                                    <label for="passportfrontpage" class="form-lbl mandatory_lbl"><spring:message code="passportFrontAttach" />
			                                                    </label>
				                                            </div>
				                                            <div class="col-md-7">
																<div class="input-group file-upload">
																	<input type="text" class="form-control readonly required" readonly="readonly" name ="passportfrontpage "  data-msg-required="${uploadPassport}" />
																	<span class="input-group-btn">
																		<span class="btn btn-file">
																			<spring:message code="file.browse" />&hellip; 
																			<form:input path="passportFrontPage" type="file" />
																		</span>
																	</span>
																</div>
			                                                </div>
		                                            	</div>
		                                        		<!-- /text box -->
		                                        	</div>
													<div class="col-md-6 remove-pad">
														<!-- text box -->
		                                            	<div class="form-group cf">
			                                                <div class="col-md-5">
			                                                    <label for="passport_residency_page" class="form-lbl mandatory_lbl"><spring:message code="passportResidentpage" />
			                                                    </label>
				                                            </div>
				                                            <div class="col-md-7">
																<div class="input-group file-upload">
																	<input type="text" class="form-control readonly required" readonly="readonly" name ="passport_residency_page"   data-msg-required="${uploadPassportResidency}" />
																	<span class="input-group-btn">
																		<span class="btn btn-file">
																			<spring:message code="file.browse" />&hellip; 
																			<form:input path="passportResidencyPage" type="file"/>
																		</span>
																	</span>
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
				                                                    <input type="submit" class="btn" value='<spring:message code="form.button.submit" />' /> 
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
				
			</div>
		</div>
	</div>
		<!-- script -->
	<!-- 	<script src="/js/dest/app.js"></script> -->
		<script src="/js/libs/jquery.validate.js"></script>
		<script src="/js/libs/tooltip.js"></script>
				<script src="/js/libs/popover.js"></script>
				<style>
					.in { display:table !important;}
				</style>
	    <script>
				
			jQuery(function($) { 

			$("#feedbak").validate();
			
				var imageContryofResidence = '<img src="/img/help/1.jpg">';
			$('#popoverCountryofResidence').popover({placement: 'right', content: imageContryofResidence, html: true});
			
			var imageFullName = '<img src="/img/help/2.jpg">';
			$('#popoverFullName').popover({placement: 'right', content: imageFullName, html: true});
			
			var imageEmiratesId = '<img src="/img/help/3.jpg">';
			$('#popoverEmiratesId').popover({placement: 'right', content: imageEmiratesId, html: true});		
			
			var imageEmiratesIdExp = '<img src="/img/help/4.jpg">';
			$('#popoverEmiratesIdExp').popover({placement: 'right', content: imageEmiratesIdExp, html: true});	
			
			var imageDob = '<img src="/img/help/5.jpg">';
			$('#popoverdob').popover({placement: 'right', content: imageDob, html: true});
			
			});
			
	    </script>
		<!-- /script -->
	</body>
	<!-- /body -->
</html>