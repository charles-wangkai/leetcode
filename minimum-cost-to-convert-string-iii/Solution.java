import java.util.Arrays;
import java.util.List;

class Solution {
  public int minCost(String source, String target, List<List<String>> rules, int[] costs) {
    int[] dp = new int[source.length() + 1];
    Arrays.fill(dp, Integer.MAX_VALUE);
    dp[0] = 0;

    for (int i = 1; i < dp.length; ++i) {
      if (source.charAt(i - 1) == target.charAt(i - 1)) {
        dp[i] = Math.min(dp[i], dp[i - 1]);
      }

      for (int j = 0; j < rules.size(); ++j) {
        List<String> rule = rules.get(j);
        String pattern = rule.get(0);
        String replacement = rule.get(1);

        if (isMatch(source, target, pattern, replacement, i - pattern.length())
            && dp[i - pattern.length()] != Integer.MAX_VALUE) {
          dp[i] =
              Math.min(
                  dp[i],
                  dp[i - pattern.length()]
                      + (costs[j] + (int) pattern.chars().filter(c -> c == '*').count()));
        }
      }
    }

    return (dp[dp.length - 1] == Integer.MAX_VALUE) ? -1 : dp[dp.length - 1];
  }

  boolean isMatch(
      String source, String target, String pattern, String replacement, int beginIndex) {
    if (beginIndex < 0) {
      return false;
    }

    for (int i = 0; i < pattern.length(); ++i) {
      if ((pattern.charAt(i) != '*' && pattern.charAt(i) != source.charAt(beginIndex + i))
          || replacement.charAt(i) != target.charAt(beginIndex + i)) {
        return false;
      }
    }

    return true;
  }
}