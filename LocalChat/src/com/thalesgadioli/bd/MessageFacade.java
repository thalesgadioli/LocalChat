package com.thalesgadioli.bd;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.thalesgadioli.bd.MessageContract.MessageEntry;

public class MessageFacade {
	
	public static long insert (Message msg, Context context) {
		
		if (context==null)
			return -1;
		
		SQLiteDatabase db = new SQLiteDBHelper(context).getWritableDatabase();
		
		ContentValues values = new ContentValues();
		
		values.put(MessageEntry.COLUMN_DATA, msg.getDt_enviada());
		values.put(MessageEntry.COLUMN_DEST, msg.getDest().getId());
		values.put(MessageEntry.COLUMN_REM, msg.getRem().getId());
		values.put(MessageEntry.COLUMN_TEXTO, msg.getTexto());
		
		long result = -1;
		
		if (msg.getId() > 0)
			result = db.update(MessageEntry.TABLE_NAME, values, String.format("%s = %s ", MessageEntry._ID, msg.getId()), null);
		else {
			result = db.insert(MessageEntry.TABLE_NAME, null, values);
			msg.setId(result);
		}
		
		db.close();
		
		return result;
	}
	
	public static int delete (Context context) {
		if (context==null)
			return -1;
		
		SQLiteDatabase db = new SQLiteDBHelper(context).getWritableDatabase();
		int result = db.delete(MessageEntry.TABLE_NAME, null, null);
		
		db.close();
		
		return result;
	}
	
	public static int deleteById (Context context, Long id) {
		if (context==null)
			return -1;
		
		SQLiteDatabase db = new SQLiteDBHelper(context).getWritableDatabase();
	
		String[] args = new String[]{id.toString()};
		
		int result = db.delete(MessageEntry.TABLE_NAME, MessageEntry._ID + " = ?", args);
		
		db.close();
		
		return result;
	}
	
	public static List<Message> get (Context context) {
		if (context==null)
			return null;
		
		SQLiteDatabase db = new SQLiteDBHelper(context).getReadableDatabase();
		
		Cursor cursor = db.query(MessageEntry.TABLE_NAME, null, null, null, null, null, MessageEntry._ID);
		
		List<Message> result = new ArrayList<Message>();

		if (cursor.moveToFirst()) {
			Message msg;
			do {
				msg = new Message();
				msg.setId(cursor.getLong(cursor.getColumnIndex(MessageEntry._ID)));
				msg.setDest(UserFacade.getById(context,(cursor.getLong(cursor.getColumnIndex(MessageEntry.COLUMN_DEST)))));
				msg.setRem(UserFacade.getById(context,(cursor.getLong(cursor.getColumnIndex(MessageEntry.COLUMN_REM)))));
				
				msg.setDt_enviada((cursor.getString(cursor.getColumnIndex(MessageEntry.COLUMN_DATA))));
				msg.setTexto((cursor.getString(cursor.getColumnIndex(MessageEntry.COLUMN_TEXTO))));
				
				result.add(msg);
				
			} while (cursor.moveToNext());
		}
		
		db.close();
		
		return result;
	}
	
	public static List<Message> getByIds (Context context, Long id1, Long id2) {
		if (context==null)
			return null;
		
		SQLiteDatabase db = new SQLiteDBHelper(context).getReadableDatabase();
		
		 String[] args = new String[]{id1.toString(), id2.toString()};      
		
		Cursor cursor = db.query(MessageEntry.TABLE_NAME, null, "remetente = ? and destinatario = ?", args, null, null, MessageEntry._ID);
		
		List<Message> result = new ArrayList<Message>();

		if (cursor.moveToFirst()) {
			Message msg;
			do {
				msg = new Message();
				msg.setId(cursor.getLong(cursor.getColumnIndex(MessageEntry._ID)));
				msg.setDest(UserFacade.getById(context,(cursor.getLong(cursor.getColumnIndex(MessageEntry.COLUMN_DEST)))));
				msg.setRem(UserFacade.getById(context,(cursor.getLong(cursor.getColumnIndex(MessageEntry.COLUMN_REM)))));
				
				msg.setDt_enviada((cursor.getString(cursor.getColumnIndex(MessageEntry.COLUMN_DATA))));
				msg.setTexto((cursor.getString(cursor.getColumnIndex(MessageEntry.COLUMN_TEXTO))));
				
				result.add(msg);
				
			} while (cursor.moveToNext());
		}
		
		db.close();
		
		return result;
	}
	
	public static List<String> getAsString (Context context) {
		if (context==null)
			return null;
		
		SQLiteDatabase db = new SQLiteDBHelper(context).getReadableDatabase();
		
		Cursor cursor = db.query(MessageEntry.TABLE_NAME, null, null, null, null, null, MessageEntry._ID);
		
		List<String> result = new ArrayList<String>();
		
		User rem = new User();
		//User dest = new User();

		if (cursor.moveToFirst()) {
			String dado;
			do {
				
				//dest = UserFacade.getById(context, cursor.getLong(cursor.getColumnIndex(MessageEntry.COLUMN_DEST)));
				rem = UserFacade.getById(context, cursor.getLong(cursor.getColumnIndex(MessageEntry.COLUMN_REM)));
				
				String data = (cursor.getString(cursor.getColumnIndex(MessageEntry.COLUMN_DATA)));
				
				
				dado = "De: " + rem.getName() + " " + cursor.getString(cursor.getColumnIndex(MessageEntry.COLUMN_TEXTO))
						+ " no dia: " + data ; 
				
				result.add(dado);
				
			} while (cursor.moveToNext());
		}
		
		db.close();
		
		return result;
	}
}
