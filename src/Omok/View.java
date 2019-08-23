package Omok;

import java.util.Scanner;

public class View {

	public void Start() {

		Scanner sc = new Scanner(System.in);
		Pan ob = new Pan();
		int[][] pan = new int[21][21];
		ob.win(pan);
		ob.Pan(pan);

		int x, y, winner;
		int replay = 0;


		while (true) {
			while (replay == 0) {

				System.out.print("흑돌의 차례입니다.\n");
				System.out.print("행을 입력해주세요\n");
				x = sc.nextInt();
				System.out.print("열을 입력해주세요\n");
				y = sc.nextInt();

				if (x < 1 || y < 1 || x > 20 || y > 20) {
					System.out.print("판을 벗어났습니다.\n\n");
					continue;
				}
				if (pan[x][y] == 0) {
					System.out.printf("%d번째 줄  %d번째 칸에 놓으셨습니다.\n", x, y);
					pan[x][y] = 1;
					replay = 1;
					ob.Pan(pan);
				} else {
					System.out.print("\n\n이미 돌이 놓여져 있습니다!!\n\n");
					continue;
				}
			}
			winner = ob.win(pan);

			if (winner == 1) {
				System.out
						.print("\n\n\n\n\n\n\n\n================흑돌의 승리입니다! 축하합니다!===================\n\n\n\n\n\n\n\n");
				return;
			}

			while (replay == 1) {
				System.out.print("백돌의 차례입니다.\n");
				System.out.print("행을 입력해주세요\n");
				x = sc.nextInt();
				System.out.print("열을 입력해주세요\n");
				y = sc.nextInt();

				if (x < 1 || y < 1 || x > 20 || y > 20) {
					System.out.print("판을 벗어났습니다.\n\n");
					continue;
				}

				if (pan[x][y] == 0) {
					System.out.printf("%d번째 줄  %d번째 칸에 놓으셨습니다.\n", x, y);
					pan[x][y] = -1;
					replay = 0;

					ob.Pan(pan);
				} else {
					System.out.print("\n\n이미 돌이 놓여져 있습니다!!\n\n");
					continue;
				}
			}
			winner = ob.win(pan);
			if (winner == -1) {
				System.out
						.print("\n\n\n\n\n\n\n\n================백돌의 승리입니다! 축하합니다!.===================\n\n\n\n\n\n\n\n");
				return;
			}
		}

	}
}
