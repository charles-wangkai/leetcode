import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Solution {
	public boolean wordPattern(String pattern, String str) {
		String[] words = str.split(" ");
		if (words.length != pattern.length()) {
			return false;
		}

		Map<Character, String> letter2word = new HashMap<Character, String>();
		for (int i = 0; i < pattern.length(); i++) {
			char letter = pattern.charAt(i);
			String word = words[i];
			if (letter2word.containsKey(letter)) {
				if (!letter2word.get(letter).equals(word)) {
					return false;
				}
			} else {
				letter2word.put(letter, word);
			}
		}

		return letter2word.size() == new HashSet<String>(letter2word.values()).size();
	}
}
