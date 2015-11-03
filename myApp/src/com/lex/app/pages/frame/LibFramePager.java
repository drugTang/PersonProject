package com.lex.app.pages.frame;

import com.lex.app.R;
import com.lex.app.activity.SearchActivity;
import com.lex.app.pages.BasePager;
import com.lex.app.pages.content.LibContentPager;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class LibFramePager extends BasePager {
	private TextView mTitleText;
	
	private LibContentPager mLibContentPager;

	public LibFramePager(Activity activity) {
		super(activity);
	}

	@Override
	public void initView() {
		mTitleText = (TextView) mRootView.findViewById(R.id.tv_title);
		mTitleText.setText(R.string.lib);
		mMoreButton.setImageResource(R.drawable.toolbar_search_white);
		mMoreButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(mActivity,SearchActivity.class);
				mActivity.startActivity(intent);
			}
		});
	}
	
	@Override
	public void initData() {
		mLibContentPager = new LibContentPager(mActivity);
		mLibContentPager.initData();
		mFrameLayout.removeAllViews();
		mFrameLayout.addView(mLibContentPager.mRootView);
	}

}
