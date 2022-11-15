import java.util.stream.IntStream;

class Solution {
  static final int MODULUS = 1_000_000_007;

  public int countGoodStrings(int low, int high, int zero, int one) {
    int[] dp = new int[high + 1];
    dp[0] = 1;
    for (int i = 1; i < dp.length; ++i) {
      if (i >= zero) {
        dp[i] = addMod(dp[i], dp[i - zero]);
      }
      if (i >= one) {
        dp[i] = addMod(dp[i], dp[i - one]);
      }
    }

    return IntStream.rangeClosed(low, high).map(i -> dp[i]).reduce(this::addMod).getAsInt();
  }

  int addMod(int x, int y) {
    return Math.floorMod(x + y, MODULUS);
  }
}
