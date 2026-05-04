package com.controller.servlets;

import com.utilities.SessionUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Clear remember me cookie
        Cookie cookie = new Cookie("rememberedEmail", "");
        cookie.setMaxAge(0);
        response.addCookie(cookie);

        // Destroy session
        SessionUtil.destroySession(request);

        response.sendRedirect("LoginServlet");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}