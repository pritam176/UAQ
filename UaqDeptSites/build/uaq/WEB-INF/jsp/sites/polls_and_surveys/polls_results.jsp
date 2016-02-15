<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!-- Home Page Wrapper Start -->
<div class="page-content-wrapper">
	<div class="row">
		<div class="col-lg-9 col-sm-9 col-md-9 col-xs-12">
				<!-- BreadCrumbs Start -->
			<div class="breadcrumbs hide-on-mobile">
				<spring:message code="label.pageLocation" />
				&nbsp; <a href="/${param.languageCode}/home.html"><spring:message code="header.home" /></a> 
				<span><i class="glyphicon glyphicon-menu-right"></i></span> <a href="/${param.languageCode}/${landing}/news.html"><spring:message code="breadcrum.media" /></a>
				<span><i class="glyphicon glyphicon-menu-right"></i></span>
				<c:if test="${pageName == 'polls'}">
					<spring:message code="poll.results" />
				</c:if>
				<c:if test="${pageName == 'surveys'}">
					<spring:message code="survey" />
				</c:if>
			</div>
			<!-- BreadCrumbs End -->


			<!-- Page Content Start -->
			<div class="content-wrapper poll-result">
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
				<c:forEach items="${pollsResults}" var="poll">

					<div class="poll-question">
						<h3 class="sub-title"><spring:message code="poll.results"/></h3>
						<c:forEach var="questionFormField" items="${poll.formFields}">

							<section class="question question-result">
								<div class="row">
									<div class="col-lg-5 col-sm-3 col-xs-12">
										<c:if test="${param.languageCode == 'en' }">
											<h3>${fn:split(questionFormField.text, '|')[0]}</h3>
										</c:if>
										<c:if test="${param.languageCode == 'ar' }">
											<h3>${fn:split(questionFormField.text, '|')[1]}</h3>
										</c:if>
									</div>
									<div class="col-lg-3 col-sm-4 col-xs-6 x2">
									<c:forEach var="optionResult" items="${questionFormField.optionsResult}">
										
											<div class="question-holder">
												<label for="square-radio-1">
												<c:if test="${param.languageCode == 'en' }">
												${fn:split(optionResult.optionId, '|')[0]}
													
												</c:if>
												<c:if test="${param.languageCode == 'ar' }">
													${fn:split(optionResult.optionId, '|')[1]}
												</c:if>
												</label>
											</div>
										</c:forEach>
										</div>
										<c:forEach var="optionResult" items="${questionFormField.optionsResult}" varStatus="count">
										<div class="col-lg-4 col-sm-5 col-xs-6 progress-wrapper">
											<div class="progress-holder wrapper-${count.index}">
												<div class="progress" style="width: ${optionResult.percentage}">
												</div>
												<span class="poll-result-txt">${optionResult.percentage}</span>
											</div>
											
										</div>
									</c:forEach>
								</div>
							</section>
						</c:forEach>
					</div>
				</c:forEach>
			</div>
			<!-- Page Content End -->
			<!-- Survey Nav Start -->
			<!-- <div class="survey-nav-wrapper">
                        <a href="#" class="btn disable">Prev</a>
                        <a href="#" class="btn">Next</a>
                    </div> -->
			<!-- Survey Nav End -->
			<!-- Back to Top Button Start -->
			
			<div class="back-btn">
				<a href="javascipt:void(0)"><spring:message
						code="page.back" /></a>
			</div>
			<div class="back-to-top pull-right">
				<a href="#"><spring:message code="back.to.top" /> <i class="glyphicon glyphicon-triangle-top"></i></a>
			</div>
			<!-- Back to Top Button End -->
		</div>
		<div class="col-lg-3 col-sm-3 col-md-3 hide-on-mobile">
			<!-- Utility Icons Start -->
			<div class="utility-icons">
				
				<div class="submenu" style="display: none;">
					<c:set var="subjectValue"><spring:message code='poll.results'/></c:set>
					<ul class="root">
						<li class="share-fb"><a href="http://www.facebook.com/sharer/sharer.php?u=#url" target="_blank" alt=""> <img src="/img/icon-fb.png"
									alt="uaq"></a></li>
						<li><a href="http://twitter.com/share?text=text" target="_blank"><img src="/img/icon-twitter.png" /></a></li>
						<li><a href="mailto:?subject=${subjectValue}&amp;Body=${sourceURL}"><img src="/img/icon-email.png" /></a></li>
					</ul>
				</div>
				<!-- Font Increase and Decrease -->
				
				<ul class="utility-wrap">
					<li>
						<!-- Print Icon --> <a href="#" class="print-icon"></a>
					</li>
					<li>
						<!-- Share Icon --> <a href="javascript:void(0);"
						class="share-icon share-all"></a>

						<div class="submenu" style="display: none;">
							<c:set var="subjectValue">
								<c:set var="subjectValue"><spring:message code='polls'/></c:set>
							</c:set>
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
				<h2>
					<spring:message code="media.centre" />
				</h2>

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
</script>