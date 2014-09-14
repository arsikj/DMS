package com.ki.dms;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

public class ExerciseFragment extends Fragment implements OnClickListener {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.exercise_fragment, container,
				false);
		((Button) view.findViewById(R.id.exercise_btn_add))
				.setOnClickListener(this);
		return view;
	}

	@Override
	public void onClick(View arg0) {
		startActivity(new Intent(getActivity(), AddExerciseActivity.class));

	}
}
