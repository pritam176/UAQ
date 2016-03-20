<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<c:set var ="filesize"><spring:message code="filesizemsg"/></c:set>
					<c:set var ="fileextention"><spring:message code="fileextentionmsg"/></c:set>
					<c:set var ="fileerror"><spring:message code="fileerrormsg"/></c:set>
<c:set var ="fieldmsg"><spring:message code="field.validate"/></c:set>

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
								<li class=""><a href=""><spring:message code="dept.lbl.department" /></a>
								</li>
								<li class="active"><a href="#"><spring:message code="${selectedService.serviceName}"/> </a>
								</li>
							</ul>
						</div>
					</div>
				</div>
		<!-- content area -->
		<div class="content">
			<div class="row">
				<!-- left col -->
				<div class="col-xs-12 col-sm-9 col-md-9 main-left-col">

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
								<spring:message code="${selectedService.serviceName}"/>
									         <!--- BPM03.1.01: pgno 19 -->
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
								<form:form method="post" id="serviceFormId" action="${hasNextPhase?'showServicePhase.html':'submitSevice.html'}" modelAttribute="serviceForm" enctype="multipart/form-data">
									<input type="hidden" name="serviceId" value="${selectedService.id}" />
									<input type="hidden" name="servicePhase" value="${servicePhase}" />
									<input type="hidden" name="requestNumber" value="${requestNumber}" />
									
									<div class="row"></div>
									
									<c:forEach var="field" items="${fields}" varStatus="status">
									<c:if test="${fields[status.index].panelHeader != null}">
										<div id="panelHeader-${status.index}" class="row">
											<div class="col-md-12">
												<!-- form-sect-head-->
												<h5 class="form-title"><spring:message code="${fields[status.index].panelHeader}"/></h5>
											</div>
										</div>
									</c:if>
									
									<c:if test="${!fields[status.index].inSameRow}">
									<div class="row">
										<c:if test="${fields[status.index].fieldType != 'Radio'}"><div class="col-md-12 remove-pad"></c:if>
									</c:if>
											<c:if test="${fields[status.index].fieldType != 'Radio'}"><div class="col-md-6 remove-pad"></c:if>
											
												<c:choose>
												<c:when test="${fields[status.index].fieldType == 'Radio'}">
													<c:set var="labelDivClass" value="col-md-3"/>
													<c:set var="parentDivClass" value="col-md-9"/>
												</c:when>
												<c:otherwise>
													<c:set var="labelDivClass" value="col-md-5"/>
													<c:set var="parentDivClass" value="col-md-7"/>
												</c:otherwise>
												</c:choose>
												
												<!-- text box -->
												<div id="serviceField-${status.index}" class="form-group cf <c:if test='${fields[status.index].notifierField}'>notifierFieldClass</c:if>" <c:if test="${fields[status.index].requiredUpon_FieldName != null}">${fields[status.index].requiredUpon_FieldName}='${fields[status.index].requiredUpon_FieldValue}'</c:if> 
												<c:if test="${fields[status.index].mandatoryUpon_FieldName != null}">required_${fields[status.index].mandatoryUpon_FieldName}='${fields[status.index].mandatoryUpon_FieldValue}'</c:if>>
													<div class="${labelDivClass}">
														<label for="params['${fields[status.index].fieldName}']"  class="form-lbl ${fields[status.index].required?'mandatory_lbl':''}"><spring:message code="${fields[status.index].displayKey}"/>
															<c:if test="${fields[status.index].infoMessage != null}">
														 	<span class="form-lbl-subtxt"> <spring:message code="${fields[status.index].infoMessage}"/></span>
														 	</c:if>
														</label>
													</div>
													<div class="${parentDivClass} }">
														<c:choose>
															<c:when test="${fields[status.index].fieldType == 'Radio'}">
		                                                    	<c:if test="${fields[status.index].disabled || param.statusId == '6'}">
		                                                    		<input type="hidden" name="params['${fields[status.index].fieldName}']" value="${fields[status.index].fieldValue}">
		                                                    	</c:if>
																<c:forEach var="fieldLkVal" items="${fields[status.index].fieldLkValues}" varStatus="lkStatus">
				                                            	<div class="col-md-3 remove-pad">
				                                                    <div class="radio inline-custom">
			                                                            <input id="${fields[status.index].displayKey}-${lkStatus.index}" type="${fields[status.index].fieldType}" name="params['${fields[status.index].fieldName}']" <c:if test="${fields[status.index].fieldValue == fieldLkVal.key}">checked="checked"</c:if> <c:if test="${fields[status.index].disabled || param.statusId == '6'}">disabled="disabled"</c:if> value="${fieldLkVal.key}">
			                                                            <label for="${fields[status.index].displayKey}-${lkStatus.index}" class="custom">
																			<c:choose>
																				<c:when test="${fields[status.index].fieldLkNeedLocalization}">
																					<spring:message code="${fieldLkVal.value}"/>
																				</c:when>
																				<c:otherwise>
																					${fieldLkVal.value}
																				</c:otherwise>
																			</c:choose>
																		</label>
			                                                        </div>
																</div>
																</c:forEach>
																<form:errors path="params['${fields[status.index].fieldName}']" class="error"/>
															</c:when>
															<c:when test="${fields[status.index].fieldType == 'Select'}">
																<div class="custom-select-box cf">
																	<c:choose>
																		<c:when test="${fields[status.index].disabled || param.statusId == '6'}">
																			
																		<input name="params['${fields[status.index].fieldName}']" type="hidden" value="${fields[status.index].fieldValue}"/>
																		<select class="required1 required" disabled="true">
																		</c:when>
																		<c:otherwise>
																		<select class="required1 required" name="params['${fields[status.index].fieldName}']" data-msg-required="${fieldmsg}" >
																		</c:otherwise>
																	</c:choose>	
																			<option <c:if test="${fields[status.index].fieldValue == null}">selected</c:if> value="">
																				<spring:message code="option.select"/>
																			</option>
																			<c:forEach var="fieldLkVal" items="${fields[status.index].fieldLkValues}">
							
																			<option <c:if test="${fields[status.index].fieldValue == fieldLkVal.key}">selected</c:if> value="${fieldLkVal.key}">
																				<c:choose>
																					<c:when test="${fields[status.index].fieldLkNeedLocalization}"><spring:message code="${fieldLkVal.value}"/></c:when>
																					<c:otherwise>${fieldLkVal.value}</c:otherwise>
																				</c:choose>
																			</option>
																			</c:forEach>
																		</select>
																	    <form:errors path="params['${fields[status.index].fieldName}']" class="error"/>
																		
																</div>
															</c:when>
															<c:when test="${fields[status.index].fieldType == 'File'}">
																<div class="input-group file-upload">
																	<c:set var="attachmentValueStyle" value=""/>
																    <c:if test="${fields[status.index].fieldName != null && fields[status.index].fieldName != '' && param.statusId != '6'}">
																		<input name="files['${fields[status.index].fieldName}'].docTypeId" type="hidden" value="${fields[status.index].docTypeId}"/>
																		<input name="files['${fields[status.index].fieldName}'].docTypeName" type="hidden" value="${fields[status.index].docTypeName}"/>
																		<input type="text" class="form-control  other-form-file file-upload-option-view  <c:if test='${fields[status.index].required == true}'>required</c:if>" name ="files['${fields[status.index].fieldName}'].attachmentFile"  data-msg-required="${fieldmsg}" readonly="readonly" />
																		<span class="input-group-btn">
																			<span class="btn btn-file">
																				<spring:message code="file.browse" />&hellip; 
																				<input name="files['${fields[status.index].fieldName}'].attachmentFile" type="file" class="form-control file-upload-option file-upload-no-ajax" accept="image/jpg, image/JPG, image/JPEG, image/jpeg,image/jpeg,image/gif,image/png, application/pdf,image/x-eps, application/msword" error-size="${filesize}" error-extention="${fileextention}" error-failed="${fileerror}" />
																			</span>
																		</span>
																		<c:set var="attachmentValueStyle" value="position: absolute; margin: 35px -270px"/>
																    </c:if>
																    
																	<span style="${attachmentValueStyle}">
																    <c:if test="${fields[status.index].attachmentValue != null}">
																    	<div class="attachment-btm">
																			<a href="${fields[status.index].attachmentValue.viewUrl}" target="_blank">${fields[status.index].attachmentValue.fileName}</a><br/>
																    	</div>
																    </c:if>
																	</span>
																</div>
																<form:errors path="files['${fields[status.index].fieldName}'].attachmentFile" class="error"/>
															</c:when>
															<c:when test="${fields[status.index].fieldType == 'Span'}">
																<span>
																	${fields[status.index].fieldValue}
																</span>
															</c:when>
															<c:otherwise>
																<!-- Checking if field is disabled or not -->
																<c:choose>
																<c:when test="${fields[status.index].disabled || param.statusId == '6'}">
																	<input type="${fields[status.index].fieldType}" disabled="disabled" value="${fields[status.index].fieldValue}" class="form-control required" placeholder="" />
																	<c:if test="${fields[status.index].fieldName != null && fields[status.index].fieldName != '' }">
																		<input name="params['${fields[status.index].fieldName}']" type="hidden" value="${fields[status.index].fieldValue}"/>
															    	</c:if>
																</c:when>
																<c:otherwise>
																	<input data-msg-required="${fieldmsg}"  name="params['${fields[status.index].fieldName}']" type="${fields[status.index].fieldType}" value="${fields[status.index].fieldValue}" maxlength="${fields[status.index].length}" class="${fields[status.index].fieldName} form-control required" placeholder="" />
																    <form:errors path="params['${fields[status.index].fieldName}']" class="error"/>
																</c:otherwise>
																</c:choose>
															</c:otherwise>
														</c:choose>
													</div>
												</div>
												<!-- /text box -->
											<c:if test="${fields[status.index].fieldType != 'Radio'}"></div></c:if>
										<c:if test="${!fields[status.index].nextFieldInSameRow}">
										<c:if test="${fields[status.index].fieldType != 'Radio'}"></div></c:if>
									</div>
									</c:if>
									</c:forEach>

									<div class="row">
										<div class="col-md-12 remove-pad">
											<div class="col-md-6 remove-pad">
												<!-- submit button -->
												<c:if test="${param.statusId != '6'}">
												<div class="row">
													<div class="form-group submission">
														<div class="col-md-offset-5 col-md-7">
															<input type="submit" class="btn" value="<spring:message code="form.button.submit"/>" />
														</div>
													</div>
												</div>
												</c:if>
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
	</div>
</div>
<!-- script -->

<!--  //TODO don't forget to send this file to UAQ in the public HTML folder -->
<script type="text/javascript" src="/js/serviceValidation.js"></script>
<script src="/js/libs/jquery.validate.js"></script>
<script>

jQuery(function($) { 
 $(".proIdExpiryDate").datepicker({
	 minDate:1
 });
 $("#serviceFormId").validate();
});
</script>
