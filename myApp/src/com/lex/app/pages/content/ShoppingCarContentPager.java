package com.lex.app.pages.content;

import com.google.gson.Gson;
import com.lex.app.R;
import com.lex.app.adapter.ShoppingCarListViewAdapter;
import com.lex.app.constans.Constans;
import com.lex.app.domain.ShoppingCarData;
import com.lex.app.pages.BaseContentPager;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

import android.app.Activity;
import android.view.View;
import android.widget.ListView;

public class ShoppingCarContentPager extends BaseContentPager {
	private ListView mListView;

	public ShoppingCarContentPager(Activity activity) {
		super(activity);
	}

	@Override
	public View initView() {
		View view = View.inflate(mActivity, R.layout.shopping_car_pager_view, null);
		mListView = (ListView) view.findViewById(R.id.lv_shopping_car);
		return view;
	}
	
	@Override
	public void initData() {
		getDataFromServer();
	}
	
	private void getDataFromServer() {
		HttpUtils httpUtils = new HttpUtils();
		httpUtils.send(HttpMethod.GET, Constans.SERVER_URL+"/shoppingcar_list.json", new RequestCallBack<String>() {

			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				String result = responseInfo.result;
				parseData(result);
			}

			@Override
			public void onFailure(HttpException error, String msg) {
				System.out.println("ShoppingCarContentPager连接服务器失败："+msg);
			}
		});
	}
	
	private void parseData(String result) {
		Gson gson = new Gson();
		ShoppingCarData mDataList = gson.fromJson(result, ShoppingCarData.class);
		mListView.setAdapter(new ShoppingCarListViewAdapter(mDataList.data));
	}

}
