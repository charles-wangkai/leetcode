import java.util.Comparator;
import java.util.stream.IntStream;

class Solution {
  public int nearestValidPoint(int x, int y, int[][] points) {
    return IntStream.range(0, points.length)
        .filter(i -> points[i][0] == x || points[i][1] == y)
        .boxed()
        .min(
            Comparator.comparing(
                    (Integer i) -> Math.abs(points[i][0] - x) + Math.abs(points[i][1] - y))
                .thenComparing(i -> i))
        .orElse(-1);
  }
}
