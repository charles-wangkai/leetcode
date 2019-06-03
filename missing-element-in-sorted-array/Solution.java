public class Solution {
	public int missingElement(int[] nums, int k) {
		for (int i = 0; i < nums.length - 1; i++) {
			int slotNum = nums[i + 1] - nums[i] - 1;

			if (k <= slotNum) {
				return nums[i] + k;
			} else {
				k -= slotNum;
			}
		}

		return nums[nums.length - 1] + k;
	}
}
