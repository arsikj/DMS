package com.ki.dms.contacts;

import java.util.ArrayList;

import com.ki.dms.contacts.model.Contact;

import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.provider.ContactsContract;

/*
 * Data access object for reading contacts from phonebook
 */
public class ContactsDao {

	private Activity activity;
	private ContactsLoadListener contactsLoadListener;

	public ContactsDao(Activity activity,
			ContactsLoadListener contactsLoadListener) {
		super();
		this.activity = activity;
		this.contactsLoadListener = contactsLoadListener;
		getcontacts();
	}

	//gets all contacts
	private void getcontacts() {
		(new Thread(new Runnable() {
			@Override
			public void run() {
				ContentResolver cr = activity.getContentResolver();
				String[] projection = {
						ContactsContract.CommonDataKinds.Phone.NUMBER,
						ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME };
				Cursor cur = cr.query(
						ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
						projection,
						ContactsContract.CommonDataKinds.Phone.HAS_PHONE_NUMBER
								+ " = 1", null,
						ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
				final ArrayList<Contact> contacts = new ArrayList<Contact>();
				while (cur.moveToNext()) {
					Contact contact = new Contact();
					contact.setNumber(cur.getString(0));
					contact.setDisplayName(cur.getString(1));
					contacts.add(contact);
				}
				cur.close();
				activity.runOnUiThread(new Runnable() {
					@Override
					public void run() {
						ContactsDao.this.contactsLoadListener
								.onContactsLoad(contacts);
					}
				});

			}
		})).start();
	}
}
