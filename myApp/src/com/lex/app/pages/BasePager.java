package com.lex.app.pages;

import com.lex.app.R;
import com.lex.app.activity.MainActivity;

import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.ImageButton;

public abstract class BasePager {
	public MainActivity mActivity;
	public View mRootView;
	public ImageButton mMenuButton;
	public ImageButton mMoreButton;
	public FrameLayout mFrameLayout;
	
	public BasePager(Activity activity) {
		mActivity = (MainActivity)activity;
		mRootView = View.inflate(activity, R.layout.base_pager, null);
		mMenuButton = (ImageButton) mRootView.findViewById(R.id.ib_menu);
		mMoreButton = (ImageButton) mRootView.findViewById(R.id.ib_more);
		mFrameLayout = (FrameLayout) mRootView.findViewById(R.id.fl_detail);
		mMenuButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				mActivity.getSlidingMenu().toggle();
			}
		});
		initView();
	}
	
	public abstract void initView();
	
	public void initData() {
	}
	
}
