import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {
	public List<String> commonChars(String[] A) {
		Map<Character, Integer> merged = buildLetterToCount(A[0]);
		for (int i = 1; i < A.length; i++) {
			Map<Character, Integer> letterToCount = buildLetterToCount(A[i]);

			for (char letter : merged.keySet()) {
				merged.put(letter, Math.min(merged.get(letter), letterToCount.getOrDefault(letter, 0)));
			}
		}

		List<String> result = new ArrayList<>();
		for (char letter : merged.keySet()) {
			for (int i = 0; i < merged.get(letter); i++) {
				result.add(String.valueOf(letter));
			}
		}
		return result;
	}

	Map<Character, Integer> buildLetterToCount(String s) {
		Map<Character, Integer> letterToCount = new HashMap<>();
		for (char letter : s.toCharArray()) {
			letterToCount.put(letter, letterToCount.getOrDefault(letter, 0) + 1);
		}
		return letterToCount;
	}
}
