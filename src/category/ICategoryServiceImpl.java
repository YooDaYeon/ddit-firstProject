package category;

import java.util.List;

import member.IMemberService;
import member.IMemberServiceImpl;
import VO.CategoryVO;


public class ICategoryServiceImpl implements ICategoryService{
	private static ICategoryService categoryService;
	private IMemberService memberService = IMemberServiceImpl.getInstance();
	
	private ICategoryDAO categoryDao;
	private ICategoryServiceImpl() {
		categoryDao = ICategoryDAOImpl.getInstance();
	}

	public static ICategoryService getInstance() {
		if(categoryService == null){
			categoryService = new ICategoryServiceImpl();
		}
		return categoryService;
	}

	@Override
	public List<CategoryVO> printCategory() {
		return categoryDao.printCategory();
	}


	@Override
	public boolean insertCategory(CategoryVO cv) {
		return categoryDao.insertCategory(cv);
	}

	@Override
	public boolean subMenuUseCh(String categoryName) {
		return categoryDao.subMenuUseCh(categoryName);
	}

	@Override
	public boolean deleteCategory(String categoryName) {
		return categoryDao.deleteCategory(categoryName);
	}

	@Override
	public List<CategoryVO> getCsCategoryMenu() {
		return categoryDao.getCsCategoryMenu();
	}

	@Override
	public List<CategoryVO> categoryMoney() {
		return categoryDao.categoryMoney();
	}

}
