package com.utilities;

import java.sql.Connection;

public class TestConnection {
    public static void main(String[] args) {
        try {
            Connection conn = DBConnection.getConnection();
            if (conn != null) {
                System.out.println("SUCCESS - Database connected!");
                conn.close();
            }
        } catch (Exception e) {
            System.out.println("FAILED: " + e.getMessage());
        }
    }
}