public class Solution {
	public boolean PredictTheWinner(int[] nums) {
		int n = nums.length;
		int[][] maxDiffs = new int[n][n];
		for (int i = 0; i < n; i++) {
			maxDiffs[i][i] = nums[i];
		}
		for (int length = 2; length <= n; length++) {
			for (int begin = 0; begin + length - 1 < n; begin++) {
				int end = begin + length - 1;

				maxDiffs[begin][end] = Math.max(nums[begin] - maxDiffs[begin + 1][end],
						nums[end] - maxDiffs[begin][end - 1]);
			}
		}
		return maxDiffs[0][n - 1] >= 0;
	}
}
