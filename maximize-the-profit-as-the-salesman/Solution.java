import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class Solution {
  public int maximizeTheProfit(int n, List<List<Integer>> offers) {
    Collections.sort(offers, Comparator.comparing(offer -> offer.get(1)));

    int[] dp = new int[n + 1];
    int offerIndex = 0;
    for (int i = 1; i < dp.length; ++i) {
      dp[i] = dp[i - 1];

      while (offerIndex != offers.size() && offers.get(offerIndex).get(1) + 1 == i) {
        dp[i] = Math.max(dp[i], dp[offers.get(offerIndex).get(0)] + offers.get(offerIndex).get(2));
        ++offerIndex;
      }
    }

    return dp[n];
  }
}
