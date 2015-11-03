package com.lex.app.adapter;

import java.util.List;

import com.lex.app.pages.BaseContentPager;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

public class LibViewPagerAdapter extends PagerAdapter {
	private List<BaseContentPager> mPagerList;
	
	public LibViewPagerAdapter(List<BaseContentPager> list) {
		mPagerList = list;
	}

	@Override
	public int getCount() {
		return mPagerList.size();
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		return arg0 == arg1;
	}
	
	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		View view = mPagerList.get(position).mRootView;
		container.addView(view);
		return view;
	}
	
	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		container.removeView((View)object);
	}

}
