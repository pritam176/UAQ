<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type="text/javascript">
		
<c:set var="valMobile"><spring:message code="plzMobileNumber" /></c:set>
<c:set var="valMobileLength"><spring:message code="actveemobile.length" /></c:set>
<c:set var="valLength"><spring:message code="validate.length" /></c:set>
<c:set var ="plzPassportmsg"><spring:message code="plzPassportNum"/></c:set>
<c:set var ="plzTradeLicNummsg"><spring:message code="plzTradeLicNum"/></c:set>
<c:set var ="emiratesMsg"><spring:message code="plzChooseEmirates"/></c:set>
<c:set var="emipass"><spring:message code='emirateorpassport'/></c:set>
	
<c:set var ="validemailMsg"><spring:message code="validemail"/></c:set>
		</script>
		
		<div class="container-fluid">
			<div class="wrapper">
				<!-- header -->
				
				<!-- /header -->
					<!--mainmenu -->
				<div class="mainmenu">
					<div class="col-md-12 hidden-xs hidden-sm">
						<div class="mainmenu-wrap">
							<ul class="no-list cf">
								<li>
									<a href="/${param.languageCode}/home.html"><img src="/img/home1.png" alt="Home UAQ">
									</a>
								</li>
								<li class=""><a href="/${param.languageCode}/registrationlanding.html"><spring:message code="registration" /></a>
								</li>
								<li class="active"><a href="#"><spring:message code="activateAcounttxt"/></a>
								</li>
								
							</ul>
						</div>
					</div>
				</div>
				<!--/mainmenu -->
				<!-- content area -->
				<div class="content">
					<div class="row">
						
						<!-- left col -->
						<div class="col-xs-12 col-sm-9 col-md-9 main-left-col">
							<!-- page title -->
							<div class="page-title-wrap">
								<div class="row">
									<div class="col-xs-12 col-sm-9 col-md-10 title-txt">
										<!-- page title -->
										<h2 class="page-title">
										<spring:message code="activateAcounttxt"/>
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
										   <form:form action="accountactivation.html"  commandName="activation" class="eserviceform" method="post" id="activation">
										    <div class="row">
												<div class="row"> 
													<div class="col-md-12 remove-pad"><div class="form-group cf"><label class=" pull-right"></label> </div></div>
												</div>
											</div>
											<div class="row">
												<div class="col-md-12 remove-pad">
													<div class="col-md-6 remove-pad">
														<!-- text box -->
		                                            	<div class="form-group cf">
			                                                <div class="col-md-5">
																<div class="radio inline-custom">
																	<form:radiobutton path="userType" id="regtype1" name="regtype" value="1" class="radiobtn" checked="checked"/>
																	
		                                                            <label for="regtype1" class="custom"><spring:message code="individualUserTxt"/></label>
																</div>
				                                            </div>			                                                
															<div class="col-md-7">
																<div class="radio inline-custom">
																	<form:radiobutton path="userType" id="regtype2" value="2" name="regtype" class="radiobtn" />
																	
																	<label for="regtype2" class="custom"><spring:message code="establishUserTxt"/></label>
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
			                                                    <label for="mobile" class="form-lbl mandatory_lbl"><spring:message code="mobileNumlbl"/>
			                                                    </label>
				                                            </div>
				                                            <div class="col-md-7">
																 <form:input path="mobileNumber" id="mobile" name="mobile"  class="form-control  required " data-rule-maxlength="11" data-rule-minlength="11" data-msg-required="${valMobile}" data-msg-number="${valMobile}"
																	data-msg-maxlength="${valMobileLength}" 
																	data-msg-minlength="${valMobileLength}" data-mask="000-0000000"/>
			                                                  
			                                                </div>
		                                            	</div>
		                                        		<!-- /text box -->
		                                        	</div>
												</div>
	                                        </div>
											<div class="row indiformelem">
												<div class="col-md-12 remove-pad">
													<div class="col-md-6 remove-pad">
														<!-- text box -->
		                                            	<div class="form-group cf">
			                                                <div class="col-md-5">
			                                                    <label for="emailpassport" class="form-lbl mandatory_lbl"><spring:message code="emiratedId"/>
			                                                    </label>
																<span class="either-label"> <spring:message code="lable.or"/> </span>
				                                            </div>
				                                            <div class="col-md-7">
																<form:input path="emirateId" id="emirateId" name="emirateId"  class="form-control indiform either_group indiform-either"  data-rule-maxlength="18" data-rule-minlength="18"
															 data-msg-required="${emaritesid}"
																	data-msg-maxlength="${valLength}" 
																	data-msg-minlength="${valLength}" data-mask="000-0000-0000000-0"/>
			                                                   
			                                                </div>
		                                            	</div>
		                                        		<!-- /text box -->
		                                        	</div>
												</div>
	                                        </div> 	
											
											<div class="row indiformelem">
												<div class="col-md-12 remove-pad">
													<div class="col-md-6 remove-pad">
														<!-- text box -->
		                                            	<div class="form-group cf">
			                                                <div class="col-md-5">
			                                                    <label for="passport" class="form-lbl mandatory_lbl"><spring:message code="passportNum"/>
			                                                    </label>
				                                            </div>
				                                            <div class="col-md-7">
																<form:input path="passportId" id="passportId" name="passportId" class="form-control indiform indiform-either either_group" data-msg-required="${plzPassportmsg}" data-msg-either_group="${emipass}" maxlength="20"/>
			                                                    
			                                                </div>
		                                            	</div>
		                                        		<!-- /text box -->
		                                        	</div>
												</div>
	                                        </div> 
	                                        
	                                        <div class="row estformelem">
												<div class="col-md-12 remove-pad">
													<div class="col-md-6 remove-pad">
														<!-- text box -->
		                                            	<div class="form-group cf">
			                                                <div class="col-md-5">
			                                                    <label for="tradelicence" class="form-lbl mandatory_lbl"><spring:message code="trade.licence.number"/>
			                                                    </label>
				                                            </div>
				                                            <div class="col-md-7">
			                                                    <form:input id="tradelicence" path="tradelicence" type="text" class="form-control required" data-msg-required="${plzTradeLicNummsg}" maxlength="20" />
			                                                </div>
		                                            	</div>
		                                        		<!-- /text box -->
		                                        	</div>
												</div>
	                                        </div> 	
	                                        
	                                        
	                                        <div class="row estformelem">
												<div class="col-md-12 remove-pad">
													<div class="col-md-6 remove-pad">
														<!-- text box -->
		                                            	<div class="form-group cf">
			                                                <div class="col-md-5">
			                                                    <label for="emirates" class="form-lbl estform mandatory_lbl"><spring:message code="chooseEmirates"/>
			                                                    </label>
				                                            </div>
				                                            <div class="col-md-7">
			                                                     <div class="custom-select-box cf">
	                                                               <form:select path="emirate" class="required1 family-fld required" data-msg-required="${emiratesMsg}">
																	<form:option value=""><spring:message code="selectEmirates"/></form:option>
																		<form:options items="${emirateList}" />
																	</form:select>
	                                                            </div>
			                                                </div>
		                                            	</div>
		                                        		<!-- /text box -->
		                                        	</div>
												</div>
	                                        </div> 
											<!-- As Per Giressh failur messge -->
	                                        <div class="row">
												<div class="row"> 
													<div class="col-md-9 remove-pad">
													<div class="form-group cf">

													<label style="text-align: center;" class="submit-faild-error" >${message}</label>
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
				                                                    <input type="submit" class="btn" value='<spring:message code="form.button.submit"/>' /> 
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
				
				<!-- /content area -->
				
		<!-- script -->
		<!-- <script src="/js/dest/app.js"></script> -->
		<script src="/js/libs/jquery.validate.js"></script>
		<script src="/js/libs/additional-methods.min.js"></script>
		<script type="text/javascript"src="/js/libs/jquery.mask.js"></script>

	    <script>

			jQuery(function($) { 
				$("#activation").validate( {
					
					rules: {
						emirateId: {
							require_from_group: [1, ".either_group"]
						},
						passportId: {
							require_from_group: [1, ".either_group"]
						}
					}
					
				});
				
				
				$('input, textarea, select').focus(function() {
					$(".submit-faild-error").hide();
				}).blur(function(){
					$(".submit-faild-error").hide();
				});
				
				var datamsgeither = $("#passportId").attr("data-msg-either_group");
				jQuery.extend(jQuery.validator.messages, {		 
				require_from_group: jQuery.format(datamsgeither)
				});
			
				//$(".estformelem").hide();
				
				val2= document.querySelector('input[name="userType"]:checked').value;
				if(val2 == 2){
						$(".estform").addClass("required");
						//$(".indiform").removeClass("required");
						$(".indiform").val("");
						$(".estformelem").show();
						$(".indiformelem").hide();
						$("label.error").hide();
						$(".indiform-either").removeClass("either_group");
						$('input[name="emirateId"]').rules('remove');
						$('input[name="passportId"]').rules('remove');
                       

					}else{

						$(".estform").removeClass("required");
						$(".estform").val("");
						$("#tradelicence").val("");
						//$(".indiform").addClass("required");
						$(".estformelem").hide();
						$(".indiformelem").show();
						$("label.error").hide();
						$(".indiform-either").addClass("either_group");
						
						$('input[name="emirateId"]').rules('add', {
							require_from_group: [1, ".either_group"]
						});
						 $('input[name="passportId"]').rules('add', {
							require_from_group: [1, ".either_group"]
						});

					}
				
				$(".radiobtn").click(function(){
					$(".submit-faild-error").hide();
					val1= document.querySelector('input[name="userType"]:checked').value; 
					if(val1 == 2){
						$(".estform").addClass("required");
						//$(".indiform").removeClass("required");
						$(".indiform").val("");
						$(".estformelem").show();
						$(".indiformelem").hide();
						$("label.error").hide();
						$(".indiform-either").removeClass("either_group");
						$('input[name="emirateId"]').rules('remove');
						$('input[name="passportId"]').rules('remove');
                        

					}else{

						$(".estform").removeClass("required");
						$(".estform").val("");
						$("#tradelicence").val("");
						//$(".indiform").addClass("required");
						$(".estformelem").hide();
						$(".indiformelem").show();
						$("label.error").hide();
						$(".indiform-either").addClass("either_group");
						
						$('input[name="emirateId"]').rules('add', {
							require_from_group: [1, ".either_group"]
						});
						 $('input[name="passportId"]').rules('add', {
							require_from_group: [1, ".either_group"]
						});

					}
				});
			});
	    </script>