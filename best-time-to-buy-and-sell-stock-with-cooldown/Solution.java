class Solution {
	public int maxProfit(int[] prices) {
		if (prices.length == 0) {
			return 0;
		}

		int[] profits = new int[prices.length];
		for (int i = 1; i < profits.length; ++i) {
			profits[i] = profits[i - 1];
			for (int j = 0; j < i; ++j) {
				profits[i] = Math.max(profits[i], (prices[i] - prices[j]) + (j >= 2 ? profits[j - 2] : 0));
			}
		}

		return profits[profits.length - 1];
	}
}
