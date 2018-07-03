public class Solution {
	public int maxProfit(int[] prices, int fee) {
		int profitWithoutStock = 0;
		int profitWithStock = Integer.MIN_VALUE;
		for (int price : prices) {
			int oldProfitWithoutStock = profitWithoutStock;

			profitWithoutStock = Math.max(profitWithoutStock, profitWithStock + price);
			profitWithStock = Math.max(profitWithStock, oldProfitWithoutStock - price - fee);
		}
		return profitWithoutStock;
	}
}
