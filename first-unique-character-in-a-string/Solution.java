import java.util.HashMap;
import java.util.Map;

public class Solution {
	public int firstUniqChar(String s) {
		Map<Character, Integer> ch2count = new HashMap<Character, Integer>();
		for (int i = 0; i < s.length(); i++) {
			char ch = s.charAt(i);
			if (!ch2count.containsKey(ch)) {
				ch2count.put(ch, 0);
			}
			ch2count.put(ch, ch2count.get(ch) + 1);
		}

		for (int i = 0; i < s.length(); i++) {
			char ch = s.charAt(i);
			if (ch2count.containsKey(ch) && ch2count.get(ch) == 1) {
				return i;
			}
		}
		return -1;
	}
}
