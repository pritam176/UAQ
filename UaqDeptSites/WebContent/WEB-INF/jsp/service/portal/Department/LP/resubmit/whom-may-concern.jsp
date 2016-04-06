<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
		<div class="container-fluid">
			<div class="wrapper">
					<!--mainmenu -->
				<div class="mainmenu">
					<div class="col-md-12 hidden-xs hidden-sm">
						<div class="mainmenu-wrap">
							<ul class="no-list cf">
								<li>
									<a href="/${param.languageCode}/home.html"><img src="/img/home1.png" alt="Home UAQ">
									</a>
								</li>
								<li class=""><a href="/${param.languageCode}/myrequest.html"><spring:message code="label.myrequest" /></a>
								</li>
								
								<li class="active"><a href="#"> <spring:message code="label.lp.toWhomCert" /></a>
								</li>
								
							</ul>
						</div>
					</div>
				</div>
				<!--/mainmenu -->
				<!-- content area -->
				<div class="content">
				<c:set var="addressToReq"><spring:message code="label.lp.addressTo.required" /></c:set>
				<c:set var="familyBookReq"><spring:message code="label.lp.familyBookNo.required" /></c:set>
				<c:set var="spouseIdReq"><spring:message code="label.spouseIdNo.required" /></c:set>
				<c:set var="familyBookReq"><spring:message code="label.lp.familyBookScan.required" /></c:set>
				<c:set var="spouseEmiratesReq"><spring:message code="label.ps.spouse.emirates.attach.required" /></c:set>
				<c:set var="valLength"><spring:message code="validate.length" /></c:set>
				
				<c:set var ="filesize"><spring:message code="filesizemsg"/></c:set>
								<c:set var ="fileextention"><spring:message code="fileextentionmsg"/></c:set>
								<c:set var ="fileerror"><spring:message code="fileerrormsg"/></c:set>
				
					<div class="row">
						<!-- right col -->
						<div class="col-xs-12 col-sm-3 col-md-3 main-right-col">
							<!-- social media share callout -->
					
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
										<spring:message code="label.lp.toWhomCert" />
										
										<spring:message code="label.resubmit"/>
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
										   <form:form commandName="whomItmayConcernCommand" name="feedbak" id="feedbak" enctype="multipart/form-data" method="POST" action="/${param.languageCode}/resubmitwhomitmayconcern.html" >
										    <div class="row"></div>
											<form:hidden path="requestNo" id="requestNo" value=""/>
											<form:hidden path="towhomeReqId" id="towhomeReqId" value=""/>
											<form:hidden path="statusId" id="statusId" value=""/>
											
												
											<div class="row">
												<div class="col-md-12 remove-pad">
													<div class="col-md-6 remove-pad">
														<!-- text box -->
		                                            	<div class="form-group cf">
			                                                <div class="col-md-5">
			                                                    <label for="pro-name" class="form-lbl mandatory_lbl"><spring:message code="label.lp.addressTo" />
			                                                    </label>
				                                            </div>
				                                            <div class="col-md-7">
			                                                    <div class="custom-select-box cf">
	                                                                <form:select path="addressTo" class="required1 required"  name="addressto" id="addressto" data-msg-required="${addressToReq}">
	                                                                    <option value="">
																			<spring:message code="option.select" />
																		</option>
																		<form:options items="${addressedTOMap}" />
	                                                                </form:select>
	                                                            </div>
			                                                </div>
		                                            	</div>
		                                        		<!-- /text box -->
		                                        	</div>
													<div class="col-md-6 remove-pad addressto-other" style="display:none;">
														<!-- text box -->
		                                            	<div class="col-md-7">
																<form:input path="other" id="familybooknumaddress" name="familybooknumaddress" type="text" class="form-control required" maxlength="50" />
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
			                                                    <label for="familybooknum" class="form-lbl mandatory_lbl_sh"><spring:message code="label.lp.familyBookNo" />
			                                                    </label>
				                                            </div>
				                                            <div class="col-md-7">
																<form:input path="familyBookNo" id="familybooknum" name="familybooknum" type="text" class="form-control required other-form" data-msg-required="${familyBookReq}" maxlength="20"/>
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
			                                                    <label for="spouseid" class="form-lbl mandatory_lbl_sh"><spring:message code="label.spouseIdNo" />
			                                                    </label>
				                                            </div>
				                                            <div class="col-md-7">
																<form:input path="spouseIdNo" id="spouseid" name="spouseid" type="text" class="form-control required other-form"  data-msg-required="${spouseIdReq}" data-msg-minlength="${valLength}" data-rule-minlength="18" data-mask="000-0000-0000000-0" />
			                                                </div>
		                                            	</div>
		                                        		<!-- /text box -->
		                                        	</div>
												</div>
	                                        </div>		

											<div class="row">
												<div class="col-md-12">  <!-- form-sect-head-->
													<h5 class="form-title"><spring:message code="label.ps.attachment" /></h5>
												</div> 
											</div>	<!-- /form-sect-head-->

											<div class="row">
												<div class="col-md-12 remove-pad">
													<div class="col-md-6 remove-pad">
														<!-- text box -->
		                                            	<div class="form-group cf">
			                                                <div class="col-md-5">
			                                                    <label for="police-report" class="form-lbl mandatory_lbl_sh"><spring:message code="label.lp.familyBookScan" /> <span class="form-lbl-subtxt"><spring:message code="label.lp.addressToSheikPgm" />  </span>
			                                                    </label>
				                                            </div>
				                                            <div class="col-md-7">
			                                                    <div class="input-group file-upload">
																	<input type="text" class="form-control required other-form-file" name ="police-report" data-msg-required="${familyBookReq}" readonly="readonly" />
																	<span class="input-group-btn">
																		<span class="btn btn-file">
																			<spring:message code="file.browse" />&hellip; 
																			<input name="scanFamilyBook" id="scanFamilyBook" type="file" data-msg-required="${tradingLicReq}" accept="image/jpg, image/JPG, image/JPEG, image/jpeg,image/jpeg,image/gif,image/png, application/pdf,image/x-eps, application/msword" error-size="${filesize}" error-extention="${fileextention}" error-failed="${fileerror}" />
																		</span>
																		<form:hidden path="scanFamilyBook_name" id="scanFamilyBook_name" value=""/>
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
			                                                    <label  for="emiratesid-back" class="form-lbl mandatory_lbl_sh"><spring:message code="label.lp.spouseEmiratesIdAttach" /><span class="form-lbl-subtxt"><spring:message code="label.lp.directedToSheikPgm" /></span>
			                                                    </label>
				                                            </div>
				                                            <div class="col-md-7">
																<div class="input-group file-upload">
																	<input type="text" class="form-control required other-form-file" name="emiratesid-back" data-msg-required="${spouseEmiratesReq}" readonly="readonly" />
																	<span class="input-group-btn">
																		<span class="btn btn-file">
																			<spring:message code="file.browse" />&hellip; 
																			<input name="sposeEmiratesId" type="file" data-msg-required="${tradingLicReq}" id="sposeEmiratesId" accept="image/jpg, image/JPG, image/JPEG, image/jpeg,image/jpeg,image/gif,image/png, application/pdf,image/x-eps, application/msword" error-size="${filesize}" error-extention="${fileextention}" error-failed="${fileerror}"  />
																		</span>
																		<form:hidden path="sposeEmiratesId_name" id="sposeEmiratesId_name" value=""/>
																	</span>
																</div>
			                                                </div>
		                                            	</div>
													
		                                        		<!-- /text box -->
		                                        	</div>	
												</div>
	                                        </div>
	                                       <!-- ----- uploaded file area starts here --->
											<div class="row ">
													<div class="col-md-12 remove-pad">
														<div class="col-md-6 remove-pad">
															<!-- text box -->
															<div class="form-group cf">
																<div class="col-md-5">
																	<label class="form-lbl "> <spring:message code="label.service.uploadedfiles" /></label>
																</div>
																<div class="col-md-7">
																
																	<c:forEach  items="${attachmentPayLoad}" var="attachments">
																	<c:if test="${attachments.attachmentType ne '6' }">
																	
																	<c:if test="${param.languageCode eq 'en' }">
																	<div class="col-md-12 remove-pad attachment-btm">
																	<a href="${attachments.viewURL}" target="_blank"> ${attachments.attachmentType_EN}</a>
																	</div>
																	</c:if>
																	<c:if test="${param.languageCode eq 'ar' }">
																	<div class="col-md-12 remove-pad attachment-btm">
																	<a href="${attachments.viewURL}" target="_blank"> ${attachments.attachmentType_AR}</a>
																	</div>
																	</c:if>
																	
																	</c:if>
																	</c:forEach>
																</div>
															</div>
															<!-- /text box -->
														</div>
													</div>
											</div>

											<!-- ----- uploaded file area ends   here --->
											
											<!-- -----Back end uploaded file area ends   here --->
											<div class="row ">
													<div class="col-md-12 remove-pad">
														<div class="col-md-6 remove-pad">
															<!-- text box -->
															<div class="form-group cf">
																<div class="col-md-5">
																	<label class="form-lbl "> <spring:message code="label.service.reviwer.uploadedfiles" /> </label>
																</div>
																<div class="col-md-7">
																	<c:forEach items="${attachmentPayLoad}" var="attachments">
																	
																	<c:if test="${attachments.attachmentType eq '6' }">
																	
																	<c:if test="${param.languageCode eq 'en' }">
																	<div class="col-md-12 remove-pad attachment-btm">
																	<a href="${attachments.viewURL}" target="_blank"> ${attachments.attachmentType_EN}</a>
																	</div>
																	</c:if>
																	<c:if test="${param.languageCode eq 'ar' }">
																	<div class="col-md-12 remove-pad attachment-btm">
																	<a href="${attachments.viewURL}" target="_blank"> ${attachments.attachmentType_AR}</a>
																	</div>
																	</c:if>
																	
																	</c:if>
																	
																	</c:forEach>
																</div>
															</div>
															<!-- /text box -->
														</div>
													</div>
											</div>
											<!-- -----Back end uploaded file area ends   here --->
											<!-- Comment Section -->
											<div class="row">
												<div class="col-md-12 remove-pad">
													<div class="col-md-6 remove-pad">
															<!-- text box -->
														<div class="form-group cf">
															<div class="col-md-5">
																<label  for="supp_category" class="form-lbl "><spring:message code="label.service.optionalcomments" />
																</label>
															</div>
															<div class="col-md-7">
																<p>  ${reviewerComments} </p>
															</div>
														</div>
													</div> 
												</div>
	                                        </div>
										<!--/ Comment Section -->
	                                        <div class="row">
												<div class="col-md-12 remove-pad">
		                                        	<div class="col-md-6 remove-pad">
														<!-- submit button -->
			                                        	<div class="row">
				                                            <div class="form-group submission">
				                                                <div class="col-md-offset-5 col-md-7">
				                                                    <input type="submit" class="btn" value='<spring:message code="label.eservice.resubmit" />' /> 
				                                                </div>
				                                            </div>
				                                        </div>
				                                        <!-- /submit button -->
		                                        	</div>
												</div>
	                                        </div>
										<%-- </form> --%> </form:form>	
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
		<script src="/js/libs/jquery.validate.js"></script>
		<script type="text/javascript"src="/js/libs/jquery.mask.js"></script>
	    <script>
	    jQuery(function($) { 

			$("#feedbak").validate();
			//$('#addressto').attr('disabled', true);
				$('#addressto').change(function() {
					  $("label.error").hide();
					 // alert("$(this).val()" + $(this).val());
					if($(this).val()==16){
						$(".addressto-other").show();
						$(".other-form").attr("readonly", true);
						$(".other-form").val("");
						$(".other-form").removeClass("required");
						$(".other-form-file").removeClass("required");
						$(".other-form-file").parent().addClass("disabledinput");
						$("#scanFamilyBook").attr('disabled', true);
						$("#sposeEmiratesId").attr('disabled', true);
						$(".mandatory_lbl_sh").removeClass("mandatory_lbl");
						$(".other-form").val("");
						$(".other-form-file").val("");
						$("#familybooknumaddress").val("");
						$("#scanFamilyBook_name").val("");
						$("#sposeEmiratesId_name").val("");
					
					}else if($(this).val()==1){
						
						$(".other-form-file").addClass("required");
						$(".addressto-other").hide();
						//$("#familybooknum").val("");
						$(".other-form").attr("readonly", false);
						$(".other-form").addClass("required");
						$(".other-form-file").parent().removeClass("disabledinput");
						$("#scanFamilyBook").attr('disabled', false);
						$("#sposeEmiratesId").attr('disabled', false);
						$(".mandatory_lbl_sh").addClass("mandatory_lbl");
						$(".other-form").val("");
						$(".other-form-file").val("");
						$("#familybooknumaddress").val("");
						$("#scanFamilyBook_name").val("");
						$("#sposeEmiratesId_name").val("");
						
					}
						
					else{
						$(".addressto-other").hide();
						//$("#familybooknum").val("");
						$(".other-form").attr("readonly", false);
						$(".other-form").removeClass("required");
						$(".other-form-file").removeClass("required");
						$(".other-form-file").parent().addClass("disabledinput");
						$("#scanFamilyBook").attr('disabled', true);
						$("#sposeEmiratesId").attr('disabled', true);
						$(".mandatory_lbl_sh").removeClass("mandatory_lbl");
						$(".other-form").val("");
						$(".other-form-file").val("");
						$("#familybooknumaddress").val("");
						$("#scanFamilyBook_name").val("");
						$("#sposeEmiratesId_name").val("");
					
					}
				
				}); 
				
				selected = $("#addressto").val();
				//alert(selected);
				if(selected==16){
						$(".addressto-other").show();
						$(".other-form").attr("readonly", true);
						//$(".other-form").val("");
						$(".other-form").removeClass("required");
						$(".other-form-file").removeClass("required");
						$(".other-form-file").parent().addClass("disabledinput");
						$("#scanFamilyBook").attr('disabled', true);
						$("#sposeEmiratesId").attr('disabled', true);
						$(".mandatory_lbl_sh").removeClass("mandatory_lbl");
					
					}else if(selected==1){
						//alert("dfdf");
						$(".other-form-file").addClass("required");
						$(".addressto-other").hide();
						//$("#familybooknum").val("");
						$(".other-form").attr("readonly", false);
						$(".other-form").addClass("required");
						$(".other-form-file").parent().removeClass("disabledinput");
						$("#scanFamilyBook").attr('disabled', false);
						$("#sposeEmiratesId").attr('disabled', false);
						$(".mandatory_lbl_sh").addClass("mandatory_lbl");
						
					}else{
						$(".addressto-other").hide();
						//$("#familybooknum").val("");
						$(".other-form").attr("readonly", false);
						$(".other-form").removeClass("required");
						$(".other-form-file").removeClass("required");
						$(".other-form-file").parent().addClass("disabledinput");
						$("#scanFamilyBook").attr('disabled', true);
						$("#sposeEmiratesId").attr('disabled', true);
						$(".mandatory_lbl_sh").removeClass("mandatory_lbl");
						$(".other-form").val("");
						$(".other-form-file").val("");
						$("#familybooknumaddress").val("");
						$("#scanFamilyBook_name").val("");
						$("#sposeEmiratesId_name").val("");
					
					}
						
					
			});
	    

	    </script>
		<!-- /script -->
	</body>
	<!-- /body -->
</html>