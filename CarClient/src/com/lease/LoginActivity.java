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
 * 登录页面
 * 
 *
 * 
 */

public class LoginActivity extends BaseActivity {
	private static boolean isServerSideLogin = false;
	public static Tencent mTencent;
	// title
	private TextView mTvTitle;
	// 登录用户名称
	private EditText mLoginNumber;
	// 登录密码
	private EditText mLoginPswd;
	// 登录按钮
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
	 * 控件初始化
	 */
	@Override
	public void initWidget() {
		mTencent = Tencent.createInstance("1106057086", getApplicationContext());

		mivQQlogin = (ImageView) findViewById(R.id.mivQQlogin);
		mdialog = new LoadingDialog(this, "正在登录");
		mTvTitle = (TextView) findViewById(R.id.mTvTitle);
		mTvTitle.setText("登录");
		mLoginNumber = (EditText) findViewById(R.id.mLoginNumber);
		mLoginPswd = (EditText) findViewById(R.id.mLoginPswd);
		mLogin = (Button) findViewById(R.id.mLogin);
		mEnterpriseQuery = (Button) findViewById(R.id.mEnterpriseQuery);
		// mLoginNumber.setInputType(EditorInfo.TYPE_CLASS_PHONE);
		// 事件的监听
		mLogin.setOnClickListener(this);
		mEnterpriseQuery.setOnClickListener(this);
		// 给输入框设置默认的测试数据
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
				ToastUtil.ShowCentre(LoginActivity.this, "请输入手机号码");
				return;
			}
			if (TextUtils.isEmpty(mLoginPswd.getText().toString())) {
				ToastUtil.ShowCentre(LoginActivity.this, "请输入登录密码");
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
	 * 用户的登录
	 * 
	 * @param isShow
	 */
	private void LoginUserPost(boolean isShow) {
		AjaxParams params = new AjaxParams();
		params.put("action_flag", "login");
		params.put("uphone", mLoginNumber.getText().toString());
		params.put("pswd", mLoginPswd.getText().toString());
		httpPost(Consts.URL + Consts.APP.RegisterAction, params, Consts.actionId.resultFlag, isShow, "正在登录...");
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
		/** 判断是否登陆过 */
		if (!mTencent.isSessionValid()) {
			mTencent.login(this, "all", loginListener);
		}/** 登陆过注销之后在登录 */
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
				ToastUtil.ShowCentre(LoginActivity.this, "登录失败");
				return;
			}
			JSONObject jsonResponse = (JSONObject) response;
			if (null != jsonResponse && jsonResponse.length() == 0) {
				ToastUtil.ShowCentre(LoginActivity.this, "登录失败");
				return;
			}
			mdialog.dismiss();
			// ToastUtil.ShowCentre(LoginActivity.this, "登录成功");
			doComplete((JSONObject) response);
		}

		protected void doComplete(JSONObject values) {

		}

		@Override
		public void onError(UiError e) {
			ToastUtil.ShowCentre(LoginActivity.this, "登录失败");
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
			ToastUtil.ShowCentre(LoginActivity.this, "取消登录");
		}

		super.onActivityResult(requestCode, resultCode, data);
	}

}
