<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!--mainmenu -->
<div class="mainmenu">
	<div class="col-md-12 hidden-xs hidden-sm">
		<div class="mainmenu-wrap">
			<ul class="no-list cf">
				<li><a href="/${param.languageCode}/home.html"><img
						src="/img/home1.png" alt="Home UAQ"> </a></li>
				<c:if test="${not empty parentLandingPage.url and not empty parentLandingPage.title and not empty parentLandingPage.navigations}">
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
					<li class="in-active"><spring:message code="footer.careers"/></li>
				</c:if>
			</ul>
		</div>
	</div>
</div>
<!--/mainmenu -->
<!-- right col -->
<div class="content cf">
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
						 href="http://www.facebook.com/sharer/sharer.php?s=100&amp;p[url]=http://dev.uaq.ae/en&amp;p[images][0]=http://logicum.co/wp-content/uploads/2013/01/sharetweetbuttons.jpg&amp;p[title]=Creating Custom share buttons: Facebook, Twitter, Google+&amp;p[summary]=Build your custom share buttons from normal images with examples on each button">
							<img src="/img/icons/icon-fb.png" alt="uaq">
						  </a>
					</li>
					<li class="share-twitter">
						<a  target="_blank" href="http://www.twitter.com/share?url=http://www.google.com/"><img src="/img/icons/icon-twitter.png" alt="tweet"></a>
						
					</li>
					<li class="share-print">
						<a href="#"><img src="/img/icons/icon-print.png" alt="uaq"></a>
					</li>
					<c:set var="subjectValue"><spring:message code='career.detail.title'/></c:set>
					<li class="share-email">
						<a href="mailto:${careerEmailTo}?&amp;Subject=${subjectValue}&amp;Body=${sourceURL}">
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
	<!-- upload resume -->
	<div class="callout-wrap">
		<div class="callout-content">
			 <a href="mailto:${careerEmailTo}?Subject=New Resume" class="link-btn"><img src="/img/icons/icon-upload.png" alt="uaq"> <spring:message code="career.upload.button"/></a>
		</div>	
	</div>
	<!-- / upload resume -->
</div>
<!-- right col -->

<div class="col-xs-12 col-sm-9 col-md-9 main-left-col">
	<!-- page title -->
	<div class="page-title-wrap">
		<div class="row">
			<div class="col-xs-12 col-sm-9 col-md-10 title-txt">
				<!-- page title -->
				<h2 class="page-title">
					<spring:message code="footer.careers" />
				</h2>
				<!-- /page title -->
			</div>
			<div class="hidden-xs show-sm col-md-2 title-icon">
				<img src="${careersPageVO.titleIcon}" alt="uaq">
			</div>
		</div>
	</div>
	<!-- /page title -->
	<!-- page content -->
	<div class="main-content-wrap">
		<div class="content-listing cf">
			<!-- career listing -->
			<ul class="career-wrap">
				<c:if test="${not empty searchResponse.searchResults}">
					<c:forEach items="${searchResponse.searchResults}" var="jobVO">
						<li class="listing">
							<div class="listing-item">
								<div class="listing-content">
									<h4 class="red-text">
										<c:if test="${param.languageCode=='en'}">
											<a href="/${param.languageCode}/careers/${jobVO.name}.html"><strong>${jobVO.jobTitle},</strong>
												${jobVO.departmentNameEN}, <spring:message code="career.umm.al.quwain" />.</a>
										</c:if>
										<c:if test="${param.languageCode=='ar'}">
											<a href="/${param.languageCode}/careers/${jobVO.name}.html"><strong>${jobVO.jobTitle},</strong>
												${jobVO.departmentNameAR}, <spring:message code="career.umm.al.quwain" />.</a>
										</c:if>
									</h4>
									<p>${jobVO.teaserText}</p>
									<div class="career-ref">
										 <a href="mailto:${jobVO.mailTo}?Subject=<spring:message code="career.application.post" /> ${jobVO.jobTitle} <spring:message code="carrer.job.reference" /> ${jobVO.jobReferenceNumber}" class="apply-now"><span class="glyphicon glyphicon-file red-text"></span><spring:message code="career.apply.now"/></a> 
									</div>
								</div>
							</div>
						</li>
					</c:forEach>
				</c:if>
			</ul>
			<!-- careers listing -->
			<!-- listing pagination -->
			<c:if test="${not empty searchResponse.searchResults}">

				<div>
					<div>
						<spring:message code='search.results.label' />
						&nbsp;${searchResponse.totalNumberOfrows}&nbsp;
						<spring:message code='search.results.label2' />
					</div>

					<c:set var="count" value="1" />
					<ul class="pagination">
						<c:if test="${searchResponse.currentPage > 1 }">
							<li><a
								href="/${param.languageCode}/careers.html?currentPage=${1}"
								class="first"><spring:message code="pagination.first" /></a></li>
						</c:if>
						<c:if test="${searchResponse.lowerLimitPagination > 1}">
							<li><a
								href="/${param.languageCode}/careers.html?currentPage=${searchResponse.lowerLimitPagination - 1}"
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
												href="/${param.languageCode}/careers.html?currentPage=${pageCount}">${pageCount}</a></li>
										</c:when>
										<c:otherwise>
											<li><a
												href="/${param.languageCode}/careers.html?currentPage=${pageCount}">${pageCount}</a></li>
										</c:otherwise>
									</c:choose>

									<c:set var="count" value="${pageCount}" />
								</c:if>
							</c:forEach>
						</c:if>
						<c:if test="${searchResponse.totalNumberOfPages > count}">
							<li><a class="disabled">...</a></li>
							<li><a href="/${param.languageCode}/careers.html?currentPage=${searchResponse.totalNumberOfPages}">${searchResponse.totalNumberOfPages}</a></li>

						</c:if>
						<c:if
							test="${searchResponse.upperLimitPagination < searchResponse.totalNumberOfPages}">
							<li><a
								href="/${param.languageCode}/careers.html?currentPage=${searchResponse.upperLimitPagination +1}"
								class="next">&raquo;</a></li>
						</c:if>
						<c:if
							test="${searchResponse.currentPage != searchResponse.totalNumberOfPages}">
							<li><a
								href="/${param.languageCode}/careers.html?currentPage=${searchResponse.totalNumberOfPages}"
								class="last"><spring:message code="pagination.last" /></a></li>
						</c:if>

					</ul>
				</div>
			</c:if>
			<!-- /listing pagination -->
		</div>
	</div>
	</div>
	<!-- /page content -->
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