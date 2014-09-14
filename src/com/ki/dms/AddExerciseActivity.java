package com.ki.dms;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.RadioButton;

public class AddExerciseActivity extends Activity implements
		OnCheckedChangeListener {

	private static final int[] radionIds = { R.id.radio_walk,
			R.id.radio_bicycle, R.id.radio_jog, R.id.radio_run, R.id.radio_swim };
	String exercise;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.exercise_form);
		for (Integer id : radionIds) {
			RadioButton rb = (RadioButton) findViewById(id);
			rb.setOnCheckedChangeListener(this);
		}

	}

	@Override
	public void onCheckedChanged(CompoundButton button, boolean checked) {
		if (checked) {
			exercise = button.getText().toString();
		}
	}

	public void Save(View view) {
		// TODO save
	}
}
