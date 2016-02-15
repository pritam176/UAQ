<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<style>
	
	.error {
		/* color: #D8000C;
		background-color: #FFBABA; */
	 }

</style>

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
					<!-- /page title -->
					<div class="main-content-wrap">
						<div class="page-content-wrap">
							<div class="form-content cf">
								<div class="col-md-12">
									<h2 class="error payment-error">${message}</h2>
									<form action="${returnUrl}" id="payment-error-form">
										<table width="100%" border="0" cellspacing="0" cellpadding="0"
											class="customtable">
											<tr>
												<td class="removemob">&nbsp;</td>
												<td>		
													<a href="${myRequestUrl}" class="link-btn hidden-sm hidden-xs"><spring:message code='btn.close'/> </a>											
													<a href="#" class="link-btn hidden-lg hidden-md"  onclick="redirect();"><spring:message code='btn.close'/> </a>
												 </td>												
											</tr>
											<tr>
												<td colspan="3" class="tabh"></td>
											</tr>
											<tr>
												<td class="removemob">&nbsp;</td>
												<td><img
													src="/img/cards.png"
													alt="" class="payment-error-img">
												</td>
											</tr>
										</table>
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