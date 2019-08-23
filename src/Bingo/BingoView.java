package Bingo;

import java.util.Scanner;

public class BingoView {

	/**
	 * 빙고게임 시작
	 */
	BingoView() {
		System.out.println("빙고 게임을 시작합니다");
		System.out.println("세로, 가로, 대각선에서 총 3개의 줄이 완성되면 Bingo가 됩니다");
		System.out.println("재미있는 빙고 놀이 하세요");

		while (true) {
			System.out.println("\n1. 게임 시작   2. 게임 종료");
			
			Scanner sc = new Scanner(System.in);
			int num = 0;

			try {
				num = sc.nextInt();
			} catch (Exception e) {
				System.out.println("숫자를 입력하세요");
				new BingoView();
			}

			switch (num) {
			case 1:
				startGame();
				return;
			case 2:
				System.out.println("게임이 종료되었습니다");
				return;
			default:
				System.out.println("1~2까지의 숫자만 입력하세요");
				break;

			}

		}

	}
	
	
	private void startGame(){
		
		int[] num = new int[16];
		
		num = insertNum(); // 사용자가 빙고게임에 사용할 숫자 입력
		System.out.println();
		System.out.println("사용자 빙고판");
		printBoard(num); // 사용자가 가진 빙고판 출력
		System.out.println("\n----------------------------");
		
		System.out.println("\n컴퓨터 빙고판");
		int[] comNum = createComBoard(); // 컴퓨터의 숫자 생성
		printBoard(comNum); // 컴퓨터가 가진 숫자 출력

		System.out.println("\n===============================");
		System.out.println("빙고를 시작합니다");
		bingoGame(num, comNum);
		System.out.println("게임이 종료되었습니다");

			
	}
	
	
	
	/**
	 * 빙고게임에 사용할 숫자 입력
	 */
	private int[] insertNum() {
		Scanner sc = new Scanner(System.in);

		System.out.println("1~16까지의 숫자 중 중복되지 않게 16개 숫자를 입력하세요");

		int[] num = new int[16];

		for (int i = 0; i < 16; i++) {

			try {
				System.out.println(i + 1 + "번 째 숫자를 입력하세요");
				num[i] = sc.nextInt();

				if (num[i] > 16 || num[i] == 0) {
					System.out.println("1~16까지의 숫자만 입력하세요");
					num[i] = 0;
					i--;
					continue;
				}

				for (int j = 0; j < i; j++) {
					if (num[j] == num[i]) {
						System.out.println("중복된 숫자입니다. 다시 입력하세요");
						num[i] = 0;
						i--;
						break;
					}
				}

			} catch (Exception e) {
				System.out.println("숫자를 입력하세요");
				continue;
			}

		}
		return num;
	
	}

	/**
	 * 빙고판 출력
	 * 
	 * @param num
	 */
	private void printBoard(int[] num) {

		for (int i = 0; i < num.length; i++) {

			if (i % 4 == 0) {
				System.out.println();
			}
			System.out.print(num[i] + "\t");
		}
	}

	/**
	 * 빙고게임 시 숫자 선택
	 * 
	 * @return
	 */
	private int chooseNum() {

		System.out.println("숫자를 선택하세요");
		Scanner sc = new Scanner(System.in);
		int choose = sc.nextInt();

		return choose;
	}

	/**
	 * 빙고게임
	 * 
	 * @param userNum
	 * @param comNum
	 */
	private void bingoGame(int[] userNum, int[] comNum) {

		while (true) {
			int n = chooseNum(); // 숫자 선택
			n = overlapNumCheck("user", n); // 불렀던 숫자를 다시 불렀는지 확인하고 불렀으면 다시 체크해줌
			changeNum(userNum,comNum,n);

			System.out.println("\n사용자 빙고판");
			printBoard(userNum);
			System.out.println("\n----------------------------");
			System.out.println("\n컴퓨터 빙고판");
			printBoard(comNum);
			System.out.println("\n----------------------------");

			// 3줄 빙고 됐는지 체크
			if (completeBingoCheck(userNum, comNum) == true) {
				return;
			}
			int comn = (int) (Math.random() * 16 + 1); // 컴퓨터는 랜덤으로 숫자 부름
			comn = overlapNumCheck("com", comn); // 불렀던 숫자를 다시 불렀는지 확인

			System.out.println("\n****************************");
			System.out.println("컴퓨터 숫자 " + comn + "입력");
			changeNum(userNum,comNum,comn);

			System.out.println("\n사용자 빙고판");
			printBoard(userNum);
			System.out.println("\n----------------------------");
			System.out.println("\n컴퓨터 빙고판");
			printBoard(comNum);
			System.out.println("\n----------------------------");

			if (completeBingoCheck(userNum, comNum) == true) {
				return;
			}

		}

	}
	
	/**
	 * 게임에서 선택한 숫자를 0으로 바꿔서 사용한 것으로 보이게 변경
	 * @param userNum
	 * @param comNum
	 * @param n
	 */
	private void changeNum(int[] userNum, int[] comNum, int n){
		
		for (int i = 0; i < userNum.length; i++) { // 사용자가 입력한 값을 0으로 변환
			int u = 0;
			int c = 0;

			if (userNum[i] == n) {
				userNum[i] = 0;
				u++;
			}
			if (comNum[i] == n) {
				comNum[i] = 0;
				c++;
			}

			if (u == 1 && c == 1) {
				break;
			}
		}	
		
	}
	
	

	/**
	 * 컴퓨터의 빙고판 생성
	 * 
	 * @return
	 */
	private int[] createComBoard() {
		int n = 0;
		int[] comNum = new int[16];

		for (int i = 0; i < comNum.length; i++) {
			comNum[i] = (int) (Math.random() * 16 + 1);
			for (int j = 0; j < i; j++) {
				if (comNum[j] == comNum[i]) {
					comNum[i] = 0;
					i--;
					break;
				}

			}

		}

		return comNum;
	}

	/**
	 * 한줄 빙고 체크
	 * 
	 * @param num
	 * @return
	 */
	private int bingoCheck(int[] num) {
		int bingo = 0;

		if (num[0] == 0 && num[1] == 0 && num[2] == 0 && num[3] == 0) {
			bingo++;
		}

		if (num[4] == 0 && num[5] == 0 && num[6] == 0 && num[7] == 0) {
			bingo++;
		}

		if (num[8] == 0 && num[9] == 0 && num[10] == 0 && num[11] == 0) {
			bingo++;
		}

		if (num[12] == 0 && num[13] == 0 && num[14] == 0 && num[15] == 0) {
			bingo++;
		}

		if (num[0] == 0 && num[4] == 0 && num[8] == 0 && num[12] == 0) {
			bingo++;
		}

		if (num[1] == 0 && num[5] == 0 && num[9] == 0 && num[13] == 0) {
			bingo++;
		}

		if (num[2] == 0 && num[6] == 0 && num[10] == 0 && num[14] == 0) {
			bingo++;
		}

		if (num[3] == 0 && num[7] == 0 && num[11] == 0 && num[15] == 0) {
			bingo++;
		}

		if (num[0] == 0 && num[5] == 0 && num[10] == 0 && num[15] == 0) {
			bingo++;
		}

		if (num[3] == 0 && num[6] == 0 && num[9] == 0 && num[12] == 0) {
			bingo++;
		}

		return bingo;
	}

	/**
	 * 3줄 빙고로 완료 되었는지 체크
	 * 
	 * @param userNum
	 * @param comNum
	 * @return
	 */
	private boolean completeBingoCheck(int[] userNum, int[] comNum) {

		boolean complete = false;

		if (bingoCheck(userNum) == 3 || bingoCheck(comNum) == 3) { // 빙고!!!
			if (bingoCheck(userNum) == 3) {
				System.out.println("\n사용자가 빙고다!! 아싸바리용가리치킨너겟!!!!!");
				if(bingoCheck(comNum) == 3){
					System.out.println("\n컴퓨터가 빙고다!! 아싸바리용가리치킨너겟!!!!!");
				}
				complete = true;
			} else if (bingoCheck(comNum) == 3) {
				System.out.println("\n컴퓨터가 빙고다!! 아싸바리용가리치킨너겟!!!!!");
				complete = true;
			}
		}
		return complete;

	}

	/**
	 * 사용한 숫자를 중복으로 부르면 다시 부르도록
	 */
	int a = 0;
	int[] useNum = new int[16];// 쓴 숫자는 중복되지 않도록 하기위해 모아두는 곳
	private int overlapNumCheck(String who, int n) {

		if (who == "user") {
			for (int i = 0; i < useNum.length; i++) {
				if (n == useNum[i]) {
					System.out.println("이미 사용한 숫자입니다. 다시 입력하세요");
					n = chooseNum();
					i = -1;
					continue;
				}
			}
		} else if (who == "com") {
			for (int i = 0; i < useNum.length; i++) {
				if (n == useNum[i]) {
					n = (int) (Math.random() * 16 + 1);
					i = -1; // 다시 처음부터 돌리면서 사용한 숫자 있나 보기 위해 사용
					continue;
				}
			}
		}

		useNum[a++] = n; // 사용한 숫자 저장

		return n;
	}

}
