<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List, com.model.Trek" %>
<%
    if (session.getAttribute("loggedUser") == null) {
        response.sendRedirect("login.jsp");
        return;
    }
    List<Trek> treks = (List<Trek>) request.getAttribute("treks");
%>
<!DOCTYPE html>
<html>
<head><title>All Treks - NepalHikeHub</title></head>
<body>
    <h2>Available Treks</h2>
    <a href="home.jsp">Home</a> | <a href="LogoutServlet">Logout</a>
    <hr>

    <% if (treks != null && !treks.isEmpty()) {
        for (Trek t : treks) { %>
        <div style="border:1px solid #ccc; margin:10px; padding:10px;">
            <h3><%= t.getTrekName() %></h3>
            <p>Region: <%= t.getRegion() %> | Difficulty: <%= t.getDifficulty() %></p>
            <p>Duration: <%= t.getDurationDays() %> days | Price: $<%= t.getPrice() %></p>
            <p><%= t.getDescription() %></p>
            <a href="TrekServlet?action=detail&trekId=<%= t.getTrekId() %>">View Details & Book</a>
        </div>
    <% } } else { %>
        <p>No treks available at the moment.</p>
    <% } %>
</body>
</html>