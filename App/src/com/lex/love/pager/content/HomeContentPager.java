package com.lex.love.pager.content;

import com.lex.love.R;
import com.lex.love.pager.BaseContentPager;
import com.lex.love.view.TypeView;

import android.app.Activity;
import android.view.View;

public class HomeContentPager extends BaseContentPager{
	private String mTypeString;
	private TypeView mTypeView;
	
	public HomeContentPager(Activity activity, String result) {
		super(activity);
		mTypeString = result;
	}

	@Override
	public View initView() {
		View view = View.inflate(mActivity, R.layout.home_content_pager_view, null);
		mTypeView = (TypeView) view.findViewById(R.id.tv_message);
		return view;
	}
	
	@Override
	public void initData() {
		mTypeView.start(mTypeString);
	}

}
