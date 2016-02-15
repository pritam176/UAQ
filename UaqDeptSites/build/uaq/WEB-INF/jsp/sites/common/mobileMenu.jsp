<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- Mobile Menu Start -->
<div class="mobile-menu">
	<ul>
		<li><a href="/${param.languageCode}/home.html"><spring:message code="header.home" /></a></li>

		<c:forEach items="${navigations[param.languageCode]}" var="navigation">
			<c:choose>
				<c:when test="${ navigation.displayTypeHome == 'Tree'}">
					<li class="has-submenu"><a href="/${param.languageCode}/${navigation.navigations[0].url}"><c:out value="${navigation.title}" /></a><a class="dropdown-nav pull-right"></a>
						<ul class="mobile-sub-nav">
							<c:forEach items="${navigation.navigations}" var="item" varStatus="loop" begin="1">
								<li><a href="/${param.languageCode}/${item.url}"><i class="glyphicon glyphicon-menu-right"></i>${item.title}</a></li>
							</c:forEach>
						</ul></li>
				</c:when>

				<c:when test="${ navigation.displayTypeHome == 'MediaTree'}">
					<li class="has-submenu"><a href="/${param.languageCode}/${navigation.navigations[0].url}"><c:out value="${navigation.title}" /></a><a class="dropdown-nav pull-right"></a>
						<ul class="mobile-sub-nav">
							<c:forEach items="${navigation.navigations}" var="item" varStatus="loop" begin="1">
								<li><a href="/${param.languageCode}/${item.url}"><i class="glyphicon glyphicon-menu-right"></i>${item.title}</a></li>
							</c:forEach>
						</ul></li>
				</c:when>
				<c:when test="${navigation.displayTypeHome == 'Images'}">
					<li><a href="/${param.languageCode}/media-center/image-gallery.html"> <c:out value="${navigation.title}" /></a></li>
				</c:when>
				<c:when test="${navigation.displayTypeHome == 'Image'}">
					<li><a href="/${param.languageCode}/media-center/image-gallery.html"> <c:out value="${navigation.title}" /></a></li>
				</c:when>
				<c:when test="${navigation.displayTypeHome == 'Videos'}">
					<li><a href="/${param.languageCode}/${navigation.url}"> <c:out value="${navigation.title}" /></a></li>
				</c:when>

				<c:otherwise>
					<li><a href="/${param.languageCode}/${navigation.url}"> ${navigation.title} </a></li>
				</c:otherwise>
			</c:choose>
		</c:forEach>
	</ul>
</div>
<!-- Mobile Menu End -->