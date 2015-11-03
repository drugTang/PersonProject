package com.lex.love.pager.frame;

import com.lex.love.pager.BaseContentPager;
import com.lex.love.pager.BaseFramePager;
import com.lex.love.pager.content.OtherContentPager;

import android.app.Activity;

public class OtherFramePager extends BaseFramePager {
	private BaseContentPager mOtherContentPager;
	
	public OtherFramePager(Activity activity) {
		super(activity);
	}

	@Override
	public void initView() {
		mTitleText.setText("我们");
	}
	
	@Override
	public void initData() {
		mOtherContentPager = new OtherContentPager(mActivity);
		mOtherContentPager.initData();
		mContentFrame.removeAllViews();
		mContentFrame.addView(mOtherContentPager.mRootView);
	}

}
