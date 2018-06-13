package com.lease;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.pony.base.BaseActivity;
import com.pony.db.MemberUserUtils;
import com.pony.model.UserModel;

/**
 * 
 *  用户信息的展示
 */
public class UserActivity extends BaseActivity {

	// 标题
	private TextView mTvTitle;
	// 返回
	private ImageView mIvBack;
	private TextView mName;
	private String state;
	private RelativeLayout mtvUserName;
	private TextView mPhone;
	private TextView mAddress;
	private RelativeLayout mrlPhone;

	
	private TextView uRealName;
	
	private RelativeLayout mrlMoney;
	private RelativeLayout mrlAddress;
	
	private TextView mtvMoney;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user);
		
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.mIvBack:
			UserActivity.this.finish();
			break;
		case R.id.mrlPhone:
	
			break;
		case R.id.mrlAddress:
			Intent mrlAddress = new Intent(UserActivity.this, UpdatePswdActivity.class);
			startActivity(mrlAddress);
			break;
			
		case R.id.mrlMoney:
			Intent mrlMoney = new Intent(UserActivity.this, MoneyActivity.class);
			startActivity(mrlMoney);
			break;
		}
	}

	@Override
	public void initWidget() {
		
		
		
		mtvMoney = (TextView) findViewById(R.id.mtvMoney);
		uRealName = (TextView) findViewById(R.id.uRealName);
		mrlMoney = (RelativeLayout) findViewById(R.id.mrlMoney);
		
		mPhone = (TextView) findViewById(R.id.mPhone);
		mAddress = (TextView) findViewById(R.id.mAddress);
		mrlPhone = (RelativeLayout) findViewById(R.id.mrlPhone);
		mrlAddress = (RelativeLayout) findViewById(R.id.mrlAddress);
		mIvBack = (ImageView) findViewById(R.id.mIvBack);
		mTvTitle = (TextView) findViewById(R.id.mTvTitle);
		mName = (TextView) findViewById(R.id.mName);

		mTvTitle.setText("我的资料");
		mIvBack.setVisibility(View.VISIBLE);
		mIvBack.setOnClickListener(this);
		mrlAddress.setOnClickListener(this);
		mrlMoney.setOnClickListener(this);
	}

	@Override
	public void initData() {

		try {
			UserModel userModel = (UserModel) MemberUserUtils.getBean(this, "user_messgae");
			mName.setText("用户名称： " + MemberUserUtils.getName(this)+" ( 积分："+userModel.getScoreMessage()+" )");
			mPhone.setText("手机号码： " + userModel.getUphone());
			uRealName.setText("驾驶证号： " + userModel.getuCode());
			mtvMoney.setText("用户余额： " + userModel.getuMoney()+"元");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	
	@Override
	protected void onResume() {
		super.onResume();
		initWidget();
		initData();
	}
}
