// https://leetcode.com/problems/count-number-of-trapezoids-ii/solutions/6980653/python-parallel-lines-and-midpoints/

import java.util.HashMap;
import java.util.Map;

class Solution {
  public int countTrapezoids(int[][] points) {
    Map<Slope, Integer> slopeToCount = new HashMap<>();
    Map<Line, Integer> lineToCount = new HashMap<>();
    Map<Midpoint, Integer> midpointToCount = new HashMap<>();
    Map<MidpointLine, Integer> midpointLineToCount = new HashMap<>();

    for (int i = 0; i < points.length; ++i) {
      for (int j = i + 1; j < points.length; ++j) {
        int dx = points[i][0] - points[j][0];
        int dy = points[i][1] - points[j][1];
        int g = gcd(dx, dy);
        dx /= g;
        dy /= g;
        if (dx < 0 || (dx == 0 && dy < 0)) {
          dx *= -1;
          dy *= -1;
        }

        Slope slope = new Slope(dx, dy);
        slopeToCount.put(slope, slopeToCount.getOrDefault(slope, 0) + 1);

        Line line = new Line(slope, dx * points[i][1] - dy * points[i][0]);
        lineToCount.put(line, lineToCount.getOrDefault(line, 0) + 1);

        Midpoint midpoint = new Midpoint(points[i][0] + points[j][0], points[i][1] + points[j][1]);
        midpointToCount.put(midpoint, midpointToCount.getOrDefault(midpoint, 0) + 1);

        MidpointLine midpointLine = new MidpointLine(midpoint, line);
        midpointLineToCount.put(
            midpointLine, midpointLineToCount.getOrDefault(midpointLine, 0) + 1);
      }
    }

    return (int)
        ((slopeToCount.values().stream().mapToLong(this::computeNChoose2).sum()
                - lineToCount.values().stream().mapToLong(this::computeNChoose2).sum())
            - (midpointToCount.values().stream().mapToLong(this::computeNChoose2).sum()
                - midpointLineToCount.values().stream().mapToLong(this::computeNChoose2).sum()));
  }

  long computeNChoose2(int n) {
    return n * (n - 1L) / 2;
  }

  int gcd(int x, int y) {
    return (y == 0) ? x : gcd(y, x % y);
  }
}

record Slope(int dx, int dy) {}

record Line(Slope slope, int intercept) {}

record Midpoint(int sumX, int sumY) {}

record MidpointLine(Midpoint midpoint, Line line) {}
