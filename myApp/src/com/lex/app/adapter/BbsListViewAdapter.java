package com.lex.app.adapter;

import java.util.List;

import com.lex.app.BaseApplication;
import com.lex.app.R;
import com.lex.app.constans.Constans;
import com.lex.app.domain.MessageData.SingleData;
import com.lidroid.xutils.BitmapUtils;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class BbsListViewAdapter extends BaseAdapter {
	private List<SingleData> mDataList;
	private BitmapUtils bitmapUtils;
	private Context context;

	public BbsListViewAdapter(List<SingleData> list) {
		mDataList = list;
		context = BaseApplication.getContext();
		bitmapUtils = new BitmapUtils(context);
	}

	@Override
	public int getCount() {
		return mDataList.size();
	}

	@Override
	public SingleData getItem(int position) {
		return mDataList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder;
		if (convertView == null) {
			convertView = View.inflate(context, R.layout.bbs_content_list_view_item, null);
			viewHolder = new ViewHolder();
			viewHolder.headicon = (ImageButton) convertView.findViewById(R.id.ib_head);
			viewHolder.username = (TextView) convertView.findViewById(R.id.tv_name);
			viewHolder.info = (TextView) convertView.findViewById(R.id.tv_info);
			viewHolder.more = (ImageButton) convertView.findViewById(R.id.ib_more);
			viewHolder.content = (TextView) convertView.findViewById(R.id.tv_content);
			viewHolder.rlLike = (LinearLayout) convertView.findViewById(R.id.ll_like);
			viewHolder.likeImage = (ImageView) convertView.findViewById(R.id.iv_like);
			viewHolder.likeText = (TextView) convertView.findViewById(R.id.tv_like);
			viewHolder.rlShare = (LinearLayout) convertView.findViewById(R.id.ll_share);
			viewHolder.rlComment = (LinearLayout) convertView.findViewById(R.id.ll_comment);
			viewHolder.commentText = (TextView) convertView.findViewById(R.id.tv_comment);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder)convertView.getTag();
		}
		SingleData data = mDataList.get(position);
		bitmapUtils.display(viewHolder.headicon, Constans.SERVER_URL + data.headicon);
		viewHolder.username.setText(data.username);
		viewHolder.info.setText(data.pubdate+"  "+ data.from);
		viewHolder.content.setText(data.content);
		viewHolder.likeText.setText(data.likecount);
		
		return convertView;
	}

	class ViewHolder {
		public ImageButton headicon;
		public TextView username;
		public TextView info;
		public ImageButton more;
		public TextView content;
		public LinearLayout rlLike;
		public ImageView likeImage;
		public TextView likeText;
		public LinearLayout rlShare;
		public LinearLayout rlComment;
		public TextView commentText;
	}

}
