package category;

import java.util.List;

import VO.CategoryVO;
import db.DBClass;

public class ICategoryDAOImpl implements ICategoryDAO{
	private static ICategoryDAO categoryDao;
	private DBClass db;
	private ICategoryDAOImpl(){
		db = DBClass.getInstance();
	}
	
	public static ICategoryDAO getInstance() {
		if(categoryDao == null){
			categoryDao = new ICategoryDAOImpl();
		}
		return categoryDao;
	}

	@Override
	public List<CategoryVO> printCategory() {
		return db.printCategory();
	}


	@Override
	public boolean insertCategory(CategoryVO cv) {
		return db.insertCategory(cv);
	}

	@Override
	public boolean subMenuUseCh(String categoryName) {
		return db.subMenuUseCh(categoryName);
	}

	@Override
	public boolean deleteCategory(String categoryName) {
		return db.deleteCategory(categoryName);
	}

	@Override
	public List<CategoryVO> getCsCategoryMenu() {
		return db.getCsCategoryMenu();
	}

	@Override
	public List<CategoryVO> categoryMoney() {
		return db.categoryMoney();
	}

}
