package com.ki.dms;

import java.util.ArrayList;
import com.ki.dms.db.UserTable;
import com.ki.dms.model.User;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;
import android.widget.Toast;

public class Login extends Activity implements OnItemClickListener,
		OnItemLongClickListener {

	ListView listView;
	UserListAdapter adapter;
	ArrayList<User> users;
	UserTable ut;

	private static final int ADD_USER_REQ_CODE = 4321;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		listView = (ListView) findViewById(R.id.dms_list_users);
		ut = new UserTable(this);

		ut.open();
		users = ut.allUsers();
		ut.close();

		adapter = new UserListAdapter(this, users);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(this);
		listView.setOnItemLongClickListener(this);

	}

	public void onClick(View view) {
		if (users.size() < 9) {
			Intent intent = new Intent(this, AddNewUser.class);
			startActivityForResult(intent, ADD_USER_REQ_CODE);
		} else {
			Toast.makeText(this, R.string.login_toast_add_new_user_negative,
					Toast.LENGTH_LONG).show();
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == RESULT_OK) {
			switch (requestCode) {
			case ADD_USER_REQ_CODE:
				refreshList();
				break;
			}
		}
		super.onActivityResult(requestCode, resultCode, data);
	}

	private void refreshList() {
		ut.open();
		users.add(ut.getlastUser());
		ut.close();
		adapter.notifyDataSetChanged();
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
		/*
		 * Toast.makeText(this, users.get(pos).getName(), Toast.LENGTH_SHORT)
		 * .show();
		 */
		User user = users.get(pos);
		Intent intent = new Intent(this, MainActivity.class);
		intent.putExtra("tag", user);
		startActivity(intent);

	}

	@Override
	public boolean onItemLongClick(AdapterView<?> parent, View view,
			final int position, long id) {
		AlertDialog.Builder builder = new AlertDialog.Builder(Login.this);
		final int pos = position;
		builder.setTitle(R.string.alert_dialog_options_title);
		builder.setItems(R.array.options_array,
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						ut.open().deleteRow(users.get(pos).getId());
						ut.close();

						users.remove(adapter.getItem(pos));

						adapter.notifyDataSetChanged();
					}
				});

		builder.show();
		return true;
	}
}
