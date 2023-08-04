import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

class Solution {
  public boolean wordBreak(String s, List<String> wordDict) {
    Set<String> words = Set.copyOf(wordDict);

    boolean[] dp = new boolean[s.length() + 1];
    dp[0] = true;
    for (int i = 0; i < s.length(); ++i) {
      int i_ = i;
      dp[i + 1] =
          IntStream.rangeClosed(0, i)
              .anyMatch(j -> dp[j] && words.contains(s.substring(j, i_ + 1)));
    }

    return dp[s.length()];
  }
}
