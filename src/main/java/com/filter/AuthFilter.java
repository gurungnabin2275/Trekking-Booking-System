package com.filter;

import com.utilities.SessionUtil;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebFilter(urlPatterns = {
    "/home.jsp",
    "/TrekServlet",
    "/BookingServlet",
    "/myBookings.jsp",
    "/trekDetail.jsp",
    "/treks.jsp"
})
public class AuthFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest  req  = (HttpServletRequest)  request;
        HttpServletResponse resp = (HttpServletResponse) response;

        if (SessionUtil.isLoggedIn(req)) {
            // User is logged in — let them through
            chain.doFilter(request, response);
        } else {
            // Not logged in — redirect to login
            resp.sendRedirect(req.getContextPath() + "/LoginServlet");
        }
    }

    @Override public void init(FilterConfig filterConfig) {}
    @Override public void destroy() {}
}