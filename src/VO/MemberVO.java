package VO;

public class MemberVO {
	private String memId; //고객 아이디(pk)
	private String memName; //고객이름
	private String memPw; //비밀번호
	private String memBir; // 고객 생일
	private int memMoney; // 고객 돈
	private int memMileage; // 고객마일리지
	private int memRight; // 멤버구분 => 관리자 : 0 , 일반고객 : 1 , 탈퇴회원 : 2 
	private boolean memUse; // 고객 탈퇴 방지 , 로그인만 못하게
	private String memEmail;//이메일

	
	public String getMemEmail() {
		return memEmail;
	}
	public void setMemEmail(String memEmail) {
		this.memEmail = memEmail;
	}
	public boolean isMemUse() {
		return memUse;
	}
	public void setMemUse(boolean memUse) {
		this.memUse = memUse;
	}
	public String getMemId() {
		return memId;
	}
	public void setMemId(String memId) {
		this.memId = memId;
	}
	public String getMemName() {
		return memName;
	}
	public void setMemName(String memName) {
		this.memName = memName;
	}
	public String getMemPw() {
		return memPw;
	}
	public void setMemPw(String memPw) {
		this.memPw = memPw;
	}
	public String getMemBir() {
		return memBir;
	}
	public void setMemBir(String memBir) {
		this.memBir = memBir;
	}
	public int getMemMoney() {
		return memMoney;
	}
	public void setMemMoney(int memMoney) {
		this.memMoney = memMoney;
	}
	public int getMemMileage() {
		return memMileage;
	}
	public void setMemMileage(int memMileage) {
		this.memMileage = memMileage;
	}
	public int getMemRight() {
		return memRight;
	}
	public void setMemRight(int memRight) {
		this.memRight = memRight;
	}
	
}
