class Solution {
  static final int MODULUS = 1_000_000_007;

  public int concatenatedBinary(int n) {
    int result = 0;
    for (int i = 1; i <= n; ++i) {
      for (char ch : Integer.toBinaryString(i).toCharArray()) {
        result = addMod(multiplyMod(result, 2), ch - '0');
      }
    }

    return result;
  }

  int addMod(int x, int y) {
    return (x + y) % MODULUS;
  }

  int multiplyMod(int x, int y) {
    return (int) ((long) x * y % MODULUS);
  }
}
