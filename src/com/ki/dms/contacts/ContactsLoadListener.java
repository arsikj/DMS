package com.ki.dms.contacts;

import java.util.ArrayList;

import com.ki.dms.contacts.model.Contact;

public interface ContactsLoadListener {

	public void onContactsLoad(ArrayList<Contact> contacts);
}
