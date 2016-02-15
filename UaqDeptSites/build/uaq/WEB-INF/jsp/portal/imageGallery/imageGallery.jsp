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
					<li class="active"><a
						href="/${param.languageCode}/${parentLandingPage.url}">${parentLandingPage.title}</a></li>
					<c:forEach items="${parentLandingPage.navigations}" var="item">
						<c:set var="flag" value="false"></c:set>
						<c:choose>
							<c:when test="${source==item.url}">
								<li class="active-sub"><a
									href="/${param.languageCode}/${item.url}">${item.title}</a></li>
							</c:when>
							<c:when test="${ not empty item.navigations  }">
								<c:forEach items="${item.navigations}" var="subItems">
									<c:choose>
										<c:when test="${source==subItems.url}">
											<li class="active-sub"><a
												href="/${param.languageCode}/${item.url}">${item.title}</a></li>
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
					<li class="in-active"><spring:message
							code="label.gallery.image" /></li>
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
									<li><a href="/${param.languageCode}/rssFeeds.html"><img src="/img/icons/icon-rss.png"
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
									<c:set var="subjectValue"><spring:message code='footer.faq'/></c:set>
									<li class="share-email"><a
										href="mailto:?&amp;Subject=${subjectValue}&amp;Body=${sourceURL}">
											<img src="/img/icons/icon-email.png" alt="uaq">
									</a></li>
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
							<c:forEach items="${detailPageVO.navigations}"
								var="thirdNavigations">
								<c:choose>
									<c:when test="${source==thirdNavigations.url}">
										<li class="active"><a
											href="/${param.languageCode}/${thirdNavigations.url}">${thirdNavigations.heading}</a></li>
									</c:when>
									<c:otherwise>
										<li><a
											href="/${param.languageCode}/${thirdNavigations.url}">${thirdNavigations.heading}</a></li>
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

						<!-- page sub title -->
						<h4 class="page-title-sub">
							<spring:message code="label.media.room" />
						</h4>
						<!-- page sub title -->

						<!-- page title -->
						<h2 class="page-title">
							<spring:message code="label.gallery.image" />
						</h2>
						<!-- /page title -->
					</div>
					<div class="hidden-xs show-sm col-md-2 title-icon">
						<img src="${imageGalleryPageVO.titleIcon}" alt="uaq">
					</div>
				</div>
			</div>
			<!-- /page title -->
			<!-- main content -->
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
								<form:form method="POST" id="imageForm" name="imageForm" action="${sourceURL}"
									commandName="imageSearchCommand">
									<form:select path="category" name="categories" id="categories"
										cssClass="_select imageGallerySelect" onchange="submitForm(this);">
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
				<div class="image-gallery-wrap">
					<div class="row">
					<c:choose>
						<c:when test="${not empty images}">
						<div class="col-md-5">
							<div class="image-gallery">
								<div id="gallery" class="content">
									<div class="image-show-wrap">
										<a href="javascript:void(0);" onclick="previewImage(this);"></a>
										<div id="image-show"></div>
									</div>

									<!-- Modal -->
									<div class="modal fade" id="large-image-modal" role="dialog">
										<div class="modal-dialog">

											<!-- Modal content-->
											<div class="modal-content">
												<button type="button" class="close close-image-modal"
													data-dismiss="modal"></button>
												<div class="modal-body">
													<div class="slideshow-container">
														<div id="loading" class="loader"></div>
														<div id="slideshow" class="slideshow"></div>
													</div>

												</div>
												<div class="modal-footer">
													<div id="controls" class="controls"></div>
													<div id="caption" class="caption-container">
														<div class="photo-index"></div>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="col-md-7">
						
							<div class="image-gallery">
								<div class="custom-scroller">
									<div id="thumbs" class="navigation">
										<div class="scrollbox">
											<ul class="thumbs noscript">
												<c:forEach items="${images}" var="image">
													<li><a class="thumb" name="" href="${image.image}"
														data-subimg="${image.previewImage}" title=""> <img
															src="${image.galleryTeaserImage}" alt="" />
													</a></li>
												</c:forEach>
											</ul>
										</div>
									</div>

								</div>
							</div>
							
						</div>
						</c:when>
							<c:otherwise>
								<spring:message code="label.no.images"></spring:message>
							</c:otherwise>
						</c:choose>
					</div>
				</div>
			</div>
			<!-- /main content -->
		</div>
		<!-- /left col -->
	</div>
</div>
<!-- /content area -->

<c:set var="next"><spring:message code="pagination.next"/></c:set>
<c:set var="previous"><spring:message code="pagination.previous"/></c:set>

<!-- script -->
<script src="/js/imageGallery.js"></script>
<script src="/js/jquery.ImageGallery.js"></script>
<script src="/js/jquery.opacityrollover.js"></script>
<script src="/js/enscroll-0.6.0.min.js"></script>
<script type="text/javascript">
//$(".imageGallerySelect option:eq(0)").attr("disabled", "disabled");
	function submitForm(obj) {
		$('#imageForm').submit();
	}
</script>

<c:choose>
<c:when test="${not empty images}">
<script type="text/javascript">
	$(document)
			.ready(
					function() {
						$('div.content').css('display', 'block');

						//controll opacity 
						var onMouseOutOpacity = 0.67;
						$('#thumbs ul.thumbs li').opacityrollover({
							mouseOutOpacity : onMouseOutOpacity,
							mouseOverOpacity : 1.0,
							fadeSpeed : 'fast',
							exemptionSelector : '.selected'
						});

						// Initialize Gallery
						var gallery = $('#thumbs')
								.galleriffic(
										{
											delay : 2500,
											numThumbs : 100000,
											preloadAhead : null,
											enableTopPager : false,
											enableBottomPager : false,
											maxPagesToShow : null,
											imageContainerSel : '#slideshow',
											controlsContainerSel : '#controls',
											captionContainerSel : '#caption',
											loadingContainerSel : '#loading',
											renderSSControls : true,
											renderNavControls : true,
											playLinkText : '',
											pauseLinkText : 'Pause Slideshow',
											prevLinkText : '${previous}',
											nextLinkText : '${next}',
											nextPageLinkText : '${next}',
											prevPageLinkText : '${previous}',
											enableHistory : false,
											autoStart : false,
											syncTransitions : true,
											defaultTransitionDuration : 900,
											onSlideChange : function(prevIndex,
													nextIndex) {
												this
														.find('ul.thumbs')
														.children()
														.eq(prevIndex)
														.fadeTo('fast',
																onMouseOutOpacity)
														.end().eq(nextIndex)
														.fadeTo('fast', 1.0);
												this.$captionContainer
														.find('div.photo-index')
														.html(
																'<span class="page-count">'
																		+ (nextIndex + 1)
																		+ '</span>'
																		+ '<span class="page-count">/</span>'
																		+ '<span class="page-count">'
																		+ this.data.length
																		+ '</span>');
											},
											onPageTransitionOut : function(
													callback) {
												this.fadeTo('fast', 0.0,
														callback);
											},
											onPageTransitionIn : function() {
												this.fadeTo('fast', 1.0);
											}
										});
					});

	$('#myModal').on('#preview-image', function(e) {
		e.preventDefault;
		$("#myModal").modal('show');
	});
	$('.scrollbox').enscroll();
</script>
  </c:when>
</c:choose> 
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