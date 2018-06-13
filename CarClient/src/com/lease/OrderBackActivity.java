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

public class OrderBackActivity extends BaseActivity {

	// 标题
	private TextView mTvTitle;
	// 返回
	private ImageView mIvBack;
	private OrderModel orderModel;
	private EditText metContent;
	private Button mbtnUpdate;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_opinion);
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
			if (TextUtils.isEmpty(metContent.getText().toString())) {
				ToastUtil.ShowCentre(this, "请输入退单内容");
				return;
			}
			insertUserMessageWords(true);
			break;
		}
	}

	@Override
	public void initWidget() {

		mIvBack = (ImageView) findViewById(R.id.mIvBack);
		mTvTitle = (TextView) findViewById(R.id.mTvTitle);
		metContent = (EditText) findViewById(R.id.metContent);
		mbtnUpdate = (Button) findViewById(R.id.mbtnUpdate);

		mIvBack.setVisibility(View.VISIBLE);
		mIvBack.setOnClickListener(this);
		mTvTitle.setText("添加退单原因");
		mbtnUpdate.setOnClickListener(this);
	}

	@Override
	public void initData() {
		orderModel = (OrderModel) this.getIntent().getSerializableExtra("msg");
	}


	private void insertUserMessageWords(boolean isShow) {
		
		AjaxParams params = new AjaxParams();
		params.put("action_flag", "addOrderBackMessage");
		params.put("orderbackmsgUserId", MemberUserUtils.getUid(this));
		params.put("orderbackmsgUserName", MemberUserUtils.getName(this));
		params.put("orderbackmsgOrderId", orderModel.getOrderId());
		params.put("orderbackmsgContent", metContent.getText().toString());
		httpPost(Consts.URL + Consts.APP.OrderAction, params, Consts.actionId.resultCode, isShow, "正在提交...");
	}

	@Override
	protected void callBackSuccess(ResponseEntry entry, int actionId) {
		super.callBackSuccess(entry, actionId);

		switch (actionId) {
		case Consts.actionId.resultCode:
			ToastUtil.show(OrderBackActivity.this, entry.getRepMsg());
			new Handler().postDelayed(new Runnable() {
				@Override
				public void run() {
					OrderBackActivity.this.finish();
				}
			}, 1000);
			break;
		case Consts.actionId.resultFlag:
			ToastUtil.show(OrderBackActivity.this, entry.getRepMsg());
			new Handler().postDelayed(new Runnable() {
				@Override
				public void run() {
					OrderBackActivity.this.finish();
				}
			}, 1000);
			break;
		}

	}

	@Override
	protected void callBackAllFailure(String strMsg, int actionId) {
		super.callBackAllFailure(strMsg, actionId);
		ToastUtil.show(OrderBackActivity.this, strMsg);

	}
}
