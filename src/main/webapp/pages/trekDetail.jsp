<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List, com.model.Trek, com.model.Route, com.model.Review" %>
<%
    if (session.getAttribute("loggedUser") == null) {
        response.sendRedirect("login.jsp");
        return;
    }
    Trek trek        = (Trek)        request.getAttribute("trek");
    List<Route>  routes  = (List<Route>)  request.getAttribute("routes");
    List<Review> reviews = (List<Review>) request.getAttribute("reviews");
%>
<!DOCTYPE html>
<html>
<head><title><%= trek.getTrekName() %> - NepalHikeHub</title></head>
<body>
    <a href="TrekServlet">Back to Treks</a>
    <h2><%= trek.getTrekName() %></h2>
    <p>Region: <%= trek.getRegion() %> | Difficulty: <%= trek.getDifficulty() %></p>
    <p>Duration: <%= trek.getDurationDays() %> days | Distance: <%= trek.getDistanceKm() %> km</p>
    <p>Price per person: $<%= trek.getPrice() %></p>
    <p><%= trek.getDescription() %></p>

    <h3>Route Waypoints</h3>
    <table border="1" cellpadding="8">
        <tr><th>#</th><th>Waypoint</th><th>Altitude (m)</th><th>Notes</th></tr>
        <% if (routes != null) { for (Route r : routes) { %>
        <tr>
            <td><%= r.getWaypointOrder() %></td>
            <td><%= r.getWaypointName() %></td>
            <td><%= r.getAltitudeM() %></td>
            <td><%= r.getNotes() %></td>
        </tr>
        <% } } %>
    </table>

    <h3>Book This Trek</h3>
    <% if (request.getAttribute("error") != null) { %>
        <p style="color:red;"><%= request.getAttribute("error") %></p>
    <% } %>
    <form action="BookingServlet" method="post">
        <input type="hidden" name="trekId" value="<%= trek.getTrekId() %>" />

        <label>Trek Start Date:</label><br>
        <input type="date" name="trekStartDate" required /><br><br>

        <label>Number of Persons:</label><br>
        <input type="number" name="numPersons" min="1" max="<%= trek.getMaxGroupSize() %>" value="1" required /><br><br>

        <label>Special Requests:</label><br>
        <textarea name="specialRequests" rows="3" cols="40"></textarea><br><br>

        <input type="submit" value="Book Now" />
    </form>

    <h3>Reviews</h3>
    <% if (reviews != null && !reviews.isEmpty()) {
        for (Review rv : reviews) { %>
        <div style="border:1px solid #ddd; margin:8px; padding:8px;">
            <p>Rating: <%= rv.getRating() %>/5</p>
            <p><%= rv.getComment() %></p>
            <small><%= rv.getReviewDate() %></small>
        </div>
    <% } } else { %>
        <p>No reviews yet for this trek.</p>
    <% } %>
</body>
</html>