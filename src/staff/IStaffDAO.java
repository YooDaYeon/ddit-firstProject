package staff;

import java.util.List;

import VO.StaffVO;

public interface IStaffDAO {
	/**
	 * 전체 직원 출력하는 메서드
	 * @author
	 */
	List<StaffVO> staffPrint();
	
	/**
	 * 직원 퇴사처리하는 메서드
	 * @author 
	 * @param sv
	 */
	boolean staffRetireFinish(int sid);


}
