package VO;

import java.util.*;


public class OrderVO {
	private int orderId; // 주문번호(pk)
	private Date orderDate; //주문날짜 Date사용
	private int pId; // 제품 번호(fk)
	private String memId; // 고객아이디(fk)
	private int totalSales;
	
	public int gettotalSales() {
		return totalSales;
	}
	public void settotalSales(int totalSales) {
		this.totalSales = totalSales;
	}
	
	
	
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	public int getpId() {
		return pId;
	}
	public void setpId(int pId) {
		this.pId = pId;
	}
	public String getMemId() {
		return memId;
	}
	public void setMemId(String memId) {
		this.memId = memId;
	}
//	public int getcId() {
//		return cId;
//	}
//	public void setcId(int cId) {
//		this.cId = cId;
//	}
}
