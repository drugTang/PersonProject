package com.lex.love.pager.frame;

import com.lex.love.pager.BaseContentPager;
import com.lex.love.pager.BaseFramePager;
import com.lex.love.pager.content.PicContentPager;

import android.app.Activity;

public class PicFramePager extends BaseFramePager {
	private BaseContentPager mPicContentPager;

	public PicFramePager(Activity activity) {
		super(activity);
	}

	@Override
	public void initView() {
		mTitleText.setText("她");
	}
	
	@Override
	public void initData() {
		mPicContentPager = new PicContentPager(mActivity);
		mPicContentPager.initData();
		mContentFrame.removeAllViews();
		mContentFrame.addView(mPicContentPager.mRootView);
	}

}
