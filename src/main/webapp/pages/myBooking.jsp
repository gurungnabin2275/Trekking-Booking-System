<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List, com.model.Booking" %>
<%
    if (session.getAttribute("loggedUser") == null) {
        response.sendRedirect("login.jsp");
        return;
    }
    List<Booking> bookings = (List<Booking>) request.getAttribute("bookings");
%>
<!DOCTYPE html>
<html>
<head><title>My Bookings - NepalHikeHub</title></head>
<body>
    <h2>My Bookings</h2>
    <a href="home.jsp">Home</a> | <a href="LogoutServlet">Logout</a>
    <hr>

    <% if (request.getAttribute("success") != null) { %>
        <p style="color:green;"><%= request.getAttribute("success") %></p>
    <% } %>

    <% if (bookings != null && !bookings.isEmpty()) { %>
    <table border="1" cellpadding="8">
        <tr>
            <th>Booking ID</th>
            <th>Trek ID</th>
            <th>Start Date</th>
            <th>Persons</th>
            <th>Special Requests</th>
            <th>Status</th>
        </tr>
        <% for (Booking b : bookings) { %>
        <tr>
            <td><%= b.getBookingId() %></td>
            <td><%= b.getTrekId() %></td>
            <td><%= b.getTrekStartDate() %></td>
            <td><%= b.getNumPersons() %></td>
            <td><%= b.getSpecialRequests() != null ? b.getSpecialRequests() : "-" %></td>
            <td><%= b.getStatus() %></td>
        </tr>
        <% } %>
    </table>
    <% } else { %>
        <p>You have no bookings yet. <a href="TrekServlet">Browse Treks</a></p>
    <% } %>
</body>
</html>