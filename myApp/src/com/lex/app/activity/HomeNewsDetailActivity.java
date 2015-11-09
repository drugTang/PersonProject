package com.lex.app.activity;

import com.lex.app.R;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageButton;

public class HomeNewsDetailActivity extends BaseActivity {
	private Button mBackButton;
	private ImageButton mShareButton;
	private WebView mWebView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home_news_detail);
		mWebView = (WebView) findViewById(R.id.web_view);
		mBackButton = (Button) findViewById(R.id.btn_back);
		mShareButton = (ImageButton) findViewById(R.id.ib_share);
		
		initData();
	}
	
	private void initData() {
		Bundle bundle = getIntent().getExtras();
		String url = bundle.getString("url");
		WebSettings settings = mWebView.getSettings();
		settings.setJavaScriptEnabled(true);
		mWebView.loadUrl(url);
		
		mBackButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
			}
			
		});
		
		mShareButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				
			}
			
		});
	}
}
