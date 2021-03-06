package com.lex.love.view;

import java.util.Timer;
import java.util.TimerTask;

import com.lex.love.R;

import android.content.Context;
import android.media.MediaPlayer;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * 模拟打字机的效果,并带有打字音频
 * 
 * @author Administrator
 *
 */
public class TypeView extends TextView {
	private OnTypeViewListener mTypeViewListener;
	private static final int TYPE_TIME_DELAY = 200;
	private int mTypeTimeDelay = TYPE_TIME_DELAY;
	private String mShowTextString = null;
	private Timer mTypeTimer;
	private MediaPlayer mMediaPlayer;
	private Context mContext;

	public TypeView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		initViewContext();
	}

	public TypeView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initViewContext();
	}

	public TypeView(Context context) {
		super(context);
		initViewContext();
	}
	
	private void initViewContext() {
		mContext = getContext();
	}
	
	/**
	 * 设置监听器
	 *
	 */
	public void setOnTypeViewListener(OnTypeViewListener listener) {
		mTypeViewListener = listener;
	}
	
	public void start(String textString) {
		start(textString, TYPE_TIME_DELAY);
	}
	
	public void start(final String textString, final int typeTimeDelay) {
		if(TextUtils.isEmpty(textString) || typeTimeDelay < 0) {
			return;
		}
		post(new Runnable() {

			@Override
			public void run() {
				mTypeTimeDelay = typeTimeDelay;
				mShowTextString = textString;
				setText("");
				startTypeTimer();
				if(null != mTypeViewListener) {
					mTypeViewListener.onTypeStart();
				}
			}
			
		});
	}
	
	public void stop() {
		stopTypeTimer();
		stopAudio();
	}
	
	/**
	 * 开始音频播放
	 */
	protected void startAudioPlay() {
		stopAudio();
		playAudio(R.raw.type_in);
	}
	private void playAudio(int resId) {
		mMediaPlayer = MediaPlayer.create(mContext, resId);
		mMediaPlayer.start();
	}
	
	/**
	 * 停止音频播放
	 */
	private void stopAudio() {
		if(null != mMediaPlayer && mMediaPlayer.isPlaying()) {
			mMediaPlayer.stop();
			mMediaPlayer.release();
			mMediaPlayer = null;
		}
	}
	
	/**
	 * 开启打印定时器
	 */
	private void startTypeTimer() {
		stopTypeTimer();//确保定时器是关闭的之后再开启
		mTypeTimer = new Timer();
		mTypeTimer.schedule(new TypeTimeTask(), mTypeTimeDelay);
	}
	/**
	 * 关闭打印定时器
	 */
	private void stopTypeTimer() {
		if(null != mTypeTimer) {
			mTypeTimer.cancel();
			mTypeTimer = null;
		}
	}
	
	class TypeTimeTask extends TimerTask {

		@Override
		public void run() {
			post(new Runnable() {

				@Override
				public void run() {
					if(getText().toString().length() < mShowTextString.length()) {
						setText(mShowTextString.substring(0,getText().toString().length() + 1));
//						startAudioPlay();
						startTypeTimer();
					} else {
						stopTypeTimer();
						if(null != mTypeViewListener) {
							mTypeViewListener.onTypeOver();
						}
					}
				}});
		}
		
	} 
	
	/**
	 * 监听器
	 * 在打印开始前和打印开始后调用
	 * @author Administrator
	 *
	 */
	public interface OnTypeViewListener {
		public void onTypeStart();
		public void onTypeOver();
	}
	
}
