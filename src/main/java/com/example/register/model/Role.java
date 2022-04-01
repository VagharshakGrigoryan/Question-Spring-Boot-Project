package com.example.register.model;


public enum Role {
	
	USER("Basic", "user"),
	CONTRIBUTOR("Contributor", "contributor"),
	MODERATOR("Moderator", "moderator"),
	ADMINISTRATOR("Administrator", "admin");
	
	public final String displayValue;
	public final String shortName;
	
	Role(String display, String shortName) {
		this.displayValue = display;
		this.shortName = shortName;
	}
	
	public static Role fromShortName(String shortName ) {
		
		switch (shortName) {
			case "user":
				return USER;
			case "contributor":
				return CONTRIBUTOR;
			case "moderator":
				return MODERATOR;
			case "admin":
				return ADMINISTRATOR;
			default:
				throw new IllegalArgumentException("Short[" + shortName + "] not supported.");
		}
	}
}
