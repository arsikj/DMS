package com.ki.dms.model;

import java.util.Date;

public class Exercise {

	private Date date;
	private int duration;
	private String type;

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Exercise(long date, int duration, String type) {
		this.date = new Date(date);
		this.duration = duration;
		this.type = type;
	}

}
