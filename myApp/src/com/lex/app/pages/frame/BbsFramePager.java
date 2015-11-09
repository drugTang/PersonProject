package com.lex.app.pages.frame;

import com.lex.app.R;
import com.lex.app.activity.PublishMessageActivity;
import com.lex.app.pages.BaseContentPager;
import com.lex.app.pages.BasePager;
import com.lex.app.pages.content.BbsContentPager;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class BbsFramePager extends BasePager {
	private TextView mTitleText;
	private BaseContentPager mBbsContentPager;

	public BbsFramePager(Activity activity) {
		super(activity);
	}

	@Override
	public void initView() {
		mTitleText = (TextView) mRootView.findViewById(R.id.tv_title);
		mTitleText.setText(R.string.bbs);
		mMoreButton.setImageResource(R.drawable.add_small);
		mMoreButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				mActivity.startActivity(new Intent(mActivity,PublishMessageActivity.class));
			}
		});
	}
	
	@Override
	public void initData() {
		mBbsContentPager = new BbsContentPager(mActivity);
		mBbsContentPager.initData();
		mFrameLayout.removeAllViews();
		mFrameLayout.addView(mBbsContentPager.mRootView);
	}

}
