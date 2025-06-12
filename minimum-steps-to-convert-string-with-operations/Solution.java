import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
  public int minOperations(String word1, String word2) {
    int n = word1.length();

    int[] dp = new int[n + 1];
    for (int i = 1; i <= n; ++i) {
      dp[i] = Integer.MAX_VALUE;
      for (int j = 1; j <= i; ++j) {
        dp[i] =
            Math.min(
                dp[i],
                dp[j - 1]
                    + computeOperationNum(word1.substring(j - 1, i), word2.substring(j - 1, i)));
      }
    }

    return dp[n];
  }

  int computeOperationNum(String s1, String s2) {
    return Math.min(
        computeReplaceAndSwapNum(s1, s2),
        computeReplaceAndSwapNum(new StringBuilder(s1).reverse().toString(), s2) + 1);
  }

  int computeReplaceAndSwapNum(String s1, String s2) {
    Map<String, Integer> pairToCount = new HashMap<>();
    List<String> pairs = new ArrayList<>();
    for (int i = 0; i < s1.length(); ++i) {
      if (s1.charAt(i) != s2.charAt(i)) {
        String pair = "%c%c".formatted(s1.charAt(i), s2.charAt(i));
        pairs.add(pair);
        updateMap(pairToCount, pair, 1);
      }
    }

    int result = 0;
    for (String pair : pairs) {
      String reversed = new StringBuilder(pair).reverse().toString();
      if (pairToCount.containsKey(pair) && pairToCount.containsKey(reversed)) {
        updateMap(pairToCount, pair, -1);
        updateMap(pairToCount, reversed, -1);
        ++result;
      }
    }

    result += pairToCount.values().stream().mapToInt(Integer::intValue).sum();

    return result;
  }

  void updateMap(Map<String, Integer> pairToCount, String pair, int delta) {
    pairToCount.put(pair, pairToCount.getOrDefault(pair, 0) + delta);
    pairToCount.remove(pair, 0);
  }
}
