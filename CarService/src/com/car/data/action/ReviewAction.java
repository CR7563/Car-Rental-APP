package com.student.data.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.student.data.dao.ReviewDao;

public class ReviewAction extends HttpServlet {

	private ReviewDao reviewDao;

	public ReviewAction() {
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
		if (action_flag.equals("addReviewMessage")) {
			addReviewMessage(request, response);
		} else if (action_flag.equals("listMessage")) {
			listMessage(request, response);
		}else if (action_flag.equals("listNoticesMessage")) {
			listNoticesMessage(request, response);
		}else if (action_flag.equals("updateReview")) {
			updateReview(request, response);
		}else if (action_flag.equals("addOpp")) {
			addOpp(request, response);
		}else if (action_flag.equals("listOpp")) {
			listOpp(request, response);
		}else if (action_flag.equals("deleteMessage")) {
			deleteUserPc(request, response);
		}
	}

	public void init() throws ServletException {

		reviewDao = new ReviewDao();
	}
	
	private void deleteUserPc(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getContextPath();
		String rid = request.getParameter("rid");
		String rMessageId = request.getParameter("rMessageId");
		
		
		List<Object> params = new ArrayList<Object>();
		params.add(rid);
		boolean flag = reviewDao.deleteOpp(params);
		if (flag) {
			
			
			System.out.println(rMessageId);
			List<Object> paramsQuery = new ArrayList<Object>();
			paramsQuery.add(rMessageId);
			// 已经进行分页之后的数据集合
			List<Map<String, Object>> list = reviewDao.listReviewMessage(paramsQuery);
			// studentDao.listMessageTeacher(list);
			request.setAttribute("listMessage", list);
			request.getRequestDispatcher("../reviewMessage.jsp").forward(request, response);
		} else {
			System.out.println("失败了");
		}

	}
	
	private void listOpp(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getContextPath();
		
		String orderFlowerId = request.getParameter("orderFlowerId");
		List<Object> params = new ArrayList<Object>();
		params.add(orderFlowerId);
		
		// 已经进行分页之后的数据集合
		List<Map<String, Object>> list = reviewDao.listNoticesMessage(params);
		// studentDao.listMessageTeacher(list);
		request.setAttribute("listMessage", list);
		request.getRequestDispatcher("../reviewMessage.jsp").forward(request, response);
		
	}
	
	private void addOpp(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getContextPath();
		
		String rUserId = request.getParameter("oUserId");
		String rUserName = request.getParameter("oUserName");
		String rVideoId = request.getParameter("oReviewContent");

		List<Object> params = new ArrayList<Object>();
		params.add(rUserId);
		params.add(rUserName);
		params.add(rVideoId);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");// 设置日期格式
		params.add(df.format(new Date()));
		
		boolean flag = reviewDao.addOpp(params);

		if (flag) {

			JSONObject jsonmsg = new JSONObject();
			jsonmsg.put("repMsg", "提交成功");
			jsonmsg.put("repCode", "666");
			System.out.println(jsonmsg);
			response.getWriter().print(jsonmsg);// 将路径返回给客户端

		} else {
			JSONObject jsonmsg = new JSONObject();
			jsonmsg.put("repMsg", "提交失败");
			jsonmsg.put("repCode", "111");
			System.out.println(jsonmsg);
		}

	}
	private void updateReview(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String rid = request.getParameter("rid");
		String replyContent = request.getParameter("replyContent");
		String rNoticeId = request.getParameter("rNoticeId");
		
		List<Object> params = new ArrayList<Object>();
		params.add(replyContent);
		params.add(rid);
		boolean flag = reviewDao.updateMessage(params);
		if(flag){
			List<Object> paramsReview = new ArrayList<Object>();
			paramsReview.add(rNoticeId);
			// 已经进行分页之后的数据集合
			List<Map<String, Object>> list = reviewDao.listReviewMessage(paramsReview);
			// studentDao.listMessageTeacher(list);
			request.setAttribute("listMessage", list);
			request.getRequestDispatcher("../reviewMessage.jsp").forward(request, response);
		}
		
		
		
	}
	
	
	private void listNoticesMessage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String rMessageId = request.getParameter("rMessageId");
		List<Object> params = new ArrayList<Object>();
		params.add(rMessageId);
		
		System.out.println(rMessageId);
		
		List<Map<String, Object>> list = reviewDao.listNoticesMessage(params);
		JSONObject jsonmsg = new JSONObject();
		jsonmsg.put("repMsg", "请求成功");
		jsonmsg.put("repCode", "666");
		jsonmsg.put("data", list);
		System.out.println(jsonmsg);
		response.getWriter().print(jsonmsg);// 将路径返回给客户端
		
	}
	
	
	private void listMessage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getContextPath();
		
		String carId = request.getParameter("carId");
		System.out.println(carId);
		List<Object> params = new ArrayList<Object>();
		params.add(carId);
		// 已经进行分页之后的数据集合
		List<Map<String, Object>> list = reviewDao.listReviewMessage(params);
		// studentDao.listMessageTeacher(list);
		request.setAttribute("listMessage", list);
		request.getRequestDispatcher("../reviewMessage.jsp").forward(request, response);
		
	}
	
	private void addReviewMessage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String reviewUserId = request.getParameter("reviewUserId");
		String reviewUserName = request.getParameter("reviewUserName");
		String rMessageId = request.getParameter("rMessageId");
		String rReviewContent = request.getParameter("rReviewContent");

		List<Object> params_check = new ArrayList<Object>();
		params_check.add(reviewUserId);
		params_check.add(reviewUserName);
		params_check.add(rMessageId);
		params_check.add(rReviewContent);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");// 设置日期格式
		params_check.add(df.format(new Date()));
		
		
		System.out.println(reviewUserId);
		System.out.println(reviewUserName);
		System.out.println(rMessageId);
		System.out.println(rReviewContent);
		
		boolean flag = reviewDao.addReviewMessage(params_check);
		JSONObject jsonmsg = new JSONObject();
		if (flag) {
			jsonmsg.put("repMsg", "提交成功");
			jsonmsg.put("repCode", "666");
			response.getWriter().print(jsonmsg);// 将路径返回给客户端
			System.out.println(jsonmsg);
		} else {
			jsonmsg.put("repMsg", "预约失败");
			jsonmsg.put("repCode", "111");
			response.getWriter().print(jsonmsg);// 将路径返回给客户端
			System.out.println(jsonmsg);
		}

	}

}
