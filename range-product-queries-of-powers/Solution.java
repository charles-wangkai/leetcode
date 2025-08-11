import java.util.Arrays;
import java.util.stream.IntStream;

class Solution {
  static final int MODULUS = 1_000_000_007;

  public int[] productQueries(int n, int[][] queries) {
    int[] powers =
        IntStream.rangeClosed(0, 30).filter(i -> ((n >> i) & 1) == 1).map(i -> 1 << i).toArray();

    return Arrays.stream(queries)
        .mapToInt(
            query ->
                IntStream.rangeClosed(query[0], query[1])
                    .reduce(1, (x, y) -> multiplyMod(x, powers[y])))
        .toArray();
  }

  static int multiplyMod(int x, int y) {
    return Math.floorMod((long) x * y, MODULUS);
  }
}
