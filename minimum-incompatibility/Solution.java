import java.util.Arrays;

class Solution {
  public int minimumIncompatibility(int[] nums, int k) {
    int n = nums.length;

    if (k == n) {
      return 0;
    }

    int[] sorted = Arrays.stream(nums).sorted().toArray();

    int[][] dp = new int[1 << n][n];
    for (int i = 0; i < dp.length; ++i) {
      Arrays.fill(dp[i], Integer.MAX_VALUE);
    }
    for (int i = 0; i < n; ++i) {
      dp[1 << i][i] = 0;
    }

    for (int mask = 0; mask < 1 << n; ++mask) {
      if (Integer.bitCount(mask) % (n / k) == 1) {
        for (int j = 0; j < n; ++j) {
          if ((mask & (1 << j)) != 0) {
            for (int l = 0; l < n; ++l) {
              if ((mask & (1 << l)) != 0 && dp[mask ^ (1 << l)][j] != Integer.MAX_VALUE) {
                dp[mask][l] = Math.min(dp[mask][l], dp[mask ^ (1 << l)][j]);
              }
            }
          }
        }
      } else {
        for (int j = 0; j < n; ++j) {
          if ((mask & (1 << j)) != 0) {
            for (int l = j + 1; l < n; ++l) {
              if ((mask & (1 << l)) != 0
                  && sorted[l] != sorted[j]
                  && dp[mask ^ (1 << l)][j] != Integer.MAX_VALUE) {
                dp[mask][l] = Math.min(dp[mask][l], dp[mask ^ (1 << l)][j] + sorted[l] - sorted[j]);
              }
            }
          }
        }
      }
    }

    int result = Arrays.stream(dp[(1 << n) - 1]).min().getAsInt();

    return (result == Integer.MAX_VALUE) ? -1 : result;
  }
}
