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
								
								<li class="active"><a href="#"><spring:message code="label.paymenthistory"/></a>
								</li>
								
							</ul>
						</div>
					</div>
				</div>

		<div class="content cf">
		<!-- right col -->
		<!-- left col -->
		<div class="col-xs-12 col-sm-12 col-md-12 main-left-col">
			<!-- page title -->
			<div class="page-title-wrap">
				<div class="row">
					<div class="col-xs-12 col-sm-9 col-md-10 title-txt">
						<!-- page title -->
						<h2 class="page-title"><spring:message code="label.paymenthistory"/></h2>
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
								<div class="th first-col-hist"><spring:message code="label.paymenthistory.purchaseId"/></div>
								<div class="th first-col-hist"><spring:message code="label.paymenthistory.transactionId"/></div>
								<div class="th first-col-hist"><spring:message code="label.paymenthistory.receiptNumber"/></div>
								<div class="th first-col-hist"><spring:message code="label.paymenthistory.purchaseStatus"/>	</div>
								<div class="th three-col-hist"><spring:message code="label.paymenthistory.transactionMessage"/></div>
								<div class="th first-col-hist"><spring:message code="label.paymenthistory.transactionDate"/></div>
								<div class="th first-col-hist"><spring:message code="label.paymenthistory.eDirhamFees"/></div><!-- Transaction Ammount -->
							</div>
							
							
						 <c:forEach items="${paymentHistoryList}" var="item" varStatus="status">								
									
							<div class="tr">
									<div class="td first-col-hist accordion-xs-toggle"><c:out value="${item.purchaseId}"/></div>
									<div class="accordion-xs-collapse">
										<div class="inner">
											<div class="td first-col-hist tbl-service-name"><c:out value="${item.transactionID}"/></div>
											<div class="td first-col-hist tbl-created-date"><c:out  value="${item.confirmationId}"/></div>
											<div class="td first-col-hist tbl-modified-date"><c:out  value="${item.purchaseStatus}"/></div>					
											<div class="td three-col-hist tbl-status"><c:out value="${item.statusMessage}"/></div>
											<div class="td first-col-hist tbl-action"><c:out value="${item.transcationDate}"/></div>
											<div class="td first-col-hist tbl-action"><c:out value="${item.transcationAmount}"/></div>
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
