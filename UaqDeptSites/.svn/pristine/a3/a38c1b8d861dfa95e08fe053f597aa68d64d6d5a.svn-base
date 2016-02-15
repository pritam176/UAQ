<!DOCTYPE html>
<html>
<head>
<link rel="icon" type="image/x-icon" href="/img/icon-fav.ico" />
		<link rel="icon" type="image/png" href="/img/icon-fav.png" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<meta http-equiv="CACHE-CONTROL" content="NO-CACHE" />
<meta http-equiv="EXPIRES" content="0" />
<meta charset="UTF-8">
<c:if test="${param.isNative == '1' }">
<meta name="viewport" content="initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0" />
</c:if>
<c:if test="${param.isNative != '1' }">
<meta name="viewport" content="initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no" />
</c:if>


<title>${pageMetadata.pageTitle}</title>
<meta name="keywords" content="${pageMetadata.pageKeywords}" />
<meta name="description" content="${pageMetadata.pageDescription}" />
<c:if test="${param.languageCode=='en' }">
	<link href="/css/app.css" rel="stylesheet">
</c:if>
<c:if test="${param.languageCode=='ar' }">
	<link href="/css/app-arabic.css" rel="stylesheet">
</c:if>
<c:set var ="lablstarong"><spring:message code="lable.password.strong"/></c:set>
<c:set var ="lablemedium"><spring:message code="lable.password.medium"/></c:set>
<c:set var ="lableweek"><spring:message code="lable.password.weak"/></c:set>
<c:set var ="Strength"><spring:message code="label.password.strength"/></c:set>
<!--<link rel="shortcut icon" href="" type="image/x-icon" />-->
<script src="/js/libs/jquery.min.js"></script>
<!--[if lt IE 9]>
      <script src="/js/ie.min.js"></script>
    <![endif]-->   
<script>
   var app_id_txt = '427909230731951';
   var site_Url = window.location.href;
   var logo="";
   var title_txt="";
   var caption_txt="";
   var descr_txt="";
   var email_share="";
</script>
</head>
<!--body starts from here -->
<body>
<div class="container-fluid">
	<div class="wrapper">
			<!-- header -->
			<div class="headerPart" style="width: 100%;">
				<div class="row">
					<header id="header" class="cf">
						<tiles:insertAttribute name="header" />
					</header>
				</div>
			</div>
			<!-- /header -->
			<!-- mobile menu -->
			<tiles:insertAttribute name="mobilemenu" />
			<!-- /mobile menu -->
			<tiles:insertAttribute name="body" />
			<!-- /footer -->
			<tiles:insertAttribute name="footer" />
			<!-- /footer -->
		</div>
		</div>
	<!-- container fluid ends here -->
	<c:set var="emailSubject" value="${pageMetadata.pageTitle}" />
	<script type="text/javascript">
		var emailsubject= "${emailSubject}";
		var email_Id = "sampleemail@uaq.com";
		var share_FB_Url = "https://www.facebook.com/dialog/feed?app_id="+app_id_txt+"&link="+site_Url+"&picture="+logo+"&name="+title_txt+"&caption="+caption_txt+"&description="+descr_txt+"&redirect_uri="+site_Url;
		var share_twtter_Url = "https://twitter.com/intent/tweet?url="+site_Url+"&text="+title_txt;
	</script>
	<script src="/js/dest/app.js"></script>
	<script src="/js/libs/jquery-ui-timepicker-addon.js"></script>
	<!--      mobile pop up for image  -->
	<div class="modal fade" id="myImgaeModal" role="dialog" data-msg-strong="${lablstarong}" data-msg-weak="${lableweek}" data-msg-medium="${lablemedium}" data-msg-stength="${Strength}" >
		<div class="vertical-alignment-helper">
			<div class="modal-dialog vertical-align-center">
				<!-- Modal content-->
				<div class="modal-content">
					<div class="modal-body">
						<button type="button" class="close pull-right close-img" data-dismiss="modal">
							<span class=" glyphicon glyphicon-remove"></span>
						</button>
						<div  id="image-container">
							
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!--      /mobile pop up for image  -->
</body>
<!-- body ends here -->

</html>