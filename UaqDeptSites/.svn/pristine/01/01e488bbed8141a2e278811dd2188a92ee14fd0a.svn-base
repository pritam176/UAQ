<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>


<!-- Home Page Wrapper Start -->
<div class="page-content-wrapper">
	<div class="row">
		<div class="col-lg-9 col-sm-9 col-md-9 col-xs-12">
			<!-- BreadCrumbs Start -->
			<div class="breadcrumbs hide-on-mobile">
				<spring:message code="label.pageLocation" />
				<a href="/${param.languageCode}/home.html"><spring:message
						code="header.home" /></a> <span><i
					class="glyphicon glyphicon-menu-right"></i></span>
				<spring:message code="label.search" />

			</div>
			<!-- BreadCrumbs End -->

			<c:if test="${not empty searchResponse.searchResult}">
				<span class="search-pagination-count-lbl"> <spring:message
						code='label.showing' />
				</span>
				<span class="search-pagination-count">${fn:length(searchResponse.searchResult)}&nbsp;<spring:message
						code='label.of' />&nbsp;${searchResponse.totalNumberOfrows}&nbsp;<spring:message
						code='search.results.label2' /></span>
			</c:if>
			<c:if test="${empty searchResponse.searchResult}">
				<span class="search-pagination-count-lbl"> <spring:message
						code='search.no.results' />
				</span>
				<div class="back-btn">
							<a href="javascipt:void(0)"><spring:message code="page.back" /></a>
						</div>
			</c:if>

			<!-- Page Content Start -->
			<div class="content-wrapper">
				<ul class="listing-wrap search-listing">
					<c:if test="${not empty searchResponse.searchResult}">
						<c:forEach items="${searchResponse.searchResult}" var="search">
							<li class="listing">
								<div class="listing-item">
									<div class="listing-content">
										<a href="${search.url}"><strong>${search.teaserTitle}</strong></a>
										<p>${search.teaserText}</p>
									</div>
								</div>
							</li>
						</c:forEach>
					</c:if>

				</ul>
				<div class="pagination-wrapper">
					<nav>
						<c:if test="${not empty searchResponse.searchResult}">

							<c:set var="count" value="1" />
							<ul class="pagination">
								<c:if test="${searchResponse.currentPage > 1 }">
									<li><a
										href="/${param.languageCode}/search.html?currentPage=${1}"
										class="first"><spring:message code="pagination.first" /></a></li>
								</c:if>
								<c:if test="${searchResponse.lowerLimitPagination > 1}">
									<li><a
										href="/${param.languageCode}/search.html?currentPage=${searchResponse.lowerLimitPagination - 1}"
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
														href="/${param.languageCode}/search.html?currentPage=${pageCount}">${pageCount}</a></li>
												</c:when>
												<c:otherwise>
													<li><a
														href="/${param.languageCode}/search.html?currentPage=${pageCount}">${pageCount}</a></li>
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
										href="/${param.languageCode}/search.html?currentPage=${searchResponse.upperLimitPagination +1}"
										class="next">&raquo;</a></li>
								</c:if>
								<c:if
									test="${searchResponse.currentPage != searchResponse.totalNumberOfPages}">
									<li><a
										href="/${param.languageCode}/search.html?currentPage=${searchResponse.totalNumberOfPages}"
										class="last"><spring:message code="pagination.last" /></a></li>
								</c:if>

							</ul>
						</c:if>
					</nav>
				</div>
			</div>
			<!-- Page Content End -->
			<!-- Back to Top Button Start -->
			<div class="back-to-top pull-right">
				<a href="#"><spring:message code="back.to.top" /><i
					class="glyphicon glyphicon-triangle-top"></i></a>
			</div>
			<!-- Back to Top Button End -->
		</div>
		<div class="col-lg-3 col-sm-3 col-md-3 hide-on-mobile"></div>
	</div>
</div>
<!-- Home Page Wrapper End -->