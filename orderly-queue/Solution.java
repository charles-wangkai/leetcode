import java.util.Arrays;

public class Solution {
	public String orderlyQueue(String S, int K) {
		if (K > 1) {
			char[] letters = S.toCharArray();
			Arrays.sort(letters);
			return new String(letters);
		} else {
			String result = S;
			for (int i = 1; i < S.length(); i++) {
				String rotated = S.substring(i) + S.substring(0, i);
				if (rotated.compareTo(result) < 0) {
					result = rotated;
				}
			}
			return result;
		}
	}
}
