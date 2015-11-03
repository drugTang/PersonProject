package com.lex.app.adapter;

import java.util.List;

import com.lex.app.pages.BasePager;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;


/**
 * 内容布局的适配器，产生Home,lib,bbs,store,setting 5个页面
 * 
 * @author Administrator
 *
 */
public class ContentPagerAdapter extends PagerAdapter{
	private List<BasePager> mPagerList;
	
	public ContentPagerAdapter(List<BasePager> pagerList) {
		mPagerList = pagerList;
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
