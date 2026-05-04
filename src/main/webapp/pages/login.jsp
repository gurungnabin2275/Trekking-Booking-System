<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Login - NepalHikeHub</title>
</head>
<body>
    <h2>Login</h2>

    <% if (request.getAttribute("error") != null) { %>
        <p style="color:red;"><%= request.getAttribute("error") %></p>
    <% } %>
    <% if (request.getAttribute("success") != null) { %>
        <p style="color:green;"><%= request.getAttribute("success") %></p>
    <% } %>

    <form action="LoginServlet" method="post">
        <label>Email:</label><br>
        <input type="email" name="email" required /><br><br>

        <label>Password:</label><br>
        <input type="password" name="password" required /><br><br>

        <input type="submit" value="Login" />
    </form>

    <p>Don't have an account? <a href="RegisterServlet">Register here</a></p>
</body>
</html>