import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class Solution {
  static final int[] X_OFFSETS = {-1, 0, 1, 0};
  static final int[] Y_OFFSETS = {0, 1, 0, -1};

  public int shortestDistance(int[][] grid) {
    Map<Point, Integer> point2totalDistance = null;

    int row = grid.length;
    int col = grid[0].length;
    for (int x = 0; x < row; x++) {
      for (int y = 0; y < col; y++) {
        if (grid[x][y] == 1) {
          Map<Point, Integer> point2distance = search(grid, x, y);
          point2totalDistance = merge(point2totalDistance, point2distance);

          if (point2totalDistance.isEmpty()) {
            return -1;
          }
        }
      }
    }

    return point2totalDistance.values().stream()
        .mapToInt(totalDistance -> totalDistance)
        .min()
        .getAsInt();
  }

  Map<Point, Integer> search(int[][] grid, int x, int y) {
    int row = grid.length;
    int col = grid[0].length;

    Point origin = new Point(x, y);
    Map<Point, Integer> point2distance = new HashMap<>();
    point2distance.put(origin, 0);
    Queue<Point> queue = new LinkedList<>();
    queue.offer(origin);
    while (!queue.isEmpty()) {
      Point head = queue.poll();
      for (int i = 0; i < X_OFFSETS.length; i++) {
        int nextX = head.x + X_OFFSETS[i];
        int nextY = head.y + Y_OFFSETS[i];
        Point nextPoint = new Point(nextX, nextY);
        if (nextX >= 0
            && nextX < row
            && nextY >= 0
            && nextY < col
            && grid[nextX][nextY] == 0
            && !point2distance.containsKey(nextPoint)) {
          point2distance.put(nextPoint, point2distance.get(head) + 1);
          queue.offer(nextPoint);
        }
      }
    }
    point2distance.remove(origin);
    return point2distance;
  }

  Map<Point, Integer> merge(
      Map<Point, Integer> point2totalDistance, Map<Point, Integer> point2distance) {
    Map<Point, Integer> merged;
    if (point2totalDistance == null) {
      merged = point2distance;
    } else {
      merged = new HashMap<>();
      for (Point point : point2totalDistance.keySet()) {
        if (point2distance.containsKey(point)) {
          merged.put(point, point2totalDistance.get(point) + point2distance.get(point));
        }
      }
    }
    return merged;
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
