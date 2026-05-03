package com.dao;

import com.interfaces.RouteDAO;
import com.model.Route;
import com.utilities.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RouteDAOImpl implements RouteDAO {

    @Override
    public List<Route> getRoutesByTrek(int trekId) {
        List<Route> routes = new ArrayList<>();
        String sql = "SELECT * FROM routes WHERE trek_id = ? ORDER BY waypoint_order ASC";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, trekId);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                Route route = new Route();
                route.setRouteId(rs.getInt("route_id"));
                route.setTrekId(rs.getInt("trek_id"));
                route.setWaypointOrder(rs.getInt("waypoint_order"));
                route.setWaypointName(rs.getString("waypoint_name"));
                route.setAltitudeM(rs.getInt("altitude_m"));
                route.setNotes(rs.getString("notes"));
                routes.add(route);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return routes;
    }
}