<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!--mainmenu -->
<div class="mainmenu">
	<div class="col-md-12 hidden-xs hidden-sm">
		<div class="mainmenu-wrap">
			<ul class="no-list cf">
				<li><a href="/${param.languageCode}/home.html"><img
						src="/img/home1.png" alt="Home UAQ"> </a></li>
				<c:if
					test="${not empty parentLandingPage.url and not empty parentLandingPage.title and not empty parentLandingPage.navigations}">
					<li class="active"><a href="/${param.languageCode}/${parentLandingPage.url}">${parentLandingPage.title}</a></li>

					<c:forEach items="${parentLandingPage.navigations}" var="item">
						<c:choose>
							<c:when test="${source==item.url}">
								<li class="active-sub"><a
									href="/${param.languageCode}/${item.url}">${item.title}</a></li>
							</c:when>
							<c:otherwise>
								<li><a href="/${param.languageCode}/${item.url}">${item.title}</a></li>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</c:if>

				<c:if test="${empty parentLandingPage}">
					<li class="in-active"><spring:message code="funeral.title" /></li>
				</c:if>
			</ul>
		</div>
	</div>
</div>
<!--/mainmenu -->
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
			<div class="page-title-wrap page-title-wrap-funerial">
				<div class="row">
					<div class="col-xs-9 col-sm-9 col-md-10 title-txt title-txt-funeral">
						<!-- sub page title 
										<h4 class="page-title-sub">
										MUNICIPALITY
										</h4>-->
						<!-- /sub page title -->
						<!-- page title -->
						<h2 class="page-title ">
							<spring:message code="funeral.title" />
						</h2>
						
						<!-- /page title -->
					</div>
					<div class="col-xs-3 col-sm-3 col-md-2 title-logut">
						<!-- <img src="/img/banner/Funeral.png" alt="uaq"> -->
						<label class="display-inline-block pull-right funeral-anchor"> <a href="/${param.languageCode}/logout.html"><spring:message code="label.logout" /></a></label>
					</div>
					
				</div>
			</div>
			<!-- /page title -->
			<div class="main-content-wrap">

				<div class="page-content-wrap">
					<div class="form-content cf">
						<form:form method="POST" commandName="funeralCommand"
							action="/${param.languageCode}/funeral.html" id="funeral">
							<div class="row">
								<div class="row">
									<form:errors path="description" />
									<c:set var="funeralMandatory"><spring:message code="form.fields.asterisk.mandatory" /></c:set>
									<div class="col-md-12 remove-pad">
										<div class="form-group cf">
											<label class="pull-right">${funeralMandatory}</label>
										</div>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-md-12 remove-pad">
									<div class="col-md-10 remove-pad">										
										<div class="form-group cf">
											<div class="col-md-4">
												<label for="departee_name" class="form-lbl mandatory_lbl"><spring:message
														code="funeral.form.field.depatee.name" /></label>
											</div>
											<div class="col-md-8">
											<c:set var="departeeNameMandatory"><spring:message code='funeral.form.field.required.departee.name'/></c:set>
												<form:input path="departeeName"
													cssClass="form-control required" id="departeeName" 
													data-msg-required="${departeeNameMandatory}"
													type="text" />
											</div>
										</div>										
									</div>									
								</div>
							</div>

							<div class="row">
								<div class="col-md-12 remove-pad">
									<div class="col-md-10 remove-pad">										
										<div class="form-group cf">
											<div class="col-md-4">
												<label for="funeral_date" class="form-lbl mandatory_lbl"><spring:message
														code="funeral.form.field.funeral.date" /></label>
											</div>
											<div class="col-md-8">
											<c:set var="funeralDateMandatory"><spring:message code='funeral.form.field.required.funeral.date'/></c:set>
												<form:input path="funeralDate" id="funeralDate" 
												data-msg-required="${funeralDateMandatory}"
													cssClass="form-control required funeral-date" type="text" />
											</div>
										</div>										
									</div>									
								</div>
							</div>
														
							<div class="row">
								<div class="col-md-12 remove-pad">
									<div class="col-md-10 remove-pad">
										<!-- text box -->
										<div class="form-group cf">
											<div class="col-md-4">
												<label for="message" class="form-lbl mandatory_lbl"><spring:message
														code="desc" /> </label>
											</div>
											<div class="col-md-8">
											<div id="edior_message" class="easyeditor"></div>
											<c:set var="descRequired"><spring:message code='form.field.required.desc'/></c:set>
												<form:hidden path="description" 
												data-msg-required="${descRequired}"
													class="form-control required funeralmsg " id="message" />
											</div>
										</div>										
									</div>																																	
								</div>
							</div>
							
							<div class="row">
								<div class="col-md-12 remove-pad">																										
									<div class="col-md-10 remove-pad">										
										<div class="form-group cf">
											<div class="col-md-4">
												<label for="push_notification_message" class="form-lbl"><spring:message
														code="funeral.form.field.push.notification.message" /></label>
											</div>
											<div class="col-md-8">											
												<form:input path="pushNotificationMessage" id="pushNotificationMessage" 												
													cssClass="form-control" type="text" />
											</div>
										</div>										
									</div>																									
								</div>
							</div>											

							<div class="row">
								<div class="col-md-12 remove-pad">
									<div class="col-md-10 remove-pad">
										<!-- submit button -->
										<div class="row">
											<div class="form-group submission">
												<div class="col-md-offset-4 col-md-8">
													<input type="submit" class="btn"
														value="<spring:message code='form.button.submit'/>" />
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
</div>
<!-- /content area -->
	<!-- script -->
	
		<script src="/js/libs/jquery.validate.js"></script>
		<script src="/js/tinymce/tinymce.min.js"></script>
		<script src="/js/jquery.easyeditor.js"></script>
	    <script>
	  
	    jQuery(function($) {
	    	$("#funeral").validate();
			$('.funeral-date').datepicker({
					
					 minDate: 0
				});
		$('.funeral-date-time').datetimepicker({
								minDate:0,
								controlType: 'select',
								timeFormat: 'hh:mm tt'
							}); 				
				
				});
	    
	  //custom editor
		new EasyEditor('#edior_message');
		
		$('#edior_message').focusout(function () {
			$('#message').val($('#edior_message').html());
		});
	    
	    </script>
		<!-- /script -->