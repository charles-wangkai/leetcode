public class BestTimeToBuyAndSellStock_IV {
	public int maxProfit(int k, int[] prices) {
		if (k >= prices.length - 1) {
			return maxProfitWithUnlimitedTransactions(prices);
		}

		int[] inProfits = new int[k + 1];
		int[] offProfits = new int[k + 1];
		for (int i = 0; i < prices.length - 1; i++) {
			int priceDelta = prices[i + 1] - prices[i];
			int[] nextInProfits = new int[inProfits.length];
			int[] nextOffProfits = new int[offProfits.length];
			for (int j = 1; j < nextInProfits.length; j++) {
				nextInProfits[j] = Math.max(inProfits[j], offProfits[j - 1])
						+ priceDelta;
				nextOffProfits[j] = Math.max(inProfits[j], offProfits[j]);
			}
			inProfits = nextInProfits;
			offProfits = nextOffProfits;
		}

		int maxEarn = 0;
		for (int i = 0; i < inProfits.length; i++) {
			maxEarn = Math.max(maxEarn, Math.max(inProfits[i], offProfits[i]));
		}
		return maxEarn;
	}

	int maxProfitWithUnlimitedTransactions(int[] prices) {
		int maxEarn = 0;
		for (int i = 0; i < prices.length - 1; i++) {
			maxEarn += Math.max(0, prices[i + 1] - prices[i]);
		}
		return maxEarn;
	}
}
