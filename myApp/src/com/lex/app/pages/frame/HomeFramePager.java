package com.lex.app.pages.frame;

import java.util.List;

import com.google.gson.Gson;
import com.lex.app.R;
import com.lex.app.constans.Constans;
import com.lex.app.domain.HomeCategoryData;
import com.lex.app.domain.HomeCategoryData.Children;
import com.lex.app.pages.BaseContentPager;
import com.lex.app.pages.BasePager;
import com.lex.app.pages.content.HomeContentPager;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;

public class HomeFramePager extends BasePager {
	private TextView mTitleText;
	private HomeCategoryData mCategoryData;
	private List<Children> mChildrenData;
	
	private BaseContentPager mHomeContentPager;

	public HomeFramePager(Activity activity) {
		super(activity);
	}

	@Override
	public void initView() {
		mTitleText = (TextView)mRootView.findViewById(R.id.tv_title);
		mTitleText.setText(R.string.home);  
		mMoreButton.setVisibility(View.GONE);
	}
	
	@Override
	public void initData() {
		getDataFromServer();
	}
	
	private void getDataFromServer() {
		HttpUtils httpUtils = new HttpUtils();
		httpUtils.send(HttpMethod.GET, Constans.CATEGORY_URL, new RequestCallBack<String>() {

			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				String result = responseInfo.result;
				parseData(result);
			}

			@Override
			public void onFailure(HttpException error, String msg) {
				System.out.println("HomeFramePager:连接服务器失败"+msg);
			}
		});
	}
	
	private void parseData(String result) {
		Gson gson = new Gson();
		mCategoryData = gson.fromJson(result, HomeCategoryData.class);
		mChildrenData = mCategoryData.data.get(0).children;
		
		mHomeContentPager = new HomeContentPager(mActivity,mChildrenData);
		mHomeContentPager.initData();
		mFrameLayout.removeAllViews();
		mFrameLayout.addView(mHomeContentPager.mRootView);
	}
	
	
	
}
