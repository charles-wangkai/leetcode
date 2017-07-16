public class MaximumAverageSubarray_II {
	public double findMaxAverage(int[] nums, int k) {
		double maxAverage = -Double.MAX_VALUE;
		int beginSum = 0;
		for (int i = 0; i <= k - 2; i++) {
			beginSum += nums[i];
		}
		for (int length = k; length <= nums.length; length++) {
			beginSum += nums[length - 1];

			int maxSum = beginSum;
			int sum = beginSum;
			for (int i = length; i < nums.length; i++) {
				sum += nums[i] - nums[i - length];
				maxSum = Math.max(maxSum, sum);
			}

			maxAverage = Math.max(maxAverage, (double) maxSum / length);
		}
		return maxAverage;
	}
}
