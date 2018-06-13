package com.lease;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.pony.base.BaseActivity;
import com.pony.db.MemberUserUtils;


public class SoftMessageActivity extends BaseActivity {

	// ����
	private TextView mTvTitle;
	// ����
	private ImageView mIvBack;

	private Button mExit;
	
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_soft_message);
		initWidget();
		initData();
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.mIvBack:
			finish();
			break;
		case R.id.mExit:
			MemberUserUtils.setUid(SoftMessageActivity.this,"");
			Intent intent = new Intent(SoftMessageActivity.this, LoginActivity.class);
			startActivity(intent);
			finish();
			break;
		}
	}

	@Override
	public void initWidget() {
		
		mExit = (Button) findViewById(R.id.mExit);
		mIvBack = (ImageView) findViewById(R.id.mIvBack);
		mTvTitle = (TextView) findViewById(R.id.mTvTitle);


		mIvBack.setVisibility(View.VISIBLE);
		mIvBack.setOnClickListener(this);
		mExit.setOnClickListener(this);
		mTvTitle.setText("�����Ϣ");

	}

	

	@Override
	public void initData() {

	}


}
