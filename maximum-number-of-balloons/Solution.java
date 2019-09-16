import java.util.HashMap;
import java.util.Map;

public class Solution {
	public int maxNumberOfBalloons(String text) {
		Map<Character, Integer> targetLetterToCount = buildLetterToCount("balloon");
		Map<Character, Integer> letterToCount = buildLetterToCount(text);

		return targetLetterToCount.keySet().stream()
				.mapToInt(letter -> letterToCount.getOrDefault(letter, 0) / targetLetterToCount.get(letter)).min()
				.getAsInt();
	}

	Map<Character, Integer> buildLetterToCount(String s) {
		Map<Character, Integer> letterToCount = new HashMap<>();
		for (char letter : s.toCharArray()) {
			letterToCount.put(letter, letterToCount.getOrDefault(letter, 0) + 1);
		}

		return letterToCount;
	}
}
