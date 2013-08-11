package com.thalesgadioli.main;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.thalesgadioli.bd.User;
import com.thalesgadioli.bd.UserFacade;

public class ListActivity extends Activity implements OnItemClickListener, OnItemLongClickListener {
	
	private ListView mList;
	private Long id;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list);
		
		SharedPreferences pref = getSharedPreferences(MainActivity.PREF_NAME, MODE_PRIVATE);
		/*Long*/ id = pref.getLong(MainActivity.PREF_USER_ID, 0);
		
		List<User> users = UserFacade.getWithoutId(this, id);
		
		if (users!=null && users.size() > 0) {
			mList = (ListView) findViewById(R.id.listView1);
			
			ArrayAdapter<User> mAdapter = new ArrayAdapter<User>(this, android.R.layout.simple_list_item_1, users);
			
			mList.setAdapter(mAdapter);
			mList.setOnItemClickListener(this);
			mList.setOnItemLongClickListener(this);
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.list, menu);
		return true;
	}

	@Override
	public void onItemClick(AdapterView<?> adapter, View view, int position, long id) {
		User user = (User) adapter.getItemAtPosition(position);

		Intent intent = new Intent(this, ChatActivity.class);
		intent.putExtra("dest_id", user.getId());
		startActivity(intent);
	}

	@Override
	public boolean onItemLongClick(AdapterView<?> adapter, View view, int position,
			long id) {
		User user = (User) adapter.getItemAtPosition(position);

		Intent intent = new Intent(this, CadastroActivity.class);
		intent.putExtra("id_user", user.getId());
		startActivity(intent);
		
		return false;
	}
	
	@Override
	protected void onRestart() {
		//SharedPreferences pref = getSharedPreferences(MainActivity.PREF_NAME, MODE_PRIVATE);
		//Long id = pref.getLong(MainActivity.PREF_USER_ID, 0);
		
		List<User> users = UserFacade.getWithoutId(this, id);
		
		if (users!=null && users.size() > 0) {
			mList = (ListView) findViewById(R.id.listView1);
			
			ArrayAdapter<User> mAdapter = new ArrayAdapter<User>(this, android.R.layout.simple_list_item_1, users);
			
			mList.setAdapter(mAdapter);
			mList.setOnItemClickListener(this);
			mList.setOnItemLongClickListener(this);
		}
		super.onRestart();
	}
}
