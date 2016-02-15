package com.uaq.flexfliter;

import COM.FutureTense.Interfaces.FTValList;
import COM.FutureTense.Interfaces.IList;

import com.openmarket.basic.interfaces.AssetException;
import com.openmarket.gator.flexfilters.AbstractFlexFilter;
import com.openmarket.gator.interfaces.IFilterEnvironment;
import com.openmarket.gator.interfaces.IFilterableAssetInstance;

public class ParentCategoryFilter extends AbstractFlexFilter {

	public ParentCategoryFilter(FTValList ftvallist) {
		super(ftvallist);
		System.out.println("ParentCategory Constructor: " + ftvallist);
	}

	@Override
	public void filterAsset(IFilterEnvironment env, String identifier, FTValList ftValList, IFilterableAssetInstance instance) throws AssetException {
		try {
			// list
			String attributeId = getAttrID(env, ftValList, "INPUT ATTRIBUTE EN");
			StringBuffer string = new StringBuffer("select STRINGVALUE from content_p_mungo  where cs_ownerid in(");
			string.append(" select inherited from content_C_amap where ownerid=");
			string.append(instance.getAssetID());
			string.append(" and attributeid=");
			string.append(attributeId);
			string.append(") and cs_attrid=");
			string.append(attributeId);
			IList ilist = env.getICS().SQL("content_p_mungo", string.toString(), "enAttr", 1, false, new StringBuffer("Error"));
			String categoryValue = ilist.getValue("stringvalue");
			instance.addDerivedDataValue(identifier, env.getAttributeIdentifier(ftValList.getValString("OUTPUT ATTRIBUTE EN")), categoryValue);

			attributeId = getAttrID(env, ftValList, "INPUT ATTRIBUTE AR");
			string = new StringBuffer("select STRINGVALUE from content_p_mungo  where cs_ownerid in(");
			string.append(" select inherited from content_C_amap where ownerid=");
			string.append(instance.getAssetID());
			string.append(" and attributeid=");
			string.append(attributeId);
			string.append(") and cs_attrid=");
			string.append(attributeId);
			ilist = env.getICS().SQL("content_p_mungo", string.toString(), "enAttr", 1, false, new StringBuffer("Error"));
			categoryValue = ilist.getValue("stringvalue");
			instance.addDerivedDataValue(identifier, env.getAttributeIdentifier(ftValList.getValString("OUTPUT ATTRIBUTE AR")), categoryValue);

		} catch (Exception e) {
			System.out.println("Exception " + e.getMessage());
		}
	}

	@Override
	public FTValList getLegalArguments(IFilterEnvironment arg0, String arg1) throws AssetException {
		FTValList ftVal = new FTValList();
		ftVal.put("INPUT ATTRIBUTE EN", "INPUT ATTRIBUTE EN");
		ftVal.put("INPUT ATTRIBUTE AR", "INPUT ATTRIBUTE AR");
		ftVal.put("OUTPUT ATTRIBUTE EN", "OUTPUT ATTRIBUTE EN");
		ftVal.put("OUTPUT ATTRIBUTE AR", "OUTPUT ATTRIBUTE AR");
		return ftVal;
	}
}
