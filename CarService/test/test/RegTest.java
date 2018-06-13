package test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.junit.Test;

import com.student.data.dao.CarDao;
import com.student.data.dao.OrderDao;
import com.student.data.dao.RegisterDao;

public class RegTest {

	private RegisterDao orderDao;

	@Test
	public void Reg() {
		orderDao = new RegisterDao();

		List<Object> params = new ArrayList<Object>();
		params.add("15249243002");
		params.add("123456");
		
		Map<String, Object> list_msg = orderDao.queryOne(params);
		
		JSONObject jsonmsg = new JSONObject();
		jsonmsg.put("repCode", "666");
		jsonmsg.put("data", list_msg);
		System.out.println(jsonmsg);
	}
}
