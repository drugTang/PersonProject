package com.lex.love.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.os.AsyncTask;

public class HttpUtils {
	public static final int TIME_OUT_MILLIS = 5000;
	public static final String REQUEST_METHOD = "GET";
	
	public static void send(String url, HttpCallbackListener listener) {
		new MyTask(listener).execute(url);
	}

	static class MyTask extends AsyncTask<String, Integer, String> {
		
		private HttpCallbackListener mListener;
		private Exception mError;
		
		public MyTask(HttpCallbackListener listener) {
			mListener = listener;
		}

		@Override
		protected String doInBackground(String... params) {
			HttpURLConnection connection = null;
			String result = null;
			try {
				URL url = new URL(params[0]);
				connection = (HttpURLConnection) url.openConnection();
				connection.setConnectTimeout(TIME_OUT_MILLIS);
				connection.setRequestMethod(REQUEST_METHOD);
				connection.setReadTimeout(TIME_OUT_MILLIS);
				connection.connect();
				
				InputStream input = connection.getInputStream();
				BufferedReader reader = new BufferedReader(new InputStreamReader(input));
				StringBuilder sb = new StringBuilder();
				String line = "";
				while((line = reader.readLine()) != null) {
					sb.append(line+"\n");
				}
				result = sb.toString();
			} catch (MalformedURLException e) {
				mError = e;
				e.printStackTrace();
			} catch (IOException e) {
				mError = e;
				e.printStackTrace();
			} finally {
				connection.disconnect();
			}
			return result;
		}

		@Override
		protected void onPostExecute(String result) {
			if(result != null) {
				mListener.onSuccess(result);
			} else {
				mListener.onFailure(mError, mError.getMessage());	
			}
		}
		
	}
}
