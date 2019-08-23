package category;

import java.util.List;

import VO.CategoryVO;

public interface ICategoryDAO {
	/**
	 * @author
	 */
	List<CategoryVO> printCategory(); //List<카테고리VO>
	
	/**
	 * 판매하느 카테고리 메뉴만 출력하는 기능
	 * @return
	 */

	
	/**
	 * 카테고리 추가하는 메서드
	 * @author 
	 * @param cv
	 */
	boolean insertCategory(CategoryVO cv);

	/**
	 * 카테고리 하위메뉴 유무 확인하는 메서드
	 * @author 
	 * @return 카테고리 밑에 하위메뉴가 있는지 알려주는 boolean값
	 */
	boolean subMenuUseCh(String categoryName);

	/**
	 * 카테고리 삭제하는 메서드
	 * @author 
	 * @param cv
	 */
	boolean deleteCategory(String categoryName);
	
	/**
	 * 수정
	 * @return
	 */
	List<CategoryVO> getCsCategoryMenu();
	
	
	/**
	 * 카테고리별 매출 더해서 출력해주는 메서드
	 * @author
	 */
	List<CategoryVO> categoryMoney();

}
