package com.bad.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Statement;


public class DBConnectionManager {
	private static final String IP = "localhost";

	private static DBConnectionManager db = null;
	private static final String dbURL = "jdbc:mysql://" + IP + "/guestAccess";

	private static final String dbUsername = "guestaccess";
	private static final String dbPassword = "Fu5o32ta8A75xaN1T32I162E1I2iC8";
	private Connection conn;

	private PreparedStatement psUserInfo, psAccessRulesUser, psAccessRule, psMakeRule;

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
			psAccessRulesUser = conn.prepareStatement("SELECT * FROM accessRules WHERE homeOwner = ?;");
			psAccessRule = conn.prepareStatement("SELECT * FROM accessRules WHERE id = ?;");
			psMakeRule = conn.prepareStatement("INSERT INTO accessRules (homeOwner, guest, guestNumber, pin, startTime, endTime) VALUES (?, ?, ?, ?, ?, ?);", Statement.RETURN_GENERATED_KEYS);
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

	public ArrayList<AccessRule> getAccessRulesForUser(String username) {
		ArrayList<AccessRule> rules = new ArrayList<AccessRule>();
		try {
			psAccessRulesUser.setString(1, username);

			ResultSet rs = psAccessRulesUser.executeQuery();
			if(rs.next()) {
				rules.add(new AccessRule(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rules;
	}

	public AccessRule addRule(AccessRule rule) {
		AccessRule ret = null;
		try {
			psMakeRule.setString(1, rule.getHomeOwner());
			psMakeRule.setString(2, rule.getGuest());
			psMakeRule.setString(3, rule.getGuestNumber());
			psMakeRule.setString(4, rule.getPin());
			psMakeRule.setString(5, rule.getStartTime());
			psMakeRule.setString(6, rule.getEndTime());

			psMakeRule.execute();
			ResultSet rs = psMakeRule.getGeneratedKeys();
			if(rs.next()) {
				int id = rs.getInt(1);

				psAccessRule.setInt(1, id);

				ResultSet rs2 = psAccessRule.executeQuery();

				if(rs2.next()) {
					ret = new AccessRule(rs2.getInt(1), rs2.getString(2), rs2.getString(3), rs2.getString(4), rs2.getString(5), rs2.getString(6), rs2.getString(7));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ret;
	}
}
