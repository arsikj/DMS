package com.ki.dms;

import java.util.ArrayList;

import com.ki.dms.model.User;

import android.app.Service;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class UserListAdapter extends BaseAdapter {
	LayoutInflater inflater;
	ArrayList<User> users;

	public UserListAdapter(Context context, ArrayList<User> users) {
		this.inflater = (LayoutInflater) context
				.getSystemService(Service.LAYOUT_INFLATER_SERVICE);
		this.users = users;
	}

	@Override
	public int getCount() {
		return users.size();
	}

	@Override
	public Object getItem(int indx) {
		return users.get(indx);
	}

	@Override
	public long getItemId(int id) {
		return id;
	}

	@Override
	public View getView(int pos, View convertView, ViewGroup parent) {
		Holder holder;
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.user_list_item, parent,
					false);
			holder = new Holder(convertView);
			convertView.setTag(holder);
		} else {
			holder = (Holder) convertView.getTag();
		}
		holder.name.setText(users.get(pos).getName());
		return convertView;
	}

	static class Holder {
		ImageView icon;
		TextView name;

		public Holder(View view) {
			icon = (ImageView) view.findViewById(R.id.user_item_icon);
			name = (TextView) view.findViewById(R.id.user_item_name);
		}
	}

}
