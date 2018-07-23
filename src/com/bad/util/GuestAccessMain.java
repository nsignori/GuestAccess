package com.bad.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import javax.xml.bind.DatatypeConverter;

import com.bad.GUI.AddRuleGUI;
import com.bad.GUI.HomeGUI;
import com.bad.GUI.LoginGUI;

import javafx.application.Application;

public class GuestAccessMain {
	private static DBConnectionManager db = DBConnectionManager.getInstance();
	private static String username = "";

	private static int width = 500;
	private static int height = 400;

	public static void main(String[] args) {
		Application.launch(SetUp.class);
	}

	public static void setScene(String input) {
		String scene = "", supplement = "";
		if(input.contains(" ")) {
			scene = input.substring(0, input.indexOf(" "));
			supplement = input.substring(input.indexOf(" ") + 1);
		} else {
			scene = input;
		}
		switch (scene) {
		case "login":
			SetUp.setScene(new LoginGUI());
			break;
		case "home":
			SetUp.setScene(new HomeGUI());
			break;
		case "addRule":
			SetUp.setScene(new AddRuleGUI());
			break;
		case "editRule":
			SetUp.setScene(new EditRuleGUI(getAccessRule(Integer.parseInt(supplement))));
			break;
		default:
			System.out.println("Case \"" + input + "\" not known.");
		}
	}

	public static void exit() {
		db.close();
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
				GuestAccessMain.username = username;
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

	public static AccessRule addRule(AccessRule rule) {
		return db.addRule(rule);
	}

	public static AccessRule editRule(AccessRule rule) {
		return db.editRule(rule);
	}

	public static void deleteRule(AccessRule rule) {
		db.deleteRule(rule);
	}
}
