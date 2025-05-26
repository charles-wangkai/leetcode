import java.util.HashMap;
import java.util.Map;

class Solution {
  public int maxSubstrings(String word) {
    int[] dp = new int[word.length() + 1];
    Map<Character, Integer> letterToLastIndex = new HashMap<>();
    for (int i = 1; i < dp.length; ++i) {
      dp[i] = dp[i - 1];

      if (letterToLastIndex.containsKey(word.charAt(i - 1))) {
        dp[i] = Math.max(dp[i], dp[letterToLastIndex.get(word.charAt(i - 1))] + 1);
      }

      if (i >= 3) {
        letterToLastIndex.put(word.charAt(i - 3), i - 3);
      }
    }

    return dp[dp.length - 1];
  }
}