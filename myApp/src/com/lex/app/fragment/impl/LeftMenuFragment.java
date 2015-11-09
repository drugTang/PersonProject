package com.lex.app.fragment.impl;

import com.lex.app.R;
import com.lex.app.activity.CommentActivity;
import com.lex.app.activity.LibraryActivity;
import com.lex.app.activity.MessageActivity;
import com.lex.app.activity.OrderActivity;
import com.lex.app.activity.SettingActivity;
import com.lex.app.activity.ShareActivity;
import com.lex.app.adapter.LeftMenuListViewAdapter;
import com.lex.app.fragment.BaseFragment;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class LeftMenuFragment extends BaseFragment {
	private ListView mListView;
	@Override
	public View initView() {
		View view = View.inflate(mActivity, R.layout.fragment_left_menu, null);
		mListView = (ListView) view.findViewById(R.id.lv_left_menu);
		initData();
		return view;
	}
	
	@Override
	public void initData() {
		mListView.setAdapter(new LeftMenuListViewAdapter(mActivity));
		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				switch(position) {
				case 0:
					mActivity.startActivity(new Intent(mActivity,LibraryActivity.class));
					break;
				case 1:
					mActivity.startActivity(new Intent(mActivity,OrderActivity.class));
					break;
				case 2:
					mActivity.startActivity(new Intent(mActivity,CommentActivity.class));
					break;
				case 3:
					mActivity.startActivity(new Intent(mActivity,ShareActivity.class));
					break;
				case 4:
					mActivity.startActivity(new Intent(mActivity,MessageActivity.class));
					break;
				case 5:
					mActivity.startActivity(new Intent(mActivity,SettingActivity.class));
					break;
				default:break;
				}
			}
		});
	}

}
