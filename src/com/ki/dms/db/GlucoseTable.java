package com.ki.dms.db;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.ki.dms.model.Glucose;
import com.ki.dms.model.User;

public class GlucoseTable {
	DbAdapter adapter;
	private SQLiteDatabase db;

	public static final String KEY_ROWID = "id";
	public static final int COL_ROWID = 0;

	public static final String KEY_USER_ID = "id_user";
	public static final String KEY_DATE = "date";
	public static final String KEY_GLUCOSE_LEVEL = "glucose_level";
	public static final String KEY_REASON = "reason";

	public static final int COL_USER_ID = 1;
	public static final int COL_DATE = 2;
	public static final int COL_GLUCOSE_LEVEL = 3;
	public static final int COL_REASON = 4;

	public static final String[] ALL_KEYS = new String[] { KEY_ROWID,
			KEY_USER_ID, KEY_DATE, KEY_GLUCOSE_LEVEL, KEY_REASON };

	public static final String DATABASE_TABLE = "glucose";

	static final String CREATE_TABLE_SQL = "create table " + DATABASE_TABLE
			+ " (" + KEY_ROWID + " integer primary key autoincrement, "
			+ KEY_USER_ID + " integer not null, " + KEY_DATE
			+ " integer not null, " + KEY_GLUCOSE_LEVEL + " integer not null, "
			+ KEY_REASON + " text not null );";

	public GlucoseTable(Context context) {
		adapter = new DbAdapter(context);
	}

	public GlucoseTable open() {
		db = adapter.open();
		return this;
	}

	public void close() {
		adapter.close();
	}

	// Add a new set of values to the database.
	public long insertRow(long user_id, long date, int level, String reason) {

		ContentValues initialValues = new ContentValues();
		initialValues.put(KEY_USER_ID, user_id);
		initialValues.put(KEY_DATE, date);
		initialValues.put(KEY_GLUCOSE_LEVEL, level);
		initialValues.put(KEY_REASON, reason);

		return db.insert(DATABASE_TABLE, null, initialValues);
	}

	public long saveMeasure(long user_id, Glucose glucose) {
		return insertRow(user_id, glucose.getDate().getTime(),
				glucose.getLevel(), glucose.getReason());
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
	public boolean updateRow(long rowId, long user_id, long date, int level,
			String reason) {
		String where = KEY_ROWID + "=" + rowId;

		ContentValues newValues = new ContentValues();
		newValues.put(KEY_USER_ID, user_id);
		newValues.put(KEY_DATE, date);
		newValues.put(KEY_GLUCOSE_LEVEL, level);
		newValues.put(KEY_REASON, reason);

		// Insert it into the database.
		return db.update(DATABASE_TABLE, newValues, where, null) != 0;
	}

	public ArrayList<Glucose> measuresForUser(long userId) {
		ArrayList<Glucose> measures = new ArrayList<Glucose>();
		String selection = KEY_USER_ID + "=" + userId;
		Cursor cursor = db.query(DATABASE_TABLE, ALL_KEYS, selection, null,
				null, null, null);

		if (!cursor.moveToFirst()) {// if cursor is empty
			return measures;
		}
		do {
			measures.add(populate(cursor));
		} while (cursor.moveToNext());

		return measures;
	}

	public void populateMeasuresForUser(User user) {
		user.setMeasures(measuresForUser(user.getId()));
	}

	public boolean update(long id, ContentValues values) {
		return db.update(DATABASE_TABLE, values, KEY_ROWID + " = " + id, null) > 0;
	}

	private Glucose populate(Cursor cursor) {
		return new Glucose(cursor.getLong(COL_DATE),
				cursor.getInt(COL_GLUCOSE_LEVEL), cursor.getString(COL_REASON));
	}
}
