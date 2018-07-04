public class Solution {
	public int minSubArrayLen(int s, int[] nums) {
		int minLength = Integer.MAX_VALUE;
		int beginIndex = 0;
		int sum = 0;
		for (int i = 0; i < nums.length; i++) {
			sum += nums[i];
			while (sum - nums[beginIndex] >= s) {
				sum -= nums[beginIndex];
				beginIndex++;
			}
			if (sum >= s) {
				minLength = Math.min(minLength, i - beginIndex + 1);
			}
		}
		return minLength == Integer.MAX_VALUE ? 0 : minLength;
	}
}
