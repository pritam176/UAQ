<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<div class="page-content-wrapper feedback-form">
	<div class="row">
		<div class="col-lg-12 col-sm-12 col-md-12 col-xs-12">
			<!-- BreadCrumbs Start -->
			<div class="breadcrumbs hide-on-mobile">
				<spring:message code="label.pageLocation" />
				<a href="/${param.languageCode}/home.html"><spring:message
						code="header.home" /></a> <span><i
					class="glyphicon glyphicon-menu-right"></i></span>
				<spring:message code="header.sitemap" />
			</div>
			<!-- BreadCrumbs End -->

			<!-- Page Content Start -->
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
				<div class="page-content-wrap">
					<div class="page-content-wrap sitemap-wrap">
						<div class="row">
							<div class="col-md-12">
								<c:forEach items="${navigations[param.languageCode]}"
									var="navigation">
									<div class="col-md-2 remove-pad">
										<c:if test="${not empty navigation.navigations}">
											<ul class="first-level">

												<li><span class="first-head"><a
														href="/${param.languageCode}/${navigation.navigations[0].url}"><c:out
																value="${navigation.title}" /></a></span>

													<ul class="second-level">
														<c:forEach items="${navigation.navigations}"
															var="secondaryNav">

															<c:choose>
																<c:when test="${empty secondaryNav.navigations}">
																	<li><a
																		href="/${param.languageCode}/${secondaryNav.url}">
																			${secondaryNav.title}</a></li>
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
										</c:if>

									</div>
								</c:forEach>


								<div class="col-md-12 remove-pad">
									<ul class="first-level">



										<li><span class="first-head"><a
												href="/${param.languageCode}/content/PrivacyPolicy.html"><spring:message
														code="footer.privacy.policy" /></a></span></li>
										<li><span class="first-head"><a
												href="/${param.languageCode}/content/TermsAndConditions.html"><spring:message
														code="footer.terms.conditions" /></a></span></li>
										<li><span class="first-head"><a
												href="/${param.languageCode}/content/Disclaimer.html"><spring:message
														code="footer.disclaimer" /></a></span></li>

										<li><span class="first-head"><a
												href="/${param.languageCode}/feedback.html"><spring:message
														code="footer.feedback" /></a></span></li>
										<li><span class="first-head"><a
												href="/${param.languageCode}/content/ContactUs.html"><spring:message
														code="footer.contact.us" /></a></span></li>
									</ul>
								</div>

							</div>
						</div>
					</div>


				</div>
			</div>
			<!-- Page Content End -->
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

<c:if test="${not empty site}">
	<c:set var="logo" value="/img/logo/${site}.png"></c:set>
</c:if>

<script type = "text/javascript">
	var title_txt = '${titleText}';
	var caption_txt = '${captionText}';
	var descr_txt = '${descrText}';
	var logo = '${logo}';
	var sub_value = '${titleText}';
	var email_share = 'mailto:?subject='+sub_value+'&body='+site_Url;
</script>