import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

class DetectSquares {
  Map<Point, Integer> pointToCount = new HashMap<>();

  public void add(int[] point) {
    Point p = new Point(point[0], point[1]);
    pointToCount.put(p, pointToCount.getOrDefault(p, 0) + 1);
  }

  public int count(int[] point) {
    int result = 0;
    for (Point p1 : pointToCount.keySet()) {
      Point p2 = null;
      Point p3 = null;
      if (p1.x == point[0] && p1.y > point[1]) {
        int size = p1.y - point[1];

        p2 = new Point(point[0] + size, p1.y);
        p3 = new Point(point[0] + size, point[1]);
      } else if (p1.x > point[0] && p1.y == point[1]) {
        int size = p1.x - point[0];

        p2 = new Point(p1.x, point[1] - size);
        p3 = new Point(point[0], point[1] - size);
      } else if (p1.x == point[0] && p1.y < point[1]) {
        int size = point[1] - p1.y;

        p2 = new Point(point[0] - size, p1.y);
        p3 = new Point(point[0] - size, point[1]);
      } else if (p1.x < point[0] && p1.y == point[1]) {
        int size = point[0] - p1.x;

        p2 = new Point(p1.x, point[1] + size);
        p3 = new Point(point[0], point[1] + size);
      }

      result +=
          pointToCount.get(p1)
              * pointToCount.getOrDefault(p2, 0)
              * pointToCount.getOrDefault(p3, 0);
    }

    return result;
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
    return Objects.hash(x, y);
  }

  @Override
  public boolean equals(Object obj) {
    Point other = (Point) obj;

    return x == other.x && y == other.y;
  }
}

// Your DetectSquares object will be instantiated and called as such:
// DetectSquares obj = new DetectSquares();
// obj.add(point);
// int param_2 = obj.count(point);
