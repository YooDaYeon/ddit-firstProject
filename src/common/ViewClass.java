package common;

import hangman.HangmanMain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import order.IOrderService;
import order.IOrderServiceImpl;
import product.IProductService;
import product.IProductServiceImpl;
import reservation.IReservationService;
import reservation.IReservationServiceImpl;
import staff.IStaffService;
import staff.IStaffServiceImpl;
import taja.TajaMain;
import Bingo.BingoMain;
import Omok.OmokMain;
import VO.BoardVO;
import VO.CategoryVO;
import VO.MemberVO;
import VO.ProductVO;
import VO.ReservationVO;
import VO.StaffVO;
import board.IBoardService;
import board.IBoardServiceImpl;
import category.ICategoryService;
import category.ICategoryServiceImpl;
import member.IMemberService;
import member.IMemberServiceImpl;

public class ViewClass {
	private IMemberService memberService = IMemberServiceImpl.getInstance();
	private ICategoryService categoryService = ICategoryServiceImpl.getInstance();
	private IBoardService boardService = IBoardServiceImpl.getInstance();
	private IOrderService orderService = IOrderServiceImpl.getInstance();
	private IProductService productService = IProductServiceImpl.getInstance();
	private IReservationService reservationService = IReservationServiceImpl.getInstance();
	private IStaffService staffService = IStaffServiceImpl.getInstance();
	private OmokMain omok = new OmokMain();
	private TajaMain taja = new TajaMain();
	private HangmanMain hangman= new HangmanMain();
	private BingoMain bingo =new BingoMain();
	
	private MemberVO loginMember = null;
	private Regular re = new Regular();
	private StaffVO staff = new StaffVO();
	private List<Object> receiptList = new ArrayList<Object>();
	private int payPrice;
	private int roomcnt=0;
	/** 시작메뉴 **/
	void startMenu() {

		while (true) {
			System.out.println("----------카페----------");
			System.out.println("1. 로그인");
			System.out.println("2. 회원가입");
			System.out.println("3. 오목게임");
			System.out.println("4. 행맨");
			System.out.println("5. 타자연습");
			System.out.println("6. 빙고");
			System.out.println("번호를 입력해주세요 :");
			Scanner sc = new Scanner(System.in);
			int num = 0;

			try {
				num = sc.nextInt();
			} catch (Exception e) {
				System.out.println("ERROR) 번호를 입력해주세요.");
				continue;
			}
			switch (num) {
			case 1:
				login();
				break;
			case 2:
				addPerson();
				break;
			case 3:
				omok.main();
				break;
			case 4:
				hangman.main();
				break;
			case 5:
				taja.main();
				break;
			case 6:
				bingo.main();
				break;

			default:
				System.out.println("ERROR) 1~6번을 선택할 수 있습니다.");
				break;
			}
		}

	}

	/** 로그인초기화면 **/
	// sql
	// getMemberInfo()
	private void login() {
		while (true) {
			System.out.println("----------로그인----------");
			String id = loginId();
			String pw = loginPw();

			Map<String, String> params = new HashMap<String, String>();
			params.put("memId", id);
			params.put("memPw", pw);

			loginMember = memberService.getMemberInfo(params);

			if (loginMember != null) {
				// 멤버의 계정권한에 따라 메뉴 호출
				switch (loginMember.getMemRight()) {
				case 0: // 관리자
					System.out.println(loginMember.getMemName() + "님 환영합니다!!\n");
					adminLogin();
					break;
				case 1: // 회원
					System.out.println(loginMember.getMemName() + "님 환영합니다!!\n");
					memberLogin();
					break;
				case 2: // 탈퇴 회원
					System.out.println("ERROR) 탈퇴한 회원입니다.");
					continue;
				}
				break;
			} else {
				System.out.println("ERROR) 해당 회원이 존재 하지 않습니다.");
				break;
			}
		}
	}

	/**
	 * 아이디 입력 메서드
	 * 
	 * @return
	 */

	private String loginId() {
		String id;
		Scanner sc = new Scanner(System.in);

		while (true) {
			System.out.print("아이디 입력 :");
			id = sc.next();

			return id;

		}
	}

	/**
	 * 비밀번호 입력메서드
	 * 
	 * @return
	 */

	private String loginPw() {
		System.out.print("비밀번호 입력 :");
		Scanner sc = new Scanner(System.in);
		String pw = sc.next();
		return pw;
	}

	/** 고객메인메뉴 **/
	private void memberLogin() {
		while (true) {
			Scanner sc = new Scanner(System.in);
			int num = 0;
			System.out.println("----------고객메뉴----------");
			System.out.println("1. 주문");
			System.out.println("2. 회원정보");
			System.out.println("3. 게시판");
			System.out.println("4. 방예약");
			System.out.println("5. 로그아웃");
			System.out.println("번호를 입력해주세요 :");
			try {
				num = sc.nextInt();
			} catch (Exception e) {
				System.out.println("ERROR) 번호를 입력해주세요.");
				continue;
			}
			switch (num) {
			case 1:
				order();
				break;
			case 2:
				customerCs();
				break;
			case 3:
				boardMenu();
				break;
			case 4:
				roomInsertDate();
				break;
			case 5:
				System.out.println("----------로그아웃----------");
				System.out.println();
				loginMember = null;
				return;

			default:
				System.out.println("ERROR) 1~5번을 선택할 수 있습니다.");
				break;
			}

		}

	}

	// 개별상품 선택해서 선택한 개별상품 아이디 가져옴.
	private int productMune(int cId) {
		while (true) {
			List<ProductVO> pv = productService.getCsProductMenu();
			
			List<CategoryVO> cv = categoryService.printCategory();
			
			for (int i = 0; i < cv.size(); i++) {
				if(cv.get(i).getcId() == cId && cv.get(i).getcUse() == false) {
					System.out.println("현재 "+cv.get(i).getcName()+"에 하위메뉴가 없습니다.");
					return 99;
				}
			}
			
			
			
			int a = 1;
			System.out.println("----------상품메뉴----------");
			for (int i = 0; i < pv.size(); i++) {
				if (pv.get(i).getcId() == cId) {
					System.out.println(a+ "." + pv.get(i).getpName()
							+ "(" + pv.get(i).getpPrice()+ ")");
					a++;
				}
			}
			System.out.println("상품번호를 선택해주세요 :");
			Scanner sc = new Scanner(System.in);
			int num2 = 0;

			try {
				num2 = sc.nextInt();
			} catch (Exception e) {
				System.out.println("ERROR) 번호를 입력해주세요.");
				continue;
			}
			return pv.get(num2-1).getpId();
		}
	}

	/** 주문 **/
	/** 주문 
	 * 수정
	 */
	private void order() {
		while (true) {
			System.out.println("----------카테고리메뉴----------");
			
			List<CategoryVO> cv = categoryService.getCsCategoryMenu();
			for (int i = 0; i < cv.size(); i++) {
				System.out.println((i+1)+ ". " + cv.get(i).getcName());
			}
			System.out.println("카테고리 번호를 선택해주세요 :");
			Scanner sc = new Scanner(System.in);
			int num = 0;

			try {
				num = sc.nextInt();
			} catch (Exception e) {
				System.out.println("ERROR) 번호를 입력해주세요.");
				continue;
			}
			
//			추가service.printProduct(cv.get(num-1).getcId());
			int cId = cv.get(num-1).getcId();//카테고리 아이디 
			// 개별상품 선택해서 선택한 개별상품 아이디 가져옴.
			int pId = productMune(cv.get(num-1).getcId());// 개별상품 인덱스 번호
			if(pId==99){
				return;
			}
			int qty = choiceProductQty();// 수량
			
			
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("pId", pId);
			map.put("qty", qty);
			map.put("cId", cId);
			payPrice = orderService.payUse(map);
			
			Map<String, Object> map2 = new HashMap<String, Object>();
			map2.put("pId", pId);
			map2.put("cId", cId);
			String prodName = orderService.summary(map2);
			
			
			if(loginMember.getMemMoney() < payPrice) {
				System.out.println("----------결제----------");
				System.out.println("잔액이 부족합니다.");
				System.out.println();
				payPrice = 0;
				return;
			}else {
				orderService.addpTotalSale(map);
				loginMember.setMemMoney(loginMember.getMemMoney()-payPrice);
				loginMember.setMemMileage(loginMember.getMemMileage()+(payPrice/100));
				System.out.println("----------결제----------");
				System.out.println(payPrice+"포인트가 결제되었습니다.");
				System.out.println(loginMember.getMemName()+"님의 마일리지가 "+(payPrice/100)+"점 추가되었습니다.");
				System.out.println("현재 마일리지는"+loginMember.getMemMileage()+"점입니다.");
				System.out.println(loginMember.getMemName()+"님의 잔여포인트는 "+
				loginMember.getMemMoney()+"포인트입니다.");
				System.out.println();
				
				
				
				Map<String, Object> asd = new HashMap<String, Object>();
				asd.put("mem_name", loginMember.getMemName()); //고객명
				asd.put("payPrice", payPrice); //주문가격
				asd.put("mem_mileage", loginMember.getMemMileage()); //마일리지
				asd.put("qty", qty); // 주문수량
				asd.put("prodName", prodName);//주문상품명
				
				memberService.createPdf(asd);
				memberService.sendMail(asd , loginMember);
				
				addOrder();
			}
			
//			List<Object> obj = pay(pId,qty);
			// 추가주문
			return;
//			pay(price, mount);
		}
	}

	private int choiceProductQty() {
		while (true) {
			Scanner sc = new Scanner(System.in);
			int mount = 0;
			System.out.println("주문할 수량을 입력해주요 : ");
			try {
				mount = sc.nextInt();
			} catch (Exception e) {
				System.out.println("ERROR) 번호를 입력해주세요.");
				continue;
			}
			return mount;
		}
	}

	/** 추가주문 선택화면 **/
	private void addOrder() {
		while (true) {
			Scanner sc = new Scanner(System.in);
			int order = 0;
			System.out.println("----------추가주문----------");
			System.out.println("추가 주문하시겠습니까?");
			System.out.println("1. YES");
			System.out.println("2. NO");
			try {
				order = sc.nextInt();
			} catch (Exception e) {
				System.out.println("ERROR) 번호를 입력해주세요.");
				continue;
			}
			switch (order) {
			case 1:
				order();
			case 2:
				payPrice = 0;
				return;
			default:
				System.out.println("ERROR)1~2번 중에서 입력해주세요");
				continue;
			}
		}
	}

	/** 결제 **/
//	private List<Object> pay(int pId, int qty){
//		List<ProductVO> pv = service.getCsProductMenu();
//		String prodName = pv.get(pId).getpName();//상품명
//		int prodQty = qty;//수량
//		int prodSale = pv.get(pId).getpPrice()*qty;//가격
////		List<Object> ob = new ArrayList<Object>();
//		receiptList.add(prodName);
//		receiptList.add(prodQty);
//		receiptList.add(prodSale);
//		return receiptList;
//	}
	
	
//	private void payPrint(List<Object> obj) {
//		// 영수증 처럼 출력
//		//회원이름, 상품명, 수량, 가격, 합계가격, 시간
//		System.out.println("고객명 : "+loginMember.getMemName());//회원이름
//		System.out.println("판매상품\t수량\t가격");
//		for (int i = 0; i < obj.size(); i++) {
//			System.out.print(obj.get(i)+"\t");
//			if(i==2||i==5||i==8||i==11)
//			System.out.println();
//		}
//		Date d = new Date();
//		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		System.out.println(df.format(d));
//		
//		for (int i = 0; i < receiptList.size(); i++) {
//			receiptList.remove(i);
//		}
//	}


	// ----------------------회원정보 확인----------------------------
	/**
	 * 회원정보 조회 메뉴
	 * 
	 * @author 김범휘
	 * **/
	private void customerCs() {
		while (true) {
			System.out.println("----------회원정보----------");
			System.out.println("1. 회원정보 조회 및 수정");
			System.out.println("2. 마일리지 확인");
			System.out.println("3. 포인트 충전");
			System.out.println("4. 뒤로가기");
			System.out.println("번호를 입력해주세요 : ");
			Scanner sc = new Scanner(System.in);
			int num = 0;
			try {
				num = sc.nextInt();
			} catch (Exception e) {
				System.out.println("ERROR) 번호를 입력해주세요.");
				continue;
			}
			switch (num) {
			case 1:
				customerMgMenu();
				break;
			case 2:
				customerMlCh();
				break;
			case 3:
				customerPointCr();
				break;
			case 4:
				return;
			default:
				System.out.println("ERROR) 1~4번을 선택할 수 있습니다.");
				continue;
			}
		}
	}

	/**
	 * 회원정보수정
	 * 
	 * @author 김범휘
	 */
	private void customerMgMenu() {
		while (true) {
			System.out.println("----------회원정보수정----------");
			System.out.println("1. 회원정보 열람");
			System.out.println("2. 이름 수정");
			System.out.println("3. 비밀먼호 수정");
			System.out.println("4. 생년월일 수정");
			System.out.println("5. 회원탈퇴");
			System.out.println("6. 뒤로가기");
			System.out.println("번호를 입력해주세요 : ");
			Scanner sc = new Scanner(System.in);
			int num = 0;
			try {
				num = sc.nextInt();
			} catch (Exception e) {
				System.out.println("ERROR) 번호를 입력해주세요.");
				continue;
			}
			switch (num) {
			case 1:
				customerInfo();
				break;
			case 2:
				customerNameMd();
				break;
			case 3:
				customerPwMd();
				break;
			case 4:
				customerBdMd();
				break;
			case 5:
				customerRetire();
				return;
			case 6:
				return;
			default:
				System.out.println("ERROR) 1~6번을 선택할 수 있습니다.");
				continue;
			}
		}

	}

	/**
	 * 본인 회원정보 출력
	 * 
	 * @author 김범휘
	 */
	private void customerInfo() {
		System.out.println("----------내 회원정보----------");
		System.out.println("아이디: " + loginMember.getMemId());
		System.out.println("비밀번호: " + loginMember.getMemPw());
		System.out.println("이름: " + loginMember.getMemName());
		System.out.println("구분: " + loginMember.getMemRight());
		System.out.println("생일: " + loginMember.getMemBir());
		System.out.println("포인트: " + loginMember.getMemMoney());
		System.out.println("마일리지: " + loginMember.getMemMileage());
		System.out.println();
	}

	/**
	 * 이름수정
	 * 
	 * @author 김범휘
	 */
	private void customerNameMd() {
		System.out.println("수정할 이름을 입력해주세요 :");
		Scanner sc = new Scanner(System.in);
		String name = sc.next();

		if (memberService.changeName(name, loginMember.getMemId())) {
			System.out.println("이름수정에 성공하였습니다.");
		} else {
			System.out.println("이름수정에 실패하였습니다.");
		}
		// service.changeName(name, loginMember.getMemId());
		// sql
		// 이름수정하는 메서드
		// void changeName(String name);
	}

	/**
	 * 비번수정
	 * 
	 * @author 김범휘
	 */
	private void customerPwMd() {
		System.out.println("수정할 비밀번호를 입력해주세요 : ");
		Scanner sc = new Scanner(System.in);
		String pw = sc.next();

		if (memberService.changePw(pw, loginMember.getMemId())) {
			System.out.println("비밀번호 수정에 성공하였습니다.");
		} else {
			System.out.println("비밀번호 수정에 실패하였습니다.");
		}
		// sql
		// 비번수정하는 메서드
		// void changePw(String pw);
	}

	/**
	 * 생일수정
	 * 
	 * @author 김범휘
	 */
	private void customerBdMd() {

		boolean result = false;
		Scanner sc = new Scanner(System.in);
		String bd;

		while (true) {
			System.out.println("수정할 생일을 입력해주세요 : ");
			System.out.println("형식 : YYMMDD");

			try {
				bd = sc.next();
			} catch (Exception e) {
				System.out.println("ERROR) 번호를 입력해주세요.");
				continue;
			}

			Pattern bdCh = Pattern
					.compile("\\d{2}(0[1-9]|1[0-2])(0[1-9]|1[0-9]|2[0-9]|3[0-1])");
			Matcher bdCh2 = bdCh.matcher(bd);
			result = bdCh2.matches();

			if (result == true) {
				if (memberService.changeBd(bd, loginMember.getMemId())) {
					System.out.println("생일 수정에 성공하였습니다.");
					return;
				} else {
					System.out.println("생일 수정에 실패하였습니다.");
					return;
				}
			}else{
				System.out.println("ERROR) 형식에 맞지 않습니다. 다시 입력하세요");
				continue;
			}
		}
		// sql
		// 생일수정하는 메서드
		// void changeBd(int bd);
	}

	/**
	 * 돈충전
	 * 
	 * @author 김범휘
	 */
	private void customerPointCr() {
		// sql
		// 돈충전하는 메서드
		// void changeMoney(int money);
		while (true) {
			System.out.println("충전할 금액을 입력해주세요 : ");
			Scanner sc = new Scanner(System.in);
			int money = 0;
			try {
				money = sc.nextInt();
			} catch (Exception e) {
				System.out.println("ERROR) 번호를 입력해주세요.");
				continue;
			}
			if (memberService.changeMoney(money, loginMember.getMemId())) {
				System.out.println("포인트 충전에 성공하였습니다.");
				return;
			} else {
				System.out.println("포인트 충전에 실패하였습니다.");
				return;
			}
		}
	}

	/**
	 * 마일리지 확인
	 * 
	 * @author 김범휘
	 */
	private void customerMlCh() {
		System.out.println(loginMember.getMemName() + "님의 현재 마일리지: "
				+ loginMember.getMemMileage());
	}

	/**
	 * 회원탈퇴
	 * 
	 * @author 김범휘
	 */
	private void customerRetire() {
		// sql
		// 탈퇴하는 메서드
		// void MemberRetire();
		if (memberService.MemberRetire(loginMember.getMemId())) {
			System.out.println("회원 탈퇴에 성공하였습니다.");
		} else {
			System.out.println("회원 탈퇴에 실패하였습니다.");
		}
		startMenu();
		return;
	}

	private void boardMenu() {
		while (true) {
			Scanner sc = new Scanner(System.in);
			int num = 0;
			System.out.println("----------게시판메뉴----------");
			System.out.println("1. 새 게시물 작성");
			System.out.println("2. 게시물 목록");
			System.out.println("3. 게시물 삭제");
			System.out.println("4. 뒤로가기");
			System.out.println("번호를 입력해주세요 : ");
			try {
				num = sc.nextInt();
			} catch (Exception e) {
				System.out.println("ERROR) 번호를 입력해주세요.");
				continue;
			}
			switch (num) {
			case 1:
				newTitle();
				break;
			case 2:
				printTotalBoard();
				break;
			case 3:
				deleteContent();
				break;
			case 4:
				return;

			default:
				System.out.println("ERROR) 1~3번을 선택할 수 있습니다.");
				continue;
			}
		}
	}

	private void printTitle() {
		printTitleNum();
		// sql
		// 전체 게시물 제목출력
		// void printBoard();

		// sql
		// 타이틀번호를 통해 게시물내용 출력
		// void printContent(int a);
	}

	private void roomInsertDate() {
		for (int i = 0; i < reservationService.roomPrint().size(); i++) {
			System.out.println("방 번호 : "+ reservationService.roomPrint().get(i).getReRoomNum()
					+ "\n스터디룸 예약불가 시간 : "
					+ reservationService.roomPrint().get(i).getStartDate() + " ~ "
					+ reservationService.roomPrint().get(i).getEndDate());
			System.out.println();
		}

		reCompare(insertRoomNum());

	}

	private int insertRoomNum() {

		while (true) {
			System.out.println("스터디룸 번호를 선택해주세요");
			Scanner sc = new Scanner(System.in);
			int num = 0;

			try {
				num = sc.nextInt();

			} catch (Exception e) {
				System.out.println("ERROR) 번호를 입력해주세요.");
				continue;
			}
			return num;
		}
	}
	
	Date startDate(){
		SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		Scanner sc = new Scanner(System.in);
		System.out.println("시작시간을 입력해주세요 ex) 06(시)");
		int startHour = sc.nextInt();
		Date startDate = new Date();
		startDate.setHours(startHour);
		startDate.setMinutes(00);
		startDate.setSeconds(00);
		return startDate;
		
	}
	
	Date endDate(){
		SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		Scanner sc = new Scanner(System.in);
		System.out.println("종료시간을 입력해주세요");
		int endHour = sc.nextInt();
		Date endDate = new Date();
		endDate.setHours(endHour);
		endDate.setMinutes(00);
		endDate.setSeconds(00);
		return endDate;
		
	}
	
	


	void reCompare(int num) {
		
		Date s = startDate();
		Date e1 = endDate();

		for (int i = 0; i < reservationService.roomPrint().size(); i++) {
			
			if (reservationService.roomPrint().get(i).getReRoomNum() == num) {
				ReservationVO re = new ReservationVO();

				SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

				for (int j = roomcnt; j < reservationService.roomPrint().size(); j++) {
					for (int k = roomcnt; k < reservationService.roomPrint().size(); k++) {

						try {
							if ((s.getTime() >= fm.parse(reservationService.roomPrint().get(k).getStartDate()).getTime() &&
									s.getTime() < fm.parse(reservationService.roomPrint().get(k).getEndDate()).getTime()) ||
									(e1.getTime() > fm.parse(reservationService.roomPrint().get(k).getStartDate()).getTime() &&
											e1.getTime() <= fm.parse(reservationService.roomPrint().get(k).getEndDate()).getTime())) {
								continue;
							} else {
								re.setStartDate(fm.format(s));
								re.setEndDate(fm.format(e1));
								re.setReRoomNum(num);
								reservationService.roomPrint().add(re);
								System.out.println("예약 완료");
								roomcnt++;
								return;
							}
						} catch (ParseException e) {
							e.printStackTrace();
							return;
						}
					}
					System.out.println("예약불가합니다.");
				}
			} else {
				System.out.println("방 번호가 일치하지 않습니다");
				return;
			}
		}

	}
	// -----------------------------관리자 메뉴

	private void adminLogin() {
		while (true) {
			Scanner sc = new Scanner(System.in);
			int num = 0;
			System.out.println("----------관리자 메뉴----------");
			System.out.println("1. 고객관리");
			System.out.println("2. 매장관리");
			System.out.println("3. 매출관리");
			System.out.println("4. 게시판관리");
			System.out.println("5. 직원관리");
			System.out.println("6. 로그아웃");
			System.out.println("번호를 입력해주세요 : ");
			try {
				num = sc.nextInt();
			} catch (Exception e) {
				System.out.println("ERROR) 번호를 입력해주세요.");
				continue;
			}
			switch (num) {
			case 1:
				customerMg();
				break;
			case 2:
				storeMgMenu();
				break;
			case 3:
				salesMgMenu();
				break;
			case 4:
				boardMenu();
				break;
			case 5:
				staffMg();
				break;
			case 6:
				loginMember = null;
				return;
			default:
				System.out.println("ERROR) 1번부터 6번 중에 눌러주세요.");
				break;
			}
		}
	}

	private void boardMgMenu() {
		while (true) {
			System.out.println("----------게시판메뉴----------");
			System.out.println("1. 새 게시물 작성");
			System.out.println("2. 게시물 목록");
			System.out.println("3. 게시물 삭제");
			System.out.println("번호를 입력해주세요 : ");
			Scanner sc = new Scanner(System.in);
			int num = 0;
			try {
				num = sc.nextInt();
			} catch (Exception e) {
				System.out.println("ERROR) 번호를 입력해주세요.");
				continue;
			}
			switch (num) {
			case 1:
				newTitle();
				break;
			case 2:
				printTitle();
				break;
			case 3:
				deleteContentAdmin();
				break;

			default:
				System.out.println("ERROR) 1~3번을 선택할 수 있습니다.");
				continue;
			}
		}
	}

	private void deleteContentAdmin() {

		// printBoard()
		addTitle();
		// deleteBoard();
		System.out.println("삭제완료");
	}

	private void staffMg() {
		staffService.staffPrint();
		for (int i = 0; i < staffService.staffPrint().size(); i++) {
			System.out
					.println("직원번호 : " + staffService.staffPrint().get(i).getsId());
			System.out.println("직원이름 : "
					+ staffService.staffPrint().get(i).getsName());
			System.out.println("출근시간 : "
					+ staffService.staffPrint().get(i).getsAttendance());
			System.out.println("퇴근시간 : "
					+ staffService.staffPrint().get(i).getsLeave());
			System.out.println("받은월급 : "
					+ staffService.staffPrint().get(i).getsPay());
			System.out.println();
		}
		staffMgMenu(insertStaffId());

	}

	private int insertStaffId() {
		System.out.println("직원의 번호를 선택해주세요");
		Scanner sc = new Scanner(System.in);
		int num = sc.nextInt();
		return num;
	}

	private void staffMgMenu(int staffId) {
		while (true) {
			Scanner sc = new Scanner(System.in);
			int num = 0;
			System.out.println("--------------직원관리 메뉴---------------");
			System.out.println("1. 급여관리");
			System.out.println("2. 퇴사처리");
			System.out.println("3. 뒤로가기");
			System.out.println("직원을 선택해주세요 : ");

			try {
				num = sc.nextInt();
			} catch (Exception e) {
				System.out.println("ERROR) 번호를 입력해주세요.");
				continue;
			}
			switch (num) {
			case 1:
				salaryMg(staffId);
				return;
			case 2:
				staffRetire(staffId);
				return;
			case 3:
				return;
			default:
				System.out.println("ERROR) 1~2번을 선택할 수 있습니다.");
				break;
			}

		}

	}

	private void staffRetire(int staffId) {
		if (staffService.staffRetireFinish(staffId)) {

			System.out.println("퇴사처리 되었습니다.");

		} else {

			System.out.println("퇴사처리를 실패하였습니다.");
		}
	}

	private void salaryMg(int staffId) {
		staffService.staffPrint().get(staffId).setsAttendance(insertHour());// 출근시간
		staffService.staffPrint().get(staffId).setsLeave(outHour());// 퇴근시간
		salaryGive(staffId);

	}

	private String insertHour() {
		while (true) {

			SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd-HH");

			Scanner sc = new Scanner(System.in);
			int hourOfDay = 0;
			System.out.println("출근시간을 입력해주세요 ex) 06(시)");
			try {
				hourOfDay = sc.nextInt();
			} catch (Exception e) {
				System.out.println("ERROR) 숫자를 입력해주세요.");
				continue;
			}

			Date date = new Date();

			date.setHours(hourOfDay);

			return fm.format(date);

		}
	}

	private String outHour() {
		while (true) {
			SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd-HH");

			Scanner sc = new Scanner(System.in);
			System.out.println("퇴근시간을 입력해주세요");
			int hourOfDay = sc.nextInt();

			Date date = new Date();

			date.setHours(hourOfDay);

			return fm.format(date);
		}
	}

	void salaryGive(int staffId) {
		String a1 = staffService.staffPrint().get(staffId).getsAttendance();
		String aa1 = a1.replace("-", "");
		String a2 = staffService.staffPrint().get(staffId).getsLeave();
		String aa2 = a2.replace("-", "");
		int b1 = Integer.parseInt(aa1);
		int b2 = Integer.parseInt(aa2);
		int result = (b2 - b1) * 8350;
		staffService.staffPrint().get(staffId).setsPay(result);
		System.out.println("급여 지급완료!   급여 :" + result);

	}

	// 회원가입
	// 서비스거쳐서 디비로
	// id받는 메서드(아이디받고, 중복체크하고, 정규식)
	// 다 private
	// pw받는 메서드(정규식), 이름받는 메서드 , ...
	// 멤버VO 객체 생성 => set메서드 이용해서 세팅(여기까지가 1단계)
	// while문 생각해서 쓰기

	/**
	 * 회원가입
	 */
	private void addPerson() {
		String id = addId();
		String pw = addPw();
		String name = addName();
		String bd = addBd();
//		String email= addEmail();

		MemberVO mv = new MemberVO();

		mv.setMemId(id);
		mv.setMemPw(pw);
		mv.setMemName(name);
		mv.setMemBir(bd);
		mv.setMemRight(1);
		mv.setMemUse(true);
		mv.setMemEmail(null);
		memberService.addPersonFinish(mv);
	}
	
	private String addEmail(){
		
		Scanner sc = new Scanner(System.in);
		String email;
		System.out.println("사용하실 email를 입력해주세요 : ");
		email = sc.next();
//		while(true){
//			
//			if (re.regEmail(email)) {
//				
//			} else {
//				System.out.println("ERROR) 형식에 맞춰 입력해주세요.");
//				continue;
//			}
//			
//			if(memberService.checkEmail(email)){
//				break;
//			} else {
//				System.out.println("ERROR) 중복된 email입니다.");
//				continue;
//			}
//	
//		
//		}
		return email;
		
	}

	/**
	 * 아이디입력
	 * 
	 * @return
	 */
	private String addId() {
		Scanner sc = new Scanner(System.in);
		String id;


		while (true) {
			System.out.println("사용하실 아이디를 입력해주세요 : ");
			System.out.println("(최소 4자리 이상의 영문자로 아이디를 만드세요)");
			id = sc.next();
			

			if (re.regId(id)) {
	
			} else {
				System.out.println("ERROR) 형식에 맞춰 입력해주세요.");
				continue;
			}
			
			if(memberService.checkId(id)){
				break;
			} else {
				System.out.println("ERROR) 중복된 ID입니다.");
				continue;
			}
	
		
		}
		return id;
	}

	// 이름입력
	private String addName() {
		while (true) {
			Scanner sc = new Scanner(System.in);
			String name;
			System.out.println("이름을 입력해주세요 : ");
			name = sc.next();
			return name;
			// 정규식
		}
	}

	// 비밀번호입력
	private String addPw() {
		while (true) {
			Scanner sc = new Scanner(System.in);
			String pw;
			System.out.println("사용하실 패스워드를 입력해주세요 : ");
			pw = sc.next();
			return pw;
			// 정규식
		}
	}

	// 생년월일입력
	private String addBd() {

		boolean result = false;
		Scanner sc = new Scanner(System.in);
		String bd;

		while (true) {

			System.out.println("생년월일을 입력해주세요 : ");
			System.out.println("(형식 : YYMMDD)");
			bd = sc.next();


			if (re.regBirth(bd)==false) {
				System.out.println("ERROR) 규정에 맞지 않습니다. 다시 입력하세요");
				continue;
			}
			return bd;
			// 정규식
		}
	}

	/**
	 * 고객아이디 입력
	 * 
	 * @author 김범휘
	 */
	// 고객관리
	private void customerMg() {
		System.out.println("----------고객아이디명단----------");
		System.out.println(Arrays.toString(memberService.MemberIdPrint()));

		System.out.println("관리할 고객아이디를 입력해주세요 : ");
		Scanner sc = new Scanner(System.in);
		String customerId = sc.next();
		adminCsMgMenu(customerId);
	}

	/**
	 * 관리자 고객관리 메뉴
	 * 
	 * @author 김범휘
	 */
	private void adminCsMgMenu(String customerId) {
		while (true) {
			System.out.println("1. 회원정보조회");
			System.out.println("2. 포인트 충전(환불)");
			System.out.println("3. 회원탈퇴처리");
			System.out.println("4. 뒤로가기");
			System.out.println("번호를 입력해주세요 : ");
			Scanner sc = new Scanner(System.in);
			int num = 0;

			try {
				num = sc.nextInt();
			} catch (Exception e) {
				System.out.println("ERROR) 번호를 입력해주세요.");
				continue;
			}

			switch (num) {
			case 1:
				System.out.println("[아이디, 비밀번호, 이름  , 생일 , 포인트 , 마일리지, 회원구분 ]");
				System.out.println(Arrays.toString(memberService
						.adminCsPrint(customerId)));
				System.out.println();
				break;
			case 2:
				adminCsPointUP(customerId);
				break;
			case 3:
				memberService.adminCsRetire(customerId);
				System.out.println("회원탈퇴 되었습니다.");
				break;
			case 4:
				return;
			default:
				System.out.println("ERROR) 1~4번을 선택할 수 있습니다.");
				break;
			}
		}
	}

	/**
	 * 선택한 회원 돈 충전
	 * 
	 * @author 김범휘
	 * @param customerId
	 */
	private void adminCsPointUP(String customerId) {
		while (true) {
			System.out.println("충전할 포인트를 입력해주세요 : ");
			Scanner sc = new Scanner(System.in);
			int money = 0;
			try {
				money = sc.nextInt();
			} catch (Exception e) {
				System.out.println("ERROR) 번호를 입력해주세요.");
				continue;
			}
			if (memberService.adminCsPointUP(money, customerId)) {
				System.out.println("포인트 충전에 성공하였습니다.");
				return;
			} else {
				System.out.println("포인트 충전에 실패하였습니다.");
				return;
			}
		}
	}

	/**
	 * 매장관리
	 * 
	 * @author 김범휘
	 */
	private void storeMgMenu() {
		while (true) {
			Scanner sc = new Scanner(System.in);
			int num = 0;
			System.out.println("----------매장관리----------");
			System.out.println("1. 카테고리관리");
			System.out.println("2. 개별상품관리");
			System.out.println("3. 뒤로가기");
			System.out.println("번호를 입력해주세요 : ");

			try {
				num = sc.nextInt();
			} catch (Exception e) {
				System.out.println("ERROR) 번호를 입력해주세요.");
				continue;
			}

			switch (num) {
			case 1:
				categoryMgMenu();
				break;
			case 2:
				productMgMenu();
				break;
			case 3:
				return;
			default:
				System.out.println("ERROR) 1~3번을 선택할 수 있습니다.");
				break;
			}
		}
	}

	// 매출관리
	private void salesMgMenu() {
		while (true) {
			Scanner sc = new Scanner(System.in);
			int num = 0;
			System.out.println("----------매출관리----------");
			System.out.println("1. 총매출");
			System.out.println("2. 카테고리별 매출");
			System.out.println("3. 개별 매출");
			System.out.println("4. 뒤로가기");
			System.out.println("번호를 입력해주세요 : ");
			try {
				num = sc.nextInt();
			} catch (Exception e) {
				System.out.println("ERROR) 번호를 입력해주세요.");
				continue;
			}
			switch (num) {
			case 1:
				totalSales();
				break;
			case 2:
				categorySales();
				break;
			case 3:
				productSales();
				break;
			case 4:
				return;
			default:
				System.out.println("ERROR) 1~4번을 선택할 수 있습니다.");
				continue;
			}

		}
	}

	/**
	 * 카테고리관리메뉴
	 * 
	 * @author 김범휘
	 */
	private void categoryMgMenu() {
		while (true) {
			Scanner sc = new Scanner(System.in);
			int num = 0;
			System.out.println("----------카테고리관리----------");
			System.out.println("1. 카테고리추가");
			System.out.println("2. 카테고리삭제");
			System.out.println("3. 카테고리조회");
			System.out.println("4. 뒤로가기");
			System.out.println("번호를 입력해주세요 : ");
			try {
				num = sc.nextInt();
			} catch (Exception e) {
				System.out.println("ERROR) 번호를 입력해주세요.");
				continue;
			}
			switch (num) {
			case 1:
				addCategory();
				break;
			case 2:
				deleteCategory();
				break;
			case 3:
				adminCategoryPrint();
				break;
			case 4:
				return;
			default:
				System.out.println("ERROR) 1~4번을 선택할 수 있습니다.");
				break;
			}
		}
	}

	/**
	 * 전체카테고리 세부정보 출력
	 * 
	 * @author 김범휘
	 */
	void adminCategoryPrint() {
		for (int i = 0; i < categoryService.printCategory().size(); i++) {
			System.out.println("카테고리ID : "
					+ categoryService.printCategory().get(i).getcId());
			System.out.println("카테고리명 : "
					+ categoryService.printCategory().get(i).getcName());
			System.out.println("카테고리구분 : "
					+ categoryService.printCategory().get(i).getcRight());
			System.out.println("하위메뉴 유무 : "
					+ categoryService.printCategory().get(i).getcUse());
			System.out.println();
		}
	}

	/**
	 * 개별상품관리메뉴
	 * 
	 * @author 김범휘
	 */
	private void productMgMenu() {
		while (true) {
			Scanner sc = new Scanner(System.in);
			int num = 0;
			System.out.println("----------개별상품관리----------");
			System.out.println("1. 개별상품추가");
			System.out.println("2. 개별상품삭제");
			System.out.println("3. 개별상품조회");
			System.out.println("4. 뒤로가기");
			System.out.println("번호를 입력해주세요 : ");
			try {
				num = sc.nextInt();
			} catch (Exception e) {
				System.out.println("ERROR) 번호를 입력해주세요.");
				continue;
			}
			switch (num) {
			case 1:
				addProduct();
				break;
			case 2:
				deleteProduct();
				break;
			case 3:
				productPrint();
				break;
			case 4:
				return;
			default:
				System.out.println("ERROR) 1~3번을 선택할 수 있습니다.");
				continue;
			}
		}
	}

	// 총매출
	private void totalSales() {
		// sql
		// 총매출 더해서 출력해주는 메서드
		// void totalMoney();
		int sum = 0;
		System.out.println("----------총매출----------");
		for (int i = 0; i < productService.productMoney().size(); i++) {
			sum += productService.productMoney().get(i).getpTotalSale();
		}
		System.out.println(sum);
		System.out.println();
	}

	// 카테고리별 매출
	private void categorySales() {
	      System.out.println("----------카테고리별 매출----------");
	      // sql
	      // 카테고리별 매출 더해서 출력해주는 메서드
	      // void categoryMoney();
	      System.out.println("제품명\t판매금액\t");
	      List<CategoryVO> cv = categoryService.categoryMoney();
	      for (int j = 0; j < cv.size(); j++) {
	         System.out.println(cv.get(j).getcName() + "\t"
	               + cv.get(j).getcTotalSale());
	      }
	      System.out.println();
	   }

	// 개별 매출
	private void productSales() {
		// sql
		// 개별매출 더해서 출력해주는 메서드
		// void productMoney();
		System.out.println("----------개별매출----------");
		System.out.println("제품명\t판매개수\t판매금액\t");
		for (int i = 0; i < productService.productMoney().size(); i++) {
			System.out.println(productService.productMoney().get(i).getpName() + "\t"
					+ productService.productMoney().get(i).getpMount() + "\t"
					+ productService.productMoney().get(i).getpTotalSale());
		}
		System.out.println();
	}

	/**
	 * 카테고리 추가
	 * 
	 * @author 김범휘
	 */
	private void addCategory() {
		String categoryName = categoryName();
		CategoryVO cv = new CategoryVO();

		cv.setcName(categoryName);

		if (categoryService.insertCategory(cv)) {
			System.out.println(categoryName + "카테고리가 추가되었습니다.");
		}
	}

	/**
	 * 카테고리 이름 받는 메서드
	 * 
	 * @author 김범휘
	 * @return 카테고리 이름
	 */
	private String categoryName() {
		System.out.println("카테고리의 이름을 입력하세요 : ");
		Scanner sc = new Scanner(System.in);
		String name = sc.next();
		return name;
	}

	/**
	 * 카테고리삭제
	 * 
	 * @author 김범휘
	 */
	private void deleteCategory() {
		System.out.println("----------카테고리목록----------");
		for (int i = 0; i < categoryService.printCategory().size(); i++) {
			System.out
					.printf(categoryService.printCategory().get(i).getcName() + "   ");
		}
		System.out.println();
		String categoryName = categoryName();
		if (categoryService.subMenuUseCh(categoryName)) {
			// sql
			// 카테고리 삭제하는 메서드
			// CategoryVO deleteCategory(CategoryVO cv);
			categoryService.deleteCategory(categoryName);
			System.out.println(categoryName + "카테고리를 삭제하였습니다.");
		}
	}

	/**
	 * 개별상품추가
	 * 
	 * @author 김범휘
	 */
	private void addProduct() {
		// 카테고리 메뉴 출력
		// printCategory();
		System.out.println("----------카테고리목록----------");
		for (int i = 0; i < categoryService.printCategory().size(); i++) {
			System.out
					.printf(categoryService.printCategory().get(i).getcName() + "   ");
		}
		System.out.println();

		// 카테고리명 입력받는 메서드
		String categoryName = InsertcategoryName();// 카테고리명(pk)
		String productName = addProductName(); // 추가할 상품 이름 입력받기
		int productPrice = addProductPrice();// 추가할 상품 가격 입력받기

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("cName", categoryName);
		params.put("pName", productName);
		params.put("pPrice", productPrice);

		if (productService.insertProduct(params)) {
			System.out.println(categoryName + "상품을 등록하였습니다.");
		}

		// sql
		// 상품 추가
		// productVO insertProduct(productVO pv);

	}

	/**
	 * 추가할 상품 이름 입력
	 * 
	 * @author 김범휘
	 * @return 상품 이름
	 */
	private String addProductName() {
		System.out.println("추가할 상품의 이름을 입력하세요 : ");
		Scanner sc = new Scanner(System.in);
		String name = sc.next();
		return name;
	}

	/**
	 * 추가할 상품 가격 입력
	 * 
	 * @author 김범휘
	 * @return 상품 가격
	 */
	private int addProductPrice() {
		while (true) {
			System.out.println("추가할 상품의 가격을 입력하세요 : ");
			Scanner sc = new Scanner(System.in);
			int price = 0;
			try {
				price = sc.nextInt();
			} catch (Exception e) {
				System.out.println("ERROR) 번호를 입력해주세요.");
				continue;
			}
			return price;
		}
	}

	/**
	 * 카테고리명 입력
	 * 
	 * @author 김범휘
	 * @return 카테고리명
	 */
	private String InsertcategoryName() {
		System.out.println("추가할 상품의 카테고리명을 입력해주요 : ");
		Scanner sc = new Scanner(System.in);
		String name = sc.next();
		return name;
	}

	/**
	 * 개별상품삭제
	 * 
	 * @author 김범휘
	 */
	private void deleteProduct() {
		productPrint();// 개별상품 정보 출력
		int productId = addProductId();// 삭제할 개별상품 아이디 입력받기
		if (productService.deleteProduct(productId)) {
			System.out.println("상품이 삭제되었습니다.");
		}
	}

	/**
	 * 삭제할 상품 번호 입력
	 * 
	 * @author 김범휘
	 * @return 삭제할 상품 번호
	 */
	private int addProductId() {
		while (true) {
			System.out.println("삭제할 상품의 아이디를 입력하세요 : ");
			Scanner sc = new Scanner(System.in);
			int productId = 0;
			try {
				productId = sc.nextInt();
			} catch (Exception e) {
				System.out.println("ERROR) 번호를 입력해주세요.");
				continue;
			}
			return productId;
		}
	}

	/**
	 * 개별상품 상세정보 전체 출력
	 * 
	 * @author 김범휘
	 */
	void productPrint() {
		productService.productPrint();// List<ProductVO>
		for (int i = 0; i < productService.productPrint().size(); i++) {
			System.out.println("상품ID :"
					+ productService.productPrint().get(i).getpId());
			System.out.println("상품이름 :"
					+ productService.productPrint().get(i).getpName());
			System.out.println("상품가격 :"
					+ productService.productPrint().get(i).getpPrice());
			System.out.println("상품구분 :"
					+ productService.productPrint().get(i).getpRight());
			System.out.println("카테고리ID :"
					+ productService.productPrint().get(i).getcId());
			System.out.println("카테고리이름 :"
					+ productService.productPrint().get(i).getcName());
			System.out.println();
		}
	}

	/**
	 * 새 게시물 추가
	 *
	 * @author 유다연
	 */
	static int a = 2;

	private void newTitle() {

		String title = addTitle();
		String content = addContent();
		int setbId = a++;

		BoardVO insertBoard = new BoardVO();
		insertBoard.setbId(setbId);
		insertBoard.setbTitle(title);
		insertBoard.setbContent(content);
		insertBoard.setMemId(loginMember.getMemId());
		boardService.insertBoard(insertBoard);
		// sql
		// 게시물 제목과 내용입려하는 메서드
		// BoardVO insertBoard(Board bv);
	}

	/**
	 * 추가할 게시물 제목 입력
	 *
	 */
	private String addTitle() {
		Scanner sc = new Scanner(System.in);
		System.out.println("제목을 입력하세요 : ");
		String title = sc.next();
		return title;
	}

	/**
	 * 추가할 게시물 내용 입력
	 * 
	 * @return
	 */
	private String addContent() {
		Scanner sc = new Scanner(System.in);
		System.out.println("내용을 입력하세요 : ");
		String content = sc.next();
		return content;
	}

	/**
	 * 게시물 제목과 내용 출력
	 * 
	 * @author 유다연
	 */
	private void printTotalBoard() {
		// sql
		// 전체 게시물 제목출력
		boardService.printTitle(); // boardList
		System.out.println("----------제목----------");
		for (int j = 0; j < boardService.printTitle().size(); j++) {
			System.out.println((boardService.printTitle().get(j).getbId() + 1)
					+ ". " +boardService.printTitle().get(j).getbTitle());
		}

		int titleNum = printTitleNum();

		// sql
		// 타이틀번호를 통해 게시물내용 출력
		// void printContent(int a);
		System.out.println("----------내용----------");
		System.out.println(boardService.printContent(titleNum));

	}

	/**
	 * 타이틀 숫자 입력 -- 타이틀에 없는 숫자 입력하면 처리
	 * 
	 * @author 유다연
	 * @return
	 */
	private int printTitleNum() {
		Scanner sc = new Scanner(System.in);
		System.out.println("타이틀숫자를 입력해주세요 : ");
		int num = sc.nextInt();
		return num;
	}

	/**
	 * 게시물 삭제
	 * 
	 * @author 유다연
	 */
	private void deleteContent() {

		if (loginMember.getMemId().equals("admin")) {
			for (int j = 0; j < boardService.printTitle().size(); j++) {
				System.out.println((boardService.printTitle().get(j).getbId() + 1)
						+ boardService.printTitle().get(j).getbTitle());
			}
		} else {
			for (int i = 0; i < boardService.printMyBoard(loginMember.getMemId())
					.size(); i++) {
				System.out
						.println((boardService.printMyBoard(loginMember.getMemId())
								.get(i).getbId() + 1)
								+ ". "
								+ boardService.printMyBoard(loginMember.getMemId())
										.get(i).getbTitle());
			}
		}

		int deleteNum = deleteBoardNum(); // 삭제할 게시물 번호 입력받음
		int deleteCheck = deleteCheck();
		switch (deleteCheck) {
		case 1:
			boardService.deleteBoard(loginMember.getMemId(), deleteNum);// 삭제 과정
			System.out.println("삭제되었습니다.");
			System.out.println("----------작성한게시물----------");

			for (int i = 0; i < boardService.printMyBoard(loginMember.getMemId())
					.size(); i++) {
				System.out
						.println((boardService.printMyBoard(loginMember.getMemId())
								.get(i).getbId() + 1)
								+ ". " +boardService.printMyBoard(loginMember.getMemId())
										.get(i).getbTitle());
			}

			return;
		case 2:
			return;

		}
	}

	/**
	 * 삭제유무 재확인
	 * 
	 * @author 유다연
	 * @return
	 */
	private int deleteCheck() {

		int num;
		while (true) {
			System.out.println("정말 삭제하시겠습니까?");
			Scanner sc = new Scanner(System.in);
			System.out.println("1. 삭제\t\t2. 취소");
			try {
				num = sc.nextInt();
			} catch (Exception e) {
				System.out.println("ERROR) 번호를 입력해주세요.");
				continue;
			}
			return num;
		}

	}

	private int deleteBoardNum() {

		int num;
		while (true) {
			Scanner sc = new Scanner(System.in);
			System.out.println("삭제할 게시물 번호를 입력하세요 : ");
			try {
				num = sc.nextInt();
			} catch (Exception e) {
				System.out.println("ERROR) 번호를 입력해주세요.");
				continue;
			}
			return num;
		}
	}
	
	
	
	
	
	
	
}
