package com.uaq.controller;

import static com.uaq.common.ApplicationConstants.PAGE_LABEL;
import static com.uaq.common.ApplicationConstants.SESSION_LOGIN_INFO_PORTAL;
import static com.uaq.common.ApplicationConstants.UAQ_URL;
import static com.uaq.common.ApplicationConstants.URL_SEPARATOR;
import static com.uaq.common.UAQURLConstant.MY_REQUEST_URL;
import static com.uaq.payment.PaymentConstants.PAYMENT_METHOD_TYPE_GII_CO_BRANDED_MAG_STRIPE_CARD;
import static com.uaq.payment.PaymentConstants.PAYMENT_METHOD_TYPE_GII_PLC_CHIP_CARD;
import static com.uaq.payment.PaymentConstants.PAYMENT_METHOD_TYPE_GII_PLC_Mag_Stripe_Card;
import static com.uaq.payment.PaymentConstants.PAYMENT_METHOD_TYPE_MASTERCARD;
import static com.uaq.payment.PaymentConstants.PAYMENT_METHOD_TYPE_VISA;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.uaq.common.PaymentSessionUtil;
import com.uaq.common.PropertiesUtil;
import com.uaq.common.ViewPath;
import com.uaq.dao.DAOManager;
import com.uaq.logger.UAQLogger;
import com.uaq.payment.InquiryPaymentResponse;
import com.uaq.payment.PaymentStatus;
import com.uaq.service.PaymentServiceManager;
import com.uaq.service.PortalUtil;
import com.uaq.service.PurchaseService;
import com.uaq.vo.LoginOutputVO;
import com.uaq.vo.PageMetadataVO;

@Controller
public class PaymentDetailController extends BaseController {

	private static transient UAQLogger logger = new UAQLogger(PaymentDetailController.class);

	@Autowired
	private PortalUtil portalUtil;

	private static final String ERROR_PAGE = "error.page";
	private static final String ERROR_PAGE_MOBILE = "error.page.mobile";
	private static final String PAYMENT_STATUS_SUCCESS = "success";
	static final String PAYMENT_CONFIRMATION_PAGE = "payment_confirmation.jsp";

	PaymentServiceManager paymentServiceManager = new PaymentServiceManager();
	PurchaseService purchaseService = new PurchaseService();

	@RequestMapping(value = ViewPath.PAYMENT_DETAIL, method = RequestMethod.GET)
	public String handleRequest(HttpServletRequest request, HttpServletResponse response, ModelMap model) {

		logger.enter("handleRequest POST");

		super.handleRequest(request, model);

		InquiryPaymentResponse inquiryPaymentResponse = null;
		PaymentStatus paymentStatus = null;
		String status = PAYMENT_STATUS_SUCCESS;

		boolean isMobile = portalUtil.isMobile(request, response);

		String languageCode = request.getSession().getAttribute("languageCode") != null ? (String) request.getSession().getAttribute("languageCode") : "en";
		Locale locale = new Locale(languageCode);
		
		
		String message = messageSource.getMessage("purchase.payment.success", null, locale);
		String pageLabel ="purchase.payment.review";
		String view = (isMobile == false) ? "login.again" : "login.again.mobile";
		LoginOutputVO logininfo = (LoginOutputVO) request.getSession().getAttribute(SESSION_LOGIN_INFO_PORTAL);
			if (logininfo != null) {
				view = (isMobile == false) ? "payment.confirmation" : "payment.confirmation.mobile";
				request.getSession().setAttribute("isMobile", isMobile);
				DAOManager daoManager = new DAOManager();
				try {
					Connection con = daoManager.getConnection();
					String purchaseId = request.getParameter("purchaseId");
					logger.debug("purchaseId = " + purchaseId);

					if (purchaseId != null && !purchaseId.isEmpty()) {

						// get payment details such as requestId, serviceId,
						// requestNo, statusId
						paymentStatus = purchaseService.getPaymentInfo(purchaseId,con);
						logger.debug("updateApplicantRequestTableStatus :" + paymentStatus.toString());

						pageLabel = PaymentSessionUtil.getPageLable(paymentStatus.getServiceId());
						
						String serviceName= messageSource.getMessage(pageLabel, null, locale);
						model.addAttribute("ServiceName", serviceName);
						
					/*	returnUrl = returnUrl + "&statusId=" + paymentStatus.getStatusId() 
								  + "&serviceId=" + paymentStatus.getServiceId() 
								  + "&requestId=" + paymentStatus.getRequestId()
								  + "&requestNo=" + paymentStatus.getRequestNo();

						logger.debug("returnUrl : " + returnUrl);				

						request.getSession().setAttribute("returnUrl", returnUrl);*/

						inquiryPaymentResponse = purchaseService.getSuccessfulPaymentTransaction(purchaseId,con);

						inquiryPaymentResponse.setPaymentMethodType(getPaymentMethodTypeDescription(inquiryPaymentResponse.getPaymentMethodType(), languageCode));

						logger.debug("inquiryPaymentResponse payment details :" + inquiryPaymentResponse);
						
						model.addAttribute("generalFee",purchaseService.getGeneralFee(con));
					}
					daoManager.commit();
				} catch (Exception e) {
					daoManager.rollback();
					logger.error(e.getMessage());
					message = messageSource.getMessage("purchase.payment.trans.fail", null, locale);
				}finally{
					daoManager.closeConnection();
				}

				logger.debug("status = " + status + " : message = " + message);

				if (inquiryPaymentResponse == null) {
					view = (isMobile == false) ? ERROR_PAGE : ERROR_PAGE_MOBILE;
				}

			}
			model.addAttribute("inquiryPaymentResponse", inquiryPaymentResponse);
			model.addAttribute("status", status);
			model.addAttribute("message", message);
			model.addAttribute("myRequestUrl",PropertiesUtil.getProperty(UAQ_URL) + URL_SEPARATOR + languageCode + URL_SEPARATOR + MY_REQUEST_URL);
			PageMetadataVO pageMetadataVO = new PageMetadataVO();
			pageMetadataVO.setPageTitle(messageSource.getMessage(pageLabel, null, locale));
			pageMetadataVO.setPageDescription(messageSource.getMessage(pageLabel, null, locale));
			pageMetadataVO.setPageKeywords(messageSource.getMessage(pageLabel, null, locale));
			model.addAttribute("pageMetadata", pageMetadataVO);
			/*model.addAttribute("returnUrl", returnUrl);*/
		

		
		
		model.addAttribute(PAGE_LABEL, pageLabel);
		logger.exit("handleRequest POST");

		return view;
	}

	/**
	 * This method is used to create payment method type description according
	 * to the language
	 * 
	 * @param paymentMethodType
	 * @param languageCode
	 * @return
	 */
	private String getPaymentMethodTypeDescription(String paymentMethodType, String languageCode) throws UnsupportedEncodingException {

		logger.enter("getPaymentMethodTypeDescription : paymentMethodType = " + paymentMethodType + " : languageCode = " + languageCode);

		String result = null;

		if (paymentMethodType != null && languageCode != null) {

			Locale locale = new Locale(languageCode);

			// in response, we get the second part of the payment method type
			// e.g 1 instead of 1-1, so we have to split and take second
			// part(start index is 0)
			if (paymentMethodType.equals(PAYMENT_METHOD_TYPE_VISA.split("-")[1])) {
				result = messageSource.getMessage("payment.method.type.visa", null, locale);
			} else if (paymentMethodType.equals(PAYMENT_METHOD_TYPE_MASTERCARD.split("-")[1])) {
				result = messageSource.getMessage("payment.method.type.mastercard", null, locale);
			} else if (paymentMethodType.equals(PAYMENT_METHOD_TYPE_GII_PLC_Mag_Stripe_Card.split("-")[1])) {
				result = messageSource.getMessage("payment.method.type.red", null, locale);
			} else if (paymentMethodType.equals(PAYMENT_METHOD_TYPE_GII_CO_BRANDED_MAG_STRIPE_CARD.split("-")[1])) {
				result = messageSource.getMessage("payment.method.type.blue", null, locale);
			} else if (paymentMethodType.equals(PAYMENT_METHOD_TYPE_GII_PLC_CHIP_CARD.split("-")[1])) {
				result = messageSource.getMessage("payment.method.type.gold", null, locale);
			}
		}

		logger.exit("getPaymentMethodTypeDescription : result = " + result);

		return result;
	}

}
