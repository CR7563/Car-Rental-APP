package com.lease;

import java.util.ArrayList;
import java.util.List;

import net.tsz.afinal.http.AjaxParams;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.pony.adapter.MyCollectListAdapter;
import com.pony.base.BaseActivity;
import com.pony.config.Consts;
import com.pony.db.MemberUserUtils;
import com.pony.model.CollectModel;
import com.pony.model.ResponseEntry;


/**
 * 类别
 * 
 * 
 *         2016-12-26
 */
public class MyCollectActivity extends BaseActivity  {

	// 标题
	private TextView mTvTitle;
	// 返回
	private ImageView mIvBack;
	private TextView mIvStu;
	private ListView mListMessage;
	private List<CollectModel> list_result = new ArrayList<CollectModel>();
	private String state;
	private LinearLayout mllNomessage;
	MyCollectListAdapter orderListAdapter;

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
		mTvTitle.setText("我的收藏");
		mIvBack.setVisibility(View.VISIBLE);
		mIvBack.setOnClickListener(this);
		mIvStu.setOnClickListener(this);
	}

	@Override
	public void initData() {
		MessageAction(true);
		mListMessage.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {

				Intent intent = new Intent(MyCollectActivity.this, CarMessageActivity.class);
				intent.putExtra("msg", list_result.get(position).getCarMsg());
				MyCollectActivity.this.startActivity(intent);
			}
		});
	}

	private void MessageAction(boolean isShow) {
		AjaxParams params = new AjaxParams();
		params.put("action_flag", "listCollectMessage");
		params.put("collectUserId", MemberUserUtils.getUid(this));
		httpPost(Consts.URL + Consts.APP.CarAction, params, Consts.actionId.resultFlag, isShow, "正在加载...");
	}


	@Override
	protected void callBackSuccess(ResponseEntry entry, int actionId) {
		super.callBackSuccess(entry, actionId);

		switch (actionId) {
		case Consts.actionId.resultFlag:
			if (null != entry.getData() && !TextUtils.isEmpty(entry.getData())) {

				String jsonMsg = entry.getData().substring(1, entry.getData().length() - 1);
				if (null != jsonMsg && !TextUtils.isEmpty(jsonMsg)) {
					list_result = mGson.fromJson(entry.getData(), new TypeToken<List<CollectModel>>() {
					}.getType());
					orderListAdapter = new MyCollectListAdapter(MyCollectActivity.this, list_result);
					mListMessage.setAdapter(orderListAdapter);
				} else {
					mllNomessage.setVisibility(View.VISIBLE);
				}
			}
			break;

		default:
			break;
		}

	}

}
