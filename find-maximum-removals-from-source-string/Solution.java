import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Solution {
  public int maxRemovals(String source, String pattern, int[] targetIndices) {
    Set<Integer> targets = Arrays.stream(targetIndices).boxed().collect(Collectors.toSet());

    int[] dp = new int[targetIndices.length + 1];
    Arrays.fill(dp, -1);
    dp[0] = 0;

    for (int i = 0; i < source.length(); ++i) {
      for (int j = dp.length - 1; j >= 0; --j) {
        if (dp[j] != -1 && dp[j] != pattern.length() && pattern.charAt(dp[j]) == source.charAt(i)) {
          ++dp[j];
        }
        if (targets.contains(i) && j != 0 && dp[j - 1] != -1) {
          dp[j] = Math.max(dp[j], (dp[j - 1] == pattern.length()) ? pattern.length() : dp[j - 1]);
        }
      }
    }

    return IntStream.range(0, dp.length).filter(i -> dp[i] == pattern.length()).max().getAsInt();
  }
}