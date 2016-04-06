
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
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
								<li class="active"><a href="#"><spring:message code="label.ps.addlandReq" /></a>
								</li>
								
							</ul>
						</div>
					</div>
				</div>
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
										<spring:message code="label.ps.addlandReq" />
										
										<spring:message code="label.resubmit"/>
										<!--- ps page 16 -->
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
									<form:form commandName="addLandRequestCommand" name="feedbak"
										id="feedbak" enctype="multipart/form-data" method="post"
										action="resubmitaddlandrequest.html">
										<div class="row"></div>

										<!-- Request Detail For Resubmitting-->
										
										
										<form:hidden path="requestNo" id="requestNo" value=""/>
										<form:hidden path="stausId" id="stausId" value=""/>
										<form:hidden path="serviceId" id="serviceId" value=""/>
										<form:hidden path="sorceType" id="sorceType" value=""/>
										<form:hidden path="ownerName" id="ownerName" value=""/>
										<form:hidden path="ownerId" id="ownerId" value=""/>
										<form:hidden path="submittednocid" id="submittednocid" value=""/>
										<form:hidden path="sitePalnDOcId" id="sitePalnDOcId" value=""/>
										<form:hidden path="landValue" id="landValue" value=""/>
										<form:hidden path="commeteRemark" id="commeteRemark" value=""/>
										<form:hidden path="trueSitePlanDocId" id="trueSitePlanDocId" value=""/>
										<form:hidden path="createdBy" id="createdBy" value=""/>
										
				

										<div class="row">
											<div class="col-md-12 remove-pad">
												<div class="col-md-6 remove-pad">
													<!-- text box -->
													<div class="form-group cf">
														<div class="col-md-5">
															<label for="plan-number" class="form-lbl "><spring:message code="label.ps.siteplanNo" /> <span class="form-lbl-subtxt">
																<%-- <spring:message code="label.ps.land.owned" /> --%>	</span>
															</label>
														</div>
														<div class="col-md-7">
															<form:input path="siteNumber" id="plan-number"
																name="plan-number" type="text"
																class="form-control "  data-msg-required="${siteplanReq}" data-mask="0000000000"/>
														</div>
													</div>
													<!-- /text box -->
												</div>
											</div>
										</div>
										<div class="row">
											<div class="col-md-12">
												<!-- form-sect-head-->
												<h5 class="form-title"><spring:message code="label.ps.locations" /> </h5>
											</div>
											<!-- /form-sect-head-->
										</div>
										<div class="row">
											<div class="form-group cf">

												<div class="col-md-9">
													<!-- radio button -->
													<div class="col-md-3 remove-pad">
														<div class="radio inline-custom">
															<form:radiobutton path="locations" id="sector-radio"
																name="locations" value="sector"
																class="radiobtn required" />
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
																	<form:select path="sector" class="required1 required"
																		id="sector-select" name="sector1" disabled="disabled" data-msg-required="${sectorReq}">
																		<form:option value="" ><spring:message code="option.select"/> </form:option> 
																		<form:options items="${sectorList}" />
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
															<label for="block" class="form-lbl mandatory_lbl"><spring:message code="label.ps.block" />
															</label>
														</div>
														<div class="col-md-7">
															<form:input path="block" type="text" id="block"
																name="block" class="form-control sectorform required"
																readonly="readonly" data-msg-required="${blockReq}" maxlength="20"/>
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
															<label for="subsector" class="form-lbl mandatory_lbl"><spring:message code="label.ps.subSector" />  </label>
														</div>
														<div class="col-md-7">
															<form:input path="subsector" id="subsector"
																name="subsector" type="text"
																class="form-control sectorform required"
																readonly="readonly" data-msg-required="${subSectorReq}" maxlength="20"/>
														</div>
													</div>
													<!-- /text box -->
												</div>
												<div class="col-md-6 remove-pad">
													<!-- text box -->
													<div class="form-group cf">
														<div class="col-md-5">
															<label for="plotnumber" class="form-lbl mandatory_lbl"><spring:message code="label.ps.plotNo" />  </label>
														</div>
														<div class="col-md-7">
															<form:input path="plotNumber" id="plotnumber"
																name="plotnumber" type="text"
																class="form-control sectorform required "
																readonly="readonly" data-msg-required="${plotNoReq}" maxlength="20"/>
														</div>
													</div>
													<!-- /text box -->
												</div>
											</div>
										</div>
										<!--  Area starts here !!!!!!!!!  -->
										<div class="row">
											<div class="form-group cf">
												<div class="col-md-9">
													<!-- radio button -->
													<div class="col-md-3 remove-pad">
														<div class="radio inline-custom">
															<form:radiobutton path="locations" id="replacement1"
																name="locations" value="area" class="radiobtn" />
															<label for="replacement1" class="custom"><spring:message code="label.ps.area" /> </label>
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
															<label for="area" class="form-lbl mandatory_lbl"><spring:message code="label.ps.area" />
															</label>
														</div>
														<div class="col-md-7">
															<div class="col-md-12 col-xs-12 remove-pad">
																<div class="custom-select-box cf">
																	<form:select path="area" class="required1 required"
																		id="area-select" name="area" disabled="disabled" data-msg-required="${areaReq}">
																		<form:option value=""><spring:message code="option.select"/> </form:option> 
																		<form:options items="${areaList}" />
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
															<label for="area-block" class="form-lbl mandatory_lbl"><spring:message code="label.ps.block" />
															</label>
														</div>
														<div class="col-md-7">
															<form:input path="areablock" type="text" id="area-block"
																name="area-block" class="form-control areaform required"
																readonly="readonly" data-msg-required="${blockReq}" maxlength="20"/>
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
															<label for="sub-area" class="form-lbl mandatory_lbl"><spring:message code="label.ps.subArea" /></label>
														</div>
														<div class="col-md-7">
															<form:input path="subarea" id="sub-area" name="sub-area"
																type="text" class="form-control areaform required "
																readonly="readonly" data-msg-required="${subAreaReq}"  maxlength="20" />
														</div>
													</div>
													<!-- /text box -->
												</div>
												<div class="col-md-6 remove-pad">
													<!-- text box -->
													<div class="form-group cf">
														<div class="col-md-5">
															<label for="area-plotnumber"
																class="form-lbl mandatory_lbl"><spring:message code="label.ps.plotNo" /> </label>
														</div>
														<div class="col-md-7">
															<form:input path="areaPlotNumber" id="area-plotnumber"
																name="area-plotnumber" type="text"
																class="form-control areaform required"
																readonly="readonly" data-msg-required="${plotNoReq}"  maxlength="20"/>
														</div>
													</div>
													<!-- /text box -->
												</div>
											</div>
										</div>
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
																	<form:select path="landUsage" class="required1 required"
																		id="landUsageselect" name="landUsage"  data-msg-required="${landUsageReq}">
																		<form:option value=""><spring:message code="option.select"/> </form:option> 
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

										<div class="row">
											<div class="col-md-12">
												<!-- form-sect-head-->
												<h5 class="form-title"><spring:message code="label.ps.attachment" /> </h5>
											</div>
										</div>
										<!-- /form-sect-head-->
										<div class="row">
											<div class="col-md-12 remove-pad">
												<div class="col-md-6 remove-pad">
													<!-- text box -->
													<div class="form-group cf">
														<div class="col-md-5">
															<label for="emiratesid-back"
																class="form-lbl"> <spring:message code="label.ps.sitePlanDoc" /> </label>
														</div>
														<div class="col-md-7">
															<div class="input-group file-upload">
																<input  type="text"
																	class="form-control" name="emiratesid-back" id="emiratesid-back-file" readonly="readonly" data-msg-required="${sitePlanDocReq}"/>
																<span class="input-group-btn"> <span
																	class="btn btn-file"> <spring:message code="file.browse" />&hellip; 
																	<input	name="sitePlanDocument" type="file" accept="image/jpg, image/JPG,image/JPEG, image/jpeg,image/jpeg,image/gif,image/png, application/pdf,image/x-eps, application/msword" id="sitePlanDocument"  error-size="${filesize}" error-extention="${fileextention}" error-failed="${fileerror}" />
																</span>
																<form:hidden path="sitePlanDocument_name" id="sitePlanDocument_name" value=""/>
																</span>
															</div>
														</div>
													</div>

													<!-- /text box -->
												</div>
											</div>
										</div>
										<!-- ----- uploaded file area starts here --->
											<%-- <div class="row ">
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
																		<a href="${attachments.downloadurl}" target="_blank"> ${attachments.fileName}</a> <!-- please update link and file name/some name -->
																	</div>
																	</c:forEach>
																</div>
															</div>
															<!-- /text box -->
														</div>
													</div>
											</div> --%>
											<div class="row">
	                                            	<div class="form-group cf">
		                                                <div class="col-md-3">
		                                                    <label class="form-lbl form-lbl-text"><spring:message code="label.service.uploadedfiles" />
		                                                    </label>
			                                            </div>
			                                            <div class="col-md-9">
		                                                   <c:forEach items="${attachmentPayLoad}" var="attachments">
																	
		                                                    <c:if test="${attachments.createdby.value=='Site Plan Document'}">
																	<div class="col-md-12 remove-pad attachment-btm">
																		<a href="${attachments.downloadurl.value}" target="_blank" alt="${attachments.fileName.value}" title="${attachments.fileName.value}"> ${attachments.createdby.value}</a> <!-- please update link and file name/some name -->
																	</div>
																 	</c:if> 
															</c:forEach>
															
		                                                </div>
	                                            	</div>
	                                        </div>
											<!-- ----- uploaded file area ends   here --->
											<!-- reviewer atttached file start here -->
											<div class="row">
	                                            	<div class="form-group cf">
		                                                <div class="col-md-3">
		                                                    <label class="form-lbl form-lbl-text"><spring:message code="pws.resubmitattach.reviewer" />
		                                                    </label>
			                                            </div>
			                                            <div class="col-md-9">
														
		                                                   <c:forEach items="${attachmentPayLoad}" var="attachments">
																	<c:if test="${attachments.createdby.value=='Reviewer Supportive Attachment'}"> 
																	<div class="col-md-12 remove-pad attachment-btm">
																		<a href="${attachments.downloadurl.value}" target="_blank" alt="${attachments.fileName.value}" title="${attachments.fileName}"> ${attachments.fileName}</a> <!-- please update link and file name/some name -->
																	</div>
																	</c:if> 
															</c:forEach>
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
										<div class="row">
											<div class="col-md-12 remove-pad">
												<div class="col-md-6 remove-pad">
													<!-- submit button -->
													<div class="row">
														<div class="form-group submission">
															<div class="col-md-offset-5 col-md-7">
															 <c:if test="${status ne '6' }">
																<input type="submit" class="btn" value='<spring:message code="label.eservice.resubmit"/>' />
																</c:if>
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
	<script type="text/javascript"src="/js/libs/jquery.mask.js"></script>


	<script>

	
jQuery(function($) { 
				$("#feedbak").validate();
				
			/* functionality for disabling unselected area */
				window.onload = function(){
					valSelected= document.querySelector('input[name="locations"]:checked').value; 
					$("#emiratesid-back-file").attr("readonly", true);
					
					if(valSelected == "sector"){
						$(".areaform").removeClass("required");
						$(".areaform").attr("readonly", true);
						$(".sectorform").attr("readonly", false);
						$(".sectorform").addClass("required");
						$("#sector-select").attr("disabled", false);
						$("#area-select").attr("disabled", true);
						$("label.error").hide();
					}else{

						$(".sectorform").removeClass("required");
						$(".areaform").attr("readonly", false);
						$(".sectorform").attr("readonly", true);
						$(".areaform").addClass("required");
						$("#sector-select").attr("disabled", true);
						$("#area-select").attr("disabled", false);
						$("label.error").hide();
					}
					
				}
				
			/* end of functionality for disabling unselected area */
			
				$(".radiobtn").click(function(){
					val= document.querySelector('input[name="locations"]:checked').value; 
					if(val == "sector"){
						$(".areaform").removeClass("required");
						$(".areaform").attr("readonly", true);
						$(".sectorform").attr("readonly", false);
						$(".sectorform").addClass("required");
						$("#sector-select").attr("disabled", false);
						$("#area-select").attr("disabled", true);
						$(".areaform").val("");
						$(".sectorform").val("");
						$("label.error").hide();
						$("#sector-select option:first-child").attr("selected", true);
						$("#area-select option:first-child").attr("selected", true);

					}else{

						$(".sectorform").removeClass("required");
						$(".areaform").attr("readonly", false);
						$(".sectorform").attr("readonly", true);
						$(".areaform").val("");
						$(".sectorform").val("");
						$(".areaform").addClass("required");
						$("#sector-select").attr("disabled", true);
						$("#area-select").attr("disabled", false);
						$("label.error").hide();
						$("#sector-select option:first-child").attr("selected", true);
						$("#area-select option:first-child").attr("selected", true);
					}
				});
			});
			
	    </script>
	<!-- /script -->
