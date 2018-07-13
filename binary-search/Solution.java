public class Solution {
	public int search(int[] nums, int target) {
		int lowerIndex = 0;
		int upperIndex = nums.length - 1;
		while (lowerIndex <= upperIndex) {
			int middleIndex = (lowerIndex + upperIndex) / 2;

			if (nums[middleIndex] == target) {
				return middleIndex;
			} else if (nums[middleIndex] < target) {
				lowerIndex = middleIndex + 1;
			} else {
				upperIndex = middleIndex - 1;
			}
		}
		return -1;
	}
}
