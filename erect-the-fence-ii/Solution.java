// https://en.wikipedia.org/wiki/Smallest-circle_problem#Welzl's_algorithm
// https://leetcode.com/problems/erect-the-fence-ii/discuss/1347721/Welzl's-algorithm
// https://www.nayuki.io/page/smallest-enclosing-circle

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

class Solution {
  public double[] outerTrees(int[][] trees) {
    List<Point> points =
        Arrays.stream(trees).map(t -> new Point(t[0], t[1])).collect(Collectors.toList());
    Collections.shuffle(points);

    Circle circle = search(points, new ArrayList<>(), 0);

    return new double[] {circle.center.x, circle.center.y, circle.radius};
  }

  Circle search(List<Point> points, List<Point> boundaries, int index) {
    if (index == points.size() || boundaries.size() == 3) {
      return computeCircle(boundaries);
    }

    Circle circle = search(points, boundaries, index + 1);
    if (computeDistance(points.get(index), circle.center) > circle.radius) {
      boundaries.add(points.get(index));
      circle = search(points, boundaries, index + 1);
      boundaries.remove(boundaries.size() - 1);
    }

    return circle;
  }

  double computeDistance(Point p1, Point p2) {
    return Math.sqrt((p1.x - p2.x) * (p1.x - p2.x) + (p1.y - p2.y) * (p1.y - p2.y));
  }

  Circle computeCircle(List<Point> boundaries) {
    if (boundaries.isEmpty()) {
      return new Circle(new Point(0, 0), 0);
    } else if (boundaries.size() == 1) {
      return new Circle(boundaries.get(0), 0);
    } else if (boundaries.size() == 2) {
      Point p1 = boundaries.get(0);
      Point p2 = boundaries.get(1);

      return new Circle(
          new Point((p1.x + p2.x) / 2, (p1.y + p2.y) / 2), computeDistance(p1, p2) / 2);
    }

    return computeCircleFromThreePoints(boundaries.get(0), boundaries.get(1), boundaries.get(2));
  }

  Circle computeCircleFromThreePoints(Point a, Point b, Point c) {
    double ox = (Math.min(Math.min(a.x, b.x), c.x) + Math.max(Math.max(a.x, b.x), c.x)) / 2;
    double oy = (Math.min(Math.min(a.y, b.y), c.y) + Math.max(Math.max(a.y, b.y), c.y)) / 2;
    double ax = a.x - ox, ay = a.y - oy;
    double bx = b.x - ox, by = b.y - oy;
    double cx = c.x - ox, cy = c.y - oy;
    double d = (ax * (by - cy) + bx * (cy - ay) + cx * (ay - by)) * 2;
    if (d == 0) return null;
    double x =
        ((ax * ax + ay * ay) * (by - cy)
                + (bx * bx + by * by) * (cy - ay)
                + (cx * cx + cy * cy) * (ay - by))
            / d;
    double y =
        ((ax * ax + ay * ay) * (cx - bx)
                + (bx * bx + by * by) * (ax - cx)
                + (cx * cx + cy * cy) * (bx - ax))
            / d;
    Point p = new Point(ox + x, oy + y);
    double r =
        Math.max(Math.max(computeDistance(p, a), computeDistance(p, b)), computeDistance(p, c));
    return new Circle(p, r);
  }
}

class Circle {
  Point center;
  double radius;

  Circle(Point center, double radius) {
    this.center = center;
    this.radius = radius;
  }
}

class Point {
  double x;
  double y;

  Point(double x, double y) {
    this.x = x;
    this.y = y;
  }
}