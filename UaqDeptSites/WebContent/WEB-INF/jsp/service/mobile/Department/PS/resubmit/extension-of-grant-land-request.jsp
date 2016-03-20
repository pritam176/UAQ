<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
		<c:set var="grantIssDateReq"><spring:message code="label.ps.grantIssDate.required" /></c:set>
		<c:set var="grantExpDateReq"><spring:message code="label.ps.grantExpDate.required" /></c:set>
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
			<%-- <div class="col-xs-12 col-sm-9 col-md-9 main-left-col">
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
								<spring:message code="label.ps.extensiongrantland" />
								<!--- ps page 1 -->
							</h2>
							<!-- /page title -->
						</div>
						<div class="hidden-xs show-sm col-md-2 title-icon">
							<img src="/img/icons/icon-uaq.png" alt="uaq">
						</div>
					</div>
				</div> --%>
				<!-- /page title -->
				<div class="main-content-wrap">

					<div class="page-content-wrap">
						<div class="form-content cf">
							<form:form commandName="extensionOfGrandCommand" id="feedbak"
								enctype="multipart/form-data" method="POST"
								action="resubmitextensiongrandLand.html">
								<div class="row">
								
														 
			                                           <form:hidden id="requestNo" path="requestNo"  />
			                                            <form:hidden id="serviceId" path="serviceId" />
			                                            <form:hidden id="sourcetype" path="sorceType"  />   	   
			                                        	
														<form:hidden id="ownerId" path="ownerId"  />
														<form:hidden id="statusid" path="stausId"  />
														<form:hidden id="ownername" path="ownerName"  />
														<form:hidden id="extGrandLandId" path="extGrandLandId"  />
														
								
								</div>

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
													<form:input id="plan-number" path="sitePlanNumber"
														type="text" class="form-control " data-msg-required="${siteplanReq}"  data-mask="0000000000" />
												</div>
											</div>
											<!-- /text box -->
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-md-12">
										<!-- form-sect-head-->
										<h5 class="form-title"><spring:message code="label.ps.locations" /></h5>
									</div>
									<!-- /form-sect-head-->
								</div>
								<div class="row">
									<div class="form-group cf">

										<div class="col-md-9">
											<!-- radio button -->
											<div class="col-md-3 remove-pad">
												<div class="radio inline-custom">
													<form:radiobutton id="sector-radio" path="locations"
														value="sector" class="radiobtn required" />
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
													<div class="col-md-12  remove-pad">
														<div class="custom-select-box cf">
															<form:select class="required1 required"
																id="sector-select" path="sector" disabled="disabled" data-msg-required="${sectorReq}">
																<form:option value=""><spring:message code="option.select"/> </form:option> 
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
													<form:input type="text" id="block" path="block"
														class="form-control sectorform required"
														readonly="readonly" data-msg-required="${blockReq}" maxlength="20" />
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
													<label for="subsector" class="form-lbl mandatory_lbl"><spring:message code="label.ps.subSector" /> </label>
												</div>
												<div class="col-md-7">
													<form:input id="subsector" path="subsector" type="text"
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
													<label for="plotnumber" class="form-lbl mandatory_lbl"><spring:message code="label.ps.plotNo" /> </label>
												</div>
												<div class="col-md-7">
													<form:input id="plotnumber" path="sectorPlotnumber"
														type="text" class="form-control sectorform required "
														readonly="readonly"  data-msg-required="${plotNoReq}"  data-msg-number="${plotNoReq}" maxlength="20"/>
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
													<form:radiobutton id="replacement1" path="locations"
														value="area" class="radiobtn" />
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
													<label for="area1" class="form-lbl mandatory_lbl"><spring:message code="label.ps.area" /></label>
													
												</div>
												<div class="col-md-7">
													<div class="col-md-12  remove-pad">
														<div class="custom-select-box cf">
															<form:select class="required1 required" id="area-select"
																path="area" disabled="disabled"  data-msg-required="${areaReq}" >
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
													<form:input type="text" id="area-block" path="areaBlock"
														class="form-control areaform required" readonly="readonly" data-msg-required="${blockReq}" maxlength="20" />
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
													<form:input id="sub-area" path="subArea" type="text"
														class="form-control areaform required "
														readonly="readonly" data-msg-required="${subAreaReq}" maxlength="20"/>
												</div>
											</div>
											<!-- /text box -->
										</div>
										<div class="col-md-6 remove-pad">
											<!-- text box -->
											<div class="form-group cf">
												<div class="col-md-5">
													<label for="area-plotnumber" class="form-lbl mandatory_lbl"><spring:message code="label.ps.plotNo" /> </label>
												</div>
												<div class="col-md-7">
													<form:input id="area-plotnumber" path="areaPlotnumber"
														type="text" class="form-control areaform required"
														readonly="readonly" data-msg-required="${plotNoReq}"  data-msg-number="${plotNoReq}" maxlength="20"/>
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
																id="area-select" name="landUsage"  data-msg-required="${landUsageReq}">
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
									<div class="col-md-12 remove-pad">
										<div class="col-md-6 remove-pad">
											<!-- text box -->
											<div class="form-group cf">
												<div class="col-md-5">
													<label for="grand-date" class="form-lbl mandatory_lbl"><spring:message code="label.ps.grantIssueDate" /> </label>
												</div>
												<div class="col-md-7">
													<form:input id="grand-date" path="grandIssueDate"
														type="text" class="form-control required " data-msg-required="${grantIssDateReq}" />
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
													<label for="grand-exp-date" class="form-lbl mandatory_lbl"><spring:message code="label.ps.grantExpDate" /> </label>
												</div>
												<div class="col-md-7">
													<form:input id="grand-exp-date" path="grandExpDate"
														type="text" class="form-control required " data-msg-required="${grantExpDateReq}"/>
												</div>
											</div>
											<!-- /text box -->
										</div>

									</div>
								</div>

								<div class="row">
									<div class="col-md-12">
										<!-- form-sect-head-->
										<h5 class="form-title"><spring:message code="label.ps.attachment" /></h5>
									</div>
								</div>
								<!-- /form-sect-head-->

								<div class="row">
									<div class="col-md-12 remove-pad">
										<div class="col-md-6 remove-pad">
											<!-- text box -->
											<div class="form-group cf">
												<div class="col-md-5">
													<label for="emiratesid-front"
														class="form-lbl "><spring:message code="label.ps.sitePlanDoc" />
													</label>
												</div>
												<div class="col-md-7">
													<div class="input-group file-upload">
														<input type="text" class="form-control "
															name="sitePlanDocument" data-msg-required="${sitePlanDocReq}" readonly="readonly"/> <span class="input-group-btn">
															<span class="btn btn-file"> <spring:message code="file.browse" />&hellip; <input
																type="file" name="sitePlanDocument" id="sitePlanDocument" accept="image/jpg, image/JPG,image/JPEG, image/jpeg,image/jpeg,image/gif,image/png, application/pdf,image/x-eps, application/msword" error-size="${filesize}" error-extention="${fileextention}" error-failed="${fileerror}">
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
											<div class="row ">
													<div class="col-md-12 remove-pad">
														<div class="col-md-6 remove-pad">
															<!-- text box -->
															<div class="form-group cf">
																<div class="col-md-5">
																	<label class="form-lbl form-lbl-text"> <spring:message code="label.service.uploadedfiles" /></label>
																</div>
																<div class="col-md-7">
																	<c:forEach items="${attachmentPayLoad}" var="attachments">
																	<c:if test="${fn:trim(attachments.createdby) eq  fn:trim('Site Plan Document')}">
																	<div class="col-md-12 remove-pad attachment-btm">
																		<a href="${attachments.downloadurl}" target="_blank"> ${attachments.createdby}</a>
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
											<div class="row ">
													<div class="col-md-12 remove-pad">
														<div class="col-md-6 remove-pad">
															<!-- text box -->
															<div class="form-group cf">
																<div class="col-md-5">
																	<label class="form-lbl form-lbl-text"> <spring:message code="label.service.reviwer.uploadedfiles" /> </label>
																</div>
																<div class="col-md-7">
																	<c:forEach items="${attachmentPayLoad}" var="attachments">
																	<c:if test="${fn:trim(attachments.createdby) ne fn:trim('Site Plan Document')}">
																	<div class="col-md-12 remove-pad attachment-btm">
																		<a href="${attachments.downloadurl}" target="_blank"> ${attachments.createdby}</a>
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
				
				var date_today = new Date();
				date_today.setDate(date_today.getDate() - 1);
				var date_today_add = new Date(date_today);
					
				var current_selected_date = $('#grand-date').datepicker('getDate');
				$('#grand-date').datepicker({
					maxDate: date_today_add,	
					onSelect: function (selected) {
						var date2 = $("#grand-date").datepicker('getDate');
						date2.setDate(date2.getDate() + 1);
						$("#grand-exp-date").datepicker('option', 'minDate', date2);
					}
				});
				
				var date_today = new Date();
				date_today.setDate(date_today.getDate() - 1);
				var date_today_add = new Date(date_today);
				
				var current_date2 = $("#grand-date").datepicker('getDate');
				current_date2.setDate(current_date2.getDate() + 1);
				$('#grand-exp-date').datepicker({
					yearRange: '1900:2050',
					dateFormat: 'mm/dd/yy',
					minDate:current_date2,
				});



				
				$("#feedbak").validate();
				window.onload = function(){
					valSelected= document.querySelector('input[name="locations"]:checked').value; 
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
						$(".areaform").addClass("required");
						$("#sector-select").attr("disabled", true);
						$("#area-select").attr("disabled", false);
						$(".areaform").val("");
						$(".sectorform").val("");
						$("label.error").hide();
						$("#sector-select option:first-child").attr("selected", true);
						$("#area-select option:first-child").attr("selected", true);

					}
				});
			});
			
	    </script>
	<!-- /script -->
