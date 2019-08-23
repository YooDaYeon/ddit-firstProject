package taja;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class View {
	void stratMenu() {
		Taja tj = new Taja();
		while(true){
			System.out.println("타자연습 ^^;");
			String randomSentence = tj.insertSentence();
			int sentenceLength = randomSentence.length(); //전체 글자수(공백포함)
			Map<String, Object> params = null;
			System.out.println("1을 입력하면 시작됩니다.");
			Scanner sc = new Scanner(System.in);
			int num = 0; 
			try {
				num = sc.nextInt();
			} catch (Exception e) {
				System.out.println("ERROR) 1만 입력 가능합니다.");
				continue;
			}
			switch (num) {
			case 1:
				System.out.println(randomSentence);
				params = insertSentence();
				break;

			default:
				System.out.println("ERROR) 1만 입력가능합니다.");
				continue;
			}
			String insertSentence = (String) params.get("문장");
			double time = (double) params.get("시간");
			long timetime = Math.round(time);
			int count = checkSentence(randomSentence,insertSentence); // 틀린개수
//		타수 : (전체 글자수-오타수)*60/걸린시간(초)
//		정확도 : (전체 글자수 - 오타수)/전체글자수*100
			System.out.println("경과시간 : "+timetime+ "초");
			System.out.println("타수 : "+(randomSentence.length()-count)*60/timetime);
			System.out.println("정확도 : "+Math.round((double)(randomSentence.length()-count)/randomSentence.length()*100));
			return;
		}
		
	}
	private int checkSentence(String randomSentence, String insertSentence) {
		int count = 0;
		for (int i = 0; i < randomSentence.length(); i++) {
			try {
				if(randomSentence.charAt(i) != insertSentence.charAt(i)) {
					count++;
				}
			} catch (StringIndexOutOfBoundsException e) {
				count++;
			}
		}
		return count;
	}
	private Map<String, Object> insertSentence() {
		Scanner sc = new Scanner(System.in);
		double start = System.currentTimeMillis(); // 1/1000의 초
		String insertSentence = sc.nextLine();
		double end = System.currentTimeMillis();
		double time = (end - start)/1000;
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("문장", insertSentence);
		params.put("시간", time);
		return params;
	}
}
