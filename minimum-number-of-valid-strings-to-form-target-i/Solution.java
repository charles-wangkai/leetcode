import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

class Solution {
  static final int BASE = 31;

  public int minValidStrings(String[] words, String target) {
    Set<Long> prefixHashes = new HashSet<>();
    for (String word : words) {
      long prefixHash = 0;
      for (char c : word.toCharArray()) {
        prefixHash = prefixHash * BASE + (c - 'a' + 1);
        prefixHashes.add(prefixHash);
      }
    }

    int[] dp = new int[target.length() + 1];
    Arrays.fill(dp, Integer.MAX_VALUE);
    dp[0] = 0;
    for (int i = 0; i < dp.length - 1; ++i) {
      if (dp[i] != Integer.MAX_VALUE) {
        long hash = 0;
        for (int j = i; j < target.length(); ++j) {
          hash = hash * BASE + (target.charAt(j) - 'a' + 1);
          if (!prefixHashes.contains(hash)) {
            break;
          }

          dp[j + 1] = Math.min(dp[j + 1], dp[i] + 1);
        }
      }
    }

    return (dp[dp.length - 1] == Integer.MAX_VALUE) ? -1 : dp[dp.length - 1];
  }
}