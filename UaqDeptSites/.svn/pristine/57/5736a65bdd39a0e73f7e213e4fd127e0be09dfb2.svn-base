<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<!-- Internal Page Nav Start -->
<nav class="main-nav hide-on-mobile">
	<ul>

		<c:forEach items="${navigations[param.languageCode]}" var="navigation">

			<c:choose>
				<c:when
					test="${(null!=parentLandingPage && navigation.assetId ==parentLandingPage.assetId) ||(navigation.url==source) }">
					<li class="active"><c:choose>
							<c:when test="${not empty navigation.navigations}">
								<div class="item-container">

									<a
										href="/${param.languageCode}/${navigation.navigations[0].url}"><img
										src="${navigation.image}" alt="${title} " /></a>
									</div>
								<a
									href="/${param.languageCode}/${navigation.navigations[0].url}"><span
									class="title"><c:out value="${navigation.title}" /></span></a>

							</c:when>

							<c:otherwise>
								<div class="item-container">

									<a href="/${param.languageCode}/${navigation.url}"><img
										src="${navigation.image}" alt="${title} " /></a>
									</div>
								<a href="/${param.languageCode}/${navigation.url}"><span
									class="title"><c:out value="${navigation.title}" /></span></a>
							</c:otherwise>
						</c:choose></li>
				</c:when>
				<c:otherwise>
					<li><c:choose>
							<c:when test="${not empty navigation.navigations}">
								<div class="item-container">
									<a
										href="/${param.languageCode}/${navigation.navigations[0].url}"><img
										src="${navigation.image}" alt="${navigation.title}" /></a>
									</div>
								<a
									href="/${param.languageCode}/${navigation.navigations[0].url}"><span
									class="title"><c:out value="${navigation.title}" /></span></a>

							</c:when>
							<c:otherwise>
								<div class="item-container">
									<a href="/${param.languageCode}/${navigation.url}"><img
										src="${navigation.image}" alt="${navigation.title}" /></a>
									
								</div>
								<a href="/${param.languageCode}/${navigation.url}"><span
									class="title"><c:out value="${navigation.title}" /></span></a>
							</c:otherwise>
						</c:choose></li>
				</c:otherwise>
			</c:choose>
		</c:forEach>
	</ul>
</nav>
<!-- Internal Page Nav End -->

