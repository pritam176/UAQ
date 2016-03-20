package com.mod.financial.view.utils;
import java.util.Locale;

import javax.faces.context.FacesContext;

import oracle.adf.share.ADFContext;

import oracle.jbo.domain.Number;

public class UserInfo {
    private String userName;
    private String name;
    private Number userId;
    private String photoUrl;
    private String themeName = "mod1";
    private String themeVersion;
    private String language;
    private Number orgId;
    private Number deptNo;
    private String firstAttempt = "N";


    public UserInfo() {
        super();
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserId(Number userId) {
        this.userId = userId;
    }

    public Number getUserId() {
        return userId;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setThemeName(String themeName) {
        this.themeName = themeName;
    }

    public String getThemeName() {
        if (themeName == null || themeName.equals("")) {
            themeName = "fusionFx";
        }
        return themeName;
    }

    public void setThemeVersion(String themeVersion) {
        this.themeVersion = themeVersion;
    }

    public String getThemeVersion() {
        if (themeVersion == null) {
            themeVersion = "v1.2";
        }
        return themeVersion;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getLanguage() {
        if (language == null || language.equals("")) {
            language = "ar";
        }
        return language;
    }

    public String changeLocal() {
        Locale newLocale = new Locale(getLanguage());
        FacesContext context = FacesContext.getCurrentInstance();
        context.getViewRoot().setLocale(newLocale);
        return null;
    }

    public void setFirstAttempt(String firstAttempt) {
        this.firstAttempt = firstAttempt;
    }

    public String getFirstAttempt() {
        return firstAttempt;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setOrgId(Number orgId) {
        this.orgId = orgId;
    }

    public Number getOrgId() {
        return orgId;
    }

    public void setDeptNo(Number deptNo) {
        this.deptNo = deptNo;
    }

    public Number getDeptNo() {
        return deptNo;
    }
}
