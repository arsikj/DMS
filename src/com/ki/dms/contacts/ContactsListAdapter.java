package com.ki.dms.contacts;

/*
 * Adapter for contact list
 */

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ki.dms.R;
import com.ki.dms.contacts.model.Contact;

public class ContactsListAdapter extends BaseAdapter {

	ArrayList<Contact> contacts = new ArrayList<Contact>();
	LayoutInflater li;

	public ContactsListAdapter(Context context) {
		li = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	public void setContacts(ArrayList<Contact> contacts) {
		this.contacts.clear();
		this.contacts.addAll(contacts);
	}

	public ArrayList<Contact> getContacts() {
		return contacts;
	}

	@Override
	public int getCount() {
		return contacts.size();
	}

	@Override
	public Object getItem(int position) {
		return contacts.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		Holder holder = null;
		if (convertView == null) {
			convertView = li.inflate(R.layout.contact_view, parent, false);
			holder = new Holder(convertView);
			convertView.setTag(holder);
		} else {
			holder = (Holder) convertView.getTag();
		}

		holder.setDisplayName(contacts.get(position).getDisplayName());
		holder.setNumber(contacts.get(position).getNumber());

		return convertView;
	}

	public static class Holder {
		TextView displayName;
		TextView number;

		public Holder(View view) {
			displayName = (TextView) view.findViewById(R.id.contact_display_name);
			number = (TextView) view.findViewById(R.id.contact_number);
		}

		public void setDisplayName(String displayName) {
			this.displayName.setText(displayName);
		}

		public void setNumber(String number) {
			this.number.setText(number);
		}
	}
}
