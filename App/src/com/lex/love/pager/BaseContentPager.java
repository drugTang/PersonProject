package com.lex.love.pager;

import com.lex.love.activity.MainActivity;

import android.app.Activity;
import android.view.View;

public abstract class BaseContentPager {
	public MainActivity mActivity;
	public View mRootView;

	public BaseContentPager(Activity activity) {
		if (activity instanceof MainActivity) {
			mActivity = (MainActivity) activity;
		}
		mRootView = initView();
	}

	public abstract View initView();

	public void initData() {
	}
}
