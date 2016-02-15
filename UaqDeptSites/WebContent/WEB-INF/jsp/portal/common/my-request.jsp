<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<div class="mainmenu">
					<div class="col-md-12 hidden-xs hidden-sm">
						<div class="mainmenu-wrap">
							<ul class="no-list cf">
								<li>
									<a href="/${param.languageCode}/home.html"><img src="/img/home1.png" alt="Home UAQ">
									</a>
								</li>
								
								<li class="active"><a href="#"><spring:message code="label.myrequest"/></a>
								</li>
								
							</ul>
						</div>
					</div>
				</div>

		<div class="content cf">
		<!-- right col -->
		<!-- left col -->
		<div class="col-xs-12 col-sm-9 col-md-9 main-left-col">
			<!-- page title -->
			<div class="page-title-wrap">
				<div class="row">
					<div class="col-xs-12 col-sm-9 col-md-10 title-txt">
						<!-- page title -->
						<h2 class="page-title"><spring:message code="label.myrequest"/></h2>
						<!-- /page title -->
					</div>
					<div class="hidden-xs show-sm col-md-2 title-icon">
						<img src="/img/icons/icon-uaq.png" alt="uaq">
					</div>
				</div>
			</div>
			<!-- /page title -->
			<!-- main content -->
			<div class="main-content-wrap">
				<div class="my-request-wrap">
					<div class="table-wrap cf">
						
						<div class="divtable accordion-xs">
							<div class="tr headings">
								<div class="th first-col"><spring:message code="requestno"/></div>
								<div class="th second-col"><spring:message code="serviceName"/></div>
								<div class="th first-col"><spring:message code="createddte"/></div>
								<div class="th first-col">
								
								<spring:message code="mdfedDte"/>
								</div>
								<div class="th first-col"><spring:message code="stus"/></div>
								<div class="th three-col"><spring:message code="acton"/></div>
							</div>
							
							
						 <c:forEach items="${myRequest}" var="item"
									varStatus="status">								
									
							<div class="tr">
									<div class="td first-col accordion-xs-toggle"><c:out value="${item.requestNo}"/></div>
									<div class="accordion-xs-collapse">
										<div class="inner">
											<div class="td second-col tbl-service-name"><c:out value="${item.serviceName}"/></div>
											<div class="td first-col tbl-created-date"><c:out  value="${item.createdDate}"/></div>
											<div class="td first-col tbl-modified-date">
											<c:if test="${item.createdDate != item.modifiedDate}">
											<c:out  value="${item.modifiedDate}"/>
											</c:if>
											</div>
											<div class="td first-col tbl-status"><c:out value="${item.statusName}"/></div>
											<div class="td three-col tbl-action">
												
												<!--  Waste Containor -->
												<c:if test="${item.serviceId ==101 &&  item.sorceType == 1 && ( item.statusId ==5 || item.statusId ==6 || item.statusId ==33 ||  item.statusId ==18 )}">
													
													<a href="${item.url}?serviceId=${item.serviceId}&requestNo=${item.requestNo}&statusId=${item.statusId}&typeOfUser=${item.userType}" class="link-btn">
													<c:if test="${item.statusId ==5}">
													<spring:message code="label.eservice.resubmit"/>
													</c:if>
													<c:if test="${item.statusId ==6}">
													<spring:message code="label.eservice.viewdetails"/>
													</c:if>
													<c:if test="${item.statusId ==33}">
													<spring:message code="label.payment.paynow"/>
													</c:if><c:if test="${item.statusId ==18}">
													<spring:message code="label.payment.paynow"/>
													</c:if>
													<c:if test="${item.statusId !=5 && item.statusId !=6 && item.statusId !=33 && item.statusId !=18}">
													${item.statusName}
													</c:if>
													</a>
												
												</c:if>
												
												<!--  Extention Grand   Land -->
												<c:if test="${item.serviceId ==301 &&  item.sorceType == 1 && ( item.statusId ==5 || item.statusId ==6 || item.statusId ==33 ||  item.statusId ==18 )}">
													
													<a href="${item.url}?serviceId=${item.serviceId}&requestNo=${item.requestNo}&statusId=${item.statusId}&typeOfUser=${item.userType}" class="link-btn">
													<c:if test="${item.statusId ==5}">
													<spring:message code="label.eservice.resubmit"/>
													</c:if>
													<c:if test="${item.statusId ==6}">
													<spring:message code="label.eservice.viewdetails"/>
													</c:if>
													<c:if test="${item.statusId ==18}">
													<spring:message code="label.payment.paynow"/>
													</c:if>
													<c:if test="${item.statusId !=5 && item.statusId !=6 && item.statusId !=18}">
													${item.statusName}
													</c:if>
													</a>
													
												</c:if>
												
												<!--  LAND_DEMACRATION_REQUEST-->
												<c:if test="${item.serviceId ==302 &&  item.sorceType == 1 && ( item.statusId ==5 || item.statusId ==6 || item.statusId ==33 ||  item.statusId ==18 )}">
													
													<a href="${item.url}?serviceId=${item.serviceId}&requestNo=${item.requestNo}&statusId=${item.statusId}&typeOfUser=${item.userType}" class="link-btn">
													<c:if test="${item.statusId ==5}">
													<spring:message code="label.eservice.resubmit"/>
													</c:if>
													<c:if test="${item.statusId ==6}">
													<spring:message code="label.eservice.viewdetails"/>
													</c:if>
													<c:if test="${item.statusId ==33}">
													<spring:message code="label.payment.paynow"/>
													</c:if><c:if test="${item.statusId ==18}">
													<spring:message code="label.payment.paynow"/>
													</c:if>
													<c:if test="${item.statusId !=5 && item.statusId !=6 && item.statusId !=33 && item.statusId !=18}">
													${item.statusName}
													</c:if>
													</a>
													
												</c:if>
												
												<!--  Add Land -->
												<c:if test="${item.serviceId ==303 &&  item.sorceType == 1 && ( item.statusId ==5 || item.statusId ==6 || item.statusId ==33 ||  item.statusId ==18 || item.statusId ==26  ) }">
													
													<a href="${item.url}?serviceId=${item.serviceId}&requestNo=${item.requestNo}&statusId=${item.statusId}&typeOfUser=${item.userType}" class="link-btn">
													<c:if test="${item.statusId ==5}">
													<c:if test="${item.sorceType ==1}">
													<spring:message code="label.eservice.resubmit"/>
													</c:if>
													</c:if>
													<c:if test="${item.statusId ==6}">
													<spring:message code="label.eservice.viewdetails"/>
													</c:if>
													<c:if test="${item.statusId ==26}">
													<spring:message code="label.ps.subNoc"/>
													</c:if>
													<c:if test="${item.statusId ==33}">
													<spring:message code="label.payment.paynow"/>
													</c:if><c:if test="${item.statusId ==18}">
													<spring:message code="label.payment.paynow"/>
													</c:if>
													
													<c:if test="${item.statusId !=5 && item.statusId !=6 && item.statusId !=26 && item.statusId !=33 && item.statusId !=18}">
													${item.statusName}
													</c:if>
													</a>
													
												</c:if>
												<!--  Issu Site Paln-->
												<c:if test="${item.serviceId ==304 &&  item.sorceType == 1 && ( item.statusId ==5 || item.statusId ==6 || item.statusId ==33 || item.statusId ==18 )}">
													
													<a href="${item.url}?serviceId=${item.serviceId}&requestNo=${item.requestNo}&statusId=${item.statusId}&typeOfUser=${item.userType}" class="link-btn">
													<c:if test="${item.statusId ==5}">
													<c:if test="${item.sorceType ==1}">
													<spring:message code="label.eservice.resubmit"/>
													</c:if>
													</c:if>
													<c:if test="${item.statusId ==6}">
													<spring:message code="label.eservice.viewdetails"/>
													</c:if>
													<c:if test="${item.statusId ==26}">
													<spring:message code="label.ps.subNoc"/>
													</c:if>
													<c:if test="${item.statusId ==33}">
													<spring:message code="label.payment.paynow"/>
													</c:if><c:if test="${item.statusId ==18}">
													<spring:message code="label.payment.paynow"/>
													</c:if>
													<c:if test="${item.statusId !=5 && item.statusId !=6 && item.statusId !=26 && item.statusId !=33 && item.statusId !=18}">
													${item.statusName}
													</c:if>
													</a>
													
												</c:if>
												<!-- New Supplier-->
												<c:if test="${item.serviceId ==501 &&  item.sorceType == 1 && ( item.statusId ==5 || item.statusId ==6 || item.statusId ==33 || item.statusId ==18 )}">
													
													<a href="${item.url}?serviceId=${item.serviceId}&requestNo=${item.requestNo}&statusId=${item.statusId}&typeOfUser=${item.userType}" class="link-btn">
													<c:if test="${item.statusId ==5}">
													<spring:message code="label.eservice.resubmit"/>
													</c:if>
													<c:if test="${item.statusId ==6}">
													<spring:message code="label.eservice.viewdetails"/>
													</c:if>
													<c:if test="${item.statusId ==33}">
													<spring:message code="label.payment.paynow"/>
													</c:if><c:if test="${item.statusId ==18}">
													<spring:message code="label.payment.paynow"/>
													</c:if>
													<c:if test="${item.statusId !=5 && item.statusId !=6 && item.statusId !=33 && item.statusId !=18}">
													${item.statusName}
													</c:if>
													
													</a>
													
												</c:if>
												
												<!-- re New Supplier-->
												
												<c:if test="${item.serviceId ==502 &&  item.sorceType == 1 && ( item.statusId ==5 || item.statusId ==6 || item.statusId ==33 || item.statusId ==18 )}">
													
													<a href="${item.url}?serviceId=${item.serviceId}&requestNo=${item.requestNo}&statusId=${item.statusId}&typeOfUser=${item.userType}" class="link-btn">
													<c:if test="${item.statusId ==5}">
													<spring:message code="label.eservice.resubmit"/>
													</c:if>
													<c:if test="${item.statusId ==6}">
													<spring:message code="label.eservice.viewdetails"/>
													</c:if>
													<c:if test="${item.statusId ==33}">
													<spring:message code="label.payment.paynow"/>
													</c:if><c:if test="${item.statusId ==18}">
													<spring:message code="label.payment.paynow"/>
													</c:if>
													<c:if test="${item.statusId !=5 && item.statusId !=6 && item.statusId !=33 && item.statusId !=18}">
													${item.statusName}
													</c:if>
													</a>
													
												</c:if>
												
											<!-- who ever it may concern -->
												<c:if test="${item.serviceId ==405  &&  item.sorceType == 1 && ( item.statusId ==5 || item.statusId ==6 || item.statusId ==33 || item.statusId ==18 )}">
													
													<a href="${item.url}?serviceId=${item.serviceId}&requestNo=${item.requestNo}&statusId=${item.statusId}&letter=${item.letter}" class="link-btn">
													<c:if test="${item.statusId ==5}">
													<spring:message code="label.eservice.resubmit"/>
													</c:if>
													<c:if test="${item.statusId ==6}">
													<spring:message code="label.eservice.viewdetails"/>
													</c:if>
													<c:if test="${item.statusId ==33}">
													<spring:message code="label.payment.paynow"/>
													</c:if><c:if test="${item.statusId ==18}">
													<spring:message code="label.payment.paynow"/>
													</c:if>
													<c:if test="${item.statusId !=5 && item.statusId !=6 && item.statusId !=33 && item.statusId !=18}">
													${item.statusName}
													</c:if>
													</a>
													
												</c:if>
													<!-- New Pro -->
												<c:if test="${item.serviceId ==403 &&  item.sorceType == 1 && ( item.statusId ==5 || item.statusId ==6 || item.statusId ==33 || item.statusId ==18 )}">
													
													<a href="${item.url}?serviceId=${item.serviceId}&requestNo=${item.requestNo}&statusId=${item.statusId}&letter=${item.letter}" class="link-btn">
													<c:if test="${item.statusId ==5}">
													<spring:message code="label.eservice.resubmit"/>
													</c:if>
													<c:if test="${item.statusId ==6}">
													<spring:message code="label.eservice.viewdetails"/>
													</c:if>
													<c:if test="${item.statusId ==33}">
													<spring:message code="label.payment.paynow"/>
													</c:if><c:if test="${item.statusId ==18}">
													<spring:message code="label.payment.paynow"/>
													</c:if>
													<c:if test="${item.statusId !=5 && item.statusId !=6 && item.statusId !=33 && item.statusId !=18}">
													${item.statusName}
													</c:if>
													</a>
													
												</c:if>
													<!-- re New Pro -->
												<c:if test="${item.serviceId ==404 &&  item.sorceType == 1 && ( item.statusId ==5 || item.statusId ==6 || item.statusId ==33 || item.statusId ==18 )}">
													
													<a href="${item.url}?serviceId=${item.serviceId}&requestNo=${item.requestNo}&statusId=${item.statusId}&letter=${item.letter}" class="link-btn">
													<c:if test="${item.statusId ==5}">
													<spring:message code="label.eservice.resubmit"/>
													</c:if>
													<c:if test="${item.statusId ==6}">
													<spring:message code="label.eservice.viewdetails"/>
													</c:if>
													<c:if test="${item.statusId ==33}">
													<spring:message code="label.payment.paynow"/>
													</c:if><c:if test="${item.statusId ==18}">
													<spring:message code="label.payment.paynow"/>
													</c:if>
													<c:if test="${item.statusId !=5 && item.statusId !=6 && item.statusId !=33 && item.statusId !=18}">
													${item.statusName}
													</c:if>
													</a>
													
												</c:if>
												
													<!-- LPV -->
												<c:if test="${item.serviceId ==401 &&  item.sorceType == 1 && ( item.statusId ==5 || item.statusId ==6 || item.statusId ==33 || item.statusId ==18 )}">
													
													<a href="${item.url}?serviceId=${item.serviceId}&requestNo=${item.requestNo}&statusId=${item.statusId}&letter=${item.letter}" class="link-btn">
													<c:if test="${item.statusId ==5}">
													<spring:message code="label.eservice.resubmit"/>
													</c:if>
													<c:if test="${item.statusId ==6}">
													<spring:message code="label.eservice.viewdetails"/>
													</c:if>
													<c:if test="${item.statusId ==33}">
													<spring:message code="label.payment.paynow"/>
													</c:if><c:if test="${item.statusId ==18}">
													<spring:message code="label.payment.paynow"/>
													</c:if>
													<c:if test="${item.statusId !=5 && item.statusId !=6 && item.statusId !=33 && item.statusId !=18}">
													${item.statusName}
													</c:if>
													</a>
													
												</c:if>
												
												
											</div>
										</div>
									</div>
							
							</div>
								</c:forEach> 
						
						</div>
						<!-- /my request table -->
					</div>
				</div>
			</div>
			<!-- /main content -->
		</div>
		<!-- /left col -->
	</div>

<!-- /content area -->
<!-- footer -->
<!--<footer class="stickyBottom cf" id="footer">
					<script>
						$('#footer').load('views/footer/footer.html');
					</script>
				</footer>-->
<!-- /footer -->


<!-- script -->
<!-- <script src="/js/dest/app.js"></script> -->
<script>
	$(function() {
		var isXS = false, $accordionXSCollapse = $('.accordion-xs-collapse');

		// Window resize event (debounced)
		var timer;
		$(window).resize(function() {
			if (timer) {
				clearTimeout(timer);
			}
			timer = setTimeout(function() {
				isXS = $(window).width() < 900;

				// Add/remove collapse class as needed
				if (isXS) {
					$accordionXSCollapse.addClass('collapse');
				} else {
					$accordionXSCollapse.removeClass('collapse');
				}
			}, 100);
		}).trigger('resize'); //trigger window resize on pageload    

		// Initialise the Bootstrap Collapse
		$accordionXSCollapse.each(function() {
			$(this).collapse({
				toggle : false
			});
		});

		// Accordion toggle click event (live)
		$(document)
				.on(
						'click',
						'.accordion-xs-toggle',
						function(e) {
							e.preventDefault();

							var $thisToggle = $(this), $targetRow = $thisToggle
									.parent('.tr'), $targetCollapse = $targetRow
									.find('.accordion-xs-collapse');

							if (isXS && $targetCollapse.length) {
								var $siblingRow = $targetRow.siblings('.tr'), $siblingToggle = $siblingRow
										.find('.accordion-xs-toggle'), $siblingCollapse = $siblingRow
										.find('.accordion-xs-collapse');

								$targetCollapse.collapse('toggle'); //toggle this collapse
								$siblingCollapse.collapse('hide'); //close siblings

								$thisToggle.toggleClass('collapsed'); //class used for icon marker
								$siblingToggle.removeClass('collapsed'); //remove sibling marker class
							}
						});
	});
</script>
<!-- /script -->
