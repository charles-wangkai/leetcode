class Solution {
	public int uniquePaths(int m, int n) {
		int[][] pathNums = new int[m][n];
		for (int i = 0; i < m; ++i) {
			for (int j = 0; j < n; ++j) {
				pathNums[i][j] = (i == 0 && j == 0) ? 1
						: (((i == 0) ? 0 : pathNums[i - 1][j]) + ((j == 0) ? 0 : pathNums[i][j - 1]));
			}
		}

		return pathNums[m - 1][n - 1];
	}
}
