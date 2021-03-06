package com.lex.photowall;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;

public class MyScrollView extends ScrollView implements OnTouchListener {
	/**
	 * 每页要加载的图片的数量
	 */
	public static final int PAGE_SIZE = 15;
	/**
	 * 记录当前加载到第几页
	 */
	private int page;
	/**
	 * 每一列的宽度
	 */
	private int columnWidth;
	private int firstColumnHeight;
	private int secondColumnHeight;
	private int thirdColumnHeight;
	/**
	 * 是否已经加载过Layout，这里的Layout初始化只需加载一次
	 */
	private boolean loadOnce;
	private ImageLoader imageLoader;

	private LinearLayout firstColumn;
	private LinearLayout secondColumn;
	private LinearLayout thirdColumn;

	private static Set<LoadImageTask> taskCollection;
	/**
	 * MyScrollView下的直接子布局。
	 */
	private static View scrollLayout;

	/**
	 * MyScrollView布局的高度。
	 */
	private static int scrollViewHeight;

	private static int lastScrollY = -1;
	/** 
     * 记录所有界面上的图片，用以可以随时控制对图片的释放。 
     */  
    private List<ImageView> imageViewList = new ArrayList<ImageView>(); 

	private Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			MyScrollView myScrollView = (MyScrollView) msg.obj;
			int scrollY = myScrollView.getScrollY();
			if (scrollY == lastScrollY) {
				if (scrollViewHeight + scrollY >= scrollLayout.getHeight() && taskCollection.isEmpty()) {
					myScrollView.loadMoreImage();
				}
				myScrollView.checkVisibility();
			} else {
				lastScrollY = scrollY;
				Message message = new Message();
				message.obj = myScrollView;
				handler.sendMessageDelayed(message, 5);
			}
		};
	};

	public MyScrollView(Context context, AttributeSet attrs) {
		super(context, attrs);
		imageLoader = ImageLoader.getInstance();
		taskCollection = new HashSet<LoadImageTask>();
		setOnTouchListener(this);
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		super.onLayout(changed, l, t, r, b);
		if (changed && !loadOnce) {
			scrollViewHeight = getHeight();
			scrollLayout = getChildAt(0);
			firstColumn = (LinearLayout) findViewById(R.id.first_column);
			secondColumn = (LinearLayout) findViewById(R.id.second_column);
			thirdColumn = (LinearLayout) findViewById(R.id.third_column);
			columnWidth = firstColumn.getWidth();
			loadOnce = true;
			loadMoreImage();
		}
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		if (event.getAction() == MotionEvent.ACTION_UP) {
			Message msg = new Message();
			msg.obj = this;
			handler.sendMessageDelayed(msg, 5);
		}
		return false;
	}

	private void checkVisibility() {
		for (int i = 0; i < imageViewList.size(); i++) {
			ImageView imageView = imageViewList.get(i);
			int borderTop = (Integer) imageView.getTag(R.string.border_top);
			int borderBottom = (Integer) imageView.getTag(R.string.border_bottom);
			if (borderBottom > getScrollY() && borderTop < getScrollY() + scrollViewHeight) {
				String imageUrl = (String) imageView.getTag(R.string.image_url);
				Bitmap bitmap = imageLoader.getBitmapFromMemoryCache(imageUrl);
				if (bitmap != null) {
					imageView.setImageBitmap(bitmap);
				} else {
					LoadImageTask task = new LoadImageTask(imageView);
					task.execute(imageUrl);
				}
			} else {
				imageView.setImageResource(R.drawable.empty_photo);
			}
		}
	}

	private void loadMoreImage() {
		if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
			int startIndex = page * PAGE_SIZE;
			int endIndex = page * PAGE_SIZE + PAGE_SIZE;
			if (startIndex < Images.imageUrls.length) {
				Toast.makeText(getContext(), "正在加载...", Toast.LENGTH_SHORT).show();
				if (endIndex > Images.imageUrls.length) {
					endIndex = Images.imageUrls.length;
				}
				for (int i = startIndex; i < endIndex; i++) {
					//每个图片开启一个进程去下载
					LoadImageTask task = new LoadImageTask();
					taskCollection.add(task);
					task.execute(Images.imageUrls[i]);
				}
				page++;
			} else {
				Toast.makeText(getContext(), "已没有更多图片", Toast.LENGTH_SHORT).show();
			}
		} else {
			Toast.makeText(getContext(), "未发现SD卡", Toast.LENGTH_SHORT).show();
		}
	}

	class LoadImageTask extends AsyncTask<String, Void, Bitmap> {
		/** 
         * 图片的URL地址 
         */  
        private String mImageUrl;  
  
        /** 
         * 可重复使用的ImageView 
         */  
        private ImageView mImageView;  
  
        public LoadImageTask() {  
        }  
  
        /** 
         * 将可重复使用的ImageView传入 
         *  
         * @param imageView 
         */  
        public LoadImageTask(ImageView imageView) {  
            mImageView = imageView;  
        }  
  
        @Override  
        protected Bitmap doInBackground(String... params) {  
            mImageUrl = params[0];  
            Bitmap imageBitmap = imageLoader  
                    .getBitmapFromMemoryCache(mImageUrl);  
            if (imageBitmap == null) {  
                imageBitmap = loadImage(mImageUrl);  
            }  
            return imageBitmap;  
        }  
  
        //UI线程执行
        @Override  
        protected void onPostExecute(Bitmap bitmap) {  
            if (bitmap != null) {  
                double ratio = bitmap.getWidth() / (columnWidth * 1.0);  
                int scaledHeight = (int) (bitmap.getHeight() / ratio);  
                addImage(bitmap, columnWidth, scaledHeight);  
            }  
            taskCollection.remove(this);  
        }  
  
        /** 
         * 根据传入的URL，对图片进行加载。如果这张图片已经存在于SD卡中，则直接从SD卡里读取，否则就从网络上下载。 
         *  
         * @param imageUrl 
         *            图片的URL地址 
         * @return 加载到内存的图片。 
         */  
        private Bitmap loadImage(String imageUrl) {  
            File imageFile = new File(getImagePath(imageUrl));  
            if (!imageFile.exists()) {  
                downloadImage(imageUrl);  
            } else { 
//            if (imageUrl != null) {  
                Bitmap bitmap = ImageLoader.decodeSampledBitmapFromResource(  
                        imageFile.getPath(), columnWidth);  
                if (bitmap != null) {  
                    imageLoader.addBitmapToMemoryCache(imageUrl, bitmap);  
                    return bitmap;  
                }  
            }  
            return null;  
        }  
  
        /** 
         * 向ImageView中添加一张图片 
         *  
         * @param bitmap 
         *            待添加的图片 
         * @param imageWidth 
         *            图片的宽度 
         * @param imageHeight 
         *            图片的高度 
         */  
        private void addImage(Bitmap bitmap, int imageWidth, int imageHeight) {  
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(  
                    imageWidth, imageHeight);  
            if (mImageView != null) {  
                mImageView.setImageBitmap(bitmap);  
            } else {  
                ImageView imageView = new ImageView(getContext());  
                imageView.setLayoutParams(params);  
                imageView.setImageBitmap(bitmap);  
                imageView.setScaleType(ScaleType.FIT_XY);  
                imageView.setPadding(5, 5, 5, 5);  
                imageView.setTag(R.string.image_url, mImageUrl);  
                findColumnToAdd(imageView, imageHeight).addView(imageView);  
                imageViewList.add(imageView);  
            }  
        }  
  
        /** 
         * 找到此时应该添加图片的一列。原则就是对三列的高度进行判断，当前高度最小的一列就是应该添加的一列。 
         *  
         * @param imageView 
         * @param imageHeight 
         * @return 应该添加图片的一列 
         */  
        private LinearLayout findColumnToAdd(ImageView imageView,  
                int imageHeight) {  
            if (firstColumnHeight <= secondColumnHeight) {  
                if (firstColumnHeight <= thirdColumnHeight) {  
                    imageView.setTag(R.string.border_top, firstColumnHeight);  
                    firstColumnHeight += imageHeight;  
                    imageView.setTag(R.string.border_bottom, firstColumnHeight);  
                    return firstColumn;  
                }  
                imageView.setTag(R.string.border_top, thirdColumnHeight);  
                thirdColumnHeight += imageHeight;  
                imageView.setTag(R.string.border_bottom, thirdColumnHeight);  
                return thirdColumn;  
            } else {  
                if (secondColumnHeight <= thirdColumnHeight) {  
                    imageView.setTag(R.string.border_top, secondColumnHeight);  
                    secondColumnHeight += imageHeight;  
                    imageView  
                            .setTag(R.string.border_bottom, secondColumnHeight);  
                    return secondColumn;  
                }  
                imageView.setTag(R.string.border_top, thirdColumnHeight);  
                thirdColumnHeight += imageHeight;  
                imageView.setTag(R.string.border_bottom, thirdColumnHeight);  
                return thirdColumn;  
            }  
        }  
  
        /** 
         * 将图片下载到SD卡缓存起来。 
         *  
         * @param imageUrl 
         *            图片的URL地址。 
         */  
        private void downloadImage(String imageUrl) {  
            HttpURLConnection con = null;  
            FileOutputStream fos = null;  
            BufferedOutputStream bos = null;  
            BufferedInputStream bis = null;  
            File imageFile = null;  
            try {  
                URL url = new URL(imageUrl);  
                con = (HttpURLConnection) url.openConnection();  
                con.setConnectTimeout(5 * 1000);  
                con.setReadTimeout(15 * 1000);  
                con.setDoInput(true);  
                con.setDoOutput(true);  
                bis = new BufferedInputStream(con.getInputStream());  
                imageFile = new File(getImagePath(imageUrl));  
                fos = new FileOutputStream(imageFile);  
                bos = new BufferedOutputStream(fos);  
                byte[] b = new byte[1024];  
                int length;  
                while ((length = bis.read(b)) != -1) {  
                    bos.write(b, 0, length);  
                    bos.flush();  
                }  
            } catch (Exception e) {  
                e.printStackTrace();  
            } finally {  
                try {  
                    if (bis != null) {  
                        bis.close();  
                    }  
                    if (bos != null) {  
                        bos.close();  
                    }  
                    if (con != null) {  
                        con.disconnect();  
                    }  
                } catch (IOException e) {  
                    e.printStackTrace();  
                }  
            }  
            if (imageFile != null) {  
                Bitmap bitmap = ImageLoader.decodeSampledBitmapFromResource(  
                        imageFile.getPath(), columnWidth);  
                if (bitmap != null) {  
                    imageLoader.addBitmapToMemoryCache(imageUrl, bitmap);  
                }  
            }  
        }  
  
        /** 
         * 获取图片的本地存储路径。 
         *  
         * @param imageUrl 
         *            图片的URL地址。 
         * @return 图片的本地存储路径。 
         */  
        private String getImagePath(String imageUrl) {  
            int lastSlashIndex = imageUrl.lastIndexOf("/");  
            String imageName = imageUrl.substring(lastSlashIndex + 1);  
            String imageDir = Environment.getExternalStorageDirectory()  
                    .getPath() + "/PhotoWallFalls/";  
            File file = new File(imageDir);  
            if (!file.exists()) {  
                file.mkdirs();  
            }  
            String imagePath = imageDir + imageName;  
            return imagePath;  
        }  
    }  

}
