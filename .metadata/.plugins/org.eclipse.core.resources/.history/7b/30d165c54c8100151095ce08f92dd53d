package com.lex.photowall;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.util.LruCache;

public class ImageLoader {
	/**
	 * 图片缓存技术的核心类，用于缓存所有下载好的图片，在程序内存达到设定值时回将最少最近使用的图片移除掉
	 */
	private static LruCache<String, Bitmap> mMemoryCache;
	
	/*
	 * 类实例
	 */
	private static ImageLoader mImageLoader;
	
	private ImageLoader() {
		//获取程序最大可用内存
		int maxMemory = (int)Runtime.getRuntime().maxMemory();
		//设置图片缓存到校为最大可用内存的1/8
		int cacheSize = maxMemory / 8;
		mMemoryCache = new LruCache<String, Bitmap>(cacheSize) {
			@Override
			protected int sizeOf(String key, Bitmap value) {
				return value.getByteCount();
			}
		};
	}
	
	public static ImageLoader getInstance() {
		if(mImageLoader == null) {
			mImageLoader = new ImageLoader();
		}
		return mImageLoader;
	}
	
	public void addBitmapToMemoryCache(String key, Bitmap bitmap) {
		if(getBitmapFromMemoryCache(key) == null) {
			mMemoryCache.put(key, bitmap);
		}
	}
	
	public Bitmap getBitmapFromMemoryCache(String key) {
		return mMemoryCache.get(key);
	}
	
	//
	public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth) {
		//源图片的宽度
		final int width = options.outWidth;
		int inSampleSize = 1;
		if(width > reqWidth) {
			//计算实际宽度与目标宽度的比率
			final int widthRatio = Math.round(width / (float)reqWidth);
			inSampleSize = widthRatio;
		}
		return inSampleSize;
	}
	
	public static Bitmap decodeSampledBitmapFromResource(String pathName, int reqWidth) {
		// 第一次解析将inJustDecodeBounds设置为true，来获取图片大小  
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeFile(pathName,options);
		options.inSampleSize = calculateInSampleSize(options, reqWidth);
		options.inJustDecodeBounds = false;
		return BitmapFactory.decodeFile(pathName,options);
	}

}
