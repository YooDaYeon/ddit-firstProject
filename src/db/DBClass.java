package db;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import VO.BoardVO;
import VO.CategoryVO;
import VO.MemberVO;
import VO.OrderVO;
import VO.ProductVO;
import VO.ReservationVO;
import VO.StaffVO;


public class DBClass {
	
	private static DBClass db;
	
	private DBClass() {
		
	}
	
	public static DBClass getInstance() {
		if(db == null){
			db = new DBClass();
		}
		return db;
	}
	
	private List<MemberVO> memberList = new ArrayList<MemberVO>();// 멤버리스트
	private List<CategoryVO> categoryList = new ArrayList<CategoryVO>();// 카테고리리스트
	private List<ProductVO> productList = new ArrayList<ProductVO>();// 상품리스트
	private List<OrderVO> orderList = new ArrayList<OrderVO>();// 주문리스트
	private List<BoardVO> boardList = new ArrayList<BoardVO>();// 게시판리스트
	private List<StaffVO> staffList = new ArrayList<StaffVO>();// 직원리스트
	private List<ReservationVO> reservationList = new ArrayList<ReservationVO>();// 직원리스트
	
	// 인터페이스타입<제네릭타입> 참조변수명 = new ArrayList 구현체
	// 컬렉션 끼리는 서로 형변환이 가능하다 (리스트는 셋으로 셋은 리스트로 바꿀수 있다. 인터페이스타입으로 해놔야 이것들이 가능하다.)
	// 그래서 항상 앞에는 인터페이스 타입 뒤에는 구현체타입 ex)맵 - 해쉬맵

	{
		// 관리자 계정
		MemberVO mv1 = new MemberVO();
		mv1.setMemUse(true);
		mv1.setMemRight(0);
		mv1.setMemId("admin");
		mv1.setMemPw("8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918");
		mv1.setMemName("admin");
		mv1.setMemBir("900101");
		mv1.setMemMoney(0);
		mv1.setMemMileage(0);
		mv1.setMemEmail("youbi89@naver.com");
		memberList.add(mv1);

		// 회원 계정
		MemberVO mv2 = new MemberVO();
		mv2.setMemUse(true);
		mv2.setMemRight(1);
		mv2.setMemId("test");
		mv2.setMemPw("9f86d081884c7d659a2feaa0c55ad015a3bf4f1b2b0b822cd15d6c15b0f00a08");
		mv2.setMemName("성민창");
		mv2.setMemBir("931017");
		mv2.setMemMoney(10000);
		mv2.setMemMileage(0);
		mv2.setMemEmail("bijou0147@naver.com");
		memberList.add(mv2);

		MemberVO mv3 = new MemberVO();
		mv3.setMemUse(true);
		mv3.setMemRight(1);
		mv3.setMemId("test1");
		mv3.setMemPw("1b4f0e9851971998e732078544c96b36c3d01cedf7caa332359d6f1d83567014");
		mv3.setMemName("유다연");
		mv3.setMemBir("940213");
		mv3.setMemMoney(10000);
		mv3.setMemMileage(100);
		mv3.setMemEmail("");
		memberList.add(mv3);
	}

	{

		// 카테고리
		CategoryVO cv1 = new CategoryVO();
		cv1.setcUse(true);
		cv1.setcId(0);
		cv1.setcName("Coffee");
		categoryList.add(cv1);

		CategoryVO cv2 = new CategoryVO();
		cv2.setcUse(true);
		cv2.setcId(1);
		cv2.setcName("Tea");
		categoryList.add(cv2);

		CategoryVO cv3 = new CategoryVO();
		cv3.setcUse(false);
		cv3.setcId(2);
		cv3.setcName("Ade");
		categoryList.add(cv3);

	}

	{
		// 상품(Coffee류)
		ProductVO pv1 = new ProductVO();
		pv1.setpId(0);
		pv1.setcId(0);// Coffee류
		pv1.setpName("아메리카노");
		pv1.setpPrice(3000);
		pv1.setcName("Coffee");
		productList.add(pv1);

		ProductVO pv2 = new ProductVO();
		pv2.setpId(1);
		pv2.setcId(0);// Coffee류
		pv2.setpName("카페라떼");
		pv2.setpPrice(4000);
		pv2.setcName("Coffee");
		productList.add(pv2);

		ProductVO pv3 = new ProductVO();
		pv3.setpId(2);
		pv3.setcId(0);// Coffee류
		pv3.setpName("카푸치노");
		pv3.setpPrice(5000);
		pv3.setcName("Coffee");
		productList.add(pv3);
		
		ProductVO pv4 = new ProductVO();
		pv4.setpId(3);
		pv4.setcId(1);
		pv4.setpName("루이보스");
		pv4.setpPrice(5000);
		pv4.setcName("Tea");
		pv4.setpRight(0);
		productList.add(pv4);
		
		ProductVO pv5 = new ProductVO();
		pv5.setpId(4);
		pv5.setcId(1);
		pv5.setpName("카모마일");
		pv5.setpPrice(5000);
		pv5.setcName("Tea");
		pv5.setpRight(0);
		productList.add(pv5);
		
		ProductVO pv6 = new ProductVO();
		pv6.setpId(5);
		pv6.setcId(1);
		pv6.setpName("얼그레이");
		pv6.setpPrice(5000);
		pv6.setcName("Tea");
		pv6.setpRight(0);
		productList.add(pv6);
	}

	{

		// 주문
		OrderVO ov1 = new OrderVO();
		ov1.setOrderId(0);
		ov1.setMemId("test");
		ov1.setpId(0); // 아메리카노
		ov1.setOrderDate(null);
		orderList.add(ov1);

	}

	{
		// 게시판
		BoardVO bv1 = new BoardVO();
		bv1.setbId(0);
		bv1.setbTitle("공지사항");
		bv1.setbContent("리뷰 남겨주세요.");
		bv1.setMemId("admin");
		boardList.add(bv1);

		BoardVO bv2 = new BoardVO();
		bv2.setbId(1);
		bv2.setbTitle("리뷰1");
		bv2.setbContent("커피존맛탱구리");
		bv2.setMemId("test");
		boardList.add(bv2);
	}
	{
		// 직원관리
		StaffVO sv1 = new StaffVO();
		sv1.setsOut(false);
		sv1.setsId(0);
		sv1.setsAttendance(null);
		sv1.setsLeave(null);
		sv1.setsName("김범휘");
		sv1.setsPay(0);
		staffList.add(sv1);
		
		StaffVO sv2 = new StaffVO();
		sv2.setsOut(false);
		sv2.setsId(1);
		sv2.setsAttendance(null);
		sv2.setsLeave(null);
		sv2.setsName("유다연");
		sv2.setsPay(0);
		staffList.add(sv2);
		
		StaffVO sv3 = new StaffVO();
		sv3.setsOut(false);
		sv3.setsId(2);
		sv3.setsAttendance(null);
		sv3.setsLeave(null);
		sv3.setsName("성민창");
		sv3.setsPay(0);
		staffList.add(sv3);

	}

	{
		// 스터디룸 관리
		ReservationVO rv1 = new ReservationVO();
		rv1.setReRoomUse(false);
		rv1.setMemId("test");
		rv1.setStartDate("2019-02-22 01:00:00");
		rv1.setEndDate("2019-02-22 03:00:00");
		rv1.setReRoomNum(0);
		reservationList.add(rv1);
	}


	/**
	 * 아이디 중복확인
	 * @param id
	 * @return
	 */
	public boolean checkId(String id) {
		boolean result = false;
		for (int i = 0; i < memberList.size(); i++) {
			if (id.equals(memberList.get(i).getMemId())) {
				result = false;
				break;
			} else {
				result = true;
			}
		}
		return result;
	}
	/**
	 * 로그인
	 * @param params
	 * @return
	 */
	public MemberVO getMemberInfo(Map<String, String> params) {
		String id = params.get("memId");
		String pw = params.get("memPw");
		MemberVO memberInfo = null;
		for (int i = 0; i < memberList.size(); i++) {
			if (id.equals(memberList.get(i).getMemId())
					&& pw.equals(memberList.get(i).getMemPw())) {
				memberInfo = memberList.get(i);
				break;
			}
		}
		return memberInfo;
	}
	
	/**
	 * 신규회원 리스트에 추카
	 * @param mv
	 * @return
	 */
	public boolean addPersonFinish(MemberVO mv){
		return memberList.add(mv);
		
	}
	/**
	 * 이름변경
	 * @author 김범휘
	 * @param name
	 * @param loginMemberId
	 * @return 변경완료 블린
	 */
	public boolean changeName(String name, String loginMemberId) {
		for (int i = 0; i < memberList.size(); i++) {
			if(loginMemberId.equals(memberList.get(i).getMemId())){
				memberList.get(i).setMemName(name);
				break;
			}
		}
		return true;
	}
	/**
	 * 비번변경
	 * @author 김범휘
	 * @param name
	 * @param loginMemberPw
	 * @return 변경완료 블린
	 */
	public boolean changePw(String pw, String loginMemberId) {
		for (int i = 0; i < memberList.size(); i++) {
			if(loginMemberId.equals(memberList.get(i).getMemId())){
				memberList.get(i).setMemPw(pw);
				break;
			}
		}
		return true;
	}


	/**
	 * 생일변경
	 * @author 김범휘
	 * @param pw
	 * @param loginMemberPw
	 * @return 생일변경 블린
	 */
	public boolean changeBd(String bd, String loginMemberId) {
		for (int i = 0; i < memberList.size(); i++) {
			if(loginMemberId.equals(memberList.get(i).getMemId())){
				memberList.get(i).setMemBir(bd);
				break;
			}
		}
		return true;
	}

	/**
	 * 돈충전
	 * @author 김범휘
	 * @param money
	 * @param loginMemberId
	 * @return 돈충전 블린
	 */
	public boolean changeMoney(int money, String loginMemberId) {
		for (int i = 0; i < memberList.size(); i++) {
			if(loginMemberId.equals(memberList.get(i).getMemId())){
				memberList.get(i).setMemMoney(memberList.get(i).getMemMoney()+money);
				break;
			}
		}
		return true;
	}
	/**
	 * 회원탈퇴
	 * @author 김범휘
	 * @param loginMemberId
	 * @return	회원탈퇴 블린
	 */
	public boolean MemberRetire(String loginMemberId) {
		for (int i = 0; i < memberList.size(); i++) {
			if(loginMemberId.equals(memberList.get(i).getMemId())){
				memberList.get(i).setMemRight(2);
				memberList.get(i).setMemUse(false);
				break;
			}
		}
		return true;
	}
	/**
	 * 전체회원리스트 출력
	 * @author 김범휘
	 * @return
	 */
	public String[] MemberIdPrint() {
		String[] memberIds = new String[memberList.size()];
		for (int i = 0; i < memberList.size(); i++) {
			memberIds[i] = memberList.get(i).getMemId();
		}
		return memberIds;
	}

	/**
	 * 검색한 회원 정보 출력
	 * @author 김범휘
	 * @param customerId
	 * @return
	 */
	public Object[] adminCsPrint(String customerId) {
		Object[] customerInfo = new Object[7];
		for (int i = 0; i < memberList.size(); i++) {
			if(customerId.equals(memberList.get(i).getMemId())){
				customerInfo[0] = memberList.get(i).getMemId();
				customerInfo[1] = memberList.get(i).getMemPw();
				customerInfo[2] = memberList.get(i).getMemName();
				customerInfo[3] = memberList.get(i).getMemBir();
				customerInfo[4] = memberList.get(i).getMemMoney();
				customerInfo[5] = memberList.get(i).getMemMileage();
				customerInfo[6] = memberList.get(i).getMemRight();
				break;
			}
		}
		return customerInfo;
	}
	/**
	 * 검색한 회원 돈충전
	 * @author 김범휘
	 * @param customerId
	 */
	public boolean adminCsPointUP(int money, String customerId) {
		for (int i = 0; i < memberList.size(); i++) {
			if(customerId.equals(memberList.get(i).getMemId())){
				memberList.get(i).setMemMoney(money + memberList.get(i).getMemMoney());
			}
		}
		return true;
	}
	/**
	 * 관리자계정으로 선택한 회원 탈퇴
	 * @author 김범휘
	 * @param customerId
	 */
	public void adminCsRetire(String customerId){
		for (int i = 0; i < memberList.size(); i++) {
			if(customerId.equals(memberList.get(i).getMemId())){
				memberList.get(i).setMemRight(2);
				memberList.get(i).setMemUse(false);
				break;
			}
		}
	}
	/**
	 * 카테고리 메뉴출력
	 * @author 김범휘
	 * @return
	 */
	public List<CategoryVO> printCategory() {
		return categoryList;
	}
	/**
	 * 개별상품 추가
	 * @author 김범휘
	 * @param params
	 * @return
	 */
	public boolean insertProduct(Map<String, Object> params){
		String categoryName = (String)params.get("cName");
		String productName = (String)params.get("pName");
		int productPrice = (int)params.get("pPrice");
		int cId = 0;
		
		for (int i = 0; i < categoryList.size(); i++) {
			if(categoryName.equals(categoryList.get(i).getcName())){
				cId = categoryList.get(i).getcId();
				categoryList.get(i).setcUse(true);
				break;
			}
		}
		
		
		ProductVO pv = new ProductVO();
		pv.setcName(categoryName);
		pv.setpName(productName);
		pv.setpPrice(productPrice);
		pv.setpId(productList.size());
		pv.setpRight(0);
		pv.setcId(cId);
		
		return productList.add(pv);
	}
	/**
	 * 개별상품 전체 조회
	 * @author 김범휘
	 * @return
	 */
	public List<ProductVO> productPrint(){
		return productList;
	}
	/**
	 * 개별상품 삭제
	 * @author 김범휘
	 * @param productId
	 * @return
	 */
	public boolean deleteProduct(int productId){
		for (int i = 0; i < productList.size(); i++) {
			if(productId == productList.get(i).getpId()) {
				productList.get(i).setpRight(1);
				break;
			}
		}
		return true;
	}
	/**
	 * 카테고리 추가
	 * @author 김범휘
	 * @param cv
	 * @return
	 */
	public boolean insertCategory(CategoryVO cv){
		cv.setcId(categoryList.size());
		cv.setcRight(0);
		cv.setcUse(false);
		return categoryList.add(cv);
	}
	
	/**
	 * 카테고리 하위메뉴 유무 확인
	 * @author 김범휘
	 * @param categoryName
	 * @return
	 */
	public boolean subMenuUseCh(String categoryName) {
//		for (int i = 0; i < productList.size(); i++) {
//			if(categoryName.equals(productList.get(i).getcName())&&productList.get(i).getpRight()==1) {
//				for (int j = 0; j < categoryList.size(); j++) {
//					if(categoryName.equals(categoryList.get(j).getcName())){
//						categoryList.get(j).setcUse(true);
//						categoryList.get(j).setcRight(0);
//						return false;
//					}
//				}
//			}
//		}

		for (int i = 0; i < productList.size(); i++) {
			if(categoryName.equals(productList.get(i).getcName())&&productList.get(i).getpRight()==0) {
				System.out.println(categoryName+"카테고리에 하위메뉴가 존재하여 삭제할 수 없습니다.");
				return false;
			}
		}
		
		
		for (int z = 0; z < categoryList.size(); z++) {
			if(categoryName.equals(categoryList.get(z).getcName())){
				categoryList.get(z).setcUse(false);
				categoryList.get(z).setcRight(1);
			}
		}
		return true;
	}
	/**
	 * 카테고리 삭제
	 * @author 김범휘
	 * @param categoryName
	 * @return
	 */
	public boolean deleteCategory(String categoryName){
		for (int i = 0; i < categoryList.size(); i++) {
			if(categoryName.equals(categoryList.get(i).getcName())) {
				categoryList.get(i).setcRight(1);
				return true;
			}
		}
		return false;
	}
	/**
	 * 개별상품 출력
	 * @return
	 */
	public List<ProductVO> printProduct(){
		return productList;
	}
	
	/**
	 * 주문이 끝나면 개별상품 매출과 판매수량이 증가
	 * 수정
	 * @author 김범휘
	 * @param map
	 */
	public void addpTotalSale(Map<String, Object> map){
		int pId = (int) map.get("pId");
		int qty = (int) map.get("qty");
		int cId = (int) map.get("cId");
		
		List<ProductVO> pv = new ArrayList<ProductVO>();
		for (int i = 0; i < productList.size(); i++) {
			if(cId == productList.get(i).getcId() && productList.get(i).getpRight()==0) {
				pv.add(productList.get(i));
			}
		}
        pv.get(pId).setpMount(pv.get(pId).getpMount()+qty);
        pv.get(pId).setpTotalSale(pv.get(pId).getpTotalSale()+(qty*pv.get(pId).getpPrice()));
//		for (int i = 0; i < pv.size(); i++) {
//			if(pv.get(i).getpId() == pId) {
//	            pv.get(i).setpMount(pv.get(i).getpMount()+qty);
//	            pv.get(i).setpTotalSale(pv.get(i).getpTotalSale()+(qty*pv.get(i).getpPrice()));
//	         }
//		}
	}
	
	/**
	 * 게시판 입력
	 * 
	 * @author 유다연
	 */
	public boolean insertBoard(BoardVO bv) {
		return boardList.add(bv);
	}

	/**
	 * 게시물 타이틀 목록으로 출력
	 * 
	 * @author 유다연
	 */
	public List<BoardVO> printTitle() { // 조합은 뷰 자료만 넘기기 List<String
		return boardList;
	}

	/**
	 * 선택한 타이틀의 내용 출력
	 * 
	 * @param num
	 * @author 유다연
	 */
	public String printContent(int num) { //VO에 대한 내용 BoardVO반환, 아이디를 넘겨리
		String content = "";

		for (int i = 0; i < boardList.size(); i++) { 
			if (boardList.get(i).getbId() == num - 1) {
				content = boardList.get(i).getbContent() + "";
				break;
			}
		}
		
		return content;
	}

	/**
	 * 내가 작성한 게시물만 출력
	 * 
	 * @author 유다연
	 */
	public List<BoardVO> printMyBoard(String memId) { //List<BoardVO) 
		List<BoardVO> bv = new ArrayList<BoardVO>();
		for (int i = 0; i < boardList.size(); i++) {
			if (memId.equals(boardList.get(i).getMemId())||memId.equals("admin")) {
				BoardVO bb= boardList.get(i);
				bv.add(bb);
			}

		}
		return bv;
	}
	
	
	
	public boolean deleteBoard(String memId, int bId) { // HashMap 
		
		boolean result = false;
		for (int i = 0; i < boardList.size(); i++) {
			if ((boardList.get(i).getMemId().equals(memId)
					|| memId.equals("admin"))
					&& (boardList.get(i).getbId() == bId-1)) {
				boardList.remove(i);
				result=true;
			}
			
		}
		return result;
	}
	
	public List<StaffVO> staffPrint() {
		
		return staffList;
	}
	
	public  boolean staffRetireFinish(int sid){
		for(int i=0; i<staffList.size(); i++){
			if(sid==staffList.get(i).getsId()){
				staffList.remove(i);	
				break;
			}
		}
		return true;
	}
	
	
	/**
	 * 개별상품별로 매출합계 출력할 때 사용
	 * @author 유다연
	 * @return
	 */
	public List<ProductVO> productMoney(){
		return productList;
	}
	
	/**
	 * 카테고리별로 매출합계 출력할 때 사용
	 * @author 유다연
	 */
	public List<CategoryVO> categoryMoney(){
	      
	      for(int j=0;j<categoryList.size();j++){   
	         categoryList.get(j).setcTotalSale(0);
	      }
	      
	      for(int j=0;j<categoryList.size();j++){   
	         for(int i=0;i<productList.size();i++){
	            if(categoryList.get(j).getcId()==productList.get(i).getcId()){
	               categoryList.get(j).setcTotalSale(categoryList.get(j).getcTotalSale()+
	                     productList.get(i).getpTotalSale());
	            }
	         }
	      }
	      return categoryList;
	   }
	
	/**
	 * 판매중인 카테고리만 찾음
	 * 수정
	 * @author 김범휘
	 * @return 판매중인 카테고리
	 */
	public List<CategoryVO> getCsCategoryMenu() {
		List<CategoryVO> cv = new ArrayList<CategoryVO>();
		
		for (int i = 0; i < categoryList.size(); i++) {
			if(categoryList.get(i).getcRight() == 0) {
				cv.add(categoryList.get(i));
			}
		}
		return cv;
	}
	/**
	 * 판매중인 개별상품만 찾음
	 * 수정
	 * @author 김범휘
	 * @return 판매중인 개별상품
	 */
	public List<ProductVO> getCsProductMenu() {
		List<ProductVO> pv = new ArrayList<ProductVO>(); 
		
		for (int i = 0; i < productList.size(); i++) {
			if(productList.get(i).getpRight() == 0) {
				pv.add(productList.get(i));
			}
		}
		return pv;
	}
	
	
	public List<ReservationVO> roomPrint() {
		
		return reservationList;
	}
	public int payUse(Map<String, Object> map) {
		int pId = (int) map.get("pId");//인덱스번호
		int qty = (int) map.get("qty");//수량
		int cId = (int) map.get("cId");//카테고리번호
		
		List<ProductVO> pv = new ArrayList<ProductVO>();
		for (int i = 0; i < productList.size(); i++) {
			if(cId == productList.get(i).getcId() && productList.get(i).getpRight()==0) {
				pv.add(productList.get(i));
			}
		}
		int payPrice = pv.get(pId).getpPrice() * qty;
		
		
		return payPrice;
		
	}

	public String summary(Map<String, Object> map2) {
		int prodID = (int) map2.get("pId");
		int categoryId = (int) map2.get("cId");
		String prodName = null;
		
		
		List<ProductVO> pv = new ArrayList<ProductVO>();
		for (int i = 0; i < productList.size(); i++) {
			if(categoryId == productList.get(i).getcId() && productList.get(i).getpRight()==0) {
				
				pv.add(productList.get(i));
			}
		}
		
		prodName = pv.get(prodID).getpName();
		return prodName;
		
	}
	
	
	public boolean checkEmail(String email) {
		boolean result = false;
		for (int i = 0; i < memberList.size(); i++) {
			if (email.equals(memberList.get(i).getMemEmail())) {
				result = false;
				break;
			} else {
				result = true;
			}
		}
		return result;
	}

}

