public class Solution {
	public int balancedStringSplit(String s) {
		int result = 0;
		int countL = 0;
		int countR = 0;
		for (char ch : s.toCharArray()) {
			if (ch == 'L') {
				countL++;
			} else {
				countR++;
			}

			if (countL == countR) {
				result++;
			}
		}

		return result;
	}
}
