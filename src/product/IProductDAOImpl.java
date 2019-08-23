package product;

import java.util.List;
import java.util.Map;

import VO.ProductVO;
import db.DBClass;

public class IProductDAOImpl implements IProductDAO{
	private static IProductDAO productDao;
	private DBClass db;
	private IProductDAOImpl(){
		db = DBClass.getInstance();
	}
	
	public static IProductDAO getInstance() {
		if(productDao == null){
			productDao = new IProductDAOImpl();
		}
		return productDao;
	}

	@Override
	public List<ProductVO> printProduct() {
		return db.printProduct();
	}


	@Override
	public boolean insertProduct(Map<String, Object> params) {
		return db.insertProduct(params);
	}

	@Override
	public boolean deleteProduct(int productId) {
		return db.deleteProduct(productId);
	}

	@Override
	public List<ProductVO> getCsProductMenu() {
		return db.getCsProductMenu();
	}

	@Override
	public List<ProductVO> productMoney() {
		return db.productMoney();
	}

	@Override
	public List<ProductVO> productPrint() {
		// TODO Auto-generated method stub
		return db.productPrint();
	}

	
	

}
