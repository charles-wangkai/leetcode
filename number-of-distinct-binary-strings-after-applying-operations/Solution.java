import java.util.stream.IntStream;

class Solution {
  static final int MODULUS = 1_000_000_007;

  public int countDistinctStrings(String s, int k) {
    return IntStream.range(0, s.length() - k + 1).reduce(1, (x, y) -> multiplyMod(x, 2));
  }

  static int multiplyMod(int x, int y) {
    return Math.floorMod((long) x * y, MODULUS);
  }
}
