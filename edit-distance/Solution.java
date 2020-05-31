public class Solution {
	public int minDistance(String word1, String word2) {
		int[][] distances = new int[word1.length() + 1][word2.length() + 1];
		for (int i = 0; i <= word1.length(); ++i) {
			for (int j = 0; j <= word2.length(); ++j) {
				if (i == 0) {
					distances[i][j] = j;
				} else if (j == 0) {
					distances[i][j] = i;
				} else {
					if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
						distances[i][j] = distances[i - 1][j - 1];
					} else {
						distances[i][j] = Math.min(Math.min(distances[i - 1][j], distances[i][j - 1]),
								distances[i - 1][j - 1]) + 1;
					}
				}
			}
		}

		return distances[word1.length()][word2.length()];
	}
}
