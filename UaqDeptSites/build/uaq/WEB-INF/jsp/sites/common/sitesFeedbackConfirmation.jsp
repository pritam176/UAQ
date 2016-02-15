<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
    
                
 <!-- Home Page Wrapper Start -->
        <div class="page-content-wrapper">
            <div class="row">
                <div class="col-lg-9 col-sm-9 col-md-9 col-xs-12">
                	<!-- BreadCrumbs Start -->
                    <div class="breadcrumbs hide-on-mobile">
						<spring:message code="label.pageLocation" />
						<a href="/${param.languageCode}/home.html"><spring:message
								code="header.home" /></a> <span><i
							class="glyphicon glyphicon-menu-right"></i></span>
						<spring:message code="feedback.title" />
					</div>
                    <!-- BreadCrumbs End -->

                    <!-- Page Content Start -->
                    <div class="content-wrapper">
                        <div class="thankyou-page"> 
                            <h2><spring:message code="form.feedback.confirmation"/></h2>
                              <p></p>
                        </div>
                    </div>
                    <!-- Page Content End -->
                    <!-- Back Button Start -->
                    <div class="back-btn">
                       <!--  <a href="#">Back</a> -->
                    </div>
                    <!-- Back Button End -->
                </div>
                <div class="col-lg-3 col-sm-3 col-md-3 hide-on-mobile">
                	
                </div>
            </div>
        </div>
        <!-- Home Page Wrapper End -->