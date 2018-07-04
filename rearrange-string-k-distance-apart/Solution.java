import java.util.HashMap;
import java.util.Map;

public class Solution {
	public String rearrangeString(String str, int k) {
		Map<Character, Integer> letter2remain = computeLetter2count(str);

		Map<Character, Integer> letter2lastIndex = new HashMap<Character, Integer>();
		for (char letter : letter2remain.keySet()) {
			letter2lastIndex.put(letter, -k);
		}

		StringBuilder result = new StringBuilder();
		for (int i = 0; i < str.length(); i++) {
			char chosen = 0;
			int chosenRemain = -1;

			for (char letter : letter2remain.keySet()) {
				if (i - letter2lastIndex.get(letter) < k) {
					continue;
				}

				int remain = letter2remain.get(letter);
				if (remain == 0) {
					continue;
				}

				if (remain > chosenRemain) {
					chosenRemain = remain;
					chosen = letter;
				}
			}

			if (chosenRemain < 0) {
				return "";
			}

			result.append(chosen);

			letter2remain.put(chosen, letter2remain.get(chosen) - 1);
			letter2lastIndex.put(chosen, i);
		}
		return result.toString();
	}

	Map<Character, Integer> computeLetter2count(String str) {
		Map<Character, Integer> letter2count = new HashMap<Character, Integer>();
		for (int i = 0; i < str.length(); i++) {
			char letter = str.charAt(i);
			if (!letter2count.containsKey(letter)) {
				letter2count.put(letter, 0);
			}
			letter2count.put(letter, letter2count.get(letter) + 1);
		}
		return letter2count;
	}
}