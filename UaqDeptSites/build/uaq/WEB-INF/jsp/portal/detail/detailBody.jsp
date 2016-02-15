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
				<li class="active"><a
					href="/${param.languageCode}/${parentLandingPage.url}">${parentLandingPage.title}</a></li>
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
			</ul>
		</div>
	</div>
</div>
<!--mainmenu -->

<c:set var="showRelatedAwards" value="false"></c:set>
<c:set var="showImageGallery" value="false"></c:set>
<c:set var="showArticleAsset" value="false"></c:set>
<c:if test="${not empty detailPageVO.rhsIDViewObjectMap}">
	<c:forEach items="${detailPageVO.rhsIDViewObjectMap}" var="rhsitmes">
		<c:if test="${rhsitmes.key=='ImageList'}">
			<c:set var="showImageGallery" value="true"></c:set>
		</c:if>
		<c:if test="${rhsitmes.key=='AwardsList'}">
			<c:set var="showRelatedAwards" value="true"></c:set>
		</c:if>
		<c:if test="${rhsitmes.key=='Article'}">
			<c:set var="showArticleAsset" value="true"></c:set>
		</c:if>
	</c:forEach>
</c:if>
<div class="content">
	<div class="row">
		<!-- right col -->
		<div class="col-xs-12 col-sm-3 col-md-3 main-right-col">

			<div class="callout-wrap">
				<div class="callout-content">
					<div class="social-share-wrap">
						<ul>
							<li><a href="/${param.languageCode}/rssFeeds.html"><img
									src="/img/icons/icon-rss.png" alt="uaq"></a></li>
							<li class="share-fb"><a target="_blank" alt="share it"
								href="http://www.facebook.com/sharer/sharer.php?s=100&amp;p[url]=http://dev.uaq.ae/en&amp;p[images][0]=http://logicum.co/wp-content/uploads/2013/01/sharetweetbuttons.jpg&amp;p[title]=Creating Custom share buttons: Facebook, Twitter, Google+&amp;p[summary]=Build your custom share buttons from normal images with examples on each button">
									<img src="/img/icons/icon-fb.png" alt="uaq">
							</a></li>
							<li class="share-twitter"><a target="_blank"
								href="http://www.twitter.com/share?url=http://www.google.com/"><img
									src="/img/icons/icon-twitter.png" alt="tweet"></a></li>
							<li class="share-print"><a href="#"><img
									src="/img/icons/icon-print.png" alt="uaq"></a></li>
							<c:set var="subjectValue" value="${detailPageVO.title}"/>
							<li class="share-email"><a href="mailto:?&amp;Subject=${subjectValue}&amp;Body=${sourceURL}"> 
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

			<!-- callout slider Holder -->
			<c:if test="${not empty detailPageVO.navigations }">
				<div class="callout-wrap">
					<div class="callout-head">
						<h5 class="right-nav-title">
							<c:out value="${detailPageVO.title}" />
						</h5>
					</div>
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
			</c:if>
			<!-- RHS for Awards and Image Gallery -->
			<c:forEach items="${detailPageVO.rhsIDViewObjectMap}" var="rhslist">
				<c:if
					test="${showImageGallery=='true' and not empty rhslist.value and rhslist.key=='ImageList'}">

					<div class="callout-wrap">
						<div class="callout-head">
							<h5 class="right-nav-title">
								<spring:message code="label.detail.gallery" />
							</h5>
						</div>
						<div class="callout-content">
							<div class="gallery-callout-wrap cf">
								<ul class="_gallery galleryUl col-md-12">
									<c:forEach items="${rhslist.value}" var="ImageVO">
										<c:if test="${not empty ImageVO.value}">
											<c:set value="${ImageVO.value}" var="imageAsset" />
											<li class="col-xs-4 col-sm-4 col-md-4 remove-pad"><a
												href="${imageAsset.originalImage}"><img
													src="${imageAsset.galleryTeaserImage}" alt=""></a></li>
										</c:if>
									</c:forEach>
								</ul>
								<a
									href="/${param.languageCode}/umm-al-quwain/media-room/imagegallery.html"
									class="link-btn"><spring:message
										code="label.detail.view.photos" /></a>
							</div>
						</div>
					</div>
				</c:if>
				<!-- gallery ends here -->
				<!-- Related Awards section -->
				<c:if
					test="${showRelatedAwards=='true' and not empty rhslist.value and rhslist.key=='AwardsList'}">
					<div class="callout-wrap">
						<div class="callout-head">
							<h5 class="right-nav-title">
								<spring:message code="label.awards" />
							</h5>
						</div>

						<div class="sliderHolder sliderHolder-right">
							<ul class="_slider">
								<c:forEach items="${rhslist.value}" var="awardVO">
									<c:if test="${not empty awardVO.value}">
										<c:set value="${awardVO.value}" var="awardAsset" />
										<c:forEach items="${awardAsset.images}" var="image">
											<li>
												<div class="slider-content">
													<img src="${image.teaserImage}" />
													<p>${awardAsset.teaserText}</p>
												</div>
											</li>
										</c:forEach>
									</c:if>
								</c:forEach>
							</ul>
						</div>
					</div>
				</c:if>
				<!-- /Related Awards section -->
			</c:forEach>
			<!-- /RHS for Awards and Image Gallery -->

			<!-- Related Article Asset section -->
			<c:if
				test="${showArticleAsset=='true' and not empty detailPageVO.rhsIDViewObjectMap}">
				<c:forEach items="${detailPageVO.rhsIDViewObjectMap}" var="rhsList">
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
			<!-- /Related Article Asset section -->
			<!-- /callout slider Holder-->
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
							<c:out value="${parentLandingPage.title}" />
						</h4>
						<!-- /sub page title -->
						<!-- page title -->
						<h2 class="page-title">
							<c:out value="${detailPageVO.title}" />
						</h2>
						<!-- /page title -->
					</div>
					<div class="hidden-xs show-sm col-md-2 title-icon">
						<img src="${detailPageVO.titleIcon}" alt="uaq">
					</div>
				</div>
			</div>
			<!-- /page title -->
			<div class="main-content-wrap">
				<div class="sub-title">
					<h3>${detailPageVO.heading}</h3>
				</div>
				<div class="page-content-wrap">
					<div class="content-wrap">
						<div class="common-content cf">
							<img src="${detailPageVO.image}"/>
							<c:set var="teaserImage" value="${imageVO.teaserImage}"></c:set>
							<p>${detailPageVO.body}</p>
						</div>

						<div class="page-nav cf">
							<a href="#"><spring:message code="page.back"></spring:message></a>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- /left col -->
	</div>
</div>
<!-- container fluid ends here -->
	<c:if test="${not empty pageMetadata.pageTitle}">
		<c:set var="titleText" value="${pageMetadata.pageTitle}"></c:set>
	</c:if>
	
	<c:if test="${not empty pageMetadata.pageKeywords}">
		<c:set var="captionText" value="${pageMetadata.pageKeywords}"></c:set>
	</c:if>
	
	<c:if test="${not empty pageMetadata.pageDescription}">
		<c:set var="descrText" value="${pageMetadata.pageDescription}"></c:set>
	</c:if>
	
	<c:if test="${not empty detailPageVO.teaserImage}">
		<c:set var="teaserImage" value="${detailPageVO.teaserImage}"></c:set>
	</c:if>
	
	<script type="text/javascript">
		var title_txt = "${titleText}";
		var caption_txt = "${captionText}";
		var descr_txt = "${descrText}";
		var logo = "${teaserImage}";
		var sub_value = "${detailPageVO.title}";
		var email_share = "mailto:?subject="+sub_value+"&body="+site_Url;
	</script>