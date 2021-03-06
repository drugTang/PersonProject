package com.lex.app.pages.content;

import java.util.ArrayList;
import java.util.List;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.lex.app.R;
import com.lex.app.adapter.LibViewPagerAdapter;
import com.lex.app.pages.BaseContentPager;
import com.lex.app.pages.lib.LibBorrowDetailPager;
import com.lex.app.pages.lib.LibNewsDetailPager;
import com.lex.app.pages.lib.LibSearchDetailPager;

import android.app.Activity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class LibContentPager extends BaseContentPager implements OnPageChangeListener {
	private ViewPager mViewPager;
	private RadioGroup mRadioGroup;
	private List<BaseContentPager> mPagerList;

	public LibContentPager(Activity activity) {
		super(activity);
	}

	@Override
	public View initView() {
		View view = View.inflate(mActivity, R.layout.lib_frame_pager_view, null);
		mViewPager = (ViewPager) view.findViewById(R.id.vp_lib);
		mRadioGroup = (RadioGroup) view.findViewById(R.id.radio_group);
		return view;
	}

	@Override
	public void initData() {
		mPagerList = new ArrayList<BaseContentPager>();
		mPagerList.add(new LibNewsDetailPager(mActivity));
		mPagerList.add(new LibBorrowDetailPager(mActivity));
		mPagerList.add(new LibSearchDetailPager(mActivity));

		mViewPager.setAdapter(new LibViewPagerAdapter(mPagerList));
		mViewPager.setOnPageChangeListener(this);
		onPageSelected(0);
		
		mRadioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				switch (checkedId) {
				case R.id.radio_news:
					mViewPager.setCurrentItem(0);
					break;
				case R.id.radio_borrow:
					mViewPager.setCurrentItem(1);
					break;
				case R.id.radio_search:
					mViewPager.setCurrentItem(2);
					break;
				}
			}
		});
	}

	@Override
	public void onPageScrollStateChanged(int arg0) {

	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {

	}

	@Override
	public void onPageSelected(int position) {
		mPagerList.get(position).initData();// 页面被选中时初始化数据
		switch (position) {
		case 0:
			mRadioGroup.check(R.id.radio_news);
			break;
		case 1:
			mRadioGroup.check(R.id.radio_borrow);
			break;
		case 2:
			mRadioGroup.check(R.id.radio_search);
			break;
		}
		if (position == 0) {
			mActivity.getSlidingMenu().setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
		} else {
			mActivity.getSlidingMenu().setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
		}
	}

}
