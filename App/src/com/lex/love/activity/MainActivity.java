package com.lex.love.activity;

import java.util.ArrayList;
import java.util.List;

import com.lex.love.R;
import com.lex.love.pager.BaseFramePager;
import com.lex.love.pager.frame.HomeFramePager;
import com.lex.love.pager.frame.OtherFramePager;
import com.lex.love.pager.frame.PicFramePager;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class MainActivity extends Activity {
	private ViewPager mViewPager;
	private RadioGroup mRadioGroup;
	private List<BaseFramePager> mPagerList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mViewPager = (ViewPager) findViewById(R.id.vp_frame);
		mRadioGroup = (RadioGroup) findViewById(R.id.radio_group);

		mPagerList = new ArrayList<BaseFramePager>();
		mPagerList.add(new HomeFramePager(this));
		mPagerList.add(new PicFramePager(this));
		mPagerList.add(new OtherFramePager(this));

		mPagerList.get(0).initData();
		
		mViewPager.setAdapter(new ContentPagerAdapter());
		mViewPager.addOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int position) {
				switch (position) {
				case 0:
					mPagerList.get(position).initData();
					mRadioGroup.check(R.id.radio_home);
					break;
				case 1:
					mPagerList.get(position).initData();
					mRadioGroup.check(R.id.radio_pic);
					break;
				case 2:
					mPagerList.get(position).initData();
					mRadioGroup.check(R.id.radio_other);
					break;
				}
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
			}
		});

		mRadioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				switch (checkedId) {
				case R.id.radio_home:
					mViewPager.setCurrentItem(0, false);
					break;
				case R.id.radio_pic:
					mViewPager.setCurrentItem(1, false);
					break;
				case R.id.radio_other:
					mViewPager.setCurrentItem(2, false);
					break;
				}
			}
		});
	}

	class ContentPagerAdapter extends PagerAdapter {

		@Override
		public int getCount() {
			return mPagerList.size();
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			View view = mPagerList.get(position).mRootView;
			container.addView(view);
			return view;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView((View) object);
		}

	}

}
