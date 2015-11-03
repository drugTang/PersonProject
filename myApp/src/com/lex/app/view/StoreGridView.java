package com.lex.app.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

public class StoreGridView extends GridView{

	public StoreGridView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public StoreGridView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public StoreGridView(Context context) {
		super(context);
	}
	
	/**
	 * 设置不滚动
	 */
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
		super.onMeasure(widthMeasureSpec, expandSpec);
	}

}
