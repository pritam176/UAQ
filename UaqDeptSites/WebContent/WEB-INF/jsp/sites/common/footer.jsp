<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!-- Footer Wrapper Start -->
        <footer>
            <div class="row">
                 <c:if test="${not empty socialLinks}">
                        <c:forEach items="${socialLinks}" var="socialLink">
                         	
                         	<c:if test="${socialLink.key=='facebookContact'}">
                         		<c:set var="facebookContactValue" value="${socialLink.value}"/>
                         	</c:if>
                         	
                         	<c:if test="${socialLink.key=='twitterContact'}">
                         		<c:set var="twitterContactValue" value="${socialLink.value}"/>
                         	</c:if>
                         	
                         	<c:if test="${socialLink.key=='linkedInContact'}">
                         		<c:set var="linkedInContactValue" value="${socialLink.value}"/>
                         	</c:if>
                         	
                         	<c:if test="${socialLink.key=='instagramContact'}">
                         		<c:set var="instagramContactValue" value="${socialLink.value}"/>
                         	</c:if>
                         	
                         	<c:if test="${socialLink.key=='youtubeContact'}">
                         		<c:set var="youtubeContactValue" value="${socialLink.value}"/>
                         	</c:if>
                         	
                         	<c:if test="${socialLink.key=='deptName'}">
                         		<c:set var="deptName" value="${socialLink.value}"/>
                         	</c:if>
                         	
                       	</c:forEach>
                 </c:if>
            
                <div class="col-lg-6 col-sm-7 col-xs-12">
                    <nav>
                        <ul>
                            <li><a href="/${param.languageCode}/content/PrivacyPolicy.html"><spring:message
						code="footer.privacy.policy" /></a></li>
                            <li><a href="/${param.languageCode}/content/TermsAndConditions.html"><spring:message
						code="footer.terms.conditions" /></a></li>
                            <li><a href="/${param.languageCode}/content/Disclaimer.html"><spring:message
						code="footer.disclaimer" /></a></li>
                            <li><a href="/${param.languageCode}/content/SiteMap.html"><spring:message
						code="footer.site.map" /></a></li>
                            <li><a href="/${param.languageCode}/feedback.html"><spring:message code="footer.feedback" /></a></li>
                            <li><a href="/${param.languageCode}/content/ContactUs.html"><spring:message
						code="footer.contact.us" /></a></li>
                        </ul>
                    </nav>
                    <c:set var="copyrightmsg"><spring:message code="copyright.msg" /></c:set>
                    <c:set var="copyrightDeptName"><spring:message code="copyright.dept.name"/></c:set>
	                
	                <c:set var="copyrightmsg" value="${fn:replace(copyrightmsg, copyrightDeptName, deptName)}"/>
                    <div class="copyrights">
                        <p>${copyrightmsg} </p>
                    </div>
                </div>

	                
	                <div class="col-lg-5 col-sm-5 col-xs-12 2col-lg-offset-1 pull-right">
	                    <div class="social-icons pull-right">
	                    	<ul class="pull-right">
	                    	
	                    		<c:if test="${not empty facebookContactValue}">       
	                        		<li class="facebook social-footer"><a href="${facebookContactValue}" target="_blank"><span><spring:message code="label.facebook"/></span></a></li>
	                        	</c:if>
	                       		
	                       		<c:if test="${not empty twitterContactValue}">
	                            	<li class="twitter social-footer"><a href="${twitterContactValue}" target="_blank"><span><spring:message code="label.twitter"/></span></a></li>
	                            </c:if>
	                            
	                            <c:if test="${not empty linkedinContactValue}">
	                            	<li class="linkedin social-footer"><a href="${linkedinContactValue}" target="_blank"><span><spring:message code="label.linkedin"/></span></a></li>
	                            </c:if>
	                            
	                            <c:if test="${not empty instagramContactValue}">
	                            	<li class="instagram social-footer"><a href="${instagramContactValue}" target="_blank"><span><spring:message code="label.instagram"/></span></a></li>
	                        	</c:if>
	                    		
	                       		<c:if test="${not empty youtubeContactValue}">
	                            	<li class="youtube social-footer"><a href="${youtubeContactValue}" target="_blank"><span><spring:message code="label.youtube"/></span></a></li>
	                        	</c:if>
	                          	
	                        </ul>
	                    </div>
	                    <div class="footer-note text-right">
	                        <p><spring:message code="copyright.msg.last.updated" /></p>
	                    </div>
	                </div>
            </div>
        </footer>