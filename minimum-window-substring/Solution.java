import java.util.HashMap;
import java.util.Map;

public class Solution {
	public String minWindow(String S, String T) {
		Map<Character, Integer> letter2count = new HashMap<Character, Integer>();
		for (int i = 0; i < T.length(); i++) {
			char letter = T.charAt(i);
			if (!letter2count.containsKey(letter)) {
				letter2count.put(letter, 0);
			}
			letter2count.put(letter, letter2count.get(letter) + 1);
		}

		String result = null;
		int back = 0;
		for (int front = 0; front < S.length(); front++) {
			char frontLetter = S.charAt(front);
			if (!letter2count.containsKey(frontLetter)) {
				continue;
			}
			letter2count.put(frontLetter, letter2count.get(frontLetter) - 1);
			while (isAllCountsNonPositive(letter2count)) {
				if (result == null || front - back + 1 < result.length()) {
					result = S.substring(back, front + 1);
				}

				char backLetter = S.charAt(back);
				if (letter2count.containsKey(backLetter)) {
					letter2count.put(backLetter,
							letter2count.get(backLetter) + 1);
				}
				back++;
			}
		}

		return result == null ? "" : result;
	}

	boolean isAllCountsNonPositive(Map<Character, Integer> letter2count) {
		for (int count : letter2count.values()) {
			if (count > 0) {
				return false;
			}
		}
		return true;
	}
}
