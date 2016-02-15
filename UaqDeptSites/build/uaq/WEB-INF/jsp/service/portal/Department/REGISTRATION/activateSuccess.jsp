<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%-- <%@ page import="net.tanesha.recaptcha.ReCaptcha"%>
 <%@ page import="net.tanesha.recaptcha.ReCaptchaFactory"%> --%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

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
								<li class="active"> 
									<a href="#"><spring:message code="${pagelable}"/>
									</a>
								</li>
								
								
							</ul>
						</div>
					</div>
				</div>
				<!--/mainmenu -->
				<!-- content area -->
				<div class="content">	
						
						<!-- left col -->
						<div class="col-xs-12 col-sm-9 col-md-9 main-left-col">
							<!-- page title -->
							<div class="page-title-wrap">
								<div class="row">
									<div class="col-xs-12 col-sm-9 col-md-10 title-txt">
										<!-- page title -->
										<h2 class="page-title">
										<spring:message code="${pagelable}"/>
										
										</h2>
										<!-- /page title -->
									</div>
									<div class="hidden-xs show-sm col-md-2 title-icon">
										<img src="/img/icons/icon-uaq.png" alt="uaq">
									</div>
								</div>
							</div>
<div >
</div>
</div>
<div class="main-content-wrap">
		
							
								<div class="page-content-wrap">
										<div class="form-content cf">
										  <div class="row">
												<div class="row"> 
													<div class="col-md-9 remove-pad">
													<div class="form-group cf">

													<div style="text-align: center;">${message}</div>
</div>
</div>
</div>
</div>
</div>
</div>
</div>
</div>
</div>
</div>
<!-- <script src="js/dest/app.js"></script> -->
		<script src="/js/libs/jquery.validate.js"></script>
		<script src="/js/libs/tooltip.js"></script>
				<script src="/js/libs/popover.js"></script>