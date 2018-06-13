package com.lease;

import net.tsz.afinal.http.AjaxParams;
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
import com.pony.model.ResponseEntry;
import com.pony.model.UserModel;
import com.pony.util.ToastUtil;


/**
 * 用户注册
 * 
 *
 * 
 */
public class UpdateAddressActivity extends BaseActivity {

	// title
	private TextView mTvTitle;
	// 返回
	private ImageView mIvBack;
	// 查询按钮
	private Button mbtnUpdate;

	private EditText metName;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_update_address);
		initWidget();
	}

	@Override
	public void initWidget() {
		UserModel userModel = (UserModel) MemberUserUtils.getBean(this, "user_messgae");
		metName = (EditText) findViewById(R.id.metName);
		mbtnUpdate = (Button) findViewById(R.id.mbtnUpdate);
		mIvBack = (ImageView) findViewById(R.id.mIvBack);
		mTvTitle = (TextView) findViewById(R.id.mTvTitle);
		mTvTitle.setText("更改地址信息");
		metName.setHint(userModel.getUaddress());
		mIvBack.setVisibility(View.VISIBLE);
		mIvBack.setOnClickListener(this);
		mbtnUpdate.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
			case R.id.mIvBack:
				UpdateAddressActivity.this.finish();
				break;
			case R.id.mbtnUpdate:

				if (TextUtils.isEmpty(metName.getText().toString())) {
					ToastUtil.ShowCentre(UpdateAddressActivity.this, "请输入要更改的地址");
					return;
				}

				RegisterAction(true);
				break;
		}
	}

	@Override
	public void initData() {

	}

	private void RegisterAction(boolean isShow) {
		AjaxParams params = new AjaxParams();
		params.put("action_flag", "updateAddress");
		params.put("uaddress", metName.getText().toString());
		params.put("uid", MemberUserUtils.getUid(this));
		httpPost(Consts.URL + Consts.APP.RegisterAction, params, Consts.actionId.resultFlag, isShow, "正在更新...");
	}

	@Override
	protected void callBackSuccess(ResponseEntry entry, int actionId) {
		super.callBackSuccess(entry, actionId);
		ToastUtil.show(UpdateAddressActivity.this, entry.getRepMsg());

		UserModel userModel = (UserModel) MemberUserUtils.getBean(this, "user_messgae");
		userModel.setUaddress(metName.getText().toString());
		MemberUserUtils.putBean(this, "user_messgae", userModel);
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				UpdateAddressActivity.this.finish();
			}
		}, 1000);
	}

	@Override
	protected void callBackAllFailure(String strMsg, int actionId) {
		super.callBackAllFailure(strMsg, actionId);
		ToastUtil.show(UpdateAddressActivity.this, strMsg);

	}
}
