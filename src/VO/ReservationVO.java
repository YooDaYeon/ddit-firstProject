package VO;

import java.util.Date;

public class ReservationVO {
	
	private String startDate;//pk 예약 시작시간
	private String endDate;//예약 종료 시간
	private int reRoomNum; // 방번호
	private boolean reRoomUse;// 방 사용유무
	private String memId;// 회원아이디
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public int getReRoomNum() {
		return reRoomNum;
	}
	public void setReRoomNum(int reRoomNum) {
		this.reRoomNum = reRoomNum;
	}
	public boolean isReRoomUse() {
		return reRoomUse;
	}
	public void setReRoomUse(boolean reRoomUse) {
		this.reRoomUse = reRoomUse;
	}
	public String getMemId() {
		return memId;
	}
	public void setMemId(String memId) {
		this.memId = memId;
	}
}
