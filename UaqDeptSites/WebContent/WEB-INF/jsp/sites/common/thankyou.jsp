
						<!-- right col -->
						<!-- left col -->
						<div class="col-xs-12 col-sm-9 col-md-9 main-left-col">
							<!-- page title -->
							<div class="page-title-wrap">
								<div class="row">
									<div class="col-xs-12 col-sm-9 col-md-10 title-txt">
										<!-- sub page title 
										<h4 class="page-title-sub">
										MUNICIPALITY
										</h4>-->
										<!-- /sub page title -->
										<!-- page title -->
										<h2 class="page-title">
											
										</h2>
										<!-- /page title -->
									</div>
									<div class="hidden-xs show-sm col-md-2 title-icon">
										<img src="img/icons/icon-uaq.png" alt="uaq">
									</div>
								</div>
							</div>
							<!-- /page title -->
							<div class="main-content-wrap">
								<div class="page-content-wrap">
									  <div class="row">
											<div class="col-md-12 thankyou-page"> 
												<h2> Thank You for using our online service </h2>
												
												  <p>${outputPayload.status_EN} <!-- your request has been successfully submitted and your request number is xxxxxx --></p>
												 								</div>
									  </div>
								</div>
							</div>
						<!-- /left col -->
					</div>
				</div>
			</div>
				<!-- /content area -->
				<!-- footer -->
				<footer class="stickyBottom cf" id="footer">
					<script>
						$('#footer').load('views/footer/footer.html');  
					</script>
				</footer>
				<!-- /footer -->
			</div>
		</div>
		<!-- script -->
		<script src="js/dest/app.js"></script>
		<script src="js/libs/jquery.validate.js"></script>
	    <script>
	       
			jQuery(function($) {  $("#feedbak").validate(); });
			
	    </script>
		<!-- /script -->
	</body>
	<!-- /body -->
</html>