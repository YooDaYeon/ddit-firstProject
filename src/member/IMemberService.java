package member;

import java.util.Map;

import VO.MemberVO;

public interface IMemberService {
	/**
	 * 일치하는 아이디와 비밀번호를 가진 회원 정보 가져오는 기능
	 * @author
	 * @return 한명의 회원의 MemberVO 객체의 주소
	 */
	MemberVO getMemberInfo(Map<String, String> params);
	
	/**
	 * 이름변경
	 * @author 김범휘
	 */
	boolean changeName(String name, String loginMemberId); //mem_id map
	
	
	/**
	 * 비번변경
	 * @author 김범휘
	 */
	boolean changePw(String pw, String loginMemberId);
	
	/**
	 * 생일변경
	 * @author 김범휘
	 */
	boolean changeBd(String bd, String loginMemberId);

	/**
	 * 돈충전
	 * @author 김범휘
	 */
	boolean changeMoney(int money, String loginMemberId);

	/**
	 * 회원탈퇴
	 * @author 김범휘
	 */
	boolean MemberRetire(String loginMemberId); //누구?
	
	/**
	 * insert용 메서드
	 * @author 
	 * @param mv
	 */
	boolean addPersonFinish(MemberVO mv);

	/**
	 * 중복확인용 메서드
	 * @author 
	 * @param id
	 * @return 중복인지 아닌지 알려주는 boolean 값
	 */
	boolean checkId(String id);
	
	/**
	 * 전체 회원리스트 출력
	 * @author 김범휘
	 * @return
	 */
	String[] MemberIdPrint();
	/**
	 * 관리자가 선택한 회원정보 출력
	 * @author 김범휘
	 * @param customerId
	 * @return
	 */
	Object[] adminCsPrint(String customerId);
	/**
	 * 검색한 회원 돈충전
	 * @author 김범휘
	 */
	boolean adminCsPointUP(int money, String customerId);
	/**
	 * 관리자 계정으로 선택한 회원 탈퇴
	 * @author 김범휘
	 */
	void adminCsRetire(String customerId);
	
	
	/**
	 * 2차
	 * @author 유다연
	 */
	void createPdf(Map<String, Object> params);

	/**
	 * 비밀번호 암호화
	 * @author 김범휘
	 * @param 평문상태의 비밀번호
	 * @return 암호화된 비밀번호
	 */
	String pwEncryption(String pw);

	void sendMail(Map<String, Object> asd, MemberVO vo);
	
	boolean checkEmail(String email);
	
	
	
	
}
