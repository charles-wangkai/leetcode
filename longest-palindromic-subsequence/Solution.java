public class Solution {
	public int longestPalindromeSubseq(String s) {
		int length = s.length();

		int[][] maxLengths = new int[length][length + 1];
		for (int i = 0; i < length; i++) {
			maxLengths[i][i + 1] = 1;
		}

		for (int i = 2; i <= length; i++) {
			for (int j = 0; j + i <= length; j++) {
				if (s.charAt(j) == s.charAt(j + i - 1)) {
					maxLengths[j][j + i] = maxLengths[j + 1][j + i - 1] + 2;
				} else {
					maxLengths[j][j + i] = Math.max(Math.max(maxLengths[j + 1][j + i], maxLengths[j][j + i - 1]),
							maxLengths[j + 1][j + i - 1]);
				}
			}
		}

		return maxLengths[0][length];
	}
}
