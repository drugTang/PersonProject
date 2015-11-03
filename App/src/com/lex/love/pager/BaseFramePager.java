package com.lex.love.pager;

import com.lex.love.R;
import com.lex.love.activity.MainActivity;

import android.app.Activity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

public abstract class BaseFramePager {
	public MainActivity mActivity;
	public View mRootView;
	public TextView mTitleText;
	public FrameLayout mContentFrame;
	
	public BaseFramePager(Activity activity) {
		mActivity = (MainActivity) activity;
		mRootView = View.inflate(mActivity, R.layout.base_frame_pager_view, null);
		mTitleText = (TextView)mRootView.findViewById(R.id.tv_title);
		mContentFrame = (FrameLayout)mRootView.findViewById(R.id.fl_content);
		
		initView();
	}
	
	public abstract void initView();
	
	public void initData(){}
}
