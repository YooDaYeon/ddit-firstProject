package member;

import java.util.Map;

import VO.MemberVO;
import db.DBClass;

public class IMemberDAOImpl implements IMemberDAO{
	private static IMemberDAO memberDao;
	private DBClass db;
	private IMemberDAOImpl(){
		db = DBClass.getInstance();
	}
	
	public static IMemberDAO getInstance() {
		if(memberDao == null){
			memberDao = new IMemberDAOImpl();
		}
		return memberDao;
	}

	@Override
	public MemberVO getMemberInfo(Map<String, String> params) {
		return db.getMemberInfo(params);
	}

	@Override
	public boolean changeName(String name, String loginMemberId) {
		return db.changeName(name, loginMemberId);
	}

	@Override
	public boolean changePw(String pw, String loginMemberId) {
		return db.changePw(pw, loginMemberId);
	}

	@Override
	public boolean changeBd(String bd, String loginMemberId) {
		// TODO Auto-generated method stub
		return db.changeBd(bd, loginMemberId);
	}

	@Override
	public boolean changeMoney(int money, String loginMemberId) {
		// TODO Auto-generated method stub
		return db.changeMoney(money, loginMemberId);
	}

	@Override
	public boolean MemberRetire(String loginMemberId) {
		// TODO Auto-generated method stub
		return db.MemberRetire(loginMemberId);
	}

	@Override
	public boolean addPersonFinish(MemberVO mv) {
		// TODO Auto-generated method stub
		return db.addPersonFinish(mv);
	}

	@Override
	public boolean checkId(String id) {
		// TODO Auto-generated method stub
		return db.checkId(id);
	}

	@Override
	public String[] MemberIdPrint() {
		// TODO Auto-generated method stub
		return db.MemberIdPrint();
	}

	@Override
	public Object[] adminCsPrint(String customerId) {
		// TODO Auto-generated method stub
		return db.adminCsPrint(customerId);
	}

	@Override
	public boolean adminCsPointUP(int money, String customerId) {
		// TODO Auto-generated method stub
		return db.adminCsPointUP(money, customerId);
	}

	@Override
	public void adminCsRetire(String customerId) {
		// TODO Auto-generated method stub
		db.adminCsRetire(customerId);
		
	}

	@Override
	public boolean checkEmail(String email) {
		// TODO Auto-generated method stub
		return false;
	}


}
