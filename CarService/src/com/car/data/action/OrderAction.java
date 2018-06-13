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

import com.student.data.dao.OrderDao;

public class OrderAction extends HttpServlet {

	private OrderDao orderDao;

	public OrderAction() {
		super();
	}

	public void destroy() {
		super.destroy();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getContextPath();
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();

		String action_flag = request.getParameter("action_flag");
		if (action_flag.equals("addOrder")) {
			addOrderMessage(request, response);
		} else if (action_flag.equals("updatePayState")) {
			updateState(request, response);
		} else if (action_flag.equals("orderListMessage")) {
			listOrderMsgMessage(request, response);
		} else if (action_flag.equals("orderListMessagePhone")) {
			listOrderMsgMessagePhone(request, response);
		} else if (action_flag.equals("deleteOrderPc")) {
			deleteOrderPc(request, response);
		} else if (action_flag.equals("deleteOrderPhone")) {
			deleteOrderPhone(request, response);
		} else if (action_flag.equals("listOrderPcMessage")) {
			listOrderPcMessage(request, response);
		} else if (action_flag.equals("noPayOrder")) {
			noPayOrder(request, response);
		} else if (action_flag.equals("noPayOrderNumber")) {
			noPayOrderNumber(request, response);
		} else if (action_flag.equals("listOrderDeleteMessage")) {
			listOrderDeleteMessage(request, response);
		} else if (action_flag.equals("updateAddState")) {
			updateAddState(request, response);
		} else if (action_flag.equals("updateReduceState")) {
			updateReduceState(request, response);
		} else if (action_flag.equals("updateUserState")) {
			updateUserState(request, response);
		} else if (action_flag.equals("addOrderBackMessage")) {
			addOrderBackMessage(request, response);
		} else if (action_flag.equals("queryBackOrder")) {
			queryBackOrder(request, response);
		} else if (action_flag.equals("updateBackState")) {
			updateBackState(request, response);
		} else if (action_flag.equals("listMessageChoice")) {
			listMessageChoice(request, response);
		} else if (action_flag.equals("updateOrderState")) {
			updateOrderState(request, response);
		} else if (action_flag.equals("deleteOrderUserPhone")) {
			deleteOrderUserPhone(request, response);
		}
	}

	private void deleteOrderUserPhone(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String path = request.getContextPath();
		String orderId = request.getParameter("orderId");
		List<Object> params = new ArrayList<Object>();
		params.add(orderId);
		boolean flag = orderDao.deleteOrder(params);
		if (flag) {
			JSONObject jsonmsg = new JSONObject();
			jsonmsg.put("repMsg", "删除成功");
			jsonmsg.put("repCode", "666");
			jsonmsg.put("data", "");
			System.out.println(jsonmsg);
			response.getWriter().print(jsonmsg);// 将路径返回给客户端
		} else {
			// 生成json字符串
			JSONObject jsonmsg = new JSONObject();
			jsonmsg.put("repMsg", "删除失败 ");
			jsonmsg.put("repCode", "666");
			jsonmsg.put("data", "");
			System.out.println(jsonmsg);
			response.getWriter().print(jsonmsg);// 将路径返回给客户端
		}

	}

	private void updateOrderState(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getContextPath();
		String orderId = request.getParameter("orderId");
		String houseId = request.getParameter("chapterMsg");
		String chapterMsg = (String) request.getParameter("chapterMsg");
		List<Object> params = new ArrayList<Object>();
		params.add(houseId);
		params.add(orderId);
		System.out.println(orderId);
		System.out.println(chapterMsg);
		boolean flag = orderDao.updateOrderNumber(params);
		if (flag) {
			listOrderMsgMessage(request, response);
		} else {
		}

	}

	private void listMessageChoice(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getContextPath();
		String orderId = request.getParameter("orderId");
		List<Object> params = new ArrayList<Object>();
		params.add(orderId);
		Map<String, Object> mapmsg = orderDao.queryOrderMessage(params);
		List<Map<String, Object>> list = orderDao.listHouseMessage();
		request.setAttribute("listMessage", list);
		request.setAttribute("mapmsg", mapmsg);
		request.getRequestDispatcher("../formUpdateOrder.jsp").forward(request, response);

	}

	private void updateBackState(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getContextPath();
		String orderState = request.getParameter("orderState");
		String orderId = request.getParameter("orderId");
		List<Object> params = new ArrayList<Object>();
		params.add(orderState);
		params.add(orderId);
		boolean flag = orderDao.updateUserState(params);
		if (flag) {
			listOrderMsgMessage(request, response);
		} else {
			JSONObject jsonmsg = new JSONObject();
			jsonmsg.put("repMsg", "操作失败");
			jsonmsg.put("repCode", "111");
			System.out.println(jsonmsg);
			response.getWriter().print(jsonmsg);// 将路径返回给客户端
		}

	}

	private void queryBackOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String orderId = request.getParameter("orderId");
		List<Object> params = new ArrayList<Object>();
		params.add(orderId);
		Map<String, Object> mapmsg = orderDao.queryBackOrder(params);
		request.setAttribute("mapmsg", mapmsg);
		request.getRequestDispatcher("../queryOrderBackMessage.jsp").forward(request, response);

	}

	private void addOrderBackMessage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String path = request.getContextPath();

		String orderbackmsgUserId = request.getParameter("orderbackmsgUserId");
		String orderbackmsgUserName = request.getParameter("orderbackmsgUserName");
		String orderbackmsgOrderId = request.getParameter("orderbackmsgOrderId");
		String orderbackmsgContent = request.getParameter("orderbackmsgContent");

		List<Object> params = new ArrayList<Object>();
		params.add(orderbackmsgUserId);
		params.add(orderbackmsgUserName);
		params.add(orderbackmsgOrderId);
		params.add(orderbackmsgContent);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");// 设置日期格式
		params.add(df.format(new Date()));

		boolean flag = orderDao.addOrderBackMessage(params);
		JSONObject jsonmsg = new JSONObject();
		if (flag) {
			List<Object> paramsUpdate = new ArrayList<Object>();
			paramsUpdate.add("3");
			paramsUpdate.add(orderbackmsgOrderId);
			boolean flagUpdate = orderDao.updateUserState(paramsUpdate);
			if (flagUpdate) {
				jsonmsg.put("repMsg", "提交成功");
				jsonmsg.put("repCode", "666");
				System.out.println(jsonmsg);
				response.getWriter().print(jsonmsg);// 将路径返回给客户端

			} else {
				jsonmsg.put("repMsg", "操作失败");
				jsonmsg.put("repCode", "111");
				System.out.println(jsonmsg);
				response.getWriter().print(jsonmsg);// 将路径返回给客户端
			}

			jsonmsg.put("repMsg", "支付成功");
			jsonmsg.put("repCode", "666");
			response.getWriter().print(jsonmsg);// 将路径返回给客户端
			System.out.println(jsonmsg);
		} else {
			jsonmsg.put("repMsg", "支付失败");
			jsonmsg.put("repCode", "111");
			response.getWriter().print(jsonmsg);// 将路径返回给客户端
			System.out.println(jsonmsg);
		}

	}

	private void updateUserState(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getContextPath();
		String orderState = request.getParameter("orderState");
		String orderId = request.getParameter("orderId");
		List<Object> params = new ArrayList<Object>();
		params.add(orderState);
		params.add(orderId);
		boolean flag = orderDao.updateUserState(params);
		if (flag) {

			JSONObject jsonmsg = new JSONObject();
			jsonmsg.put("repMsg", "操作成功");
			jsonmsg.put("repCode", "666");
			System.out.println(jsonmsg);
			response.getWriter().print(jsonmsg);// 将路径返回给客户端

		} else {
			JSONObject jsonmsg = new JSONObject();
			jsonmsg.put("repMsg", "操作失败");
			jsonmsg.put("repCode", "111");
			System.out.println(jsonmsg);
			response.getWriter().print(jsonmsg);// 将路径返回给客户端
		}

	}

	private void deleteCarOrderPhone(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String path = request.getContextPath();
		String uid = request.getParameter("uid");
		String oid = request.getParameter("oid");
		List<Object> params = new ArrayList<Object>();
		params.add(oid);
		boolean flag = orderDao.deleteOrder(params);

		if (flag) {
			boolean flagState = orderDao.deleteState(params);

			if (flagState) {
				// 生成json字符串
				JSONObject jsonmsg = new JSONObject();
				jsonmsg.put("repMsg", "删除成功");
				jsonmsg.put("repCode", "666");
				jsonmsg.put("data", "");
				System.out.println(jsonmsg);
				response.getWriter().print(jsonmsg);// 将路径返回给客户端
			}

		} else {
			// 生成json字符串
			JSONObject jsonmsg = new JSONObject();
			jsonmsg.put("repMsg", "删除失败 ");
			jsonmsg.put("repCode", "666");
			jsonmsg.put("data", "");
			System.out.println(jsonmsg);
			response.getWriter().print(jsonmsg);// 将路径返回给客户端
		}

	}

	private void updateReduceState(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getContextPath();
		String orderNumber = request.getParameter("orderNumber");
		String orderUserId = request.getParameter("orderUserId");
		String orderId = request.getParameter("orderId");
		List<Object> params = new ArrayList<Object>();
		params.add(orderNumber);
		params.add(orderId);
		params.add(orderUserId);

		if (Integer.valueOf(orderNumber) == 0) {

			List<Object> paramsDelete = new ArrayList<Object>();
			paramsDelete.add(orderId);
			boolean flagDelete = orderDao.deleteOrder(paramsDelete);
			if (flagDelete) {
				JSONObject jsonmsg = new JSONObject();
				jsonmsg.put("repMsg", "预订成功");
				jsonmsg.put("repCode", "666");
				System.out.println(jsonmsg);
				response.getWriter().print(jsonmsg);// 将路径返回给客户端
			}

		} else {

			boolean flag = orderDao.updateNumber(params);
			if (flag) {

				JSONObject jsonmsg = new JSONObject();
				jsonmsg.put("repMsg", "预订成功");
				jsonmsg.put("repCode", "666");
				System.out.println(jsonmsg);
				response.getWriter().print(jsonmsg);// 将路径返回给客户端

			} else {
				JSONObject jsonmsg = new JSONObject();
				jsonmsg.put("repMsg", "预订失败");
				jsonmsg.put("repCode", "111");
				System.out.println(jsonmsg);
				response.getWriter().print(jsonmsg);// 将路径返回给客户端
			}
		}

	}

	/**
	 * 更新支付状态
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void updateAddState(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getContextPath();
		String orderNumber = request.getParameter("orderNumber");
		String orderUserId = request.getParameter("orderUserId");
		String orderId = request.getParameter("orderId");
		List<Object> params = new ArrayList<Object>();
		params.add(orderNumber);
		params.add(orderId);
		params.add(orderUserId);
		boolean flag = orderDao.updateNumber(params);
		if (flag) {

			JSONObject jsonmsg = new JSONObject();
			jsonmsg.put("repMsg", "预订成功");
			jsonmsg.put("repCode", "666");
			System.out.println(jsonmsg);
			response.getWriter().print(jsonmsg);// 将路径返回给客户端

		} else {
			JSONObject jsonmsg = new JSONObject();
			jsonmsg.put("repMsg", "预订失败");
			jsonmsg.put("repCode", "111");
			System.out.println(jsonmsg);
			response.getWriter().print(jsonmsg);// 将路径返回给客户端
		}

	}

	private void listOrderDeleteMessage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String orderUserId = request.getParameter("orderUserId");
		String orderFoodId = request.getParameter("orderFoodId");
		List<Object> params = new ArrayList<Object>();
		params.add(orderUserId);
		params.add(orderFoodId);
		List<Map<String, Object>> list = orderDao.listOrderDeleteMessage(params);
		// 生成json字符串
		JSONObject jsonmsg = new JSONObject();
		jsonmsg.put("repMsg", "请求成功");
		jsonmsg.put("repCode", "666");
		jsonmsg.put("data", list.size());
		System.out.println(jsonmsg);
		response.getWriter().print(jsonmsg);// 将路径返回给客户端
	}

	private void noPayOrderNumber(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String orderUserId = request.getParameter("orderUserId");
		List<Object> params = new ArrayList<Object>();
		params.add(orderUserId);
		Map<String, Object> map = orderDao.listOrderNumberPhone(params);
		// 生成json字符串
		JSONObject jsonmsg = new JSONObject();
		jsonmsg.put("repMsg", "请求成功");
		jsonmsg.put("repCode", "666");
		jsonmsg.put("data", map.get("foodNumber"));
		System.out.println(jsonmsg);
		response.getWriter().print(jsonmsg);// 将路径返回给客户端
	}

	private void noPayOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String orderUserId = request.getParameter("orderUserId");
		List<Object> params = new ArrayList<Object>();
		params.add(orderUserId);
		List<Map<String, Object>> list = orderDao.listCarOrder(params);
		// 生成json字符串
		JSONObject jsonmsg = new JSONObject();
		jsonmsg.put("repMsg", "请求成功");
		jsonmsg.put("repCode", "666");
		jsonmsg.put("data", list);
		System.out.println(jsonmsg);
		response.getWriter().print(jsonmsg);// 将路径返回给客户端
	}

	private void listOrderPcMessage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		List<Map<String, Object>> list = orderDao.listOrderPcMessage();
		request.setAttribute("listorderMesage", list);
		request.getRequestDispatcher("/content/ordermessage.jsp").forward(request, response);
	}

	/**
	 * 添加状态
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void addState(HttpServletRequest request, HttpServletResponse response, String hid, String oid, String uid) throws ServletException,
			IOException {

		List<Object> params = new ArrayList<Object>();
		params.add(Integer.valueOf(hid));
		params.add(Integer.valueOf(oid));
		params.add(1);
		params.add(Integer.valueOf(uid));
		boolean flag = orderDao.addState(params);
		if (flag) {
			System.out.println("状态成功了");
		} else {
			System.out.println("状态失败了");
		}

	}

	/**
	 * 更新支付状态
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void updateState(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getContextPath();
		String orderUserId = request.getParameter("orderUserId");
		String orderFoodId = request.getParameter("orderFoodId");

		String orderTotalMoney = request.getParameter("orderTotalMoney");
		String orderAddress = request.getParameter("orderAddress");
		String orderPayType = request.getParameter("orderPayType");
		System.out.println(orderUserId);
		System.out.println(orderFoodId);
		System.out.println(orderTotalMoney);
		System.out.println(orderAddress);
		System.out.println(orderPayType);

		boolean flag = orderDao.updateState(orderUserId, orderFoodId, orderTotalMoney, orderAddress, orderPayType);

		if (flag) {
			JSONObject jsonmsg = new JSONObject();
			jsonmsg.put("repMsg", "支付成功");
			jsonmsg.put("repCode", "666");
			System.out.println(jsonmsg);
			response.getWriter().print(jsonmsg);// 将路径返回给客户端
		} else {
			JSONObject jsonmsg = new JSONObject();
			jsonmsg.put("repMsg", "预订失败");
			jsonmsg.put("repCode", "111");
			System.out.println(jsonmsg);
			response.getWriter().print(jsonmsg);// 将路径返回给客户端
		}

	}

	private void addOrderMessage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String path = request.getContextPath();
		String orderMoney = request.getParameter("orderMoney");
		String orderMessageId = request.getParameter("orderMessageId");
		String orderUserId = request.getParameter("orderUserId");
		String orderUserName = request.getParameter("orderUserName");

		List<Object> params = new ArrayList<Object>();
		params.add(orderMessageId);
		params.add(orderUserId);
		params.add(orderUserName);
		params.add("1");
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");// 设置日期格式
		params.add(df.format(new Date()));
		params.add(orderMoney);

		boolean flag = orderDao.addOrder(params);
		JSONObject jsonmsg = new JSONObject();
		if (flag) {
			jsonmsg.put("repMsg", "支付成功");
			jsonmsg.put("repCode", "666");
			response.getWriter().print(jsonmsg);// 将路径返回给客户端
			System.out.println(jsonmsg);
		} else {
			jsonmsg.put("repMsg", "支付失败");
			jsonmsg.put("repCode", "111");
			response.getWriter().print(jsonmsg);// 将路径返回给客户端
			System.out.println(jsonmsg);
		}

	}

	/**
	 * 用户订单
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */

	private void listOrderMsgMessage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 已经进行分页之后的数据集合
		List<Map<String, Object>> list = orderDao.listOrderMessage();
		//

		int moneyTotal = 0;
		for (int i = 0; i < list.size(); i++) {
			moneyTotal += Integer.valueOf(list.get(i).get("orderMoney").toString());
		}
		request.setAttribute("listMesage", list);
		request.setAttribute("moneyTotal", moneyTotal + "");
		request.getRequestDispatcher("../orderMessage.jsp").forward(request, response);
	}

	private void listOrderMsgMessagePhone(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String orderState = request.getParameter("orderState");
		String orderUserId = request.getParameter("orderUserId");
		List<Object> params = new ArrayList<Object>();
		params.add(orderUserId);
		// 已经进行分页之后的数据集合
		List<Map<String, Object>> list = orderDao.listMyOrderMessagePhone(params);
		// 生成json字符串
		JSONObject jsonmsg = new JSONObject();
		jsonmsg.put("repMsg", "请求成功");
		jsonmsg.put("repCode", "666");
		jsonmsg.put("data", list);
		System.out.println(jsonmsg);
		response.getWriter().print(jsonmsg);// 将路径返回给客户端
	}

	private void deleteOrderPc(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getContextPath();
		String uid = request.getParameter("uid");
		String oid = request.getParameter("oid");
		List<Object> params = new ArrayList<Object>();
		params.add(oid);
		boolean flag = orderDao.deleteOrder(params);

		if (flag) {
			boolean flagState = orderDao.deleteState(params);
			if (flagState) {
				System.out.println("成功了");
				response.sendRedirect(path + "/servlet/OrderAction?action_flag=orderListMessage&uid=" + uid);
			} else {
				System.out.println("flagState失败了");
			}

		} else {
			System.out.println("flag失败了");
		}

	}

	private void deleteOrderPhone(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String path = request.getContextPath();
		String uid = request.getParameter("uid");
		String oid = request.getParameter("oid");
		List<Object> params = new ArrayList<Object>();
		params.add(oid);
		boolean flag = orderDao.deleteOrder(params);

		if (flag) {
			boolean flagState = orderDao.deleteState(params);

			if (flagState) {
				// 生成json字符串
				JSONObject jsonmsg = new JSONObject();
				jsonmsg.put("repMsg", "删除成功");
				jsonmsg.put("repCode", "666");
				jsonmsg.put("data", "");
				System.out.println(jsonmsg);
				response.getWriter().print(jsonmsg);// 将路径返回给客户端
			}

		} else {
			// 生成json字符串
			JSONObject jsonmsg = new JSONObject();
			jsonmsg.put("repMsg", "删除失败 ");
			jsonmsg.put("repCode", "666");
			jsonmsg.put("data", "");
			System.out.println(jsonmsg);
			response.getWriter().print(jsonmsg);// 将路径返回给客户端
		}

	}

	public void init() throws ServletException {
		orderDao = new OrderDao();
	}

}