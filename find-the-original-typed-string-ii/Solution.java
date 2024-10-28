import java.util.ArrayList;
import java.util.List;

class Solution {
  static final int MODULUS = 1_000_000_007;

  public int possibleStringCount(String word, int k) {
    List<Integer> parts = new ArrayList<>();
    int part = 0;
    for (int i = 0; i <= word.length(); ++i) {
      if (i != 0 && i != word.length() && word.charAt(i) == word.charAt(i - 1)) {
        ++part;
      } else {
        if (part != 0) {
          parts.add(part);
        }

        part = 1;
      }
    }

    if (parts.size() >= k) {
      return parts.stream().reduce(this::multiplyMod).get();
    }

    int[] dp = new int[k - parts.size() + 1];
    dp[0] = 1;
    for (int p : parts) {
      int[] prefixSums = buildPrefixSums(dp);

      int[] nextDp = new int[dp.length];
      for (int i = 0; i < nextDp.length - 1; ++i) {
        nextDp[i] = computeRangeSum(prefixSums, Math.max(0, i - p + 1), i);
      }
      for (int i = 0; i < dp.length; ++i) {
        nextDp[nextDp.length - 1] =
            addMod(
                nextDp[nextDp.length - 1],
                multiplyMod(dp[i], Math.max(0, p - (nextDp.length - 1 - i))));
      }

      dp = nextDp;
    }

    return dp[dp.length - 1];
  }

  int computeRangeSum(int[] prefixSums, int beginIndex, int endIndex) {
    return addMod(prefixSums[endIndex], (beginIndex == 0) ? 0 : -prefixSums[beginIndex - 1]);
  }

  int[] buildPrefixSums(int[] dp) {
    int[] result = new int[dp.length];
    for (int i = 0; i < result.length; ++i) {
      result[i] = addMod((i == 0) ? 0 : result[i - 1], dp[i]);
    }

    return result;
  }

  int addMod(int x, int y) {
    return Math.floorMod(x + y, MODULUS);
  }

  int multiplyMod(int x, int y) {
    return Math.floorMod((long) x * y, MODULUS);
  }
}