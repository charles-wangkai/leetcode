public class Solution {
	public int maxValueAfterReverse(int[] nums) {
		int total = 0;
		int change = 0;
		int minPairMax = Integer.MAX_VALUE;
		int maxPairMin = Integer.MIN_VALUE;
		for (int i = 0; i < nums.length - 1; ++i) {
			int a = nums[i];
			int b = nums[i + 1];

			total += Math.abs(a - b);
			change = Math.max(change, Math.max(Math.abs(nums[0] - b) - Math.abs(a - b),
					Math.abs(nums[nums.length - 1] - a) - Math.abs(a - b)));
			minPairMax = Math.min(minPairMax, Math.max(a, b));
			maxPairMin = Math.max(maxPairMin, Math.min(a, b));
		}
		change = Math.max(change, (maxPairMin - minPairMax) * 2);

		return total + change;
	}
}
