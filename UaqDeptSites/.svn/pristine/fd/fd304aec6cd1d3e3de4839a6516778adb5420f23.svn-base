<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!--mainmenu -->
<div class="mainmenu">
	<div class="col-md-12 hidden-xs hidden-sm remove-pad">
		<div class="mainmenu-wrap">
			<ul class="no-list cf">
				<li><a href="/${param.languageCode}/home.html"><img src="/img/home1.png" alt="Home UAQ">
				</a></li>
					<li class="active"><a href="${currentPage.url}">${currentPage.title}</a></li>
				<c:forEach items="${currentPage.navigations}" var="item">
					<li><a href="/${param.languageCode}/${item.url}">${item.title}</a></li>
				</c:forEach>
			</ul>
		</div>
	</div>
</div>
<!--/mainmenu -->

<!--Mobile Breadcrumb --> 
<div class="col-md-12 mobile-landing-headding header-item-${currentPage.title} hidden-md">${currentPage.title}</div>
 <!--/Mobile Breadcrumb -->
 
<!-- content area -->
<div class="content">
	<div class="row">
		<!-- single col -->
		<div class="col-xs-12 col-sm-12 col-md-12">
			<!-- inner banner -->
			<div class="row">
				<div class="col-md-12 remove-pad">
					<!-- inner banner wrap -->
					<div class="inner-banner-wrap">
						<!-- inner banner img -->
						<div class="banner-img" data-bgimage="${currentPage.navigations[0].bannerImage}"></div>
						<!-- /inner banner img -->
						<!-- inner banner content -->
						<div class="inner-banner-content cf">
							<div class="col-xs-10 col-sm-10 col-md-10">
								<h3>${currentPage.navigations[0].title}</h3>
								<p>${currentPage.navigations[0].bannerText}</p>
							</div>
							<div class="col-xs-2 col-sm-2 col-md-2 text-right">
								<a
									href="/${param.languageCode}/${currentPage.navigations[0].url}"
									class="readmore"> <spring:message code="landing.readmore" />
								</a>
							</div>
						</div>

						<!-- /inner banner content -->
					</div>
					<!-- /inner banner wrap -->
				</div>
			</div>
			<!-- /inner banner -->
			<!-- landing listing -->
			<div class="row">
				<div class="col-md-12 remove-pad">
					<!-- landing listing wrap -->
					<div class="special-landing-wrap">
						<c:forEach items="${currentPage.navigations}" var="item"
							varStatus="loop" begin="1">
							<!-- box content -->
							<div class="col-sm-4 col-md-3 col-xs-12 remove-pad">
								<div class="items">
									<a href="/${param.languageCode}/${item.url}"> <img
										src="${item.image}" alt="" class="thumbnail" />
										<h3>${item.title}</h3>
										<p>
											${item.teaserText}
										</p>
										<span class="pull-right"> </span>
									</a>
								</div>
							</div>
							<!-- /box content -->
						</c:forEach>

					</div>
					<!-- /landing listing wrap -->
				</div>
			</div>
			<!-- /landing listing -->
		</div>
		<!-- single col -->
	</div>
</div>
<!-- /content area -->
