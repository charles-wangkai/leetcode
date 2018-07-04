public class Solution {
	public void wiggleSort(int[] nums) {
		boolean greaterOrEqual = true;
		for (int i = 1; i < nums.length; i++) {
			if ((nums[i] >= nums[i - 1]) != greaterOrEqual) {
				int temp = nums[i];
				nums[i] = nums[i - 1];
				nums[i - 1] = temp;
			}
			greaterOrEqual = !greaterOrEqual;
		}
	}
}
