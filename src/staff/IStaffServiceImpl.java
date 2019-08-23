package staff;

import java.util.List;

import member.IMemberService;
import member.IMemberServiceImpl;
import VO.StaffVO;


public class IStaffServiceImpl implements IStaffService{
	private static IStaffService staffService;
	private IMemberService memberService = IMemberServiceImpl.getInstance();
	private IStaffDAO staffDao;
	private IStaffServiceImpl() {
		staffDao = IStaffDAOImpl.getInstance();
	}

	public static IStaffService getInstance() {
		if(staffService == null){
			staffService = new IStaffServiceImpl();
		}
		return staffService;
	}

	@Override
	public List<StaffVO> staffPrint() {
		// TODO Auto-generated method stub
		return staffDao.staffPrint();
	}

	@Override
	public boolean staffRetireFinish(int sid) {
		// TODO Auto-generated method stub
		return staffDao.staffRetireFinish(sid);
	}



}
