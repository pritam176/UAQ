package com.mod.financial.view.utils;

import java.io.IOException;
import java.io.PrintWriter;

import java.util.Enumeration;

import javax.servlet.*;
import javax.servlet.http.*;

public class ReportServletAction extends HttpServlet {
    private static final String CONTENT_TYPE = "text/html; charset=UTF-8";

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException,
                                                           IOException {
        response.setContentType(CONTENT_TYPE);
        getAllparams(request, response);
    }

    void getAllparams(HttpServletRequest request, HttpServletResponse response)throws ServletException,
                                                           IOException {
        Enumeration enumeration = request.getParameterNames();
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head><title>Report Test </title></head>");
        out.println("<body>");
        while (enumeration.hasMoreElements()) {
            String parameterName = (String)enumeration.nextElement();
            out.println("<p>" + parameterName +
                               "= " +
                               request.getParameter(parameterName) +"</p>");
          
        }
        
        out.println("</body></html>");
    }


}
