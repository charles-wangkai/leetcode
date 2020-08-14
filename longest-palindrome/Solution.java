import java.util.HashMap;
import java.util.Map;

class Solution {
	public int longestPalindrome(String s) {
		Map<Character, Integer> letterToCount = new HashMap<>();
		for (char letter : s.toCharArray()) {
			letterToCount.put(letter, letterToCount.getOrDefault(letter, 0) + 1);
		}

		return letterToCount.values().stream().mapToInt(count -> count / 2 * 2).sum()
				+ (letterToCount.values().stream().anyMatch(count -> count % 2 != 0) ? 1 : 0);
	}
}
