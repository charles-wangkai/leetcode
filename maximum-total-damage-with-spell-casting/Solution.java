import java.util.HashMap;
import java.util.Map;

class Solution {
  public long maximumTotalDamage(int[] power) {
    Map<Integer, Integer> damageToCount = new HashMap<>();
    for (int damage : power) {
      damageToCount.put(damage, damageToCount.getOrDefault(damage, 0) + 1);
    }

    int[] damages = damageToCount.keySet().stream().sorted().mapToInt(Integer::intValue).toArray();

    long[] dp = new long[damages.length];
    for (int i = 0; i < dp.length; ++i) {
      int prev = i - 1;
      while (prev != -1 && damages[i] - damages[prev] <= 2) {
        --prev;
      }

      dp[i] =
          Math.max(
              (i == 0) ? 0 : dp[i - 1],
              ((prev == -1) ? 0 : dp[prev]) + (long) damages[i] * damageToCount.get(damages[i]));
    }

    return dp[dp.length - 1];
  }
}