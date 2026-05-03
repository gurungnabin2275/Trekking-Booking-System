package com.controller.servlets;

import com.dao.UserDAOImpl;
import com.interfaces.UserDAO;
import com.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email    = request.getParameter("email");
        String password = request.getParameter("password");

        UserDAO userDAO = new UserDAOImpl();
        User user = userDAO.getUserByEmail(email);

        if (user == null) {
            request.setAttribute("error", "No account found with that email.");
            request.getRequestDispatcher("login.jsp").forward(request, response);

        } else if (!user.getPasswordHash().equals(password)) {
            request.setAttribute("error", "Incorrect password.");
            request.getRequestDispatcher("login.jsp").forward(request, response);

        } else if (!user.isApproved()) {
            request.setAttribute("error", "Your account is pending admin approval.");
            request.getRequestDispatcher("login.jsp").forward(request, response);

        } else {
            HttpSession session = request.getSession();
            session.setAttribute("loggedUser", user);
            session.setAttribute("userId",   user.getUserId());
            session.setAttribute("userName", user.getName());
            session.setAttribute("roleId",   user.getRoleId());

            if (user.getRoleId() == 1) {
                response.sendRedirect("admin/dashboard.jsp");
            } else {
                response.sendRedirect("home.jsp");
            }
        }
    }
}