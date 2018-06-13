package com.pony.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.net.Uri;

/**
 * ͼƬ������
 * 
 */
public class ImageUtil {

	/***
	 * ͼƬ�����ŷ���
	 * 
	 * @param bgimage
	 *            ��ԴͼƬ��Դ
	 * @param newWidth
	 *            �����ź���
	 * @param newHeight
	 *            �����ź�߶�
	 * @return
	 */
	public static Bitmap zoomImage(Bitmap bgimage, double newWidth, double newHeight) {
		// ��ȡ���ͼƬ�Ŀ�͸�
		float width = bgimage.getWidth();
		float height = bgimage.getHeight();
		// ��������ͼƬ�õ�matrix����
		Matrix matrix = new Matrix();
		// ������������
		float scaleWidth = ((float) newWidth) / width;
		float scaleHeight = ((float) newHeight) / height;
		// ����ͼƬ����
		matrix.postScale(scaleWidth, scaleHeight);
		Bitmap bitmap = Bitmap.createBitmap(bgimage, 0, 0, (int) width, (int) height, matrix, true);
		return bitmap;
	}

	/**
	 * dipתpix
	 * 
	 * @param dp
	 * @return
	 */
	public static int dp2px(Context context, float dp) {
		float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dp * scale + 0.5f);
	}

	public static Bitmap createWaterMaskRightTop(Context context, Bitmap src, Bitmap watermark, int paddingRight, int paddingTop) {
		return createWaterMaskBitmap(src, watermark, src.getWidth() - watermark.getWidth() - dp2px(context, paddingRight), dp2px(context, paddingTop));
	}

	private static Bitmap createWaterMaskBitmap(Bitmap src, Bitmap watermark, int paddingLeft, int paddingTop) {
		if (src == null) {
			return null;
		}
		int width = src.getWidth();
		int height = src.getHeight();
		// ����һ��bitmap
		Bitmap newb = Bitmap.createBitmap(width, height, Config.ARGB_8888);// ����һ���µĺ�SRC���ȿ��һ����λͼ
		// ����ͼƬ��Ϊ����
		Canvas canvas = new Canvas(newb);
		// �ڻ��� 0��0�����Ͽ�ʼ����ԭʼͼƬ
		canvas.drawBitmap(src, 0, 0, null);
		// �ڻ����ϻ���ˮӡͼƬ
		canvas.drawBitmap(watermark, paddingLeft, paddingTop, null);
		// ����
		canvas.save(Canvas.ALL_SAVE_FLAG);
		// �洢
		canvas.restore();
		return newb;
	}

	public static File shoot(Activity a, String name, Bitmap bitmap) {
		String sdcardPathDir = android.os.Environment.getExternalStorageDirectory().getPath() + "/PonyImage/";
		File file = new File(sdcardPathDir);
		if (!file.exists()) {
			file.mkdir();
		}
		try {
			savePic(a, bitmap, sdcardPathDir + name);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return file;
	}

	// ���浽sdcard
	private static void savePic(Activity a, Bitmap b, String strFileName) throws IOException {
		FileOutputStream fos = null;
		fos = new FileOutputStream(strFileName);
		if (null != fos) {
			b.compress(Bitmap.CompressFormat.PNG, 90, fos);
			fos.flush();
			fos.close();
			// ����ͼ��
			File file = new File(strFileName);
			updateImgPath(a, file, strFileName);
		}
	}

	public static void updateImgPath(Activity a, File file, String name) {
		Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
		Uri uri = Uri.fromFile(file);
		intent.setData(uri);
		a.sendBroadcast(intent);
	}


	public static Bitmap loadResBitmap(String path, int scalSize) {
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = false;
		options.inSampleSize = scalSize;
		Bitmap bmp = BitmapFactory.decodeFile(path, options);
		return bmp;
	}
}