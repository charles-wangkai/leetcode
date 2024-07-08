import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Solution {
  static final int BASE = 31;

  public int minimumCost(String target, String[] words, int[] costs) {
    Map<Long, Integer> wordHashToCost = new HashMap<>();
    for (int i = 0; i < words.length; ++i) {
      long wordHash = computeHash(words[i]);
      wordHashToCost.put(
          wordHash, Math.min(wordHashToCost.getOrDefault(wordHash, Integer.MAX_VALUE), costs[i]));
    }

    int[] wordLengths = Arrays.stream(words).mapToInt(String::length).sorted().distinct().toArray();

    long[] basePowers = new long[target.length() + 1];
    basePowers[0] = 1;
    for (int i = 1; i < basePowers.length; ++i) {
      basePowers[i] = basePowers[i - 1] * BASE;
    }

    long[] prefixHashes = new long[target.length() + 1];
    for (int i = 1; i < prefixHashes.length; ++i) {
      prefixHashes[i] = prefixHashes[i - 1] * BASE + (target.charAt(i - 1) - 'a' + 1);
    }

    int[] dp = new int[target.length() + 1];
    Arrays.fill(dp, Integer.MAX_VALUE);
    dp[0] = 0;

    for (int i = 1; i < dp.length; ++i) {
      for (int wordLength : wordLengths) {
        if (wordLength > i) {
          break;
        }

        long wordHash = prefixHashes[i] - prefixHashes[i - wordLength] * basePowers[wordLength];
        if (dp[i - wordLength] != Integer.MAX_VALUE && wordHashToCost.containsKey(wordHash)) {
          dp[i] = Math.min(dp[i], dp[i - wordLength] + wordHashToCost.get(wordHash));
        }
      }
    }

    return (dp[dp.length - 1] == Integer.MAX_VALUE) ? -1 : dp[dp.length - 1];
  }

  static long computeHash(String s) {
    long result = 0;
    for (char c : s.toCharArray()) {
      result = result * BASE + (c - 'a' + 1);
    }

    return result;
  }
}