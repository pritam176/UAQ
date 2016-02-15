<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%-- <%@ page import="net.tanesha.recaptcha.ReCaptcha"%>
 <%@ page import="net.tanesha.recaptcha.ReCaptchaFactory"%> --%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

		<div class="container-fluid">
			<div class="wrapper">
				<!-- header -->
				
				<!-- /header -->
					<!--mainmenu -->
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
								<li class="active"><a href="#"><spring:message code="individualCitizenLbl" /></a>
								</li>
								
							</ul>
						</div>
					</div>
				</div>
				<!--/mainmenu -->
				<!-- content area -->
				<div class="content">
					<div class="row">
					
					
					<c:set var="valMobile"><spring:message code="plzMobileNumber" /></c:set>
					<c:set var="valMobileLength"><spring:message code="maxnum" /></c:set>
					<c:set var="valLength"><spring:message code="validate.length" /></c:set>
					<c:set var="userName"><spring:message code='plzUserName'/></c:set>
					<c:set var="password"><spring:message code='plzPassword'/></c:set>
					<c:set var="passwordMax"><spring:message code='passMax'/></c:set>
					<c:set var="passwordMin"><spring:message code = 'passMin'/></c:set>
					<c:set var="passwordMisMatch"><spring:message code = 'passwordMisMatch'/></c:set>
					<c:set var="emaritesid"><spring:message code="enterEmirates" /></c:set>
					<c:set var="dobmsg"><spring:message code="plzChooseDOB"/></c:set>
					<c:set var ="emirateExpDateMsg"><spring:message code="plzChooseEmirateIDExpDate"/></c:set>
					<c:set var ="emailMsg"><spring:message code="plzEmailAddress"/></c:set>
					<c:set var ="emiratesMsg"><spring:message code="plzChooseEmirates"/></c:set>
					<c:set var ="familyusernameMsg"><spring:message code="plzFullnameBook"/></c:set>
					<c:set var ="familyemirateMsg"><spring:message code="plzCooseEmirtesBook"/></c:set>
					<c:set var ="familytownNameMsg"><spring:message code="plzTownNameBook"/></c:set>
					<c:set var ="familytownNoMsg"><spring:message code="plzTownNumber"/></c:set>
					<c:set var ="familymemberMsg"><spring:message code="plzFamilyNumber"/></c:set>
					<c:set var ="familytribeMsg"><spring:message code="plzTribeName"/></c:set>
					<c:set var ="familyclanMsg"><spring:message code="plzClanNumber"/></c:set>
					<c:set var ="familyisuDateMsg"><spring:message code="plzChooseIssuedateBook"/></c:set>
					<c:set var ="familymotherNameMsg"><spring:message code="plzMotherName"/></c:set>
					<c:set var ="familymotherfatherNameMsg"><spring:message code="plzMotherFatherName"/></c:set>
					<c:set var ="familybookMsg"><spring:message code="plzAttchFamilyBook"/></c:set>
					<c:set var ="fullname"><spring:message code="fullName"/></c:set>
					<c:set var ="plzFullNameIndivmsg"><spring:message code="plzFullNameIndiv"/></c:set>	
					<c:set var ="plzPassportmsg"><spring:message code="plzPassportNum"/></c:set>
					
					
					<c:set var ="plzEmiratedIDfrontAttchmsg"><spring:message code="plzEmiratedIDfrontAttch"/></c:set>
					<c:set var ="plzEmiratesIdBackAttchmsg"><spring:message code="plzEmiratesIdBackAttch"/></c:set>	
					<c:set var ="plzAttchPassportFrontmsg"><spring:message code="plzAttchPassportFront"/></c:set>
					<c:set var ="plzAttchFamilyBookmsg"><spring:message code="plzAttchFamilyBook"/></c:set>
					<c:set var ="enterValidlandlinemsg"><spring:message code="enterValidlandline"/></c:set>	
					
					
					
				
					
					
					
					
					
						
						<!-- left col -->
						<div class="col-xs-12 col-sm-9 col-md-9 main-left-col">
							<!-- page title -->
							<div class="page-title-wrap">
								<div class="row">
									<div class="col-xs-12 col-sm-9 col-md-10 title-txt">
										<!-- page title -->
										<h2 class="page-title">
										<spring:message code="individualCitizenLbl" />
										</h2>
										<!-- /page title -->
									</div>
									<div class="hidden-xs show-sm col-md-2 title-icon">
										<img src="/img/icons/icon-uaq.png" alt="uaq">
									</div>
								</div>
							</div>
							<!-- /page title -->
							<!-- page content -->


							<div class="main-content-wrap">
		
								
								<div class="page-content-wrap">
										<div class="form-content cf">
										  <form:form method="post" commandName="registrationIndividualforUAEBean" 
											action="uaecitzenregister.html" 
											enctype="multipart/form-data"
											id="registration">
										    <div class="row">
												<div class="row"> 
													<div class="col-md-12 remove-pad">
													<div class="form-group cf">
													<label class="mandatory_lbl pull-right"><spring:message code="allfieldmandatorylbl"/></label> </div></div>
												</div>
											</div>	
											<div class="row">
												<div class="col-md-12">  <!-- form-sect-head-->
													<h5 class="form-title"><spring:message code="accountDetailsSplit"/></h5>
												</div> <!-- /form-sect-head-->
											</div>
											<div class="row">
												<div class="col-md-12 remove-pad">
													<div class="col-md-6 remove-pad">
														<!-- text box -->
		                                            	<div class="form-group cf">
			                                                <div class="col-md-5">
			                                                    <label for="first_name"  class="form-lbl mandatory_lbl"><spring:message code="countryCitizen"/></label>
				                                            </div>
				                                            <div class="col-md-7 form-grp-help">
			                                                    <div class="custom-select-box cf">
	                                                                <form:select path="countryCitizen" class="selectBox required1 form-grp-help required"   id="country">
																	
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
			                                                    <label for="countryResidency" class="form-lbl mandatory_lbl"><spring:message code="countryResidence" /></label>
				                                            </div>
				                                            <div class="col-md-7">
			                                                   <div class="custom-select-box cf">
															   <form:select path="countryResidency" class="required1 " name="countryofcitizen" required ="required" id="countryResidency">
																	 
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
			                                                    <label for="mobile_number1" class="form-lbl mandatory_lbl"><spring:message code="mobileNum"/>
			                                                    </label>
				                                            </div>
				                                            <div class="col-md-7">
															    <div class="col-md-4 col-xs-3 remove-pad">
																	<div class="custom-select-box cf">
																	 <form:select path="mobilecode1" class="required1" name="mobilecode1" required ="required" id="mobilecode1">
																		<option value="050">050</option>
																			<option value="052">052</option>
																			<option value="055">055</option>
																			<option value="056">056</option>
																		</form:select>
																	</div> 
																</div>
																<div class="col-md-8 col-xs-9 remove-pad">
																
																
																	
																	<form:input  path="mobile1" id="mobile_number1" name="mobile_number1" class="form-control required"   type="text" 
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
			                                                    <label for="mobile_number2" class="form-lbl "><spring:message code="mobileNum2"/>
			                                                    </label>
				                                            </div>
				                                            <div class="col-md-7">
																<div class="col-md-4 col-xs-3 remove-pad">
																	<div class="custom-select-box cf">
																			 <form:select path="mobilecode2" id="mobilecode2" class="required1" name="mobilecode2" >
																		<option value="050">050</option>
																			<option value="052">052</option>
																			<option value="055">055</option>
																			<option value="056">056</option>
																		</form:select>
																	</div> 
																</div>
																<div class="col-md-8 col-xs-9 remove-pad">
																
																
																
																<form:input  path="mobile2" id="mobile_number2" name="mobile_number2" type="text"  class="form-control" 
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
			                                                    <label for="landline" class="form-lbl "><spring:message code="landlineNum"/>
			                                                    </label>
				                                            </div>
				                                            <div class="col-md-7">
																 <div class="col-md-4 col-xs-3 remove-pad">
																	<div class="custom-select-box cf">
																		<form:select path="landLinecode" class="required1" name="landLinecode" id="landLinecode" >
																			<option value="02">02</option>
																			<option value="04">04</option>
																			<option value="06">06</option>
																			<option value="07">07</option>
																			<option value="09">09</option>
																		</form:select>
																		
																	</div> 
																</div>
																<div class="col-md-8 col-xs-9 remove-pad">
																		<form:input  path="landLine" id="landline" name="landline"  class="form-control" data-rule-number="true" data-msg-number="${enterValidlandlinemsg}"
																		 />
																</div>	
			                                                </div>
		                                            	</div>
		                                        		<!-- /text box -->
		                                        	</div>
		                                        	<div class="col-md-6 remove-pad">
														<!-- text box -->
		                                            	<div class="form-group cf">
			                                                <div class="col-md-5">
			                                                    <label  for="email" class="form-lbl mandatory_lbl"><spring:message code="email"/>
			                                                    </label>
				                                            </div>
				                                            <div class="col-md-7">
																
																<form:input type="email" path="emailAddress" id="email" name="email" class="form-control required"   data-msg-required="${emailMsg}" />
			                                                </div>
		                                            	</div>
													
		                                        		<!-- /text box -->
		                                        	</div>
												</div>
	                                        </div>
											<div class="row">
												<div class="col-md-12">  <!-- form-sect-head-->
													<h5 class="form-title"><spring:message code="credentialsSplit"/></h5>
												</div> <!-- /form-sect-head-->
											</div>	
											<div class="row">
												<div class="col-md-12 remove-pad">
													<div class="col-md-6 remove-pad">
														<!-- text box -->
		                                            	<div class="form-group cf">
			                                                <div class="col-md-5">
			                                                    <label for="username" class="form-lbl mandatory_lbl"><spring:message code="username"/>
			                                                    </label>
				                                            </div>
				                                            <div class="col-md-7"> 
			                                                  
																
																<form:input  path="username" id="username" name="username" class="form-control required"   data-msg-required="${userName}" />
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
			                                                    <label for="password" class="form-lbl mandatory_lbl"><spring:message code="password"/>
			                                                    </label>
				                                            </div>
				                                            <div class="col-md-7">
			                                                  
																
																<form:password  path="password" id="password_register" name="password_register" class="form-control required"   
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
			                                                    <label  for="confirm-password" class="form-lbl mandatory_lbl"><spring:message code="cpassword"/>
			                                                    </label>
				                                            </div>
				                                            <div class="col-md-7">
															<form:password  path="confirmPassword"  id="confirm-password" name="confirm-password"  equalto="#password_register" class="form-control required"  
															data-msg-required="${passwordMisMatch}"  />
																
			                                                </div>
		                                            	</div>
													
		                                        		<!-- /text box -->
		                                        	</div>
												</div>
	                                        </div>
											<div class="row">
												<div class="col-md-12">  <!-- form-sect-head-->
													<h5 class="form-title"><spring:message code="idinfo"/></h5>
												</div> 
											</div>	<!-- /form-sect-head-->
											<div class="row">
												<div class="col-md-12 remove-pad">
													<div class="col-md-6 remove-pad">
														<!-- text box -->
		                                            	<div class="form-group cf">
			                                                <div class="col-md-5">
			                                                    <label for="fullname" class="form-lbl mandatory_lbl"><spring:message code="fullName"/>
			                                                    </label><span class="form-lbl-subtxt"> <br/><spring:message code="fullNameHintText"/></span>
				                                            </div>
				                                            <div class="col-md-7 form-grp-help">
																<div class="custom-input">
																<form:input  path="fullname" id="fullname" name="fullname"  class="form-control"  required ="required"
																data-msg-required="${plzFullNameIndivmsg}" />
																</div><span class="form-help" id="popoverFullName">?</span>
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
			                                                    <label for="emiratesid" class="form-lbl mandatory_lbl"><spring:message code="emiratedId"/>
			                                                    </label>
				                                            </div>
				                                            
				                                           
				                                            
				                                            <div class="col-md-7 form-grp-help">
																<div class="custom-input">
															<form:input  path="emirateId" id="emaritesid" name="Emaritesid"  class="form-control required" data-rule-maxlength="18" data-rule-minlength="18"
															 data-msg-required="${emaritesid}"
																	data-msg-maxlength="${valLength}" 
																	data-msg-minlength="${valLength}" data-mask="000-0000-0000000-0" />
			                                                 </div><span class="form-help" id="popoverEmiratesId">?</span>
			                                                </div>
		                                            	</div>
		                                        		<!-- /text box -->
		                                        	</div>
		                                        	<div class="col-md-6 remove-pad">
														<!-- text box -->
		                                            	<div class="form-group cf">
			                                                <div class="col-md-5">
			                                                    <label  for="emiratesidexp" class="form-lbl mandatory_lbl"><spring:message code="emiratesIdExp"/>
			                                                    </label>
				                                            </div>
				                                            <div class="col-md-7 form-grp-help">
																<div class="custom-input">
																<form:input  path="emirateIdExpiryDate" type="date" id="emiratesidexp-password" name="emiratesidexp-password" class="form-control required"   
																data-msg-required="${emirateExpDateMsg}" />
																</div><span class="form-help" id="popoverEmiratesIdExp">?</span>
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
			                                                    <label for="emirates" class="form-lbl mandatory_lbl"><spring:message code="chooseEmirates"/>
			                                                    </label>
				                                            </div>
				                                            <div class="col-md-7">
			                                                    <div class="custom-select-box cf">
																	<form:select path="emirate" class="required1 required" name="emirate" id="emirate" data-msg-required="${emiratesMsg}">
																			<form:option value="">---Select Emirates---</form:option>
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
			                                                    <label for="dob" class="form-lbl mandatory_lbl"><spring:message code="dob"/>
			                                                    </label>
				                                            </div>
				                                            <div class="col-md-7 form-grp-help">
																<div class="custom-input">
			                                                    <form:input  path="dob" id="dob" name="dob" type="date" class="form-control required"   data-msg-required="${dobmsg}"/>
			                                                </div><span class="form-help" id="popoverdob">?</span>
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
			                                                    <label for="emiratesid-front" class="form-lbl mandatory_lbl"><spring:message code="emiratedIdFrontAttch"/>
			                                                    </label>
				                                            </div>
				                                            <div class="col-md-7">
			                                                    <div class="input-group file-upload">
																	<input type="text" class="form-control required" readonly="readonly" name ="emiratesid-front"  data-msg-required="${plzEmiratedIDfrontAttchmsg}" />
																	<span class="input-group-btn">
																		<span class="btn btn-file">
																			<spring:message code="file.browse"/>&hellip; 
																			<input type="file" name="emerateIdFront">
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
			                                                    <label  for="emiratesid-back" class="form-lbl mandatory_lbl"><spring:message code="emiratedIdBackAttch"/>
			                                                    </label>
				                                            </div>
				                                            <div class="col-md-7">
																<div class="input-group file-upload">
																	<input type="text" class="form-control required" readonly="readonly" name ="emiratesid-back" data-msg-required="${plzEmiratesIdBackAttchmsg}" />
																	<span class="input-group-btn">
																		<span class="btn btn-file">
																			<spring:message code="file.browse"/>&hellip; 
																			<input type="file" name="emerateIdBack" name="emerateIdBack">
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
												<div class="col-md-12"> <!-- form-sect-head-->
													<h5 class="form-title"><spring:message code="passportInformatioinSplit"/></h5>
												</div>
											</div><!-- /form-sect-head-->
											<div class="row">
												<div class="col-md-12 remove-pad">
													<div class="col-md-6 remove-pad">
														<!-- text box -->
		                                            	<div class="form-group cf">
			                                                <div class="col-md-5">
			                                                    <label for="passportnum" class="form-lbl mandatory_lbl"><spring:message code="passportNum"/>
			                                                    </label>
				                                            </div>
				                                            <div class="col-md-7">
																<form:input type="text" path="passportNumber" id="passportnum" name="passportnum"  class="form-control required" data-msg-required="${plzPassportmsg}"   />
			                                                   
			                                                </div>
		                                            	</div>
		                                        		<!-- /text box -->
		                                        	</div>
													<div class="col-md-6 remove-pad">
														<!-- text box -->
		                                            	<div class="form-group cf">
			                                                <div class="col-md-5">
			                                                    <label for="passportfrontpage" class="form-lbl mandatory_lbl"><spring:message code="passportFrontAttach"/>
			                                                    </label>
				                                            </div>
				                                            <div class="col-md-7">
																<div class="input-group file-upload">
																	<input type="text" class="form-control required" readonly="readonly" name ="passportfrontpage" data-msg-required="${plzAttchPassportFrontmsg}" />
																	<span class="input-group-btn">
																		<span class="btn btn-file">
																			<spring:message code="file.browse"/>&hellip; 
																			<input type="file" name="passportFront" name="passportFront">
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
												<div class="col-md-12"> <!-- form-sect-head-->
													<h5 class="form-title"><spring:message code="familyBookSplit"/></h5>
												</div> 
											</div>
											<div class="col-md-12 remove-pad">
														<div class="col-md-6 remove-pad">
															<!-- text box -->
															<div class="form-group cf">
																<div class="col-md-5">
																	<label for="familynum" class="form-lbl "> <spring:message code="hasFamilyBook"/></label>
																</div>
																<div class="col-md-7">
																	
																	<div class="checkbox inline-custom">
																		<form:checkbox path="hasFamilyBook" id="hasfamilybook"  name="check2" value="" checked="checked" />
																		<label for="hasfamilybook" class="custom"></label>
																	</div>
																</div>
															</div>
															<!-- /text box -->
														</div>
												</div>	
											<!--  <div class="row">
												<div class="col-md-12 remove-pad">
														<div class="col-md-6 remove-pad">
															
															<div class="form-group cf">
																<div class="col-md-5 checkbox">
																	<label for="familynum" >Has Family Book</label>
																</div>
																<div class="col-md-7 checkbox">
																	
																	<form:checkbox path="hasFamilyBook" style="display:block;" value="" />
																</div>
																
															</div>
															
														</div>
												</div>	
											</div> -->
											
											
											
											<!-- /form-sect-head-->
											<div class="row">
												<div class="col-md-12 remove-pad">
														<div class="col-md-6 remove-pad">
															<!-- text box -->
															<div class="form-group cf">
																<div class="col-md-5">
																	<label for="familybook_fullName" class="form-lbl mandatory_lbl"><spring:message code="fullNameBook"/></label><span class="form-lbl-subtxt"> <br/><spring:message code="hintBookFullname"/></span>
																</div>
																<div class="col-md-7">
																
																	<form:input type="text"	path="familybook_fullName" id="familybook_fullName" name="familybook_fullName"  class="form-control family-fld required"  data-msg-required="${familyusernameMsg}" />
																	
																</div>
															</div>
															<!-- /text box -->
														</div>
														<div class="col-md-6 remove-pad">
															<!-- text box -->
															<div class="form-group cf">
																<div class="col-md-5">
																	<label for="familayemirate" class="form-lbl mandatory_lbl"><spring:message code="chooseEmirates"/><span class="form-lbl-subtxt"> <br/><spring:message code="hintBookFullname"/></span>
																	</label>
																</div>
																<div class="col-md-7">
																	<div class="custom-select-box cf">
																	<form:select path="familybook_emirates" class="required1 family-fld required" data-msg-required="${familyemirateMsg}">
																	<form:option value="">---Select Emirates---</form:option>
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
																	<label for="townname" class="form-lbl mandatory_lbl"><spring:message code="townName"/></label>
																</div>
																<div class="col-md-7">
																<form:input type="text" path="townName" class="form-control family-fld required"  data-msg-required="${familytownNameMsg}"/>
																
																</div>
															</div>
															<!-- /text box -->
														</div>
														<div class="col-md-6 remove-pad">
															<!-- text box -->
															<div class="form-group cf">
																<div class="col-md-5">
																	<label for="townnum" class="form-lbl mandatory_lbl"><spring:message code="townNumber"/>
																	</label>
																</div>
																<div class="col-md-7">
																
																<form:input type="text" path="townNumber"  id="townnum" name="townnum"  class="form-control family-fld required" data-msg-required="${familytownNoMsg}" />
																	
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
																	<label for="familynum" class="form-lbl mandatory_lbl"><spring:message code="familyNumber"/></label>
																</div>
																<div class="col-md-7">
																<form:input type="text" path="familyNumber" id="familynum" name="familynum"  class="form-control family-fld required"  data-msg-required="${familymemberMsg}"/>
																
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
																	<label for="tribname" class="form-lbl mandatory_lbl"><spring:message code="tribeName"/></label>
																</div>
																<div class="col-md-7">
																	<form:input type="text" path="tribeName" id="tribname" name="tribname"  class="form-control family-fld required" data-msg-required="${familytribeMsg}" />
																	
																</div>
															</div>
															<!-- /text box -->
														</div>
														<div class="col-md-6 remove-pad">
															<!-- text box -->
															<div class="form-group cf">
																<div class="col-md-5">
																	<label for="clannum" class="form-lbl mandatory_lbl"><spring:message code="clanNumber"/>
																	</label>
																</div>
																<div class="col-md-7">
																	<form:input type="text" path="clanNumber" id="clannum" name="clannum"  class="form-control family-fld required" data-msg-required="${familyclanMsg}" />
																	
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
																	<label for="insurancedate" class="form-lbl mandatory_lbl"><spring:message code="issueDateBook"/></label>
																</div>
																<div class="col-md-7">
																	<form:input id="insurancedate" name="insurancedate" type="date"  path="issuanceDate" class="form-control family-fld required" data-msg-required="${familyisuDateMsg}" />
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
																	<label for="mothername" class="form-lbl mandatory_lbl"><spring:message code="motherNameTxt"/></label>
																</div>
																<div class="col-md-7">
																	<form:input type="text" path="motherName"  id="mothername" name="mothername"  class="form-control family-fld required" data-msg-required="${familymotherNameMsg}" />
																	
																</div>
															</div>
															<!-- /text box -->
														</div>
														<div class="col-md-6 remove-pad">
															<!-- text box -->
															<div class="form-group cf">
																<div class="col-md-5">
																	<label for="motherfather" class="form-lbl mandatory_lbl"><spring:message code="motherFatherNameTxt"/>
																	</label>
																</div>
																<div class="col-md-7">
															
																<form:input type="text" path="mothersFatherName" id="motherfather" name="motherfather"  class="form-control family-fld required" data-msg-required="${familymotherfatherNameMsg}" />	
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
																	<label for="familybook" class="form-lbl mandatory_lbl"><spring:message code="familyBookAttch"/></label>
																</div>
																<div class="col-md-7">
																	<div class="input-group file-upload">
																		<input type="text" class="form-control required family-fld" name ="familybook" id="familybook" readonly="readonly" data-msg-required="${familybookMsg}" />
																		
																		<span class="input-group-btn">
																			<span class="btn btn-file">
																				<spring:message code="file.browse"/>&hellip; 
																				<input type="file" name="familyBook" class="" id="family_book_browse" />
																			</span>
																		</span>
																		
																	</div>
																</div>
															</div>
															<!-- /text box -->
														</div>
												</div>	
											</div>
											<div class="col-md-12 remove-pad">
														<div class="col-md-6 remove-pad">
															<!-- text box -->
															<div class="form-group cf">
																<div class="col-md-5">
																	<label for="familynum" class="form-lbl "><spring:message code="subscribletter"/></label>
																</div>
																<div class="col-md-7">
																	
																	<div class="checkbox inline-custom">
																		<form:checkbox path="newsletter" id="newsletter"  name="check3" value=""  />
																		<label for="newsletter" class="custom"></label>
																	</div>
																</div>
															</div>
															<!-- /text box -->
														</div>
												</div>
											
											<%-- <div class="row">
												<div class="col-md-12 remove-pad">
														<div class="col-md-6 remove-pad">
															<!-- subscribe radio  box -->
															<div class="form-group cf">
																<div class="col-md-5 checkbox">
																	<label for="subscribeNewsLetter" >Subscribe to receive letter</label>
																</div>
																<div class="col-md-7 checkbox">
																	 <form:checkbox path="newsletter" style="display:block;" />
																</div>
																
															</div>
															<!-- /text box -->
														</div>
												</div>	
											</div> --%>
											
											
	                                        <div class="row">
												<div class="col-md-12 remove-pad">
		                                        	<div class="col-md-6 remove-pad">
														<!-- submit button -->
			                                        	<div class="row">
				                                            <div class="form-group submission">
				                                                <div class="col-md-offset-5 col-md-7">
																	
				                                                    <input type="submit" class="btn" value='<spring:message code="form.button.submit"/>' />
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
						
						<!-- /left col -->
						
						<!-- script -->
		<!-- <script src="/js/dest/app.js"></script> -->
		<script src="/js/libs/jquery.validate.js"></script>
		
		<script src="/js/libs/tooltip.js"></script>
		<script src="/js/libs/popover.js"></script>
		<script type="text/javascript"src="/js/libs/jquery.mask.js"></script>
	    <script>

			jQuery(function($) { 
				
				$("#hasfamilybook").click(function(){
					  //var chk= $('#hasfamilybook').val($(this).is(':checked'));
					  if (!$(this).is(':checked')) {
							
							$(".family-fld").removeClass("required");
							$(".family-fld").removeClass("error");
							$(".family-fld").attr('disabled', true);
							$("#family_book_browse").attr('disabled', true);
							$(".family-fld").next('.error').hide();
							$("#familybook").next('.error').hide();

						}else{
						
						$(".family-fld").addClass("required");
						$(".family-fld").removeClass("error");
						$(".family-fld").attr('disabled', false);	
						$("#family_book_browse").attr('disabled', false);
						$(".family-fld").next('.error').hide();
						$("#familybook").next('.error').hide();

						}	
					
					});

				$("#registration").validate();
			
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
			
			
			
			
			//var image = '<img src="images/image2.png"">';
			//$('#popover').popover({placement: 'right', content: image, html: true});
			
			});
			
	    </script>
				

