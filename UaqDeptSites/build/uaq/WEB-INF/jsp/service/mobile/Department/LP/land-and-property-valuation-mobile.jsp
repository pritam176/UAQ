<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>UAQ</title>
		<link href="/css/app.css" rel="stylesheet">
		<!--<link rel="shortcut icon" href="" type="image/x-icon" />-->
		<script src="js/libs/jquery.min.js"></script>
		<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
		<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
		<!--[if lt IE 9]>
		<script src="js/ie.min.js"></script>
		<![endif]-->
	</head>
	<!-- body -->
	<body>
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
										   <form:form commandName="landandPropertyValuationCommand"
										name="feedbak" id="feedbak" enctype="multipart/form-data"
										method="POST" action="landandproperty.html">
										    <div class="row"></div>	
											<div class="row">
	                                            	<div class="form-group cf">
		                                                <div class="col-md-3">
		                                                    <label class="form-lbl">Please indicate your position
		                                                    </label>
			                                            </div>
			                                            <div class="col-md-9">
			                                            	<!-- radio button -->
			                                            	<div class="col-md-3 remove-pad">
			                                                    <div class="radio inline-custom">
		                                                            <form:radiobutton path="indicatePosition" id="owner"  name="container" value="new"/>
		                                                            <label for="owner" class="custom">Owner</label>
		                                                        </div>
															</div>
															<!-- /radio button -->
															<!-- radio button -->
															<div class="col-md-3 remove-pad">
		                                                        <div class="radio inline-custom">
		                                                            <form:radiobutton path="indicatePosition" id="heir"  name="container" value="replacement"/>
		                                                            <label for="heir" class="custom">Heir</label>
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
			                                                    <label for="land-status"  class="form-lbl mandatory_lbl">Land Status</label>
				                                            </div>
				                                            <div class="col-md-7">
			                                                    <div class="custom-select-box cf">
	                                                                <form:select path="landStatus" class="required1 required" name="land-status" >
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
			                                                    <label for="land-type" class="form-lbl mandatory_lbl">Land Type</label>
				                                            </div>
				                                            <div class="col-md-7">
			                                                   <div class="custom-select-box cf">
	                                                                <form:select path="landType"  class="required1 required" name="land-type" >
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
			                                                    <label for="plan-number" class="form-lbl mandatory_lbl">Site Plan Number <span class="form-lbl-subtxt"> (Required only if the land is owned by the applicant)</span>
			                                                    </label>
				                                            </div>
				                                            <div class="col-md-7">
															    <div class="col-md-4 col-xs-12 remove-pad">
																	<form:input path="sitePlanNumber" id="plan-number" name="plan-number" type="number"  class="form-control required" />
																</div>
			                                                </div>
		                                            	</div>
		                                        		<!-- /text box -->
		                                        	</div>
												</div>
	                                        </div>
											<div class="row">
												<div class="col-md-12">  <!-- form-sect-head-->
													<h5 class="form-title">Locations (Choose only one of the three options below)</h5>
												</div> <!-- /form-sect-head-->
											</div>	
											<div class="row">
												<div class="form-group cf">
												
													<div class="col-md-9">
														<!-- radio button -->
														<div class="col-md-3 remove-pad">
															<div class="radio inline-custom">
																<form:radiobutton path="location" id="sector-radio"  name="locations" value="sector" class="radiobtn required"  />
																<label for="sector-radio" class="custom">Sector</label>
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
			                                                    <label for="sector1" class="form-lbl mandatory_lbl">Sector
			                                                    </label>
				                                            </div>
				                                            <div class="col-md-7">
																<div class="col-md-12 col-xs-12 remove-pad">
																	<div class="custom-select-box cf">
																		<form:select path="sector" class="required1 required" id="sector-select" name="sector1"  disabled="disabled">
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
			                                                    <label  for="block" class="form-lbl mandatory_lbl">Block
			                                                    </label>
				                                            </div>
				                                            <div class="col-md-7">
																<form:input path="sectorBlock"  type="text" id="block" name="block" class="form-control sectorform required"  readonly="readonly" />
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
			                                                    <label for="subsector" class="form-lbl mandatory_lbl">Sub Sector
			                                                    </label>
				                                            </div>
				                                            <div class="col-md-7"> 
			                                                    <form:input path="subSector" id="subsector" name="subsector" type="text" class="form-control sectorform required"  readonly="readonly" />
			                                                </div>
		                                            	</div>
		                                        		<!-- /text box -->
		                                        	</div>												
													<div class="col-md-6 remove-pad">
														<!-- text box -->
		                                            	<div class="form-group cf">
			                                                <div class="col-md-5">
			                                                    <label for="plotnumber" class="form-lbl mandatory_lbl">Plot Number
			                                                    </label>
				                                            </div>
				                                            <div class="col-md-7"> 
			                                                    <form:input path="sectorPlotNumber" id="plotnumber" name="plotnumber" type="text" class="form-control sectorform required "  readonly="readonly" />
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
																<form:radiobutton path="location" id="replacement1"  name="locations" value="area" class="radiobtn"/>
																<label for="replacement1" class="custom">Area</label>
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
			                                                    <label for="area1" class="form-lbl mandatory_lbl">Area
			                                                    </label>
				                                            </div>
				                                            <div class="col-md-7">
																<div class="col-md-12 col-xs-12 remove-pad">
																	<div class="custom-select-box cf">
																		<form:select path="area"  class="required1 required" id="area-select" name="area1" disabled="disabled"  >
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
			                                                    <label  for="area-block" class="form-lbl mandatory_lbl">Block
			                                                    </label>
				                                            </div>
				                                            <div class="col-md-7">
																<form:input path="areaBlock"  type="text" id="area-block" name="area-block" class="form-control areaform required" readonly="readonly" />
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
			                                                    <label for="sub-area" class="form-lbl mandatory_lbl">Sub Area
			                                                    </label>
				                                            </div>
				                                            <div class="col-md-7"> 
			                                                    <form:input path="subArea" id="sub-area" name="sub-area" type="text" class="form-control areaform required " readonly="readonly" />
			                                                </div>
		                                            	</div>
		                                        		<!-- /text box -->
		                                        	</div>												
													<div class="col-md-6 remove-pad">
														<!-- text box -->
		                                            	<div class="form-group cf">
			                                                <div class="col-md-5">
			                                                    <label for="area-plotnumber" class="form-lbl mandatory_lbl">Plot Number
			                                                    </label>
				                                            </div>
				                                            <div class="col-md-7"> 
			                                                    <form:input path="areaPlotNumber" id="area-plotnumber" name="area-plotnumber" type="text" class="form-control areaform required" readonly="readonly" />
			                                                </div>
		                                            	</div>
		                                        		<!-- /text box -->
		                                        	</div>
												</div>
	                                        </div>
											<!--  /Area starts here !!!!!!!!!  -->
											
											<div class="row">
												<div class="col-md-12">  <!-- form-sect-head-->
													<h5 class="form-title">Attachment</h5>
												</div> 
											</div>	<!-- /form-sect-head-->

											<div class="row">
												<div class="col-md-12 remove-pad">
													<div class="col-md-6 remove-pad">
														<!-- text box -->
		                                            	<div class="form-group cf">
			                                                <div class="col-md-5">
			                                                    <label for="emiratesid-front" class="form-lbl mandatory_lbl">Ownership Certificate
			                                                    </label>
				                                            </div>
				                                            <div class="col-md-7">
			                                                    <div class="input-group file-upload">
																	<form:input path="" type="text" class="form-control required" name ="emiratesid-front" readonly="readonly" />
																	<span class="input-group-btn">
																		<span class="btn btn-file">
																			Browse&hellip; 
																			<form:input path="ownerCertificate" type="file" accept="image/jpg, image/JPG,image/JPEG, image/jpeg,image/jpeg,image/gif,image/png, application/pdf,image/x-eps, application/msword"/>
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
			                                                    <label  for="emiratesid-back" class="form-lbl mandatory_lbl">Site Plan Document
			                                                    </label>
				                                            </div>
				                                            <div class="col-md-7">
																<div class="input-group file-upload">
																	<form:input path="" type="text" class="form-control required" name="emiratesid-back" readonly="readonly" />
																	<span class="input-group-btn">
																		<span class="btn btn-file">
																			Browse&hellip; 
																			<form:input path="sitePlanDocument" type="file" accept="image/jpg, image/JPG,image/JPEG, image/jpeg,image/jpeg,image/gif,image/png, application/pdf,image/x-eps, application/msword"/>
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
				                                                    <input type="submit" class="btn" value="Submit" /> 
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
		<!-- script -->
		<script src="/js/dest/app.js"></script>
		<script src="/js/libs/jquery.validate.js"></script>
		<script src="/js/libs/tooltip.js"></script>
				<script src="/js/libs/popover.js"></script>

	    <script>

			jQuery(function($) { 

			$("#feedbak").validate();
			
			$(".radiobtn").click(function(){
				val= document.querySelector('input[name="locations"]:checked').value; 
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
		<!-- /script -->
	</body>
	<!-- /body -->
</html>