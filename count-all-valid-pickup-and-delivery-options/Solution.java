import java.util.stream.IntStream;

class Solution {
  static final int MODULUS = 1_000_000_007;

  public int countOrders(int n) {
    return IntStream.rangeClosed(1, n)
        .map(i -> i * (2 * i - 1))
        .reduce((x, y) -> Math.floorMod((long) x * y, MODULUS))
        .getAsInt();
  }
}
