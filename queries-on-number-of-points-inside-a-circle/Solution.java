import java.util.Arrays;

class Solution {
  public int[] countPoints(int[][] points, int[][] queries) {
    return Arrays.stream(queries)
        .mapToInt(
            query ->
                (int)
                    Arrays.stream(points)
                        .filter(p -> computeDistance(p[0], p[1], query[0], query[1]) <= query[2])
                        .count())
        .toArray();
  }

  double computeDistance(int x0, int y0, int x1, int y1) {
    return Math.sqrt((x0 - x1) * (x0 - x1) + (y0 - y1) * (y0 - y1));
  }
}
