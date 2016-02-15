<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

		<div class="container-fluid">
			<div class="wrapper">
				<!-- content area -->
				<div class="content">
				<c:set var="addressToReq"><spring:message code="label.lp.addressTo.required" /></c:set>
				<c:set var="familyBookReq"><spring:message code="label.lp.familyBookNo.required" /></c:set>
				<c:set var="spouseIdReq"><spring:message code="label.spouseIdNo.required" /></c:set>
				<c:set var="familyBookReq"><spring:message code="label.lp.familyBookScan.required" /></c:set>
				<c:set var="spouseEmiratesReq"><spring:message code="label.ps.spouse.emirates.attach.required" /></c:set>
				
				<c:set var ="filesize"><spring:message code="filesizemsg"/></c:set>
								<c:set var ="fileextention"><spring:message code="fileextentionmsg"/></c:set>
								<c:set var ="fileerror"><spring:message code="fileerrormsg"/></c:set>
				
					<div class="row">
						<!-- right col -->
						<div class="col-xs-12 col-sm-3 col-md-3 main-right-col">
						</div>

						<div class="col-xs-12 col-sm-9 col-md-9 main-left-col">
							<div class="main-content-wrap">
								
								<div class="page-content-wrap">
										<div class="form-content cf">
										   <form:form commandName="whomItmayConcernCommand" name="feedbak" id="feedbak" enctype="multipart/form-data" method="POST" action="/${param.languageCode}/resubmitwhomitmayconcern.html" >
										    <div class="row"></div>
										
												
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
	                                                                <form:select path="addressTo" disabled="true" class="required1 required"  name="addressto" id="addressto" data-msg-required="${addressToReq}">
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
																<form:input path="other"  readonly="true" id="familybooknumaddress" name="familybooknumaddress" type="text" class="form-control required"  />
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
																<form:input path="familyBookNo" readonly="true" id="familybooknum" name="familybooknum" type="text" class="form-control required other-form" data-msg-required="${familyBookReq}" maxlength="20"/>
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
																<form:input path="spouseIdNo" readonly="true" id="spouseid" name="spouseid" type="text" class="form-control required other-form"  data-msg-required="${spouseIdReq}" maxlength="20"/>
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
																	<c:if test="${attachments.docTypeId ne '6' }">
																	<div class="col-md-12 remove-pad attachment-btm">
																		<a href="${attachments.url}" target="_blank"> ${attachments.filename}</a>
																	</div>
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
																	
																	<c:if test="${attachments.docTypeId == '6' }">
																	<div class="col-md-12 remove-pad attachment-btm">
																	<a href="${attachments.url}" target="_blank"> ${attachments.filename}</a>
																	</div>
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
				                                                <c:if test="${status ne '6' }">
				                                                    <input type="submit" class="btn" value='<spring:message code="form.button.submit" />' />
				                                                    </c:if> 
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
	    <script>
		selected = $("#addressto").val();
				
				if(selected==16){
					$(".addressto-other").show();
				}else{
							
					$(".addressto-other").hide();

				}	
	</script>
		<!-- /script -->
	</body>
	<!-- /body -->
</html>