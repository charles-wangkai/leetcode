import java.util.HashMap;
import java.util.Map;

public class Solution {
  public boolean isReflected(int[][] points) {
    int n = points.length;

    if (n == 0) {
      return true;
    }

    int minX = Integer.MAX_VALUE;
    int maxX = Integer.MIN_VALUE;
    for (int[] point : points) {
      minX = Math.min(minX, point[0]);
      maxX = Math.max(maxX, point[0]);
    }

    return isSame(points, reflect(points, minX + maxX));
  }

  int[][] reflect(int[][] points, int sumX) {
    int[][] result = new int[points.length][2];
    for (int i = 0; i < result.length; i++) {
      result[i][0] = sumX - points[i][0];
      result[i][1] = points[i][1];
    }
    return result;
  }

  boolean isSame(int[][] points1, int[][] points2) {
    return buildPoint2count(points1).equals(buildPoint2count(points2));
  }

  Map<Point, Integer> buildPoint2count(int[][] points) {
    Map<Point, Integer> point2count = new HashMap<>();
    for (int[] p : points) {
      Point point = new Point(p[0], p[1]);

      if (!point2count.containsKey(point)) {
        point2count.put(point, 0);
      }
      point2count.put(point, point2count.get(point) + 1);
    }
    return point2count;
  }
}

class Point {
  int x;
  int y;

  Point(int x, int y) {
    this.x = x;
    this.y = y;
  }

  @Override
  public int hashCode() {
    return x * y;
  }

  @Override
  public boolean equals(Object obj) {
    Point other = (Point) obj;
    return x == other.x && y == other.y;
  }
}
