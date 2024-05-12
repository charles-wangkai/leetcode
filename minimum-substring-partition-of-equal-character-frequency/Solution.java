import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Solution {
  public int minimumSubstringsInPartition(String s) {
    int[] dp = new int[s.length() + 1];
    Arrays.fill(dp, Integer.MAX_VALUE);
    dp[0] = 0;
    for (int i = 1; i < dp.length; ++i) {
      Map<Character, Integer> letterToCount = new HashMap<>();
      for (int j = i - 1; j >= 0; --j) {
        letterToCount.put(s.charAt(j), letterToCount.getOrDefault(s.charAt(j), 0) + 1);
        if (check(letterToCount)) {
          dp[i] = Math.min(dp[i], dp[j] + 1);
        }
      }
    }

    return dp[dp.length - 1];
  }

  boolean check(Map<Character, Integer> letterToCount) {
    int targetCount = letterToCount.values().iterator().next();
    for (int count : letterToCount.values()) {
      if (count != targetCount) {
        return false;
      }
    }

    return true;
  }
}