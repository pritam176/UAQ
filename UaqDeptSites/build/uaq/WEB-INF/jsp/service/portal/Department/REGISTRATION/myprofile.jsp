<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
	
	<div class="mainmenu">
					<div class="col-md-12 hidden-xs hidden-sm">
						<div class="mainmenu-wrap">
							<ul class="no-list cf">
								<li>
									<a href="/${param.languageCode}/home.html"><img src="/img/home1.png" alt="Home UAQ">
									</a>
								</li>
								
								<li class="active"><a href="#"><spring:message code="label.profile"/></a>
								</li>
								
							</ul>
						</div>
					</div>
				</div>
						<!--breadcrumbs -->
					<div class="col-md-12 hidden-xs">
						<div class="breadcrumb-wrap">
							<ul class="no-list cf">
								<li>
									<a href="/${param.languageCode}/home.html"><img src="/img/home1.png" alt="Home UAQ">
									</a>
								</li>
								<li class="active"><a href="#"><spring:message code="label.profile"/></a>
								</li>
								
							</ul>
						</div>
					</div>
					<div class="col-md-12 visible-xs ">
					<div class="mob-breadcrumb-wrap green">

				<spring:message code="label.detail.business" />	

					</div>
					</div>
				<!--/breadcrumbs -->
				<!-- content area -->
				<div class="content">
					<div class="row">
						<!-- right col -->
						<div class="col-xs-12 col-sm-3 col-md-3 main-right-col">
							<!-- social media share callout -->
				
							<!-- /social media share callout -->
							<!-- welcome user -->
							<div class="callout-wrap">
								<div class="callout-content">
									<div class="welcome-user">
										<p> <spring:message code="label.welcome" /></p>
										<h3> ${accountDetailfromToken.firstName}</h3>
										<a href="/${param.languageCode}/logout.html" class="link_text"><spring:message code="label.logout" /></a>
									</div>
								</div>	
							</div>
							<!-- /welcome user -->
							<!-- callout -->
							<div class="callout-wrap">
								<div class="callout-head">
									<h5 class="right-nav-title"><spring:message code="label.profile" /></h5>
								</div>
								<div class="callout-content">
									<div class="right-nav">
			                            <ul>
			                                <li>
			                                	<a href="#"><spring:message code="label.myprofile" /></a>
			                                </li>
			                                <li class="active">
			                                	<a href="/${param.languageCode}/myrequest.html"><spring:message code="label.myrequest" /></a>
			                                </li>
			                            </ul>
			                        </div>
								</div>	
							</div>
							<!-- /callout -->		

							
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
											<spring:message code="label.myprofile" />
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
										<div class="profile-content cf">
											<div class="row">
												<div class="col-md-12 remove-pad">
													<div class="col-md-6 remove-pad">
														<!-- text box -->
		                                            	<div class="form-group cf">
			                                                <div class="col-md-12">
			                                                    <label for="first_name"  class="profile-lbl"> <spring:message code="fullNameBook" /></label>
				                                            </div>
				                                            <div class="col-md-12 profile-val">${accountDetailfromToken.firstName} </div>
		                                            	</div>
		                                        		<!-- /text box -->
		                                        	</div>
		                                        	<div class="col-md-6 remove-pad">
														<!-- text box -->
		                                            	<div class="form-group cf">
			                                                <div class="col-md-12">
			                                               
			                                                 <c:if test="${accountDetailfromToken.dob != '1800-01-01'}">
			                                                    <label for="first_name"  class="profile-lbl"><spring:message code="dob" /></label>
			                                                    </c:if>
			                                                
				                                            </div>
				                                            
				                                             <c:if test="${accountDetailfromToken.dob != '1800-01-01'}">
															<div class="col-md-12 profile-val">${accountDetailfromToken.dob}</div>
															</c:if>
														
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
			                                                <div class="col-md-12">
			                                                <c:if test="${not empty accountDetailfromToken.countryidofresidency}">
			                                                    <label for="first_name"  class="profile-lbl"><spring:message code="countryResidence" /></label>
			                                                    </c:if>
				                                            </div>
				                                            <c:if test="${ not empty accountDetailfromToken.countryidofresidency}">
				                                            <div class="col-md-12 profile-val">${accountDetailfromToken.countryidofresidency} </div>
				                                            </c:if>
		                                            	</div>
		                                        		<!-- /text box -->
		                                        	</div>
		                                        	<div class="col-md-6 remove-pad">
														<!-- text box -->
		                                            	<div class="form-group cf">
			                                                <div class="col-md-12">
			                                                <c:if test="${ not empty accountDetailfromToken.countryidofcitizenship}">
			                                                    <label for="first_name"  class="profile-lbl"><spring:message code="countryCitizen" /></label>
			                                                    </c:if>
				                                            </div>
				                                            <c:if test="${ not empty accountDetailfromToken.countryidofcitizenship}">
				                                            <div class="col-md-12 profile-val">${accountDetailfromToken.countryidofcitizenship} </div>
				                                            </c:if>
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
			                                                <div class="col-md-12">
			                                                    <label for="first_name"  class="profile-lbl"><spring:message code="mobileNum1" /></label>
				                                            </div>
				                                            <div class="col-md-12 profile-val">${accountDetailfromToken.mobileNo} </div>
		                                            	</div>
		                                        		<!-- /text box -->
		                                        	</div>
		                                        	<div class="col-md-6 remove-pad">
														<!-- text box -->
		                                            	<div class="form-group cf">
			                                                <div class="col-md-12">
			                                                    <label for="first_name"  class="profile-lbl"><spring:message code="feedback.form.field.email" /></label>
				                                            </div>
				                                            <div class="col-md-12 profile-val">${accountDetailfromToken.emailAddress}</div>
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
			                                                <div class="col-md-12">
			                                                <c:if test="${not empty accountDetailfromToken.passportNo}">
			                                                    <label for="first_name"  class="profile-lbl"><spring:message code="passportNum" /></label>
			                                                    </c:if>
																 <c:if test="${not empty accountDetailfromToken.tradeLienceNo}">
			                                                    <label for="first_name"  class="profile-lbl"><spring:message code="trade.licence.number" /></label>
			                                                    </c:if>
				                                            </div>
				                                            <c:if test="${ not empty accountDetailfromToken.passportNo}">
				                                            <div class="col-md-12 profile-val">${accountDetailfromToken.passportNo}</div>
				                                            </c:if>
															 <c:if test="${not empty accountDetailfromToken.tradeLienceNo}">
				                                            <div class="col-md-12 profile-val">${accountDetailfromToken.tradeLienceNo}</div>
				                                            </c:if>
															
		                                            	</div>
		                                        		<!-- /text box -->
		                                        	</div>
		                                        	<div class="col-md-6 remove-pad">
														<!-- text box -->
		                                            	<div class="form-group cf">
			                                                <div class="col-md-12">
			                                                <c:if test="${ not empty accountDetailfromToken.emiratesId}">
			                                                    <label for="first_name"  class="profile-lbl"><spring:message code="emiratedId" /></label>
			                                                    </c:if>
			                                                     
				                                            </div>
				                                             <c:if test="${not empty accountDetailfromToken.emiratesId}">
				                                            <div class="col-md-12 profile-val">${accountDetailfromToken.emiratesId}</div>
				                                            </c:if>
				                                            
		                                            	</div>
													
		                                        		<!-- /text box -->
		                                        	</div>
												</div>
	                                        </div>
									</div>
							</div>
						</div>
						<!-- /left col -->
					</div>
				</div>
				<!-- /content area -->
				
		<!-- script -->
	<!-- 	<script src="/js/dest/app.js"></script> -->
		<script src="/js/libs/jquery.validate.js"></script>
	    <script>
	       
			jQuery(function($) {  $("#feedbak").validate(); });
			
	    </script>
		<!-- /script -->
	