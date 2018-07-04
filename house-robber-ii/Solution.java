public class Solution {
	public int rob(int[] nums) {
		if (nums.length == 0) {
			return 0;
		}
		return Math.max(rob(nums, 1, nums.length - 1),
				nums[0] + rob(nums, 2, nums.length - 2));
	}

	int rob(int[] num, int beginIndex, int endIndex) {
		if (beginIndex > endIndex) {
			return 0;
		}

		int prev3 = 0;
		int prev2 = 0;
		int prev1 = 0;
		for (int i = beginIndex; i <= endIndex; i++) {
			int current = Math.max(prev3, prev2) + num[i];
			prev3 = prev2;
			prev2 = prev1;
			prev1 = current;
		}
		return Math.max(prev2, prev1);
	}
}
