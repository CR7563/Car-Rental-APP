package com.lease;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import net.tsz.afinal.http.AjaxParams;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
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
import com.pony.view.DialogMsg;

public class PayMessageActivity extends BaseActivity {

	// 标题
	private TextView mTvTitle;
	// 返回
	private ImageView mIvBack;
	private TextView mtvPrice;
	private Button mPay;
	private int choiceType = 1;
	private RadioGroup mrdChoice;
	private RadioButton mrbWeiXin = null;
	private RadioButton mrbPay = null;

	private TextView mtvName;
	UserModel userModel;
	private CarModel houseModel;

	private Button mbtnStartDate;
	private Button mbtnEndDate;
	private EditText metScore;
	private int choiceTime = 1;

	private DialogMsg dialogMsg;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pay_message);
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

			if (TextUtils.isEmpty(metScore.getText().toString())) {
				ToastUtil.ShowCentre(this, "请输入积分");
				return;
			}

			UserModel userModel = (UserModel) MemberUserUtils.getBean(this, "user_messgae");

			
			if (Integer.valueOf(userModel.getuMoney()) < dayTotal) {
				ToastUtil.ShowCentre(this, "余额不足，请充值");
				return;
			}
			
			
			if (Integer.valueOf(userModel.getScoreMessage()) < Integer.valueOf(metScore.getText().toString())) {
				ToastUtil.ShowCentre(this, "请输入的积分大于你拥有的积分");
				return;
			}

			if (dayTotal > Integer.valueOf(userModel.getuMoney())) {
				dialogMsg.Show();
			} else {

				OrderAction(true);
			}

			break;

		case R.id.mbtnStartDate:

			if (TextUtils.isEmpty(metScore.getText().toString())) {
				ToastUtil.ShowCentre(this, "请输入积分");
				return;
			}

			choiceTime = 1;
			showTime();
			break;

		case R.id.mbtnEndDate:

			if (mbtnStartDate.getText().toString().equals("请选择开始时间")) {
				ToastUtil.ShowCentre(this, "请选择开始时间");
				return;
			}

			choiceTime = 2;
			showTime();
			break;
		}
	}

	@Override
	public void initWidget() {

		dialogMsg = new DialogMsg(this);
		dialogMsg.Set_Msg("余额不足，确认充值吗？");
		dialogMsg.submit_ok().setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent mrlMoney = new Intent(PayMessageActivity.this, MoneyActivity.class);
				startActivity(mrlMoney);
				dialogMsg.Close();
			}
		});

		dialogMsg.submit_no().setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				dialogMsg.Close();
			}
		});

		mbtnStartDate = (Button) findViewById(R.id.mbtnStartDate);
		mbtnEndDate = (Button) findViewById(R.id.mbtnEndDate);

		metScore = (EditText) findViewById(R.id.metScore);
		mbtnStartDate.setOnClickListener(this);
		mbtnEndDate.setOnClickListener(this);

		mrdChoice = (RadioGroup) findViewById(R.id.mrdChoice);
		mrbWeiXin = (RadioButton) findViewById(R.id.mrbWeiXin);
		mrbPay = (RadioButton) findViewById(R.id.mrbPay);
		mtvName = (TextView) findViewById(R.id.mtvName);
		mrdChoice = (RadioGroup) findViewById(R.id.mrdChoice);
		mrbWeiXin = (RadioButton) findViewById(R.id.mrbWeiXin);
		mrbPay = (RadioButton) findViewById(R.id.mrbPay);
		mPay = (Button) findViewById(R.id.mPay);
		mtvPrice = (TextView) findViewById(R.id.mtvPrice);
		mIvBack = (ImageView) findViewById(R.id.mIvBack);
		mTvTitle = (TextView) findViewById(R.id.mTvTitle);
		mTvTitle.setText("支付确认");
		mIvBack.setVisibility(View.VISIBLE);
		mIvBack.setOnClickListener(this);
		mPay.setOnClickListener(this);
	}

	private String socreMessage;

	@Override
	public void initData() {

		houseModel = (CarModel) this.getIntent().getSerializableExtra("msg");
		userModel = (UserModel) MemberUserUtils.getBean(this, "user_messgae");
		//
		// if (userModel.getScoreMessage() == null) {
		//
		// socreMessage = "0";
		//
		// } else {
		// socreMessage = userModel.getScoreMessage();
		// }

		metScore.setHint("你拥有：" + userModel.getScoreMessage() + "积分");

		mtvPrice.setText(houseModel.getCarMoney() + "元/天");
		mtvName.setText(houseModel.getCarNo());

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
	private void OrderAction(boolean isShow) {

		AjaxParams params = new AjaxParams();
		params.put("action_flag", "addOrder");
		params.put("orderUserId", MemberUserUtils.getUid(this));
		params.put("orderUserName", MemberUserUtils.getName(this));
		params.put("orderMessageId", houseModel.getCarId());
		params.put("orderMoney", dayTotal + "");
		httpPost(Consts.URL + Consts.APP.OrderAction, params, Consts.actionId.resultFlag, isShow, "正在支付...");
	}
	
	private void RegisterAction(boolean isShow) {
		UserModel userModel = (UserModel) MemberUserUtils.getBean(this, "user_messgae");
		AjaxParams params = new AjaxParams();
		params.put("action_flag", "updateMoney");
		params.put("uMoney", ((Integer.valueOf(userModel.getuMoney()) - dayTotal) + "")+"");
		params.put("uid", MemberUserUtils.getUid(this));
		httpPost(Consts.URL + Consts.APP.RegisterAction, params, Consts.actionId.resultCode, isShow, "正在更新...");
	}
	

	@Override
	protected void callBackSuccess(ResponseEntry entry, int actionId) {
		super.callBackSuccess(entry, actionId);
		
		switch (actionId) {
		case Consts.actionId.resultFlag:
//			UserModel userModel = (UserModel) MemberUserUtils.getBean(this, "user_messgae");
//			userModel.setScoreMessage((Integer.valueOf(userModel.getScoreMessage()) + 1 - Integer.valueOf(metScore.getText().toString())) + "");
//			userModel.setuMoney((Integer.valueOf(userModel.getuMoney()) - dayTotal) + "");
//			MemberUserUtils.putBean(this, "user_messgae", userModel);
//
//			ToastUtil.show(PayMessageActivity.this, entry.getRepMsg() + "，可在订单查看");
//			new Handler().postDelayed(new Runnable() {
//				@Override
//				public void run() {
//					PayMessageActivity.this.finish();
//				}
//			}, 2000);
//			
			
			RegisterAction(false);
			break;
			
		case Consts.actionId.resultCode:
			UserModel userModel = (UserModel) MemberUserUtils.getBean(this, "user_messgae");
			userModel.setScoreMessage((Integer.valueOf(userModel.getScoreMessage()) + 1 - Integer.valueOf(metScore.getText().toString())) + "");
			userModel.setuMoney((Integer.valueOf(userModel.getuMoney()) - dayTotal) + "");
			MemberUserUtils.putBean(this, "user_messgae", userModel);

			ToastUtil.show(PayMessageActivity.this, entry.getRepMsg() + "，可在订单查看");
			new Handler().postDelayed(new Runnable() {
				@Override
				public void run() {
					PayMessageActivity.this.finish();
				}
			}, 2000);
			break;
		}


	}

	@Override
	protected void callBackAllFailure(String strMsg, int actionId) {
		super.callBackAllFailure(strMsg, actionId);
		ToastUtil.show(PayMessageActivity.this, strMsg);
	}

	WheelMain wheelMain;
	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

	private void showTime() {
		LayoutInflater inflater1 = LayoutInflater.from(this);
		final View timepickerview1 = inflater1.inflate(R.layout.timepicker, null);
		ScreenInfo screenInfo1 = new ScreenInfo(this);
		wheelMain = new WheelMain(timepickerview1);
		wheelMain.screenheight = screenInfo1.getHeight();
		Calendar calendar = Calendar.getInstance();
		String time1 = calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH) + 1) + "-" + calendar.get(Calendar.DAY_OF_MONTH) + "";
		Calendar calendar1 = Calendar.getInstance();
		if (JudgeDate.isDate(time1, "yyyy-MM-dd")) {
			try {
				calendar1.setTime(dateFormat.parse(time1));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		int year1 = calendar1.get(Calendar.YEAR);
		int month1 = calendar1.get(Calendar.MONTH);
		int day1 = calendar1.get(Calendar.DAY_OF_MONTH);
		wheelMain.initDateTimePicker(year1, month1, day1);

		final MyAlertDialog dialog = new MyAlertDialog(this).builder().setTitle("选择时间").setView(timepickerview1)
				.setNegativeButton("取消", new OnClickListener() {
					@Override
					public void onClick(View v) {

					}
				});
		dialog.setPositiveButton("保存", new OnClickListener() {
			@Override
			public void onClick(View v) {

				if (choiceTime == 1) {
					mbtnStartDate.setText(wheelMain.getTime());
				} else {
					mbtnEndDate.setText(wheelMain.getTime());

					TotalMoney(mbtnStartDate.getText().toString(), mbtnEndDate.getText().toString());
				}

			}
		});
		dialog.show();

	}

	public static int differentDaysByMillisecond(Date date1, Date date2) {
		int days = (int) ((date2.getTime() - date1.getTime()) / (1000 * 3600 * 24));
		return days;
	}

	private int dayTotal;

	private void TotalMoney(String dateStart, String dateEnd) {
		{
			SimpleDateFormat formatStart = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat formatEnd = new SimpleDateFormat("yyyy-MM-dd");
			try {
				Date dateStartMessage = formatStart.parse(dateStart);
				Date dateEndMessage = formatEnd.parse(dateEnd);

				System.out.println("两个日期的差距：" + differentDaysByMillisecond(dateStartMessage, dateEndMessage));

				if (differentDaysByMillisecond(dateStartMessage, dateEndMessage) == 0) {
					ToastUtil.ShowCentre(this, "开始时间和结束时间不能相同");
					return;
				}

				dayTotal = (Integer.valueOf(houseModel.getCarMoney()) * differentDaysByMillisecond(dateStartMessage, dateEndMessage))
						- (Integer.valueOf(metScore.getText().toString()) * 10);
				mtvPrice.setText(dayTotal + "元(" + differentDaysByMillisecond(dateStartMessage, dateEndMessage) + "天)");

			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
	}

}
