public class Solution {
	public int maxProfit(int[] prices) {
		int maxEarn = 0;
		int minPrice = Integer.MAX_VALUE;
		for (int price : prices) {
			minPrice = Math.min(minPrice, price);
			maxEarn = Math.max(maxEarn, price - minPrice);
		}
		return maxEarn;
	}
}
