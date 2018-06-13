package com.pony.base;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.Toast;

import com.amap.api.navi.AMapNavi;
import com.amap.api.navi.AMapNaviListener;
import com.amap.api.navi.AMapNaviView;
import com.amap.api.navi.AMapNaviViewListener;
import com.amap.api.navi.model.AMapLaneInfo;
import com.amap.api.navi.model.AMapNaviCross;
import com.amap.api.navi.model.AMapNaviInfo;
import com.amap.api.navi.model.AMapNaviLocation;
import com.amap.api.navi.model.AMapNaviStaticInfo;
import com.amap.api.navi.model.AMapNaviTrafficFacilityInfo;
import com.amap.api.navi.model.AimLessModeCongestionInfo;
import com.amap.api.navi.model.AimLessModeStat;
import com.amap.api.navi.model.NaviInfo;
import com.amap.api.navi.model.NaviLatLng;
import com.autonavi.tbt.NaviStaticInfo;
import com.autonavi.tbt.TrafficFacilityInfo;
import com.pony.util.ErrorInfo;
import com.pony.util.TTSController;


public class BaseNaviActivity extends Activity implements AMapNaviListener, AMapNaviViewListener {

    protected AMapNaviView mAMapNaviView;
    protected AMapNavi mAMapNavi;
    protected TTSController mTtsManager;
    protected NaviLatLng mEndLatlng = new NaviLatLng(34.286182, 108.877927);
    protected NaviLatLng mStartLatlng = new NaviLatLng(34.226062, 108.931394);
    protected final List<NaviLatLng> sList = new ArrayList<NaviLatLng>();
    protected final List<NaviLatLng> eList = new ArrayList<NaviLatLng>();
    protected List<NaviLatLng> mWayPointList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //ʵ������������
        mTtsManager = TTSController.getInstance(getApplicationContext());
        mTtsManager.init();

        //
        mAMapNavi = AMapNavi.getInstance(getApplicationContext());
        mAMapNavi.addAMapNaviListener(this);
        mAMapNavi.addAMapNaviListener(mTtsManager);

        //����ģ�⵼�����г��ٶ�
        mAMapNavi.setEmulatorNaviSpeed(75);
        sList.add(mStartLatlng);
        eList.add(mEndLatlng);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mAMapNaviView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mAMapNaviView.onPause();

//        ������ֹͣ�㵱ǰ��˵����仰��һ�ᵽ�µ�·�ڻ��ǻ���˵��
        mTtsManager.stopSpeaking();
//
//        ֹͣ����֮�󣬻ᴥ���ײ�stop��Ȼ��Ͳ������лص��ˣ�����Ѷ�ɵ�ǰ����û��˵��İ�仰���ǻ�˵��
//        mAMapNavi.stopNavi();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mAMapNaviView.onDestroy();
        //since 1.6.0 ������naviview destroy��ʱ���Զ�ִ��AMapNavi.stopNavi();������ִ��
        mAMapNavi.stopNavi();
        mAMapNavi.destroy();
        mTtsManager.destroy();
    }

    @Override
    public void onInitNaviFailure() {
        Toast.makeText(this, "init navi Failed", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onInitNaviSuccess() {
        //��ʼ���ɹ�
    }

    @Override
    public void onStartNavi(int type) {
        //��ʼ�����ص�
    }

    @Override
    public void onTrafficStatusUpdate() {
        //
    }

    @Override
    public void onLocationChange(AMapNaviLocation location) {
        //��ǰλ�ûص�
    }

    @Override
    public void onGetNavigationText(int type, String text) {
        //�������ͺͲ������ֻص�
    }

    @Override
    public void onEndEmulatorNavi() {
        //����ģ�⵼��
    }

    @Override
    public void onArriveDestination() {
        //����Ŀ�ĵ�
    }

    @Override
    public void onArriveDestination(NaviStaticInfo naviStaticInfo) {
        //����Ŀ�ĵأ���ͳ����Ϣ�ص�
    }

    @Override
    public void onArriveDestination(AMapNaviStaticInfo aMapNaviStaticInfo) {

    }

    @Override
    public void onCalculateRouteSuccess() {
        //·�߼���ɹ�
    }

    @Override
    public void onCalculateRouteFailure(int errorInfo) {
        //·�߼���ʧ��
        Log.e("dm", "--------------------------------------------");
        Log.i("dm", "·�߼���ʧ�ܣ�������=" + errorInfo + ",Error Message= " + ErrorInfo.getError(errorInfo));
        Log.i("dm", "��������ϸ���Ӽ���http://lbs.amap.com/api/android-navi-sdk/guide/tools/errorcode/");
        Log.e("dm", "--------------------------------------------");
        Toast.makeText(this, "errorInfo��" + errorInfo + ",Message��" + ErrorInfo.getError(errorInfo), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onReCalculateRouteForYaw() {
        //ƫ�������¼���·�߻ص�
    }

    @Override
    public void onReCalculateRouteForTrafficJam() {
        //ӵ�º����¼���·�߻ص�
    }

    @Override
    public void onArrivedWayPoint(int wayID) {
        //����;����
    }

    @Override
    public void onGpsOpenStatus(boolean enabled) {
        //GPS����״̬�ص�
    }

    @Override
    public void onNaviSetting() {
        //�ײ��������õ���ص�
    }

    @Override
    public void onNaviMapMode(int isLock) {
        //��ͼ��ģʽ������������
    }

    @Override
    public void onNaviCancel() {
        finish();
    }


    @Override
    public void onNaviTurnClick() {
        //ת��view�ĵ���ص�
    }

    @Override
    public void onNextRoadClick() {
        //��һ����·View����ص�
    }


    @Override
    public void onScanViewButtonClick() {
        //ȫ����ť����ص�
    }

    @Deprecated
    @Override
    public void onNaviInfoUpdated(AMapNaviInfo naviInfo) {
        //��ʱ
    }

    @Override
    public void onNaviInfoUpdate(NaviInfo naviinfo) {
        //���������е���Ϣ���£��뿴NaviInfo�ľ���˵��
    }

    @Override
    public void OnUpdateTrafficFacility(TrafficFacilityInfo trafficFacilityInfo) {
        //�ѹ�ʱ
    }

    @Override
    public void OnUpdateTrafficFacility(AMapNaviTrafficFacilityInfo aMapNaviTrafficFacilityInfo) {
        //�ѹ�ʱ
    }

    @Override
    public void showCross(AMapNaviCross aMapNaviCross) {
        //��ʾת��ص�
    }

    @Override
    public void hideCross() {
        //����ת��ص�
    }

    @Override
    public void showLaneInfo(AMapLaneInfo[] laneInfos, byte[] laneBackgroundInfo, byte[] laneRecommendedInfo) {
        //��ʾ������Ϣ

    }

    @Override
    public void hideLaneInfo() {
        //���س�����Ϣ
    }

    @Override
    public void onCalculateMultipleRoutesSuccess(int[] ints) {
        //��·����·�ɹ��ص�
    }

    @Override
    public void notifyParallelRoad(int i) {
        if (i == 0) {
            Toast.makeText(this, "��ǰ������·����", Toast.LENGTH_SHORT).show();
            Log.d("wlx", "��ǰ������·����");
            return;
        }
        if (i == 1) {
            Toast.makeText(this, "��ǰ����·", Toast.LENGTH_SHORT).show();

            Log.d("wlx", "��ǰ����·");
            return;
        }
        if (i == 2) {
            Toast.makeText(this, "��ǰ�ڸ�·", Toast.LENGTH_SHORT).show();

            Log.d("wlx", "��ǰ�ڸ�·");
        }
    }

    @Override
    public void OnUpdateTrafficFacility(AMapNaviTrafficFacilityInfo[] aMapNaviTrafficFacilityInfos) {
        //���½�ͨ��ʩ��Ϣ
    }

    @Override
    public void updateAimlessModeStatistics(AimLessModeStat aimLessModeStat) {
        //����Ѳ��ģʽ��ͳ����Ϣ
    }


    @Override
    public void updateAimlessModeCongestionInfo(AimLessModeCongestionInfo aimLessModeCongestionInfo) {
        //����Ѳ��ģʽ��ӵ����Ϣ
    }


    @Override
    public void onLockMap(boolean isLock) {
        //����ͼ״̬�����仯ʱ�ص�
    }

    @Override
    public void onNaviViewLoaded() {
        Log.d("wlx", "����ҳ����سɹ�");
        Log.d("wlx", "�벻Ҫʹ��AMapNaviView.getMap().setOnMapLoadedListener();��overwrite����SDK�ڲ������߼�");
    }

    @Override
    public boolean onNaviBackClick() {
        return false;
    }


}
