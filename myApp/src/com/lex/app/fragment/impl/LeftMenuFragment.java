package com.lex.app.fragment.impl;

import com.lex.app.R;
import com.lex.app.fragment.BaseFragment;

import android.view.View;

public class LeftMenuFragment extends BaseFragment {

	@Override
	public View initView() {
		View view = View.inflate(mActivity, R.layout.left_sliding_menu, null);
		return view;
	}

}
