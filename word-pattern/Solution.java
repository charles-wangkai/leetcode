import java.util.HashMap;
import java.util.Map;

class Solution {
	public boolean wordPattern(String pattern, String str) {
		String[] words = str.split(" ");
		if (words.length != pattern.length()) {
			return false;
		}

		Map<Character, String> letterToWord = new HashMap<>();
		for (int i = 0; i < pattern.length(); ++i) {
			char letter = pattern.charAt(i);
			String word = words[i];
			if (letterToWord.containsKey(letter)) {
				if (!letterToWord.get(letter).equals(word)) {
					return false;
				}
			} else {
				letterToWord.put(letter, word);
			}
		}

		return letterToWord.size() == letterToWord.values().stream().distinct().count();
	}
}
