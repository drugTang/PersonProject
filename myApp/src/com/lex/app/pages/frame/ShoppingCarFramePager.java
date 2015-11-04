package com.lex.app.pages.frame;

import com.lex.app.R;
import com.lex.app.pages.BaseContentPager;
import com.lex.app.pages.BasePager;
import com.lex.app.pages.content.ShoppingCarContentPager;

import android.app.Activity;
import android.widget.TextView;

public class ShoppingCarFramePager extends BasePager {
	private TextView mTitleText;
	private BaseContentPager mShoppingCarContentPager;

	public ShoppingCarFramePager(Activity activity) {
		super(activity);
	}

	@Override
	public void initView() {
		mTitleText = (TextView) mRootView.findViewById(R.id.tv_title);
		mTitleText.setText(R.string.shoppingcar);
	}
	
	@Override
	public void initData() {
		mShoppingCarContentPager = new ShoppingCarContentPager(mActivity);
		mShoppingCarContentPager.initData();
		mFrameLayout.removeAllViews();
		mFrameLayout.addView(mShoppingCarContentPager.mRootView);
	}

}
