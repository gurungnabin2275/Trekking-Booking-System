package com.dao;

import com.interfaces.ReviewDAO;
import com.model.Review;
import com.utilities.DBConnection;   
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReviewDAOImpl implements ReviewDAO {

    @Override
    public boolean addReview(Review review) {
        String sql = "INSERT INTO reviews (user_id, trek_id, booking_id, rating, comment, review_date) " +
                     "VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, review.getUserId());
            ps.setInt(2, review.getTrekId());
            ps.setInt(3, review.getBookingId());
            ps.setInt(4, review.getRating());
            ps.setString(5, review.getComment());
            ps.setDate(6, review.getReviewDate());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Review> getReviewsByTrek(int trekId) {
        List<Review> reviews = new ArrayList<>();
        String sql = "SELECT * FROM reviews WHERE trek_id = ? ORDER BY review_date DESC";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, trekId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Review r = new Review();
                r.setReviewId(rs.getInt("review_id"));
                r.setUserId(rs.getInt("user_id"));
                r.setTrekId(rs.getInt("trek_id"));
                r.setBookingId(rs.getInt("booking_id"));
                r.setRating(rs.getInt("rating"));
                r.setComment(rs.getString("comment"));
                r.setReviewDate(rs.getDate("review_date"));
                reviews.add(r);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return reviews;
    }
}