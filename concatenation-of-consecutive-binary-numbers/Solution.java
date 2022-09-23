import java.util.stream.IntStream;

class Solution {
  static final int MODULUS = 1_000_000_007;

  public int concatenatedBinary(int n) {
    return IntStream.rangeClosed(1, n)
        .reduce((result, i) -> addMod(multiplyMod(result, (Integer.highestOneBit(i) * 2)), i))
        .getAsInt();
  }

  int addMod(int x, int y) {
    return Math.floorMod(x + y, MODULUS);
  }

  int multiplyMod(int x, int y) {
    return Math.floorMod((long) x * y, MODULUS);
  }
}
