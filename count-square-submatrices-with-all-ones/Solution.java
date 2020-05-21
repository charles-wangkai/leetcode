public class Solution {
	public int countSquares(int[][] matrix) {
		int m = matrix.length;
		int n = matrix[0].length;

		int[][] sums = new int[m + 1][n + 1];
		for (int i = 1; i <= m; ++i) {
			for (int j = 1; j <= n; ++j) {
				sums[i][j] = sums[i - 1][j] + sums[i][j - 1] - sums[i - 1][j - 1] + matrix[i - 1][j - 1];
			}
		}

		int result = 0;
		for (int i = 1; i <= m; ++i) {
			for (int j = 1; j <= n; ++j) {
				for (int size = 1; i + size - 1 <= m && j + size - 1 <= n; ++size) {
					if (sums[i + size - 1][j + size - 1] - sums[i - 1][j + size - 1] - sums[i + size - 1][j - 1]
							+ sums[i - 1][j - 1] == size * size) {
						++result;
					}
				}
			}
		}

		return result;
	}
}
