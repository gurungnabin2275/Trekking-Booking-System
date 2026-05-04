package com.dao;

import com.interfaces.PaymentDAO;
import com.model.Payment;
import com.utilities.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PaymentDAOImpl implements PaymentDAO {

    @Override
    public boolean addPayment(Payment payment) {
        String sql = "INSERT INTO payments (booking_id, amount, payment_method, payment_status, " +
                     "payment_date, transaction_ref) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, payment.getBookingId());
            ps.setDouble(2, payment.getAmount());
            ps.setString(3, payment.getPaymentMethod());
            ps.setString(4, payment.getPaymentStatus());
            ps.setDate(5, payment.getPaymentDate());
            ps.setString(6, payment.getTransactionRef());

            int rows = ps.executeUpdate();
            if (rows > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    payment.setPaymentId(rs.getInt(1));
                }
                return true;
            }
            return false;
        } catch (Exception e) {
            System.err.println("Add Payment Error: " + e.getMessage());
            return false;
        }
    }

    @Override
    public Payment getPaymentById(int paymentId) {
        String sql = "SELECT * FROM payments WHERE payment_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, paymentId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return extractPayment(rs);
            }
        } catch (Exception e) {
            System.err.println("Get Payment Error: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Payment> getPaymentsByBookingId(int bookingId) {
        List<Payment> payments = new ArrayList<>();
        String sql = "SELECT * FROM payments WHERE booking_id = ? ORDER BY payment_date DESC";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, bookingId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                payments.add(extractPayment(rs));
            }
        } catch (Exception e) {
            System.err.println("Get Payments by Booking Error: " + e.getMessage());
        }
        return payments;
    }

    @Override
    public boolean updatePaymentStatus(int paymentId, String status) {
        String sql = "UPDATE payments SET payment_status = ? WHERE payment_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, status);
            ps.setInt(2, paymentId);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            System.err.println("Update Status Error: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean deletePayment(int paymentId) {
        String sql = "DELETE FROM payments WHERE payment_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, paymentId);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            System.err.println("Delete Payment Error: " + e.getMessage());
            return false;
        }
    }

    @Override
    public List<Payment> getAllPayments() {
        List<Payment> payments = new ArrayList<>();
        String sql = "SELECT * FROM payments ORDER BY payment_date DESC";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                payments.add(extractPayment(rs));
            }
        } catch (Exception e) {
            System.err.println("Get All Payments Error: " + e.getMessage());
        }
        return payments;
    }

    private Payment extractPayment(ResultSet rs) throws SQLException {
        Payment payment = new Payment();
        payment.setPaymentId(rs.getInt("payment_id"));
        payment.setBookingId(rs.getInt("booking_id"));
        payment.setAmount(rs.getDouble("amount"));
        payment.setPaymentMethod(rs.getString("payment_method"));
        payment.setPaymentStatus(rs.getString("payment_status"));
        payment.setPaymentDate(rs.getDate("payment_date"));
        payment.setTransactionRef(rs.getString("transaction_ref"));
        return payment;
    }
}