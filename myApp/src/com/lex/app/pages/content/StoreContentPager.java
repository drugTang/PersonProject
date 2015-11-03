package com.lex.app.pages.content;

import com.google.gson.Gson;
import com.lex.app.R;
import com.lex.app.adapter.StoreGridViewAdapter;
import com.lex.app.adapter.StoreListViewAdapter;
import com.lex.app.constans.Constans;
import com.lex.app.domain.StoreData;
import com.lex.app.pages.BaseContentPager;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

import android.app.Activity;
import android.view.View;
import android.widget.GridView;
import android.widget.ListView;

public class StoreContentPager extends BaseContentPager {
	private ListView mListView;
	private GridView mGridView;

	public StoreContentPager(Activity activity) {
		super(activity);
	}

	@Override
	public View initView() {
		View view = View.inflate(mActivity, R.layout.store_content_pager_view, null);
		mListView = (ListView) view.findViewById(R.id.lv_store);
		View headerView = View.inflate(mActivity, R.layout.store_content_pager_header_view, null);
		mGridView = (GridView) headerView.findViewById(R.id.gv_top);
		mListView.addHeaderView(headerView);
		return view;
	}

	@Override
	public void initData() {
		mGridView.setAdapter(new StoreGridViewAdapter());
		
		getDataFromServer();
	}

	private void getDataFromServer() {
		HttpUtils httpUtils = new HttpUtils();
		httpUtils.send(HttpMethod.GET, Constans.SERVER_URL +"/store_like_list.json", new RequestCallBack<String>() {

			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				String result = responseInfo.result;
				parseData(result);
			}

			@Override
			public void onFailure(HttpException error, String msg) {
				
			}
		});
	}
	
	private void parseData(String result) {
		Gson gson = new Gson();
		StoreData storeData =gson.fromJson(result, StoreData.class);
		mListView.setAdapter(new StoreListViewAdapter(storeData.data));
	}

}
