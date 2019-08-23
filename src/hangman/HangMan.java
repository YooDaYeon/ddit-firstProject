package hangman;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HangMan {
	public String word() {
		List<String> wordList = new ArrayList<String>();
		wordList.add("apple");
		wordList.add("watermelon");
		wordList.add("orange");
		wordList.add("pineapple");
		wordList.add("grape");
		wordList.add("banana");
		wordList.add("strawberry");
		wordList.add("koreanmelon");
		wordList.add("dragonfruit");
		wordList.add("grapefruit");
		wordList.add("mango");
		wordList.add("peach");
		wordList.add("tomato");
		wordList.add("blueberry");
		wordList.add("cherry");
		wordList.add("pomegranate");
		wordList.add("pear");
		wordList.add("lemon");
		wordList.add("coconut");
		wordList.add("kiwi");
		
		int random = (int)(Math.random()*wordList.size());
		return wordList.get(random);
	}
	
	public void life2 () {
		System.out.println("┌───────────────┬─────────────");
		System.out.println("│               │");
		System.out.println("│            @@@@@@@@@@");
		System.out.println("│           (  ♥   ♥    )");
		System.out.println("│            │         │ ");
		System.out.println("│            │   ^     │");
		System.out.println("│            └─────────┘");
		System.out.println("│");
		System.out.println("│");
		System.out.println("│");
		System.out.println("│");
		System.out.println("│");
		System.out.println("│");
		System.out.println("├──────────────────────────────┐");
		System.out.println("│                              │");
		System.out.println("└──────────────────────────────┘");
		
	}
	public void life1 () {
		System.out.println("┌───────────────┬─────────────");
		System.out.println("│               │");
		System.out.println("│            @@@@@@@@@@");
		System.out.println("│           (  ♥   ♥    )");
		System.out.println("│            │         │ ");
		System.out.println("│            │   ^     │");
		System.out.println("│            └─────────┘");
		System.out.println("│                ─┼─");
		System.out.println("│");
		System.out.println("│");
		System.out.println("│");
		System.out.println("│");
		System.out.println("├──────────────────────────────┐");
		System.out.println("│                              │");
		System.out.println("└──────────────────────────────┘");
		
	}
	public void life0 () {
		System.out.println("┌───────────────┬─────────────");
		System.out.println("│               │");
		System.out.println("│            @@@@@@@@@@");
		System.out.println("│           (  ♥   ♥    )");
		System.out.println("│            │         │ ");
		System.out.println("│            │   ^     │");
		System.out.println("│            └─────────┘");
		System.out.println("│                ─┼─");
		System.out.println("│                ┌┴┐");
		System.out.println("├──────────────────────────────┐");
		System.out.println("│                              │");
		System.out.println("└──────────────────────────────┘");
	}

	
	
	
}
