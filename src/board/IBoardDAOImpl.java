package board;

import java.util.List;

import VO.BoardVO;
import db.DBClass;

public class IBoardDAOImpl implements IBoardDAO{
	private static IBoardDAO boardDao;
	private DBClass db;
	private IBoardDAOImpl(){
		db = DBClass.getInstance();
	}
	
	public static IBoardDAO getInstance() {
		if(boardDao == null){
			boardDao = new IBoardDAOImpl();
		}
		return boardDao;
	}

	@Override
	public boolean insertBoard(BoardVO bv) {
		// TODO Auto-generated method stub
		return db.insertBoard(bv);
	}

	@Override
	public List<BoardVO> printTitle() {
		// TODO Auto-generated method stub
		return db.printTitle();
	}


	@Override
	public String printContent(int board_id) {
		// TODO Auto-generated method stub
		return db.printContent(board_id);
	}

	@Override
	public List<BoardVO> printMyBoard(String memId) {
		// TODO Auto-generated method stub
		return db.printMyBoard(memId);
	}

	@Override
	public boolean deleteBoard(String memId, int bId) {
		// TODO Auto-generated method stub
		return db.deleteBoard(memId, bId);
	}

}
