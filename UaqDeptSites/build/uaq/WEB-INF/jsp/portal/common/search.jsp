<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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
					<li class="in-active"><spring:message code="label.search" /></li>
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
							<li class="share-email">
								<a href="mailto:?&amp;Subject=UAQ%20Share&amp;Body=I%20saw%20this%20and%20thought%20of%20you!%20">
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
				<spring:message code="search.results.label" />
				</h2>
				<!-- /page title -->
			</div>
			
			<div class="hidden-xs show-sm col-md-2 title-icon">
				<img src="/img/banner/Global Search.png" alt="uaq">
			</div>
		</div>
	</div>
	<!-- /page title -->
	<!-- main content -->
	<div class="main-content-wrap">
		
    <!-- content filter -->
			<div class="content-filter-wrap">
			<div class="form-inline filer-wrap">
			<div class="col-xs-12 col-sm-7 col-md-6 pull-right">
				<div class="col-sm-5 col-md-4 remove-pad"><label class="form-lbl select-filter-lbl select-filter-txt"><spring:message code='label.search.here'/></label></div>
				
				<div class="col-sm-7 col-md-8 remove-pad">
					<form:form id="search-results" method="POST" commandName="searchCommand" action="/${param.languageCode}/search.html" role="form">
						<div class="input-group">
							<c:set var="searchPlaceholder"><spring:message code='search.keyword'/></c:set>
							<form:input id="search" path="keyword" placeholder="${searchPlaceholder}" class="form-control form-txt"/>
							<span class="input-group-btn">
								<button class="btn search-btn" type="submit" id="search-btn"></button>
							</span>
						</div>
					</form:form>
				</div>
					
				</div>
			</div>
			</div>
			<!-- content filter -->
			<!-- content pagination -->
<div class="search-pagination-wrap cf">
    <div class="listing-count">
    	<c:if test="${not empty searchResponse.searchResult}">
	        <span class="search-pagination-count-lbl">
	            <spring:message code='label.showing'/>  
	        </span>
	        <span class="search-pagination-count">${fn:length(searchResponse.searchResult)}&nbsp;<spring:message code='label.of'/>&nbsp;${searchResponse.totalNumberOfrows}&nbsp;<spring:message code='search.results.label2'/></span>
        </c:if>
        <c:if test="${empty searchResponse.searchResult}">
        	<span class="search-pagination-count-lbl">
	            <spring:message code='search.no.results'/>  
	        </span>
        </c:if>
    </div>
	    
</div>
<!-- content pagination -->
	<!-- content listing -->
<div class="content-listing cf">
<!-- listing -->
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
<!-- listing -->
<!-- listing pagination -->

<!-- /listing pagination -->
</div>

<c:if test="${not empty searchResponse.searchResult}" >		

			<div>
				<%-- <div><spring:message code='search.results.label'/>&nbsp;${searchResponse.totalNumberOfrows}&nbsp;<spring:message code='search.results.label2'/></div> --%>
						
				<c:set var="count" value="1" />
					<ul class="pagination">
						<c:if test="${searchResponse.currentPage > 1 }">
							<li><a
							href="/${param.languageCode}/search.html?currentPage=${1}"
							class="first"><spring:message code="pagination.first" /></a></li>
						</c:if>
					<c:if test="${searchResponse.lowerLimitPagination > 1}">
					<li><a href="/${param.languageCode}/search.html?currentPage=${searchResponse.lowerLimitPagination - 1}"
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
			</div>
	</c:if>

	</div>
	<!-- /main content -->
</div>
<!-- /left col -->
</div>
</div>

