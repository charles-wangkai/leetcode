class Solution {
  static final int LIMIT = 1000;
  static final int MODULUS = 1_000_000_007;

  int[][] cache = new int[LIMIT + 1][LIMIT + 1];

  public int rearrangeSticks(int n, int k) {
    if (n <= 1) {
      return (k == n) ? 1 : 0;
    }
    if (n < k || k == 0) {
      return 0;
    }

    if (cache[n][k] == 0) {
      cache[n][k] =
          addMod(rearrangeSticks(n - 1, k - 1), multiplyMod(rearrangeSticks(n - 1, k), n - 1));
    }

    return cache[n][k];
  }

  static int addMod(int x, int y) {
    return (x + y) % MODULUS;
  }

  static int multiplyMod(int x, int y) {
    return (int) ((long) x * y % MODULUS);
  }
}
