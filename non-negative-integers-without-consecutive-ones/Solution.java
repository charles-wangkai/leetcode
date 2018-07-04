import java.util.HashMap;
import java.util.Map;

public class Solution {
	Map<String, Integer> cache = new HashMap<String, Integer>();

	public int findIntegers(int num) {
		return search(Integer.toBinaryString(num));
	}

	int search(String numStr) {
		if (numStr.length() == 0) {
			return 1;
		}
		if (numStr.length() == 1) {
			return Integer.parseInt(numStr) + 1;
		}

		if (numStr.charAt(0) == '0') {
			return search(numStr.substring(1));
		}

		if (cache.containsKey(numStr)) {
			return cache.get(numStr);
		}

		int result = search(repeat('1', numStr.length() - 1))
				+ search((numStr.charAt(1) == '0') ? numStr.substring(2) : repeat('1', numStr.length() - 2));
		cache.put(numStr, result);
		return result;
	}

	String repeat(char digit, int count) {
		StringBuilder result = new StringBuilder();
		for (int i = 0; i < count; i++) {
			result.append(digit);
		}
		return result.toString();
	}
}
