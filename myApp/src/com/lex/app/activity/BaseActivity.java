package com.lex.app.activity;

import com.lex.app.utils.ActivityCollector;
import com.lex.app.utils.LogUtils;

import android.app.Activity;
import android.os.Bundle;

public class BaseActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		LogUtils.i(getClass().getSimpleName()+" onCreate...");
		ActivityCollector.addActivity(this);
	}
	
	@Override
	protected void onDestroy() {
		ActivityCollector.removeActivity(this);
		LogUtils.i(getClass().getSimpleName()+" onDestory...");
		super.onDestroy();
	}
}
