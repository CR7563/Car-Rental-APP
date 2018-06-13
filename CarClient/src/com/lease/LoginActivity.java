package com.lease;

import net.tsz.afinal.http.AjaxParams;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.pony.base.BaseActivity;
import com.pony.config.Consts;
import com.pony.db.MemberUserUtils;
import com.pony.model.ResponseEntry;
import com.pony.model.UserModel;
import com.pony.util.LoadingDialog;
import com.pony.util.ToastUtil;
import com.pony.util.Util;
import com.tencent.connect.UserInfo;
import com.tencent.connect.common.Constants;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

/**
 * ��¼ҳ��
 * 
 *
 * 
 */

public class LoginActivity extends BaseActivity {
	private static boolean isServerSideLogin = false;
	public static Tencent mTencent;
	// title
	private TextView mTvTitle;
	// ��¼�û�����
	private EditText mLoginNumber;
	// ��¼����
	private EditText mLoginPswd;
	// ��¼��ť
	private Button mLogin;
	private Button mEnterpriseQuery;
	private LinearLayout mllTop;
	private ImageView mivQQlogin;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		initWidget();
	}

	/**
	 * �ؼ���ʼ��
	 */
	@Override
	public void initWidget() {
		mTencent = Tencent.createInstance("1106057086", getApplicationContext());

		mivQQlogin = (ImageView) findViewById(R.id.mivQQlogin);
		mdialog = new LoadingDialog(this, "���ڵ�¼");
		mTvTitle = (TextView) findViewById(R.id.mTvTitle);
		mTvTitle.setText("��¼");
		mLoginNumber = (EditText) findViewById(R.id.mLoginNumber);
		mLoginPswd = (EditText) findViewById(R.id.mLoginPswd);
		mLogin = (Button) findViewById(R.id.mLogin);
		mEnterpriseQuery = (Button) findViewById(R.id.mEnterpriseQuery);
		// mLoginNumber.setInputType(EditorInfo.TYPE_CLASS_PHONE);
		// �¼��ļ���
		mLogin.setOnClickListener(this);
		mEnterpriseQuery.setOnClickListener(this);
		// �����������Ĭ�ϵĲ�������
		mLoginNumber.setSelection(mLoginNumber.getText().length());
		// mLoginNumber.setText("TEA20170123164556");
		mivQQlogin.setOnClickListener(this);
		mLoginNumber.setText("15249243002");
		mLoginPswd.setText("123456");
	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.mLogin:
			if (TextUtils.isEmpty(mLoginNumber.getText().toString())) {
				ToastUtil.ShowCentre(LoginActivity.this, "�������ֻ�����");
				return;
			}
			if (TextUtils.isEmpty(mLoginPswd.getText().toString())) {
				ToastUtil.ShowCentre(LoginActivity.this, "�������¼����");
				return;
			}

			// Intent intent = new Intent(LoginActivity.this,
			// FrameworkActivity.class);
			// startActivity(intent);
			// finish();
			//
			LoginUserPost(true);
			//
			break;

		case R.id.mivQQlogin:
			loginQQ();
			break;
		case R.id.mEnterpriseQuery:
			Intent mEnterpriseQuery = new Intent(LoginActivity.this, RegisterCreatActivity.class);
			startActivity(mEnterpriseQuery);
		default:
			break;
		}
	}

	@Override
	public void initData() {
	}

	/**
	 * �û��ĵ�¼
	 * 
	 * @param isShow
	 */
	private void LoginUserPost(boolean isShow) {
		AjaxParams params = new AjaxParams();
		params.put("action_flag", "login");
		params.put("uphone", mLoginNumber.getText().toString());
		params.put("pswd", mLoginPswd.getText().toString());
		httpPost(Consts.URL + Consts.APP.RegisterAction, params, Consts.actionId.resultFlag, isShow, "���ڵ�¼...");
	}

	@Override
	protected void callBackSuccess(ResponseEntry entry, int actionId) {
		super.callBackSuccess(entry, actionId);

		switch (actionId) {
		case Consts.actionId.resultFlag:

			if (null != entry.getData() && !TextUtils.isEmpty(entry.getData())) {

				UserModel userModel = mGson.fromJson(entry.getData(), UserModel.class);
				MemberUserUtils.setUid(LoginActivity.this, userModel.getUid());
				MemberUserUtils.setName(LoginActivity.this, userModel.getUname());
				MemberUserUtils.setLoginFlag(LoginActivity.this, "food");
				MemberUserUtils.putBean(LoginActivity.this, "user_messgae", userModel);

				Intent intent = new Intent(LoginActivity.this, FrameworkActivity.class);
				startActivity(intent);
				finish();

			}
			break;

		}

	}

	@Override
	protected void callBackAllFailure(String strMsg, int actionId) {
		super.callBackAllFailure(strMsg, actionId);

		ToastUtil.show(LoginActivity.this, strMsg);

	}

	public void loginQQ() {
		mdialog.show();
		/** �ж��Ƿ��½�� */
		if (!mTencent.isSessionValid()) {
			mTencent.login(this, "all", loginListener);
		}/** ��½��ע��֮���ڵ�¼ */
		else {
			// mTencent.logout(this);
			mTencent.login(this, "all", loginListener);
		}
	}

	IUiListener loginListener = new BaseUiListener() {
		@Override
		protected void doComplete(JSONObject values) {
			initOpenidAndToken(values);
			updateUserInfo();
			// updateLoginButton();
		}
	};

	public static void initOpenidAndToken(JSONObject jsonObject) {
		try {
			String token = jsonObject.getString(Constants.PARAM_ACCESS_TOKEN);
			String expires = jsonObject.getString(Constants.PARAM_EXPIRES_IN);
			String openId = jsonObject.getString(Constants.PARAM_OPEN_ID);
			if (!TextUtils.isEmpty(token) && !TextUtils.isEmpty(expires) && !TextUtils.isEmpty(openId)) {
				mTencent.setAccessToken(token, expires);
				mTencent.setOpenId(openId);
			}
		} catch (Exception e) {
		}
	}

	private void updateUserInfo() {
		if (mTencent != null && mTencent.isSessionValid()) {
			IUiListener listener = new IUiListener() {

				@Override
				public void onError(UiError e) {

				}

				@Override
				public void onComplete(final Object response) {
					Message msg = new Message();
					msg.obj = response;
					msg.what = 0;
					mHandler.sendMessage(msg);
					new Thread() {

						@Override
						public void run() {
							JSONObject json = (JSONObject) response;
							Log.i("pony_log", json.toString());
							if (json.has("figureurl")) {
								Bitmap bitmap = null;
								try {
									bitmap = Util.getbitmap(json.getString("figureurl_qq_1"));
								} catch (JSONException e) {

								}
								Message msg = new Message();
								msg.obj = bitmap;
								msg.what = 1;
								mHandler.sendMessage(msg);
							}
						}

					}.start();
				}

				@Override
				public void onCancel() {

				}
			};
			mInfo = new UserInfo(this, mTencent.getQQToken());
			mInfo.getUserInfo(listener);

		} else {
			// mUserInfo.setText("");
			// mUserInfo.setVisibility(android.view.View.GONE);
			// mUserLogo.setVisibility(android.view.View.GONE);
		}
	}

	private UserInfo mInfo;

	Handler mHandler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			if (msg.what == 0) {
				JSONObject response = (JSONObject) msg.obj;
				if (response.has("nickname")) {
					try {

						MemberUserUtils.setUid(LoginActivity.this, response.getString("nickname") + "");
						MemberUserUtils.setName(LoginActivity.this, response.getString("nickname"));
						MemberUserUtils.setLoginFlag(LoginActivity.this, "QQ");
						Intent intent = new Intent(LoginActivity.this, FrameworkActivity.class);
						startActivity(intent);
						finish();
						// mLogin.setText(response.getString("nickname"));
					} catch (JSONException e) {
						e.printStackTrace();
					}
				}
			} else if (msg.what == 1) {
				Bitmap bitmap = (Bitmap) msg.obj;
				// mUserLogo.setImageBitmap(bitmap);
				// mUserLogo.setVisibility(android.view.View.VISIBLE);
			}
		}

	};

	private class BaseUiListener implements IUiListener {

		@Override
		public void onComplete(Object response) {
			if (null == response) {
				ToastUtil.ShowCentre(LoginActivity.this, "��¼ʧ��");
				return;
			}
			JSONObject jsonResponse = (JSONObject) response;
			if (null != jsonResponse && jsonResponse.length() == 0) {
				ToastUtil.ShowCentre(LoginActivity.this, "��¼ʧ��");
				return;
			}
			mdialog.dismiss();
			// ToastUtil.ShowCentre(LoginActivity.this, "��¼�ɹ�");
			doComplete((JSONObject) response);
		}

		protected void doComplete(JSONObject values) {

		}

		@Override
		public void onError(UiError e) {
			ToastUtil.ShowCentre(LoginActivity.this, "��¼ʧ��");
		}

		@Override
		public void onCancel() {
			if (isServerSideLogin) {
				isServerSideLogin = false;
			}
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

		mdialog.dismiss();
		if (requestCode == Constants.REQUEST_LOGIN || requestCode == Constants.REQUEST_APPBAR) {
			Tencent.onActivityResultData(requestCode, resultCode, data, loginListener);
		} else {
			ToastUtil.ShowCentre(LoginActivity.this, "ȡ����¼");
		}

		super.onActivityResult(requestCode, resultCode, data);
	}

}
