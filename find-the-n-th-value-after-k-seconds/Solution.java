import java.util.Arrays;

class Solution {
  static final int MODULUS = 1_000_000_007;

  public int valueAfterKSeconds(int n, int k) {
    int[] a = new int[n];
    Arrays.fill(a, 1);

    for (int i = 0; i < k; ++i) {
      for (int j = 1; j < a.length; ++j) {
        a[j] = addMod(a[j], a[j - 1]);
      }
    }

    return a[a.length - 1];
  }

  int addMod(int x, int y) {
    return Math.floorMod(x + y, MODULUS);
  }
}