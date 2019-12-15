public class Solution {
	public int maxSideLength(int[][] mat, int threshold) {
		int m = mat.length;
		int n = mat[0].length;

		int[][] prefixSums = new int[m + 1][n + 1];
		for (int r = 1; r <= m; r++) {
			for (int c = 1; c <= n; c++) {
				prefixSums[r][c] = prefixSums[r - 1][c] + prefixSums[r][c - 1] - prefixSums[r - 1][c - 1]
						+ mat[r - 1][c - 1];
			}
		}

		for (int size = Math.min(m, n); size >= 1; size--) {
			for (int r = 1; r + size - 1 <= m; r++) {
				for (int c = 1; c + size - 1 <= n; c++) {
					if (computeSquareSum(prefixSums, r, c, size) <= threshold) {
						return size;
					}
				}
			}
		}

		return 0;
	}

	static int computeSquareSum(int[][] prefixSums, int r, int c, int size) {
		return prefixSums[r + size - 1][c + size - 1] - prefixSums[r - 1][c + size - 1]
				- prefixSums[r + size - 1][c - 1] + prefixSums[r - 1][c - 1];
	}
}
