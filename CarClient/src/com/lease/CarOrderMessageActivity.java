package com.lease;

import net.tsz.afinal.http.AjaxParams;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.pony.base.BaseActivity;
import com.pony.config.Consts;
import com.pony.db.MemberUserUtils;
import com.pony.model.CarModel;
import com.pony.model.ResponseEntry;
import com.pony.photo.ui.ShowPictureActivity;
import com.pony.util.ToastUtil;
import com.squareup.picasso.Picasso;

public class CarOrderMessageActivity extends BaseActivity {

	// 标题
	private TextView mTvTitle;
	// 返回
	private ImageView mIvBack;

	private CarModel storeModel;
	private TextView mIvStu;
	private ImageView infor_image;
	private Button mbtnPay;

	private TextView carNo;
	private TextView carMoney;
	private TextView carMessage;
	private TextView carTypeName;
	private TextView carImage;

	private TextView mtvShopPrice;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_carorder_messages);
		initWidget();
		initData();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.mIvBack:
			finish();
			break;
		case R.id.mbtnPay:
			Intent intent = new Intent(this, ReviewCreatActivity.class);
			intent.putExtra("msg", storeModel);
			startActivity(intent);
			break;
		case R.id.infor_image:
			Intent infor_image = new Intent(this, ShowPictureActivity.class);
			infor_image.putExtra("piction_path", Consts.URL_IMAGE_LOCAL + storeModel.getCarImage());
			startActivity(infor_image);
			break;
		case R.id.mIvStu:
			if (storeModel.isCollectState()) {
				collectNoMessage(false, storeModel);
			} else {
				collectOkMessage(false, storeModel);
			}
			break;
		}
	}

	@Override
	public void initWidget() {

		mtvShopPrice = (TextView) findViewById(R.id.mtvShopPrice);
		carNo = (TextView) findViewById(R.id.carNo);
		carMoney = (TextView) findViewById(R.id.carMoney);
		carMessage = (TextView) findViewById(R.id.carMessage);
		carTypeName = (TextView) findViewById(R.id.carTypeName);

		mbtnPay = (Button) findViewById(R.id.mbtnPay);
		infor_image = (ImageView) findViewById(R.id.infor_image);
		mIvStu = (TextView) findViewById(R.id.mIvStu);
		mTvTitle = (TextView) findViewById(R.id.mTvTitle);
		mIvBack = (ImageView) findViewById(R.id.mIvBack);

		mIvStu.setVisibility(View.GONE);
		mIvBack.setVisibility(View.VISIBLE);
		mIvBack.setOnClickListener(this);
		mTvTitle.setText("车辆详情");
		mIvStu.setOnClickListener(this);
		mbtnPay.setOnClickListener(this);
	}

	@Override
	public void initData() {

		carNo = (TextView) findViewById(R.id.carNo);
		carMoney = (TextView) findViewById(R.id.carMoney);
		carMessage = (TextView) findViewById(R.id.carMessage);
		carTypeName = (TextView) findViewById(R.id.carTypeName);
		try {
			storeModel = (CarModel) this.getIntent().getSerializableExtra("msg");
			carNo.setText(storeModel.getCarNo());
			carMoney.setText(storeModel.getCarMoney()+ "元/天");
			carTypeName.setText(storeModel.getCarTypeName());
			carMessage.setText(storeModel.getCarMessage());
			Picasso.with(this).load(Consts.URL_IMAGE_LOCAL + storeModel.getCarImage()).placeholder(R.drawable.default_drawable_show_pictrue)
					.into(infor_image);
			mtvShopPrice.setText(storeModel.getCarMoney() + "元");

			if (storeModel.isCollectState()) {
				mIvStu.setText("已收藏");
			} else {
				mIvStu.setText("收藏");
			}

			infor_image.setOnClickListener(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void collectNoMessage(boolean isShow, CarModel musicOnlineModel) {

		AjaxParams params = new AjaxParams();
		params.put("action_flag", "deleteCollectMessage");
		params.put("collectUserId", MemberUserUtils.getUid(this));
		params.put("collectMessageId", musicOnlineModel.getCarId());
		httpPost(Consts.URL + Consts.APP.CarAction, params, Consts.actionId.resultSix, isShow, "正在加载...");
	}

	private void collectOkMessage(boolean isShow, CarModel musicOnlineModel) {
		AjaxParams params = new AjaxParams();
		params.put("action_flag", "addCollectMessage");
		params.put("collectUserId", MemberUserUtils.getUid(this));
		params.put("collectMessageId", musicOnlineModel.getCarId());
		httpPost(Consts.URL + Consts.APP.CarAction, params, Consts.actionId.resultSeven, isShow, "正在加载...");
	}

	@Override
	protected void callBackSuccess(ResponseEntry entry, int actionId) {
		super.callBackSuccess(entry, actionId);

		switch (actionId) {

		case Consts.actionId.resultSeven:
			storeModel.setCollectState(true);
			Log.i("pony_log", entry.getRepMsg());
			mIvStu.setText("已收藏");
			ToastUtil.show(this, " 收藏成功");
			break;

		case Consts.actionId.resultSix:
			storeModel.setCollectState(false);
			Log.i("pony_log", entry.getRepMsg());
			mIvStu.setText(" 收藏");
			ToastUtil.show(this, "取消成功");
			break;
		}

	}

}
