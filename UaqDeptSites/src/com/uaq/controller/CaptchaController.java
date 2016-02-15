package com.uaq.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.octo.captcha.service.CaptchaServiceException;
import com.octo.captcha.service.image.ImageCaptchaService;
import com.octo.captcha.service.multitype.GenericManageableCaptchaService;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import com.uaq.common.ViewPath;
import com.uaq.logger.UAQLogger;

/**
 * Controller class to render captcha images.
 * 
 * @author nsharma
 * 
 */
@Controller
@RequestMapping(value = ViewPath.CAPTCHA)
public class CaptchaController {

	@Autowired
	private GenericManageableCaptchaService captchaService;

	private static transient UAQLogger logger = new UAQLogger(CaptchaController.class);

	/**
	 * Creates the captcha Image and writes it to the output stream.
	 * 
	 * @param request
	 *            implicit http request object.
	 * @param response
	 *            implicit http response object.
	 * @throws Exception
	 */
	@RequestMapping(method = RequestMethod.GET)
	public void showCaptchaImage(HttpServletRequest request, HttpServletResponse response) throws Exception {

		byte[] captchaChallengeAsJpeg = null;
		// the output stream to render the captcha image as jpeg into
		ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();
		try {
			// get the session id that will identify the generated captcha.
			// the same id must be used to validate the response, the session id
			// is a good candidate!

			String captchaId = request.getSession().getId();
			logger.debug("Captcha ID which gave the image::" + captchaId);
			// call the ImageCaptchaService getChallenge method
			BufferedImage challenge = ((ImageCaptchaService) getCaptchaService()).getImageChallengeForID(captchaId, request.getLocale());

			// a jpeg encoder
			JPEGImageEncoder jpegEncoder = JPEGCodec.createJPEGEncoder(jpegOutputStream);
			jpegEncoder.encode(challenge);
		} catch (IllegalArgumentException e) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
		} catch (CaptchaServiceException e) {
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}

		captchaChallengeAsJpeg = jpegOutputStream.toByteArray();

		// flush it in the response
		response.setHeader("Cache-Control", "no-store");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setContentType("image/jpeg");
		// response.getOutputStream().write(jpegOutputStream);
		ServletOutputStream responseOutputStream = response.getOutputStream();
		responseOutputStream.write(captchaChallengeAsJpeg);
		responseOutputStream.flush();
		responseOutputStream.close();
	}

	/**
	 * @return the captchaService
	 */
	public GenericManageableCaptchaService getCaptchaService() {
		return captchaService;
	}

	/**
	 * @param captchaService
	 *            the captchaService to set
	 */
	public void setCaptchaService(GenericManageableCaptchaService captchaService) {
		this.captchaService = captchaService;
	}
}
