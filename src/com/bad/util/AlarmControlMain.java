package com.bad.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.xml.bind.DatatypeConverter;

import com.bad.GUI.AlarmPanelGUI;
import com.bad.GUI.LoginGUI;

import javafx.application.Application;

public class AlarmControlMain {
	private static DBConnectionManager db = DBConnectionManager.getInstance();
	private static String username = "";

	private static int width = 250;
	private static int height = 300;

	public static void main(String[] args) {
		Application.launch(AlarmSetUp.class);
	}

	public static void exit() {
		db.close();
	}

	public static void setScene(String scene) {
		switch (scene) {
		case "login":
			AlarmSetUp.setScene(new LoginGUI());
			break;
		case "alarmPanel":
			AlarmSetUp.setScene(new AlarmPanelGUI());
			break;
		}
	}

	public static String getUsername() {
		return username;
	}

	public static boolean login(String username, String password) {
		boolean correct = false;
		String[] userInfo = db.getUserInfo(username);

		if(userInfo != null) {
			correct = userInfo[0].equals(hash(password + userInfo[1]));
			if(correct) {
				AlarmControlMain.username = username;
			}
		}

		return correct;
	}

	private static String hash(String input) {
		String myHash = null;
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(input.getBytes());
			byte[] digest = md.digest();
			myHash = DatatypeConverter.printHexBinary(digest);

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return myHash;
	}

	public static AccessRule getAccessRule(int id) {
		return db.getAccessRules(id);
	}

	public static ArrayList<AccessRule> getAccessRulesForUser() {
		return db.getAccessRulesForUser(username);
	}

	public static int getWidth() {
		return width;
	}

	public static int getHeight() {
		return height;
	}

	public static int checkPin(String pin) {
		int id = -1;
		ArrayList<AccessRule> rules = db.getAccessRulesForUser(username);
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");  
		LocalDateTime now = LocalDateTime.now();

		for(AccessRule temp : rules) {
			if((temp.getStartTime().compareTo(dtf.format(now)) < 0) && (temp.getEndTime().compareTo(dtf.format(now)) > 0)) {
				if(temp.getPin().equals(pin)) {
					id = temp.getId();
				}
			}
		}

		return id;
	}
}
