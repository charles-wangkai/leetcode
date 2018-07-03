public class Solution {
	public int maxProfit(int[] prices) {
		int maxEarn = 0;
		for (int i = 0; i < prices.length - 1; i++) {
			maxEarn += Math.max(0, prices[i + 1] - prices[i]);
		}
		return maxEarn;
	}
}
