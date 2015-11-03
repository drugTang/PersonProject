package com.lex.app.pages.frame;

import com.lex.app.R;
import com.lex.app.pages.BasePager;

import android.app.Activity;
import android.widget.TextView;

public class SettingFramePager extends BasePager {
	private TextView mTitleText;

	public SettingFramePager(Activity activity) {
		super(activity);
	}

	@Override
	public void initView() {
		mTitleText = (TextView) mRootView.findViewById(R.id.tv_title);
		mTitleText.setText(R.string.setting);
	}

}
