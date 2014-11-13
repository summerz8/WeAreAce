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
public class AuthenticationFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        Boolean isLogin = (Boolean) req.getSession().getAttribute("isLogin");
        Long HFactoryId = (Long) req.getSession().getAttribute("HFactoryId");
        Long HStoreId = (Long) req.getSession().getAttribute("HStoreId");
        String url = req.getRequestURI();

        System.err.println("isLogin: " + isLogin);

        if (!(url.contains("/Factory/")&&!url.contains("/SCM/")&&!url.contains("MRP")) && HFactoryId != null) {
            req.getSession().removeAttribute("HFactoryId");
        }
        if (!(url.contains("/Store/ResourceControl")) && HStoreId != null) {
            req.getSession().removeAttribute("HStoreId");
        }
        if (url.contains("javax.faces.resource")) {
            chain.doFilter(request, response);
        }
        //check first time login or logged out
        if (url.contains("loginPage.xhtml")) {
            chain.doFilter(request, response);
        } else if (isLogin == null || !isLogin) {

            String contextPath = req.getServletContext().getContextPath();
            resp.sendRedirect(contextPath + "/loginPage.xhtml");

        } else {

            chain.doFilter(request, response);

        }

    }

    @Override
    public void destroy() {
    }

}
