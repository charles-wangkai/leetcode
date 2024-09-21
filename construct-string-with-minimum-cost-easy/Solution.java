import java.util.Arrays;
import java.util.stream.IntStream;

class Solution {
  public int minimumCost(String target, String[] words, int[] costs) {
    int[] dp = new int[target.length() + 1];
    Arrays.fill(dp, Integer.MAX_VALUE);
    dp[0] = 0;

    for (int i = 1; i < dp.length; ++i) {
      for (int j = 0; j < words.length; ++j) {
        if (words[j].length() <= i
            && dp[i - words[j].length()] != Integer.MAX_VALUE
            && isMatching(target, i, words[j])) {
          dp[i] = Math.min(dp[i], dp[i - words[j].length()] + costs[j]);
        }
      }
    }

    return (dp[dp.length - 1] == Integer.MAX_VALUE) ? -1 : dp[dp.length - 1];
  }

  boolean isMatching(String target, int length, String word) {
    return IntStream.range(0, word.length())
        .allMatch(i -> word.charAt(i) == target.charAt(target.length() - length + i));
  }
}