import java.util.HashSet;
import java.util.Set;

public class Solution {
	public int longestRepeatingSubstring(String S) {
		int result = 0;
		int lower = 1;
		int upper = S.length();
		while (lower <= upper) {
			int middle = (lower + upper) / 2;

			if (hasRepeating(S, middle)) {
				result = middle;

				lower = middle + 1;
			} else {
				upper = middle - 1;
			}
		}
		return result;
	}

	static boolean hasRepeating(String S, int length) {
		Set<String> seen = new HashSet<>();
		for (int i = 0; i + length <= S.length(); i++) {
			String substring = S.substring(i, i + length);

			if (seen.contains(substring)) {
				return true;
			}
			seen.add(substring);
		}
		return false;
	}
}
