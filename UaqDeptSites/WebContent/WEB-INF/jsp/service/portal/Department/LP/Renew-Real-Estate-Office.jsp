<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>UAQ</title>
<link href="/css/app.css" rel="stylesheet">
<!--<link rel="shortcut icon" href="" type="image/x-icon" />-->
<script src="/js/libs/jquery.min.js"></script>
<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
		<script src="/js/ie.min.js"></script>
		<![endif]-->
</head>
<!-- body -->
<body>

	<!--breadcrumbs -->
	<div class="col-md-12 hidden-xs">
		<div class="breadcrumb-wrap">
			<ul class="no-list cf">
				<li><a href="#"><img src="/img/home1.png" alt="Home UAQ">
				</a></li>
				<li class="active"><a href="#">START A BUSINESS</a></li>
				<li><a href="#">CLOSE A BUSINESS</a></li>
				<li><a href="#">WORKING WITH GOVERNMENT</a></li>
				<li class="active"><a href="#">START A BUSINESS</a></li>
				<li><a href="#">CLOSE A BUSINESS</a></li>
				<li><a href="#">WORKING WITH GOVERNMENT</a></li>
			</ul>
		</div>
	</div>
	<div class="col-md-12 visible-xs ">
		<div class="mob-breadcrumb-wrap green">Business</div>
	</div>
	<!--/breadcrumbs -->
	<!-- content area -->
	<div class="content">
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
										<button class="btn btn-default dropdown-toggle" type="button"
											data-toggle="dropdown">
											<span class="sele"> A </span> <span class="caret"></span>
										</button>
										<ul class="dropdown-menu">
											<li><a href="javascript:void(0);">A</a></li>
											<li><a href="javascript:void(0);">A+</a></li>
											<li><a href="javascript:void(0);">A++</a></li>
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
								Renew Real Estate Office Request
								<!--- BPM03.01.07: pgno 35 -->
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
							<form:form commandName="reNewRealesateCommand"
								modelAttribute="reNewRealesateCommand" id="feedbak"
								enctype="multipart/form-data" method="post"
								action="my-request.html">
								<div class="row"></div>

								<div class="row">
									<div class="col-md-12 remove-pad">
										<div class="col-md-6 remove-pad">
											<!-- text box -->
											<div class="form-group cf">
												<div class="col-md-5">
													<label class="form-lbl">Managing Director Name </label>
												</div>
												<div class="col-md-7">
													<label class="form-lbl">Salem </label> <a href="#"
														class="pull-right"> Edit </a>
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
													<label class="form-lbl">Address </label>
												</div>
												<div class="col-md-7">
													<label class="form-lbl">Address </label> <a href="#"
														class="pull-right"> Edit </a>
												</div>
											</div>
											<!-- /text box -->
										</div>
									</div>
								</div>
								<div class="row" id="collapsMoreInfo" data-parent="#accordion2">
									<div class="col-md-12 remove-pad">
										<div class="col-md-6 remove-pad">
											<!-- text box -->
											<div class="form-group cf">
												<div class="col-md-5">
													<label class="form-lbl">Phone Number </label>
												</div>
												<div class="col-md-7">
													<label class="form-lbl">293848932489 </label> <a href="#"
														class="pull-right"> Edit </a>
												</div>
											</div>
											<!-- /text box -->
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-md-12">
										<!-- form-sect-head-->
										<h5 class="form-title">Request Details</h5>
									</div>
								</div>
								<!-- /form-sect-head-->
								<div class="row">
									<div class="col-md-12 remove-pad">
										<div class="col-md-6 remove-pad">
											<!-- text box -->
											<div class="form-group cf">
												<div class="col-md-5">
													<label class="form-lbl">Request Number </label>
												</div>
												<div class="col-md-7">
													<label class="form-lbl">LP948035</label>
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
													<label class="form-lbl">My Details </label>
												</div>
												<div class="col-md-7">
													<label class="form-lbl">GF Realsestate</label>
												</div>
											</div>
											<!-- /text box -->
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-md-12">
										<!-- form-sect-head-->
										<h5 class="form-title">Attachments</h5>
									</div>
								</div>
								<!-- /form-sect-head-->

								<div class="row">
									<div class="col-md-12 remove-pad">
										<div class="col-md-6 remove-pad">
											<!-- text box -->
											<div class="form-group cf">
												<div class="col-md-5">
													<label for="police-certificate"
														class="form-lbl mandatory_lbl">Police Clearance
														Certificate </label>
												</div>
												<div class="col-md-7">
													<div class="input-group file-upload">
														<input type="text"
															class="form-control required other-form"
															name="police-certificate" /> <span
															class="input-group-btn"> <span
															class="btn btn-file"> Browse&hellip; <input
																type="file" path="policeClearance" multiple="multiple">
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
													<label for="personal-photo" class="form-lbl mandatory_lbl">Personal
														Photograph </label>
												</div>
												<div class="col-md-7">
													<div class="input-group file-upload">
														<input type="text"
															class="form-control required other-form"
															name="personal-photo" /> <span class="input-group-btn">
															<span class="btn btn-file"> Browse&hellip; <input
																type="file" path="personalPhotograph"
																multiple="multiple">
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
							</form:form>
						</div>
					</div>
				</div>
				<!-- /left col -->
			</div>
		</div>
		<!-- /content area -->
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
</body>
<!-- /body -->
</html>