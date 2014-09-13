package com.ki.dms.model;

import java.util.Date;

public class Glucose {

	private Date date;
	private int level;
	private String reason;

	public Glucose() {
	}

	public Glucose(long date, int level, String reason) {
		this.date = new Date(date);
		this.level = level;
		this.reason = reason;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(long date) {
		this.date = new Date(date);
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

}
