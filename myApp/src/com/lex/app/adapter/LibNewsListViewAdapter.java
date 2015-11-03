package com.lex.app.adapter;

import java.util.List;

import com.lex.app.BaseApplication;
import com.lex.app.R;
import com.lex.app.domain.HomeListData.NewsData;
import com.lidroid.xutils.BitmapUtils;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class LibNewsListViewAdapter extends BaseAdapter {
	private List<NewsData> mDataList;
	private BitmapUtils bitmapUtils;
	private Context context;
	
	public LibNewsListViewAdapter(List<NewsData> list) {
		mDataList = list;
		context = BaseApplication.getContext();
		bitmapUtils = new BitmapUtils(context);
		bitmapUtils.configDefaultLoadFailedImage(R.drawable.demo);
		bitmapUtils.configDefaultLoadingImage(R.drawable.demo);
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
			convertView = View.inflate(context, R.layout.lib_news_detail_list_view_item, null);
			viewHolder = new ViewHolder();
			viewHolder.image = (ImageView) convertView.findViewById(R.id.iv_lib_news);
			viewHolder.title = (TextView) convertView.findViewById(R.id.tv_lib_news_title);
			viewHolder.time = (TextView) convertView.findViewById(R.id.tv_lib_news_time);
			viewHolder.clicked = (TextView) convertView.findViewById(R.id.tv_lib_news_click);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		NewsData data = mDataList.get(position);
		bitmapUtils.display(viewHolder.image, data.listimage);
		viewHolder.title.setText(data.title);
		viewHolder.time.setText(data.pubdate);
		viewHolder.clicked.setText("阅读量：" + "18次");
		return convertView;
	}
	
	class ViewHolder {
		ImageView image;
		TextView title;
		TextView time;
		TextView clicked;
	}

}
