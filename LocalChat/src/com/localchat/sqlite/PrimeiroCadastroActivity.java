package com.localchat.sqlite;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.localchat.bd.User;
import com.localchat.bd.UserFacade;
import com.thalesgadioli.sqlite.R;

public class PrimeiroCadastroActivity extends Activity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_primeiro_cadastro);
		
		Button botao1 = (Button) findViewById(R.id.button5);
		botao1.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.primeiro_cadastro, menu);
		return true;
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		
if (arg0.getId() == R.id.button5) {
			
			User user = new User();
			
			EditText et1 = (EditText) findViewById(R.id.editText5);
			EditText et2 = (EditText) findViewById(R.id.editText6);
			
			String nome = et1.getText().toString();
			String fone = et2.getText().toString();
			
			user.setName(nome);
			user.setPhone(fone);
			
			long id = UserFacade.insert(user, this);
			
			SharedPreferences.Editor editor = getSharedPreferences(MainActivity.PREF_NAME, MODE_PRIVATE).edit();
			editor.putLong(MainActivity.PREF_USER_ID, id);
			editor.commit();
			
			Toast.makeText(this, "Operacao realizada com sucesso!", Toast.LENGTH_SHORT).show();
			
			Intent intent = new Intent(this, MainActivity.class);
			startActivity(intent);
			
		} 
		
	}

}
