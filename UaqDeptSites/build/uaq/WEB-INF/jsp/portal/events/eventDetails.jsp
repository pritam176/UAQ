<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
					<li class="active"><a href="/${param.languageCode}/${parentLandingPage.url}">${parentLandingPage.title}</a></li>
					<c:forEach items="${parentLandingPage.navigations}" var="item">
						<c:choose>
							<c:when test="${fn:containsIgnoreCase(item.url, sourceURL)}">
								<li class="active-sub"><a href="/${param.languageCode}/${item.url}">${item.title}</a></li>
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
												<c:set var="subjectValue"><spring:message code='footer.events'/></c:set>
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
							
							<!-- page title -->
						<c:if test="${not empty detailPageVO.navigations }">
			<div class="callout-wrap">
				<div class="callout-head">
					<h5 class="right-nav-title">
						<spring:message code="label.media.room" />
					</h5>
					</div>
					<div class="callout-content">
					<div  class="right-nav">
					<ul>
									<c:forEach items="${detailPageVO.navigations}"	var="thirdNavigations">
									<c:choose>
										<c:when test="${fn:containsIgnoreCase(thirdNavigations.url, 'events')}">
											<li class="active"><a href="/${param.languageCode}/${thirdNavigations.url}">${thirdNavigations.heading}</a></li>
											</c:when>
										<c:otherwise>
											<li ><a href="/${param.languageCode}/${thirdNavigations.url}">${thirdNavigations.heading}</a></li>
										</c:otherwise>
									</c:choose>
									</c:forEach>
								</ul>
				</div>
				</div>
			</div></c:if>

						</div>
						<!-- right col -->
							
						<!-- left col -->
						<div class="col-xs-12 col-sm-9 col-md-9 main-left-col">
								<div class="page-title-wrap">
								<div class="row">
									<div class="col-xs-12 col-sm-9 col-md-10 title-txt">
										<!-- page title -->
										<h4 class="page-title-sub">
							<spring:message code="label.media.room" />
						</h4>
						<h2 class="page-title"><spring:message code="label.events" /></h2>
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
								<!-- event details -->
								<div class="listing-detail-wrap">
									<div class="event-deails cf">
									<c:if test = "${not empty eventVO.images}">
									  <c:forEach items="${eventVO.images}" var="imageVO">
										<div class="event-img col-md-4 remove-pad">
											<img src="${imageVO.genericContentImage}" class="text-left" alt="uaq">
											<c:set var="teaserImage" value="${imageVO.teaserImage}"></c:set>
										</div>
										  </c:forEach>
													</c:if>
										<div class="event-content col-md-8">
											<h3 class="red-txt"><c:out value="${eventVO.title}"></c:out></h3>
											<p><c:out value="${eventVO.body}" escapeXml="false"></c:out>
											</p>
										
											<ol class="events-contact-wrap">
												<li class="events-contact-addr col-md-7">
													<span><c:out value="${eventVO.addressLine1}"></c:out>. </span>
												</li>
												<li class="col-md-5">
													<span class="events-contact-date"><c:out value="${eventVO.openingDate}"></c:out> - <c:out value="${eventVO.endingDate}"></c:out></span>
												</li>
											</ol>
										</div>
									</div>
									
									<div id="event_map" style="width: 100%; height: 400px;">
										${eventVO.latitude}, ${eventVO.longitude}
									</div>
									<div class="page-nav cf">
												<a href="#"><spring:message code="page.back"></spring:message></a>
									</div>
		
								</div>
								<!-- /event-details -->
							</div>
							<!-- /page content -->
						</div>
						<!-- /left col -->
					</div>
				</div>
                        <!-- /page content here -->
                <!-- container fluid ends here -->

<script src="http://maps.google.com/maps/api/js?sensor=false" type="text/javascript"></script>
<script>
	var myLatlng = new google.maps.LatLng("${eventVO.latitude}","${eventVO.longitude}");
	var mapOptions = {
		zoom: 10,
		center: myLatlng
	}
	var map = new google.maps.Map(document.getElementById('event_map'), mapOptions);

	var marker = new google.maps.Marker({
		  position: myLatlng,
		  map: map,
		  title: 'UMM AL QUWAIN'
	});
 </script>
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
		var logo = '${teaserImage}';
		var sub_value = '${eventVO.title}';
		var email_share = 'mailto:?subject='+sub_value+'&body='+site_Url;
	</script>