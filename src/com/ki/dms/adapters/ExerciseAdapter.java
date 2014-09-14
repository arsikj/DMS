package com.ki.dms.adapters;

import java.util.ArrayList;
import java.util.Date;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ki.dms.R;
import com.ki.dms.model.Exercise;

public class ExerciseAdapter extends BaseAdapter {

	ArrayList<Exercise> measures;
	LayoutInflater inflater;

	public ExerciseAdapter(ArrayList<Exercise> measures, LayoutInflater inflater) {
		this.measures = measures;
		this.inflater = inflater;
	}

	public void addMeasure(Exercise exercise) {
		measures.add(exercise);
		notifyDataSetChanged();
	}

	@Override
	public int getCount() {
		return measures.size();
	}

	@Override
	public Object getItem(int index) {
		return measures.get(index);
	}

	@Override
	public long getItemId(int arg0) {
		return arg0;
	}

	@Override
	public View getView(int index, View convertView, ViewGroup parent) {
		Holder holder;

		if (convertView == null) {
			convertView = inflater.inflate(R.layout.exercise_list_item, parent,
					false);
			holder = new Holder(convertView);
			convertView.setTag(holder);
		} else {
			holder = (Holder) convertView.getTag();
		}
		holder.duration.setText(measures.get(index).getDuration() + "mins.");
		holder.date.setText(getDistance(measures.get(index).getDate()));
		holder.type.setText(measures.get(index).getType());
		return convertView;
	}

	private String getDistance(Date date) {
		int ms = (int) ((new Date()).getTime() - date.getTime());
		int min = ms / 60000;
		if (min == 0) {
			return "less tha 1 minute ago";
		}
		int hours = min / 60;
		if (hours == 0) {
			return min + " minutes ago";
		}
		int days = hours / 24;
		if (days == 0) {
			return hours + " hours ago";
		}
		int weeks = days / 7;
		if (weeks == 0) {
			return days + " days ago";
		}
		return weeks + " weeks ago";
	}

	static class Holder {
		TextView duration, date, type;

		public Holder(View view) {
			duration = (TextView) view
					.findViewById(R.id.exercise_item_duration);
			date = (TextView) view.findViewById(R.id.exercise_item_date);
			type = (TextView) view.findViewById(R.id.exercise_item_type);
		}
	}
}
