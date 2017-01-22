public class TargetSum {
	public int findTargetSumWays(int[] nums, int S) {
		return search(nums, 0, S);
	}

	int search(int[] nums, int index, int remain) {
		if (index == nums.length) {
			return (remain == 0) ? 1 : 0;
		}

		return search(nums, index + 1, remain + nums[index]) + search(nums, index + 1, remain - nums[index]);
	}
}
