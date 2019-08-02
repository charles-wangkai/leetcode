public class Solution {
	public int longestCommonSubsequence(String text1, String text2) {
		int length1 = text1.length();
		int length2 = text2.length();

		int[][] maxLengths = new int[length1 + 1][length2 + 1];
		for (int i = 0; i <= length1; i++) {
			for (int j = 0; j <= length2; j++) {
				if (i != 0 && j != 0) {
					if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
						maxLengths[i][j] = maxLengths[i - 1][j - 1] + 1;
					} else {
						maxLengths[i][j] = Math.max(maxLengths[i - 1][j], maxLengths[i][j - 1]);
					}
				}
			}
		}

		return maxLengths[length1][length2];
	}
}
