import java.util.Arrays;

class Solution {
	public int numSubarrayProductLessThanK(int[] nums, int k) {
		if (k <= 1) {
			return 0;
		}

		nums = Arrays.copyOf(nums, nums.length + 1);
		nums[nums.length - 1] = k;
		long product = 1;
		int endIndex = -1;
		int result = 0;
		for (int beginIndex = 0; beginIndex < nums.length; ++beginIndex) {
			while (product < k) {
				++endIndex;
				product *= nums[endIndex];
			}

			result += endIndex - beginIndex;
			product /= nums[beginIndex];
		}

		return result;
	}
}
