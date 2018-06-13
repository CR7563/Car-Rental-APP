package com.pony.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lease.LoginActivity;
import com.lease.MyCollectActivity;
import com.lease.MyOkOrderActivity;
import com.lease.R;
import com.lease.SoftMessageActivity;
import com.lease.UserActivity;
import com.pony.base.BaseFragment;


/**
 * 
 * @author WangXuan
 * 
 */
public class MeFragment extends BaseFragment {

	// 获取view
	private View rootView;
	private RelativeLayout mtvUser;
	private RelativeLayout mrlMyCollect;
	private RelativeLayout mrlReview;
	private RelativeLayout mtvSoft;
	private TextView mtvMaxMoney;
	
	/**
	 * 
	 * 1900+1500+1500
	 * 
	 * 
	 */

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		rootView = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_content, null);

		return rootView;
	}

	/**
	 * 获取控件
	 */
	@Override
	public void initWidget() {
		
		
		mtvSoft = (RelativeLayout) rootView.findViewById(R.id.mtvSoft);
		mrlMyCollect = (RelativeLayout) rootView.findViewById(R.id.mrlMyCollect);
		mrlReview = (RelativeLayout) rootView.findViewById(R.id.mrlReview);
		mtvUser = (RelativeLayout) rootView.findViewById(R.id.mtvUser);
		mrlMyCollect.setOnClickListener(this);
		mtvUser.setOnClickListener(this);
		mtvSoft.setOnClickListener(this);
		mrlReview.setOnClickListener(this);
		
		
	}

	/**
	 * 处理点击事件
	 */
	@Override
	public void onClick(View v) {
		
		
		switch (v.getId()) {
		case R.id.mrlMyCollect:
			Intent mrlMyCollect = new Intent(getActivity(), MyOkOrderActivity.class);
			getActivity().startActivity(mrlMyCollect);
			break;
		case R.id.mrlReview:
			Intent mrlReview = new Intent(getActivity(), MyCollectActivity.class);
			getActivity().startActivity(mrlReview);
			break;
			
			

		case R.id.mtvSoft:
			Intent mtvSoft = new Intent(getActivity(), SoftMessageActivity.class);
			getActivity().startActivity(mtvSoft);
			break;

		case R.id.mtvUser:
			Intent mtvUser = new Intent(getActivity(), UserActivity.class);
			getActivity().startActivity(mtvUser);
			break;

		case R.id.mExit:
			Intent intent = new Intent(getActivity(), LoginActivity.class);
			startActivity(intent);
			getActivity().finish();
			break;
		default:
			break;
		}

	}

	/**
	 * 处理数据
	 */
	@Override
	public void initData() {

	}

	@Override
	public void onResume() {
		super.onResume();
		initWidget();
		initData();
	}
}
