public class SplitArrayLargestSum {
	public int splitArray(int[] nums, int m) {
		long result = -1;
		long lower = 0;
		long upper = computeSum(nums);
		while (lower <= upper) {
			long middle = (lower + upper) / 2;
			if (isValid(nums, m, middle)) {
				result = middle;
				upper = middle - 1;
			} else {
				lower = middle + 1;
			}
		}
		return (int) result;
	}

	long computeSum(int[] nums) {
		long result = 0;
		for (int num : nums) {
			result += num;
		}
		return result;
	}

	boolean isValid(int[] nums, int m, long sumLimit) {
		int index = 0;
		for (int i = 0; i < m; i++) {
			if (nums[index] > sumLimit) {
				return false;
			}

			long sum = nums[index];
			index++;
			while (nums.length - index >= m - i && sum + nums[index] <= sumLimit) {
				sum += nums[index];
				index++;
			}
		}
		return index == nums.length;
	}
}
