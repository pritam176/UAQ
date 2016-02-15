<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

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
					<c:set var="flag" value="false"></c:set>
						<c:choose>
							<c:when test="${source==item.url}">
								<li class="active-sub"><a href="/${param.languageCode}/${item.url}">${item.title}</a></li>
							</c:when>
							<c:when test="${ not empty item.navigations  }">
								<c:forEach items="${item.navigations}" var="subItems">
									<c:choose>
											<c:when test="${source==subItems.url}">
												<li class="active-sub"><a href="/${param.languageCode}/${item.url}">${item.title}</a></li>
												<c:set var="flag" value="true"></c:set>
											</c:when>
									</c:choose>
								</c:forEach>
								<c:if test="${flag=='false'}">
								<li><a href="/${param.languageCode}/${item.url}">${item.title}</a></li>
								</c:if>
							</c:when>
							<c:otherwise>
								<li><a href="/${param.languageCode}/${item.url}">${item.title}</a></li>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</c:if>

				<c:if test="${empty parentLandingPage}">
					<li class="in-active"><spring:message code="label.gallery.video" /></li>
				</c:if>
			</ul>
		</div>
	</div>
</div>
<!--/mainmenu -->
<div class="content">
	<div class="row">
		<!-- right col -->
		<div class="col-xs-12 col-sm-3 col-md-3 main-right-col">
						<!-- social media share callout -->
			<div class="callout-wrap">
				<div class="callout-content">
					<div class="social-share-wrap">
						<ul>
							<li>
								<a href="/${param.languageCode}/rssFeeds.html"><img src="/img/icons/icon-rss.png" alt="uaq"></a>
							</li>
							<li class="share-fb">
								 <a target="_blank" alt="share it" 
								 href="JavaScript:void(0);">
									<img src="/img/icons/icon-fb.png" alt="uaq">
								  </a>
							</li>
							<li class="share-twitter">
								<a  target="_blank" href="JavaScript:void(0);" alt="tweet"></a>
							</li>
							<li class="share-print">
								<a href="#"><img src="/img/icons/icon-print.png" alt="uaq"></a>
							</li>
							<c:set var="subjectValue"><spring:message code='header.sitemap'/></c:set>
							<li class="share-email">
								<a href="mailto:?&amp;Subject=${subjectValue}&amp;Body=${sourceURL}">
									<img src="/img/icons/icon-email.png" alt="uaq">
								</a>
							</li>
							<li class="share-fontsize">
							<div class="dropdown">										
								<button class="btn btn-default dropdown-toggle" type="button" data-toggle="dropdown">
								<span class="sele"> A </span>
								<span class="caret"></span></button>
								<ul class="dropdown-menu">
								  <li><a href="javascript:void(0);" >A</a></li>
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
				<!-- callout -->
			<div class="callout-wrap">
				<c:if test="${not empty parentLandingPage.title}">
					<div class="callout-head">
						<h5 class="right-nav-title">${detailPageVO.title}</h5>
					</div>
				</c:if>
				<div class="callout-content">
					<div class="right-nav">
						<ul>
							<c:forEach items="${detailPageVO.navigations}"	var="thirdNavigations">
									<c:choose>
									 <c:when test="${source==thirdNavigations.url}">
											<li class="active"><a href="/${param.languageCode}/${thirdNavigations.url}">${thirdNavigations.heading}</a></li>
											</c:when>
										<c:otherwise>
											<li ><a href="/${param.languageCode}/${thirdNavigations.url}">${thirdNavigations.heading}</a></li>
										</c:otherwise>
									</c:choose>
									</c:forEach>
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
					<!-- sub page title -->
						<h4 class="page-title-sub">
							<spring:message code="label.media.room" />
						</h4>
						<!-- /sub page title -->
						<!-- page title -->
						<h2 class="page-title">
							<spring:message code="label.gallery.video" />
						</h2>
						<!-- /page title -->
					</div>
					<div class="hidden-xs show-sm col-md-2 title-icon">
						<img src="${videoGalleryPageVO.titleIcon}" alt="uaq">
					</div>
				</div>
			</div>
			<!-- /page title -->
			<!-- page content -->
			<div class="main-content-wrap">
				<!-- content filter -->
				 <div class="content-filter-wrap">
					<div class="form-inline filer-wrap">
						 <div class="form-group col-xs-2 col-sm-5 col-md-9 text-right">
							<label class="form-lbl"><spring:message
									code="label.gallery.filter" /></label>
						 </div>

						 <div class="form-group col-xs-10 col-sm-7 col-md-3">
							<div class="custom-select-box">							
								<form:form method="POST" id="videoForm" action="${sourceURL}"
									commandName="videoSearchCommand">
									<form:select path="category" cssClass="_select videoGallerySelect" onchange="submitForm(this);">
										<option value="0">
											<spring:message code="option.select" />
										</option>
										<form:options items="${categoriesMap}" />
									</form:select>
								</form:form>
							</div>
						</div> 
					</div>
				</div> 
				<!-- content filter --> 
				
				<!-- accordion listing -->
				<div class="service-catalogue-wrap">
				<c:choose>
					<c:when test="${not empty videos}">
						<div class="panel-group" id="videogallery">
							<div class="row">
								<c:forEach items="${videos}" var="videoVO">
									<div class="col-md-4 col-sm-6 col-xs-12">
										<div class="listing-item">
											<div class="listing-img" data-toggle="modal"
												data-target="#myVideoModal" data-videomp4="${videoVO.video}">
												<img src="${videoVO.teaserImage}" alt="">
												<div class="payoverlay"></div>
											</div>
											<div class="listing-content">
												<p>${videoVO.teaserTitle}</p>
											</div>
										</div>
									</div>
								</c:forEach>
							</div>
							<!-- /second row !-->
						</div>
						</c:when>
						<c:otherwise>
							<spring:message code="label.no.videos"></spring:message>
						</c:otherwise>
					</c:choose>
					
				</div>

				<!-- listing pagination -->
				<!-- <div class=" pagination">
					<ul>
						<li><a href="#">Previous</a></li>
						<li><a href="#">1</a></li>
						<li><a href="#">2</a></li>
						<li><a href="#">3</a></li>
						<li><a href="#">4</a></li>
						<li><a href="#">Next</a></li>
					</ul>
				</div> -->
				<!-- /listing pagination -->
			</div>
			<!-- /page content -->
		</div>
		<!-- /left col -->
	</div>
</div>
<!-- Home Page Wrapper End -->

<div class="modal fade" id="myVideoModal" role="dialog">
	<div class="vertical-alignment-helper">
		<div class="modal-dialog vertical-align-center">
			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-body">
					<button type="button" class="close" data-dismiss="modal">
						<span class="glyphicon glyphicon-remove"></span>
					</button>
					<video controls id="video-container">
						<source src="" type="video/mp4"></source>
									  <source src="" type="video/ogg">
									  Your browser does not support HTML5 video.
					</source>
					</video>
				</div>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">
//$(".videoGallerySelect option:eq(0)").attr("disabled", "disabled");
	function submitForm(obj) {
		$('#videoForm').submit();
	}
</script>

 	<c:if test="${not empty pageMetadata.pageTitle}">
		<c:set var="titleText" value="${pageMetadata.pageTitle}"></c:set>
	</c:if>
	
	<c:if test="${not empty pageMetadata.pageKeywords}">
		<c:set var="captionText" value="${pageMetadata.pageKeywords}"></c:set>
	</c:if>
	
	<c:if test="${not empty pageMetadata.pageDescription}">
		<c:set var="descrText" value="${pageMetadata.pageDescription}"></c:set>
	</c:if>

	<script type="text/javascript">
		var title_txt = '${titleText}';
		var caption_txt = '${captionText}';
		var descr_txt = '${descrText}';
		var logo = 'www.uaq.ae/img/logo.png';
		var sub_value = "${pageMetadata.pageTitle}";
		var email_share = "mailto:?subject="+sub_value+"&body="+site_Url;
	</script>

