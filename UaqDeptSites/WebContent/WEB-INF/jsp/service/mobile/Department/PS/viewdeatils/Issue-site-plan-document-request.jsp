<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

		<div class="container-fluid">
			<div class="wrapper">
				
			

				<!-- content area -->
				<div class="content">
				
					<c:set var="siteplanReq"><spring:message code="label.ps.siteplanNo.required" /></c:set>
					<c:set var="sectorReq"><spring:message code="label.ps.sector.required" /></c:set>
					<c:set var="blockReq"><spring:message code="label.ps.block.required" /></c:set>	
					<c:set var="subSectorReq"><spring:message code="label.ps.subSector.required" /></c:set>	
					<c:set var="plotNoReq"><spring:message code="label.ps.plotNo.required" /></c:set>	
					<c:set var="areaReq"><spring:message code="label.ps.area.required" /></c:set>
					<c:set var="subAreaReq"><spring:message code="label.ps.subArea.required" /></c:set>
					<c:set var="landUsageReq"><spring:message code="label.ps.landUsage.required" /></c:set>
					<c:set var="sitePlanDocReq"><spring:message code="label.ps.sitePlanDoc.required" /></c:set>
					<c:set var="landUsageReq"><spring:message code="label.ps.landUsage.required" /></c:set>
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
							
							<!-- /page title -->
							<div class="main-content-wrap">
								
								<div class="page-content-wrap">
										<div class="form-content cf">
										<form:form  id="feedbak" commandName="issueSitePlanDocumentCommand" enctype="multipart/form-data" method="post" action="resubmitsitePlanDocumentPage.html" >
										    <div class="row"></div>	

	                                        <div class="row">
												<div class="col-md-12 remove-pad">
													<div class="col-md-6 remove-pad">
														<!-- text box -->
														<div class="form-group cf">
			                                                <div class="col-md-5">
			                                                    <label for="plan-number" class="form-lbl mandatory_lbl"><spring:message code="label.ps.siteplanNo" />    <span class="form-lbl-subtxt"><%-- <spring:message code="label.ps.land.owned" /> --%></span>
			                                                    </label>
				                                            </div>
				                                            <div class="col-md-7">
																	<form:input path="sitePlanNumber" readonly="true" id="plan-number" name="plan-number" type="text"  class="form-control required" data-msg-required="${siteplanReq}" maxlength="10"/>
			                                                </div>
		                                            	</div>
		                                        		<!-- /text box -->
		                                        	</div>
												</div>
	                                        </div>
											<div class="row">
												<div class="col-md-12">  <!-- form-sect-head-->
													<h5 class="form-title"><spring:message code="label.ps.locations" /> </h5>
												</div> <!-- /form-sect-head-->
											</div>	
											<c:if test="${location =='sector'}">
											<div class="row">
												<div class="form-group cf">
												
													<div class="col-md-9">
														<!-- radio button -->
														<div class="col-md-3 remove-pad">
															<div class="radio inline-custom">
																<form:radiobutton disabled="true" id="sector-radio" path="locations" name="locations" value="sector" class="radiobtn required"  />
																<label for="sector-radio" class="custom"><spring:message code="label.ps.sector" /></label>
															</div>
														</div>
														<!-- /radio button -->
													</div>
												</div>
											</div>
	                                        <div class="row">
												<div class="col-md-12 remove-pad">
													<div class="col-md-6 remove-pad">
														<!-- text box -->
		                                            	<div class="form-group cf">
			                                                <div class="col-md-5">
			                                                    <label for="sector1" class="form-lbl mandatory_lbl"><spring:message code="label.ps.sector" />
			                                                    </label>
				                                            </div>
				                                            <div class="col-md-7">
																<div class="col-md-12 col-xs-12 remove-pad">
																	<div class="custom-select-box cf">
																		<form:select path="sector"  class="required1 required" id="sector-select" name="sector1"  disabled="true" data-msg-required="${sectorReq}">
																			<form:option value=""></form:option>
																			<form:options items="${sectorList}"/>
																		</form:select>
																	</div> 
																</div>
																
			                                                </div>
		                                            	</div>
		                                        		<!-- /text box -->
		                                        	</div>
		                                        	<div class="col-md-6 remove-pad">
														<!-- text box -->
		                                            	<div class="form-group cf">
			                                                <div class="col-md-5">
			                                                    <label  for="block" class="form-lbl mandatory_lbl"><spring:message code="label.ps.block" />
			                                                    </label>
				                                            </div>
				                                            <div class="col-md-7">
																<form:input path="block"  type="text" id="block" name="block" class="form-control sectorform required"  readonly="true" data-msg-required="${blockReq}"/>
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
			                                                    <label for="subsector" class="form-lbl mandatory_lbl"><spring:message code="label.ps.subSector" />
			                                                    </label>
				                                            </div>
				                                            <div class="col-md-7"> 
			                                                    <form:input path="subsector" id="subsector" name="subsector" type="text" class="form-control sectorform required"  readonly="true" data-msg-required="${subSectorReq}" />
			                                                </div>
		                                            	</div>
		                                        		<!-- /text box -->
		                                        	</div>												
													<div class="col-md-6 remove-pad">
														<!-- text box -->
		                                            	<div class="form-group cf">
			                                                <div class="col-md-5">
			                                                    <label for="plotnumber" class="form-lbl mandatory_lbl"><spring:message code="label.ps.plotNo" />
			                                                    </label>
				                                            </div>
				                                            <div class="col-md-7"> 
			                                                    <form:input path="sectorPlotNumber" id="plotnumber" name="plotnumber" type="text" class="form-control sectorform required "  readonly="true" data-msg-required="${plotNoReq}" maxlength="20"/>
			                                                </div>
		                                            	</div>
		                                        		<!-- /text box -->
		                                        	</div>
												</div>
	                                        </div>
											</c:if>
										<!--  Area starts here !!!!!!!!!  -->
										<c:if test="${location =='area'}">
											<div class="row">
												<div class="form-group cf">
													<div class="col-md-9">
														<!-- radio button -->
														<div class="col-md-3 remove-pad">
															<div class="radio inline-custom">
																<form:radiobutton path="locations" id="replacement1" disabled="true" name="locations" value="area" class="radiobtn"/>
																<label for="replacement1" class="custom"><spring:message code="label.ps.area" /></label>
															</div>
														</div>
														<!-- /radio button -->
													</div>
												</div>
											</div>
	                                        <div class="row">
												<div class="col-md-12 remove-pad">
													<div class="col-md-6 remove-pad">
														<!-- text box -->
		                                            	<div class="form-group cf">
			                                                <div class="col-md-5">
			                                                    <label for="area1" class="form-lbl mandatory_lbl"><spring:message code="label.ps.area" />
			                                                    </label>
				                                            </div>
				                                            <div class="col-md-7">
																<div class="col-md-12 col-xs-12 remove-pad">
																	<div class="custom-select-box cf">
																		<form:select path="area"  class="required1 required" id="area-select" name="area1" disabled="true" data-msg-required="${areaReq}" >
																				<form:option value=""></form:option>
																			<form:options items="${areaList}"/>
																	</form:select>
																	</div> 
																</div>
																
			                                                </div>
		                                            	</div>
		                                        		<!-- /text box -->
		                                        	</div>
		                                        	<div class="col-md-6 remove-pad">
														<!-- text box -->
		                                            	<div class="form-group cf">
			                                                <div class="col-md-5">
			                                                    <label  for="area-block" class="form-lbl mandatory_lbl"><spring:message code="label.ps.block" />
			                                                    </label>
				                                            </div>
				                                            <div class="col-md-7">
																<form:input path="areaBlock"  type="text" id="area-block" name="area-block" class="form-control areaform required" readonly="true" data-msg-required="${blockReq}"/>
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
			                                                    <label for="sub-area" class="form-lbl mandatory_lbl"><spring:message code="label.ps.subArea" />
			                                                    </label>
				                                            </div>
				                                            <div class="col-md-7"> 
			                                                    <form:input path="subArea" id="sub-area" name="sub-area" type="text" class="form-control areaform required " readonly="true" data-msg-required="${subAreaReq}" maxlength="20"/>
			                                                </div>
		                                            	</div>
		                                        		<!-- /text box -->
		                                        	</div>												
													<div class="col-md-6 remove-pad">
														<!-- text box -->
		                                            	<div class="form-group cf">
			                                                <div class="col-md-5">
			                                                    <label for="area-plotnumber" class="form-lbl mandatory_lbl"><spring:message code="label.ps.plotNo" />
			                                                    </label>
				                                            </div>
				                                            <div class="col-md-7"> 
			                                                    <form:input path="areaPlotNumber" id="area-plotnumber" name="area-plotnumber" type="text" class="form-control areaform required" readonly="true" data-msg-required="${plotNoReq}" maxlength="20"/>
			                                                </div>
		                                            	</div>
		                                        		<!-- /text box -->
		                                        	</div>
												</div>
	                                        </div>
	                                        </c:if>
											<!--  /Area starts here !!!!!!!!!  -->
											
											<div class="row">
												<div class="col-md-12 remove-pad">
													<div class="col-md-6 remove-pad">
														<!-- text box -->
		                                            	<div class="form-group cf">
			                                                <div class="col-md-5">
			                                                    <label class="form-lbl"> 
			                                                    </label>
				                                            </div>
				                                            <div class="col-md-7">
			                                                   
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
																<label for="landUsage" class="form-lbl mandatory_lbl"><spring:message code="label.ps.landUsage" /> </label>
															</div>
															<div class="col-md-7">
																	<div class="col-md-12 col-xs-12 remove-pad">
																	<div class="custom-select-box cf">
																		<form:select path="landUsage" class="required1 required" disabled="true"
																			id="landusage" name="landUsage"  data-msg-required="${landUsageReq}">
																			<form:option value=""></form:option>
																			<form:options items="${landType}" />
																		</form:select>
																	</div>
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
																	<c:forEach items="${attachmentPayLoad}" var="attachments">
																	<div class="col-md-12 remove-pad attachment-btm">
																	<c:if test="${fn:trim(attachments.modifiedby.value) ne fn:trim('Reviewer Supportive Attachment')}">
																		<a href="${attachments.downloadurl.value}" target="_blank"> ${attachments.modifiedby.value}</a> 
																		</c:if>
																	</div>
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
																	<div class="col-md-12 remove-pad attachment-btm">
																	<c:if test="${fn:containsIgnoreCase(fn:trim(attachments.modifiedby.value), fn:trim('Reviewer Supportive Attachment'))}">
																		<a href="${attachments.downloadurl.value}" target="_blank"> ${attachments.modifiedby.value}</a> 
																	</c:if>
																	</div>
																	</c:forEach>
																</div>
															</div>
															<!-- /text box -->
														</div>
													</div>
											</div>

											<!-- ----- uploaded file area ends   here --->
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
	                                        
										<%-- </form>	 --%></form:form>
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
		<script src="/js/dest/app.js"></script>
		<script src="/js/libs/jquery.validate.js"></script>
	    <script>

			jQuery(function($) { 
				window.onload = function(){
					
					$(".areaform").attr("readonly", true);
					$(".sectorform").addClass("required");
					$("#area-select").attr("disabled", true);
					$("label.error").hide();
					$(".sectorform").removeClass("required");
					$(".sectorform").attr("readonly", true);
					$("#plan-number").attr("readonly", true);
					$("#sector-select").attr("disabled", true);
					$("#landusage").attr("disabled", true);
					$("label.error").hide();
				
				
			}
			});
			
	    </script>
		<!-- /script -->
	
		