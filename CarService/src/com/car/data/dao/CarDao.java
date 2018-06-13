package com.student.data.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.student.data.service.HotelService;
import com.student.jdbc.JdbcUtils;

public class CarDao implements HotelService {
	private JdbcUtils jdbcUtils;

	public CarDao() {
		jdbcUtils = new JdbcUtils();
	}
	
	
	public List<Map<String, Object>> listSearchNumberMessage() {
		List<Map<String, Object>> listResult = new ArrayList<Map<String, Object>>();
		Map<String, Object> mapResult;
		// TODO Auto-generated method stub
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		String sql = "select * from carmsg ";
		try {
			jdbcUtils.getConnection();
			list = jdbcUtils.findMoreResult(sql, null);
			for (int i = 0; i < list.size(); i++) {
				mapResult = list.get(i);
				List<Object> paramsFocus = new ArrayList<Object>();
				paramsFocus.clear();
				paramsFocus.add(list.get(i).get("carId") + "");
				
				if(queryNumber(paramsFocus).get("scoreMessage")!=null){
					mapResult.put("queryNumber", queryNumber(paramsFocus).get("scoreMessage"));	
				}else{
					mapResult.put("queryNumber", 0);
				}
				
				listResult.add(mapResult);

			}

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			jdbcUtils.releaseConn();
		}
		return list;
	}
	
	
	public Map<String, Object> queryNumber(List<Object> params) {
		Map<String, Object> map = null;
		String sql = "select count(orderMessageId) as scoreMessage from ordermsg where orderMessageId = ? group by orderMessageId ";
		try {
			jdbcUtils.getConnection();
			map = jdbcUtils.findSimpleResult(sql, params);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			jdbcUtils.releaseConn();
		}
		return map;
	}
	

	public List<Map<String, Object>> listCollectMessage(List<Object> params) {
		List<Map<String, Object>> listResult = new ArrayList<Map<String, Object>>();
		Map<String, Object> mapResult;
		// TODO Auto-generated method stub
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		String sql = "select * from collectmsg where collectUserId = ?";
		try {
			jdbcUtils.getConnection();
			list = jdbcUtils.findMoreResult(sql, params);
			for (int i = 0; i < list.size(); i++) {
				mapResult = list.get(i);

				List<Object> paramsScore = new ArrayList<Object>();
				paramsScore.clear();
				paramsScore.add(list.get(i).get("collectMessageId") + "");
				Map<String, Object> queryScore = queryCar(paramsScore);
				mapResult.put("carMsg", queryScore);

			}

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			jdbcUtils.releaseConn();
		}
		return list;
	}

	public List<Map<String, Object>> listSearchScoreMessage(String userId) {
		List<Map<String, Object>> listResult = new ArrayList<Map<String, Object>>();
		Map<String, Object> mapResult;
		// TODO Auto-generated method stub
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		String sql = "select * from carmsg ";
		try {
			jdbcUtils.getConnection();
			list = jdbcUtils.findMoreResult(sql, null);
			for (int i = 0; i < list.size(); i++) {
				mapResult = list.get(i);
				List<Object> paramsFocus = new ArrayList<Object>();
				paramsFocus.clear();
				paramsFocus.add(userId);
				paramsFocus.add(list.get(i).get("carId") + "");
				mapResult.put("collectState", queryCollectState(paramsFocus));
				listResult.add(mapResult);

				List<Object> paramsScore = new ArrayList<Object>();
				paramsScore.clear();
				paramsScore.add(list.get(i).get("carId") + "");

				Map<String, Object> queryScore = queryScore(paramsScore);

				if (queryScore.get("scoreMsg").equals("")) {
					mapResult.put("scoreMsg", "5");
				} else {
					mapResult.put("scoreMsg", queryScore.get("scoreMsg"));
				}

			}

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			jdbcUtils.releaseConn();
		}
		return list;
	}

	public List<Map<String, Object>> listScoreMessage() {

		List<Map<String, Object>> listResult = new ArrayList<Map<String, Object>>();
		Map<String, Object> mapResult;

		// TODO Auto-generated method stub
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		String sql = "select *,avg(scoreMessage) from scoremsg  group by scoreMessageId ";
		try {
			jdbcUtils.getConnection();
			list = jdbcUtils.findMoreResult(sql, null);

			for (int i = 0; i < list.size(); i++) {
				mapResult = list.get(i);
				List<Object> paramsFocus = new ArrayList<Object>();
				paramsFocus.clear();
				paramsFocus.add(list.get(i).get("scoreMessageId") + "");
				mapResult.put("carMessage", queryCar(paramsFocus).get("carNo"));
				listResult.add(mapResult);
			}

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			jdbcUtils.releaseConn();
		}
		return list;
	}

	public List<Map<String, Object>> listCollectMessage() {

		List<Map<String, Object>> listResult = new ArrayList<Map<String, Object>>();
		Map<String, Object> mapResult;

		// TODO Auto-generated method stub
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		String sql = "select *,count(collectUserId) from collectmsg  group by collectMessageId ";
		try {
			jdbcUtils.getConnection();
			list = jdbcUtils.findMoreResult(sql, null);

			for (int i = 0; i < list.size(); i++) {
				mapResult = list.get(i);
				List<Object> paramsFocus = new ArrayList<Object>();
				paramsFocus.clear();
				paramsFocus.add(list.get(i).get("collectMessageId") + "");
				mapResult.put("carMessage", queryCar(paramsFocus).get("carNo"));
				listResult.add(mapResult);
			}

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			jdbcUtils.releaseConn();
		}
		return list;
	}

	public Map<String, Object> queryCar(List<Object> params) {
		Map<String, Object> map = null;
		String sql = "select * from carmsg where carId=?";
		try {
			jdbcUtils.getConnection();
			map = jdbcUtils.findSimpleResult(sql, params);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			jdbcUtils.releaseConn();
		}
		return map;
	}

	public boolean updateImageMessage(List<Object> params) {
		boolean flag = false;
		try {
			String sql = "update  user set uRealName =?,uEmail =?,uSex =?,uCardImg =?,uCarImg =? where uid = ?";
			jdbcUtils.getConnection();
			flag = jdbcUtils.updateByPreparedStatement(sql, params);
		} catch (Exception e) {
		} finally {
			jdbcUtils.releaseConn();
		}
		return flag;
	}

	public boolean addScore(List<Object> params) {
		boolean flag = false;
		try {
			String sql = "insert into scoremsg (scoreMessageId,scoreMessage) values  (?,?)";
			jdbcUtils.getConnection();
			flag = jdbcUtils.updateByPreparedStatement(sql, params);
		} catch (Exception e) {
		} finally {
			jdbcUtils.releaseConn();
		}
		return flag;
	}

	public List<Map<String, Object>> listSearchTypeMessage(List<Object> params, String userId) {
		List<Map<String, Object>> listResult = new ArrayList<Map<String, Object>>();
		Map<String, Object> mapResult;
		// TODO Auto-generated method stub
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		String sql = "select * from carmsg where carTypeName = ?";
		try {
			jdbcUtils.getConnection();
			list = jdbcUtils.findMoreResult(sql, params);
			for (int i = 0; i < list.size(); i++) {
				mapResult = list.get(i);
				List<Object> paramsFocus = new ArrayList<Object>();
				paramsFocus.clear();
				paramsFocus.add(userId);
				paramsFocus.add(list.get(i).get("carId") + "");
				mapResult.put("collectState", queryCollectState(paramsFocus));
				listResult.add(mapResult);

				List<Object> paramsScore = new ArrayList<Object>();
				paramsScore.clear();
				paramsScore.add(list.get(i).get("carId") + "");

				Map<String, Object> queryScore = queryScore(paramsScore);

				if (queryScore.get("scoreMsg").equals("")) {
					mapResult.put("scoreMsg", "5");
				} else {
					mapResult.put("scoreMsg", queryScore.get("scoreMsg"));
				}
			}

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			jdbcUtils.releaseConn();
		}
		return list;
	}
	public List<Map<String, Object>> listSearchMoneyDescMessage(String userId) {
		List<Map<String, Object>> listResult = new ArrayList<Map<String, Object>>();
		Map<String, Object> mapResult;
		// TODO Auto-generated method stub
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		String sql = "select * from carmsg order by carMoney desc ";
		try {
			jdbcUtils.getConnection();
			list = jdbcUtils.findMoreResult(sql, null);
			for (int i = 0; i < list.size(); i++) {
				mapResult = list.get(i);
				List<Object> paramsFocus = new ArrayList<Object>();
				paramsFocus.clear();
				paramsFocus.add(userId);
				paramsFocus.add(list.get(i).get("carId") + "");
				mapResult.put("collectState", queryCollectState(paramsFocus));
				// mapResult.put("applyState", queryApplyState(paramsFocus));
				listResult.add(mapResult);

				List<Object> paramsScore = new ArrayList<Object>();
				paramsScore.clear();
				paramsScore.add(list.get(i).get("carId") + "");

				Map<String, Object> queryScore = queryScore(paramsScore);

				if (queryScore.get("scoreMsg").equals("")) {
					mapResult.put("scoreMsg", "5");
				} else {
					mapResult.put("scoreMsg", queryScore.get("scoreMsg"));
				}

			}

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			jdbcUtils.releaseConn();
		}
		return list;
	}
	public List<Map<String, Object>> listSearchMoneyAscMessage(String userId) {
		List<Map<String, Object>> listResult = new ArrayList<Map<String, Object>>();
		Map<String, Object> mapResult;
		// TODO Auto-generated method stub
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		String sql = "select * from carmsg order by carMoney asc ";
		try {
			jdbcUtils.getConnection();
			list = jdbcUtils.findMoreResult(sql, null);
			for (int i = 0; i < list.size(); i++) {
				mapResult = list.get(i);
				List<Object> paramsFocus = new ArrayList<Object>();
				paramsFocus.clear();
				paramsFocus.add(userId);
				paramsFocus.add(list.get(i).get("carId") + "");
				mapResult.put("collectState", queryCollectState(paramsFocus));
				// mapResult.put("applyState", queryApplyState(paramsFocus));
				listResult.add(mapResult);

				List<Object> paramsScore = new ArrayList<Object>();
				paramsScore.clear();
				paramsScore.add(list.get(i).get("carId") + "");

				Map<String, Object> queryScore = queryScore(paramsScore);

				if (queryScore.get("scoreMsg").equals("")) {
					mapResult.put("scoreMsg", "5");
				} else {
					mapResult.put("scoreMsg", queryScore.get("scoreMsg"));
				}

			}

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			jdbcUtils.releaseConn();
		}
		return list;
	}

	public boolean deleteCollect(List<Object> params) {
		boolean flag = false;
		try {
			String sql = "delete from collectmsg where collectUserId=? and collectMessageId = ?";
			jdbcUtils.getConnection();
			flag = jdbcUtils.updateByPreparedStatement(sql, params);
		} catch (Exception e) {
		} finally {
			jdbcUtils.releaseConn();
		}
		return flag;
	}

	public boolean addCollect(List<Object> params) {
		boolean flag = false;
		try {
			String sql = "insert into collectmsg (collectUserId,collectUserName,collectMessageId,collectTime) values  (?,?,?,?)";
			jdbcUtils.getConnection();
			flag = jdbcUtils.updateByPreparedStatement(sql, params);
		} catch (Exception e) {
		} finally {
			jdbcUtils.releaseConn();
		}
		return flag;
	}

	public boolean deleteType(List<Object> params) {
		boolean flag = false;
		try {
			String sql = "delete from typemsg where typeId=?";
			jdbcUtils.getConnection();
			flag = jdbcUtils.updateByPreparedStatement(sql, params);
		} catch (Exception e) {
		} finally {
			jdbcUtils.releaseConn();
		}
		return flag;
	}

	public List<Map<String, Object>> listTypeMessage() {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		String sql = "select * from typemsg";
		try {
			jdbcUtils.getConnection();
			list = jdbcUtils.findMoreResult(sql, null);
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			jdbcUtils.releaseConn();
		}
		return list;
	}

	public boolean addType(List<Object> params) {
		boolean flag = false;
		try {
			String sql = "insert into typemsg (typeName,typeTime) values  (?,?)";
			jdbcUtils.getConnection();
			flag = jdbcUtils.updateByPreparedStatement(sql, params);
		} catch (Exception e) {
		} finally {
			jdbcUtils.releaseConn();
		}
		return flag;
	}

	public Map<String, Object> queryHouse(List<Object> params) {
		Map<String, Object> map = null;
		String sql = "select * from housemsg where houseId=?";
		try {
			jdbcUtils.getConnection();
			map = jdbcUtils.findSimpleResult(sql, params);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			jdbcUtils.releaseConn();
		}
		return map;
	}

	public List<Map<String, Object>> listSearchMessage(String proname) {

		// TODO Auto-generated method stub
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		String sql = "select * from navigation where (1=1) ";
		// limit ?,?
		StringBuffer buffer = new StringBuffer(sql);
		List<Object> params = new ArrayList<Object>();
		if (proname != null) {
			buffer.append(" and nName like ? ");
			params.add("%" + proname + "%");
		}
		try {
			jdbcUtils.getConnection();

			System.out.println(buffer.toString());
			list = jdbcUtils.findMoreResult(buffer.toString(), params);
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			jdbcUtils.releaseConn();
		}
		return list;
	}

	public boolean updateMessage(List<Object> params) {
		boolean flag = false;
		try {
			String sql = "update  housemsg set houseName =?,houseMoney = ?,houseTypeId = ? ,houseTypeName= ?,houseImage = ? where houseId = ?";
			jdbcUtils.getConnection();
			flag = jdbcUtils.updateByPreparedStatement(sql, params);
		} catch (Exception e) {
		} finally {
			jdbcUtils.releaseConn();
		}
		return flag;
	}

	@Override
	public boolean addMessage(List<Object> params) {
		boolean flag = false;
		try {
			String sql = "insert into carmsg (carNo,carMoney,carMessage" + ",carTypeId,carTypeName," + "carTime,carImage) values  (?,?,?,?,?,?,?)";
			jdbcUtils.getConnection();
			flag = jdbcUtils.updateByPreparedStatement(sql, params);
		} catch (Exception e) {
		} finally {
			jdbcUtils.releaseConn();
		}
		return flag;
	}

	public List<Map<String, Object>> listTopFood() {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		String sql = "select * from flowermsg where fTopState = 2";
		try {
			jdbcUtils.getConnection();
			list = jdbcUtils.findMoreResult(sql, null);
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			jdbcUtils.releaseConn();
		}
		return list;
	}

	public List<Map<String, Object>> listPhoneMessage(String userId) {

		List<Map<String, Object>> listResult = new ArrayList<Map<String, Object>>();
		Map<String, Object> mapResult;

		// TODO Auto-generated method stub
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		String sql = "select * from carmsg";
		try {
			jdbcUtils.getConnection();
			list = jdbcUtils.findMoreResult(sql, null);

			for (int i = 0; i < list.size(); i++) {
				mapResult = list.get(i);
				List<Object> paramsFocus = new ArrayList<Object>();
				paramsFocus.clear();
				paramsFocus.add(list.get(i).get("carId") + "");
				mapResult.put("stateMessage", queryState(paramsFocus));

				List<Object> paramsCollect = new ArrayList<Object>();
				paramsCollect.clear();
				paramsCollect.add(userId);
				paramsCollect.add(list.get(i).get("carId") + "");
				mapResult.put("collectState", queryCollectState(paramsCollect));
				listResult.add(mapResult);

				List<Object> paramsScore = new ArrayList<Object>();
				paramsScore.clear();
				paramsScore.add(list.get(i).get("carId") + "");

				Map<String, Object> queryScore = queryScore(paramsScore);

				if (queryScore.get("scoreMsg").equals("")) {
					mapResult.put("scoreMsg", "5");
				} else {
					mapResult.put("scoreMsg", queryScore.get("scoreMsg"));
				}

			}

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			jdbcUtils.releaseConn();
		}
		return list;
	}

	public Map<String, Object> queryScore(List<Object> params) {
		Map<String, Object> map = null;
		String sql = "select AVG(scoreMessage) as scoreMsg from scoremsg  where scoreMessageId = ?";
		try {
			jdbcUtils.getConnection();
			map = jdbcUtils.findSimpleResult(sql, params);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			jdbcUtils.releaseConn();
		}
		return map;
	}

	public boolean queryCollectState(List<Object> params) {
		boolean flag = false;
		String sql = "select * from collectmsg where collectUserId = ? and collectMessageId=  ?";
		try {
			jdbcUtils.getConnection();
			Map<String, Object> map = jdbcUtils.findSimpleResult(sql, params);
			flag = !map.isEmpty() ? true : false;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			jdbcUtils.releaseConn();
		}
		return flag;
	}

	public boolean queryState(List<Object> params) {
		boolean flag = false;
		String sql = "select * from ordermsg where  orderMessageId=  ? and orderState = 1";
		try {
			jdbcUtils.getConnection();
			Map<String, Object> map = jdbcUtils.findSimpleResult(sql, params);
			flag = !map.isEmpty() ? true : false;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			jdbcUtils.releaseConn();
		}
		return flag;
	}

	public List<Map<String, Object>> listStudentMessage(List<Object> params) {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		String sql = "select * from student where  sId = ?";
		try {
			jdbcUtils.getConnection();
			list = jdbcUtils.findMoreResult(sql, params);
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			jdbcUtils.releaseConn();
		}
		return list;
	}

	public List<Map<String, Object>> listReviewMessage() {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		String sql = "select * from review";
		try {
			jdbcUtils.getConnection();
			list = jdbcUtils.findMoreResult(sql, null);
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			jdbcUtils.releaseConn();
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> listMessage() {

		List<Map<String, Object>> listResult = new ArrayList<Map<String, Object>>();
		Map<String, Object> mapResult;

		// TODO Auto-generated method stub
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		String sql = "select * from carmsg";
		try {
			jdbcUtils.getConnection();
			list = jdbcUtils.findMoreResult(sql, null);

			for (int i = 0; i < list.size(); i++) {
				mapResult = list.get(i);
				List<Object> paramsFocus = new ArrayList<Object>();
				paramsFocus.clear();
				paramsFocus.add(list.get(i).get("houseId") + "");
				mapResult.put("stateMessage", queryState(paramsFocus));
				listResult.add(mapResult);
			}

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			jdbcUtils.releaseConn();
		}
		return list;
	}

	@Override
	public boolean deleteMessage(List<Object> params) {
		boolean flag = false;
		try {
			String sql = "delete from carmsg where carId=?";
			jdbcUtils.getConnection();
			flag = jdbcUtils.updateByPreparedStatement(sql, params);
		} catch (Exception e) {
		} finally {
			jdbcUtils.releaseConn();
		}
		return flag;
	}
}
