import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class Solution {
	public int calculateTime(String keyboard, String word) {
		Map<Character, Integer> letterToIndex = new HashMap<>();
		for (int i = 0; i < keyboard.length(); i++) {
			letterToIndex.put(keyboard.charAt(i), i);
		}

		return IntStream.range(0, word.length())
				.map(i -> Math.abs(
						letterToIndex.get(word.charAt(i)) - ((i == 0) ? 0 : letterToIndex.get(word.charAt(i - 1)))))
				.sum();
	}
}
