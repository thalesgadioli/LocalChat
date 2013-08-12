package com.localchat.bd;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.localchat.bd.MessageContract.MessageEntry;
import com.localchat.bd.UserContract.UserEntry;


public class SQLiteDBHelper extends SQLiteOpenHelper{
	
	private static final int DB_VERSION = 3;
	private static final String DB_NAME = "idez.db";

	private static final String SQL_CREATE_USER =
			"CREATE TABLE " + UserEntry.TABLE_NAME + " (" +
					UserEntry._ID + " INTEGER PRIMARY KEY, " +
					UserEntry.COLUMN_NAME + " TEXT, " +
					UserEntry.COLUMN_PHONE + " TEXT); ";
	
	private static final String SQL_CREATE_MESSAGE =
			"CREATE TABLE " + MessageEntry.TABLE_NAME + " (" +
					MessageEntry._ID + " INTEGER PRIMARY KEY, " +
					MessageEntry.COLUMN_DATA + " TEXT, " +
					MessageEntry.COLUMN_TEXTO + " TEXT, " +
					MessageEntry.COLUMN_REM + " INTEGER, " +
					MessageEntry.COLUMN_DEST + " INTEGER); ";
	
	public SQLiteDBHelper(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(SQL_CREATE_USER);
		db.execSQL(SQL_CREATE_MESSAGE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + UserEntry.TABLE_NAME);
		db.execSQL("DROP TABLE IF EXISTS " + MessageEntry.TABLE_NAME);
		onCreate(db);
	}
	
	

}
