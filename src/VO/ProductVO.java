package VO;

public class ProductVO {

	private int pId; // 상품 번호(pk)
	private String pName; // 상품이름
	private int pPrice; // 상품 가격
	private int pMount; // 상품 개수
	private int pRight; //상품구분  - 0 : 판매중인 상품 , 1: 삭제된 상품 
	private int cId; // 카테고리 아이디(fk)
	private String cName; // 카테고리 이름(fk)
	private int pTotalSale; //개별상품 총매출

	public int getpTotalSale() {
		return pTotalSale;
	}
	public void setpTotalSale(int pTotalSale) {
		this.pTotalSale = pTotalSale;
	}
	
	
	public int getpRight() {
		return pRight;
	}
	public void setpRight(int pRight) {
		this.pRight = pRight;
	}
	public String getcName() {
		return cName;
	}
	public void setcName(String cName) {
		this.cName = cName;
	}
	public int getpId() {
		return pId;
	}
	public void setpId(int pId) {
		this.pId = pId;
	}
	public String getpName() {
		return pName;
	}
	public void setpName(String pName) {
		this.pName = pName;
	}
	public int getpPrice() {
		return pPrice;
	}
	public void setpPrice(int pPrice) {
		this.pPrice = pPrice;
	}
	public int getpMount() {
		return pMount;
	}
	public void setpMount(int pMount) {
		this.pMount = pMount;
	}
	public int getcId() {
		return cId;
	}
	public void setcId(int cId) {
		this.cId = cId;
	}
}
