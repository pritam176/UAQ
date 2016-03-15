
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
								<li class="active"><a href="#"><spring:message code="establishmentReg" /> </a>
								</li>
								
							</ul>
						</div>
					</div>
				</div>
				<!--/mainmenu -->
				<!-- content area -->
				<div class="content">
				<c:set var="valMobile"><spring:message code="plzMobileNumber" /></c:set>
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
					<c:set var="passwordMisMatchmsg"><spring:message code = 'passwordMisMatch'/></c:set>
					<c:set var="enterWebsite"><spring:message code = 'plzWebsite'/></c:set>
					<c:set var ="confirmemailmsg"><spring:message code="confirm.email.failure"/></c:set>
					<c:set var="valLength"><spring:message code="validate.length" /></c:set>
					<c:set var="emaritesid"><spring:message code="enterEmirates" /></c:set>
					<c:set var ="filesize"><spring:message code="filesizemsg"/></c:set>
					<c:set var ="fileextention"><spring:message code="fileextentionmsg"/></c:set>
					<c:set var ="fileerror"><spring:message code="fileerrormsg"/></c:set>
					<c:set var ="plzEmiratesIdBackAttchmsg"><spring:message code="plzEmiratesIdBackAttch"/></c:set>	
					<c:set var ="plzEmiratedIDfrontAttchmsg"><spring:message code="plzEmiratedIDfrontAttch"/></c:set>
					<c:set var ="postboxmsg"><spring:message code="label.egd.postBox.required"/></c:set>
					<c:set var ="enterDigit"><spring:message code="feedback.enter.ten.digit"/></c:set>
					<c:set var ="acceptMessgaelabel"><spring:message code="acceptMessgae"/></c:set>
					<c:set var ="validemailMsg"><spring:message code="validemail"/></c:set>
					
					<c:set var ="usernamemax"><spring:message code="username.max"/></c:set>
					<c:set var ="usernamemin"><spring:message code="username.min"/></c:set>
					
					<c:set var ="alphmsg"><spring:message code="alph.msg"/></c:set>
					<c:set var ="passwordformatmsg"><spring:message code="password.format"/></c:set>
					
					
					
				
				
				
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
										   <form:form commandName="registrationEstablishmentCommand" name="feedbak" class="eservice-form" id="feedbak" enctype="multipart/form-data" method="post" action="establishmentregister.html" >
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
													<div class="col-md-5 remove-pad ">
														<!-- text box -->
		                                            	<div class="form-group cf">
			                                                <div class="col-md-5">
			                                                    <label for="establisname" class="form-lbl mandatory_lbl"><spring:message code="establishNameAsStated" />
			                                                    </label>
				                                            </div>
				                                            <div class="col-md-7"> 
			                                                    <form:input path="establishmentName" id="establisname" name="establisname" type="text" class="form-control required" maxlength="100" data-msg-required="${establishmentRequired}" />
			                                                </div>
		                                            	</div>
		                                        		<!-- /text box -->
		                                        	</div>
												</div>
	                                        </div>
	                                        <div class="row">
												<div class="col-md-12 remove-pad">
													<div class="col-md-5 remove-pad ">
														<!-- text box -->
														<div class="form-group cf">
			                                                <div class="col-md-5">
			                                                    <label for="mobile_number1" class="form-lbl mandatory_lbl"><spring:message code="mobileNum1" />
			                                                    </label>
				                                            </div>
				                                            <div class="col-md-7">
															    <div class="col-md-4 col-xs-3 remove-pad  arbic-code">
																	<div class="custom-select-box cf">
																		<form:select path="mobileCode"  class="required1" name="countryofcitizen" required="required" >
																			<form:option value="050">050</form:option>
																			<form:option value="052">052</form:option>
																			<form:option value="055">055</form:option>
																			<form:option value="056">056</form:option>
																		</form:select>
																	</div> 
																</div>
																<div class="col-md-8 col-xs-9 remove-pad">
																	<form:input path="mobileNumber" id="mobile_number1" name="mobile_number1" type="text"  class="form-control"   required="required"
																	data-rule-maxlength="7" data-rule-minlength="7" data-msg-required="${enterMobile}" data-rule-number="true" data-msg-number="${valMobile}"
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
			                                                    <label for="mobile_number2" class="form-lbl mandatory_lbl "><spring:message code="officePhoneLabel" />
			                                                    </label>
				                                            </div>
				                                            <div class="col-md-7">
																<div class="col-md-4 col-xs-3 remove-pad arbic-code">
																	<div class="custom-select-box cf">
																		<form:select path="officeCode"  class="required1" name="countryofcitizen" >
																			<form:option value="02">02</form:option>
																			<form:option value="04">04</form:option>
																			<form:option value="06">06</form:option>
																			<form:option value="07">07</form:option>
																			<form:option value="09">09</form:option>
																		</form:select>
																	</div> 
																</div>
																<div class="col-md-8 col-xs-9 remove-pad">
																	<form:input path="officePhone" id="mobile_number2" name="mobile_number2" type="text"  class="form-control required" 
																	data-rule-maxlength="7" data-rule-minlength="7" data-msg-required="${enterOfficeNum}" data-rule-number="true" data-msg-number="${valMobile}"
																	data-msg-maxlength="${valMobileLength}"
																	data-msg-minlength="${valMobileLength}" data-mask="0000000"/>
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
																<label  for="email" class="form-lbl mandatory_lbl"><spring:message code="email" />
																</label>
															</div>
															<div class="col-md-7">
																<form:input path="emailAddress"  type="email" id="email" name="email" class="form-control" required="required" data-msg-required="${emailMsg}" data-msg-email="${validemailMsg}"/>
															</div>
														</div>
													
														<!-- /text box -->
													</div>
													<div class="col-md-5 remove-pad col-md-offset-1 col-lg-offset-1">
														<!-- text box -->
		                                            	<div class="form-group cf">
															<div class="col-md-5">
																<label  for="email" class="form-lbl mandatory_lbl"><spring:message code="confirm.email"/>
																</label>
															</div>
															<div class="col-md-7">
																
																<form:input type="email" path="emailconfirm" id="emailconfirm" name="emailconfirm" class="form-control confirm-email"   data-msg-required="${emailMsg}" data-msg-email="${validemailMsg}" equalto="#email" data-msg-equalto="${confirmemailmsg}" />
			                                                </div>
														</div>
													
		                                        		<!-- /text box -->
		                                        	</div>
												</div>	
											</div>	
											
								<!--  emirates id added starts  -->
											
											<div class="row">
												<div class="col-md-12 remove-pad">
													<div class="col-md-5 remove-pad">
														<!-- text box -->
														<div class="form-group cf">
															<div class="col-md-5">
																<label  for="email" class="form-lbl mandatory_lbl"><spring:message code="emiratedId"/>
																</label>
															</div>
															<div class="col-md-7">
																<form:input id="emiratesId" path="emiratesId" type="text" class="form-control required"  data-rule-maxlength="18" data-rule-minlength="18"
															 data-msg-required="${emaritesid}"
																	data-msg-maxlength="${valLength}" 
																	data-msg-minlength="${valLength}" data-mask="000-0000-0000000-0" />
															</div>
														</div>
													
														<!-- /text box -->
													</div>
													
												</div>	
											</div>
								<!--  emirates id added ends here  -->
								
	                                        <div class="row">
												<div class="col-md-12 remove-pad">
													<div class="col-md-5 remove-pad">
														<!-- text box -->
		                                            	<div class="form-group cf">
			                                                <div class="col-md-5">
			                                                    <label for="address" class="form-lbl mandatory_lbl"> <spring:message code="label.address" />
			                                                    </label>
				                                            </div>
				                                            <div class="col-md-7">
																<form:input path="address" id="address" name="address" type="text" class="form-control " required="required" data-msg-required="${addressReq}" maxlength="100"
																/>
			                                                </div>
		                                            	</div>
		                                        		<!-- /text box -->
		                                        	</div>
		                                        	<div class="col-md-5 remove-pad col-md-offset-1 col-lg-offset-1">
														<!-- text box -->
		                                            	<div class="form-group cf">
			                                                <div class="col-md-5">
			                                                    <label  for="website" class="form-lbl "><spring:message code="label.website" />
			                                                    </label>
				                                            </div>
				                                            <div class="col-md-7">
																<form:input path="website"  type="text" id="website" name="website" website="true" class="form-control"  data-msg-website="${enterWebsite}" />
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
			                                                    <div class="custom-select-box cf">
	                                                                <form:select path="emirates"  class="required1" name="countryofcitizen" required="required" data-msg-required="${emiratesReq}">
	                                                                   <form:option value=""><spring:message code="selectEmirates"/></form:option>
																			<form:options items="${emirateList}" />
	                                                                </form:select>
	                                                            </div>
			                                                </div>
		                                            	</div>
		                                        		<!-- /text box -->
		                                        	</div>
													<!-- postbox number added -->
													<div class="col-md-5 remove-pad col-md-offset-1 col-lg-offset-1">
														<!-- text box -->
		                                            	<div class="form-group cf">
			                                                <div class="col-md-5">
			                                                    <label  for="post_box" class="form-lbl mandatory_lbl"><spring:message code="label.egd.postBox" /> </label>
				                                            </div>
				                                            <div class="col-md-7">
																<form:input  path="postbox" id="post_box" name="postbox"  class="form-control required" maxlength="10"  data-rule-number="true" data-msg-number="${enterDigit}" data-msg-required="${postboxmsg}" data-mask="0000000000"/>
			                                                </div>
		                                            	</div>
		                                        	</div>
													<!-- /postbox number added -->
												</div>
	                                        </div>
											<div class="row">
												<div class="col-md-12 remove-pad">
													<div class="col-md-5 remove-pad">
														<!-- text box -->
		                                            	<div class="form-group cf">
			                                                <div class="col-md-5">
			                                                    <label for="tradelicenceNumber" class="form-lbl mandatory_lbl"><spring:message code="trade.licence.number" /> 
			                                                    </label>
				                                            </div>
				                                            <div class="col-md-7">
																<form:input path="tradeLicenseNumber" id="tradelicenceNumber" name="tradelicenceNumber" type="text" class="form-control" required="required" maxlength="20" data-msg-required="${tradeLicNoReq}"/>
			                                                </div>
		                                            	</div>
		                                        		<!-- /text box -->
		                                        	</div>
		                                        	<div class="col-md-5 remove-pad col-md-offset-1 col-lg-offset-1">
														<!-- text box -->
		                                            	<div class="form-group cf">
			                                                <div class="col-md-5">
			                                                    <label  for="tradelicenceNumberexpdate" class="form-lbl mandatory_lbl"><spring:message code="tradeLicenExpiryDate" />   
			                                                    </label>
				                                            </div>
				                                            <div class="col-md-7">
																<form:input path="tradeLicenseExpDate"  type="text" id="tradelicenceNumberexpdate" name="tradelicenceNumberexpdate" class="form-control" required="required" data-msg-required="${tradeLicexp}" />
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
			                                                    <label for="tradelicenceType" class="form-lbl mandatory_lbl"> <spring:message code="tradeLicenseType" />
			                                                    </label>
				                                            </div>
				                                            <div class="col-md-7">
																<div class="custom-select-box cf">
																	<form:select path="tradeLicenseType"  class="required1" name="tradelicenceType" required="required" data-msg-required="${tradeLicType}">
																		  <form:option value=""><spring:message code="selecttradeLicenseType"/></form:option>
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
													<div class="col-md-5 remove-pad">
														<!-- text box -->
		                                            	<div class="form-group cf">
			                                                <div class="col-md-5">
			                                                    <label for="tradelicenceattachment" class="form-lbl mandatory_lbl"><spring:message code="tradeLicense" />
			                                                    </label>
				                                            </div>
				                                            <div class="col-md-7">
																<div class="input-group file-upload">
																	<form:input type="text" class="form-control required" path="tradelicenceattachment" name ="tradelicenceattachment" readonly="true" data-msg-required="${tradeLicAttach}" />
																	<span class="input-group-btn">
																		<span class="btn btn-file">
																			<spring:message code="file.browse" />&hellip; 
																			<input type="file" class="upload" name ="tradeLicenseAttach" accept="image/jpg, image/JPG, image/JPEG, image/jpeg,image/jpeg,image/gif,image/png, application/pdf,image/x-eps, application/msword" id ="tradeLicenseAttach" error-size="${filesize}" error-extention="${fileextention}" error-failed="${fileerror}"  />
																		</span>
																		<form:hidden path="tradeLicenseAttach_name" id="tradeLicenseAttach_name" value=""/>
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
													<div class="col-md-5 remove-pad">
														<!-- text box -->
		                                            	<div class="form-group cf">
			                                                <div class="col-md-5">
			                                                    <label for="signatoryattastation" class="form-lbl mandatory_lbl"><spring:message code="signatureAttest" /> 
			                                                    </label>
				                                            </div>
				                                            <div class="col-md-7">
																<div class="input-group file-upload">
																	<form:input type="text" class="form-control required" path = "signatoryattastation" name ="signatoryattastation" readonly="true" data-msg-required="${signatureAttest}"/>
																	<span class="input-group-btn">
																		<span class="btn btn-file">
																			<spring:message code="file.browse" />&hellip; 
																			<input type="file" class="upload" accept="image/jpg, image/JPG, image/JPEG, image/jpeg,image/jpeg,image/gif,image/png, application/pdf,image/x-eps, application/msword" name="signatoriesAttestation" id="signatoriesAttestation" error-size="${filesize}" error-extention="${fileextention}" error-failed="${fileerror}" />
																		</span>
																		<form:hidden path="signatoriesAttestation_name" id="signatoriesAttestation_name" value=""/>
																	</span>
																</div>
			                                                </div>
		                                            	</div>
		                                        		<!-- /text box -->
		                                        	</div>
												</div>
											</div>	
											
											
											<!-- Emirtae ID Attachemnt start here  -->
											
											
											
													<div class="row">
												<div class="col-md-12 remove-pad">
													<div class="col-md-5 remove-pad">
														<!-- text box -->
		                                            	<div class="form-group cf">
			                                                <div class="col-md-5">
			                                                    <label for="emiratesidfrontpage" class="form-lbl mandatory_lbl"><spring:message code="emiratedIdFrontAttch"/>
			                                                    </label>
				                                            </div>
				                                            <div class="col-md-7">
			                                                    <div class="input-group file-upload">
																	<form:input type="text" class="form-control required" readonly="true" path ="emiratesidfront" name ="emiratesidfrontpage" data-msg-required="${plzEmiratedIDfrontAttchmsg}" />
																	<span class="input-group-btn">
																		<span class="btn btn-file">
																			<spring:message code="file.browse"/>&hellip; 
																			<input type="file" class="upload"  name="emiratesIdFront" id="emiratesIdFront" accept="image/jpg, image/JPG,image/JPEG, image/jpeg,image/jpeg,image/gif,image/png, application/pdf,image/x-eps, application/msword" error-size="${filesize}" error-extention="${fileextention}" error-failed="${fileerror}" />
																		</span>
																	</span>
																	<form:hidden path="emiratesIdFront_name" id="emiratesIdFront_name" value=""/>
																</div>
			                                                </div>
		                                            	</div>
		                                        		<!-- /text box -->
		                                        	</div>
		                                        	<div class="col-md-5 remove-pad col-md-offset-1">
														<!-- text box -->
		                                            	<div class="form-group cf">
			                                                <div class="col-md-5">
			                                                    <label  for="emiratesidbackpage" class="form-lbl mandatory_lbl"><spring:message code="emiratedIdBackAttch"/>
			                                                    </label>
				                                            </div>
				                                            <div class="col-md-7">
																<div class="input-group file-upload">
																	<form:input type="text" class="form-control readonly required" readonly="true" path = "emiratesidback" name="emiratesidbackpage" id="emiratesid-back" data-msg-required="${plzEmiratesIdBackAttchmsg}" />
																	<span class="input-group-btn">
																		<span class="btn btn-file">
																			<spring:message code="file.browse"/>&hellip; 
																			<input type="file" class="upload" name="emiratesIdBackSide" id="emiratesIdBackSide"  accept="image/jpg, image/JPG,image/JPEG, image/jpeg,image/jpeg,image/gif,image/png, application/pdf,image/x-eps, application/msword" error-size="${filesize}" error-extention="${fileextention}" error-failed="${fileerror}" />
																		</span>
																	</span>
																	<form:hidden path="emiratesIdBackSide_name" id="emiratesIdBackSide_name" value=""/>
																</div>
			                                                </div>
		                                            	</div>
													
		                                        		<!-- /text box -->
		                                        	</div>
												</div>
	                                        </div>
											<!-- Emirate Id Attachemnt end here -->
											
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
			                                                    <form:input path="userName" id="username" name="username" type="text" class="form-control " required="required"  data-msg-required="${userName}"
			                                                     data-rule-alphacheck="true" data-msg-alphacheck="${alphmsg}"
			                                                    data-rule-maxlength="15"
																data-rule-minlength="4" 
																data-msg-maxlength="${usernamemax}" 
																data-msg-minlength="${usernamemin}"/>
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
			                                                    <label for="password" class="form-lbl mandatory_lbl"><spring:message code="header.password" /> 
			                                                    </label>
				                                            </div>
				                                            <div class="col-md-7">
			                                                    <form:input path="password" id="password_register" name="password_register" type="password" class="form-control"  required="required" data-msg-required="${password}" 
																data-rule-maxlength="15" 
																data-rule-minlength="8" 
																data-msg-maxlength="${passwordMax}"
																data-msg-minlength="${passwordMin}"
																data-rule-passwdweak="true" data-msg-passwdweak="${passwordformatmsg}"/>
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
																<form:input path="confirmPassword"  type="password" equalto="#password_register" id="confirm-password" name="confirm-password" class="form-control"  required="required" data-msg-equalto="${passwordMisMatchmsg}" data-msg-required="${passwordMisMatchmsg}" />
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
		                                        	<div class="col-md-5 remove-pad">
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
		<!-- <script src="js/dest/app.js"></script> -->
		<script src="/js/libs/jquery.validate.js"></script>
		<script type="text/javascript"src="/js/libs/jquery.mask.js"></script>
	    <script>

			jQuery(function($) { 
				jQuery.validator.addMethod("website", function(value, element, param) { return this.optional(element) || /^(www\.)(((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:)*@)?(((\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5]))|((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)*(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?)(:\d*)?)(\/((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)+(\/(([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)*)*)?)?(\?((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)|[\uE000-\uF8FF]|\/|\?)*)?(#((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)|\/|\?)*)?$/i.test(value);
				}, jQuery.validator.messages.website);
				
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


				$("#feedbak").validate({ignore: []});
				
				
				$('#tradelicenceNumberexpdate').datepicker({
					
					 minDate: 0
				});
			});
			$("#verification").focus(function(){
			
				$(".error-captcha").html("");
			});
			
			
	    </script>
		<!-- /script -->
	</body>
	<!-- /body -->
</html>