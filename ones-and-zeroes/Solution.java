public class Solution {
	public int findMaxForm(String[] strs, int m, int n) {
		int[][] counts = new int[m + 1][n + 1];
		for (String str : strs) {
			int num0 = count(str, '0');
			int num1 = count(str, '1');

			int[][] nextCounts = new int[m + 1][n + 1];
			for (int i = 0; i <= m; i++) {
				for (int j = 0; j <= n; j++) {
					nextCounts[i][j] = 0;

					if (i - 1 >= 0) {
						nextCounts[i][j] = Math.max(counts[i][j], counts[i - 1][j]);
					}
					if (j - 1 >= 0) {
						nextCounts[i][j] = Math.max(counts[i][j], counts[i][j - 1]);
					}
					if (i - num0 >= 0 && j - num1 >= 0) {
						nextCounts[i][j] = Math.max(counts[i][j], counts[i - num0][j - num1] + 1);
					}
				}
			}
			counts = nextCounts;
		}
		return counts[m][n];
	}

	int count(String s, char c) {
		return (int) s.chars().filter(x -> x == c).count();
	}
}
