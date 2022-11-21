class Solution {
  static final int MODULUS = 1_000_000_007;

  public int beautifulPartitions(String s, int k, int minLength) {
    int[] dp = new int[s.length() + 1];
    dp[0] = 1;
    for (int i = 0; i < k; ++i) {
      int[] nextDp = new int[dp.length];
      int sum = 0;
      for (int prevLength = 0; prevLength + minLength < dp.length; ++prevLength) {
        if (isPrimeDigit(s.charAt(prevLength))) {
          sum = addMod(sum, dp[prevLength]);
        }
        if (!isPrimeDigit(s.charAt(prevLength + minLength - 1))) {
          nextDp[prevLength + minLength] = sum;
        }
      }

      dp = nextDp;
    }

    return dp[s.length()];
  }

  int addMod(int x, int y) {
    return Math.floorMod(x + y, MODULUS);
  }

  boolean isPrimeDigit(char c) {
    return "2357".indexOf(c) >= 0;
  }
}
