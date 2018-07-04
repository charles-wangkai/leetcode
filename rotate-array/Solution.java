public class Solution {
	public void rotate(int[] nums, int k) {
		if (nums.length == 0) {
			return;
		}
		k %= nums.length;

		reverse(nums, 0, nums.length - 1);
		reverse(nums, 0, k - 1);
		reverse(nums, k, nums.length - 1);
	}

	void reverse(int[] nums, int begin, int end) {
		for (int i = begin, j = end; i < j; i++, j--) {
			int temp = nums[i];
			nums[i] = nums[j];
			nums[j] = temp;
		}
	}
}
