public class Solution {
	public int minDistance(String word1, String word2) {
		return word1.length() + word2.length() - findMaxCommonSubsequence(word1, word2) * 2;
	}

	int findMaxCommonSubsequence(String word1, String word2) {
		int[][] maxCommonSubsequences = new int[word1.length() + 1][word2.length() + 1];
		for (int i = 0; i <= word1.length(); i++) {
			for (int j = 0; j <= word2.length(); j++) {
				if (i == 0 || j == 0) {
					continue;
				}

				if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
					maxCommonSubsequences[i][j] = maxCommonSubsequences[i - 1][j - 1] + 1;
				} else {
					maxCommonSubsequences[i][j] = Math.max(maxCommonSubsequences[i - 1][j],
							maxCommonSubsequences[i][j - 1]);
				}
			}
		}
		return maxCommonSubsequences[word1.length()][word2.length()];
	}
}
