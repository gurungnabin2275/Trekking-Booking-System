package com.dao;

import com.interfaces.TrekDAO;
import com.model.Trek;
import com.utilities.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TrekDAOImpl implements TrekDAO {

    @Override
    public List<Trek> getAllActiveTreks() {
        List<Trek> treks = new ArrayList<>();
        String sql = "SELECT * FROM treks WHERE is_active = 1";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Trek t = new Trek();
                t.setTrekId(rs.getInt("trek_id"));
                t.setTrekName(rs.getString("trek_name"));
                t.setDifficulty(rs.getString("difficulty"));
                t.setDurationDays(rs.getInt("duration_days"));
                t.setPrice(rs.getDouble("price"));
                t.setMaxGroupSize(rs.getInt("max_group_size"));
                t.setRegion(rs.getString("region"));
                t.setStartLocation(rs.getString("start_location"));
                t.setEndLocation(rs.getString("end_location"));
                t.setDistanceKm(rs.getDouble("distance_km"));
                t.setImageUrl(rs.getString("image_url"));
                t.setDescription(rs.getString("description"));
                t.setActive(rs.getBoolean("is_active"));
                treks.add(t);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return treks;
    }

    @Override
    public Trek getTrekById(int trekId) {
        String sql = "SELECT * FROM treks WHERE trek_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, trekId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Trek t = new Trek();
                t.setTrekId(rs.getInt("trek_id"));
                t.setTrekName(rs.getString("trek_name"));
                t.setDifficulty(rs.getString("difficulty"));
                t.setDurationDays(rs.getInt("duration_days"));
                t.setPrice(rs.getDouble("price"));
                t.setMaxGroupSize(rs.getInt("max_group_size"));
                t.setRegion(rs.getString("region"));
                t.setStartLocation(rs.getString("start_location"));
                t.setEndLocation(rs.getString("end_location"));
                t.setDistanceKm(rs.getDouble("distance_km"));
                t.setImageUrl(rs.getString("image_url"));
                t.setDescription(rs.getString("description"));
                t.setActive(rs.getBoolean("is_active"));
                return t;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}