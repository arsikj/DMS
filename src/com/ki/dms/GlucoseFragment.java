package com.ki.dms;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

public class GlucoseFragment extends Fragment {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		if (savedInstanceState == null) {
			YesNoDialog();
		}
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.glucose_fragment, container,
				false);
		return view;
	}

	private void YesNoDialog() {
		AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());

		dialog.setTitle("Glucose daily reading");
		dialog.setMessage("Have you taken your blood sugar reading today?");
		dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// prikazi bla
				Toast.makeText(getActivity(), "Yes", Toast.LENGTH_SHORT).show();
			}
		});
		dialog.setNegativeButton("No", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO toast i pa isto kako gore
				Toast.makeText(getActivity(), "No", Toast.LENGTH_SHORT).show();
			}
		});
		dialog.setCancelable(false);
		dialog.show();
	}
}
