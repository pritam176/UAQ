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
				<li><a href="/${param.languageCode}/home.html"><img src="/img/home1.png" alt="Home UAQ"> </a></li>
				<c:if test="${not empty parentLandingPage.url and not empty parentLandingPage.title and not empty parentLandingPage.navigations}">
					<li class="active"><a href="/${param.languageCode}/${parentLandingPage.url}">${parentLandingPage.title}</a></li>

					<c:forEach items="${parentLandingPage.navigations}" var="item">
						<c:choose>
							<c:when test="${source==item.url}">
								<li class="active-sub"><a href="/${param.languageCode}/${item.url}">${item.title}</a></li>
							</c:when>
							<c:otherwise>
								<li><a href="/${param.languageCode}/${item.url}">${item.title}</a></li>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</c:if>

				<c:if test="${empty parentLandingPage}">
					<li class="in-active"><spring:message code="label.heading.news" /></li>
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
							<c:set var="subjectValue"><spring:message code='label.departments'/></c:set>
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
			<!-- callout -->
			<div class="callout-wrap">
				<div class="callout-head">
					<h5 class="right-nav-title">
						<spring:message code="map.view.department.locations" />
					</h5>
				</div>
				<div class="callout-content">
					<div class="right-nav">
						<div class="" id="departmentmaps" style="width: 100%; height: 400px;"></div>
					</div>
				</div>
			</div>
			<!-- /callout -->

			<!-- gallery callout -->

			<!-- /gallery callout -->


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
							<spring:message code="label.ummalquwain" />
						</h4>
						<!-- /sub page title -->
						<!-- page title -->
						<h2 class="page-title">
							<spring:message code="label.departments" />
						</h2>
						<!-- /page title -->
					</div>
					<div class="hidden-xs show-sm col-md-2 title-icon">
						<img src="/img/banner/Departments.png" alt="uaq">
					</div>
				</div>
			</div>
			<!-- /page title -->
			<!-- page content -->
			<div class="main-content-wrap">
				<!-- content filter -->
				<!-- content filter -->
				<div class="content-listing cf">
					<!-- events listing -->
					<c:if test="${not empty departments}">
						<c:forEach items="${departments}" var="department">
							<ul class="listing-wrap department-listing">
								<li class="listing">
									<div class="listing-item Depitem cf">
										<c:if test="${not empty department.images}">
											<c:forEach items="${department.images}" var="imageVO">
												<div class="col-xs-12 col-sm-4 departmentimgholder">
													<!--<img src="img/DLogo.png" alt="UAQ">-->
													<img src="${imageVO.genericContentImage}" alt="">
												</div>
											</c:forEach>
										</c:if>
										<div class="col-xs-12 col-sm-8 ">

											<a href="department/${department.name}.html" class="redtxt">
												<h3 class="title">${param.languageCode == 'en' ? department.departmentNameEN : department.departmentNameAR}</h3>
											</a>
											<ul>
												<c:if test="${not empty department.telephoneNumber }">
													<li><img src="/img/Small01.png" alt="uaq"> <a href="tel:${department.telephoneNumber}">${department.telephoneNumber}</a></li>
												</c:if>
												<c:if test="${not empty department.fax }">
													<li><img src="/img/Small02.png" alt="uaq"> <a href="#">${department.fax}</a></li>
												</c:if>
												<c:if test="${not empty department.emailID }">
													<li><img src="/img/Small03.png" alt="uaq"> <a href="mailto:${department.emailID}?Subject=UAQ%20email"  target="_blank">${department.emailID}</a></li>
												</c:if>
												<c:if test="${not empty department.website }">
													<li><img src="/img/Small04.png" alt="uaq"> <a href="http://${department.website}"  target="_blank">${department.website}</a></li>
												</c:if>
												<c:if test="${not empty department.facebookContact }">
													<li><img src="/img/icons/icon-fb-gray.png" alt="uaq"> <a href="http://${department.facebookContact}" target="_blank">${department.facebookContact}</a></li>
												</c:if>
												<c:if test="${not empty department.twitterContact}">
													<li><img src="/img/icons/icon-twitter-gray.png" alt="uaq"> <a href="http://${department.twitterContact}"  target="_blank">${department.twitterContact}</a></li>
												</c:if>
											</ul>


										</div>
									</div>
								</li>
							</ul>
						</c:forEach>
					</c:if>
					<!-- events listing -->
					<!-- listing pagination -->
					<!-- <div class = "pagination">
	                                            <ul>
	                                                <li>
	                                                    <a href="#">Previous</a>
	                                                </li>
	                                                <li><a href="#">1</a></li>
	                                                <li><a href="#">2</a></li>
	                                                <li><a href="#">3</a></li>
	                                                <li><a href="#">4</a></li>
	                                                <li><a href="#">Next</a></li>
	                                            </ul>
	                                        </div> -->
					<!-- /listing pagination -->
				</div>
				<!-- back nav -->
				<!-- <div class="page-nav cf">
											<a href="#"><spring:message code="page.back"></spring:message></a>
										</div> -->
				<!-- back nav -->
			</div>
			<!-- /page content -->
		</div>
		<!-- /left col -->
	</div>
</div>
<script src="http://maps.google.com/maps/api/js?sensor=false" type="text/javascript"></script>
<script type="text/javascript">
	// The following example creates complex markers to indicate beaches near
	// Sydney, NSW, Australia. Note that the anchor is set to (0,32) to correspond
	// to the base of the flagpole.

	var sample_1 = ${departmentsJSON};
	var map = new google.maps.Map(document.getElementById('departmentmaps'), {
		zoom : 8,
		center : new google.maps.LatLng(25.124453, 55.379503),
		mapTypeId : google.maps.MapTypeId.ROADMAP
	});

	var infowindow = new google.maps.InfoWindow();

	var marker, i;
	$.each(sample_1, function(key, data) {
		marker = new google.maps.Marker({
			position : new google.maps.LatLng(data.coordinates.lat, data.coordinates.lon),
			map : map
		});
		google.maps.event.addListener(marker, 'click', (function(marker, i) {
			return function() {
				infowindow.setContent(data.title, data.link.caption);
				infowindow.open(map, marker);
			}
		})(marker, i));
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
		var logo = 'www.uaq.ae/img/logo.png';
		var sub_value = '${titleText}';
		var email_share = 'mailto:?subject='+sub_value+'&body='+site_Url;
	</script>
