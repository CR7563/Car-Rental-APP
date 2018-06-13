package com.lease;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import net.tsz.afinal.http.AjaxParams;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

import com.pony.base.BaseActivity;
import com.pony.config.Consts;
import com.pony.db.MemberUserUtils;
import com.pony.model.CarModel;
import com.pony.model.ResponseEntry;
import com.pony.model.UserModel;
import com.pony.time.JudgeDate;
import com.pony.time.MyAlertDialog;
import com.pony.time.ScreenInfo;
import com.pony.time.WheelMain;
import com.pony.util.ToastUtil;

public class MoneyActivity extends BaseActivity {

	// 标题
	private TextView mTvTitle;
	// 返回
	private ImageView mIvBack;
	private Button mPay;
	private int choiceType = 1;
	private RadioGroup mrdChoice;
	private RadioButton mrbWeiXin = null;
	private RadioButton mrbPay = null;

	UserModel userModel;
	private CarModel houseModel;

	
	private EditText metName;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_moeny_message);
		initWidget();
		initData();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.mIvBack:
			finish();
			break;
		case R.id.mPay:
			RegisterAction(true);
			break;

		}
	}

	@Override
	public void initWidget() {


		metName = (EditText)findViewById(R.id.metName);
		mrdChoice = (RadioGroup) findViewById(R.id.mrdChoice);
		mrbWeiXin = (RadioButton) findViewById(R.id.mrbWeiXin);
		mrbPay = (RadioButton) findViewById(R.id.mrbPay);
		mrdChoice = (RadioGroup) findViewById(R.id.mrdChoice);
		mrbWeiXin = (RadioButton) findViewById(R.id.mrbWeiXin);
		mrbPay = (RadioButton) findViewById(R.id.mrbPay);
		mPay = (Button) findViewById(R.id.mPay);
		mIvBack = (ImageView) findViewById(R.id.mIvBack);
		mTvTitle = (TextView) findViewById(R.id.mTvTitle);
		mTvTitle.setText("金额充值");
		mIvBack.setVisibility(View.VISIBLE);
		mIvBack.setOnClickListener(this);
		mPay.setOnClickListener(this);
	}

	@Override
	public void initData() {

		houseModel = (CarModel) this.getIntent().getSerializableExtra("msg");
		userModel = (UserModel) MemberUserUtils.getBean(this, "user_messgae");

		mrdChoice.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				if (checkedId == mrbWeiXin.getId()) {
					choiceType = 1;
				} else if (checkedId == mrbPay.getId()) {
					choiceType = 2;
				}
			}
		});
	}

	/**
	 * 订单的添加
	 * 
	 * @param isShow
	 */
	private void RegisterAction(boolean isShow) {
		UserModel userModel = (UserModel) MemberUserUtils.getBean(this, "user_messgae");
		AjaxParams params = new AjaxParams();
		params.put("action_flag", "updateMoney");
		params.put("uMoney", (Integer.valueOf(metName.getText().toString())+Integer.valueOf(userModel.getuMoney()))+"");
		params.put("uid", MemberUserUtils.getUid(this));
		httpPost(Consts.URL + Consts.APP.RegisterAction, params, Consts.actionId.resultFlag, isShow, "正在更新...");
	}

	@Override
	protected void callBackSuccess(ResponseEntry entry, int actionId) {
		super.callBackSuccess(entry, actionId);
		ToastUtil.show(MoneyActivity.this, entry.getRepMsg());
		
		UserModel userModel = (UserModel) MemberUserUtils.getBean(this, "user_messgae");
		userModel.setuMoney((Integer.valueOf(metName.getText().toString())+Integer.valueOf(userModel.getuMoney()))+"");
		MemberUserUtils.putBean(this, "user_messgae", userModel);
		
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				MoneyActivity.this.finish();
			}
		}, 2000);
	}

	@Override
	protected void callBackAllFailure(String strMsg, int actionId) {
		super.callBackAllFailure(strMsg, actionId);
		ToastUtil.show(MoneyActivity.this, strMsg);
	}

	
}
