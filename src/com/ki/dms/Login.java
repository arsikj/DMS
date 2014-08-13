package com.ki.dms;

import java.util.ArrayList;

import com.ki.dms.model.User;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

public class Login extends Activity implements OnItemClickListener {

	ListView listView;
	UserListAdapter adapter;
	ArrayList<User> users;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		listView = (ListView) findViewById(R.id.dms_list_users);
		users = new ArrayList<User>();
		users.add(new User("Deki"));
		users.add(new User("Boki"));
		adapter = new UserListAdapter(this, users);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(this);
	}

	public void onClick(View view) {
		// Add new user
		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
		Toast.makeText(this, users.get(pos).getName(), Toast.LENGTH_SHORT)
				.show();

	}
}
