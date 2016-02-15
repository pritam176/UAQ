<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<div class="page-content-wrapper">
	<div class="row">
		<div class="col-lg-9 col-sm-9 col-md-9 col-xs-12">
			<!-- BreadCrumbs Start -->
					
			<div class="breadcrumbs hide-on-mobile">
				<spring:message code="label.pageLocation" />
				<a href="/${param.languageCode}/home.html"><spring:message code="header.home" /></a>
				<c:forEach items="${navigations[param.languageCode]}" var="navigation">
				<c:if test="${(null!=parentLandingPage && navigation.assetId ==parentLandingPage.assetId)}">
				
				<span><i class="glyphicon glyphicon-menu-right"></i></span> <a href="/${param.languageCode}/${navigation.navigations[0].url}"><c:out value="${navigation.title}" /></a>
				</c:if>
				</c:forEach>  
				<span><i class="glyphicon glyphicon-menu-right"></i></span>
				<spring:message code="label.department.reports"/>
			</div>
			<!-- BreadCrumbs End -->

			<!-- Page Content Start -->
			<div class="content-wrapper">
			<c:set var="subjectValue" value="${reportVO.title}"/>
			<div class="page-title-mob">
									<h2 class="main-title show-on-mobile title_mobile-share-wrap">${parentLandingPage.title}</h2>
									<div class="utility-icons show-on-mobile mobile-share-wrap" >
				<ul class="utility-wrap" style="float: right;">
					<li>
					
						<!-- Share Icon --> <a href="javascript:void(0);" class="share-icon share-all" id=""></a>

						<div class="submenu" style="display: none;">
							<ul class="root">
								<li class="share-fb"><a href="" target="_blank" alt=""> <img src="/img/icon-fb.png" alt="uaq"></a></li>
								<li><a href="http://twitter.com/share?text=text" target="_blank"><img src="/img/icon-twitter.png"></a></li>
								<li><a href="mailto:?subject=${subjectValue}&amp;Body=${sourceURL}"><img src="/img/icon-email.png"></a></li>
							</ul>
						</div>
					</li>
				</ul>
			</div>
			</div>
				<h2 class="main-title no-border">${reportVO.title}</h2>
				<div class="detail-post-content">
					<span class="post-date">${reportVO.datevalue}</span> <img
						src="${reportVO.images[0].genericContentImage}"
						class="pull-right" alt="">
					<c:set var="teaserImage" value="${reportVO.images[0].teaserImage}"/>
					<p>${reportVO.body}</p>
					<p>${reportVO.author}</p>

				</div>
			</div>
			<!-- Page Content End -->
			<!-- Back Button Start -->
			<div class="back-btn">
				<a href="javascipt:void(0)"><spring:message
						code="page.back" /></a>
			</div>
			<!-- Back Button End -->
			<!-- Back to Top Button Start -->
			<div class="back-to-top pull-right">
				<a href="#"><spring:message code="back.to.top" /> <i
					class="glyphicon glyphicon-triangle-top"></i></a>
			</div>
			<!-- Back to Top Button End -->
		</div>
		<div class="col-lg-3 col-sm-3 col-md-3 hide-on-mobile">
			<!-- Utility Icons Start -->
			<div class="utility-icons">
				<ul class="utility-wrap">
					<li>
						<!-- Print Icon --> <a href="#" class="print-icon"></a>
					</li>
					<li>
						<!-- Share Icon --> <a href="javascript:void(0);"
						class="share-icon share-all"></a>

						<div class="submenu" style="display: none;">
							<c:set var="subjectValue" value="${reportVO.title}"/>
							<ul class="root">
								<li class="share-fb"><a
									href="http://www.facebook.com/sharer/sharer.php?u=#url"
									target="_blank" alt=""> <img src="/img/icon-fb.png"
										alt="uaq"></a></li>
								<li><a href="http://twitter.com/share?text=text"
									target="_blank"><img src="/img/icon-twitter.png" /></a></li>
								<li><a
									href="mailto:?subject=${subjectValue}&amp;Body=${sourceURL}"><img
										src="/img/icon-email.png" /></a></li>
							</ul>
						</div>
					</li>
					<li>
						<!-- Font Increase and Decrease -->
						<ul class="font-changers">
							<li><a href="#" class="decrease-font"></a></li>
							<li><a href="#" class="reset-font"></a></li>
							<li><a href="#" class="increase-font"></a></li>
						</ul>
					</li>
				</ul>
			</div>
			<!-- Utility Icons End -->
			<!-- Side Bar Start -->
			<div class="side-bar">
					<c:if test="${null!=parentLandingPage}">
				<h2>
					<c:out value="${parentLandingPage.title}"/>
				</h2>
				</c:if>
				<ul class="side-nav">
				<c:forEach items="${parentLandingPage.navigations}" var="item">
					
						<c:choose>
							<c:when test="${activeNav==item.url}">
								<li><a class="active"
									href="/${param.languageCode}/${item.url}">${item.title}</a></li>
							</c:when>
							<c:otherwise>
								<li><a href="/${param.languageCode}/${item.url}">${item.title}</a></li>
							</c:otherwise>
						</c:choose>

					
				</c:forEach>
				</ul>
			</div>
			<!-- Side Bar End -->
		</div>
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

<script type = "text/javascript">
	var title_txt = '${titleText}';
	var caption_txt = '${captionText}';
	var descr_txt = '${descrText}';
	var logo = '${teaserImage}';
	var sub_value = '${reportVO.title}';
	var email_share = 'mailto:?subject='+sub_value+'&body='+site_Url;
</script>