package com.localchat.sqlite;

import com.thalesgadioli.sqlite.R;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener {
	
	public static final String PREF_NAME = "lChat_pref"; 
	
	public static final String PREF_USER_ID = "pref_userid";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Button botao1 = (Button) findViewById(R.id.button1);
		Button botao2 = (Button) findViewById(R.id.button2);
		
		botao1.setOnClickListener(this);
		botao2.setOnClickListener(this);
		
		SharedPreferences pref = getSharedPreferences(MainActivity.PREF_NAME, MODE_PRIVATE);
		long id = pref.getLong(MainActivity.PREF_USER_ID, 0);
		
		if (id == 0) {
			Intent intent = new Intent(this, PrimeiroCadastroActivity.class);
			startActivity(intent);
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View arg0) {
		
		if (arg0.getId() == R.id.button2) {
			Intent intent = new Intent(this, ListActivity.class);
			startActivity(intent);
		} else if (arg0.getId() == R.id.button1) {
			Intent intent = new Intent(this, CadastroActivity.class);
			startActivity(intent);
		} 
	}
	

}
