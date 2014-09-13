package com.ki.dms.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbAdapter {

	private final static String DB_NAME = "DMS";

	private static final int DB_VERSION = 1;

	Context context;
	DbHelper dbHelper;

	public DbAdapter(Context context) {
		this.context = context;
		dbHelper = new DbHelper(context);
	}

	public SQLiteDatabase open() {
		return dbHelper.getWritableDatabase();
	}

	public void close() {
		dbHelper.close();
	}

	private class DbHelper extends SQLiteOpenHelper {

		public DbHelper(Context context) {
			super(context, DB_NAME, null, DB_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL(UserTable.CREATE_TABLE_SQL);
			db.execSQL(GlucoseTable.CREATE_TABLE_SQL);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			db.execSQL("DROP TABLE IF EXISTS " + UserTable.DATABASE_TABLE);
			db.execSQL("DROP TABLE IF EXISTS " + GlucoseTable.DATABASE_TABLE);
			// Recreate new database:
			onCreate(db);
		}

	}
}
