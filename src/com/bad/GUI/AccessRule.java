package com.bad.GUI;

public class AccessRule {
	private int id, pin;
	private String homeOwner, guest, startTime, endTime;
	
	public AccessRule(int id, String homeOwner, String guest, int pin, String startTime, String endTime) {
		this.id = id;
		this.homeOwner = homeOwner;
		this.guest = guest;
		this.pin = pin;
		this.startTime = startTime;
		this.endTime = endTime;
	}
	
	public AccessRule(String homeOwner, String guest, String startTime, String endTime) {
		this.homeOwner = homeOwner;
		this.guest = guest;
		this.pin = (int) (Math.random() * 9999);
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

	public String getPin() {
		return "0000".substring(("" + pin).length()) + pin;
	}

	public String getStartTime() {
		return startTime;
	}

	public String getEndTime() {
		return endTime;
	}
}
