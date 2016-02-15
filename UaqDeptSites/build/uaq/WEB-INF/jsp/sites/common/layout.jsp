<!DOCTYPE html>
<html>
<head>
<link rel="icon" type="image/x-icon" href="/img/icon-fav.ico" />
<link rel="icon" type="image/png" href="/img/icon-fav.png" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<meta http-equiv="CACHE-CONTROL" content="NO-CACHE" />
<meta http-equiv="EXPIRES" content="0" />
<meta name="format-detection" content="telephone=no" />
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<title>${pageMetadata.pageTitle}</title>
<meta name="keywords" content="${pageMetadata.pageKeywords}" />
<meta name="description" content="${pageMetadata.pageDescription}" />
<link rel="stylesheet" href="/css/bootstrap.css">
<link rel="stylesheet/less" href="/css/global-styles.less" type="text/css">
<link rel="stylesheet" type="text/css" href="/css/video-gallery.css">
<link rel="stylesheet" type="text/css" href="/css/datepicker.css">

<c:if test="${param.languageCode=='ar'}">
	<link rel="stylesheet" href="/css/bootstrap-rtl.css">
	<link rel="stylesheet/less" href="/css/global-styles-rtl.less" type="text/css">
</c:if>
<script type="text/javascript" src="/js/lib/jquery-1.11.1.min.js"></script>



<script src="/js/lib/less.js"></script>
<script src="/js/lib/modernizr.js"></script>
<!--[if lt IE 9]>
	    <script src="/js/lib/respond.min.js"></script>
	    <script src="/js/lib/html5shiv.js"></script>
	    <script src="/js/lib/css3-mediaqueries.js"></script>
	    <script src="/js/lib/selectivizr-min.js"></script>
	<![endif]-->

<script>
share_FB_Url ="";
site_Url ="";

</script>

</head>
<!--body starts from here -->
<body>
<tiles:insertAttribute name="mobilemenu" />
<div class="bg-wrapper" style="background-image:url('${homeVO.backgroundImage}');">
	
	<div class="container">
		<tiles:insertAttribute name="header" />
		<tiles:insertAttribute name="navigation" />
		<tiles:insertAttribute name="body" />
		<tiles:insertAttribute name="footer" />
	</div>

	<div class="feedback hide-on-mobile">
		<a href="#" class="feedback-link"><spring:message code='feedback.title'/></a>
	</div>
	<div class="overlay-wrapper">
		<div class="feedback-wrapper">
		<div class="close-icon"><i class="glyphicon glyphicon-remove"></i></div>
			<div class="logo-wrapper pull-left">
				<img src="/img/logo-state.png" alt="UMM Alquwain">
			</div>
			<h2 class="service-feedbackcall" style="display:none;"> </h2>
			<div class="service-feedback pull-left">
				<h2 id="model_smile_question" data-smile-callback-sucess="<spring:message code='smiley.success'/>" data-smile-callback-error="<spring:message code='duplicate.session'/>"
					data-smile-question="<spring:message code='smiley.question'/>">
					<spring:message code="smiley.question" />
				</h2>
				<ul>
					<li class="good"><a href="#" class="smile"
						data-smile-val="happy"
						data-smile="/${param.languageCode}/smiley.html"> </a></li>
					<li class="average"><a href="#" class="smile"
						data-smile-val="ok"
						data-smile="/${param.languageCode}/smiley.html"> </a></li>
					<li class="poor"><a href="#" class="smile"
						data-smile-val="sad"
						data-smile="/${param.languageCode}/smiley.html"></a></li>
				</ul>
			</div>
		</div>
	</div>

	
	<script type="text/javascript" src="/js/lib/video-gallery.min.js"></script>
	<script type="text/javascript" src="/js/lib/bootstrap.min.js"></script>
	<script type="text/javascript" src="/js/lib/bootstrap-datepicker.min.js"></script>
	<c:if test="${param.languageCode=='en'}">
		<script type="text/javascript" src="/js/global.js"></script>
	</c:if>
	
	<c:if test="${param.languageCode=='ar'}">
		<script type="text/javascript" src="/js/global-rtl.js"></script>
	</c:if>
	</div>
</body>

</html>