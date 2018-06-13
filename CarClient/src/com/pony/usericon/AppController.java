package com.pony.usericon;

import android.app.Application;
import android.graphics.Bitmap;

public class AppController extends Application {
	public static Bitmap cropped = null;

	@Override
	public void onCreate() {
		super.onCreate();
		// load custom font
		// FontUtils.loadFont(getApplicationContext(), "Roboto-Light.ttf");
	}
}
