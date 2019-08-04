public class Solution {
	public int movesToMakeZigzag(int[] nums) {
		return Math.min(computeMoveNum(nums, false), computeMoveNum(nums, true));
	}

	int computeMoveNum(int[] nums, boolean upOrDown) {
		int moveNum = 0;
		int prev = nums[0];
		for (int i = 1; i < nums.length; i++) {
			if (upOrDown) {
				moveNum += Math.max(0, prev - (nums[i] - 1));
				prev = nums[i];
			} else {
				moveNum += Math.max(0, nums[i] - (prev - 1));
				prev = Math.min(nums[i], prev - 1);
			}

			upOrDown = !upOrDown;
		}
		return moveNum;
	}
}
