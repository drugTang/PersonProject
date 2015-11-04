package com.lex.app.adapter;

import java.util.List;

import com.lex.app.BaseApplication;
import com.lex.app.R;
import com.lex.app.constans.Constans;
import com.lex.app.domain.ShoppingCarData.SingleData;
import com.lidroid.xutils.BitmapUtils;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

public class ShoppingCarListViewAdapter extends BaseAdapter {
	private List<SingleData> mDataList;
	private BitmapUtils bitmapUtils;
	private Context context;
	
	public ShoppingCarListViewAdapter(List<SingleData> list) {
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
			convertView = View.inflate(context, R.layout.shopping_car_content_list_view_item, null);
			viewHolder = new ViewHolder();
			viewHolder.choose = (RadioButton) convertView.findViewById(R.id.rb_choose);
			viewHolder.image = (ImageView) convertView.findViewById(R.id.iv_commodity);
			viewHolder.title = (TextView) convertView.findViewById(R.id.tv_title);
			viewHolder.price = (TextView) convertView.findViewById(R.id.tv_price);
			viewHolder.count = (TextView) convertView.findViewById(R.id.tv_quantity);
			viewHolder.reduce = (ImageButton) convertView.findViewById(R.id.ib_reduce);
			viewHolder.increase = (ImageButton) convertView.findViewById(R.id.ib_increase);
			convertView.setTag(viewHolder);
		}else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		SingleData data = mDataList.get(position);
		bitmapUtils.display(viewHolder.image, Constans.SERVER_URL+ data.image);
		viewHolder.title.setText(data.title);
		viewHolder.price.setText(data.price);
		viewHolder.count.setText(data.count);
		return convertView;
	}
	
	class ViewHolder {
		RadioButton choose;
		ImageView image;
		TextView title;
		TextView price;
		TextView count;
		ImageButton reduce;
		ImageButton increase;
	}

}
