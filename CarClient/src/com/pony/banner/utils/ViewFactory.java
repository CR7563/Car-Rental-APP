package com.pony.banner.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ImageView;

import com.lease.R;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.pony.config.Consts;
import com.squareup.picasso.Picasso;

/**
 * ImageView创建工厂
 */
public class ViewFactory {

	/**
	 * 获取ImageView视图的同时加载显示url
	 * 
	 * @param text
	 * @return
	 */
	public static ImageView getImageView(Context context, String url) {
		ImageView imageView = (ImageView) LayoutInflater.from(context).inflate(R.layout.view_banner, null);


		Picasso.with(context).load(url).placeholder(R.drawable.default_drawable_show_pictrue).into(imageView);
		return imageView;
	}
}
