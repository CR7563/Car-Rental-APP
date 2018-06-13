package com.lease;

import java.util.List;

import net.tsz.afinal.http.AjaxParams;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.pony.adapter.ReplyAdapter;
import com.pony.base.BaseActivity;
import com.pony.config.Consts;
import com.pony.db.MemberUserUtils;
import com.pony.model.CarModel;
import com.pony.model.ResponseEntry;
import com.pony.model.ReviewModel;
import com.pony.photo.ui.ShowPictureActivity;
import com.pony.util.ToastUtil;
import com.pony.view.ListviewForScrollView;
import com.squareup.picasso.Picasso;

public class CarMessageActivity extends BaseActivity {

	// ����
	private TextView mTvTitle;
	// ����
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
	private ListviewForScrollView mListMessage;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_store_messages);
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
			Intent intent = new Intent(this, PayMessageActivity.class);
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
		mListMessage = (ListviewForScrollView) findViewById(R.id.mListMessage);
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

		mIvStu.setVisibility(View.VISIBLE);
		mIvBack.setVisibility(View.VISIBLE);
		mIvBack.setOnClickListener(this);
		mTvTitle.setText("��������");
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
			carMoney.setText(storeModel.getCarMoney() + "Ԫ/��");
			carTypeName.setText(storeModel.getCarTypeName());
			carMessage.setText(storeModel.getCarMessage());
			Picasso.with(this).load(Consts.URL_IMAGE_LOCAL + storeModel.getCarImage()).placeholder(R.drawable.default_drawable_show_pictrue)
					.into(infor_image);
			mtvShopPrice.setText(storeModel.getCarMoney() + "Ԫ");

			if (storeModel.isCollectState()) {
				mIvStu.setText("���ղ�");
			} else {
				mIvStu.setText("�ղ�");
			}

			if (storeModel.getStateMessage().equals("true")) {
				mbtnPay.setClickable(false);
				mbtnPay.setFocusable(false);
				mbtnPay.setBackgroundResource(R.drawable.btn_no_hui);
				mbtnPay.setText("������");
			}

			infor_image.setOnClickListener(this);
			ListNoticesMessage(false);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void collectNoMessage(boolean isShow, CarModel musicOnlineModel) {

		AjaxParams params = new AjaxParams();
		params.put("action_flag", "deleteCollectMessage");
		params.put("collectUserId", MemberUserUtils.getUid(this));
		params.put("collectMessageId", musicOnlineModel.getCarId());
		httpPost(Consts.URL + Consts.APP.CarAction, params, Consts.actionId.resultSix, isShow, "���ڼ���...");
	}

	private void collectOkMessage(boolean isShow, CarModel musicOnlineModel) {
		AjaxParams params = new AjaxParams();
		params.put("action_flag", "addCollectMessage");
		params.put("collectUserId", MemberUserUtils.getUid(this));
		params.put("collectMessageId", musicOnlineModel.getCarId());
		httpPost(Consts.URL + Consts.APP.CarAction, params, Consts.actionId.resultSeven, isShow, "���ڼ���...");
	}

	private void ListNoticesMessage(boolean isShow) {
		AjaxParams params = new AjaxParams();
		params.put("action_flag", "listNoticesMessage");
		params.put("rMessageId", storeModel.getCarId());
		httpPost(Consts.URL + Consts.APP.ReviewAction, params, Consts.actionId.resultFlag, isShow, "�����ύ...");
	}

	@Override
	protected void callBackSuccess(ResponseEntry entry, int actionId) {
		super.callBackSuccess(entry, actionId);

		switch (actionId) {

		case Consts.actionId.resultSeven:
			storeModel.setCollectState(true);
			Log.i("pony_log", entry.getRepMsg());
			mIvStu.setText("���ղ�");
			ToastUtil.show(this, " �ղسɹ�");
			break;

		case Consts.actionId.resultSix:
			storeModel.setCollectState(false);
			Log.i("pony_log", entry.getRepMsg());
			mIvStu.setText(" �ղ�");
			ToastUtil.show(this, "ȡ���ɹ�");
			break;

		case Consts.actionId.resultFlag:
			List<ReviewModel> list_result = mGson.fromJson(entry.getData(), new TypeToken<List<ReviewModel>>() {
			}.getType());
			ReplyAdapter replyAdapter = new ReplyAdapter(this, list_result);
			mListMessage.setAdapter(replyAdapter);
			break;
		}

	}

}
