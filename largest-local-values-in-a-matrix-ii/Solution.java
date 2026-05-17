import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

class Solution {
  public int countLocalMaximums(int[][] matrix) {
    int n = matrix.length;
    int m = matrix[0].length;

    SortedMap<Integer, List<Point>> valueToPoints = new TreeMap<>(Comparator.reverseOrder());
    for (int r = 0; r < n; ++r) {
      for (int c = 0; c < m; ++c) {
        if (matrix[r][c] != 0) {
          valueToPoints.putIfAbsent(matrix[r][c], new ArrayList<>());
          valueToPoints.get(matrix[r][c]).add(new Point(r, c));
        }
      }
    }

    int result = 0;
    int[][] prefixSums = new int[n][m];
    for (List<Point> points : valueToPoints.values()) {
      for (Point point : points) {
        if (isLocalMaximum(prefixSums, point.r(), point.c(), matrix[point.r()][point.c()])) {
          ++result;
        }
      }

      for (Point point : points) {
        for (int c = point.c(); c < m; ++c) {
          ++prefixSums[point.r()][c];
        }
      }
    }

    return result;
  }

  boolean isLocalMaximum(int[][] prefixSums, int centerR, int centerC, int x) {
    int n = prefixSums.length;
    int m = prefixSums[0].length;

    for (int r = Math.max(0, centerR - x); r <= Math.min(n - 1, centerR + x); ++r) {
      int dc = x - ((Math.abs(r - centerR) == x) ? 1 : 0);
      if (computeRangeSum(prefixSums, r, Math.max(0, centerC - dc), Math.min(m - 1, centerC + dc))
          != 0) {
        return false;
      }
    }

    return true;
  }

  int computeRangeSum(int[][] prefixSums, int r, int beginC, int endC) {
    return prefixSums[r][endC] - ((beginC == 0) ? 0 : prefixSums[r][beginC - 1]);
  }
}

record Point(int r, int c) {}
