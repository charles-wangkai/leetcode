public class Solution {
	public int countNumbersWithUniqueDigits(int n) {
		int result = 0;
		for (int i = 0; i <= n; i++) {
			int part;
			if (i == 0) {
				part = 1;
			} else {
				part = 9;
				for (int j = 0; j < i - 1; j++) {
					part *= 9 - j;
				}
			}

			result += part;
		}
		return result;
	}
}
