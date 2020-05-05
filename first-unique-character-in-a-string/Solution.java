import java.util.HashMap;
import java.util.Map;

public class Solution {
	public int firstUniqChar(String s) {
		Map<Character, Integer> letterToCount = new HashMap<>();
		for (char letter : s.toCharArray()) {
			letterToCount.put(letter, letterToCount.getOrDefault(letter, 0) + 1);
		}

		for (int i = 0; i < s.length(); i++) {
			char letter = s.charAt(i);
			if (letterToCount.get(letter) == 1) {
				return i;
			}
		}

		return -1;
	}
}
