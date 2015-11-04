package com.lex.app.fragment.impl;

import java.util.ArrayList;
import java.util.List;

import com.lex.app.R;
import com.lex.app.adapter.ContentPagerAdapter;
import com.lex.app.fragment.BaseFragment;
import com.lex.app.pages.BasePager;
import com.lex.app.pages.frame.BbsFramePager;
import com.lex.app.pages.frame.HomeFramePager;
import com.lex.app.pages.frame.LibFramePager;
import com.lex.app.pages.frame.ShoppingCarFramePager;
import com.lex.app.pages.frame.StoreFramePager;

import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class ContentFragment extends BaseFragment {
	private View mRootView;
	private ViewPager mContentPager;
	private ContentPagerAdapter mContentPagerAdapter;
	private RadioGroup mRadioGroup;
	private List<BasePager> mPagerList = new ArrayList<BasePager>();

	@Override
	public View initView() {
		mRootView = View.inflate(mActivity, R.layout.fragment_content, null);

		mContentPager = (ViewPager) mRootView.findViewById(R.id.vp_content);
		mRadioGroup = (RadioGroup) mRootView.findViewById(R.id.radio_group);

		initData();
		return mRootView;
	}

	@Override
	public void initData() {
		
		mPagerList.add(new HomeFramePager(mActivity));
		mPagerList.add(new LibFramePager(mActivity));
		mPagerList.add(new BbsFramePager(mActivity));
		mPagerList.add(new StoreFramePager(mActivity));
		mPagerList.add(new ShoppingCarFramePager(mActivity));

		mContentPagerAdapter = new ContentPagerAdapter(mPagerList);

		mRadioGroup.setOnCheckedChangeListener(new RadioChangeListener());
		
		mPagerList.get(0).initData();

		mContentPager.setAdapter(mContentPagerAdapter);
	}

	private class RadioChangeListener implements OnCheckedChangeListener {

		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			switch (checkedId) {
			case R.id.radio_home:
				mPagerList.get(0).initData();
				mContentPager.setCurrentItem(0, false);
				break;
			case R.id.radio_lib:
				mPagerList.get(1).initData();
				mContentPager.setCurrentItem(1, false);
				break;
			case R.id.radio_bbs:
				mPagerList.get(2).initData();
				mContentPager.setCurrentItem(2, false);
				break;
			case R.id.radio_store:
				mPagerList.get(3).initData();
				mContentPager.setCurrentItem(3, false);
				break;
			case R.id.radio_setting:
				mPagerList.get(4).initData();
				mContentPager.setCurrentItem(4, false);
				break;
			}
		}
	}
	
}
