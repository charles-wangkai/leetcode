import java.util.HashMap;
import java.util.Map;

public class Solution {
	public int numKLenSubstrNoRepeats(String S, int K) {
		if (K > S.length()) {
			return 0;
		}

		Map<Character, Integer> letterToCount = new HashMap<>();
		for (int i = 0; i < K; i++) {
			increaseCount(letterToCount, S.charAt(i));
		}

		int result = (letterToCount.size() == K) ? 1 : 0;
		for (int i = K; i < S.length(); i++) {
			increaseCount(letterToCount, S.charAt(i));
			decreaseCount(letterToCount, S.charAt(i - K));

			if (letterToCount.size() == K) {
				result++;
			}
		}
		return result;
	}

	void increaseCount(Map<Character, Integer> letterToCount, char letter) {
		letterToCount.put(letter, letterToCount.getOrDefault(letter, 0) + 1);
	}

	void decreaseCount(Map<Character, Integer> letterToCount, char letter) {
		letterToCount.put(letter, letterToCount.get(letter) - 1);
		letterToCount.remove(letter, 0);
	}
}
