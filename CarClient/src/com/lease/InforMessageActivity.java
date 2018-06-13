package com.lease;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.pony.base.BaseActivity;
import com.pony.config.Consts;
import com.pony.model.InforModel;
import com.pony.model.MessageModel;
import com.squareup.picasso.Picasso;


public class InforMessageActivity extends BaseActivity {

	// 标题
	private TextView mTvTitle;
	// 返回
	private ImageView mIvBack;

	private TextView mtvTime;
	private TextView mtvName;
	private TextView mtvMessage;
	private MessageModel foodModel;
	private TextView mIvStu;
	private ImageView infor_image;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_infor_messages);
		initWidget();
		initData();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.mIvBack:
			finish();
			break;
			
			
		}
	}

	@Override
	public void initWidget() {
		infor_image = (ImageView) findViewById(R.id.infor_image);
		mIvStu = (TextView) findViewById(R.id.mIvStu);
		mtvMessage = (TextView) findViewById(R.id.mtvMessage);
		mtvName = (TextView) findViewById(R.id.mtvName);
		mtvTime = (TextView) findViewById(R.id.mtvTime);

		mIvBack = (ImageView) findViewById(R.id.mIvBack);
		mTvTitle = (TextView) findViewById(R.id.mTvTitle);

		mIvStu.setVisibility(View.GONE);
		mIvBack.setVisibility(View.VISIBLE);
		mIvBack.setOnClickListener(this);
		mIvStu.setText("收藏");
		mTvTitle.setText("租车须知");
		mIvStu.setOnClickListener(this);
	}

	@Override
	public void initData() {

		try {
			foodModel = (MessageModel) this.getIntent().getSerializableExtra("msg");
			mtvName.setText(foodModel.getNewsTitle());
			mtvTime.setText(foodModel.getNewsTime());
			mtvMessage.setText("        "+foodModel.getNewsContent());
			
			
			Picasso.with(this).load(Consts.URL_IMAGE + foodModel.getNewsImage()).placeholder(R.drawable.default_drawable_show_pictrue)
			.into(infor_image);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	


}
