package com.localchat.sqlite;

import android.app.Activity;
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

public class CadastroActivity extends Activity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cadastro);
		Button botao1 = (Button) findViewById(R.id.button3);
		botao1.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View arg0) {
		
		if (arg0.getId() == R.id.button3) {
			
			User user = new User();
			
			EditText et1 = (EditText) findViewById(R.id.editText1);
			EditText et2 = (EditText) findViewById(R.id.editText2);
			
			String nome = et1.getText().toString();
			String fone = et2.getText().toString();
			
			user.setName(nome);
			user.setPhone(fone);
			
			/*long id = */UserFacade.insert(user, this);
			
			Toast.makeText(this, "Operacao realizada com sucesso!", Toast.LENGTH_SHORT).show();
			et1.setText("");
			et2.setText("");
			
		} 
	}
	

}
