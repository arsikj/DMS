package com.ki.dms;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.ki.dms.model.User;

public class MainActivity extends Activity implements OnItemClickListener {

	DrawerLayout drawerLayout;
	ListView drawerListView;
	User user;
	ActionBarDrawerToggle toggle;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		user = getIntent().getParcelableExtra("tag");

		drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		drawerListView = (ListView) findViewById(R.id.drawer_list);

		drawerListView.setAdapter(new ArrayAdapter<String>(this,
				R.layout.drawer_list_item, getResources().getStringArray(
						R.array.drawer_list)));
		drawerListView.setOnItemClickListener(this);
		setTitle(user.getName());
		toggle = new ActionBarDrawerToggle(this, drawerLayout,
				R.drawable.ic_drawer_open, R.string.drawer_open,
				R.string.drawer_close) {
			@Override
			public void onDrawerOpened(View drawerView) {
				super.onDrawerOpened(drawerView);
				// change title
			}

			@Override
			public void onDrawerClosed(View drawerView) {
				super.onDrawerClosed(drawerView);
				// change title
			}
		};

		drawerLayout.setDrawerListener(toggle);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setHomeButtonEnabled(true);
		if (savedInstanceState == null) {
			switchFragment(0);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		switchFragment(position);

	}

	private void switchFragment(int position) {
		drawerLayout.closeDrawer(drawerListView);
		Fragment fragment = new GlucoseFragment(user);// CASE 0
		switch (position) {
		case 1:
			fragment = new ExerciseFragment(user);
			break;
		case 2:
			startActivity(new Intent(this, Help.class));
			break;
		case 3:
			finish();
			return;
		}
		getFragmentManager().beginTransaction()
				.replace(R.id.content_frame, fragment).commit();
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		// Sync the toggle state after onRestoreInstanceState has occurred.
		toggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		toggle.onConfigurationChanged(newConfig);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Pass the event to ActionBarDrawerToggle, if it returns
		// true, then it has handled the app icon touch event
		if (toggle.onOptionsItemSelected(item)) {
			return true;
		}
		// Handle your other action bar items...

		return super.onOptionsItemSelected(item);
	}
}
