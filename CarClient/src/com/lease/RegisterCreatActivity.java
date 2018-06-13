package com.lease;

import java.io.File;

import net.tsz.afinal.http.AjaxParams;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.pony.base.BaseActivity;
import com.pony.config.Consts;
import com.pony.model.ResponseEntry;
import com.pony.util.LoadingDialog;
import com.pony.util.ToastUtil;


public class RegisterCreatActivity extends BaseActivity  {
	// 标题
	private TextView mTvTitle;
	// 返回
	private ImageView mIvBack;
	private Button mSubmit;

	private EditText metName;
	private EditText metPhone;
	private EditText metPswd;
	
	private EditText metCode;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_creat_register);
		initWidget();
		initData();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.mIvBack:
			finish();
			break;

		case R.id.mSubmit:

			createTopicPost(true);

			break;

		}
	}

	@Override
	public void initWidget() {
		
		metCode = (EditText) findViewById(R.id.metCode);
		metName = (EditText) findViewById(R.id.metName);
		metPhone = (EditText) findViewById(R.id.metPhone);
		metPswd = (EditText) findViewById(R.id.metPswd);

		mSubmit = (Button) findViewById(R.id.mSubmit);
		mIvBack = (ImageView) findViewById(R.id.mIvBack);
		mTvTitle = (TextView) findViewById(R.id.mTvTitle);
		mTvTitle.setText("注册");
		mIvBack.setVisibility(View.VISIBLE);
		mIvBack.setOnClickListener(this);
		mSubmit.setOnClickListener(this);

	}

	@Override
	public void initData() {


	}




	private void createTopicPost(boolean isShow) {

		AjaxParams params = new AjaxParams();
		params.put("action_flag", "addUser");
		params.put("uname", metName.getText().toString());
		params.put("upswd", metPswd.getText().toString());
		params.put("uphone", metPhone.getText().toString());
		params.put("uCode", metCode.getText().toString());
		httpPost(Consts.URL + Consts.APP.RegisterAction, params, Consts.actionId.resultCode, isShow, "正在注册...");

	}

	@Override
	protected void callBackSuccess(ResponseEntry entry, int actionId) {
		super.callBackSuccess(entry, actionId);
		ToastUtil.show(RegisterCreatActivity.this, entry.getRepMsg());
		new Handler().postDelayed(new Runnable() {
			//
			@Override
			public void run() {
				finish();
			}
		}, 2000);
	}

	@Override
	protected void callBackAllFailure(String strMsg, int actionId) {
		super.callBackAllFailure(strMsg, actionId);
		ToastUtil.show(RegisterCreatActivity.this, strMsg);

	}
}
