package com.lex.app.pages.lib;

import com.lex.app.R;
import com.lex.app.pages.BaseContentPager;

import android.app.Activity;
import android.view.View;

/**
 * 图书馆热门搜索页面
 * 
 * @author Administrator
 *
 */
public class LibSearchDetailPager extends BaseContentPager {

	public LibSearchDetailPager(Activity activity) {
		super(activity);
	}

	@Override
	public View initView() {
		View view = View.inflate(mActivity, R.layout.lib_search_detail_list_view, null);
		return view;
	}

}
