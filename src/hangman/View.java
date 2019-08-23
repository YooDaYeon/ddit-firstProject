package hangman;

import java.util.Scanner;

public class View {
	HangMan hm = new HangMan();
	String randomWord = hm.word();
	String[] findWord = new String[randomWord.length()];
	int life = 3;
	boolean a = false;

	public void startMenu() {
		System.out.println("[]    []     [][]     []    []     []]]]]     []      []     [][]     []    []");
		System.out.println("[]    []    []  []    []]]  []    []    []    []]]  [[[]    []  []    []]]  []");
		System.out.println("[][[]][]   [][[]][]   [] [] []    []          [] [[]] []   [][[]][]   [] [] []");
		System.out.println("[]    []   []    []   []  [[[]    []  [[[[    []  []  []   []    []   []  [[[]");
		System.out.println("[]    []   []    []   []    []     []]]]]     []      []   []    []   []    []");
		for (int i = 0; i < findWord.length; i++) {
			findWord[i] = "*";
		}
		System.out.println();
		System.out.println("GameStart!");
		System.out.println("주제는 과일입니다.");
		System.out.println();
		System.out.println("Life가 0점이 되면 GameOver!");
		System.out.println("Life : "+life);
		System.out.print("글자 : ");
		for (int i = 0; i < findWord.length; i++) {
			System.out.print(findWord[i]);
		}
		System.out.println();
		while(true){
			System.out.println();
			System.out.println("1. 알파벳 입력");
			System.out.println("2. 정답 입력");
	
			Scanner sc = new Scanner(System.in);
			int menuNum = 0;
			try {
				menuNum = sc.nextInt();
			} catch (Exception e) {
				System.out.println("ERROR) 숫자를 입력해주세요.");
				continue;
			}
			
			switch (menuNum) {
			case 1:
				insertWord();
				break;
			case 2:
				a = insertAnswer();
				break;
			default:
				System.out.println("ERROR) 1~2번 중에 입력해주세요.");
				continue;
			}
			
			if (life==0||a==true){
				return;
			}
		}
	}

	private boolean insertAnswer() {
		System.out.println("예상되는 정답을 입력해주세요 : ");
		Scanner sc = new Scanner(System.in);
		String answer = sc.next();
		if(answer.equals(randomWord)) {
			System.out.println("정답입니다.");
			return true;
		}else if(life==3) {
			life--;
			hm.life2();
			System.out.println("정답이 아닙니다. Life - 1");
			System.out.println("Life : "+life);
			System.out.print("글자 : ");
			for (int i = 0; i < findWord.length; i++) {
				System.out.print(findWord[i]);
			}
			System.out.println();
			return false;
		}else if(life==2){
			life--;
			hm.life1();
			System.out.println("정답이 아닙니다. Life - 1");
			System.out.println("Life : "+life);
			System.out.print("글자 : ");
			for (int i = 0; i < findWord.length; i++) {
				System.out.print(findWord[i]);
			}
			System.out.println();
			return false;
		}else if(life==1){
			life--;
			hm.life0();
			System.out.println("LIfe가 모두 소멸되었습니다.");
			System.out.println("GameOver");
			System.out.println("정답은 "+randomWord);
			return false;
		}
		return false;
	}

	private void insertWord() {
		System.out.println("예상되는 알파벳(소문자) 한개를 입력해주세요 : ");
		while(true){
			Scanner sc = new Scanner(System.in);
			String insertWord = sc.next();
			char word = insertWord.charAt(0);
			if(insertWord.length() > 1 || !('a'<=word&&word<='z')) {
				System.out.println("ERROR) 알파벳(소문자) 한개만 입력해주세요.");
				continue;
			}
			String[] checkWord = checkWord(insertWord);
			for (int i = 0; i < findWord.length; i++) {
				if(findWord[i].equals("*")){
					findWord[i] = checkWord[i];
				}
			}
			System.out.print("글자 : ");
			for (int i = 0; i < findWord.length; i++) {
				System.out.print(findWord[i]);
			}
			System.out.println();
			break;
		}
	}

	private String[] checkWord(String insertWord) {
		String[] checkWord = new String[findWord.length];
		for (int i = 0; i < checkWord.length; i++) {
			checkWord[i] = "*";
		}
		if(randomWord.contains(insertWord)) {
			System.out.println("일치하는 알파벳이 있습니다.");
			System.out.println("Life : "+life);
		}else if(life==3) {
			hm.life2();
			System.out.println("일치하는 알파벳이 없습니다. Life - 1");
			life--;
			System.out.println("Life : "+life);
		}else if(life==2) {
			hm.life1();
			System.out.println("일치하는 알파벳이 없습니다. Life - 1");
			life--;
			System.out.println("Life : "+life);
		}else if(life == 1) {
			hm.life0();
			life--;
			System.out.println("LIfe가 모두 소멸되었습니다.");
			System.out.println("GameOver");
			System.out.println("정답은 "+randomWord);
		}
		System.out.println();
		for (int i = 0; i < randomWord.length(); i++) {
			if(insertWord.charAt(0) == randomWord.charAt(i)) {
				checkWord[i] = findWord[i].replace('*', insertWord.charAt(0));
			}
		}
		return checkWord;
	}
		
		
		
		
		

	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
