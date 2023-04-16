class Solution {
  static final int MODULUS = 1_000_000_007;

  public int numWays(String[] words, String target) {
    int[][] counts = new int[words[0].length()][26];
    for (int i = 0; i < counts.length; ++i) {
      for (String word : words) {
        ++counts[i][word.charAt(i) - 'a'];
      }
    }

    int[] dp = new int[target.length() + 1];
    dp[0] = 1;
    for (int[] c : counts) {
      for (int i = dp.length - 1; i >= 1; --i) {
        dp[i] = addMod(dp[i], multiplyMod(c[target.charAt(i - 1) - 'a'], dp[i - 1]));
      }
    }

    return dp[target.length()];
  }

  int addMod(int x, int y) {
    return Math.floorMod(x + y, MODULUS);
  }

  int multiplyMod(int x, int y) {
    return Math.floorMod((long) x * y, MODULUS);
  }
}
