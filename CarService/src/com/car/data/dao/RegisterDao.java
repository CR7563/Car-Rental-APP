package com.student.data.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.student.data.service.RegisterService;
import com.student.jdbc.JdbcUtils;

public class RegisterDao implements RegisterService {
	private JdbcUtils jdbcUtils;

	public RegisterDao() {
		jdbcUtils = new JdbcUtils();
	}
	
	
	public boolean updateMoney(List<Object> params) {
		boolean flag = false;
		try {
			String sql = "update  user set uMoney =? where uid = ?";
			jdbcUtils.getConnection();
			flag = jdbcUtils.updateByPreparedStatement(sql, params);
		} catch (Exception e) {
		} finally {
			jdbcUtils.releaseConn();
		}
		return flag;
	}
	
	
	public boolean updatePswd(List<Object> params) {
		boolean flag = false;
		try {
			String sql = "update  user set upswd =? where uid = ?";
			jdbcUtils.getConnection();
			flag = jdbcUtils.updateByPreparedStatement(sql, params);
		} catch (Exception e) {
		} finally {
			jdbcUtils.releaseConn();
		}
		return flag;
	}
	
	
	public boolean CheckNoTeacher(List<Object> params) {
		boolean flag = false;
		String sql = "select * from teacher where tNo=?";
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
	
	
	public boolean CheckNoStudent(List<Object> params) {
		boolean flag = false;
		String sql = "select * from student where stuNo=?";
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
	
	public boolean CheckNoWorker(List<Object> params) {
		boolean flag = false;
		String sql = "select * from worker where wNo=?";
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
	public boolean updateAddress(List<Object> params) {
		boolean flag = false;
		try {
			String sql = "update  user set uaddress =? where uid = ?";
			jdbcUtils.getConnection();
			flag = jdbcUtils.updateByPreparedStatement(sql, params);
		} catch (Exception e) {
		} finally {
			jdbcUtils.releaseConn();
		}
		return flag;
	}
	
	public boolean updateName(List<Object> params) {
		boolean flag = false;
		try {
			String sql = "update  user set uname =? where uid = ?";
			jdbcUtils.getConnection();
			flag = jdbcUtils.updateByPreparedStatement(sql, params);
		} catch (Exception e) {
		} finally {
			jdbcUtils.releaseConn();
		}
		return flag;
	}
	
	
	
	public boolean updatePhone(List<Object> params) {
		boolean flag = false;
		try {
			String sql = "update  user set uphone =? where uid = ?";
			jdbcUtils.getConnection();
			flag = jdbcUtils.updateByPreparedStatement(sql, params);
		} catch (Exception e) {
		} finally {
			jdbcUtils.releaseConn();
		}
		return flag;
	}
	
	public boolean deleteUser(List<Object> params) {
		boolean flag = false;
		try {
			String sql = "delete from user where uid=?";
			jdbcUtils.getConnection();
			flag = jdbcUtils.updateByPreparedStatement(sql, params);
		} catch (Exception e) {
		} finally {
			jdbcUtils.releaseConn();
		}
		return flag;
	}
	

	public boolean resgisterPhone(List<Object> params) {
		boolean flag = false;
		try {
			String sql = "insert into user (uname,uphone,upswd,utime,uCode) values  (?,?,?,?,?)";
			jdbcUtils.getConnection();
			flag = jdbcUtils.updateByPreparedStatement(sql, params);
		} catch (Exception e) {
		} finally {
			jdbcUtils.releaseConn();
		}
		return flag;
	}

	@Override
	public boolean resgisterCheck(List<Object> params) {
		boolean flag = false;
		String sql = "select * from user where uname=? and upswd=?";
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

	@Override
	public boolean Login(List<Object> params) {
		boolean flag = false;
		String sql = "select * from user where uphone=? and upswd=?";
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

	@Override
	public Map<String, Object> queryOne(List<Object> params) {
		Map<String, Object> map = null;
		String sql = "select * from user where uphone=? and upswd=?";
		try {
			jdbcUtils.getConnection();
			map = jdbcUtils.findSimpleResult(sql, params);
			
			List<Object> paramsScore = new ArrayList<Object>();
			paramsScore.add(map.get("uid"));
			
			
			
			
			if(queryScore(paramsScore).get("scoreMessage")!=null){
				map.put("scoreMessage", queryScore(paramsScore).get("scoreMessage"));
			}else{
				map.put("queryNumber", 0);
			}
			
		
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			jdbcUtils.releaseConn();
		}
		return map;
	}
	
	
	
	public Map<String, Object> queryScore(List<Object> params) {
		Map<String, Object> map = null;
		String sql = "select count(orderUserId) as scoreMessage from ordermsg where orderUserId = ? group by orderUserId ";
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

	public Map<String, Object> queryTeaNo(List<Object> params) {
		Map<String, Object> map = null;
		String sql = "select * from teacher where tNo=?";
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

	public Map<String, Object> queryWorkNo(List<Object> params) {
		Map<String, Object> map = null;
		String sql = "select * from worker where wNo=?";
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
	public Map<String, Object> queryStuNo(List<Object> params) {
		Map<String, Object> map = null;
		String sql = "select * from student where stuNo=?";
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
	

	@Override
	public Map<String, Object> queryId(List<Object> params) {
		Map<String, Object> map = null;
		String sql = "select uid from user where uname=? and upswd=?";
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
	public List<Map<String, Object>> listUserMsg() {

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		String sql = "select * from user";
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
	public List<Map<String, Object>> listUser(List<Object> params) {

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		String sql = "select * from user";
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

//	public List<Map<String, Object>> listUser(String proname, int start, int end) {
//		// TODO Auto-generated method stub
//		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
//		String sql = "select * from user where (1=1) ";
//		// limit ?,?
//		StringBuffer buffer = new StringBuffer(sql);
//		List<Object> params = new ArrayList<Object>();
//		if (proname != null) {
//			buffer.append(" and uname like ? ");
//			params.add("%" + proname + "%");
//		}
//		buffer.append("limit ?,? ");
//		params.add(start);
//		params.add(end);
//		try {
//			jdbcUtils.getConnection();
//			list = jdbcUtils.findMoreResult(buffer.toString(), params);
//		} catch (Exception e) {
//			// TODO: handle exception
//		} finally {
//			jdbcUtils.releaseConn();
//		}
//		return list;
//	}

	public int getItemCount() {
		int result = 0;
		Map<String, Object> map = null;
		String sql = " select count(*) mycount from user ";
		try {
			jdbcUtils.getConnection();
			map = jdbcUtils.findSimpleResult(sql, null);
			result = Integer.parseInt(map.get("mycount").toString());
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			jdbcUtils.releaseConn();
		}
		// TODO Auto-generated method stub
		return result;
	}

}
