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
				<c:if
					test="${not empty parentLandingPage.url and not empty parentLandingPage.title and not empty parentLandingPage.navigations}">
					<li class="active"><a
						href="/${param.languageCode}/${parentLandingPage.url}">${parentLandingPage.title}</a></li>
					<c:forEach items="${parentLandingPage.navigations}" var="item">
						<c:set var="flag" value="false"></c:set>
						<c:choose>
							<c:when test="${source==item.url}">
								<li class="active-sub"><a
									href="/${param.languageCode}/${item.url}">${item.title}</a></li>
							</c:when>
							<c:when test="${ not empty item.navigations  }">
								<c:forEach items="${item.navigations}" var="subItems">
									<c:choose>
										<c:when test="${source==subItems.url}">
											<li class="active-sub"><a
												href="/${param.languageCode}/${item.url}">${item.title}</a></li>
											<c:set var="flag" value="true"></c:set>
										</c:when>
									</c:choose>
								</c:forEach>
								<c:if test="${flag=='false'}">
									<li><a href="/${param.languageCode}/${item.url}">${item.title}</a></li>
								</c:if>
							</c:when>
							<c:otherwise>
								<li><a href="/${param.languageCode}/${item.url}">${item.title}</a></li>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</c:if>
				<c:if test="${empty parentLandingPage}">
					<li class="in-active"><spring:message code="label.events" /></li>
				</c:if>
			</ul>
		</div>
	</div>
</div>
<!--/mainmenu -->
<c:set var="navigationPath" value="${fn:split(source, '/')}"></c:set>
<c:set var="navigationLength" value="${fn:length(navigationPath)}"></c:set>
<!--body starts from here -->
<div class="content cf">
	<div class="row">
		<!-- right col -->
		<div class="col-xs-12 col-sm-3 col-md-3 main-right-col">
			<!-- social media share callout -->
			<!-- social media share callout -->
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
							<c:set var="subjectValue"><spring:message code='footer.events'/></c:set>
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
			<c:if test="${not empty detailPageVO.navigations }">
				<div class="callout-wrap">
					<div class="callout-head">
						<h5 class="right-nav-title">${detailPageVO.title}</h5>
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
			<!-- /callout -->
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
							<spring:message code="label.media.room" />
						</h4>
						<!-- /sub page title -->
						<!-- page title -->
						<h2 class="page-title">
							<spring:message code="label.events" />
						</h2>
						<!-- /page title -->
					</div>
					<div class="hidden-xs show-sm col-md-2 title-icon">
						<img src="/img/banner/Events.png" alt="uaq">
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
							commandName="searchCommand"
							action="/${param.languageCode}/${parent}/${landing}/events.html">
							<div class="form-group col-xs-10 col-sm-10 col-md-2">
								<!--<input type="search" class="form-control" id="" placeholder="Search Keyword">-->
								<c:set var="phKeywords">
									<spring:message code='search.keyword' />
								</c:set>
								<form:input path="keyword" cssClass="form-control" id=""
									placeholder="${phKeywords}" />
							</div>
							<div class="form-group datepic col-sm-6 col-xs-6 col-md-2">
								<label class="calendarHolder"> <!-- <input type="text" name="" id="dp1440398218723" class="form-control  datepicker hasDatepicker" placeholder="From">-->
									<c:set var="phFrom">
										<spring:message code='search.from' />
									</c:set> <form:input path="startDate" class=" form-control datepicker"
										placeholder="${phFrom}" /> <i class="calendar"></i>
								</label>
							</div>

							<div class="form-group datepic col-xs-6 col-sm-6 col-md-2">
								<label class="calendarHolder"> <!--<input type="text" name="" id="dp1440398218724" class="form-control  datepicker hasDatepicker" placeholder="To">-->
									<c:set var="phTo">
										<spring:message code='search.to' />
									</c:set> <form:input path="endDate" class="form-control  datepicker"
										placeholder="${phTo}" /> <i class="calendar"></i>
								</label>
							</div>

							<div class="form-group col-xs-12 col-sm-6 col-md-3">
								<div class="custom-dropdown">
									<div class="custom-select-box">
										<%-- <form:form commandName="searchCommand"> --%>
										<form:select path="category" name="categories" id="categories">
											<option value="">
												<spring:message code="option.select" />
											</option>
											<form:options items="${categoryMap}" />
										</form:select>
										<%-- </form:form> --%>
										<span class="error cf">
											<%-- <spring:message code="label.dropdown.message" /> --%>
										</span>
									</div>
								</div>
							</div>

							<div class="form-group col-xs-12 col-sm-6 col-md-2">
								<input type="submit" class="btn grey-color"
									value="<spring:message code='search.title'/>">
							</div>
						</form:form>
					</div>
				</div>

				<c:if test="${not empty searchResponse.searchResults}">
					<c:forEach items="${searchResponse.searchResults}" var="eventVO">
						<!-- content filter -->
						<!-- content listing -->
						<div class="content-listing cf">
							<!-- events listing -->
							<ul class="listing-wrap event-listing">
								<li class="listing">
									<div class="listing-item">
										<c:if test="${not empty eventVO.images}">
											<c:forEach items="${eventVO.images}" var="imageVO">
												<div class="listing-img">
													<img src="${imageVO.teaserImage}" class="img-left"
														alt="uaq">
												</div>

											</c:forEach>
										</c:if>


										<div class="listing-content">
											<c:choose>
												<c:when test="${navigationLength >2 }">
													<p class="red-text">
														<a
															href="/${param.languageCode}/${parent}/${landing}/events/${eventVO.url}.html?parentPage=media-room"><c:out
																value="${eventVO.teaserTitle}" /></a>
													</p>
												</c:when>
												<c:otherwise>
													<p class="red-text">
														<a
															href="/${param.languageCode}/events/${eventVO.url}.html"><c:out
																value="${eventVO.teaserTitle}" /></a>
													</p>
												</c:otherwise>

											</c:choose>

											<p>
												<c:out value="${eventVO.teaserText}" />
											</p>

											<dl class="venue-txt">
												<dt>
													<spring:message code="label.events.duration" />
													<span class="pull-right m-right20">:</span>
												</dt>
												<dd>
													<span><c:out value="${eventVO.openingDate}"></c:out> - <c:out value="${eventVO.endingDate}"></c:out></span>
												</dd></br>
												<dt>
													<spring:message code="label.events.venue" />
													<span class="pull-right m-right20">:</span>
												</dt>
												<dd>

													<c:out value="${eventVO.addressLine1}" />
												</dd>
											</dl>
											<c:choose>
												<c:when test="${navigationLength >2 }">
													<a class="red-text listing-txt-link"
														href="/${param.languageCode}/${parent}/${landing}/events/${eventVO.url}.html?parentPage=media-room"><spring:message
															code="label.view.detail" /></a>
												</c:when>
												<c:otherwise>
													<a class="red-text listing-txt-link"
														href="/${param.languageCode}/events/${eventVO.url}.html"><spring:message
															code="label.view.detail" /></a>
												</c:otherwise>
											</c:choose>
										</div>
									</div>
								</li>
							</ul>
					</c:forEach>
				</c:if>

				<!-- events listing -->
				<!-- listing pagination -->
				<c:if test="${not empty searchResponse.searchResults}">

					<div>
						<c:set var="count" value="1" />
						<div class="search-pagination-wrap cf">
							<div class="listing-count">
								<c:if test="${not empty searchResponse.searchResults}">
									<span class="search-pagination-count-lbl"> <spring:message
											code='label.showing' />
									</span>
									<span class="search-pagination-count">${fn:length(searchResponse.searchResults)}&nbsp;<spring:message
											code='label.of' />&nbsp;${searchResponse.totalNumberOfrows}&nbsp;<spring:message
											code='search.results.label2' /></span>
								</c:if>
								<c:if test="${empty searchResponse.searchResults}">
									<span class="search-pagination-count-lbl"> <spring:message
											code='search.no.results' />
									</span>
								</c:if>
							</div>
						</div>
						<ul class="pagination">
							<c:if test="${searchResponse.currentPage > 1 }">
								<li><a
									href="/${param.languageCode}/${parent}/${landing}/events.html?currentPage=${1}"
									class="first"><spring:message code="pagination.first" /></a></li>
							</c:if>
							<c:if test="${searchResponse.lowerLimitPagination > 1}">
								<li><a
									href="/${param.languageCode}/${parent}/${landing}/events.html?currentPage=${searchResponse.lowerLimitPagination - 1}"
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
													href="/${param.languageCode}/${parent}/${landing}/events.html?currentPage=${pageCount}">${pageCount}</a></li>
											</c:when>
											<c:otherwise>
												<li><a
													href="/${param.languageCode}/${parent}/${landing}/events.html?currentPage=${pageCount}">${pageCount}</a></li>
											</c:otherwise>
										</c:choose>

										<c:set var="count" value="${pageCount}" />
									</c:if>
								</c:forEach>
							</c:if>
							<c:if test="${searchResponse.totalNumberOfPages > count}">
								<li><a class="disabled">...</a></li>
								<li><a href="/${param.languageCode}/${parent}/${landing}/events.html?currentPage=${searchResponse.totalNumberOfPages}">${searchResponse.totalNumberOfPages}</a></li>

							</c:if>
							<c:if
								test="${searchResponse.upperLimitPagination < searchResponse.totalNumberOfPages}">
								<li><a
									href="/${param.languageCode}/${parent}/${landing}/events.html?currentPage=${searchResponse.upperLimitPagination +1}"
									class="next">&raquo;</a></li>
							</c:if>
							<c:if
								test="${searchResponse.currentPage != searchResponse.totalNumberOfPages}">
								<li><a
									href="/${param.languageCode}/${parent}/${landing}/events.html?currentPage=${searchResponse.totalNumberOfPages}"
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
	
	<script type="text/javascript">
		var title_txt = '${titleText}';
		var caption_txt = '${captionText}';
		var descr_txt = '${descrText}';
		var logo = 'www.uaq.ae/img/logo.png';
		var sub_value = '${titleText}';
		var email_share = 'mailto:?subject='+sub_value+'&body='+site_Url;
	</script>