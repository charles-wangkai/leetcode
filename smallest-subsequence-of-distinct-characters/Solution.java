import java.util.HashMap;
import java.util.Map;

public class Solution {
	public String smallestSubsequence(String text) {
		if (text.isEmpty()) {
			return "";
		}

		Map<Character, Integer> letterToCount = new HashMap<>();
		for (char letter : text.toCharArray()) {
			letterToCount.put(letter, letterToCount.getOrDefault(letter, 0) + 1);
		}

		int selectedIndex = 0;
		for (int i = 0;; i++) {
			char letter = text.charAt(i);
			if (letter < text.charAt(selectedIndex)) {
				selectedIndex = i;
			}

			if (letterToCount.get(letter) == 1) {
				break;
			}

			letterToCount.put(letter, letterToCount.get(letter) - 1);
		}

		return String.format("%c%s", text.charAt(selectedIndex), smallestSubsequence(
				text.substring(selectedIndex + 1).replaceAll(String.valueOf(text.charAt(selectedIndex)), "")));
	}
}
