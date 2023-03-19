import java.util.stream.IntStream;

class Solution {
  public boolean checkValidGrid(int[][] grid) {
    int n = grid.length;

    Point[] points = new Point[n * n];
    for (int r = 0; r < n; ++r) {
      for (int c = 0; c < n; ++c) {
        points[grid[r][c]] = new Point(r, c);
      }
    }

    return points[0].equals(new Point(0, 0))
        && IntStream.range(0, points.length - 1)
            .allMatch(
                i ->
                    Math.abs(
                            (points[i + 1].r() - points[i].r())
                                * (points[i + 1].c() - points[i].c()))
                        == 2);
  }
}

record Point(int r, int c) {}
