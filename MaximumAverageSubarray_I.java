import java.util.stream.IntStream;

public class MaximumAverageSubarray_I {
	public double findMaxAverage(int[] nums, int k) {
		int sum = IntStream.range(0, k).map(i -> nums[i]).sum();
		int maxSum = sum;
		for (int i = k; i < nums.length; i++) {
			sum += nums[i] - nums[i - k];
			maxSum = Math.max(maxSum, sum);
		}
		return (double) maxSum / k;
	}
}
