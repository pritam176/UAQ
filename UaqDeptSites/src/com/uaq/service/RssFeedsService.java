package com.uaq.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.fatwire.wem.sso.SSOException;
import com.sun.syndication.feed.synd.SyndCategory;
import com.sun.syndication.feed.synd.SyndCategoryImpl;
import com.sun.syndication.feed.synd.SyndContent;
import com.sun.syndication.feed.synd.SyndContentImpl;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndEntryImpl;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.feed.synd.SyndFeedImpl;
import com.uaq.command.SearchCommand;
import com.uaq.common.PropertiesUtil;
import com.uaq.exception.UAQException;
import com.uaq.vo.EventsSearchResponseVO;
import com.uaq.vo.EventsVO;
import com.uaq.vo.NavigationVO;
import com.uaq.vo.NewsVO;
import com.uaq.vo.SearchResponseVO;

/**
 * BaseService class for Rss Feeds. It calls other services to gather feeds
 * 
 * @author mraheem
 * 
 */
@Service(value = "rssFeedsService")
public class RssFeedsService implements BaseService<SearchCommand, SyndFeed> {

	@Autowired
	@Qualifier("newsService")
	private BaseService<NavigationVO, SearchResponseVO> newsService;
	
	@Autowired
	@Qualifier("eventsService")
	private BaseService<EventsVO, EventsVO> eventsService;
	
	@Override
	public SyndFeed execute(SearchCommand searchCommand) throws UAQException {

		SearchResponseVO searchResponseVO = null;
		List<NewsVO> newsList = null;
		List<EventsVO> eventsList = null;
		SyndFeed feed = null;
		int pageSize = Integer.valueOf(PropertiesUtil.getProperty("rss.feeds.pagesize"));

		searchCommand.setPageSize(pageSize);
		searchCommand.setCurrentPage(1);
		searchCommand.setStartRow(0);
		
		String category = searchCommand.getCategory();

		try {
			
			if (searchCommand.getCategory().equalsIgnoreCase("news")) {	
				searchCommand.setCategory(null);
				searchResponseVO = ((NewsService) newsService).getNewsList(searchCommand, "uaq");
				searchCommand.setCategory(category);				
				if (null != searchResponseVO && searchResponseVO.getSearchResult() != null && searchResponseVO.getSearchResult().size() > 0) {
					newsList = searchResponseVO.getSearchResult();
					feed = createRssFeedsNews(newsList, searchCommand);
				}					
			} else if (searchCommand.getCategory().equalsIgnoreCase("events")) {
				searchCommand.setCategory(null);
				searchResponseVO = ((EventsService) eventsService).getEventsList(searchCommand, "uaq");	
				searchCommand.setCategory(category);
				if (null != searchResponseVO && ((EventsSearchResponseVO)searchResponseVO).getSearchResults() != null && ((EventsSearchResponseVO)searchResponseVO).getSearchResults().size() > 0) {
					eventsList = ((EventsSearchResponseVO)searchResponseVO).getSearchResults();
					feed = createRssFeedsEvents(eventsList, searchCommand);
				}				
			} 
		} catch (SSOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return feed;
	}

	public SyndFeed createRssFeedsNews(List<NewsVO> newsList, SearchCommand searchCommand) {

		List<SyndEntry> entries = new ArrayList<SyndEntry>();
		SyndEntry entry = null;
		SyndContent description = null;
		List<SyndCategory> categories = null;
		SyndCategory category = null;
		
		String URL_SEPARATOR = "/";
		String HMTL = ".html";

		String strCategory = searchCommand.getCategory();
		String url = PropertiesUtil.getProperty("uaq.url") + URL_SEPARATOR + searchCommand.getLanguage() + URL_SEPARATOR
				+ searchCommand.getCategory() + "/rssFeeds.html";

		// crate RSS Feed
		SyndFeed feed = new SyndFeedImpl();
		feed.setFeedType("rss_2.0");
		feed.setTitle("UAQ" + " | " + strCategory);
		feed.setLink(url);
		feed.setDescription("UAQ" + " | " + "UAQ News RSS Feed");

		// Create Entries
		for (int i = 0; i < newsList.size(); i++) {
			// Create Entry
			entry = new SyndEntryImpl();
			String detailURL = PropertiesUtil.getProperty("uaq.url") + URL_SEPARATOR + searchCommand.getLanguage() + URL_SEPARATOR + searchCommand.getCategory() + URL_SEPARATOR + newsList.get(i).getUrl() + HMTL;
			//String imageURL = PropertiesUtil.getProperty("uaq.url") + URL_SEPARATOR + searchCommand.getLanguage() + URL_SEPARATOR + searchCommand.getCategory() + URL_SEPARATOR + newsList.get(i).getTeaserImage() + HMTL;
			entry.setTitle(newsList.get(i).getTitle());			
			entry.setLink(detailURL);
			//entry.setPublishedDate(newsList.get(i).getDate());
			
			/*List<SyndContent> contents = new ArrayList<SyndContent>(); 
			SyndContent con = new SyndContentImpl();
			
			con.setValue(imageURL);
			contents.add(con);			
			entry.setContents(contents);*/

			// Create entry description
			description = new SyndContentImpl();
			description.setType("text/html");
			description.setValue(newsList.get(i).getTeaserText());
			entry.setDescription(description);
			entry.setPublishedDate(Calendar.getInstance().getTime());

			// Create optional category
			categories = new ArrayList<SyndCategory>();
			category = new SyndCategoryImpl();
			category.setName(strCategory);
			categories.add(category);
			entry.setCategories(categories);

			entries.add(i, entry);
		}

		feed.setEntries(entries);

		return feed;
	}
	
	public SyndFeed createRssFeedsEvents(List<EventsVO> eventsList, SearchCommand searchCommand) {

		List<SyndEntry> entries = new ArrayList<SyndEntry>();
		SyndEntry entry = null;
		SyndContent description = null;
		List<SyndCategory> categories = null;
		SyndCategory category = null;
		
		String URL_SEPARATOR = "/";
		String HMTL = ".html";

		String strCategory = searchCommand.getCategory();
		String url = PropertiesUtil.getProperty("uaq.url") + URL_SEPARATOR + searchCommand.getLanguage() + URL_SEPARATOR
				+ searchCommand.getCategory() + "/rssFeeds.html";
		
		// crate RSS Feed
		SyndFeed feed = new SyndFeedImpl();
		feed.setFeedType("rss_2.0");
		feed.setTitle("UAQ" + " | " + strCategory);
		feed.setLink(url);
		feed.setDescription("UAQ" + " | " + "UAQ Events RSS Feed");

		// Create Entries
		for (int i = 0; i < eventsList.size(); i++) {
			// Create Entry
			entry = new SyndEntryImpl();
			String detailURL = PropertiesUtil.getProperty("uaq.url") + URL_SEPARATOR + searchCommand.getLanguage() + URL_SEPARATOR + searchCommand.getCategory() + URL_SEPARATOR + eventsList.get(i).getUrl() + HMTL;
			entry.setTitle(eventsList.get(i).getTitle());	
			
			/*List<SyndContent> contents = new ArrayList<SyndContent>(); 
			SyndContent con = new SyndContentImpl();
			con.setValue("testing...");
			contents.add(con);			
			entry.setContents(contents);*/
			
			entry.setLink(detailURL);
			entry.setPublishedDate(Calendar.getInstance().getTime());

			// Create entry description
			description = new SyndContentImpl();
			description.setType("text/html");
			description.setValue(eventsList.get(i).getTeaserText());
			entry.setDescription(description);

			// Create optional category
			categories = new ArrayList<SyndCategory>();
			category = new SyndCategoryImpl();
			category.setName(strCategory);
			categories.add(category);
			entry.setCategories(categories);

			entries.add(i, entry);
		}

		feed.setEntries(entries);

		return feed;
	}

	@Override
	public SyndFeed executeSites(SearchCommand inputObject) throws UAQException {
		// TODO Auto-generated method stub
		return null;
	}
}
