<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<!-- Home Page Wrapper Start -->
<div class="page-content-wrapper">
	<div class="row">
		<div class="col-lg-9 col-sm-9 col-md-9 col-xs-12">
			<!-- BreadCrumbs Start -->
			<div class="breadcrumbs hide-on-mobile">
				<spring:message code="label.pageLocation"></spring:message>
				<a href="/${param.languageCode}/home.html"><spring:message code="header.home" /></a>
				
				<c:forEach items="${navigations[param.languageCode]}"
					var="navigation">
					<c:if
						test="${(null!=parentLandingPage && navigation.assetId ==parentLandingPage.assetId) }">
						<span><i class="glyphicon glyphicon-menu-right"></i></span>
						<a href="/${param.languageCode}/${navigation.navigations[0].url}"><c:out
								value="${navigation.title}" /></a>
					</c:if>
				</c:forEach>
				
				
				<span><i class="glyphicon glyphicon-menu-right"></i></span>
				
				<c:forEach items="${parentLandingPage.navigations}" var="item" begin="0">						
							<c:if test="${source==item.url}">
								${item.title}
							</c:if>					
					</c:forEach>

			</div>
			<!-- BreadCrumbs End -->

			<!-- Count the number of Categories in the serviceMap -->
			<c:forEach items="${serviceMap}" varStatus="c">
				<c:set var="CategoryCount" value="${c.count}"/>
			</c:forEach>
			<!-- End Count the number of Categories in the serviceMap -->
			
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
				<div class="row">
					<div class="col-lg-12 col-sm-12 col-xs-12">
						<div class="services-list panel-group">
							<div class="panel">
								<c:if test="${not empty serviceMap}">
									<c:choose>
									<c:when test="${CategoryCount==1}"> 
										<c:forEach items="${serviceMap}" var="serviceCategoryMap" varStatus="loop">
											<c:set var="categoryName" value="${serviceCategoryMap.key}"/>
											<div class="panel-heading active">
											    <h4 class="panel-title">
			                                    	${categoryName}
			                                    </h4>
					                        </div>
											<div class="panel-collapse" style="display: block;">
												<div class="panel-body">
													<ul>
														<c:forEach items="${serviceCategoryMap.value}" var="serviceVO">
															<li><a href="/${param.languageCode}/${parent}/service/${serviceVO.url}.html">${serviceVO.title}</a></li>
														</c:forEach>
													</ul>
												</div>
											</div>
										</c:forEach>
										</c:when>
										<c:otherwise>
											<c:forEach items="${serviceMap}" var="serviceCategoryMap" varStatus="loop">
												<c:set var="categoryName" value="${serviceCategoryMap.key}"/>
													<div class="panel-heading">
													    <h4 class="panel-title">
					                                    	${categoryName}
					                                    </h4>
							                        </div>
													<div class="panel-collapse">
														<div class="panel-body">
															<ul>
																<c:forEach items="${serviceCategoryMap.value}" var="serviceVO">
																	<li><a href="/${param.languageCode}/${parent}/service/${serviceVO.url}.html">${serviceVO.title}</a></li>
																</c:forEach>
															</ul>
														</div>
													</div>
											</c:forEach>
										</c:otherwise>
									</c:choose>
								</c:if>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- Page Content End -->
			<!-- Back to Top Button Start -->
			<div class="back-to-top pull-right">
				<a href="#"><spring:message code="back.to.top" /> <i class="glyphicon glyphicon-triangle-top"></i></a>
			</div>
			<!-- Back to Top Button End -->
			<div class="back-btn">
				<a href="javascipt:void(0)"><spring:message code="page.back" /></a>
			</div>
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
				<h2>${parentLandingPage.title}</h2>
				<ul class="side-nav">
					<c:forEach items="${parentLandingPage.navigations}" var="item" begin="0">
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