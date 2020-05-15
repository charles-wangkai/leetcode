import java.util.Arrays;

public class Solution {
	public int maxSubarraySumCircular(int[] A) {
		int maxSum = computeMaxSum(A);
		if (maxSum < 0) {
			return maxSum;
		}

		return Math.max(maxSum, Arrays.stream(A).sum() - computeMinSum(A));
	}

	int computeMaxSum(int[] a) {
		int sum = 0;
		int maxSum = Integer.MIN_VALUE;
		for (int number : a) {
			sum += number;
			maxSum = Math.max(maxSum, sum);
			sum = Math.max(0, sum);
		}

		return maxSum;
	}

	int computeMinSum(int[] a) {
		return -computeMaxSum(Arrays.stream(a).map(x -> -x).toArray());
	}
}
