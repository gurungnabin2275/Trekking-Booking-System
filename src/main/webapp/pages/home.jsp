<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    if (session.getAttribute("loggedUser") == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>Home - NepalHikeHub</title>
</head>
<body>
    <h2>Welcome, <%= session.getAttribute("userName") %>!</h2>
    <a href="TrekServlet">Browse Treks</a> |
    <a href="BookingServlet">My Bookings</a> |
    <a href="LogoutServlet">Logout</a>

    <h3>Explore Our Treks</h3>
    <p><a href="TrekServlet">Click here to see all available treks</a></p>
</body>
</html>