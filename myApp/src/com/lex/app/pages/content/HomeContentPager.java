package com.lex.app.pages.content;

import java.util.ArrayList;
import java.util.List;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.lex.app.R;
import com.lex.app.adapter.HomeViewPagerAdapter;
import com.lex.app.domain.HomeCategoryData.Children;
import com.lex.app.pages.BaseContentPager;
import com.lex.app.pages.home.HomeDetailPager;
import com.viewpagerindicator.TabPageIndicator;

import android.app.Activity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;

public class HomeContentPager extends BaseContentPager {
	private List<Children> mChildrenData;
	private ViewPager mViewPager;
	private TabPageIndicator mIndicator;
	private List<HomeDetailPager> mPagerList;
	
	public HomeContentPager(Activity activity,List<Children> childrenData) {
		super(activity);
		mChildrenData = childrenData;
	}

	@Override
	public View initView() {
		View view = View.inflate(mActivity, R.layout.home_frame_pager_view, null);
		mViewPager = (ViewPager)view.findViewById(R.id.vp_home);
		mIndicator = (TabPageIndicator)view.findViewById(R.id.indicator);
		return view;
	}
	
	@Override
	public void initData() {
		mPagerList = new ArrayList<HomeDetailPager>();
		for(int i = 0 ; i < mChildrenData.size(); i++) {
			mPagerList.add(new HomeDetailPager(mActivity,mChildrenData.get(i).url));
		}
		
		mViewPager.setAdapter(new HomeViewPagerAdapter(mPagerList, mChildrenData));
		mIndicator.setViewPager(mViewPager);
		mIndicator.setOnPageChangeListener(new MyOnPageChangeListener());
		mIndicator.onPageSelected(0);
	}
	
	private class MyOnPageChangeListener implements OnPageChangeListener {

		@Override
		public void onPageScrollStateChanged(int arg0) {
			
		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
			
		}

		@Override
		public void onPageSelected(int position) {
			mPagerList.get(position).initData();
			if(position == 0) {
				mActivity.getSlidingMenu().setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
			} else {
				mActivity.getSlidingMenu().setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
			}
		}
		
	}

}
