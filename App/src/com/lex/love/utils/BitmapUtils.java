package com.lex.love.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Environment;
import android.widget.ImageView;

public class BitmapUtils {
	private Context mContext;

	public BitmapUtils(Context context) {
		mContext = context;
	}

	public void display(ImageView image) {
		// 内存缓存
		// 本地缓存
		// 网络缓存

	}

	public class BitmapTask extends AsyncTask<String, Void, Bitmap> {

		@Override
		protected Bitmap doInBackground(String... params) {
			downloadBitmap(params[0]);
			return null;
		}

		@Override
		protected void onPostExecute(Bitmap result) {

		}

		private void downloadBitmap(String imageUrl) {
			HttpURLConnection connection = null;
			BufferedInputStream bis = null;
			FileOutputStream fos = null;
			BufferedOutputStream bos = null;
			try {
				URL url = new URL(imageUrl);
				connection = (HttpURLConnection) url.openConnection();
				connection.setRequestMethod("GET");
				connection.setConnectTimeout(5 * 1000);
				connection.setReadTimeout(15 * 1000);
				InputStream in = connection.getInputStream();
				bis = new BufferedInputStream(in);
				File file = new File(getImagePath(imageUrl));
				fos = new FileOutputStream(file);
				bos = new BufferedOutputStream(fos);
				byte[] b = new byte[1024];
				int length;
				while ((length = bis.read(b)) != -1) {
					bos.write(b, 0, length);
					bos.flush();
				}

			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (connection != null) {
					connection.disconnect();
				}
				try {
					if (bis != null) {
						bis.close();
					}
					if (fos != null) {
						bis.close();
					}
					if (bos != null) {
						bis.close();
					}

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		private String getImagePath(String imageUrl) {
			int lastIndex = imageUrl.lastIndexOf("/");
			String imageName = imageUrl.substring(lastIndex + 1);
			String imageDir = Environment.getDataDirectory().getPath() + "/life/";
			File file = new File(imageDir);
			if (!file.exists()) {
				file.mkdirs();
			}
			String imagePath = imageDir + imageName;
			return imagePath;
		}

	}

}
