/**
 * 
 */
package com.uaq.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.fatwire.rest.beans.AssetBean;
import com.fatwire.rest.beans.AssetInfo;
import com.fatwire.rest.beans.AssetsBean;
import com.fatwire.rest.beans.Attribute;
import com.fatwire.wem.sso.SSOException;
import com.uaq.common.AssetUtil;
import com.uaq.exception.UAQException;
import com.uaq.vo.FaqVO;

/**
 * @author Akhil
 * 
 */

@Service("faqService")
public class FAQService implements BaseService<FaqVO, List<FaqVO>> {

	@Override
	public List<FaqVO> execute(FaqVO faqVO) throws UAQException {

		List<FaqVO> faqList = null;

		try {
			AssetsBean faqAssetBean = AssetUtil.searchAssetbyDefinition("Content_C", "FAQ", "uaq", faqVO.getTicketId());
			List<AssetInfo> assetInfoList = faqAssetBean.getAssetinfos();
			faqList = getFAQList(assetInfoList, faqVO);
		} catch (SSOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return faqList;
	}

	private List<FaqVO> getFAQList(List<AssetInfo> assetInfoList, FaqVO faqVOObject) throws SSOException {

		List<FaqVO> faqList = new ArrayList<FaqVO>();
		FaqVO faqVO = null;

		for (AssetInfo assetinfo : assetInfoList) {
			faqVO = new FaqVO();
			String assetId = assetinfo.getId().split(":")[1];
			AssetBean assetBean = AssetUtil.getAssetDetail("uaq", "Content_C", assetId, faqVOObject.getTicketId());
			faqVO.setLanguage(assetBean.getDimensions().get(0).getName());
			for (Attribute attributes : assetBean.getAttributes()) {
				if (attributes.getName().equalsIgnoreCase("QuestionText")) {
					faqVO.setQuestionText(attributes.getData().getStringValue());
				}
				if (attributes.getName().equalsIgnoreCase("AnswerText")) {
					faqVO.setAnswerText(attributes.getData().getStringValue());
				}
			}
			faqList.add(faqVO);
		}

		return faqList;
	}

	@Override
	public List<FaqVO> executeSites(FaqVO faqVO) throws UAQException {
		// TODO Auto-generated method stub
		return null;
	}

}
