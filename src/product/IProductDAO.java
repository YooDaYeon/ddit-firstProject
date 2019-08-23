package product;

import java.util.List;
import java.util.Map;

import VO.ProductVO;

public interface IProductDAO {
	/**
	 * 선택된 카테고리번호를 통해서 개별상품 메뉴를 출력하는 메서드
	 * @author PC06
	 * @param num
	 */
	List<ProductVO> printProduct();//반환타입
	

	
	/**
	 * 상품 추가 메서드
	 * @author 
	 * @param pv
	 */
	boolean insertProduct(Map<String, Object> params);

	/**
	 * 상품 삭제 메서드
	 * @author 
	 * @param pv
	 */
	boolean deleteProduct(int productId);
	
	/**
	 * 수정
	 * @return
	 */
	List<ProductVO> getCsProductMenu();
	
	/**
	 * 개별매출 더해서 출력해주는 메서드
	 * @author
	 */
	List<ProductVO> productMoney();

	List<ProductVO> productPrint();
	
	/**
	 * 수정
	 * @param map
	 */

	
	

}
