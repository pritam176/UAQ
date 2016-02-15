<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="container-fluid">
		<div class="wrapper">
		<div class="mainmenu">
					<div class="col-md-12 hidden-xs hidden-sm">
						<div class="mainmenu-wrap">
							<ul class="no-list cf">
								<li>
									<a href="/${param.languageCode}/home.html"><img src="/img/home1.png" alt="Home UAQ">
									</a>
								</li>
								<li class=""><a href='<spring:message code="dept.url" />'><spring:message code="dept.lbl.department" /></a>
								</li>
								<li class="active"><a href="#"><spring:message code="${param.pagelable}" /></a>
								</li>
								
							</ul>
						</div>
					</div>
				</div>
			<!-- content area -->
			<div class="content">
			<div class="row">
					<!-- right col -->
					
					<!-- right col -->
						<!-- left col -->
						<div class="col-xs-12 col-sm-9 col-md-9 main-left-col">
							<!-- page title -->
							<div class="page-title-wrap">
								<div class="row">
									<div class="col-xs-12 col-sm-9 col-md-10 title-txt">
										
										<h2 class="page-title">
									<spring:message code="${param.pagelable}" />
										</h2>
										<!-- /page title -->
									</div>
									<div class="hidden-xs show-sm col-md-2 title-icon">
										<img src="/img/icons/icon-uaq.png" alt="uaq">
									</div>
								</div>
							</div>
							<!-- /page title -->
							<div class="main-content-wrap">
								<div class="page-content-wrap">
										<div class="form-content cf">
											<div class="col-md-12 thankyou-page"> 
												<h2> <spring:message code="dept.lbl.thank" /> </h2>
												  <div>${param.message} </div>
												  <c:if test="${not empty actmessage}">
												  <div>${actmessage}</div>
												  </c:if>
												<c:if test="${param.statusId == '33'}">
												   <c:url var="actionUrl" value="${param.paymentUrl}">
           											 <c:param name="serviceId" value="${param.serviceId}" />
           											 <c:param name="requestNo" value="${param.requestNo}" />
           											 <c:param name="statusId" value="${param.statusId}" />
           											 <c:param name="pagelable" value="${param.pagelable}" />
           											 <c:param name="typeOfUser" value="${param.typeOfUser}" />
           											 <c:param name="letter" value="${param.letter}" />
        										   </c:url>
        										 <div><a class ="link-btn" href='<c:out value="${actionUrl}"/>'><spring:message code="label.eservices.proceedtoappfee" /></a></div>
												 </c:if>
												 
												 <c:if test="${param.statusId == '21'}">
												  <c:url var="actionUrl" value="${param.paymentUrl}">
           											 <c:param name="serviceId" value="${param.serviceId}" />
           											 <c:param name="requestNo" value="${param.requestNo}" />
           											  <c:param name="statusId" value="${param.statusId}" />
           											  <c:param name="pagelable" value="${param.pagelable}" />
        										 </c:url>
        										<div><a class ="link-btn" href='<c:out value="${actionUrl}"/>'><spring:message code="label.eservices.proceedtorev" /></a></div>
												</c:if>
												 
									  </div>
								</div>
							</div>
							<!-- /left col -->
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
				
				
				<!-- /content area -->
				<!-- footer -->
				
				<!-- /footer -->
		
		<!-- script -->
		<!-- <script src="js/dest/app.js"></script> -->
		
		<!-- /script -->
	