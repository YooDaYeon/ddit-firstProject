package order;

import java.util.Map;

import product.IProductService;
import product.IProductServiceImpl;
import category.ICategoryService;
import category.ICategoryServiceImpl;
import member.IMemberService;
import member.IMemberServiceImpl;

public class IOrderServiceImpl implements IOrderService{
	private static IOrderService orderService;
	private IMemberService memberService = IMemberServiceImpl.getInstance();
	private ICategoryService categoryService = ICategoryServiceImpl.getInstance();
	private IProductService productService= IProductServiceImpl.getInstance();
	
	
	private IOrderDAO orderDao;
	private IOrderServiceImpl() {
		orderDao = IOrderDAOImpl.getInstance();
	}

	public static IOrderService getInstance() {
		if(orderService == null){
			orderService = new IOrderServiceImpl();
		}
		return orderService;
	}


	@Override
	public int payUse(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return orderDao.payUse(map);
	}
	
	@Override
	public void addpTotalSale(Map<String, Object> map) {
		orderDao.addpTotalSale(map);
		//이 메서드를 order에 있어야할지 prdouct에 있어야할지 고민
	}

	@Override
	public String summary(Map<String, Object> map2) {
		String prodName = orderDao.summary(map2);
		return prodName;
	}
}
