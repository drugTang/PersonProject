package com.lex.app.adapter;

import com.lex.app.R;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class LeftMenuListViewAdapter extends BaseAdapter {
	private String[] menus = {"我的图书馆","我的订单","我的评论","我的分享","我的说说","设置"};
	private Context mContext;
	
	public LeftMenuListViewAdapter(Context context) {
		this.mContext = context;
	}

	@Override
	public int getCount() {
		return menus.length;
	}

	@Override
	public String getItem(int position) {
		return menus[position];
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if(convertView == null) {
			convertView = View.inflate(mContext, R.layout.left_menu_list_view_item, null);
		}
		TextView text = (TextView) convertView.findViewById(R.id.tv_menu);
		String str = menus[position];
		text.setText(str);
		return convertView;
	}

}