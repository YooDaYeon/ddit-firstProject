package taja;

import java.util.ArrayList;
import java.util.List;

public class Taja {
	String insertSentence() {
		List<String> SentenceList = new ArrayList<String>();
		SentenceList.add("If you make a mistake, if you get all tangled up, you just tango on.");
		SentenceList.add("You always did look pretty, just pretty nigh good enough to eat.");
		SentenceList.add("If you do not love me, I love you enough for both.");
		SentenceList.add("Love means never having to say you're sorry.");
		SentenceList.add("It's awful not to be loved, it's the worst thing in the world.");
		SentenceList.add("Love is touch. Touch is love. Love is reaching, reaching love.");
		SentenceList.add("Love is real Real is love. Love is feeling, feeling love. Love is wanting to be loved.");
		SentenceList.add("Love and reason do not go together.");
		int random = (int)(Math.random()*8);
		return SentenceList.get(random);
	}
}
