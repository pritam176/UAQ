
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
								<li class="active"><a href="#"><spring:message code="label.lp.newProCard" /></a>
								</li>
								
							</ul>
						</div>
					</div>
				</div>
			<!-- content area -->
			<div class="content">
			
			<c:set var="proNameReq"><spring:message code="label.lp.proName.required" /></c:set>
			<c:set var="proIdReq"><spring:message code="label.lp.proId.required" /></c:set>
			<c:set var="proExpDateReq"><spring:message code="label.lp.proExpDate.required" /></c:set>
			<c:set var="proNationalityReq"><spring:message code="label.lp.proNationality.required" /></c:set>
			<c:set var="proIdProofReq"><spring:message code="label.lp.proIdProof.required" /></c:set>
			<c:set var="proPhotoReq"><spring:message code="label.lp.proPhoto.required" /></c:set>
			
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
									<h2 class="page-title">
									<spring:message code="label.lp.newProCard" />	
										<!--- BPM03.1.01: pgno 19 -->
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
									<form:form commandName="procardRequestCommand" name="feedbak"
										id="feedbak" enctype="multipart/form-data" method="post"
										action="issuenewprocard.html">
										<div class="row"></div>
										<div class="row">
											<div class="col-md-12 remove-pad">
												<div class="col-md-6 remove-pad">
													<!-- text box -->
													<div class="form-group cf">
														<div class="col-md-5">
															<label for="pro-name" class="form-lbl mandatory_lbl"><spring:message code="label.lp.proName" /></label>
														</div>
														<div class="col-md-7">
															<form:input path="proName" id="pro-name" name="pro-name"
																type="text" class="form-control required" placeholder=""  data-msg-required="${proNameReq}"/>
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
															<label for="pro-id" class="form-lbl mandatory_lbl"><spring:message code="label.lp.proIdNo" /> </label>
														</div>
														<div class="col-md-7">
															<form:input path="proIdNumber" id="pro-id" name="pro-id"
																type="text" class="form-control required" placeholder="" data-msg-required="${proIdReq}"/>
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
															<label for="pro-exp-date" class="form-lbl mandatory_lbl"><spring:message code="label.lp.proExpDate" /> </label>
														</div>
														<div class="col-md-7">
															<form:input path="proIdExpiryDate" id="pro-exp-date"
																name="pro-exp-date" type="date"
																class="form-control required" placeholder="" data-msg-required="${proExpDateReq}"/>
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
															<label for="land-status" class="form-lbl mandatory_lbl"><spring:message code="label.lp.proNationality" /> </label>
														</div>
														<div class="col-md-7">
															<div class="custom-select-box cf">
																<form:select path="proNationality"
																	class="required1 required" name="land-status" data-msg-required="${proNationalityReq}">
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
															<label for="police-report" class="form-lbl mandatory_lbl"><spring:message code="label.lp.proIdProof" /><span class=""><spring:message code="label.lp.idPassport" /> 
															</span>
															</label>
														</div>
														<div class="col-md-7">
															<div class="input-group file-upload">
																<input type="text" class="form-control required"
																	name="police-report" data-msg-required="${proIdProofReq}" readonly="readonly" /> <span class="input-group-btn">
																	<span class="btn btn-file"> <spring:message code="file.browse" />&hellip; <form:input
																			path="proIdProof" type="file" />
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
															<label for="emiratesid-back"
																class="form-lbl mandatory_lbl"><spring:message code="label.lp.proPhoto" /> </label>
														</div>
														<div class="col-md-7">
															<div class="input-group file-upload">
																<input type="text" class="form-control required"
																	name="emiratesid-back" data-msg-required="${proPhotoReq}" readonly="readonly"/> <span
																	class="input-group-btn"> <span
																	class="btn btn-file"> <spring:message code="file.browse" />&hellip; <form:input
																			path="proPhotograph" type="file" />
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
																 <input type="submit" class="btn" value='<spring:message code="form.button.submit" />'  /> 
															</div>
														</div>
													</div>
													<!-- /submit button -->
												</div>
											</div>
										</div>
									</form:form>
									<%-- 	</form> --%>
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
