package com.ki.dms.db;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.ki.dms.model.User;

public class UserTable {
	DbAdapter adapter;
	private SQLiteDatabase db;

	public static final String KEY_ROWID = "id";
	public static final int COL_ROWID = 0;

	public static final String KEY_NAME = "name";
	public static final String KEY_ID_NUMBER = "id_number";
	public static final String KEY_DOCTOR_NAME = "doctor_name";
	public static final String KEY_DOCTOR_NUMBER = "doctor_number";
	public static final String KEY_LOW_GLUCOSE = "low_glucose";
	public static final String KEY_HIGH_GLUCOSE = "high_glucose";
	public static final String KEY_EXERCISE_DAYS = "exercise_days";

	public static final int COL_NAME = 1;
	public static final int COL_ID_NUMBER = 2;
	public static final int COL_DOCTOR_NAME = 3;
	public static final int COL_DOCTOR_NUMBER = 4;
	public static final int COL_LOW_GLUCOSE = 5;
	public static final int COL_HIGH_GLUCOSE = 6;
	public static final int COL_EXERCISE_DAYS = 7;

	public static final String[] ALL_KEYS = new String[] { KEY_ROWID, KEY_NAME,
			KEY_ID_NUMBER, KEY_DOCTOR_NAME, KEY_DOCTOR_NUMBER, KEY_LOW_GLUCOSE,
			KEY_HIGH_GLUCOSE, KEY_EXERCISE_DAYS };

	public static final String DATABASE_TABLE = "user";

	static final String CREATE_TABLE_SQL = "create table " + DATABASE_TABLE
			+ " (" + KEY_ROWID + " integer primary key autoincrement, "
			+ KEY_NAME + " text not null, " + KEY_ID_NUMBER
			+ " text not null, " + KEY_DOCTOR_NAME + " text not null, "
			+ KEY_DOCTOR_NUMBER + " text, " + KEY_LOW_GLUCOSE + " integer , "
			+ KEY_HIGH_GLUCOSE + " integer, " + KEY_EXERCISE_DAYS + " integer "
			+ " );";

	public UserTable(Context context) {
		adapter = new DbAdapter(context);
	}

	public UserTable open() {
		db = adapter.open();
		return this;
	}

	public void close() {
		adapter.close();
	}

	// Add a new set of values to the database.
	public long insertRow(String name, String idNumber, String doctorName,
			String doctorNumber, int lowGlucose, int highGlucose) {

		ContentValues initialValues = new ContentValues();
		initialValues.put(KEY_NAME, name);
		initialValues.put(KEY_ID_NUMBER, idNumber);
		initialValues.put(KEY_DOCTOR_NAME, doctorName);
		initialValues.put(KEY_DOCTOR_NUMBER, doctorNumber);
		initialValues.put(KEY_LOW_GLUCOSE, lowGlucose);
		initialValues.put(KEY_HIGH_GLUCOSE, highGlucose);
		initialValues.put(KEY_EXERCISE_DAYS, 0);

		return db.insert(DATABASE_TABLE, null, initialValues);
	}

	public boolean deleteRow(long rowId) {
		String where = KEY_ROWID + "=" + rowId;
		return db.delete(DATABASE_TABLE, where, null) != 0;
	}

	// public void deleteAll() {
	// Cursor c = getAllRows();
	// long rowId = c.getColumnIndexOrThrow(KEY_ROWID);
	// if (c.moveToFirst()) {
	// do {
	// deleteRow(c.getLong((int) rowId));
	// } while (c.moveToNext());
	// }
	// c.close();
	// }

	// Get a specific row (by rowId)
	public Cursor getRow(long rowId) {
		String where = KEY_ROWID + "=" + rowId;
		Cursor c = db.query(true, DATABASE_TABLE, ALL_KEYS, where, null, null,
				null, null, null);
		if (c != null) {
			c.moveToFirst();
		}
		return c;
	}

	// Change an existing row to be equal to new data.
	public boolean updateRow(long rowId, String name, String idNumber,
			String doctorName, String doctorNumber, int lowGlucose,
			int highGlucose, int exercisedDays) {
		String where = KEY_ROWID + "=" + rowId;

		ContentValues newValues = new ContentValues();
		newValues.put(KEY_NAME, name);
		newValues.put(KEY_ID_NUMBER, idNumber);
		newValues.put(KEY_DOCTOR_NAME, doctorName);
		newValues.put(KEY_DOCTOR_NUMBER, doctorNumber);
		newValues.put(KEY_LOW_GLUCOSE, lowGlucose);
		newValues.put(KEY_HIGH_GLUCOSE, highGlucose);
		newValues.put(KEY_EXERCISE_DAYS, exercisedDays);

		// Insert it into the database.
		return db.update(DATABASE_TABLE, newValues, where, null) != 0;
	}

	public ArrayList<User> allUsers() {
		ArrayList<User> users = new ArrayList<User>();
		Cursor cursor = db.query(DATABASE_TABLE, ALL_KEYS, null, null, null,
				null, null);

		if (!cursor.moveToFirst()) {// if cursor is empty
			return users;
		}
		do {
			users.add(populate(cursor));
		} while (cursor.moveToNext());

		return users;
	}

	public boolean update(long id, ContentValues values) {
		return db.update(DATABASE_TABLE, values, KEY_ROWID + " = " + id, null) > 0;
	}

	public User getlastUser() {
		Cursor cursor = db.query(DATABASE_TABLE, ALL_KEYS, null, null, null,
				null, null);
		if (cursor.moveToLast()) {
			return populate(cursor);
		}
		return null;
	}

	private User populate(Cursor cursor) {
		User user = new User();
		user.setId(cursor.getLong(COL_ROWID));
		user.setName(cursor.getString(COL_NAME));
		user.setIdNumber(cursor.getString(COL_ID_NUMBER));
		user.setDoctorName(cursor.getString(COL_DOCTOR_NAME));
		user.setDoctorNumber(cursor.getString(COL_DOCTOR_NUMBER));
		user.setLowGlucose(cursor.getInt(COL_LOW_GLUCOSE));
		user.setHighGlucose(cursor.getInt(COL_HIGH_GLUCOSE));
		user.setExercisedDays(cursor.getInt(COL_EXERCISE_DAYS));
		return user;
	}
}
