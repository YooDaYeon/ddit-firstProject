package reservation;

import java.util.List;

import VO.ReservationVO;
import db.DBClass;

public class IReservationDAOImpl implements IReservationDAO{
	private static IReservationDAO reservationDao;
	private DBClass db;
	private IReservationDAOImpl(){
		db = DBClass.getInstance();
	}
	
	public static IReservationDAO getInstance() {
		if(reservationDao == null){
			reservationDao = new IReservationDAOImpl();
		}
		return reservationDao;
	}



	@Override
	public List<ReservationVO> roomPrint() {
		// TODO Auto-generated method stub
		return db.roomPrint();
	}

}
