package com.lex.love.pager.content;

import com.lex.love.R;
import com.lex.love.pager.BaseContentPager;

import android.app.Activity;
import android.view.View;

public class PicContentPager extends BaseContentPager {

	public PicContentPager(Activity activity) {
		super(activity);
	}

	@Override
	public View initView() {
		View view = View.inflate(mActivity, R.layout.pic_content_pager_view, null);
		return view;
	}

}
