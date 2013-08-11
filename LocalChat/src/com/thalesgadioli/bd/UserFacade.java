package com.thalesgadioli.bd;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.thalesgadioli.bd.UserContract.UserEntry;

public class UserFacade {
	
	public static long insert (User user, Context context) {
		
		if (context==null)
			return -1;
		
		SQLiteDatabase db = new SQLiteDBHelper(context).getWritableDatabase();
		
		ContentValues values = new ContentValues();
		
		values.put(UserEntry.COLUMN_NAME, user.getName());
		values.put(UserEntry.COLUMN_PHONE, user.getPhone());
		
		long result = -1;
		
		if (user.getId() > 0)
			result = db.update(UserEntry.TABLE_NAME, values, String.format("%s = %s ", UserEntry._ID, user.getId()), null);
		else {
			result = db.insert(UserEntry.TABLE_NAME, null, values);
			user.setId(result);
		}
		
		db.close();
		
		return result;
	}
	
	public static int delete (Context context) {
		if (context==null)
			return -1;
		
		SQLiteDatabase db = new SQLiteDBHelper(context).getWritableDatabase();
		int result = db.delete(UserEntry.TABLE_NAME, null, null);
		
		db.close();
		
		return result;
	}
	
	public static List<User> get (Context context) {
		if (context==null)
			return null;
		
		SQLiteDatabase db = new SQLiteDBHelper(context).getReadableDatabase();
		
		Cursor cursor = db.query(UserEntry.TABLE_NAME, null, null, null, null, null, UserEntry._ID);
		
		List<User> result = new ArrayList<User>();;

		if (cursor.moveToFirst()) {
			User user;
			do {
				user = new User();
				user.setId(cursor.getLong(cursor.getColumnIndex(UserEntry._ID)));
				user.setName(cursor.getString(cursor.getColumnIndex(UserEntry.COLUMN_NAME)));
				user.setPhone(cursor.getString(cursor.getColumnIndex(UserEntry.COLUMN_PHONE)));
				
				result.add(user);
				
			} while (cursor.moveToNext());
		}
		
		db.close();
		
		return result;
	}
	
	public static List<User> getWithoutId (Context context, Long id) {
		if (context==null)
			return null;
		
		SQLiteDatabase db = new SQLiteDBHelper(context).getReadableDatabase();
		
		Cursor cursor = db.query(UserEntry.TABLE_NAME, null, "_ID <> " + id, null, null, null, UserEntry._ID);
		
		List<User> result = new ArrayList<User>();;

		if (cursor.moveToFirst()) {
			User user;
			do {
				user = new User();
				user.setId(cursor.getLong(cursor.getColumnIndex(UserEntry._ID)));
				user.setName(cursor.getString(cursor.getColumnIndex(UserEntry.COLUMN_NAME)));
				user.setPhone(cursor.getString(cursor.getColumnIndex(UserEntry.COLUMN_PHONE)));
				
				result.add(user);
				
			} while (cursor.moveToNext());
		}
		
		db.close();
		
		return result;
	}

	
	public static User getById (Context context, Long id) {
		if (context==null)
			return null;
		
		SQLiteDatabase db = new SQLiteDBHelper(context).getReadableDatabase();
		
		Cursor cursor = db.query(UserEntry.TABLE_NAME, null, "_ID = " + id, null, null, null, UserEntry._ID);
		
		List<User> result = new ArrayList<User>();;

		if (cursor.moveToFirst()) {
			User user;
			do {
				user = new User();
				user.setId(cursor.getLong(cursor.getColumnIndex(UserEntry._ID)));
				user.setName(cursor.getString(cursor.getColumnIndex(UserEntry.COLUMN_NAME)));
				user.setPhone(cursor.getString(cursor.getColumnIndex(UserEntry.COLUMN_PHONE)));
				
				result.add(user);
				
			} while (cursor.moveToNext());
		}
		
		db.close();
		
		return result.get(0);
	}
	
	public static List<String> getAsString (Context context) {
		if (context==null)
			return null;
		
		SQLiteDatabase db = new SQLiteDBHelper(context).getReadableDatabase();
		
		Cursor cursor = db.query(UserEntry.TABLE_NAME, null, null, null, null, null, UserEntry._ID);
		
		List<String> result = new ArrayList<String>();;

		if (cursor.moveToFirst()) {
			String dado;
			do {
				dado = cursor.getString(cursor.getColumnIndex(UserEntry.COLUMN_NAME)) + 
				" (" + cursor.getColumnIndex(UserEntry.COLUMN_PHONE) + ")";
				
				result.add(dado);
				
			} while (cursor.moveToNext());
		}
		
		db.close();
		
		return result;
	}
}
