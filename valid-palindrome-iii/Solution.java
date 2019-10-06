public class Solution {
	public boolean isValidPalindrome(String s, int k) {
		int n = s.length();

		int[][] maxLengths = new int[n][n];
		for (int i = 0; i < n; i++) {
			maxLengths[i][i] = 1;
		}

		for (int length = 2; length <= n; length++) {
			for (int beginIndex = 0; beginIndex + length <= n; beginIndex++) {
				int endIndex = beginIndex + length - 1;

				if (s.charAt(beginIndex) == s.charAt(endIndex)) {
					maxLengths[beginIndex][endIndex] = maxLengths[beginIndex + 1][endIndex - 1] + 2;
				} else {
					maxLengths[beginIndex][endIndex] = Math.max(maxLengths[beginIndex + 1][endIndex],
							maxLengths[beginIndex][endIndex - 1]);
				}
			}
		}

		return maxLengths[0][n - 1] + k >= n;
	}
}
