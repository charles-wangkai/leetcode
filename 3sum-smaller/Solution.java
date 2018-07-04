import java.util.Arrays;

public class Solution {
	public int threeSumSmaller(int[] nums, int target) {
		Arrays.sort(nums);
		int count = 0;
		for (int i = 0; i < nums.length; i++) {
			int remain = target - nums[i];
			for (int j = i + 1, k = nums.length - 1; j < k;) {
				if (nums[j] + nums[k] < remain) {
					count += k - j;
					j++;
				} else {
					k--;
				}
			}
		}
		return count;
	}
}
