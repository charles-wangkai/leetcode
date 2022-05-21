import java.util.Arrays;

class Solution {
  public int coinChange(int[] coins, int amount) {
    int[] minCoinNums = new int[amount + 1];
    Arrays.fill(minCoinNums, Integer.MAX_VALUE);
    minCoinNums[0] = 0;

    for (int coin : coins) {
      for (int i = coin; i < minCoinNums.length; ++i) {
        if (minCoinNums[i - coin] != Integer.MAX_VALUE) {
          minCoinNums[i] = Math.min(minCoinNums[i], minCoinNums[i - coin] + 1);
        }
      }
    }

    return (minCoinNums[amount] == Integer.MAX_VALUE) ? -1 : minCoinNums[amount];
  }
}
