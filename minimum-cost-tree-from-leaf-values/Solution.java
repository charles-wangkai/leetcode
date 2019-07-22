public class Solution {
	public int mctFromLeafValues(int[] arr) {
		int n = arr.length;
		int[][] rangeMaxs = new int[n][n];
		for (int i = 0; i < n; i++) {
			int rangeMax = -1;
			for (int j = i; j < n; j++) {
				rangeMax = Math.max(rangeMax, arr[j]);
				rangeMaxs[i][j] = rangeMax;
			}
		}

		int[][] nonLeafSums = new int[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				nonLeafSums[i][j] = Integer.MAX_VALUE;
			}
		}
		for (int i = 0; i < n; i++) {
			nonLeafSums[i][i] = 0;
		}
		for (int length = 2; length <= n; length++) {
			for (int i = 0; i + length <= n; i++) {
				for (int j = i; j < i + length - 1; j++) {
					nonLeafSums[i][i + length - 1] = Math.min(nonLeafSums[i][i + length - 1], nonLeafSums[i][j]
							+ nonLeafSums[j + 1][i + length - 1] + rangeMaxs[i][j] * rangeMaxs[j + 1][i + length - 1]);
				}
			}
		}

		return nonLeafSums[0][n - 1];
	}
}
