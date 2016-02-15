<!DOCTYPE html>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html lang="en">
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>UAQ</title>
		<link href="css/app.css" rel="stylesheet">
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
										   <form:form commandName="procardRequestCommand" name="feedbak" id="feedbak" enctype="multipart/form-data" method="post" action="newprocardsave.html" >
										    <div class="row"></div>	
											<div class="row">
												<div class="col-md-12 remove-pad">
													<div class="col-md-6 remove-pad">
														<!-- text box -->
		                                            	<div class="form-group cf">
			                                                <div class="col-md-5">
			                                                    <label for="pro-name" class="form-lbl mandatory_lbl">Name of PRO
			                                                    </label>
				                                            </div>
				                                            <div class="col-md-7">
			                                                    <form:input path="proName" id="pro-name" name="pro-name" type="text" class="form-control required" placeholder=""  />
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
			                                                    <label for="pro-id" class="form-lbl mandatory_lbl">PRO ID Number
			                                                    </label>
				                                            </div>
				                                            <div class="col-md-7">
			                                                    <form:input path="proIdNumber" id="pro-id" name="pro-id" type="text" class="form-control required" placeholder=""  />
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
			                                                    <label for="pro-exp-date" class="form-lbl mandatory_lbl">PRO Expiry Date
			                                                    </label>
				                                            </div>
				                                            <div class="col-md-7">
			                                                    <form:input path="proIdExpiryDate" id="pro-exp-date" name="pro-exp-date" type="date" class="form-control required" placeholder=""  />
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
			                                                    <label for="land-status"  class="form-lbl mandatory_lbl">PRO Nationality</label>
				                                            </div>
				                                            <div class="col-md-7">
			                                                    <div class="custom-select-box cf">
	                                                                <form:select path="proNationality"  class="required1 required" name="land-status" >
	                                                                    <form:option value=""></form:option>
	                                                                   <form:options items="${nationalList}" />
	                                                                </form:select>
	                                                            </div>
			                                                </div>
		                                            	</div>
		                                        		<!-- /text box -->
		                                        	</div>
												</div>
	                                        </div>

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
			                                                    <label for="police-report" class="form-lbl mandatory_lbl">IP Proof of PRO<span class=""> (ID or Passport copy) </span>
			                                                    </label>
				                                            </div>
				                                            <div class="col-md-7">
			                                                    <div class="input-group file-upload">
																	<input type="text" class="form-control required" name ="police-report" readonly="readonly" />
																	<span class="input-group-btn">
																		<span class="btn btn-file">
																			Browse&hellip; 
																			<form:input path="proIdProof" type="file" accept="image/jpg, image/JPG,image/JPEG, image/jpeg,image/jpeg,image/gif,image/png, application/pdf,image/x-eps, application/msword" />
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
			                                                    <label  for="emiratesid-back" class="form-lbl mandatory_lbl">PRO Photograph 
			                                                    </label>
				                                            </div>
				                                            <div class="col-md-7">
																<div class="input-group file-upload">
																	<input type="text" class="form-control required" name="emiratesid-back" readonly="readonly" />
																	<span class="input-group-btn">
																		<span class="btn btn-file">
																			Browse&hellip; 
																			<form:input path="proPhotograph" type="file" accept="image/jpg, image/JPG,image/JPEG, image/jpeg,image/jpeg,image/gif,image/png, application/pdf,image/x-eps, application/msword" />
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
		<script src="js/dest/app.js"></script>
		<script src="js/libs/jquery.validate.js"></script>
		<script src="js/libs/tooltip.js"></script>
		<script src="js/libs/popover.js"></script>

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
				
				}else{

					$(".sectorform").removeClass("required");
					$(".areaform").attr("readonly", false);
					$(".sectorform").attr("readonly", true);
					$(".areaform").addClass("required");
					$("#sector-select").attr("disabled", true);
					$("#area-select").attr("disabled", false);

				}
				
			
			});
		});
		
	</script>
		<!-- /script -->
	</body>
	<!-- /body -->
</html>