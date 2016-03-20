package com.mod.financial.view.managed.common;

import com.mod.financial.view.utils.ADFUtils;

import com.mod.financial.view.utils.JSFUtils;

import com.mod.financial.view.utils.QueueSend;
import com.mod.financial.view.utils.UserInfo;

import java.io.IOException;

import javax.faces.context.FacesContext;

import javax.faces.event.PhaseEvent;

import javax.jms.JMSException;

import javax.naming.InitialContext;

import javax.naming.NamingException;

import javax.security.auth.Subject;
import javax.security.auth.login.FailedLoginException;
import javax.security.auth.login.LoginException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpSession;

import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

import oracle.jbo.domain.Number;

import weblogic.security.URLCallbackHandler;
import weblogic.security.services.Authentication;

public class Login {
    private String lang;

    public Login() {
        lang = "ar" ;
    }

    public String login_action() {
        String result =
            (String)ADFUtils.findOperation("InitLoginProcedure").execute();
        int errorCode =
            Integer.parseInt(result.substring(result.indexOf("=") + 1,
                                              result.indexOf(",")));
        String errorMessage = result.substring(result.lastIndexOf("=") + 1);
        if (errorCode != 0) {
            JSFUtils.addFacesErrorMessage(errorMessage);
            return null;
        } else {
//            HttpSession session =
//                (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true);
            ADFUtils.findOperation("ExecuteWithParams").execute();
           String password = (String) JSFUtils.resolveExpression("#{bindings.pwd.inputValue}");
           String username = (String) JSFUtils.resolveExpression("#{bindings.LogName.inputValue}");
           byte[] pw = password.getBytes();
           FacesContext ctx = FacesContext.getCurrentInstance();
           HttpServletRequest request = (HttpServletRequest)ctx.getExternalContext().getRequest();
            try{
                Subject subject = Authentication.login(new URLCallbackHandler(username, pw));
                weblogic.servlet.security.ServletAuthentication.runAs(subject, request);
                UserInfo userInfo =
                    (UserInfo)JSFUtils.resolveExpression("#{userInfo}");
                userInfo.setUserName(username);
                userInfo.setName((String)JSFUtils.resolveExpression("#{bindings.Name.inputValue}"));
                userInfo.setUserId((Number)JSFUtils.resolveExpression("#{bindings.Id.inputValue}"));
                userInfo.setLanguage((String)JSFUtils.resolveExpression("#{bindings.Language.inputValue}"));
                userInfo.setThemeName((String)JSFUtils.resolveExpression("#{bindings.Theme.inputValue}"));
                userInfo.setOrgId((Number)JSFUtils.resolveExpression("#{bindings.OrgId.inputValue}"));
                userInfo.setDeptNo((Number)JSFUtils.resolveExpression("#{bindings.DeptNo.inputValue}"));
                userInfo.setLanguage(lang);
                userInfo.changeLocal();
            }
            catch (LoginException le) {
                        le.printStackTrace();
                    }

            return "home";
        }

    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getLang() {
        return lang;
    }

    public String sendClientMessageAction() {
        QueueSend qs = new QueueSend();
        InitialContext ic;

        try {
            ic = QueueSend.getInitialContext("t3://127.0.0.1:7105");
            qs.init(ic, qs.QUEUE);
            qs.readAndSend(qs);
            qs.close();
        } catch (NamingException e) {
            e.printStackTrace();
        } catch (JMSException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
