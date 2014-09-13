package com.ki.dms;

import java.util.ArrayList;
import java.util.Date;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.ki.dms.adapters.GlucoseListAdapter;
import com.ki.dms.db.GlucoseTable;
import com.ki.dms.model.Glucose;
import com.ki.dms.model.User;

@SuppressLint("ValidFragment")
public class GlucoseFragment extends Fragment implements OnClickListener {

	ListView listView;
	GlucoseListAdapter adapter;
	User user;
	GlucoseTable glucoseTable;
	Glucose todayMeasure;

	public GlucoseFragment() {
	}

	public GlucoseFragment(User user) {
		this.user = user;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.glucose_fragment, container,
				false);
		listView = (ListView) view.findViewById(R.id.glucose_list);
		glucoseTable = new GlucoseTable(getActivity());
		glucoseTable.open();
		glucoseTable.populateMeasuresForUser(user);
		glucoseTable.close();

		ArrayList<Glucose> measures = user.getMeasures();
		// TODO uncomment
		// if (measures.size() != 0) {
		// Date lastMeasure = measures.get(measures.size() - 1).getDate();
		// Calendar calendar = Calendar.getInstance();
		// Calendar today = Calendar.getInstance();
		// calendar.setTime(lastMeasure);
		// today.setTime(new Date());
		//
		// if (calendar.get(Calendar.DAY_OF_YEAR) == today
		// .get(Calendar.DAY_OF_YEAR)) {
		// ((LinearLayout) view.findViewById(R.id.glucose_form))
		// .setVisibility(LinearLayout.GONE);
		// }
		// }

		adapter = new GlucoseListAdapter(user.getMeasures(), getActivity()
				.getLayoutInflater());
		listView.setStackFromBottom(true);
		listView.setAdapter(adapter);

		((Button) view.findViewById(R.id.glucose_measure_btn))
				.setOnClickListener(this);

		return view;
	}

	@Override
	public void onClick(View arg0) {
		EditText editText = (EditText) getView().findViewById(
				R.id.glucose_measure);
		editText.clearFocus();// TODO close keyboard
		String measure = editText.getText().toString();
		int m;
		try {
			m = Integer.parseInt(measure);
		} catch (NumberFormatException e) {
			Toast.makeText(getActivity(), "Not a valid value.",
					Toast.LENGTH_SHORT).show();
			return;
		}
		if (m < 0 || m > 999) {
			Toast.makeText(getActivity(), "Not a valid value.",
					Toast.LENGTH_SHORT).show();
		} else {
			String reason = "No reason.";
			AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
			if (m < user.getLowGlucose()) {

				builder.setTitle("Low glucose level");
				builder.setIcon(android.R.drawable.ic_dialog_alert);
				builder.setMessage("Please, eat a sugar source, take medicine, and eat meals and snacks as described by your doctor.");
				builder.setPositiveButton("Ok", null);

				addMeasure();
			} else if (m > user.getHighGlucose()) {
				builder.setTitle("High glucose level");
				builder.setIcon(android.R.drawable.ic_dialog_alert);
				final EditText input = new EditText(getActivity());
				input.setHint("Input reason");
				builder.setView(input);
				builder.setPositiveButton("Ok",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								todayMeasure.setReason(input.getText()
										.toString());
								addMeasure();
							}
						});
				builder.setNegativeButton("Cancel",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								addMeasure();

							}
						});
				builder.setNeutralButton("Call " + user.getDoctorName(),
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								todayMeasure.setReason(input.getText()
										.toString());
								addMeasure();
								Intent intent = new Intent(Intent.ACTION_CALL);
								intent.setData(Uri.parse("tel:"
										+ user.getDoctorNumber()));
								startActivity(intent);
							}
						});
			} else {
				// normal
				builder.setTitle("Congratulations");
				builder.setIcon(android.R.drawable.ic_dialog_info);
				builder.setMessage("Your measure is in normal range.");
				builder.setPositiveButton("Ok", null);
				reason = "Glucose level normal.";
				addMeasure();
			}
			builder.show();
			todayMeasure = new Glucose((new Date()).getTime(), m, reason);

			((LinearLayout) getView().findViewById(R.id.glucose_form))
					.setVisibility(LinearLayout.GONE);
		}
	}

	private void addMeasure() {
		glucoseTable.open();
		glucoseTable.saveMeasure(user.getId(), todayMeasure);
		glucoseTable.close();
		adapter.addMeasure(todayMeasure);
	}
}
