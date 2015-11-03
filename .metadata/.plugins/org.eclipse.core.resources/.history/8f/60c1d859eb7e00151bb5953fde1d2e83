package com.lex.app.pages.frame;

import com.lex.app.R;
import com.lex.app.pages.BaseContentPager;
import com.lex.app.pages.BasePager;
import com.lex.app.pages.content.StoreContentPager;

import android.app.Activity;
import android.widget.TextView;

public class StoreFramePager extends BasePager {
	private TextView mTitleText;
	private BaseContentPager mStoreContentPager;

	public StoreFramePager(Activity activity) {
		super(activity);
	}

	@Override
	public void initView() {
		mTitleText = (TextView) mRootView.findViewById(R.id.tv_title);
		mTitleText.setText(R.string.store);
	}
	
	@Override
	public void initData() {
		mStoreContentPager = new StoreContentPager(mActivity);
		mStoreContentPager.initData();
		mFrameLayout.removeAllViews();
		mFrameLayout.addView(mStoreContentPager.mRootView);
	}

}
