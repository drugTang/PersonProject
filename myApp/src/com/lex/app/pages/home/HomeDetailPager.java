package com.lex.app.pages.home;

import java.util.List;

import com.google.gson.Gson;
import com.lex.app.R;
import com.lex.app.adapter.HomeListViewAdapter;
import com.lex.app.adapter.TopNewsPagerAdapter;
import com.lex.app.constans.Constans;
import com.lex.app.domain.HomeListData;
import com.lex.app.domain.HomeListData.TopNewsData;
import com.lex.app.pages.BaseContentPager;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.viewpagerindicator.CirclePageIndicator;

import android.app.Activity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

public class HomeDetailPager extends BaseContentPager implements OnPageChangeListener {
	private ListView mListView;
	private ViewPager mViewPager;
	private TextView mTopTitle;
	private CirclePageIndicator mIndicator;
	private String mUrl;
	private HomeListData mHomeListData;
	private List<TopNewsData> mTopNewsData;

	public HomeDetailPager(Activity activity, String url) {
		super(activity);
		mUrl = Constans.SERVER_URL + url;
	}

	@Override
	public View initView() {
		View view = View.inflate(mActivity, R.layout.home_pager_view, null);
		mListView = (ListView) view.findViewById(R.id.lv_home);
		View headerView = View.inflate(mActivity, R.layout.home_pager_view_top, null);
		mViewPager = (ViewPager) headerView.findViewById(R.id.vp_top);
		mTopTitle = (TextView) headerView.findViewById(R.id.tv_top_title);
		mIndicator = (CirclePageIndicator) headerView.findViewById(R.id.indicator);

		mListView.addHeaderView(headerView);
		return view;
	}

	@Override
	public void initData() {
		getDataFromServer();
	}

	private void getDataFromServer() {
		HttpUtils httpUtils = new HttpUtils();
		httpUtils.send(HttpMethod.GET, mUrl, new RequestCallBack<String>() {

			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				String result = responseInfo.result;
				parseData(result);
			}

			@Override
			public void onFailure(HttpException error, String msg) {
				System.out.println("HomeDetailPager 获取数据失败！");
			}
		});
	}

	private void parseData(String result) {
		Gson gson = new Gson();
		mHomeListData = gson.fromJson(result, HomeListData.class);
		mTopNewsData = mHomeListData.data.topnews;

		mViewPager.setAdapter(new TopNewsPagerAdapter(mTopNewsData));
		mIndicator.setViewPager(mViewPager);
		mIndicator.setOnPageChangeListener(this);
		mIndicator.onPageSelected(0);
		mListView.setAdapter(new HomeListViewAdapter(mHomeListData.data.news));
		mListView.setOnItemClickListener(new MyOnClickListener());
	}
	
	private class MyOnClickListener implements OnItemClickListener{

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			//这里可以响应事件，跳转到相应的页面
		}
		
	}

	@Override
	public void onPageScrollStateChanged(int arg0) {

	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {

	}

	@Override
	public void onPageSelected(int position) {
		mTopTitle.setText(mTopNewsData.get(position).title);
	}
}