<!DOCTYPE html>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html lang="en">
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>UAQ</title>
		<link href="/css/app.css" rel="stylesheet">
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
								<li class="active"><a href="#"><spring:message code="establishmentReg" /> </a>
								</li>
								
							</ul>
						</div>
					</div>
				</div>
				<!--/mainmenu -->
				<!-- content area -->
				<div class="content">
				
				<c:set var="establishmentRequired"><spring:message code="feedback.form.field.establishment.required" /></c:set>
				<c:set var="enterMobile"><spring:message code="plzMobileNumber" /></c:set>
				<c:set var="valMobileLength"><spring:message code="maxnum" /></c:set>
				<c:set var="enterOfficeNum"><spring:message code="plzOfficeNumber" /></c:set>
				<c:set var="emailAdressReq"><spring:message code="feedback.form.field.email.required" /></c:set>
				<c:set var="addressReq"><spring:message code="plzAddress" /></c:set>
				<c:set var="emiratesReq"><spring:message code="plzChooseEmirates" /></c:set>
				<c:set var="tradeLicNoReq"><spring:message code="plzTradeLicenseNumber" /></c:set>
				<c:set var="tradeLicexp"><spring:message code="plzTradeLicenseExp" /></c:set>
				<c:set var="tradeLicType"><spring:message code="plzTradelicenseType" /></c:set>
				<c:set var="tradeLicAttach"><spring:message code="plzUploadTradeLicense" /></c:set>
				<c:set var="signatureAttest"><spring:message code="plzSignatureAttestation" /></c:set>
				<c:set var="userName"><spring:message code='plzUserName'/></c:set>
				<c:set var ="emailMsg"><spring:message code="plzEmailAddress"/></c:set>
				<c:set var="password"><spring:message code='plzPassword'/></c:set>
					<c:set var="passwordMax"><spring:message code='passMax'/></c:set>
					<c:set var="passwordMin"><spring:message code = 'passMin'/></c:set>
					<c:set var="passwordMisMatch"><spring:message code = 'passwordMisMatch'/></c:set>
					<c:set var="enterWebsite"><spring:message code = 'plzWebsite'/></c:set>
					
				
				
				
					<div class="row">
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
											<spring:message code="establishmentReg" />
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
										   <form:form commandName="registrationEstablishmentCommand" name="feedbak" id="feedbak" enctype="multipart/form-data" method="post" action="establishmentregister.html" >
										    <div class="row">
												<div class="row"> 
													<div class="col-md-12 remove-pad"><div class="form-group cf"><label class="mandatory_lbl pull-right"><spring:message code="feedback.form.mandatory" /></label> </div></div>
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
			                                                    <label for="establisname" class="form-lbl mandatory_lbl"><spring:message code="establishNameAsStated" />
			                                                    </label>
				                                            </div>
				                                            <div class="col-md-7"> 
			                                                    <form:input path="establishmentName" id="establisname" name="establisname" type="text" class="form-control required" data-msg-required="${establishmentRequired}" />
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
			                                                    <label for="mobile_number1" class="form-lbl mandatory_lbl"><spring:message code="mobileNum1" />
			                                                    </label>
				                                            </div>
				                                            <div class="col-md-7">
															    <div class="col-md-4 col-xs-3 remove-pad">
																	<div class="custom-select-box cf">
																		<form:select path="mobileCode"  class="required1" name="countryofcitizen" required="required" >
																			<option value="050">050</option>
																			<option value="052">052</option>
																			<option value="055">055</option>
																			<option value="056">056</option>
																		</form:select>
																	</div> 
																</div>
																<div class="col-md-8 col-xs-9 remove-pad">
																	<form:input path="mobileNumber" id="mobile_number1" name="mobile_number1" type="text"  class="form-control"   required="required"
																	data-rule-maxlength="7" data-rule-minlength="7" data-msg-required="${enterMobile}" data-rule-number="true" data-msg-number="${valMobile}"
																	data-msg-maxlength="${valMobileLength}" 
																	data-msg-minlength="${valMobileLength}"/>
																</div>
			                                                </div>
		                                            	</div>
		                                            	
		                                        		<!-- /text box -->
		                                        	</div>
		                                        	<div class="col-md-6 remove-pad">
														<!-- text box -->
		                                            	<div class="form-group cf">
			                                                <div class="col-md-5">
			                                                    <label for="mobile_number2" class="form-lbl "><spring:message code="officePhoneLabel" />
			                                                    </label>
				                                            </div>
				                                            <div class="col-md-7">
																<div class="col-md-4 col-xs-3 remove-pad">
																	<div class="custom-select-box cf">
																		<form:select path="officeCode"  class="required1" name="countryofcitizen" >
																		<option value="050">050</option>
																			<option value="052">052</option>
																			<option value="055">055</option>
																			<option value="056">056</option>
																		</form:select>
																	</div> 
																</div>
																<div class="col-md-8 col-xs-9 remove-pad">
																	<form:input path="officePhone" id="mobile_number2" name="mobile_number2" type="text"  class="form-control required" 
																	data-rule-maxlength="7" data-rule-minlength="7" data-msg-required="${enterOfficeNum}" data-rule-number="true" data-msg-number="${valMobile}"
																	data-msg-maxlength="${valMobileLength}"
																	data-msg-minlength="${valMobileLength}"/>
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
																<label  for="email" class="form-lbl mandatory_lbl"><spring:message code="email" />
																</label>
															</div>
															<div class="col-md-7">
																<form:input path="emailAddress"  type="email" id="email" name="email" class="form-control" required="required" data-msg-required="${emailMsg}"/>
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
			                                                    <label for="address" class="form-lbl mandatory_lbl"> <spring:message code="label.address" />
			                                                    </label>
				                                            </div>
				                                            <div class="col-md-7">
																<form:input path="address" id="address" name="address" type="text" class="form-control" required="required" data-msg-required="${addressReq}"/>
			                                                </div>
		                                            	</div>
		                                        		<!-- /text box -->
		                                        	</div>
		                                        	<div class="col-md-6 remove-pad">
														<!-- text box -->
		                                            	<div class="form-group cf">
			                                                <div class="col-md-5">
			                                                    <label  for="website" class="form-lbl "><spring:message code="label.website" />
			                                                    </label>
				                                            </div>
				                                            <div class="col-md-7">
																<form:input path="website"  type="url" id="website" name="website" url="true" class="form-control"  data-msg-url="${enterWebsite}" />
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
			                                                    <label for="emirates" class="form-lbl mandatory_lbl"><spring:message code="chooseEmirates" /> 
			                                                    </label>
				                                            </div>
				                                            <div class="col-md-7">
			                                                    <div class="custom-select-box cf">
	                                                                <form:select path="emirates"  class="required1" name="countryofcitizen" required="required" data-msg-required="${emiratesReq}">
	                                                                   <form:option value="">---Select Emirate---</form:option>
																			<form:options items="${emirateList}" />
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
			                                                    <label for="tradelicenceNumber" class="form-lbl mandatory_lbl"><spring:message code="trade.licence.number" /> 
			                                                    </label>
				                                            </div>
				                                            <div class="col-md-7">
																<form:input path="tradeLicenseNumber" id="tradelicenceNumber" name="tradelicenceNumber" type="text" class="form-control" required="required" data-msg-required="${tradeLicNoReq}"/>
			                                                </div>
		                                            	</div>
		                                        		<!-- /text box -->
		                                        	</div>
		                                        	<div class="col-md-6 remove-pad">
														<!-- text box -->
		                                            	<div class="form-group cf">
			                                                <div class="col-md-5">
			                                                    <label  for="tradelicenceNumberexpdate" class="form-lbl mandatory_lbl"><spring:message code="tradeLicenExpiryDate" />   
			                                                    </label>
				                                            </div>
				                                            <div class="col-md-7">
																<form:input path="tradeLicenseExpDate"  type="date" id="tradelicenceNumberexpdate" name="tradelicenceNumberexpdate" class="form-control" required="required" data-msg-required="${tradeLicexp}" />
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
			                                                    <label for="tradelicenceType" class="form-lbl mandatory_lbl"> <spring:message code="tradeLicenseType" />
			                                                    </label>
				                                            </div>
				                                            <div class="col-md-7">
																<div class="custom-select-box cf">
																	<form:select path="tradeLicenseType"  class="required1" name="tradelicenceType" required="required" data-msg-required="${tradeLicType}">
																		  <form:option value="">---Select Trade License---</form:option>
																			<form:options items="${tradeLicList}" />
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
			                                                    <label for="tradelicenceattachment" class="form-lbl mandatory_lbl"><spring:message code="tradeLicense" />
			                                                    </label>
				                                            </div>
				                                            <div class="col-md-7">
																<div class="input-group file-upload">
																	<input type="text" class="form-control required" name ="tradelicenceattachment" readonly="readonly" data-msg-required="${tradeLicAttach}" />
																	<span class="input-group-btn">
																		<span class="btn btn-file">
																			<spring:message code="file.browse" />&hellip; 
																			<form:input path="tradeLicenseAttach" type="file"/>
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
														<!-- text box -->
		                                            	<div class="form-group cf">
			                                                <div class="col-md-5">
			                                                    <label for="signatoryattastation" class="form-lbl mandatory_lbl"><spring:message code="signatureAttest" /> 
			                                                    </label>
				                                            </div>
				                                            <div class="col-md-7">
																<div class="input-group file-upload">
																	<form:input path="" type="text" class="form-control required" name ="signatoryattastation" readonly="readonly" data-msg-required="${signatureAttest}"/>
																	<span class="input-group-btn">
																		<span class="btn btn-file">
																			<spring:message code="file.browse" />&hellip; 
																			<form:input path="signatoriesAttestation" type="file"/>
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
			                                                    <form:input path="userName" id="username" name="username" type="text" class="form-control" required="required"  data-msg-required="${userName}"/>
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
			                                                    <label for="password" class="form-lbl mandatory_lbl"><spring:message code="header.password" /> 
			                                                    </label>
				                                            </div>
				                                            <div class="col-md-7">
			                                                    <form:input path="password" id="password_register" name="password_register" type="password" class="form-control"  required="required" data-msg-required="${password}" 
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
																<form:input path="confirmPassword"  type="password" equalto="#password_register" id="confirm-password" name="confirm-password" class="form-control"  required="required"  data-msg-required="${passwordMisMatch}" />
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
				                                                    <input type="submit" class="btn" value='<spring:message code="form.button.submit" />'/> 
				                                                </div>
				                                            </div>
				                                        </div>
				                                        <!-- /submit button -->
		                                        	</div>
												</div>
	                                        </div>
										<%-- </form>	 --%>
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
		<!-- <script src="js/dest/app.js"></script> -->
		<script src="/js/libs/jquery.validate.js"></script>
	    <script>

			jQuery(function($) { 
				$("#feedbak").validate();
			});
			
	    </script>
		<!-- /script -->
	</body>
	<!-- /body -->
</html>