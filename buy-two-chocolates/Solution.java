class Solution {
  public int buyChoco(int[] prices, int money) {
    int result = -1;
    for (int i = 0; i < prices.length; ++i) {
      for (int j = i + 1; j < prices.length; ++j) {
        if (prices[i] + prices[j] <= money) {
          result = Math.max(result, money - prices[i] - prices[j]);
        }
      }
    }

    return (result == -1) ? money : result;
  }
}
