package test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.junit.Test;

import com.student.data.dao.CarDao;

public class FlowerTest {

	private CarDao orderDao;

	@Test
	public void Reg() {
		orderDao = new CarDao();

		List<Object> params = new ArrayList<Object>();
		params.add("69");

		List<Map<String, Object>> list_msg = orderDao.listPhoneMessage("69");

		 
		JSONObject jsonmsg = new JSONObject();
		jsonmsg.put("repCode", "666");
		jsonmsg.put("data", list_msg);
		System.out.println(jsonmsg);
	}

	public List<Map<String, Object>> sortToMap(List<Map<String, Object>> mapList, final String sort, final String order) {
		Collections.sort(mapList, new Comparator<Map<String, Object>>() {
			public int compare(Map<String, Object> o1, Map<String, Object> o2) {
				if (o1.get(sort) instanceof String) {
					String map1value = objectToString(o1.get(sort));
					String map2value = objectToString(o2.get(sort));
					if ("DESC".equals(order.toUpperCase())) {
						return map1value.compareTo(map2value);
					} else {
						return map2value.compareTo(map1value);
					}

				} else {
					Double map1value = objectToDouble(o1.get(sort));
					Double map2value = objectToDouble(o2.get(sort));
					if ("DESC".equals(order.toUpperCase())) {
						return map1value - map2value > 0 ? 1 : 1;
					} else {
						return map1value - map2value > 0 ? -1 : 1;
					}
				}
			}
		});
		return mapList;
	}

	/**
	 * 将Object转换为String
	 */
	public static String objectToString(Object o) {
		if (o == null) {
			return "";
		} else if (o instanceof String) {
			return (String) o;
		} else if (o instanceof Integer) {
			return String.valueOf((Integer) o);
		} else if (o instanceof Long) {
			return String.valueOf((Long) o);
		} else if (o instanceof Double) {
			return String.valueOf((Double) o);
		} else if (o instanceof Float) {
			return String.valueOf((Float) o);
		} else {
			return "";
		}
	}

	/**
	 * 将Object转换为Double
	 */
	public static Double objectToDouble(Object o) {
		if (o instanceof BigDecimal) {
			return ((BigDecimal) o).doubleValue();
		} else if (o instanceof String) {
			return Double.valueOf((String) o);
		} else if (o instanceof Integer) {
			return Double.valueOf((Integer) o);
		} else if (o instanceof Double) {
			return (Double) o;
		} else if (o instanceof Long) {
			return ((Long) o).doubleValue();
		} else {
			return 0.0;
		}
	}
}
