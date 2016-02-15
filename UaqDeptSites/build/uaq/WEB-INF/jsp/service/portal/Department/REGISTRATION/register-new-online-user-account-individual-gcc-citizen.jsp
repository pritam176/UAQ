<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

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
								<li class="active"><a href="#"><spring:message code="individualGccCitizen"/></a>
								</li>
								
							</ul>
						</div>
					</div>
				</div>
				<!--/mainmenu -->
				<!-- content area -->
				<div class="content">
				
					
				<c:set var="enterCountry"><spring:message code="feedback.form.field.required.country" /></c:set>
				<c:set var="valMobile"><spring:message code="plzMobileNumber" /></c:set>
				<c:set var ="emailMsg"><spring:message code="plzEmailAddress"/></c:set>
				<c:set var="userName"><spring:message code='plzUserName'/></c:set>
					<c:set var="password"><spring:message code='plzPassword'/></c:set>
					<c:set var="passwordMax"><spring:message code='passMax'/></c:set>
					<c:set var="passwordMin"><spring:message code = 'passMin'/></c:set>
					<c:set var="passwordMisMatchmsg"><spring:message code = 'passwordMisMatch'/></c:set>
					<c:set var="fullNamereq"><spring:message code = 'plzFullNameIndiv'/></c:set>
					<c:set var="enterEmirate"><spring:message code = 'enterEmirates'/></c:set>
					<c:set var ="emirateExpDateMsg"><spring:message code="plzChooseEmirateIDExpDate"/></c:set>
					<c:set var ="emiratesMsg"><spring:message code="plzChooseEmirates"/></c:set>
					<c:set var="dobmsg"><spring:message code="plzChooseDOB"/></c:set>
				<c:set var="emiratesFront"><spring:message code="plzEmiratesFront"/></c:set>
				<c:set var="emiratesBack"><spring:message code="plzEmiratesBack"/></c:set>
					<c:set var="enterPassportNo"><spring:message code = 'plzPassportNumber'/></c:set>
					<c:set var="uploadPassport"><spring:message code = 'plzUploadPassport'/></c:set>
					<c:set var="enterLandLine"><spring:message code = 'plzLandLine'/></c:set>
					<c:set var ="hintTxtPassPostNamemsg"><spring:message code="hintTxtPassPostName"/></c:set>
					
					<c:set var ="filesize"><spring:message code="filesizemsg"/></c:set>
					<c:set var ="fileextention"><spring:message code="fileextentionmsg"/></c:set>
					<c:set var ="fileerror"><spring:message code="fileerrormsg"/></c:set>
					
					<c:set var="addressReq"><spring:message code="plzAddress" /></c:set>
					<c:set var ="validemailMsg"><spring:message code="validemail"/></c:set>
					<c:set var ="confirmemailmsg"><spring:message code="confirm.email.failure"/></c:set>
					
					<c:set var ="acceptMessgaelabel"><spring:message code="acceptMessgae"/></c:set>
				
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
											<spring:message code="label.gccIndividual" />
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
										   <form:form commandName="registrationGccCitizenCommand" name="feedbak" id="feedbak" class="eservice-form" enctype="multipart/form-data" method="post" action="gcccitizenregister.html" >
										    <div class="row">
												<div class="row"> 
													<div class="col-md-12 remove-pad"><div class="form-group cf"><label class="mandatory_lbl pull-right"> <spring:message code="feedback.form.mandatory" /></label> </div></div>
												</div>
											</div>	
											<div class="row">
												<div class="col-md-12">  <!-- form-sect-head-->
													<h5 class="form-title"> <spring:message code="accountDetailsSplit" /></h5>
												</div> <!-- /form-sect-head-->
											</div>
											<div class="row">
												<div class="col-md-12 remove-pad">
													<div class="col-md-5 remove-pad">
														<!-- text box -->
		                                            	<div class="form-group cf">
			                                                <div class="col-md-5">
															 <label for="countryofcitizen" class="form-lbl mandatory_lbl"><spring:message code="countryCitizen" /></label>
			                                                    
				                                            </div>
				                                            <div class="col-md-7 form-grp-help">
			                                                    <div class="custom-select-box">
																 <form:select path="countryCitizenship"  class="required1 required" name="countryofcitizen" data-msg-required="${enterCountry}">
	                                                               
	                                                               
	                                                                     <form:option value="1"><spring:message code="UAE"/> </form:option>
	                                                               
																	<form:options items="${gccnationalList}" />
	                                                                </form:select>
	                                                            </div><span class="form-help" id="popoverCountryofResidence">?</span>
			                                                </div>
		                                            	</div>
		                                        		<!-- /text box -->
		                                        	</div>
		                                        	<div class="col-md-5 remove-pad col-md-offset-1 col-lg-offset-1">
														<!-- text box -->
		                                            	<div class="form-group cf">
			                                                <div class="col-md-5">
			                                                   <label for="countryResidence"  class="form-lbl mandatory_lbl"><spring:message code="countryResidence" /></label>
				                                            </div>
				                                            <div class="col-md-7">
			                                                   <div class="custom-select-box">
	                                                                <form:select path="countryResidence"  class="required1 required" name="countryResidence" data-msg-required="${enterCountry}" >
	                                                                   
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
													<div class="col-md-5 remove-pad">
														<!-- text box -->
														<div class="form-group cf">
			                                                <div class="col-md-5">
			                                                    <label for="mobile_number1" class="form-lbl mandatory_lbl"><spring:message code="mobileNum" />
			                                                    </label>
				                                            </div>
				                                            <div class="col-md-7">
															    <div class="col-md-4 col-xs-3 remove-pad  arbic-code">
																	<div class="custom-select-box">
																		<form:select path="mobileCode1"  class="required1 required" name="mobile_number1" >
																			<form:option value="050">050</form:option>
																			<form:option value="052">052</form:option>
																			<form:option value="055">055</form:option>
																			<form:option value="056">056</form:option>
																		</form:select>
																	</div> 
																</div>
																<div class="col-md-8 col-xs-9 remove-pad">
																	<form:input path="mobileNumber1" id="mobile_number1" name="mobile_number1" type="text"  class="form-control required"  data-rule-maxlength="7" data-rule-minlength="7" data-msg-required="${valMobile}" data-rule-number="true" data-msg-number="${valMobile}"
																	data-msg-maxlength="${valMobileLength}" 
																	data-msg-minlength="${valMobileLength}" data-mask="0000000"/>
																</div>
			                                                </div>
		                                            	</div>
		                                            	
		                                        		<!-- /text box -->
		                                        	</div>
		                                        	<div class="col-md-5 remove-pad col-md-offset-1 col-lg-offset-1">
														<!-- text box -->
		                                            	<div class="form-group cf">
			                                                <div class="col-md-5">
			                                                    <label for="mobile_number2" class="form-lbl "><spring:message code="mobileNum2" />
			                                                    </label>
				                                            </div>
				                                            <div class="col-md-7">
																<div class="col-md-4 col-xs-3 remove-pad  arbic-code">
																	<div class="custom-select-box">
																		<form:select path="mobileCode2"  class="required1" name="mobileCode2" >
																			<form:option value="050">050</form:option>
																			<form:option value="052">052</form:option>
																			<form:option value="055">055</form:option>
																			<form:option value="056">056</form:option>
																		</form:select>
																	</div> 
																</div>
																<div class="col-md-8 col-xs-9 remove-pad">
																	<form:input path="mobileNumber2" id="mobile_number2" name="mobile_number2" type="text"  class="form-control" data-rule-maxlength="7" data-rule-minlength="7" data-msg-required="${valMobile}" data-rule-number="true" data-msg-number="${valMobile}"
																	data-msg-maxlength="${valMobileLength}" 
																	data-msg-minlength="${valMobileLength}" data-mask="0000000" />
																</div>
			                                                </div>
		                                            	</div>
		                                        		<!-- /text box -->
		                                        	</div>
												</div>
	                                        </div>

	                                        <div class="row">
												<div class="col-md-12 remove-pad">
													<div class="col-md-5 remove-pad">
														<!-- text box -->
		                                            	<div class="form-group cf">
			                                                <div class="col-md-5">
			                                                    <label for="landline" class="form-lbl "><spring:message code="landlineNum" />
			                                                    </label>
				                                            </div>
				                                            <div class="col-md-7">
																 <div class="col-md-4 col-xs-3 remove-pad  arbic-code">
																	<div class="custom-select-box">
																		<form:select path="landLineCode"  class="required1" name="landLineCode"  >
																			<form:option value="02">02</form:option>
																			<form:option value="04">04</form:option>
																			<form:option value="06">06</form:option>
																			<form:option value="07">07</form:option>
																			<form:option value="09">09</form:option>
																		</form:select>
																	</div> 
																</div>
																<div class="col-md-8 col-xs-9 remove-pad">
																	<form:input path="landLineNumber"  id="landline" name="landline" type="text" class="form-control "  
																	data-rule-maxlength="7" data-rule-minlength="7" 
																	data-msg-maxlength="Enter 7 digit number" 
																	data-msg-minlength="Enter 7 digit number" data-rule-number="true" data-msg-number="${valMobileLength}" data-mask="0000000"/>
																</div>	
			                                                </div>
		                                            	</div>
		                                        		<!-- /text box -->
		                                        	</div>
		                                        	<div class="col-md-5 remove-pad col-md-offset-1 col-lg-offset-1">
														<!-- text box -->
		                                            	<div class="form-group cf">
			                                                <div class="col-md-5">
			                                                    <label for="address" class="form-lbl mandatory_lbl"> <spring:message code="label.address" />
			                                                    </label>
				                                            </div>
				                                            <div class="col-md-7">
																<form:input path="address" id="address" name="address" type="text" class="form-control" required="required" data-msg-required="${addressReq}" maxlength="100"/>
			                                                </div>
		                                            	</div>
													
		                                        		<!-- /text box -->
		                                        	</div>
												</div>
	                                        </div>
											
											<!-- ------------------------ Email address ---------------------------------------- -->
											<div class="row">
												<div class="col-md-12 remove-pad">
													<div class="col-md-5 remove-pad">
														<!-- text box -->
		                                            	<div class="form-group cf">
			                                                <div class="col-md-5">
			                                                    <label  for="email" class="form-lbl mandatory_lbl"><spring:message code="email"/>
			                                                    </label>
				                                            </div>
				                                            <div class="col-md-7">
																
																<form:input type="email" path="email" id="email" name="email" class="form-control required"   data-msg-required="${emailMsg}" data-msg-email="${validemailMsg}" />
			                                                </div>
		                                            	</div>
													
		                                        		<!-- /text box -->
		                                        	</div>
													<div class="col-md-5 remove-pad col-md-offset-1 col-lg-offset-1">
														<!-- text box -->
		                                            	<div class="form-group cf">
			                                                <div class="col-md-5">
			                                                    <label  for="emailconfirm" class="form-lbl mandatory_lbl"><spring:message code="confirm.email"/>
			                                                    </label>
				                                            </div>
				                                            <div class="col-md-7">
																
																<form:input type="email" path="emailconfirm" id="emailconfirm" name="emailconfirm" class="form-control"   data-msg-required="${emailMsg}" data-msg-email="${validemailMsg}" equalto="#email" data-msg-equalto="${confirmemailmsg}" />
			                                                </div>
		                                            	</div>
													
		                                        		<!-- /text box -->
		                                        	</div>
												</div>
	                                        </div>
											<!-- ------------------------------  /email  ends here ---------------------------------- -->
											<div class="row">
												<div class="col-md-12">  <!-- form-sect-head-->
													<h5 class="form-title"><spring:message code="credentialsSplit" /></h5>
												</div> <!-- /form-sect-head-->
											</div>	
											<div class="row">
												<div class="col-md-12 remove-pad">
													<div class="col-md-5 remove-pad">
														<!-- text box -->
		                                            	<div class="form-group cf">
			                                                <div class="col-md-5">
			                                                    <label for="username" class="form-lbl mandatory_lbl"><spring:message code="username" />
			                                                    </label>
				                                            </div>
				                                            <div class="col-md-7"> 
			                                                    <form:input path="userName" id="username" name="username" type="text" class="form-control required"   data-msg-required="${userName}" />
			                                                </div>
		                                            	</div>
		                                        		<!-- /text box -->
		                                        	</div>
												</div>
	                                        </div>
											<div class="row">
												<div class="col-md-12 remove-pad">
													<div class="col-md-5 remove-pad">
														<!-- text box -->
		                                            	<div class="form-group cf">
			                                                <div class="col-md-5">
			                                                    <label for="password_register" class="form-lbl mandatory_lbl"><spring:message code="password" />
			                                                    </label>
				                                            </div>
				                                            <div class="col-md-7">
			                                                    <form:input path="password" id="password_register" name="password_register" type="password" class="form-control required" 
			                                                    
																data-msg-required="${password}" 
																data-rule-maxlength="15" 
																data-rule-minlength="8" 
																data-msg-maxlength="${passwordMax}" 
																data-msg-minlength="${passwordMin}" />
			                                                </div>
		                                            	</div>
		                                        		<!-- /text box -->
		                                        	</div>
		                                        	<div class="col-md-5 remove-pad col-md-offset-1 col-lg-offset-1">
														<!-- text box -->
		                                            	<div class="form-group cf">
			                                                <div class="col-md-5">
			                                                    <label  for="confirm-password" class="form-lbl mandatory_lbl"><spring:message code="cpassword" />
			                                                    </label>
				                                            </div>
				                                            <div class="col-md-7">
																<form:input path="confirmPassword"  type="password" id="confirm-password" equalto="#password_register" name="confirm-password" class="form-control required"  data-msg-equalto="${passwordMisMatchmsg}" data-msg-required="${passwordMisMatchmsg}"/>
			                                                </div>
		                                            	</div>
													
		                                        		<!-- /text box -->
		                                        	</div>
												</div>
	                                        </div>
											<div class="row">
												<div class="col-md-12">  <!-- form-sect-head-->
													<h5 class="form-title"><spring:message code="idinfo" /> </h5>
												</div> 
											</div>	<!-- /form-sect-head-->
											<div class="row">
												<div class="col-md-12 remove-pad">
													<div class="col-md-5 remove-pad">
														<!-- text box -->
		                                            	<div class="form-group cf">
			                                                <div class="col-md-5">
			                                                    <label for="fullNameEmirates" class="form-lbl mandatory_lbl"><spring:message code="fullNameBook" /> 
			                                                    </label><span class="form-lbl-subtxt"> <br/> <spring:message code="fullNameHintText"/> </span>
				                                            </div>
				                                            <div class="col-md-7 form-grp-help">
																<div class="custom-input">	 <!-- added for help poover  -->
																	<form:input path="fullNameEmirates" id="fullNameEmirates" name="fullNameEmirates" type="text" class="form-control col-md-11 charecterKey required"  data-msg-required="${fullNamereq}"/> <!-- <span class="form-help" id="popover2">?</span>-->
																</div><span class="form-help" id="popoverFullName">?</span> <!-- added for help poover  -->
														   </div>
		                                            	</div>
		                                        		<!-- /text box -->
		                                        	</div>
												</div>
	                                        </div>
											<div class="row">
												<div class="col-md-12 remove-pad">
													<div class="col-md-5 remove-pad">
														<!-- text box -->
		                                            	<div class="form-group cf">
			                                                <div class="col-md-5">
			                                                    <label for="emiratesid" class="form-lbl mandatory_lbl"><spring:message code="emiratedId" />
			                                                    </label>
				                                            </div>
				                                            <div class="col-md-7">
			                                                    <form:input path="emiratesId" id="emaritesid" name="emiratesid" type="text" class="form-control required"  data-rule-maxlength="18" data-rule-minlength="18"
															 data-msg-required="${enterEmirate}"
																	data-msg-maxlength="${valLength}" 
																	data-msg-minlength="${valLength}" data-mask="000-0000-0000000-0" />
			                                                </div>
		                                            	</div>
		                                        		<!-- /text box -->
		                                        	</div>
		                                        	<div class="col-md-5 remove-pad col-md-offset-1 col-lg-offset-1">
														<!-- text box -->
		                                            	<div class="form-group cf">
			                                                <div class="col-md-5">
			                                                    <label  for="emiratesidexp" class="form-lbl mandatory_lbl"><spring:message code="emiratesIdExp" /> 
			                                                    </label>
				                                            </div>
				                                            <div class="col-md-7">
																<form:input path="emiratesIdExpDate"  type="text" id="emiratesidexp-password" name="emiratesidexp-password" class="form-control required"  data-msg-required="${emirateExpDateMsg}"/>
			                                                </div>
		                                            	</div>
													
		                                        		<!-- /text box -->
		                                        	</div>
												</div>
	                                        </div>
											<div class="row">
												<div class="col-md-12 remove-pad">
													<div class="col-md-5 remove-pad">
														<!-- text box -->
		                                            	<div class="form-group cf">
			                                                <div class="col-md-5">
			                                                    <label for="emirates" class="form-lbl mandatory_lbl"><spring:message code="chooseEmirates" /> 
			                                                    </label>
				                                            </div>
				                                            <div class="col-md-7">
			                                                    <div class="custom-select-box">
	                                                                <form:select path="emirates"  class="required1 required" name="emirates"  data-msg-required="${emiratesMsg}" >
	                                                                   <form:option value=""><spring:message code="selectEmirates"/></form:option>
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
													<div class="col-md-5 remove-pad">
														<!-- text box -->
		                                            	<div class="form-group cf">
			                                                <div class="col-md-5">
			                                                    <label for="dob" class="form-lbl mandatory_lbl"><spring:message code="dob" />
			                                                    </label>
				                                            </div>
				                                            <div class="col-md-7">
			                                                    <form:input path="dob" id="dob" name="dob" type="text" class="form-control required"  data-msg-required="${dobmsg}" />
			                                                </div>
		                                            	</div>
		                                        		<!-- /text box -->
		                                        	</div>
												</div>
	                                        </div>
											<div class="row">
												<div class="col-md-12 remove-pad">
													<div class="col-md-5 remove-pad">
														<!-- text box -->
		                                            	<div class="form-group cf">
			                                                <div class="col-md-5">
			                                                    <label for="emiratesidfront" class="form-lbl mandatory_lbl"><spring:message code="emiratedIdFrontAttch" />
			                                                    </label>
				                                            </div>
				                                            <div class="col-md-7">
			                                                    <div class="input-group file-upload">
																	<form:input type="text" class="form-control required " readonly="true" path="emiratesidfront" name ="emiratesidfront"  data-msg-required="${emiratesFront}" />
																	<span class="input-group-btn">
																		<span class="btn btn-file">
																			<spring:message code="file.browse" />&hellip; 
																			<input type="file" class="upload"  name="emiratesIdFrontside"  accept="image/jpg, image/JPG, image/JPEG, image/jpeg,image/jpeg,image/gif,image/png, application/pdf,image/x-eps, application/msword" id="emiratesIdFrontside" error-size="${filesize}" error-extention="${fileextention}" error-failed="${fileerror}" />
																		</span>
																		<form:hidden path="emiratesIdFrontside_name" id="emiratesIdFrontside_name" value=""/>
																	</span>
																</div>
			                                                </div>
		                                            	</div>
		                                        		<!-- /text box -->
		                                        	</div>
		                                        	<div class="col-md-5 remove-pad col-md-offset-1 col-lg-offset-1">
														<!-- text box -->
		                                            	<div class="form-group cf">
			                                                <div class="col-md-5">
			                                                    <label  for="emiratesidback" class="form-lbl mandatory_lbl"><spring:message code="emiratedIdBackAttch" />
			                                                    </label>
				                                            </div>
				                                            <div class="col-md-7">
																<div class="input-group file-upload">
																	<form:input type="text" class="form-control required " path="emiratesidback" readonly="true" name="emiratesidback"  data-msg-required="${emiratesBack}" />
																	<span class="input-group-btn">
																		<span class="btn btn-file">
																			<spring:message code="file.browse" />&hellip; 
																			<input type="file" class="upload"  name="emiratesIdBackSide" id="emiratesIdBackSide" accept="image/jpg, image/JPG, image/JPEG, image/jpeg,image/jpeg,image/gif,image/png, application/pdf,image/x-eps, application/msword" error-size="${filesize}" error-extention="${fileextention}" error-failed="${fileerror}"/>
																		</span>
																		<form:hidden path="emiratesIdBackSide_name" id="emiratesIdBackSide_name" value=""/>
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
													<h5 class="form-title"><spring:message code="passportInformatioinSplit" /></h5>
												</div>
											</div><!-- /form-sect-head-->
											<div class="row">
												<div class="col-md-12 remove-pad">
													<div class="col-md-5 remove-pad">
														<!-- text box -->
		                                            	<div class="form-group cf">
			                                                <div class="col-md-5">
			                                                    <label for="fullName" class="form-lbl mandatory_lbl"><spring:message code="fullNameBook" /> 
			                                                    </label><span class="form-lbl-subtxt"> <br/><spring:message code="hintTxtPassPostName"/></span>
				                                            </div>
				                                            <div class="col-md-7">
			                                                    <form:input path="fullName" id="fullName" name="fullName" type="text" class="form-control charecterKey required"   data-msg-required="${fullNamereq}"/>
			                                                </div>
		                                            	</div>
		                                        		<!-- /text box -->
		                                        	</div>
												</div>
	                                        </div>
	
											<div class="row">
												<div class="col-md-12 remove-pad">
													<div class="col-md-5 remove-pad">
														<!-- text box -->
		                                            	<div class="form-group cf">
			                                                <div class="col-md-5">
			                                                    <label for="passportnum" class="form-lbl mandatory_lbl"><spring:message code="passportNum" />
			                                                    </label>
				                                            </div>
				                                            <div class="col-md-7">
			                                                    <form:input path="passportNumber" id="passportnum" name="passportnum" type="text" class="form-control required"   data-msg-required="${enterPassportNo}"/>
			                                                </div>
		                                            	</div>
		                                        		<!-- /text box -->
		                                        	</div>
													<div class="col-md-5 remove-pad col-md-offset-1 col-lg-offset-1">
														<!-- text box -->
		                                            	<div class="form-group cf">
			                                                <div class="col-md-5">
			                                                    <label for="passportfrontpage" class="form-lbl mandatory_lbl"><spring:message code="passportFrontAttach" />
			                                                    </label>
				                                            </div>
				                                            <div class="col-md-7">
																<div class="input-group file-upload">
																	<form:input type="text" class="form-control readonly required" readonly="true" path ="passportfrontpage" name ="passportfrontpage"  data-msg-required="${uploadPassport}"/>
																	<span class="input-group-btn">
																		<span class="btn btn-file">
																			<spring:message code="file.browse" />&hellip; 
																			<input type="file" class="upload"  name="passportFrontPage" id="passportFrontPage" accept="image/jpg, image/JPG, image/JPEG, image/jpeg,image/jpeg,image/gif,image/png, application/pdf,image/x-eps, application/msword" error-size="${filesize}" error-extention="${fileextention}" error-failed="${fileerror}" />
																		</span>
																		<form:hidden path="passportFrontPage_name" id="passportFrontPage_name" value=""/>
																	</span>
																</div>
			                                                </div>
		                                            	</div>
		                                        		<!-- /text box -->
		                                        	</div>
												</div>
	                                        </div>
	                                       <!-- captcha part Starts here -->
											<div class="row">
												<div class="col-md-12 remove-pad">
													<div class="col-md-5 remove-pad">
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
											<!-- /captcha part Starts here -->
												<!-- check newsletter -->
											<div class="row">
												<div class="col-md-12 remove-pad">
															<div class="col-md-5 remove-pad">
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
											</div>
											<!-- /check newsletter -->
												<!-- Terms and conditions -->
											<div class="row">
												<div class="col-md-12 remove-pad">
															<div class="col-md-5 remove-pad">
																<!-- text box -->
																<div class="form-group cf">
																	<div class="col-md-5">
																		<label for="termsandcondition" class="form-lbl "><spring:message code="acceptTerans"/></label>
																	</div>
																	<div class="col-md-7">
																		
																		<div class="checkbox inline-custom">
																				<div class="terms-link"> <a href="javascript:void(0)" data-toggle="modal" data-target="#termsModal" ><spring:message code="termsAndCondition"/></a></div>
																			<input type="checkbox" name="termsandcondition" id="termsandcondition1"  name="terms" value="" class="required" data-msg-required="${acceptMessgaelabel}" />
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
		                                        	<div class="col-md-5 remove-pad">
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
		<!-- script -->
		<!-- <script src="/js/dest/app.js"></script> -->
		<script src="/js/libs/jquery.validate.js"></script>
		<script src="/js/libs/jquery.mytooltip.js"></script>
		<script type="text/javascript"src="/js/libs/jquery.mask.js"></script>
				<style>
					.in { display:table !important;}
				</style>
	    <script>
				
			jQuery(function($) { 

			$("#feedbak").validate({ignore: []});
			
			
			
			$('#emiratesidexp-password').datepicker({
					
					 minDate: 0
				});
				$('#dob').datepicker({
					
					 maxDate: 0,
					 yearRange: '1900:2050'
				});

				
				var imageContryofResidence = '<img src="/img/help/1.jpg">';
				var imageFullName = '<img src="/img/help/2.jpg">';
				var imageEmiratesId = '<img src="/img/help/3.jpg">';
				var imageEmiratesIdExp = '<img src="/img/help/4.jpg">';
				var imageDob = '<img src="/img/help/5.jpg">';
				//alert('${param.languageCode}');
				if('${param.languageCode}'=="ar"){
					position1="left";
				}else{
					position1="right";
				}
				
				/* tool tip */
				  $(document).ready(function() {
						 $('#popoverFullName').myTooltip({
						   'content': imageFullName,
						   'direction':position1
						  });
						  $('#popoverCountryofResidence').myTooltip({
						   'content': imageContryofResidence,
						   'direction':position1
						  });
						  
						 /* $('#popoverEmiratesId').myTooltip({
						   'content': imageEmiratesId,
						   'direction':position1
						  });
						  	  
						$('#popoverEmiratesIdExp').myTooltip({
						   'content': imageEmiratesIdExp,
						   'direction':position1
						  });	
						  
						$('#popoverdob').myTooltip({
						   'content': imageDob,
						   'direction':position1
						  }); */
						  
					});
				  /* tool tip ends here */
			
				$("#verification").focus(function(){
			
					$(".error-captcha").html("");
				});
			
			});
			
	    </script>
		<!-- /script -->
	</body>
	<!-- /body -->
</html>