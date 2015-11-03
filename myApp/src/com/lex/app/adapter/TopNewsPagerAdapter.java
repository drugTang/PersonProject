package com.lex.app.adapter;

import java.util.List;

import com.lex.app.BaseApplication;
import com.lex.app.domain.HomeListData.TopNewsData;
import com.lidroid.xutils.BitmapUtils;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

public class TopNewsPagerAdapter extends PagerAdapter {
	private List<TopNewsData> mDataList;
	private BitmapUtils bitmapUtils;
	private Context context;
	
	public TopNewsPagerAdapter(List<TopNewsData> list) {
		mDataList = list;
		context = BaseApplication.getContext();
		bitmapUtils = new BitmapUtils(context);
	}

	@Override
	public int getCount() {
		return mDataList.size();
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		return arg0 == arg1;
	}
	
	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		ImageView image = new ImageView(context);
		image.setScaleType(ScaleType.FIT_XY);
		bitmapUtils.display(image, mDataList.get(position).topimage);
		container.addView(image);
		return image;
	}
	
	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		container.removeView((View)object);
	}

}
