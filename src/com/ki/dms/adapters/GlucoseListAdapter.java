package com.ki.dms.adapters;
/*
 * Adapter for glucose list
 */
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ki.dms.R;
import com.ki.dms.model.Glucose;

public class GlucoseListAdapter extends BaseAdapter {

	ArrayList<Glucose> measures;
	LayoutInflater inflater;

	public GlucoseListAdapter(ArrayList<Glucose> measures,
			LayoutInflater inflater) {
		this.measures = measures;
		this.inflater = inflater;
	}

	//adds a glucose reading
	public void addMeasure(Glucose glucose) {
		measures.add(glucose);
		notifyDataSetChanged();
	}

	//return number of glucose readings for specific user
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
			convertView = inflater.inflate(R.layout.glucose_list_item, parent,
					false);
			holder = new Holder(convertView);
			convertView.setTag(holder);
		} else {
			holder = (Holder) convertView.getTag();
		}
		holder.level.setText(measures.get(index).getLevel() + "");
		holder.date.setText(getFormatDate(measures.get(index).getDate()));
		holder.reason.setText(measures.get(index).getReason());
		return convertView;
	}

	@SuppressLint("SimpleDateFormat")
	private String getFormatDate(Date date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm");
		return dateFormat.format(date);
	}

	static class Holder {
		TextView level, date, reason;

		public Holder(View view) {
			level = (TextView) view.findViewById(R.id.glucose_item_level);
			date = (TextView) view.findViewById(R.id.glucose_item_date);
			reason = (TextView) view.findViewById(R.id.glucose_item_reason);
		}
	}
}
