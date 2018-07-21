package com.bad.storage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class DBConnectionManager {
	private static final String IP = "localhost";

	private static DBConnectionManager db = null;
	private static final String dbURL = "jdbc:mysql://" + IP + "/guestAccess";

	private static final String dbUsername = "guestaccess";
	private static final String dbPassword = "Fu5o32ta8A75xaN1T32I162E1I2iC8";
	private Connection conn;

	private PreparedStatement psUserInfo;

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
		try {
			psUserInfo = conn.prepareStatement("SELECT * FROM users WHERE username = ?;");
		} catch (SQLException e) {
			e.printStackTrace();
		}
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

	public String[] getUserInfo(String username) {
		String[] info = null;
		try {
			psUserInfo.setString(1, username);

			ResultSet rs = psUserInfo.executeQuery();
			if(rs.next()) {
				info = new String[] {rs.getString(2), rs.getString(3)};
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return info;
	}
}
