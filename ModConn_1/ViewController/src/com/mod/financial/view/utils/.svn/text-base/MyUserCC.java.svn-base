package com.mod.financial.view.utils;

import oracle.adf.share.ADFContext;
import oracle.adf.share.config.SiteCC;

import oracle.adf.share.security.SecurityContext;

import oracle.mds.config.CustClassListMapping;
import oracle.mds.config.CustConfig;
import oracle.mds.config.MDSConfigurationException;
import oracle.mds.core.MDSSession;
import oracle.mds.core.MetadataObject;
import oracle.mds.core.RestrictedSession;
import oracle.mds.core.SessionOptions;
import oracle.mds.cust.CacheHint;

public class MyUserCC extends SiteCC {
    private String mLayerName ;
    
    public MyUserCC() {
        mLayerName = "myLayer";
    }

    public String getMLayerName() {
        return mLayerName;
    }
    public CacheHint getCacheHint(){
        return CacheHint.ALL_USERS;
    }
    public String [] getValue (RestrictedSession ses ,MetadataObject mo ){
         ADFContext adfCtx = ADFContext.getCurrent();  
         SecurityContext secCntx = adfCtx.getSecurityContext();  
         String _user = secCntx.getUserName(); 
        return  new String []{_user};
       
    }
}
