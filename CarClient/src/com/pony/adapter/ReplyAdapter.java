package com.pony.adapter;

import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lease.R;
import com.pony.listener.ReviewListner;
import com.pony.model.ReviewModel;


//import com.tenant.model.CourseModel;

public class ReplyAdapter extends BaseAdapter {
	private Context mContext;
	private List<ReviewModel> list_result;
	private ReviewListner mReviewListner;

	public ReplyAdapter(Context mContext) {
		super();
		this.mContext = mContext;
	}

	public ReplyAdapter(Context mContext, List<ReviewModel> list_msg) {
		super();
		this.mContext = mContext;
		this.list_result = list_msg;
		notifyDataSetChanged();
	}

	public List<ReviewModel> setData(List<ReviewModel> list_str) {
		return list_result = list_str;

	}

	@Override
	public int getCount() {
		return list_result.size();
	}

	@Override
	public Object getItem(int position) {
		return list_result.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		ViewHolder vh = null;

		if (convertView == null) {
			convertView = LayoutInflater.from(mContext).inflate(R.layout.item_reply, null);
			vh = new ViewHolder();
			vh.message_name = (TextView) convertView.findViewById(R.id.message_name);
			vh.message_state = (TextView) convertView.findViewById(R.id.message_state);
			vh.messageContent = (TextView) convertView.findViewById(R.id.messageContent);
			vh.mtvReplyContent = (TextView) convertView.findViewById(R.id.mtvReplyContent);
			vh.mtvContent = (TextView) convertView.findViewById(R.id.mtvContent);

			convertView.setTag(vh);

		} else {
			vh = (ViewHolder) convertView.getTag();
		}

		vh.message_name.setText(list_result.get(position).getrUserName());
		vh.message_state.setText(list_result.get(position).getrCreatime());
		// vh.messageContent.setText("评论内容："+list_result.get(position).getrReviewContent());
		vh.mtvContent.setText(list_result.get(position).getrReviewContent());

//		if (list_result.get(position).getrUserName().indexOf("回复") != -1) {
//
//			int startPos = 0;
//			String reviewMessage = list_result.get(position).getrUserName();
//			if (reviewMessage.indexOf("回复") != -1) {
//				startPos = reviewMessage.indexOf("回复");
//			} else {
//				System.out.println("不包含");
//			}
//			SpannableStringBuilder builder = new SpannableStringBuilder(reviewMessage);
//
//			// ForegroundColorSpan 为文字前景色，BackgroundColorSpan为文字背景色
//			ForegroundColorSpan reviewMsg = new ForegroundColorSpan(Color.parseColor("#666666"));
//			ForegroundColorSpan huifuMsg = new ForegroundColorSpan(Color.parseColor("#40ac44"));
//
//			builder.setSpan(reviewMsg, 0, startPos - 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//			builder.setSpan(huifuMsg, startPos, startPos + 1, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
//			builder.setSpan(reviewMsg, startPos + 2, reviewMessage.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//			vh.message_name.setText(builder);  
//		} else {
//			vh.message_name.setText(list_result.get(position).getrUserName());
//		}

		// if(TextUtils.isEmpty(list_result.get(position).getrReplyContent())&&list_result.get(position).getrReplyContent().equals("")){
		// vh.mtvReplyContent .setVisibility(View.GONE);
		// }else{
		// vh.mtvReplyContent .setVisibility(View.VISIBLE);
		// vh.mtvReplyContent.setText("回复内容："+list_result.get(position).getrReplyContent());
		// }

		vh.mtvContent.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mReviewListner.setRemove(position, list_result.get(position));
			}
		});

		return convertView;
	}

	class ViewHolder {
		TextView message_name;
		TextView message_state;
		TextView messageContent;
		TextView mtvContent;
		TextView mtvReplyContent;
	}
}