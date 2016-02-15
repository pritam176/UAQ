<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<div class="container-fluid">
	<div class="wrapper">
		<!-- header -->
		<header id="header" class="cf"> </header>
		<!-- /header -->
		<!--mainmenu -->
		<div class="mainmenu">
			<div class="col-md-12 hidden-xs hidden-sm">
				<div class="mainmenu-wrap">
					<ul class="no-list cf">
						<li><a href="/${param.languageCode}/home.html"><img
						src="/img/home1.png" alt="Home UAQ"> </a></li>
						</a></li>
						<li class="active"><a href="#"><spring:message
									code="header.sitemap" /></a></li>
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
									<li class="share-fb"><a target="_blank"
										href="JavaScript:void(0);">
											<img src="/img/icons/icon-fb.png" alt="uaq">
									</a></li>
									<li class="share-twitter"><a target="_blank"
										href="JavaScript:void(0);"><img
											src="/img/icons/icon-twitter.png" alt="tweet"></a></li>
									<li class="share-print"><a href="#"><img
											src="/img/icons/icon-print.png" alt="uaq"></a></li>
									<c:set var="subjectValue"><spring:message code='header.sitemap'/></c:set>
									<li class="share-email">
									<a href="mailto:?&amp;Subject=${subjectValue}&amp;Body=${sourceURL}">
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
				</div>
				<!-- right col -->
				<!-- left col -->
				<div class="col-xs-12 col-sm-9 col-md-9 main-left-col">
					<!-- page title -->
					<div class="page-title-wrap">
						<div class="row">
							<div class="col-xs-12 col-sm-9 col-md-10 title-txt">
								<!-- sub page title 
										<h4 class="page-title-sub">
										MUNICIPALITY
										</h4>-->
								<!-- /sub page title -->
								<!-- page title -->
								<h2 class="page-title">
									<spring:message code="header.sitemap" />
								</h2>
								<!-- /page title -->
							</div>
							<div class="hidden-xs show-sm col-md-2 title-icon">
								<img src="${sitemapVO.titleIcon}">
							</div>
						</div>
					</div>
					<!-- /page title -->
					<div class="main-content-wrap">
						<div class="page-content-wrap sitemap-wrap">
							<div class="row">
								<div class="col-md-12">
									<c:forEach items="${navigationList[param.languageCode]}"
										var="navigation">
										<div class="col-md-2 remove-pad">
											<ul class="first-level">
												<li><span class="first-head"><a
														href="/${param.languageCode}/${navigation.url}"><c:out
																value="${navigation.title}" /></a></span>
													<ul class="second-level">
														<c:forEach items="${navigation.navigations}"
															var="secondaryNav">

															<c:choose>
																<c:when test="${empty secondaryNav.navigations}">
																	<li><span class="third-head"><a
																		href="/${param.languageCode}/${secondaryNav.url}">
																			${secondaryNav.title}</a></span></li>
																</c:when>
																<c:when test="${not empty secondaryNav.navigations}">
																	<li><span class="third-head"><a
																			href="/${param.languageCode}/${secondaryNav.url}">${secondaryNav.title}</a></span></li>
																</c:when>

															</c:choose>

															<c:if test="${not empty secondaryNav.navigations}">
																<ul class="third-level">
																	<c:forEach items="${secondaryNav.navigations}"
																		var="thirdLevelNav">

																		<li><span class="sitemapcontent"><a
																				href="/${param.languageCode}/${thirdLevelNav.url}">
																					${thirdLevelNav.title} </a></span></li>

																	</c:forEach>
																</ul>
															</c:if>
														</c:forEach>
													</ul></li>
											</ul>

										</div>
									</c:forEach>


									<div class="col-md-12 remove-pad">
										<ul class="first-level">
											<li><span class="first-head"><a
													href="/${param.languageCode}/faq.html"><spring:message
															code="footer.faq" /></a></span></li>
											<li><span class="first-head"><a
													href="/${param.languageCode}/news.html"><spring:message
															code="footer.news" /></a></span></li>
											<li><span class="first-head"><a
													href="/${param.languageCode}/careers.html"><spring:message
															code="footer.careers" /></a></span></li>
											<li><span class="first-head"><a
													href="/${param.languageCode}/feedback.html"> <spring:message
															code="footer.feedback" /></a></span></li>
															<li><span class="first-head"><a
													href="/${param.languageCode}/content/PrivacyPolicy.html"> <spring:message
															code="footer.privacy.policy" /></a></span></li>
											<li><span class="first-head"><a
													href="/${param.languageCode}/content/TermsAndConditions.html"> <spring:message
															code="footer.terms.conditions" /></a></span></li>
											<li><span class="first-head"><a
													href="/${param.languageCode}/content/Disclaimer.html"> <spring:message
															code="footer.disclaimer" /></a></span></li>
											<li><span class="first-head"><a
													href="/${param.languageCode}/content/ContactUs.html"> <spring:message
															code="footer.contact.us" /></a></span></li>	
										</ul>
									</div>

								</div>
							</div>
						</div>
						<!-- /left col -->
					</div>
				</div>
			</div>
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

<script type="text/javascript">
	var title_txt = '${titleText}';
	var caption_txt = '${captionText}';
	var descr_txt = '${descrText}';
	var logo = 'www.uaq.ae/img/logo.png';
	var sub_value = '${titleText}';
	var email_share = 'mailto:?subject='+sub_value+'&body='+site_Url;
</script>