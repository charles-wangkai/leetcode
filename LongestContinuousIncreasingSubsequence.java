public class LongestContinuousIncreasingSubsequence {
	public int findLengthOfLCIS(int[] nums) {
		if (nums.length == 0) {
			return 0;
		}

		int maxLength = 0;
		int length = 1;
		for (int i = 1; i <= nums.length; i++) {
			if (i < nums.length && nums[i] > nums[i - 1]) {
				length++;
			} else {
				maxLength = Math.max(maxLength, length);

				length = 1;
			}
		}
		return maxLength;
	}
}
