
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<c:set var ="filesize"><spring:message code="filesizemsg"/></c:set>
<c:set var ="fileextention"><spring:message code="fileextentionmsg"/></c:set>
<c:set var ="fileerror"><spring:message code="fileerrormsg"/></c:set>

<c:set var ="enterNoc"><spring:message code="plzenternoc"/></c:set>

		<div class="container-fluid">
			<div class="wrapper">
				<!-- content area -->
				
				<div class="mainmenu">
					<div class="col-md-12 hidden-xs hidden-sm">
						<div class="mainmenu-wrap">
							<ul class="no-list cf">
								<li>
									<a href="/${param.languageCode}/home.html"><img src="/img/home1.png" alt="Home UAQ">
									</a>
								</li>
								
								<li class=""><a href="/${param.languageCode}/myrequest.html"><spring:message code="label.myrequest"/></a>
								</li>
								<li class="active"><a href="#"><spring:message code="label.ps.subNoc"/></a>
								</li>
								
							</ul>
						</div>
					</div>
				</div>
				
					<div class="content">
				<div class="row">
						<!-- right col -->
							<div class="col-xs-12 col-sm-3 col-md-3 main-right-col">
						<!-- social media share callout -->
							<!-- DepartmentLists -->
								<jsp:include page="../common/depts_services_rhs.jsp"></jsp:include>
							<!-- /DepartmentLists -->
						<!-- /social media share callout -->

					</div>
				
					<div class="row">
						<!-- left col -->
						<div class="col-xs-12 col-sm-9 col-md-9 main-left-col">
							<!-- page title -->
							<div class="page-title-wrap">
								<div class="row">
									<div class="col-xs-12 col-sm-9 col-md-10 title-txt">
										<!-- page title -->
										<h2 class="page-title"><spring:message code="label.ps.subNoc" />  </h2>
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
										   <form:form commandName="submitNocCommand" name="feedbak" id="feedbak" enctype="multipart/form-data" method="POST" action="submitnoc.html" >
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
			                                                <div class="col-md-5 col-xs-6">
																<label class="form-lbl"><spring:message code="label.lp.requestNo" /> </label> 
				                                            </div>
				                                            <div class="col-md-7 col-xs-6">
																<label class="form-lbl">${requestno}</label> 
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
			                                                <div class="col-md-5 col-xs-6">
																<label class="form-lbl"><spring:message code="label.lp.myDetails" /> </label> 
				                                            </div>
				                                            <div class="col-md-7 col-xs-6">
																<label class="form-lbl ">${firstName}</label>  <a href="#collapsMoreInfo" data-toggle="collapse"   class="pull-right" ><spring:message code="label.lp.moreInfo" /> </a> 
			                                                </div>
		                                            	</div>
		                                        		<!-- /text box -->
		                                        	</div>
												</div>
	                                        </div>	
											<ul class="row collapse" id="collapsMoreInfo" >  <!-- this are will hold the more infor part -->
												<li class="col-md-12 remove-pad">  
													<div class="col-md-6 remove-pad">
														<!-- text box -->
		                                            	<div class="form-group cf">
			                                                <div class="col-md-5 col-xs-6">
																<label class="form-lbl"><spring:message code="label.lp.mydetails2" /></label> 
				                                            </div>
				                                            <div class="col-md-7 col-xs-6">
																<label class="form-lbl">${mobileNo}</label>  
			                                                </div>
		                                            	</div>
		                                        		<!-- /text box -->
		                                        	</div>
												</li>
												<li class="col-md-12 remove-pad">  
													<div class="col-md-6 remove-pad">
														<!-- text box -->
		                                            	<div class="form-group cf">
			                                                <div class="col-md-5 col-xs-6">
																<label class="form-lbl"><spring:message code="label.lp.mydetails3" /></label> 
				                                            </div>
				                                            <div class="col-md-7 col-xs-6">
																<label class="form-lbl">${emailId}</label>  
			                                                </div>
		                                            	</div>
		                                        		<!-- /text box -->
		                                        	</div>
												</li>
												
												
	                                        </ul>  <!-- / More infor part -->
											<div class="row">
												<div class="col-md-12">  <!-- form-sect-head-->
													<h5 class="form-title"><spring:message code="label.lp.requestDetails" /></h5>
												</div> 
											</div>	
											
											<!-- SECTOR/AREA START HERE-->	
											<c:if test="${location =='sector'}">
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
										</c:if>
										<!--  Area starts here !!!!!!!!!  -->
										<c:if test="${location =='area'}">
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
															<label for="area1" class="form-lbl mandatory_lbl"><spring:message code="label.ps.area" />
															</label>
														</div>
														<div class="col-md-7">
															<div class="col-md-12 col-xs-12 remove-pad">
																<div class="custom-select-box cf">
																	<form:select path="area" class="required1 required"
																		id="area-select" name="area1" disabled="disabled" data-msg-required="${areaReq}">
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
										</c:if>				
											<div class="row">
												<div class="col-md-12 remove-pad">
													<div class="col-md-6 remove-pad">
														<!-- text box -->
		                                            	<div class="form-group cf">
			                                                <div class="col-md-5 col-xs-6">
																<label class="form-lbl"><spring:message code="label.ps.landUsage" />  </label> 
				                                            </div>
				                                           <div class="col-md-7">
																<div class="col-md-12 col-xs-12 remove-pad">
																<div class="custom-select-box cf">
																	<form:select path="landUsage" class="required1 required" disabled="disabled"
																		id="landusage" name="landUsage"  data-msg-required="${landUsageReq}">
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
												<div class="col-md-12">  <!-- form-sect-head-->
													<h5 class="form-title"><spring:message code="label.ps.comments" /></h5>
												</div> 
											</div>	<!-- /form-sect-head-->
											<c:if test ="${ not empty optionalComent}">
												<div class="row fmb30">
												<div class="col-md-12">  <!-- form-sect-head-->
													<label class="form-lbl"> ${optionalComent}</label> 
												</div> 
											</div>	
											
											</c:if>
											
											<div class="row fmb30">
												<div class="col-md-12">  <!-- form-sect-head-->
													<label class="form-lbl"><spring:message code="lable.ps.noc.coments"/> ${nocNameComment}</label> 
												</div> 
											</div>	
											
											
											
											<div class="row">
												<div class="col-md-12 remove-pad">
													<div class="col-md-6 remove-pad">
														<!-- text box -->
		                                           	<c:if test="${ not empty nocAttachmentPayLoad}">
														<c:forEach items="${nocAttachmentPayLoad}" var="attachments">
		                                            	<div class="form-group cf">
			                                                <div class="col-md-5 col-xs-6">
																<label class="form-lbl">
																<c:if test="${param.languageCode eq 'en'}">
																${attachments.requestNOCType_EN}
																</c:if>
																<c:if test="${param.languageCode eq 'ar'}">
																${attachments.requestNOCType_Ar}
																</c:if>
																</label> 
				                                            </div>
				                                            <div class="col-md-7 col-xs-6">
																<label class="form-lbl">
																	<a href="${attachments.requestNOCDID}" class="link-btn"><spring:message code="label.ps.download" /></a>
																</label>  
			                                                </div>
		                                            	</div>
		                                            	</c:forEach>
		                                            	</c:if>
		                                        		<!-- /text box -->
		                                        	</div>
												</div>
	                                        </div>	
											
											<div class="row">
												<div class="col-md-12">  <!-- form-sect-head-->
													<h5 class="form-title"><spring:message code="label.rhs.action" /></h5>
												</div> 
											</div>	<!-- /form-sect-head-->
											
												<c:if test="${ not empty nocAttachmentPayLoad}">
														<c:forEach items="${nocAttachmentPayLoad}" var="attachments" varStatus="count">
											<div class="row">
												<div class="col-md-12 remove-pad">
													<div class="col-md-6 remove-pad">
														<!-- text box -->
		                                            	<div class="form-group cf">
			                                                <div class="col-md-5">
			                                             
			                                            
			                                                <c:if test="${param.languageCode eq 'en'}">
															 <label  for="noc_${count.index}" class="form-lbl mandatory_lbl"> <spring:message code="label.ps.subNoc" />	  ${attachments.requestNOCType_EN} </label>
															  <c:set var ="nocName"> ${attachments.requestNOCType_EN}</c:set>
																</c:if>
																<c:if test="${param.languageCode eq 'ar'}">
																 <c:set var ="nocName"> ${attachments.requestNOCType_Ar}</c:set>
															 <label  for="noc_${count.index}" class="form-lbl mandatory_lbl"> <spring:message code="lable.for.noc" />	  ${attachments.requestNOCType_Ar} </label>
																</c:if>
			                                                
			                                                
			                                                
			                                                   
			                                                   
				                                            </div>
				                                            <div class="col-md-7">
																<div class="input-group file-upload">
																	<input type="text" class="form-control required other-form" name="noc_${count.index}" readonly="readonly"  data-msg-required="${enterNoc} &nbsp; ${nocName}" />
																	<span class="input-group-btn">
																		<span class="btn btn-file">
																			<spring:message code="file.browse" />&hellip; 
																			<input  name="subnoc[${count.index}]" id="subnoc_${count.index}" type="file"  accept="image/jpg, image/JPG,image/JPEG, image/jpeg,image/jpeg,image/gif,image/png, application/pdf,image/x-eps, application/msword" error-size="${filesize}" error-extention="${fileextention}" error-failed="${fileerror}" />
																		</span>
																		<form:hidden path="subnoc_name[${attachments.requestNOCID}]" id="subnoc_${count.index}_name" value=""/>
																		
																		
																		
																	</span>
																</div>
			                                                </div>
		                                            	</div>
													
		                                        		<!-- /text box -->
		                                        	</div>	
												</div>
	                                        </div>
												</c:forEach>
		                                            	</c:if>
											
	                                       
	                                        <div class="row">
												<div class="col-md-12 remove-pad">
		                                        	<div class="col-md-6 remove-pad">
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
	</div>
		<!-- script -->
		
		<script src="/js/libs/jquery.validate.js"></script>
	    <script>

			jQuery(function($) { 
				$("#feedbak").validate();
			});
			
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
			
			
	    </script>
	