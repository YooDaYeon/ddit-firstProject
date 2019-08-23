package order;

import java.util.Map;

public interface IOrderDAO {

	
	int payUse(Map<String, Object> map);
	/**
	 * 수정
	 * @param map
	 */
	void addpTotalSale(Map<String, Object> map);
	String summary (Map<String, Object> map2);

}
