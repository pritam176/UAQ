package com.uaq.controller;

import static com.uaq.common.ApplicationConstants.SESSION_TICKET;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedOutput;
import com.uaq.command.SearchCommand;
import com.uaq.common.PropertiesUtil;
import com.uaq.common.ViewPath;
import com.uaq.exception.UAQException;
import com.uaq.logger.UAQLogger;
import com.uaq.service.RssFeedsService;


/**
 * This controller is used for Contact Us page.
 * 
 * @author mraheem
 * 
 */
@Controller
public class RssFeedsController extends BaseController {

	@Autowired
	@Qualifier("rssFeedsService")
	RssFeedsService rssFeedsService;

	private static transient UAQLogger logger = new UAQLogger(RssFeedsController.class);

	/**
	 * 
	 */
	@RequestMapping(value = ViewPath.RSS_FEEDS_PAGE, method = RequestMethod.GET)
	public String handleRequest(HttpServletRequest request, ModelMap model) {

		logger.enter("handle Request in RssFeedsController");

		String view = "rss.feeds.page";
		String languageCode = request.getParameter("languageCode");

		super.handleRequest(request, model);

		//model.addAttribute("pageMetadata", MetaDataUtil.getPageMetaData(view, languageCode, messageSource));

		logger.exit("exiting RssFeedsController");

		return view;
	}
	
	/**
	 * creates RSS Feeds for particular category and deliver
	 * 
	 * @param category
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping(value = ViewPath.RSS_FEEDS, method = RequestMethod.GET)
	public void getRssFeeds(@PathVariable("site") String site, @PathVariable("category") String category, HttpServletRequest request, HttpServletResponse response, ModelMap model) {

		logger.enter("handle Request in RssFeedsController");
		SyndFeed rssFeed = null;

		SearchCommand newsSearchCommand = new SearchCommand();
		String language = request.getParameter("languageCode");
		newsSearchCommand.setCategory(category); // news, events
		newsSearchCommand.setLanguage(language);
		newsSearchCommand.setSite(PropertiesUtil.getProperty(site + "_" + "csSiteName"));
		newsSearchCommand.setTicketId((String) request.getSession().getAttribute(SESSION_TICKET));

		try {
			rssFeed = rssFeedsService.execute(newsSearchCommand);
			response.setContentType("application/xml;charset=UTF-8");
			SyndFeedOutput output = new SyndFeedOutput();
			output.output(rssFeed, response.getWriter());

		} catch (UAQException e) {
			logger.error(e.getMessage());
		} catch (IOException e) {
			logger.error(e.getMessage());
		} catch (FeedException e) {
			logger.error(e.getMessage());
		}

		logger.exit("exiting RssFeedsController");

	}
	
	
}