package com.ki.dms;

import com.ki.dms.adapters.ExerciseAdapter;
import com.ki.dms.db.ExerciseTable;
import com.ki.dms.model.User;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

/**
 * Fragment responsible for exercise_fragment.xml view
 * 
 * @author DMS team
 * 
 */
public class ExerciseFragment extends Fragment implements OnClickListener {

	ListView listView;
	ExerciseAdapter adapter;
	ExerciseTable exerciseTable;
	User user;

	public ExerciseFragment() {
		// empty constructor
	}

	public ExerciseFragment(User user) {// constructor with user
		this.user = user;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		exerciseTable = new ExerciseTable(getActivity());
		// setting view
		View view = inflater.inflate(R.layout.exercise_fragment, container,
				false);
		// set listener
		((Button) view.findViewById(R.id.exercise_btn_add))
				.setOnClickListener(this);
		listView = (ListView) view.findViewById(R.id.exercise_list);
		// populate data
		exerciseTable.open().populateExcersisesForUser(user).close();
		adapter = new ExerciseAdapter(user.getExcersises(), getActivity()
				.getLayoutInflater());
		listView.setAdapter(adapter);
		return view;
	}

	@Override
	public void onResume() {
		// refreshing table
		exerciseTable.open().populateExcersisesForUser(user).close();
		adapter = new ExerciseAdapter(user.getExcersises(), getActivity()
				.getLayoutInflater());
		listView.setAdapter(adapter);
		super.onResume();
	}

	@Override
	public void onClick(View arg0) {// start new activity
		Intent intent = new Intent(getActivity(), AddExerciseActivity.class);
		intent.putExtra("USER", user);
		startActivity(intent);
	}
}
