<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
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
					<li class="in-active"><spring:message code="label.services" /></li>
				</c:if>
			</ul>
		</div>
	</div>
</div>
<!--/mainmenu -->
<div class="content cf">
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
							<c:set var="subjectValue"><spring:message code='label.department.services'/></c:set>
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
			<!-- support callout -->
				<c:if	test="${ not empty servicesCatalogPageVO.rhsIDViewObjectMap}"> 
				<c:forEach items="${servicesCatalogPageVO.rhsIDViewObjectMap}" var="rhsList">
					<c:if test="${rhsList.key=='Article' and not empty rhsList.value}">
						<c:forEach items="${rhsList.value}" var="articleVO">
							<c:set var="articleAsset" value="${articleVO.value}" />
							<div class="callout-wrap">
								<div class="support-callout-wrap">
									<img src="/img/icon-support.png" alt="" title="">
									<div class="support-content">
										<h4>${articleAsset.title}</h4>
										<p>${articleAsset.helpText1}</p>
										<p>${articleAsset.helpText2}&nbsp;
										<label class="device-tel">${articleAsset.telephoneNumber}</label>
										<a class="device-tel" href="tel:${articleAsset.telephoneNumber}">${articleAsset.telephoneNumber}</a></p>
										<a href="http://${articleAsset.urlLink}"><p class="red-text">${articleAsset.urlText}</p></a>
									</div>
								</div>
							</div>
						</c:forEach>
					</c:if>
				</c:forEach>
			</c:if>
			<!-- /support callout -->
		
		
		</div>
		<!-- right col -->
		<!-- left col -->
		<div class="col-xs-12 col-sm-9 col-md-9 main-left-col">
			<!-- page title -->
			<div class="page-title-wrap">
				<div class="row">
					<div class="col-xs-12 col-sm-9 col-md-10 title-txt">
						<!-- page title -->
						<h2 class="page-title">
							<spring:message code="header.service.catalogue" />
						</h2>
						<!-- /page title -->
					</div>
					<div class="hidden-xs show-sm col-md-2 title-icon">
						<img src="/img/icons/icon-uaq.png" alt="uaq">
					</div>
				</div>
			</div>
			<!-- /page title -->
			<!-- page content -->
			<div class="main-content-wrap">

				<!-- accordion listing -->
				<div class="service-catalogue-wrap">
					<div class="panel-groupFaq1" id="accordion1">
						<c:set var="iterator" value="0"></c:set>
						<c:forEach items="${serviceMap}" var="serviceMapVO">
							<c:set var="iterator" value="${iterator+1}"></c:set>
							<div class="panel panel-style service-panel">
								<div class="panel-heading accordion-toggle collapsed"
									data-toggle="collapse"
									data-parent="#accordion1,#accordion2,#accordion3"
									data-target="#${iterator}">
									<h4 class="panel-title">
										
										<c:out value="${serviceMapVO.key}"></c:out> 
									</h4>

								</div>
								<div id="${iterator}" class="panel-collapse collapse">
									<div class="panel-body">
										<ul class="service-catalogue-items">
												<c:forEach items="${serviceMapVO.value}" var="serviceVO">
													<li><a
														href="/${param.languageCode}/services/${serviceVO.url}"><c:out
																value="${serviceVO.title}" /></a></li>
											</c:forEach>

										</ul>

									</div>
								</div>
							</div>
						</c:forEach>


						<!-- /accordion listing -->
					</div>
				</div>
			</div>
			<!-- /page content -->
		</div>
		<!-- /left col -->
	</div>
</div>
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
		var sub_value = '${servicesCatalogPageVO.title}';
		var email_share = 'mailto:?subject='+sub_value+'&body='+site_Url;
	</script>