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
					<li class="in-active"><spring:message code='footer.follow.rssfeed'/></li>
				</c:if>
			</ul>
		</div>
	</div>
</div>
<!--/mainmenu -->

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
											<li class="share-email">
												<a href="mailto:?&amp;Subject=UAQ%20Share&amp;Body=I%20saw%20this%20and%20thought%20of%20you!%20">
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
							<!-- /callout -->
						</div>
						<!-- right col -->
						<!-- left col -->
						<div class="col-xs-12 col-sm-9 col-md-9 main-left-col">
							<!-- page title -->
							<div class="page-title-wrap">
								<div class="row">
									<div class="col-xs-12 col-sm-9 col-md-10 title-txt">
										<!-- page title -->
										<h2 class="page-title">
											<spring:message code='footer.follow.rssfeed'/>
										</h2>
										<!-- /page title -->
									</div>
									<div class="hidden-xs show-sm col-md-2 title-icon">
										<img src="/img/icons/icon-rss.png" alt="uaq">
									</div>
								</div>
							</div>
							<!-- /page title -->
							<!-- page content -->
							<div class="main-content-wrap">
										<!-- accordion listing -->

									<!-- callout -->
							<div class="callout-wrap">
								<div class="callout-head">
									<div class="right-nav-title feed-title">
										<h4><spring:message code='rss.feeds.choice'/></h4>
										<p><spring:message code='rss.feeds.title.msg'/></p>
									</div>
								</div>
								<div class="callout-content  ">
									<div class="right-nav">
			                            <ul class="rssfeedlisting">
			                                <li>
			                                	<a href="/${param.languageCode}/news/rssFeeds.html"><spring:message code='rss.feeds.news'/>
													<span class="icon-rss pull-right"><img src="/img/icons/icon-rss.png" alt="uaq rss feed"></span>
												</a>
			                                </li>
			                                <li>
			                                	<a href="/${param.languageCode}/news/rssFeeds.html"><spring:message code='rss.feeds.events'/>
													<span class="icon-rss pull-right"><img src="/img/icons/icon-rss.png" alt="uaq rss feed"></span>
												</a>
			                                </li>
											
			                            </ul>
			                        </div>
								</div>	
							</div>
							<!-- /callout -->
                         
							</div>
							<!-- /page content -->
						</div>
						<!-- /left col -->
					</div>
				</div>
				<!-- /content area -->
