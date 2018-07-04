public class Solution {
	public boolean checkPossibility(int[] nums) {
		int decreaseNum = 0;
		for (int i = 0; i + 1 < nums.length; i++) {
			if (nums[i] > nums[i + 1]) {
				decreaseNum++;
				if (!isNonDecreasing(get(nums, i - 1), get(nums, i + 1), get(nums, i + 2))
						&& !isNonDecreasing(get(nums, i - 1), get(nums, i), get(nums, i + 2))) {
					return false;
				}
			}
		}
		return decreaseNum <= 1;
	}

	int get(int[] nums, int index) {
		if (index < 0) {
			return Integer.MIN_VALUE;
		} else if (index == nums.length) {
			return Integer.MAX_VALUE;
		} else {
			return nums[index];
		}
	}

	boolean isNonDecreasing(int x, int y, int z) {
		return x <= y && y <= z;
	}
}
