package com.pony.usericon;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Calendar;
import java.util.Locale;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.lease.R;
import com.pony.base.BaseActivity;

public class ChoiceImageActivity extends BaseActivity {

	private CropImageView mCropView;

	private static final String KEY_IMG_INDEX = "img_index";
	private boolean isStyes = true; // true ֧�����š� false ��֧������
	private TextView textTitle;

	// title
	private TextView mTvTitle;
	// ����
	private ImageView mIvBack;
	// ����
	private TextView mIvStu;
	
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_choice_image);

		
		mIvStu = (TextView) findViewById(R.id.mIvStu);
		mIvBack = (ImageView) findViewById(R.id.mIvBack);
		mTvTitle = (TextView) findViewById(R.id.mTvTitle);
		mTvTitle.setText("�ü���Ƭ");
		mIvStu.setText("ȷ��");
		mIvBack.setVisibility(View.VISIBLE);
		mIvStu.setVisibility(View.VISIBLE);
		mIvBack.setOnClickListener(this);
		mIvStu.setOnClickListener(this);
		
		
		textTitle = (TextView) findViewById(R.id.textTitle);
		textTitle.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (mCropView != null) {
					if (isStyes) {
						mCropView.setHandleShowMode(CropImageView.ShowMode.SHOW_ALWAYS); // ��ʾ��
						mCropView.setGuideShowMode(CropImageView.ShowMode.SHOW_ALWAYS); // ��ʾ�ߣ��ڲ����ߣ�
						mCropView.setmIsScaling(true); // �Զ���һ�����ԣ�֧������
						isStyes = false;
						textTitle.setText("����ҡ��ü��򲻿�����������");
					} else {
						mCropView.setHandleShowMode(CropImageView.ShowMode.NOT_SHOW); // ����ʾ��
						mCropView.setGuideShowMode(CropImageView.ShowMode.NOT_SHOW); // ����ʾ�ߣ��ڲ����ߣ�
						mCropView.setmIsScaling(false); // �Զ���һ�����ԣ���֧������
						isStyes = true;
						textTitle.setText("����ҡ��ü����������");
					}
					Toast.makeText(ChoiceImageActivity.this, "OK", Toast.LENGTH_LONG).show();

				}
			}
		});

		findViews();
		sourceUri = Uri.fromFile(new File(this.getIntent().getStringExtra("imagePath")));
		mCropView.setImageBitmap(getBitmapFromUri(sourceUri));
	}

	Uri sourceUri;

	private Bitmap getBitmapFromUri(Uri uri) {
		try {
			// ��ȡuri���ڵ�ͼƬ
			Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
			return bitmap;
		} catch (Exception e) {
			Log.e("[Android]", e.getMessage());
			Log.e("[Android]", "Ŀ¼Ϊ��" + uri);
			e.printStackTrace();
			return null;
		}
	}

	private final View.OnClickListener btnListener = new View.OnClickListener() {
		@Override
		public void onClick(View v) {

			switch (v.getId()) {
//				AppController.cropped = mCropView.getCroppedBitmap();
////				Intent intent = new Intent(ChoiceImageActivity.this, ResultActivity.class);
////				startActivity(intent);
//				
//				Uri uri = Uri.parse(MediaStore.Images.Media.insertImage(getContentResolver(), mCropView.getCroppedBitmap(), null,null));
//				saveImage(uri);
//				Intent mIntent = getIntent();
//				mIntent.putExtra("action", 1); 
//				setResult(1, mIntent);
//				finish();
				
			case R.id.buttonFitImage:
				mCropView.setCropMode(CropImageView.CropMode.RATIO_FIT_IMAGE);
				break;
			case R.id.button1_1:
				mCropView.setCropMode(CropImageView.CropMode.RATIO_1_1);
				break;
			case R.id.button3_4:
				mCropView.setCropMode(CropImageView.CropMode.RATIO_3_4);
				break;
			case R.id.button4_3:
				mCropView.setCropMode(CropImageView.CropMode.RATIO_4_3);
				break;
			case R.id.button9_16:
				mCropView.setCropMode(CropImageView.CropMode.RATIO_9_16);
				break;
			case R.id.button16_9:
				mCropView.setCropMode(CropImageView.CropMode.RATIO_16_9);
				break;
			case R.id.buttonCustom:
				mCropView.setCustomRatio(7, 5);
				break;
			case R.id.buttonFree:
				mCropView.setCropMode(CropImageView.CropMode.RATIO_FREE);
				break;
			case R.id.buttonCircle:
				mCropView.setCropMode(CropImageView.CropMode.CIRCLE);
				break;
			case R.id.buttonChangeImage:
				mCropView.setImageURI(sourceUri);
				break;
			case R.id.buttonRotateImage:
				mCropView.rotateImage(CropImageView.RotateDegrees.ROTATE_90D);
				break;
			}
		}
	};

	private void findViews() {
		mCropView = (CropImageView) findViewById(R.id.cropImageView);
		mCropView.setMinFrameSizeInDp(100);
		mCropView.setCropMode(CropImageView.CropMode.RATIO_16_9);

		findViewById(R.id.buttonDone).setOnClickListener(btnListener);
		findViewById(R.id.buttonFitImage).setOnClickListener(btnListener);
		findViewById(R.id.button1_1).setOnClickListener(btnListener);
		findViewById(R.id.button3_4).setOnClickListener(btnListener);
		findViewById(R.id.button4_3).setOnClickListener(btnListener);
		findViewById(R.id.button9_16).setOnClickListener(btnListener);
		findViewById(R.id.button16_9).setOnClickListener(btnListener);
		findViewById(R.id.buttonFree).setOnClickListener(btnListener);
		findViewById(R.id.buttonChangeImage).setOnClickListener(btnListener);
		findViewById(R.id.buttonRotateImage).setOnClickListener(btnListener);
		findViewById(R.id.buttonCustom).setOnClickListener(btnListener);
		findViewById(R.id.buttonCircle).setOnClickListener(btnListener);
	}
	private Intent mIntent;
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		// ����ҳ��
		case R.id.mIvBack:
			Intent mIvBack = getIntent();
			mIntent.putExtra("imagePath", ""); 
			setResult(2, mIvBack);
			finish();
			break;
		case R.id.mIvStu:
			AppController.cropped = mCropView.getCroppedBitmap();
			Uri uri = Uri.parse(MediaStore.Images.Media.insertImage(getContentResolver(), mCropView.getCroppedBitmap(), null,null));
			String pathString = saveImage(uri);
			
			Intent mIntent = getIntent();
			mIntent.putExtra("imagePath",pathString); 
			setResult(2, mIntent);
			finish();
			break;

		}
	}

	@Override
	public void initWidget() {
		
	}

	@Override
	public void initData() {
		
	}

	private String saveImage(Uri uri) {
		Bitmap bitmap = getBitmapFromUri(uri);
		String name = new DateFormat().format("yyyyMMddhhmmss", Calendar.getInstance(Locale.CHINA)) + ".jpg";
		String saveDir = Environment.getExternalStorageDirectory() + "/Pictures";
		File dir = new File(saveDir);
		if (!dir.exists()) {
			dir.mkdir();
		}
		// ������sdCard
		File file = new File(saveDir, name);
		try {
			FileOutputStream out = new FileOutputStream(file);
			if (bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out)) {
				out.flush();
				out.close();
				bitmap.recycle();
			}
		} catch (Exception e) {
		}


		return file.getPath();
	}
}
