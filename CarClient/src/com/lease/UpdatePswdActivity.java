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
import com.pony.model.ResponseEntry;
import com.pony.util.ToastUtil;


/**
 */
public class UpdatePswdActivity extends BaseActivity {

	// title
	private TextView mTvTitle;
	// ����
	private ImageView mIvBack;
	// ��ѯ��ť
	private Button mbtnUpdate;
	private EditText metOldPswd;
	private EditText metNewPswd;
	private EditText mettwoPswd;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_update_pswd);
		initWidget();
	}

	@Override
	public void initWidget() {
		metOldPswd = (EditText) findViewById(R.id.metOldPswd);
		metNewPswd = (EditText) findViewById(R.id.metNewPswd);
		mettwoPswd = (EditText) findViewById(R.id.mettwoPswd);
		mbtnUpdate = (Button) findViewById(R.id.mbtnUpdate);
		mIvBack = (ImageView) findViewById(R.id.mIvBack);
		mTvTitle = (TextView) findViewById(R.id.mTvTitle);
		mTvTitle.setText("�޸�����");
		mIvBack.setVisibility(View.VISIBLE);
		mIvBack.setOnClickListener(this);
		mbtnUpdate.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
			case R.id.mIvBack:
				UpdatePswdActivity.this.finish();
				break;
			case R.id.mbtnUpdate:

				if (TextUtils.isEmpty(metOldPswd.getText().toString())) {
					ToastUtil.ShowCentre(UpdatePswdActivity.this, "������ԭ����");
					return;
				}

				
				if (TextUtils.isEmpty(metNewPswd.getText().toString())) {
					ToastUtil.ShowCentre(UpdatePswdActivity.this, "������������");
					return;
				}
				
				if (TextUtils.isEmpty(mettwoPswd.getText().toString())) {
					ToastUtil.ShowCentre(UpdatePswdActivity.this, "������ȷ��������");
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
		params.put("action_flag", "updatePswd");
		params.put("pswd", metNewPswd.getText().toString());
		params.put("userId", MemberUserUtils.getUid(this));
		httpPost(Consts.URL + Consts.APP.RegisterAction, params, Consts.actionId.resultFlag, isShow, "���ڸ���...");
	}

	@Override
	protected void callBackSuccess(ResponseEntry entry, int actionId) {
		super.callBackSuccess(entry, actionId);
		ToastUtil.show(UpdatePswdActivity.this, entry.getRepMsg());

		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				Intent intent = new Intent(UpdatePswdActivity.this, LoginActivity.class);
				startActivity(intent);
				finish();
			}
		}, 1000);
	}

	@Override
	protected void callBackAllFailure(String strMsg, int actionId) {
		super.callBackAllFailure(strMsg, actionId);
		ToastUtil.show(UpdatePswdActivity.this, strMsg);

	}
}
