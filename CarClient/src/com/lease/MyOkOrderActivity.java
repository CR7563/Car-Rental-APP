package com.lease;

import java.util.ArrayList;
import java.util.List;

import net.tsz.afinal.http.AjaxParams;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.pony.adapter.OrderListAdapter;
import com.pony.base.BaseActivity;
import com.pony.config.Consts;
import com.pony.db.MemberUserUtils;
import com.pony.listener.DeletetOrderListner;
import com.pony.model.OrderModel;
import com.pony.model.ResponseEntry;
import com.pony.model.UserModel;
import com.pony.util.ToastUtil;

/**
 * 类别
 * 
 * 
 * 
 *         2016-12-26
 */
public class MyOkOrderActivity extends BaseActivity implements DeletetOrderListner {

	// 标题
	private TextView mTvTitle;
	// 返回
	private ImageView mIvBack;
	private TextView mIvStu;
	private ListView mListMessage;
	private List<OrderModel> list_result = new ArrayList<OrderModel>();
	private String state;
	private LinearLayout mllNomessage;
	OrderListAdapter orderListAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_im);

	}

	@Override
	protected void onResume() {
		super.onResume();
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
		mIvStu = (TextView) findViewById(R.id.mIvStu);
		mIvStu.setText("添加");
		mIvStu.setVisibility(View.GONE);
		mllNomessage = (LinearLayout) findViewById(R.id.mllNomessage);
		mListMessage = (ListView) findViewById(R.id.mListMessage);

		mIvBack = (ImageView) findViewById(R.id.mIvBack);
		mTvTitle = (TextView) findViewById(R.id.mTvTitle);
		state = this.getIntent().getStringExtra("state");
		mTvTitle.setText("我的租赁");
		mIvBack.setVisibility(View.VISIBLE);
		mIvBack.setOnClickListener(this);
		mIvStu.setOnClickListener(this);
	}

	@Override
	public void initData() {
		MessageAction(true);
		mListMessage.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int pos, long arg3) {

				Intent intent = new Intent(MyOkOrderActivity.this, CarOrderMessageActivity.class);
				intent.putExtra("msg", list_result.get(pos).getCarMessage());
				MyOkOrderActivity.this.startActivity(intent);
			}
		});
	}

	private void MessageAction(boolean isShow) {
		AjaxParams params = new AjaxParams();
		params.put("action_flag", "orderListMessagePhone");
		params.put("orderUserId", MemberUserUtils.getUid(this));
		httpPost(Consts.URL + Consts.APP.OrderAction, params, Consts.actionId.resultFlag, isShow, "正在加载...");
	}

	private void deleteOrderUserPhone(boolean isShow, OrderModel collectModel) {
		AjaxParams params = new AjaxParams();
		params.put("action_flag", "deleteOrderUserPhone");
		params.put("orderId", collectModel.getOrderId());
		httpPost(Consts.URL + Consts.APP.OrderAction, params, Consts.actionId.resultCode, isShow, "正在加载...");
	}
	int moneyTotal;
	private void RegisterAction(boolean isShow) {
		UserModel userModel = (UserModel) MemberUserUtils.getBean(this, "user_messgae");
		AjaxParams params = new AjaxParams();
		params.put("action_flag", "updateMoney");
		moneyTotal	 = Integer.valueOf(userModel.getuMoney()) + Integer.valueOf(collectModelUpdate.getOrderMoney());
		params.put("uMoney", moneyTotal + "");
		params.put("uid", MemberUserUtils.getUid(this));
		httpPost(Consts.URL + Consts.APP.RegisterAction, params, Consts.actionId.resultState, isShow, "正在加载...");
	}

	@Override
	protected void callBackSuccess(ResponseEntry entry, int actionId) {
		super.callBackSuccess(entry, actionId);

		switch (actionId) {
		case Consts.actionId.resultFlag:
			if (null != entry.getData() && !TextUtils.isEmpty(entry.getData())) {

				String jsonMsg = entry.getData().substring(1, entry.getData().length() - 1);
				if (null != jsonMsg && !TextUtils.isEmpty(jsonMsg)) {
					list_result = mGson.fromJson(entry.getData(), new TypeToken<List<OrderModel>>() {
					}.getType());
					orderListAdapter = new OrderListAdapter(MyOkOrderActivity.this, list_result, MyOkOrderActivity.this);
					mListMessage.setAdapter(orderListAdapter);
				} else {
					mListMessage.setVisibility(View.GONE);
					mllNomessage.setVisibility(View.VISIBLE);

				}
			}
			break;
		case Consts.actionId.resultCode:
			RegisterAction(true);
			break;

		case Consts.actionId.resultState:

			list_result.remove(posIndex);
			orderListAdapter.notifyDataSetChanged();
			UserModel userModel = (UserModel) MemberUserUtils.getBean(this, "user_messgae");
			userModel.setuMoney(moneyTotal+"");
			MemberUserUtils.putBean(this, "user_messgae", userModel);
			break;
		default:
			break;
		}

	}

	private int posIndex;
	private OrderModel collectModelUpdate;

	@Override
	public void setDelete(int postion, OrderModel collectModel) {
		posIndex = postion;
		collectModelUpdate = collectModel;
		deleteOrderUserPhone(false, collectModel);
	}

}
