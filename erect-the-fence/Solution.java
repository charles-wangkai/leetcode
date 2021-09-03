import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

class Solution {
  public int[][] outerTrees(int[][] trees) {
    Point[] points =
        Arrays.stream(trees).map(tree -> new Point(tree[0], tree[1])).toArray(Point[]::new);

    Point origin =
        Arrays.stream(points)
            .min(Comparator.comparing((Point p) -> p.y).thenComparing(p -> p.x))
            .get();
    List<Point> rest =
        Arrays.stream(points)
            .filter(p -> p != origin)
            .sorted(
                (p1, p2) -> {
                  int x1 = p1.x - origin.x;
                  int y1 = p1.y - origin.y;
                  int x2 = p2.x - origin.x;
                  int y2 = p2.y - origin.y;

                  int slopeCmp = Integer.compare(y1 * x2, y2 * x1);

                  return (slopeCmp != 0)
                      ? slopeCmp
                      : -Integer.compare(x1 * x1 + y1 * y1, x2 * x2 + y2 * y2);
                })
            .collect(Collectors.toList());

    Map<Point, List<Point>> pointToShorts = new HashMap<>();
    for (int i = 1; i < rest.size(); ++i) {
      Point p1 = rest.get(i - 1);
      Point p2 = rest.get(i);

      int x1 = p1.x - origin.x;
      int y1 = p1.y - origin.y;
      int x2 = p2.x - origin.x;
      int y2 = p2.y - origin.y;

      if (y1 * x2 == y2 * x1) {
        rest.remove(i);

        if (!pointToShorts.containsKey(p1)) {
          pointToShorts.put(p1, new ArrayList<>());
        }
        pointToShorts.get(p1).add(p2);

        --i;
      }
    }

    List<Point> result = new ArrayList<>();
    result.add(origin);
    for (int i = 0; i < 2 && i < rest.size(); ++i) {
      result.add(rest.get(i));
    }

    for (int i = 2; i < rest.size(); ++i) {
      while (computeCrossProduct(
              result.get(result.size() - 2), result.get(result.size() - 1), rest.get(i))
          < 0) {
        result.remove(result.size() - 1);
      }
      result.add(rest.get(i));
    }

    List<Point> additions = new ArrayList<>();
    if (result.size() >= 2) {
      additions.addAll(pointToShorts.getOrDefault(result.get(1), List.of()));
    }
    if (result.size() >= 3) {
      additions.addAll(pointToShorts.getOrDefault(result.get(result.size() - 1), List.of()));
    }
    result.addAll(additions);

    return result.stream().map(p -> new int[] {p.x, p.y}).toArray(int[][]::new);
  }

  int computeCrossProduct(Point p0, Point p1, Point p2) {
    int x1 = p1.x - p0.x;
    int y1 = p1.y - p0.y;
    int x2 = p2.x - p0.x;
    int y2 = p2.y - p0.y;

    return x1 * y2 - x2 * y1;
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
