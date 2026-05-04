<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Register - NepalHikeHub</title>
</head>
<body>
    <h2>Register</h2>

    <% if (request.getAttribute("error") != null) { %>
        <p style="color:red;"><%= request.getAttribute("error") %></p>
    <% } %>

    <form action="RegisterServlet" method="post">
        <label>Name:</label><br>
        <input type="text" name="name" required /><br><br>

        <label>Email:</label><br>
        <input type="email" name="email" required /><br><br>

        <label>Password:</label><br>
        <input type="password" name="password" required /><br><br>

        <label>Phone:</label><br>
        <input type="text" name="phone" /><br><br>

        <input type="submit" value="Register" />
    </form>

    <p>Already have an account? <a href="LoginServlet">Login here</a></p>
</body>
</html>