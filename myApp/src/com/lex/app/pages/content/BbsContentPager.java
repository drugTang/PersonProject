package com.lex.app.pages.content;

import java.util.List;

import com.google.gson.Gson;
import com.lex.app.R;
import com.lex.app.adapter.BbsListViewAdapter;
import com.lex.app.constans.Constans;
import com.lex.app.domain.MessageData;
import com.lex.app.domain.MessageData.SingleData;
import com.lex.app.pages.BaseContentPager;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

import android.app.Activity;
import android.view.View;
import android.widget.ListView;

public class BbsContentPager extends BaseContentPager {
	private ListView mListView;

	public BbsContentPager(Activity activity) {
		super(activity);
	}

	@Override
	public View initView() {
		View view = View.inflate(mActivity, R.layout.bbs_content_pager_view, null);
		mListView = (ListView) view.findViewById(R.id.lv_bbs_content);
		return view;
	}

	@Override
	public void initData() {
		getDataFromServer();
	}

	private void getDataFromServer() {
		HttpUtils httpUtils = new HttpUtils();
		httpUtils.send(HttpMethod.GET, Constans.SERVER_URL + "/message.json", new RequestCallBack<String>() {

			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				String result = responseInfo.result;
				parseData(result);
			}

			@Override
			public void onFailure(HttpException error, String msg) {
				System.out.println("BbsContentPager 连接服务器失败:" + msg);
			}
		});
	}

	private void parseData(String result) {
		Gson gson = new Gson();
		MessageData mMessageData = gson.fromJson(result, MessageData.class);
		List<SingleData> mDataList = mMessageData.data;

		mListView.setAdapter(new BbsListViewAdapter(mDataList));
	}

}
