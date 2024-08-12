import java.util.Arrays;

class Solution {
  static final int MODULUS = 1_000_000_007;
  static final int LIMIT = 1000;

  public int countOfPairs(int[] nums) {
    int[] dp = new int[LIMIT + 1];
    for (int last1 = 0; last1 <= nums[0]; ++last1) {
      dp[last1] = 1;
    }
    int[] prefixSums = buildPrefixSums(dp);

    for (int i = 1; i < nums.length; ++i) {
      int[] nextDp = new int[dp.length];
      for (int nextLast1 = 0; nextLast1 <= nums[i]; ++nextLast1) {
        int upper = Math.min(nextLast1, nums[i - 1] - nums[i] + nextLast1);
        if (upper >= 0) {
          nextDp[nextLast1] = prefixSums[upper];
        }
      }

      dp = nextDp;
      prefixSums = buildPrefixSums(dp);
    }

    return Arrays.stream(dp).reduce(this::addMod).getAsInt();
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
}