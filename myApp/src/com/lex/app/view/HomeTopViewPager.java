package com.lex.app.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class HomeTopViewPager extends ViewPager {

	public HomeTopViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public HomeTopViewPager(Context context) {
		super(context);
	}
	
	/**
	 * 请求父控件及祖宗控件不要拦截事件
	 */
	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		getParent().requestDisallowInterceptTouchEvent(true);
		return super.dispatchTouchEvent(ev);
	}

}
