class Solution {
  public int maxProfit(int[] prices) {
    int result = 0;
    int minPrice = Integer.MAX_VALUE;
    for (int price : prices) {
      minPrice = Math.min(minPrice, price);
      result = Math.max(result, price - minPrice);
    }

    return result;
  }
}
