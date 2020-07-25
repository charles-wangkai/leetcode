class Solution {
	public int findMin(int[] nums) {
		return search(nums, 0, nums.length - 1);
	}

	int search(int[] nums, int lower, int upper) {
		if (lower == upper || nums[lower] < nums[upper]) {
			return nums[lower];
		}

		int middle = (lower + upper) / 2;
		return Math.min(search(nums, lower, middle), search(nums, middle + 1, upper));
	}
}
