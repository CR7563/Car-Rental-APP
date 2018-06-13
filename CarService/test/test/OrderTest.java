package test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.junit.Test;

import com.student.data.dao.CarDao;
import com.student.data.dao.OrderDao;

public class OrderTest {

	private OrderDao orderDao;

	@Test
	public void Reg() {
		orderDao = new OrderDao();

		List<Object> params = new ArrayList<Object>();
		params.add("81");
		
		List<Map<String, Object>> list_msg = orderDao.listOrderMessage();
		
		JSONObject jsonmsg = new JSONObject();
		jsonmsg.put("repCode", "666");
		jsonmsg.put("data", list_msg);
		System.out.println(jsonmsg);
	}
}
