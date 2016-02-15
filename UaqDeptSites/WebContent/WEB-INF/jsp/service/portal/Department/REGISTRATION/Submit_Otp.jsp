<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>




<c:set var ="otomsg"><spring:message code="plzEnterOTP"/></c:set>
<c:set var ="otpsuccessmsg"><spring:message code="otpsuccess"/></c:set>
<c:set var ="otpfailuremsg"><spring:message code="otpfailure"/></c:set>

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
								<li class="active"><a href="#"><spring:message code="otpvalidation"/></a>
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
										<spring:message code="otpvalidation"/>
										</h2>
										<!-- /page title -->
									</div>
									<div class="hidden-xs show-sm col-md-2 title-icon">
										<img src="/img/icons/icon-uaq.png" alt="uaq">
									</div>
								</div>
							</div>
							<!-- /page title -->
							<!-- page content -->
						<!-- left col -->
						<!-- <div class="col-xs-12 col-sm-9 col-md-9 main-left-col">
							page title
							<div class="page-title-wrap">
								<div class="row">
									<div class="col-xs-12 col-sm-9 col-md-10 title-txt">
										sub page title 
										<h4 class="page-title-sub">
										MUNICIPALITY
										</h4>
										/sub page title
										page title
										<h2 class="page-title">
											OTP VALIDATION
										</h2>
										/page title
									</div>
									<div class="hidden-xs show-sm col-md-2 title-icon">
										<img src="/img/icons/icon-uaq.png" alt="uaq">
									</div>
								</div>
							</div> -->
							<!-- /page title -->
							<div class="main-content-wrap">
								
								<div class="page-content-wrap">
										<div class="form-content cf">
										<form:form id="otpForm" commandName="validateOTPCommand"  action="/${param.languageCode}/otp.html" method="post" >
										
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
			                                                    <label for="otp" class="form-lbl mandatory_lbl"><spring:message code="OTPtxt"/>
			                                                    </label>
				                                            </div>
				                                            <div class="col-md-7">
																<form:input path="otp" type="password" name="otp" id="otp" class="form-control required"  data-msg-required="${otomsg}" msg_otp_sucsses="${otpsuccessmsg}" msg_otp_error="${otpfailuremsg}" />
			                                                    <form:hidden path="typeOfUser" />
																<form:hidden path="accountId" />
																<form:hidden path="mobile" />
																<form:hidden path="emirateId" />
																<form:hidden path="passportId"  />
																<label id="otpsent" class="error optplbl" style="display:block; margin-top:20px;">${message}</label>
			                                                </div>
		                                            	</div>
		                                        		<!-- /text box -->
		                                        	</div>
													<div class="col-md-6 remove-pad">
														<!-- text box -->
		                                            	<div class="form-group cf">
			                                                <div class="col-md-12">
			                                                    <label for="reotp" class="form-lbl"> <a href="#" id="generateOTP"><spring:message code="sendAnotherOTP"/> </a>
			                                                    </label>
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
				                                                    <input type="submit" class="btn" value=<spring:message code="form.button.submit"/>> 
																	
				                                                </div>
				                                            </div>
				                                        </div>
				                                        <!-- /submit button -->
		                                        	</div>
												</div>
	                                        </div>
	                                         <div class="row">
												<div class="row"> 
													<div class="col-md-9 remove-pad">
													<div class="form-group cf">

											</div>
											</div>
										</div>
										</div>
										</form:form>	
									</div>
							</div>
						</div>
						<!-- /left col -->
					</div>
			

		<!-- script -->
		<!-- <script src="/js/dest/app.js"></script> -->
		<script src="/js/libs/jquery.validate.js"></script>
		<script src="/js/libs/tooltip.js"></script>
		<script src="/js/libs/popover.js"></script>

	    <script>
	    var contexPath = "<%=request.getContextPath() %>";
			jQuery(function($) { 

			$("#otpForm").validate();
			
			$( "#generateOTP" ).click(function(e) {
				var formData = JSON.parse(JSON.stringify(jQuery('#otpForm').serializeArray()));
				
				getOTP(formData);
				e.preventDefault();
				return false;
				 //console.log(formData);
				});
			
			
			
			var image = '<img src="img/help/help1.jpg">';
			$('#popover').popover({placement: 'right', content: image, html: true});
			
			});
			function getOTP(datastring){
				$.ajax({
					
					
				    type: "POST",
				    //contentType : "application/json",
				   
				    url: "/en/generateotp.html",
				    data: $("#otpForm").serialize(),
				   
				    success: function(responce) {
				    	//$("#otp").val(responce);
				    	$("#otpsent").detach();
				    	console.log(responce);
				    	if(responce!=""){
						
						
						datamsg_sucess= $("#otp").attr("msg_otp_sucsses");
				    	}else{
				    		datamsg_sucess= $("#otp").attr("msg_otp_error");
				    	}
						//datamsg_error= $("#otp").attr("msg_otp_error");
						
						$("#otp").after('<label id="otpsent" class="error optplbl" style="display:block; margin-top:20px;">'+datamsg_sucess+' </label>');
				    	
				      
				           }
				      });
			}
			$("#otp").focus(function(){
				$("#otpsent").detach();
			});
	    </script>
		<!-- /script -->
