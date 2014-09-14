package com.ki.dms.contacts.model;

/*
 * model for displaying all contacts that logged user has in phonebook
 */
public class Contact {

	private String displayName;

	private String number;

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String name) {
		this.displayName = name;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

}
