package com.thalesgadioli.sqlite;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.thalesgadioli.bd.Message;
import com.thalesgadioli.bd.MessageFacade;
import com.thalesgadioli.bd.UserFacade;

public class ChatActivity extends Activity implements OnClickListener {
	
	private ListView mList;
	
	private long id_dest;
	private long id;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_chat);
		
		AtualizaChat();
		
		Button botao1 = (Button) findViewById(R.id.button4);
		botao1.setOnClickListener(this);
	}
	
	public void AtualizaChat() {
		
		Intent intent = getIntent();
		id_dest = (Long) intent.getSerializableExtra("dest_id");
		
		SharedPreferences pref = getSharedPreferences(MainActivity.PREF_NAME, MODE_PRIVATE);
		id = pref.getLong(MainActivity.PREF_USER_ID, 0);
		
		List<Message> msgs = MessageFacade.getByIds(this, id, id_dest);
		if (msgs!=null && msgs.size() > 0) {
			mList = (ListView) findViewById(R.id.listView2);
			ArrayAdapter<Message> mAdapter = new ArrayAdapter<Message>(this, android.R.layout.simple_list_item_1, msgs);
			mList.setAdapter(mAdapter);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.chat, menu);
		return true;
	}

	@Override
	public void onClick(View arg0) {
		if (arg0.getId() == R.id.button4) {
			
			Message msg = new Message();
			
			EditText et1 = (EditText) findViewById(R.id.editText4);
			
			String texto = et1.getText().toString();
			
			msg.setTexto(texto);
			
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			
			msg.setDt_enviada(formatter.format(new Date()));
			
			msg.setRem(UserFacade.getById(this, id));
			msg.setDest(UserFacade.getById(this, id_dest));
			
			MessageFacade.insert(msg, this);
			
			et1.setText("");
			AtualizaChat();
			
		} 
		
	}

}
