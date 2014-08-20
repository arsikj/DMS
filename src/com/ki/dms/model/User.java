package com.ki.dms.model;

public class User {

	private long id;

	private String name;

	private String idNumber;

	private String doctorName;

	private String doctorNumber;

	private int lowGlucose;

	private int highGlucose;

	private int exercisedDays;

	public User() {

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
	public boolean equals(Object o) {// Override equals for Set structure
		if (o == null || !(o instanceof User)) {
			return false;
		}
		return ((User) o).id == this.id;
	}
}
