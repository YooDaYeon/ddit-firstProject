package reservation;

import java.util.List;

import member.IMemberService;
import member.IMemberServiceImpl;
import VO.ReservationVO;


public class IReservationServiceImpl implements IReservationService{
	private static IReservationService reservationService;

	private IMemberService memberService = IMemberServiceImpl.getInstance();
	private IReservationDAO reservationDao;
	private IReservationServiceImpl() {
		reservationDao = IReservationDAOImpl.getInstance();
	}

	public static IReservationService getInstance() {
		if(reservationService == null){
			reservationService = new IReservationServiceImpl();
		}
		return reservationService;
	}



	@Override
	public List<ReservationVO> roomPrint() {
		return reservationDao.roomPrint();
	}


}
