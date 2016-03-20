package com.mod.financial.view.utils;

import java.io.IOException;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class Logout {
    public Logout() {
        super();
    }
    public String logout_action() {
        // Add event code here...
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        session.invalidate();
        ExternalContext ectx = FacesContext.getCurrentInstance().getExternalContext();
        HttpServletRequest request = (HttpServletRequest) ectx.getRequest();
        String temp = request.getContextPath() + "/faces/common/login.jspx";
        try {
            ectx.redirect(temp);
        } catch (IOException e) {
        }
        return "logout";
    }
}
