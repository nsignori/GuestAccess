package com.bad.GUI;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.xml.bind.DatatypeConverter;

public class UserMaker {

	public static void main(String[] args) {
		String userPassword = "password";
		
		String salt = makeSalt(32);
		System.out.println(hash(userPassword + salt) + "\n\"" + salt  + "\"");
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
	
	private static String makeSalt(int size) {
		String salt = "";
		for(int i = 0; i < size; i++) {
			salt += (char)((int)(Math.random() * 93) + 33);
		}
		return salt;
	}
}
