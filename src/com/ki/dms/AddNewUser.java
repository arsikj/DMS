package com.ki.dms;

import com.ki.dms.db.UserTable;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddNewUser extends Activity {

	private static final int CONTACT_CODE = 1234;

	private EditText userName, ssn, doctorName, doctorNumber, low, high;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_new_user);
		userName = (EditText) findViewById(R.id.add_user_name_et);
		doctorName = (EditText) findViewById(R.id.add_user_doctor_et);
		doctorNumber = (EditText) findViewById(R.id.add_user_doctor_number_et);

		low = (EditText) findViewById(R.id.add_user_low);

		high = (EditText) findViewById(R.id.add_user_high);
		ssn = (EditText) findViewById(R.id.add_user_ssn_et);
	}

	public void getContact(View v) {
		startActivityForResult(
				new Intent(this, PhoneContactsListActivity.class), CONTACT_CODE);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

		if (resultCode == RESULT_OK) {
			switch (requestCode) {
			case CONTACT_CODE:
				String name = data
						.getStringExtra(PhoneContactsListActivity.DISPLAY_NAME);

				String number = data
						.getStringExtra(PhoneContactsListActivity.NUMBER);
				doctorName.setText(name);
				doctorNumber.setText(number);
				break;
			}
		}

		super.onActivityResult(requestCode, resultCode, data);
	}

	public void save(View v) {
		String name = userName.getText().toString();
		String SSN = ssn.getText().toString();
		String dName = doctorName.getText().toString();
		String dNumber = doctorNumber.getText().toString();
		String empty = null;
		if (name.trim().length() == 0) {
			empty = "name";
		}
		if (SSN.trim().length() == 0) {
			empty = "ssn";
		}
		if (dName.trim().length() == 0) {
			empty = "doctor name";
		}
		if (dNumber.trim().length() == 0) {
			empty = "doctor number";
		}
		if (empty != null) {
			Toast.makeText(this, "Not a valid value for " + empty,
					Toast.LENGTH_SHORT).show();
			return;
		}
		int lowGlucose = -1;
		int highGlucose;
		try {
			lowGlucose = Integer.parseInt(low.getText().toString());
			highGlucose = Integer.parseInt(high.getText().toString());
		} catch (NumberFormatException e) {
			Toast.makeText(
					this,
					"Not a valid value for "
							+ (lowGlucose == -1 ? "low glucose"
									: "high glucose"), Toast.LENGTH_SHORT)
					.show();
			return;
		}
		UserTable ut = new UserTable(this);
		ut.open();
		ut.insertRow(name, SSN, dName, dNumber, lowGlucose, highGlucose);
		ut.close();
		setResult(RESULT_OK);
		finish();
	}
}
