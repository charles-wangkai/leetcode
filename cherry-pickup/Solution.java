public class Solution {
	public int cherryPickup(int[][] grid) {
		int N = grid.length;

		int[][] dp = init(N);
		dp[0][0] = grid[0][0];

		for (int k = 1; k <= 2 * (N - 1); k++) {
			int[][] curr = init(N);

			for (int i = Math.max(0, k - N + 1); i < N && i <= k; i++) {
				for (int j = Math.max(0, k - N + 1); j < N && j <= k; j++) {
					if (grid[i][k - i] < 0 || grid[j][k - j] < 0) {
						continue;
					}

					int cherries = dp[i][j];
					if (i > 0) {
						cherries = Math.max(cherries, dp[i - 1][j]);
					}
					if (j > 0) {
						cherries = Math.max(cherries, dp[i][j - 1]);
					}
					if (i > 0 && j > 0) {
						cherries = Math.max(cherries, dp[i - 1][j - 1]);
					}

					if (cherries < 0) {
						continue;
					}

					curr[i][j] = cherries + grid[i][k - i] + (i == j ? 0 : grid[j][k - j]);
				}
			}

			dp = curr;
		}

		return Math.max(0, dp[N - 1][N - 1]);
	}

	int[][] init(int size) {
		int[][] result = new int[size][size];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				result[i][j] = -1;
			}
		}
		return result;
	}
}
