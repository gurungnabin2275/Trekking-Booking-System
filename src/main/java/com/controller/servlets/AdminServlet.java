package com.controller.servlets;

import com.dao.UserDAOImpl;
import com.dao.TrekDAOImpl;
import com.dao.BookingDAOImpl;
import com.interfaces.UserDAO;
import com.interfaces.TrekDAO;
import com.interfaces.BookingDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/admin/dashboard")
public class AdminServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        TrekDAO    trekDAO    = new TrekDAOImpl();
        BookingDAO bookingDAO = new BookingDAOImpl();

        request.setAttribute("treks",    trekDAO.getAllActiveTreks());
        request.getRequestDispatcher("/admin/dashboard.jsp").forward(request, response);
    }
}