	<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
	<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
	
	<section class="top-nav-bar-wrapper">
		<div class="row">
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 pull-right">
				<div class="search-wrapper pull-right">
					<a href="#" class="search-icon"><i
						class="glyphicon glyphicon-search"></i></a>
					<div class="search-form hidden">
						<form:form commandName="searchCommand" action="/${param.languageCode}/search.html" method="post" >
							<div class="form-group">
								<c:set var="searchPlaceholder"><spring:message code='label.search.here'/></c:set>
								<form:input path="keyword" id="searchItemInput" placeholder="${searchPlaceholder}"
									class="form-control pull-left"/> <a href="#"
									class="close-search pull-left"><i
									class="glyphicon glyphicon-remove"></i></a>
							</div>
						</form:form>
					</div>
				</div>
				<ul class="top-nav-bar pull-right">
					<li class=""><a href="/${param.languageCode}/home.html"><spring:message code="header.home"/></a></li>
					<li><a href="/${param.languageCode}/content/ContactUs.html"><spring:message code="header.contact.us"/></a></li>
					<li><a href="/${param.languageCode}/content/SiteMap.html"><spring:message code="header.sitemap"/></a></li>
					<%-- <li><a href="/${param.languageCode}/content/help.html"><spring:message code="header.help"/></a></li> --%>
					<c:if test="${param.languageCode=='ar'}">
					<li class="language-change"><a href="/${param.languageCode}/changeLanguage.html?fromPage=${source}"><spring:message code="change.english" /></a></li>
					</c:if>
					<c:if test="${param.languageCode=='en'}">
						<li class="language-change"><a href="/${param.languageCode}/changeLanguage.html?fromPage=${source}"><spring:message code="change.arabic" /></a></li>
					</c:if>
					</ul>
			</div>
		</div>
	</section>

	<section class="logo-wrapper mobile-logo-wrap">
		<div class="row mobile-logo-bdy">
			<div class="col-lg-4 col-sm-4 col-xs-4 mobile-logo-space">
				<div class="logo-government text-left">
					<img src="/img/logo.png" alt="Government of UMM Alquwain">
				</div>
			</div>
			<div class="col-lg-4 col-sm-4 col-xs-4 mobile-logo-space-center">
				<div class="logo-state text-center">
					<c:if test="${site == 'dec'}">
						<img src="/img/Executive_Council_Logo.png" alt="Executive Council">
					</c:if>
					<c:if test="${site != 'dec'}">
						<img src="/img/logo-state.png" alt="UMM Alquwain">
					</c:if>
					
				</div>
			</div>
			<div class="col-lg-4 col-sm-4 col-xs-4 mobile-logo-space">
				<div class="logo-department text-right">
					<a href="/${param.languageCode}/home.html"> <img src="/img/logo/${site}.png"
						alt="Department of Economic Development">
					</a>
				</div>
			</div>
		</div>
		
		<%-- <div class="row show-on-mobile">
			<div class="col-sm-12 col-xs-12">
				<div class="mobile-logo text-center">
					<a href="/${param.languageCode}/home.html"> <img src="/img/mobile-logo.png"
						alt="Government of UMM Alquwain">
					</a>
				</div>
			</div>
		</div> --%>
	</section>
	<div class="show-on-mobile mobile-nav">
		<a href="javascript:void(0);"><i class="glyphicon glyphicon-menu-hamburger"></i></a>
	</div>
	
