import java.util.stream.IntStream;

class Solution {
  static final int LIMIT = 1 << 7;

  public int maxValue(int[] nums, int k) {
    boolean[][] leftOrs = buildOrs(nums, k);
    boolean[][] rightOrs = reverse(buildOrs(reverse(nums), k));

    int result = -1;
    for (int i = 0; i < nums.length - 1; ++i) {
      for (int leftOr = 0; leftOr < LIMIT; ++leftOr) {
        for (int rightOr = 0; rightOr < LIMIT; ++rightOr) {
          if (leftOrs[i][leftOr] && rightOrs[i + 1][rightOr]) {
            result = Math.max(result, leftOr ^ rightOr);
          }
        }
      }
    }

    return result;
  }

  boolean[][] buildOrs(int[] values, int k) {
    boolean[][] result = new boolean[values.length][LIMIT];
    boolean[][] dp = new boolean[k + 1][LIMIT];
    dp[0][0] = true;
    for (int i = 0; i < result.length; ++i) {
      for (int j = k - 1; j >= 0; --j) {
        for (int prev = 0; prev < LIMIT; ++prev) {
          if (dp[j][prev]) {
            dp[j + 1][prev | values[i]] = true;
          }
        }
      }

      for (int j = 0; j < LIMIT; ++j) {
        if (dp[k][j]) {
          result[i][j] = true;
        }
      }
    }

    return result;
  }

  int[] reverse(int[] a) {
    return IntStream.range(0, a.length).map(i -> a[a.length - 1 - i]).toArray();
  }

  boolean[][] reverse(boolean[][] a) {
    return IntStream.range(0, a.length)
        .mapToObj(i -> a[a.length - 1 - i])
        .toArray(boolean[][]::new);
  }
}