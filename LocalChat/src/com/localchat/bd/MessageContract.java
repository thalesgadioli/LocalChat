package com.localchat.bd;
import android.provider.BaseColumns;


public class MessageContract {

	public MessageContract () {
	}
	
	public static abstract class MessageEntry implements BaseColumns {
		
		public static final String TABLE_NAME = "message";
		
		public static final String COLUMN_TEXTO = "texto";
		public static final String COLUMN_REM = "remetente";
		public static final String COLUMN_DEST = "destinatario";
		public static final String COLUMN_DATA = "dt_enviada";
	}
}
