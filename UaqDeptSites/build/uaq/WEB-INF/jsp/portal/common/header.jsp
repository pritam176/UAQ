<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div class="col-md-12">
	<figure class="logo-holder col-sm-4 col-xs-12">
		<a href="/${param.languageCode}/home.html" target="_blank"><img src="/img/logo.png" alt="UAQ LOGO" /></a>
	</figure>
	<nav class="navbar col-sm-8 col-xs-12">
		<ul class="nolist pull-right cf navbarItems">
			<li class="navItem service-catalogue"><a href="/${param.languageCode}/services/servicecatalog.html" class="servicescatalogue">
							<img src="/img/icons/icon-services.png" alt="" />
					 <span class="hidden-xs hidden-sm hidden-md catalogue"><spring:message code="form.header.service" /></span>
			</a></li>
			<li class="navItem rate"><!--  <a href="#" data-toggle="modal" data-toggle="modal" data-target="#rate_modal" data-whatever="@getbootstrap"> -->
				<a href="#" id="rate_model_feedback" data-rate-feedback="<spring:message code='duplicate.session' />" >
					<figure>
						<img src="/img/icons/icon-rate.png" class="img-resposnsive" alt="rate"/>
					</figure>
			</a></li>

			<li class="navItem weatherImg"><span class="type hidden-xs pull-right">
						<figure>
							<img src="/img/ajax-loader.gif" id="weatherimage" alt="Weather Image"/>
						</figure>
				</span> <span class="degree">loading..</span>
			</li>
			<c:if test="${loginstatus=='Failure'}">
			<li class="navItem login"><a href="#" data-toggle="modal" data-target="#login_modal" data-whatever="@getbootstrap">
					<figure>
						<img src="/img/icons/icon-lock.png" alt="Login image">
					</figure> <span class="login hidden-xs hidden-sm hidden-md "><spring:message code="header.login" /></span>
			</a></li>
			</c:if>
			<!-- profile tag start here -->
			<c:if test="${loginstatus=='Success'}">
			<li class="navItem login">
				<a href="#"   data-toggle="modal"  data-target="#profileModal" data-whatever="@getbootstrap">
					<figure>
						<img src="/img/icons/myprofile.png" alt="Login image">
					</figure>
					<span class="login hidden-xs hidden-sm hidden-md ">${UserName}</span>
				</a>
			</li>
			</c:if>
			<!--  profile tag ends here !!! -->
			<li class="navItem lang"><c:if test="${param.languageCode=='en'}">
					<a href="/${param.languageCode}/changeLanguage.html?fromPage=${source}&ignore=${ignore}${paramList}"> <spring:message code="change.arabic" />
					</a>
				</c:if> <c:if test="${param.languageCode=='ar'}">
					<a href="/${param.languageCode}/changeLanguage.html?fromPage=${source}&ignore=${ignore}${paramList}"> <spring:message code="change.english" />
					</a>
				</c:if></li>
			<li class="navItem search hidden-xs"><a href="#" data-toggle="modal" data-toggle="modal" data-target="#search_modal" data-whatever="@getbootstrap">
					<figure>
						<img src="/img/icons/icon-search.png" alt="seach">
					</figure>
			</a></li>
			<li class="navItem menu"><a href="#">
					<figure class="searchDesktop">
						<img src="/img/icons/icon-search-menu.png" alt="menu">
					</figure> <c:if test="${param.languageCode=='en'}">
						<span class="searchMob"> </span>
					</c:if> <c:if test="${param.languageCode=='ar'}">
						<span class="searchMob arabicnav"> </span>
					</c:if>
			</a></li>
		</ul>
		
		<div class="mob-search visible-xs">
			<div class="form-group cf">
			<c:set var="searchPlaceholder"><spring:message code='label.search' /></c:set>
			<form:form id="mob-search" commandName="searchCommand" name="headersearch" action="/${param.languageCode}/search.html" method="post">
				<form:input path="keyword" placeholder="${searchPlaceholder}" class="search"/> 
				<input type="submit" value="" class="searchIcon">
			</form:form>
			</div>
		</div>
	</nav>


	<div class="mainMenu hidden-xs">

		<div class="table">
			<div class="tr">
				<c:forEach items="${navigationList[param.languageCode]}" var="navigation">
					<div class="td th">
						<a href="/${param.languageCode}/${navigation.url}"><img src="${navigation.titleIcon}" alt="" /><span class="hidden-xs hidden-sm catalogue"><c:out value="${navigation.title}" /></span></a>
					</div>
				</c:forEach>
				<div class="td th menuLast">

					<img src="/img/activemenu.png" alt="Menu">

				</div>
			</div>
			<div class="tr">
				<c:forEach items="${navigationList[param.languageCode]}" var="navigation">
					<div class="td">
						<ul class="no-list">
							<c:forEach items="${navigation.navigations}" var="secondaryNav" varStatus="loop">
								<li><a href="/${param.languageCode}/${secondaryNav.url}">${secondaryNav.title}</a></li>

							</c:forEach>
						</ul>
						<div class="footerColor"></div>
					</div>

				</c:forEach>
				<div class="td">
					<div class="footerColor"></div>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- popup header login -->
<div class="modal fade header-modal" id="login_modal" tabindex="-1" role="dialog" aria-labelledby="login_modalLabel">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="header-modal-wrap">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="<spring:message code='btn.close'/>">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<form:form name="login-form" id="login-form" action="#" commandName="logincommand" method="post" autocomplete="off" onsubmit="return validateLoginForm()">
						<div class="row">
							<div class="col-md-8 col-md-offset-4">
								<h4 class="modal-title col-md-offset-4" id="modal-lbl-head">
									<spring:message code="header.login" />
								</h4>
								<label class="login-error-msg" id="login_error_msg" style="display:none"> </label>
								<div class="row">
									<div class="col-xs-12 col-sm-6 col-md-5">
										<div class="form-group">
											<c:set var="phUsername"><spring:message code='header.username'/></c:set>
											<form:input type="text" class="modal-txt required" autocomplete="off" id="username" path="loginUsername" placeholder="${phUsername}" />
										</div>
									</div>
									<div class="col-xs-12 col-sm-6 col-md-5">
										<div class="form-group">
											<c:set var="phPassword"><spring:message code='header.password'/></c:set>
											<form:password path="loginPassword" class="modal-txt required" id="password"  placeholder="${phPassword}" />
											<form:hidden path="loginStatus" id="loginStatus" autocomplete="off"/>
										</div>
									</div>
									<div class="col-xs-12 col-sm-6 col-md-2">
										<c:set var="errorMessage"><spring:message code='label.something.wrong'/></c:set>
										<c:set var="errorLoginEmpty"><spring:message code='label.login.empty'/></c:set>
										<div class="login-loading" style="display:none;"><img src="/img/loginLoader.gif"> </div>	
										<input type="submit" class="popup-btn" id="user_login" data-error-service-msg="${errorMessage}" data-error-empty-msg="${errorLoginEmpty}" value='<spring:message code="header.login" />' />
									</div>
								</div>
								<div class="row">
									<div class="col-md-12">
										<div class="form-group pull-left">
											<a href="/${param.languageCode}/forgetusername.html" class="modal-head-link"><spring:message code="header.forget.username" /></a>
										</div>
										<div class="form-group pull-left">
											<a href="/${param.languageCode}/forgetpassword.html" class="modal-head-link"><spring:message code="header.forget.forgotpassword" /></a>
										</div>
										<div class="form-group pull-left">
											<a href="/${param.languageCode}/registrationlanding.html" class="modal-head-link"><spring:message code="header.forget.register" /></a>
										</div>
										<div class="form-group pull-left">
											<a href="/${param.languageCode}/accountactivation.html" class="modal-head-link"><spring:message code="header.forget.activateAccount"/></a>
										</div>
									</div>
								</div>
							</div>
						</div>
					</form:form>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- /popup header login  -->

<!-- popup header search -->
<div class="modal fade header-modal" id="search_modal" tabindex="-1" role="dialog" aria-labelledby="search_modalLabel">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="header-modal-wrap">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="<spring:message code='btn.close'/>">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<form:form id="header-search" commandName="searchCommand" name="headersearch" action="/${param.languageCode}/search.html" method="post">
						<div class="row">
							<div class="col-md-6 col-md-offset-6">
								<h4 class="modal-title col-md-offset-6" id="modal-lbl-head">
									<spring:message code='search.msg' />
								</h4>
								<div class="row">
									<div class="col-xs-12 col-sm-12 col-md-12">
										<div class="form-group">
											<c:set var="searchPlaceholder">
												<spring:message code='label.search' />
											</c:set>
											<form:input path="keyword" class="topsearch" id="search2" name="search" placeholder="${searchPlaceholder}" /> 
											<span id="headerSearch" class="glyphicon2 glyphicon-search"></span>
										</div>
									</div>
								</div>
							</div>
						</div>
					</form:form>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- /popup header search  -->

<!-- popup header rating -->

<div class="modal fade header-modal" id="rate_modal" tabindex="-1" role="dialog" aria-labelledby="rate_modalLabel">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="header-modal-wrap">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="<spring:message code='btn.close'/>">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<form>
						<div class="row">
							<div class="col-md-6 col-md-offset-6">
								<div class="row">
									<div class="col-xs-12">
										<div class="happinessIndicator">
										    <h4 class="modal-title col-md-offset-6" id="model_smile_question" data-smile-question="<spring:message code='smiley.question'/>" data-smile-callback-sucess="<spring:message code='smiley.success'/>" data-smile-callback-error="<spring:message code='duplicate.session'/>">
												<spring:message code="smiley.question" />																								
											</h4>											
											<a href="#" class="smile" data-smile-val="happy" data-smile="/${param.languageCode}/smiley.html"><img src='/img/icons/01.png' class="img-responsive img1" alt="" title="" /> <img
												src='/img/icons/01a.png' class="img-responsive img2" alt="" title="" /></a> 
											<a href="#" class="smile" data-smile-val="ok" data-smile="/${param.languageCode}/smiley.html"><img
												src='/img/icons/02.png' class="img-responsive img1" alt="" title="" /> <img src='/img/icons/02a.png' class="img-responsive img2" alt="" title="" /></a> 
											<a href="#" class="smile"
												data-smile-val="sad" data-smile="/${param.languageCode}/smiley.html"><img src='/img/icons/03.png' class="img-responsive img1" alt="" title="" /> <img src='/img/icons/03a.png'
												class="img-responsive img2" alt="" title="" /></a>																		
										</div>
									</div>
								</div>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</div>

<!-- /popup header rating  -->
<!-- popup header rating sucess -->
<div class="modal fade header-modal" id="rate_modal_success"
	tabindex="-1" role="dialog" aria-labelledby="rate_modalLabel">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="header-modal-wrap">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<div class="row">
						<div class="col-md-12">
							<div class="row">
								<div class="col-xs-12">
									<div class="happinessIndicator-feedback">
										<h4 class="modal-title col-md-offset-6" id="modal-lbl-head-feedback"></h4>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- /popup header rating succcess  -->
		<!-- popup header Profile -->
		<div class="modal fade header-modal" id="profileModal" tabindex="-1" role="dialog" aria-labelledby="profile_modalLabel">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="header-modal-wrap">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
						</div>
						<div class="modal-body">
							<div class="row">
								<div class="col-md-12"  >
									
									<div class="profile-settings">
										<h4 class="modal-title col-md-offset-6" ><spring:message code="label.welcome"/> </h4>
										<h2 class="modal-title col-md-offset-6" id="welcome_name" >${UserName}</h2>
										<div class="profile-group ">
											<a href="/${param.languageCode}/userprofile.html" id="welcome_profile_url" class="modal-head-link ">
												<span class="welcom-icons"><img src="/img/profile.png" alt="My Profile"/></span><label class="hidden-xs" id="profile_name">${UserName}</label> 
											</a>
										</div>
										<div class="profile-group ">
											<a href="/${param.languageCode}/myrequest.html" id="welcome_request_url" class="modal-head-link ">
												<span class="welcom-icons"> <img src="/img/myrequest.png" alt="My Request"/></span><label class="hidden-xs"><spring:message code="label.myrequest"/></label> 
											</a>
										</div>
										<div class="profile-group ">
											<a href="/${param.languageCode}/paymenthistory.html" id="welcome_request_url" class="modal-head-link ">
												<span class="welcom-icons"> <img src="/img/paymenthistory.png" alt="My Request"/></span><label class="hidden-xs"><spring:message code="label.paymenthistory"/></label> 
											</a>
										</div>
										<div class="profile-group">
											<a href="/${param.languageCode}/logout.html" id="welcome_logout_url" class="modal-head-link">
												<span class="welcom-icons"> <img src="/img/logout.png" alt="Logout" /></span><label class="hidden-xs"><spring:message code="label.logout"/></label> 
											</a>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
<!-- /popup header Profile  -->