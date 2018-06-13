package com.lease;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.pony.db.MemberUserUtils;
import com.pony.fragment.CarFragment;
import com.pony.fragment.MeFragment;

public class FrameworkActivity extends FragmentActivity implements OnClickListener {

	/**
	 * ����չʾ��Ϣ��Fragment
	 */
	private CarFragment messageFragment;

	/**
	 * ����չʾ��ϵ�˵�Fragment
	 */
	private MeFragment newsFragment;

	/**
	 * ��Ϣ���沼��
	 */
	private View messageLayout;

	/**
	 * ���ý��沼��
	 */
	private View settingLayout;
	//
	/**
	 * ���ý��沼��
	 */
	private View person_layout;

	/**
	 * ��Tab��������ʾ��Ϣͼ��Ŀؼ�
	 */
	private ImageView messageImage;

	/**
	 * ��Tab��������ʾ����ͼ��Ŀؼ�
	 */
	private ImageView settingImage;

	/**
	 * ��Tab��������ʾ����ͼ��Ŀؼ�
	 */
	private ImageView person_image;

	/**
	 * ��Tab��������ʾ��Ϣ����Ŀؼ�
	 */
	private TextView messageText;

	/**
	 * ��Tab��������ʾ���ñ���Ŀؼ�
	 */
	private TextView settingText;
	//
	/**
	 * ��Tab��������ʾ���ñ���Ŀؼ�
	 */
	private TextView person_text;

	private long mExitTime;
	private static final int INTERVAL = 2000;
	Intent intent;

	/**
	 * ���ڶ�Fragment���й���
	 */
	private FragmentManager fragmentManager;
	ImageView infoOperatingIV;
	private Button mivCreateMessage;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_framework);

		setWindowStatus();
		// ��ʼ������Ԫ��
		initViews();
		findViewById(R.id.iv_menu).setOnClickListener(clickListener);
		// getSupportFragmentManager().beginTransaction()
		fragmentManager = getSupportFragmentManager();
		// ��һ������ʱѡ�е�0��tab
		setTabSelection(0);
	}

	private OnClickListener clickListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.iv_menu:// ����߳���

				SharedPreferences read = getSharedPreferences("lock", MODE_WORLD_READABLE);
				// ����2����ȡ�ļ��е�ֵ
				String value = read.getString("user_id", "");
				if (TextUtils.isEmpty(value)) {
					Intent intent = new Intent(FrameworkActivity.this, LoginActivity.class);
					startActivity(intent);
				} else {
					// Intent intent = new Intent(MainActivity.this,
					// UserActivity.class);
					// startActivity(intent);
				}
				break;
			}
		}
	};

	/**
	 * �������ȡ��ÿ����Ҫ�õ��Ŀؼ���ʵ���������������úñ�Ҫ�ĵ���¼���
	 */
	private void initViews() {

		mivCreateMessage = (Button) findViewById(R.id.mivCreateMessage);
		mivCreateMessage.setVisibility(View.GONE);
		mivCreateMessage.setVisibility(View.GONE);
		messageLayout = findViewById(R.id.message_layout);
		settingLayout = findViewById(R.id.setting_layout);
		person_layout = findViewById(R.id.person_layout);

		messageImage = (ImageView) findViewById(R.id.message_image);
		settingImage = (ImageView) findViewById(R.id.setting_image);
		person_image = (ImageView) findViewById(R.id.person_image);

		messageText = (TextView) findViewById(R.id.message_text);
		settingText = (TextView) findViewById(R.id.setting_text);
		person_text = (TextView) findViewById(R.id.person_text);

		messageLayout.setOnClickListener(this);
		settingLayout.setOnClickListener(this);
		person_layout.setOnClickListener(this);
		mivCreateMessage.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.message_layout:
			// ���������Ϣtabʱ��ѡ�е�1��tab
			setTabSelection(0);
			break;
		case R.id.setting_layout:

			setTabSelection(1);
			// �����������tabʱ��ѡ�е�4��tab

			break;
		case R.id.person_layout:
			// �����������tabʱ��ѡ�е�4��tab
			if (MemberUserUtils.getUid(this).equals("")) {
				Intent intent = new Intent(this, LoginActivity.class);
				startActivity(intent);
			} else {
				setTabSelection(2);
			}
			break;
		case R.id.mivCreateMessage:
		
			break;

		default:
			break;
		}
	}

	/**
	 * ���ݴ����index����������ѡ�е�tabҳ��
	 * 
	 * @param index
	 *            ÿ��tabҳ��Ӧ���±ꡣ0��ʾ��Ϣ��1��ʾ��ϵ�ˣ�2��ʾ��̬��3��ʾ���á�
	 */
	private void setTabSelection(int index) {
		// ÿ��ѡ��֮ǰ��������ϴε�ѡ��״̬
		clearSelection();
		// ����һ��Fragment����
		FragmentTransaction transaction = fragmentManager.beginTransaction();
		// �����ص����е�Fragment���Է�ֹ�ж��Fragment��ʾ�ڽ����ϵ����
		hideFragments(transaction);
		switch (index) {
		case 0:
			// ���������Ϣtabʱ���ı�ؼ���ͼƬ��������ɫ
			messageImage.setImageResource(R.drawable.scenic_true);
			messageText.setTextColor(Color.parseColor("#72c557"));
			if (messageFragment == null) {
				// ���MessageFragmentΪ�գ��򴴽�һ������ӵ�������
				messageFragment = new CarFragment();
				transaction.add(R.id.content, messageFragment);
			} else {
				// ���MessageFragment��Ϊ�գ���ֱ�ӽ�����ʾ����
				transaction.show(messageFragment);
			}
			break;
		case 1:
			// ���������ϵ��tabʱ���ı�ؼ���ͼƬ��������ɫ
//			settingImage.setImageResource(R.drawable.travels_true);
//			settingText.setTextColor(Color.parseColor("#72c557"));
//			if (imFragment == null) {
//				// ���ContactsFragmentΪ�գ��򴴽�һ������ӵ�������
//				imFragment = new BookFragment();
//				transaction.add(R.id.content, imFragment);
//			} else {
//				// ���ContactsFragment��Ϊ�գ���ֱ�ӽ�����ʾ����
//				transaction.show(imFragment);
//			}
//			break;
		case 2:
			// ������˶�̬tabʱ���ı�ؼ���ͼƬ��������ɫ
			person_image.setImageResource(R.drawable.me_true);
			person_text.setTextColor(Color.parseColor("#72c557"));
			if (newsFragment == null) {
				// ���NewsFragmentΪ�գ��򴴽�һ������ӵ�������
				newsFragment = new MeFragment();
				transaction.add(R.id.content, newsFragment);
			} else {
				// ���NewsFragment��Ϊ�գ���ֱ�ӽ�����ʾ����
				transaction.show(newsFragment);
			}
			break;
		}
		transaction.commit();
	}

	/**
	 * ��������е�ѡ��״̬��
	 */
	private void clearSelection() {
		messageImage.setImageResource(R.drawable.scenic_false);
		messageText.setTextColor(Color.parseColor("#82858b"));
		settingImage.setImageResource(R.drawable.travels_false);
		settingText.setTextColor(Color.parseColor("#82858b"));
		person_image.setImageResource(R.drawable.me_false);
		person_text.setTextColor(Color.parseColor("#82858b"));

	}

	/**
	 * �����е�Fragment����Ϊ����״̬��
	 * 
	 * @param transaction
	 *            ���ڶ�Fragmentִ�в���������
	 */
	private void hideFragments(FragmentTransaction transaction) {
		if (messageFragment != null) {
			transaction.hide(messageFragment);
		}
//		if (imFragment != null) {
//			transaction.hide(imFragment);
//		}
		if (newsFragment != null) {
			transaction.hide(newsFragment);
		}

	}

	// ����״̬��
	private void setWindowStatus() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
			// ͸��״̬��
			getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
			// ͸��������
			getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
			// ����״̬����ɫ
			getWindow().setBackgroundDrawableResource(R.color.main_color);
		}
	}

	public void onBackPressed() {
		if (System.currentTimeMillis() - mExitTime > INTERVAL) {
			Toast.makeText(this, "�ٵ�һ���˳�����", Toast.LENGTH_SHORT).show();
			mExitTime = System.currentTimeMillis();
		} else {
			Intent intent = new Intent(Intent.ACTION_MAIN);
			intent.addCategory(Intent.CATEGORY_HOME);
			this.startActivity(intent);
			System.exit(0);
		}
	}

}