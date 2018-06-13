package com.pony.app;

import java.util.Map;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.amap.api.location.AMapLocation;
import com.amap.api.navi.AMapNavi;
import com.lease.R;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.pony.db.DBHelper;
import com.pony.model.UserModel;


/**
 * 
 * @author �Զ���Application
 * 
 */
public class FinanceApplication extends Application {

	// ���ڴ�ŵ���ʱʱ��
	public static Map<String, Long> map;
	// ��ǰ�û���Sec�˻���Ϣʵ��
	public UserModel mUser = null;
	private static FinanceApplication INSTANCE = null;
	public static SQLiteDatabase db;
	public static boolean messageFlag = false;

	public static  AMapLocation aMapLocationMessage;
	
	@Override
	public void onCreate() {
		super.onCreate();

		configImageLoader();
		AMapNavi.setApiKey(this, "10191beabc59f5a7beba0005492cb819");

		INSTANCE = this;
		DBHelper dbHelper = new DBHelper(getApplicationContext(), DBHelper.DB_NAME);
		// ��ִ����getWritableDatabase��ʱ��Żᴴ�����ݿ�
		db = dbHelper.getWritableDatabase();
	}

	public static synchronized FinanceApplication getInstance() {
		return INSTANCE;
	}

	public void setUser(UserModel user) {
		this.mUser = user;
	}

	public UserModel getUser() {
		return this.mUser;
	}

	/**
	 * ����ImageLoder
	 */
	private void configImageLoader() {
		// ��ʼ��ImageLoader
		@SuppressWarnings("deprecation")
		DisplayImageOptions options = new DisplayImageOptions.Builder().showStubImage(R.drawable.default_drawable_show_pictrue) // ����ͼƬ�����ڼ���ʾ��ͼƬ
				.showImageForEmptyUri(R.drawable.default_drawable_show_pictrue) // ����ͼƬUriΪ�ջ��Ǵ����ʱ����ʾ��ͼƬ
				.showImageOnFail(R.drawable.default_drawable_show_pictrue) // ����ͼƬ���ػ��������з���������ʾ��ͼƬ
				.cacheInMemory(true) // �������ص�ͼƬ�Ƿ񻺴����ڴ���
				.cacheOnDisc(true) // �������ص�ͼƬ�Ƿ񻺴���SD����
				// .displayer(new RoundedBitmapDisplayer(20)) // ���ó�Բ��ͼƬ
				.build(); // �������ù���DisplayImageOption����

		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext()).defaultDisplayImageOptions(options)
				.threadPriority(Thread.NORM_PRIORITY - 2).denyCacheImageMultipleSizesInMemory()
				.discCacheFileNameGenerator(new Md5FileNameGenerator()).tasksProcessingOrder(QueueProcessingType.LIFO).build();
		ImageLoader.getInstance().init(config);
	}
}
