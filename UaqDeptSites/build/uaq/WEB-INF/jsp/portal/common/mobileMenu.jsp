<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<div id="mobileMenu" style="display:none;">
	<div class="mobileMenu visible-xs">
		<div class="mobileNav">
			<ul class="no-list menuItems">
				<li>
					<p>
						<a href="/${param.languageCode}/home.html"><spring:message code="header.home"></spring:message></a>
					</p>
				</li>

				<c:forEach items="${navigationList[param.languageCode]}" var="navigation">
					<li class="navItem">
						<p>
							<a href="/${param.languageCode}/${navigation.url}"><c:out value="${navigation.title}" /></a>
							<a href="javascript:void(0);" class="mobile-nav-arrow"></a>
						</p>
						<ul class="no-list sub-menu">
							<c:forEach items="${navigation.navigations}" var="secondNav">
								<li><a href="/${param.languageCode}/${secondNav.url}"><c:out value="${secondNav.title}" /></a></li>
							</c:forEach>
						</ul>
					</li>

				</c:forEach>
			</ul>
		</div>
	</div>
</div>