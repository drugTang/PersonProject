package com.lex.googleplay;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;

public class AppDetailActivity extends ActionBarActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_app_detail);
		ActionBar actionBar = getSupportActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
	}
	
//	@Override
//	public boolean onOptionsItemSelected(MenuItem item) {
		//可以在Manifest中配置父Activity实现
//		if(item.getItemId() == android.R.id.home) { //判断ActionBar的图标按钮是否被点击
//			finish();
//		}
//		return super.onOptionsItemSelected(item);
//	}
}