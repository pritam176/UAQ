package com.uaq.common;

public class ViewPath {

	/********** Payment Mappings******* */
	public static final String PAYMENT_REVIEW = "/uaq/paymentReview.html";
	public static final String PAYMENT_CONFIRMATION = "/uaq/paymentConfirmation.html";
	public static final String PAYMENT_AUTOUPDATE = "/uaq/autoUpdatePayments.html";	
	public static final String PAYMENT_DETAIL = "/uaq/paymentDetail.html";
	
	public static final String PAGE_NOT_FOUND = "/{site}/page-not-found.html";

	// Events
	public static final String EVENTS_DETAIL = "/{site}/events/{name}.html";	
	public static final String EVENTS_DETAIL_SITES = "/{site}/{landing}/events/{name}.html";
	public static final String EVENTS_LISTING = "/{site}/events.html";
	public static final String EVENTS_MEDIAROOM = "/{site}/{landing}/{parent}/events.html";
	
	
	public static final String EVENTS_SITES_LISTING = "/{site}/{landing}/events.html";
	public static final String EVENTS_MEDIAROOM_DETAILS = "/{site}/{parent}/{landing}/events/{name}.html";

	// Awards
	
	public static final String AWARDS_LISTING = "/{site}/{landing}/{parent}/awards.html";
	public static final String AWARDS_SITES_LISTING = "/{site}/{landing}/awards-and-achievements.html";
		

	public static final String AWARDS_DETAIL = "/{site}/award/{name}.html";
	public static final String AWARDS_DETAIL_SITES = "/{site}/{parent}/award/{name}.html";
	
	// Certificates
	
		
	public static final String CERTIFICATES_SITES_LISTING = "/{site}/{landing}/certificates.html";
			
	public static final String CERTIFICATES_DETAIL_SITES = "/{site}/{parent}/certificate/{name}.html";
	
	// Reports
	
	
	public static final String REPORTS_SITES_LISTING = "/{site}/{landing}/reports.html";
				
	public static final String REPORTS_DETAIL_SITES = "/{site}/{parent}/report/{name}.html";
	
	// Projects
	public static final String PROJECTS_LISTING = "/{site}/projects.html";

	public static final String PROJECTS_LISTING_SITES = "/{site}/{landing}/projects.html";

	public static final String PROJECTS_DETAIL = "/{site}/project/{name}.html";

	public static final String PROJECTS_DETAIL_SITES = "/{site}/{landing}/project/{name}.html";
	
	// People
	public static final String PEOPLE_LISTING = "/{site}/{landing}/our-people.html";

	//Portal Services
	public static final String SERVICES_DETAIL = "/{site}/services/{sector}/{name}.html";

	public static final String SERVICES_CATALOG = "/{site}/services/servicecatalog.html";
	public static final String SERVICES_CATALOG_PORTAL = "/uaq/servicecatalog.html";
	public static final String SERVICES_SITES = "/{site}/our-activities/services.html";

	// Departments
	public static final String DEPARTMENTS_LISTING = "/uaq/{landing}/department.html";

	public static final String DEPARTMENT_DETAIL = "/uaq/{landing}/department/{name}.html";

	// Home
	public static final String PORTAL_HOME = "/uaq/home.html";

	// Landing Page
	public static final String LANDING_PAGE = "/uaq/{pageName}.html";

	// SiteMap
	public static final String SITEMAP = "/{site}/content/SiteMap.html";

	// News
	public static final String NEWS_DETAIL = "/{site}/news/{name}.html";

	public static final String NEWS_LISTING = "/{site}/news.html";

	public static final String NEWS_LISTING_SITES = "/{site}/{landing}/news.html";

	public static final String NEWS_DETAIL_SITES = "/{site}/{landing}/news/{name}.html";
	
	public static final String NEWS_AND_ANNOUNCEMENT_LISTING_SITES = "/{site}/{landing}/news-and-announcements.html";

	// Publication
	public static final String PUBLICATION_DETAIL = "/{site}/{landing}/publication/{name}.html";

	public static final String PUBLICATION_LISTING_SITES = "/{site}/{landing}/publications.html";

	public static final String GENERIC_CONTENT_PAGE = "/{site}/content/{pageName}.html";

	public static final String IMAGE_GALLERY = "/{sites}/{parent}/{landing}/imagegallery.html";
	
	public static final String DETAIL_PAGE = "/uaq/{landing}/{pageName}.html";

	public static final String FEEDBACK = "/{site}/feedback.html";

	public static final String CAPTCHA = "/{site}/captcha.html";
	
	public static final String IMAGE_GALLERY_SITES = "/{sites}/{landing}/image-gallery.html";

	public static final String VIDEO_GALLERY = "/{site}/{parent}/{landing}/videogallery.html";
	
	public static final String VIDEO_GALLERY_SITES = "/{site}/{landing}/video-gallery.html";

	//public static final String VIDEO_GALLERY_MEDIAROOM = "/{site}/{landing}/VideoGallery.html";

	public static final String SEARCH = "/{site}/search.html";

	/* public static final String SMILEY = "/uaq/smiley.html"; */

	// Career Generic Page
	public static final String CAREER_LISTING = "/{site}/careers.html";

	public static final String CAREER_DETAILS = "/{site}/careers/{name}.html";

	// FAQ
	public static final String FAQ_LISTING = "/uaq/faq.html";

	// Detail Page
	public static final String DETAIL_DETAIL_PAGE = "/uaq/{parentPage}/{landing}/{pageName}.html";

	/************* Department URL Mappings ***************/

	public static final String SITES_HOME_PAGE = "/{sites}/home.html";

	//public static final String SITES_LANDING_PAGE = "/{sites}/{pageName}.html";

	public static final String FLUSH_CACHE = "/uaq/flushCache.html";

	public static final String SITES_DETAIL_PAGE = "/{sites}/{landing}/{pageName}.html";
	
	public static final String SITES_DETAIL_PAGE_TMP = "/{sites}/{pageName}.html";

	public static final String SITES_SERVICES_LIST = "/{site}/services.html";

	public static final String SITES_SERVICES_DETAIL = "/{site}/{parent}/service/{name}.html";

	/******* Land and Property URL Mappings ********/
	/*
	 * public static final String LOAD_LAND_PROPERTY_PAGE =
	 * "/uaq/loadpage.html";
	 * 
	 * public static final String LAND_PROPERTY_SERVICE =
	 * "/uaq/landandproperty.html";
	 *//********* New procard URL Mappings *******/
	/*
	 * public static final String NEW_PROCARD_SERVICE = "/uaq/newprocard.html";
	 * 
	 * public static final String NEW_PROCARD_SAVE = "/uaq/newprocardsave.html";
	 */

	/******* e Service Department(PS,PWS,LP,EGD) URL Mappings ********/

	public static final String LAND_PROPERTY_VALUTION = "/uaq/landandpropertyvalution.html";

	public static final String ISSUE_NEW_PROCARD_SERVICE = "/uaq/issuenewprocard.html";

	public static final String LAND_DEMARCATION_REQUEST = "/uaq/landdemarcationrequest.html";

	public static final String ISSUE_SITE_PLAN_DOCUMENT = "/uaq/sitePlanDocumentPage.html";

	public static final String ADD_LAND_REQUEST = "/uaq/addlandrequest.html";

	public static final String EGD_NEW_SUPPLIER_REGISTRATION_PAGE = "/uaq/newSupplierRegistrationPage.html";

	public static final String EGD_RENEW_SUPPLIER_REGISTRATION_PAGE = "/uaq/reNewSupplierRegistrationPage.html";

	public static final String LP_RENEW_REAL_ESTATE_PAGE = "/uaq/renewRealestatePage.html";

	public static final String PWS_WASTE_CONTAINER_REQUEST = "/uaq/wastecontainer.html";

	public static final String EXTENSION_GRAND_LAND_REQUEST = "/uaq/extensiongrandLand.html";

	public static final String NEW_GRANTLANDREQ = "/uaq/grantlandrequest.html";
	
	public static final String PAYMENT_HISTORY ="/uaq/paymenthistory.html";
	
	
	/******* URL for based on Service status From MyRequest ********/

	public static final String MYREQUEST = "/uaq/myrequest.html";

	public static final String MY_REQUEST_RESUBMIT = "/uaq/myrequestresubmit.html";
	
	public static final String MY_REQUEST_REVIEW = "/uaq/myrequestreview.html";

	public static final String ISSUE_SITE_PLAN ="/uaq/issuesiteplan.html";
	
	
	//PSAfterServicePaymentController URL 
	public static final String TO_ISSUE_SITE_PLAN ="/uaq/toissuesiteplan.html";
	
	public static final String DELIVER ="/uaq/delever.html";
	
	public static final String SUBMIT_NOC = "/uaq/submitnoc.html";
	
	public static final String SUBMIT_LICENSE = "/uaq/submitlicense.html";
	
	public static final String THANKYOU_PAYMENT="/uaq/thankyoupayment.html";
	
	public static final String THANKYOU_PAGE ="/uaq/thankyou.html";
	
	
	public static final String THANKYOU_PATMENT_PAGE ="/uaq/thankyoupayment.html";
	
	//EGDAfterServicePaymentController URL 
	public static final String INITIATE_ACTIVATE_ACCOUNT ="/uaq/initiateaccountactivate.html";

	/******* Registration URL Mappings ********/
	

	public static final String ACTIVATE_FORM = "/uaq/activateform.html";

	public static final String ACCOUNT_ACTIVATION = "/uaq/accountactivation.html";
	
	public static final String ACCOUNT_ACTIVATION_SUCCESS = "/uaq/accountactivationsuccess.html";

	public static final String VALIDATE_OTP_PAGE = "/uaq/otp.html";

	public static final String GENERATE_OTP = "/uaq/generateotp.html";

	public static final String FORGET_PASSWORD = "/uaq/forgetpassword.html";
	
	public static final String CHANGE_PASSWORD = "/uaq/changePassword.html";
	
	public static final String FORGET_USERNAME = "/uaq/forgetusername.html";

	public static final String USER_LOGIN = "/uaq/login.html";

	public static final String USER_LOGOUT = "/uaq/logout.html";
	
	public static final String USER_PROFILE="/uaq/userprofile.html";
	
	public static final String REGISTRATION_ESTABLISHMENT = "/uaq/establishmentregister.html";
	
	public static final String REGISTRATION_GCC_CITIZEN = "/uaq/gcccitizenregister.html";
	
	public static final String REGISTRATION_GCC_RESIDENT = "/uaq/gccresidentregister.html";
	
	public static final String REGISTRATION_UAE_RESIDENT = "/uaq/uaeresidentregister.html";
	
	public static final String REGISTRATION_UAE_CITIZEN = "/uaq/uaecitzenregister.html";
	
	public static final String REGISTRATION_INDIVIDUAL_VISITOR = "/uaq/visitorregister.html";
	
	public static final String REGISTRATION_LANDING="/uaq/registrationlanding.html";
	
	public static final String REGISTRATION_TC="/uaq/tearmscondition.html";
	
	/******* Registration URL Mappings ENDS ********/

	public static final String SMILEY = "/{site}/smiley.html";

	/********** LP Department URL Mappings******* */
	public static final String RENEW_PROCARD_SERVICE = "/uaq/renewprocard.html";

	public static final String RENEW_REALESTATE_SERVICE = "/uaq/renewrealestate.html";
	
	public static final String ISSUE_SITEPLAN_DOC_REQUEST = "/uaq/issuesiteplandoc.html";

	public static final String NEW_REALESTATE_SERVICE = "/uaq/newrealestate.html";
	
	
	

	public static final String SITES_POLLS = "/{site}/{landing}/polls.html";
	public static final String SITES_SURVEY = "/{site}/{landing}/surveys.html";
	
	public static final String CONTACT_US = "/{site}/contactus.html";
	
	public static final String RSS_FEEDS = "/{site}/{category}/rssFeeds.html";
	
	public static final String RSS_FEEDS_PAGE = "/{site}/rssFeeds.html";
	
	/********** Resubmit Mappings******* */
	
	public static final String RESUBMIT_ADD_LAND = "/uaq/resubmitaddlandrequest.html";
	
	public static final String RESUBMIT_ISSUE_SITE_PLAN_DOC = "/uaq/resubmitsitePlanDocumentPage.html";
	
	public static final String RESUBMIT_WASTECONTAINER = "/uaq/resubmitwastecontainer.html";
	
	public static final String RESUBMIT_EXTENSION_GRAND_LAND_REQUEST = "/uaq/resubmitextensiongrandLand.html";
	
	public static final String PROCED_TO_PAYMENT ="/uaq/proceedpayment.html";

	public static final String RESUBMIT_LAND_DEMARCATION_REQUEST = "/uaq/resubmitlanddemarcationrequest.html";
	
	public static final String RESUBMIT_GRAND_LAND_REQUEST="/uaq/resubmitgrantlandrequest.html";
	
	public static final String RESUBMIT_NEW_SUPPLIER_REGISTRATION = "/uaq/resubmitnewSupplierRegistrationPage.html";
	
	public static final String RESUBMIT_RE_NEW_SUPPLIER_REGISTRATION = "/uaq/resubmitreNewSupplierRegistrationPage.html";
	
	
	
	public static final String WHOM_IT_MAY_CONCERN = "/uaq/whomitmayconcern.html";
	public static final String RESUBMIT_WHOM_IT_MAY_CONCERN = "/uaq/resubmitwhomitmayconcern.html";
	
	public static final String COMING_SOON = "/uaq/comingsoon.html";
	
	
	public static final String SERVICES_ERROR_PAGE ="/uaq/serviceerrorpage.html";
	
	public static final String FUNERAL = "/uaq/funeral.html";
	public static final String FUNERAL_LOGIN = "/uaq/funeralLogin.html";
	public static final String FUNERAL_LOGOUT = "/uaq/funeralLogout.html";
	
	public static final String SHOW_SERVICE_PHASE_PAGE ="/uaq/showServicePhase.html";
	public static final String SUBMIT_SERVICE_PARAMS = "/uaq/submitSevice.html";
	public static final String INITIATE_SERVICE_REQUEST = "/uaq/initiateSevice.html";
	public static final String SERVICE_PAYMENT_COMPLETE = "/uaq/paymentCompleted.html";
}
