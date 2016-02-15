<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
								<li class=""><a href=""><spring:message code="dept.lbl.department" /></a>
								</li>
								<li class="active"><a href="#"><spring:message code="label.lp.landProperty" /></a>
								</li>
								
							</ul>
						</div>
					</div>
				</div>
				<!-- content area -->
				<div class="content">
				
				<c:set var="landStatusReq"><spring:message code="label.lp.landStatus.required" /></c:set>
				<c:set var="landTypeReq"><spring:message code="label.lp.landType.required" /></c:set>
				<c:set var="siteplanReq"><spring:message code="label.ps.siteplanNo.required" /></c:set>
				<c:set var="sectorReq"><spring:message code="label.ps.sector.required" /></c:set>
				<c:set var="blockReq"><spring:message code="label.ps.block.required" /></c:set>	
				<c:set var="subSectorReq"><spring:message code="label.ps.subSector.required" /></c:set>	
				<c:set var="plotNoReq"><spring:message code="label.ps.plotNo.required" /></c:set>	
				<c:set var="areaReq"><spring:message code="label.ps.area.required" /></c:set>
				<c:set var="subAreaReq"><spring:message code="label.ps.subArea.required" /></c:set>
				<c:set var="ownerShipReq"><spring:message code="label.lp.uploadOwnership.required" /></c:set>
				<c:set var="sitePlanDocReq"><spring:message code="label.ps.sitePlanDoc.required" /></c:set>
				
				
					<div class="row">
						<!-- right col -->
						<div class="col-xs-12 col-sm-3 col-md-3 main-right-col">
							<!-- social media share callout -->
							<div class="callout-wrap">
								<div class="callout-content">
									<div class="social-share-wrap">
										<ul>
											<li>
												<a href="#"><img src="/img/icons/icon-rss.png" alt="uaq"></a>
											</li>
											<li>
												<a href="#"><img src="/img/icons/icon-fb.png" alt="uaq"></a>
											</li>
											<li>
												<a href="#"><img src="/img/icons/icon-twitter.png" alt="uaq"></a>
											</li>
											<li>
												<a href="#"><img src="/img/icons/icon-print.png" alt="uaq"></a>
											</li>
											<li>
												<a href="#"><img src="/img/icons/icon-email.png" alt="uaq"></a>
											</li>
											<li>
												<a href="#"><img src="" alt="">
													<div class="dropdown">
														<button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
															<spring:message code="label.a" />
															<span class="caret"></span>
														</button>
														<ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
															<li><a href="#">	<spring:message code="label.rhs.action" /></a>
															</li>
															<li><a href="#"><spring:message code="label.rhs.another_action" /></a>
															</li>
															<li><a href="#"><spring:message code="label.rhs.something_else_here" /></a>
															</li>
															<li><a href="#"><spring:message code="label.rhs.separated_link" /></a>
															</li>
														</ul>
													</div>
												</a>
											</li>
										</ul>
									</div>
								</div>	
							</div>
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
												<spring:message code="label.lp.landProperty" />           <!--- BPM03.1.01: -->
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
										   	<form:form commandName="landandPropertyValuationCommand"
										name="feedbak" id="feedbak" enctype="multipart/form-data"
										method="POST" action="landandpropertyvalution.html">
										    <div class="row"></div>	
											<div class="row">
	                                            	<div class="form-group cf">
		                                                <div class="col-md-3">
		                                                    <label class="form-lbl"><spring:message code="label.lp.indicatePosition" />
		                                                    </label>
			                                            </div>
			                                            <div class="col-md-9">
			                                            	<!-- radio button -->
			                                            	<div class="col-md-3 remove-pad">
			                                                    <div class="radio inline-custom">
		                                                           <form:radiobutton path="indicatePosition" id="owner"
																name="container" value="new" />
		                                                            <label for="owner" class="custom"><spring:message code="label.lp.owner" /></label>
		                                                        </div>
															</div>
															<!-- /radio button -->
															<!-- radio button -->
															<div class="col-md-3 remove-pad">
		                                                        <div class="radio inline-custom">
		                                                            <form:radiobutton path="indicatePosition" id="heir"
																name="container" value="replacement" />
		                                                            <label for="heir" class="custom"><spring:message code="label.lp.heir" /></label>
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
			                                                    <label for="land-status"  class="form-lbl mandatory_lbl"><spring:message code="label.lp.landStatus" /></label>
				                                            </div>
				                                            <div class="col-md-7">
			                                                    <div class="custom-select-box cf">
	                                                                <form:select path="landStatus"
																	class="required1 required" name="land-status" 
																	data-msg-required="${landStatusReq}">
																	<form:option value=""></form:option>
																	<form:options items="${landStatus}" />
																</form:select>
	                                                            </div>
			                                                </div>
		                                            	</div>
		                                        		<!-- /text box -->
		                                        	</div>
		                                        	<div class="col-md-6 remove-pad">
														<!-- text box -->
		                                            	<div class="form-group cf">
			                                                <div class="col-md-5">
			                                                    <label for="land-type" class="form-lbl mandatory_lbl"><spring:message code="label.lp.landType" /></label>
				                                            </div>
				                                            <div class="col-md-7">
			                                                   <div class="custom-select-box cf">
	                                                               	<form:select path="landType" class="required1 required"
																	name="land-type" data-msg-required="${landTypeReq}">
																	<form:option value=""></form:option>
																	<form:options items="${landTypeList}" />
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
													<div class="col-md-6 remove-pad">
														<!-- text box -->
														<div class="form-group cf">
			                                                <div class="col-md-5">
			                                                    <label for="plan-number" class="form-lbl mandatory_lbl"><spring:message code="label.ps.siteplanNo" /><span class="form-lbl-subtxt"> <spring:message code="label.ps.land.owned" /> </span>
			                                                    </label>
				                                            </div>
				                                            <div class="col-md-7">
															    <div class="col-md-4 col-xs-12 remove-pad">
																	<form:input path="sitePlanNumber" id="plan-number"
																	name="plan-number" type="number"
																	class="form-control required"  data-msg-required="${siteplanReq}"/>
																</div>
			                                                </div>
		                                            	</div>
		                                        		<!-- /text box -->
		                                        	</div>
												</div>
	                                        </div>
											<div class="row">
												<div class="col-md-12">  <!-- form-sect-head-->
													<h5 class="form-title"><spring:message code="label.ps.locations" /></h5>
												</div> <!-- /form-sect-head-->
											</div>	
											<div class="row">
												<div class="form-group cf">
												
													<div class="col-md-9">
														<!-- radio button -->
														<div class="col-md-3 remove-pad">
															<div class="radio inline-custom">
															<form:radiobutton path="location" id="sector-radio"
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
																		id="sector-select" name="sector1" disabled="disabled" 
																		data-msg-required="${sectorReq}">
																		<form:option value=""></form:option>
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
			                                                    <label  for="block" class="form-lbl mandatory_lbl"><spring:message code="label.ps.block" />
			                                                    </label>
				                                            </div>
				                                            <div class="col-md-7">
																<form:input path="sectorBlock" type="text" id="block"
																name="block" class="form-control sectorform required"
																readonly="readonly"  data-msg-required="${blockReq}"/>
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
			                                   <form:input path="subSector" id="subsector"
																name="subsector" type="text"
																class="form-control sectorform required"
																readonly="readonly" data-msg-required="${subSectorReq}"/>
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
			                                                    <form:input path="sectorPlotNumber" id="plotnumber"
																name="plotnumber" type="text"
																class="form-control sectorform required "
																readonly="readonly" data-msg-required="${plotNoReq}"/>
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
																<form:radiobutton path="location" id="replacement1"
																name="locations" value="area" class="radiobtn" />
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
																		<form:select path="area" class="required1 required"
																		id="area-select" name="area1" disabled="disabled" 
																		data-msg-required="${areaReq}">
																		<form:option value=""></form:option>
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
			                                                    <label  for="area-block" class="form-lbl mandatory_lbl"><spring:message code="label.ps.block" />
			                                                    </label>
				                                            </div>
				                                            <div class="col-md-7">
																<form:input path="areaBlock" type="text" id="area-block"
																name="area-block" class="form-control areaform required"
																readonly="readonly" data-msg-required="${blockReq}" />
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
			                                                  <form:input path="subArea" id="sub-area" name="sub-area"
																type="text" class="form-control areaform required "
																readonly="readonly" data-msg-required="${subAreaReq}"/>
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
			                                                   <form:input path="areaPlotNumber" id="area-plotnumber"
																name="area-plotnumber" type="text"
																class="form-control areaform required" readonly="readonly" data-msg-required="${plotNoReq}"/>
			                                                </div>
		                                            	</div>
		                                        		<!-- /text box -->
		                                        	</div>
												</div>
	                                        </div>
											<!--  /Area starts here !!!!!!!!!  -->
											
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
			                                                    <label for="emiratesid-front" class="form-lbl mandatory_lbl"><spring:message code="label.lp.ownershipCert" />
			                                                    </label>
				                                            </div>
				                                            <div class="col-md-7">
			                                                    <div class="input-group file-upload">
																		<form:input path="" type="text"
																	class="form-control required" name="emiratesid-front"
																	readonly="readonly" data-msg-required="${ownerShipReq}" />
																	<span class="input-group-btn">
																		<span class="btn btn-file">
																			<spring:message code="file.browse" />&hellip; 
																			<form:input
																			path="ownerCertificate" type="file"/>
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
			                                                    <label  for="emiratesid-back" class="form-lbl mandatory_lbl"><spring:message code="label.ps.sitePlanDoc" />
			                                                    </label>
				                                            </div>
				                                            <div class="col-md-7">
																<div class="input-group file-upload">
																	<form:input path="" type="text"	class="form-control required" name="emiratesid-back"
																	readonly="readonly" 
																	data-msg-required="${sitePlanDocReq}" />
																	<span class="input-group-btn">
																		<span class="btn btn-file">
																			 <spring:message code="file.browse" />&hellip; 
																			<form:input
																			path="sitePlanDocument" type="file"/>
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
		<!-- script -->
		<script src="/js/dest/app.js"></script>
		<script src="/js/libs/jquery.validate.js"></script>
		<script src="/js/libs/tooltip.js"></script>
				<script src="/js/libs/popover.js"></script>

	    <script>

			jQuery(function($) { 

			$("#feedbak").validate();
			
			$(".radiobtn").click(function(){
				val= document.querySelector('input[name="location"]:checked').value; 
				if(val == "sector"){
					//$("label.error").hide();
					$(".areaform").removeClass("required");
					$(".areaform").attr("readonly", true);
					$(".sectorform").attr("readonly", false);
					$(".sectorform").addClass("required");
					$("#sector-select").attr("disabled", false);
					$("#area-select").attr("disabled", true);
					//document.getElementById("forgot-btn").disabled = false;
					
				
				}else{
					
					//$("label.error").hide();
					$(".sectorform").removeClass("required");
					$(".areaform").attr("readonly", false);
					$(".sectorform").attr("readonly", true);
					$(".areaform").addClass("required");
					$("#sector-select").attr("disabled", true);
					$("#area-select").attr("disabled", false);
					//document.getElementById("forgot-btn").disabled = false;

				}
				
				//alert(val);
			
			});
			/// $('#feedbak :input').attr('readonly','');
			});
			
	    </script>