package com.thalesgadioli.bd;
import android.provider.BaseColumns;


public class UserContract {

	public UserContract () {
	}
	
	public static abstract class UserEntry implements BaseColumns {
		
		public static final String TABLE_NAME = "user";
		
		public static final String COLUMN_NAME = "nome";
		public static final String COLUMN_PHONE = "phone";
	}
}
