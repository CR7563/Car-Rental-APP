package com.lease;

import net.tsz.afinal.http.AjaxParams;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.pony.base.BaseActivity;
import com.pony.config.Consts;
import com.pony.db.MemberUserUtils;
import com.pony.model.OrderModel;
import com.pony.model.ResponseEntry;
import com.pony.util.ToastUtil;

public class OrderMessageActivity extends BaseActivity {

	// 标题
	private TextView mTvTitle;
	// 返回
	private ImageView mIvBack;

	private OrderModel orderModel;

	private TextView mtvName;
	private TextView mtvMoney;
	private TextView mtvTime;


	private Button mbtnUpdate;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_order_message);
		initWidget();
		initData();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.mIvBack:
			finish();
			break;
		case R.id.mbtnUpdate:
			updateOpderState(true, "2");
			break;

		}
	}

	@Override
	public void initWidget() {

		mbtnUpdate = (Button) findViewById(R.id.mbtnUpdate);
		mbtnUpdate.setOnClickListener(this);

		mIvBack = (ImageView) findViewById(R.id.mIvBack);
		mTvTitle = (TextView) findViewById(R.id.mTvTitle);
		mtvName = (TextView) findViewById(R.id.mtvName);
		mtvMoney = (TextView) findViewById(R.id.mtvMoney);
		mtvTime = (TextView) findViewById(R.id.mtvTime);

		mIvBack.setVisibility(View.VISIBLE);
		mIvBack.setOnClickListener(this);
		mTvTitle.setText("订单详情");
	}

	@Override
	public void initData() {
		orderModel = (OrderModel) this.getIntent().getSerializableExtra("msg");
//		mtvName.setText(orderModel.getHouseMessage().getHouseName());
//		mtvMoney.setText("￥" + orderModel.getHouseMessage().getHouseMoney());
		mtvTime.setText(orderModel.getOrderCreateTime());


	}

	private void updateOpderState(boolean isShow, String stateMsg) {
		AjaxParams params = new AjaxParams();
		params.put("action_flag", "updateUserState");
		params.put("orderState", stateMsg);
		params.put("orderId", orderModel.getOrderId());
		httpPost(Consts.URL + Consts.APP.OrderAction, params, Consts.actionId.resultFlag, isShow, "正在提交...");
	}


	@Override
	protected void callBackSuccess(ResponseEntry entry, int actionId) {
		super.callBackSuccess(entry, actionId);

		switch (actionId) {
		case Consts.actionId.resultFlag:
			ToastUtil.show(OrderMessageActivity.this, entry.getRepMsg());
			new Handler().postDelayed(new Runnable() {
				@Override
				public void run() {
					OrderMessageActivity.this.finish();
				}
			}, 1000);
			break;
		}

	}

	@Override
	protected void callBackAllFailure(String strMsg, int actionId) {
		super.callBackAllFailure(strMsg, actionId);
		ToastUtil.show(OrderMessageActivity.this, strMsg);

	}
}
