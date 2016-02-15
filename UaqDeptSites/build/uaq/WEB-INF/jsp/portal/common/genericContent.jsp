<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
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
					<li class="in-active">${genericPageVO.title}</li>
				</c:if>
			</ul>
		</div>
	</div>
</div>
<!--/mainmenu -->

<c:set var="showRelatedAwards" value="false"></c:set>
<c:set var="showImageGallery" value="false"></c:set>
<c:set var="showArticleAsset" value="false"></c:set>
<c:set var="showDepartmentContact" value="false"></c:set>
<!-- Check if association of Contact Us is there or not -->
	
	<c:if test="${not empty genericPageVO.bodyIDViewObjectMap}">
		<c:forEach items="${genericPageVO.bodyIDViewObjectMap}"
			var="bodyListItems">
			<c:if test="${bodyListItems.key=='Department'}">
				<c:set var="showDepartmentContact" value="true"></c:set>
			</c:if>
		</c:forEach>
	</c:if>

<c:if test="${not empty genericPageVO.rhsIDViewObjectMap}">
	<c:forEach items="${genericPageVO.rhsIDViewObjectMap}" var="rhsitmes">
		<c:if test="${rhsitmes.key=='ImageList'}">
			<c:set var="showImageGallery" value="true"></c:set>
		</c:if>
		<c:if test="${rhsitmes.key=='AwardsList'}">
			<c:set var="showRelatedAwards" value="true"></c:set>
		</c:if>
		<c:if test="${rhsitmes.key=='Article'}">
			<c:set var="showArticleAsset" value="true"></c:set>
		</c:if>
	</c:forEach>
</c:if>

<!-- content area -->
<div class="content">
	<div class="row">
		<!-- right col -->
		<div class="col-xs-12 col-sm-3 col-md-3 main-right-col">
			<!-- social media share callout -->
			<div class="callout-wrap">
				<div class="callout-content">
					<div class="social-share-wrap">
						<ul>
							<li><a href="/${param.languageCode}/rssFeeds.html"><img
									src="/img/icons/icon-rss.png" alt="uaq"></a></li>
							<li class="share-fb"><a target="_blank" alt="share it"
								onclick="shareFb(this)" class="share-fb-link"> <img src="/img/icons/icon-fb.png"
									alt="uaq">
							</a></li>
							<li class="share-twitter"><a target="_blank"
								href="http://www.twitter.com/share?url=http://www.google.com/"><img
									src="/img/icons/icon-twitter.png" alt="tweet"></a></li>
							<li class="share-print"><a href="#"><img
									src="/img/icons/icon-print.png" alt="uaq"></a></li>
							<c:set var="subjectValue" value="${genericPageVO.title}"/>
							<li class="share-email"><a href="#"> 
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
			<div class="callout-wrap">
				<%-- <div class="callout-head">
					<h5 class="right-nav-title">
						<spring:message code="label.about.ummalquwain" />
					</h5>
				</div> --%>
				<!-- <div class="callout-content">
					<div class="right-nav">
						<ul>
							<li><a href="#">Facts &amp; Figures</a></li>
							<li class="active"><a href="#">The Union</a></li>
						</ul>
					</div>
				</div> -->
			</div>
			<!-- /callout -->

			<c:if
				test="${showImageGallery=='true' and not empty genericPageVO.rhsIDViewObjectMap}">
				<c:forEach items="${genericPageVO.rhsIDViewObjectMap}" var="rhsList">
					<c:if
						test="${not empty rhsList.value and rhsList.key=='ImageList'}">
						<div class="callout-wrap">
							<div class="callout-head">
								<h5 class="right-nav-title">
									<spring:message code="label.gallery" />
								</h5>
							</div>
							<div class="callout-content">
								<div class="gallery-callout-wrap cf">
									<ul class="_gallery galleryUl col-md-12">
										<c:forEach var="imageVO" items="${rhsList.value}">
											<c:set var="imageAsset" value="${imageVO.value}" />
											<li class="col-xs-4 col-sm-4 col-md-4 remove-pad"><a
												href="${imageAsset.originalImage}"><img
													src="${imageAsset.galleryTeaserImage}" alt=""></a></li>
										</c:forEach>
									</ul>
									<a href="/${param.languageCode}/imageGallery.html"
										class="link-btn"><spring:message code="label.gallery" /></a>
								</div>
							</div>
						</div>
					</c:if>
				</c:forEach>
			</c:if>

			<c:if test="${showArticleAsset=='true' and not empty genericPageVO.rhsIDViewObjectMap}">
				<c:forEach items="${genericPageVO.rhsIDViewObjectMap}" var="rhsList">
					<c:if test="${rhsList.key=='Article' and not empty rhsList.value}">
						<c:forEach items="${rhsList.value}" var="articleVO">
							<c:set var="articleAsset" value="${articleVO.value}" />
							<div class="callout-wrap">
								<div class="support-callout-wrap">
									<img src="/img/icon-support.png" alt="" title="">
									<div class="support-content">
										<h4>${articleAsset.title}</h4>
										<p>${articleAsset.helpText1}</p>
										<p>${articleAsset.helpText2}&nbsp;
										<label class="device-tel">${articleAsset.telephoneNumber}</label>
										<a class="device-tel" href="tel:${articleAsset.telephoneNumber}">${articleAsset.telephoneNumber}</a></p>
										<a href="http://${articleAsset.urlLink}"><p class="red-text">${articleAsset.urlText}</p></a>
									</div>
								</div>
							</div>
						</c:forEach>
					</c:if>
				</c:forEach>
			</c:if>
		</div>
		<!-- right col -->

		<!-- left col -->
		<c:choose>
			<c:when test="${showDepartmentContact=='true'}">
				<div class="col-xs-12 col-sm-9 col-md-9 main-left-col">
					<c:if test="${not empty genericPageVO.bodyIDViewObjectMap}">
						<c:forEach items="${genericPageVO.bodyIDViewObjectMap}"
							var="bodyListItems">
							<c:forEach var="deptitems" items="${bodyListItems.value}">
								<!-- page title -->
								<div class="page-title-wrap">
									<div class="row">
										<div class="col-xs-12 col-sm-9 col-md-10 title-txt">
											<!-- page title -->
											<h2 class="page-title">
												<c:set var="dept" value="${deptitems.value}" />
												${genericPageVO.title}
											</h2>
											<!-- /page title -->
										</div>
										<div class="hidden-xs show-sm col-md-2 title-icon">
											<img src="${genericPageVO.titleIcon}" alt="uaq">
										</div>
									</div>
								</div>
								<!-- /page title -->
								<!-- page content -->
								<div class="main-content-wrap">
									<div class="contactus-wrap cf">
										<!-- contact us   -->
										<div class="col-xs-12 col-sm-7 col-md-5">
											<ul class="contactus-left">
												<li><img src="/img/icon-building-small.png" alt="uaq">
													<span>${dept.addressLine1},${dept.addressLine2},${dept.addressLine3}</span></li>
												<li class="hidden-sm hidden-xs visible-md visible-lg"><img
													src="/img/Small01.png" alt="uaq"> <span>
													<a class="helpTel" href="tel:${dept.telephoneNumber}">${dept.telephoneNumber}</a></span></li>
												<li class="hidden-lg hidden-md visible-sm visible-xs"><img
													src="/img/Small01.png" alt="uaq"> <span>
													<a class="helpTel" href="tel:${dept.telephoneNumber}">${dept.telephoneNumber}</a></span></li>
												<li><img src="/img/Small02.png" alt="uaq"> <span>${dept.fax}</span></li>
												<li><img src="/img/Small03.png" alt="uaq"> <span><a
														href="mailto:${dept.emailID}">${dept.emailID}</a></span></li>
												<li><img src="/img/Small04.png" alt="uaq"> <span><a target="_blank"
														href="http://${dept.website}">${dept.website}</a></span></li>
											</ul>
											<ul class="follow-us list-inline col-xs-12 col-sm-12 ">
												<li><spring:message code="footer.follow.us.on" /></li>
												<li><a href="${dept.facebookContact}"
													target="_blank"><img src="/img/Facebookicon.png"
														alt="fb"></a></li>
												<li><a href="${dept.twitterContact}"
													target="_blank"><img src="/img/follow-twitter.png"
														alt="twitter"></a></li>
												<li><a href="${dept.youtubeContact}"
													target="_blank"><img src="/img/Youtube.png"
														alt="youtube"></a></li>
												<li><a href="${dept.googlePlusContact}"
													target="_blank"><img src="/img/Google.png" alt="Google"></a></li>
												<li><a href="/${param.languageCode}/rssFeeds.html"
													target="_blank"><img src="/img/Rss.png" alt="Rss"></a></li>
											</ul>
										</div>
										<div class="col-xs-12 col-sm-5 col-md-7 remove-pad">
											<div id="event_map" style="width: 100%; height: 400px;">
											</div>
										</div>
									</div>
									<!-- contact us  -->
									<!-- listing pagination -->
								</div>
								<!-- /page content -->
							</c:forEach>
						</c:forEach>
					</c:if>
				</div>
			</c:when>

			<c:otherwise>
				<div class="col-xs-12 col-sm-9 col-md-9 main-left-col">
					<!-- page title -->
					<div class="page-title-wrap">
						<div class="row">
							<div class="col-xs-12 col-sm-9 col-md-10 title-txt">
								<!-- page title -->
								<h2 class="page-title">${genericPageVO.title}</h2>
								<!-- /page title -->
							</div>
							<div class="hidden-xs show-sm col-md-2 title-icon">
								<img src="${genericPageVO.titleIcon}" alt="uaq">
							</div>
						</div>
					</div>
					<!-- /page title -->
					<!-- main content -->
					<div class="main-content-wrap">
						<div class="page-content-wrap">
							<div class="content-wrap">
								<div class="common-content cf">
									<p>${genericPageVO.body}</p>
								</div>
							</div>
						</div>
					</div>
					<!-- /main content -->
				</div>
			</c:otherwise>

		</c:choose>

		<!-- /left col -->

	</div>
</div>
<c:choose>
	<c:when test="${param.languageCode=='en'}">
		<c:set var="deptname" value="${dept.departmentNameEN}" />
	</c:when>

	<c:otherwise>
		<c:set var="deptname" value="${dept.departmentNameAR}" />
	</c:otherwise>
</c:choose>
<script src="https://maps.googleapis.com/maps/api/js"></script>
<script>
	var myLatlng = new google.maps.LatLng("${dept.latitude}",
			"${dept.longitude}");
	var mapOptions = {
		zoom : 10,
		center : myLatlng
	}
	var map = new google.maps.Map(document.getElementById('event_map'),
			mapOptions);

	var marker = new google.maps.Marker({
		position : myLatlng,
		map : map,
		title : '${deptname}'
	});
</script>
<!-- /content area -->
<c:if test="${not empty genericPageVO.title}">
	<c:set var="titleText" value="${genericPageVO.title}"></c:set>
</c:if>

<c:if test="${not empty genericPageVO.teaserText}">
	<c:set var="captionText" value="${genericPageVO.title}"></c:set>
</c:if>

<c:if test="${not empty genericPageVO.teaserText}">
	<c:set var="descrText" value="${genericPageVO.teaserText}"></c:set>
</c:if>

<script type="text/javascript">
	var title_txt = '${titleText}';
	var caption_txt = '${captionText}';
	var descr_txt = '${descrText}';
	var logo = 'www.uaq.ae/img/logo.png';
	var sub_value = "${genericPageVO.title}";
	var email_share = "mailto:?subject="+sub_value+"&body="+site_Url;
</script>