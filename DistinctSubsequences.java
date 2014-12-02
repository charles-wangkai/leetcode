public class DistinctSubsequences {
	public int numDistinct(String S, String T) {
		int lengthS = S.length();
		int lengthT = T.length();
		int[][] ways = new int[lengthS + 1][lengthT + 1];
		for (int i = 0; i <= lengthS; i++) {
			for (int j = 0; j <= lengthT; j++) {
				if (j == 0) {
					ways[i][j] = 1;
				} else if (i == 0) {
					ways[i][j] = 0;
				} else {
					ways[i][j] = ways[i - 1][j]
							+ (S.charAt(i - 1) == T.charAt(j - 1) ? ways[i - 1][j - 1]
									: 0);
				}
			}
		}
		return ways[lengthS][lengthT];
	}
}
