<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

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
					<li class="in-active"><spring:message code="footer.awards" /></li>
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
								<a href="#"><img src="/img/icons/icon-rss.png" alt="uaq"></a>
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
							<c:set var="subjectValue"><spring:message code='label.awards'/></c:set>
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
						<img src="${awardsPageVO.titleIcon}" alt="news">
					</div>
				</div>
			</div>
			<!-- /page title -->
			<!-- page content -->
			<div class="main-content-wrap">
				<div class="content-listing cf">
					<!-- news listing -->
					<ul class="listing-wrap news-listing">
						<c:if test="${not empty searchResponse.searchAwardResults}">
							<c:forEach items="${searchResponse.searchAwardResults}" var="awardsList">
								<li class="listing">
									<div class="listing-item">
										<c:if test="${not empty awardsList.images}">
											<c:forEach items="${awardsList.images}" var="imageVO">
												<div class="listing-img">
													<img src="${imageVO.teaserImage}" alt="">
												</div>
											</c:forEach>
										</c:if>
										<div class="listing-content">
											<p class="red-text">
												<a href="/${param.languageCode}/award/${awardsList.url}.html">${awardsList.teaserTitle}</a>
											</p>
											<p>
												${awardsList.teaserText}
												<p class="red-text">
												<a href="/${param.languageCode}/award/${awardsList.url}.html"><spring:message code="landing.readmore"/></a>
												</p>
											</p>
										</div>
									</div>
								</li>
							</c:forEach>
						</c:if>
					</ul>
					<!-- events listing -->
					
					<!-- listing pagination -->
					<c:if test="${not empty searchResponse.searchAwardResults}">

						<div>
							<c:set var="count" value="1" />
							<ul class="pagination">
								<c:if test="${searchResponse.currentPage > 1 }">
									<li><a
										href="/${param.languageCode}/awards.html?currentPage=${1}"
										class="first"><spring:message code="pagination.first" /></a></li>
								</c:if>
								<c:if test="${searchResponse.lowerLimitPagination > 1}">
									<li><a
										href="/${param.languageCode}/awards.html?currentPage=${searchResponse.lowerLimitPagination - 1}"
										class="prev">&laquo;</a></li>
								</c:if>
								<c:if test="${searchResponse.totalNumberOfPages > 1}">
									<c:forEach var="pageCount"
										begin="${searchResponse.lowerLimitPagination}"
										end="${searchResponse.upperLimitPagination}" step="1">
										<c:if test="${pageCount <= searchResponse.totalNumberOfPages}">
											<c:choose>
												<c:when
													test="${not empty searchResponse.currentPage && pageCount==searchResponse.currentPage}">
													<li class="active"><a
														href="/${param.languageCode}/awards.html?currentPage=${pageCount}">${pageCount}</a></li>
												</c:when>
												<c:otherwise>
													<li><a
														href="/${param.languageCode}/awards.html?currentPage=${pageCount}">${pageCount}</a></li>
												</c:otherwise>
											</c:choose>

											<c:set var="count" value="${pageCount}" />
										</c:if>
									</c:forEach>
								</c:if>
								<c:if test="${searchResponse.totalNumberOfPages > count}">
									<li><a>...</a></li>
									<li><a>${searchResponse.totalNumberOfPages}</a></li>

								</c:if>
								<c:if
									test="${searchResponse.upperLimitPagination < searchResponse.totalNumberOfPages}">
									<li><a
										href="/${param.languageCode}/awards.html?currentPage=${searchResponse.upperLimitPagination +1}"
										class="next">&raquo;</a></li>
								</c:if>
								<c:if
									test="${searchResponse.currentPage != searchResponse.totalNumberOfPages}">
									<li><a
										href="/${param.languageCode}/awards.html?currentPage=${searchResponse.totalNumberOfPages}"
										class="last"><spring:message code="pagination.last" /></a></li>
								</c:if>

							</ul>
						</div>
					</c:if>
					<!-- /listing pagination -->
				</div>
				<div class="page-nav cf">
					<a href="#"><spring:message code="page.back" /></a>
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
		var sub_value = '${titleText}';
		var email_share = 'mailto:?subject='+sub_value+'&body='+site_Url;
	</script>