package com.pony.config;

import android.os.Environment;

/**
 * 
 * @author wangxuan
 * 
 */
public class Consts {

	public static int TIME_OUT = 30000;
	public final static int SPLASH_DISPLAY_LENGHT = 3000; //

	public static String IMAGEPATH = Environment.getExternalStorageDirectory() + "/XCI/DTRM/IMAGES/";
	public static String IMAGEURL = "http://113.140.71.254:7010/";

	/**
	 * page
	 */
	public final static int PAGE = 1; //
	public final static String URL = "http://192.168.1.168:1010/CarService/";
	
	public static final String requestURL = "http://192.168.1.168:1010/CarService/servlet/FileImageUploadServlet";

	public final static String URL_IMAGE = "http://192.168.1.168:1010/CarService/upload/";
	
	public final static String URL_IMAGE_LOCAL = "http://192.168.1.168:1010/carmessage/upload/";
	public final static int PAGE_SIZE = 10;

	public final static int USERTYPE = 1;


	public static class APP {
		public static final String RegisterAction = "servlet/RegisterAction";
		public static final String CourseAction = "servlet/CourseAction";
		public static final String MessageAction = "servlet/MessageAction";
		public static final String JobAction = "servlet/JobAction";
		public static final String PersonAction = "servlet/PersonAction";
		public static final String PersonToJobAction = "servlet/PersonToJobAction";
		
		
		
		public static final String StudentAction = "servlet/StudentAction";
		public static final String TeacherAction = "servlet/TeacherAction";
		public static final String HouseAction = "servlet/HouseAction";
		public static final String OrderAction = "servlet/OrderAction";
		public static final String SignAction = "servlet/SignAction";
		public static final String LeaveAction = "servlet/LeaveAction";
		public static final String TypeAction = "servlet/TypeAction";
		public static final String ScoreAction = "servlet/ScoreAction";
		
		public static final String NoticeAction = "servlet/NoticeAction";
		public static final String DoctorAction = "servlet/DoctorAction";
		public static final String AppointmentAction = "servlet/AppointmentAction";
		public static final String ReviewAction = "servlet/ReviewAction";
		public static final String MeetingRoomAction = "servlet/MeetingRoomAction";
		public static final String AppointmentRoomAction = "servlet/AppointmentRoomAction";
		public static final String CarShopAction = "servlet/CarShopAction";
		public static final String FileImageUploadServlet = "servlet/FileImageUploadServlet";
		public static final String FileImageUploadUpdateServlet = "servlet/FileImageUploadUpdateServlet";
		public static final String UserServlet = "servlet/UserServlet";
		public static final String BookAction = "servlet/BookAction";
		public static final String FlowerAction = "servlet/FlowerAction";
		public static final String CarAction = "servlet/CarAction";
		
		
	}

	public static class actionId {
		public static final int resultFlag = 1;
		public static final int resultCode = 2;
		public static final int resultMsg = 3;
		public static final int resultState = 4;
		public static final int resultFive = 5;
		public static final int resultSix = 6;
		public static final int resultSeven = 7;
	}
}
