import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Solution {
	public String frequencySort(String s) {
		Map<Character, Integer> letterToCount = new HashMap<>();
		for (char letter : s.toCharArray()) {
			letterToCount.put(letter, letterToCount.getOrDefault(letter, 0) + 1);
		}

		List<Character> sortedLetters = letterToCount.keySet().stream()
				.sorted((l1, l2) -> -Integer.compare(letterToCount.get(l1), letterToCount.get(l2)))
				.collect(Collectors.toList());

		StringBuilder result = new StringBuilder();
		for (char letter : sortedLetters) {
			for (int i = 0; i < letterToCount.get(letter); ++i) {
				result.append(letter);
			}
		}

		return result.toString();
	}
}
