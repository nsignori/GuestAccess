package com.bad.GUI;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.xml.bind.DatatypeConverter;

import com.bad.storage.DBConnectionManager;

import javafx.application.Application;

public class Main {
    private static DBConnectionManager db = DBConnectionManager.getInstance();

    public static void main(String[] args) {
        Application.launch(SetUp.class);
    }

    public static void setScene(String scene) {
        switch (scene) {
        case "login":
            SetUp.setScene(new LoginGUI());
            break;
            case "home":
                SetUp.setScene(new HomeGUI());
                break;
            default:
                System.out.println("Case \"" + scene + "\" not known.");
        }
    }

    public static void exit() {
        db.close();
    }
    
    public static boolean login(String username, String password) {
    	boolean correct = false;
    	String[] userInfo = db.getUserInfo(username);
    	
    	if(userInfo != null) {
    		correct = userInfo[0].equals(hash(password + userInfo[1]));
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return myHash;
	}
}
