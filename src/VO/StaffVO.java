package VO;

import java.util.Date;

public class StaffVO {
	private int sId;//pk 직원id
	private String sAttendance ;//출근시간
	private String sLeave ;//퇴근시간
	private int sPay;//급여
	private boolean sOut;//알바생 퇴사유무
	private String sName;//알바생이름
	public int getsId() {
		return sId;
	}
	public void setsId(int sId) {
		this.sId = sId;
	}
	public String getsAttendance() {
		return sAttendance;
	}
	public void setsAttendance(String sAttendance) {
		this.sAttendance = sAttendance;
	}
	public String getsLeave() {
		return sLeave;
	}
	public void setsLeave(String sLeave) {
		this.sLeave = sLeave;
	}
	public int getsPay() {
		return sPay;
	}
	public void setsPay(int sPay) {
		this.sPay = sPay;
	}
	public boolean issOut() {
		return sOut;
	}
	public void setsOut(boolean sOut) {
		this.sOut = sOut;
	}
	public String getsName() {
		return sName;
	}
	public void setsName(String sName) {
		this.sName = sName;
	}
	


}
