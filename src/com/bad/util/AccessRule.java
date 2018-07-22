package com.bad.util;

public class AccessRule {
	private int id;
	private String homeOwner, guest, guestNumber, pin, startTime, endTime;
	
	public AccessRule(int id, String homeOwner, String guest, String guestNumber, String pin, String startTime, String endTime) {
		this.id = id;
		this.homeOwner = homeOwner;
		this.guest = guest;
		this.guestNumber = guestNumber;
		this.pin = pin;
		this.startTime = startTime;
		this.endTime = endTime;
	}
	
	public AccessRule(String homeOwner, String guest, String guestNumber, String startTime, String endTime) {
		this.homeOwner = homeOwner;
		this.guest = guest;
		this.guestNumber = guestNumber;
		this.pin = "" + (int) (Math.random() * 9999);
		this.pin = "0000".substring(this.pin.length()) + this.pin;
		this.startTime = startTime;
		this.endTime = endTime;
	}

	public int getId() {
		return id;
	}

	public String getHomeOwner() {
		return homeOwner;
	}

	public String getGuest() {
		return guest;
	}
	
	public String getGuestNumber() {
		return guestNumber;
	}

	public String getPin() {
		return pin;
	}

	public String getStartTime() {
		return startTime;
	}

	public String getEndTime() {
		return endTime;
	}
}
