package board;

import java.util.List;

import VO.BoardVO;

public interface IBoardDAO {
	/**
	 * 게시물을 등록하는메서드
	 * @author 
	 * @param bv
	 */
	boolean insertBoard(BoardVO bv); //반환타입 boolean

	
	List<BoardVO> printTitle();


	/**
	 * 게시물 id를 통해 게시물내용 출력
	 * @author 
	 * @param a
	 */
	String printContent(int board_id); // BoardVO 

	/**
	 * 자기가 올린 게시물제목만 출력
	 * @author 
	 */
	List<BoardVO> printMyBoard(String memId);

	/**
	 * 게시물 삭제
	 * @author 
	 */
	boolean deleteBoard(String memId, int bId);

}
