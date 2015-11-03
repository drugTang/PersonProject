package com.lex.app.pages;

import com.lex.app.activity.MainActivity;

import android.app.Activity;
import android.view.View;

public abstract class BaseContentPager {
	public MainActivity mActivity;
	public View mRootView;
	
	public BaseContentPager(Activity activity) {
		mActivity = (MainActivity)activity;
		mRootView = initView();
	}
	
	public abstract View initView();
	
	public void initData() {
		
	}
}
