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
					<li class="in-active"><spring:message code="footer.news" /></li>
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
							<c:set var="subjectValue"><spring:message code='footer.news'/></c:set>
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
							<spring:message code="label.heading.news" />
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
				<!-- content filter -->
				<div class="content-filter-wrap">
					<div class="form-inline filer-wrap">
						<div class="form-group col-xs-2 col-sm-2 col-md-1">
							<label class="form-lbl"><spring:message
									code="label.search" /></label>
						</div>
						<form:form method="POST" id="eventForm" name="eventForm"
							commandName="searchCommand" action="/${param.languageCode}/news.html">
							<div class="form-group col-xs-10 col-sm-10 col-md-2">
								<c:set var="searchPlaceholder"><spring:message code='search.keyword'/></c:set>
								<form:input path="keyword" cssClass="form-control" placeholder="${searchPlaceholder}"/>
							</div>
							<div class="form-group datepic col-sm-6 col-xs-6 col-md-2">
								<label class="calendarHolder">
								<c:set var="phFrom"><spring:message code='search.from'/></c:set> 
								<form:input path="startDate" cssClass="form-control  datepicker" placeholder="${phFrom}"/> <i
									class="calendar"></i>
								</label>
							</div>

							<div class="form-group datepic col-xs-6 col-sm-6 col-md-2">
								<label class="calendarHolder">
								<c:set var="phTo"><spring:message code='search.to'/></c:set> 
								<form:input path="endDate" cssClass="form-control  datepicker" placeholder="${phTo}"/> <i
									class="calendar"></i>
								</label>
							</div>

							<div class="form-group col-xs-12 col-sm-6 col-md-3">
								<div class="custom-dropdown">
									<div class="custom-select-box">
										<form:select path="category" name="categories" id="categories"
											cssStyle="width:__">
											<option value="">
												<spring:message code="option.select" />
											</option>
											<form:options items="${categoriesMap}" />
										</form:select>
									</div>
								</div>
							</div>

							<div class="form-group col-xs-12 col-sm-6 col-md-2">
								<input type="submit" class="btn grey-color" value="<spring:message code="label.search" />">
							</div>
						</form:form>
					</div>
				</div>
				<!-- content filter -->
				<div class="content-listing cf">
					<!-- news listing -->
					<ul class="listing-wrap news-listing">
						<c:if test="${not empty searchResponse.searchResult}">
							<c:forEach items="${searchResponse.searchResult}" var="newsList">
								<li class="listing">
									<div class="listing-item">
										<c:if test="${not empty newsList.images}">
											<c:forEach items="${newsList.images}" var="imageVO">
												<div class="listing-img">
													<img src="${imageVO.teaserImage}" alt="">
												</div>
											</c:forEach>
										</c:if>
										<div class="listing-content">
											<span class="listing-date" dir="ltr">${newsList.postedDate}</span>
											<p class="red-text">
												<a href="/${param.languageCode}/news/${newsList.url}.html">${newsList.teaserTitle}</a>
											</p>
											<p>
											${newsList.teaserText}
											<a href="/${param.languageCode}/news/${newsList.url}.html"><spring:message code="landing.readmore"/></a>
											</p>
										</div>
									</div>
								</li>
							</c:forEach>
						</c:if>
					</ul>
					<!-- events listing -->
					
					<!-- listing pagination -->
					<c:if test="${not empty searchResponse.searchResult}">

						<div>
							<c:set var="count" value="1" />
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
							
							<ul class="pagination">
								<c:if test="${searchResponse.currentPage > 1 }">
									<li><a
										href="/${param.languageCode}/news.html?currentPage=${1}"
										class="first"><spring:message code="pagination.first" /></a></li>
								</c:if>
								<c:if test="${searchResponse.lowerLimitPagination > 1}">
									<li><a
										href="/${param.languageCode}/news.html?currentPage=${searchResponse.lowerLimitPagination - 1}"
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
														href="/${param.languageCode}/news.html?currentPage=${pageCount}">${pageCount}</a></li>
												</c:when>
												<c:otherwise>
													<li><a
														href="/${param.languageCode}/news.html?currentPage=${pageCount}">${pageCount}</a></li>
												</c:otherwise>
											</c:choose>

											<c:set var="count" value="${pageCount}" />
										</c:if>
									</c:forEach>
								</c:if>
								<c:if test="${searchResponse.totalNumberOfPages > count}">
									<li><a class="disabled">...</a></li>
									<li><a href="/${param.languageCode}/news.html?currentPage=${searchResponse.totalNumberOfPages}">${searchResponse.totalNumberOfPages}</a></li>

								</c:if>
								<c:if
									test="${searchResponse.upperLimitPagination < searchResponse.totalNumberOfPages}">
									<li><a
										href="/${param.languageCode}/news.html?currentPage=${searchResponse.upperLimitPagination +1}"
										class="next">&raquo;</a></li>
								</c:if>
								<c:if
									test="${searchResponse.currentPage != searchResponse.totalNumberOfPages}">
									<li><a
										href="/${param.languageCode}/news.html?currentPage=${searchResponse.totalNumberOfPages}"
										class="last"><spring:message code="pagination.last" /></a></li>
								</c:if>

							</ul>
						</div>
					</c:if>
					<!-- /listing pagination -->
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