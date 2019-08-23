package reservation;

import java.util.List;

import VO.ReservationVO;

public interface IReservationDAO {

	
	/**
	 * 방 목록 프린트
	 * @author
	 */
	List<ReservationVO> roomPrint();

}
