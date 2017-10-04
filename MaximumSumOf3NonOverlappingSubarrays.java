public class MaximumSumOf3NonOverlappingSubarrays {
	public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
		int[] leftMaxSums = new int[nums.length];
		int[] leftIndices = new int[nums.length];
		int leftSum = 0;
		for (int i = 0; i < k; i++) {
			leftSum += nums[i];
		}
		leftMaxSums[k - 1] = leftSum;
		leftIndices[k - 1] = 0;
		for (int i = k; i < leftMaxSums.length; i++) {
			leftSum += nums[i] - nums[i - k];
			if (leftSum > leftMaxSums[i - 1]) {
				leftMaxSums[i] = leftSum;
				leftIndices[i] = i - k + 1;
			} else {
				leftMaxSums[i] = leftMaxSums[i - 1];
				leftIndices[i] = leftIndices[i - 1];
			}
		}

		int[] rightMaxSums = new int[nums.length];
		int[] rightIndices = new int[nums.length];
		int rightSum = 0;
		for (int i = nums.length - 1; i >= nums.length - k; i--) {
			rightSum += nums[i];
		}
		rightMaxSums[nums.length - k] = rightSum;
		rightIndices[nums.length - k] = nums.length - k;
		for (int i = nums.length - k - 1; i >= 0; i--) {
			rightSum += nums[i] - nums[i + k];
			if (rightSum >= rightMaxSums[i + 1]) {
				rightMaxSums[i] = rightSum;
				rightIndices[i] = i;
			} else {
				rightMaxSums[i] = rightMaxSums[i + 1];
				rightIndices[i] = rightIndices[i + 1];
			}
		}

		int maxSum3 = 0;
		int[] indices = null;
		int sum = 0;
		for (int i = k; i < k + k; i++) {
			sum += nums[i];
		}
		for (int i = k; i + k + k <= nums.length; i++) {
			int sum3 = leftMaxSums[i - 1] + sum + rightMaxSums[i + k];
			if (sum3 > maxSum3) {
				maxSum3 = sum3;
				indices = new int[] { leftIndices[i - 1], i, rightIndices[i + k] };
			}

			sum += nums[i + k] - nums[i];
		}
		return indices;
	}
}
