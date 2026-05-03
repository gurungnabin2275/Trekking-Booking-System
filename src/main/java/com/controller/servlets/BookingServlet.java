package com.controller.servlets;

import com.dao.BookingDAOImpl;
import com.interfaces.BookingDAO;
import com.model.Booking;
import com.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

@WebServlet("/BookingServlet")
public class BookingServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Check login
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("loggedUser") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        User loggedUser = (User) session.getAttribute("loggedUser");

        int    trekId          = Integer.parseInt(request.getParameter("trekId"));
        String trekStartDate   = request.getParameter("trekStartDate");
        int    numPersons      = Integer.parseInt(request.getParameter("numPersons"));
        String specialRequests = request.getParameter("specialRequests");

        Booking booking = new Booking();
        booking.setUserId(loggedUser.getUserId());
        booking.setTrekId(trekId);
        booking.setBookingDate(new Date(System.currentTimeMillis())); // today
        booking.setTrekStartDate(Date.valueOf(trekStartDate));        // from form
        booking.setNumPersons(numPersons);
        booking.setSpecialRequests(specialRequests);
        booking.setStatus("pending");

        BookingDAO bookingDAO = new BookingDAOImpl();
        boolean success = bookingDAO.createBooking(booking);

        if (success) {
            request.setAttribute("success", "Booking submitted! Wait for admin confirmation.");
            request.getRequestDispatcher("myBookings.jsp").forward(request, response);
        } else {
            request.setAttribute("error", "Booking failed. Please try again.");
            response.sendRedirect("TrekServlet?action=detail&trekId=" + trekId);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Show bookings of logged in user
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("loggedUser") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        User loggedUser = (User) session.getAttribute("loggedUser");
        BookingDAO bookingDAO = new BookingDAOImpl();
        List<Booking> bookings = bookingDAO.getBookingsByUser(loggedUser.getUserId());

        request.setAttribute("bookings", bookings);
        request.getRequestDispatcher("myBookings.jsp").forward(request, response);
    }
}