import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

class Solution {
  public int minGenerations(int[][] points, int[] target) {
    int result = 0;
    Set<Point> set =
        Arrays.stream(points)
            .map(point -> new Point(point[0], point[1], point[2]))
            .collect(Collectors.toSet());
    while (true) {
      if (set.contains(new Point(target[0], target[1], target[2]))) {
        return result;
      }

      Set<Point> added = new HashSet<>();
      for (Point p1 : set) {
        for (Point p2 : set) {
          Point p = new Point((p1.x() + p2.x()) / 2, (p1.y() + p2.y()) / 2, (p1.z() + p2.z()) / 2);
          if (!set.contains(p)) {
            added.add(p);
          }
        }
      }
      if (added.isEmpty()) {
        return -1;
      }

      set.addAll(added);
      ++result;
    }
  }
}

record Point(int x, int y, int z) {}
