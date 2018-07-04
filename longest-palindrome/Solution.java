import java.util.HashMap;
import java.util.Map;

public class Solution {
	public int longestPalindrome(String s) {
		Map<Character, Integer> letter2count = new HashMap<Character, Integer>();
		for (char letter : s.toCharArray()) {
			if (!letter2count.containsKey(letter)) {
				letter2count.put(letter, 0);
			}
			letter2count.put(letter, letter2count.get(letter) + 1);
		}

		boolean odd = false;
		int length = 0;
		for (int count : letter2count.values()) {
			length += count / 2 * 2;
			if (!odd && count % 2 == 1) {
				length++;
				odd = true;
			}
		}
		return length;
	}
}
