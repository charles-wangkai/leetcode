import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

class Solution {
  static final int MODULUS = 1_000_000_007;

  public int sumOfPowers(int[] nums, int k) {
    Arrays.sort(nums);

    Set<Integer> powers = new HashSet<>();
    for (int i = 0; i < nums.length; ++i) {
      for (int j = i + 1; j < nums.length; ++j) {
        powers.add(nums[j] - nums[i]);
      }
    }

    int result = 0;
    for (int power : powers) {
      int[][][] dp = buildDp(nums, k, power);
      for (int p = 0; p < dp.length; ++p) {
        result = addMod(result, multiplyMod(power, dp[p][k][1]));
      }
    }

    return result;
  }

  int[][][] buildDp(int[] nums, int k, int power) {
    int[][][] dp = new int[nums.length][k + 1][2];
    for (int i = 0; i < dp.length; ++i) {
      dp[i][1][0] = 1;

      for (int count = 2; count <= k; ++count) {
        for (int prevI = 0; prevI < i; ++prevI) {
          if (nums[i] - nums[prevI] > power) {
            dp[i][count][0] = addMod(dp[i][count][0], dp[prevI][count - 1][0]);
            dp[i][count][1] = addMod(dp[i][count][1], dp[prevI][count - 1][1]);
          } else if (nums[i] - nums[prevI] == power) {
            dp[i][count][1] = addMod(dp[i][count][1], dp[prevI][count - 1][0]);
            dp[i][count][1] = addMod(dp[i][count][1], dp[prevI][count - 1][1]);
          }
        }
      }
    }

    return dp;
  }

  int addMod(int x, int y) {
    return Math.floorMod(x + y, MODULUS);
  }

  int multiplyMod(int x, int y) {
    return Math.floorMod((long) x * y, MODULUS);
  }
}