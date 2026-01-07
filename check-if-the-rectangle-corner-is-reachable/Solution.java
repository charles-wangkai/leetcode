// https://leetcode.com/problems/check-if-the-rectangle-corner-is-reachable/solutions/6531064/python-best-dsu-solution-here-by-awice-ozfu/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
  public boolean canReachCorner(int xCorner, int yCorner, int[][] circles) {
    Dsu dsu = new Dsu(circles.length + 4);

    for (int i = 0; i < circles.length; ++i) {
      if (isSegmentAndCircleIntersect(new Point(0, 0), new Point(0, yCorner), circles[i])) {
        dsu.union(i, circles.length);
      }
      if (isSegmentAndCircleIntersect(
          new Point(0, yCorner), new Point(xCorner, yCorner), circles[i])) {
        dsu.union(i, circles.length + 1);
      }
      if (isSegmentAndCircleIntersect(
          new Point(xCorner, yCorner), new Point(xCorner, 0), circles[i])) {
        dsu.union(i, circles.length + 2);
      }
      if (isSegmentAndCircleIntersect(new Point(xCorner, 0), new Point(0, 0), circles[i])) {
        dsu.union(i, circles.length + 3);
      }
    }

    for (int i = 0; i < circles.length; ++i) {
      for (int j = i + 1; j < circles.length; ++j) {
        if (isCirclesIntersect(
            xCorner,
            yCorner,
            circles[i][0],
            circles[i][1],
            circles[i][2],
            circles[j][0],
            circles[j][1],
            circles[j][2])) {
          dsu.union(i, j);
        }
      }
    }

    return !(dsu.find(circles.length) == dsu.find(circles.length + 2)
        || dsu.find(circles.length) == dsu.find(circles.length + 3)
        || dsu.find(circles.length + 1) == dsu.find(circles.length + 2)
        || dsu.find(circles.length + 1) == dsu.find(circles.length + 3));
  }

  boolean isSegmentAndCircleIntersect(Point p1, Point p2, int[] circle) {
    return isInCircle(p1, circle)
        || isInCircle(p2, circle)
        || ((p1.x() == p2.x())
            ? (Math.abs(circle[0] - p1.x()) <= circle[2]
                && isOnSegment(new Point(p1.x(), circle[1]), p1, p2))
            : (Math.abs(circle[1] - p1.y()) <= circle[2]
                && isOnSegment(new Point(circle[0], p1.y()), p1, p2)));
  }

  boolean isInCircle(Point p, int[] circle) {
    return square(p.x() - circle[0]) + square(p.y() - circle[1]) <= square(circle[2]);
  }

  boolean isOnSegment(Point p, Point p1, Point p2) {
    return p.x() >= Math.min(p1.x(), p2.x())
        && p.x() <= Math.max(p1.x(), p2.x())
        && p.y() >= Math.min(p1.y(), p2.y())
        && p.y() <= Math.max(p1.y(), p2.y());
  }

  boolean isCirclesIntersect(
      int xCorner, int yCorner, int x1, int y1, int r1, int x2, int y2, int r2) {
    int dx = x2 - x1;
    int dy = y2 - y1;

    long dd = square(dx) + square(dy);
    if (dd == 0 || dd < square(r1 - r2) || dd > square(r1 + r2)) {
      return false;
    }

    double d = Math.sqrt(dd);
    double a = (square(r1) - square(r2) + dd) / (2.0 * d);
    double h = Math.sqrt(square(r1) - a * a);
    double x = x1 + a * dx / d;
    double y = y1 + a * dy / d;
    double tx = -h * dy / d;
    double ty = h * dx / d;

    return isInRectangle(x - tx, y + ty, xCorner, yCorner)
        || isInRectangle(x + tx, y - ty, xCorner, yCorner);
  }

  boolean isInRectangle(double x, double y, int xCorner, int yCorner) {
    return x > 0 && x < xCorner && y > 0 && y < yCorner;
  }

  long square(int x) {
    return (long) x * x;
  }
}

record Point(int x, int y) {}

class Dsu {
  int[] parentOrSizes;

  Dsu(int n) {
    parentOrSizes = new int[n];
    Arrays.fill(parentOrSizes, -1);
  }

  int find(int a) {
    if (parentOrSizes[a] < 0) {
      return a;
    }

    parentOrSizes[a] = find(parentOrSizes[a]);

    return parentOrSizes[a];
  }

  void union(int a, int b) {
    int aLeader = find(a);
    int bLeader = find(b);
    if (aLeader != bLeader) {
      parentOrSizes[aLeader] += parentOrSizes[bLeader];
      parentOrSizes[bLeader] = aLeader;
    }
  }

  int getSize(int a) {
    return -parentOrSizes[find(a)];
  }

  Map<Integer, List<Integer>> buildLeaderToGroup() {
    Map<Integer, List<Integer>> leaderToGroup = new HashMap<>();
    for (int i = 0; i < parentOrSizes.length; ++i) {
      int leader = find(i);
      leaderToGroup.putIfAbsent(leader, new ArrayList<>());
      leaderToGroup.get(leader).add(i);
    }

    return leaderToGroup;
  }
}
