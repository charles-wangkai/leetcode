import java.util.HashMap;
import java.util.Map;

public class Solution {
	public String customSortString(String S, String T) {
		Map<Character, Integer> letter2count = new HashMap<Character, Integer>();
		for (char letter : T.toCharArray()) {
			letter2count.put(letter, letter2count.getOrDefault(letter, 0) + 1);
		}

		StringBuilder result = new StringBuilder();
		for (char letter : S.toCharArray()) {
			if (letter2count.containsKey(letter)) {
				for (int i = 0; i < letter2count.get(letter); i++) {
					result.append(letter);
				}
			}
		}

		for (char letter : letter2count.keySet()) {
			if (S.indexOf(letter) < 0) {
				for (int i = 0; i < letter2count.get(letter); i++) {
					result.append(letter);
				}
			}
		}

		return result.toString();
	}
}
