package com.ki.dms;

import com.ki.dms.model.User;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.TextView;

public class MainActivity extends Activity {

	TextView welcomeUser;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		User user = getIntent().getParcelableExtra("tag");
		welcomeUser = (TextView)findViewById(R.id.main_user);
		welcomeUser.setText(R.string.welcome_user+user.getName().toString());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
