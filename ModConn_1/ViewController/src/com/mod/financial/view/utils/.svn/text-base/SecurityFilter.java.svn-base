package com.mod.financial.view.utils;

import java.io.IOException;

import java.util.ResourceBundle;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SecurityFilter implements Filter {
    private FilterConfig _filterConfig = null;

    public void init(FilterConfig filterConfig) throws ServletException {
        _filterConfig = filterConfig;
    }

    public void destroy() {
        _filterConfig = null;
    }

    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException,
                                                   ServletException {
        HttpServletRequest req = (HttpServletRequest)request;
        HttpServletResponse res = (HttpServletResponse)response;
        HttpSession session = req.getSession(true);
        String requstedURI = req.getRequestURI() + "";
        if (req.getRequestURI().contains("login")) {
            if (session.getAttribute("userInfo") != null) {
                UserInfo userInfo = (UserInfo)session.getAttribute("userInfo");
                String userName = userInfo.getUserName();
                if (userName != null && !"".equals(userName) &&
                    !"null".equals(userName)) {
                    res.sendRedirect(session.getServletContext().getContextPath() +
                                     "/faces/common/Home.jspx");
                } else
                    chain.doFilter(request, response);
            } else
                chain.doFilter(request, response);
        } else {
            if (session.getAttribute("userInfo") != null) {
                UserInfo userInfo = (UserInfo)session.getAttribute("userInfo");
                String userName = userInfo.getUserName();
                if (userName != null && !"".equals(userName) &&
                    !"null".equals(userName)) {
                    chain.doFilter(request, response);
                } else {
                    if (requstedURI.contains("/images") ||
                        requstedURI.contains("/css") ||
                        requstedURI.contains("/js/")) {
                        chain.doFilter(request, response);
                    } else {
                        if (!res.isCommitted()) {
                            res.sendRedirect(session.getServletContext().getContextPath() +
                                             "/faces/common/login.jspx");
                        }
                    }
                }
            } else {
                res.sendRedirect(session.getServletContext().getContextPath() +
                                 "/faces/common/login.jspx");
            }
        }
    }
}
