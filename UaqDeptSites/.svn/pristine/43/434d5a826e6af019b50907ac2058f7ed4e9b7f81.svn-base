
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<div class="mainmenu">
	<div class="col-md-12 hidden-xs hidden-sm">
		<div class="mainmenu-wrap">
			<ul class="no-list cf">
				<li><a href="/${param.languageCode}/home.html"><img
						src="/img/home1.png" alt="Home UAQ"> </a></li>
				<li class=""><a href="/${param.languageCode}/services/servicecatalog.html"><spring:message
							code="form.header.service" /></a></li>
				<li class="active"><a href="#"><spring:message
							code="label.egd.renewSupplier" /></a></li>

			</ul>
		</div>
	</div>
</div>


<!-- content area -->
<div class="content">

	<div class="col-xs-12 col-sm-3 col-md-3 main-right-col">
		<!-- social media share callout -->

		<!-- DepartmentLists -->
		<jsp:include page="../common/depts_services_rhs.jsp"></jsp:include>
		<!-- /DepartmentLists -->

		<!-- /social media share callout -->
	</div>

	<c:set var="establishmentReq">
		<spring:message code="feedback.form.field.establishment.required" />
	</c:set>
	<c:set var="tradeLicReq">
		<spring:message code="plzTradeLicenseNumber" />
	</c:set>
	<c:set var="expDateReq">
		<spring:message code="plzTradeLicenseExp" />
	</c:set>
	<c:set var="mobileReq">
		<spring:message code="plzMobileNumber" />
	</c:set>
	<c:set var="emailReq">
		<spring:message code="plzEmailAddress" />
	</c:set>
	<c:set var="addressReq">
		<spring:message code="plzAddress" />
	</c:set>
	<c:set var="officeTelReq">
		<spring:message code="label.egd.offTel.required" />
	</c:set>
	<c:set var="postBoxReq">
		<spring:message code="label.egd.postBox.required" />
	</c:set>
	<c:set var="suppCatReq">
		<spring:message code="label.egd.supplierCatagory.required" />
	</c:set>
	<c:set var="regTypeReq">
		<spring:message code="label.egd.registrationType.required" />
	</c:set>
	<c:set var="tradingLicReq">
		<spring:message code="label.egd.tradingLic.required" />
	</c:set>
	<c:set var="signatoryReq">
		<spring:message code="label.egd.signatoryAttest.required" />
	</c:set>
	<c:set var="commerceReq">
		<spring:message code="label.egd.commerceMember.required" />
	</c:set>
	<c:set var="certificatesReq">
		<spring:message code="label.egd.certificates.required" />
	</c:set>
	
	<c:set var ="filesize"><spring:message code="filesizemsg"/></c:set>
								<c:set var ="fileextention"><spring:message code="fileextentionmsg"/></c:set>
								<c:set var ="fileerror"><spring:message code="fileerrormsg"/></c:set>
								
								
	<c:set var="valMobile"><spring:message code="plzMobileNumber" /></c:set>
	<c:set var="valofficeLength"><spring:message code="maxnumoffc" /></c:set>
	<c:set var="emiratesReq"><spring:message code="plzChooseEmirates" /></c:set>
	
	
	<div class="row">
		<!-- right col -->

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
							<spring:message code="label.egd.renewSupplier" />
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
						<form:form commandName="newSupplierRegistrationCommand"
							id="feedbak" enctype="multipart/form-data" method="post"
							action="reNewSupplierRegistrationPage.html">
							<div class="row">
								<div class="row">
									<div class="col-md-12 remove-pad">
										<div class="form-group cf">
											<label class="mandatory_lbl pull-right"><spring:message
													code="allfieldmandatorylbl" /></label>
										</div>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-md-12 remove-pad">
									<div class="col-md-6 remove-pad">
										<!-- text box -->
										<div class="form-group cf">
											<div class="col-md-5">
												<label for="establishment-name"
													class="form-lbl mandatory_lbl"><spring:message
														code="establishNameAsStated" /></label>
											</div>
											<div class="col-md-7 form-grp-help">
												<form:input type="text" id="establishment-name"
													path="establishmentName" class="form-control required"
													required="required" readonly="true" data-msg-required="${establishmentReq}" />
											</div>
										</div>
										<!-- /text box -->
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-md-12 remove-pad">
									<div class="col-md-6 remove-pad">
										<!-- text box -->
										<div class="form-group cf">
											<div class="col-md-5">
												<label for="trade_licence" class="form-lbl mandatory_lbl"><spring:message
														code="trade.licence.number" /></label>
											</div>
											<div class="col-md-7">
												<form:input type="text" id="trade_licence"
													path="tradeLicenceNumber" class="form-control required"
													required="required"  readonly="true" data-msg-required="${tradeLicReq}" />
											</div>
										</div>
										<!-- /text box -->
									</div>
								</div>
							</div>

							<div class="row">
								<div class="col-md-12 remove-pad">
									<div class="col-md-6 remove-pad">
										<!-- text box -->
										<div class="form-group cf">
											<div class="col-md-5">
												<label for="expiry_date" class="form-lbl mandatory_lbl">
													<spring:message code="tradeLicenExpiryDate" />
												</label>
											</div>
											<div class="col-md-7">
												<form:input type="text"  readonly="true" id="expiry_date" path="expiryDate"
													class="form-control required"
													data-msg-required="${expDateReq}" />
											</div>
										</div>
										<!-- /text box -->
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-md-12 remove-pad">
									<div class="col-md-6 remove-pad">
										<!-- text box -->
										<div class="form-group cf">
											<div class="col-md-5">
												<label for="mobile_number" class="form-lbl mandatory_lbl"><spring:message
														code="mobileNum1" /> </label>
											</div>
											<div class="col-md-7">
												<form:input id="mobile_number" path="mobileNumber"
													type="text" class="form-control required"
													required="required" readonly="true" data-msg-required="${mobileReq}" />
											</div>
										</div>
										<!-- /text box -->
									</div>
								</div>
							</div>

							<div class="row">
								<div class="col-md-12 remove-pad">
									<div class="col-md-6 remove-pad">
										<!-- text box -->
										<div class="form-group cf">
											<div class="col-md-5">
												<label for="email" class="form-lbl mandatory_lbl"><spring:message
														code="email" /></label>
											</div>
											<div class="col-md-7">
												<form:input id="email" path="email" type="email"
													class="form-control required"
													data-msg-required="${emailReq}" readonly="true"/>
											</div>
											<!-- /text box -->
										</div>
									</div>
								</div>
							</div>
							
							
							<!-- /emirate ID added -->
	                                        
	                                        <div class="row">
												<div class="col-md-12 remove-pad">
													<div class="col-md-6 remove-pad">
															<!-- text box -->
														<div class="form-group cf">
															<div class="col-md-5">
																<label for="emirateId" class="form-lbl mandatory_lbl"><spring:message code="emiratedId"/>
																</label>
															</div>
															<div class="col-md-7">
																<form:input path="emirateId" id="emirateId" readonly="true"  name="emirateId"  class="form-control required" data-msg-required="${emailReq}"/>
															</div>
														<!-- /text box -->
														</div>
		                                        	</div>
												</div>
	                                        </div>

							<div class="row">
								<div class="col-md-12 remove-pad">
									<div class="col-md-6 remove-pad">
										<!-- text box -->
										<div class="form-group cf">
											<div class="col-md-5">
												<label for="address" class="form-lbl mandatory_lbl"><spring:message
														code="label.address" /> </label>
											</div>
											<div class="col-md-7">
												<form:input type="text" id="address" path="address"
													class="form-control required"
													data-msg-required="${addressReq}" maxlength="100" />
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-md-12 remove-pad">
									<div class="col-md-6 remove-pad">
										<!-- text box -->
										<div class="form-group cf">
											<div class="col-md-5">
												<label for="office_telephone" class="form-lbl mandatory_lbl"><spring:message
														code="label.egd.offTel" /> </label>
											</div>
											<div class="col-md-7">
												<form:input type="text" id="office_telephone"
													path="officeNumber" class="form-control required"
													data-msg-required="${officeTelReq}" placeholder="0[x]-xxxxxxx" data-rule-maxlength="10" data-rule-minlength="10" 
													 data-msg-number="${valMobile}" 
													data-msg-maxlength="${valofficeLength}" data-msg-minlength="${valofficeLength}" data-mask="00-0000000"/>
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-md-12 remove-pad">
									<div class="col-md-6 remove-pad">
										<!-- text box -->
										<div class="form-group cf">
											<div class="col-md-5">
												<label for="post_box" class="form-lbl mandatory_lbl"><spring:message
														code="label.egd.postBox" /> </label>
											</div>
											<div class="col-md-7">
												<form:input type="text" id="post_box" path="postBox"
													class="form-control required"
													data-rule-maxlength="10" data-rule-minlength="1"  data-rule-number="true" 
													data-msg-required="${postBoxReq}" data-msg-number="${postBoxReq}" data-msg-maxlength="${valofficeLength}" 
													data-msg-minlength="${valofficeLength}"  data-mask="0000000000"/>
											</div>
										</div>
									</div>
								</div>
							</div>
							                                        <!--  emitate list------------------->
	                                        <div class="row">
												<div class="col-md-12 remove-pad">
													<div class="col-md-6 remove-pad">
															<!-- text box -->
														<div class="form-group cf">
															<div class="col-md-5">
																<label for="emirates" class="form-lbl mandatory_lbl"><spring:message code="chooseEmirates" /> 
			                                                    </label>
															</div>
															<div class="col-md-7">
																<div class="custom-select-box cf">
	                                                                <form:select path="emirates"  class="required1" name="countryofcitizen" required="required" data-msg-required="${emiratesReq}">
	                                                                   <form:option value=""><spring:message code="selectEmirates"/></form:option>
																			<form:options items="${emirateList}" />
	                                                                </form:select>
	                                                            </div>
															</div>
														</div>
													</div> 
												</div>
	                                        </div>
	                                        <!-- end of emirate list -------------->
							<div class="row">
								<div class="col-md-12 remove-pad">
									<div class="col-md-6 remove-pad">
										<!-- text box -->
										<div class="form-group cf">
											<div class="col-md-5">
												<label for="supp_category" class="form-lbl mandatory_lbl"><spring:message
														code="label.egd.supplierCategory" /></label>
											</div>
											<div class="col-md-7">
												<div class="custom-select-box cf">
													<form:select class="required1 required"
														path="supplierCategory" data-msg-required="${suppCatReq}">
														<form:option value=""><spring:message code="option.select"/></form:option>
														<form:options items="${eGDSuppCategory}" />
													</form:select>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-md-12 remove-pad">
									<div class="col-md-6 remove-pad">
										<!-- text box -->
										<div class="form-group cf">
											<div class="col-md-5">
												<label for="supp_category" class="form-lbl mandatory_lbl"><spring:message
														code="label.egd.regType" /> </label>
											</div>
											<div class="col-md-7">
												<div class="custom-select-boxc">
													<form:select class="required1 required multi-select"  multiple="multiple" size="4"
														path="registrationsType" data-msg-required="${regTypeReq}">
														<form:options items="${egdSuppRegTypes}" />
													</form:select>

												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-md-12"> <!-- form-sect-head-->
									<h5 class="form-title"><spring:message code="label.ps.attachments" /></h5>
								</div> 
							</div>
							<div class="row">
								<div class="col-md-12 remove-pad">
									<div class="col-md-6 remove-pad">
										<!-- text box -->
										<div class="form-group cf">
											<div class="col-md-5">
												<label for="trading_attach3"
													class="form-lbl mandatory_lbl required"><spring:message
														code="label.egd.tradingLic" /> <span class=""> 
												</span> </label>
											</div>
											<div class="col-md-7">
											 <div class="input-group file-upload">
																<c:forEach items="${userAttachmentsList}" var="userAttachmentVO">
																<c:if test="${userAttachmentVO.attachmentType == '1'}">
																	<a href="${userAttachmentVO.attachmentUrl}" target="_blank">
																	 <c:if test="${param.languageCode eq 'en'}">
																	${userAttachmentVO.attachmentName}
																	</c:if>
																	<c:if test="${param.languageCode eq 'ar'}">
																	<spring:message code="tradeLicense" />
																	</c:if></a>
																</c:if>
																</c:forEach>
																
																</div>
											</div>
										</div>
										<!-- /text box -->
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-md-12 remove-pad">
									<div class="col-md-6 remove-pad">
										<!-- text box -->
										<div class="form-group cf">
											<div class="col-md-5">
												<label for="trading_attach2" class="form-lbl mandatory_lbl"><spring:message
														code="label.egd.signatoryAttest" /></label>
											</div>
											<div class="col-md-7">
											 <div class="input-group file-upload">
																<c:forEach items="${userAttachmentsList}" var="userAttachmentVO">
																<c:if test="${userAttachmentVO.attachmentType == '2'}">
																	<a href="${userAttachmentVO.attachmentUrl}" target="_blank"><c:if test="${param.languageCode eq 'en'}">
																	${userAttachmentVO.attachmentName}
																	</c:if>
																	<c:if test="${param.languageCode eq 'ar'}">
																	<spring:message code="signatureAttest" />
																	</c:if></a>
																</c:if>
																</c:forEach>
																</div>
											</div>
										</div>
										<!-- /text box -->
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-md-12 remove-pad">
									<div class="col-md-6 remove-pad">
										<!-- text box -->
										<div class="form-group cf">
											<div class="col-md-5">
												<label for="trading_attach1" class="form-lbl "><spring:message
														code="label.egd.commerceMember" /></label>
											</div>
											<div class="col-md-7">
												<div class="input-group file-upload">
													<input type="text" class="form-control "
														name="trading_attach1" readonly
														data-msg-required="${commerceReq}" /> <span
														class="input-group-btn"> 
														<span class="btn btn-file">
															<spring:message code="file.browse" />&hellip; 
																<input 	name="chamberOfCommerce" type="file" accept="image/jpg, image/JPG, image/JPEG, image/jpeg,image/jpeg,image/gif,image/png, application/pdf,image/x-eps, application/msword" data-msg-required="${commerceReq}" id="chamberOfCommerce" error-size="Please upload less than 2Mb" error-extention="Please upload valid format" error-failed="Please upload try again" />
														</span>
														</span>
														<form:hidden path="chamberOfCommerce_name" id="chamberOfCommerce_name" value=""/>
												</div>
											</div>
										</div>
										<!-- /text box -->
									</div>
								</div>
							</div>
							<div class="row certificate-last-row">
								<div class="col-md-12 remove-pad">
									<div class="col-md-6 remove-pad">
										<!-- text box -->
										<div class="form-group cf">
											<div class="col-md-5">
												<label for="trading_attach" class="form-lbl"><spring:message
														code="label.department.certificates" /></label>
											</div>
											<div class="col-md-7">
												<div class="input-group file-upload">
													<input type="text" class="form-control"
														name="trading_attach" readonly
														data-msg-required="${certificatesReq}" /> <span
														class="input-group-btn"> <span class="btn btn-file">
															<spring:message code="file.browse" />&hellip; 
															<input 	name="certificates" type="file" id="supplier_file_0" accept="image/jpg, image/JPG, image/JPEG, image/jpeg,image/jpeg,image/gif,image/png, application/pdf,image/x-eps, application/msword" error-size="${filesize}" error-extention="${fileextention}" error-failed="${fileerror}" />
															</span>
															</span>
																<form:hidden path="supplier_file_name[0]" id="supplier_file_0_name" value=""/>
												</div>
											</div>
										</div>
										<!-- /text box -->
									</div>
								</div>
							</div>
							<div class="row add-morecertificate-row">
								<div class="col-md-12 remove-pad">
									<div class="col-md-6 remove-pad">
										<!-- text box -->
										<div class="form-group cf">
											<div class="col-md-5"></div>
											<div class="col-md-7">
												<label for="emiratesid-front" class="form-lbl "><a
													href="#" id="addcertificate"> <spring:message
															code="label.egd.addMoreCert" />
												</a></label>
											</div>
										</div>
										<!-- /text box -->
									</div>
								</div>
							</div>



							<div class="row">
								<div class="col-md-12 remove-pad">
									<div class="col-md-6 remove-pad">
										<!-- submit button -->
										<div class="row">
											<div class="form-group submission">
												<div class="col-md-offset-5 col-md-7">
													<input type="submit" class="btn"
														value='<spring:message code="form.button.submit" />' />
												</div>
											</div>
										</div>
										<!-- /submit button -->
									</div>
								</div>
							</div>
						</form:form>
					</div>
				</div>
			</div>
			<!-- /left col -->
		</div>
	</div>
	<!-- /content area -->
</div>
<!-- script -->
<script src="/js/libs/jquery.validate.js"></script>
<script type="text/javascript"src="/js/libs/jquery.mask.js"></script>

<script>
	jQuery(function($) {

		$("#feedbak").validate();
		var certificateId = 1;
		var attach_arr = new Array();
		$("#addcertificate")
				.click(
						function(event) {
							event.preventDefault();
							if(attach_arr.length<2){
							//$(".row").removeClass("certificate-last-row");
							//certihtml = '<div class="row certificate-last-row" id="attachid_'+certificateId+'" ><div class="col-md-12 remove-pad"><div class="col-md-6 remove-pad"><div class="form-group cf"><div class="col-md-5"><label for="certificate_attach_'+certificateId+'" class="form-lbl mandatory_lbl">Certificates </label></div><div class="col-md-7"><div class="input-group file-upload"><input type="text" class="form-control" name ="certificate_attach_'+certificateId+'" id ="certificate_attach_'+certificateId+'" readonly /><span class="input-group-btn"><span class="btn btn-file">Browse&hellip; <input type="file" multiple></span></span><span class="glyphicon glyphicon-remove removeico" data-remove="attachid_'+certificateId+'"></span></div></div></div></div></div></div>';
							//alert("certihtml"+certihtml);
							
							certihtml = '<div class="row certificate-last-row" id="attachid_'+certificateId+'" ><div class="col-md-12 remove-pad"><div class="col-md-6 remove-pad"><div class="form-group cf"><div class="col-md-5"><label for="file_attach_'+certificateId+'" class="form-lbl"><spring:message code="label.department.certificates" /></label></div><div class="col-md-7"><div class="input-group file-upload"><input type="text" class="form-control"  name ="file_attach_'+certificateId+'" data-msg-required="${certificatesReq}" id ="file_attach_'+certificateId+'"  /><span class="input-group-btn"><span class="btn btn-file"><spring:message code="file.browse"/>&hellip; <input type="file" name="files['+certificateId+']" id="supplier_file_'+certificateId+'" accept="image/jpg, image/JPG, image/JPEG, image/jpeg,image/jpeg,image/gif,image/png, application/pdf,image/x-eps, application/msword" error-size="${filesize}" error-extention="${fileextention}" error-failed="${fileerror}" data-msg-required="${certificatesReq}" ></span></span><span class="glyphicon glyphicon-remove removeico" data-remove="attachid_'+certificateId+'"></span><input type="hidden" name="supplier_file_name['+certificateId+']" id="supplier_file_'+certificateId+'_name" value=""></div></div></div></div></div></div>';
							

							$(".add-morecertificate-row").before(certihtml);

							/* $('#file_attach_' + certificateId).rules(
									'add', {
										required : true,
										
									}); */
								attach_arr.push(certificateId);
								certificateId = certificateId + 1;
								if( attach_arr.length>=2){
									$("#addcertificate").hide();
								}else{
									$("#addcertificate").show();
								}
							}

						});

		$(".form-content").on("click", ".removeico", function() {

			//alert("test ");
			var removeitem = $(this).attr("data-remove");
			$("#" + removeitem).remove();
			$("#addcertificate").show();
			attach_arr.pop(removeitem.split("_")[1]);

		});
		//var image = '<img src="img/help/help1.jpg">';
		//$('#popover').popover({placement: 'right', content: image, html: true});

	});
</script>
<!-- /script -->
</body>
<!-- /body -->
</html>