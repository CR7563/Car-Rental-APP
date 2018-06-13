package com.student.data.action;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.student.data.dao.CarDao;

public class CarAction extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private ServletFileUpload upload;
	private final long MAXSize = 4194304 * 2L;// 4*2MB
	private String filedir = null;

	private CarDao carDao;

	public CarAction() {
		super();
	}

	public void destroy() {
		super.destroy();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String path = request.getContextPath();
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();

		String action_flag = request.getParameter("action_flag");
		if (action_flag.equals("addMessage")) {
			addMessage(request, response);
		} else if (action_flag.equals("listMessage")) {
			listMessage(request, response);
		} else if (action_flag.equals("listPhoneMessage")) {
			listPhoneMessage(request, response);
		} else if (action_flag.equals("updateReview")) {
			updateReview(request, response);
		} else if (action_flag.equals("deleteMessage")) {
			deleteMessage(request, response);
		} else if (action_flag.equals("listMessageChoice")) {
			listMessageChoice(request, response);
		} else if (action_flag.equals("listSearchMessage")) {
			listSearchMessage(request, response);
		} else if (action_flag.equals("queryMessage")) {
			queryMessage(request, response);
		} else if (action_flag.equals("addType")) {
			addType(request, response);
		} else if (action_flag.equals("listTypeMessage")) {
			listTypeMessage(request, response);
		} else if (action_flag.equals("deleteType")) {
			deleteType(request, response);
		} else if (action_flag.equals("listMessageChoice")) {
			listMessageChoice(request, response);
		} else if (action_flag.equals("UpdateMessage")) {
			UpdateMessage(request, response);
		} else if (action_flag.equals("addCollectMessage")) {
			addCollectMessage(request, response);
		} else if (action_flag.equals("deleteCollectMessage")) {
			deleteCollectMessage(request, response);
		} else if (action_flag.equals("listTypePhoneMessage")) {
			listTypePhoneMessage(request, response);
		} else if (action_flag.equals("listSearchMoneyMessage")) {
			listSearchMoneyMessage(request, response);
		} else if (action_flag.equals("listSearchTypeMessage")) {
			listSearchTypeMessage(request, response);
		} else if (action_flag.equals("listSearchScoreMessage")) {
			listSearchScoreMessage(request, response);
		} else if (action_flag.equals("addScore")) {
			addScore(request, response);
		} else if (action_flag.equals("updateUserImage")) {
			updateUserImage(request, response);
		} else if (action_flag.equals("listCollectMessage")) {
			listCollectMessage(request, response);
		} else if (action_flag.equals("listScoreMessage")) {
			listScoreMessage(request, response);
		} else if (action_flag.equals("listCollectMessage")) {
			listCollectMessage(request, response);
		} else if (action_flag.equals("listNumberMessage")) {
			listNumberMessage(request, response);
		}
	}

	public void init(ServletConfig config) throws ServletException {
		FileItemFactory factory = new DiskFileItemFactory();// Create a factory
		this.upload = new ServletFileUpload(factory);// Create a new file upload
		this.upload.setSizeMax(this.MAXSize);// Set overall request size
		filedir = config.getServletContext().getRealPath("upload");
		System.out.println("filedir=" + filedir);
		carDao = new CarDao();
	}
	

	private void listNumberMessage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String searchMsg = request.getParameter("searchMsg");
		List<Map<String, Object>> list = carDao.listSearchNumberMessage();

		if (searchMsg.equals("1")) {
			Collections.sort(list, new Comparator<Map<String, Object>>() {
				public int compare(Map<String, Object> o1, Map<String, Object> o2) {
					return o1.get("queryNumber").toString().compareTo(o2.get("queryNumber").toString());
				}
			});
		} else {
			Collections.sort(list, new Comparator<Map<String, Object>>() {
				public int compare(Map<String, Object> o1, Map<String, Object> o2) {
					return o2.get("queryNumber").toString().compareTo(o1.get("queryNumber").toString());
				}
			});
		}
		JSONObject jsonmsg = new JSONObject();
		jsonmsg.put("repMsg", "请求成功");
		jsonmsg.put("repCode", "666");
		jsonmsg.put("data", list);
		System.out.println(jsonmsg);
		response.getWriter().print(jsonmsg);// 将路径返回给客户端
	}
	

	private void listCollectMessage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String collectUserId = request.getParameter("collectUserId");
		List<Object> params = new ArrayList<Object>();
		params.add(collectUserId);
		List<Map<String, Object>> flagFood = carDao.listCollectMessage(params);

		JSONObject jsonmsg = new JSONObject();
		jsonmsg.put("repMsg", "请求成功");
		jsonmsg.put("repCode", "666");
		jsonmsg.put("data", flagFood);
		System.out.println(jsonmsg);
		response.getWriter().print(jsonmsg);// 将路径返回给客户端

	}

	private void listSearchScoreMessage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getContextPath();
		String userId = request.getParameter("userId");
		String searchMsg = request.getParameter("searchMsg");

		List<Map<String, Object>> list = carDao.listSearchScoreMessage(userId);

		if (searchMsg.equals("1")) {
			Collections.sort(list, new Comparator<Map<String, Object>>() {
				public int compare(Map<String, Object> o1, Map<String, Object> o2) {
					return o1.get("scoreMsg").toString().compareTo(o2.get("scoreMsg").toString());
				}
			});
		} else {
			Collections.sort(list, new Comparator<Map<String, Object>>() {
				public int compare(Map<String, Object> o1, Map<String, Object> o2) {
					return o2.get("scoreMsg").toString().compareTo(o1.get("scoreMsg").toString());
				}
			});
		}

		JSONObject jsonmsg = new JSONObject();
		jsonmsg.put("repMsg", "请求成功");
		jsonmsg.put("repCode", "666");
		jsonmsg.put("data", list);
		System.out.println(jsonmsg);
		response.getWriter().print(jsonmsg);// 将路径返回给客户端

	}

	private void listScoreMessage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getContextPath();
		List<Map<String, Object>> list = carDao.listScoreMessage();
		request.setAttribute("listMessage", list);
		request.getRequestDispatcher("../scoreMessage.jsp").forward(request, response);

	}

	//
	// private void listCollectMessage(HttpServletRequest request,
	// HttpServletResponse response) throws ServletException, IOException {
	// String path = request.getContextPath();
	// List<Map<String, Object>> list = carDao.listCollectMessage();
	// request.setAttribute("listMessage", list);
	// request.getRequestDispatcher("../collectMessage.jsp").forward(request,
	// response);
	//
	// }

	private void updateUserImage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String uRealName = request.getParameter("uRealName");
		String uEmail = request.getParameter("uEmail");
		String uSex = request.getParameter("uSex");
		String uCardImg = request.getParameter("uCardImg");
		String uCarImg = request.getParameter("uCarImg");
		String uid = request.getParameter("uid");
		String imagePath = null;
		if (uid == null) {
			try {
				List<FileItem> items = this.upload.parseRequest(request);
				if (items != null && !items.isEmpty()) {
					for (FileItem fileItem : items) {
						String filename = fileItem.getName();
						String imgPath = "E:\\2017-2017CDCode\\CarLease\\CarLeaseService\\WebRoot\\upload";
						System.out.println("文件保存路径为:" + imgPath + "/" + filename);
						File real_path = new File(imgPath + "/" + filename);
						InputStream inputSteam = fileItem.getInputStream();
						BufferedInputStream fis = new BufferedInputStream(inputSteam);
						FileOutputStream fos = new FileOutputStream(real_path);
						int f;
						while ((f = fis.read()) != -1) {
							fos.write(f);
						}
						fos.flush();
						fos.close();
						fis.close();
						inputSteam.close();
						System.out.println("文件：" + filename + "上传成功!");
						imagePath = filename;

					}
				}

			} catch (FileUploadException e) {
				e.printStackTrace();
			}
		} else {

			List<Object> params = new ArrayList<Object>();
			params.add(uRealName + "");
			params.add(uEmail + "");
			params.add(uSex + "");
			params.add(uCardImg + "");
			params.add(uCarImg + "");
			params.add(uid + "");
			boolean flag = carDao.updateImageMessage(params);
			System.out.println(flag + "");
			if (flag) {
				System.out.println("上传文件成功!");
				JSONObject jsonmsg = new JSONObject();
				jsonmsg.put("repMsg", "处理成功");
				jsonmsg.put("repCode", "666");
				response.getWriter().print(jsonmsg);// 将路径返回给客户端

			} else {
				System.out.println("上传文件失败");
			}
		}
	}

	private void addScore(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getContextPath();
		String scoreMessageId = request.getParameter("scoreMessageId");
		String scoreMessage = request.getParameter("scoreMessage");
		List<Object> params = new ArrayList<Object>();
		params.add(scoreMessageId);
		params.add(scoreMessage);

		System.out.println(scoreMessageId);
		System.out.println(scoreMessage);
		boolean flag = carDao.addScore(params);
		if (flag) {
			JSONObject jsonmsg = new JSONObject();
			jsonmsg.put("repMsg", "评分成功");
			jsonmsg.put("repCode", "666");
			System.out.println(jsonmsg);
			response.getWriter().print(jsonmsg);// 将路径返回给客户端
		} else {
			JSONObject jsonmsg = new JSONObject();
			jsonmsg.put("repMsg", "评分失败");
			jsonmsg.put("repCode", "111");
			System.out.println(jsonmsg);
			response.getWriter().print(jsonmsg);// 将路径返回给客户端
		}

	}

	private void listSearchTypeMessage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getContextPath();
		String searchMsg = request.getParameter("searchMsg");
		String userId = request.getParameter("userId");

		List<Object> params = new ArrayList<Object>();
		params.add(searchMsg);

		List<Map<String, Object>> list = carDao.listSearchTypeMessage(params, userId);
		JSONObject jsonmsg = new JSONObject();
		jsonmsg.put("repMsg", "请求成功");
		jsonmsg.put("repCode", "666");
		jsonmsg.put("data", list);
		System.out.println(jsonmsg);
		response.getWriter().print(jsonmsg);// 将路径返回给客户端

	}

	private void listSearchMoneyMessage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getContextPath();
		String searchMsg = request.getParameter("searchMsg");
		String userId = request.getParameter("userId");
		List<Map<String, Object>> list;

		if (searchMsg.equals("1")) {
			list = carDao.listSearchMoneyAscMessage(userId);
		} else {
			list = carDao.listSearchMoneyDescMessage(userId);
		}

		JSONObject jsonmsg = new JSONObject();
		jsonmsg.put("repMsg", "请求成功");
		jsonmsg.put("repCode", "666");
		jsonmsg.put("data", list);
		System.out.println(jsonmsg);
		response.getWriter().print(jsonmsg);// 将路径返回给客户端

	}

	private void listTypePhoneMessage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		List<Map<String, Object>> flagFood = carDao.listTypeMessage();

		JSONObject jsonmsg = new JSONObject();
		jsonmsg.put("repMsg", "请求成功");
		jsonmsg.put("repCode", "666");
		jsonmsg.put("data", flagFood);
		System.out.println(jsonmsg);
		response.getWriter().print(jsonmsg);// 将路径返回给客户端

	}

	private void deleteCollectMessage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getContextPath();
		String collectUserId = request.getParameter("collectUserId");
		String collectMessageId = request.getParameter("collectMessageId");
		List<Object> params = new ArrayList<Object>();
		params.add(collectUserId);
		params.add(collectMessageId);
		boolean flag = carDao.deleteCollect(params);
		if (flag) {
			JSONObject jsonmsg = new JSONObject();
			jsonmsg.put("repMsg", "取消成功");
			jsonmsg.put("repCode", "666");
			System.out.println(jsonmsg);
			response.getWriter().print(jsonmsg);// 将路径返回给客户端
		} else {
			JSONObject jsonmsg = new JSONObject();
			jsonmsg.put("repMsg", "取消失败");
			jsonmsg.put("repCode", "111");
			System.out.println(jsonmsg);
			response.getWriter().print(jsonmsg);// 将路径返回给客户端
		}

	}

	private void addCollectMessage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getContextPath();
		String collectUserId = request.getParameter("collectUserId");
		String collectUserName = request.getParameter("collectUserName");
		String collectMessageId = request.getParameter("collectMessageId");
		List<Object> params = new ArrayList<Object>();
		params.add(collectUserId);
		params.add(collectUserName);
		params.add(collectMessageId);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");// 设置日期格式
		params.add(df.format(new Date()));
		boolean flag = carDao.addCollect(params);
		if (flag) {
			JSONObject jsonmsg = new JSONObject();
			jsonmsg.put("repMsg", "收藏成功");
			jsonmsg.put("repCode", "666");
			System.out.println(jsonmsg);
			response.getWriter().print(jsonmsg);// 将路径返回给客户端
		} else {
			JSONObject jsonmsg = new JSONObject();
			jsonmsg.put("repMsg", "收藏失败");
			jsonmsg.put("repCode", "111");
			System.out.println(jsonmsg);
			response.getWriter().print(jsonmsg);// 将路径返回给客户端
		}

	}

	private void listMessageChoice(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getContextPath();
		List<Map<String, Object>> list = carDao.listTypeMessage();
		request.setAttribute("listMessage", list);
		request.getRequestDispatcher("../formCar.jsp").forward(request, response);

	}

	private void deleteType(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String typeId = request.getParameter("typeId");
		List<Object> params = new ArrayList<Object>();
		params.add(typeId);
		boolean flag = carDao.deleteType(params);
		if (flag) {
			listTypeMessage(request, response);
		}

	}

	private void addType(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getContextPath();

		String typeName = request.getParameter("typeName");
		List<Object> params = new ArrayList<Object>();
		params.add(typeName);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");// 设置日期格式
		params.add(df.format(new Date()));
		boolean flag = carDao.addType(params);

		if (flag) {
			listTypeMessage(request, response);

		} else {
			JSONObject jsonmsg = new JSONObject();
			jsonmsg.put("repMsg", "提交失败");
			jsonmsg.put("repCode", "111");
			System.out.println(jsonmsg);
			// response.getWriter().print(jsonmsg);// 将路径返回给客户端
		}

	}

	private void listTypeMessage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getContextPath();
		List<Map<String, Object>> list = carDao.listTypeMessage();
		request.setAttribute("listMessage", list);
		request.getRequestDispatcher("../typeMessage.jsp").forward(request, response);

	}

	private void UpdateMessage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String houseId = request.getParameter("houseId");

		final long MAX_SIZE = 2048 * 1024 * 1024;// 设置上传文件最大值为2G，可以改为更大
		// 表单含有文件要提交
		String path = request.getContextPath();
		DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
		// 构建一个文件上传类
		ServletFileUpload servletFileUpload = new ServletFileUpload(diskFileItemFactory);
		// servletFileUpload.setFileSizeMax(3 * 1024 * 1024);
		servletFileUpload.setSizeMax(MAX_SIZE);// 上传文件总大小
		List<FileItem> list = null;
		List<Object> params = new ArrayList<Object>();
		try {
			// 解析request的请求
			list = servletFileUpload.parseRequest(request);
			// 取出所有表单的值:判断非文本字段和文本字段
			for (FileItem fileItem : list) {
				if (fileItem.isFormField()) {

					if (fileItem.getFieldName().equals("houseName")) {

						params.add(fileItem.getString("utf-8"));
						System.out.println(fileItem.getString("utf-8"));

					}

					if (fileItem.getFieldName().equals("houseMoney")) {

						params.add(fileItem.getString("utf-8"));
						System.out.println(fileItem.getString("utf-8"));

					}

					if (fileItem.getFieldName().equals("chapterMsg")) {

						System.out.println(fileItem.getString("utf-8"));
						params.add(fileItem.getString("utf-8").split(",")[0]);
						params.add(fileItem.getString("utf-8").split(",")[1]);

					}

				} else {
					try {
						// String image = fileItem.getName();
						// System.out.println("image--->>" + image);
						// params.add(image);
						// params.add(houseId);
						// String upload_path = request.getRealPath("/upload");
						// System.out.println("--->>" + upload_path);
						// File real_path = new File(upload_path + "/" + image);
						// fileItem.write(real_path);

						String image = fileItem.getName();
						System.out.println("image111--->>" + image);
						params.add(image);
						params.add(houseId);
						String upload_path = request.getRealPath("/upload");
						System.out.println("--->>" + upload_path);
						String imgPath = "E:\\2017-2017CDCode\\Hotel\\HotelService\\WebRoot\\upload";
						File real_path = new File(imgPath + "/" + image);
						fileItem.write(real_path);

						// 把数据插入到数据库中
					} catch (Exception e) {
						e.printStackTrace();
					}

					boolean flag = carDao.updateMessage(params);

					if (flag) {
						listMessage(request, response);
					} else {
						System.out.println("flag:no");
					}

				}
			}
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void queryMessage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String houseId = request.getParameter("houseId");
		List<Object> params = new ArrayList<Object>();
		params.add(houseId);
		Map<String, Object> mapmsg = carDao.queryHouse(params);
		List<Map<String, Object>> list = carDao.listTypeMessage();
		request.setAttribute("listMessage", list);
		request.setAttribute("mapmsg", mapmsg);
		request.getRequestDispatcher("../formUpdateHouse.jsp").forward(request, response);

	}

	private void addMessage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final long MAX_SIZE = 2048 * 1024 * 1024;// 设置上传文件最大值为2G，可以改为更大
		// 表单含有文件要提交
		String path = request.getContextPath();
		DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
		// 构建一个文件上传类
		ServletFileUpload servletFileUpload = new ServletFileUpload(diskFileItemFactory);
		// servletFileUpload.setFileSizeMax(3 * 1024 * 1024);
		servletFileUpload.setSizeMax(MAX_SIZE);// 上传文件总大小
		List<FileItem> list = null;
		List<Object> params = new ArrayList<Object>();
		try {
			// 解析request的请求
			list = servletFileUpload.parseRequest(request);
			// 取出所有表单的值:判断非文本字段和文本字段
			for (FileItem fileItem : list) {
				if (fileItem.isFormField()) {
					if (fileItem.getFieldName().equals("carNo")) {

						params.add(fileItem.getString("utf-8"));
						System.out.println(fileItem.getString("utf-8"));

					}

					if (fileItem.getFieldName().equals("carMoney")) {

						params.add(fileItem.getString("utf-8"));
						System.out.println(fileItem.getString("utf-8"));

					}

					if (fileItem.getFieldName().equals("carMessage")) {

						params.add(fileItem.getString("utf-8"));
						System.out.println(fileItem.getString("utf-8"));

					}

					if (fileItem.getFieldName().equals("chapterMsg")) {

						System.out.println(fileItem.getString("utf-8"));
						params.add(fileItem.getString("utf-8").split(",")[0]);
						params.add(fileItem.getString("utf-8").split(",")[1]);

						SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
						params.add(df.format(new Date()));
					}

					// }

				} else {
					try {
						String image = fileItem.getName();
						System.out.println("image111--->>" + image);
						params.add(image);
						String upload_path = request.getRealPath("/upload");
						System.out.println("--->>" + upload_path);
						String imgPath = "E:\\2017-2017CDCode\\CarLease\\CarLeaseService\\WebRoot\\upload";
						File real_path = new File(imgPath + "/" + image);
						fileItem.write(real_path);
						// 把数据插入到数据库中
					} catch (Exception e) {
						e.printStackTrace();
					}

					boolean flag = carDao.addMessage(params);

					if (flag) {
						listMessage(request, response);
					} else {
						System.out.println("flag:no");
					}

				}
			}
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void listSearchMessage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getContextPath();
		String searchMsg = request.getParameter("searchMsg");
		List<Map<String, Object>> list = carDao.listSearchMessage(searchMsg);
		JSONObject jsonmsg = new JSONObject();
		jsonmsg.put("repMsg", "请求成功");
		jsonmsg.put("repCode", "666");
		jsonmsg.put("data", list);
		System.out.println(jsonmsg);
		response.getWriter().print(jsonmsg);// 将路径返回给客户端

	}

	private void deleteMessage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String carId = request.getParameter("carId");
		List<Object> params = new ArrayList<Object>();
		params.add(carId);
		boolean flag = carDao.deleteMessage(params);
		if (flag) {
			listMessage(request, response);
		}

	}

	private void updateReview(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String rId = request.getParameter("rId");
		String replyContent = request.getParameter("replyContent");
		List<Object> params = new ArrayList<Object>();
		params.add(replyContent);
		params.add(rId);
		boolean flag = carDao.updateMessage(params);
		if (flag) {
			List<Map<String, Object>> list = carDao.listReviewMessage();
			request.setAttribute("listMessage", list);
			request.getRequestDispatcher("../reviewListMessage.jsp").forward(request, response);
		}

	}

	private void listPhoneMessage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String userId = request.getParameter("userId");
		System.out.println(userId);

		List<Map<String, Object>> flagFood = carDao.listPhoneMessage(userId);

		JSONObject jsonmsg = new JSONObject();
		jsonmsg.put("repMsg", "请求成功");
		jsonmsg.put("repCode", "666");
		jsonmsg.put("data", flagFood);
		System.out.println(jsonmsg);
		response.getWriter().print(jsonmsg);// 将路径返回给客户端

	}

	private void listMessage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getContextPath();
		List<Map<String, Object>> list = carDao.listMessage();
		request.setAttribute("listMessage", list);
		request.getRequestDispatcher("../carMessage.jsp").forward(request, response);

	}

	private void addOldMessage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getContextPath();

		String naviName = request.getParameter("naviName");
		String naviJD = request.getParameter("naviJD");
		String naviWD = request.getParameter("naviWD");
		List<Object> params = new ArrayList<Object>();
		params.add(naviName);
		params.add(naviJD);
		params.add(naviWD);
		boolean flag = carDao.addMessage(params);
		if (flag) {
			listMessage(request, response);
		} else {
			JSONObject jsonmsg = new JSONObject();
			jsonmsg.put("repMsg", "提交失败");
			jsonmsg.put("repCode", "111");
			System.out.println(jsonmsg);
			response.getWriter().print(jsonmsg);// 将路径返回给客户端
		}

	}

}
