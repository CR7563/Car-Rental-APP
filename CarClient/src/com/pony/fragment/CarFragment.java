package com.pony.fragment;

import java.util.ArrayList;
import java.util.List;

import net.tsz.afinal.http.AjaxParams;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.google.gson.reflect.TypeToken;
import com.lease.CarMessageActivity;
import com.lease.R;
import com.pony.adapter.ChoiceMessageAdapter;
import com.pony.adapter.LookListAdapter;
import com.pony.banner.CycleViewPager;
import com.pony.banner.CycleViewPager.ImageCycleViewListener;
import com.pony.banner.utils.ViewFactory;
import com.pony.base.BaseFragment;
import com.pony.config.Consts;
import com.pony.db.MemberUserUtils;
import com.pony.model.CarModel;
import com.pony.model.ResponseEntry;
import com.pony.model.SelectImageItem;
import com.pony.model.TypeModel;
import com.pony.util.ToastUtil;
import com.pony.view.ListviewForScrollView;

/**
 * 精选
 * 
 * @author ansen
 * @create time 2016-04-19
 */
public class CarFragment extends BaseFragment {
	// 获取view
	private View rootView;
	// 获取控件
	private ListView mListMessage;
	View convertView;
	private List<CarModel> list_result = new ArrayList<CarModel>();
	private List<CarModel> list_result_search = new ArrayList<CarModel>();
	private List<TypeModel> list_result_type = new ArrayList<TypeModel>();
	private CycleViewPager cycleViewPager;
	private List<ImageView> views = new ArrayList<ImageView>();
	private List<SelectImageItem> infos = new ArrayList<SelectImageItem>();
	private LinearLayout mllBuJu;
	private String[] imgArray = new String[] {
			"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1518077314142&di=d32fb46134c3b2b72b9e6ebe028ecee3&imgtype=0&src=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F01d2aa56f12c4b32f875a944402118.jpg%40900w_1l_2o_100sh.jpg",
			"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1518077371068&di=12c5e89d0952365f5110048122c12d2c&imgtype=0&src=http%3A%2F%2Fimages.hisupplier.com%2Fvar%2FuserImages%2F201501%2F26%2F102939549985_s.jpg"
			};
	
	
	private String[] moneyArray = new String[] {"不限制" ,"由低到高", "由高到低"};
	private String[] reviewyArray = new String[] {"不限制" ,"由低到高", "由高到低"};
	private ListView mListChoiceMessage;
	List<String> listChoice = new ArrayList<String>();
	
	private CheckBox mllType;
	private CheckBox mllMoney;
	private CheckBox mllReview;
	private int posIndexChoice = 0;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		rootView = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_selected, null);
		initWidget();
		initData();
		return rootView;
	}

	@Override
	public void initWidget() {
		mllType = (CheckBox) rootView.findViewById(R.id.mllType);
		mllMoney = (CheckBox) rootView.findViewById(R.id.mllMoney);
		mllReview = (CheckBox) rootView.findViewById(R.id.mllReview);
		
		
		mListChoiceMessage = (ListView) rootView.findViewById(R.id.mListChoiceMessage);
		convertView = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_banner, null);
		cycleViewPager = (CycleViewPager) getActivity().getFragmentManager().findFragmentById(R.id.fragment_soft_image);
		mListMessage = (ListView) rootView.findViewById(R.id.mListMessage);
		mListMessage.addHeaderView(convertView);
		mListChoiceMessage.setVisibility(View.GONE);
		
		mllType.setOnClickListener(this);
		mllMoney.setOnClickListener(this);
		mllReview.setOnClickListener(this);
		
	}

	@Override
	public void onClick(View v) {
	}

	
	private int choiceFlag = 0;
	@Override
	public void initData() {
		listChoice = new ArrayList<String>();

		mllReview.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
				listChoice.clear();
				choiceFlag = 2;
				for (int i = 0; i < reviewyArray.length; i++) {
					listChoice.add(reviewyArray[i]);
				}

				ChoiceMessageAdapter choiceMessageAdapter = new ChoiceMessageAdapter(getActivity(), listChoice);
				mListChoiceMessage.setAdapter(choiceMessageAdapter);
				
				if (arg1) {
					mListChoiceMessage.setVisibility(View.VISIBLE);
				} else {
					mListChoiceMessage.setVisibility(View.GONE);
				}
			}
		});
		
		mllType.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
				// TODO Auto-generated method stub
				listChoice.clear();
				choiceFlag =0;
				for (int i = 0; i < list_result_type.size(); i++) {
					listChoice.add(list_result_type.get(i).getTypeName());
				}

				ChoiceMessageAdapter choiceMessageAdapter = new ChoiceMessageAdapter(getActivity(), listChoice);
				mListChoiceMessage.setAdapter(choiceMessageAdapter);
				
				
				if (arg1) {
					mListChoiceMessage.setVisibility(View.VISIBLE);
				} else {
					mListChoiceMessage.setVisibility(View.GONE);
				}
			}
		});
		
		mllMoney.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
				listChoice.clear();
				choiceFlag = 1;
				for (int i = 0; i < moneyArray.length; i++) {
					listChoice.add(moneyArray[i]);
				}

				ChoiceMessageAdapter choiceMessageAdapter = new ChoiceMessageAdapter(getActivity(), listChoice);
				mListChoiceMessage.setAdapter(choiceMessageAdapter);
				
				if (arg1) {
					mListChoiceMessage.setVisibility(View.VISIBLE);
				} else {
					mListChoiceMessage.setVisibility(View.GONE);
				}
			}
		});
		
		mListChoiceMessage.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				// TODO Auto-generated method stub
				
				if(arg2==0){
					postAction(false);
//					mListMoneyMessage.setVisibility(View.GONE);
					
				}else{
					posIndexChoice = arg2;
//					mListMoneyMessage.setVisibility(View.GONE);
					if(choiceFlag==0){
						listSearchTypeMessage(true);
					}else if(choiceFlag==1){
						
						if(arg2==1){
							listPhoneMoneyQueryMessage(true,"1");	
						}else{
							listPhoneMoneyQueryMessage(true,"2");
						}
						
					
					}else{
						listNumberMessage(true,arg2);
					}
				
				}
				
				
				if(choiceFlag==0){
					mllType.setChecked(false);
				}else if(choiceFlag==1){
					mllMoney.setChecked(false);
				}else{
					mllReview.setChecked(false);
				}
//		
			}
		});
		

		infos.clear();
		views.clear();
		for (int i = 0; i < imgArray.length; i++) {
			SelectImageItem info = new SelectImageItem();
			info.setUrl(imgArray[i]);
			info.setSid(i);
			infos.add(info);
		}

		// 将最后一个ImageView添加进来
		views.add(ViewFactory.getImageView(getActivity(), infos.get(infos.size() - 1).getUrl()));
		for (int i = 0; i < infos.size(); i++) {
			views.add(ViewFactory.getImageView(getActivity(), infos.get(i).getUrl()));
		}
		// 将第一个ImageView添加进来
		views.add(ViewFactory.getImageView(getActivity(), infos.get(0).getUrl()));

		// 设置循环，在调用setData方法前调用
		cycleViewPager.setCycle(true);

		// 在加载数据前设置是否循环
		cycleViewPager.setData(views, infos, mAdCycleViewListener);
		// 设置轮播
		cycleViewPager.setWheel(true);

		// 设置轮播时间，默认5000ms
		cycleViewPager.setTime(2000);
		// 设置圆点指示图标组居中显示，默认靠右
		cycleViewPager.setIndicatorCenter();
		postAction(true);

		mListMessage.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {

//				if (list_result.get(position-1).getStateMessage().equals("true")) {
//					ToastUtil.ShowCentre(getActivity(), "此车辆已租赁哦！");
//
//				} else {
					Intent intent = new Intent(getActivity(), CarMessageActivity.class);
					intent.putExtra("msg", list_result.get(position-1));
					getActivity().startActivity(intent);
//				}

			}
		});
	}
	private ImageCycleViewListener mAdCycleViewListener = new ImageCycleViewListener() {

		@Override
		public void onImageClick(SelectImageItem info, int position, View imageView) {
			if (cycleViewPager.isCycle()) {
				position = position - 1;
			}
		}

	};
	private void postAction(boolean isShow) {
		AjaxParams params = new AjaxParams();
		params.put("action_flag", "listPhoneMessage");
		params.put("userId", MemberUserUtils.getUid(getActivity()));
		httpPost(Consts.URL + Consts.APP.CarAction, params, Consts.actionId.resultFlag, isShow, "正在加载...");
	}

	
	
	private void listTypePhoneMessage(boolean isShow) {
		AjaxParams params = new AjaxParams();
		params.put("action_flag", "listTypePhoneMessage");
		httpPost(Consts.URL + Consts.APP.CarAction, params, Consts.actionId.resultCode, isShow, "正在加载...");
	}

	private void listPhoneMoneyQueryMessage(boolean isShow,String msg) {
		AjaxParams params = new AjaxParams();
		params.put("action_flag", "listSearchMoneyMessage");
		params.put("searchMsg", msg);
		params.put("userId", MemberUserUtils.getUid(getActivity()));
		httpPost(Consts.URL + Consts.APP.CarAction, params, Consts.actionId.resultState, isShow, "正在加载...");
	}
	
	
	private void listSearchTypeMessage(boolean isShow) {
		AjaxParams params = new AjaxParams();
		params.put("action_flag", "listSearchTypeMessage");
		params.put("searchMsg", listChoice.get(posIndexChoice));
		params.put("userId", MemberUserUtils.getUid(getActivity()));
		httpPost(Consts.URL + Consts.APP.CarAction, params, Consts.actionId.resultState, isShow, "正在加载...");
	}
	
	
	private void listNumberMessage(boolean isShow,int pos) {
		AjaxParams params = new AjaxParams();
		params.put("action_flag", "listNumberMessage");
		params.put("searchMsg", pos+"");
		httpPost(Consts.URL + Consts.APP.CarAction, params, Consts.actionId.resultState, isShow, "正在加载...");
	}
	
	
	@Override
	protected void callBackSuccess(ResponseEntry entry, int actionId) {
		super.callBackSuccess(entry, actionId);

		switch (actionId) {
		case Consts.actionId.resultFlag:
			if (null != entry.getData() && !TextUtils.isEmpty(entry.getData())) {
				String jsonMsg = entry.getData().substring(1, entry.getData().length() - 1);
				if (null != jsonMsg && !TextUtils.isEmpty(jsonMsg)) {
					list_result = mGson.fromJson(entry.getData(), new TypeToken<List<CarModel>>() {
					}.getType());
					LookListAdapter lookListAdapter = new LookListAdapter(getActivity(), list_result);
					mListMessage.setAdapter(lookListAdapter);
					listTypePhoneMessage(true);
				}
			}
			break;
		case Consts.actionId.resultCode:
			if (null != entry.getData() && !TextUtils.isEmpty(entry.getData())) {
				String jsonMsg = entry.getData().substring(1, entry.getData().length() - 1);
				if (null != jsonMsg && !TextUtils.isEmpty(jsonMsg)) {
					list_result_type = mGson.fromJson(entry.getData(), new TypeToken<List<TypeModel>>() {
					}.getType());
					
					
				}
			}
			break;
			
		case Consts.actionId.resultState:
			if (null != entry.getData() && !TextUtils.isEmpty(entry.getData())) {
				String jsonMsg = entry.getData().substring(1, entry.getData().length() - 1);
				if (null != jsonMsg && !TextUtils.isEmpty(jsonMsg)) {
					list_result_search = mGson.fromJson(entry.getData(), new TypeToken<List<CarModel>>() {
					}.getType());
					LookListAdapter lookListAdapter = new LookListAdapter(getActivity(), list_result_search);
					mListMessage.setAdapter(lookListAdapter);
				}else{
					ToastUtil.ShowCentre(getActivity(), "搜索信息不存在");
				}
			}
			break;
		}

	}

	@Override
	protected void callBackAllFailure(String strMsg, int actionId) {
		super.callBackAllFailure(strMsg, actionId);
		ToastUtil.show(getActivity(), strMsg);

	}

}
