public class Solution {
	public int wiggleMaxLength(int[] nums) {
		if (nums.length < 2) {
			return nums.length;
		}

		return Math.max(search(nums, true), search(nums, false));
	}

	private int search(int[] nums, boolean upOrDown) {
		int maxLength = 1;
		for (int i = 1; i < nums.length; i++) {
			if (nums[i] != nums[i - 1] && (nums[i] > nums[i - 1]) == upOrDown) {
				maxLength++;
				upOrDown = !upOrDown;
			}
		}
		return maxLength;
	}
}
