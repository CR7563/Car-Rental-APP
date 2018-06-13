package com.pony.photo;

import java.io.IOException;
import java.util.Date;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ExifInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lease.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.pony.util.ImageUtil;
import com.pony.view.DialogMsg;
import com.squareup.picasso.Picasso;


public class PictureSlideDeleteFragment extends BasicTrackFragment implements View.OnClickListener, View.OnLongClickListener {

	private View mContentView;
	private String mUrl;
	private PhotoView bannerImage;
	private int index;
	private int size;
	private DialogMsg dialogMsg;
	private Bitmap bitmap;
	private String datetime;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		mContentView = inflater.inflate(R.layout.fragment_picture_delete_slide_title, container, false);
		mUrl = getArguments().getString("url");
		index = getArguments().getInt("index");
		size = getArguments().getInt("size");
		getPhotoLocation(mUrl);
		return mContentView;
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		if (this.getActivity() != null) {
			init();
		}
	}

	ImageView goBackView;
	TextView titleView;
	TextView delete;

	public long getBitmapsize(Bitmap bitmapMsg) {
		// Pre HC-MR1
		return bitmapMsg.getRowBytes() * bitmapMsg.getHeight();

	}

	private void init() {
		dialogMsg = new DialogMsg(getActivity());
		dialogMsg.Set_Msg("�����ͼƬ�����������");
		bannerImage = (PhotoView) mContentView.findViewById(R.id.page_image);
		if (mUrl != null) {

//			bitmap = BitmapFactory.decodeFile(mUrl);

			// double width;
			// double height;

			// if (bitmap.getWidth() < 1000) {
			// bannerImage.setImageBitmap(bitmap);
			// } else {
//			bannerImage.setImageBitmap(bitmap);
			// }
			Picasso.with(getActivity()).load(mUrl).placeholder(R.drawable.default_drawable_show_pictrue)
			.into(bannerImage);
			//
			bannerImage.setScaleType(ImageView.ScaleType.FIT_CENTER);
		}

		// �˷���Ϊÿ���̶�ʱ��ᷢ��һ�ζ�λ����Ϊ�˼��ٵ������Ļ������������ģ�
		// ע�����ú��ʵĶ�λʱ��ļ������С���֧��Ϊ2000ms���������ں���ʱ�����stopLocation()������ȡ����λ����
		// �ڶ�λ�������ں��ʵ��������ڵ���onDestroy()����
		// �ڵ��ζ�λ����£���λ���۳ɹ���񣬶��������stopLocation()�����Ƴ����󣬶�λsdk�ڲ����Ƴ�
		// ������λ
		// ��ʼ����λ

		dialogMsg = new DialogMsg(getActivity());
		dialogMsg.Set_Msg("�����ͼƬ�����������");
		bannerImage = (PhotoView) mContentView.findViewById(R.id.page_image);

		bannerImage.setOnLongClickListener(new OnLongClickListener() {

			@Override
			public boolean onLongClick(View v) {
				// TODO Auto-generated method stub
				dialogMsg.Show();
				return false;
			}
		});

		dialogMsg.submit_no().setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				dialogMsg.Close();
			}
		});

		dialogMsg.submit_ok().setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				ImageUtil.shoot(getActivity(), new Date() + ".jpg", bitmap);
				dialogMsg.Close();
			}
		});
	}

	public static Bitmap loadResBitmap(String path, int scalSize) {
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = false;
		options.inSampleSize = scalSize;
		Bitmap bmp = BitmapFactory.decodeFile(path, options);
		return bmp;
	}

	public String getPhotoLocation(String imagePath) {
		Log.i("TAG", "getPhotoLocation==" + imagePath);
		float output1 = 0;
		float output2 = 0;

		try {
			ExifInterface exifInterface = new ExifInterface(imagePath);
			datetime = exifInterface.getAttribute(ExifInterface.TAG_DATETIME);// ����ʱ��
			String deviceName = exifInterface.getAttribute(ExifInterface.TAG_MAKE);// �豸Ʒ��
			String deviceModel = exifInterface.getAttribute(ExifInterface.TAG_MODEL); // �豸�ͺ�
			String latValue = exifInterface.getAttribute(ExifInterface.TAG_GPS_LATITUDE);
			String lngValue = exifInterface.getAttribute(ExifInterface.TAG_GPS_LONGITUDE);
			String latRef = exifInterface.getAttribute(ExifInterface.TAG_GPS_LATITUDE_REF);
			String lngRef = exifInterface.getAttribute(ExifInterface.TAG_GPS_LONGITUDE_REF);
			if (latValue != null && latRef != null && lngValue != null && lngRef != null) {
				output1 = convertRationalLatLonToFloat(latValue, latRef);
				output2 = convertRationalLatLonToFloat(lngValue, lngRef);
			}

			Log.i("pony_log", "�ֻ��ͺţ�" + deviceName + "," + deviceModel);
			Log.i("pony_log", "����ʱ�䣺" + datetime);
			Log.i("pony_log", "����λ�ã�" + output1 + ";" + output2);

			// setDiffColor(phoneTV, "�ֻ��ͺţ�" + deviceName + "," + deviceModel);
			// setDiffColor(latlngTV, "��γ�ȣ�" + output1 + ";" + output2);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return output1 + "-" + output2;
	}

	private static float convertRationalLatLonToFloat(String rationalString, String ref) {

		String[] parts = rationalString.split(",");

		String[] pair;
		pair = parts[0].split("/");
		double degrees = Double.parseDouble(pair[0].trim()) / Double.parseDouble(pair[1].trim());

		pair = parts[1].split("/");
		double minutes = Double.parseDouble(pair[0].trim()) / Double.parseDouble(pair[1].trim());

		pair = parts[2].split("/");
		double seconds = Double.parseDouble(pair[0].trim()) / Double.parseDouble(pair[1].trim());

		double result = degrees + (minutes / 60.0) + (seconds / 3600.0);
		if ((ref.equals("S") || ref.equals("W"))) {
			return (float) -result;
		}
		return (float) result;
	}

	private DisplayImageOptions mDetailOption;

	public DisplayImageOptions getOption() {
		if (mDetailOption == null) {
			mDetailOption = new DisplayImageOptions.Builder().showStubImage(R.drawable.default_drawable_show_pictrue)
			// ����ͼƬ�������ڼ���ʾ��ͼƬ
					.showImageOnLoading(R.drawable.default_drawable_show_pictrue).showImageForEmptyUri(R.drawable.default_drawable_show_pictrue)// ����ͼƬUriΪ�ջ��Ǵ����ʱ����ʾ��ͼƬ
					.showImageOnFail(R.drawable.default_drawable_show_pictrue)// ����ͼƬ����/��������д���ʱ����ʾ��ͼƬ
					// .cacheInMemory(true)// �Ƿ񾏴涼�ȴ���
					.cacheOnDisc(true)// �Ƿ񾏴浽sd����
					.build();
		}
		return mDetailOption;
	}

	@Override
	public void onClick(View v) {
	}

	@Override
	public boolean onLongClick(View v) {
		if (bannerImage != null) {
			// initDialog();
			dialogMsg.Show();
		}
		return false;
	}

}
