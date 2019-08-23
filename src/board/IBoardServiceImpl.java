package board;

import java.util.List;

import member.IMemberService;
import member.IMemberServiceImpl;
import VO.BoardVO;


public class IBoardServiceImpl implements IBoardService{
	private static IBoardService boardService;
	private IMemberService memberService = IMemberServiceImpl.getInstance();
	
	private IBoardDAO boardDao;
	private IBoardServiceImpl() {
		boardDao = IBoardDAOImpl.getInstance();
	}

	public static IBoardService getInstance() {
		if(boardService == null){
			boardService = new IBoardServiceImpl();
		}
		return boardService;
	}

	@Override
	public boolean insertBoard(BoardVO bv) {
		return boardDao.insertBoard(bv);
	}

	@Override
	public List<BoardVO> printTitle() {
		return boardDao.printTitle();
	}



	@Override
	public String printContent(int board_id) {
		return boardDao.printContent(board_id);
	}

	@Override
	public List<BoardVO> printMyBoard(String memId) {
		return boardDao.printMyBoard(memId);
	}

	@Override
	public boolean deleteBoard(String memId, int bId) {
		return boardDao.deleteBoard(memId, bId);
	}

	
	
	
	
}
