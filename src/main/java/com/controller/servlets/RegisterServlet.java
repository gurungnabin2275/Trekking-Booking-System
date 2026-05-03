package com.controller.servlets;

import com.dao.UserDAOImpl;
import com.interfaces.UserDAO;
import com.model.User;

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

        String name     = request.getParameter("name");
        String email    = request.getParameter("email");
        String password = request.getParameter("password");
        String phone    = request.getParameter("phone");

        UserDAO userDAO = new UserDAOImpl();
        User existing = userDAO.getUserByEmail(email);

        if (existing != null) {
            request.setAttribute("error", "Email already registered. Please login.");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }

        User newUser = new User();
        newUser.setName(name);
        newUser.setEmail(email);
        newUser.setPasswordHash(password);
        newUser.setPhone(phone);
        newUser.setRoleId(2);
        newUser.setApproved(false);

        boolean success = userDAO.registerUser(newUser);

        if (success) {
            request.setAttribute("success", "Registration successful! Wait for admin approval.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        } else {
            request.setAttribute("error", "Registration failed. Please try again.");
            request.getRequestDispatcher("register.jsp").forward(request, response);
        }
    }
}