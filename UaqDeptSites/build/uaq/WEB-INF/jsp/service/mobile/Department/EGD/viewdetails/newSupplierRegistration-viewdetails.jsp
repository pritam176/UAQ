<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

	
		
				<!-- content area -->
				<div class="content">
					<div class="row">
						<!-- right col -->
						<div class="col-xs-12 col-sm-3 col-md-3 main-right-col">
							<!-- social media share callout -->
							
								<%-- <!-- DepartmentLists -->
								<jsp:include page="../common/depts_services_rhs.jsp"></jsp:include>
								<!-- /DepartmentLists --> --%>
							
								<c:set var="establishmentReq"><spring:message code="feedback.form.field.establishment.required" /></c:set>
								<c:set var="tradeLicReq"><spring:message code="plzTradeLicenseNumber" /></c:set>
								<c:set var="expDateReq"><spring:message code="plzTradeLicenseExp" /></c:set>
								<c:set var="mobileReq"><spring:message code="plzMobileNumber" /></c:set>
								<c:set var="emailReq"><spring:message code="plzEmailAddress" /></c:set>
								<c:set var="addressReq"><spring:message code="plzAddress" /></c:set>
								<c:set var="officeTelReq"><spring:message code="label.egd.offTel.required" /></c:set>
								<c:set var="postBoxReq"><spring:message code="label.egd.postBox.required" /></c:set>
								<c:set var="suppCatReq"><spring:message code="label.egd.supplierCatagory.required" /></c:set>
								<c:set var="regTypeReq"><spring:message code="label.egd.registrationType.required" /></c:set>
								<c:set var="tradingLicReq"><spring:message code="label.egd.tradingLic.required" /></c:set>
								<c:set var="signatoryReq"><spring:message code="label.egd.signatoryAttest.required" /></c:set>
								<c:set var="commerceReq"><spring:message code="label.egd.commerceMember.required" /></c:set>
								<c:set var="certificatesReq"><spring:message code="label.egd.certificates.required" /></c:set>
								
								<c:set var ="filesize"><spring:message code="filesizemsg"/></c:set>
								<c:set var ="fileextention"><spring:message code="fileextentionmsg"/></c:set>
								<c:set var ="fileerror"><spring:message code="fileerrormsg"/></c:set>
							
								<c:set var="valMobile"><spring:message code="plzMobileNumber" /></c:set>
								<c:set var="valofficeLength"><spring:message code="maxnumoffc" /></c:set>
								<c:set var="emiratesReq"><spring:message code="plzChooseEmirates" /></c:set>
								
								
								
								
							
							<!-- /social media share callout -->
						</div>
						<!-- right col -->
						<!-- left col -->
						<div class="col-xs-12 col-sm-9 col-md-9 main-left-col">
							<!-- page title -->
							
							<!-- /page title -->
							<div class="main-content-wrap">
								
								<div class="page-content-wrap">
										<div class="form-content cf">
										 <form:form commandName="newSupplierRegistrationCommand"
								 id="feedbak"
								enctype="multipart/form-data" method="post" action="resubmitnewSupplierRegistrationPage.html">
										    <div class="row">
												<div class="row"> 
													<div class="col-md-12 remove-pad"><div class="form-group cf">
													<%-- <label class="mandatory_lbl pull-right"><spring:message code="feedback.form.mandatory" /></label> --%>
													 </div></div>
												</div>
											</div>	
											<div class="row">
												<div class="col-md-12 remove-pad">
													<div class="col-md-6 remove-pad">
														<!-- text box -->
		                                            	<div class="form-group cf">
			                                                <div class="col-md-5">
			                                                    <label for="establishment-name"  class="form-lbl mandatory_lbl"><spring:message code="establishNameAsStated" /></label>
				                                            </div>
				                                            <div class="col-md-7 form-grp-help">
			                                                    <form:input path="establishmentName" readonly="true" type="text" id="establishment-name" name="establishment-name" class="form-control required" required="required" data-msg-required="${establishmentReq}"/>
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
			                                                    <label for="trade_licence" class="form-lbl mandatory_lbl"><spring:message code="trade.licence.number" /></label>
				                                            </div>
				                                            <div class="col-md-7">
			                                                   	<form:input path="tradeLicenceNumber" readonly="true"  type="text" id="trade_licence" name="trade_licence" class="form-control required"  required="required" data-msg-required="${tradeLicReq}"/>
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
			                                                    <label for="expiry_date" class="form-lbl mandatory_lbl"> <spring:message code="tradeLicenExpiryDate" />
			                                                    </label>
				                                            </div>
				                                            <div class="col-md-7">
															    <form:input path="expiryDate" readonly="true" type="text" id="expiry_date" name="expiry_date" class="form-control required" data-msg-required="${expDateReq}"/>
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
			                                                    <label for="mobile_number" class="form-lbl mandatory_lbl"><spring:message code="mobileNum1" />
			                                                    </label>
				                                            </div>
				                                            <div class="col-md-7">
																<form:input path="mobileNumber" readonly="true" id="mobile_number" name="mobile_number" type="text"  class="form-control required" data-msg-required="${mobileReq}"/>
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
																<label for="email" class="form-lbl mandatory_lbl"><spring:message code="email" />
																</label>
															</div>
															<div class="col-md-7">
																<form:input path="email" id="email" readonly="true"  name="email" type="email" class="form-control required" data-msg-required="${emailReq}"/>
															</div>
														<!-- /text box -->
														</div>
		                                        	</div>
												</div>
	                                        </div>
	                                        
	                                        <!-- /emirate ID added -->
	                                        
	                                        <div class="row">
												<div class="col-md-12 remove-pad">
													<div class="col-md-6 remove-pad">
															<!-- text box -->
														<div class="form-group cf">
															<div class="col-md-5">
																<label for="emirateId" class="form-lbl mandatory_lbl"><spring:message code="emiratedId"/>
																</label>
															</div>
															<div class="col-md-7">
																<form:input path="emirateId" id="emirateId" readonly="true"  name="emirateId"  class="form-control required" data-msg-required="${emailReq}"/>
															</div>
														<!-- /text box -->
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
																<label  for="address" class="form-lbl mandatory_lbl"><spring:message code="label.address" />
																</label>
															</div>
															<div class="col-md-7">
																<form:input path="address" readonly="true"  type="text" id="address" name="address" class="form-control required" data-msg-required="${addressReq}" maxlength="100"/>
															</div>
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
																<label  for="office_telephone" class="form-lbl mandatory_lbl"><spring:message code="label.egd.offTel" />
																</label>
															</div>
															<div class="col-md-7">
																<form:input path="officeNumber" readonly="true" type="text" id="office_telephone" name="office_telephone" class="form-control required" 
																data-msg-required="${officeTelReq}" data-rule-maxlength="11" data-rule-minlength="11" 
																  data-msg-number="${valMobile}" 
																data-msg-maxlength="${valofficeLength}" data-msg-minlength="${valofficeLength}" maxlength="11" data-mask="00-0000000" />
															</div>
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
																<label  for="post_box" class="form-lbl mandatory_lbl"><spring:message code="label.egd.postBox" />
																</label>
															</div>
															<div class="col-md-7">
																<form:input path="postBox" readonly="true"  type="text" id="post_box" name="post_box" class="form-control required" 
																 data-msg-required="${postBoxReq}" data-rule-maxlength="10" data-rule-minlength="1" 
																 data-rule-number="true" data-msg-number="${valMobile}" 
																data-msg-maxlength="${valofficeLength}" data-msg-minlength="${valofficeLength}" 
																msxlength="10"/>
															</div>
														</div>
													</div> 
												</div>
	                                        </div>
	                                                 <!--  emitate list------------------->
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
	                                                                <form:select path="emirates" disabled="true"  class="required1" name="countryofcitizen" required="required" data-msg-required="${emiratesReq}">
	                                                                   <form:option value=""><spring:message code="selectEmirates"/></form:option>
																			<form:options items="${emirateList}" />
	                                                                </form:select>
	                                                            </div>
															</div>
														</div>
													</div> 
												</div>
	                                        </div>
	                                        <!-- end of emirate list -------------->
											<div class="row">
												<div class="col-md-12 remove-pad">
													<div class="col-md-6 remove-pad">
															<!-- text box -->
														<div class="form-group cf">
															<div class="col-md-5">
																<label  for="supp_category" class="form-lbl mandatory_lbl"><spring:message code="label.egd.supplierCategory" />
																</label>
															</div>
															<div class="col-md-7">
																<div class="custom-select-box cf">
	                                                                <form:select path="supplierCategory" disabled="true" class="required1 required" name="countryofcitizen" data-msg-required="${suppCatReq}">
	                                                                   <form:options items="${eGDSuppCategory}" />
	                                                                </form:select>
	                                                            </div>
															</div>
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
																<label  for="supp_category" class="form-lbl mandatory_lbl"><spring:message code="label.egd.regType" />
																</label>
															</div>
															<div class="col-md-7">
																<div class="custom-select-boxc">
																	
																		<form:select path="registrationsType"  disabled="true" class="required1 required multi-select"  name="countryofcitizen" data-msg-required="${regTypeReq}" multiple="multiple" size="4" >
																			<form:options items="${egdSuppRegTypes}" />
																		</form:select>
																
	                                                            </div>
															</div>
														</div>
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
																	<c:forEach items="${attachmentPayLoad}" var="attachments">
																	<c:if test="${fn:trim(attachments.docTypeId) ne fn:trim('Reviewer Supportive Attachment')}">
																	<div class="col-md-12 remove-pad attachment-btm">
																		<a href="${attachments.downloadUrl}" target="_blank"> ${attachments.docTypeId}</a>
																	</div>
																	</c:if>
																	
																	</c:forEach>
																</div>
															</div>
															<!-- /text box -->
														</div>
													</div>
											</div>
											<!-- ----- uploaded file area starts here --->
													
										<!-- -----Back end uploaded file area ends   here --->
											<%-- <div class="row ">
													<div class="col-md-12 remove-pad">
														<div class="col-md-6 remove-pad">
															<!-- text box -->
															<div class="form-group cf">
																<div class="col-md-5">
																	<label class="form-lbl "> <spring:message code="label.service.reviwer.uploadedfiles" /> </label>
																</div>
																<div class="col-md-7">
																	<c:forEach items="${attachmentPayLoad}" var="attachments">
																	<c:if test="${fn:containsIgnoreCase(fn:trim(attachments.docTypeId), fn:trim('Reviewer Supportive Attachment'))}">
																	<div class="col-md-12 remove-pad attachment-btm">
																		<a href="${attachments.downloadUrl}" target="_blank"> ${attachments.docTypeId}</a>
																		</div>
																	</c:if>
																	</c:forEach>
																</div>
															</div>
															<!-- /text box -->
														</div>
													</div>
											</div> --%>
											<!-- -----Back end uploaded file area ends   here --->
											<!-- Comment Section -->
											<div class="row">
												<div class="col-md-12 remove-pad">
													<div class="col-md-6 remove-pad">
															<!-- text box -->
														<div class="form-group cf">
															<div class="col-md-5">
																<label  for="supp_category" class="form-lbl ">Comment
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
				                                                <c:if test="${status ne '6' }">
				                                                    <input type="submit" class="btn" value='<spring:message code="form.button.submit" />'/>
				                                                    </c:if> 
				                                                </div>
				                                            </div>
				                                        </div>
				                                        <!-- /submit button -->
		                                        	</div>
												</div>
	                                        </div>
										<%-- </form>	 --%></form:form>
									</div>
							</div>
						</div>
						<!-- /left col -->
					</div>
				</div>
				<!-- /content area -->

			</div>
	
		<!-- script -->
		
		<script src="/js/libs/jquery.validate.js"></script>
		<script type="text/javascript"src="/js/libs/jquery.mask.js"></script>

	    <script>

			jQuery(function($) { 

			$("#feedbak").validate();
			var certificateId = 1;
			var attach_arr = new Array();
			$("#addcertificate")
					.click(
							function(event) {
								event.preventDefault();

								//$(".row").removeClass("certificate-last-row");
								//certihtml = '<div class="row certificate-last-row" id="attachid_'+certificateId+'" ><div class="col-md-12 remove-pad"><div class="col-md-6 remove-pad"><div class="form-group cf"><div class="col-md-5"><label for="certificate_attach_'+certificateId+'" class="form-lbl mandatory_lbl">Certificates </label></div><div class="col-md-7"><div class="input-group file-upload"><input type="text" class="form-control" name ="certificate_attach_'+certificateId+'" id ="certificate_attach_'+certificateId+'" readonly /><span class="input-group-btn"><span class="btn btn-file">Browse&hellip; <input type="file" multiple></span></span><span class="glyphicon glyphicon-remove removeico" data-remove="attachid_'+certificateId+'"></span></div></div></div></div></div></div>';
								//alert("certihtml"+certihtml);
								if(attach_arr.length<2){
								certihtml = '<div class="row certificate-last-row" id="attachid_'+certificateId+'" ><div class="col-md-12 remove-pad"><div class="col-md-6 remove-pad"><div class="form-group cf"><div class="col-md-5"><label for="file_attach_'+certificateId+'" class="form-lbl mandatory_lbl"> Attachment  </label></div><div class="col-md-7"><div class="input-group file-upload"><input type="text" class="form-control"  name ="file_attach_'+certificateId+'" data-msg-required="${deptlblattachmntmsg}" id ="file_attach_'+certificateId+'" readonly /><span class="input-group-btn"><span class="btn btn-file"><spring:message code="file.browse"/>&hellip; <input type="file" accept="image/jpg, image/JPG,image/JPEG, image/jpeg,image/jpeg,image/gif,image/png, application/pdf,image/x-eps, application/msword" name="files['+certificateId+']" id="supplier_file_'+certificateId+'" error-size="Please upload less than 2Mb" error-extention="Please upload valid format" error-failed="Please upload try again" ></span></span><span class="glyphicon glyphicon-remove removeico" data-remove="attachid_'+certificateId+'"></span><input type="hidden" name="supplier_file_name['+certificateId+']" id="supplier_file_'+certificateId+'_name" value=""></div></div></div></div></div></div>';
								

								$(".add-morecertificate-row").before(certihtml);

								$('#file_attach_' + certificateId).rules(
										'add', {
											required : true,
											messages : {
												required : 'This field is required'
											}
										});
								attach_arr.push(certificateId);
								certificateId = certificateId + 1;
								if( attach_arr.length>=2){
									$("#addcertificate").hide();
								}else{
									$("#addcertificate").show();
								}
							}

							});

			$(".form-content").on("click", ".removeico", function() {

				//alert("test ");
				var removeitem = $(this).attr("data-remove");
				$("#" + removeitem).remove();
				$("#addcertificate").show();
				attach_arr.pop(removeitem.split("_")[1]);

			});
			
			});
			
	    </script>
		<!-- /script -->
	