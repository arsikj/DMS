package com.ki.dms.db;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
<<<<<<< HEAD:src/com/ki/dms/db/ExcersiseTable.java
import com.ki.dms.model.Excersise;
import com.ki.dms.model.User;
/*
 * database model for exercise table
 */
public class ExcersiseTable {
=======

import com.ki.dms.model.Exercise;
import com.ki.dms.model.Glucose;
import com.ki.dms.model.User;

public class ExerciseTable {
>>>>>>> origin/master:src/com/ki/dms/db/ExerciseTable.java

	DbAdapter adapter;
	private SQLiteDatabase db;

	public static final String KEY_ROWID = "id";
	public static final int COL_ROWID = 0;

	public static final String KEY_USER_ID = "id_user";
	public static final String KEY_DATE = "date";
	public static final String KEY_DURATION = "duration";
	public static final String KEY_TYPE = "type";

	public static final int COL_USER_ID = 1;
	public static final int COL_DATE = 2;
	public static final int COL_DURATION = 3;
	public static final int COL_TYPE = 4;

	public static final String[] ALL_KEYS = new String[] { KEY_ROWID,
			KEY_USER_ID, KEY_DATE, KEY_DURATION, KEY_TYPE };

	public static final String DATABASE_TABLE = "excersise";

	/*
	 * query for table creation
	 */
	static final String CREATE_TABLE_SQL = "create table " + DATABASE_TABLE
			+ " (" + KEY_ROWID + " integer primary key autoincrement, "
			+ KEY_USER_ID + " integer not null, " + KEY_DATE
			+ " integer not null, " + KEY_DURATION + " integer not null, "
			+ KEY_TYPE + " text not null );";

	public ExerciseTable(Context context) {
		adapter = new DbAdapter(context);
	}

	public ExerciseTable open() {
		db = adapter.open();
		return this;
	}

	public void close() {
		adapter.close();
	}

	// Add a new set of values to the database.
	public long insertRow(long user_id, long date, int duration, String type) {

		ContentValues initialValues = new ContentValues();
		initialValues.put(KEY_USER_ID, user_id);
		initialValues.put(KEY_DATE, date);
		initialValues.put(KEY_DURATION, duration);
		initialValues.put(KEY_TYPE, type);

		return db.insert(DATABASE_TABLE, null, initialValues);
	}

<<<<<<< HEAD:src/com/ki/dms/db/ExcersiseTable.java
	//Adds an exercise to the existing table
	public long saveExcersise(long user_id, Excersise excersise) {
=======
	public long saveExcersise(long user_id, Exercise excersise) {
>>>>>>> origin/master:src/com/ki/dms/db/ExerciseTable.java
		return insertRow(user_id, excersise.getDate().getTime(),
				excersise.getDuration(), excersise.getType());
	}

	//Deletes an exercise from the existing table
	public boolean deleteRow(long rowId) {
		String where = KEY_ROWID + "=" + rowId;
		return db.delete(DATABASE_TABLE, where, null) != 0;
	}


	// Get a specific row (by rowId)
	public Cursor getRow(long rowId, long userId) {
		String where = KEY_ROWID + "=" + rowId + " and " + KEY_USER_ID + "="
				+ userId;
		Cursor c = db.query(true, DATABASE_TABLE, ALL_KEYS, where, null, null,
				null, null, KEY_DATE);
		if (c != null) {
			c.moveToFirst();
		}
		return c;
	}

	// Change an existing row to be equal to new data.
	public boolean updateRow(long rowId, long user_id, long date, int duration,
			String type) {
		String where = KEY_ROWID + "=" + rowId;

		ContentValues newValues = new ContentValues();
		newValues.put(KEY_USER_ID, user_id);
		newValues.put(KEY_DATE, date);
		newValues.put(KEY_DURATION, duration);
		newValues.put(KEY_TYPE, type);

		// Insert it into the database.
		return db.update(DATABASE_TABLE, newValues, where, null) != 0;
	}

<<<<<<< HEAD:src/com/ki/dms/db/ExcersiseTable.java
	//returns all exercises for specific user
	public ArrayList<Excersise> excersisesForUser(long userId) {
		ArrayList<Excersise> excersises = new ArrayList<Excersise>();
=======
	public ArrayList<Exercise> excersisesForUser(long userId) {
		ArrayList<Exercise> excersises = new ArrayList<Exercise>();
>>>>>>> origin/master:src/com/ki/dms/db/ExerciseTable.java
		String selection = KEY_USER_ID + "=" + userId;
		Cursor cursor = db.query(DATABASE_TABLE, ALL_KEYS, selection, null,
				null, null, null);

		if (!cursor.moveToFirst()) {// if cursor is empty
			return excersises;
		}
		do {
			excersises.add(populate(cursor));
		} while (cursor.moveToNext());

		return excersises;
	}

<<<<<<< HEAD:src/com/ki/dms/db/ExcersiseTable.java
	//loads the users exercises list
	public void populateExcersisesForUser(User user) {
=======
	public ExerciseTable populateExcersisesForUser(User user) {
>>>>>>> origin/master:src/com/ki/dms/db/ExerciseTable.java
		user.setExcersises(excersisesForUser(user.getId()));
		return this;
	}

	//method for editing exercise table
	public boolean update(long id, ContentValues values) {
		return db.update(DATABASE_TABLE, values, KEY_ROWID + " = " + id, null) > 0;
	}

<<<<<<< HEAD:src/com/ki/dms/db/ExcersiseTable.java
	//populates the exercise table
	private Excersise populate(Cursor cursor) {
		return new Excersise(cursor.getLong(COL_DATE),
=======
	private Exercise populate(Cursor cursor) {
		return new Exercise(cursor.getLong(COL_DATE),
>>>>>>> origin/master:src/com/ki/dms/db/ExerciseTable.java
				cursor.getInt(COL_DURATION), cursor.getString(COL_TYPE));
	}
}
