<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

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
					<li class="in-active"><spring:message code="label.heading.news"/></li>
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
								 href="JavaScript:void(0);">
									<img src="/img/icons/icon-fb.png" alt="uaq">
								  </a>
							</li>
							<li class="share-twitter">
								<a  target="_blank" href="JavaScript:void(0);" alt="tweet"></a>
								
							</li>
							<li class="share-print">
								<a href="#"><img src="/img/icons/icon-print.png" alt="uaq"></a>
							</li>
							<c:set var="subjectValue"><spring:message code='footer.news'/></c:set>
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
							<!-- callout -->
							<div class="callout-wrap">
							<c:if test="${not empty newsVO.relatedNews}">
								<div class="callout-head">
									<h5 class="right-nav-title"><spring:message code="label.news.related_news" /></h5>
								</div></c:if>
								<div class="callout-content">
									<div class="right-related-news">
							  <c:if test="${not empty newsVO.relatedNews}">
                            	<ul class="listing-wrap news-listing">
	                            	<c:forEach items="${newsVO.relatedNews}" var="newsItems">
	                            		<c:if test="${not empty newsItems.key}">
	                            			<c:set value="${newsItems.value}" var="news"/>
											<c:forEach items="${news}" var="newsAsset">
												<c:if test="${not empty newsAsset.key}">
													<c:set value="${newsAsset.value}" var="relatedNews"/>
														<li class="listing">
														<a href="/${param.languageCode}/news/${relatedNews.url}.html">
					                                        <div class="listing-item">
					                                         <c:if test = "${not empty relatedNews.images}">
															<c:forEach items="${relatedNews.images}" var="imageVO">
																<div class="listing-img">
				                                                  <img src="${imageVO.teaserImage}" alt="" /> 
				                                                </div>
															</c:forEach></c:if>
				                                                <div class="listing-content">
				                                                    <span class="listing-date">${relatedNews.postedDate}</span>
				                                                    <c:set var="truncatedTeaserTitle" value="${fn:split(relatedNews.teaserTitle,' ')}"/>
				                                                    <p class="red-text">
				                                                  		<c:if test="${fn:length(truncatedTeaserTitle)>=8}">
					                                                    	<c:forEach var="i" begin="0" end="8">
														 						${truncatedTeaserTitle[i]}&nbsp;
																			</c:forEach>...<spring:message code="news.readmore"/>
																		</c:if>
																		<c:if test="${fn:length(truncatedTeaserTitle)>0 and fn:length(truncatedTeaserTitle)<8}">
																			${relatedNews.teaserTitle}
																		</c:if>
					                                                </p>
				                                                </div>
					                                        </div>
					                                        </a>
			                                    		</li>
			                                    		</c:if>
											</c:forEach>
										</c:if>
	                            	</c:forEach>
                            	</ul></c:if>
								</div>	
							</div>
							<!-- /callout -->
						</div>
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
										<spring:message code="label.heading.news" />
										</h2>
										<!-- /page title -->
									</div>
									<div class="hidden-xs show-sm col-md-2 title-icon">
										<img src="/img/banner/News.png" alt="news">
									</div>
								</div>
							</div>
							<!-- /page title -->
							<!-- page content -->
							<div class="main-content-wrap">
								<!-- listing-details -->
								<div class="listing-details-wrap">
									<div class="sub-title">
										<p>${newsVO.postedDate}</p>
										<h3>${newsVO.title}</h3>
									</div>
									<div class="page-content-wrap">
										<div class="content-wrap">
											<div class="common-content cf">
											   <c:if test = "${not empty newsVO.images}">
                                                  <c:forEach items="${newsVO.images}" var="imageVO">
														<img src="${imageVO.genericContentImage}" class="img-left" alt="uaq">
														<c:set var="teaserImage" value="${imageVO.teaserImage}"></c:set>
												  </c:forEach>
                                                </c:if>
												<c:if test="${not empty newsVO.body}">
												<p>${newsVO.body}</p>
												</c:if>
											</div>
											<div class="page-nav cf">
												<a href="#"><spring:message code="page.back" /></a>
											</div>
										</div>
									</div>
								</div>
								<!-- /listing-details -->
							</div>
							<!-- /page content -->
						</div>
						<!-- /left col -->
					</div>
				</div>
	
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
		var logo = '${teaserImage}';
		var sub_value = '${newsVO.title}';
		var email_share = 'mailto:?subject='+sub_value+'&body='+site_Url;
	</script>