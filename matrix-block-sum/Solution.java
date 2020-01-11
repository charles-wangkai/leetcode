public class Solution {
	public int[][] matrixBlockSum(int[][] mat, int K) {
		int m = mat.length;
		int n = mat[0].length;

		int[][] prefixSums = new int[m][n];
		for (int r = 0; r < m; ++r) {
			for (int c = 0; c < n; ++c) {
				prefixSums[r][c] = getPrefixSum(prefixSums, r - 1, c) + getPrefixSum(prefixSums, r, c - 1)
						- getPrefixSum(prefixSums, r - 1, c - 1) + mat[r][c];
			}
		}

		int[][] result = new int[m][n];
		for (int r = 0; r < m; ++r) {
			for (int c = 0; c < n; ++c) {
				result[r][c] = getPrefixSum(prefixSums, r + K, c + K) - getPrefixSum(prefixSums, r - K - 1, c + K)
						- getPrefixSum(prefixSums, r + K, c - K - 1) + getPrefixSum(prefixSums, r - K - 1, c - K - 1);
			}
		}

		return result;
	}

	int getPrefixSum(int[][] prefixSums, int maxR, int maxC) {
		int r = Math.min(maxR, prefixSums.length - 1);
		int c = Math.min(maxC, prefixSums[0].length - 1);

		return (r < 0 || c < 0) ? 0 : prefixSums[r][c];
	}
}
