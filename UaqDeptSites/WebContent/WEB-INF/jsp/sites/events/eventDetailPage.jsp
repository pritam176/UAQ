<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<div class="page-content-wrapper">
            <div class="row">
                <div class="col-lg-9 col-sm-9 col-md-9 col-xs-12">
			<!-- BreadCrumbs Start -->
				<div class="breadcrumbs hide-on-mobile">
				<spring:message code="label.pageLocation" />
				&nbsp; <a href="/${param.languageCode}/home.html"><spring:message code="header.home" /></a> 
				<c:forEach items="${navigations[param.languageCode]}" var="navigation">
				<c:if test="${(null!=parentLandingPage && navigation.assetId ==parentLandingPage.assetId)}">
				<span><i class="glyphicon glyphicon-menu-right"></i></span> <a href="/${param.languageCode}/${navigation.navigations[0].url}"><c:out value="${navigation.title}" /></a>
				</c:if>
				</c:forEach>
				<span><i class="glyphicon glyphicon-menu-right"></i></span> <spring:message code="label.events" />
						
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
                        <div class="event-detail-map-wrapper">
                            <div class="row">
                                <div class="col-lg-6 col-sm-6 col-xs-12">
                                    <div class="row">
                                        <div class="col-lg-4 col-sm-5 col-xs-5 events-title-detail">
                                            <spring:message code="label.name" />: 
                                        </div>
                                        <div class="col-lg-8 col-sm-7 col-xs-7 events-description-detail">
                                            ${eventVO.eventName}
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-lg-4 col-sm-5 col-xs-5 events-title-detail">
                                            <spring:message code="label.address" />: 
                                        </div>
                                        <div class="col-lg-8 col-sm-7 col-xs-7 events-description-detail">
                                            ${eventVO.addressLine1} <br>
                                            ${eventVO.addressLine2}<br>
                                            ${eventVO.addressLine3}
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-lg-4 col-sm-5 col-xs-5 events-title-detail">
                                            <spring:message code="label.openingdate" />:
                                        </div>
                                        <div class="col-lg-8 col-sm-7 col-xs-7 events-description-detail">
                                            <strong>${eventVO.openingDate}</strong>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-lg-4 col-sm-5 col-xs-5 events-title-detail">
                                           <spring:message code="label.enddate" />:
                                        </div>
                                        <div class="col-lg-8 col-sm-7 col-xs-7 events-description-detail">
                                            <strong>${eventVO.endingDate}</strong>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-lg-4 col-sm-5 col-xs-5 events-title-detail">
                                           <spring:message code="label.website" />:
                                        </div>
                                        <div class="col-lg-8 col-sm-7 col-xs-7 events-description-detail">
                                            <strong><a href="${eventVO.website}" target="_blank">${eventVO.website}</a></strong>
                                        </div>
                                    </div>
                                </div>
                                
                                <div class="col-lg-5 col-sm-5 col-lg-offset-1 col-sm-offset-1">
                                    <div id="event_map" style="width: 100%; height: 200px;">
										${eventVO.latitude}, ${eventVO.longitude}
									</div>
                                </div>
                            </div>
                        </div>
                        <h2 class="sub-title"><spring:message code="label.eventdetails" /></h2>
                        <h3 class="events-list-title">${eventVO.title}</h3>
                        <p>
						<c:if test="${not empty eventVO.images}">
							<c:forEach items="${eventVO.images}" var="imageVO">
	                        <img src="${imageVO.genericContentImage}" alt="events details" class="pull-left event-details-img">
	                        <c:set var="teaserImage" value="${imageVO.teaserImage}"/>
							 </c:forEach>
						</c:if>
                        ${eventVO.body}
                    </div>
                    <!-- Page Content End -->
                    <!-- Back Button Start -->
                    <div class="back-btn">
                        <a href="javascipt:void(0)"><spring:message code="page.back" /></a>
                    </div>
                    <!-- Back Button End -->
                    <!-- Back to Top Button Start -->
                    <div class="back-to-top pull-right">
                        <a href="#"><spring:message code="back.to.top" />  <i class="glyphicon glyphicon-triangle-top"></i></a>
                    </div>
                    <!-- Back to Top Button End -->
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
							<c:set var="subjectValue" value="${eventVO.title}"/>
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
				<c:if test="${null!=parentLandingPage}">
				<h2>
					<c:out value="${parentLandingPage.title}"/>
				</h2>
				</c:if>
				<ul class="side-nav">
					 <c:forEach items="${parentLandingPage.navigations}" var="item">
				 <c:choose>
						<c:when test="${activeNav==item.url}">
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

<script type = "text/javascript">
	var title_txt = '${titleText}';
	var caption_txt = '${captionText}';
	var descr_txt = '${descrText}';
	var logo = '${teaserImage}';
	var sub_value = '${eventVO.title}';
	var email_share = 'mailto:?subject='+sub_value+'&body='+site_Url;
</script>