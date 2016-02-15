<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
		<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
		<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
		<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
		<meta http-equiv="CACHE-CONTROL" content="NO-CACHE" />
		<meta http-equiv="EXPIRES" content="0" />
		  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
		 <title>${pageMetadata.pageTitle}</title>
		  <meta name="keywords" content="${pageMetadata.pageKeywords}" />
			<meta name="description" content="${pageMetadata.pageDescription}" />
		  <c:if test="${param.languageCode=='en'}">
		  	<link rel="stylesheet" href="/css/global-ltr.css"/>
		  </c:if>
		  <c:if test="${param.languageCode=='ar'}">
		  	<link rel="stylesheet" href="/css/global-rtl.css"/>
		  </c:if>		  
		  
		  <link href="/img/favicon.ico" rel="shortcut icon" type="image/x-icon" />
		  
		  <script src="/js/libs/jquery.js"></script>
		
		  <!--[if lt IE 9]>
		<script src="/js/libs/ie/html5shiv.js"></script>
		<script src="/js/libs/ie/respond.min.js"></script>		
		<![endif]-->

<script>
	
</script>

</head>

<body>	

	<tiles:insertAttribute name="sitesheader"/>
	
	<tiles:insertAttribute name="sitesbody"/>
	<tiles:insertAttribute name="sitesrightcolumn"/>
	
	<tiles:insertAttribute name="sitesfooter"/>

</body>
</html>