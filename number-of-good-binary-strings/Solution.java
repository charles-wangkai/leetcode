import java.util.stream.IntStream;

class Solution {
  static final int MODULUS = 1_000_000_007;

  public int goodBinaryStrings(int minLength, int maxLength, int oneGroup, int zeroGroup) {
    int[] dp = new int[maxLength + 1];
    dp[0] = 1;
    for (int i = 1; i < dp.length; ++i) {
      if (i >= oneGroup) {
        dp[i] = addMod(dp[i], dp[i - oneGroup]);
      }
      if (i >= zeroGroup) {
        dp[i] = addMod(dp[i], dp[i - zeroGroup]);
      }
    }

    return IntStream.rangeClosed(minLength, maxLength)
        .map(i -> dp[i])
        .reduce(this::addMod)
        .getAsInt();
  }

  int addMod(int x, int y) {
    return Math.floorMod(x + y, MODULUS);
  }
}
