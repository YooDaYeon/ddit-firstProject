package staff;

import java.util.List;

import VO.StaffVO;
import db.DBClass;

public class IStaffDAOImpl implements IStaffDAO{
	private static IStaffDAO dao;
	private DBClass db;
	private IStaffDAOImpl(){
		db = DBClass.getInstance();
	}
	
	public static IStaffDAO getInstance() {
		if(dao == null){
			dao = new IStaffDAOImpl();
		}
		return dao;
	}

	@Override
	public List<StaffVO> staffPrint() {
		// TODO Auto-generated method stub
		return db.staffPrint();
	}

	@Override
	public boolean staffRetireFinish(int sid) {
		// TODO Auto-generated method stub
		return db.staffRetireFinish(sid);
	}



}
