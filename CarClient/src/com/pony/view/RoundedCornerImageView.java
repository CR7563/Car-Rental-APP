package com.pony.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * 
 * @author Chenss
 * @Description:图片圆角
 * @time:2015�??5�??21
 */
public class RoundedCornerImageView extends ImageView {
	/**
	 * 获取屏幕密度
	 */
	private final float density = getContext().getResources().getDisplayMetrics().density;
	/**
	 * �??
	 */
	private float roundness;

	public RoundedCornerImageView(Context context) {
		super(context);

		init();
	}

	public RoundedCornerImageView(Context context, AttributeSet attrs) {
		super(context, attrs);

		init();
	}

	public RoundedCornerImageView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);

		init();
	}

	@Override
	public void draw(Canvas canvas) {
		final Bitmap composedBitmap;
		final Bitmap originalBitmap;
		final Canvas composedCanvas;
		final Canvas originalCanvas;
		final Paint paint;
		final int height;
		final int width;

		width = getWidth();

		height = getHeight();

		composedBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
		originalBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);

		composedCanvas = new Canvas(composedBitmap);
		originalCanvas = new Canvas(originalBitmap);

		paint = new Paint();
		paint.setAntiAlias(true);
		paint.setColor(Color.BLACK);

		super.draw(originalCanvas);

		composedCanvas.drawARGB(0, 0, 0, 0);

		composedCanvas.drawRoundRect(new RectF(0, 0, width, height), this.roundness, this.roundness, paint);

		paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));

		composedCanvas.drawBitmap(originalBitmap, 0, 0, paint);

		canvas.drawBitmap(composedBitmap, 0, 0, new Paint());
	}

	public float getRoundness() {
		return this.roundness / this.density;
	}

	public void setRoundness(float roundness) {
		this.roundness = roundness * this.density;
	}

	private void init() {
		// 括号中的数字是调整图片弧度的 调成100为圆形图�?? 调成15为圆角图�??
		setRoundness(100);
	}
}
