import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

class Solution {
  public int[][] outerTrees(int[][] trees) {
    Point[] points =
        Arrays.stream(trees).map(tree -> new Point(tree[0], tree[1])).toArray(Point[]::new);

    Point origin =
        Arrays.stream(points).min(Comparator.comparing(Point::y).thenComparing(Point::x)).get();
    List<Point> rest =
        Arrays.stream(points)
            .filter(p -> p != origin)
            .sorted(
                (p1, p2) -> {
                  int x1 = p1.x() - origin.x();
                  int y1 = p1.y() - origin.y();
                  int x2 = p2.x() - origin.x();
                  int y2 = p2.y() - origin.y();

                  int slopeCmp = Integer.compare(y1 * x2, y2 * x1);

                  return (slopeCmp != 0)
                      ? slopeCmp
                      : Integer.compare(x1 * x1 + y1 * y1, x2 * x2 + y2 * y2);
                })
            .toList();

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

    result.addAll(
        Arrays.stream(points)
            .filter(p -> computeCrossProduct(result.get(0), result.get(result.size() - 1), p) == 0)
            .toList());

    return result.stream().distinct().map(p -> new int[] {p.x(), p.y()}).toArray(int[][]::new);
  }

  int computeCrossProduct(Point p0, Point p1, Point p2) {
    int x1 = p1.x() - p0.x();
    int y1 = p1.y() - p0.y();
    int x2 = p2.x() - p0.x();
    int y2 = p2.y() - p0.y();

    return x1 * y2 - x2 * y1;
  }
}

record Point(int x, int y) {}
