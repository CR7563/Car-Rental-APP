package com.pony.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lease.R;

public class ChoiceMessageAdapter extends BaseAdapter {
	private LayoutInflater inflater;
	private List<String> list_result;
	private int posIndex;
	private Context mContext;

	public ChoiceMessageAdapter(Context context, List<String> list_result) {
		mContext = context;
		inflater = LayoutInflater.from(context);
		this.list_result = list_result;
	}

	@Override
	public int getCount() {
		return list_result.size();
	}

	@Override
	public Object getItem(int position) {
		return list_result.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.item_choice_money, null);
			holder = new ViewHolder();
			holder.message_name = (TextView) convertView.findViewById(R.id.message_name);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		try {
			holder.message_name.setText(list_result.get(position));
		} catch (Exception e) {
		}

		return convertView;

	}

	private class ViewHolder {
		private TextView message_name;
	}

	public void setPos(int pos) {
		posIndex = pos;
	}

}
