import java.util.Arrays;

public class Solution {
	public int minFallingPathSum(int[][] arr) {
		int size = arr.length;

		int[] minSums = arr[0];
		for (int i = 1; i < size; i++) {
			int[] nextMinSums = new int[size];
			for (int j = 0; j < nextMinSums.length; j++) {
				nextMinSums[j] = Integer.MAX_VALUE;
				for (int k = 0; k < size; k++) {
					if (k != j) {
						nextMinSums[j] = Math.min(nextMinSums[j], minSums[k] + arr[i][j]);
					}
				}
			}

			minSums = nextMinSums;
		}

		return Arrays.stream(minSums).min().getAsInt();
	}
}
