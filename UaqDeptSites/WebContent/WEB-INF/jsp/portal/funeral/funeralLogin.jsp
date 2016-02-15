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
					<li class="in-active"><spring:message code="header.login" /></li>
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
							<!-- <li><a href="/${param.languageCode}/rssFeeds.html"><img src="/img/icons/icon-rss.png"
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
							<c:set var="subjectValue"><spring:message code='funeral.title'/></c:set>
							<li class="share-email"><a
								href="mailto:?&amp;Subject=${subjectValue}&amp;Body=${sourceURL}">
									<img src="/img/icons/icon-email.png" alt="uaq">
							</a></li> -->
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
							<spring:message code="header.login" />
						</h2>
						<!-- /page title -->
					</div>
					<div class="hidden-xs show-sm col-md-2 title-icon">
						<!-- <img src="/img/banner/Funeral.png" alt="uaq"> -->
					</div>
				</div>
			</div>
			<!-- /page title -->
			<div class="main-content-wrap">

				<div class="page-content-wrap">
					<div class="form-content cf">
						<c:if test="${not empty errorMessage}">
							<p><span class="errorBox">${errorMessage}</span></p>
						</c:if>
						<form:form name="login-form" id="login-form-funeral" method="POST" commandName="loginCommand" autocomplete="off" 
							 action="/${param.languageCode}/funeralLogin.html" >
							<div class="row">
								<div class="row">									
									<c:set var="allMandatoryMessage"><spring:message code="feedback.form.mandatory" /></c:set>
									<div class="col-md-12 remove-pad">
										<div class="form-group cf">											
											<label class="mandatory_lbl pull-right">${allMandatoryMessage}</label>
										</div>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-md-12 remove-pad">
									<div class="col-md-6 remove-pad">										
										<div class="form-group cf">
											<div class="col-md-5">
												<label for="username" class="form-lbl mandatory_lbl"><spring:message
														code="header.username" /></label>
											</div>
											<div class="col-md-7">
											<c:set var="mandatoryFieldMessage"><spring:message code='field.validate'/></c:set>
												<form:input path="loginUsername" cssClass="form-control required" 
													data-msg-required="${mandatoryFieldMessage}"  autocomplete="off" id="username" />
											</div>
										</div>										
									</div>									
								</div>
							</div>
							
							<div class="row">
								<div class="col-md-12 remove-pad">
									<div class="col-md-6 remove-pad">										
										<div class="form-group cf">
											<div class="col-md-5">
												<label for="password" class="form-lbl mandatory_lbl"><spring:message
														code="header.password" /></label>
											</div>
											<div class="col-md-7">
											<c:set var="mandatoryFieldMessage"><spring:message code='field.validate'/></c:set>
											<form:password path="loginPassword" cssClass="form-control required" id="password"  
												data-msg-required="${mandatoryFieldMessage}" />
											<form:hidden path="loginStatus" id="loginStatus" autocomplete="off"/>
											</div>
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
	