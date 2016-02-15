package com.uaq.util;

import java.util.Locale;

import org.springframework.context.MessageSource;

import com.uaq.vo.PageMetadataVO;

public class MetaDataUtil {

	public static PageMetadataVO getPageMetaData(String pageName, String languageCode, MessageSource messageSource) {

		Locale locale = new Locale(languageCode);
		PageMetadataVO pageMetadata = new PageMetadataVO();

		String pageTitle = messageSource.getMessage(pageName.concat(".page.title"), null, locale);
		String pageKeywords = messageSource.getMessage(pageName.concat(".page.keywords"), null, locale);
		String pageDescription = messageSource.getMessage(pageName.concat(".page.description"), null, locale);

		pageMetadata.setPageTitle(pageTitle);
		pageMetadata.setPageKeywords(pageKeywords);
		pageMetadata.setPageDescription(pageDescription);

		return pageMetadata;
	}

}
