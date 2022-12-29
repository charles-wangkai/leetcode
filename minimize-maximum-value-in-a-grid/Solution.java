import java.util.Comparator;
import java.util.stream.IntStream;

class Solution {
  public int[][] minScore(int[][] grid) {
    int m = grid.length;
    int n = grid[0].length;

    Point[] points =
        IntStream.range(0, m)
            .boxed()
            .flatMap(r -> IntStream.range(0, n).mapToObj(c -> new Point(r, c)))
            .sorted(Comparator.comparing(p -> grid[p.r()][p.c()]))
            .toArray(Point[]::new);

    int[][] result = new int[m][n];
    int[] rowValues = new int[m];
    int[] colValues = new int[n];
    for (Point point : points) {
      result[point.r()][point.c()] = Math.max(rowValues[point.r()], colValues[point.c()]) + 1;
      rowValues[point.r()] = result[point.r()][point.c()];
      colValues[point.c()] = result[point.r()][point.c()];
    }

    return result;
  }
}

record Point(int r, int c) {}
