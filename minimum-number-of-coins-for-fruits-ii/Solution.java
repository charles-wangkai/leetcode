import java.util.Arrays;
import java.util.SortedMap;
import java.util.TreeMap;

class Solution {
  public int minimumCoins(int[] prices) {
    int[] dp = new int[prices.length];
    Arrays.fill(dp, Integer.MAX_VALUE);
    dp[0] = prices[0];

    SortedMap<Integer, Integer> prevToCount = new TreeMap<>();
    updateMap(prevToCount, dp[0], 1);

    int beginIndex = 0;
    for (int i = 1; ; ++i) {
      while ((beginIndex + 1) * 2 < i) {
        updateMap(prevToCount, dp[beginIndex], -1);
        ++beginIndex;
      }
      if (i == dp.length) {
        return prevToCount.firstKey();
      }

      dp[i] = prevToCount.firstKey() + prices[i];

      updateMap(prevToCount, dp[i], 1);
    }
  }

  void updateMap(SortedMap<Integer, Integer> prevToCount, int key, int delta) {
    prevToCount.put(key, prevToCount.getOrDefault(key, 0) + delta);
    prevToCount.remove(key, 0);
  }
}