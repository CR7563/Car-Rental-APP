package com.pony.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lease.R;
import com.pony.config.Consts;
import com.pony.listener.DeletetOrderListner;
import com.pony.model.OrderModel;
import com.squareup.picasso.Picasso;

public class OrderListAdapter extends BaseAdapter {
	private LayoutInflater inflater;
	private List<OrderModel> list_result;
	private int posIndex;
	private Context mContext;
	private DeletetOrderListner mDeletetOrderListner;

	public OrderListAdapter(Context context, List<OrderModel> list_result,DeletetOrderListner deletetOrderListner) {
		mContext = context;
		inflater = LayoutInflater.from(context);
		this.list_result = list_result;
		this.mDeletetOrderListner = deletetOrderListner;
	}

	@Override
	public int getCount() {
		return list_result.size();
	}

	@Override
	public Object getItem(int position) {
		return list_result.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.myorder_item, null);
			holder = new ViewHolder();
			holder.mTvTitle = (TextView) convertView.findViewById(R.id.mTvTitle);
			holder.mTvMoney = (TextView) convertView.findViewById(R.id.mTvMoney);
			holder.mtvTime = (TextView) convertView.findViewById(R.id.mtvTime);
			holder.mivShop = (ImageView) convertView.findViewById(R.id.mivShop);
			holder.mivDelete = (ImageView) convertView.findViewById(R.id.mivDelete);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

//		if (list_result.get(position).getOrderState().equals("1")) {
//			holder.mTvTitle.setText(list_result.get(position).getOrderFlowerName() + "(付款)");
//		} else if (list_result.get(position).getOrderState().equals("2")) {
//			holder.mTvTitle.setText(list_result.get(position).getOrderFlowerName() + "(催单)");
//		} else if (list_result.get(position).getOrderState().equals("3")) {
//			holder.mTvTitle.setText(list_result.get(position).getOrderFlowerName() + "(退单申请已提交)");
//		}else if (list_result.get(position).getOrderState().equals("4")) {
//			
//		}

		holder.mTvTitle.setText("车牌号码："+list_result.get(position).getCarMessage().getCarNo());
		holder.mTvMoney.setText("订单金额：" + list_result.get(position).getOrderMoney()+"元");
		holder.mtvTime.setText("订单时间：" + list_result.get(position).getOrderCreateTime());

		
		holder.mivDelete.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				mDeletetOrderListner.setDelete(position, list_result.get(position));
			}
		});
		Picasso.with(mContext).load(Consts.URL_IMAGE_LOCAL + list_result.get(position).getCarMessage().getCarImage())
				.placeholder(R.drawable.default_drawable_show_pictrue).into(holder.mivShop);

		return convertView;

	}

	private class ViewHolder {
		private TextView mTvTitle;
		private TextView mTvMoney;
		private TextView mtvTime;
		private ImageView mivShop;
		
		private ImageView mivDelete;
		
	}

	public void setPos(int pos) {
		posIndex = pos;
	}

}
