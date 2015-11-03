package com.lex.app.pages.lib;

import java.util.List;

import com.google.gson.Gson;
import com.lex.app.R;
import com.lex.app.adapter.LibBorrowListViewAdapter;
import com.lex.app.constans.Constans;
import com.lex.app.domain.HomeListData;
import com.lex.app.domain.HomeListData.NewsData;
import com.lex.app.pages.BaseContentPager;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

import android.app.Activity;
import android.view.View;
import android.widget.ListView;

/**
 * 图书馆热门借阅页面
 * 
 * @author Administrator
 *
 */
public class LibBorrowDetailPager extends BaseContentPager {
	private ListView mListView;

	public LibBorrowDetailPager(Activity activity) {
		super(activity);
	}

	@Override
	public View initView() {
		View view = View.inflate(mActivity, R.layout.lib_borrow_detail_pager_view, null);
		mListView = (ListView) view.findViewById(R.id.lv_lib_borrow);
		return view;
	}
	
	@Override
	public void initData() {
		getDataFromServer();
	}

	/**
	 * 此处的连接使用的测试连接
	 * 以后服务器搭建后需要更改
	 */
	private void getDataFromServer() {
		HttpUtils httpUtils = new HttpUtils();
		httpUtils.send(HttpMethod.GET, Constans.SERVER_URL + "/10007/list_1.json", new RequestCallBack<String>() {

			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				String result = responseInfo.result;
				System.out.println("LibBorrowDetailPager："+result);
				parseData(result);
			}

			@Override
			public void onFailure(HttpException error, String msg) {
				System.out.println("LibBorrowDetailPager 连接服务器失败："+msg);
			}
		});
	}
	
	/**
	 * 这里用到的是Home页面的数据
	 * 以后搭建服务器后需要更改
	 * 
	 * @param result
	 */
	private void parseData(String result) {
		Gson gson = new Gson();
		HomeListData homeListData = gson.fromJson(result, HomeListData.class);
		List<NewsData> mBorrowDataList = homeListData.data.news;
		mListView.setAdapter(new LibBorrowListViewAdapter(mBorrowDataList));
	}

}