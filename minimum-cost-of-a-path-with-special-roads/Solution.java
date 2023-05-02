import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

class Solution {
  public int minimumCost(int[] start, int[] target, int[][] specialRoads) {
    Point[] points =
        Stream.concat(
                Stream.of(new Point(start[0], start[1]), new Point(target[0], target[1])),
                Arrays.stream(specialRoads)
                    .flatMap(
                        specialRoad ->
                            Stream.of(
                                new Point(specialRoad[0], specialRoad[1]),
                                new Point(specialRoad[2], specialRoad[3]))))
            .distinct()
            .toArray(Point[]::new);
    Map<Point, Integer> pointToIndex =
        IntStream.range(0, points.length).boxed().collect(Collectors.toMap(i -> points[i], i -> i));

    int[][] distances = new int[points.length][points.length];
    for (int i = 0; i < points.length; ++i) {
      for (int j = 0; j < points.length; ++j) {
        distances[i][j] =
            Math.abs(points[i].x() - points[j].x()) + Math.abs(points[i].y() - points[j].y());
      }
    }
    for (int[] specialRoad : specialRoads) {
      int fromIndex = pointToIndex.get(new Point(specialRoad[0], specialRoad[1]));
      int toIndex = pointToIndex.get(new Point(specialRoad[2], specialRoad[3]));

      distances[fromIndex][toIndex] = Math.min(distances[fromIndex][toIndex], specialRoad[4]);
    }

    for (int k = 0; k < points.length; ++k) {
      for (int i = 0; i < points.length; ++i) {
        for (int j = 0; j < points.length; ++j) {
          distances[i][j] = Math.min(distances[i][j], distances[i][k] + distances[k][j]);
        }
      }
    }

    return distances[pointToIndex.get(new Point(start[0], start[1]))][
        pointToIndex.get(new Point(target[0], target[1]))];
  }
}

record Point(int x, int y) {}
