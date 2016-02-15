<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- Home Page Wrapper Start -->
<div class="page-content-wrapper">
	<div class="row">
		<div class="col-lg-12 col-sm-12 col-md-12 col-xs-12">
			<!-- BreadCrumbs Start -->
			<div class="breadcrumbs hide-on-mobile">
				<spring:message code="label.pageLocation" />
				<a href="/${param.languageCode}/home.html"><spring:message
						code="header.home" /></a> <span><i
					class="glyphicon glyphicon-menu-right"></i></span> ${genericPageVO.title}
			</div>
			<!-- BreadCrumbs End -->

			<c:set var="showDepartmentContact" value="false"></c:set>
			<c:choose>
				<c:when test="${not empty genericPageVO.bodyIDViewObjectMap}">
					<c:forEach items="${genericPageVO.bodyIDViewObjectMap}"
						var="bodyListItems">
						<c:if test="${bodyListItems.key=='Department'}">
							<c:set var="showDepartmentContact" value="true"></c:set>
						</c:if>
					</c:forEach>
				</c:when>
			</c:choose>

			<c:choose>
				<c:when test="${not empty genericPageVO.bodyIDViewObjectMap}">
					<!-- Page Content Start -->
					<div class="content-wrapper">
					<c:set var="subjectValue" value="${pageMetadata.pageTitle}"/>
					<div class="page-title-mob page-title-border">
					<h2 class="main-title title_mobile-share-wrap">${genericPageVO.title}</h2>
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
						<c:if test="${not empty genericPageVO.bodyIDViewObjectMap}">
							<c:forEach items="${genericPageVO.bodyIDViewObjectMap}"
								var="bodyListItems">
								<c:forEach var="deptitems" items="${bodyListItems.value}">
									<div class="row contact-us-page">
										<c:set var="dept" value="${deptitems.value}" />
										<div class="col-lg-7 col-sm-7 col-xs-12">
											<div class="map-holder" id="contact_map"></div>
										</div>
										<c:choose>
											<c:when test="${param.languageCode=='en'}">
												<c:set var="deptName" value='${dept.departmentNameEN}' />
											</c:when>
											<c:otherwise>
												<c:set var="deptName" value='${dept.departmentNameAR}' />
											</c:otherwise>
										</c:choose>

										<div class="col-lg-5 col-sm-5 col-xs-12 contact-right-box">
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
											<h3>
												${deptName}<br /> ${dept.addressLine1}<br />
												${dept.addressLine2}<br />
											</h3>
											<p>${dept.addressLine3}</p>
											<p class="hide-small">
												<spring:message code="label.tel"></spring:message>
												: <label class="ar-dial">${dept.telephoneNumber}</label>										
											</p>
											<p>
												<spring:message code="label.fax"></spring:message>
												: <label class="ar-dial">${dept.fax}</label>
											</p>
											
											<c:if test="${not empty dept.emailID}">
												<div class="mail-address">
													<a href="mailto:${dept.emailID}">${dept.emailID}</a>
												</div>
											</c:if>
											
											<c:if test="${not empty dept.facebookContact || not empty dept.twitterContact || 
														not empty dept.instagramContact || not empty dept.youtubeContact}">
											<div class="followus-wrapper">
												<div class="social-icons">
													<p class="pull-left">
														<spring:message code="follow.us"></spring:message>
													</p>
													<ul class="pull-left">
														<c:if test="${not empty dept.facebookContact}">
														<li class="facebook"><a href="${dept.facebookContact}/" target="_blank"><span><spring:message
																		code="label.facebook"></spring:message></span></a></li>
																		</c:if>
																		
														<c:if test="${not empty dept.twitterContact}">																		
														<li class="twitter"><a href="${dept.twitterContact}/" target="_blank"><span><spring:message
																		code="label.twitter"></spring:message></span></a></li>
																		</c:if>
																		
														<c:if test="${not empty dept.instagramContact}">
														<li class="instagram"><a href="${dept.instagramContact}/" target="_blank"><span><spring:message
																		code="label.instagram"></spring:message></span></a></li>
																	 </c:if>
																		
														<c:if test="${not empty dept.youtubeContact}">
														<li class="youtube"><a href="${dept.youtubeContact}/" target="_blank"><span><spring:message
																		code="label.youtube"></spring:message></span></a></li>
																	 </c:if>
														
												</div>
											</div>
											</c:if>
										</div>
									</div>
								</c:forEach>
							</c:forEach>
						</c:if>
					</div>
					<!-- Page Content End -->
				</c:when>

				<c:otherwise>
					<!-- Page Content Start -->
					<div class="content-wrapper">
					<div class="page-title-mob page-title-border">
					<h2 class="main-title title_mobile-share-wrap">${genericPageVO.title}</h2>
					<div class="utility-icons show-on-mobile mobile-share-wrap" >
					<ul class="utility-wrap" style="float: right;">
					<li>
						<!-- Share Icon --> <a href="javascript:void(0);" class="share-icon share-all" id=""></a>

						<div class="submenu" style="display: none;">
							<ul class="root">
								<li class="share-fb"><a href="javascript:void(0);" target="_blank" alt=""> <img src="/img/icon-fb.png" alt="uaq"></a></li>
								<li><a href="http://twitter.com/share?text=text" target="_blank"><img src="/img/icon-twitter.png"></a></li>
								<li><a href="mailto:?subject=${subjectValue}&amp;Body=${sourceURL}"><img src="/img/icon-email.png"></a></li>
							</ul>
						</div>
					</li>
					
				</ul>
			</div>
			</div>
						<div class="row special-utility-wrap">
							<div class="utility-icons special-utility">
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
							
						</div>
							<div class="col-lg-12 col-sm-12 col-xs-12 ">
								<p>${genericPageVO.body}</p>
							</div>
						</div>
					</div>
					<!-- Page Content End -->
					<!-- Back Button Start -->
			<div class="back-btn">
				<a href="javascipt:void(0)"><spring:message code="page.back"></spring:message></a>
			</div>
			<!-- Back Button End -->
			<!-- Back to Top Button Start -->
			<div class="back-to-top pull-right">
				<a href="#"><spring:message code="back.to.top"></spring:message><i
					class="glyphicon glyphicon-triangle-top"></i></a>
			</div>
			<!-- Back to Top Button End -->

				</c:otherwise>
			</c:choose>

			
		</div>
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


<script src="http://maps.google.com/maps/api/js?sensor=false" type="text/javascript"></script>
<script>
	var myLatlng = new google.maps.LatLng("${dept.latitude}","${dept.longitude}");
	var mapOptions = {
		zoom : 10,
		center : myLatlng
	}
	var map = new google.maps.Map(document.getElementById('contact_map'),
			mapOptions);

	var marker = new google.maps.Marker({
		position : myLatlng,
		map : map,
		title : "${deptname}"
	});
</script>
<!-- Home Page Wrapper End -->
