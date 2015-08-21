import java.util.HashMap;
import java.util.Map;

public class PalindromePermutation {
	public boolean canPermutePalindrome(String s) {
		Map<Character, Integer> ch2count = new HashMap<Character, Integer>();
		for (char ch : s.toCharArray()) {
			if (!ch2count.containsKey(ch)) {
				ch2count.put(ch, 0);
			}
			ch2count.put(ch, ch2count.get(ch) + 1);
		}

		boolean hasOddCount = false;
		for (int count : ch2count.values()) {
			if (count % 2 != 0) {
				if (hasOddCount) {
					return false;
				}
				hasOddCount = true;
			}
		}
		return true;
	}
}
