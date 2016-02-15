<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<link rel="stylesheet" type="text/css" href="/css/image-gallery.css">

<!-- Page Container Start -->
<div class="containe2r">
	<!-- Home Page Wrapper Start -->
	<div class="page-content-wrapper">
		<div class="row">
			<div class="col-lg-9 col-sm-9 col-md-9 col-xs-12">
				<!-- BreadCrumbs Start -->
				<div class="breadcrumbs hide-on-mobile">
				<spring:message code="label.pageLocation" />
				&nbsp; <a href="/${param.languageCode}/home.html"><spring:message code="header.home" /></a> 
				<c:forEach items="${navigations[param.languageCode]}"
					var="navigation">
					<c:if
						test="${(null!=parentLandingPage && navigation.assetId ==parentLandingPage.assetId) }">
						<span><i class="glyphicon glyphicon-menu-right"></i></span>
						<a href="/${param.languageCode}/${navigation.navigations[0].url}"><c:out
								value="${navigation.title}" /></a>
					</c:if>
				</c:forEach>
				<span><i class="glyphicon glyphicon-menu-right"></i></span> <spring:message code="gallery.image" />
						
			</div>
				<!-- BreadCrumbs End -->

				<!-- Page Content Start -->
				<div class="content-wrapper">
				<c:set var="subjectValue" value="${pageMetadata.pageTitle}"/>
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
					<div class="image-gallery">
						<div class="title">
							<div class="row">
								<div class="col-lg-3 col-sm-5 col-xs-12">
									<h3><spring:message code="gallery.image"/></h3>
								</div>
								<div class="col-lg-4 col-lg-offset-5 col-sm-offset-3 col-sm-4 hide-on-mobile"> 
									<form:form method="POST" id="imageForm" name="imageForm" action="${sourceURL}"
									commandName="imageSearchCommand">
										<form:select path="category" name="categories" id="categories"
											cssClass="form-control select-category" onchange="submitForm(this);">
											<option value="0">
												<spring:message code="option.select" />
											</option>
											<form:options items="${categoriesMap}" />
										</form:select>
									</form:form>
								</div>
							</div>
						</div>
						<c:choose>
						<c:when test="${not empty images}">
						<div class="gallery-wrapper">
							<div id="gallery" class="gallery-content">
								<div id="controls" class="controls"></div>
								<div class="slideshow-container">
									<div id="loading" class="loader"></div>
									<div id="slideshow" class="slideshow"></div>
								</div>

							</div>
							<div id="thumbs" class="navigation">
								<ul class="thumbs noscript">
									<c:forEach items="${images}" var="imageVO">
										<li><a class="thumb" name="" href="${imageVO.previewImage}" title="${imageVO.title}"> 
										<img src="${imageVO.galleryTeaserImage}" alt="${imageVO.title}" />
										</a></li>
									</c:forEach>
								</ul>
							</div>
						</div>
						</c:when>
						<c:otherwise>
							<spring:message code="label.no.images"></spring:message>
						</c:otherwise>
						</c:choose>
					</div>
				</div>
				<!-- Page Content End -->
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
			<ul class="utility-wrap image-gallery-share">
			<li>
				<!-- Print Icon -->
				<a href="#" class="print-icon"></a>
				</li>
				<li>
				<!-- Share Icon -->
				<a href="javascript:void(0);" class="share-icon share-all"></a>
				<div class="submenu" style="display: none;">
				<c:set var="subjectValue"><spring:message code='label.gallery.image'/></c:set>
					<ul class="root">
						<li class="share-fb"><a href="javascript:void(0);" target="_blank" alt=""> <img src="/img/icon-fb.png"
									alt="uaq"></a></li>
						<li><a href="javascript:void(0);" target="_blank"><img src="/img/icon-twitter.png" /></a></li>
						<li><a href="mailto:?subject=${subjectValue}&amp;Body=${sourceURL}"><img src="/img/icon-email.png" /></a></li>
					</ul>
				</div>
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
				 <c:forEach items="${parentLandingPage.navigations}" var="item" >
				 <c:choose>
						<c:when test="${source==item.url}">
								<li><a class="active" href="/${param.languageCode}/${item.url}">${item.title}</a></li>
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
	<!-- Home Page Wrapper End -->

</div>
<!-- Page Container End -->

<div class="feedback hide-on-mobile">
	<a href="#" class="feedback-link"><spring:message code="website.feedback"/></a>
</div>
<div class="overlay-wrapper">
	<div class="feedback-wrapper">
		<div class="logo-wrapper pull-left">
			<img src="/img/logo-state.png" alt="UMM Alquwain">
		</div>
		<div class="service-feedback pull-left">
			<h2><spring:message code="header.rateservice"/></h2>
			<ul>
				<li class="good"><a href="#"><spring:message code="label.good"/></a></li>
				<li class="average"><a href="#"><spring:message code="label.average"/></a></li>
				<li class="poor"><a href="#"><spring:message code="label.poor"/></a></li>
			</ul>
		</div>
	</div>
</div>

<c:set var="next"><spring:message code="pagination.next"/></c:set>
<c:set var="previous"><spring:message code="pagination.previous"/></c:set>
 
<script type="text/javascript" src="/js/lib/jquery.galleriffic.js"></script>
<script type="text/javascript" src="/js/lib/jquery.opacityrollover.js"></script>

<script type="text/javascript">
//$(".imageGallerySelect option:eq(0)").attr("disabled", "disabled");
	function submitForm(obj) {
		$('#imageForm').submit();
	}
</script>

<script type="text/javascript">
$('div.navigation').css({
    'width': '100%',
    'clear': 'both'
});
$('.gallery-content').css('display', 'block');

// Initially set opacity on thumbs and add
// additional styling for hover effect on thumbs
var onMouseOutOpacity = 0.67;
$('#thumbs ul.thumbs li').opacityrollover({
    mouseOutOpacity: onMouseOutOpacity,
    mouseOverOpacity: 1.0,
    fadeSpeed: 'fast',
    exemptionSelector: '.selected'
});

// Initialize Advanced Galleriffic Gallery
var gallery = $('#thumbs').galleriffic({
    delay: 2500,
    numThumbs: 18,
    preloadAhead: 10,
    enableTopPager: false,
    enableBottomPager: true,
    maxPagesToShow: 7,
    imageContainerSel: '#slideshow',
    controlsContainerSel: '#controls',
    captionContainerSel: '#caption',
    loadingContainerSel: '#loading',
    renderSSControls: false,
    renderNavControls: true,
    playLinkText: 'Play Slideshow',
    pauseLinkText: 'Pause Slideshow',
    prevLinkText: '${previous}',
    nextLinkText: '${next}',
    nextPageLinkText: '${next}',
    prevPageLinkText: '${previous}',
    enableHistory: false,
    autoStart: false,
    syncTransitions: true,
    defaultTransitionDuration: 900,
    onSlideChange: function (prevIndex, nextIndex) {
        // 'this' refers to the gallery, which is an extension of $('#thumbs')
        this.find('ul.thumbs').children()
            .eq(prevIndex).fadeTo('fast', onMouseOutOpacity).end()
            .eq(nextIndex).fadeTo('fast', 1.0);
    },
    onPageTransitionOut: function (callback) {
        this.fadeTo('fast', 0.0, callback);
    },
    onPageTransitionIn: function () {
        this.fadeTo('fast', 1.0);
    }
});
    </script>
