import java.util.HashMap;
import java.util.Map;

class Solution {
  public long maxScore(int[] prices) {
    Map<Integer, Long> groupToSum = new HashMap<>();
    for (int i = 0; i < prices.length; ++i) {
      int group = prices[i] - i;
      groupToSum.put(group, groupToSum.getOrDefault(group, 0L) + prices[i]);
    }

    return groupToSum.values().stream().mapToLong(Long::longValue).max().getAsLong();
  }
}
