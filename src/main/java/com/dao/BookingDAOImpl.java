package com.dao;

import com.interfaces.BookingDAO;
import com.model.Booking;
import com.utilities.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookingDAOImpl implements BookingDAO {

    @Override
    public boolean createBooking(Booking booking) {
        String sql = "INSERT INTO bookings (user_id, trek_id, booking_date, trek_start_date, " +
                     "num_persons, special_requests, status) VALUES (?, ?, ?, ?, ?, ?, 'pending')";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, booking.getUserId());
            ps.setInt(2, booking.getTrekId());
            ps.setDate(3, booking.getBookingDate());
            ps.setDate(4, booking.getTrekStartDate());
            ps.setInt(5, booking.getNumPersons());
            ps.setString(6, booking.getSpecialRequests());

            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Booking> getBookingsByUser(int userId) {
        List<Booking> bookings = new ArrayList<>();
        String sql = "SELECT * FROM bookings WHERE user_id = ? ORDER BY booking_date DESC";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Booking b = new Booking();
                b.setBookingId(rs.getInt("booking_id"));
                b.setUserId(rs.getInt("user_id"));
                b.setTrekId(rs.getInt("trek_id"));
                b.setBookingDate(rs.getDate("booking_date"));
                b.setTrekStartDate(rs.getDate("trek_start_date"));
                b.setNumPersons(rs.getInt("num_persons"));
                b.setSpecialRequests(rs.getString("special_requests"));
                b.setStatus(rs.getString("status"));
                bookings.add(b);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bookings;
    }
    @Override
    public List<Booking> getAllBookings() {
        List<Booking> bookings = new ArrayList<>();
        String sql = "SELECT * FROM bookings ORDER BY booking_date DESC";
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Booking b = new Booking();
                b.setBookingId(rs.getInt("booking_id"));
                b.setUserId(rs.getInt("user_id"));
                b.setTrekId(rs.getInt("trek_id"));
                b.setBookingDate(rs.getDate("booking_date"));
                b.setTrekStartDate(rs.getDate("trek_start_date"));
                b.setNumPersons(rs.getInt("num_persons"));
                b.setSpecialRequests(rs.getString("special_requests"));
                b.setStatus(rs.getString("status"));
                bookings.add(b);
            }
        } catch (Exception e) { e.printStackTrace(); }
        return bookings;
    }

    @Override
    public boolean updateBookingStatus(int bookingId, String status) {
        String sql = "UPDATE bookings SET status = ? WHERE booking_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, status);
            ps.setInt(2, bookingId);
            return ps.executeUpdate() > 0;
        } catch (Exception e) { e.printStackTrace(); }
        return false;
    }
}