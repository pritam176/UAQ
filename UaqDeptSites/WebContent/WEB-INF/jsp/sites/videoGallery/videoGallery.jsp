<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<!-- Home Page Wrapper Start -->
<div class="page-content-wrapper">
	<div class="row">
		<div class="col-lg-9 col-sm-9 col-md-9 col-xs-12">
			<!-- BreadCrumbs Start -->
			<div class="breadcrumbs hide-on-mobile">
				<spring:message code="label.pageLocation" />
				&nbsp; <a href="/${param.languageCode}/home.html"><spring:message code="header.home" /></a> 
				<c:forEach items="${navigations[param.languageCode]}" var="navigation">
				<c:if test="${(null!=parentLandingPage && navigation.assetId == parentLandingPage.assetId)}">
				<span><i class="glyphicon glyphicon-menu-right"></i></span> <a href="/${param.languageCode}/${navigation.navigations[0].url}"><c:out value="${navigation.title}" /></a>
				</c:if>
				</c:forEach> 
					<span><i class="glyphicon glyphicon-menu-right"></i></span>
				<spring:message code="gallery.video" />

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
								<li class="share-fb"><a href="" target="_blank" alt=""> <img src="/img/icon-fb.png" alt="uaq"></a></li>
								<li><a href="http://twitter.com/share?text=text" target="_blank"><img src="/img/icon-twitter.png"></a></li>
								<li><a href="mailto:?subject=${subjectValue}&amp;Body=${sourceURL}"><img src="/img/icon-email.png"></a></li>
							</ul>
						</div>
					</li>
				</ul>
			</div>
			</div>
				<div class="video-gallery">
					<div class="title">
						<div class="row">
							<div class="col-lg-3 col-sm-5 col-xs-12">
								<h3>
									<spring:message code="label.gallery.video" />
								</h3>
							</div>
							<div class="col-lg-4 col-lg-offset-5 col-sm-offset-3 col-sm-4 hide-on-mobile img-cat-select"> 
									<form:form method="POST" id="videoForm" name="videoForm" action="${sourceURL}"
									commandName="videoSearchCommand">
										<form:select path="category" name="categories" id="categories"
											cssClass="form-control select-category" onchange="submitForm(this);">
											<option value="0">
												<spring:message code="option.select" />
											</option>
											<form:options items="${categoriesMap}" />
										</form:select>
									</form:form>
							</div> 
						</div>
					</div>
					<c:choose>
					<c:when test="${not empty videos}">
					<div class="video-wrapper">
						<c:forEach items="${videos}" var="videoVO" varStatus="videoCount">
						<div style="display: none;" id="video${videoCount.count}">
							<video class="lg-video-object lg-html5" controls preload="true">
								<source  src="${videoVO.video}" type="video/mp4">
								 <spring:message code="label.videogallery.html.message" />
							</video>
						</div>
						</c:forEach>
						 
						<!-- data-src should not be provided when you use html5 videos -->
						<ul id="html5-videos" class="gallery">
						<c:forEach items="${videos}" var="videoVO" varStatus="videoCount">
						<li data-poster="${videoVO.teaserImage}"  data-html="#video${videoCount.count}" >
							  <img src="${videoVO.teaserImage}" />
							  <div class="gallery-hover">
                                    <i class="glyphicon glyphicon-play-circle"></i>
                                    <span class="hover-video-txt"><spring:message code="label.videogallery.hovertext" /></span>
                               </div>
						  </li>
						  </c:forEach>
						</ul>

						<div class="pagination-wrapper">
							<nav>
								<c:if test="${not empty videos}">

									<c:set var="count" value="1" />
									<ul class="pagination">
										<c:if test="${searchResponse.currentPage > 1 }">
											<li><a href="/${param.languageCode}/${landing}/videogallery.html?currentPage=${1}" class="first"><spring:message code="pagination.first" /></a></li>
										</c:if>
										<c:if test="${searchResponse.lowerLimitPagination > 1}">
											<li><a href="/${param.languageCode}/${landing}/videogallery.html?currentPage=${searchResponse.lowerLimitPagination - 1}" class="prev">&laquo;</a></li>
										</c:if>
										<c:if test="${searchResponse.totalNumberOfPages > 1}">
											<c:forEach var="pageCount" begin="${searchResponse.lowerLimitPagination}" end="${searchResponse.upperLimitPagination}" step="1">
												<c:if test="${pageCount <= searchResponse.totalNumberOfPages}">
													<c:choose>
														<c:when test="${not empty searchResponse.currentPage && pageCount==searchResponse.currentPage}">
															<li class="active"><a href="/${param.languageCode}/${landing}/videogallery.html?currentPage=${pageCount}">${pageCount}</a></li>
														</c:when>
														<c:otherwise>
															<li><a href="/${param.languageCode}/${landing}/videogallery.html?currentPage=${pageCount}">${pageCount}</a></li>
														</c:otherwise>
													</c:choose>

													<c:set var="count" value="${pageCount}" />
												</c:if>
											</c:forEach>
										</c:if>
										<c:if test="${searchResponse.totalNumberOfPages > count}">
											<li><a class="disabled">...</a></li>
											<li><a href="/${param.languageCode}/${landing}/videogallery.html?currentPage=${searchResponse.totalNumberOfPages}">${searchResponse.totalNumberOfPages}</a></li>

										</c:if>
										<c:if test="${searchResponse.upperLimitPagination < searchResponse.totalNumberOfPages}">
											<li><a href="/${param.languageCode}/${landing}/videogallery.html?currentPage=${searchResponse.upperLimitPagination +1}" class="next">&raquo;</a></li>
										</c:if>
										<c:if test="${searchResponse.currentPage != searchResponse.totalNumberOfPages}">
											<li><a href="/${param.languageCode}/${landing}/videogallery.html?currentPage=${searchResponse.totalNumberOfPages}" class="last"><spring:message code="pagination.last" /></a></li>
										</c:if>

									</ul>

								</c:if>
							</nav>
						</div>
					</div>
					</c:when>
					<c:otherwise>
						<spring:message code="label.no.videos"></spring:message>
					</c:otherwise>
					</c:choose>
				</div>

			</div>
			<!-- Page Content End -->
			<!-- Back to Top Button Start -->
			<div class="back-to-top pull-right">
				<a href="#"><spring:message code="back.to.top" /> <i class="glyphicon glyphicon-triangle-top"></i></a>
			</div>
			<!-- Back to Top Button End -->
		</div>
		<div class="col-lg-3 col-sm-3 col-md-3 hide-on-mobile">
			<!-- Utility Icons Start -->
			<div class="utility-icons">
			<ul class="utility-wrap video-gallery-share">
			<li>
				<!-- Print Icon -->
				<a href="#" class="print-icon"></a>
				</li>
				<li>
				<!-- Share Icon -->
				<a href="javascript:void(0);" class="share-icon share-all"></a>
				<div class="submenu" style="display: none;">
				<c:set var="subjectValue"><spring:message code='label.gallery.video'/></c:set>
					<ul class="root">
						<li class="share-fb"><a href="http://www.facebook.com/sharer/sharer.php?u=#url" target="_blank" alt=""> <img src="/img/icon-fb.png" alt="uaq"></a></li>
						<li><a href="http://twitter.com/share?text=text" target="_blank"><img src="/img/icon-twitter.png" /></a></li>
						<li><a href="mailto:?subject=${subjectValue}&amp;Body=${sourceURL}"><img src="/img/icon-email.png" /></a></li>
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
								<li><a class="active" href="/${param.languageCode}/${item.url}">${item.title}</a></li>
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
<!-- Home Page Wrapper End -->

<script type="text/javascript">
//$(".videoGallerySelect option:eq(0)").attr("disabled", "disabled");
	function submitForm(obj) {
		$('#videoForm').submit();
	}
</script>