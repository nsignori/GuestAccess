package com.bad.storage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectionManager {
    // Sets address of DB based on location
    private static final String IP = "localhost";

    private static DBConnectionManager db = null;
    private static final String dbURL = "jdbc:mysql://" + IP + "/weightTracker";

    private static final String dbUsername = "WeightTracker";
    private static final String dbPassword = "Min4ROkAF7yI8ArisA4OFIyaFepuzi";
    private Connection conn;

    private DBConnectionManager() throws ClassNotFoundException, SQLException {
        DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        Class.forName("com.mysql.jdbc.Driver");
        DriverManager.setLoginTimeout(10);

        try {
            conn = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
            makePS();
        } catch(SQLException e) {
            System.err.println("Failed External " + e);
        }
    }

    public String getIp() {
        String ret = null;

        try {
            String url;
            url = conn.getMetaData().getURL();
            ret = url.substring(url.indexOf("//") + 2, url.indexOf("/e"));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ret;
    }

    // Create all the PreparedStatements
    private void makePS() {
//        try {
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
    }

    public static DBConnectionManager getInstance() {
        if (db == null) {
            try {
                db = new DBConnectionManager();
            } catch (ClassNotFoundException | SQLException e) {
                System.err.println("DB CONNECTION ERROR");
                e.printStackTrace();
            }
        }
        return db;
    }

    public void close() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
