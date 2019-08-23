package product;

import java.util.List;
import java.util.Map;

import category.ICategoryService;
import category.ICategoryServiceImpl;
import member.IMemberService;
import member.IMemberServiceImpl;
import VO.ProductVO;


public class IProductServiceImpl implements IProductService{
	private static IProductService productService;
	private IMemberService memberService = IMemberServiceImpl.getInstance();
	private ICategoryService categoryService = ICategoryServiceImpl.getInstance();
	
	
	private IProductDAO productDao;
	private IProductServiceImpl() {
		productDao = IProductDAOImpl.getInstance();
	}

	public static IProductService getInstance() {
		if(productService == null){
			productService = new IProductServiceImpl();
		}
		return productService;
	}

	@Override
	public List<ProductVO> printProduct() {
		return productDao.printProduct();
	}



	@Override
	public boolean insertProduct(Map<String, Object> params) {
		return productDao.insertProduct(params);
	}

	@Override
	public boolean deleteProduct(int productId) {
		return productDao.deleteProduct(productId);
	}

	@Override
	public List<ProductVO> getCsProductMenu() {
		return productDao.getCsProductMenu();
	}

	@Override
	public List<ProductVO> productMoney() {
		return productDao.productMoney();
	}

	@Override
	public List<ProductVO> productPrint() {
		// TODO Auto-generated method stub
		return productDao.productPrint();
	}

	

}
