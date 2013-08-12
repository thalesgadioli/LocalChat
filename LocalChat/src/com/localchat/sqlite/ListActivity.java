package com.localchat.sqlite;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.localchat.bd.User;
import com.localchat.bd.UserFacade;
import com.thalesgadioli.sqlite.R;

public class ListActivity extends Activity implements OnItemClickListener {
	
	private ListView mList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list);
		
		List<User> users = UserFacade.get(this);
		
		if (users!=null && users.size() > 0) {
			mList = (ListView) findViewById(R.id.listView1);
			
			ArrayAdapter<User> mAdapter = new ArrayAdapter<User>(this, android.R.layout.simple_list_item_1, users);
			
			mList.setAdapter(mAdapter);
			mList.setOnItemClickListener(this);
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

}
