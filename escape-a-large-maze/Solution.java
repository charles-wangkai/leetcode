import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class Solution {
  static final int COMPONENT_LIMIT = 40000;
  static final int SIZE_LIMIT = 1000000;
  static final int[] R_OFFSETS = {-1, 0, 1, 0};
  static final int[] C_OFFSETS = {0, 1, 0, -1};

  public boolean isEscapePossible(int[][] blocked, int[] source, int[] target) {
    Set<Point> blockedSet =
        Arrays.stream(blocked).map(x -> new Point(x[0], x[1])).collect(Collectors.toSet());

    if (blockedSet.contains(new Point(source[0], source[1]))
        || blockedSet.contains(new Point(target[0], target[1]))) {
      return false;
    }

    Boolean result = search(blockedSet, source, target);
    if (result != null) {
      return result;
    }

    result = search(blockedSet, target, source);
    if (result != null) {
      return result;
    }

    return true;
  }

  Boolean search(Set<Point> blockedSet, int[] begin, int[] end) {
    Point beginPoint = new Point(begin[0], begin[1]);
    Point endPoint = new Point(end[0], end[1]);

    Set<Point> visited = new HashSet<>();
    Deque<Point> stack = new ArrayDeque<>();
    visited.add(beginPoint);
    stack.push(beginPoint);

    while (!stack.isEmpty()) {
      Point current = stack.pop();

      for (int i = 0; i < R_OFFSETS.length; i++) {
        int adjR = current.r + R_OFFSETS[i];
        int adjC = current.c + C_OFFSETS[i];

        if (adjR >= 0 && adjR < SIZE_LIMIT && adjC >= 0 && adjC < SIZE_LIMIT) {
          Point adjPoint = new Point(adjR, adjC);

          if (adjPoint.equals(endPoint)) {
            return true;
          }

          if (!visited.contains(adjPoint) && !blockedSet.contains(adjPoint)) {
            visited.add(adjPoint);
            stack.push(adjPoint);
          }
        }
      }

      if (visited.size() > COMPONENT_LIMIT) {
        return null;
      }
    }

    return false;
  }
}

class Point {
  int r;
  int c;

  Point(int r, int c) {
    this.r = r;
    this.c = c;
  }

  @Override
  public int hashCode() {
    return Objects.hash(c, r);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null) return false;
    if (getClass() != obj.getClass()) return false;
    Point other = (Point) obj;
    return c == other.c && r == other.r;
  }
}
