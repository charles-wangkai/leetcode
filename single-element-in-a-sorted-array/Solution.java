public class Solution {
	public int singleNonDuplicate(int[] nums) {
		int lower = 0;
		int upper = nums.length - 1;
		while (lower < upper) {
			int middle = (lower + upper) / 2;
			if (nums[middle] == nums[middle - 1]) {
				if ((middle - lower + 1) % 2 == 0) {
					lower = middle + 1;
				} else {
					upper = middle;
				}
			} else if (nums[middle] == nums[middle + 1]) {
				if ((upper - middle + 1) % 2 == 0) {
					upper = middle - 1;
				} else {
					lower = middle;
				}
			} else {
				return nums[middle];
			}
		}
		return nums[lower];
	}
}
