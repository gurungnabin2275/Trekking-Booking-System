package com.controller.servlets;

import com.dao.UserDAOImpl;
import com.interfaces.UserDAO;
import com.model.User;
import com.utilities.PasswordUtil;
import com.utilities.SessionUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // If already logged in, go to home
        if (SessionUtil.isLoggedIn(request)) {
            response.sendRedirect("home.jsp");
            return;
        }

        // Pre-fill email from cookie if Remember Me was used
        String rememberedEmail = "";
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie c : cookies) {
                if (c.getName().equals("rememberedEmail")) {
                    rememberedEmail = c.getValue();
                }
            }
        }
        request.setAttribute("rememberedEmail", rememberedEmail);
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email      = request.getParameter("email");
        String password   = request.getParameter("password");
        String rememberMe = request.getParameter("rememberMe");

        // Validation
        if (email == null || email.trim().isEmpty()) {
            request.setAttribute("error", "Email is required.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }
        if (password == null || password.trim().isEmpty()) {
            request.setAttribute("error", "Password is required.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }

        UserDAO userDAO = new UserDAOImpl();
        User user = userDAO.getUserByEmail(email.trim());

        if (user == null) {
            request.setAttribute("error", "No account found with that email.");
            request.getRequestDispatcher("login.jsp").forward(request, response);

        } else if (!PasswordUtil.verify(password, user.getPasswordHash())) {
            request.setAttribute("error", "Incorrect password.");
            request.getRequestDispatcher("login.jsp").forward(request, response);

        } else if (!user.isApproved()) {
            request.setAttribute("error", "Your account is pending admin approval.");
            request.getRequestDispatcher("login.jsp").forward(request, response);

        } else {
            // Create session
            SessionUtil.createSession(request, user);

            // Handle Remember Me cookie
            if ("on".equals(rememberMe)) {
                Cookie emailCookie = new Cookie("rememberedEmail", email);
                emailCookie.setMaxAge(7 * 24 * 60 * 60); // 7 days
                response.addCookie(emailCookie);
            } else {
                // Clear cookie if not checked
                Cookie emailCookie = new Cookie("rememberedEmail", "");
                emailCookie.setMaxAge(0);
                response.addCookie(emailCookie);
            }

            // Redirect by role
            if (user.getRoleId() == 1) {
                response.sendRedirect("admin/dashboard.jsp");
            } else {
                response.sendRedirect("home.jsp");
            }
        }
    }
}