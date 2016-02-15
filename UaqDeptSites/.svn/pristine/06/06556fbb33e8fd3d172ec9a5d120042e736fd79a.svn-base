<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
								<c:set var="familyReq"><spring:message code="label.ps.familymembers.required" /></c:set>
								<c:set var="employerReq"><spring:message code="label.ps.employer.required" /></c:set>
								<c:set var="salaryReq"><spring:message code="label.ps.salary.required" /></c:set>
								<c:set var="residenceReq"><spring:message code="label.ps.residence.required" /></c:set>
								<c:set var="maritalReq"><spring:message code="label.ps.marital.required" /></c:set>
								<c:set var="emiratesIdReq"><spring:message code="label.ps.spouseemirates.required" /></c:set>
								<c:set var="familyBookReq"><spring:message code="label.ps.familybook.required" /></c:set>
								<c:set var="spouseEmiratesAttachReq"><spring:message code="label.ps.spouse.emirates.attach.required" /></c:set>
								<c:set var="propertyDetailsReq"><spring:message code="label.ps.property.details.required" /></c:set>
								<c:set var="salaryCertReq"><spring:message code="label.ps.salary.cert.required" /></c:set>
								<c:set var="valLength"><spring:message code="validate.length" /></c:set>
	
		<div class="container-fluid">
			<div class="wrapper">
				<!-- content area -->
				<div class="content">
					<div class="row">
						<!-- left col -->
						<div class="col-xs-12 col-sm-9 col-md-9 main-left-col">
							<div class="main-content-wrap">
								
								<div class="page-content-wrap">
										<div class="form-content cf">
										   <form:form commandName="grantLandRequestCommand" name="feedbak" id="feedbak" enctype="multipart/form-data" method="post" action="grantlandrequest.html" >
										    <div class="row"></div>	

	                                        <div class="row">
												<div class="col-md-12 remove-pad">
													<div class="col-md-6 remove-pad">
														<!-- text box -->
														<div class="form-group cf">
			                                                <div class="col-md-5">
			                                                    <label for="plan-number" class="form-lbl">	<spring:message code="label.ps.familycount" />  
			                                                    </label>
				                                            </div>
				                                            <div class="col-md-7">
																	<form:input path="familyMembers" id="plan-number" name="plan-number" type="text"  class="form-control" data-msg-required="${familyReq}" data-rule-number="true" data-msg-number="${familyReq}" />
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
			                                                    <label for="employer" class="form-lbl mandatory_lbl"><spring:message code="label.ps.employer" />  
			                                                    </label>
				                                            </div>
				                                            <div class="col-md-7">
																	<form:input path="employer" id="employer" name="employer" type="text"  class="form-control required" data-msg-required="${employerReq}" />
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
			                                                    <label for="salary" class="form-lbl mandatory_lbl"><spring:message code="label.ps.salary" />  
			                                                    </label>
				                                            </div>
				                                            <div class="col-md-7">
																	<form:input path="monthlySalary" id="salary" name="salary" type="text"  class="form-control required" data-msg-required="${salaryReq}" data-rule-number="true" data-msg-number="${salaryReq}" />
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
			                                                    <label for="resi-address" class="form-lbl mandatory_lbl"><spring:message code="label.ps.resAddress" />
			                                                    </label>
				                                            </div>
				                                            <div class="col-md-7">
																	<form:input path="currentAddress" id="resi-address" name="resi-address" type="text"  class="form-control required" data-msg-required="${residenceReq}"  />
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
			                                                    <label for="marital-status" class="form-lbl mandatory_lbl"><spring:message code="label.ps.marital" />
			                                                    </label>
				                                            </div>
				                                            <div class="col-md-7">
																<div class="col-md-12 col-xs-12 remove-pad">
																	<div class="custom-select-box cf">
																		<form:select path="maritalStatus"
																		  class="required1 required" id="marital-status" name="marital-status"  data-msg-required="${maritalReq}" >
																			<option value="">Select </option>	
																			<option value="2">Single </option>
																			<option value="1">Married</option>
																		</form:select>
																	</div> 
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
			                                                    <label for="sp-emirateId" class="form-lbl mandatory_lbl"><spring:message code="label.ps.spouseEmiratesId" />
			                                                    </label>
				                                            </div>
				                                            <div class="col-md-7">
																	<form:input path="spousesEmiratesId" id="sp-emirateId" name="sp-emirateId" type="text"  class="form-control required" data-rule-maxlength="15" data-rule-minlength="15"
																data-msg-maxlength="${valLength}" 
																data-msg-minlength="${valLength}"  data-msg-required="${emiratesIdReq}" />
			                                                </div>
		                                            	</div>
		                                        		<!-- /text box -->
		                                        	</div>
												</div>
	                                        </div>	
											<div class="row">
												<div class="col-md-12">  <!-- form-sect-head-->
													<h5 class="form-title"><spring:message code="label.ps.attachments" /></h5>
												</div> 
											</div>	<!-- /form-sect-head-->
											<div class="row">
												<div class="col-md-12 remove-pad">
													<div class="col-md-6 remove-pad">
														<!-- text box -->
		                                            	<div class="form-group cf">
			                                                <div class="col-md-5">
			                                                    <label  for="family-book" class="form-lbl mandatory_lbl"><spring:message code="familyBookAttch" /></label>
				                                            </div>
				                                            <div class="col-md-7">
																<div class="input-group file-upload">
																	<form:input path="" type="text" class="form-control required" name="family-book" id="family-book" readonly="readonly" required="required" data-msg-required="${familyBookReq}"  />
																	<span class="input-group-btn">
																		<span class="btn btn-file">
																			<spring:message code="file.browse" />&hellip; 
																			<form:input path="familyBook" type="file" />
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
			                                                    <label  for="sp-imirate-attach" class="form-lbl mandatory_lbl"><spring:message code="label.ps.spouseEmiratesAttach" /></label>
				                                            </div>
				                                            <div class="col-md-7">
																<div class="input-group file-upload">
																	<form:input path="" type="text" class="form-control required" name="sp-imirate-attach" id="sp-imirate-attach" readonly="readonly" required="required" data-msg-required="${spouseEmiratesAttachReq}"  />
																	<span class="input-group-btn">
																		<span class="btn btn-file">
																			<spring:message code="file.browse" />&hellip; 
																			<form:input path="spousesEmiratesIdAttch" type="file"/>
																		</span>
																	</span>
																</div>
			                                                </div>
		                                            	</div>
													
		                                        		<!-- /text box -->
		                                        	</div>	
												</div>
	                                        </div>			
										<!-- 	<div class="row">
												<div class="col-md-12 remove-pad">
													<div class="col-md-6 remove-pad">
														text box
		                                            	<div class="form-group cf">
			                                                <div class="col-md-5">
			                                                    <label  for="sp-imirate-attach" class="form-lbl mandatory_lbl">Spouse's Imirate Id</label>
				                                            </div>
				                                            <div class="col-md-7">
																<div class="input-group file-upload">
																	<input type="text" class="form-control required" name="sp-imirate-attach" id="sp-imirate-attach" />
																	<span class="input-group-btn">
																		<span class="btn btn-file">
																			Browse&hellip; 
																			<input type="file" multiple>
																		</span>
																	</span>
																</div>
			                                                </div>
		                                            	</div>
													
		                                        		/text box
		                                        	</div>	
												</div>
	                                        </div> -->			
											<div class="row">
												<div class="col-md-12 remove-pad">
													<div class="col-md-6 remove-pad">
														<!-- text box -->
		                                            	<div class="form-group cf">
			                                                <div class="col-md-5">
			                                                    <label  for="propert-acc-details" class="form-lbl mandatory_lbl"><spring:message code="label.ps.propAccDetail" /></label>
				                                            </div>
				                                            <div class="col-md-7">
																<div class="input-group file-upload">
																	<form:input path="" type="text" class="form-control required" name="propert-acc-details" id="propert-acc-details" required="required" readonly="readonly" data-msg-required="${propertyDetailsReq}" />
																	<span class="input-group-btn">
																		<span class="btn btn-file">
																			<spring:message code="file.browse" />&hellip; 
																			<form:input path="propertyAccDetailsAttach" type="file"/>
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
			                                                    <label  for="salary-certificate" class="form-lbl mandatory_lbl"><spring:message code="label.ps.salaryCert" /></label>
				                                            </div>
				                                            <div class="col-md-7">
																<div class="input-group file-upload">
																	<form:input path="" type="text" class="form-control required" name="salary-certificate" id="salary-certificate" readonly="readonly" required="required" data-msg-required="${salaryCertReq}"  />
																	<span class="input-group-btn">
																		<span class="btn btn-file">
																			<spring:message code="file.browse" />&hellip; 
																			<form:input path="salaryCertificate" type="file" />
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
				                                                    <input type="submit" class="btn" value='<spring:message code="form.button.submit" />'/> 
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
		<!-- <script src="/js/dest/app.js"></script> -->
		<script src="/js/libs/jquery.validate.js"></script>
		<script src="/js/libs/tooltip.js"></script>
		<script src="/js/libs/popover.js"></script>
		<script type="text/javascript"src="/js/libs/jquery.mask.js"></script>

	    <script>

			jQuery(function($) { 
				$("#feedbak").validate();
				$(".radiobtn").click(function(){
					val= document.querySelector('input[name="locations"]:checked').value; 
					if(val == "sector"){
						$(".areaform").removeClass("required");
						$(".areaform").attr("readonly", true);
						$(".sectorform").attr("readonly", false);
						$(".sectorform").addClass("required");
						$("#sector-select").attr("disabled", false);
						$("#area-select").attr("disabled", true);
						$("#sector-select option:first-child").attr("selected", true);
						$("#area-select option:first-child").attr("selected", true);

					}else{

						$(".sectorform").removeClass("required");
						$(".areaform").attr("readonly", false);
						$(".sectorform").attr("readonly", true);
						$(".areaform").addClass("required");
						$("#sector-select").attr("disabled", true);
						$("#area-select").attr("disabled", false);
						$("#sector-select option:first-child").attr("selected", true);
						$("#area-select option:first-child").attr("selected", true);

					}
				});
				
			});
			
	    </script>
		<!-- /script -->
	