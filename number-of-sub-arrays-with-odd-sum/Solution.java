class Solution {
  static final int MODULUS = 1_000_000_007;

  public int numOfSubarrays(int[] arr) {
    int[] dp = new int[2];
    dp[0] = 1;

    int result = 0;
    int prefixSum = 0;
    for (int x : arr) {
      prefixSum = (prefixSum + x) % 2;
      result = addMod(result, dp[1 - prefixSum]);
      ++dp[prefixSum];
    }

    return result;
  }

  int addMod(int x, int y) {
    return Math.floorMod(x + y, MODULUS);
  }
}