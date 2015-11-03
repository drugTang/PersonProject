package com.lex.app.adapter;

import java.util.List;

import com.lex.app.BaseApplication;
import com.lex.app.R;
import com.lex.app.constans.Constans;
import com.lex.app.domain.StoreData.SingleData;
import com.lidroid.xutils.BitmapUtils;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class StoreListViewAdapter extends BaseAdapter {
	private List<SingleData> mDataList;
	private BitmapUtils bitmapUtils;
	private Context context;
	
	public StoreListViewAdapter(List<SingleData> list){
		mDataList = list;
		context = BaseApplication.getContext();
		bitmapUtils = new BitmapUtils(context);
	}

	@Override
	public int getCount() {
		return mDataList.size();
	}

	@Override
	public SingleData getItem(int position) {
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
			convertView = View.inflate(context, R.layout.store_content_pager_list_view_item, null);
			viewHolder = new ViewHolder();
			viewHolder.image = (ImageView) convertView.findViewById(R.id.iv_store_like_image);
			viewHolder.title = (TextView) convertView.findViewById(R.id.tv_store_like_title);
			viewHolder.desc = (TextView) convertView.findViewById(R.id.tv_store_like_desc);
			viewHolder.price = (TextView) convertView.findViewById(R.id.tv_store_like_price);
			viewHolder.distance = (TextView) convertView.findViewById(R.id.tv_store_like_distance);
			viewHolder.sell = (TextView) convertView.findViewById(R.id.tv_store_like_sell);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		SingleData data = mDataList.get(position);
		bitmapUtils.display(viewHolder.image, Constans.SERVER_URL + data.image);
		viewHolder.title.setText(data.title);
		viewHolder.desc.setText(data.description);
		viewHolder.price.setText(data.price);
		viewHolder.distance.setText(data.distance);
		viewHolder.sell.setText(data.sell);
		return convertView;
	}
	
	class ViewHolder {
		ImageView image;
		TextView title;
		TextView desc;
		TextView price;
		TextView distance;
		TextView sell;
	}

}
