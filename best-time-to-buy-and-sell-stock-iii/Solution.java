class Solution {
  public int maxProfit(int[] prices) {
    int[] maxAfterPrices = new int[prices.length];
    int maxAfterPrice = Integer.MIN_VALUE;
    for (int i = maxAfterPrices.length - 1; i >= 0; --i) {
      maxAfterPrice = Math.max(maxAfterPrice, prices[i]);
      maxAfterPrices[i] = maxAfterPrice;
    }

    int result = 0;
    int maxEarn1 = 0;
    int minPrice = Integer.MAX_VALUE;
    for (int i = 0; i < prices.length; ++i) {
      minPrice = Math.min(minPrice, prices[i]);
      maxEarn1 = Math.max(maxEarn1, prices[i] - minPrice);

      int maxEarn2 = maxAfterPrices[i] - prices[i];

      result = Math.max(result, maxEarn1 + maxEarn2);
    }

    return result;
  }
}
