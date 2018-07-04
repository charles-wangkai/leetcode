public class Solution {
	public int minCostClimbingStairs(int[] cost) {
		int prev = 0;
		int prevprev = 0;
		for (int i = 2; i <= cost.length; i++) {
			int curr = Math.min(prev + cost[i - 1], prevprev + cost[i - 2]);

			prevprev = prev;
			prev = curr;
		}
		return prev;
	}
}
