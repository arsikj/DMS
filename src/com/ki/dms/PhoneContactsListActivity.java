package com.ki.dms;

import java.util.ArrayList;

import android.app.Activity;
import android.app.SearchManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;

import com.ki.dms.contacts.ContactsDao;
import com.ki.dms.contacts.ContactsListAdapter;
import com.ki.dms.contacts.ContactsLoadListener;
import com.ki.dms.contacts.model.Contact;

public class PhoneContactsListActivity extends Activity implements
		ContactsLoadListener, OnItemClickListener, OnQueryTextListener {

	ListView lv;
	ContactsListAdapter adapter;
	final ArrayList<Contact> contacts = new ArrayList<Contact>();

	public static final String DISPLAY_NAME = "DISPLAY_NAME";
	public static final String NUMBER = "NUMBER";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.contacts_list);
		lv = (ListView) findViewById(R.id.contacts_list);
		adapter = new ContactsListAdapter(this);
		lv.setAdapter(adapter);
		lv.setOnItemClickListener(this);
		new ContactsDao(this, this);
	}

	@Override
	public void onContactsLoad(ArrayList<Contact> contacts) {
		this.contacts.clear();
		this.contacts.addAll(contacts);

		adapter.setContacts(contacts);
		adapter.notifyDataSetChanged();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.search, menu);

		SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);

		SearchView searchView = (SearchView) menu.findItem(R.id.search)
				.getActionView();

		searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
		searchView.setIconifiedByDefault(false);
		
		searchView.setOnQueryTextListener(this);
		return true;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		Contact contact = adapter.getContacts().get(position);
		Intent data = new Intent();
		data.putExtra(DISPLAY_NAME, contact.getDisplayName());
		data.putExtra(NUMBER, contact.getNumber());
		setResult(RESULT_OK, data);
		finish();
	}

	@Override
	public boolean onQueryTextChange(String newText) {
		ArrayList<Contact> contacts = new ArrayList<Contact>();
		for (Contact contact : this.contacts) {
			if (contact.getDisplayName().contains(newText)) {
				contacts.add(contact);
			}
		}
		adapter.setContacts(contacts);
		adapter.notifyDataSetChanged();
		return true;
	}

	@Override
	public boolean onQueryTextSubmit(String arg0) {
		return false;
	}
}
