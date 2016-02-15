<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


				<!--/mainmenu -->
					<div class="content">	
						<!-- right col -->
						<!-- left col -->
						<div class="col-xs-12 col-sm-9 col-md-9 main-left-col">
							<!-- page title -->
						
							<!-- /page title -->
							<div class="main-content-wrap">
								<div class="page-content-wrap">
									  <div class="row">
											<div class="col-md-12 thankyou-page"> 
											<input type="hidden" id="status" value="failed"/>
												
												 <div>
												 <c:if test="${ empty typeOfUser}">
												 <spring:message code="plz.login.mobile" />
												 </c:if>
												  <c:if test="${typeOfUser == '1' }">
												  <spring:message code="plz.login.establishment" />
												  </c:if>
												  <c:if test="${typeOfUser == '2' }">
												  <spring:message code="plz.login.individual" />
												  </c:if>
												   <c:if test="${invalid == 'true'}">
												   <br />
												  <spring:message code="request.invalid.data" />
												  </c:if>
												 </div>
											</div>
									  </div>
								</div>
							</div>
						<!-- /left col -->
					</div>
			</div>
		<!-- script -->
	<!-- 	<script src="/js/dest/app.js"></script> -->
		
	