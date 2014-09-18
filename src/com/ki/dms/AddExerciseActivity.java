package com.ki.dms;

import java.util.Date;

import com.ki.dms.db.ExerciseTable;
import com.ki.dms.model.Exercise;
import com.ki.dms.model.User;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

/**
 * Activity responsible for exercise_for.xml view
 * 
 * @author DMS team
 * 
 */
public class AddExerciseActivity extends Activity implements
		OnCheckedChangeListener {
	/**
	 * Ids for radio buttons
	 */
	private static final int[] radionIds = { R.id.radio_walk,
			R.id.radio_bicycle, R.id.radio_jog, R.id.radio_run, R.id.radio_swim };
	/**
	 * Type of exercise
	 */
	String exercise = "Walking";
	User user;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		/**
		 * Setting view
		 */
		setContentView(R.layout.exercise_form);

		for (Integer id : radionIds) {// set listener for radio buttons
			RadioButton rb = (RadioButton) findViewById(id);
			rb.setOnCheckedChangeListener(this);
		}
		// get user
		user = getIntent().getParcelableExtra("USER");
	}

	/**
	 * on checked change listener
	 */
	@Override
	public void onCheckedChanged(CompoundButton button, boolean checked) {
		if (checked) {
			exercise = button.getText().toString();
		}
	}

	/**
	 * On click listener for save button
	 * 
	 * @param view
	 *            object button
	 */
	public void Save(View view) {
		// get duration
		String durationText = ((EditText) findViewById(R.id.exercise_duration))
				.getText().toString();
		if (durationText.trim().length() == 0) {
			Toast.makeText(this, "Invalid input", Toast.LENGTH_SHORT).show();
			return;
		}
		int duration;
		try {// parsing
			duration = Integer.parseInt(durationText);
		} catch (NumberFormatException e) {
			Toast.makeText(this, "Invalid input", Toast.LENGTH_SHORT).show();
			return;
		}
		// creating exercise and saving to database
		Exercise excersise = new Exercise((new Date()).getTime(), duration,
				exercise);
		ExerciseTable table = new ExerciseTable(this);
		table.open();
		table.saveExcersise(user.getId(), excersise);
		table.close();
		finish();
	}
}
