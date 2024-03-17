import java.util.stream.IntStream;

class Solution {
  static final int MODULUS = 1_000_000_007;

  public int sumOfPower(int[] nums, int k) {
    int[][] dp = new int[k + 1][nums.length + 1];
    dp[0][0] = 1;

    for (int value : nums) {
      int[][] nextDp = new int[k + 1][];
      for (int i = 0; i < nextDp.length; ++i) {
        nextDp[i] = dp[i].clone();
      }

      for (int sum = 0; sum <= k - value; ++sum) {
        for (int count = 0; count <= nums.length - 1; ++count) {
          nextDp[sum + value][count + 1] = addMod(nextDp[sum + value][count + 1], dp[sum][count]);
        }
      }

      dp = nextDp;
    }

    int[] twoPowers = new int[nums.length + 1];
    twoPowers[0] = 1;
    for (int i = 1; i < twoPowers.length; ++i) {
      twoPowers[i] = multiplyMod(twoPowers[i - 1], 2);
    }

    int[][] dp_ = dp;
    return IntStream.rangeClosed(0, nums.length)
        .map(i -> multiplyMod(dp_[k][i], twoPowers[nums.length - i]))
        .reduce(this::addMod)
        .getAsInt();
  }

  int addMod(int x, int y) {
    return Math.floorMod(x + y, MODULUS);
  }

  int multiplyMod(int x, int y) {
    return Math.floorMod((long) x * y, MODULUS);
  }
}