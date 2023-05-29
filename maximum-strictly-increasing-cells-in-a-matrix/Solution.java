import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

class Solution {
  public int maxIncreasingCells(int[][] mat) {
    int m = mat.length;
    int n = mat[0].length;

    SortedMap<Integer, List<Point>> valueToPoints = new TreeMap<>();
    for (int r = 0; r < m; ++r) {
      for (int c = 0; c < n; ++c) {
        valueToPoints.putIfAbsent(mat[r][c], new ArrayList<>());
        valueToPoints.get(mat[r][c]).add(new Point(r, c));
      }
    }

    int[] rowMaxLengths = new int[m];
    int[] colMaxLengths = new int[n];
    for (List<Point> points : valueToPoints.values()) {
      int[] maxLengths =
          points.stream()
              .mapToInt(point -> Math.max(rowMaxLengths[point.r()], colMaxLengths[point.c()]) + 1)
              .toArray();

      for (int i = 0; i < points.size(); ++i) {
        rowMaxLengths[points.get(i).r()] =
            Math.max(rowMaxLengths[points.get(i).r()], maxLengths[i]);
        colMaxLengths[points.get(i).c()] =
            Math.max(colMaxLengths[points.get(i).c()], maxLengths[i]);
      }
    }

    return Arrays.stream(rowMaxLengths).max().getAsInt();
  }
}

record Point(int r, int c) {}
