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
								<li class=""><a href="/${param.languageCode}/services/servicecatalog.html"><spring:message code="form.header.service" /></a>
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
						<div class="col-xs-12 col-sm-9 col-md-9 main-left-col">
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
							<div class="main-content-wrap">
								<div class="page-content-wrap">
										<div class="form-content cf">
											<div class="col-md-12 thankyou-page"> 
												<h2> <spring:message code="label.service.erropage" /> </h2>
												 <h4> ${userUpdateStatus}</h4>
									  </div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>