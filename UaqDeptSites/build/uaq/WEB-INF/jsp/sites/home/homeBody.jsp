<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<section class="home-menu-wrapper">
	<div class="row">
		<div class="col-lg-12 col-sm-12 col-xs-12 remove-pad-mob">
			<ul class="menu-wrapper-list">
			<c:set var="hoverCSS" value="hover-container"></c:set>
			<c:if test="${site == 'egd'}">
				<c:set var="hoverCSS" value="hover-container hoverGreen"></c:set>
			</c:if>
				<c:forEach items="${navigations[param.languageCode]}" var="navigation">
					<c:choose>
						<c:when test="${navigation.displayTypeHome == 'Images'}">
							<li class="show-image-gallery homeimagegallery">
							<a href="/${param.languageCode}/${navigation.url}">
								<div class="item-wrapper">
									<div class="bg-item-wrapper">
										<i class="glyphicon glyphicon-play-circle"></i>
									</div>
									<div class="item-container">
										<img src="${navigation.image}" alt="UMM Alquwain Events">
									</div>
								</div> <span class="title"><c:out value="${navigation.title}" /></span>
							</a>
							</li>
						</c:when>
						
						<c:when test="${navigation.displayTypeHome == 'Image' || navigation.displayTypeHome == 'ImageDetail'}">
							<li class="homeimagedetail">
							<a href="/${param.languageCode}/${navigation.url}">
								<div class="item-wrapper">
									<div class="bg-item-wrapper">
										<i class="glyphicon glyphicon-play-circle"></i>
									</div>
									<div class="item-container">
										<img src="${navigation.image}" alt="${navigation.title}" />
									</div>
								</div> <span class="title"><c:out value="${navigation.title}" /></span>
								</a>
								<div class="${hoverCSS} bg-img4">
								<div class="layer-parent" style="background-image: linear-gradient(rgba(41, 148, 76, 0.90), rgba(41, 148, 76, 0.90)), url('${navigation.image}');">
									<div class="title">
										<h2>
											<c:out value="${navigation.title}" />
										</h2>
									</div>
									<div class="hover-content-wrapper">
										<h3 class="sub-title">
											<c:out value="${navigation.title}" />
										</h3>
										<div class="thumb-info-wrapper">
											<img src="${navigation.teaserImage}" class="pull-left" alt="Awareness campaign on labour">
											<c:if test="${not empty navigation.teaserTitle}">
												<h4 class="tenders-title">												
													<c:out value="${navigation.teaserTitle}" />												
												</h4>
											</c:if>
											<p>${navigation.teaserText}</p>
										</div>
										<div class="btn-wrapper">
											<a href="${navigation.url}" class="btn-hover-container"><spring:message code="landing.readmore" /> </a>
										</div>
									</div>
								</div>
								</div>
					
							</li>
							
							<%-- <li class="show-image-gallery">
							<a href="${navigation.url}"> 
								<div class="item-wrapper">
									<div class="bg-item-wrapper">
										<i class="glyphicon glyphicon-play-circle"></i>
									</div>
									<div class="item-container">
										<img src="${navigation.image}" alt="UMM Alquwain Events">
									</div>
								</div> <span class="title"><c:out value="${navigation.title}" /></span>
							</a>
							</li> --%>
							
						</c:when>

						<c:when test="${navigation.displayTypeHome == 'Videos'}">

							<li class="show-video-gallery homevideos">
								<div class="item-wrapper">
									<div class="bg-item-wrapper">
										<i class="glyphicon glyphicon-play-circle"></i>
									</div>
									<div class="item-container">
										<img src="${navigation.image}" alt="">
										<c:forEach items="${navigation.associations}" var="item" varStatus="videoCount">
											<div style="display: none;" id="video${videoCount.count}">
												<video class="lg-video-object lg-html5 video-js vjs-default-skin" controls preload="none">
													<source src="${item.video}" type="video/mp4" >Your browser does not support HTML5 video.</source>
												</video>
											</div>
										</c:forEach>

										<!-- video thumb -->
										<ul id="video-gallery">

											<c:forEach items="${navigation.associations}" var="item" varStatus="videoCount">
												<li data-poster="${item.image}" data-html="#video${videoCount.count}"><img src="${item.teaserImage}" />
													<div class="gallery-hover">
														<i class="glyphicon glyphicon-play-circle"></i>
													</div></li>
											</c:forEach>
										</ul>
									</div>
								</div> <a href="${navigation.url}"> <span class="title"><c:out value="${navigation.title}" /></span></a>
							</li>

						</c:when>
						
						<c:when test="${ navigation.displayTypeHome == 'Tree'}">

							<li class="hometree">
							<a href="/${param.languageCode}/${navigation.navigations[0].url}">
								<div class="item-wrapper">
									<div class="bg-item-wrapper">
										<i class="glyphicon glyphicon-play-circle"></i>
									</div>
									<div class="item-container">
										<img src="${navigation.image}" alt="${navigation.title}" />
									</div>
								</div>  <span class="title"><c:out value="${navigation.title}" /></span>
								</a>
								<div class="${hoverCSS} bg-img6">
								<div class="layer-parent" style="background-image: linear-gradient(rgba(41, 148, 76, 0.95), rgba(41, 148, 76, 0.95)), url('${navigation.image}');">
									<div class="title">
										<h2>${navigation.title}</h2>
									</div>
									<div class="hover-content-wrapper">
										<div class="left-info-content pull-left">
											<p>
												<img src="${navigation.navigations[0].teaserImage}" alt="${navigation.navigations[0].teaserTitle}" align="left" /> ${navigation.navigations[0].teaserText}
											</p>
										</div>
										<div class="right-info-content pull-right">
											<ul>
												<c:forEach items="${navigation.navigations}" var="item" varStatus="loop" begin="1">
													<li><i class="glyphicon glyphicon-menu-right"></i> <a href="/${param.languageCode}/${item.url}">${item.title}</a></li>

												</c:forEach>
											</ul>
										</div>
										<div class="btn-wrapper">
											<a href="/${param.languageCode}/${navigation.navigations[0].url}" class="btn-hover-container"><spring:message code="landing.readmore" /></a>
										</div>
									</div>
								</div>
								</div>
							
							</li>
						</c:when>

						<c:when test="${ navigation.displayTypeHome == 'MediaTree'}">
							
							<li class="homemediatree">
							<a href="/${param.languageCode}/${navigation.navigations[0].url}">
								<div class="item-wrapper">
									<div class="bg-item-wrapper">
										<i class="glyphicon glyphicon-play-circle"></i>
									</div>
									<div class="item-container">
										<img src="${navigation.image}" alt="${navigation.title}" />
									</div>
								</div>  <span class="title">${navigation.title}</span>
								</a>
								<div class="${hoverCSS} bg-img7">
								<div class="layer-parent" style="background-image: linear-gradient(rgba(211, 102, 63, 0.95), rgba(211, 102, 63, 0.95)), url('${navigation.image}');">
									<div class="title">
										<h2>${navigation.title}</h2>
									</div>
									<div class="hover-content-wrapper">
										<h3 class="sub-title">
											<spring:message code="news.latest.news" />
										</h3>
										<div class="left-image-content pull-left">
											<article>
												<div class="img-wrapper-article">
													<img src="${navigation.navigations[0].teaserImage}" alt="${navigation.navigations[0].teaserTitle}" />
												</div>
												<h3>${navigation.navigations[0].heading}</h3>
												<div class="date-time-stamp">${navigation.navigations[0].date}</div>
												<p>${navigation.navigations[0].teaserText}</p>
											</article>
										</div>
										<div class="right-links-content pull-right">
											<ul>
												<c:forEach items="${navigation.navigations}" var="item" varStatus="loop" begin="1">
													<li><i class="glyphicon glyphicon-menu-right"></i> <a href="/${param.languageCode}/${item.url}">${item.title}</a></li>
												</c:forEach>
											</ul>
										</div>
										<div class="btn-wrapper">
											<a href="/${param.languageCode}/${navigation.navigations[0].url}" class="btn-hover-container"><spring:message code="landing.readmore" /></a>
										</div>
									</div>
								</div>
								</div>
							</li>
					
						</c:when>


						<c:when test="${navigation.displayTypeHome == 'List'}"> 

							<li class="homelisting">
							<a href="/${param.languageCode}/${navigation.url}">
								<div class="item-wrapper">
									<div class="bg-item-wrapper">
										<i class="glyphicon glyphicon-play-circle"></i>
									</div>
									<div class="item-container">
										<img src="${navigation.image}" alt="${navigation.title}" />
									</div>
								</div>  <span class="title">${navigation.title}</span>
								</a>
								<div class="${hoverCSS} bg-img1">
								<div class="layer-parent" style="background-image: linear-gradient(rgba(137, 137, 137, 0.90), rgba(137, 137, 137, 0.90)), url('${navigation.image}');">
									<div class="title">
										<h2>${navigation.title}</h2>
									</div>
									<div class="hover-content-wrapper">
										<h3 class="sub-title">${navigation.teaserTitle}</h3>
										<div class="list-wrapper">
											<ul>

												<c:forEach items="${navigation.associations}" var="item">
													<c:set var="truncatedTeaserTitle" value="${fn:split(item.teaserTitle,' ')}"/>											
													<li>
													<a class="fullLink" href="/${param.languageCode}/${fn:split(fn:split(navigation.url, '.')[0],'/')[0]}/${item.url}">
														<div class="image-wrapper">
															<img src="${item.teaserImage}" alt="${item.title}" />
														</div>
														<h3 class="title">${item.title}</h3>
														<p>
															<c:if test="${fn:length(truncatedTeaserTitle)>=8}">			
																<c:forEach var="i" begin="0" end="8">
														 			${truncatedTeaserTitle[i]}&nbsp;
																</c:forEach>...<spring:message code="news.readmore"/>
															</c:if>
															<c:if test="${fn:length(truncatedTeaserTitle)>0 and fn:length(truncatedTeaserTitle)<8}">
																	${item.teaserTitle}
															</c:if>
														</p>
													</a>
														<div class="link-wrapper">
															<a href="/${param.languageCode}/${fn:split(fn:split(navigation.url, '.')[0],'/')[0]}/${item.url}"><span><spring:message code="sites.go" /></span></a>
														</div>
													</li>
												</c:forEach>
											</ul>
										</div>
										<div class="btn-wrapper pull-right">
											<a href="/${param.languageCode}/${navigation.url}" class="btn-hover-container"><spring:message code="label.all" /> &nbsp; ${navigation.title} </a>
										</div>
									</div>
								</div>
							
							</li>
						</c:when>
						
						<c:when test="${navigation.displayTypeHome == 'Detail'}">
							<li class="homedetail">
							<a href="/${param.languageCode}/${navigation.url}">
								<div class="item-wrapper">
									<div class="bg-item-wrapper">
										<i class="glyphicon glyphicon-play-circle"></i>
									</div>
									<div class="item-container">
										<img src="${navigation.image}" alt="${navigation.title}" />
									</div>
								</div> <span class="title"><c:out value="${navigation.title}" /></span>
								</a>
								<div class="${hoverCSS} bg-img4">
								<div class="layer-parent" style="background-image: linear-gradient(rgba(34, 178, 168, 0.90), rgba(34, 178, 168, 0.90)), url('${navigation.image}');">
									<div class="title">
										<h2>
											<c:out value="${navigation.title}" />
										</h2>
									</div>
									<div class="hover-content-wrapper">
										<h3 class="sub-title">
											<c:out value="${navigation.title}" />
										</h3>
										<div class="thumb-info-wrapper">
											<img src="${navigation.teaserImage}" class="pull-left" alt="Awareness campaign on labour">
											<c:if test="${not empty navigation.teaserTitle}">
												<h4 class="tenders-title">												
													<c:out value="${navigation.teaserTitle}" />												
												</h4>
											</c:if>
											<p>${navigation.teaserText}</p>
										</div>
										<div class="btn-wrapper">
											<a href="/${param.languageCode}/${navigation.url}" class="btn-hover-container"><spring:message code="landing.readmore" /> </a>
										</div>
									</div>
								</div>
								</div>
					
							</li>

						</c:when>
						<c:when test="${navigation.displayTypeHome == 'ContactUs'}">

							<li class="homecontactus">
								<a href="/${param.languageCode}/${navigation.url}">
								<div class="item-wrapper">
									<div class="bg-item-wrapper">
										<i class="glyphicon glyphicon-play-circle"></i>
									</div>
									<div class="item-container">
										<img src="${navigation.image}" alt="Contact Us">
									</div>
								</div>  <span class="title"><spring:message code="footer.contact.us" /></span></a>
								<div class="${hoverCSS} bg-img5">
								<div class="layer-parent" style="background-image: linear-gradient(rgba(137, 137, 137, 0.90), rgba(137, 137, 137, 0.90)), url('${navigation.image}');">
									<div class="title">
										<h2>
											<spring:message code="footer.contact.us" />
										</h2>
									</div>
									</a>
									<div class="hover-content-wrapper">
										<div class="address-wrapper">
											<address class="pull-left">

												<c:if test="${not empty navigation.departmentVO.addressLine1 }">
													<h3>
														<c:out value="${navigation.departmentVO.addressLine1 }" />
													</h3>
												</c:if>

												<c:if test="${not empty navigation.departmentVO.addressLine2 }">
													<p>
														<c:out value="${navigation.departmentVO.addressLine2 }" />
													</p>
												</c:if>
												<c:if test="${not empty navigation.departmentVO.telephoneNumber }">
													<p class="arab-tel">
														<c:out value="${navigation.departmentVO.telephoneNumber }" />
													</p>
												</c:if>

												<c:if test="${not empty navigation.departmentVO.fax }">
													<p class="arab-tel">
														<c:out value="${navigation.departmentVO.fax }" />
													</p>
												</c:if>

												<c:if test="${not empty navigation.departmentVO.emailID }">
													<a href="mailto:${navigation.departmentVO.emailID }" class="email">${navigation.departmentVO.emailID }</a>
												</c:if>

												<c:if test="${not empty navigation.departmentVO.website }">
													<a href="${navigation.departmentVO.website }" class="web">${navigation.departmentVO.website }</a>
												</c:if>
												
												<c:if test="${not empty navigation.departmentVO.latitude }">
													<c:set var="latValue" value="${navigation.departmentVO.latitude}"/>
												</c:if>
												
												<c:if test="${not empty navigation.departmentVO.longitude }">
													<c:set var="longValue" value="${navigation.departmentVO.longitude}"/>
												</c:if>
												
												<c:choose>
													<c:when test="${param.languageCode=='en'}">
														<c:set var="deptname" value="${navigation.departmentVO.departmentNameEN}" />
													</c:when>
												
													<c:otherwise>
														<c:set var="deptname" value="${navigation.departmentVO.departmentNameAR}" />
													</c:otherwise>
												</c:choose>
												
											</address>
											<div class="map-wrapper pull-right">
												<!-- <div id="home_contact_map" style="width:260px; height:230px;">
												</div>-->
												
<iframe width="260px" height="230px" frameborder="0" scrolling="no" marginheight="0" marginwidth="0" src="https://maps.google.com/maps?q=${latValue},${longValue}&z=18&output=embed&iwloc=0"></iframe>
												
											</div>
										</div>
										<div class="social-icons">
											<p class="pull-left">Follow Us</p>
											<ul class="pull-left">
												<c:if test="${not empty navigation.departmentVO.facebookContact }">
													<li class="facebook"><a href="${navigation.departmentVO.facebookContact }"><span>Facebook</span></a></li>
												</c:if>

												<c:if test="${not empty navigation.departmentVO.twitterContact }">
													<li class="twitter"><a href="${navigation.departmentVO.twitterContact }"><span>Twitter</span></a></li>
												</c:if>
												
												<c:if test="${not empty navigation.departmentVO.linkedInContact }">
													<li class="linkedin"><a href="${navigation.departmentVO.linkedInContact }"><span>LinkedIn</span></a></li>
												</c:if>
												
												<c:if test="${not empty navigation.departmentVO.instagramContact}">
													<li class="instagram"><a href="${navigation.departmentVO.instagramContact }"><span>Instagram</span></a></li>
												</c:if>
												
												<c:if test="${not empty navigation.departmentVO.youtubeContact }">
													<li class="youtube"><a href="${navigation.departmentVO.youtubeContact }"><span>Youtube</span></a></li>
												</c:if>
											</ul>
										</div>

									</div>
								</div>
								</div>
								
							</li>

						</c:when>
					</c:choose>
				</c:forEach>
			</ul>
			</div>
		</div>

</section>

<c:forEach items="${navigations[param.languageCode]}" var="navigation">
	<c:if test="${navigation.displayTypeHome == 'Images'}">
		<!-- Image gallery light box wrapper -->
		<div class="video-gallery image-gallery-home hidden">
			<div class="video-wrapper ">
				<ul class="gallery">
					<c:forEach items="${navigation.associations}" var="item">
						<li data-src="${item.image}"><a href="#"> <img src="${item.teaserImage}" alt="" /></a></li>
						<!-- <li data-src="http://www.youtube.com/embed/eKG08z85DtY?enablejsapi=1"><a href="#"> <img src="/img/video-gallery/thumbs/1.jpg" alt="">
						</a></li>-->
					</c:forEach>
				</ul>
			</div>
		</div>

	</c:if>
</c:forEach>