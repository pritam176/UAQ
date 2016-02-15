<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<footer class="stickyBottom cf" id="footer">
	<div class="submenuItemsHolder cf">
		<c:set var="primaryNavigationCount" value="0" scope="page" />
		<c:forEach items="${navigationList[param.languageCode]}"
			var="navigation">
			<c:if test="${navigation.showInFooter == 'True'}">
				<c:set var="primaryNavigationCount"
					value="${primaryNavigationCount + 1}" scope="page" />
				<c:set var="hoverClass">hovercontainer ${navigation.name}</c:set>

					<c:choose>
					<c:when test="${fn:startsWith(source,'citizens') && fn:startsWith(navigation.url,'citizens')}">
					
						<c:set var="submenuItem" scope="page">submenuItem footer-item-${primaryNavigationCount} footer-item-Citizens</c:set>
					</c:when>

					<c:when test="${fn:startsWith(source,'business') && fn:startsWith(navigation.url,'business')}">
					
					<c:set var="submenuItem" scope="page">submenuItem footer-item-${primaryNavigationCount} footer-item-Business</c:set>
					</c:when>

					<c:when test="${fn:startsWith(source,'visitor') && fn:startsWith(navigation.url,'visitor')}">
					
					<c:set var="submenuItem" scope="page">submenuItem footer-item-${primaryNavigationCount} footer-item-Visitor</c:set>
					</c:when>

					<c:when test="${fn:startsWith(source,'residents') && fn:startsWith(navigation.url,'residents')}">
					
					<c:set var="submenuItem" scope="page">submenuItem footer-item-${primaryNavigationCount} footer-item-Residents</c:set>
					</c:when>
					<c:otherwise>
						<c:set var="submenuItem" scope="page">submenuItem footer-item-${primaryNavigationCount} footer-item</c:set>
					</c:otherwise>

					</c:choose>		

				<div class="${submenuItem}" data-footer-image=${navigation.image}>
					<div class="${hoverClass}">
						<h2>${navigation.title}</h2>
						<div class="col-sm-6 col-xs-12">
							<div class="textbox">
								<ul>
									<li><a
										href="/${param.languageCode}/${navigation.navigations[0].url}">${navigation.navigations[0].title}</a></li>
								</ul>
								<div class="footer-hover-content-box">
									<img src="${navigation.navigations[0].teaserImage}" alt=""
										class="img" /><p>${navigation.navigations[0].teaserText}</p>									
										<a href="/${param.languageCode}/${navigation.navigations[0].url}"><spring:message code="landing.readmore" /></a>
								</div>
							</div>

						</div>

					
						<div class="col-sm-6 col-xs-12">
							<ul>
							<c:set var="readMore" value="false"/> 
								<c:forEach items="${navigation.navigations}" var="item"
									varStatus="loop" begin="1">
									<c:if test="${loop.count<=5}">
									<li><a href="/${param.languageCode}/${item.url}">${item.title}</a></li>
									</c:if>
									
									<c:if test="${loop.count>4 && readMore=='false'}" >
									<c:set var="readMore" value="true"/>
									<a href="/${param.languageCode}/${navigation.url}" class="hoverMore"><spring:message code="landing.readmore"/></a>
									</c:if>
								</c:forEach>
							</ul>
						</div>
					</div>
					<a href="/${param.languageCode}/${navigation.url}">
						<h3 class="submenuTitle">${navigation.heading}</h3>${navigation.teaserText}
					</a>
				</div>
			</c:if>
		</c:forEach>
	</div>
	<%@ page import="com.uaq.vo.HomeVO"%>
<%
HomeVO homeVO = (HomeVO) request.getSession().getAttribute("homeVO");
%>

	<div class="copyrightHolder cf">
		<div class="leftHolder hidden-sm col-md-3 hidden-xs">
			<p>
				<spring:message code="copyright.msg"></spring:message>
			</p>
		</div>
		<div class="leftHolder col-md-9 col-sm-12 col-xs-12  pr0">
			<ul class="no-list socilaIcons cf">
				<li class="showmob"><a href="#collapseApp"
					data-toggle="collapse" aria-expanded="true"
					aria-controls="collapseApp" data-parent="monogram-acc"
					onclick="return false;"> <img
						src="/img/icons/icon-phone-app.png" alt="Smart phone apps">
						<span class="footericons"><spring:message
								code="footer.smart.app" /></span></a></li>
				<li class="showmob"><a href="/${param.languageCode}/faq.html">
						<img src="/img/icons/icon-faq.png" alt="FAQ"> <span
						class="footericons"><spring:message code="footer.faq" /></span>
				</a></li>
				<li class="showmob"><a data-toggle="collapse"
					href="#collapseSocial" aria-expanded="true"
					aria-controls="collapseSocial" data-parent="monogram-acc"
					onclick="return false;"> <img src="/img/facebook.png"
						alt="Social image"> <span class="footericons"><spring:message
								code="footer.social.app" /></span>
				</a></li>
				<li class="showmob"><a href="/${param.languageCode}/news.html">
						<img src="/img/news.png" alt="News"> <span
						class="footericons"><spring:message code="footer.news" /></span>
				</a></li>
				<li class="showmob"><a
					href="/${param.languageCode}/events.html">
						<img src="/img/events.png" alt="Events"> <span
						class="footericons"><spring:message code="footer.events" /></span>
				</a></li>
				<li><a href="/${param.languageCode}/careers.html"> <img
						src="/img/careers.png" alt="Careers"> <span
						class="footericons"><spring:message code="footer.careers" /></span></a></li>
				<li class="showmob"><a
					href="/${param.languageCode}/feedback.html"> <img
						src="/img/feedback.png" alt="Feedback"> <span
						class="footericons"><spring:message code="footer.feedback" /></span></a></li>
			</ul>
		</div>
	</div>


	<div class="collapse" id="collapseSocial" data-parent="#accordion2">
		<div class="well ">
			<div class="socialIcons">
				<div class="facebook">
					<a href="${department.facebookContact}" target="_blank"><img src="/img/fb.png" class="socialimg" alt=""></a>
					<div class="follow">
						<a href="${department.facebookContact}" target="_blank"><spring:message code="footer.follow.facebook" /><br />
							</a>
					</div>
				</div>
				<div class="twitter">
					<a href="${department.twitterContact}" target="_blank"><img src="/img/twitter.png" class="socialimg"
						alt=""></a>
					<div class="follow twitter-follow">
						<a href="${department.twitterContact}" target="_blank"><spring:message code="footer.follow.twitter" /><br />
							</a>
							<span class="collapse collapseFeed" id="collapseTwitter" data-parent="#accordion3"><div id="twitter-news"></div></span>
					</div>
				</div>
				<div class="google">
					<a href="${department.googlePlusContact}" target="_blank"><img src="/img/googleicon.png" class="socialimg"
						alt=""></a>
					<div class="follow">
						<a href="${department.googlePlusContact}" target="_blank"><spring:message code="footer.follow.google" /><br />
							</a>
					</div>
				</div>
				<div class="rssfeed">
					<a href="/${param.languageCode}/rssFeeds.html" target="_blank"><img src="/img/rssicon.png" class="socialimg"
						alt=""></a>
					<div class="follow">
						<a href="/${param.languageCode}/rssFeeds.html" target="_blank"><spring:message code="footer.follow.rssfeed" /><br />
							</a>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="collapse" id="collapseApp" data-parent="#accordion2">
		<div class="well ">
			<div class="appIcons">
				<div class="apple">
					<a href="https://itunes.apple.com/ae/app/uaq-guide/id920507395?mt=8" target="_blank"><img src="/img/aple.png" class="socialimg" alt=""></a>
				</div>
				<div class="googleplay ">
					<a href="https://play.google.com/store/apps/details?id=euaq.gov.ae&hl=en" target="_blank"><img src="/img/googlePlay.png" class="socialimg"
						alt=""></a>
				</div>
				<div class="windows ">
					<a href="https://www.microsoft.com/en-us/store/apps/uaq-guide/9wzdncrcwft6" target="_blank"><img src="/img/windows.png" class="socialimg"
						alt=""></a>
				</div>
				<div class="backberry">
					<a href="https://appworld.blackberry.com/webstore/content/59946712/?countrycode=US&lang=en" target="_blank"><img src="/img/backberry.png" class="socialimg"
						alt=""></a>
				</div>
			</div>
		</div>
	</div>

	<div class="policyHolder  hidden-xs cf">
		<div class="col-md-7 hidden-sm hidden-md">
			<p>
			<spring:message code="copyright.msg.last.updated"></spring:message>
			</p>
		</div>
		<div class="col-md-12 col col-sm-12 col-lg-5 pr0">
			<ul class="no-list redirectLinks">
				<li><a href="/${param.languageCode}/content/PrivacyPolicy.html"><spring:message
							code="footer.privacy.policy" /></a></li>
				<li><a
					href="/${param.languageCode}/content/TermsAndConditions.html"><spring:message
							code="footer.terms.conditions" /></a></li>
				<li><a href="/${param.languageCode}/content/Disclaimer.html"><spring:message
							code="footer.disclaimer" /></a></li>
				<li><a href="/${param.languageCode}/content/SiteMap.html"><spring:message
							code="footer.site.map" /></a></li>
				<li><a href="/${param.languageCode}/content/ContactUs.html"><spring:message
							code="footer.contact.us" /></a></li>
			</ul>
		</div>
	</div>

	 <div class="collapse" id="collapseApp" data-parent="#accordion2">
		<div class="well ">
			<div class="socialIcons">
				<div class="facebook">
					<a href="${department.facebookContact}" target="_blank"><img src="/img/fb.png" class="socialimg" alt=""></a>
					<div class="follow">
						<a href="${department.facebookContact}" target="_blank"><spring:message code="footer.follow.facebook" /><br />
							</a>
					</div>
				</div>
				<div class="twitter">
					<a href="${department.twitterContact}" target="_blank"><img src="/img/twitter.png" class="socialimg"
						alt=""></a>
					<div class="follow">
						<a href="${department.twitterContact}" target="_blank"><spring:message code="footer.follow.twitter" /><br />
							</a>
						
					</div>
				</div>
				<div class="google">
					<a href="${department.googlePlusContact}" target="_blank"><img src="/img/googleicon.png" class="socialimg"
						alt=""></a>
					<div class="follow">
						<a href="${department.googlePlusContact}" target="_blank"><spring:message code="footer.follow.google" /><br />
							</a>
					</div>
				</div>
				<div class="rssfeed">
					<a href="/${param.languageCode}/rssFeeds.html" target="_blank"><img src="/img/rssicon.png" class="socialimg"
						alt=""></a>
					<div class="follow">
						<a href="/${param.languageCode}/rssFeeds.html" target="_blank"><spring:message code="footer.follow.rssfeed" /><br />
							</a>
					</div>
				</div>
			</div>
		</div>
	</div>
		
	<c:if test="${param.languageCode=='en'}">	
	<script type='text/javascript' src='/js/twitterfetch.js'></script> 
	</c:if> <c:if test="${param.languageCode=='ar'}">
		<script type='text/javascript' src='/js/twitterfetch-ar.js'></script> 
	</c:if>
	<script> jQuery(document).ready(function() {twitterFetcher.fetch( '648890794552639488', 'twitter-news', 1, true);});</script>

	<script>
	
	
	
	//$("#user_login").click(function(){
		function validateLoginForm(e){
			
			 username = $("#username").val();
            password = $("#password").val();
            var loginerror = 0;

            if (username == "") {
                loginerror = 1;
            }
            if (password == "") {
                loginerror = 1;
            }
            if (loginerror == 1) {
				var empt_error_msg=$("#user_login").attr("data-error-empty-msg");
                $("#login_error_msg").show(); 
				$("#login_error_msg").text(empt_error_msg);
                return false;
            }else{
			var contexPath = "";
			var language ="${param.languageCode}";
			var password= $("#password").val();
			var username= $("#username").val();
			var error_service = $("#user_login").attr("data-error-service-msg");
			$("#user_login").attr('disabled', true);
			$(".login-loading").show();
			
		$.ajax({
			 method: "POST",
			 url: "/"+language+"/login.html", 
			 data: { username:username, password:password} ,// 
			 success: function(data) {
				 if(data.status==="Success"){
						$(".login-loading").hide();
						$('#login_modal').modal('hide');
						$('#profileModal').modal('show');
						$('#welcome_name').text(data.name);
						$("#profile_name").text(data.name);
						$("#user_login").attr('disabled', false);
				 }else{
					 $(".login-loading").hide();
					 $("#login_error_msg").show(); 
					 if(typeof(data.msg)=="undefined"){
						 $("#login_error_msg").text(error_service);
					 }else{
						$("#login_error_msg").text(data.msg); 
					 }
					 
					 $("#user_login").attr('disabled', false);
				 }
			 },
				error: function(e) {
					 $(".login-loading").hide();
					 $("#login_error_msg").show(); 
					 $("#login_error_msg").text(error_service);
					 $("#user_login").attr('disabled', false);
			 }
		
	
		});
		} 
		//alert("test");
			return false;
		  e.preventDefault();
		
	}
	</script>
</footer>