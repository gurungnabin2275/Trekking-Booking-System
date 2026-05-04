package com.controller.servlets;

import com.dao.UserDAOImpl;
import com.interfaces.UserDAO;
import com.model.User;
import com.utilities.PasswordUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("register.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String name            = request.getParameter("name");
        String email           = request.getParameter("email");
        String password        = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
        String phone           = request.getParameter("phone");

        // Validation
        if (name == null || name.trim().isEmpty()) {
            request.setAttribute("error", "Name is required.");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }
        if (email == null || !email.contains("@")) {
            request.setAttribute("error", "Valid email is required.");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }
        if (password == null || password.length() < 6) {
            request.setAttribute("error", "Password must be at least 6 characters.");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }
        if (!password.equals(confirmPassword)) {
            request.setAttribute("error", "Passwords do not match.");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }

        UserDAO userDAO = new UserDAOImpl();

        // Check duplicate email
        if (userDAO.getUserByEmail(email.trim()) != null) {
            request.setAttribute("error", "Email already registered. Please login.");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }

        // Build user with encrypted password
        User newUser = new User();
        newUser.setName(name.trim());
        newUser.setEmail(email.trim());
        newUser.setPasswordHash(PasswordUtil.encrypt(password)); // encrypted!
        newUser.setPhone(phone);
        newUser.setRoleId(2);
        newUser.setApproved(false);

        boolean success = userDAO.registerUser(newUser);

        if (success) {
            request.setAttribute("success", "Registration successful! Wait for admin approval to login.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        } else {
            request.setAttribute("error", "Registration failed. Please try again.");
            request.getRequestDispatcher("register.jsp").forward(request, response);
        }
    }
}