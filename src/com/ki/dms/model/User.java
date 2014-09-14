package com.ki.dms.model;

import java.util.ArrayList;

import android.os.Parcel;
import android.os.Parcelable;
/*
 * Data model for user 
 */
public class User implements Parcelable {

	private long id;

	private String name;

	private String idNumber;

	private String doctorName;

	private String doctorNumber;

	private int lowGlucose;

	private int highGlucose;

	private int exercisedDays;

	ArrayList<Glucose> measures;
	
	ArrayList<Exercise> excersises;

	public ArrayList<Exercise> getExcersises() {
		return excersises;
	}

	public void setExcersises(ArrayList<Exercise> excersises) {
		this.excersises = excersises;
	}

	public ArrayList<Glucose> getMeasures() {
		return measures;
	}

	public void setMeasures(ArrayList<Glucose> measures) {
		this.measures = measures;
	}

	public void addMeasure(Glucose glucose) {
		if (measures == null) {
			measures = new ArrayList<Glucose>();
			measures.add(glucose);
		}
	}

	public User() {

	}

	public User(Parcel in) {
		id = in.readLong();
		name = in.readString();
		idNumber = in.readString();
		doctorName = in.readString();
		doctorNumber = in.readString();
		lowGlucose = in.readInt();
		highGlucose = in.readInt();
		exercisedDays = in.readInt();
	}

	public User(String name) {
		setName(name);
	}

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the idNumber
	 */
	public String getIdNumber() {
		return idNumber;
	}

	/**
	 * @param idNumber
	 *            the idNumber to set
	 */
	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	/**
	 * @return the doctorName
	 */
	public String getDoctorName() {
		return doctorName;
	}

	/**
	 * @param doctorName
	 *            the doctorName to set
	 */
	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	/**
	 * @return the doctorNumber
	 */
	public String getDoctorNumber() {
		return doctorNumber;
	}

	/**
	 * @param doctorNumber
	 *            the doctorNumber to set
	 */
	public void setDoctorNumber(String doctorNumber) {
		this.doctorNumber = doctorNumber;
	}

	/**
	 * @return the lowGlucose
	 */
	public int getLowGlucose() {
		return lowGlucose;
	}

	/**
	 * @param lowGlucose
	 *            the lowGlucose to set
	 */
	public void setLowGlucose(int lowGlucose) {
		this.lowGlucose = lowGlucose;
	}

	/**
	 * @return the highGlucose
	 */
	public int getHighGlucose() {
		return highGlucose;
	}

	/**
	 * @param highGlucose
	 *            the highGlucose to set
	 */
	public void setHighGlucose(int highGlucose) {
		this.highGlucose = highGlucose;
	}

	/**
	 * @return the exercisedDays
	 */
	public int getExercisedDays() {
		return exercisedDays;
	}

	/**
	 * @param exercisedDays
	 *            the exercisedDays to set
	 */
	public void setExercisedDays(int exercisedDays) {
		this.exercisedDays = exercisedDays;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	/*
	 * saves the user object
	 */
	@Override
	public void writeToParcel(Parcel out, int flags) {
		out.writeLong(id);
		out.writeString(name);
		out.writeString(idNumber);
		out.writeString(doctorName);
		out.writeString(doctorNumber);
		out.writeInt(lowGlucose);
		out.writeInt(highGlucose);
		out.writeInt(exercisedDays);
	}

	public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {

		@Override
		public User createFromParcel(Parcel size) {
			return new User(size);
		}

		@Override
		public User[] newArray(int size) {
			return new User[size];
		}
	};
}
