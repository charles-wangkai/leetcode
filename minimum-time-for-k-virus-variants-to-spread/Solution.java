import java.util.Arrays;
import java.util.stream.IntStream;

class Solution {
  static final int LIMIT = 100;

  public int minDayskVariants(int[][] points, int k) {
    return IntStream.rangeClosed(1, LIMIT)
        .map(
            x ->
                IntStream.rangeClosed(1, LIMIT)
                    .map(y -> computeDayNum(points, k, x, y))
                    .min()
                    .getAsInt())
        .min()
        .getAsInt();
  }

  int computeDayNum(int[][] points, int k, int x, int y) {
    return Arrays.stream(points)
        .mapToInt(p -> Math.abs(p[0] - x) + Math.abs(p[1] - y))
        .boxed()
        .sorted()
        .skip(k - 1)
        .findFirst()
        .get();
  }
}