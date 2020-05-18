import java.util.HashMap;
import java.util.Map;

public class Solution {
	public boolean checkInclusion(String s1, String s2) {
		if (s2.length() < s1.length()) {
			return false;
		}

		Map<Character, Integer> letterToCount = new HashMap<>();
		s1.chars().forEach(ch -> updateLetterToCount(letterToCount, (char) ch, -1));

		for (int i = 0; i < s1.length() - 1; i++) {
			updateLetterToCount(letterToCount, s2.charAt(i), 1);
		}

		for (int i = s1.length() - 1; i < s2.length(); i++) {
			updateLetterToCount(letterToCount, s2.charAt(i), 1);

			if (letterToCount.isEmpty()) {
				return true;
			}

			updateLetterToCount(letterToCount, s2.charAt(i - s1.length() + 1), -1);
		}

		return false;
	}

	void updateLetterToCount(Map<Character, Integer> letterToCount, char letter, int delta) {
		letterToCount.put(letter, letterToCount.getOrDefault(letter, 0) + delta);
		letterToCount.remove(letter, 0);
	}
}
