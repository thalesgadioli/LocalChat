package com.thalesgadioli.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.thalesgadioli.bd.User;
import com.thalesgadioli.bd.UserFacade;

public class CadastroActivity extends Activity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cadastro);
		Button botao1 = (Button) findViewById(R.id.button3);
		botao1.setOnClickListener(this);
		
		Intent intent = getIntent();
		
		if (intent.getSerializableExtra("id_user") != null) {
			
			EditText et1 = (EditText) findViewById(R.id.editText1);
			EditText et2 = (EditText) findViewById(R.id.editText2);
		
			Long id = (Long) intent.getSerializableExtra("id_user");
			
			User user = UserFacade.getById(this, id);
			et1.setText(user.getName());
			et2.setText(user.getPhone());
			
		}
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
			
			Intent intent = getIntent();
			
			if (intent.getSerializableExtra("id_user") != null)
				user.setId((Long)intent.getSerializableExtra("id_user"));
			
			/*long id = */UserFacade.insert(user, this);
			
			Toast.makeText(this, "Operacao realizada com sucesso!", Toast.LENGTH_SHORT).show();
			et1.setText("");
			et2.setText("");
			
		} 
	}
	
}
