package com.lex.app.utils;

import com.lex.app.BaseApplication;

import android.content.res.Resources;

public class Utility {
	public static Resources getResources() {
		return BaseApplication.getContext().getResources();
	}
	
	public static int px2dip(int px) {
		float density = getResources().getDisplayMetrics().density;
		return (int)(px/density  + 0.5f);
	}
	
	public static int dip2px(int dip) {
		float density = getResources().getDisplayMetrics().density;
		return (int)(dip * density + 0.5f);
	}
}
