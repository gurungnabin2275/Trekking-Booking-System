<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List, com.model.Trek, com.model.User, com.model.Booking" %>
<%
    if (session.getAttribute("roleId") == null || (int)session.getAttribute("roleId") != 1) {
        response.sendRedirect(request.getContextPath() + "/LoginServlet");
        return;
    }
    List<Trek>    treks    = (List<Trek>)    request.getAttribute("treks");
%>
<!DOCTYPE html>
<html>
<head><title>Admin Dashboard - NepalHikeHub</title></head>
<body>
    <h2>Admin Dashboard</h2>
    <p>Welcome, <%= session.getAttribute("userName") %>
    | <a href="<%= request.getContextPath() %>/LogoutServlet">Logout</a></p>
    <hr>

    <h3>Manage Treks</h3>
    <a href="<%= request.getContextPath() %>/admin/manageTreks.jsp">View / Add / Edit / Delete Treks</a>
    <br><br>

    <h3>Manage Users</h3>
    <a href="<%= request.getContextPath() %>/admin/manageUsers.jsp">View / Approve / Delete Users</a>
    <br><br>

    <h3>Manage Bookings</h3>
    <a href="<%= request.getContextPath() %>/admin/manageBookings.jsp">View / Confirm / Reject Bookings</a>
</body>
</html>