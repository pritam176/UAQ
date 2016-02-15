<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
		<div class="container-fluid">
			<div class="wrapper">
				<!-- header -->
				
				<!-- /header -->
					<!--mainmenu -->
				<div class="mainmenu">
					<div class="col-md-12 hidden-xs hidden-sm">
						<div class="mainmenu-wrap">
							<ul class="no-list cf">
								<li>
									<a href="/${param.languageCode}/home.html"><img src="/img/home1.png" alt="Home UAQ">
									</a>
								</li>
								<li class="active"><a href="#"><spring:message code="registration" /></a>
								</li>
								
							</ul>
						</div>
					</div>
				</div>
				<!--/mainmenu -->
				<!-- content area -->
				<div class="content">
					<div class="row">
						
						<!-- left col -->
						<div class="col-xs-12 col-sm-9 col-md-9 main-left-col">
							<!-- page title -->
							<div class="page-title-wrap">
								<div class="row">
									<div class="col-xs-12 col-sm-9 col-md-10 title-txt">
										<!-- page title -->
										<h2 class="page-title">
										<spring:message code="registration" />
										</h2>
										<!-- /page title -->
									</div>
									<div class="hidden-xs show-sm col-md-2 title-icon">
										<img src="/img/icons/icon-uaq.png" alt="uaq">
									</div>
								</div>
							</div>
							<!-- /page title -->
							<!-- page content -->
							<div class="main-content-wrap">
							
										<!-- accordion listing -->
										<div class="service-catalogue-wrap">
                                             <div class="panel-groupFaq1" id="accordion1">
											  <div class="panel panel-style">
                                                    <div class="panel-heading accordion-toggle " data-toggle="collapse" >
                                                         <h4 class="panel-title"><spring:message code="individualUserTxt"/></h4>

                                                    </div>
                                                    <div id="4" class="panel-collapse collapse in">
                                                        <div class="panel-body">
                                                            <ul class="service-catalogue-items registration-item">
																<li><a href="/${param.languageCode}/uaecitzenregister.html"><spring:message code="individualCitizenLbl"/></a></li>
																<li><a href="/${param.languageCode}/uaeresidentregister.html"><spring:message code="individualResidentLbl"/></a></li>
																<li><a href="/${param.languageCode}/gccresidentregister.html"><spring:message code="individualGccResident"/></a></li>
																<li><a href="/${param.languageCode}/gcccitizenregister.html"><spring:message code="individualGccCitizen"/></a></li>
																<li><a href="/${param.languageCode}/visitorregister.html"><spring:message code="indivlVisitor"/></a></li>
                                                            </ul>
                                                        </div>
                                                    </div>
                                                </div>

                                                <div class="panel panel-style">
                                                    <div class="panel-heading accordion-toggle " data-toggle="collapse">
                                                         <h4 class="panel-title"><spring:message code="establishUserTxt"/></h4>

                                                    </div>
                                                    <div id="2" class="panel-collapse collapse in">
                                                        <div class="panel-body">
                                                            <ul class="service-catalogue-items">
                                                                    <li><a href="/${param.languageCode}/establishmentregister.html"><spring:message code="establishmentReg"/></a></li>
                                                                   
                                                                </ul>
                                                        </div>
                                                    </div>
                                                </div>
                                             

                                               

									</div>
								</div>
							</div>
							<!-- /page content -->
						</div>
						<!-- /left col -->
					</div>
				</div>
				<!-- /content area -->
			
			</div>
		</div>
		<!-- script -->
		<!-- <script src="js/dest/app.js"></script> -->
		<!-- /script -->
	</body>
	<!-- /body -->
</html>