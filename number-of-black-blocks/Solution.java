import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

class Solution {
  public long[] countBlackBlocks(int m, int n, int[][] coordinates) {
    Set<Point> blacks =
        Arrays.stream(coordinates)
            .map(coordinate -> new Point(coordinate[0], coordinate[1]))
            .collect(Collectors.toSet());

    Set<Point> topLefts = new HashSet<>();
    for (int[] coordinate : coordinates) {
      for (int dx = -1; dx <= 0; ++dx) {
        for (int dy = -1; dy <= 0; ++dy) {
          int x = coordinate[0] + dx;
          int y = coordinate[1] + dy;
          if (x >= 0 && x < m - 1 && y >= 0 && y < n - 1) {
            topLefts.add(new Point(x, y));
          }
        }
      }
    }

    long[] result = new long[5];
    for (Point topLeft : topLefts) {
      ++result[computeBlackNum(blacks, topLeft)];
    }
    result[0] = (m - 1L) * (n - 1) - result[1] - result[2] - result[3] - result[4];

    return result;
  }

  int computeBlackNum(Set<Point> blacks, Point topLeft) {
    int result = 0;
    for (int dx = 0; dx <= 1; ++dx) {
      for (int dy = 0; dy <= 1; ++dy) {
        if (blacks.contains(new Point(topLeft.x() + dx, topLeft.y() + dy))) {
          ++result;
        }
      }
    }

    return result;
  }
}

record Point(int x, int y) {}
