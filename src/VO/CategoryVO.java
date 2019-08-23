package VO;

public class CategoryVO {

	private int cId; // 카테고리 아이디(pk)
	private String cName; // 카테고리 이름(pk)
	private boolean cUse; //카테고리 하위메뉴 유무 => 없으면 삭제가능 , 있으면 삭제불가능
	private int cRight; //카테고리 구분 => 0: 사용중인 카테고리 , 1: 미사용중인 카테고리
	private int cTotalSale; //카테고리별 총매출
	
	public int getcTotalSale() {
		return cTotalSale;
	}
	public void setcTotalSale(int cTotalSale) {
		this.cTotalSale = cTotalSale;
	}
	
	public boolean getcUse() {
		return cUse;
	}
	public void setcUse(boolean cUse) {
		this.cUse = cUse;
	}
	public int getcId() {
		return cId;
	}
	public void setcId(int cId) {
		this.cId = cId;
	}
	public String getcName() {
		return cName;
	}
	public void setcName(String cName) {
		this.cName = cName;
	}
	
	public int getcRight() {
		return cRight;
	}
	public void setcRight(int cRight) {
		this.cRight = cRight;
	}
}
