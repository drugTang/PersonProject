package com.lex.love.pager.frame;

import com.lex.love.Global.URLCollector;
import com.lex.love.pager.BaseContentPager;
import com.lex.love.pager.BaseFramePager;
import com.lex.love.pager.content.HomeContentPager;
import com.lex.love.utils.HttpCallbackListener;
import com.lex.love.utils.HttpUtils;

import android.app.Activity;

public class HomeFramePager extends BaseFramePager {
	private BaseContentPager mHomeContentPager;

	public HomeFramePager(Activity activity) {
		super(activity);
	}

	@Override
	public void initView() {
		mTitleText.setText("我");
	}
	
	@Override
	public void initData() {
		System.out.println("initData...");
		getDataFromServer();
	}
	
	private void getDataFromServer() {
		HttpUtils.send(URLCollector.LOVE_MESSAGE_URL, new HttpCallbackListener(){

			@Override
			public void onSuccess(String result) {
				mHomeContentPager = new HomeContentPager(mActivity,result);
				mHomeContentPager.initData();
				mContentFrame.removeAllViews();
				mContentFrame.addView(mHomeContentPager.mRootView);
			}

			@Override
			public void onFailure(Exception e, String msg) {
				System.out.println("MainActivity 从服务器获取数据失败："+ msg);
			}});
	}

}
