
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

		
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
								<li class="active"><a href="#"><spring:message code="label.lp.newRealEstate" /></a>
								</li>
								
							</ul>
						</div>
					</div>
				</div>
				<div class="content">
				<c:set var="directorReq"><spring:message code="label.lp.managingDirName.required" /></c:set>
				<c:set var="addressReq"><spring:message code="plzAddress" /></c:set>
				<c:set var="familyBookReq"><spring:message code="label.lp.familyBookScan.required" /></c:set>
				<c:set var="spouseEmiratesReq"><spring:message code="label.ps.spouse.emirates.attach.required" /></c:set>
				
					<div class="row">
					<!-- right col -->
							<div class="col-xs-12 col-sm-3 col-md-3 main-right-col">
						<!-- social media share callout -->
						<div class="callout-wrap">
							<div class="callout-content">
								<div class="social-share-wrap">
									<ul>
										<li><a href="#"><img src="/img/icons/icon-rss.png"
												alt="uaq"></a></li>
										<li class="share-fb"><a target="_blank" alt="share it"
											href="http://www.facebook.com/sharer/sharer.php?s=100&amp;p[url]=http://dev.uaq.ae/en&amp;p[images][0]=http://logicum.co/wp-content/uploads/2013/01/sharetweetbuttons.jpg&amp;p[title]=Creating Custom share buttons: Facebook, Twitter, Google+&amp;p[summary]=Build your custom share buttons from normal images with examples on each button">
												<img src="/img/icons/icon-fb.png" alt="uaq">
										</a></li>
										<li class="share-twitter"><a target="_blank"
											href="http://www.twitter.com/share?url=http://www.google.com/"><img
												src="/img/icons/icon-twitter.png" alt="tweet"></a></li>
										<li class="share-print"><a href="#"><img
												src="/img/icons/icon-print.png" alt="uaq"></a></li>
										<li class="share-email"><a
											href="mailto:?&amp;Subject=UAQ%20Share&amp;Body=I%20saw%20this%20and%20thought%20of%20you!%20">
												<img src="/img/icons/icon-email.png" alt="uaq">
										</a></li>
										<li class="share-fontsize">
											<div class="dropdown">
												<button class="btn btn-default dropdown-toggle"
													type="button" data-toggle="dropdown">
													<span class="sele"><spring:message code="label.a" /> </span> <span class="caret"></span>
												</button>
												<ul class="dropdown-menu">
													<li><a href="javascript:void(0);"><spring:message code="label.a" /></a></li>
													<li><a href="javascript:void(0);"><spring:message code="label.a" />+</a></li>
													<li><a href="javascript:void(0);"><spring:message code="label.a" />++</a></li>
												</ul>
											</div>
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
										<h2 class="page-title"> <spring:message code="label.lp.newRealEstate" />   <!--- BPM03.1.01: pgno 27 -->
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
										   <form:form commandName="newRealEstateOfficeCommand" name="feedbak"  id="feedbak" enctype="multipart/form-data" method="post" action="#" >
										    <div class="row"></div>
										
												
									
											<div class="row">
												<div class="col-md-12 remove-pad">
													<div class="col-md-6 remove-pad">
														<!-- text box -->
		                                            	<div class="form-group cf">
			                                                <div class="col-md-5">
			                                                    <label for="director-name" class="form-lbl "><spring:message code="label.lp.managingDirName" />
			                                                    </label>
				                                            </div>
				                                            <div class="col-md-7">
																<form:input path="directorName" id="director-name" name="director-name" type="text" class="form-control required other-form"  data-msg-required="${directorReq}"/>
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
			                                                    <label for="address" class="form-lbl "><spring:message code="label.address" />
			                                                    </label>
				                                            </div>
				                                            <div class="col-md-7">
																<form:input path="address" id="address" name="address" type="text" class="form-control required other-form" data-msg-required="${addressReq}" />
			                                                </div>
		                                            	</div>
		                                        		<!-- /text box -->
		                                        	</div>
												</div>
	                                        </div>		

											<div class="row">
												<div class="col-md-12">  <!-- form-sect-head-->
													<h5 class="form-title"><spring:message code="label.ps.attachments" /></h5>
												</div> 
											</div>	<!-- /form-sect-head-->

											<div class="row">
												<div class="col-md-12 remove-pad">
													<div class="col-md-6 remove-pad">
														<!-- text box -->
		                                            	<div class="form-group cf">
			                                                <div class="col-md-5">
			                                                    <label for="police-report" class="form-lbl mandatory_lbl"><spring:message code="label.lp.familyBookScan" /> <span class=""><spring:message code="label.lp.addressToSheikPgm" />  </span>
			                                                    </label>
				                                            </div>
				                                            <div class="col-md-7">
			                                                    <div class="input-group file-upload">
																	<input type="text" class="form-control required other-form" readonly="readonly" name ="police-report" data-msg-required="${familyBookReq}" />
																	<span class="input-group-btn">
																		<span class="btn btn-file">
																			<spring:message code="file.browse" />&hellip; 
																			<form:input path="familyBook" type="file" />
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
			                                                    <label  for="emiratesid-back" class="form-lbl mandatory_lbl"><spring:message code="label.lp.spouseEmiratesIdAttach" />
			                                                    </label>
				                                            </div>
				                                            <div class="col-md-7">
																<div class="input-group file-upload">
																	<input type="text" class="form-control required other-form" name="emiratesid-back" readonly="readonly" data-msg-required="${spouseEmiratesReq}"/>
																	<span class="input-group-btn">
																		<span class="btn btn-file">
																			<spring:message code="file.browse" />&hellip; 
																			<form:input path="spouseEmiratesId" type="file"/>
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
									<%-- 	</form> --%></form:form>	
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
			/// $('#feedbak :input').attr('readonly','');
			});
			
	    </script>
		<!-- /script -->
	