<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!--mainmenu -->
<div class="mainmenu">
	<div class="col-md-12 hidden-xs hidden-sm">
		<div class="mainmenu-wrap">
			<ul class="no-list cf">
				<li><a href="/${param.languageCode}/home.html"><img
						src="/img/home1.png" alt="Home UAQ"> </a></li>
				<c:if
					test="${not empty parentLandingPage.url and not empty parentLandingPage.title and not empty parentLandingPage.navigations}">
					<li class="active"><a href="${parentLandingPage.url}">${parentLandingPage.title}</a></li>

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
					<li class="in-active"><spring:message
							code="label.heading.awards" /></li>
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
							<li><a href="/${param.languageCode}/rssFeeds.html"><img
									src="/img/icons/icon-rss.png" alt="uaq"></a></li>
							<li class="share-fb"><a target="_blank" alt="share it"
								href="JavaScript:void(0);">
									<img src="/img/icons/icon-fb.png" alt="uaq">
							</a></li>
							<li class="share-twitter"><a target="_blank"
								href="JavaScript:void(0);"><img
									src="/img/icons/icon-twitter.png" alt="tweet"></a></li>
							<li class="share-print"><a href="#"><img
									src="/img/icons/icon-print.png" alt="uaq"></a></li>
							<c:set var="subjectValue"><spring:message code='label.awards'/></c:set>
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
							<spring:message code="label.heading.awards" />
						</h2>
						<!-- /page title -->
					</div>
					<div class="hidden-xs show-sm col-md-2 title-icon">
						<img src="/img/banner/News.png" alt="news">
					</div>
				</div>
			</div>
			<!-- /page title -->
			<!-- page content -->
			<div class="main-content-wrap">
				<!-- listing-details -->
				<div class="listing-details-wrap">
					<c:if test="${not empty awardVO.title}">
					<div class="sub-title">
						<h3>${awardVO.title}</h3>
					</div>
					</c:if>
					<div class="page-content-wrap">
						<div class="content-wrap">
							<div class="common-content cf">
								<c:if test="${not empty awardVO.images}">
									<c:forEach items="${awardVO.images}" var="imageVO">
									<c:if test="${not empty imageVO.genericContentImage}">
										<img src="${imageVO.genericContentImage}" class="img-left"
											alt="uaq">
									</c:if>
									<c:if test="${not empty imageVO.teaserImage}">
										<c:set var="teaserImage" value="${imageVO.teaserImage}"/>
									</c:if>
									</c:forEach>
								</c:if>
								<c:if test="${not empty awardVO.body}">
									<p>${awardVO.body}</p>
								</c:if>
							</div>
							<div class="page-nav cf">
								<a href="#"><spring:message code="page.back" /></a>
							</div>
						</div>
					</div>
				</div>
				<!-- /listing-details -->
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
		var logo = '${teaserImage}';
		var sub_value = '${awardVO.title}';
		var email_share = 'mailto:?subject='+sub_value+'&body='+site_Url;
	</script>