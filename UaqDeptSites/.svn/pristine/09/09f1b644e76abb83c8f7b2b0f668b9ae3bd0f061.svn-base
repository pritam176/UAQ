<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page language="java" import="java.util.*"%>


<script type="text/javascript">
               
     function redirect() {		
    	 window.location = "smartuaq://";
     }
               
</script>

<div class="container-fluid">
	<div class="wrapper">
		<div class="mainmenu">
			<div class="col-md-12 hidden-xs hidden-sm">
				<div class="mainmenu-wrap">
					<ul class="no-list cf">
						<li><a href="/${param.languageCode}/home.html"><img
								src="/img/home1.png" alt="Home UAQ"> </a></li>
						<li class="active"><a href="#"><spring:message code="${pagelable}"/></a></li>

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

								<h2 class="page-title"><spring:message code='purchase.payment.review'/></h2>
								<!-- /page title -->
							</div>
							<div class="hidden-xs show-sm col-md-2 title-icon">
								<img src="/img/icons/icon-uaq.png" alt="uaq">
							</div>
						</div>
					</div>
				</div>	
					<!-- /page title -->
					<div class="main-content-wrap">
						<div class="page-content-wrap">
							<div class="form-content cf">
								<div class="col-md-12">
									<h3 class="title">
										<spring:message code='purchase.details' />
									</h3>

									<form method="POST"
										action="${webPaymentRequest.merchantAccount.redirectURL}">
										<input type="hidden" name="Action"
											value="${webPaymentRequest.action}" /> <input type="hidden"
											name="BankID"
											value="${webPaymentRequest.merchantAccount.bankID}" /> <input
											type="hidden" name="MerchantID"
											value="${webPaymentRequest.merchantAccount.merchantID}" /> <input
											type="hidden" name="Currency"
											value="${webPaymentRequest.currency}" /> <input
											type="hidden" name="PUN"
											value="${webPaymentRequest.transactionId}" /> <input
											type="hidden" name="PaymentDescription"
											value="${webPaymentRequest.paymentDescription}" /> <input
											type="hidden" name="MerchantModuleSessionID"
											value="${webPaymentRequest.merchantModuleSessionID}" /> <input
											type="hidden" name="TransactionRequestDate"
											value="${webPaymentRequest.transactionRequestDate}" /> <input
											type="hidden" name="SecureHash"
											value="${webPaymentRequest.secureHash}" /> <input
											type="hidden" name="ExtraFields_f16"
											value="${webPaymentRequest.extraFields_f16}" /> <input
											type="hidden" name="ExtraFields_f18"
											value="${webPaymentRequest.merchantAccount.terminalID}" /> <input
											type="hidden" name="ExtraFields_f14"
											value="${webPaymentRequest.extraFields_f14}" /> <input
											type="hidden" name="Lang" value="${webPaymentRequest.lang}" />
										<input type="hidden" name="ApplicationNumber"
											value="${webPaymentRequest.referenceID}" /> <input
											type="hidden" name="ExtraFields_intendedEDirhamService"
											value="${webPaymentRequest.intendedEDirhamService}" />
										<!-- to support multiple services per request, put the following in loop and replace 1,2,3 with index of the loop -->
										<input type="hidden" name="EServiceMainCodeSubCode_1"
											value="${webPaymentRequest.service.serviceCode}" />
										<!-- if variable service code, only then we include the price otherwise not  -->
										<c:if test="${webPaymentRequest.service.price != '0'}">
											<input type="hidden" name="EServicePrice_1"
												value="${webPaymentRequest.service.price}" />
										</c:if>
										<input type="hidden" name="EServiceQuantity_1"
											value="${webPaymentRequest.service.quantity}" />
											
											<div class="row payment-review">
													<div class="col-xs-6 col-md-3 payment-row"><spring:message code='purchase.id' /></div>
													<div class="col-xs-6 col-md-9 payment-row">${purchaseId }</div>
													<div class="col-xs-6 col-md-3 payment-row"><spring:message
														code='purchase.date' /></div>
													<div class="col-xs-6 col-md-9 payment-row"><c:set var="now" value="<%=new java.util.Date()%>" />
														<fmt:formatDate value="${now}" pattern="dd/MM/yyyy" />
													</div>
													<%-- <div class="col-xs-6 col-md-3 payment-row"><spring:message code='labe.payment.general.fee' /></div>
													<div class="col-xs-6 col-md-9 payment-row">${generalFee}</div> --%>
													<div class="col-xs-6 col-md-3 payment-row"><spring:message code='label.servicecatalog.serviceFee' /></div>
													<div class="col-xs-6 col-md-9 payment-row">${serviceFee}</div>
													<div class="col-xs-6 col-md-3 payment-row"><spring:message code='purchase.payment.total.amount' /></div>
													<div class="col-xs-6 col-md-9 payment-row">${totalAmount}</div>
													<div class="col-xs-6 col-md-3 payment-row"> <spring:message code='purchase.payment.accept.terms.conds' />  </div>
													<div class="col-xs-6 col-md-9 payment-row">
													<input type="checkbox" id="checkme" /> 
													<div class="checkbox inline-custom">
														<input id="acceptterms" type="checkbox" name="check2" value="1" >
															<label for="acceptterms" class="custom" ></label>
													</div>
													<a href="javascript:void(0)" data-toggle="modal" data-target="#termsModal" >
														<spring:message
														code='purchase.payment.terms.conds' /> </a>
													</div>
													<div class="col-xs-2 col-md-3 payment-row"></div>
													<div class="col-xs-12 col-md-9 payment-row">
													
													<input type="submit" id="paymentbtn" disabled value="<spring:message code='purchase.payment.review' />" class="btn btn1" id="" />
													
													<a href="${myRequestUrl}" class="link-btn hidden-sm hidden-xs"><spring:message code='btn.cancel'/> </a> 
													<a href="#" class="link-btn hidden-lg hidden-md"  onclick="redirect();"><spring:message code='btn.cancel'/> </a>
													
													</div>												
												
												<div class="col-xs-2 col-md-3 payment-row secure-payment"></div>
													<div class="col-xs-9 col-md-9 payment-row secure-payment"> <img
													src="/img/cards.png"
													alt=""></div>												
											</div>
										
									</form>
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
<!-- Modal -->

<div class="modal fade" id="termsModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
		<button type="button" class="close pull-right close-btn" data-dismiss="modal">
						<span class="glyphicon glyphicon-remove"></span>
		</button>
      <div class="modal-header">
        <h4 class="modal-title" id="myModalLabel"><spring:message code='purchase.payment.terms.conds'/></h4>
      </div>
      <div class="modal-body">
		<c:if test="${sessionScope.languageCode == 'en' }">
			<%@include file="paymentTermsConditions_en.html"%>
		</c:if>
		<c:if test="${sessionScope.languageCode == 'ar' }">
			<%@include file="paymentTermsConditions_ar.jsp"%>
		</c:if>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn link-btn " data-dismiss="modal"><spring:message code='btn.close'/></button>
      </div>
    </div>
  </div>
</div>

<!-- /Modal -->

<!-- Modal  repay-->

<div class="modal fade" id="repayModal" tabindex="-1" role="dialog" aria-labelledby="">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
     
      <div class="modal-body"> <h5><spring:message code='purchase.repay.message'/></h5></div>
      
    </div>
  </div>
</div>

<!-- /Modal repay-->
<script>

	$(window).load(function(){
	var repay="${repay}";
		if(repay == 'true'){
	        $('#repayModal').modal('show');
			setTimeout(function() {
				$("#repayModal").modal('hide');
			}, 3000);
		}
    });

	$("#paymentbtn").click(function(){
		if(document.getElementById('acceptterms').checked) {
		} else {			
			alert("<spring:message code='purchase.payment.accept.terms.conds'/>");
			return false;
		}
	});
	$("#acceptterms").click(function(){
		if(document.getElementById('acceptterms').checked) {
			$('input[type="submit"]').prop('disabled', false);
		} else {
			$('input[type="submit"]').prop('disabled', true);
		}
	});
</script>