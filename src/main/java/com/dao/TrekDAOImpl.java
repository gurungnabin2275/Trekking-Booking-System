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
    @Override
    public boolean addTrek(Trek t) {
        String sql = "INSERT INTO treks (trek_name, difficulty, duration_days, price, max_group_size, region, start_location, end_location, distance_km, image_url, description, is_active) VALUES (?,?,?,?,?,?,?,?,?,?,?,1)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, t.getTrekName());
            ps.setString(2, t.getDifficulty());
            ps.setInt(3, t.getDurationDays());
            ps.setDouble(4, t.getPrice());
            ps.setInt(5, t.getMaxGroupSize());
            ps.setString(6, t.getRegion());
            ps.setString(7, t.getStartLocation());
            ps.setString(8, t.getEndLocation());
            ps.setDouble(9, t.getDistanceKm());
            ps.setString(10, t.getImageUrl());
            ps.setString(11, t.getDescription());
            return ps.executeUpdate() > 0;
        } catch (Exception e) { e.printStackTrace(); }
        return false;
    }

    @Override
    public boolean updateTrek(Trek t) {
        String sql = "UPDATE treks SET trek_name=?, difficulty=?, duration_days=?, price=?, max_group_size=?, region=?, start_location=?, end_location=?, distance_km=?, description=?, is_active=? WHERE trek_id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, t.getTrekName());
            ps.setString(2, t.getDifficulty());
            ps.setInt(3, t.getDurationDays());
            ps.setDouble(4, t.getPrice());
            ps.setInt(5, t.getMaxGroupSize());
            ps.setString(6, t.getRegion());
            ps.setString(7, t.getStartLocation());
            ps.setString(8, t.getEndLocation());
            ps.setDouble(9, t.getDistanceKm());
            ps.setString(10, t.getDescription());
            ps.setBoolean(11, t.isActive());
            ps.setInt(12, t.getTrekId());
            return ps.executeUpdate() > 0;
        } catch (Exception e) { e.printStackTrace(); }
        return false;
    }

    @Override
    public boolean deleteTrek(int trekId) {
        String sql = "UPDATE treks SET is_active = 0 WHERE trek_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, trekId);
            return ps.executeUpdate() > 0;
        } catch (Exception e) { e.printStackTrace(); }
        return false;
    }

    @Override
    public List<Trek> getAllTreks() {
        List<Trek> treks = new ArrayList<>();
        String sql = "SELECT * FROM treks";
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
        } catch (Exception e) { e.printStackTrace(); }
        return treks;
    }
}