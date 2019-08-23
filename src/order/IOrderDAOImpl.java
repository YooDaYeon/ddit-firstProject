package order;

import java.util.Map;

import db.DBClass;

public class IOrderDAOImpl implements IOrderDAO{
	private static IOrderDAO orderDao;
	private DBClass db;
	private IOrderDAOImpl(){
		db = DBClass.getInstance();
	}
	
	public static IOrderDAO getInstance() {
		if(orderDao == null){
			orderDao = new IOrderDAOImpl();
		}
		return orderDao;
	}


	@Override
	public int payUse(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return db.payUse(map);
	}
	
	@Override
	public void addpTotalSale(Map<String, Object> map) {
		db.addpTotalSale(map);
		//이 메서드를 order에 있어야할지 prdouct에 있어야할지 고민
	}

	@Override
	public String summary(Map<String, Object> map2) {
		String prodName = db.summary(map2);
		return prodName;
	}
		

}
