package com.lex.app.adapter;

import com.lex.app.BaseApplication;
import com.lex.app.R;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class StoreGridViewAdapter extends BaseAdapter {
	private String[] mMenuText = { "零食", "正餐", "日用品", "书籍", "文具", "二手", "床上用品", "电子产品" };

	private int[] mImageId = { R.drawable.ic_category_0, R.drawable.ic_category_1, R.drawable.ic_category_2,
			R.drawable.ic_category_3, R.drawable.ic_category_4, R.drawable.ic_category_5, R.drawable.ic_category_6,
			R.drawable.ic_category_7 };

	private Context context;

	public StoreGridViewAdapter() {
		context = BaseApplication.getContext();
	}

	@Override
	public int getCount() {
		return mMenuText.length;
	}

	@Override
	public String getItem(int position) {
		return mMenuText[position];
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder;
		if (convertView == null) {
			convertView = View.inflate(context, R.layout.store_content_pager_grid_view_item, null);
			viewHolder = new ViewHolder();
			viewHolder.text = (TextView) convertView.findViewById(R.id.tv_store_menu);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		String menuText = mMenuText[position];
		int imageId = mImageId[position];
		Drawable image = context.getResources().getDrawable(imageId);
		image.setBounds(0, 0, image.getIntrinsicHeight(), image.getIntrinsicWidth());
		viewHolder.text.setCompoundDrawables(null, image, null, null);
		viewHolder.text.setText(menuText);
		
		return convertView;
	}

	class ViewHolder {
		TextView text;
	}

}
