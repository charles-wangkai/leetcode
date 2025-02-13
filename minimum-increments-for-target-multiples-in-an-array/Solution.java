import java.util.Arrays;
import java.util.stream.IntStream;

class Solution {
  public int minimumIncrements(int[] nums, int[] target) {
    int limit = Arrays.stream(nums).max().getAsInt() + Arrays.stream(target).sum();

    int[][] dp = new int[nums.length + 1][1 << target.length];
    for (int i = 0; i < dp.length; ++i) {
      Arrays.fill(dp[i], Integer.MAX_VALUE);
    }
    dp[0][0] = 0;

    for (int i = 0; i < dp.length - 1; ++i) {
      int i_ = i;
      int sameMask =
          IntStream.range(0, target.length)
              .filter(j -> nums[i_] % target[j] == 0)
              .map(j -> 1 << j)
              .sum();

      for (int nextMask = 1; nextMask < 1 << target.length; ++nextMask) {
        int factor = computeFactor(target, limit, nextMask);
        if (factor != -1) {
          for (int mask = 0; mask < 1 << target.length; ++mask) {
            if (dp[i][mask] != Integer.MAX_VALUE) {
              dp[i + 1][mask | sameMask] = Math.min(dp[i + 1][mask | sameMask], dp[i][mask]);
              dp[i + 1][mask | nextMask] =
                  Math.min(
                      dp[i + 1][mask | nextMask],
                      dp[i][mask] + computeIncrementNum(nums[i], factor));
            }
          }
        }
      }
    }

    return dp[dp.length - 1][(1 << target.length) - 1];
  }

  int computeIncrementNum(int value, int factor) {
    return Math.floorMod(-value, factor);
  }

  int computeFactor(int[] target, int limit, int nextMask) {
    int result = -1;
    for (int i = 0; i < target.length; ++i) {
      if (((nextMask >> i) & 1) == 1) {
        result = (result == -1) ? target[i] : lcm(result, target[i]);
        if (result > limit) {
          return -1;
        }
      }
    }

    return result;
  }

  int lcm(int x, int y) {
    return x / gcd(x, y) * y;
  }

  int gcd(int x, int y) {
    return (y == 0) ? x : gcd(y, x % y);
  }
}