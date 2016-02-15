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
					<li class="active"><a href="/${param.languageCode}/${parentLandingPage.url}">${parentLandingPage.title}</a></li>

					<c:forEach items="${parentLandingPage.navigations}" var="item">
						<c:choose>
							<c:when test="${fn:containsIgnoreCase(item.url, 'department')}">
								<li class="active-sub"><a href="/${param.languageCode}/${item.url}">${item.title}</a></li>
							</c:when>
							<c:otherwise>
								<li><a href="/${param.languageCode}/${item.url}">${item.title}</a></li>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</c:if>
				
				<c:if test="${empty parentLandingPage}">	
					<li class="in-active"><spring:message code="label.department.capital"/></li>
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
				<!-- DepartmentLists -->
				<div class="callout-wrap">
					<div class="callout-content">
						<div class="department-lists">
							<div class="callout-head">
								<h5 class="right-nav-title right-nav-gray-title">
									<spring:message code="label.departments" />
								</h5>
							</div>
							<div class="custom-select-box">
								<form:form method="POST" id="deptForm" name="deptForm"
									commandName="searchCommand">
									<form:select path="category" name="categories" id="categories"
										cssClass="_select" onchange="submitForm(this);">
										<c:forEach items="${departmentMap}" var="department">
									        <option value="${department.key}" ${department.key == departmentVO.name ? 'selected' : ''}>${department.value}</option>
									    </c:forEach>										
									</form:select>
								</form:form>
							</div>
						</div>
					</div>
				</div>
				<!-- /DepartmentLists -->

				<!-- callout -->
				<c:if test="${not empty departmentVO.services }">
					<div class="callout-wrap">
						<div class="callout-head">
							<h5 class="right-nav-title">
								<spring:message code="label.department.services" />
							</h5>
						</div>
						<div class="callout-content">
							<div class="right-nav">
								<ul>
									<c:forEach items="${departmentVO.services}" var="serviceVO">
										<li><a
											href="/${param.languageCode}/services/${serviceVO.site}/${serviceVO.url}.html"><c:out
													value="${serviceVO.title}" /></a></li>
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
								<spring:message code="label.departments" />
							</h4>
							<!-- /sub page title -->
							<!-- page title -->
							<h2 class="page-title">
								${param.languageCode == 'en' ? departmentVO.departmentNameEN : departmentVO.departmentNameAR}
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
					<!-- listing-details -->
					<div class="listing-details-wrap">
						<div class="page-content-wrap">
							<div class="content-wrap">
								<!-- -->
								<div class="deparment-deails depDetialHolder cf">

									<!-- <div class="depInfo cf"> -->
									<c:if test="${not empty departmentVO.images}">
										<c:forEach items="${departmentVO.images}" var="imageVO">
											<div class="col-xs-12 col-sm-4 imgHolder">
												<img src="${imageVO.genericContentImage}" alt="UAQ">
											</div>
										</c:forEach>
									</c:if>
									<div class="col-xs-12 col-sm-8 itemInfo">

										<span class="redtxt">
											<h3 class="title">${param.languageCode == 'en' ?
												departmentVO.departmentNameEN :
												departmentVO.departmentNameAR}</h3>
										</span>

										<div class="col-sm-6 workInfo">
											<div class="whour">
												<h4 class="redtxt">
													<spring:message code="label.department.working.hours" />
													:
												</h4>
												<p>${departmentVO.timings}</p>
											</div>
											<div class="rhour">
												<h4 class="redtxt">
													<spring:message code="label.department.ramadan.hours" />
													:
												</h4>
												<p>${departmentVO.ramadanTimings}</p>
											</div>
											<ul>
												<c:if test="${not empty departmentVO.website}">
												<li><img src="/img/Small04.png" alt="uaq"> <a
													href="http://${departmentVO.website}" target="_blank">${departmentVO.website}</a></li></c:if>
											</ul>
											
										</div>
										<div class="col-sm-6 contactInfo">
											<ul>
												<li> <h4 class="redtxt">
													<spring:message code='header.contact.us'/>:
												</h4></li>
											<c:if test="${not empty departmentVO.telephoneNumber}">
												<li><img src="/img/Small01.png" alt="uaq"> <a
													href="tel:${departmentVO.telephoneNumber}">${departmentVO.telephoneNumber}</a></li></c:if>
											<c:if test="${not empty departmentVO.fax}">		
												<li><img src="/img/Small02.png" alt="uaq"> <a
													href="#">${departmentVO.fax}</a></li></c:if>
											<c:if test="${not empty departmentVO.emailID}">
												<li><img src="/img/Small03.png" alt="uaq"> <a
													href="mailto:${departmentVO.emailID}?Subject=UAQ%20email" target="_blank">${departmentVO.emailID}</a></li></c:if>
											<c:if test="${not empty departmentVO.facebookContact}">
												<li><img src="/img/icons/icon-fb-gray.png" alt="uaq"> <a
													href="http://${departmentVO.facebookContact}" target="_blank">${departmentVO.facebookContact}</a></li></c:if>
											<c:if test="${not empty departmentVO.twitterContact}">
												<li><img src="/img/icons/icon-twitter-gray.png" alt="uaq"> <a
													href="http://${departmentVO.twitterContact}" target="_blank">${departmentVO.twitterContact}</a></li></c:if>
											</ul>
										</div>
									</div>

									<div id="depMap" style="width: 100%; height: 400px; top:30px; bottom:30px">

									</div>
									
								</div>
							</div>
							<div class="page-nav cf"  style="margin-top:50px;">
								<a href="#"><spring:message code="page.back"></spring:message></a>
							</div>
						</div>
						<!-- /listing-details -->
					</div>
					<!-- /page content -->
				</div>
				<!-- /left col -->
			</div>
		</div>
		<!-- /content area -->
		<!-- footer -->

		<!-- /footer -->
	</div>

<script src="http://maps.google.com/maps/api/js?sensor=false" type="text/javascript"></script>

<script type="text/javascript">
		
				function submitForm(obj){					
					document.deptForm.action = obj.options[obj.selectedIndex].value + ".html";
					document.deptForm.submit();
				}
		</script>
		
<c:choose>
	<c:when test="${param.languageCode=='en'}">
		<c:set var="deptname" value="${departmentVO.departmentNameEN}" />
	</c:when>

	<c:otherwise>
		<c:set var="deptname" value="${departmentVO.departmentNameAR}" />
	</c:otherwise>
</c:choose>

<script>
	<c:set var="latvalue" value="${departmentVO.latitude}"/>
	<c:set var="longvalue" value="${departmentVO.longitude}"/>
	
	var myLatlng = new google.maps.LatLng(<c:out value="${latvalue}"/>,<c:out value="${longvalue}"/>);
	var mapOptions = {
	  zoom: 10,
	  center: myLatlng
	}
	var map = new google.maps.Map(document.getElementById('depMap'), mapOptions);
	
	var marker = new google.maps.Marker({
	    position: myLatlng,
	    map: map,
	    title: '${deptname}'
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
		var sub_value = '${deptname}';
		var email_share = 'mailto:?subject='+sub_value+'&body='+site_Url;
	</script>