import java.util.HashMap;
import java.util.Map;

public class LongestSubstringWithAtLeastKRepeatingCharacters {
	public int longestSubstring(String s, int k) {
		if (s.isEmpty()) {
			return 0;
		}

		Map<Character, Integer> letter2count = new HashMap<Character, Integer>();
		for (char letter : s.toCharArray()) {
			if (!letter2count.containsKey(letter)) {
				letter2count.put(letter, 0);
			}
			letter2count.put(letter, letter2count.get(letter) + 1);
		}

		int result = 0;
		int beginIndex = 0;
		for (int i = 0; i <= s.length(); i++) {
			if (i == s.length() || letter2count.get(s.charAt(i)) < k) {
				result = Math.max(result, (i == s.length() && beginIndex == 0) ? s.length()
						: longestSubstring(s.substring(beginIndex, i), k));
				beginIndex = i + 1;
			}
		}
		return result;
	}
}
