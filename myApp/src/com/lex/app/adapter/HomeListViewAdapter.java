package com.lex.app.adapter;

import java.util.List;

import com.lex.app.BaseApplication;
import com.lex.app.R;
import com.lex.app.domain.HomeListData.NewsData;
import com.lidroid.xutils.BitmapUtils;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class HomeListViewAdapter extends BaseAdapter {
	private List<NewsData> mDataList;
	private BitmapUtils bitmapUtils;
	
	public HomeListViewAdapter(List<NewsData> list) {
		mDataList = list;
		bitmapUtils = new BitmapUtils(BaseApplication.getContext());
	}

	@Override
	public int getCount() {
		return mDataList.size();
	}

	@Override
	public NewsData getItem(int position) {
		return mDataList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder;
		if(convertView == null) {
			convertView = View.inflate(BaseApplication.getContext(), R.layout.home_list_view_item, null);
			viewHolder = new ViewHolder();
			viewHolder.image = (ImageView) convertView.findViewById(R.id.iv_news);
			viewHolder.title = (TextView) convertView.findViewById(R.id.tv_news_title);
			viewHolder.time = (TextView)convertView.findViewById(R.id.tv_time);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		NewsData data = getItem(position);
		bitmapUtils.display(viewHolder.image, data.listimage);
		viewHolder.title.setText(data.title);
		viewHolder.time.setText(data.pubdate);
		
		return convertView;
		
	}
	
	static class ViewHolder{
		ImageView image;
		TextView title;
		TextView time;
	}

}
