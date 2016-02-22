<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<div class="page-content-wrapper">
	<div class="row">
		<div class="col-lg-9 col-sm-9 col-md-9 col-xs-12">
			<!-- BreadCrumbs Start -->
			<div class="breadcrumbs hide-on-mobile">
				<spring:message code="label.pageLocation" /><a href="/${param.languageCode}/home.html"><spring:message	code="header.home" /></a>
				<c:forEach items="${navigations[param.languageCode]}" var="navigation">
				<c:if test="${(null!=parentLandingPage && navigation.assetId ==parentLandingPage.assetId)}">
				<span><i class="glyphicon glyphicon-menu-right"></i></span> <a href="/${param.languageCode}/${navigation.navigations[0].url}"><c:out value="${navigation.title}" /></a>
				</c:if>
				</c:forEach> 
				<span><i class="glyphicon glyphicon-menu-right"></i></span><spring:message code="${pageName}" />
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
								<%-- <li class="share-fb"><a href="" target="_blank" alt=""> <img src="/img/icon-fb.png" alt="uaq"></a></li>
								<li><a href="http://twitter.com/share?text=text" target="_blank"><img src="/img/icon-twitter.png"></a></li>
								<li><a href="mailto:?subject=${subjectValue}&amp;Body=${sourceURL}"><img src="/img/icon-email.png"></a></li> --%>
							</ul>
						</div>
					</li>
				</ul>
			</div>
			</div>

					<form:form method="POST" id="newsForm" name="newsForm"
						commandName="searchCommand" action="news-and-announcements.html">
						<%-- <div class="filter-wrapper">
						<div class="filter-text pull-left">
							<spring:message code="label.filter" />
						</div>

						<div class="filter-input pull-left">
							<c:set var="searchPlaceholder"><spring:message code='search.keyword'/></c:set>
							<form:input id="search" path="keyword" placeholder="${searchPlaceholder}" class="form-control form-txt"/>
						</div>
						<div class="filter-date pull-left">
							<c:set var="phFrom"><spring:message code='search.from'/></c:set>
							<c:set var="phTo"><spring:message code='search.to'/></c:set>
							<form:input path="startDate" placeholder="${phFrom}"
								cssClass="form-control  picker-input" />
							<form:input path="endDate" cssClass="form-control  picker-input" placeholder="${phTo}"/>
						</div>
						<div class="filter-category pull-left">
							<div class="custom-select-box">
								<form:select path="category" name="categories" id="categories"
									cssStyle="width:__">
									<option value="">
										<spring:message code="select.category" />
									</option>
									<form:options items="${categoriesMap}" />
								</form:select>
							</div>
						</div>
						<div class="filter-submit pull-left">
							<input type="hidden" name="source" value="${source}"> 
							<input type="submit" class="btn-filter-submit" value="<spring:message code='form.button.submit' />">
						</div>
						</div> --%>
					</form:form>
				
				<c:if test="${not empty searchResponse.searchArchaeologyHeritageResults}">

					<c:forEach items="${searchResponse.searchArchaeologyHeritageResults}" var="newsList">
						<article class="news-list award-listing">
								<div
									class="events-img-wrapper">
									<c:if test="${not empty newsList.images}">
										<c:forEach items="${newsList.images}" var="imageVO">
											<div class="listing-img">
												<img src="${imageVO.teaserImage}" alt="">
											</div>
										</c:forEach>
									</c:if>
								</div>
								<div class="events-content-wrapper">
									<h3 class="events-list-title">
										<a href="/${param.languageCode}/${landing}/${parent}/${newsList.url}.html">${newsList.teaserTitle}</a>
									</h3>
									
							<p>
								${newsList.teaserText}
								<a href="/${param.languageCode}/${landing}/${parent}/${newsList.url}.html"><spring:message code="landing.readmore"/></a>
							</p>
								</div>
						</article>
					</c:forEach>

				</c:if>	
				
				<div class="listing-count">
			    	<c:if test="${not empty searchResponse.searchArchaeologyHeritageResults}">
				        <span class="search-pagination-count-lbl">
				            <spring:message code='label.showing'/>  
				        </span>
				        <span class="search-pagination-count">${fn:length(searchResponse.searchArchaeologyHeritageResults)}&nbsp;<spring:message code='label.of'/>&nbsp;${searchResponse.totalNumberOfrows}&nbsp;<spring:message code='search.results.label2'/></span>
			        </c:if>
			        <c:if test="${empty searchResponse.searchArchaeologyHeritageResults}">
			        	<span class="search-pagination-count-lbl">
				            <spring:message code='search.no.results'/>  
				        </span>
				        
				        <div class="back-btn">
							<a href="javascipt:void(0)"><spring:message code="page.back" /></a>
						</div>	
			        </c:if>
			    </div>			
				
				<div class="pagination-wrapper">
					<nav>
						<!-- listing pagination -->
						<c:if test="${not empty searchResponse.searchArchaeologyHeritageResults}">

							<div>
								<%-- <div><spring:message code='search.results.label'/>&nbsp;${searchResponse.totalNumberOfrows}&nbsp;<spring:message code='search.results.label2'/></div> --%>

								<c:set var="count" value="1" />
								<ul class="pagination">
									<c:if test="${searchResponse.currentPage > 1 }">
										<li><a
											href="/${param.languageCode}/${landing}/${url}?currentPage=${1}"
											class="first"><spring:message code="pagination.first" /></a></li>
									</c:if>
									<c:if test="${searchResponse.lowerLimitPagination > 1}">
										<li><a
											href="/${param.languageCode}/${landing}/${url}?currentPage=${searchResponse.lowerLimitPagination - 1}"
											class="prev">&laquo;</a></li>
									</c:if>
									<c:if test="${searchResponse.totalNumberOfPages > 1}">
										<c:forEach var="pageCount"
											begin="${searchResponse.lowerLimitPagination}"
											end="${searchResponse.upperLimitPagination}" step="1">
											<c:if
												test="${pageCount <= searchResponse.totalNumberOfPages}">
												<c:choose>
													<c:when
														test="${not empty searchResponse.currentPage && pageCount==searchResponse.currentPage}">
														<li class="active"><a
															href="/${param.languageCode}/${landing}/${url}?currentPage=${pageCount}">${pageCount}</a></li>
													</c:when>
													<c:otherwise>
														<li><a
															href="/${param.languageCode}/${landing}/${url}?currentPage=${pageCount}">${pageCount}</a></li>
													</c:otherwise>
												</c:choose>

												<c:set var="count" value="${pageCount}" />
											</c:if>
										</c:forEach>
									</c:if>
									<c:if test="${searchResponse.totalNumberOfPages > count}">
										<li><a class="disabled">...</a></li>
										<li><a href="/${param.languageCode}/${landing}/${url}?currentPage=${searchResponse.totalNumberOfPages}">${searchResponse.totalNumberOfPages}</a></li>

									</c:if>
									<c:if
										test="${searchResponse.upperLimitPagination < searchResponse.totalNumberOfPages}">
										<li><a
											href="/${param.languageCode}/${landing}/${url}?currentPage=${searchResponse.upperLimitPagination +1}"
											class="next">&raquo;</a></li>
									</c:if>
									<c:if
										test="${searchResponse.currentPage != searchResponse.totalNumberOfPages}">
										<li><a
											href="/${param.languageCode}/${landing}/${url}?currentPage=${searchResponse.totalNumberOfPages}"
											class="last"><spring:message code="pagination.last" /></a></li>
									</c:if>

								</ul>
							</div>
						</c:if>
						<!-- /listing pagination -->
					</nav>
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
				<ul class="utility-wrap">
					<li>
						<!-- Print Icon --> <a href="#" class="print-icon"></a>
					</li>
					<li>
						<!-- Share Icon --> <a href="javascript:void(0);"
						class="share-icon share-all"></a>

						<div class="submenu" style="display: none;">
							<c:set var="subjectValue" value="${pageMetadata.pageTitle}"/>
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
							<c:when test="${source==item.url}">
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

<c:if test="${not empty site}">
	<c:set var="logo" value="/img/logo/${site}.png"></c:set>
</c:if>

<!-- <script type = "text/javascript">
	var title_txt = '${titleText}';
	var caption_txt = '${captionText}';
	var descr_txt = '${descrText}';
	var logo = '${logo}';
	var sub_value = '${titleText}';
	var email_share = 'mailto:?subject='+sub_value+'&body='+site_Url;
</script> -->