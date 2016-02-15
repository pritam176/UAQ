<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!-- Home Page Wrapper Start -->
<div class="page-content-wrapper">
	<div class="row">
		<div class="col-lg-9 col-sm-9 col-md-9 col-xs-12">
			<!-- BreadCrumbs Start -->
			<div class="breadcrumbs hide-on-mobile">
				<spring:message code="label.pageLocation" />
				&nbsp; <a href="/${param.languageCode}/home.html"><spring:message code="header.home" /></a> 
				
				<c:forEach items="${navigations[param.languageCode]}" var="navigation">
				<c:if test="${(null!=parentLandingPage && navigation.assetId ==parentLandingPage.assetId)}">
				<span><i class="glyphicon glyphicon-menu-right"></i></span> <a href="/${param.languageCode}/${navigation.navigations[0].url}"><c:out value="${navigation.title}" /></a>
				</c:if>
				</c:forEach> 

				<span><i class="glyphicon glyphicon-menu-right"></i></span>
				<c:if test="${pageName == 'polls'}">
					<spring:message code="polls" />
				</c:if>
				<c:if test="${pageName == 'surveys'}">
					<spring:message code="survey" />
				</c:if>
			</div>
			<!-- BreadCrumbs End -->

			<!-- Page Content Start -->
			<div class="content-wrapper ${pageName}">
			<c:set var="subjectValue" value="${pageMetadata.pageTitle}"/>
			<div class="page-title-mob">
									<h2 class="main-title show-on-mobile title_mobile-share-wrap">${parentLandingPage.title}</h2>
									<div class="utility-icons show-on-mobile mobile-share-wrap" >
				<ul class="utility-wrap" style="float: right;">
					<li>
					
						<!-- Share Icon --> <a href="javascript:void(0);" class="share-icon share-all" id=""></a>

						<div class="submenu" style="display: none;">
							<ul class="root">
								<li class="share-fb"><a href="" target="_blank" alt=""> <img src="/img/icon-fb.png" alt="uaq"></a></li>
								<li><a href="http://twitter.com/share?text=text" target="_blank"><img src="/img/icon-twitter.png"></a></li>
								<li><a href="mailto:?subject=${subjectValue}&amp;Body=${sourceURL}"><img src="/img/icon-email.png"></a></li>
							</ul>
						</div>
					</li>
				</ul>
			</div>
			</div>				
				<h3 class="sub-title">
					<c:if test="${pageName == 'polls'}">
						<spring:message code="current.polls" />
					</c:if>
					<c:if test="${pageName == 'surveys'}">
						<spring:message code="survey" />
					</c:if>
				</h3>
				<c:forEach items="${surveyCommand.surveys}" var="surveyVO">
					<c:set value="${survey.formSubmissionType}" var="pollOrSurvey" />
					<form:form method="POST" id="survayForm_${surveyVO.assetId}" commandName="surveyCommand"
						action="/${param.languageCode}/${landing}/${pageName}.html"
						class="poll-survey-form">
						<input type="hidden" name="formAssetId"
							value="${surveyVO.assetId}" />
						<input type="hidden" name="isPoll"
							value="${surveyVO.formSubmissionType == 'Poll' ? true : false}" />
						<div class="survey-question">
							<%-- <h3 class="sub-title"><c:if test="${pageName == 'polls'}"> <spring:message code="poll" /> </c:if><c:if test="${pageName == 'surveys'}"> <spring:message code="survey" /> </c:if></h3> --%>
							<c:set var="primaryNavigationCount" value="0" scope="page" />
							<c:forEach var="formField" items="${surveyVO.formFields}"
								varStatus="fieldsCount">
								<input type="hidden"
									name="questionAnswers[${fieldsCount.count}].question"
									value="${formField.assetId}" />
								<input type="hidden"
									name="questionAnswers[${fieldsCount.count}].formFieldType"
									value="${formField.formFieldType}" />

								<c:if
									test="${formField.formFieldType == 'Radio Buttons' || formField.formFieldType == 'Check Box' || formField.formFieldType == 'Textbox'}">
									<section class="question">
										<h3>
											<c:if test="${param.languageCode == 'en' }">
		                                    		${fn:split(formField.text, '|')[0]}
		                                    	 </c:if>
											<c:if test="${param.languageCode == 'ar' }">
		                                    		${fn:split(formField.text, '|')[1]}
		                                    	 </c:if>
										</h3>
										
										<c:forEach var="fieldValue" items="${formField.values}">
										<c:set var="primaryNavigationCount"		value="${primaryNavigationCount + 1}" scope="page" />
											<c:choose>
												<c:when
													test="${formField.formFieldType == 'Radio Buttons' }">
													<div class="question-holder">
														<input type="radio" id="${formField.assetId}_square-radio-${primaryNavigationCount}"
															name="questionAnswers[${fieldsCount.count}].answer"
															value="${fieldValue}" class="custom-radio"
															onclick="submitVote(this);"> <label
															for="${formField.assetId}_square-radio-${primaryNavigationCount}"> <c:if
																test="${param.languageCode == 'en' }">
					                                    		${fn:split(fieldValue, '|')[0]}
					                                    	 </c:if> <c:if
																test="${param.languageCode == 'ar' }">
					                                    		${fn:split(fieldValue, '|')[1]}
					                                    	 </c:if>
														</label>
													</div>
												</c:when>
												<c:when test="${formField.formFieldType == 'Check Box' }">
													<div class="question-holder">
														<input type="checkbox"
															id="square-checkbox-${primaryNavigationCount}"
															name="questionAnswers[${fieldsCount.count}].answer"
															value="${fieldValue}" class="custom-checkbox"
															onclick="submitVote(this);"> <label
															for="square-checkbox-${primaryNavigationCount}"> <c:if
																test="${param.languageCode == 'en' }">
				                                    		${fn:split(fieldValue, '|')[0]}
				                                    	 </c:if> <c:if
																test="${param.languageCode == 'ar' }">
				                                    		${fn:split(fieldValue, '|')[1]}
				                                    	 </c:if>
														</label>
													</div>
												</c:when>
												<%-- <c:when test="${formField.formFieldType == 'Textbox' }">
					                            	<div class="question-holder">
				                                    <input type="text" id="${fieldsCount.count}" name="questionAnswers[${fieldsCount.count}].answer" value="${fieldValue}">
				                                    <label for="${fieldsCount.count}">en :${fn:split(fieldValue, '|')[0]} ar :${fn:split(fieldValue, '|')[1]}</label>
			                                		</div>
					                            </c:when> --%>
											</c:choose>											
										</c:forEach>
									</section>
								</c:if>
							</c:forEach>
							<div class="error error-msg" >												
								<c:if test="${not empty formAssetId && formAssetId == surveyVO.assetId}"> 
									${errorMessage}
								</c:if>
							</div>
							
							<div class="vote-btn-wrap">
								<input type="hidden" name="pageName" value="${pageName}" />
								<c:if test="${pageName == 'polls'}">
									<c:set var="errorMsg"><spring:message code='label.dropdown.message'/></c:set>
								</c:if>
								<c:if test="${pageName == 'surveys'}">
									<c:set var="errorMsg"><spring:message code='label.answer.all'/></c:set>
								</c:if>
								<button type="button" id="btn_${surveyVO.assetId}" class="btn btn-default btn-gray btn-survay-poll ${pageName}" data-erro-msg="${errorMsg}">
									<c:if test="${pageName == 'polls'}">
										<spring:message code="polls.vote" />
									</c:if>
									<c:if test="${pageName == 'surveys'}">
										<spring:message code="form.button.send" />
									</c:if>
								</button>
							</div>
						</div>
						<c:if
							test="${empty requestScope['org.springframework.validation.BindingResult.surveyCommand'].allErrors}">

						</c:if>

					</form:form>

				</c:forEach>
			</div>
			<!-- Page Content End -->
			<div class="back-to-top pull-right">
				<a href="#"><spring:message code="back.to.top" /> <i
					class="glyphicon glyphicon-triangle-top"></i></a>
			</div>
			<!-- Back to Top Button End -->
		</div>
		<div class="col-lg-3 col-sm-3 col-md-3 hide-on-mobile">
			<!-- Utility Icons Start -->
			<div class="utility-icons">
				<ul class="utility-wrap">
					<li>
						<!-- Print Icon --> <a href="#" class="print-icon"></a>
					</li>
					<li>
						<!-- Share Icon --> <a href="javascript:void(0);"
						class="share-icon share-all"></a>

						<div class="submenu" style="display: none;">
							<c:set var="subjectValue">
								<c:set var="subjectValue"><spring:message code='polls'/></c:set>
							</c:set>
							<ul class="root">
								<li class="share-fb"><a
									href="http://www.facebook.com/sharer/sharer.php?u=#url"
									target="_blank" alt=""> <img src="/img/icon-fb.png"
										alt="uaq"></a></li>
								<li><a href="http://twitter.com/share?text=text"
									target="_blank"><img src="/img/icon-twitter.png" /></a></li>
								<li><a
									href="mailto:?subject=${subjectValue}&amp;Body=${sourceURL}"><img
										src="/img/icon-email.png" /></a></li>
							</ul>
						</div>
					</li>
					<li>
						<!-- Font Increase and Decrease -->
						<ul class="font-changers">
							<li><a href="#" class="decrease-font"></a></li>
							<li><a href="#" class="reset-font"></a></li>
							<li><a href="#" class="increase-font"></a></li>
						</ul>
					</li>
				</ul>
			</div>
			<!-- Utility Icons End -->
			<!-- Side Bar Start -->
			<div class="side-bar">
				<c:if test="${null!=parentLandingPage}">
				<h2>
					<c:out value="${parentLandingPage.title}"/>
				</h2>
				</c:if>

				<ul class="side-nav">
					<c:forEach items="${parentLandingPage.navigations}" var="item">
						<c:choose>
							<c:when test="${source==item.url}">
								<li><a class="active"
									href="/${param.languageCode}/${item.url}">${item.title}</a></li>
							</c:when>
							<c:otherwise>
								<li><a href="/${param.languageCode}/${item.url}">${item.title}</a></li>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</ul>


			</div>
			<!-- Side Bar End -->

		</div>
	</div>
</div>
<script type="text/javascript" src="/js/lib/icheck.min.js"></script>
<script type='text/javascript'>
	var names = {};
   

$(".btn-survay-poll").click(function(e){
	
	var btnid= $(this).attr('id');
	btnid = btnid.split("_")[1];
	formId= "#survayForm_"+btnid;
	
	 $(formId+' :radio').each(function() {
        names[$(this).attr('name')] = true;
    });
	
	var count = 0;
    $.each(names, function() { 
        count++;
    });
	
	
	var errormsg= $(this).attr("data-erro-msg");


	if ($(formId+' :radio:checked').length === count) {
			$(formId).submit();
    }else{
		$(formId+" .error-msg").html("");
		var errormsElem = "<label class='error'>"+ errormsg+ "</label>";
		$(formId+" .error-msg").append(errormsElem);
		setTimeout(function() {
		$(formId+" .error-msg").html("");
		}, 3000);
		e.preventDefault();
		return false;
	}	
	
}); 
$('.polls input').on('ifChecked', function(event){
	
	 $('input').not($(this)).iCheck('uncheck');
		//$('input').iCheck('destroy');
		$('input').iCheck('update'); 
		//$(this).removeAttr("checked");
     //alert(event.type + ' callback');
});
</script>
<c:if test="${not empty pageMetadata.pageTitle}">
	<c:set var="titleText" value="${pageMetadata.pageTitle}"></c:set>
</c:if>

<c:if test="${not empty pageMetadata.pageKeywords}">
	<c:set var="captionText" value="${pageMetadata.pageKeywords}"></c:set>
</c:if>

<c:if test="${not empty pageMetadata.pageDescription}">
	<c:set var="descrText" value="${pageMetadata.pageDescription}"></c:set>
</c:if>

<c:if test="${not empty site}">
	<c:set var="logo" value="/img/logo/${site}.png"></c:set>
</c:if>

<script type = "text/javascript">
	var title_txt = '${titleText}';
	var caption_txt = '${captionText}';
	var descr_txt = '${descrText}';
	var logo = '${logo}';
</script>