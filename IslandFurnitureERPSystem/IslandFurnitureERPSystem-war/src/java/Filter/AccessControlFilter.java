/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author dan
 */
public class AccessControlFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        int userLevel = (Integer) req.getSession().getAttribute("Userlvl");
        String url = req.getRequestURI();
        Long HFactoryId = (Long) req.getSession().getAttribute("HFactoryId");

        System.err.println("AccessControl: isLogin: " + userLevel);

        if (url.contains("javax.faces.resource")) {
            chain.doFilter(request, response);
        }
        //check first time login or logged out
        if (url.contains("loginPage.xhtml")) {
            chain.doFilter(request, response);
        }

        if (userLevel == 0) {
            if (url.contains("/Factory/")) {
                if (url.contains("/SCM/")) {
                    if (url.contains("/DocumentReferenceModule/")) {
                        chain.doFilter(request, response);
                    } else {
                        String contextPath = req.getServletContext().getContextPath();
                        resp.sendRedirect(contextPath + "/ErrorPage.xhtml");
                    }
                } else if (url.contains("/MRP/")) {
                    chain.doFilter(request, response);
                } else if (url.contains("FactoryResourceControl.xhtml") && HFactoryId == null) {
                    String contextPath = req.getServletContext().getContextPath();
                    resp.sendRedirect(contextPath + "/secured/restricted/Factory/FactoryResourceControlForHQ.xhtml");
                } else {
                    chain.doFilter(request, response);
                }
            } else {
                chain.doFilter(request, response);
            }

        } else if (userLevel == 1 && url.contains("/Factory/")) {
            chain.doFilter(request, response);

        } else if (userLevel == 2 && url.contains("/Store/")) {

            chain.doFilter(request, response);
        } else if (userLevel == 3 && url.contains("/SCM/")) {
            chain.doFilter(request, response);
        } else if (userLevel == 4 && url.contains("/MRP/")) {
            chain.doFilter(request, response);
        } else if (userLevel == 5 && url.contains("/KM/")) {
            chain.doFilter(request, response);
        } else if (userLevel == 6 && (url.contains("/OCRM/") || url.contains("/ACRM/"))) {
            chain.doFilter(request, response);
        } else if (userLevel == 7 && url.contains("/ticket/")) {
            chain.doFilter(request, response);
        } else {
            String contextPath = req.getServletContext().getContextPath();
            resp.sendRedirect(contextPath + "/ErrorPage.xhtml");

        }
    }

    @Override
    public void destroy() {

    }

}
